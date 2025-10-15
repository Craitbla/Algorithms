package com.university;

import com.university.dto.CourseInfoDTO;
import com.university.utils.PrintFormatter;

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
        PrintFormatter.formatCourseInfo(courseRepository.findCourseInfo());

    }


    private void demonstrationCRUD() {
        System.out.println("Изначальная таблица:");
        PrintFormatter.printTableStudents(studentRepository.findAll());
//        //C
        Student student = new Student("Афанасий Павлов", "+7-654-332-44-55", "AfPav@university.ru");
        studentRepository.save(student);
        System.out.println("Зачислен студент " + student.getFullName());
        PrintFormatter.printTableStudents(studentRepository.findAll());
        System.out.println("Его новоустанновленный id в коде = " + student.getId());
        //R
        System.out.println("Найден студент с id = 1");
        PrintFormatter.printTableStudents(List.of(studentRepository.findById(1L).orElseThrow()));
//        //U
        student = studentRepository.findById(1L).orElseThrow();
        student.setFullName("Иванова Карина");
        studentRepository.save(student);
        System.out.println("Имя студента c id = 1 изменено на " + student.getFullName());
        PrintFormatter.printTableStudents(studentRepository.findAll());
//        //D
        System.out.println("Удален студент с id = 1");
        studentRepository.deleteById(1L);
        PrintFormatter.printTableStudents(studentRepository.findAll());
    }




}
