package com.easyevent.services.implementation;

import com.easyevent.dto.EventDto;
import com.easyevent.services.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService implements CrudService<EventDto> {

    @Override
    public long add(EventDto eventDto) {
        return 0;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<EventDto> getAll() {
        return null;
    }

    @Override
    public EventDto get(long id) {
        return null;
    }
}
