package <%= packageName %>;

import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Interface for generic CRUD operations on a controller for a specific noun.
 * This code is auto-generated do not override, instead raise a feature request against the generator tool.
 *
 */
public interface CrudController<T, ID extends Serializable> {

  /**
   * Creates a given noun. Use the returned instance for further operations as the create operation might have
   * changed the noun instance completely.
   *
   * @param noun
   * @return the created noun
   */
  T create(T noun);

  /**
   * Retrieves an noun by its id.
   *
   * @param id must not be {@literal null}.
   * @return the noun with the given id or {@literal null} if none found
   */
  ResponseEntity<T> findOne(ID id);

  /**
   * Returns all instances of the type.
   *
   * @return all nouns
   */
  List<T> findAll();


  /**
   * Returns a paginated list of the instance type.
   *
   * @param page the page
   * @param size the size
   * @return the list
   */
  List<T> findPaginated(long page, long size);

  /**
   * Updates a given noun. Use the returned instance for further operations as the update operation might have
   * changed the noun instance completely.
   *
   * @param noun
   * @return the updated noun
   */
  T update(T noun);

  /**
   * Deletes the noun with the given id.
   *
   * @param id must not be {@literal null}.
   * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
   */
  void delete(ID id);

  /**
   * Deletes all nouns managed by the controller.
   */
  void deleteAll();
}
