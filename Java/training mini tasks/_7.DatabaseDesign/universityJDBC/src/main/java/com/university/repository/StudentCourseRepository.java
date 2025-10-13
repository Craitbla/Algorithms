package com.university.repository;
import com.university.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepository extends  JpaRepository<StudentCourse, Long> {
}

