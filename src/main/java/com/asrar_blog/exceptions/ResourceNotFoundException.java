package com.asrar_blog.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    private String resource;
    private String fieldName;
    private long fieldId;

    public ResourceNotFoundException(String resource, String fieldName, long fieldId) {
        super(String.format("%s not found with %s field of id %l",resource,fieldName,fieldId));
        this.resource = resource;
        this.fieldName = fieldName;
        this.fieldId = fieldId;
    }
}
