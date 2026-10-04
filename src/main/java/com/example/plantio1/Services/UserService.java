package com.example.plantio1.Services;

import com.example.plantio1.Entity.User;
import com.example.plantio1.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    public List<User> getAllUser() {

        return userRepo.findAll();

    }
}
