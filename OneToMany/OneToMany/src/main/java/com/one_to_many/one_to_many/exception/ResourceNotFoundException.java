package com.one_to_many.one_to_many.exception;

public class ResourceNotFoundException extends Throwable {
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String msg) {

        super(msg);
    }
}
