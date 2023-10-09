package com.example.demo.models;

import jakarta.persistence.*;

@Entity
public class BindStudentDiscipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    public Long id;
    @Column(name = "student_id")
    public Long studentId;
    @Column(name = "discipline_id")
    public Long disciplineId;
}
