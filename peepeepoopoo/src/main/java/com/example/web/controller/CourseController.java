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
    public String newCourse() {
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
                             @AuthenticationPrincipal User user,
                             Map<String, Object> model) {
        Long user_id = user.getId();
        Course course = new Course(name, direction, start_date, duration, max_amount_of_stud,
                about,tag1_id,tag2_id,tag3_id,tag4_id,tag5_id,user_id);
        courseRepo.save(course);
        return "redirect:/courses";
    }

    @GetMapping("/courses")
    public String courses( @RequestParam(required = false, defaultValue = "") String filter, Model model,@AuthenticationPrincipal User user) {
        Long user_id = user.getId();
        Iterable<Course> courses;
        if(filter!=null && !filter.isEmpty()) {
            courses = courseRepo.findByTeacherAndName(user_id,filter);
        }else{
            courses = courseRepo.findByTeacher(user_id);
        }
        model.addAttribute("courses", courses);
        model.addAttribute("filter", filter);
        return "courses";
    }


    @GetMapping("/courses/{course}")
    public String courseEditForm( @PathVariable Course course, Model model){
        model.addAttribute("course", course);
        return "courseEdit";
    }

    @PostMapping("/courses/{course}")
    public String courseSave(
                             @PathVariable Course course,
            @RequestParam String name,
                             @RequestParam String direction,
                             @RequestParam String start_date,
                             @RequestParam Integer duration,
                             @RequestParam Integer max_amount_of_stud,
                             @RequestParam String about,
                             @RequestParam String tag1_id,
                             @RequestParam String tag2_id,
                             @RequestParam String tag3_id,
                             @RequestParam String tag4_id,
                             @RequestParam String tag5_id ){
        course.setName(name);
        course.setDirection(direction);
        course.setStart_date(start_date);
        course.setDuration(duration);
        course.setMax_amount_of_stud(max_amount_of_stud);
        course.setAbout(about);
        course.setTag1_id(tag1_id);
        course.setTag2_id(tag2_id);
        course.setTag3_id(tag3_id);
        course.setTag4_id(tag4_id);
        course.setTag5_id(tag5_id);
        courseRepo.save(course);
        return "redirect:/courses";
    }

}
