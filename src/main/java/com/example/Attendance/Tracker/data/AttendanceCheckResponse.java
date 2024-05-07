package com.example.Attendance.Tracker.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttendanceCheckResponse {

    private final boolean status;

    public AttendanceCheckResponse(final boolean status) {
        this.status = status;
    }

    @JsonProperty
    public boolean isStatus() {
        return status;
    }
}
