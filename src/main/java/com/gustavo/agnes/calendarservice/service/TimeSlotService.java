package com.gustavo.agnes.calendarservice.service;

import com.gustavo.agnes.calendarservice.entity.Person;
import com.gustavo.agnes.calendarservice.entity.TimeSlot;
import com.gustavo.agnes.calendarservice.entity.TimeSlotResponse;
import com.gustavo.agnes.calendarservice.exception.InvalidTimeSlotException;
import com.gustavo.agnes.calendarservice.repository.PersonRepository;
import com.gustavo.agnes.calendarservice.repository.TimeSlotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TimeSlotService {
    private final TimeSlotRepository timeSlotRepository;
    private final PersonRepository personRepository;

    public TimeSlotService(TimeSlotRepository timeSlotRepository,
                           PersonRepository personRepository){
        this.timeSlotRepository = timeSlotRepository;
        this.personRepository = personRepository;
    }

    public void createAvailabilityTimeSlot(Long personId,
                                           LocalDateTime startTime,
                                           LocalDateTime endTime) {
        if(startTime.getMinute()!=0 || endTime.getMinute()!=0 || endTime.isBefore(startTime)){
            throw new InvalidTimeSlotException("Invalid time slot, it should be one hour");
        }

        Optional<Person> person = personRepository.findById(personId);
        if (person.isPresent()) {
            TimeSlot timeSlot = new TimeSlot(person.get(),
                    startTime,
                    endTime);
            timeSlotRepository.save(timeSlot);
        }
    }
    public List<TimeSlotResponse> getAvailability(LocalDateTime startTime, LocalDateTime endTime, Long candidateId, List<Long> interviewerIds) {
        return searchMatchingTimeSlots(
                timeSlotRepository.searchMatchingTimeSlots(startTime, endTime, candidateId, interviewerIds)
                        .stream()
                        .distinct()
                        .collect(Collectors.toList()));
    }

    private List<TimeSlotResponse> searchMatchingTimeSlots(List<TimeSlot> availableTimeSlots){
    List<TimeSlotResponse> interviewTimeSlots = new ArrayList<>();
        availableTimeSlots
            .forEach(timeSlot -> {
                LocalDateTime originalStartTime = timeSlot.getStartDateTime();
                long numberOfSlots = timeSlot
                        .getStartDateTime()
                        .until(timeSlot.getEndDateTime(), ChronoUnit.HOURS);
                for(int i = 0; i<numberOfSlots;i++){
                    LocalDateTime newSlot = timeSlot.getStartDateTime().plusHours(i);
                    interviewTimeSlots
                            .add(
                                    new TimeSlotResponse(
                                            timeSlot.getStartDateTime().truncatedTo(ChronoUnit.MINUTES),
                                            newSlot.truncatedTo(ChronoUnit.MINUTES)));
                    timeSlot.setStartDateTime(originalStartTime);
                }
            });
    return interviewTimeSlots;
    }
}
