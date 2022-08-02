package com.gustavo.agnes.calendarservice.repository;

import com.gustavo.agnes.calendarservice.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

    String MATCH_AGENDAS = "select distinct s1.slot_id,s1.start_time, s1.end_time, s1.person_id \n " +
            "from SLOTS s\n " +
            "inner join slots s1\n " +
            "where s.start_time= s1.start_time\n " +
            "and s.end_time = s1.end_time\n " +
            "and s.person_id = :candidate_id\n " +
            "and s1.person_id in (:interviewer_id)\n "+
            "and s1.person_id <> s.person_id\n "+
            "and (s.start_time between :start_time and :end_time) or (s.end_time between :start_time and :end_time) \n "+
            "and ((s1.start_time between :start_time and :end_time) or (s1.end_time between :start_time and :end_time)) ";

    @Query(value = MATCH_AGENDAS, nativeQuery = true)
    List<TimeSlot> searchMatchingTimeSlots(@Param("start_time") LocalDateTime startTime,
                                           @Param("end_time") LocalDateTime endTime,
                                           @Param("candidate_id") Long candidateId,
                                           @Param("interviewer_id") List<Long> interviewerIds);
}
