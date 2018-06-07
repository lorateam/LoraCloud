package dao;

import mapper.BoxesMapper;
import model.Boxes;
import model.BoxesExample;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSessionFactory;

public class BoxesDao {
    private SqlSession sqlSession = MybatisSessionFactory.getSession();
    public BoxesMapper boxesMapper = sqlSession.getMapper(BoxesMapper.class);

}
