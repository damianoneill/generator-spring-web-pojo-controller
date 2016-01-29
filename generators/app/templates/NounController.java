package <%= packageName %>.<%= nounLowercase %>;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import <%= packageName %>.CrudController;

/**
 * The <%= noun %> REST Controller.
 * This code is auto-generated do not override, instead raise a feature request against the generator tool.
 */
public class <%= noun %>Controller implements CrudController<<%= noun %>, <%= type %>> {

    @Autowired
    private <%= noun %>Service <%= nounLowercase %>Service;

    @Override
    @RequestMapping(value = "/<%= nounLowercasePlural %>", method = RequestMethod.POST)
    public <S extends <%= noun %>> S save(@RequestBody S <%= nounLowercase %>) {
        return <%= nounLowercase %>Service.save(<%= nounLowercase %>);
    }

    @Override
    public <S extends <%= noun %>> Iterable<S> save(Iterable<S> <%= nounLowercasePlural %>) {
        return <%= nounLowercase %>Service.save(<%= nounLowercasePlural %>);
    }

    @Override
    @RequestMapping(value = "/<%= nounLowercasePlural %>/{id}", method = RequestMethod.GET)
    public ResponseEntity<<%= noun %>> findOne(@PathVariable("id") <%= type %> id) {
        <%= noun %> <%= nounLowercase %> = <%= nounLowercase %>Service.findOne(id);
        if (<%= nounLowercase %> == null) {
            return new ResponseEntity<<%= noun %>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<<%= noun %>>(<%= nounLowercase %>, HttpStatus.OK);
    }

    @Override
    public boolean exists(<%= type %> id) {
        return <%= nounLowercase %>Service.exists(id);
    }

    @Override
    @RequestMapping(value = "/<%= nounLowercasePlural %>", method = RequestMethod.GET)
    public Iterable<<%= noun %>> findAll() {
        return <%= nounLowercase %>Service.findAll();
    }

    @Override
    public Iterable<<%= noun %>> findAll(Iterable<<%= type %>> ids) {
        return <%= nounLowercase %>Service.findAll(ids);
    }

    @Override
    public long count() {
        return <%= nounLowercase %>Service.count();
    }

    @Override
    @RequestMapping(value = "/<%= nounLowercasePlural %>/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") <%= type %> id) {
        <%= nounLowercase %>Service.delete(id);
    }

    @Override
    public void delete(<%= noun %> <%= nounLowercase %>) {
        <%= nounLowercase %>Service.delete(<%= nounLowercase %>);
    }

    @Override
    public void delete(Iterable<? extends <%= noun %>> <%= nounLowercasePlural %>) {
        <%= nounLowercase %>Service.delete(<%= nounLowercasePlural %>);
    }

    @Override
    public void deleteAll() {
        <%= nounLowercase %>Service.deleteAll();
    }
}
