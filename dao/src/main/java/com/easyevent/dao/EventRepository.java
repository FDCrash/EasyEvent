package com.easyevent.dao;

import com.easyevent.entity.ArtistEntity;
import com.easyevent.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    EventEntity getAllByArtistEntitiesAndCost(ArtistEntity artistEntity, double cost);

    EventEntity existsByArtistEntities(ArtistEntity artistEntity);
}
