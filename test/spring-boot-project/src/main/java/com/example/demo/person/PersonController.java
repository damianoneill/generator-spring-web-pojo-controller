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
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public <S extends Person> S save(@RequestBody S person) {
        return personService.save(person);
    }

    @Override
    public <S extends Person> Iterable<S> save(Iterable<S> people) {
        return personService.save(people);
    }

    @Override
    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> findOne(@PathVariable("id") String id) {
        Person person = personService.findOne(id);
        if (person == null) {
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    @Override
    public boolean exists(String id) {
        return personService.exists(id);
    }

    @Override
    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public Iterable<Person> findAll() {
        return personService.findAll();
    }

    @Override
    public Iterable<Person> findAll(Iterable<String> ids) {
        return personService.findAll(ids);
    }

    @Override
    public long count() {
        return personService.count();
    }

    @Override
    @RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        personService.delete(id);
    }

    @Override
    public void delete(Person person) {
        personService.delete(person);
    }

    @Override
    public void delete(Iterable<? extends Person> people) {
        personService.delete(people);
    }

    @Override
    @RequestMapping(value = "/people", method = RequestMethod.DELETE)
    public void deleteAll() {
        personService.deleteAll();
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<ClientErrorInformation> handleUnsupportedOperationException(HttpServletRequest req, Exception e) {
        ClientErrorInformation error = new ClientErrorInformation(e.toString(), req.getRequestURI());
        return new ResponseEntity<ClientErrorInformation>(error, HttpStatus.NOT_IMPLEMENTED);
    }
}
