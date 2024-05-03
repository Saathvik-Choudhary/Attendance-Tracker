package com.example.Attendance.Tracker.percistence;

import com.example.Attendance.Tracker.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {

    @Query("SELECT SUM(e.workingHours) "
            + "FROM Attendance e "
            + "WHERE e.dateid = ?1")
    int getMonthSummary(int dateid);
}
