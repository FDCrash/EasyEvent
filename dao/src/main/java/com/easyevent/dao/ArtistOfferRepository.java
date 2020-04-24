package com.easyevent.dao;

import com.easyevent.entity.ArtistOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistOfferRepository extends JpaRepository<ArtistOfferEntity, Long> {
}
