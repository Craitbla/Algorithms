package com.university.repository;

import com.university.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class TeacherRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Teacher> rowMapper = (rs, rowNum) -> {
        Teacher teacher = new Teacher();
        teacher.setId(rs.getLong("TEACHER_ID"));
        teacher.setFullName(rs.getString("FULL_NAME"));
        teacher.setPhoneNumber(rs.getString("PHONE_NUMBER"));
        teacher.setEmail(rs.getString("EMAIL"));
        return teacher;
    };

    public List<Teacher> findAll() {
        String sql = "SELECT * FROM teachers";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Optional<Teacher> findById(Long id) {
        String sql = "SELECT * FROM teachers WHERE TEACHER_ID = ?";
        try {
            Teacher teacher = jdbcTemplate.queryForObject(sql, rowMapper, id);
            return Optional.of(teacher);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM teachers WHERE TEACHER_ID = ?";
        jdbcTemplate.update(sql, id);
    }

    public void update(Teacher teacher) {
        String sql = "UPDATE teachers SET FULL_NAME = ?, PHONE_NUMBER = ?, EMAIL = ? WHERE TEACHER_ID = ?";
        jdbcTemplate.update(sql,
                teacher.getFullName(),
                teacher.getPhoneNumber(),
                teacher.getEmail(),
                teacher.getId());
    }

    public Teacher save(Teacher teacher) {
        if (teacher != null && teacher.getFullName() != null) {
            if (teacher.getId() != null && findById(teacher.getId()).isPresent()) {
                update(teacher);
            } else {
                String sql = "INSERT INTO teachers (FULL_NAME, PHONE_NUMBER, EMAIL) VALUES (?, ?, ?)";
                KeyHolder keyHolder = new GeneratedKeyHolder();
                jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[]{"TEACHER_ID"});
                    ps.setString(1, teacher.getFullName());
                    ps.setString(2, teacher.getPhoneNumber());
                    ps.setString(3, teacher.getEmail());
                    return ps;
                }, keyHolder);

                Number newId = keyHolder.getKey();
                teacher.setId(newId.longValue());
            }
        }
        return teacher;
    }
}