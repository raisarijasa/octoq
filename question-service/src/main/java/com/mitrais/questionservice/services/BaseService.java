package com.mitrais.questionservice.services;

import java.util.List;

public interface BaseService<T> {
    void save(T data);

    T findDataById(Long id);

    void deleteById(Long id);

    List<T> findAll();
}
