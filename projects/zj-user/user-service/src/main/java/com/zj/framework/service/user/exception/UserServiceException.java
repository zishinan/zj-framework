package com.zj.framework.service.user.exception;


public class UserServiceException extends Exception {

    public UserServiceException(String message) {
        super(message);
    }

    public UserServiceException() {
        super();
    }

    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
