package com.example.web.controller;

import com.example.web.domain.Course;
import com.example.web.domain.User;
import com.example.web.repos.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@Controller
public class CourseController {
    @Autowired
    private CourseRepo courseRepo;

    @GetMapping("/addcourse")
    public String kek() {
        return "addcourse";
    }

    @PostMapping("/addcourse")
    public String addCourse( @RequestParam String name,
                             @RequestParam String direction,
                             @RequestParam String start_date,
                             @RequestParam Integer duration,
                             @RequestParam Integer max_amount_of_stud,
                             @RequestParam String about,
                             @RequestParam String tag1_id,
                             @RequestParam String tag2_id,
                             @RequestParam String tag3_id,
                             @RequestParam String tag4_id,
                             @RequestParam String tag5_id,
                             Map<String, Object> model) {
        Course course = new Course(name, direction, start_date, duration, max_amount_of_stud,
                about,tag1_id,tag2_id,tag3_id,tag4_id,tag5_id);
        courseRepo.save(course);
        return "redirect:/courses";
    }

    @GetMapping("/courses")
    public String courses( Map<String, Object> model) {
        Iterable<Course> courses = courseRepo.findAll();
        model.put("courses", courses);
        return "courses";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String,Object> model){
        Iterable<Course> courses;
        if(filter!=null && !filter.isEmpty()) {
            courses = courseRepo.findByNameContaining(filter);
        }else{
            courses = courseRepo.findAll();
        }
        model.put("courses", courses);
        return "courses";
    }

    @GetMapping("/courses/{course}")
    public String courseEditForm( @PathVariable Course course, Model model){
        model.addAttribute("course", course);
        return "courseEdit";
    }

}
