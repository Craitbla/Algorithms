package com.university.repository;
import com.university.entity.Course;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
public class CourseRepository {
    private RowMapper<Course> rowMapper = (rs, rowNum) -> {
        Course course = new Course();
        course.setId(rs.getLong("COURSE_ID"));
        course.setName(rs.getString("COURSE_NAME"));
        course.setTeacher(rs.getTe("TEACHER_ID")); //на том пока и все
        course.setDescription(rs.getString("DESCRIPTION"));

    };


}

//@Query("SELECT c.name, t.fullName, COUNT(sc.student) FROM Course c JOIN c.teacher t JOIN c.studentCourses sc GROUP BY c.name, t.fullName")
//List<Object[]> findCourseInfo();
//    SELECT c.course_name, t.full_name, COUNT(sc.student_id) --по заданию
//    FROM courses c
//    JOIN teachers t USING(teacher_id)
//    JOIN student_courses sc USING (course_id)
//    GROUP BY(c.course_name, t.full_name)


//Каждый Object[] содержит данные для одного курса:
//[0] - название курса (String)
//[1] - имя преподавателя (String)
//[2] - количество студентов (Long)