package com.example.calender.repository;

import com.example.calender.dto.CalenderResponseDto;
import com.example.calender.entity.Calender;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTemplateCalenderRepository implements CalenderRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateCalenderRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Calender findCalenderById(Long id) {
        return null;
    }

    @Override
    public List<CalenderResponseDto> findAllCalenders() {
        return List.of();
    }

    @Override
    public CalenderResponseDto saveCalender(Calender calender) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);//데이터 저장시, INSERT Query 대신
        jdbcInsert.withTableName("calender").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("task", calender.getTask());
        parameters.put("name", calender.getName());
        parameters.put("password", calender.getPassword());
        parameters.put("created", calender.getCreated());
        parameters.put("modified", calender.getModified());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new CalenderResponseDto(key.longValue(), calender.getTask(), calender.getName(), calender.getCreated(), calender.getModified());
    }

    @Override
    public void deleteCalender(Long id) {

    }
}
