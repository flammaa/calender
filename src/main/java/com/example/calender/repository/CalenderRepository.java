package com.example.calender.repository;

import com.example.calender.dto.CalenderResponseDto;
import com.example.calender.entity.Calender;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CalenderRepository {
    Optional<Calender> findCalenderById(Long id);

    List<CalenderResponseDto> findAllCalenders();

    CalenderResponseDto saveCalender(Calender calender);

    int updateCalender(Long id, String task, String name);

    int updateTask(Long id, String task);

    int deleteCalender(Long id);
}
