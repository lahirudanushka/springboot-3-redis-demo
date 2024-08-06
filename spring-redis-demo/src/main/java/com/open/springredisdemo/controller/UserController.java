package com.open.springredisdemo.controller;

import com.open.springredisdemo.repository.User;
import com.open.springredisdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.TimeUnit;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user")
    ResponseEntity<String> save(@RequestBody User use) {
        userService.save(use);
        return ResponseEntity.ok(null);
    }

    @PostMapping(value = "/user/expire/{id}")
    ResponseEntity<String> expire(@PathVariable String id) {
        userService.setExpire(id,600, TimeUnit.SECONDS);
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = "/user/{id}")
    ResponseEntity<User> get(@PathVariable String id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping(value = "/user")
    ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }
    @DeleteMapping(value = "/user/{id}")
    ResponseEntity<String> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.ok(null);
    }




}
