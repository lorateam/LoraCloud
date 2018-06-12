package util;

import com.alibaba.fastjson.JSONObject;
import mapper.SensorsMapper;
import model.Data;
import model.Sensors;
import org.apache.ibatis.session.SqlSession;

public class SpliceSensorInfo {
    public JSONObject spliceInfo(Data data, SqlSession sqlSession){
        JSONObject jsonObject = new JSONObject();
        SensorsMapper sensorsMapper = sqlSession.getMapper(SensorsMapper.class);
        Sensors sensors = new Sensors();
        try
        {
            sensors = sensorsMapper.selectBySensorName(data.getSensor_name());
            jsonObject.put("name",sensors.getNote());
            jsonObject.put("unit",sensors.getUnit());
            jsonObject.put("value",data.getValue());
            jsonObject.put("time",data.getTime());
            jsonObject.put("sensorName", data.getSensor_name());
            jsonObject.put("addressId", data.getAddress_id());
            return jsonObject;
        }catch (Exception e){
            //如果未查询到对象，则返回空对象
            jsonObject.put("name",null);
            jsonObject.put("unit",null);
            jsonObject.put("value",null);
            jsonObject.put("time",null);
            jsonObject.put("sensorName", null);
            return jsonObject;
        }

    }
}
