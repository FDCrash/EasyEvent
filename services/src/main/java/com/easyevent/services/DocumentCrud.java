package com.easyevent.services;

public interface DocumentCrud<T> extends CrudService<T>{

    void delete(long id);

    T get(long id);
}
