package com.jiay.user.model.request;

import com.jiay.common.model.request.BaseRequest;
import javax.validation.constraints.NotEmpty;

/**
 * 登陆请求bean
 * @author jiay
 * @date 2020-03-17
 */
public class LoginRequest extends BaseRequest {

    @NotEmpty
    private String mobile;

    @NotEmpty
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
