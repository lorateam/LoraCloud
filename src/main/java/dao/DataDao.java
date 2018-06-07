package dao;

import mapper.BoxesMapper;
import mapper.DataMapper;
import model.Data;
import model.DataExample;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSessionFactory;

import java.util.List;

public class DataDao {
    private SqlSession sqlSession = MybatisSessionFactory.getSession();
    public DataMapper dataMapper = sqlSession.getMapper(DataMapper.class);
}
