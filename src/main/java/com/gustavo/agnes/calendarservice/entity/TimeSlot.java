package com.gustavo.agnes.calendarservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "SLOTS")
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long slotId;

    @Column(name = "startTime")
    private LocalDateTime startDateTime;

    @Column(name = "endTime")
    private LocalDateTime endDateTime;

    @ManyToOne
    @JoinColumn(name = "personId")
    @JsonIgnore
    private Person person;

    public TimeSlot(final Person person,
                    final LocalDateTime startTime,
                    final LocalDateTime endTime) {
        this.person = person;
        this.startDateTime = startTime;
        this.endDateTime = endTime;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "slotId=" + slotId +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", person=" + person +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TimeSlot timeSlot = (TimeSlot) o;
        return startDateTime.equals(timeSlot.startDateTime) && endDateTime.equals(timeSlot.endDateTime);
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(final Long slotId) {
        this.slotId = slotId;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(final LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(final LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(final Person person) {
        this.person = person;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDateTime, endDateTime);
    }
}
