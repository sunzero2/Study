package com.example.demo.controller;

import com.example.demo.model.vo.Address;
import com.example.demo.model.vo.Person;
import com.example.demo.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class RedisController {
    private final RedisService redisService;

    @PostMapping("/string")
    public void createString(@RequestParam("key") String key, @RequestParam("value") String value) {
        redisService.insertString(key, value);
    }

    @GetMapping("/string")
    public String readString(@RequestParam("key") String key) {
        System.out.println(key);
        return redisService.selectString(key);
    }

    @PostMapping("/list")
    public void insertList(@RequestParam("key") String key, @RequestParam("values") List<String> values) {
        redisService.insertList(key, values);
    }

    @PostMapping("set")
    public void insertSet(@RequestParam("key") String key, @RequestParam("values") List<String> values) {
        redisService.insertSet(key, values);
    }

    @PostMapping("sortedset")
    public void insertSortedSet(@RequestParam("key") String key, @RequestParam("values") List<String> values) {
        redisService.insertSortedSet(key, values);
    }

    @PostMapping("hash")
    public void insertHash(@RequestParam("key") String key, @RequestParam("values") Map<String, String> values) {
        redisService.insertHash(key, values);
    }

    @PostMapping("/person")
    public Person createPerson(@RequestParam("name") String name, @RequestParam("address") String address) {
        Person person = new Person(null, name, new Address(address));
        System.out.println(address);
        return redisService.insertPerson(person);
    }

    @GetMapping("/person")
    public Person readPerson(@RequestParam("id") String id) {
        return redisService.selectPerson(id).orElse(null);
    }
}
