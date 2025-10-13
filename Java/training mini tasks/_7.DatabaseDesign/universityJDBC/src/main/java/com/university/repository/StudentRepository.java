package com.university.repository;

import com.university.entity.Student;
import com.university.entity.StudentCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Student> rowMapper = (rs, rowNum) ->{
        Student student = new Student();
        student.setId(rs.getLong("stu"));
    }

}
