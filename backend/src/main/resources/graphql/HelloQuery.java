package com.loopit.backend.controller;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class HelloQuery {

    @QueryMapping
    public String hello() {
        return "Hello from Loopit GraphQL!";
    }
}