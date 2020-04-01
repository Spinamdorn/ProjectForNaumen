package com.example.web.repos;

import com.example.web.domain.Course;
import org.springframework.data.repository.CrudRepository;


public interface CourseRepo extends CrudRepository<Course, Long>{
}
