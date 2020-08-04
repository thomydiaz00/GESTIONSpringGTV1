package com.crud.CRUDSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crud.CRUDSpring.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	@Query("SELECT p FROM Producto p WHERE p.marca LIKE %?1%"
            + " OR p.idproducto LIKE %?1%"
            + " OR p.modelo LIKE %?1%"
            + " OR p.cantidad LIKE %?1%")
	public List<Producto> search(String busqueda);

}
