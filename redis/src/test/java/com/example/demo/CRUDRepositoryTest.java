package com.example.demo;

import com.example.demo.model.Address;
import com.example.demo.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CRUDRepositoryTest {
    @Autowired
    private PersonRedisRepository redisRepository;

    @Test
    public void basicSave() {
        // given
        Address address = new Address("경기도 화성시 봉담읍");
        Person person = new Person(null, "fisrt", "last", address);

        // when
        Person savedPerson = redisRepository.save(person);

        // then
        Optional<Person> findPerson = redisRepository.findById(savedPerson.getId());

        assertThat(findPerson.isPresent()).isEqualTo(Boolean.TRUE);
        assertThat(findPerson.get().getFirstname()).isEqualTo(person.getFirstname());

        System.out.println(findPerson.get().getId());
    }
}
