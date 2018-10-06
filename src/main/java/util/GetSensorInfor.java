//package util;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.ibatis.session.SqlSession;
//
//import java.util.List;
//import java.util.Map;
//
//public class GetSensorInfor {
//    public void getOneSensorCurrentInfor(Boxes box, JSONArray jsonArray, SqlSession sqlSession)
//    {
//        if(box.getTableName().equals("b_001"))
//        {
//            System.out.println("存在b_001");
//            B001Mapper b001Mapper = sqlSession.getMapper(B001Mapper.class);
//            B001 b001 = new B001();
//            b001.setType(box.getId());
//            b001 = b001Mapper.selectCurrentInfor(b001);
//            jsonArray.add(b001);
//        }
//        else if(box.getTableName().equals("b_002"))
//        {
//            System.out.println("存在b_002");
//            B002Mapper b002Mapper = sqlSession.getMapper(B002Mapper.class);
//            B002 b002 = new B002();
//            b002.setType(box.getId());
//            b002 = b002Mapper.selectCurrentInfor(b002);
//            jsonArray.add(b002);
//        }
//        else if(box.getTableName().equals("b_005"))
//        {
//            System.out.println("存在b_005");
//            B005Mapper b005Mapper = sqlSession.getMapper(B005Mapper.class);
//            B005 b005 = new B005();
//            b005.setType(box.getId());
//            b005 = b005Mapper.selectCurrentInfor(b005);
//            jsonArray.add(b005);
//        }
//    }
//    //TODO:一个盒子的所有传感器信息
//    public void getOneBoxAllSensorHistoryInfor(Boxes box, JSONArray jsonArray, SqlSession sqlSession){
//        if(box.getTableName().equals("b_001")){
//            B001Mapper b001Mapper = sqlSession.getMapper(B001Mapper.class);
//            List<Map<String,String>> highMaps = b001Mapper.selectAllSensorHighHistoryInfor();
//            List<Map<String,String>> lowMaps = b001Mapper.selectAllSensorLowHistoryInfor();
//            JSONObject allSensorHighHistory = new JSONObject();
//            JSONObject allSensorLowHistory  = new JSONObject();
//            allSensorHighHistory.put("high",highMaps);
//            allSensorLowHistory.put("low",lowMaps);
//            jsonArray.add(allSensorHighHistory);
//            jsonArray.add(allSensorLowHistory);
//        }
//        else if(box.getTableName().equals("b_002")){
//
//        }
//        else if(box.getTableName().equals("b_005")){
//
//        }
//    }
//
//    public void getOneBoxOneSensorHistoryInfor(Boxes box,String sensorName,JSONArray jsonArray,SqlSession sqlSession){
//        if(box.getTableName().equals("b_001")){
//            if(sensorName.equals("temp")){
//
//            }
//        }
//        else if(box.getTableName().equals("b_002")){
//
//        }
//        else if(box.getTableName().equals("b_005")){
//
//        }
//    }
//}
