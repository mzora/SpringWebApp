package com.example.amigoscode.dao;

import com.example.amigoscode.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{
    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getID().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> optPersonRequested = selectPersonById(id);
        if(optPersonRequested.isEmpty()){
            return 0;
        }
        DB.remove(optPersonRequested.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person update) {
        return selectPersonById(id)
                .map(personFoundById -> {
                    int indexOfPersonToUpdate = DB.indexOf(personFoundById);
                    if(indexOfPersonToUpdate>=0){
                        DB.set(indexOfPersonToUpdate, new Person(id, update.getName()));
                        return 1;//persona sostituita
                    }
                    return 0;
                })
                .orElse(0);//persona non trovate
    }
}
