package com.gustavo.agnes.calendarservice.service;

import com.gustavo.agnes.calendarservice.entity.Person;
import com.gustavo.agnes.calendarservice.entity.PersonRole;
import com.gustavo.agnes.calendarservice.entity.TimeSlot;
import com.gustavo.agnes.calendarservice.entity.TimeSlotResponse;
import com.gustavo.agnes.calendarservice.repository.PersonRepository;
import com.gustavo.agnes.calendarservice.repository.TimeSlotRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TimeSlotServiceTests {
    @Mock
    TimeSlotRepository timeSlotRepository;

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    TimeSlotService timeSlotService;

    @DisplayName("Given Valid Request when getAvailability then should return status 2xx.")
    @Test
    void givenValidRequestWhenGetAvailabilityShouldReturn2xx() throws Exception {
        List<TimeSlotResponse> timeSlotResponses = timeSlotService.getAvailability(any(),any(),any(),any());

        assertThat(timeSlotResponses).isNotNull();

        verify(timeSlotRepository, times(1)).searchMatchingTimeSlots(any(),any(),any(),any());
    }
}
