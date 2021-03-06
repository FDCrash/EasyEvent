package com.easyevent.services.implementation;

import com.easyevent.converters.ArtistConverter;
import com.easyevent.dao.ArtistRepository;
import com.easyevent.dto.base.ArtistDto;
import com.easyevent.services.UserCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ArtistService implements UserCrud<ArtistDto> {

    private ArtistConverter artistConverter;
    private ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistConverter artistConverter, ArtistRepository artistRepository) {
        this.artistConverter = artistConverter;
        this.artistRepository = artistRepository;
    }

    @Override
    public String add(ArtistDto artistDto) {
        return String.valueOf(artistRepository.save(artistConverter.convert(artistDto)).getId());
    }

    @Override
    public void delete(UUID id) {
        artistRepository.delete(id);
    }

    @Override
    public List<ArtistDto> getAll() {
        return artistRepository.findAll().stream()
                .map(userEntity -> artistConverter.convert(userEntity))
                .collect(Collectors.toList());
    }

    @Override
    public ArtistDto get(UUID id) {
        return artistConverter.convert(artistRepository.findOne(id));
    }

    @Override
    public ArtistDto getByLogin(String login) {
        return artistConverter.convert(artistRepository.findByLogin(login));
    }
}
