package com.example.calender.dto;

import com.example.calender.entity.Calender;
import lombok.Getter;

import java.text.DateFormat;

@Getter
public class CalenderResponseDto {
    private Long id;
    private String task;
    private DateFormat date;

    public CalenderResponseDto(Calender calender) {
        this.id = calender.getId();
        this.task = calender.getTask();
        this.date = calender.getDate();
    }
}
