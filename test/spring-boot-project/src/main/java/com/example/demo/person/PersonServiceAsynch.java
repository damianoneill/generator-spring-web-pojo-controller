package com.example.demo.person;


import com.example.demo.ServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Service;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The Person service class (Asynchronous version)
 */

@Service
class PersonServiceAsynch {

    private static final Logger LOG = LoggerFactory.getLogger(PersonService.class);

    // A shared pool for create/update/delete tasks
    private final ExecutorService executorService =
            Executors.newFixedThreadPool(10, new CustomizableThreadFactory("Person"));


    // Just for illustrative purposes; A task 'service' should be used instead
    private final AtomicInteger id = new AtomicInteger(1);

    private String genDummyId() {
        // for illustrative purposes; A task 'service' should be used instead
        return Integer.toString(id.getAndIncrement());
    }

    /**
     * Create an Person.
     *
     * @param person the person to create
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<Person>> create(Person person) {
        return Observable.<HttpEntity<Person>>create(sub -> {
            try {
                String taskId = genDummyId();
                submitCreateTask(person, taskId);
                sub.onNext(ServiceHelper.<Person>toHttpEntity(taskId));
                sub.onCompleted();
            } catch (Exception e) {
                sub.onError(e);
            }
        }).subscribeOn(Schedulers.computation());
    }

    /**
     * Find one Person.
     *
     * @param id the id
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<Person>> findOne(String id) {
        return Observable.<HttpEntity<Person>>create(sub -> {
            try {
                sub.onNext(findOnePerson(id));
                sub.onCompleted();
            } catch (Exception e) {
                sub.onError(e);
            }
        }).subscribeOn(Schedulers.computation());

    }

    /**
     * Find all people.
     *
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<List<Person>>> findAll() {
        return Observable.<HttpEntity<List<Person>>>create(sub -> {
            try {
                sub.onNext(findAllPeople());
                sub.onCompleted();
            } catch (Exception e) {
                sub.onError(e);
            }
        }).subscribeOn(Schedulers.computation());
    }

    /**
     * Update an Person.
     *
     * @param id     the id
     * @param person the person
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<Person>> update(String id, Person person) {


        return Observable.<HttpEntity<Person>>create(sub -> {
            try {
                String taskId = genDummyId();
                submitUpdateTask(id, person, taskId);
                sub.onNext(ServiceHelper.<Person>toHttpEntity(taskId));
                sub.onCompleted();
            } catch (Exception e) {
                sub.onError(e);
            }
        }).subscribeOn(Schedulers.computation());
    }

    /**
     * Delete an Person.
     *
     * @param id the id
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<Person>> delete(String id) {

        return Observable.<HttpEntity<Person>>create(sub -> {
            try {
                String taskId = genDummyId();
                submitDeleteTask(id, taskId);
                sub.onNext(ServiceHelper.<Person>toHttpEntity(taskId));
                sub.onCompleted();
            } catch (Exception e) {
                sub.onError(e);
            }
        }).subscribeOn(Schedulers.computation());
    }

    /**
     * Delete all people.
     *
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<List<Person>>> deleteAll() {

        return Observable.<HttpEntity<List<Person>>>create(sub -> {
            try {
                String taskId = genDummyId();
                submitDeleteTask(taskId);
                sub.onNext(ServiceHelper.<List<Person>>toHttpEntity(taskId));
                sub.onCompleted();
            } catch (Exception e) {
                sub.onError(e);
            }
        }).subscribeOn(Schedulers.computation());
    }

    /**
     * Submit a task to do the work.
     *
     * @param person the person
     * @param taskId the task id for tracking the work
     */
    private void submitCreateTask(final Person person, final String taskId) {
        executorService.submit(() ->
                // TODO application logic needed
                LOG.info("On taskId {} create {} ... ", taskId, person)
        );
    }

    /**
     * Submit a task to do the work.
     *
     * @param id     id of the person
     * @param person the person
     * @param taskId the task id for tracking the work
     */
    private void submitUpdateTask(final String id, final Person person, final String taskId) {
        executorService.submit(() ->
                // TODO application logic needed
                LOG.info("On taskId {} update id {} with data {} ... ", taskId, id, person)

        );
    }

    /**
     * Submit a task to do the work.
     *
     * @param id     id of the person
     * @param taskId the task id for tracking the work
     */
    private void submitDeleteTask(final String id, final String taskId) {
        executorService.submit(() ->
                // TODO application logic needed
                LOG.info("On taskId {} delete id {} ... ", taskId, id)
        );
    }

    /**
     * Submit a task to do the work.
     *
     * @param taskId the task id for tracking the work
     */
    private void submitDeleteTask(final String taskId) {
        executorService.submit(() ->
                // TODO application logic needed
                LOG.info("On taskId {} delete all ... ", taskId)
        );
    }

    private static HttpEntity<Person> findOnePerson(String id) {
        // TODO application logic needed
        LOG.info("Find for id {} ...", id);
        return new HttpEntity<>(new Person());
    }

    private static HttpEntity<List<Person>> findAllPeople() {
        // TODO application logic needed
        LOG.info("Find all ...");
        return new HttpEntity<>(new ArrayList<>());
    }


}
