package com.jiay.common.service;

import com.jiay.common.encryption.Algorithm;
import com.jiay.common.encryption.MessageDigestUtils;

import java.time.LocalDateTime;

/**
 * 服务base
 * @author jiay
 * @date 2020-03-17
 */
public abstract class BaseService {

    /**
     * 密码加密
     * @param password
     * @return
     */
    protected String encryptPassword(String password){
        return MessageDigestUtils.encrypt(password, Algorithm.SHA1);
    }

    /**
     * 生成API鉴权的Token
     * @param mobile
     * @param password
     * @return
     */
    protected String getToken(String mobile,String password){
        return MessageDigestUtils.encrypt(mobile+password+ System.currentTimeMillis(), Algorithm.SHA1);
    }

}
