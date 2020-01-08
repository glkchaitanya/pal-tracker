package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.Objects;

public class TimeEntry {

    private long id;
    private long projectId;

    public TimeEntry(long id, long projectId, long userId, LocalDate date, int hours) {
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }
    public TimeEntry(long projectId, long userId, LocalDate date, int hours) {
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }



    private long userId;
    private LocalDate date;
    private int hours;

    public TimeEntry(){

    }

/*    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        *//* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false *//*
        if (!(o instanceof TimeEntry)) {
            return false;
        }

        // Compare the data members and return accordingly
        return Long.compare(((TimeEntry) o).getId(), id) == 0;
    }

    @Override
    public int hashCode() {
    return (int)this.getId();
    }*/
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TimeEntry timeEntry = (TimeEntry) o;
    return id == timeEntry.id &&
            projectId == timeEntry.projectId &&
            userId == timeEntry.userId &&
            hours == timeEntry.hours &&
            Objects.equals(date, timeEntry.date);
}

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, userId, date, hours);
    }
}


