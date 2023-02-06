package com.blog.helper.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s Not found with %s: %s",resourceName, fieldName, fieldValue));
    }
}