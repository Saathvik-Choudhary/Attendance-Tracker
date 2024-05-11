package com.example.Attendance.Tracker.core;

import com.example.Attendance.Tracker.data.*;
import com.example.Attendance.Tracker.domain.Attendance;
import com.example.Attendance.Tracker.percistence.AttendanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AttendanceServiceTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @InjectMocks
    private AttendanceService attendanceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetSummary() {
        GetMonthWiseSummaryRequest request = new GetMonthWiseSummaryRequest(3);

        when(attendanceRepository.getMonthSummary(anyInt())).thenReturn(BigDecimal.valueOf(8)); // Mock return value for getMonthSummary()

        GetMonthWIseSummaryResponse expectedResponse = new GetMonthWIseSummaryResponse();
        expectedResponse.addMonthSummary(new MonthSummary(new Date(), BigDecimal.valueOf(8)));
        expectedResponse.addMonthSummary(new MonthSummary(new Date(), BigDecimal.valueOf(8)));
        expectedResponse.addMonthSummary(new MonthSummary(new Date(), BigDecimal.valueOf(8)));

        GetMonthWIseSummaryResponse actualResponse = attendanceService.getSummary(request);

        assertEquals(expectedResponse.getMonthSummaries().size(), actualResponse.getMonthSummaries().size());
    }

    @Test
    public void testHandleLogOut() {
        LogOutRequest request = new LogOutRequest();

        Attendance attendance = new Attendance(new Date());
        when(attendanceRepository.findAttendanceByDateId(anyInt())).thenReturn(attendance);
        when(attendanceRepository.save(any())).thenReturn(attendance);

        LogOutResponse response = attendanceService.handleLogOut(request);

        assertNotNull(response);
        assertEquals("Logout has been Recorded", response.getMessage());
    }

    @Test
    public void testCheckLogInStatus() {
        when(attendanceRepository.findByDateId(anyInt())).thenReturn(Optional.empty());

        AttendanceCheckResponse response = attendanceService.checkLogInStatus(new AttendanceCheckRequest());

        assertNotNull(response);
        assertTrue(response.getStatus());
    }

    @Test
    public void testCheckLogOutStatus() {
        when(attendanceRepository.findByDateId(anyInt())).thenReturn(Optional.of(123));
        when(attendanceRepository.getLogout(anyInt())).thenReturn(new Date());

        AttendanceCheckResponse response = attendanceService.checkLogOutStatus(new AttendanceCheckRequest());

        assertNotNull(response);
        assertFalse(response.getStatus());
    }
}
