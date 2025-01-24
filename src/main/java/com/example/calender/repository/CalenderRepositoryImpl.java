package com.example.calender.repository;

import com.example.calender.dto.CalenderResponseDto;
import com.example.calender.entity.Calender;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CalenderRepositoryImpl implements CalenderRepository {

    private final Map<Long, Calender> calenderList = new HashMap<>();

    @Override
    public Calender findCalenderById(Long id) {
        return calenderList.get(id);
    }

    @Override
    public List<CalenderResponseDto> findAllCalenders() {

        List<CalenderResponseDto> allCalenders = new ArrayList<>();

        for (Calender calender : calenderList.values()) {
            CalenderResponseDto responseDto = new CalenderResponseDto(calender);
            allCalenders.add(responseDto);
        }
        return allCalenders;
    }

    @Override
    public Calender saveCalender(Calender calender) {

        Long calenderId = calenderList.isEmpty() ? 1 : Collections.max(calenderList.keySet()) + 1;
        calender.setId(calenderId);

        calenderList.put(calenderId, calender);

        return calender;
    }

    @Override
    public void deleteCalender(Long id) {
        calenderList.remove(id);
    }
}
