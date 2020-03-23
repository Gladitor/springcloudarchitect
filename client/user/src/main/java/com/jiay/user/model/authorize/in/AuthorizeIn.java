package com.jiay.user.model.authorize.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jiay.common.model.BaseModel;
import org.hibernate.validator.constraints.NotBlank;

public class AuthorizeIn extends BaseModel {

    @JsonProperty("responseType")
    @NotBlank(message = "缺少responseType参数")
    private String responseType;

    @JsonProperty("clientId")
    @NotBlank(message = "缺少clientId参数")
    private String clientId;

    private String state;

    @JsonProperty("redirectUri")
    @NotBlank(message = "缺少redirectUri参数")
    private String redirectUri;

    private String openid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
