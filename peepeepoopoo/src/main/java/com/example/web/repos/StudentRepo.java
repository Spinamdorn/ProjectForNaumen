package com.example.web.repos;

import com.example.web.domain.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepo extends CrudRepository<Student,Long> {
}
