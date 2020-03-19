package com.jiay.user.model.response;

import com.jiay.common.model.response.BaseResponse;

/**
 * Token响应bean
 * @author jiay
 * @date 2020-03-17
 */
public class TokenResponse extends BaseResponse {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
