package com.example.demo.person;


import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * The Person service class, this should be implemented by the user of the tool.
 */
@Service
public class PersonService {

    /**
     * Create an Person.
     *
     * @param person the person
     * @return the person
     */
    public Person create(Person person) {
        throw new UnsupportedOperationException();
    }

    /**
     * Find one Person.
     *
     * @param id the id
     * @return the person
     */
    public Person findOne(String id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Find all people.
     *
     * @return the list
     */
    public List<Person> findAll() {
        return Arrays.asList(new Person().builder().name("Damian ONeill").age(43).email("damian@example.com").build());
    }

    /**
     * Update an Person.
     *
     * @param person the person
     * @return the person
     */
    public Person update(Person person) {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete an Person.
     *
     * @param id the id
     */
    public void delete(String id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete all people.
     */
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }
}
