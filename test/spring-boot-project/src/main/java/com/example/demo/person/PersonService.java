package com.example.demo.person;


import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.List;

/**
 * The Person service class, this should be implemented by the user of the tool.
 */
@Service
public class PersonService {

    /**
     * Create an Person.
     *
     * @param person the person to create
     * @return an observable that emits a HttpEntity.
     */
    public Observable<HttpEntity<Person>> create(Person person) {
        // Return entity with a body if request has been fully handled.
        throw new UnsupportedOperationException();
    }

    /**
     * Find one Person.
     *
     * @param id the id
     * @return an observable that emits a Person.
     */
    public Observable<Person> findOne(String id) {
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
