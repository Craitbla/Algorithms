package com.university.repository;

import com.university.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class StudentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Student> rowMapper = (rs, rowNum) -> {
        Student student = new Student();
        student.setId(rs.getLong("STUDENT_ID"));
        student.setFullName(rs.getString("FULL_NAME"));
        student.setPhoneNumber(rs.getString("PHONE_NUMBER"));
        student.setEmail(rs.getString("EMAIL"));
        return student;
    };

    public List<Student> findAll() {
        String sql = "SELECT * FROM students";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Optional<Student> findById(Long id) {
        String sql = "SELECT * FROM students WHERE STUDENT_ID = ?";
        try {
            Student student = jdbcTemplate.queryForObject(sql, rowMapper, id);
            return Optional.of(student);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
    public void deleteById(Long id) {
        String sql = "DELETE FROM students WHERE STUDENT_ID = ?";
        jdbcTemplate.update(sql, id);
    }

    public void update(Student student) {
        String sql = "UPDATE students SET FULL_NAME = ?, PHONE_NUMBER = ?, EMAIL = ? WHERE STUDENT_ID = ?";
        jdbcTemplate.update(sql, student.getFullName(), student.getPhoneNumber(), student.getEmail(), student.getId());
    }

    public Student save(Student student) {
        if (student != null && student.getFullName() != null) {
            if (student.getId() != null && findById(student.getId()).isPresent()) {
                update(student);
            } else {
                String sql = "INSERT INTO students (FULL_NAME, PHONE_NUMBER, EMAIL) VALUES (?, ?, ?)";
                KeyHolder keyHolder = new GeneratedKeyHolder();
                jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[]{"STUDENT_ID"});
                    ps.setString(1, student.getFullName());
                    ps.setString(2, student.getPhoneNumber());
                    ps.setString(3, student.getEmail());
                    return ps;
                }, keyHolder);
                Number newId = keyHolder.getKey();
                student.setId(newId.longValue());
            }
        }
        return student;
    }
}
