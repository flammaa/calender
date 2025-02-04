package com.example.calender.service;

import com.example.calender.dto.CalenderRequestDto;
import com.example.calender.dto.CalenderResponseDto;
import com.example.calender.entity.Calender;
import com.example.calender.repository.CalenderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CalenderServiceImpl implements CalenderService {

    private final CalenderRepository calenderRepository;

    public CalenderServiceImpl(CalenderRepository calenderRepository) {this.calenderRepository = calenderRepository;}

    @Override
    public CalenderResponseDto saveCalender(CalenderRequestDto requestDto) {

        Calender calender = new Calender(requestDto.getId(), requestDto.getTask(), requestDto.getName(), requestDto.getPassword(), requestDto.getCreated(), requestDto.getModified());

        return calenderRepository.saveCalender(calender);
    }

    @Override
    public List<CalenderResponseDto> findAllCalenders() {

        List<CalenderResponseDto> allCalenders = calenderRepository.findAllCalenders();

        return allCalenders;
    }

    @Override
    public CalenderResponseDto findCalenderById(Long id) {
        Optional<Calender> optionalCalender = calenderRepository.findCalenderById(id);

        if (optionalCalender.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        return new CalenderResponseDto(optionalCalender.get());
    }

    @Transactional
    @Override
    public CalenderResponseDto updateCalender(Long id, CalenderRequestDto requestDto) {

        Calender calender = calenderRepository.findCalenderById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));

        if (!calender.getPassword().equals(requestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
        }

        if (requestDto.getTask() == null || requestDto.getName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The task and date are required values.");
        }

        int updatedRow = calenderRepository.updateCalender(id, requestDto.getTask(), requestDto.getName()); // task, name

        if(updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id =" + id);
        }

        Optional<Calender> optionalCalender = calenderRepository.findCalenderById(id);

        return new CalenderResponseDto(optionalCalender.get());

    }

    @Transactional
    @Override
    public CalenderResponseDto updateTask(Long id, String task, String password) {

        Calender calender = calenderRepository.findCalenderById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));

        if (!calender.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
        }

        if (task == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The task and date are required values.");
        }

        int updatedRow = calenderRepository.updateTask(id, task);

        if(updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id =" + id);
        }

        Optional<Calender> optionalCalender = calenderRepository.findCalenderById(id);

        return new CalenderResponseDto(optionalCalender.get());
    }

    @Transactional
    @Override
    public void deleteCalender(Long id, CalenderRequestDto requestDto) {

        Calender calender = calenderRepository.findCalenderById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));

        //         비밀번호 확인
        if (!calender.getPassword().equals(requestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
        }

        int deletedRow = calenderRepository.deleteCalender(id);

        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        calenderRepository.deleteCalender(id);
    }



}
