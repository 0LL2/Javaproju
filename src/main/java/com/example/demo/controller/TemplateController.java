package com.example.demo.controller;

import com.example.demo.model.Event;
import com.example.demo.model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class TemplateController {

    @GetMapping("/all-events")
    public String allEvents(Model model) {
        // Lisää tyhjä lista tapahtumia malliin
        model.addAttribute("events", new ArrayList<>());
        return "all-events";
    }

    @GetMapping("/category-events")
    public String categoryEvents(Model model) {
        // Lisää tyhjä lista tapahtumia malliin
        model.addAttribute("events", new ArrayList<>());
        return "category-events";
    }

    @GetMapping("/event-form")
    public String eventForm(Model model) {
        // Lisää tyhjä tapahtuma ja tyhjä lista kategorioita malliin
        model.addAttribute("event", new Event());
        model.addAttribute("categories", new ArrayList<>());
        return "event-form";
    }

    @GetMapping("/category-form")
    public String categoryForm(Model model) {
        // Lisää tyhjä kategoria malliin
        model.addAttribute("category", new Category());
        return "category-form";
    }
}