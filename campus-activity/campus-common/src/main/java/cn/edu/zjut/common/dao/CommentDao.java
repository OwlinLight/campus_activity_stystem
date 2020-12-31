package cn.edu.zjut.common.dao;

import cn.edu.zjut.common.domain.Comment;
import cn.edu.zjut.common.domain.Showac;

import java.util.List;

public interface CommentDao {
    List<Comment> listActivityComment(Long activity_id);
}
