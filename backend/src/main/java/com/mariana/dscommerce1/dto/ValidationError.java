package com.mariana.dscommerce1.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError{

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldMessage> getErrors() {

        return errors;
    }
    public void addError(String fileName, String message){
        errors.removeIf(x -> x.getFieldName().equals(x.getFieldName()));
        errors.add(new FieldMessage(fileName, message));
    }
}
