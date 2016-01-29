package <%= packageName %>;

import org.springframework.http.ResponseEntity;

import java.io.Serializable;

/**
 * Interface for generic CRUD operations on a controller for a specific noun.
 * This code is auto-generated do not override, instead raise a feature request against the generator tool.
 *
 * @author Damian ONeill
 */
public interface CrudController<T, ID extends Serializable> extends Controller<T, ID> {

  /**
   * Saves a given noun. Use the returned instance for further operations as the save operation might have changed the
   * noun instance completely.
   *
   * @param noun
   * @return the saved noun
   */
  <S extends T> S save(S noun);

  /**
   * Saves all given nouns.
   *
   * @param nouns
   * @return the saved nouns
   * @throws IllegalArgumentException in case the given noun is {@literal null}.
   */
  <S extends T> Iterable<S> save(Iterable<S> nouns);

  /**
   * Retrieves an noun by its id.
   *
   * @param id must not be {@literal null}.
   * @return the noun with the given id or {@literal null} if none found
   * @throws IllegalArgumentException if {@code id} is {@literal null}
   */
  ResponseEntity<T> findOne(ID id);

  /**
   * Returns whether an noun with the given id exists.
   *
   * @param id must not be {@literal null}.
   * @return true if an noun with the given id exists, {@literal false} otherwise
   * @throws IllegalArgumentException if {@code id} is {@literal null}
   */
  boolean exists(ID id);

  /**
   * Returns all instances of the type.
   *
   * @return all nouns
   */
  Iterable<T> findAll();

  /**
   * Returns all instances of the type with the given IDs.
   *
   * @param ids
   * @return
   */
  Iterable<T> findAll(Iterable<ID> ids);

  /**
   * Returns the number of nouns available.
   *
   * @return the number of nouns
   */
  long count();

  /**
   * Deletes the noun with the given id.
   *
   * @param id must not be {@literal null}.
   * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
   */
  void delete(ID id);

  /**
   * Deletes a given noun.
   *
   * @param noun
   * @throws IllegalArgumentException in case the given noun is {@literal null}.
   */
  void delete(T noun);

  /**
   * Deletes the given nouns.
   *
   * @param nouns
   * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
   */
  void delete(Iterable<? extends T> nouns);

  /**
   * Deletes all nouns managed by the repository.
   */
  void deleteAll();
}
