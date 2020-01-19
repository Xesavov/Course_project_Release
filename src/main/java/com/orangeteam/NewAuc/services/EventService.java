package com.orangeteam.NewAuc.services;

import com.orangeteam.NewAuc.models.Event;
import com.orangeteam.NewAuc.reps.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    EventRepository eventRepository;

    public List<Event> getHistoryByLogin(String login) {
        return eventRepository.getHistoryByLogin(login);
    }

    public Event getEventById(Long id){
        return eventRepository.findById(id).orElse(null);
    }

    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
}
