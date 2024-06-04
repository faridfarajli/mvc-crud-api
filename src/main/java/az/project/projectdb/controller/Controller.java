package az.project.projectdb.controller;

import az.project.projectdb.dto.PersonDto;
import az.project.projectdb.entity.Person;
import az.project.projectdb.service.PersonInfoService;
import az.project.projectdb.service.PersonService;
import az.project.projectdb.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.hibernate.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller {

    private final PersonInfoService oracleService;
    private final PersonInfoService service;
    private final SearchService searchService;
    private final PersonService personService;


//    @GetMapping("/person-info")
//    public String getPersonInfo() throws SQLException {
//
//        return "persons";
//    }



    //
    @GetMapping("/person-info")
    public String getPersonInfo() throws SQLException {
//        List<Person> persons = null;
//
//        if (id == 1) {
//            persons = oracleService.callPersonFirstPage();
//        } else if (id == 2) {
//            persons = oracleService.callPersonSecondPage();
//        } else if (id == 3) {
//            persons = oracleService.callPersonThirdPage();
//        } else if (id == 4) {
//            persons = oracleService.callPersonFourthPage();
//        } else if (id == 5) {
//            persons = oracleService.callPersonFivethPage();
//        } else {
//            throw new SQLException();
//        }
//        model.addAttribute("persons", persons);


        return "persons";
    }


    @GetMapping("/update/{id}")
    public String getUpdateInfo(@PathVariable Long id, Model model) {
        Optional<Person> optionalPerson = service.findById(id);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            model.addAttribute("person", person);
        } else {
            return "personNotFound";
        }
        return "update";
    }


//    @GetMapping("/test1")
//    public String a (){
//        return "all-info";
//    }

    @PostMapping("/person-info")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> testApi(Long page, Long rows) throws SQLException {
        return ResponseEntity.ok(oracleService.getItems(page, rows));
    }


    @GetMapping("/test")
    public String testApiget() {
        return "all-info";
    }


    @GetMapping("/all-info")
    public List<Person> getFAllPersons() {
        List<Person> persons = personService.listOffPerson();
        return persons;
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
    public Optional<Person> edit(@ModelAttribute PersonDto person) throws SQLException {
        Optional<Person> persons = personService.updatePerson(person);
        return persons;
    }





//    @PostMapping("/update")
//    public String edit(@ModelAttribute PersonDto person, Model model) throws SQLException {
//        String regex = "^[A-Z][a-zA-Z]{2,14}$";
//
//        if (!person.name().matches(regex)) {
//            model.addAttribute("message", "Invalid Name");
//            return "update";
//        }
//
//        if (!person.surname().matches(regex)) {
//            model.addAttribute("message", "Invalid Surname");
//            return "update";
//        }
//
//        if (!person.middleName().matches(regex)) {
//            model.addAttribute("message", "Invalid Middle Name");
//            return "update";
//        }
//
//        int rowsAffected = personService.updatePerson(person);
//        if (rowsAffected > 0) {
//            model.addAttribute("successMessage", "Person successfully updated");
//        } else {
//            model.addAttribute("message", "Update failed");
//        }
//
//        return "update";
//    }

    @PostMapping("/delete")
    public String deleteById(@ModelAttribute Person person) {
        personService.deletePerson(person.getId());
        return "redirect:/person-info";
    }
//    @PostMapping("/delete")
//    public String deleteById(@ModelAttribute Person person) {
//        oracleService.deleteById(person.getId());
//        return "redirect:/person-info";
//    }

//        @PostMapping("/addPerson")
//
//        public String addPerson(Person person, Model model) {
//            String regex = "^[A-Z][a-zA-Z]{2,14}$";
//
//            if (!person.getName().matches(regex)) {
//                model.addAttribute("message", "Inavlid Name.");
//                return "addPerson";
//            }
//
//            if (!person.getSurname().matches(regex)) {
//                model.addAttribute("message", "Inavlid Surname.");
//                return "addPerson";
//            }
//
//            if (!person.getMiddleName().matches(regex)) {
//                model.addAttribute("message", "Invalid Middle Name.");
//                return "addPerson";
//            } else {
//                model.addAttribute("successMessage", "Success.");
//                oracleService.addPerson(person);
//
//                return "redirect:/person-info";
//            }
//
//
//    }



    @PostMapping("/addPerson")
    public String addPerson(PersonDto person, Model model) {
        String regex = "^[A-Z][a-zA-Z]{2,14}$";

        if (!person.name().matches(regex)) {
            model.addAttribute("message", "Inavlid Name.");
            return "addPerson";
        }

        if (!person.surname().matches(regex)) {
            model.addAttribute("message", "Inavlid Surname.");
            return "addPerson";
        }

        if (!person.middleName().matches(regex)) {
            model.addAttribute("message", "Invalid Middle Name.");
            return "addPerson";
        } else {
            model.addAttribute("successMessage", "Success.");
            personService.createPerson(person);

            return "redirect:/person-info";
        }


    }



    @GetMapping("/addPerson")
    public String add(Model model,Person person) {
            model.addAttribute("person",person);
                return "addPerson";
    }


    @GetMapping("/details/{id}")
    public String getInfo(@PathVariable Long id, Model model) {
        Optional<Person> optionalPerson = oracleService.findById(id);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            model.addAttribute("person", person);
        } else {
            return "personNotFound";
        }
        return "details";
    }


    @GetMapping("/excel")
    public void downloadExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=persons.xlsx");
        service.exportToExcel(response);
    }

    @GetMapping("/excel-data")
    public void downloadDataExcel(@RequestParam Long page, @RequestParam Long pageSize, HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=persons-data.xlsx");
        service.exportToExcel(page, pageSize, response);
    }




    @GetMapping("/searchbyname")
    @ResponseBody
    public List<Person> searchByName(@RequestParam("query") String query) throws SQLException {
        return searchService.searchByName(query);
    }

    @GetMapping("/searchbysurname")
    @ResponseBody
    public List<Person> searchBySurname(@RequestParam("surname") String query) throws SQLException {
        return searchService.searchBySurname(query);
    }

    @GetMapping("/searchbyid")
    @ResponseBody
    public List<Person> searchById(@RequestParam("id") Long query) throws SQLException {
        return searchService.searchById(query);
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Map<String, Object>> search(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String active,
            @RequestParam(required = false) String sex) {

        List<Map<String, Object>> results = searchService.getPersonsById(id, query, type, active, sex);
        System.out.println("Search Results: " + results);
        return results;

    }



}

