package com.example.demo.model.dao;

import com.example.demo.PersonRedisRepository;
import com.example.demo.model.vo.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class RedisDao {
    private final StringRedisTemplate redisTemplate;
    private final PersonRedisRepository redisRepository;

    public void insertString(String key, String value) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    public String selectString(String key) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public void insertList(String key, List<String> values) {
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        for (String value : values) {
            listOperations.rightPush(key, value);
        }
    }

    public void insertSet(String key, List<String> values) {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        for (String value : values) {
            setOperations.add(key, value);
        }
    }

    public void insertSortedSet(String key, List<String> values) {
        ZSetOperations<String, String> setOperations = redisTemplate.opsForZSet();
        for (int i = 0; i < values.size(); i++) {
            setOperations.add(key, values.get(i), i);
        }
    }

    public void insertHash(String key, Map<String, String> values) {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        Iterator<String> keySet = values.keySet().iterator();
        while (keySet.hasNext()) {
            String _key = keySet.next();
            hashOperations.put(key, _key, values.get(_key));
        }
    }

    public Person insertPerson(Person person) {
        return redisRepository.save(person);
    }

    public Optional<Person> selectPerson(String id) {
        return redisRepository.findById(id);
    }
}
