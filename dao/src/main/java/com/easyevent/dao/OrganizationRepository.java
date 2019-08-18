package com.easyevent.dao;

import com.easyevent.entity.OrganizationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrganizationRepository extends CrudRepository<OrganizationEntity,UUID> {
}
