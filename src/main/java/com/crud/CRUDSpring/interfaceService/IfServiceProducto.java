package com.crud.CRUDSpring.interfaceService;

import java.util.List;


import java.util.Optional;

import com.crud.CRUDSpring.model.Producto;


/*interfaz para el servicio, a implementar
 * Aca declaro las funciones y metodos que
 * voy a utilizar para listar todos, listar por ID, Guardar
 * y Eliminar
 */
public interface IfServiceProducto {
	public List<Producto> listarProd(String keyword);
	public Optional <Producto> ProductolistarId(int idproducto);
	public int saveProd(Producto p);
	public void deleteProd(int idproducto);



}
