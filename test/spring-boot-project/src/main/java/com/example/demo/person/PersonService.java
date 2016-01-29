package com.example.demo.person;


import org.springframework.stereotype.Service;

import java.util.Arrays;


/**
 * The Person service class, this should be implemented by the user of the tool.
 */
@Service
public class PersonService {

    /**
     * Save s.
     *
     * @param <S>      the type parameter
     * @param person the person
     * @return the s
     */
    public <S extends Person> S save(S person) {
        throw new UnsupportedOperationException();
    }

    /**
     * Save iterable.
     *
     * @param <S>       the type parameter
     * @param people the people
     * @return the iterable
     */
    public <S extends Person> Iterable<S> save(Iterable<S> people) {
        throw new UnsupportedOperationException();
    }

    /**
     * Find one person.
     *
     * @param id the id
     * @return the person
     */
    public Person findOne(String id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Exists boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean exists(String id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Find all iterable.
     *
     * @return the iterable
     */
    public Iterable<Person> findAll() {
        return Arrays.asList(new Person().builder().name("Damian ONeill").age(43).email("damian@example.com").build());
    }

    /**
     * Find all iterable.
     *
     * @param ids the ids
     * @return the iterable
     */
    public Iterable<Person> findAll(Iterable<String> ids) {
        throw new UnsupportedOperationException();
    }

    /**
     * Count long.
     *
     * @return the long
     */
    public long count() {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(String id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete.
     *
     * @param person the person
     */
    public void delete(Person person) {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete.
     *
     * @param people the people
     */
    public void delete(Iterable<? extends Person> people) {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete all.
     */
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }
}
