package com.example.web.repos;

import com.example.web.domain.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CourseRepo extends CrudRepository<Course, Long>{
    //List<Course>  findByNameContaining(String name);
    List<Course> findByTeacherAndName(Long teacher,String name);
    List<Course> findByTeacher(Long teacher);

}
