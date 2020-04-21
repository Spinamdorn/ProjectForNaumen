package com.example.web.controller;


import com.example.web.domain.Course;
import com.example.web.domain.Role;
import com.example.web.domain.User;
import com.example.web.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;


@Controller
@RequestMapping("/users")
@PreAuthorize("hasAuthority('SUPERUSER')")
public class UserController {
  @Autowired
  private UserRepo userRepo;

  @GetMapping
  public String getUserList(Model model){
    model.addAttribute("users", userRepo.findAll());
      return "users";
  }

  @GetMapping("/{user}")
  public String courseEditForm(@PathVariable User user, Model model){
    model.addAttribute("user", user);
    return "user";
  }

  @GetMapping("/addUser")
  public String registration(){
    return "addUser";
  }

  @PostMapping("/addUser")
  public String addTeacher(User user, Map<String,Object> model){
    User userFromDb = userRepo.findByUsername(user.getUsername());

    if (userFromDb!=null){
      model.put("message","Пользователь существует");
      return "addUser";
    }

    user.setActive(true);
    user.setRoles(Collections.singleton(Role.TEACHER));
    userRepo.save(user);
    return "redirect:/users";
  }

}
