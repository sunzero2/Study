package com.example.demo;

import com.example.demo.model.vo.Address;
import com.example.demo.model.vo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        Person person = new Person(null, "fisrt", address);

        // when
        Person savedPerson = redisRepository.save(person);

        // then
        Optional<Person> findPerson = redisRepository.findById(savedPerson.getId());

        assertThat(findPerson.isPresent()).isEqualTo(Boolean.TRUE);

        System.out.println(savedPerson);
    }
}
