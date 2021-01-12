package com.example.demo.service;

import com.example.demo.model.dao.RedisDao;
import com.example.demo.model.vo.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisDao redisDao;

    public void insertString(String key, String value) {
        redisDao.insertString(key, value);
    }

    public String selectString(String key) {
        return redisDao.selectString(key);
    }

    public void insertList(String key, List<String> values) {
        redisDao.insertList(key, values);
    }

    public void insertSet(String key, List<String> values) {
        redisDao.insertSet(key, values);
    }

    public void insertSortedSet(String key, List<String> values) {
        redisDao.insertSortedSet(key, values);
    }

    public void insertHash(String key, Map<String, String> values) {
        redisDao.insertHash(key, values);
    }

    public Person insertPerson(Person person) {
        return redisDao.insertPerson(person);
    }

    public Optional<Person> selectPerson(String id) {
        return redisDao.selectPerson(id);
    }
}
