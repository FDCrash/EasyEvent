package com.easyevent.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "artists_offers")
@Builder
public class ArtistOfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id", unique = true, nullable = false)
    private long id;

    private String name;

    private String description;

    private int cost;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private ArtistEntity artistEntity;
}
