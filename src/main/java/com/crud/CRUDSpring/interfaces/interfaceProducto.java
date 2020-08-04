package com.crud.CRUDSpring.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.CRUDSpring.model.Producto;

@Repository
public interface interfaceProducto extends CrudRepository <Producto, Integer> {

}
