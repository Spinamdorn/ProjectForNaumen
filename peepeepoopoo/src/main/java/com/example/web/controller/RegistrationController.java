package com.example.web.controller;

import com.example.web.domain.Role;
import com.example.web.domain.Student;
import com.example.web.domain.User;
import com.example.web.repos.StudentRepo;
import com.example.web.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String,Object> model){
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb!=null){
            model.put("message","Пользователь существует");
            return "registration";
        }

        Student studentProfile = new Student();
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        studentRepo.save(studentProfile);
        user.setProfileId(studentProfile.getId());

        userRepo.save(user);

        return "redirect:/login";
    }
}
