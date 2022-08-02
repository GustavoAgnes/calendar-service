package com.gustavo.agnes.calendarservice.controller;

import com.gustavo.agnes.calendarservice.service.TimeSlotService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = TimeSlotController.class)
class TimeSlotControllerTests {
    public static final String USERS_API = "/api/v1/timeslot";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TimeSlotService timeSlotService;

    @DisplayName("Given Valid Request when createAvailabilityTimeSlot then should return status 2xx.")
    @Test
    void givenValidRequestWhenCreateTimeSlotShouldReturn2xx() throws Exception {
        mockMvc
                .perform(post(USERS_API)
                        .param("personId","4")
                        .param("startTime", OffsetDateTime.now().toString())
                        .param("endTime", OffsetDateTime.now().plusHours(3).toString()))
                .andExpect(status().is2xxSuccessful());

        verify(timeSlotService, times(1)).createAvailabilityTimeSlot(any(),any(),any());
    }

    @DisplayName("Given valid Request when getTimeSlot then should return status 2xx.")
    @Test
    void givenValidRequestWhenGetTimeSlotShouldReturn2xx() throws Exception {
        mockMvc
                .perform(get(USERS_API)
                        .param("candidateId","1")
                        .param("interviewerId","2")
                        .param("startTime", OffsetDateTime.now().toString())
                        .param("endTime", OffsetDateTime.now().plusHours(3).toString()))
                .andExpect(status().is2xxSuccessful());

        verify(timeSlotService, times(1)).getAvailability(any(),any(),any(),any());
    }
}

