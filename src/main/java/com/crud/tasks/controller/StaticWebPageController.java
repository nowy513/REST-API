package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.Objects;

@Controller
public class StaticWebPageController {

    @RequestMapping("/")
    public String index(Map<String, Object> model){
        model.put("variable", "My Thymeleaf variable");
        model.put("one", 1);
        model.put("two", 2);
        model.put("first_operation", "2 * 2 = ");
        model.put("second_operation", "2 * 2 + 2 = ");
        model.put("third_operation", "2 - 2 * 2 = ");
        return "index";
    }
}
