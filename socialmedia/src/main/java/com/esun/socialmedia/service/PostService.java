package com.esun.socialmedia.service;

import com.esun.socialmedia.model.Post;
import java.util.List;

public interface PostService {
    Post createPost(Post post, String phoneNum);
    List<Post> getAllPosts();
    // List<Post> getPostsByUser(Long userId);
    Post updatePost(Long postId, String newContent);
    void deletePost(Long postId);
}
