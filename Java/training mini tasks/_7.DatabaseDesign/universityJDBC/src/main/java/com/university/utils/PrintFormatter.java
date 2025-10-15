package com.university.utils;

import com.university.dto.CourseInfoDTO;
import com.university.entity.Student;
import com.university.repository.CourseRepository;

import java.util.List;

public class PrintFormatter {
    public static String formatCourseInfo(List<CourseInfoDTO> coursesInfo) {
        if (coursesInfo == null || coursesInfo.isEmpty()) {
            return "Нет данных о курсах";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("📊 Информация о курсах:\n")
                .append("┌──────────────────────┬──────────────────────┬─────────────────┐\n")
                .append("│ Название курса       │ Преподаватель        │ Студентов       │\n")
                .append("├──────────────────────┼──────────────────────┼─────────────────┤\n");

        for (CourseInfoDTO info : coursesInfo) {
            sb.append(String.format("│ %-20s │ %-20s │ %-15d │\n",
                    truncate(info.getCourseName(), 20),
                    truncate(info.getTeacherFullName(), 20),
                    info.getNumOfStudents()
            ));
        }

        sb.append("└──────────────────────┴──────────────────────┴─────────────────┘");
        return sb.toString();
    }

    private static String getValueOrPlaceholder(String value) {
        return value != null ? value : "─";
    }

    public static <T> void printList(List<T> list) { //ok
        for (T el : list
        ) {
            System.out.println(el.toString());
        }
        System.out.println("\n");
    }

    public static <T> void printObject(T obj) { //ok
        System.out.println(obj.toString());
        System.out.println("\n");
    }

    public static void printTableStudents(List<Student> table){
        System.out.println(getTableHeader());
        for (Student s: table
        ) {
            System.out.println(toTableString(s));
        }
        System.out.println(getTableFooter());
    }
    public static String toTableString(Student student) {
        return String.format(
                "│ %-3d │ %-20s │ %-15s │ %-25s │",
                student.getId(),
                truncate(student.getFullName(), 20),
                student.getPhoneNumber() != null ? truncate(student.getPhoneNumber(), 15) : "─",
                student.getEmail() != null ? truncate(student.getEmail(), 25) : "─"
        );
    }

    public static String getTableHeader() {
        return "┌─────┬──────────────────────┬─────────────────┬───────────────────────────┐\n" +
               "│ ID  │ ФИО                  │ Телефон         │ Email                     │\n" +
               "├─────┼──────────────────────┼─────────────────┼───────────────────────────┤";
    }

    public static String getTableFooter() {
        return "└─────┴──────────────────────┴─────────────────┴───────────────────────────┘";
    }

    private static String truncate(String text, int maxLength) {
        if (text == null) return "";
        return text.length() > maxLength ? text.substring(0, maxLength - 3) + "..." : text;
    }
}
