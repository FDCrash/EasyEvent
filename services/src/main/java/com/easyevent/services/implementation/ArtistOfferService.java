package com.easyevent.services.implementation;

import com.easyevent.converters.ArtistOfferConverter;
import com.easyevent.dao.ArtistOfferRepository;
import com.easyevent.dto.base.ArtistOfferDto;
import com.easyevent.services.DocumentCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ArtistOfferService implements DocumentCrud<ArtistOfferDto> {

    private ArtistOfferConverter artistOfferConverter;
    private ArtistOfferRepository artistOfferRepository;

    @Autowired
    public ArtistOfferService(ArtistOfferConverter artistOfferConverter, ArtistOfferRepository artistOfferRepository){
        this.artistOfferConverter = artistOfferConverter;
        this.artistOfferRepository = artistOfferRepository;
    }

    @Override
    public String add(ArtistOfferDto artistOfferDto) {
        return artistOfferRepository.save(artistOfferConverter.convert(artistOfferDto)).toString();
    }

    @Override
    public void delete(long id) {
        artistOfferRepository.delete(id);
    }

    @Override
    public List<ArtistOfferDto> getAll() {
        return StreamSupport.stream(artistOfferRepository.findAll().spliterator(), false)
                .map(userEntity -> artistOfferConverter.convert(userEntity))
                .collect(Collectors.toList());
    }

    @Override
    public ArtistOfferDto get(long id) {
        return artistOfferConverter.convert(artistOfferRepository.findOne(id));
    }
}
