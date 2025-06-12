package com.esun.socialmedia.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "Post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_Id;
    
    private String content;  
    private String image;
    @Column(name = "created_at")
    private LocalDateTime created_at;
}
