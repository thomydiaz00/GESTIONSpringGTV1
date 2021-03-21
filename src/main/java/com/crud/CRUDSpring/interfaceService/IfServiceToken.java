package com.crud.CRUDSpring.interfaceService;

import java.util.List;
import java.util.Optional;

import com.crud.CRUDSpring.model.AccessToken;

public interface IfServiceToken {
    public List<AccessToken> listarAccessTokens();

    public Optional<AccessToken> AccessTokenPorId(int id);

    public int guardarAccessToken(AccessToken p);

    public void borrarAccessToken(int id);
}
