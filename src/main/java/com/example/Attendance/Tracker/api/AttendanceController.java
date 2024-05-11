package com.example.Attendance.Tracker.api;

import com.example.Attendance.Tracker.core.AttendanceService;
import com.example.Attendance.Tracker.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @CrossOrigin
    @GetMapping("")
    public ResponseEntity< Page<AttendanceSummary>> getAllAttendance(final GetAllAttendanceRequest request){
        return ResponseEntity.ok( attendanceService.getAllAttendance(request).getPage());
    }

    @CrossOrigin
    @GetMapping("/summary/{months}")
    public ResponseEntity<GetMonthWIseSummaryResponse> getMonthWiseSummary(@PathVariable final int months){
        final GetMonthWiseSummaryRequest request=new GetMonthWiseSummaryRequest(months);
        return ResponseEntity.ok( attendanceService.getSummary(request));
    }

    @CrossOrigin
    @GetMapping("/checkLogin")
    public ResponseEntity<AttendanceCheckResponse> getLogInStatus(final AttendanceCheckRequest request){
        return ResponseEntity.ok(attendanceService.checkLogInStatus(request));
    }

    @CrossOrigin
    @GetMapping("/checkLogOut")
    public ResponseEntity<AttendanceCheckResponse> getLogOutStatus(final AttendanceCheckRequest request){
        return ResponseEntity.ok(attendanceService.checkLogOutStatus(request));
    }

    @CrossOrigin
    @PutMapping("/logIn")
    public ResponseEntity<LogInResponse> putLogIn(@RequestBody final LogInRequest request){
        return ResponseEntity.ok(attendanceService.handleLogIn(request));
    }

    @CrossOrigin
    @PutMapping("/logOut")
    public ResponseEntity<LogOutResponse> putLogOut(@RequestBody final LogOutRequest request){
        return ResponseEntity.ok(attendanceService.handleLogOut(request));
    }
}
