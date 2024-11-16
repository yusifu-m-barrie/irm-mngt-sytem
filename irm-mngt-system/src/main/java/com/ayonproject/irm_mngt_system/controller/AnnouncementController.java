package com.ayonproject.irm_mngt_system.controller;

import com.ayonproject.irm_mngt_system.model.Announcement;
import com.ayonproject.irm_mngt_system.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @PostMapping("/announcements")
    public Announcement saveAnnouncement(@RequestBody Announcement announcement) {
        return announcementService.saveAnnouncement(announcement);
    }

    @GetMapping("/announcements")
    public List<Announcement> getAllAnnouncements() {
        return announcementService.getAllAnnouncements();
    }

    @GetMapping("/announcements/{id}")
    public ResponseEntity<Announcement> getAnnouncementById(@PathVariable("id") Long id) {
        Announcement announcement = announcementService.getAnnouncementById(id);
        return ResponseEntity.ok(announcement);
    }

    @PutMapping("/announcements/{id}")
    public ResponseEntity<Announcement> updateAnnouncement(@PathVariable Long id, @RequestBody Announcement announcement) {
        Announcement updatedAnnouncement = announcementService.updateAnnouncement(id, announcement);
        return ResponseEntity.ok(updatedAnnouncement);
    }

    @DeleteMapping("/announcements/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAnnouncement(@PathVariable Long id) {
        boolean deleted = announcementService.deleteAnnouncement(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/announcements/count")  // Endpoint is now /api/v1/announcements/count
    public Long countAnnouncements() {
        return announcementService.countAnnouncements();
    }
}
