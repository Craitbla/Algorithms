package com.university.repository;
import com.university.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
@Query("SELECT c.courseName, c.teacher.fullName, COUNT(s) FROM Course c LEFT JOIN c.students s GROUP BY c.id, c.courseName, c.teacher.fullName")
    List<Object[]> findCourseInfo();
//Каждый Object[] содержит данные для одного курса:
//[0] - название курса (String)
//[1] - имя преподавателя (String)
//[2] - количество студентов (Long)
}
