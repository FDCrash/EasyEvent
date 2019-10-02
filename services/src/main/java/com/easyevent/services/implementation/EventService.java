package com.easyevent.services.implementation;

import com.easyevent.converters.EventConverter;
import com.easyevent.dao.EventRepository;
import com.easyevent.dto.base.EventDto;
import com.easyevent.services.DocumentCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EventService implements DocumentCrud<EventDto> {

    private EventConverter eventConverter;
    private EventRepository eventRepository;

    @Autowired
    public EventService(EventConverter eventConverter,EventRepository eventRepository){
        this.eventConverter = eventConverter;
        this.eventRepository = eventRepository;
    }

    @Override
    public String add(EventDto eventDto) {
        return eventRepository.save(eventConverter.convert(eventDto)).toString();
    }

    @Override
    public void delete(long id) {
        eventRepository.delete(id);
    }

    @Override
    public List<EventDto> getAll() {
        return StreamSupport.stream(eventRepository.findAll().spliterator(), false)
                .map(userEntity -> eventConverter.convert(userEntity))
                .collect(Collectors.toList());
    }

    @Override
    public EventDto get(long id) {
        return eventConverter.convert(eventRepository.findOne(id));
    }
}
