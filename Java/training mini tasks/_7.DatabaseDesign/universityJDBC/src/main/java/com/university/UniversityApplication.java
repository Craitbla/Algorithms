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

//        System.out.println(PrintFormatter.formatCourseInfo(courseRepository.findCourseInfo()));
//        courseRepository.checkTableExists();
        demonstrationAllCRUD();

    }

    private void demonstrationAllCRUD(){
        demonstrationStudentsCRUD();
        demonstrationTeachersCRUD();
        demonstrationCoursesCRUD();
        demonstrationStudentCoursesCRUD();
    }

    private void demonstrationStudentsCRUD() {
        System.out.println("Демонстрация CRUD для студентов");

        System.out.println("Изначальная таблица:");
        PrintFormatter.printTable(studentRepository.findAll(), "Студенты");

        //CREATE
        Student student = new Student();
        student.setFullName("Афанасий Павлов");
        student.setPhoneNumber("+7-654-332-44-55");
        student.setEmail("AfPav@university.ru");
        studentRepository.save(student);
        System.out.println("Зачислен студент " + student.getFullName());
        PrintFormatter.printTable(studentRepository.findAll(), "Студенты после добавления");
        System.out.println("Новоустановленный id в коде = " + student.getId());

        //READ
        Student foundStudent = studentRepository.findById(1L).orElseThrow();
        System.out.println("Найден студент с id = 1");
        PrintFormatter.printStudent(foundStudent);

        //UPDATE
        foundStudent.setFullName("Иванова Карина");
        studentRepository.save(foundStudent);
        System.out.println("Имя студента c id = 1 изменено на " + foundStudent.getFullName());
        PrintFormatter.printTable(studentRepository.findAll(), "Студенты после обновления");

        //DELETE
        studentRepository.deleteById(1L);
        System.out.println("Удален студент с id = 1");
        PrintFormatter.printTable(studentRepository.findAll(), "Студенты после удаления");

        System.out.println("Конец демонстрации для студентов\n");
    }

    private void demonstrationTeachersCRUD() {
        System.out.println("Демонстрация CRUD для преподавателей");

        System.out.println("Изначальная таблица:");
        PrintFormatter.printTable(teacherRepository.findAll(), "Преподаватели");

        //CREATE
        Teacher teacher = new Teacher();
        teacher.setFullName("Петр Сидорович Иванов");
        teacher.setPhoneNumber("+7-495-123-45-67");
        teacher.setEmail("p.ivanov@university.ru");
        teacherRepository.save(teacher);
        System.out.println("Нанят преподаватель: " + teacher.getFullName());
        PrintFormatter.printTable(teacherRepository.findAll(), "Преподаватели после добавления");
        System.out.println("Новоустановленный id преподавателя = " + teacher.getId());

        //READ
        Teacher foundTeacher = teacherRepository.findById(1L).orElseThrow();
        System.out.println("Найден преподаватель с id = 1");
        PrintFormatter.printTeacher(foundTeacher);

        //UPDATE
        foundTeacher.setFullName("Клим Васильевич Ворчунов");
        foundTeacher.setEmail("ssg_32r@university.ru");
        teacherRepository.save(foundTeacher);
        System.out.println("Данные преподавателя c id = 1 обновлены");
        PrintFormatter.printTable(teacherRepository.findAll(), "Преподаватели после обновления");

        //DELETE
        teacherRepository.deleteById(1L);
        System.out.println("Удален преподаватель с id = 1");
        PrintFormatter.printTable(teacherRepository.findAll(), "Преподаватели после удаления");

        System.out.println("Конец демонстрации для преподавателей\n");
    }

    private void demonstrationCoursesCRUD() {
        System.out.println("Демонстрация CRUD для курсов");

        System.out.println("Изначальная таблица:");
        PrintFormatter.printTable(courseRepository.findAll(), "Курсы");

        //CREATE
        Course course = new Course();
        course.setName("Математический анализ");
        course.setTeacherId(2L);
        course.setDescription("Основы математического анализа для первокурсников");
        courseRepository.save(course);
        System.out.println("Создан курс: " + course.getName());
        PrintFormatter.printTable(courseRepository.findAll(), "Курсы после добавления");
        System.out.println("Новоустановленный id курса = " + course.getId());

        //READ

        Course foundCourse = courseRepository.findById(2L).orElseThrow();
        System.out.println("Найден курс с id = 2");
        PrintFormatter.printCourse(foundCourse);

        //UPDATE
        foundCourse.setName("Продвинутый математический анализ");
        foundCourse.setDescription("Углубленный курс математического анализа");
        courseRepository.save(foundCourse);
        System.out.println("Название курса c id = 2 изменено на: " + foundCourse.getName());
        PrintFormatter.printTable(courseRepository.findAll(), "Курсы после обновления");

        //DELETE
        courseRepository.deleteById(2L);
        System.out.println("Удален курс с id = 2");
        PrintFormatter.printTable(courseRepository.findAll(), "Курсы после удаления");

        System.out.println("Конец демонстрации для курсов\n");
    }

    private void demonstrationStudentCoursesCRUD() {
        System.out.println("Демонстрация CRUD для записей на курсы");

        System.out.println("Изначальная таблица:");
        PrintFormatter.printTable(studentCourseRepository.findAll(), "Записи на курсы");

        Student student = new Student();
        student.setFullName("Тестовый Студент");
        student.setPhoneNumber("+7-000-000-00-01");
        student.setEmail("test.student@university.ru");
        studentRepository.save(student);

        Course course = new Course();
        course.setName("Тестовый Курс");
        course.setTeacherId(3L);
        course.setDescription("Курс для демонстрации");
        courseRepository.save(course);

        //CREATE
        StudentCourse enrollment = new StudentCourse();
        enrollment.setStudentId(student.getId());
        enrollment.setCourseId(course.getId());
        enrollment.setEnrollmentDate(java.time.LocalDate.now());
        enrollment.setGrade(85);
        studentCourseRepository.save(enrollment);
        System.out.println("Студент записан на курс");
        PrintFormatter.printTable(studentCourseRepository.findAll(), "Записи на курсы после добавления");
        System.out.println("Новоустановленный id записи = " + enrollment.getId());

        //READ
        StudentCourse foundEnrollment = studentCourseRepository.findById(enrollment.getId()).orElseThrow();
        System.out.println("Найдена запись с id = " + foundEnrollment.getId());
        PrintFormatter.printStudentCourse(foundEnrollment);

        //UPDATE
        foundEnrollment.setGrade(95);
        foundEnrollment.setEnrollmentDate(java.time.LocalDate.now().minusDays(1));
        studentCourseRepository.save(foundEnrollment);
        System.out.println("Оценка студента по курсу обновлена на: " + foundEnrollment.getGrade());
        PrintFormatter.printTable(studentCourseRepository.findAll(), "Записи на курсы после обновления");

        //DELETE
        studentCourseRepository.deleteById(foundEnrollment.getId());
        System.out.println("Удалена запись с id = " + foundEnrollment.getId());
        PrintFormatter.printTable(studentCourseRepository.findAll(), "Записи на курсы после удаления");

        studentRepository.deleteById(student.getId());
        courseRepository.deleteById(course.getId());

        System.out.println("Конец демонстрации для записей на курсы\n");
    }




}
