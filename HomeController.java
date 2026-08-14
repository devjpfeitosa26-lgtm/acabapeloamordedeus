package com.unitickets.controller;

import com.unitickets.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping("/")
    public String home(Model model) {
        // Busca os eventos do MySQL e disponibiliza para o Thymeleaf na variável "eventos"
        model.addAttribute("eventos", eventoRepository.findAll());
        return "index"; // Abre o arquivo index.html em templates
    }
}
