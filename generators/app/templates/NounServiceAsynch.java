package com.example.demo.<%= nounLowercase %>;


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
 * The <%= noun %> service class (Asynchronous version)
 */

@Service
class <%= noun %>ServiceAsynch {

    private static final Logger LOG = LoggerFactory.getLogger(<%= noun %>Service.class);

    // A shared pool for create/update/delete tasks
    private final ExecutorService executorService =
            Executors.newFixedThreadPool(10, new CustomizableThreadFactory("<%= noun %>"));


    // Just for illustrative purposes; A task 'service' will be used instead
    private final AtomicInteger id = new AtomicInteger(1);

    private String genDummyId() {
        // for illustrative purposes; A task 'service' will be used instead
        return Integer.toString(id.getAndIncrement());
    }

    /**
     * Create an <%= noun %>.
     *
     * @param <%= nounLowercase %> the <%= nounLowercase %> to create
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<<%= noun %>>> create(<%= noun %> <%= nounLowercase %>) {
        return Observable.<HttpEntity<<%= noun %>>>create(sub -> {
            try {
                String taskId = genDummyId();
                submitCreateTask(<%= nounLowercase %>, taskId);
                sub.onNext(ServiceHelper.<<%= noun %>>toHttpEntity(taskId));
                sub.onCompleted();
            } catch (Exception e) {
                sub.onError(e);
            }
        }).subscribeOn(Schedulers.computation());
    }

    /**
     * Find one <%= noun %>.
     *
     * @param id the id
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<<%= noun %>>> findOne(<%= type %> id) {
        return Observable.<HttpEntity<<%= noun %>>>create(sub -> {
            try {
                sub.onNext(findOne<%= noun %>(id));
                sub.onCompleted();
            } catch (Exception e) {
                sub.onError(e);
            }
        }).subscribeOn(Schedulers.computation());

    }

    /**
     * Find all <%= nounLowercasePlural %>.
     *
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<List<<%= noun %>>>> findAll() {
        return Observable.<HttpEntity<List<<%= noun %>>>>create(sub -> {
            try {
                sub.onNext(findAll<%= nounPlural %>());
                sub.onCompleted();
            } catch (Exception e) {
                sub.onError(e);
            }
        }).subscribeOn(Schedulers.computation());
    }

    /**
     * Update an <%= noun %>.
     *
     * @param id     the id
     * @param <%= nounLowercase %> the <%= nounLowercase %>
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<<%= noun %>>> update(<%= type %> id, <%= noun %> <%= nounLowercase %>) {


        return Observable.<HttpEntity<<%= noun %>>>create(sub -> {
            try {
                String taskId = genDummyId();
                submitUpdateTask(id, <%= nounLowercase %>, taskId);
                sub.onNext(ServiceHelper.<<%= noun %>>toHttpEntity(taskId));
                sub.onCompleted();
            } catch (Exception e) {
                sub.onError(e);
            }
        }).subscribeOn(Schedulers.computation());
    }

    /**
     * Delete an <%= noun %>.
     *
     * @param id the id
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<<%= noun %>>> delete(<%= type %> id) {

        return Observable.<HttpEntity<<%= noun %>>>create(sub -> {
            try {
                String taskId = genDummyId();
                submitDeleteTask(id, taskId);
                sub.onNext(ServiceHelper.<<%= noun %>>toHttpEntity(taskId));
                sub.onCompleted();
            } catch (Exception e) {
                sub.onError(e);
            }
        }).subscribeOn(Schedulers.computation());
    }

    /**
     * Delete all <%= nounLowercasePlural %>.
     *
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<List<<%= noun %>>>> deleteAll() {

        return Observable.<HttpEntity<List<<%= noun %>>>>create(sub -> {
            try {
                String taskId = genDummyId();
                submitDeleteTask(taskId);
                sub.onNext(ServiceHelper.<List<<%= noun %>>>toHttpEntity(taskId));
                sub.onCompleted();
            } catch (Exception e) {
                sub.onError(e);
            }
        }).subscribeOn(Schedulers.computation());
    }

    /**
     * Submit a task to do the work.
     *
     * @param <%= nounLowercase %> the <%= nounLowercase %>
     * @param taskId the task id for tracking the work
     */
    private void submitCreateTask(final <%= noun %> <%= nounLowercase %>, final String taskId) {
        executorService.submit(() ->
                // TODO application logic needed
                LOG.info("On taskId {} create {} ... ", taskId, <%= nounLowercase %>)
        );
    }

    /**
     * Submit a task to do the work.
     *
     * @param id     id of the <%= nounLowercase %>
     * @param <%= nounLowercase %> the <%= nounLowercase %>
     * @param taskId the task id for tracking the work
     */
    private void submitUpdateTask(final <%= type %> id, final <%= noun %> <%= nounLowercase %>, final String taskId) {
        executorService.submit(() ->
                // TODO application logic needed
                LOG.info("On taskId {} update id {} with data {} ... ", taskId, id, <%= nounLowercase %>)

        );
    }

    /**
     * Submit a task to do the work.
     *
     * @param id     id of the <%= nounLowercase %>
     * @param taskId the task id for tracking the work
     */
    private void submitDeleteTask(final <%= type %> id, final String taskId) {
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

    private static HttpEntity<<%= noun %>> findOne<%= noun %>(<%= type %> id) {
        // TODO application logic needed
        LOG.info("Find for id {} ...", id);
        return new HttpEntity<>(new <%= noun %>());
    }

    private static HttpEntity<List<<%= noun %>>> findAll<%= nounPlural %>() {
        // TODO application logic needed
        LOG.info("Find all ...");
        return new HttpEntity<>(new ArrayList<>());
    }


}
