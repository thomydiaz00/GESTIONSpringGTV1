package com.crud.CRUDSpring.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.CRUDSpring.model.DiaDePractica;

@Repository
public interface interfaceDia extends CrudRepository<DiaDePractica, Integer> {
}
