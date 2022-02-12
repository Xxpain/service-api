package io.github.xxpain.serviceapi.test.service;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImp implements TestService {

    @Override
    public String test(String name) {
        return "input str: " + name;
    }

}
