package org.simpleframework.xml.core;

import org.simpleframework.xml.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


class ClassScanner {
    private Function commit;
    private Function complete;
    private final NamespaceDecorator decorator = new NamespaceDecorator();
    private Order order;
    private Function persist;
    private Function replace;
    private Function resolve;
    private Root root;
    private final ConstructorScanner scanner;
    private final Support support;
    private Function validate;

    public ClassScanner(final Detail detail, final Support support) throws Exception {
        scanner = new ConstructorScanner(detail, support);
        this.support = support;
        this.scan(detail);
    }

    public Signature getSignature() {
        return scanner.getSignature();
    }

    public List<Signature> getSignatures() {
        return scanner.getSignatures();
    }

    public ParameterMap getParameters() {
        return scanner.getParameters();
    }

    public Decorator getDecorator() {
        return decorator;
    }

    public Order getOrder() {
        return order;
    }

    public Root getRoot() {
        return root;
    }

    public Function getCommit() {
        return commit;
    }

    public Function getValidate() {
        return validate;
    }

    public Function getPersist() {
        return persist;
    }

    public Function getComplete() {
        return complete;
    }

    public Function getReplace() {
        return replace;
    }

    public Function getResolve() {
        return resolve;
    }

    private void scan(final Detail detail) throws Exception {
        final DefaultType override = detail.getOverride();
        Class type = detail.getType();
        while (null != type) {
            final Detail detail2 = support.getDetail(type, override);
            this.namespace(detail2);
            this.method(detail2);
            this.definition(detail2);
            type = detail2.getSuper();
        }
        this.commit(detail);
    }

    private void definition(final Detail detail) throws Exception {
        if (null == this.root) {
            root = detail.getRoot();
        }
        if (null == this.order) {
            order = detail.getOrder();
        }
    }

    private void namespace(final Detail detail) throws Exception {
        final NamespaceList namespaceList = detail.getNamespaceList();
        final Namespace namespace = detail.getNamespace();
        if (null != namespace) {
            decorator.add(namespace);
        }
        if (null != namespaceList) {
            for (final Namespace namespace2 : namespaceList.value()) {
                decorator.add(namespace2);
            }
        }
    }

    private void commit(final Detail detail) {
        final Namespace namespace = detail.getNamespace();
        if (null != namespace) {
            decorator.set(namespace);
        }
    }

    private void method(final Detail detail) throws Exception {
        final Iterator<MethodDetail> it = detail.getMethods().iterator();
        while (it.hasNext()) {
            this.method(it.next());
        }
    }

    private void method(final MethodDetail methodDetail) {
        final Annotation[] annotations = methodDetail.getAnnotations();
        final Method method = methodDetail.getMethod();
        for (final Annotation annotation : annotations) {
            if (annotation instanceof Commit) {
                this.commit(method);
            }
            if (annotation instanceof Validate) {
                this.validate(method);
            }
            if (annotation instanceof Persist) {
                this.persist(method);
            }
            if (annotation instanceof Complete) {
                this.complete(method);
            }
            if (annotation instanceof Replace) {
                this.replace(method);
            }
            if (annotation instanceof Resolve) {
                this.resolve(method);
            }
        }
    }

    private void replace(final Method method) {
        if (null == this.replace) {
            replace = this.getFunction(method);
        }
    }

    private void resolve(final Method method) {
        if (null == this.resolve) {
            resolve = this.getFunction(method);
        }
    }

    private void commit(final Method method) {
        if (null == this.commit) {
            commit = this.getFunction(method);
        }
    }

    private void validate(final Method method) {
        if (null == this.validate) {
            validate = this.getFunction(method);
        }
    }

    private void persist(final Method method) {
        if (null == this.persist) {
            persist = this.getFunction(method);
        }
    }

    private void complete(final Method method) {
        if (null == this.complete) {
            complete = this.getFunction(method);
        }
    }

    private Function getFunction(final Method method) {
        final boolean isContextual = this.isContextual(method);
        if (!method.isAccessible()) {
            method.setAccessible(true);
        }
        return new Function(method, isContextual);
    }

    private boolean isContextual(final Method method) {
        final Class<?>[] parameterTypes = method.getParameterTypes();
        if (1 == parameterTypes.length) {
            return Map.class.equals(parameterTypes[0]);
        }
        return false;
    }
}
