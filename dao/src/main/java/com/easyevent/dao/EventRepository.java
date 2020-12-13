package com.easyevent.dao;

import com.easyevent.entity.ArtistEntity;
import com.easyevent.entity.EventEntity;
import com.easyevent.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    EventEntity getAllByArtistEntitiesAndCost(ArtistEntity artistEntity, double cost);

    List<EventEntity> getAllByOrganizationEntities (List<OrganizationEntity> organizationEntities);

    List<EventEntity> getAllByArtistEntities (List<ArtistEntity> artistEntities);

    EventEntity existsByArtistEntities(ArtistEntity artistEntity);
}
