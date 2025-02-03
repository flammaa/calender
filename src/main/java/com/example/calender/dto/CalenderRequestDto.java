package com.example.calender.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CalenderRequestDto {
    private String task;
    private LocalDateTime created;
    private LocalDateTime modified;
    private String name;
    private String password;

}
