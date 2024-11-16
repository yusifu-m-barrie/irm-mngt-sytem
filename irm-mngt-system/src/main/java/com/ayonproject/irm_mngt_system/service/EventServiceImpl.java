package com.ayonproject.irm_mngt_system.service;

import com.ayonproject.irm_mngt_system.entity.EventEntity;
import com.ayonproject.irm_mngt_system.model.Event;
import com.ayonproject.irm_mngt_system.repository.EventRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event saveEvent(Event event) {
        EventEntity eventEntity = new EventEntity();
        BeanUtils.copyProperties(event, eventEntity);
        eventRepository.save(eventEntity);
        return event;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(entity -> new Event(
                        entity.getId(),
                        entity.getTitle(),
                        entity.getDescription(),
                        entity.getLocation(),
                        entity.getDate()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Event getEventById(Long id) {
        EventEntity entity = eventRepository.findById(id).orElse(null);
        if (entity == null) {
            return null;
        }
        return new Event(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getLocation(),
                entity.getDate()
        );
    }

    @Override
    public boolean deleteEvent(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        EventEntity entity = eventRepository.findById(id).orElse(null);
        if (entity == null) {
            return null;
        }
        BeanUtils.copyProperties(event, entity);
        eventRepository.save(entity);
        return event;
    }

    @Override
    public long countEvents() {
        return eventRepository.count();  // New count implementation
    }
}
