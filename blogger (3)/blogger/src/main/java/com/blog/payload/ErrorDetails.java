package com.blog.payload;

import lombok.Data;

import java.util.Date;

public class ErrorDetails {


    public ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    private Date timestamp;//it shows time and date
    private String message;//message tells file is found or not
    private String details;//which url error occurs
}
