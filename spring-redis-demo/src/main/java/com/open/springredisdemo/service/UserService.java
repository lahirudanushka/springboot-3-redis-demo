package com.open.springredisdemo.service;


import com.open.springredisdemo.repository.User;
import com.open.springredisdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public User findById(String id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void delete(String id) {
        userRepository.delete(id);
    }

    public boolean exists(String id) {
        return userRepository.exists(id);
    }

    public void setExpire(String id, long timeout, TimeUnit unit) {
        userRepository.setExpire(id, timeout, unit);
    }
}

