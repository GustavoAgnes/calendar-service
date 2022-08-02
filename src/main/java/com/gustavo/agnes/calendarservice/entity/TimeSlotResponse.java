package com.gustavo.agnes.calendarservice.entity;

import java.time.LocalDateTime;

public class TimeSlotResponse {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(final LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(final LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public TimeSlotResponse(final LocalDateTime startTime, final LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
