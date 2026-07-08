package org.simpleframework.xml.core;

import java.util.List;

class SignatureCreator implements Creator {
    private final List<Parameter> list;
    private final Signature signature;
    private final Class type;
    public SignatureCreator(final Signature signature) {
        type = signature.getType();
        list = signature.getAll();
        this.signature = signature;
    }
    public Class getType() {
        return type;
    }
    public Signature getSignature() {
        return signature;
    }
    public Object getInstance() throws Exception {
        return signature.create();
    }
    public Object getInstance(final Criteria criteria) throws Exception {
        final Object[] array = list.toArray();
        for (int i2 = 0; i2 < list.size(); i2++) {
            array[i2] = this.getVariable(criteria, i2);
        }
        return signature.create(array);
    }
    private Object getVariable(final Criteria criteria, final int i2) throws Exception {
        final Variable remove = criteria.remove(list.get(i2).getKey());
        if (null != remove) {
            return remove.getValue();
        }
        return null;
    }
    public double getScore(final Criteria criteria) throws Exception {
        final Signature copy = signature.copy();
        for (final Object obj : criteria) {
            final Parameter parameter = copy.get(obj);
            final Variable variable = criteria.get(obj);
            final Contact contact = variable.getContact();
            if (null != parameter && !Support.isAssignable(variable.getValue().getClass(), parameter.getType())) {
                return -1.0d;
            }
            if (contact.isReadOnly() && null == parameter) {
                return -1.0d;
            }
        }
        return this.getPercentage(criteria);
    }
    private double getPercentage(final Criteria criteria) throws Exception {
        double d2 = 0.0d;
        for (final Parameter parameter : list) {
            if (null != criteria.get(parameter.getKey())) {
                d2 += 1.0d;
            } else if (parameter.isRequired() || parameter.isPrimitive()) {
                return -1.0d;
            }
        }
        return this.getAdjustment(d2);
    }
    private double getAdjustment(final double d2) {
        final double size = list.size() / 1000.0d;
        if (0.0d < d2) {
            return size + (d2 / list.size());
        }
        return d2 / list.size();
    }
    public String toString() {
        return signature.toString();
    }
}
