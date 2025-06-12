package com.esun.socialmedia.service;

import com.esun.socialmedia.model.Post;
import com.esun.socialmedia.model.User;
import com.esun.socialmedia.repository.PostRepository;
import com.esun.socialmedia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepo;
    private final UserRepository userRepo;
    @Transactional
    @Override
    public Post createPost(Post post, String phoneNum) {
        User user = userRepo.findByPhoneNumber(phoneNum);
        if (user == null) {
            throw new IllegalArgumentException("User not found with phone number: " + phoneNum);
        }
        post.setUser_Id(user); // properly assign the User entity
        post.setCreated_at(LocalDateTime.now());

        return postRepo.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    // @Override
    // public List<Post> getPostsByUser(Long userId) {
    //     return postRepo.findByUser_UserId(userId);
    // }

    @Override
    public Post updatePost(Long postId, String newContent) {
        Post post = postRepo.findById(postId).orElseThrow();
        post.setContent(newContent);
        return postRepo.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepo.deleteById(postId);
    }
}
