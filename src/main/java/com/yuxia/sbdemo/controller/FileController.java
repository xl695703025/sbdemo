package com.yuxia.sbdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/file")
public class FileController {

    @ModelAttribute
    public void init(){
        System.out.println("init");
    }
    @RequestMapping("/test")
    public String test(Model model , HttpServletRequest request) throws Exception {
        model.addAttribute("userName","yuxia...");
        request.setAttribute("ssss","sss");
        return "index";
    }

    public static void main(String[] args) {

    }
}
