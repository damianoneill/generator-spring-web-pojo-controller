package <%= packageName %>.<%= nounLowercase %>;


import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.List;

/**
 * The <%= noun %> service class, this should be implemented by the user of the tool.
 */
@Service
class <%= noun %>Service {

    /**
     * Create an <%= noun %>.
     *
     * @param <%= nounLowercase %> the <%= nounLowercase %> to create
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<<%= noun %>>> create(<%= noun %> <%= nounLowercase %>) {
        // Return entity with a body if request has been fully handled.
        throw new UnsupportedOperationException();
    }

    /**
     * Find one <%= noun %>.
     *
     * @param id the id
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<<%= noun %>>> findOne(<%= type %> id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Find all <%= nounLowercasePlural %>.
     *
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<List<<%= noun %>>>> findAll() {
        throw new UnsupportedOperationException();
    }

    /**
     * Update an <%= noun %>.
     *
     * @param id the id
     * @param <%= nounLowercase %> the <%= nounLowercase %>
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<<%= noun %>>> update(<%= type %> id, <%= noun %> <%= nounLowercase %>) {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete an <%= noun %>.
     *
     * @param id the id
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<<%= noun %>>> delete(<%= type %> id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Delete all <%= nounLowercasePlural %>.
     *
     * @return an observable that emits a non-null HttpEntity.
     */
    public Observable<HttpEntity<List<<%= noun %>>>> deleteAll() {
        throw new UnsupportedOperationException();
    }
}
