package com.example.web.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String direction;
    private String start_date;
    private Integer duration;
    private Integer max_amount_of_stud;
    private String about;
    private String tag1_id;
    private String tag2_id;
    private String tag3_id;
    private String tag4_id;
    private String tag5_id;
    private Long teacher;


    public Course() {
    }

    public Course(String name, String direction, String start_date,
                  Integer duration, Integer max_amount_of_stud, String about,
                  String tag1_id,String tag2_id, String tag3_id, String tag4_id, String tag5_id,Long teacher) {
        this.name = name;
        this.direction = direction;
        this.start_date = start_date;
        this.duration = duration;
        this.max_amount_of_stud = max_amount_of_stud;
        this.about = about;
        this.tag1_id = tag1_id;
        this.tag2_id = tag2_id;
        this.tag3_id = tag3_id;
        this.tag4_id = tag4_id;
        this.tag5_id = tag5_id;
        this.teacher = teacher;

    }


    public Long getTeacher() {
        return teacher;
    }

    public void setTeacher_id(Long teacher) {
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getMax_amount_of_stud() {
        return max_amount_of_stud;
    }

    public void setMax_amount_of_stud(Integer max_amount_of_stud) {
        this.max_amount_of_stud = max_amount_of_stud;
    }

    public String getTag1_id() {
        return tag1_id;
    }

    public void setTag1_id(String tag1_id) {
        this.tag1_id = tag1_id;
    }

    public String getTag2_id() {
        return tag2_id;
    }

    public void setTag2_id(String tag2_id) {
        this.tag2_id = tag2_id;
    }

    public String getTag3_id() {
        return tag3_id;
    }

    public void setTag3_id(String tag3_id) {
        this.tag3_id = tag3_id;
    }

    public String getTag4_id() {
        return tag4_id;
    }

    public void setTag4_id(String tag4_id) {
        this.tag4_id = tag4_id;
    }

    public String getTag5_id() {
        return tag5_id;
    }

    public void setTag5_id(String tag5_id) {
        this.tag5_id = tag5_id;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
