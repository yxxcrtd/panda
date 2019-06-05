package com.chinaedustar.app.achievement.common.exception;

/**
 * 受查异常基类
 */
@SuppressWarnings("serial")
public class ProException extends Exception {

	public ProException(String message) {
		super(message);
	}

	public ProException(String message, Throwable e) {
		super(message, e);
	}

}