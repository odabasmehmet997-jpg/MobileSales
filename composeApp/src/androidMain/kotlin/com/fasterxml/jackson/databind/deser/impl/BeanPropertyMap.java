package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.IgnorePropertiesUtil;
import com.fasterxml.jackson.databind.util.NameTransformer;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
public class BeanPropertyMap implements Iterable<SettableBeanProperty>, Serializable {
    private static final long serialVersionUID = 2;
    private final Map<String, List<PropertyName>> _aliasDefs;
    private final Map<String, String> _aliasMapping;
    protected final boolean _caseInsensitive;
    private Object[] _hashArea;
    private int _hashMask;
    private final Locale _locale;
    private final SettableBeanProperty[] _propsInOrder;
    private int _size;
    private int _spillCount;
    private static final int findSize(final int i2) {
        if (5 >= i2) {
            return 8;
        }
        if (12 >= i2) {
            return 16;
        }
        int i3 = 32;
        while (i3 < i2 + (i2 >> 2)) {
            i3 += i3;
        }
        return i3;
    }
    public BeanPropertyMap(final boolean z, final Collection<SettableBeanProperty> collection, final Map<String, List<PropertyName>> map, final Locale locale) {
        _caseInsensitive = z;
        _propsInOrder = collection.toArray(new SettableBeanProperty[collection.size()]);
        _aliasDefs = map;
        _locale = locale;
        _aliasMapping = this._buildAliasMapping(map, z, locale);
        this.init(collection);
    }
    public BeanPropertyMap(final boolean z, final Collection<SettableBeanProperty> collection, final Map<String, List<PropertyName>> map) {
        this(z, collection, map, Locale.getDefault());
    }
    private BeanPropertyMap(final BeanPropertyMap beanPropertyMap, final SettableBeanProperty settableBeanProperty, final int i2, final int i3) {
        _caseInsensitive = beanPropertyMap._caseInsensitive;
        _locale = beanPropertyMap._locale;
        _hashMask = beanPropertyMap._hashMask;
        _size = beanPropertyMap._size;
        _spillCount = beanPropertyMap._spillCount;
        _aliasDefs = beanPropertyMap._aliasDefs;
        _aliasMapping = beanPropertyMap._aliasMapping;
        final Object[] objArr = beanPropertyMap._hashArea;
        _hashArea = Arrays.copyOf(objArr, objArr.length);
        final SettableBeanProperty[] settableBeanPropertyArr = beanPropertyMap._propsInOrder;
        final SettableBeanProperty[] settableBeanPropertyArr2 = Arrays.copyOf(settableBeanPropertyArr, settableBeanPropertyArr.length);
        _propsInOrder = settableBeanPropertyArr2;
        _hashArea[i2] = settableBeanProperty;
        settableBeanPropertyArr2[i3] = settableBeanProperty;
    }
    private BeanPropertyMap(final BeanPropertyMap beanPropertyMap, final SettableBeanProperty settableBeanProperty, final String str, final int i2) {
        _caseInsensitive = beanPropertyMap._caseInsensitive;
        _locale = beanPropertyMap._locale;
        _hashMask = beanPropertyMap._hashMask;
        _size = beanPropertyMap._size;
        _spillCount = beanPropertyMap._spillCount;
        _aliasDefs = beanPropertyMap._aliasDefs;
        _aliasMapping = beanPropertyMap._aliasMapping;
        final Object[] objArr = beanPropertyMap._hashArea;
        _hashArea = Arrays.copyOf(objArr, objArr.length);
        final SettableBeanProperty[] settableBeanPropertyArr = beanPropertyMap._propsInOrder;
        final int length = settableBeanPropertyArr.length;
        final SettableBeanProperty[] settableBeanPropertyArr2 = Arrays.copyOf(settableBeanPropertyArr, length + 1);
        _propsInOrder = settableBeanPropertyArr2;
        settableBeanPropertyArr2[length] = settableBeanProperty;
        final int i3 = _hashMask + 1;
        int i4 = i2 << 1;
        final Object[] objArr2 = _hashArea;
        if (null != objArr2[i4]) {
            i4 = ((i2 >> 1) + i3) << 1;
            if (null != objArr2[i4]) {
                final int i5 = _spillCount;
                i4 = ((i3 + (i3 >> 1)) << 1) + i5;
                _spillCount = i5 + 2;
                if (i4 >= objArr2.length) {
                    _hashArea = Arrays.copyOf(objArr2, objArr2.length + 4);
                }
            }
        }
        final Object[] objArr3 = _hashArea;
        objArr3[i4] = str;
        objArr3[i4 + 1] = settableBeanProperty;
    }
    protected BeanPropertyMap(final BeanPropertyMap beanPropertyMap, final boolean z) {
        _caseInsensitive = z;
        _locale = beanPropertyMap._locale;
        _aliasDefs = beanPropertyMap._aliasDefs;
        _aliasMapping = beanPropertyMap._aliasMapping;
        final SettableBeanProperty[] settableBeanPropertyArr = beanPropertyMap._propsInOrder;
        final SettableBeanProperty[] settableBeanPropertyArr2 = Arrays.copyOf(settableBeanPropertyArr, settableBeanPropertyArr.length);
        _propsInOrder = settableBeanPropertyArr2;
        this.init(Arrays.asList(settableBeanPropertyArr2));
    }
    public BeanPropertyMap withCaseInsensitivity(final boolean z) {
        return _caseInsensitive == z ? this : new BeanPropertyMap(this, z);
    }
    protected void init(final Collection<SettableBeanProperty> collection) {
        final int size = collection.size();
        _size = size;
        final int iFindSize = BeanPropertyMap.findSize(size);
        _hashMask = iFindSize - 1;
        final int i2 = (iFindSize >> 1) + iFindSize;
        Object[] objArrCopyOf = new Object[i2 * 2];
        int i3 = 0;
        for (final SettableBeanProperty settableBeanProperty : collection) {
            if (null != settableBeanProperty) {
                final String propertyName = this.getPropertyName(settableBeanProperty);
                final int i_hashCode = this._hashCode(propertyName);
                int i4 = i_hashCode << 1;
                if (null != objArrCopyOf[i4]) {
                    i4 = ((i_hashCode >> 1) + iFindSize) << 1;
                    if (null != objArrCopyOf[i4]) {
                        i4 = (i2 << 1) + i3;
                        i3 += 2;
                        if (i4 >= objArrCopyOf.length) {
                            objArrCopyOf = Arrays.copyOf(objArrCopyOf, objArrCopyOf.length + 4);
                        }
                    }
                }
                objArrCopyOf[i4] = propertyName;
                objArrCopyOf[i4 + 1] = settableBeanProperty;
            }
        }
        _hashArea = objArrCopyOf;
        _spillCount = i3;
    }
    public static BeanPropertyMap construct(final MapperConfig<?> mapperConfig, final Collection<SettableBeanProperty> collection, final Map<String, List<PropertyName>> map, final boolean z) {
        return new BeanPropertyMap(z, collection, map, mapperConfig.getLocale());
    }
    public static BeanPropertyMap construct(final MapperConfig<?> mapperConfig, final Collection<SettableBeanProperty> collection, final Map<String, List<PropertyName>> map) {
        return new BeanPropertyMap(mapperConfig.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES), collection, map, mapperConfig.getLocale());
    }
    public static BeanPropertyMap construct(final Collection<SettableBeanProperty> collection, final boolean z, final Map<String, List<PropertyName>> map) {
        return new BeanPropertyMap(z, collection, map);
    }
    public BeanPropertyMap withProperty(final SettableBeanProperty settableBeanProperty) {
        final String propertyName = this.getPropertyName(settableBeanProperty);
        final int length = _hashArea.length;
        for (int i2 = 1; i2 < length; i2 += 2) {
            final SettableBeanProperty settableBeanProperty2 = (SettableBeanProperty) _hashArea[i2];
            if (null != settableBeanProperty2 && settableBeanProperty2.getName().equals(propertyName)) {
                return new BeanPropertyMap(this, settableBeanProperty, i2, this._findFromOrdered(settableBeanProperty2));
            }
        }
        return new BeanPropertyMap(this, settableBeanProperty, propertyName, this._hashCode(propertyName));
    }
    public BeanPropertyMap assignIndexes() {
        final int length = _hashArea.length;
        int i2 = 0;
        for (int i3 = 1; i3 < length; i3 += 2) {
            final SettableBeanProperty settableBeanProperty = (SettableBeanProperty) _hashArea[i3];
            if (null != settableBeanProperty) {
                settableBeanProperty.assignIndex(i2);
                i2++;
            }
        }
        return this;
    }
    public BeanPropertyMap renameAll(final NameTransformer nameTransformer) {
        if (null == nameTransformer || nameTransformer == NameTransformer.NOP) {
            return this;
        }
        final int length = _propsInOrder.length;
        final ArrayList arrayList = new ArrayList(length);
        for (int i2 = 0; i2 < length; i2++) {
            final SettableBeanProperty settableBeanProperty = _propsInOrder[i2];
            if (null == settableBeanProperty) {
                arrayList.add(settableBeanProperty);
            } else {
                arrayList.add(this._rename(settableBeanProperty, nameTransformer));
            }
        }
        return new BeanPropertyMap(_caseInsensitive, arrayList, _aliasDefs, _locale);
    }
    public BeanPropertyMap withoutProperties(final Collection<String> collection) {
        return this.withoutProperties(collection, null);
    }
    public BeanPropertyMap withoutProperties(final Collection<String> collection, final Collection<String> collection2) {
        if ((null == collection || collection.isEmpty()) && null == collection2) {
            return this;
        }
        final int length = _propsInOrder.length;
        final ArrayList arrayList = new ArrayList(length);
        for (int i2 = 0; i2 < length; i2++) {
            final SettableBeanProperty settableBeanProperty = _propsInOrder[i2];
            if (null != settableBeanProperty && !IgnorePropertiesUtil.shouldIgnore(settableBeanProperty.getName(), collection, collection2)) {
                arrayList.add(settableBeanProperty);
            }
        }
        return new BeanPropertyMap(_caseInsensitive, arrayList, _aliasDefs, _locale);
    }
    public void replace(final SettableBeanProperty settableBeanProperty, final SettableBeanProperty settableBeanProperty2) {
        final int length = _hashArea.length;
        for (int i2 = 1; i2 <= length; i2 += 2) {
            final Object[] objArr = _hashArea;
            if (objArr[i2] == settableBeanProperty) {
                objArr[i2] = settableBeanProperty2;
                _propsInOrder[this._findFromOrdered(settableBeanProperty)] = settableBeanProperty2;
                return;
            }
        }
        throw new NoSuchElementException("No entry '" + settableBeanProperty.getName() + "' found, can't replace");
    }
    public void remove(final SettableBeanProperty settableBeanProperty) {
        final ArrayList arrayList = new ArrayList(_size);
        final String propertyName = this.getPropertyName(settableBeanProperty);
        final int length = _hashArea.length;
        boolean zEquals = false;
        for (int i2 = 1; i2 < length; i2 += 2) {
            final Object[] objArr = _hashArea;
            final SettableBeanProperty settableBeanProperty2 = (SettableBeanProperty) objArr[i2];
            if (null != settableBeanProperty2) {
                if (!zEquals && (zEquals = propertyName.equals(objArr[i2 - 1]))) {
                    _propsInOrder[this._findFromOrdered(settableBeanProperty2)] = null;
                } else {
                    arrayList.add(settableBeanProperty2);
                }
            }
        }
        if (!zEquals) {
            throw new NoSuchElementException("No entry '" + settableBeanProperty.getName() + "' found, can't remove");
        }
        this.init(arrayList);
    }
    public int size() {
        return _size;
    }
    public boolean isCaseInsensitive() {
        return _caseInsensitive;
    }
    public boolean hasAliases() {
        return !_aliasDefs.isEmpty();
    }
    public Iterator<SettableBeanProperty> iterator() {
        return this._properties().iterator();
    }
    private List<SettableBeanProperty> _properties() {
        final ArrayList arrayList = new ArrayList(_size);
        final int length = _hashArea.length;
        for (int i2 = 1; i2 < length; i2 += 2) {
            final SettableBeanProperty settableBeanProperty = (SettableBeanProperty) _hashArea[i2];
            if (null != settableBeanProperty) {
                arrayList.add(settableBeanProperty);
            }
        }
        return arrayList;
    }
    public SettableBeanProperty[] getPropertiesInInsertionOrder() {
        return _propsInOrder;
    }
    protected final String getPropertyName(final SettableBeanProperty settableBeanProperty) {
        final boolean z = _caseInsensitive;
        final String name = settableBeanProperty.getName();
        return z ? name.toLowerCase(_locale) : name;
    }
    public SettableBeanProperty find(final int i2) {
        final int length = _hashArea.length;
        for (int i3 = 1; i3 < length; i3 += 2) {
            final SettableBeanProperty settableBeanProperty = (SettableBeanProperty) _hashArea[i3];
            if (null != settableBeanProperty && i2 == settableBeanProperty.getPropertyIndex()) {
                return settableBeanProperty;
            }
        }
        return null;
    }
    public SettableBeanProperty find(String str) {
        if (null == str) {
            throw new IllegalArgumentException("Cannot pass null property name");
        }
        if (_caseInsensitive) {
            str = str.toLowerCase(_locale);
        }
        final int iHashCode = str.hashCode() & _hashMask;
        final int i2 = iHashCode << 1;
        final Object obj = _hashArea[i2];
        if (obj == str || str.equals(obj)) {
            return (SettableBeanProperty) _hashArea[i2 + 1];
        }
        return this._find2(str, iHashCode, obj);
    }
    private final SettableBeanProperty _find2(final String str, final int i2, final Object obj) {
        if (null == obj) {
            return this._findWithAlias(_aliasMapping.get(str));
        }
        final int i3 = _hashMask + 1;
        final int i4 = ((i2 >> 1) + i3) << 1;
        final Object obj2 = _hashArea[i4];
        if (str.equals(obj2)) {
            return (SettableBeanProperty) _hashArea[i4 + 1];
        }
        if (null != obj2) {
            int i5 = (i3 + (i3 >> 1)) << 1;
            final int i6 = _spillCount + i5;
            while (i5 < i6) {
                final Object obj3 = _hashArea[i5];
                if (obj3 == str || str.equals(obj3)) {
                    return (SettableBeanProperty) _hashArea[i5 + 1];
                }
                i5 += 2;
            }
        }
        return this._findWithAlias(_aliasMapping.get(str));
    }
    private SettableBeanProperty _findWithAlias(final String str) {
        if (null == str) {
            return null;
        }
        final int i_hashCode = this._hashCode(str);
        final int i2 = i_hashCode << 1;
        final Object obj = _hashArea[i2];
        if (str.equals(obj)) {
            return (SettableBeanProperty) _hashArea[i2 + 1];
        }
        if (null == obj) {
            return null;
        }
        return this._find2ViaAlias(str, i_hashCode, obj);
    }
    private SettableBeanProperty _find2ViaAlias(final String str, final int i2, final Object obj) {
        final int i3 = _hashMask + 1;
        final int i4 = ((i2 >> 1) + i3) << 1;
        final Object obj2 = _hashArea[i4];
        if (str.equals(obj2)) {
            return (SettableBeanProperty) _hashArea[i4 + 1];
        }
        if (null == obj2) {
            return null;
        }
        int i5 = (i3 + (i3 >> 1)) << 1;
        final int i6 = _spillCount + i5;
        while (i5 < i6) {
            final Object obj3 = _hashArea[i5];
            if (obj3 == str || str.equals(obj3)) {
                return (SettableBeanProperty) _hashArea[i5 + 1];
            }
            i5 += 2;
        }
        return null;
    }
    public boolean findDeserializeAndSet(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj, final String str) throws IOException {
        final SettableBeanProperty settableBeanPropertyFind = this.find(str);
        if (null == settableBeanPropertyFind) {
            return false;
        }
        try {
            settableBeanPropertyFind.deserializeAndSet(jsonParser, deserializationContext, obj);
            return true;
        } catch (final Exception e2) {
            this.wrapAndThrow(e2, obj, str, deserializationContext);
            return true;
        }
    }
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Properties=[");
        final Iterator<SettableBeanProperty> it = this.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            final SettableBeanProperty next = it.next();
            final int i3 = i2 + 1;
            if (0 < i2) {
                sb.append(", ");
            }
            sb.append(next.getName());
            sb.append('(');
            sb.append(next.getType());
            sb.append(')');
            i2 = i3;
        }
        sb.append(']');
        if (!_aliasDefs.isEmpty()) {
            sb.append("(aliases: ");
            sb.append(_aliasDefs);
            sb.append(")");
        }
        return sb.toString();
    }
    protected SettableBeanProperty _rename(final SettableBeanProperty settableBeanProperty, final NameTransformer nameTransformer) {
        final JsonDeserializer<Object> jsonDeserializerUnwrappingDeserializer;
        if (null == settableBeanProperty) {
            return settableBeanProperty;
        }
        final SettableBeanProperty settableBeanPropertyWithSimpleName = settableBeanProperty.withSimpleName(nameTransformer.transform(settableBeanProperty.getName()));
        final JsonDeserializer<Object> valueDeserializer = settableBeanPropertyWithSimpleName.getValueDeserializer();
        return (null == valueDeserializer || (jsonDeserializerUnwrappingDeserializer = valueDeserializer.unwrappingDeserializer(nameTransformer)) == valueDeserializer) ? settableBeanPropertyWithSimpleName : settableBeanPropertyWithSimpleName.withValueDeserializer(jsonDeserializerUnwrappingDeserializer);
    }
    protected void wrapAndThrow(Throwable th, final Object obj, final String str, final DeserializationContext deserializationContext) throws IOException {
        while ((th instanceof InvocationTargetException) && null != th.getCause()) {
            th = th.getCause();
        }
        ClassUtil.throwIfError(th);
        final boolean z = null == deserializationContext || deserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS);
        if (th instanceof IOException) {
            if (!z || !(th instanceof JsonProcessingException)) {
                throw ((IOException) th);
            }
        } else if (!z) {
            ClassUtil.throwIfRTE(th);
        }
        throw JsonMappingException.wrapWithPath(th, obj, str);
    }
    private final int _findFromOrdered(final SettableBeanProperty settableBeanProperty) {
        final int length = _propsInOrder.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (_propsInOrder[i2] == settableBeanProperty) {
                return i2;
            }
        }
        throw new IllegalStateException("Illegal state: property '" + settableBeanProperty.getName() + "' missing from _propsInOrder");
    }
    private final int _hashCode(final String str) {
        return str.hashCode() & _hashMask;
    }
    private Map<String, String> _buildAliasMapping(final Map<String, List<PropertyName>> map, final boolean z, final Locale locale) {
        if (null == map || map.isEmpty()) {
            return Collections.emptyMap();
        }
        final HashMap map2 = new HashMap();
        for (final Map.Entry<String, List<PropertyName>> entry : map.entrySet()) {
            String key = entry.getKey();
            if (z) {
                key = key.toLowerCase(locale);
            }
            final Iterator<PropertyName> it = entry.getValue().iterator();
            while (it.hasNext()) {
                String simpleName = it.next().getSimpleName();
                if (z) {
                    simpleName = simpleName.toLowerCase(locale);
                }
                map2.put(simpleName, key);
            }
        }
        return map2;
    }
}
