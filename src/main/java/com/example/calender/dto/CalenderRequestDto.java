package com.example.calender.dto;

import lombok.Getter;

import java.text.DateFormat;

@Getter
public class CalenderRequestDto {
    private String task;
    private DateFormat date;
    private String name;
}
