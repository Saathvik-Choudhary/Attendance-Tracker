package com.example.Attendance.Tracker.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.*;
import java.util.Date;

@Entity
@Table(name = "attendance")
public class Attendance {


    @Column(name = "dateId")
    private int dateId;

    @Id
    @Column(name = "id",updatable = false,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login",updatable = false,nullable = false)
    private Date login;

    @Column(name = "logout",nullable = false)
    private Date logout;

    /**
     * The total working time is being stored in milliseconds
     */
    @Column(name = "workingTime")
    private BigDecimal workingTime;



    Attendance(Attendance attendance){
        super();
    }

    public Attendance(final Date login) {
        this(attendance.get());

        Date logout = new Date(login.getTime());
        logout.setHours(23);
        logout.setMinutes(59);
        logout.setSeconds(59);

        setLogin(login);
        setLogout(logout);
        setDateId(login);
        setWorkingHours();

    }

    /**
     * Get the id of the attendance
     *
     * @return the id of the attendance
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the login time of the attendance
     *
     * @return the login time of the attendance
     */
    public Date getLogin() {
        return login;
    }

    /**
     * Get the logout time of the attendance
     *
     * @return the logout time of the attendance
     */
    public Date getLogout() {
        return logout;
    }

    /**
     * Get the date id of the attendance
     *
     * @return the date id of the attendance
     */
    public int getDateId() {
        return dateId;
    }

    /**
     * Get the total number of working time in that particular login logout cycle
     *
     * @return  the total number of working time in that particular login logout cycle
     */
    public BigDecimal getWorkingTime() {
        return workingTime;
    }


    public void setDateId(final Date login) {

        LocalDate localDate = login.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int dateid=localDate.getYear()*10000 + localDate.getMonthValue()*100 + localDate.getDayOfMonth();

        this.dateId=dateid;
    }

    public void setLogin(Date login) {
        this.login = login;
    }

    public void setLogout(Date logout) {
        this.logout = logout;
    }

    public void setWorkingHours() {
        Duration duration= Duration.between(login.toInstant(), logout.toInstant());

        this.workingTime = BigDecimal.valueOf(duration.toMillis());

    }
}
