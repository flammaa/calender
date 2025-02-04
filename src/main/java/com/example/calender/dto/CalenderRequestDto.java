package com.example.calender.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CalenderRequestDto {
    private Long id;
    private String task;
    private String name;
    private String password;
    private LocalDateTime created;
    private LocalDateTime modified;
}
