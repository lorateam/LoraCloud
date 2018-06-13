package controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import dao.BoxesDao;
import dao.DataDao;
import mapper.AddressMapper;
import mapper.BoxesMapper;
import mapper.DataMapper;
import mapper.VideoMapper;
import model.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.MybatisSessionFactory;
import util.SpliceSensorInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
* 数据库应该配置长连接
*
* 返回数据方式使用return
*
* 数据库操作:简单查询,直接通过dao调用*注解mapper*方法,复杂查询在dao内使用example(尽量不要写xml)
*
* 配置事务自动提交,修复数据插入失败问题(已完成)
*
* 日志具体内容和格式进一步优化
*
* 今晚没时间能做到要用example的,明天再搞了
* */
@Controller
@RequestMapping("")
class MessageController{

    private static Logger logger = Logger.getLogger(MessageController.class);
    @RequestMapping("/action/postdata")
    @ResponseBody
    public String postData(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //解析数据
        String uuid,sensorInfor;
        try{
            uuid = request.getHeader("uuid");
            sensorInfor = request.getHeader("sensorInfo");
        }catch (Exception e){
            return "illegal message";
        }
        //数据交互
        BoxesDao boxesDao = new BoxesDao();
        DataDao dataDao = new DataDao();
        //简单查询
        Boxes boxes = boxesDao.boxesMapper.selectAddressIdByUuid(uuid);
        if(boxes == null)
            return "error:uuid didn't regester";
        Data data = new Data();
        data.setAddress_id(boxes.getAddress_id());
        Map<String, Float> dataMap = JSONObject.parseObject(sensorInfor, new TypeReference<Map<String, Float>>() {
        });
        String error = "";
        for (Map.Entry<String, Float> entry : dataMap.entrySet()) {
            data.setSensor_name(entry.getKey());
            data.setValue(entry.getValue());
            data.setUuid(uuid);
            try {
                dataDao.dataMapper.insert(data);
            }catch (Exception e) {
                return e.toString();
//                error += entry.getKey() + " undefined!\n";
            }
        }
        if(error.equals(""))
            return "success";
        return error;
    }
    //获取所有地点的基本信息
    @RequestMapping(value = "/action/baseInfo/address",produces="text/html;charset=UTF-8")
    @ResponseBody
    public JSONArray getAllAddressInfo() throws Exception{
        //-------------打开sqlSession
        SqlSession sqlSession = MybatisSessionFactory.getSession();
        //--------------获取信息
        AddressMapper addressMapper = sqlSession.getMapper(AddressMapper.class);
        AddressExample addressExample = new AddressExample();
        List<Address> addresses = addressMapper.selectByExample(addressExample);
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(addresses);
        logger.debug(jsonArray);
        sqlSession.close();
        return jsonArray;
    }
    //获取某一个地点的所有传感器信息
    @RequestMapping(value="/action/baseInfo/address/boxes",produces="text/html;charset=UTF-8")
    @ResponseBody
    public JSONArray getOneAddressBoxInfo(@RequestParam(value = "addressId")  int addressId) throws Exception{
        //-------------打开sqlSession
        SqlSession sqlSession = MybatisSessionFactory.getSession();
        logger.debug("打开sqlSession");
        //-------------开始执行
        BoxesMapper boxesMapper = sqlSession.getMapper(BoxesMapper.class);
        BoxesExample boxesExample = new BoxesExample();
        boxesExample.createCriteria().andAddress_idEqualTo(addressId);
        List<Boxes> boxesList = boxesMapper.selectByExample(boxesExample);
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(boxesList);
        logger.debug(jsonArray);
        sqlSession.close();
        return jsonArray;
    }

