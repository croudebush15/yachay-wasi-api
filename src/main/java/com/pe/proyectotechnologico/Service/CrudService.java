package com.pe.proyectotechnologico.Service;

import java.util.List;

public interface CrudService<T, ID> {

    void create(T t);
    void update(T t);
    void delete(ID id);
    T findById(ID id);
    List<T> findAll();
}
