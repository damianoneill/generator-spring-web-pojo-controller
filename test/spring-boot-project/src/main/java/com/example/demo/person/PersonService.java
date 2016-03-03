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
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<Person>> create(Person person) {
        // Return entity with a body if request has been fully handled.
        throw new UnsupportedOperationException();
    }

    /**
     * Find one Person.
     *
     * @param id the id
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<Person>> findOne(String id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Find all people.
     *
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<List<Person>>> findAll() {
        throw new UnsupportedOperationException();
    }

    /**
     * Update an Person.
     *
     * @param id the id
     * @param person the person
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<Person>> update(String id, Person person) {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete an Person.
     *
     * @param id the id
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<Person>> delete(String id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete all people.
     *
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<List<Person>>> deleteAll() {
        throw new UnsupportedOperationException();
    }
}
