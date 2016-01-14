package <%= packageName %>;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The <%= noun %> REST Controller.
 */
@RestController
@RequestMapping("/<%= nounLowercasePlural %>")
public abstract class Abstract<%= noun %>Controller implements CrudController<<%= noun %>, <%= type %>> {


    @Override
    @RequestMapping(method = RequestMethod.POST)
    public <S extends <%= noun %>> S _save(S <%= nounLowercase %>) {
        return save(<%= nounLowercase %>);
    }
    public abstract <S extends <%= noun %>> S save(S <%= nounLowercase %>);

    @Override
    public <S extends <%= noun %>> Iterable<S> _save(Iterable<S> <%= nounLowercasePlural %>) {
        return save(<%= nounLowercasePlural %>);
    }
    public abstract <S extends <%= noun %>> Iterable<S> save(Iterable<S> <%= nounLowercasePlural %>);

    @Override
    public <%= noun %> _findOne(<%= type %> id) {
        return findOne(id);
    }
    public abstract <%= noun %> findOne(<%= type %> id);

    @Override
    public boolean _exists(<%= type %> id) {
        return exists(id);
    }
    public abstract boolean exists(<%= type %> id);

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<<%= noun %>> _findAll() {
        return findAll();
    }
    public abstract Iterable<<%= noun %>> findAll();

    @Override
    public Iterable<<%= noun %>> _findAll(Iterable<<%= type %>> ids) {
        return findAll(ids);
    }
    public abstract Iterable<<%= noun %>> findAll(Iterable<<%= type %>> ids);

    @Override
    public long _count() {
        return count();
    }
    public abstract long count();

    @Override
    public void _delete(<%= type %> id) {
        delete(id);
    }
    public abstract void delete(<%= type %> id);

    @Override
    public void _delete(<%= noun %> <%= nounLowercase %>) {
        delete(<%= nounLowercase %>);
    }
    public abstract void delete(<%= noun %> <%= nounLowercase %>);

    @Override
    public void _delete(Iterable<? extends <%= noun %>> <%= nounLowercasePlural %>) {
        delete(<%= nounLowercasePlural %>);
    }
    public abstract void delete(Iterable<? extends <%= noun %>> <%= nounLowercasePlural %>);

    @Override
    public void _deleteAll() {
        deleteAll();
    }
    public abstract void deleteAll();
}
