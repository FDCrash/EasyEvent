package com.easyevent.controllers;

import com.easyevent.dto.base.ArtistDto;
import com.easyevent.services.implementation.ArtistService;
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
@RequestMapping("/artists")
public class ArtistController {

    private ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @ApiOperation(value = "Add artist", nickname = "ArtistController.addArtist")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Artist is adding")})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addArtist(@RequestBody ArtistDto artistDto) {
        Map id = Collections.singletonMap("id", artistService.add(artistDto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Gets all artists", nickname = "ArtistController.getAllArtists")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Artists")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ArtistDto>> getAllArtists() {
        List<ArtistDto> artist = artistService.getAll();
        if (CollectionUtils.isEmpty(artist)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(artist, HttpStatus.OK);
    }

    @ApiOperation(value = "Get specific artist", nickname = "ArtistController.getArtist")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Artist")})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArtistDto> getArtist(@PathVariable("id") UUID id) {
        ArtistDto artist = artistService.get(id);
        if (artist == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(artist, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete artist", nickname = "ArtistController.deleteArtist")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Artist is deleted")})
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteArtist(@PathVariable("id") UUID id) {
        artistService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
