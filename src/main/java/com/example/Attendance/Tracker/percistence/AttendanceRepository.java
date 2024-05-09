package com.example.Attendance.Tracker.percistence;

import com.example.Attendance.Tracker.core.AttendanceService;
import com.example.Attendance.Tracker.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {


    @Query( "SELECT SUM(e.workingTime) " +
            "FROM Attendance e " +
            "WHERE e.dateId/100 = ?1/100")
    BigDecimal getMonthSummary(int monthId);

    @Query( "SELECT MAX(e.id) " +
            "FROM Attendance e")
    Long getLastEntry();


    @Query( "SELECT a.dateId " +
            "FROM Attendance a " +
            "WHERE a.dateId = ?1")
    Optional<Integer> findByDateId(int dateId);

    @Query( "SELECT a " +
            "FROM Attendance a " +
            "WHERE a.dateId = ?1")
    Attendance findAttendanceByDateId(int dateId);

    @Query("SELECT a.logout " +
            "FROM Attendance a " +
            "WHERE a.dateId = ?1")
    Date getLogout(int dateId);

    @Override
    @Query("SELECT a FROM Attendance a WHERE a.id=?1 ")
    Attendance getById(Long id);
}
