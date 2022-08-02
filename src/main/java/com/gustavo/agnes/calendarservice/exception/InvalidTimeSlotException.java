package com.gustavo.agnes.calendarservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTimeSlotException extends RuntimeException{
    public InvalidTimeSlotException(String message){
        super(message);
    }
}
