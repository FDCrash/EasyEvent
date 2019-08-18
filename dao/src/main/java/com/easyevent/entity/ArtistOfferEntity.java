package com.easyevent.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "artists_offers")
public class ArtistOfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id", unique = true, nullable = false)
    private long id;

    private String description;

    private int cost;

}
