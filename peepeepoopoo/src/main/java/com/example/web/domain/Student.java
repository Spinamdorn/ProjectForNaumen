package com.example.web.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private Integer group_id;
    @ManyToMany
    @JoinTable(name = "student_subscriptions",
    joinColumns = { @JoinColumn(name = "student_id")},
    inverseJoinColumns = {@JoinColumn(name="course_id")})
    private Set<Course> courses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student(String name, String surname, String patronymic, Integer group_id) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.group_id = group_id;
    }

    public Student() {
    }

    public Student(Long userId, String name, String surname, String patronymic, Integer group_id) {

        this.name=name;
        this.surname=surname;
        this.patronymic=patronymic;
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group) {
        this.group_id = group;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
