package com.orders.demo.dto;

import java.util.Map;

/**
 * @author IsuruP
 */
public class ErrorResponseDTO extends CommonResponseDTO{
    private Map<String, String> errors;

    public Map<String, String> getErrors() {
        return errors;
    }
    public void setErrors(Map<String, String> value) {
        errors = value;
    }
}
