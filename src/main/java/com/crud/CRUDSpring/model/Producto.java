package com.crud.CRUDSpring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/*
 * Clase para los productos, persisten los datos de la b.d
 */
@Entity
@Table(name = "producto")

public class Producto {
	public Producto() {
		
	}
	//estableced el id como si fuera una PK
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idproducto;
	private String marca;
    private String modelo;
    private float precioCompra;
    private float precioVenta;
    private int cantidad;
	
    
    public Producto(int idproducto, String marca, String modelo, float precioCompra, float precioVenta, int cantidad) {
		super();
		this.idproducto = idproducto;
		this.marca = marca;
		this.modelo = modelo;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.cantidad = cantidad;
	}


	public int getIdproducto() {
		return idproducto;
	}


	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public float getPrecioCompra() {
		return precioCompra;
	}


	public void setPrecioCompra(float precioCompra) {
		this.precioCompra = precioCompra;
	}


	public float getPrecioVenta() {
		return precioVenta;
	}


	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
    
    
	
    
	
        
   
}