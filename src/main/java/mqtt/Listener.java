package mqtt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import model.Data;
import org.apache.log4j.Logger;
import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.hawtdispatch.Task;
import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.DataService;

import javax.print.attribute.standard.DateTimeAtCompleted;

public class Listener {
    private final MQTT mqtt = new MQTT();
    private final ArrayList<Topic> topics = new ArrayList();
    private boolean debug;
    private boolean showTopic;
    private static Logger logger = Logger.getLogger(Listener.class);

    public Listener() {
    }

    private static void displayHelpAndExit(int exitCode) {
        stdout("");
        stdout("This is a simple mqtt client that will subscribe to topics and print all messages it receives.");
        stdout("");
        stdout("Arguments: [-h host] [-k keepalive] [-c] [-i id] [-u username [-p password]]");
        stdout("           [--will-topic topic [--will-payload payload] [--will-qos qos] [--will-retain]]");
        stdout("           [-d] [-s]");
        stdout("           ( [-q qos] -t topic )+");
        stdout("");
        stdout("");
        stdout(" -h : mqtt host uri to connect to. Defaults to tcp://localhost:1883.");
        stdout(" -k : keep alive in seconds for this client. Defaults to 60.");
        stdout(" -c : disable 'clean session' (store subscription and pending messages when client disconnects).");
        stdout(" -i : id to use for this client. Defaults to a random id.");
        stdout(" -u : provide a username (requires MQTT 3.1 broker)");
        stdout(" -p : provide a password (requires MQTT 3.1 broker)");
        stdout(" --will-topic : the topic on which to publish the client Will.");
        stdout(" --will-payload : payload for the client Will, which is sent by the broker in case of");
        stdout("                  unexpected disconnection. If not given and will-topic is set, a zero");
        stdout("                  length message will be sent.");
        stdout(" --will-qos : QoS level for the client Will.");
        stdout(" --will-retain : if given, make the client Will retained.");
        stdout(" -d : dispaly debug info on stderr");
        stdout(" -s : show message topics in output");
        stdout(" -q : quality of service level to use for the subscription. Defaults to 0.");
        stdout(" -t : mqtt topic to subscribe to. May be repeated multiple times.");
        stdout(" -v : MQTT version to use 3.1 or 3.1.1. (default: 3.1)");
        stdout("");
        System.exit(exitCode);
    }

    private static void stdout(Object x) {
        System.out.println(x);
    }

    private static void stderr(Object x) {
        System.err.println(x);
    }

    private static String shift(LinkedList<String> argl) {
        if (argl.isEmpty()) {
            stderr("Invalid usage: Missing argument");
            displayHelpAndExit(1);
        }

        return (String)argl.removeFirst();
    }

    public static void main() throws Exception {
        Listener main = new Listener();
        QoS qos = QoS.AT_MOST_ONCE;
        try {
            main.mqtt.setHost("tcp://118.24.3.29:1883");
            main.showTopic = false;
            main.topics.add(new Topic("#",qos));
        }catch (Exception e){
            e.printStackTrace();
        }
        main.execute();
        System.exit(0);
    }

    private void execute() {
        final CallbackConnection connection = this.mqtt.callbackConnection();
        final CountDownLatch done = new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                this.setName("MQTT client shutdown");
                if (Listener.this.debug) {
                    Listener.stderr("Disconnecting the client.");
                }

                connection.getDispatchQueue().execute(new Task() {
                    public void run() {
                        connection.disconnect(new Callback<Void>() {
                            public void onSuccess(Void value) {
                                done.countDown();
                            }

                            public void onFailure(Throwable value) {
                                done.countDown();
                            }
                        });
                    }
                });
            }
        });
        connection.listener(new org.fusesource.mqtt.client.Listener() {
            public void onConnected() {
                if (Listener.this.debug) {
                    Listener.stderr("Connected");
                }

            }

            public void onDisconnected() {
                if (Listener.this.debug) {
                    Listener.stderr("Disconnected");
                }

            }

            public void onPublish(UTF8Buffer topic, Buffer body, Runnable ack) {
                try {
                    if (Listener.this.showTopic) {
                        Listener.stdout("");
                        Listener.stdout("Topic: " + topic);
                        body.writeTo(System.out);
                        Listener.stdout("");
                    } else {
                        insertIntoDB(body);
                    }

                    ack.run();
                } catch (IOException var5) {
                    this.onFailure(var5);
                }

            }

            public void onFailure(Throwable value) {
                if (Listener.this.debug) {
                    value.printStackTrace();
                } else {
                    Listener.stderr(value);
                }

                System.exit(2);
            }
        });
        connection.resume();
        connection.connect(new Callback<Void>() {
            public void onFailure(Throwable value) {
                if (Listener.this.debug) {
                    value.printStackTrace();
                } else {
                    Listener.stderr(value);
                }

                System.exit(2);
            }

            public void onSuccess(Void value) {
                final Topic[] ta = (Topic[]) Listener.this.topics.toArray(new Topic[Listener.this.topics.size()]);
                connection.subscribe(ta, new Callback<byte[]>() {
                    public void onSuccess(byte[] value) {
                        if (Listener.this.debug) {
                            for(int i = 0; i < value.length; ++i) {
                                Listener.stderr("Subscribed to Topic: " + ta[i].name() + " with QoS: " + QoS.values()[value[i]]);
                            }
                        }

                    }

                    public void onFailure(Throwable value) {
                        Listener.stderr("Subscribe failed: " + value);
                        if (Listener.this.debug) {
                            value.printStackTrace();
                        }

                        System.exit(2);
                    }
                });
            }
        });

        try {
            done.await();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        System.exit(0);
    }

    private void insertIntoDB(Buffer body){
        String info = body.ascii().toString();
        try {
            Map<String, Float> dataMap = JSONObject.parseObject(info, new TypeReference<Map<String, Float>>() {
            });
            Data data = new Data();
            data.setAddress_id(1);
            for (Map.Entry<String, Float> entry : dataMap.entrySet()) {
                data.setSensor_name(entry.getKey());
                data.setValue(entry.getValue());
                data.setUuid("test");
                DataService dataService = new DataService();
                dataService.insert(data);
            }
        }catch (Exception e){
            logger.info("mqtt消息不是数据报文");
        }
    }
}
