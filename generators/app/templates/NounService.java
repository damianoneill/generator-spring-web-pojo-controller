package <%= packageName %>.<%= nounLowercase %>;


import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The <%= noun %> service class, this should be implemented by the user of the tool.
 */
@Service
public class <%= noun %>Service {

    /**
     * Create an <%= noun %>.
     *
     * @param <S>    the type parameter
     * @param <%= nounLowercase %> the <%= nounLowercase %>
     * @return the s
     */
    public <S extends <%= noun %>> S create(S <%= nounLowercase %>) {
        throw new UnsupportedOperationException();
    }

    /**
     * Find one <%= noun %>.
     *
     * @param id the id
     * @return the <%= nounLowercase %>
     */
    public <%= noun %> findOne(<%= type %> id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Find all <%= nounLowercasePlural %>.
     *
     * @return the list
     */
    public List<<%= noun %>> findAll() {
        throw new UnsupportedOperationException();
    }

    /**
     * Update an <%= noun %>.
     *
     * @param <S>    the type parameter
     * @param <%= nounLowercase %> the <%= nounLowercase %>
     * @return the s
     */
    public <S extends <%= noun %>> S update(S <%= nounLowercase %>) {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete an <%= noun %>.
     *
     * @param id the id
     */
    public void delete(<%= type %> id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete all <%= nounLowercasePlural %>.
     */
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }
}
