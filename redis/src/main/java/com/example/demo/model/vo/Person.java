package com.example.demo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@AllArgsConstructor
@RedisHash("people")
public class Person {
    @Id
    String id;
    String name;
    Address address;
}
