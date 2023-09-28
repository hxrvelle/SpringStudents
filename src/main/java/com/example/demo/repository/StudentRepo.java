package com.example.demo.repository;

import com.example.demo.models.Student;
import org.springframework.data.repository.CrudRepository;
import java.sql.PreparedStatement;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends CrudRepository<Student, Long> {
}

