package com.jiay.common.encryption;

import java.security.MessageDigest;
import java.time.LocalDateTime;

/**
 * 消息摘要工具
 * @author jiay
 * @date 2020-03-17
 */
public class MessageDigestUtils {
	
	
	public static String encrypt(String password,String algorithm){
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			byte[] b = md.digest(password.getBytes("UTF-8"));
			return ByteUtils.byte2HexString(b);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) throws Exception{
		System.out.println(MessageDigestUtils.encrypt("admin" + LocalDateTime.now(),Algorithm.SHA1));
	}
}
