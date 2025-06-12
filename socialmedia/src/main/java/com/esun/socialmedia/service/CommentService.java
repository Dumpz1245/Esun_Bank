package com.esun.socialmedia.service;

import com.esun.socialmedia.model.Comment;
import java.util.List;

public interface CommentService {
    Comment addComment(Comment comment);
    List<Comment> getCommentsByPost(Long postId);
}
