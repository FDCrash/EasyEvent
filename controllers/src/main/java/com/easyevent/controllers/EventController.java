package com.easyevent.controllers;

import com.easyevent.dto.base.ArtistOfferDto;
import com.easyevent.dto.base.EventDto;
import com.easyevent.services.implementation.ArtistOfferService;
import com.easyevent.services.implementation.EventService;
import com.easyevent.services.implementation.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Api
@RestController
@RequestMapping("/events")
public class EventController {

    private EventService eventService;
    private OrganizationService organizationService;
    private ArtistOfferService artistOfferService;

    @Autowired
    public EventController(EventService eventService, OrganizationService organizationService, ArtistOfferService artistOfferService) {
        this.eventService = eventService;
        this.organizationService = organizationService;
        this.artistOfferService = artistOfferService;
    }

    @ApiOperation(value = "Add events", nickname = "EventController.addEvent")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Events is adding")})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addArtist(@RequestBody EventDto eventDto) {
        Map id = Collections.singletonMap("id", eventService.add(eventDto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Add artists", nickname = "EventController.addArtist")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Artists is adding")})
    @PostMapping(value = "/addArtist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addArtist(@RequestParam long uuid, @RequestParam UUID artistId) {
        Map id = Collections.singletonMap("id", eventService.addArtist(artistId, uuid));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Add organizations", nickname = "EventController.addOrganizations")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Organization is adding")})
    @PostMapping(value = "/addOrganization", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addOrganization(@RequestParam long uuid, @RequestParam UUID organizationId) {
        Map id = Collections.singletonMap("id", eventService.addOrganization(organizationId, uuid));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Gets all events", nickname = "EventController.getAllEvents")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Events")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventDto>> getAllEvents() {
        List<EventDto> events = eventService.getAll();
        events.forEach(event -> {
            int sum = 0;
            List<Integer> cost = artistOfferService.getByArtists(event.getArtists().keySet()).stream().map(ArtistOfferDto::getCost).collect(Collectors.toList());
            for(Integer x: cost) {
                sum += x;
            }
            event.setSummaryCost(sum);
        });
        if (CollectionUtils.isEmpty(events)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @ApiOperation(value = "Get specific event", nickname = "EventController.getEvent")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Event")})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventDto> getEvent(@PathVariable("id") long id) {
        EventDto event = eventService.get(id);
        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete event", nickname = "EventController.deleteEvent")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Event is deleted")})
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") long id) {
        eventService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Gets events by organization", nickname = "EventController.getAllEvents")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Events")})
    @GetMapping(value = "/organization/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Long, String>> getEventsByOrganization(@PathVariable("id")UUID id) {
        Map<Long, String> event = organizationService.get(id).getEvents();
        if (CollectionUtils.isEmpty(event)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets events by organization", nickname = "EventController.getAllEventsByOrganization")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Events")})
    @GetMapping(value = "/organization/full/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventDto>> getFullEventsByOrganization(@PathVariable("id")UUID id) {
        List<EventDto> event = eventService.getAllByOrganization(id);
        if (CollectionUtils.isEmpty(event)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets events by artist", nickname = "EventController.getAllEventsByArtist")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Events")})
    @GetMapping(value = "/artist/full/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventDto>> getFullEventsByArtist(@PathVariable("id")UUID id) {
        List<EventDto> event = eventService.getAllByArtist(id);
        if (CollectionUtils.isEmpty(event)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

}
