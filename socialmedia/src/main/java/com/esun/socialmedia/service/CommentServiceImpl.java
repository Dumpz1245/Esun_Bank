package com.esun.socialmedia.service;

import com.esun.socialmedia.model.Comment;
import com.esun.socialmedia.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepo;
    @Transactional
    @Override
    public Comment addComment(Comment comment) {
        comment.setCreated_at(LocalDateTime.now());
        return commentRepo.save(comment);
    }

    @Override
    public List<Comment> getCommentsByPost(Long postId) {
        return commentRepo.findByPost_PostId(postId);
    }
}
