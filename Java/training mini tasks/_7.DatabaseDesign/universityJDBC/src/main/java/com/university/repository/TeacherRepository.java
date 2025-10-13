package com.university.repository;
import com.university.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface TeacherRepository extends  JpaRepository<Teacher, Long> {
}
