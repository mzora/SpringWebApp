package com.example.amigoscode.api;

import com.example.amigoscode.model.Person;
import com.example.amigoscode.service.PersonService;
import com.example.amigoscode.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/savePerson")
    public ResponseEntity<Object> addPerson(@RequestBody Person person){
        personService.addPerson(person);
        ServiceResponse<Person> response = new ServiceResponse<Person>("success",person);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @GetMapping("/getAllPersons")
    public ResponseEntity<Object> getAllPersons(){
        ServiceResponse<List<Person>> response = new ServiceResponse<>("success", personService.getAllPeople());
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/api/{id}")
    public ResponseEntity<Object> getPersonById(@PathVariable("id") UUID id){
        ServiceResponse<Person> response = new ServiceResponse<>("success", personService.getPersonById(id).orElse(null));;
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @DeleteMapping(path="/api/{id}")
    public ResponseEntity<Object> deletePersonById(@PathVariable("id") UUID id){
        ServiceResponse<Object> response = new ServiceResponse<Object>("success",personService.deletePerson(id));
        System.out.println(id);
        System.out.println(response);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/api/{id}/{name}")
    public void updatePerson(@PathVariable("id")UUID id,
                             @PathVariable("name")String name){
        Person personToUpdate = new Person(id,name);
        personService.updatePerson(id, personToUpdate);
    }

/*
    @PostMapping("/savePerson")
    public void addPerson(@Valid @NotNull @RequestBody Person person){
        System.out.println(person.toString());
        personService.addPerson(person);
    }

   @GetMapping("/getAllPersons")
    public List<Person> getAllPersons(){
        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return personService.getPersonById(id).orElse(null);
    }

    @DeleteMapping(path="{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }

    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id")UUID id,
                             @Valid
                             @NotNull
                             @RequestBody Person personToUpdate){
        personService.updatePerson(id, personToUpdate);
    }
*/
}
