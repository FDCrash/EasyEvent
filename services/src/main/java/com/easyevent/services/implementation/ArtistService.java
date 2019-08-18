package com.easyevent.services.implementation;

import com.easyevent.dto.ArtistDto;
import com.easyevent.services.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService implements CrudService<ArtistDto> {

    @Override
    public long add(ArtistDto artistDto) {
        return 0;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<ArtistDto> getAll() {
        return null;
    }

    @Override
    public ArtistDto get(long id) {
        return null;
    }
}
