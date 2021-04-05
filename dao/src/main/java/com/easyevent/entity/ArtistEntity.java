package com.easyevent.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "artists")
@PrimaryKeyJoinColumn(name = "artist_id")
@Data
@NoArgsConstructor
public class ArtistEntity extends UserEntity {

    @Column(name = "artist_id", unique = true, nullable = false)
    private UUID id;

    @Column(unique = true)
    private String pseudonym;

    private String type;

    private String description;

    @ManyToMany
    @JoinTable(name = "artists_events",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<EventEntity> eventEntities;

    @OneToMany(mappedBy = "artistEntity")
    private List<ArtistOfferEntity> artistOfferEntities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ArtistEntity that = (ArtistEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getPseudonym(), that.getPseudonym()) &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getPseudonym(), getType(), getDescription());
    }

    @Builder(builderMethodName = "artistBuilder")
    public ArtistEntity(UserEntity userEntity, String pseudonym, String type, String description, List<EventEntity> eventEntities, List<ArtistOfferEntity> artistOfferEntities) {
        super(userEntity.getId(), userEntity.getLogin(), userEntity.getPassword(), userEntity.getEmail(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.isRegistration(), userEntity.getRole(), userEntity.getPhone());
        this.id = userEntity.getId();
        this.pseudonym = pseudonym;
        this.type = type;
        this.description = description;
        this.eventEntities = eventEntities;
        this.artistOfferEntities = artistOfferEntities;
    }

    public List<Long> getEventIds() {
        return getEventEntities().stream()
                .map(EventEntity::getId)
                .collect(Collectors.toList());
    }

    public List<String> getEventNames() {
        return getEventEntities().stream()
                .map(EventEntity::getName)
                .collect(Collectors.toList());
    }

    public List<Long> getOfferIds() {
        return getArtistOfferEntities().stream()
                .map(ArtistOfferEntity::getId)
                .collect(Collectors.toList());
    }

    public List<String> getOfferNames() {
        return getArtistOfferEntities().stream()
                .map(ArtistOfferEntity::getName)
                .collect(Collectors.toList());
    }
}
