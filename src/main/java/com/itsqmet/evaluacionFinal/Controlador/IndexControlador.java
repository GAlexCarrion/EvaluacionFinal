package com.itsqmet.evaluacionFinal.Controlador;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControlador {

    @GetMapping("/")
    public String mostrarIndex() {
        return "index";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }
}