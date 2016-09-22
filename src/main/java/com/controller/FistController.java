package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FistController {

    @RequestMapping("hello/{name}")
    @ResponseBody
    public String hello(@PathVariable("name") String name) {
        return "hello " + name;
    }
}
