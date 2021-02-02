package com.xhh_study1.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestHelloController2 {

    @GetMapping("/testSpringbootStart")
    public String testSpringbootStart(@RequestParam(name="name", required=false, defaultValue="World?") String name, Model model) {
        model.addAttribute("name", name);
        System.out.println("111");
        return "testSpringbootStart";
    }


}