package com.mitrais.questionservice.services;

import java.util.List;
import java.util.Optional;

/**
 * Provide functionality to manipulate data.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 * @param <T> generic type
 */
public interface BaseService<T> {

    /**
     * save data
     *
     * @param data generic object
     */
    void save(T data);

    /**
     * find by id
     *
     * @param id id of data
     * @return return optional generic object type
     */
    Optional<T> findById(Long id);

    /**
     * delete data by id
     *
     * @param id of data
     */
    void deleteById(Long id);

    /**
     * find all data
     *
     * @return list of generic object
     */
    List<T> findAll();

    /**
     * validation is data exist by id
     *
     * @param id id of data
     * @return boolean
     */
    boolean existsById(Long id);
}
