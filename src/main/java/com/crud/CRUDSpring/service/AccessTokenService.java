package com.crud.CRUDSpring.service;

import java.util.List;
import java.util.Optional;

import com.crud.CRUDSpring.interfaceService.IfServiceToken;
import com.crud.CRUDSpring.interfaces.interfaceToken;
import com.crud.CRUDSpring.model.AccessToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenService implements IfServiceToken {
    @Autowired
    interfaceToken data;

    @Override
    public List<AccessToken> listarAccessTokens() {
        // TODO Auto-generated method stub
        return (List<AccessToken>) data.findAll();
    }

    @Override
    public Optional<AccessToken> AccessTokenPorId(int id) {
        // TODO Auto-generated method stub
        return data.findById(id);
    }

    @Override
    public int guardarAccessToken(AccessToken p) {
        // TODO Auto-generated method stub
        int estado = 0;
        AccessToken h = data.save(p);
        if (!h.equals(null)) {
            estado = 1;
        }
        return estado;
    }

    @Override
    public void borrarAccessToken(int id) {
        data.deleteById(id);
    }

}
