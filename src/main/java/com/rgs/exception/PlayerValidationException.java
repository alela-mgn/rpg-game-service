package com.rgs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class PlayerValidationException extends RuntimeException{
    public PlayerValidationException(String message)
    {
        super(message);
    }
}
