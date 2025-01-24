package com.example.calender.service;

import com.example.calender.dto.CalenderRequestDto;
import com.example.calender.dto.CalenderResponseDto;
import com.example.calender.entity.Calender;
import com.example.calender.repository.CalenderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.DateFormat;
import java.util.List;

@Service
public class CalenderServiceImpl implements CalenderService {

    private final CalenderRepository calenderRepository;

    public CalenderServiceImpl(CalenderRepository calenderRepository) {this.calenderRepository = calenderRepository;}

    @Override
    public CalenderResponseDto saveCalender(CalenderRequestDto requestDto) {

        Calender calender = new Calender(requestDto.getTask(), requestDto.getDate(), requestDto.getName(), requestDto.getPassword());

        Calender savedCalender = calenderRepository.saveCalender(calender);

        return new CalenderResponseDto(savedCalender);
    }

    @Override
    public List<CalenderResponseDto> findAllCalenders() {

        List<CalenderResponseDto> allCalenders = calenderRepository.findAllCalenders();

        return allCalenders;
    }

    @Override
    public CalenderResponseDto findCalenderById(Long id) {
        Calender calender = calenderRepository.findCalenderById(id);

        if (calender == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        return new CalenderResponseDto(calender);
    }

    @Override
    public CalenderResponseDto updateCalender(Long id, String task, DateFormat date) {

        Calender calender = calenderRepository.findCalenderById(id);

        if(calender == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id =" + id);
        }

        if (task == null || date == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The task and date are required values.");
        }

        calender.update(task, date);

        return new CalenderResponseDto(calender);

    }

    @Override
    public CalenderResponseDto updateTask(Long id, String task, DateFormat date) {

        Calender calender = calenderRepository.findCalenderById(id);

        if(calender == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id =" + id);
        }

        if (task == null || date != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The task and date are required values.");
        }

        calender.updateTask(task);

        return new CalenderResponseDto(calender);
    }

    @Override
    public void deleteCalender(Long id) {

        Calender calender = calenderRepository.findCalenderById(id);

        if (calender == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        calenderRepository.deleteCalender(id);
    }
}
