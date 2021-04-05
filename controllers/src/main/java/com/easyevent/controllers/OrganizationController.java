package com.easyevent.controllers;

import com.easyevent.dto.base.ArtistOfferDto;
import com.easyevent.dto.base.OrganizationDto;
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

@Api
@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    private OrganizationService organizationService;
    private EventService eventService;

    @Autowired
    public OrganizationController(OrganizationService organizationService, EventService eventService) {
        this.organizationService = organizationService;
        this.eventService = eventService;
    }

    @ApiOperation(value = "Add organization", nickname = "OrganizationController.addOrganization")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Organization is adding")})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addArtist(@RequestBody OrganizationDto organizationDto) {
        Map id = Collections.singletonMap("id", organizationService.add(organizationDto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Gets all organizations", nickname = "OrganizationController.getAllOrganizations")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Organizations")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrganizationDto>> getAllOrganizations() {
        List<OrganizationDto> organization = organizationService.getAll();
        if (CollectionUtils.isEmpty(organization)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    @ApiOperation(value = "Get specific organization", nickname = "OrganizationController.getOrganization")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Organization")})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable("id") UUID id) {
        OrganizationDto organization = organizationService.get(id);
        if (organization == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete organization", nickname = "OrganizationController.deleteOrganization")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Organization is deleted")})
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteOrganization(@PathVariable("id") UUID id) {
        organizationService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
