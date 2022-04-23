package cn.edu.zjut.common.test;

import cn.edu.zjut.common.dao.CommentDao;
import cn.edu.zjut.common.domain.Comment;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    CommentDao commentDao;
    @Autowired
    SqlSession sqlSession;

    @Test
    public void testCRUD(){
        CommentDao dao = sqlSession.getMapper(CommentDao.class);
        Comment comment = new Comment(4, 5, 5, 5, "上课不错", 1);
        dao.createComment(comment);
    }

}

