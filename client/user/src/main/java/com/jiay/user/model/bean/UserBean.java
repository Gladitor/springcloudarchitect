package com.jiay.user.model.bean;

import com.jiay.common.model.BaseModel;

import java.time.LocalDateTime;

/**
 * 用户类
 * @author jiay
 * @date 2020-03-17
 */
public class UserBean extends BaseModel {

    private String mobile;

    private String password;

    private String nickName;

    private LocalDateTime gmt_create;

    private LocalDateTime gmt_modified;

    private String openid;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public LocalDateTime getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(LocalDateTime gmt_create) {
        this.gmt_create = gmt_create;
    }

    public LocalDateTime getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modified(LocalDateTime gmt_modified) {
        this.gmt_modified = gmt_modified;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
