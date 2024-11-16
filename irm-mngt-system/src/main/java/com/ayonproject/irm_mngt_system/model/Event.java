package com.ayonproject.irm_mngt_system.model;

import java.time.LocalDate;

public class Event {
    private Long id;
    private String title;
    private String description;
    private String location;
    private LocalDate date;

    public Event(Long id, String title, String description, String location, LocalDate date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.date = date;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public LocalDate getDate() { return date; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setLocation(String location) { this.location = location; }
    public void setDate(LocalDate date) { this.date = date; }
}
