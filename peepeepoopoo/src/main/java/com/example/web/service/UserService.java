package com.example.web.service;

import com.example.web.domain.Course;
import com.example.web.domain.Student;
import com.example.web.domain.User;
import com.example.web.repos.CourseRepo;
import com.example.web.repos.StudentRepo;
import com.example.web.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CourseRepo curseRepo;

    @Autowired
    private StudentRepo studentRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public void subscribe(User user, Course course) {
        Student student = studentRepo.findById(user.getProfileId()).get();
        student.getCourses().add(course);
        studentRepo.save(student);
    }

    public void unsubscribe(User user, Course course) {
        Student student = studentRepo.findById(user.getProfileId()).get();
        student.getCourses().remove(course);
        studentRepo.save(student);
    }
}
