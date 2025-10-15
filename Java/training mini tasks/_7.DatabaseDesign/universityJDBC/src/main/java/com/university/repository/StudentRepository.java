package com.university.repository;

import com.university.entity.Student;
import com.university.entity.StudentCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
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

    // DELETE - удалить
    public void deleteById(Long id) {
        // Здесь будет код для DELETE
        String sql = "DELETE * FROM students WHERE STUDENT_ID = ?";
        jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
//
//    // UPDATE - обновить
//    public void update(Student student) {
//        // Здесь будет код для UPDATE
//    }
//
//    // CREATE - сохранить студента
//    public Student save(Student student) {
//        // Здесь будет код для INSERT
//    }


}
