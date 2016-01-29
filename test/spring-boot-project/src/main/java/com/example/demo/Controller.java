package com.example.demo;

import java.io.Serializable;

/**
 * Central controller marker interface. Captures the noun type to manage as well as the noun type's id type.
 * <p>
 * Modelled after spring data commons, Repository hierarchy.
 *
 * This code is auto-generated do not override, instead raise a feature request against the generator tool.
 *
 * @param <T>  the noun the controller manages
 * @param <ID> the type of the id of the noun the controller manages
 * @see CrudController
 */
public interface Controller<T, ID extends Serializable> {

}

