package <%= packageName %>;

import java.io.Serializable;

/**
 * Central controller marker interface. Captures the noun type to manage as well as the noun type's id type.
 * <p>
 * Modelled after spring data commons, Repository hierarchy.
 *
 * @param <T>  the noun the controller manages
 * @param <ID> the type of the id of the noun the controller manages
 * @author Damian ONeill
 * @see CrudController
 */
public interface Controller<T, ID extends Serializable> {

}

