package com.example.fourdsight.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GlobalResponseException extends RuntimeException {
    private final int id;
    private final String message;

    public GlobalResponseException(int id, String message) {
        super(message);
        this.id = id;
        this.message = message;
    }
}