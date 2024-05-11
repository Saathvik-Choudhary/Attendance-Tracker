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

    /**
     * Get the total number of working hours in that particular month.
     *
     * @param monthId is the month being sent in the format of (int)yyyyMMdd.
     *
     * @return the total number of working hours in a particular month in BigDecimal milliseconds.
     */
    @Query( "SELECT SUM(e.working_time) " +
            "FROM Attendance e " +
            "WHERE e.dateId/100 = ?1/100")
    BigDecimal getMonthSummary(int monthId);

    /**
     * Finds the attendance object in the repo based on the input dateId.
     *
     * @param dateId is the month being sent in the format of (int)yyyyMMdd.
     *
     * @return the attendance object based on the input dateId.
     */
    @Query( "SELECT a " +
            "FROM Attendance a " +
            "WHERE a.dateId = ?1")
    Attendance findAttendanceByDateId(int dateId);

    /**
     * Get the logout time for the give dateId.
     *
     * @param dateId is the month being sent in the format of (int)yyyyMMdd.
     *
     * @return the logout time for the give dateId.
     */
    @Query("SELECT a.logout " +
            "FROM Attendance a " +
            "WHERE a.dateId = ?1")
    Date getLogout(int dateId);

    /**
     * Finds and returns the id for the given date id.
     *
     * @param dateId is the month being sent in the format of (int)yyyyMMdd.
     *
     * @return the optional id of the searched attendance object.
     */
    @Query( "SELECT a.dateId " +
            "FROM Attendance a " +
            "WHERE a.dateId = ?1")
    Optional<Integer> findByDateId(int dateId);
}
