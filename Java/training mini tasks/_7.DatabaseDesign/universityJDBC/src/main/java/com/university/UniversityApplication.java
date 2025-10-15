package com.university;

import com.university.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.university.entity.Course;
import com.university.entity.Student;
import com.university.entity.Teacher;
import com.university.entity.StudentCourse;

import com.university.repository.StudentRepository;
import com.university.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class UniversityApplication implements CommandLineRunner {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentCourseRepository studentCourseRepository;


    public static void main(String[] args) {
        SpringApplication.run(UniversityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Программа запущена");

        demonstrationCRUD();
//        createTestData();
        getCoursesInformation();
    }


//    private void createTestData() {
//        Teacher teacher1 = new Teacher("Анна Игоревна Ковалёва", "+7-900-123-45-67", "anna.kovaleva@university.ru");
//        Teacher teacher2 = new Teacher("Игорь Николаевич Петров", "+7-900-987-65-43", "igor.petrov@university.ru");
//        Teacher teacher3 = new Teacher("Мария Владимировна Сидорова", "+7-911-222-33-44", "maria.sidorova@university.ru");
//
//        teacherRepository.saveAll(List.of(teacher1, teacher2, teacher3));
//
//        //teacherRepository.findById(teacher1.getId()).orElseThrow()
//        Course course1 = new Course("Введение в базы данных", teacher1, "Основы проектирования и работы с базами данных");
//        Course course2 = new Course("Философия", teacher2, "Углубленное изучение работы с базами данных");
//        Course course3 = new Course("Математический анализ", teacher3, "Дифференциальное и интегральное исчисление");
//
//        courseRepository.saveAll(List.of(course1, course2, course3));
//        Student student1 = new Student("Мария Соколова", "+7-911-111-22-33", "maria.sokolova@university.ru");
//        Student student2 = new Student("Алексей Ветров", "+7-922-333-44-55", "alexey.vetrov@university.ru");
//        Student student3 = new Student("Елена Орлова", "+7-933-555-66-77", "elena.orlova@university.ru");
//        Student student4 = new Student("Дмитрий Новиков", "+7-944-777-88-99", "dmitry.novikov@university.ru");
//
//        studentRepository.saveAll(List.of(student1, student2, student3, student4));
//
//        StudentCourse studentCourse1 = new StudentCourse(student1, course1, LocalDate.of(2024, 1, 15), 85);
//        StudentCourse studentCourse2 = new StudentCourse(student1, course2, LocalDate.of(2024, 1, 16), 90);
//        StudentCourse studentCourse3 = new StudentCourse(student2, course1, LocalDate.of(2024, 1, 15), 78);
//        StudentCourse studentCourse4 = new StudentCourse(student2, course3, LocalDate.of(2024, 1, 17), 92);
//        StudentCourse studentCourse5 = new StudentCourse(student3, course2, LocalDate.of(2024, 1, 16), 88);
//        StudentCourse studentCourse6 = new StudentCourse(student3, course3, LocalDate.of(2024, 1, 17), 95);
//        StudentCourse studentCourse7 = new StudentCourse(student4, course3, LocalDate.of(2024, 1, 17), 91);
//
//        studentCourseRepository.saveAll(List.of(studentCourse1, studentCourse2, studentCourse3, studentCourse4, studentCourse5, studentCourse6, studentCourse7));
//    }

//    private void demonstrationCRUD() {
//        //C
//        Student student = new Student("Афанасий Павлов", "+7-654-332-44-55", "AfPav@university.ru");
//        studentRepository.save(student);
//        System.out.println("Зачислен студент " + student.getFullName());
//        //R
//        List<Student> students = studentRepository.findAll();
//        System.out.println("Всего студентов: " + students.size());
//        //U
//        student.setFullName("Иванова Карина");
//        studentRepository.save(student);
//        System.out.println("Имя студента изменено на " + student.getFullName());
//        //D
//        studentRepository.delete(student);
//        students = studentRepository.findAll();
//        System.out.println("Всего студентов стало после удаления: " + students.size());
//    }

    private void getCoursesInformation() {
        List<CourseInfoDTO> coursesInfo = courseRepository.findCourseInfo();

        for (CourseInfoDTO info : coursesInfo) {
            System.out.println(info.getCourseName() + "\t" +
                    info.getTeacherName() + "\t" +
                    info.getStudentCount());
        }
    }

}
