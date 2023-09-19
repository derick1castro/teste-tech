package com.backend.Manager_restaurant.models;

import com.backend.Manager_restaurant.models.enums.DaysOfWeek;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Embeddable;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class HourOpen {
    private List<DaysOfWeek> daysOfWeek = new ArrayList<>();
    private List<String> hoursOpens = new ArrayList<>();

    public HourOpen(){}

    public HourOpen(List<DaysOfWeek> daysOfWeek, List<String> hoursOpens) {
        this.daysOfWeek = daysOfWeek;
        this.hoursOpens = hoursOpens;
    }

    public List<DaysOfWeek> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(List<DaysOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public List<String> getHoursOpens() {
        return hoursOpens;
    }

    public void setHoursOpens(List<String> hoursOpens) {
        this.hoursOpens = hoursOpens;
    }
}
