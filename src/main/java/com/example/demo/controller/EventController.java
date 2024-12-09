package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Event;
import com.example.demo.service.CategoryService;
import com.example.demo.service.EventService;
import com.example.demo.repository.CategoryRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String getAllEvents(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "all-events";
    }

    @GetMapping("/event-form")
    public String showEventForm(Model model) {
        
        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("categories", categories);
        model.addAttribute("event", new Event());
        return "event-form";
    }

    @PostMapping
    public String createEvent(@ModelAttribute Event event, @RequestParam(value = "categoryId", required = false) Long categoryId) {
        if (categoryId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            event.setCategory(category);
        }
        eventService.saveEvent(event);
        return "redirect:/events";
    }

    @GetMapping("/{id}")
    public String getEventById(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "event-details";
    }

    @PutMapping("/{id}")
    public String updateEvent(@PathVariable Long id, @ModelAttribute Event event) {
        event.setId(id);
        eventService.saveEvent(event);
        return "redirect:/events";
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/events";
    }
}
