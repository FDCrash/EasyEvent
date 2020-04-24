package com.easyevent.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "organizations")
@Data
@PrimaryKeyJoinColumn(name = "organization_id")
@NoArgsConstructor
public class OrganizationEntity extends UserEntity {

    @Column(name = "organization_id", unique = true, nullable = false)
    private UUID id;

    @Column(unique = true)
    private String name;

    private String type;

    private String description;

    @ManyToMany
    @JoinTable(name = "organizations_events",
            joinColumns = @JoinColumn(name = "organization_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<EventEntity> eventEntities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrganizationEntity that = (OrganizationEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getName(), getType(), getDescription());
    }

    @Builder(builderMethodName = "organizationBuilder")
    public OrganizationEntity(UserEntity userEntity, String name, String type, String description, List<EventEntity> eventEntities) {
        super(userEntity.getId(), userEntity.getLogin(), userEntity.getPassword(), userEntity.getEmail(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.isRegistration(), userEntity.getRole(), userEntity.getPhone());
        this.id = userEntity.getId();
        this.name = name;
        this.type = type;
        this.description = description;
        this.eventEntities = eventEntities;
    }

    public List<Long> getEventIds() {
        return getEventEntities().stream().map(EventEntity::getId).collect(Collectors.toList());
    }

    public List<String> getEventNames() {
        return getEventEntities().stream().map(EventEntity::getName).collect(Collectors.toList());
    }

}
