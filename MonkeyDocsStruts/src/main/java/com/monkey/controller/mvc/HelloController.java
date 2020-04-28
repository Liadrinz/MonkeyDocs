package com.monkey.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Hello")
public class HelloController {
    @RequestMapping("/Sayhi")
    @ResponseBody
    public String hello() {
        return "Hello!";
    }
}
