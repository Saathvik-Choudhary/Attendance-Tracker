package com.example.Attendance.Tracker.data;

import com.example.Attendance.Tracker.common.Response;

import java.util.Date;

public class LogInResponse extends Response {

    private final Date logIn;

    public LogInResponse(Date logIn) {
        this.logIn = logIn;
    }

    /**
     * Get the stored login time
     *
     * @return the stored login time
     */
    public Date getLogIn() {
        return logIn;
    }
}
