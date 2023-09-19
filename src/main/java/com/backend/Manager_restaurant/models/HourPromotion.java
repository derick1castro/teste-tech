package com.backend.Manager_restaurant.models;

import com.backend.Manager_restaurant.models.enums.DaysOfWeek;
import jakarta.persistence.Embeddable;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class HourPromotion {
    private List<DaysOfWeek> daysOfWeek = new ArrayList<>();
    private List<String> hours = new ArrayList<>();

    public HourPromotion(){}

    public HourPromotion(List<DaysOfWeek> daysOfWeek, List<String> hours) {
        this.daysOfWeek = daysOfWeek;
        this.hours = hours;
    }

    public List<DaysOfWeek> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(List<DaysOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public List<String> getHours() {
        return hours;
    }

    public void setHours(List<String> hours) {
        this.hours = hours;
    }
}
