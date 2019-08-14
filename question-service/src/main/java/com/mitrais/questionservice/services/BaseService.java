package com.mitrais.questionservice.services;

public interface BaseService<T> {
    void save(T data);

    T getDataById(Long id);

    void deleteById(Long id);
}
