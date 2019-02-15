package com.jiedaibao.sbdemo.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileController {

    @ModelAttribute
    public void init(){
        System.out.println("init");
    }
    @RequestMapping("/test")
    public String test() throws Exception {
        return "hello world";
    }

    public static void main(String[] args) {

    }
}
