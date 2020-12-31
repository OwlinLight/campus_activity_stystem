package cn.edu.zjut.common.service.impl;

import cn.edu.zjut.common.dao.CommentDao;
import cn.edu.zjut.common.domain.Comment;
import cn.edu.zjut.common.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;
    @Override
    public List<Comment> listActivityComment(long id) {
        return commentDao.listActivityComment(id);
    }
}
