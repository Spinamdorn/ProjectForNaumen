package com.example.web.service;

import com.example.web.domain.Student;
import com.example.web.domain.Teacher;
import com.example.web.domain.User;
import com.example.web.repos.StudentRepo;
import com.example.web.repos.TeacherRepo;
import com.example.web.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ProfileService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private UserRepo userRepo;

    public void saveStudentProfile(
             User user,
             String name,
             String surname,
             String patronymic,
             Integer group_id,
             String username,
             String password) {
        Student studentProfile = studentRepo.findById(user.getProfileId()).get();
        studentProfile.setName(name);
        studentProfile.setSurname(surname);
        studentProfile.setPatronymic(patronymic);
        studentProfile.setGroup_id(group_id);
        user.setUsername(username);
        user.setPassword(password);
        studentRepo.save(studentProfile);
        userRepo.save(user);
    }
        public void saveTeacherProfile(
                 User user,
                 String name,
                 String surname,
                 String patronymic,
                 String about,
                 String username,
                 String password) {
            Teacher teacherProfile = teacherRepo.findById(user.getProfileId()).get();
            teacherProfile.setName(name);
            teacherProfile.setSurname(surname);
            teacherProfile.setPatronymic(patronymic);
            teacherProfile.setAbout(about);
            user.setUsername(username);
            user.setPassword(password);
            teacherRepo.save(teacherProfile);
            userRepo.save(user);
    }
}
