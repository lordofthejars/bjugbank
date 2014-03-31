package com.lordofthejars.bank.util.persistence;

import java.lang.reflect.Method;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.ValidationException;

import com.lordofthejars.bank.util.Generics;
import com.lordofthejars.bank.util.Parameter;
import com.lordofthejars.bank.util.Reflection;


public class PersistenceHandler {

    public static Object invoke(EntityManager em, Method method, Object[] args) throws Throwable {

        if (method.isAnnotationPresent(NamedQuery.class)) {
            return invokeNamedQuery(em, method, args);
        }

        if (method.isAnnotationPresent(Find.class)) {
            return findByPrimaryKey(em, method, args);
        }

        if (method.isAnnotationPresent(Merge.class)) {
            return merge(em, method, args);
        }

        if (method.isAnnotationPresent(Remove.class)) {
            return remove(em, method, args);
        }

        if (method.isAnnotationPresent(Persist.class)) {
            return persist(em, method, args);
        }

        throw new AbstractMethodError("No handler logic for method: " + method.toString());
    }

    /**
     * CREATE
     *
     * Persist the specified entity
     *
     * @param em
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public static Object persist(EntityManager em, Method method, Object[] args) throws Throwable {
        final Iterable<Parameter> params = Reflection.params(method, args);
        final Parameter parameter = params.iterator().next();

        if (parameter.getValue() == null)
            throw new ValidationException(parameter.getType().getSimpleName() + " object is null");

        em.persist(parameter.getValue());

        return parameter.getValue();
    }

    /**
     * READ:
     *
     * Find an entity by primary key
     *
     * @param em
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public static Object findByPrimaryKey(EntityManager em, Method method, Object[] args) throws Throwable {
        final Class<?> entityClass = method.getReturnType();
        final Object primaryKey = args[0];

        if (primaryKey == null) {
            throw new ValidationException("Invalid id");
        }
        return em.find(entityClass, primaryKey);
    }


    /**
     * READ:
     *
     * Execute a NamedQuery
     *
     * @param em
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public static Object invokeNamedQuery(EntityManager em, Method method, Object[] args) throws Throwable {
        final NamedQuery namedQuery = method.getAnnotation(NamedQuery.class);

        final TypedQuery<?> typedQuery = em.createNamedQuery(namedQuery.value(), getEntityType(method));

        for (Parameter parameter : Reflection.params(method, args)) {
            final QueryParam queryParam = parameter.getAnnotation(QueryParam.class);

            if (parameter.getValue() == null) {
                throw new ValidationException(queryParam.value() + " is null");
            }

            typedQuery.setParameter(queryParam.value(), parameter.getValue());
        }

        return (isList(method))? typedQuery.getResultList() : typedQuery.getSingleResult();
    }

    /**
     * UPDATE
     *
     * Perform a merge on the passed in entity
     *
     * @param em
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public static Object merge(EntityManager em, Method method, Object[] args) throws Throwable {
        final Iterable<Parameter> params = Reflection.params(method, args);
        final Parameter parameter = params.iterator().next();

        if (parameter.getValue() == null)
            throw new ValidationException(parameter.getType().getSimpleName() + " object is null");

        return em.merge(parameter.getValue());
    }

    /**
     * DELETE
     *
     * Remove the specified entity
     *
     * @param em
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public static Object remove(EntityManager em, Method method, Object[] args) throws Throwable {
        final Iterable<Parameter> params = Reflection.params(method, args);
        final Parameter parameter = params.iterator().next();

        if (parameter.getValue() == null)
            throw new ValidationException(parameter.getType().getSimpleName() + " object is null");

        em.remove(em.merge(parameter.getValue()));

        return null;
    }

    /**
     * Is the return value a list?
     * @param method
     * @return
     */
    private static boolean isList(Method method) {
        return Collection.class.isAssignableFrom(method.getReturnType());
    }

    /**
     * Determine the type of entity being returned
     * @param method
     * @return
     */
    private static Class<?> getEntityType(Method method) {
        if (isList(method)) {
            return Generics.getCollectionType(method.getGenericReturnType());
        } else {
            return method.getReturnType();
        }
    }

}