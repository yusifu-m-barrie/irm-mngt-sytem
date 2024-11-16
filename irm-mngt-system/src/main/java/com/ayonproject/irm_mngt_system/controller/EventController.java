package com.ayonproject.irm_mngt_system.controller;

import com.ayonproject.irm_mngt_system.model.Event;
import com.ayonproject.irm_mngt_system.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/events")
    public Event saveEvent(@RequestBody Event event) {
        return eventService.saveEvent(event);
    }

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") Long id) {
        Event event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        Event updatedEvent = eventService.updateEvent(id, event);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEvent(@PathVariable Long id) {
        boolean deleted = eventService.deleteEvent(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/events/count")  // Endpoint is now /api/v1/events/count
    public Long countEvents() {
        return eventService.countEvents();
    }
}
