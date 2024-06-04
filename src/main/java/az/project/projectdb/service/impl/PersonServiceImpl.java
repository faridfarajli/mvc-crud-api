package az.project.projectdb.service.impl;

import az.project.projectdb.dto.PersonDto;
import az.project.projectdb.entity.Person;
import az.project.projectdb.repo.PersonRepository;
import az.project.projectdb.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    @Override
    public Person createPerson(PersonDto personDto) {
        Person person = new Person();
        person.setId(personDto.id());
        person.setName(personDto.name());
        person.setSurname(personDto.surname());
        person.setMiddleName(personDto.middleName());
        personRepository.save(person);
        return person;
    }

    @Override
    public List<Person> listOffPerson() {
        return personRepository.findAll();
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);

    }

    @Override
    public Optional<Person> updatePerson(PersonDto personDto) {
        Person person = personRepository.findById(personDto.id()).orElse(null);
        person.setName(personDto.name());
        person.setSurname(personDto.surname());
        person.setMiddleName(personDto.middleName());
        return Optional.of(personRepository.save(person));
    }

}
