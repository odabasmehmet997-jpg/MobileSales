package org.simpleframework.xml.core;

import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;


class DetailExtractor {
    private final Cache<Detail> details;
    private final Cache<ContactList> fields;
    private final Cache<ContactList> methods;
    private final DefaultType override;
    private final Support support;

    public DetailExtractor(final Support support) {
        this(support, null);
    }

    public DetailExtractor(final Support support, final DefaultType defaultType) {
        methods = new ConcurrentCache();
        fields = new ConcurrentCache();
        details = new ConcurrentCache();
        override = defaultType;
        this.support = support;
    }

    public Detail getDetail(final Class cls) {
        final Detail fetch = details.fetch(cls);
        if (null != fetch) {
            return fetch;
        }
        final DetailScanner detailScanner = new DetailScanner(cls, override);
        details.cache(cls, detailScanner);
        return detailScanner;
    }

    public ContactList getFields(final Class cls) throws Exception {
        final Detail detail;
        final ContactList fetch = fields.fetch(cls);
        return (null != fetch || null == (detail = getDetail(cls))) ? fetch : this.getFields(cls, detail);
    }

    private ContactList getFields(final Class cls, final Detail detail) throws Exception {
        final FieldScanner fieldScanner = new FieldScanner(detail, support);
        if (null != detail) {
            fields.cache(cls, fieldScanner);
        }
        return fieldScanner;
    }

    public ContactList getMethods(final Class cls) throws Exception {
        final Detail detail;
        final ContactList fetch = methods.fetch(cls);
        return (null != fetch || null == (detail = getDetail(cls))) ? fetch : this.getMethods(cls, detail);
    }

    private ContactList getMethods(final Class cls, final Detail detail) throws Exception {
        final MethodScanner methodScanner = new MethodScanner(detail, support);
        if (null != detail) {
            methods.cache(cls, methodScanner);
        }
        return methodScanner;
    }
}
