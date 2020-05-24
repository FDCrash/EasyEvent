package com.easyevent.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", unique = true, nullable = false)
    private long id;

    private String name;

    @Column(name = "start_date")
    @CreatedDate
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    private String description;

    private int cost;

    @Column(name = "created_at")
    @CreatedDate
    private String createdAt;

    @Column(name = "expire_at")
    private String expiresAt;

    @ManyToMany
    @JoinTable(name = "organizations_events",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "organization_id"))
    private List<OrganizationEntity> organizationEntities;

    @ManyToMany
    @JoinTable(name = "artists_events",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private List<ArtistEntity> artistEntities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventEntity that = (EventEntity) o;
        return getId() == that.getId() &&
                getCost() == that.getCost() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getStartDate(), that.getStartDate()) &&
                Objects.equals(getEndDate(), that.getEndDate()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getCreatedAt(), that.getCreatedAt()) &&
                Objects.equals(getExpiresAt(), that.getExpiresAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStartDate(), getEndDate(), getDescription(), getCost(), getCreatedAt(), getExpiresAt());
    }

    public List<UUID> getOrganizationIds() {
        return getOrganizationEntities().stream()
                .map(OrganizationEntity::getId)
                .collect(Collectors.toList());
    }

    public List<String> getOrganizationNames() {
        return getOrganizationEntities().stream()
                .map(OrganizationEntity::getName)
                .collect(Collectors.toList());
    }

    public List<UUID> getArtistIds() {
        return getArtistEntities().stream()
                .map(ArtistEntity::getId)
                .collect(Collectors.toList());
    }

    public List<String> getArtistPseudonym() {
        return getArtistEntities().stream()
                .map(ArtistEntity::getPseudonym)
                .collect(Collectors.toList());
    }

}
