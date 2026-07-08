package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class InjectableValues {
    public abstract Object findInjectableValue(Object obj, DeserializationContext deserializationContext, BeanProperty beanProperty, Object obj2) throws JsonMappingException;
    public static class Std extends InjectableValues implements Serializable {
        private static final long serialVersionUID = 1;
        protected final Map<String, Object> _values;
        public Std() {
            this(new HashMap());
        }
        public Std(final Map<String, Object> map) {
            _values = map;
        }
        public Std addValue(final String str, final Object obj) {
            _values.put(str, obj);
            return this;
        }
        public Std addValue(final Class<?> cls, final Object obj) {
            _values.put(cls.getName(), obj);
            return this;
        }
        public Object findInjectableValue(final Object obj, final DeserializationContext deserializationContext, final BeanProperty beanProperty, final Object obj2) throws JsonMappingException {
            if (!(obj instanceof String)) {
                deserializationContext.reportBadDefinition(ClassUtil.classOf(obj), String.format("Unrecognized inject value id type (%s), expecting String", ClassUtil.classNameOf(obj)));
            }
            final String str = (String) obj;
            final Object obj3 = _values.get(str);
            if (null != obj3 || _values.containsKey(str)) {
                return obj3;
            }
            throw new IllegalArgumentException("No injectable id with value '" + str + "' found (for property '" + beanProperty.getName() + "')");
        }
    }
}
