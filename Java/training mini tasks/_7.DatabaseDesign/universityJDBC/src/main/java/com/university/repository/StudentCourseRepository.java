package com.university.repository;

import com.university.entity.StudentCourse;
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
public class StudentCourseRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<StudentCourse> rowMapper = (rs, rowNum) -> {
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setId(rs.getLong("ID"));
        studentCourse.setStudentId(rs.getLong("STUDENT_ID"));
        studentCourse.setCourseId(rs.getLong("COURSE_ID"));

        java.sql.Date sqlDate = rs.getDate("ENROLLMENT_DATE");
        if (sqlDate != null) {
            studentCourse.setEnrollmentDate(sqlDate.toLocalDate());
        }

        studentCourse.setGrade(rs.getInt("GRADE"));
        return studentCourse;
    };

    public List<StudentCourse> findAll() {
        String sql = "SELECT * FROM student_courses";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Optional<StudentCourse> findById(Long id) {
        String sql = "SELECT * FROM student_courses WHERE ID = ?";
        try {
            StudentCourse studentCourse = jdbcTemplate.queryForObject(sql, rowMapper, id);
            return Optional.of(studentCourse);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM student_courses WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }

    public void update(StudentCourse studentCourse) {
        String sql = "UPDATE student_courses SET STUDENT_ID = ?, COURSE_ID = ?, ENROLLMENT_DATE = ?, GRADE = ? WHERE ID = ?";
        jdbcTemplate.update(sql,
                studentCourse.getStudentId(),
                studentCourse.getCourseId(),
                studentCourse.getEnrollmentDate(),
                studentCourse.getGrade(),
                studentCourse.getId());
    }

    public StudentCourse save(StudentCourse studentCourse) {
        if (studentCourse != null && studentCourse.getStudentId() != null && studentCourse.getCourseId() != null) {
            if (studentCourse.getId() != null && findById(studentCourse.getId()).isPresent()) {
                update(studentCourse);
            } else {
                String sql = "INSERT INTO student_courses (STUDENT_ID, COURSE_ID, ENROLLMENT_DATE, GRADE) VALUES (?, ?, ?, ?)";
                KeyHolder keyHolder = new GeneratedKeyHolder();
                jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[]{"ID"});
                    ps.setLong(1, studentCourse.getStudentId());
                    ps.setLong(2, studentCourse.getCourseId());
                    ps.setDate(3, java.sql.Date.valueOf(studentCourse.getEnrollmentDate()));
                    ps.setObject(4, studentCourse.getGrade());
                    return ps;
                }, keyHolder);

                Number newId = keyHolder.getKey();
                studentCourse.setId(newId.longValue());
            }
        }
        return studentCourse;
    }
}