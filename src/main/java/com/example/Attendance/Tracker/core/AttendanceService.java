package com.example.Attendance.Tracker.core;

import com.example.Attendance.Tracker.data.*;
import com.example.Attendance.Tracker.domain.Attendance;
import com.example.Attendance.Tracker.percistence.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;

    public GetMonthWIseSummaryResponse getSummary(final GetMonthWiseSummaryRequest request){

        Date originalDate = new Date();

        LocalDate localDate = originalDate.toInstant().atZone(ZoneId.of("UTC")).toLocalDate();

        LocalDate currentMonth = localDate.withDayOfMonth(1);

        GetMonthWIseSummaryResponse response=new GetMonthWIseSummaryResponse();

        for(int i=0;i<request.getNumberOfMonths();i++){
            response.addMonthSummary(
                    new MonthSummary(
                            Date.from(currentMonth.atStartOfDay(ZoneId.of("UTC")).toInstant()),
                            attendanceRepository.getMonthSummary(currentMonth.getYear()*10000 +
                                currentMonth.getMonthValue()*100 + currentMonth.getDayOfMonth())));

            currentMonth = currentMonth.minusMonths(1);
        }

        return response;
    }

    public GetAllAttendanceResponse getAllAttendance(final GetAllAttendanceRequest request){

        int pageSize = Math.max(request.getPageSize(), 100);

        Pageable pageable = PageRequest.of(request.getPageNumber(),
                pageSize,
                Sort.by("login").descending() );

        List<AttendanceSummary> attendanceSummaries = new ArrayList<>();

        for(Attendance attendance : attendanceRepository.findAll(pageable)){
            attendanceSummaries.add(new AttendanceSummary(attendance.getLogin(),
                                    attendance.getLogout(),
                                    attendance.getWorkingTime()));
        }

        final long totalCount = attendanceRepository.count();
        Page<AttendanceSummary> page = new PageImpl<>(attendanceSummaries, pageable, totalCount);

        return new GetAllAttendanceResponse(page);
    }

    public LogInResponse HandleLogIn(final LogInRequest request){

        if(checkLogInStatus(new AttendanceCheckRequest()).getStatus()){
            ZoneOffset zoneOffset = request.getZoneOffset();

            LocalDateTime localDateTime = LocalDateTime.now();
            LocalDateTime endOfDay = localDateTime.withHour(23).withMinute(59).withSecond(59);
            OffsetDateTime offsetDateTime = endOfDay.atOffset(zoneOffset);

            Date logout = Date.from(offsetDateTime.toInstant());

            Attendance attendance=new Attendance(new Date(),logout);
            attendance= attendanceRepository.save(attendance);
            LogInResponse response=new LogInResponse(attendance.getLogin());
            response.setMessage("The login has been recorded");
            return response;
        }

        LogInResponse response=new LogInResponse(new Date());
        response.addError("The login could not be saved because a login already exists in the system.");

        return response;
    }

    public LogOutResponse HandleLogOut(final LogOutRequest request){
        if(checkLogOutStatus(new AttendanceCheckRequest()).getStatus()) {
            Date requestDate = new Date();
            LocalDate localDate = requestDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int dateId=localDate.getYear()*10000 + localDate.getMonthValue()*100 + localDate.getDayOfMonth();
            Attendance attendance = attendanceRepository.findAttendanceByDateId(dateId);
            attendance.setLogout(requestDate);
            attendanceRepository.save(attendance);
        }

        LogOutResponse response=new LogOutResponse();
        response.setMessage("Logout has been Recorded");

        return response;
    }

    public AttendanceCheckResponse checkLogInStatus(AttendanceCheckRequest request) {

        Date requestDate=new Date();

        LocalDate localDate = requestDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int dateId=localDate.getYear()*10000 + localDate.getMonthValue()*100 + localDate.getDayOfMonth();

        Optional<Integer> id = attendanceRepository.findByDateId(dateId);

        if(id.isEmpty()) {
            return new AttendanceCheckResponse(true);
        }

        return new AttendanceCheckResponse(false);
    }

    public AttendanceCheckResponse checkLogOutStatus(AttendanceCheckRequest request) {

        Date requestDate = new Date();
        LocalDate localDate = requestDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int dateId=localDate.getYear()*10000 + localDate.getMonthValue()*100 + localDate.getDayOfMonth();
        Optional<Integer> id = attendanceRepository.findByDateId(dateId);

        if(id.isEmpty()) {
            return new AttendanceCheckResponse(false);
        }

        Date logoutTime=attendanceRepository.getLogout(dateId);

        if(requestDate.before(logoutTime)) {
            return new AttendanceCheckResponse(true);
        }

        return new AttendanceCheckResponse(false);
    }
}
