package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;

import java.lang.annotation.Annotation;


interface Contact extends Type {
    Object get(Object obj) throws Exception;
    Annotation getAnnotation();
    Class getDeclaringClass();
    Class getDependent();
    Class[] getDependents();
    String getName();
    boolean isReadOnly();
    void set(Object obj, Object obj2) throws Exception;
    String toString();
}
