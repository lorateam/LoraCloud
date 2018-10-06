package mapper;

import java.util.List;
import model.Data;
import model.DataExample;
import mqtt.Listener;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface DataMapper {
    long countByExample(DataExample example);

    int deleteByExample(DataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Data record);

    int insertSelective(Data record);

    @Insert("insert into data(sensor_name, value, address_id, time, uuid) values #{datas}")
    int insertDatas(@Param("datas") List<Data> datas);

    List<Data> selectByExample(DataExample example);

    //选择一个地点所有传感器的当前信息
    @Select("SELECT * from (SELECT * from data WHERE address_id = #{address_id} ORDER BY time DESC ) b GROUP BY sensor_name")
    List<Data> selectAllSensorCurrentData(int address_id);

    //选择一个地点某一个传感器的当前信息
    @Select("SELECT * from data WHERE address_id=#{address_id} and sensor_name = #{sensor_name} ORDER BY time DESC LIMIT 1")
    Data selectOneSensorCurrentData(Data data);

    //选择一个地点某一个传感器的过去十天每天的最高历史数据
    @Select("select id,sensor_name,max(value)as value,address_id,time,uuid from data where sensor_name=#{sensor_name}and address_id = #{address_id}" +
            " GROUP BY date_format(time,\"%Y-%m-%d\") ORDER BY time DESC  LIMIT 10;")
    List<Data> getOneAddressOneHistoryHighSensorInfo(Data data);

    //选择一个地点某个传感器的过去十天每天的最低历史数据
    @Select("select id,sensor_name,min(value)as value,address_id,time,uuid from data " +
            "where sensor_name=#{sensor_name}and address_id = #{address_id} " +
            "GROUP BY date_format(time,\"%Y-%m-%d\") ORDER BY time DESC  LIMIT 10;")
    List<Data> getOneAddressOneHistoryLowSensorInfo(Data data);

    Data selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Data record, @Param("example") DataExample example);

    int updateByExample(@Param("record") Data record, @Param("example") DataExample example);

    int updateByPrimaryKeySelective(Data record);

    int updateByPrimaryKey(Data record);
}