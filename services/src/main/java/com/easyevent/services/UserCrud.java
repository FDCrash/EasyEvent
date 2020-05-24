package com.easyevent.services;

import java.util.UUID;

public interface UserCrud<T> extends CrudService<T>{

    void delete(UUID id);

    T get(UUID id);

    T getByLogin(String login);
}
