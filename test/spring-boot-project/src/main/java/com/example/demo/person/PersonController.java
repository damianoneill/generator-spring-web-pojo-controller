package com.example.demo.person;

import com.example.demo.ClientErrorInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.ControllerHelper.toOkResponseEntity;
import static com.example.demo.ControllerHelper.toResponseEntity;

/**
 * The Person REST Controller.
 * This code is auto-generated do not override, instead raise a feature request against the generator tool.
 */
@RestController
class PersonController {

    private static final String NOT_AVAILABLE = "Not Available";
    private static final String FIELD_ERROR_IN_OBJECT = "Field error in object \'";
    private static final String ON_FIELD = "\' on field \'";
    private static final String COLON = "\': ";

    @Autowired

    private PersonService personService;


    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public DeferredResult<ResponseEntity<Person>> create(@RequestBody @Valid Person person) {
        DeferredResult<ResponseEntity<Person>> result = new DeferredResult<>();

        personService.create(person).subscribe(
                he -> result.setResult(toResponseEntity(he, HttpStatus.ACCEPTED)), result::setErrorResult);

        return result;
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public DeferredResult<ResponseEntity<Person>> findOne(@PathVariable("id") String id) {
        final DeferredResult<ResponseEntity<Person>> result = new DeferredResult<>();

        personService.findOne(id).subscribe(
                he -> result.setResult(toResponseEntity(he, HttpStatus.NOT_FOUND)),
                result::setErrorResult);

        return result;
    }

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public DeferredResult<ResponseEntity<List<Person>>> findAll() {
        final DeferredResult<ResponseEntity<List<Person>>> result = new DeferredResult<>();
        personService.findAll().subscribe(
                he -> result.setResult(
                        toOkResponseEntity(he.getBody(), he.getHeaders())),
                result::setErrorResult);
        return result;
    }

    /**
     * For e.g. http://localhost:8080/people?page=0&size=2
     */
    @RequestMapping(value = "/people", params = {"page", "size"}, method = RequestMethod.GET)
    public DeferredResult<ResponseEntity<List<Person>>> findPaginated(@RequestParam("page") long page, @RequestParam("size") long size) {
        final DeferredResult<ResponseEntity<List<Person>>> result = new DeferredResult<>();
        personService.findAll().subscribe(he -> {
            final List<Person> pagedList = he.getBody().stream().skip(page * size).limit(size)
                    .collect(Collectors.toCollection(ArrayList::new));
            result.setResult(toOkResponseEntity(pagedList, he.getHeaders()));
        }, result::setErrorResult);
        return result;
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.PUT)
    public DeferredResult<ResponseEntity<Person>> update(@PathVariable("id") String id, @RequestBody @Valid Person person) {
        DeferredResult<ResponseEntity<Person>> result = new DeferredResult<>();

        personService.update(id, person).subscribe(
                he -> result.setResult(toResponseEntity(he, HttpStatus.ACCEPTED)), result::setErrorResult);
        return result;

    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
    public DeferredResult<ResponseEntity<Person>> delete(@PathVariable("id") String id) {
        DeferredResult<ResponseEntity<Person>> result = new DeferredResult<>();
        personService.delete(id).subscribe(
                he -> result.setResult(toResponseEntity(he, HttpStatus.ACCEPTED)), result::setErrorResult);
        return result;
    }

    @RequestMapping(value = "/people", method = RequestMethod.DELETE)
    public DeferredResult<ResponseEntity<List<Person>>> deleteAll() {
        final DeferredResult<ResponseEntity<List<Person>>> result = new DeferredResult<>();
        personService.deleteAll().subscribe(
                he -> result.setResult(toResponseEntity(he, HttpStatus.ACCEPTED)), result::setErrorResult);
        return result;
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
    public DeferredResult<ResponseEntity<List<Person>>> findFiltered(@RequestParam("filter") String filter) {

        final DeferredResult<ResponseEntity<List<Person>>> result = new DeferredResult<>();
        personService.findAll().subscribe(item -> {
            final List<Person> pagedList = item.getBody().stream().filter(
                    p -> p.getEmail().equals(filter)).collect(Collectors.toCollection(ArrayList::new));
            result.setResult(toOkResponseEntity(pagedList, item.getHeaders()));
        }, result::setErrorResult);
        return result;
    }
    
}
