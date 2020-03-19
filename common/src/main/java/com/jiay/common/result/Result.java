package com.jiay.common.result;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JacksonJsonParser;

/**
 * 响应结果类
 * @author jiay
 * @date 2020-03-17
 */
public class Result {

	private Code code;

	private String message;

	public int getCode() {
		return code.getStatus();
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public String getMessage() {
		return message != null?message:code.getMessage();
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
