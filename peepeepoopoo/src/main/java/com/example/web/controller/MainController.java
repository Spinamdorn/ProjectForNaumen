package com.example.web.controller;

import com.example.web.domain.Teacher;
import com.example.web.repos.TeacherRepo;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private TeacherRepo teacherRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(name="name", required=false, defaultValue="World") String name,
                           Map<String, Object> model) {
        Iterable<Teacher> teachers = teacherRepo.findAll();
        model.put("teachers", teachers);
        return "main";
    }


    @PostMapping("/main")
    public String add( @RequestParam String name, @RequestParam String surname, Map<String, Object> model){
        Teacher teacher = new Teacher(name, surname);
        teacherRepo.save(teacher);
        Iterable<Teacher> teachers = teacherRepo.findAll();
        model.put("teachers", teachers);
        return "main";
    }



}
