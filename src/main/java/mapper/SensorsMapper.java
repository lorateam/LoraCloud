package mapper;

import java.util.List;
import model.Sensors;
import model.SensorsExample;
import org.apache.ibatis.annotations.Param;

public interface SensorsMapper {
    long countByExample(SensorsExample example);

    int deleteByExample(SensorsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Sensors record);

    int insertSelective(Sensors record);

    List<Sensors> selectByExample(SensorsExample example);

    Sensors selectByPrimaryKey(Integer id);

    Sensors selectBySensorName(String sensorName);

    int updateByExampleSelective(@Param("record") Sensors record, @Param("example") SensorsExample example);

    int updateByExample(@Param("record") Sensors record, @Param("example") SensorsExample example);

    int updateByPrimaryKeySelective(Sensors record);

    int updateByPrimaryKey(Sensors record);
}