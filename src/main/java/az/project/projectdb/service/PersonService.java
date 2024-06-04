package az.project.projectdb.service;

import az.project.projectdb.dto.PersonDto;
import az.project.projectdb.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PersonService {

    Person createPerson(PersonDto personDto);
    List<Person> listOffPerson();
    void deletePerson(Long id);
    Optional<Person> updatePerson(PersonDto personDto);
}
