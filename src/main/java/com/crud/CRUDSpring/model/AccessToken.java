package com.crud.CRUDSpring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "access_token")
public class AccessToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idToken;

    private String access_token;
    private String refresh_token;
    private String scope;
    private String token_type;
    private int expires_in;

    public AccessToken() {
    }

    public AccessToken(int idToken, String accessToken, String refreshToken, String scope, String tokenType,
            int expiresIn) {
        this.idToken = idToken;
        this.access_token = accessToken;
        this.refresh_token = refreshToken;
        this.scope = scope;
        this.token_type = tokenType;
        this.expires_in = expiresIn;
    }

    public int getIdToken() {
        return idToken;
    }

    public void setIdToken(int idToken) {
        this.idToken = idToken;
    }

    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String accessToken) {
        this.access_token = accessToken;
    }

    public String getRefreshToken() {
        return refresh_token;
    }

    public void setRefreshToken(String refreshToken) {
        this.refresh_token = refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTokenType() {
        return token_type;
    }

    public void setTokenType(String tokenType) {
        this.token_type = tokenType;
    }

    public int getExpiresIn() {
        return expires_in;
    }

    public void setExpiresIn(int expiresIn) {
        this.expires_in = expiresIn;
    }

    @Override
    public String toString() {
        return "AccessToken [access_token=" + access_token + ", expires_in=" + expires_in + ", idToken=" + idToken
                + ", refresh_token=" + refresh_token + ", scope=" + scope + ", token_type=" + token_type + "]";
    }

}
