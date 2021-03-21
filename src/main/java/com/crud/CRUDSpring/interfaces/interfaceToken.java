package com.crud.CRUDSpring.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.crud.CRUDSpring.model.AccessToken;

@Repository
public interface interfaceToken extends CrudRepository<AccessToken, Integer> {

}
