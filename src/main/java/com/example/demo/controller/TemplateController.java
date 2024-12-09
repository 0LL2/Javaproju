package com.example.demo.controller;

import com.example.demo.model.Event;
import com.example.demo.model.Category;
import com.example.demo.service.EventService;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TemplateController {

    @Autowired
    private EventService eventService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/all-events")
    public String allEvents(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "all-events";
    }

    @GetMapping("/category-events")
    public String categoryEvents(Model model) {
        model.addAttribute("events", new ArrayList<>());
        return "category-events";
    }

    @GetMapping("/event-form")
    public String eventForm(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "event-form";
    }

    @GetMapping("/category-form")
    public String categoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category-form";
    }

    @GetMapping("/calendar")
    public String calendar(Model model) {
        List<Event> events = eventService.getAllEvents();
        events.sort((e1, e2) -> e1.getDateTime().compareTo(e2.getDateTime()));
        model.addAttribute("events", events);
        return "calendar";
    }

    @GetMapping("/category/{id}")
    public String categoryEvents(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        if (category != null) {
            model.addAttribute("events", category.getEvents());
            model.addAttribute("category", category);
        } else {
            model.addAttribute("events", new ArrayList<>());
            model.addAttribute("category", new Category());
        }
        return "category-events";
    }

    @GetMapping("/event/{id}")
    public String eventDetails(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "event-details";
    }
}