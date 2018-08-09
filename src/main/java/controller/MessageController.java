package controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import mapper.AddressMapper;
import mapper.BoxesMapper;
import mapper.DataMapper;
import mapper.VideoMapper;
import model.*;
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


/*
* 数据库应该配置长连接
*
* 返回数据方式使用return
*
* 数据库操作:简单查询,直接通过dao调用*注解mapper*方法,复杂查询在dao内使用example(尽量不要写xml)
*
* 配置事务自动提交,修复数据插入失败问题(已完成)
*d
* 日志具体内容和格式进一步优化
*
* 今晚没时间能做到要用example的,明天再搞了
* */
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

    @RequestMapping("/action/postdata")
    @ResponseBody
    public String postData(HttpServletRequest request) {
        //解析数据
        String uuid,sensorInfor;
        try{
            uuid = request.getHeader("uuid");
            sensorInfor = request.getHeader("sensorInfo");
        }catch (Exception e){
            return "uuid or sensorInfo not found in headers";
        }

        //简单查询
        Boxes boxes = boxesService.getBox(uuid);
        if(boxes == null)
            return "error:uuid didn't regester";
        Data data = new Data();
        data.setAddress_id(boxes.getAddress_id());
        Map<String, Float> dataMap = JSONObject.parseObject(sensorInfor, new TypeReference<Map<String, Float>>() {
        });
        for (Map.Entry<String, Float> entry : dataMap.entrySet()) {
            data.setSensor_name(entry.getKey());
            data.setValue(entry.getValue());
            data.setUuid(uuid);
            try {
                dataService.insert(data);
            }catch (Exception e) {
                return e.toString();
            }
        }
        return "success";
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
    public List<Boxes> getOneAddressBoxInfo(Integer addressId) {
        return boxesService.listBoxes(addressId);
    }

    //获取某一地点的所有传感器的当前信息
    @RequestMapping(value = "/action/currentInfor/address/boxes",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public List<Data> getOneAddressAllCurrentSensorInfo(@RequestParam(value = "addressId")  int addressId) throws Exception{
        return null;
    }


    //获取某一地点的某一传感器的当前信息
    @RequestMapping(value="/action/currentInfo/address/boxes/sensor",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Data getOneAddressOneCurrentSensorInfo(@RequestParam(value = "addressId") int addressId,@RequestParam (value = "sensorName") String sensorName) throws Exception{
        return null;
    }

    //获取某一个地点的某一个传感器过去十天的历史数据
    @RequestMapping(value = "/action/historyData/address/boxes/sensor",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public List<Data> getOneAddressOneHistorySensorInfo(Integer addressId, String sensorName){
        return null;
    }

    //获取某一个地点的所有监控信息
    @RequestMapping(value = "/action/video",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public List<Video> getVideoInfo(Integer addressId) {
        return videoService.listVideos(addressId);
    }
}