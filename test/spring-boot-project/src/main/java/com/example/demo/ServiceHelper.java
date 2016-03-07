package com.example.demo;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

/**
 * Helper class for Controllers.
 */
public class ServiceHelper {

    private ServiceHelper() {
    }

    /**
     * Build a HttpEntity representing an asynchronous task which will run later.
     *
     * @param taskId id task which will do the work
     * @param <T>  type of body
     * @return A HttpEntity with no body (which indicated work is not complete) but a reference in header to the task id
     */
    public static <T> HttpEntity<T> toHttpEntity(String taskId) {
        if (StringUtils.isEmpty(taskId)){
            throw new IllegalArgumentException("Invalid task id " + taskId);
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", "/tasks/" + taskId);
        return new HttpEntity<>(null, responseHeaders);
    }

}
