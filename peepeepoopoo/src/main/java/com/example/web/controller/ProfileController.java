package com.example.web.controller;

import com.example.web.domain.*;
import com.example.web.repos.StudentRepo;
import com.example.web.repos.TeacherRepo;
import com.example.web.repos.UserRepo;
import com.example.web.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private ProfileService profileService;

    @GetMapping()
    public String aboutCourse(@AuthenticationPrincipal User user, Model model){
        if (user.getRoles().contains(Role.USER))
        {
            Student studentProfile = studentRepo.findById(user.getProfileId()).get();
            if (studentProfile.getName() == null)
            studentProfile.setName("");
            if (studentProfile.getSurname() == null)
            studentProfile.setSurname("");
            if (studentProfile.getPatronymic() == null)
                studentProfile.setPatronymic("");
            if (studentProfile.getGroup_id() == null)
                studentProfile.setGroup_id(0);
            model.addAttribute("studentProfile",studentProfile);
            model.addAttribute("user", user);
            model.addAttribute("courses", studentProfile.getCourses());
            return "studentProfile";
        }
        else{
            Teacher teacherProfile = teacherRepo.findById(user.getProfileId()).get();
            if (teacherProfile.getName() == null)
                teacherProfile.setName("");
            if (teacherProfile.getSurname() == null)
                teacherProfile.setSurname("");
            if (teacherProfile.getPatronymic() == null)
                teacherProfile.setPatronymic("");
            if (teacherProfile.getAbout() == null)
                teacherProfile.setAbout("");
            model.addAttribute("teacherProfile",teacherProfile);
            model.addAttribute("user", user);
            return "teacherProfile";
        }

    }
    @PostMapping("editstudentprofile")
    public String ProfileSave(
            @AuthenticationPrincipal User user,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String patronymic,
            @RequestParam Integer group_id,
            @RequestParam String username,
            @RequestParam String password){
            profileService.saveStudentProfile(user, name, surname, patronymic, group_id, username, password);
        return "redirect:/courses";
    }


    @PostMapping("editteacherprofile")
    public String teacherProfileSave(
            @AuthenticationPrincipal User user,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String patronymic,
            @RequestParam String about,
            @RequestParam String username,
            @RequestParam String password){
            profileService.saveTeacherProfile(user,name,surname,patronymic,about,username,password);
        return "redirect:/mycourses";
    }
}