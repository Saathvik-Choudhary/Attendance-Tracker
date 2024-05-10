package com.example.Attendance.Tracker.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    @NotBlank(message = "Login time is mandatory.")
    @NotNull(message = "Login time is mandatory.")
    private Date login;

    @Column(name = "logout",nullable = false)
    @NotBlank(message = "Logout time is mandatory.")
    @NotNull(message = "Logout time is mandatory.")
    private Date logout;

    /**
     * The total working time is being stored in milliseconds
     */
    @Column(name = "workingTime")
    @NotBlank(message = "Working time is mandatory.")
    @NotNull(message = "Working time is mandatory.")
    private BigDecimal workingTime;



    Attendance(){
        super();
    }

    /**
     * Creating a new Attendance object
     *
     * @param login is the time at which the login was called
     *
     * @param logout is the default logout time at midnight which is being set
     */
    public Attendance(final Date login,
                      final Date logout) {
        this();

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
        if (login != null) {
            this.login = (Date) login.clone();
        } else {
            throw new IllegalArgumentException("Login timestamp cannot be null");
        }
    }

    public void setLogout(final Date logout) {
        if (logout != null) {
            this.logout = (Date) logout.clone();
            setWorkingHours();
            System.out.println(logout);
        } else {
            throw new IllegalArgumentException("Logout timestamp cannot be null");
        }
    }

    public void setWorkingHours() {
        if (login != null && logout != null && logout.after(login)) {
            Duration duration = Duration.between(login.toInstant(), logout.toInstant());
            long millis = duration.toMillis();
            if (millis >= 0 && millis <= TimeUnit.HOURS.toMillis(24)) { // Assuming maximum working time is 24 hours
                this.workingTime = BigDecimal.valueOf(millis);
            } else {
                // Handle the case where working hours are invalid
                throw new IllegalArgumentException("Invalid working hours");
            }
        } else {
            // Handle the case where logout timestamp is before login timestamp
            throw new IllegalArgumentException("Logout timestamp is before login timestamp");
        }
    }


    public Attendance(final Date login) {
        this();

        Date logout= (Date) login.clone();
        logout.setHours(23);
        logout.setMinutes(59);
        logout.setSeconds(59);

        this.logout = logout;

        setLogin(login);
        setDateId(login);
        setWorkingHours();

    }
}
