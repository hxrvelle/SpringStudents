package com.example.demo.repository;

import com.example.demo.models.Discipline;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepo extends CrudRepository<Discipline, Long> {}