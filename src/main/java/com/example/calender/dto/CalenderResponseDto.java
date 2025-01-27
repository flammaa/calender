package com.example.calender.dto;

import com.example.calender.entity.Calender;
import lombok.Getter;

@Getter
public class CalenderResponseDto {
    private Long id;
    private String task;
    private String date;
    private String name;

    public CalenderResponseDto(Calender calender) {
        this.id = calender.getId();
        this.task = calender.getTask();
        this.date = calender.getDate();
        this.name = calender.getName();
    }
}
