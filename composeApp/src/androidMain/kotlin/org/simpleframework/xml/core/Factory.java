package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Position;

import java.lang.reflect.Modifier;


abstract class Factory {
    protected Context context;
    protected Class override;
    protected Support support;
    protected Type type;

    protected Factory(final Context context, final Type type) {
        this(context, type, null);
    }

    protected Factory(final Context context, final Type type, final Class cls) {
        support = context.getSupport();
        override = cls;
        this.context = context;
        this.type = type;
    }

    public Class getType() {
        final Class cls = override;
        return null != cls ? cls : type.type();
    }

    public Object getInstance() throws Exception {
        final Class type = this.getType();
        if (!Factory.isInstantiable(type)) {
            throw new InstantiationException("Type %s can not be instantiated", type);
        }
        return type.newInstance();
    }

    protected Value getOverride(final InputNode inputNode) throws Exception {
        final Value conversion = this.getConversion(inputNode);
        if (null != conversion) {
            final Position position = inputNode.getPosition();
            final Class type = conversion.getType();
            if (!Factory.isCompatible(this.getType(), type)) {
                throw new InstantiationException("Incompatible %s for %s at %s", type, this.type, position);
            }
        }
        return conversion;
    }

    public boolean setOverride(Type type, final Object obj, final OutputNode outputNode) throws Exception {
        final Class type2 = type.type();
        if (type2.isPrimitive()) {
            type = this.getPrimitive(type, type2);
        }
        return context.setOverride(type, obj, outputNode);
    }

    private Type getPrimitive(final Type type, final Class cls) throws Exception {
        final Class primitive = Support.getPrimitive(cls);
        return primitive != cls ? new OverrideType(type, primitive) : type;
    }

    public Value getConversion(final InputNode inputNode) throws Exception {
        final Value override = context.getOverride(type, inputNode);
        if (null != override && null != this.override) {
            if (!Factory.isCompatible(this.override, override.getType())) {
                return new OverrideValue(override, this.override);
            }
        }
        return override;
    }

    public static boolean isCompatible(Class cls, final Class cls2) {
        if (cls.isArray()) {
            cls = cls.getComponentType();
        }
        return cls.isAssignableFrom(cls2);
    }

    public static boolean isInstantiable(final Class cls) {
        if (Modifier.isAbstract(cls.getModifiers())) {
            return false;
        }
        return !Modifier.isInterface(r1);
    }
}
