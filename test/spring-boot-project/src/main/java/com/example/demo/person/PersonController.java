package com.example.demo.person;

import com.example.demo.ClientErrorInformation;
import com.example.demo.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Person REST Controller.
 * This code is auto-generated do not override, instead raise a feature request against the generator tool.
 */
@RestController
public class PersonController implements CrudController<Person, String> {

    @Autowired
    private PersonService personService;

    @Override
    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public Person create(@RequestBody Person person) {
        return personService.create(person);
    }

    @Override
    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> findOne(@PathVariable("id") String id) {
        Person person = personService.findOne(id);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public List<Person> findAll() {
        return personService.findAll();
    }

    /**
     * For e.g. http://localhost:8080/people?page=0&size=2
     */
    @Override
    @RequestMapping(value = "/people", params = {"page", "size"}, method = RequestMethod.GET)
    public List<Person> findPaginated(@RequestParam("page") long page, @RequestParam("size") long size) {
        return personService.findAll()
                .stream().skip(page * size).limit(size).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    @RequestMapping(value = "/people", method = RequestMethod.PUT)
    public Person update(@RequestBody Person person) {
        return personService.update(person);
    }

    @Override
    @RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        personService.delete(id);
    }

    @Override
    @RequestMapping(value = "/people", method = RequestMethod.DELETE)
    public void deleteAll() {
        personService.deleteAll();
    }


    /**
     * For e.g. http://localhost:8080/people?filter=...
     */
    @RequestMapping(value = "/people", params = {"filter"}, method = RequestMethod.GET)
    public List<Person> findFiltered(@RequestParam("filter") String filter) {
        return personService.findAll()
                .stream().filter(p -> p.getEmail().equals(filter)).collect(Collectors.toCollection(ArrayList::new));
    }


    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<ClientErrorInformation> handleUnsupportedOperationException(HttpServletRequest req, Exception e) {
        ClientErrorInformation error = new ClientErrorInformation(e.toString(), req.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.NOT_IMPLEMENTED);
    }
}
