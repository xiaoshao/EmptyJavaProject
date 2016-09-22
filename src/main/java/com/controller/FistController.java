package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Map;

@Controller
public class FistController {

    @RequestMapping("hello/{name}")
    @ResponseBody
    public String hello(@PathVariable("name") String name) {
        return "hello " + name;
    }
}
