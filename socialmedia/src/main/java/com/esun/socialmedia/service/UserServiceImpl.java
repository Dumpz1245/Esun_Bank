package com.esun.socialmedia.service;

import com.esun.socialmedia.model.LoginRequest;
import com.esun.socialmedia.model.User;
import com.esun.socialmedia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    @Override
    public User register(User user) {
        System.out.println("🟠 Before encoding: " + user.getPassword_hash());
        user.setPassword_hash(passwordEncoder.encode(user.getPassword_hash()));

        User saved = userRepo.save(user);
        System.out.println("🟢 Saved user: " + saved);
        return saved;
    }
    @Override
    public User login(LoginRequest loginrequest) {
        User user = userRepo.findByPhoneNumber(loginrequest.getPhone_number());
        if (user == null || !passwordEncoder.matches(loginrequest.getPassword(), user.getPassword_hash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        return user;
    }

    @Override
    public User getById(Long id) {
        return userRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
