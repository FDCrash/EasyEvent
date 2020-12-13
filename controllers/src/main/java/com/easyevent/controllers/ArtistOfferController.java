package com.easyevent.controllers;

import com.easyevent.dto.base.ArtistOfferDto;
import com.easyevent.services.implementation.ArtistOfferService;
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
@RequestMapping("/offers")
public class ArtistOfferController {

    private ArtistOfferService artistOfferService;

    @Autowired
    public ArtistOfferController(ArtistOfferService artistOfferService) {
        this.artistOfferService = artistOfferService;
    }

    @ApiOperation(value = "Add offers", nickname = "OfferController.addOffer")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Offers is adding")})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addArtist(@RequestBody ArtistOfferDto artistOfferDto) {
        Map id = Collections.singletonMap("id", artistOfferService.add(artistOfferDto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Gets all offers", nickname = "OfferController.getAllOffers")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Offers")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ArtistOfferDto>> getAllOffers() {
        List<ArtistOfferDto> offer = artistOfferService.getAll();
        if (CollectionUtils.isEmpty(offer)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @ApiOperation(value = "Get specific offer", nickname = "OfferController.getOffer")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Offer")})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArtistOfferDto> getEvent(@PathVariable("id") long id) {
        ArtistOfferDto offer = artistOfferService.get(id);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets offers by artist", nickname = "OfferController.getOfferByArtist")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Offer")})
    @GetMapping(value = "artist/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ArtistOfferDto>> getOfferByArtist(@PathVariable("id") UUID id) {
        List<ArtistOfferDto> offer = artistOfferService.getByArtist(id);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete offer", nickname = "OfferController.deleteOffer")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Offer is deleted")})
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteOffer(@PathVariable("id") long id) {
        artistOfferService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
