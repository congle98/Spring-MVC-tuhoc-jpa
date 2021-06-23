package com.app.service;

import com.app.exception.NotFoundException;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id) throws NotFoundException;

    void save(T t);

    void remove(Long id);
}
