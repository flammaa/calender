package com.example.calender.controller;

import com.example.calender.dto.CalenderRequestDto;
import com.example.calender.dto.CalenderResponseDto;
import com.example.calender.service.CalenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/calenders")
public class CalenderController {
    private final CalenderService calenderService;

    public CalenderController(CalenderService calenderService) { this.calenderService = calenderService; }

    @PostMapping
    public ResponseEntity<CalenderResponseDto> createCalender(@RequestBody CalenderRequestDto requestDto) {

        return new ResponseEntity<>(calenderService.saveCalender(requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<CalenderResponseDto> findAllCalenders() {
        return calenderService.findAllCalenders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalenderResponseDto> findCalenderById(@PathVariable Long id) {
        return new ResponseEntity<>(calenderService.findCalenderById(id), HttpStatus.OK);
    }

    @PutMapping("/{id})")
    public ResponseEntity<CalenderResponseDto> updateCalender(
            @PathVariable Long id,
            @RequestBody CalenderRequestDto requestDto
    ) {
        return new ResponseEntity<>(calenderService.updateCalender(id, requestDto.getTask(), requestDto.getDate()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletecalender(@PathVariable Long id) {
        calenderService.deleteCalender(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
