package com.easyevent.dao;

import com.easyevent.entity.ArtistOfferEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistOfferRepository extends CrudRepository<ArtistOfferEntity, Long> {
}
