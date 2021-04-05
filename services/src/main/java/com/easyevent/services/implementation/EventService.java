package com.easyevent.services.implementation;

import com.easyevent.converters.EventConverter;
import com.easyevent.dao.EventRepository;
import com.easyevent.dto.base.ArtistOfferDto;
import com.easyevent.dto.base.EventDto;
import com.easyevent.entity.ArtistEntity;
import com.easyevent.entity.EventEntity;
import com.easyevent.entity.OrganizationEntity;
import com.easyevent.entity.UserEntity;
import com.easyevent.services.DocumentCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventService implements DocumentCrud<EventDto> {

    private EventConverter eventConverter;
    private EventRepository eventRepository;
    private ArtistOfferService artistOfferService;

    @Autowired
    public EventService(EventConverter eventConverter, EventRepository eventRepository, ArtistOfferService artistOfferService) {
        this.eventConverter = eventConverter;
        this.eventRepository = eventRepository;
        this.artistOfferService = artistOfferService;
    }

    @Override
    public String add(EventDto eventDto) {
        return String.valueOf(eventRepository.save(eventConverter.convert(eventDto)).getId());
    }

    @Override
    public void delete(long id) {
        eventRepository.delete(id);
    }

    @Override
    public List<EventDto> getAll() {
        return eventRepository.findAll().stream()
                .map(eventEntity -> eventConverter.convert(eventEntity))
                .collect(Collectors.toList());
    }

    @Override
    public EventDto get(long id) {
        return eventConverter.convert(eventRepository.findOne(id));
    }

    public List<EventDto> getAllByOrganization(UUID uuid) {
        return eventRepository.getAllByOrganizationEntities(Collections.singletonList(
                OrganizationEntity.organizationBuilder()
                        .userEntity(
                                UserEntity.builder()
                                        .id(uuid)
                                        .build()
                        ).build()))
                .stream()
                .map(eventEntity -> eventConverter.convert(eventEntity))
                .collect(Collectors.toList());
    }

    public List<EventDto> getAllByArtist(UUID uuid) {
        return eventRepository.getAllByArtistEntities(Collections.singletonList(
                ArtistEntity.artistBuilder()
                        .userEntity(
                                UserEntity.builder()
                                        .id(uuid)
                                        .build()
                        ).build()))
                .stream()
                .map(artistEntity -> eventConverter.convert(artistEntity))
                .collect(Collectors.toList());
    }

}
