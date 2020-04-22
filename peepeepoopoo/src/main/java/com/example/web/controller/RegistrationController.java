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
import org.springframework.web.bind.annotation.RequestParam;

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
    public String addUser(User user, Map<String,Object> model,
                          @RequestParam String name,
                          @RequestParam String surname,
                          @RequestParam String patronymic,
                          @RequestParam Integer group_id
                           ){
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb!=null){
            model.put("message","Пользователь существует");
            return "registration";
        }

        Student studentProfile = new Student(name, surname, patronymic,group_id);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        studentRepo.save(studentProfile);
        user.setProfileId(studentProfile.getId());

        userRepo.save(user);

        return "redirect:/login";
    }
}
