package controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import mapper.AddressMapper;
import mapper.BoxesMapper;
import mapper.DataMapper;
import mapper.VideoMapper;
import model.*;
import mqtt.Listener;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AddressService;
import service.BoxesService;
import service.DataService;
import service.VideoService;
import util.MybatisSessionFactory;
import util.SpliceSensorInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("")
class MessageController{
    private static Logger logger = Logger.getLogger(MessageController.class);
    @Autowired
    BoxesService boxesService;
    @Autowired
    DataService dataService;
    @Autowired
    AddressService addressService;
    @Autowired
    VideoService videoService;

    public MessageController(){
        //启动mqtt数据收集
        new Thread(new Runnable() {
            @Override
            public void run(){
                try{
                    Listener.main();
                }catch (Exception e){
                    logger.info(null);
                }
            }
        }).start();
    }

    //获取所有地点的基本信息
    @RequestMapping(value = "/action/baseInfo/address",produces="text/html;charset=UTF-8")
    @ResponseBody
    public List<Address> getAllAddressInfo() throws Exception{
        return addressService.listAddress();
    }

    //获取某一个地点的所有传感器信息
    @RequestMapping(value="/action/baseInfo/address/boxes",produces="text/html;charset=UTF-8")
    @ResponseBody
    public List<Boxes> getOneAddressBoxInfo(Integer address_id) {
        return boxesService.listBoxes(address_id);
    }

    //获取某一地点的所有传感器的当前信息
    @RequestMapping(value = "/action/currentInfor/address/boxes",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public List<Data> getOneAddressAllCurrentSensorInfo(Data data) throws Exception{
        return dataService.allCurrentData(data);
    }


    //获取某一地点的某一传感器的当前信息
    @RequestMapping(value="/action/currentInfo/address/boxes/sensor",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Data getOneAddressOneCurrentSensorInfo(Data data) throws Exception{
        return dataService.oneCurrentData(data);
    }

    //获取某一个地点的某一个传感器过去十天的历史数据
    @RequestMapping(value = "/action/historyData/address/boxes/sensor",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public List<Data> getOneAddressOneHistorySensorInfo(Data data){ //address_id, sensor_name
        List<Data> low = dataService.historyLowData(data);
        List<Data> high = dataService.historyHighData(data);
        return null;
    }

    //获取某一个地点的所有监控信息
    @RequestMapping(value = "/action/video",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public List<Video> getVideoInfo(Integer address_id) {
        return videoService.listVideos(address_id);
    }

    //添加新的芯片uuid信息
    @RequestMapping(value = "/setbox",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String setbox(@Param("password")String password, Boxes boxes) {
        if (!password.equals("scumaker"))
            return "密码错误，添加失败！";
        try{
            boxesService.insert(boxes);
            return "success!";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}