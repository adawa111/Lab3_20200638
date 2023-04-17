package com.example.lab3.controller;

import com.example.lab3.entity.Hospital;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class InicioController {
    @RequestMapping("/**")
    public String handleDefault() {
        return "vistaInicial"; // Nombre de la vista predeterminada
    }



}
