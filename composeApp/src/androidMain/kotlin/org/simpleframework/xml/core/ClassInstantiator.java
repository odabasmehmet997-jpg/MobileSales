package org.simpleframework.xml.core;

import java.util.ArrayList;
import java.util.List;


class ClassInstantiator implements Instantiator {
    private final List<Creator> creators;
    private final Detail detail;
    private final Creator primary;
    private final ParameterMap registry;

    public ClassInstantiator(final List<Creator> list, final Creator creator, final ParameterMap parameterMap, final Detail detail) {
        creators = list;
        registry = parameterMap;
        primary = creator;
        this.detail = detail;
    }

    @Override // org.simpleframework.xml.core.Instantiator
    public boolean isDefault() {
        return 1 >= this.creators.size() && null != this.primary;
    }

    @Override // org.simpleframework.xml.core.Instantiator
    public Object getInstance() throws Exception {
        return primary.getInstance();
    }

    @Override // org.simpleframework.xml.core.Instantiator
    public Object getInstance(final Criteria criteria) throws Exception {
        final Creator creator = this.getCreator(criteria);
        if (null == creator) {
            throw new PersistenceException("Constructor not matched for %s", detail);
        }
        return creator.getInstance(criteria);
    }

    private Creator getCreator(final Criteria criteria) throws Exception {
        Creator creator = primary;
        double d2 = 0.0d;
        for (final Creator creator2 : creators) {
            final double score = creator2.getScore(criteria);
            if (score > d2) {
                creator = creator2;
                d2 = score;
            }
        }
        return creator;
    }

    @Override // org.simpleframework.xml.core.Instantiator
    public Parameter getParameter(final String str) {
        return registry.get(str);
    }

    @Override // org.simpleframework.xml.core.Instantiator
    public List<Parameter> getParameters() {
        return registry.getAll();
    }

    @Override // org.simpleframework.xml.core.Instantiator
    public List<Creator> getCreators() {
        return new ArrayList(creators);
    }

    public String toString() {
        return String.format("creator for %s", detail);
    }
}
