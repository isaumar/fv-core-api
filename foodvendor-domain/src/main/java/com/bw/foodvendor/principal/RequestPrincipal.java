
package com.bw.foodvendor.principal;

public interface RequestPrincipal {

    public String AUTH_TOKEN_NAME = "authToken";

    String getUserId();

    default String getUserName() {
        return null;
    }

    default String getDisplayName() {
        return null;
    }

    default String getIpAddress() {
        return null;
    }
}