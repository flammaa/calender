package com.example.calender.dto;

import com.example.calender.entity.Calender;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CalenderResponseDto {
    private Long id;
    private String task;
    private LocalDateTime created;
    private LocalDateTime modified;
    private String name;

    public CalenderResponseDto(Calender calender) {
        this.id = calender.getId();
        this.task = calender.getTask();
        this.name = calender.getName();
        this.created = calender.getCreated();
        this.modified = calender.getModified();
    }

    public CalenderResponseDto(Long id, String task, String name, LocalDateTime created, LocalDateTime modified) {
        this.id = id;
        this.task = task;
        this.name = name;
        this.created = created;
        this.modified = modified;
    }
}
