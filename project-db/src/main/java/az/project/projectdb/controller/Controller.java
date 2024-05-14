package az.project.projectdb.controller;

import az.project.projectdb.entity.Person;
import az.project.projectdb.service.PersonInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller {
    
    private final PersonInfoService oracleService;


    @GetMapping("/person-info")
    public String getPersonInfo(Model model) throws SQLException {
        List<Person> persons = oracleService.callPersonInfoFunction();
        model.addAttribute("persons", persons);
        return "persons";
    }

    @GetMapping("/student-info")
    public String getStudentInfo(Model model) throws SQLException {
        List<Person> persons = oracleService.callPersonInfoStudent();
        model.addAttribute("persons", persons);
        return "persons";
    }


    @GetMapping("/update/{id}")
    public String getUpdateInfo(@PathVariable Long id, Model model) {
        Optional<Person> optionalPerson = oracleService.findById(id);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            model.addAttribute("person", person);
        } else {
            return "personNotFound";
        }
        return "update";
    }


    @GetMapping("/delete/{id}")
    public String getDeleteInfo(@PathVariable Long id, Model model) {
        Optional<Person> optionalPerson = oracleService.deleteFindById(id);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            model.addAttribute("person", person);
        } else {
            return "personNotFound";
        }
        return "delete";
    }


    @PostMapping("/update")
    public String edit(@ModelAttribute Person person, Model model) throws SQLException {
        String regex = "^[A-Z][a-zA-Z]{2,14}$";

        if (!person.getName().matches(regex)) {
            model.addAttribute("message", "Inavlid Name");
            return "update";
        }

        if (!person.getSurname().matches(regex)) {
            model.addAttribute("message", "Inavlid Surname");
            return "update";
        }

        if (!person.getMiddleName().matches(regex)) {
            model.addAttribute("message", "Invalid Middle Name");
            return "update";
        }

        oracleService.update(person);

        return "redirect:/person-info";
    }

    @PostMapping("/delete")
    public String deleteById(@ModelAttribute Person person){
        oracleService.deleteById(person.getId());
        return "redirect:/person-info";
    }


    @PostMapping("/addPerson")
    public String addPerson(Person person, Model model) {
        String regex = "^[A-Z][a-zA-Z]{2,14}$";

        if (!person.getName().matches(regex)) {
            model.addAttribute("message", "Inavlid Name.");
            return "addPerson";
        }

        if (!person.getSurname().matches(regex)) {
            model.addAttribute("message", "Inavlid Surname.");
            return "addPerson";
        }

        if (!person.getMiddleName().matches(regex)) {
            model.addAttribute("message", "Invalid Middle Name.");
            return "addPerson";
        }

        oracleService.addPerson(person);

        return "redirect:/person-info";
    }


    @GetMapping("/addPerson")
    public String add(Model model,Person person) {
            model.addAttribute("person",person);

        return "addPerson";
    }


}
