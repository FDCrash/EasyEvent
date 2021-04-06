package com.easyevent.dao;

import com.easyevent.entity.ArtistEntity;
import com.easyevent.entity.ArtistOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ArtistOfferRepository extends JpaRepository<ArtistOfferEntity, Long> {
    List<ArtistOfferEntity> findAllByArtistEntityId (UUID id);

    List<ArtistOfferEntity> findAllByArtistEntityId(Set<UUID> id);
}
