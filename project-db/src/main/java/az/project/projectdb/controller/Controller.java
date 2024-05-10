package az.project.projectdb.controller;

import az.project.projectdb.entity.Person;
import az.project.projectdb.service.PersonInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
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
    public String edit(@ModelAttribute Person person) {
        oracleService.update(person);
        return "redirect:/person-info";
    }

    @PostMapping("/delete")
    public String deleteById(@ModelAttribute Person person){
        oracleService.deleteById(person.getId());
        return "redirect:/person-info";
    }


    @PostMapping("/addPerson")
    public String addPerson(@ModelAttribute Person person,Model model) {
        oracleService.addPerson(person);
        model.addAttribute("addPerson", person);
        return "redirect:/person-info";
    }

    @GetMapping("/addPerson")
    public String add(Model model,Person person) {
            model.addAttribute("person",person);

        return "addPerson";
    }


}
