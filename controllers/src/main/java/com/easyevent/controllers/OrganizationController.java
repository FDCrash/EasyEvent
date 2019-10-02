package com.easyevent.controllers;

import com.easyevent.dto.base.OrganizationDto;
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

import java.util.List;
import java.util.UUID;

@Api
@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    private OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
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
