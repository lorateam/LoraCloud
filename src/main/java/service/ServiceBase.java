package service;

import mapper.*;
import model.Boxes;
import model.Data;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSessionFactory;

public class ServiceBase {
    private SqlSession sqlSession = MybatisSessionFactory.getSession();
    AddressMapper addressMapper = sqlSession.getMapper(AddressMapper.class);
    BoxesMapper boxesMapper = sqlSession.getMapper(BoxesMapper.class);
    DataMapper dataMapper = sqlSession.getMapper(DataMapper.class);
    SensorsMapper sensorsMapper = sqlSession.getMapper(SensorsMapper.class);
    VideoMapper videoMapper = sqlSession.getMapper(VideoMapper.class);
}
