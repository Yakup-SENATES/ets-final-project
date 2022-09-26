package com.etstur.finalproject.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorDetails {

        private Date timestamp;
        private String message;
        private String details;

    public ErrorDetails(Date date, String message, String description) {
        this.timestamp = date;
        this.message = message;
        this.details = description;
    }
}
