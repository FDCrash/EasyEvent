package com.easyevent.dao;

import com.easyevent.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity getByLogin(String login);

    boolean existsByLogin(String login);

    UserEntity getUserEntityByLoginAndPassword(String login, String password);
}
