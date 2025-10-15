package com.university.dto;

import org.springframework.jdbc.core.RowMapper;

public class CourseInfoDTO {
    private String courseName;
    private String teacherFullName;
    private int numOfStudents;

    public CourseInfoDTO() {
    }

    public CourseInfoDTO(String courseName, String teacherFullName, int numOfStudents) {
        this.courseName = courseName;
        this.teacherFullName = teacherFullName;
        this.numOfStudents = numOfStudents;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherFullName() {
        return teacherFullName;
    }

    public void setTeacherFullName(String teacherFullName) {
        this.teacherFullName = teacherFullName;
    }

    public int getNumOfStudents() {
        return numOfStudents;
    }

    public void setNumOfStudents(int numOfStudents) {
        this.numOfStudents = numOfStudents;
    }



    @Override
    public String toString() {
        return "CourseInfoDTO{" +
                "courseName='" + courseName + '\'' +
                ", teacherFullName='" + teacherFullName + '\'' +
                ", numOfStudents=" + numOfStudents +
                '}';
    }

    public static RowMapper<CourseInfoDTO> getRowMapper(){
        return (rs, rowNum) -> new CourseInfoDTO(rs.getString("COURSE_NAME"), rs.getString("TEACHER_FULL_NAME"), rs.getInt("NUM_OF_STUDENTS"));
    }
}
