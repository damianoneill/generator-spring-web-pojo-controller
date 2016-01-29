package com.example.demo.person;


import org.springframework.stereotype.Service;

import java.util.Arrays;


/**
 * The Person service class, this should be implemented by the user of the tool.
 */
@Service
public class PersonService {

    /**
     * Create an Person.
     *
     * @param <S>    the type parameter
     * @param person the person
     * @return the s
     */
    public <S extends Person> S create(S person) {
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
     * @return the iterable
     */
    public Iterable<Person> findAll() {
        return Arrays.asList(new Person().builder().name("Damian ONeill").age(43).email("damian@example.com").build());
    }

    /**
     * Update an Person.
     *
     * @param <S>    the type parameter
     * @param person the person
     * @return the s
     */
    public <S extends Person> S update(S person) {
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
