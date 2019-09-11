package com.easyevent.services;

import java.util.List;

public interface CrudService<T> {

    String add(T t);

    List<T> getAll();
}
