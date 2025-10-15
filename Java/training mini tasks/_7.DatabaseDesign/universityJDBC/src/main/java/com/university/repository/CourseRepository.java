package com.university.repository;
import com.university.dto.CourseInfoDTO;
import com.university.entity.Course;
import com.university.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CourseRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Course> rowMapper = (rs, rowNum) -> {
        Course course = new Course();
        course.setId(rs.getLong("COURSE_ID"));
        course.setName(rs.getString("COURSE_NAME"));
        course.setTeacherId(rs.getLong("TEACHER_ID")); //на том пока и все
        course.setDescription(rs.getString("DESCRIPTION"));
        return course;
    };

    public List<CourseInfoDTO> findCourseInfo(){
        String sql = """
            SELECT
                c.COURSE_NAME,
                t.FULL_NAME,
                COUNT(sc.STUDENT_ID) as STUDENT_COUNT
            FROM courses c
            JOIN teachers t ON c.TEACHER_ID = t.TEACHER_ID
            JOIN student_courses sc ON c.COURSE_ID = sc.COURSE_ID
            GROUP BY c.COURSE_NAME, t.FULL_NAME
            """;
        return jdbcTemplate.query(sql, CourseInfoDTO.getRowMapper());
    }

}