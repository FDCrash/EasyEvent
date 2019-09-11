package com.easyevent.services.implementation;

import com.easyevent.converters.ArtistConverter;
import com.easyevent.dao.ArtistRepository;
import com.easyevent.dto.ArtistDto;
import com.easyevent.services.CrudService;
import com.easyevent.services.UserCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ArtistService implements UserCrud<ArtistDto> {

    private ArtistConverter artistConverter;
    private ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistConverter artistConverter, ArtistRepository artistRepository){
        this.artistConverter = artistConverter;
        this.artistRepository = artistRepository;
    }

    @Override
    public String add(ArtistDto artistDto) {
        return artistRepository.save(artistConverter.convert(artistDto)).toString();
    }

    @Override
    public void delete(UUID id) {
        artistRepository.delete(id);
    }

    @Override
    public List<ArtistDto> getAll() {
        return StreamSupport.stream(artistRepository.findAll().spliterator(), false)
                .map(userEntity -> artistConverter.convert(userEntity))
                .collect(Collectors.toList());
    }

    @Override
    public ArtistDto get(UUID id) {
        return artistConverter.convert(artistRepository.findOne(id));
    }
}
