package com.example.demo;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Helper class for Controllers.
 */
public class ControllerHelper {

    private ControllerHelper() {
    }

    /**
     * Generate a HTTP ResponseEntity from the HttpEntity's body and header; and add in a calculated HTTP Status
     *
     * @param <T>              the type parameter
     * @param httpEntity       HttpEntity with (optional) body and (optional) headers
     * @param statusWhenNoBody status to populate if a body not available at this time
     * @return ResponseEntity for sending on the wire
     */
    public static <T> ResponseEntity<T> toResponseEntity(HttpEntity<T> httpEntity, HttpStatus statusWhenNoBody) {
        return new ResponseEntity<>(
                httpEntity.getBody(),
                httpEntity.getHeaders(),
                httpEntity.getBody() == null ? statusWhenNoBody : HttpStatus.OK
        );
    }

    /**
     * To a 200 OK HTTP ResponseEntity
     *
     * @param <T>     the type parameter
     * @param body    the body
     * @param headers the headers
     * @return the response entity
     */
    public static <T> ResponseEntity<List<T>> toOkResponseEntity(List<T> body, HttpHeaders headers) {
        return new ResponseEntity<>(
                body,
                headers,
                HttpStatus.OK
        );
    }

}
