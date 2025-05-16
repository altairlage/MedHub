package com.fiap.medsched.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedException extends RuntimeException {
    public MedException(String message) {
        super(message);
    }
}
