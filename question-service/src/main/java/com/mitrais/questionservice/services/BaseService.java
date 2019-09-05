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
     * Provide functionality to save data.
     *
     * @param data generic object
     */
    void save(T data);

    /**
     * Provide functionality to find data by id.
     *
     * @param id id of data
     * @return return optional generic object type
     */
    Optional<T> findById(Long id);

    /**
     * Provide functionality to delete data by id.
     *
     * @param id of data
     */
    void deleteById(Long id);

    /**
     * Provide functionality to get all data.
     *
     * @return list of generic object
     */
    List<T> findAll();

    /**
     * Provide functionality to validate data exist.
     *
     * @param id id of data
     * @return boolean
     */
    boolean existsById(Long id);
}
