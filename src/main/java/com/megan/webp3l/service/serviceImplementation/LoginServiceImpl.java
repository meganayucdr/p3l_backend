package com.megan.webp3l.service.serviceImplementation;

import com.megan.webp3l.repository.UserRepository;
import com.megan.webp3l.request.LoginRequest;
import com.megan.webp3l.response.DefaultResponse;
import com.megan.webp3l.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepository repository;

    @Override
    public DefaultResponse login(LoginRequest request) {
        if (repository.existsByEmailAndPassword(request.getEmail(), request.getPassword())) {
            return DefaultResponse.builder()
                    .data(repository.findUserByEmailAndPassword(request.getEmail(), request.getPassword()))
                    .message("Success")
                    .build();
        }
        else
            return DefaultResponse.builder()
                    .message("Failed")
                    .build();
    }
}
