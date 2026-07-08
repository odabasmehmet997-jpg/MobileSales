package org.simpleframework.xml.core;

import org.simpleframework.xml.Order;
import org.simpleframework.xml.Version;

import java.util.List;

interface Scanner extends Policy {
    Caller getCaller(Context context);
    Function getCommit();
    Function getComplete();
    Decorator getDecorator();
    Instantiator getInstantiator();
    String getName();
    Order getOrder();
    ParameterMap getParameters();
    Function getPersist();
    Function getReplace();
    Function getResolve();
    Version getRevision();
    Section getSection();
    Signature getSignature();
    List<Signature> getSignatures();
    Label getText();
    Class getType();
    Function getValidate();
    Label getVersion();
    boolean isEmpty();
    boolean isPrimitive();
    boolean isStrict();
}