    //获取某一地点的所有传感器的当前信息
    @RequestMapping(value = "/action/currentInfor/address/boxes",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public JSONArray getOneAddressAllCurrentSensorInfo(@RequestParam(value = "addressId")  int addressId) throws Exception{
        //-------------打开sqlSession
        SqlSession sqlSession = MybatisSessionFactory.getSession();
        logger.debug("打开sqlSession");
        //-------------开始执行
        JSONArray jsonArray = new JSONArray();
        SpliceSensorInfo spliceSensorInfo = new SpliceSensorInfo();
        DataMapper dataMapper = sqlSession.getMapper(DataMapper.class);
        Data data = new Data();
        data.setAddress_id(addressId);
        List<Data> dataList = dataMapper.selectAllSensorCurrentData(data);
        for(Data d : dataList)
        {
            jsonArray.add(spliceSensorInfo.spliceInfo(d,sqlSession));
        }
        logger.debug(jsonArray);
        sqlSession.close();
        return jsonArray;
    }


    //获取某一地点的某一传感器的当前信息
    @RequestMapping(value="/action/currentInfo/address/boxes/sensor",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public JSONObject getOneAddressOneCurrentSensorInfo(@RequestParam(value = "addressId") int addressId,@RequestParam (value = "sensorName") String sensorName) throws Exception{
        //-------------打开sqlSession
        SqlSession sqlSession = MybatisSessionFactory.getSession();
        logger.debug("打开sqlSession");
        //-------------开始执行
        Data data = new Data();
        JSONObject jsonObject = new JSONObject();
        SpliceSensorInfo spliceSensorInfo = new SpliceSensorInfo();
        data.setAddress_id(addressId);
        data.setSensor_name(sensorName);
        DataMapper dataMapper = sqlSession.getMapper(DataMapper.class);
        data = dataMapper.selectOneSensorCurrentData(data);
        //根据data对象的信息查找sensor的名称和单位,并进行信息拼接
        jsonObject = spliceSensorInfo.spliceInfo(data,sqlSession);
        //拼接传感器信息
        logger.debug(jsonObject);
        sqlSession.close();
        return jsonObject;
    }

    //获取某一个地点的所有传感器过去十天的历史数据
//    @RequestMapping("/action/historyData/address/boxes")
//    @ResponseBody
//    public void getOneAddressAllHistorySensorInfo(HttpServletRequest request,HttpServletResponse response) throws Exception{
//        //-------------打开sqlSession
//        SqlSession sqlSession = MybatisSessionFactory.getSession();
//        logger.debug("打开sqlSession");
//        //-------------开始执行
//
//    }

    //获取某一个地点的某一个传感器过去十天的历史数据
    @RequestMapping(value = "/action/historyData/address/boxes/sensor",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public JSONArray getOneAddressOneHistorySensorInfo(@RequestParam(value = "addressId") int addressId,@RequestParam (value = "sensorName") String sensorName) throws Exception{
        //-------------打开sqlSession
        SqlSession sqlSession = MybatisSessionFactory.getSession();
        logger.debug("打开sqlSession");
        //开始执行
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Data data = new Data();
        data.setAddress_id(addressId);
        data.setSensor_name(sensorName);
        DataMapper dataMapper = sqlSession.getMapper(DataMapper.class);
        //创建Data对象储存过去十天每天的最高历史数据
        List<Data> dataHighList = dataMapper.getOneAddressOneHistoryHighSensorInfo(data);
        //创建Data对象储存过去十天每天的最低历史数据
        List<Data> dataLowList = dataMapper.getOneAddressOneHistoryLowSensorInfo(data);
        //创建Data对象组合最低、最高历史信息
        Data dHigh = new Data();
        Data dLow = new Data();
        //创建JSONArray组合最低历史信息
        JSONArray jsonArray = new JSONArray();
        for(int i = 0; i < dataHighList.size();i++)
        {
            JSONObject jsonObject = new JSONObject();
            dHigh = dataHighList.get(i);
            dLow = dataLowList.get(i);
            jsonObject.put("time",sdf.format(dHigh.getTime()));
            jsonObject.put("highValue",dHigh.getValue());
            jsonObject.put("lowValue", dLow.getValue());
            jsonArray.add(jsonObject);
        }
        System.out.println(jsonArray);
        sqlSession.close();
        return jsonArray;
    }

    //获取某一个地点的某一个传感器过去十天的历史数据
    @RequestMapping(value = "/action/video",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public List<Video> getVideo(@RequestParam(value = "addressId") int addressId) throws Exception{
        //-------------打开sqlSession
        SqlSession sqlSession = MybatisSessionFactory.getSession();
        logger.debug("打开sqlSession");
        VideoMapper videoMapper = sqlSession.getMapper(VideoMapper.class);
        VideoExample videoExample = new VideoExample();
        videoExample.createCriteria().andAdress_idEqualTo(addressId);
        return videoMapper.selectByExample(videoExample);
    }

}