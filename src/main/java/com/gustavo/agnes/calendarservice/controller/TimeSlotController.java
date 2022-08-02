package com.gustavo.agnes.calendarservice.controller;

import com.gustavo.agnes.calendarservice.entity.TimeSlotResponse;
import com.gustavo.agnes.calendarservice.exception.InvalidTimeSlotException;
import com.gustavo.agnes.calendarservice.service.TimeSlotService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/timeslot")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    public TimeSlotController(final TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @PostMapping
    public void createTimeSlot(@RequestParam(name = "personId") Long personId,
                               @RequestParam(name = "startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
                               @RequestParam(name = "endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) throws InvalidTimeSlotException {
        timeSlotService.createAvailabilityTimeSlot(personId, startTime, endTime);
    }

    @GetMapping
    public List<TimeSlotResponse> getTimeSlot(@RequestParam(name = "startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
                                              @RequestParam(name = "endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
                                              @RequestParam(name = "candidateId") Long candidateId,
                                              @RequestParam(name = "interviewerId") List<Long> interviewerIds) {
        return timeSlotService.getAvailability(startTime, endTime, candidateId, interviewerIds).stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
