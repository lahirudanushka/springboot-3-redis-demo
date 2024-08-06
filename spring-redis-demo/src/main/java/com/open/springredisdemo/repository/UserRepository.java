package com.open.springredisdemo.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Repository
public class UserRepository {

    @Value("${spring.data.redis.env}")
    private String env;

    private static final String KEY = "User";


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void save(User user) {
        redisTemplate.opsForHash().put(generateKey(user.getId()), user.getId(), user);
    }

    public User findById(String id) {
        return (User) redisTemplate.opsForHash().get(generateKey(id), id);
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return redisTemplate.keys(generateKey("*")).stream()
                .map(key -> findById(key.split(":")[2]))
                .collect(Collectors.toList());
    }

    public void delete(String id) {
        redisTemplate.opsForHash().delete(generateKey(id), id);
    }

    public boolean exists(String id) {
        return redisTemplate.opsForHash().hasKey(generateKey(id), id);
    }

    public void setExpire(String id, long timeout, TimeUnit unit) {
        redisTemplate.expire(generateKey(id), timeout, unit);
    }

    public String generateKey( String id) {
        return this.env + ":" + KEY + ":" + id;
    }
}

