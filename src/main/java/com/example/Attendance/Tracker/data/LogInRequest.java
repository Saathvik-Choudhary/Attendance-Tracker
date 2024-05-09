package com.example.Attendance.Tracker.data;

import com.example.Attendance.Tracker.common.Request;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class LogInRequest extends Request {

    private final ZoneOffset zoneOffset;

    @JsonCreator
    public LogInRequest(ZoneOffset zoneOffset) {
        this.zoneOffset = zoneOffset;
    }

    public ZoneOffset getZoneOffset() {
        return zoneOffset;
    }
}
