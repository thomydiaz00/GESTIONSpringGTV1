package com.crud.CRUDSpring.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.CRUDSpring.entity.Authority;

@Repository
public interface interfaceAuthority extends CrudRepository<Authority, Integer>{

}
