package com.example.calender.repository;

import com.example.calender.dto.CalenderResponseDto;
import com.example.calender.entity.Calender;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateCalenderRepository implements CalenderRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateCalenderRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Calender> findCalenderById(Long id) {

        List<Calender> result = jdbcTemplate.query("SELECT * FROM calender c LEFT JOIN user u ON c.id = u.id where c.id = ?", calenderRowMapperV2(), id);
        return result.stream().findAny();
    }

    @Override
    public List<CalenderResponseDto> findAllCalenders() {
        return jdbcTemplate.query("SELECT * FROM calender c LEFT JOIN user u ON c.id = u.id", calenderRowMapper());
    }

    @Override
    public CalenderResponseDto saveCalender(Calender calender) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);//데이터 저장시, INSERT Query 대신
        jdbcInsert.withTableName("calender").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("task", calender.getTask());
        parameters.put("name", calender.getName());
        parameters.put("created", calender.getCreated());
        parameters.put("modified", calender.getModified());
        parameters.put("password", calender.getPassword());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new CalenderResponseDto(key.longValue(), calender.getTask(), calender.getName(), calender.getCreated(), calender.getModified());
    }

    @Override
    public int updateCalender(Long id, String task, String name) {
        return jdbcTemplate.update("update calender c set c.task = ?, c.name = ? where c.id = ?", task, name, id);
    }

    @Override
    public int updateTask(Long id, String task) {
        return jdbcTemplate.update("update calender c set c.task = ? where c.id = ?", task, id);
    }

    @Override
    public int deleteCalender(Long id) {
        return jdbcTemplate.update("delete from calender c where c.id = ?", id);
    }

    private RowMapper<CalenderResponseDto> calenderRowMapper() {
        return new RowMapper<CalenderResponseDto>() {
            @Override
            public CalenderResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CalenderResponseDto(
                        rs.getLong("id"),
                        rs.getString("task"),
                        rs.getString("name"),
                        rs.getTimestamp("created").toLocalDateTime(),
                        rs.getTimestamp("modified").toLocalDateTime()
                );
            }
        };
    }

    private RowMapper<Calender> calenderRowMapperV2() {
        return new RowMapper<Calender>() {
            @Override
            public Calender mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Calender(
                        rs.getLong("id"),
                        rs.getString("task"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getTimestamp("created").toLocalDateTime(),
                        rs.getTimestamp("modified").toLocalDateTime()
                );
            }
        };
    }
}
