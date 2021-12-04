package com.example.fitnessdb.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Time;

@Entity
@Table(name = "time_course")
public class TimeCourseEntity extends BaseEntity{
    private String dayOfWeek;
    private String time;


    public TimeCourseEntity() {
    }

    @Column
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public TimeCourseEntity setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    @Column
    public String getTime() {
        return time;
    }

    public TimeCourseEntity setTime(String time) {
        this.time = time;
        return this;
    }


}
