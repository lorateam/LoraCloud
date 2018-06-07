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
        sensors = sensorsMapper.selectBySensorName(data.getSensor_name());
        jsonObject.put("name",sensors.getNote());
        jsonObject.put("unit",sensors.getUnit());
        jsonObject.put("value",data.getValue());
        jsonObject.put("time",data.getTime());
        return jsonObject;
    }
}
