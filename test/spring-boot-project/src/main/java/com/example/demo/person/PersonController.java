package com.example.demo.person;

import com.example.demo.ClientErrorInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Person REST Controller.
 * This code is auto-generated do not override, instead raise a feature request against the generator tool.
 */
@RestController
public class PersonController {

    public static final String NOT_AVAILABLE = "Not Available";
    public static final String FIELD_ERROR_IN_OBJECT = "Field error in object \'";
    public static final String ON_FIELD = "\' on field \'";
    public static final String COLON = "\': ";

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public DeferredResult<ResponseEntity<Person>> create(@RequestBody @Valid Person person) {
        DeferredResult<ResponseEntity<Person>> deferred = new DeferredResult<>();

        personService.create(person).subscribe(personHttpEntity -> {
            deferred.setResult(new ResponseEntity<>(
                    personHttpEntity.getBody(),
                    personHttpEntity.getHeaders(),
                    personHttpEntity.getBody() == null ? HttpStatus.ACCEPTED : HttpStatus.OK
            ));
        }, deferred::setErrorResult);

        return deferred;
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public DeferredResult<ResponseEntity<Person>> findOne(@PathVariable("id") String id) {
        final DeferredResult<ResponseEntity<Person>> deferred = new DeferredResult<>();
        // No timeout here
        personService.findOne(id).singleOrDefault(null).subscribe(item -> {
            if (item == null) {
                deferred.setResult(new ResponseEntity<>(HttpStatus.NOT_FOUND));
            } else {
                deferred.setResult(ResponseEntity.ok(item));
            }
        }, deferred::setErrorResult);
        return deferred;
    }

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> findAll() {
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }

    /**
     * For e.g. http://localhost:8080/people?page=0&size=2
     */
    @RequestMapping(value = "/people", params = {"page", "size"}, method = RequestMethod.GET)
    public ResponseEntity<List<Person>> findPaginated(@RequestParam("page") long page, @RequestParam("size") long size) {
        List<Person> pagedList = personService.findAll()
                .stream().skip(page * size).limit(size).collect(Collectors.toCollection(ArrayList::new));
        return new ResponseEntity<>(pagedList, HttpStatus.OK);
    }

    @RequestMapping(value = "/people", method = RequestMethod.PUT)
    public ResponseEntity<Person> update(@RequestBody @Valid Person person) {
        return new ResponseEntity<>(personService.update(person), HttpStatus.OK);
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        personService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/people", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAll() {
        personService.deleteAll();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<ClientErrorInformation> handleUnsupportedOperation(HttpServletRequest req, Exception e) {
        ClientErrorInformation error = new ClientErrorInformation(e.toString(), req.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ClientErrorInformation> handleMethodArgumentNotValid(HttpServletRequest req, MethodArgumentNotValidException e) {
        String message = NOT_AVAILABLE;
        FieldError fieldError = e.getBindingResult().getFieldErrors().get(0);
        if (fieldError != null) {
            message = FIELD_ERROR_IN_OBJECT + fieldError.getObjectName() + ON_FIELD + fieldError.getField() + COLON +
                    fieldError.getDefaultMessage();
        }
        ClientErrorInformation error = new ClientErrorInformation(message, req.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

        
    /**
     * For e.g. http://localhost:8080/people?filter=...
     */
    @RequestMapping(value = "/people", params = {"filter"}, method = RequestMethod.GET)
    public ResponseEntity<List<Person>> findFiltered(@RequestParam("filter") String filter) {
        List<Person> filteredList = personService.findAll()
                .stream().filter(p -> p.getEmail().equals(filter)).collect(Collectors.toCollection(ArrayList::new));
        return new ResponseEntity<>(filteredList, HttpStatus.OK);
    }
    
}
