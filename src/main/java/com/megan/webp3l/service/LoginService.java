package com.megan.webp3l.service;

import com.megan.webp3l.request.LoginRequest;
import com.megan.webp3l.response.DefaultResponse;

public interface LoginService {
    DefaultResponse login(LoginRequest request);
}
