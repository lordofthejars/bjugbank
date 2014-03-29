package com.lordofthejars.bank.util;


import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;

/**
 * @version $Revision$ $Date$
 */
public class Generics {
    public static Type[] getTypeParameters(Class desiredType, Type type) {
        if (type instanceof Class) {
            Class rawClass = (Class) type;

            // if this is the collection class we're done
            if (desiredType.equals(type)) {
                return null;
            }

            for (Type intf : rawClass.getGenericInterfaces()) {
                Type[] collectionType = getTypeParameters(desiredType, intf);
                if (collectionType != null) {
                    return collectionType;
                }
            }

            Type[] collectionType = getTypeParameters(desiredType, rawClass.getGenericSuperclass());
            return collectionType;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;

            Type rawType = parameterizedType.getRawType();
            if (desiredType.equals(rawType)) {
                Type[] argument = parameterizedType.getActualTypeArguments();
                return argument;
            }
            Type[] collectionTypes = getTypeParameters(desiredType,rawType);
            if (collectionTypes != null) {
                for (int i = 0; i < collectionTypes.length; i++) {
                    if (collectionTypes[i] instanceof TypeVariable) {
                        TypeVariable typeVariable = (TypeVariable) collectionTypes[i];
                        TypeVariable[] rawTypeParams = ((Class) rawType).getTypeParameters();
                        for (int j = 0; j < rawTypeParams.length; j++) {
                            if (typeVariable.getName().equals(rawTypeParams[j].getName())) {
                                collectionTypes[i] = parameterizedType.getActualTypeArguments()[j];
                            }
                        }
                    }
                }
            }
            return collectionTypes;
        }
        return null;
    }

    public static Class getCollectionType(Type type) {
        Type[] types = getTypeParameters(Collection.class, type);

        Type componentType = String.class;
        if (types != null && types.length == 1 && types[0] instanceof Class) {
            componentType = types[0];
        }

        return toClass(componentType);
    }

    public static Class toClass(Type type) {
        // GenericArrayType, ParameterizedType, TypeVariable<D>, WildcardType
        if (type instanceof Class) {
            Class clazz = (Class) type;
            return clazz;
        } else if (type instanceof GenericArrayType) {
            GenericArrayType arrayType = (GenericArrayType) type;
            Class componentType = toClass(arrayType.getGenericComponentType());
            return Array.newInstance(componentType, 0).getClass();
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return toClass(parameterizedType.getRawType());
        } else {
            return Object.class;
        }
    }
}
