package com.esun.socialmedia.model;
import lombok.Data;
@Data
public class LoginRequest {
    private String phone_number;
    private String password;
}