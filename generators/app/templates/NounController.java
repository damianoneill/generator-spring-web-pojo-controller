package <%= packageName %>;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The <%= noun %> REST Controller.
 */
public abstract class Abstract<%= noun %>Controller implements CrudController<<%= noun %>, <%= type %>> {

    @Override
    @RequestMapping(value = "/<%= nounLowercasePlural %>", method = RequestMethod.POST)
    public <S extends <%= noun %>> S _save(S <%= nounLowercase %>) {
        return save(<%= nounLowercase %>);
    }

    @Override
    public <S extends <%= noun %>> Iterable<S> _save(Iterable<S> <%= nounLowercasePlural %>) {
        return save(<%= nounLowercasePlural %>);
    }

    @Override
    @RequestMapping(value = "/<%= nounLowercasePlural %>/{id}", method = RequestMethod.GET)
    public ResponseEntity<<%= noun %>> _findOne(@PathVariable("id") <%= type %> id) {
        <%= noun %> <%= nounLowercase %> = findOne(id);
        if (<%= nounLowercase %> == null) {
            return new ResponseEntity<<%= noun %>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<<%= noun %>>(<%= nounLowercase %>, HttpStatus.OK);
    }

    @Override
    public boolean _exists(<%= type %> id) {
        return exists(id);
    }

    @Override
    @RequestMapping(value = "/<%= nounLowercasePlural %>", method = RequestMethod.GET)
    public Iterable<<%= noun %>> _findAll() {
        return findAll();
    }

    @Override
    public Iterable<<%= noun %>> _findAll(Iterable<<%= type %>> ids) {
        return findAll(ids);
    }

    @Override
    public long _count() {
        return count();
    }

    @Override
    @RequestMapping(value = "/<%= nounLowercasePlural %>/{id}", method = RequestMethod.DELETE)
    public void _delete(@PathVariable("id") <%= type %> id) {
        delete(id);
    }

    @Override
    public void _delete(<%= noun %> <%= nounLowercase %>) {
        delete(<%= nounLowercase %>);
    }

    @Override
    public void _delete(Iterable<? extends <%= noun %>> <%= nounLowercasePlural %>) {
        delete(<%= nounLowercasePlural %>);
    }

    @Override
    public void _deleteAll() {
        deleteAll();
    }

    public abstract <S extends <%= noun %>> S save(S <%= nounLowercase %>);
    public abstract <S extends <%= noun %>> Iterable<S> save(Iterable<S> <%= nounLowercasePlural %>);
    public abstract <%= noun %> findOne(<%= type %> id);
    public abstract boolean exists(<%= type %> id);
    public abstract Iterable<<%= noun %>> findAll();
    public abstract Iterable<<%= noun %>> findAll(Iterable<<%= type %>> ids);
    public abstract long count();
    public abstract void delete(<%= type %> id);
    public abstract void delete(<%= noun %> <%= nounLowercase %>);
    public abstract void delete(Iterable<? extends <%= noun %>> <%= nounLowercasePlural %>);
    public abstract void deleteAll();
}
