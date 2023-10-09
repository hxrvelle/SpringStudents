package com.example.demo.controllers;

import com.example.demo.models.BindStudentDiscipline;
import com.example.demo.models.Discipline;
import com.example.demo.models.Student;
import com.example.demo.repository.BindStudentDisciplineRepo;
import com.example.demo.repository.DisciplineRepo;
import com.example.demo.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private DisciplineRepo disciplineRepo;
    @Autowired
    private BindStudentDisciplineRepo bindStudentDisciplineRepo;

    @GetMapping("/student/{id}")
    public String showStudent(@PathVariable(value = "id") Long id, Model model) {
        Optional<Student> student = studentRepo.findById(id);
        model.addAttribute("student", student.get());

        List<BindStudentDiscipline> studentDisciplines = bindStudentDisciplineRepo.findByStudentId(id);
        ArrayList<Long> disciplineIds = new ArrayList<>();
        for (int i = 0; i < studentDisciplines.size(); i++) {
            disciplineIds.add(studentDisciplines.get(i).disciplineId);
        }

        Iterable<Discipline> disciplines = disciplineRepo.findAllById(disciplineIds);
        model.addAttribute("disciplines", disciplines);
        return "student";
    }

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
        Optional<Student> student = studentRepo.findById(id);
        model.addAttribute("student", student.get());
        return "editStudent";
    }

    @PostMapping("/editStudent")
    public String editStudent(
            @RequestParam long id,
            @RequestParam String lastname,
            @RequestParam String name,
            @RequestParam String stream_group,
            @RequestParam Date date
            ) {
        Optional<Student> existingStudent = studentRepo.findById(id);
        Student student = existingStudent.get();
        student.name = name;
        student.lastname = lastname;
        student.stream_group = stream_group;
        student.date = date;
        studentRepo.save(student);
        return "redirect:/students";
    }

    @GetMapping("deleteStudent/{id}")
    public String deleteStudent(@PathVariable(value = "id") long id) {
        studentRepo.deleteById(id);
        return "redirect:/students";
    }
}
