package com.example.calender.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;

@Getter
@AllArgsConstructor
public class Calender {

    @Setter
    private Long id;
    private String task;
    private String name;
    private String password;
    private DateFormat date;

    public Calender(String task, DateFormat date, String password, String name) {
        this.task = task;
        this.date = date;
        this.name = name;
        this.password = password;
    }

    public void update(String task, DateFormat date) {
        this.task = task;
        this.date = date;
    }

    public void updateTask(String Task) {this.task = task;}
}
