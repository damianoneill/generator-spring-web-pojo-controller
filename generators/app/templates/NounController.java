package <%= packageName %>.<%= nounLowercase %>;

import <%= packageName %>.ClientErrorInformation;
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
 * The <%= noun %> REST Controller.
 * This code is auto-generated do not override, instead raise a feature request against the generator tool.
 */
@RestController
public class <%= noun %>Controller {

    @Autowired
    private <%= noun %>Service <%= nounLowercase %>Service;

    @RequestMapping(value = "/<%= nounLowercasePlural %>", method = RequestMethod.POST)
    public ResponseEntity<<%= noun %>> create(@RequestBody <%= noun %> <%= nounLowercase %>) {
        return new ResponseEntity<>(<%= nounLowercase %>Service.create(<%= nounLowercase %>), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/<%= nounLowercasePlural %>/{id}", method = RequestMethod.GET)
    public ResponseEntity<<%= noun %>> findOne(@PathVariable("id") <%= type %> id) {
        <%= noun %> <%= nounLowercase %> = <%= nounLowercase %>Service.findOne(id);
        if (<%= nounLowercase %> == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(<%= nounLowercase %>, HttpStatus.OK);
    }

    @RequestMapping(value = "/<%= nounLowercasePlural %>", method = RequestMethod.GET)
    public ResponseEntity<List<<%= noun %>>> findAll() {
        return new ResponseEntity<>(<%= nounLowercase %>Service.findAll(), HttpStatus.OK);
    }

    /**
     * For e.g. http://localhost:8080/<%= nounLowercasePlural %>?page=0&size=2
     */
    @RequestMapping(value = "/<%= nounLowercasePlural %>", params = {"page", "size"}, method = RequestMethod.GET)
    public ResponseEntity<List<<%= noun %>>> findPaginated(@RequestParam("page") long page, @RequestParam("size") long size) {
        List<<%= noun %>> pagedList = <%= nounLowercase %>Service.findAll()
                .stream().skip(page * size).limit(size).collect(Collectors.toCollection(ArrayList::new));
        return new ResponseEntity<>(pagedList, HttpStatus.OK);
    }

    @RequestMapping(value = "/<%= nounLowercasePlural %>", method = RequestMethod.PUT)
    public ResponseEntity<<%= noun %>> update(@RequestBody <%= noun %> <%= nounLowercase %>) {
        return new ResponseEntity<>(<%= nounLowercase %>Service.update(<%= nounLowercase %>), HttpStatus.OK);
    }

    @RequestMapping(value = "/<%= nounLowercasePlural %>/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") <%= type %> id) {
        <%= nounLowercase %>Service.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/<%= nounLowercasePlural %>", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAll() {
        <%= nounLowercase %>Service.deleteAll();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<ClientErrorInformation> handleUnsupportedOperationException(HttpServletRequest req, Exception e) {
        ClientErrorInformation error = new ClientErrorInformation(e.toString(), req.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.NOT_IMPLEMENTED);
    }

    <%- include snippets/NounController-with-filter.ejs -%>

}
