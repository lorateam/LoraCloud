package mapper;

import java.util.List;
import model.Boxes;
import model.BoxesExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.swing.*;

public interface BoxesMapper {
    long countByExample(BoxesExample example);

    int deleteByExample(BoxesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Boxes record);

    int insertSelective(Boxes record);

    List<Boxes> selectByExample(BoxesExample example);

    Boxes selectByPrimaryKey(Integer id);

    @Select("select * from boxes where uuid=#{uuid}")
    Boxes selectAddressIdByUuid(String uuid);

    int updateByExampleSelective(@Param("record") Boxes record, @Param("example") BoxesExample example);

    int updateByExample(@Param("record") Boxes record, @Param("example") BoxesExample example);

    int updateByPrimaryKeySelective(Boxes record);

    int updateByPrimaryKey(Boxes record);
}