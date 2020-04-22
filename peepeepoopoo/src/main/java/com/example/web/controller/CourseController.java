package com.example.web.controller;

import com.example.web.domain.Course;
import com.example.web.domain.User;
import com.example.web.repos.CourseRepo;
import com.example.web.repos.StudentRepo;
import com.example.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@Controller
public class CourseController {
    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentRepo studentRepo;

    @PreAuthorize("hasAuthority('TEACHER')")
    @GetMapping("/addcourse")
    public String newCourse() {
        return "addcourse";
    }

    @PreAuthorize("hasAuthority('TEACHER')")
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
        return "redirect:/mycourses";
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @GetMapping("/mycourses")
    public String myCourses(@RequestParam(required = false, defaultValue = "") String filter, Model model, @AuthenticationPrincipal User user) {
        Long user_id = user.getId();
        Iterable<Course> courses;
        if(filter!=null && !filter.isEmpty()) {
            courses = courseRepo.findByTeacherAndNameContaining(user_id,filter);
        }else{
            courses = courseRepo.findByTeacher(user_id);
        }
        model.addAttribute("courses", courses);
        model.addAttribute("filter", filter);
        return "myCourses";
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @GetMapping("/mycourses/{course}")
    public String courseEditForm( @PathVariable Course course, Model model){
        model.addAttribute("course", course);
        return "courseEdit";
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping("/mycourses/{course}")
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
        return "redirect:/mycourses";
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/courses")
    public String courses( @RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Course> courses;
        if(filter!=null && !filter.isEmpty()) {
            courses = courseRepo.findByNameContaining(filter);
        }else{
            courses = courseRepo.findAll();
        }
        model.addAttribute("courses", courses);
        model.addAttribute("filter", filter);
        return "courses";
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/courses/{course}")
    public String aboutCourse( @AuthenticationPrincipal User user, @PathVariable Course course, Model model){
        model.addAttribute("course", course);
        model.addAttribute("subscribers", course.getStudents().size());
        String command = "Подписаться";

        if (course.getStudents().contains(studentRepo.findById(user.getProfileId()).get()))  command = "Отписаться";
        model.addAttribute("command", command);
        return "course";
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/courses/subscribe/{course}")
    public String subscribeCourse(@AuthenticationPrincipal User user, @PathVariable Course course, Model model){
        if (course.getStudents().contains(studentRepo.findById(user.getProfileId()).get()))
            userService.unsubscribe(user,course);
        else
        userService.subscribe(user,course);
        return "redirect:/courses/{course}";
    }



}
