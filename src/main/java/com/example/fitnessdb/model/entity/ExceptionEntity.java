package com.example.fitnessdb.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Table(name = "exceptions")
public class ExceptionEntity extends BaseEntity{

    private String user;
    private String exceptionThrown;
    private LocalDateTime timestamp;

    public ExceptionEntity() {
    }

    @Column
    public String getUser() {
        return user;
    }

    public ExceptionEntity setUser(String user) {
        this.user = user;
        return this;
    }

    @Column
    public String getExceptionThrown() {
        return exceptionThrown;
    }

    public ExceptionEntity setExceptionThrown(String exceptionThrown) {
        this.exceptionThrown = exceptionThrown;
        return this;
    }

    @Column
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public ExceptionEntity setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
