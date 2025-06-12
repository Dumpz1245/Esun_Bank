package com.esun.socialmedia.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;
    private String user_name;
    private String email;
    private String password_hash;  // Hashed + Salted
    private String cover_image;
    private String biography;
    @Column(name = "phone_number")  // still maps to DB field
    private String phoneNumber;
}
