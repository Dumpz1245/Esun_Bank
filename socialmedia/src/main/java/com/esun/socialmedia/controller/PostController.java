package com.esun.socialmedia.controller;

import com.esun.socialmedia.model.Post;
import com.esun.socialmedia.model.User;
import com.esun.socialmedia.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

 
    @PostMapping
    public ResponseEntity<Post> create(@RequestParam("content") String content,
                                    @RequestParam("phoneNumber") String phoneNumber) {
        System.out.println("creation attempted");

        Post post = new Post();
        post.setContent(content);

        return ResponseEntity.ok(postService.createPost(post, phoneNumber));
    }
    @GetMapping
    public ResponseEntity<List<Post>> getAll() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    // @GetMapping("/user/{userId}")
    // public ResponseEntity<List<Post>> getByUser(@PathVariable Long userId) {
    //     return ResponseEntity.ok(postService.getPostsByUser(userId));
    // }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> update(@PathVariable Long postId, @RequestBody String newContent) {
        return ResponseEntity.ok(postService.updatePost(postId, newContent));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> delete(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
