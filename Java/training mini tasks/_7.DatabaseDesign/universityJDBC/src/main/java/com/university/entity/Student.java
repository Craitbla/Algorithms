package com.university.entity;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private List<StudentCourse> studentCourses = new ArrayList<>();


    public Student() {
    }

    public Student(String fullName, String phoneNumber, String email) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    //забываю прописывать public/private (((
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(List<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; //сравнение ссылок
        if (o == null || this.getClass() != o.getClass()) return false;
        if (this == (Student))
        //Сравниваются только id объектов - это бизнес-ключ для определения равенства
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
    //если равны сразу,
    //если что-то из этого нулл и тд
    //прямое сравнение с приведением
}
