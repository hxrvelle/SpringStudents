package com.example.demo.controllers;

import com.example.demo.models.Discipline;
import com.example.demo.repository.DisciplineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class DisciplineController {
    @Autowired
    DisciplineRepo disciplineRepo;

    @GetMapping("/disciplines")
    public String showDisciplines(Model model) {
        Iterable<Discipline> disciplines = disciplineRepo.findAll();
        model.addAttribute("disciplines", disciplines);
        return "disciplines";
    }
    @GetMapping("/addDiscipline")
    public String addDiscipline() {
        return "addDiscipline";
    }
    @PostMapping("/addDiscipline")
    public String addDiscipline(@RequestParam String discipline) {
        Discipline newDiscipline = new Discipline(discipline,1);
        disciplineRepo.save(newDiscipline);
        return "redirect:/disciplines";
    }
    @GetMapping("/editDiscipline/{id}")
    public String editDiscipline(@PathVariable(value = "id") long id, Model model) {
        Optional<Discipline> discipline = disciplineRepo.findById(id);
        model.addAttribute("discipline", discipline.get());
        return "editDiscipline";
    }
    @PostMapping("/editDiscipline")
    public String editDiscipline(
            @RequestParam long id,
            @RequestParam String discipline
    ) {
        Optional<Discipline> existingDiscipline = disciplineRepo.findById(id);
        Discipline updatedDiscipline = existingDiscipline.get();
        updatedDiscipline.discipline = discipline;
        disciplineRepo.save(updatedDiscipline);
        return "redirect:/disciplines";
    }
    @GetMapping("/deleteDiscipline/{id}")
    public String deleteDiscipline(@PathVariable(value = "id") long id) {
        disciplineRepo.deleteById(id);
        return "redirect:/disciplines";
    }
}
