package com.easyevent.dao;

import com.easyevent.entity.ArtistEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArtistRepository extends CrudRepository<ArtistEntity,UUID> {
}
