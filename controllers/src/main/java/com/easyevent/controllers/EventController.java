package com.easyevent.controllers;

import com.easyevent.dto.base.EventDto;
import com.easyevent.services.implementation.EventService;
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

@Api
@RestController
@RequestMapping("/events")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @ApiOperation(value = "Add events", nickname = "EventController.addEvent")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Events is adding")})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addArtist(@RequestBody EventDto eventDto) {
        Map id = Collections.singletonMap("id", eventService.add(eventDto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Gets all events", nickname = "EventController.getAllEvents")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Events")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventDto>> getAllEvents() {
        List<EventDto> event = eventService.getAll();
        if (CollectionUtils.isEmpty(event)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(event, HttpStatus.OK);
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

}
