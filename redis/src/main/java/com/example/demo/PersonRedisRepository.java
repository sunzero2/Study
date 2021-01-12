package com.example.demo;

import com.example.demo.model.vo.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRedisRepository extends CrudRepository<Person, String> {
}
