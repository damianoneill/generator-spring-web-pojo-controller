package <%= packageName %>.<%= nounLowercase %>;


import org.springframework.stereotype.Service;


/**
 * The <%= noun %> service class, this should be implemented by the user of the tool.
 */
@Service
public class <%= noun %>Service {

    /**
     * Save s.
     *
     * @param <S>      the type parameter
     * @param <%= nounLowercase %> the <%= nounLowercase %>
     * @return the s
     */
    public <S extends <%= noun %>> S save(S <%= nounLowercase %>) {
        throw new UnsupportedOperationException();
    }

    /**
     * Save iterable.
     *
     * @param <S>       the type parameter
     * @param <%= nounLowercasePlural %> the <%= nounLowercasePlural %>
     * @return the iterable
     */
    public <S extends <%= noun %>> Iterable<S> save(Iterable<S> <%= nounLowercasePlural %>) {
        throw new UnsupportedOperationException();
    }

    /**
     * Find one <%= nounLowercase %>.
     *
     * @param id the id
     * @return the <%= nounLowercase %>
     */
    public <%= noun %> findOne(Integer id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Exists boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean exists(Integer id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Find all iterable.
     *
     * @return the iterable
     */
    public Iterable<<%= noun %>> findAll() {
        throw new UnsupportedOperationException();
    }

    /**
     * Find all iterable.
     *
     * @param ids the ids
     * @return the iterable
     */
    public Iterable<<%= noun %>> findAll(Iterable<Integer> ids) {
        throw new UnsupportedOperationException();
    }

    /**
     * Count long.
     *
     * @return the long
     */
    public long count() {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete.
     *
     * @param <%= nounLowercase %> the <%= nounLowercase %>
     */
    public void delete(<%= noun %> <%= nounLowercase %>) {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete.
     *
     * @param <%= nounLowercasePlural %> the <%= nounLowercasePlural %>
     */
    public void delete(Iterable<? extends <%= noun %>> <%= nounLowercasePlural %>) {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete all.
     */
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }
}
