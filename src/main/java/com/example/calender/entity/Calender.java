package com.example.calender.entity;

import com.example.calender.dto.CalenderRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Calender {

    @Setter
    private Long id;
    private String task;
    private String name;
    private String date;
    private String password;

    public Calender(String task, String date, String name, String password) {
        this.task = task;
        this.date = date;
        this.name = name;
        this.password = password;
    }

    public void update(String task, String name) {
        this.task = task;
        this.name = name;
    }

//    public void updateTask(String Task) {this.task = task;}
}
