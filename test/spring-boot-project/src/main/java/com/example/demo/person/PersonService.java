package com.example.demo.person;


import org.springframework.stereotype.Service;

import java.util.List;

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
     * @return the list
     */
    public List<Person> findAll() {
        throw new UnsupportedOperationException();
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
