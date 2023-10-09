package com.example.demo.repository;

import com.example.demo.models.BindStudentDiscipline;
import com.example.demo.models.Discipline;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BindStudentDisciplineRepo extends CrudRepository<BindStudentDiscipline, Long> {
    List<BindStudentDiscipline> findByStudentId(Long id);
}