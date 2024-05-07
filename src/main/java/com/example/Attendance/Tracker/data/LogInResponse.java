package com.example.Attendance.Tracker.data;

import com.example.Attendance.Tracker.common.Response;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class LogInResponse extends Response {

    private final Date logIn;

    public LogInResponse(final Date logIn) {
        this.logIn = logIn;
    }

    /**
     * Get the stored login time
     *
     * @return the stored login time
     */
    @JsonProperty
    public Date getLogIn() {
        return logIn;
    }
}
