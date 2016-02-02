package <%= packageName %>.<%= nounLowercase %>;

import <%= packageName %>.ClientErrorInformation;
import <%= packageName %>.CrudController;
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
public class <%= noun %>Controller implements CrudController<<%= noun %>, <%= type %>> {

    @Autowired
    private <%= noun %>Service <%= nounLowercase %>Service;

    @Override
    @RequestMapping(value = "/<%= nounLowercasePlural %>", method = RequestMethod.POST)
    public <%= noun %> create(@RequestBody <%= noun %> <%= nounLowercase %>) {
        return <%= nounLowercase %>Service.create(<%= nounLowercase %>);
    }

    @Override
    @RequestMapping(value = "/<%= nounLowercasePlural %>/{id}", method = RequestMethod.GET)
    public ResponseEntity<<%= noun %>> findOne(@PathVariable("id") <%= type %> id) {
        <%= noun %> <%= nounLowercase %> = <%= nounLowercase %>Service.findOne(id);
        if (<%= nounLowercase %> == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(<%= nounLowercase %>, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/<%= nounLowercasePlural %>", method = RequestMethod.GET)
    public List<<%= noun %>> findAll() {
        return <%= nounLowercase %>Service.findAll();
    }

    /**
     * For e.g. http://localhost:8080/<%= nounLowercasePlural %>?page=0&size=2
     */
    @Override
    @RequestMapping(value = "/<%= nounLowercasePlural %>", params = {"page", "size"}, method = RequestMethod.GET)
    public List<<%= noun %>> findPaginated(@RequestParam("page") long page, @RequestParam("size") long size) {
        return <%= nounLowercase %>Service.findAll()
                .stream().skip(page * size).limit(size).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    @RequestMapping(value = "/<%= nounLowercasePlural %>", method = RequestMethod.PUT)
    public <%= noun %> update(@RequestBody <%= noun %> <%= nounLowercase %>) {
        return <%= nounLowercase %>Service.update(<%= nounLowercase %>);
    }

    @Override
    @RequestMapping(value = "/<%= nounLowercasePlural %>/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") <%= type %> id) {
        <%= nounLowercase %>Service.delete(id);
    }

    @Override
    @RequestMapping(value = "/<%= nounLowercasePlural %>", method = RequestMethod.DELETE)
    public void deleteAll() {
        <%= nounLowercase %>Service.deleteAll();
    }

    <%- include snippets/NounController-with-filter.ejs -%>


    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<ClientErrorInformation> handleUnsupportedOperationException(HttpServletRequest req, Exception e) {
        ClientErrorInformation error = new ClientErrorInformation(e.toString(), req.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.NOT_IMPLEMENTED);
    }
}
