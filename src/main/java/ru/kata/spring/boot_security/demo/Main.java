package ru.kata.spring.boot_security.demo;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> jsonToSend = new HashMap<>();
        jsonToSend.put("name", "TestTest");
        jsonToSend.put("job", "TestTest");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonToSend);

        String url = "https://reqres.in/api/users";
        String response = restTemplate.postForObject(url, request, String.class);

        System.out.println(response);

    }
}