package com.lordofthejars.bank.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Reflection {

    public static Iterable<Parameter> params(final Method method, final Object[] values) {
        return new Iterable<Parameter>() {
            @Override
            public Iterator<Parameter> iterator() {
                return new Iterator<Parameter>() {
                    private int index = 0;

                    @Override
                    public boolean hasNext() {
                        return index < method.getParameterTypes().length;
                    }

                    @Override
                    public Parameter next() {
                        if (!hasNext()) throw new NoSuchElementException();
                        return new Parameter(method.getParameterAnnotations()[index], method.getParameterTypes()[index], values[index++]);
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    public static Iterable<Parameter> params(final Constructor constructor, final Object[] values) {
        return new Iterable<Parameter>() {
            @Override
            public Iterator<Parameter> iterator() {
                return new Iterator<Parameter>() {
                    private int index = 0;

                    @Override
                    public boolean hasNext() {
                        return index < constructor.getParameterTypes().length;
                    }

                    @Override
                    public Parameter next() {
                        if (!hasNext()) throw new NoSuchElementException();
                        return new Parameter(constructor.getParameterAnnotations()[index], constructor.getParameterTypes()[index], values[index++]);
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}
