package com.example.calender.entity;

import com.example.calender.dto.CalenderRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Calender {

    private Long id;
    private String task;
    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;
    private String password;

    public Calender(Long id, String task, String name, String password, LocalDateTime created, LocalDateTime modified) {
        this.id = id;
        this.task = task;
        this.name = name;
        this.created = created;
        this.modified = modified;
        this.password = password;
    }


    public void updateCalender(Long id, String task, String name) {
        this.id = id;
        this.task = task;
        this.name = name;
    }

    public void updateTask(Long id, String Task, String password) {
        this.id = id;
        this.task = task;
        this.password = password;
    }
}
