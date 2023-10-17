package com.example.ProyectoTE.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {
    @GetMapping("/Home")
    public String homePage() {
        return "Home";
    }
}
