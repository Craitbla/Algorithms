package com.university.dto;

import org.springframework.jdbc.core.RowMapper;
public record CourseInfoDTO(    String courseName,
                        String teacherName,
                        Long studentCount){
        public static RowMapper<CourseInfoDTO> getRowMapper(){ //смотреть именно на те названия которые делаются в select
        return (rs, rowNum) -> new CourseInfoDTO(rs.getString("COURSE_NAME"),
                rs.getString("FULL_NAME"),
                rs.getLong("STUDENT_COUNT"));
    }
};
//
//public class CourseInfoDTO {
//    private String courseName;
//    private String teacherFullName;
//    private int StudentCount;
//
//    public CourseInfoDTO() {
//    }
//
//    public CourseInfoDTO(String courseName, String teacherFullName, int StudentCount) {
//        this.courseName = courseName;
//        this.teacherFullName = teacherFullName;
//        this.StudentCount = StudentCount;
//    }
//
//    public String getCourseName() {
//        return courseName;
//    }
//
//    public void setCourseName(String courseName) {
//        this.courseName = courseName;
//    }
//
//    public String getTeacherFullName() {
//        return teacherFullName;
//    }
//
//    public void setTeacherFullName(String teacherFullName) {
//        this.teacherFullName = teacherFullName;
//    }
//
//    public int getNumOfStudents() {
//        return StudentCount;
//    }
//
//    public void setNumOfStudents(int StudentCount) {
//        this.StudentCount = StudentCount;
//    }
//
//
//
//    @Override
//    public String toString() {
//        return "CourseInfoDTO{" +
//                "courseName='" + courseName + '\'' +
//                ", teacherFullName='" + teacherFullName + '\'' +
//                ", StudentCount=" + StudentCount +
//                '}';
//    }
//
////    public static RowMapper<CourseInfoDTO> getRowMapper(){
////        return (rs, rowNum) -> new CourseInfoDTO(rs.getString("COURSE_NAME"), rs.getString("TEACHER_FULL_NAME"), rs.getInt("NUM_OF_STUDENTS"));
////    }
//    public static RowMapper<CourseInfoDTO> getRowMapper(){ //смотреть именно на те названия которые делаются в select
//        return (rs, rowNum) -> new CourseInfoDTO(rs.getString("COURSE_NAME"),
//                rs.getString("FULL_NAME"),
//                rs.getInt("STUDENT_COUNT"));
//    }
//}
