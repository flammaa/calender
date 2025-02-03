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

    public Calender(String task, String name, String password, LocalDateTime created, LocalDateTime modified) {
        this.task = task;
        this.created = created;
        this.modified = modified;
        this.name = name;
        this.password = password;
    }


    public void update(String task, String name) {
        this.task = task;
        this.name = name;
    }

//    public void updateTask(String Task) {this.task = task;}
}
