package com.university.utils;

import com.university.dto.CourseInfoDTO;
import com.university.entity.*;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class PrintFormatter {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String formatCourseInfo(List<CourseInfoDTO> coursesInfo) {
        if (coursesInfo == null || coursesInfo.isEmpty()) {
            return "Нет данных о курсах";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Информация о курсах:\n")
                .append("┌──────────────────────┬──────────────────────┬─────────────────┐\n")
                .append("│ Название курса       │ Преподаватель        │ Студентов       │\n")
                .append("├──────────────────────┼──────────────────────┼─────────────────┤\n");

        for (CourseInfoDTO info : coursesInfo) {
            sb.append(String.format("│ %-20s │ %-20s │ %-15d │\n",
                    truncate(info.courseName(), 20),
                    truncate(info.teacherName(), 20),
                    info.studentCount()
            ));
        }

        sb.append("└──────────────────────┴──────────────────────┴─────────────────┘");
        return sb.toString();
    }

    public static <T> void printList(List<T> list) {
        for (T el : list) {
            System.out.println(el.toString());
        }
        System.out.println("\n");
    }

    public static <T> void printObject(T obj) {
        System.out.println(obj.toString());
        System.out.println("\n");
    }

    public static void printTableStudents(List<Student> table) {
        System.out.println(getTableHeaderStudents());
        for (Student s : table) {
            System.out.println(toTableString(s));
        }
        System.out.println(getTableFooterStudents());
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

    public static String getTableHeaderStudents() {
        return "┌─────┬──────────────────────┬─────────────────┬───────────────────────────┐\n" +
               "│ ID  │ ФИО                  │ Телефон         │ Email                     │\n" +
               "├─────┼──────────────────────┼─────────────────┼───────────────────────────┤";
    }

    public static String getTableFooterStudents() {
        return "└─────┴──────────────────────┴─────────────────┴───────────────────────────┘";
    }

    public static void printTableCourses(List<Course> table) {
        System.out.println(getTableHeaderCourses());
        for (Course course : table) {
            System.out.println(toTableString(course));
        }
        System.out.println(getTableFooterCourses());
    }

    public static String toTableString(Course course) {
        return String.format(
                "│ %-3d │ %-25s │ %-13d │ %-30s │",
                course.getId(),
                truncate(course.getName(), 25),
                course.getTeacherId(),
                course.getDescription() != null ? truncate(course.getDescription(), 30) : "─"
        );
    }

    public static String getTableHeaderCourses() {
        return "┌─────┬───────────────────────────┬───────────────┬────────────────────────────────┐\n" +
               "│ ID  │ Название курса            │IDпреподавателя│ Описание                       │\n" +
               "├─────┼───────────────────────────┼───────────────┼────────────────────────────────┤";
    }

    public static String getTableFooterCourses() {
        return "└─────┴───────────────────────────┴───────────────┴────────────────────────────────┘";
    }

    public static void printTableTeachers(List<Teacher> table) {
        System.out.println(getTableHeaderTeachers());
        for (Teacher teacher : table) {
            System.out.println(toTableString(teacher));
        }
        System.out.println(getTableFooterTeachers());
    }

    public static String toTableString(Teacher teacher) {
        return String.format(
                "│ %-3d │ %-25s │ %-15s │ %-25s │",
                teacher.getId(),
                truncate(teacher.getFullName(), 25),
                teacher.getPhoneNumber() != null ? truncate(teacher.getPhoneNumber(), 15) : "─",
                teacher.getEmail() != null ? truncate(teacher.getEmail(), 25) : "─"
        );
    }

    public static String getTableHeaderTeachers() {
        return "┌─────┬───────────────────────────┬─────────────────┬───────────────────────────┐\n" +
               "│ ID  │ ФИО                       │ Телефон         │ Email                     │\n" +
               "├─────┼───────────────────────────┼─────────────────┼───────────────────────────┤";
    }

    public static String getTableFooterTeachers() {
        return "└─────┴───────────────────────────┴─────────────────┴───────────────────────────┘";
    }

    public static void printTableStudentCourses(List<StudentCourse> table) {
        System.out.println(getTableHeaderStudentCourses());
        for (StudentCourse enrollment : table) {
            System.out.println(toTableString(enrollment));
        }
        System.out.println(getTableFooterStudentCourses());
    }

    public static String toTableString(StudentCourse enrollment) {
        String dateStr = enrollment.getEnrollmentDate() != null
                ? enrollment.getEnrollmentDate().format(DATE_FORMATTER)
                : "─";

        String gradeStr = enrollment.getGrade() != null
                ? enrollment.getGrade().toString()
                : "─";

        return String.format(
                "│ %-3d │ %-11d │ %-9d │ %-12s │ %-5s │",
                enrollment.getId(),
                enrollment.getStudentId(),
                enrollment.getCourseId(),
                dateStr,
                gradeStr
        );
    }

    public static String getTableHeaderStudentCourses() {
        return "┌─────┬─────────────┬───────────┬──────────────┬───────┐\n" +
               "│ ID  │ ID студента │ ID курса  │ Дата записи  │ Оценка│\n" +
               "├─────┼─────────────┼───────────┼──────────────┼───────┤";
    }

    public static String getTableFooterStudentCourses() {
        return "└─────┴─────────────┴───────────┴──────────────┴───────┘";
    }

    public static <T> void printTable(List<T> items, String title) {
        if (items == null || items.isEmpty()) {
            System.out.println(title + ": Нет данных");
            return;
        }

        System.out.println(title + ":");

        if (items.get(0) instanceof Student) {
            printTableStudents((List<Student>) items);
        } else if (items.get(0) instanceof Course) {
            printTableCourses((List<Course>) items);
        } else if (items.get(0) instanceof Teacher) {
            printTableTeachers((List<Teacher>) items);
        } else if (items.get(0) instanceof StudentCourse) {
            printTableStudentCourses((List<StudentCourse>) items);
        } else if (items.get(0) instanceof CourseInfoDTO) {
            System.out.println(formatCourseInfo((List<CourseInfoDTO>) items));
        } else {
            printList(items);
        }
        System.out.println();
    }

    public static void printStudent(Student student) {
        System.out.println("Информация о студенте:");
        System.out.println(getTableHeaderStudents());
        System.out.println(toTableString(student));
        System.out.println(getTableFooterStudents());
        System.out.println();
    }

    public static void printCourse(Course course) {
        System.out.println("Информация о курсе:");
        System.out.println(getTableHeaderCourses());
        System.out.println(toTableString(course));
        System.out.println(getTableFooterCourses());
        System.out.println();
    }

    public static void printTeacher(Teacher teacher) {
        System.out.println("Информация о преподавателе:");
        System.out.println(getTableHeaderTeachers());
        System.out.println(toTableString(teacher));
        System.out.println(getTableFooterTeachers());
        System.out.println();
    }

    public static void printStudentCourse(StudentCourse enrollment) {
        System.out.println("Запись на курс:");
        System.out.println(getTableHeaderStudentCourses());
        System.out.println(toTableString(enrollment));
        System.out.println(getTableFooterStudentCourses());
        System.out.println();
    }

    private static String truncate(String text, int maxLength) {
        if (text == null) return "";
        return text.length() > maxLength ? text.substring(0, maxLength - 3) + "..." : text;
    }
}