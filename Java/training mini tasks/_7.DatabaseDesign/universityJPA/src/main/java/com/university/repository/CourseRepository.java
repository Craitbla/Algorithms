package com.university.repository;
import com.university.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
@Query("SELECT c.name, t.fullName, COUNT(sc.student) FROM Course c JOIN c.teacher t JOIN c.studentCourses sc GROUP BY c.name, t.fullName")
List<Object[]> findCourseInfo();

}
