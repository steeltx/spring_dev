package com.gestion.eventos.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gestion.eventos.api.domain.Event;
import com.gestion.eventos.api.repository.EventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService implements IEventService{

    private final EventRepository eventRepository;

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event save(Event event){
        return eventRepository.save(event);
    }
    
}
