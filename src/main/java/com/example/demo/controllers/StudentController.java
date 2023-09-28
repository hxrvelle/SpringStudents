package com.example.demo.controllers;

import com.example.demo.models.Student;
import com.example.demo.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

@Controller
public class StudentController {
    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/students")
    public String showStudents(Model model) {
        Iterable<Student> students = studentRepo.findAll();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/addStudent")
    public String addStudent() {
        return "addStudent";
    }

    @PostMapping("/addStudent")
    public String saveStudent(
            @RequestParam String lastname,
            @RequestParam String name,
            @RequestParam String stream_group,
            @RequestParam Date date
    ) {
        Student student = new Student(lastname, name, stream_group, true, date);
        studentRepo.save(student);
        return "redirect:/students";
    }

    @GetMapping("/editStudent/{id}")
    public String editStudent(@PathVariable(value = "id") long id, Model model) {
        new Student();
        Optional<Student> student = studentRepo.findById(id);
        if (student.isPresent()) {
            model.addAttribute("student", student.get());
        }
        return "editStudent";
    }

    @PutMapping("/editStudent/{id}")
    public String editStudent(
            @PathVariable(value = "id") Long id,
            @RequestParam String lastname,
            @RequestParam String name,
            @RequestParam String stream_group,
            @RequestParam Date date
            ) {
        Student student = new Student(lastname, name, stream_group, true, date);
        studentRepo.save(student);
        return "redirect:/students";
    }

    @GetMapping("deleteStudent/{id}")
    public String deleteStudent(@PathVariable(value = "id") long id) {
        studentRepo.deleteById(id);
        return "redirect:/students";
    }
}
