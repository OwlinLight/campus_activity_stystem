package cn.edu.zjut.common.service;

import cn.edu.zjut.common.domain.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> listActivityComment(Long id);

    int createComment(Comment comment);

}
