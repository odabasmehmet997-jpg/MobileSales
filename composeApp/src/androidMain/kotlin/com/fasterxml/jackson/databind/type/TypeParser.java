package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TypeParser implements Serializable {
    private static final long serialVersionUID = 1;
    protected final TypeFactory _factory;

    public TypeParser(final TypeFactory typeFactory) {
        _factory = typeFactory;
    }

    public TypeParser withFactory(final TypeFactory typeFactory) {
        return typeFactory == _factory ? this : new TypeParser(typeFactory);
    }

    public JavaType parse(final String str) throws IllegalArgumentException {
        final MyTokenizer myTokenizer = new MyTokenizer(str.trim());
        final JavaType type = this.parseType(myTokenizer);
        if (myTokenizer.hasMoreTokens()) {
            throw this._problem(myTokenizer, "Unexpected tokens after complete type");
        }
        return type;
    }

    protected JavaType parseType(final MyTokenizer myTokenizer) throws IllegalArgumentException {
        if (!myTokenizer.hasMoreTokens()) {
            throw this._problem(myTokenizer, "Unexpected end-of-string");
        }
        final Class<?> clsFindClass = this.findClass(myTokenizer.nextToken(), myTokenizer);
        if (myTokenizer.hasMoreTokens()) {
            final String strNextToken = myTokenizer.nextToken();
            if ("<".equals(strNextToken)) {
                return _factory._fromClass(null, clsFindClass, TypeBindings.create(clsFindClass, this.parseTypes(myTokenizer)));
            }
            myTokenizer.pushBack(strNextToken);
        }
        return _factory._fromClass(null, clsFindClass, TypeBindings.emptyBindings());
    }

    protected List<JavaType> parseTypes(final MyTokenizer myTokenizer) throws IllegalArgumentException {
        final ArrayList arrayList = new ArrayList();
        while (myTokenizer.hasMoreTokens()) {
            arrayList.add(this.parseType(myTokenizer));
            if (!myTokenizer.hasMoreTokens()) {
                break;
            }
            final String strNextToken = myTokenizer.nextToken();
            if (">".equals(strNextToken)) {
                return arrayList;
            }
            if (!",".equals(strNextToken)) {
                throw this._problem(myTokenizer, "Unexpected token '" + strNextToken + "', expected ',' or '>')");
            }
        }
        throw this._problem(myTokenizer, "Unexpected end-of-string");
    }

    protected Class<?> findClass(final String str, final MyTokenizer myTokenizer) {
        try {
            return _factory.findClass(str);
        } catch (final Exception e2) {
            ClassUtil.throwIfRTE(e2);
            throw this._problem(myTokenizer, "Cannot locate class '" + str + "', problem: " + e2.getMessage());
        }
    }

    protected IllegalArgumentException _problem(final MyTokenizer myTokenizer, final String str) {
        return new IllegalArgumentException(String.format("Failed to parse type '%s' (remaining: '%s'): %s", myTokenizer.getAllInput(), myTokenizer.getRemainingInput(), str));
    }

    static final class MyTokenizer extends StringTokenizer {
        private int _index;
        private final String _input;
        private String _pushbackToken;

        public MyTokenizer(final String str) {
            super(str, "<,>", true);
            _input = str;
        }

        @Override // java.util.StringTokenizer
        public boolean hasMoreTokens() {
            return null != this._pushbackToken || super.hasMoreTokens();
        }

        @Override // java.util.StringTokenizer
        public String nextToken() {
            final String str = _pushbackToken;
            if (null != str) {
                _pushbackToken = null;
                return str;
            }
            final String strNextToken = super.nextToken();
            _index += strNextToken.length();
            return strNextToken.trim();
        }

        public void pushBack(final String str) {
            _pushbackToken = str;
        }

        public String getAllInput() {
            return _input;
        }

        public String getRemainingInput() {
            return _input.substring(_index);
        }
    }
}
