package com.university.dto;

import org.springframework.jdbc.core.RowMapper;
public record CourseInfoDTO(    String courseName,
                        String teacherName,
                        Long studentCount){
        public static RowMapper<CourseInfoDTO> getRowMapper(){
        return (rs, rowNum) -> new CourseInfoDTO(rs.getString("COURSE_NAME"),
                rs.getString("FULL_NAME"),
                rs.getLong("STUDENT_COUNT"));
    }
}
