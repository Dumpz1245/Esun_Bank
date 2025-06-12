package com.esun.socialmedia.service;

import com.esun.socialmedia.model.LoginRequest;
import com.esun.socialmedia.model.User;

public interface UserService {
    User register(User user);
    User getById(Long id);
    User login(LoginRequest loginrequest);
}
