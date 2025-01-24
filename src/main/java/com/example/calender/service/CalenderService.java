package com.example.calender.service;

import com.example.calender.dto.CalenderRequestDto;
import com.example.calender.dto.CalenderResponseDto;

import java.text.DateFormat;
import java.util.List;

public interface CalenderService {

    CalenderResponseDto saveCalender(CalenderRequestDto requestDto);

    List<CalenderResponseDto> findAllCalenders();

    CalenderResponseDto findCalenderById(Long id);

    CalenderResponseDto updateCalender(Long id, String task, DateFormat date);

    CalenderResponseDto updateTask(Long id, String task, DateFormat date);

    void deleteCalender(Long id);
}
