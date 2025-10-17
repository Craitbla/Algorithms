package com.university.repository;

import com.university.entity.Course;
import com.university.dto.CourseInfoDTO;

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
public class CourseRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Course> rowMapper = (rs, rowNum) -> {
        Course course = new Course();
        course.setId(rs.getLong("COURSE_ID"));
        course.setName(rs.getString("NAME"));
        course.setTeacherId(rs.getLong("TEACHER_ID"));
        course.setDescription(rs.getString("DESCRIPTION"));
        return course;
    };

    public List<Course> findAll() {
        String sql = "SELECT * FROM courses";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Optional<Course> findById(Long id) {
        String sql = "SELECT * FROM courses WHERE COURSE_ID = ?";
        try {
            Course course = jdbcTemplate.queryForObject(sql, rowMapper, id);
            return Optional.of(course);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM courses WHERE COURSE_ID = ?";
        jdbcTemplate.update(sql, id);
    }

    public void update(Course course) {
        String sql = "UPDATE courses SET COURSE_NAME = ?, TEACHER_ID = ?, DESCRIPTION = ? WHERE COURSE_ID = ?";
        jdbcTemplate.update(sql,
                course.getName(),
                course.getTeacherId(),
                course.getDescription(),
                course.getId());
    }

    public Course save(Course course) {
        if (course != null && course.getName() != null) {
            if (course.getId() != null && findById(course.getId()).isPresent()) {
                update(course);
            } else {
                String sql = "INSERT INTO courses (COURSE_NAME, TEACHER_ID, DESCRIPTION) VALUES (?, ?, ?)";
                KeyHolder keyHolder = new GeneratedKeyHolder();
                jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[]{"COURSE_ID"});
                    ps.setString(1, course.getName());
                    ps.setLong(2, course.getTeacherId());
                    ps.setString(3, course.getDescription());
                    return ps;
                }, keyHolder);

                Number newId = keyHolder.getKey();
                course.setId(newId.longValue());
            }
        }
        return course;
    }

    public List<CourseInfoDTO> findCourseInfo() {
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

