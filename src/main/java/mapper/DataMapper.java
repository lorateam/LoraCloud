package mapper;

import java.util.List;
import model.Data;
import model.DataExample;
import org.apache.ibatis.annotations.Param;

public interface DataMapper {
    long countByExample(DataExample example);

    int deleteByExample(DataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Data record);

    int insertSelective(Data record);

    List<Data> selectByExample(DataExample example);

    //选择一个地点所有传感器的当前信息
    List<Data> selectAllSensorCurrentData(Data data);

    //选择一个地点某一个传感器的当前信息
    Data selectOneSensorCurrentData(Data data);

    //选择一个地点某一个传感器的过去十天每天的最高历史数据
    List<Data> getOneAddressOneHistoryHighSensorInfo(Data data);

    //选择一个地点某个传感器的过去十天每天的最低历史数据
    List<Data> getOneAddressOneHistoryLowSensorInfo(Data data);

    Data selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Data record, @Param("example") DataExample example);

    int updateByExample(@Param("record") Data record, @Param("example") DataExample example);

    int updateByPrimaryKeySelective(Data record);

    int updateByPrimaryKey(Data record);
}