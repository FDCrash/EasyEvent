package com.easyevent.services.implementation;

import com.easyevent.dto.ArtistOfferDto;
import com.easyevent.services.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistOfferService implements CrudService<ArtistOfferDto> {

    @Override
    public long add(ArtistOfferDto artistOfferDto) {
        return 0;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<ArtistOfferDto> getAll() {
        return null;
    }

    @Override
    public ArtistOfferDto get(long id) {
        return null;
    }
}
