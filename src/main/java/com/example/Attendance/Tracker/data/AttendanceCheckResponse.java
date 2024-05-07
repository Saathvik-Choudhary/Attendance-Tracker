package com.example.Attendance.Tracker.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AttendanceCheckResponse {

    private final boolean status;

    @JsonCreator
    public AttendanceCheckResponse(final boolean status) {
        this.status = status;
    }

    /**
     * Get the status whether the login and logout buttons are active or inactive
     *
     * @return the status whether the login and logout buttons are active or inactive
     */
    @JsonProperty
    public boolean getStatus() {
        return status;
    }
}
