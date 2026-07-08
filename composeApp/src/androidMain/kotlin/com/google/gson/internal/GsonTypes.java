
package com.google.gson.internal;

import java.lang.reflect.*;
import java.util.*;

import static java.util.Objects.requireNonNull;

public final class GsonTypes {
  static final Type[] EMPTY_TYPE_ARRAY = {};
  private GsonTypes() {
    throw new UnsupportedOperationException();
  }
  public static ParameterizedType newParameterizedTypeWithOwner(
          Type ownerType, Class<?> rawType, Type... typeArguments) {
    return new ParameterizedTypeImpl(ownerType, rawType, typeArguments);
  }
  public static GenericArrayType arrayOf(Type componentType) {
    return new GenericArrayTypeImpl(componentType);
  }
  public static WildcardType subtypeOf(Type bound) {
    Type[] upperBounds;
    if (bound instanceof WildcardType) {
      upperBounds = ((WildcardType) bound).getUpperBounds();
    } else {
      upperBounds = new Type[] {bound};
    }
    return new WildcardTypeImpl(upperBounds, EMPTY_TYPE_ARRAY);
  }
  public static WildcardType supertypeOf(Type bound) {
    Type[] lowerBounds;
    if (bound instanceof WildcardType) {
      lowerBounds = ((WildcardType) bound).getLowerBounds();
    } else {
      lowerBounds = new Type[] {bound};
    }
    return new WildcardTypeImpl(new Type[] {Object.class}, lowerBounds);
  }
  public static Type canonicalize(Type type) {
    if (type instanceof final Class<?> c) {
        return c.isArray() ? new GenericArrayTypeImpl(canonicalize(c.getComponentType())) : c;

    } else if (type instanceof final ParameterizedType p) {
        return new ParameterizedTypeImpl(
          p.getOwnerType(), (Class<?>) p.getRawType(), p.getActualTypeArguments());

    } else if (type instanceof final GenericArrayType g) {
        return new GenericArrayTypeImpl(g.getGenericComponentType());

    } else if (type instanceof final WildcardType w) {
        return new WildcardTypeImpl(w.getUpperBounds(), w.getLowerBounds());

    } else {
      // unsupported type, return as is
      return type;
    }
  }
  public static Class<?> getRawType(Type type) {
    if (type instanceof Class<?>) {
      // type is a normal class.
      return (Class<?>) type;

    } else if (type instanceof final ParameterizedType parameterizedType) {

        // getRawType() returns Type instead of Class; that seems to be an API mistake,
      // see https://bugs.openjdk.org/browse/JDK-8250659
      Type rawType = parameterizedType.getRawType();
      return (Class<?>) rawType;

    } else if (type instanceof GenericArrayType) {
      Type componentType = ((GenericArrayType) type).getGenericComponentType();
      return Array.newInstance(getRawType(componentType), 0).getClass();

    } else if (type instanceof TypeVariable) {
      // we could use the variable's bounds, but that won't work if there are multiple.
      // having a raw type that's more general than necessary is okay
      return Object.class;

    } else if (type instanceof WildcardType) {
      Type[] bounds = ((WildcardType) type).getUpperBounds();
      // Currently the JLS only permits one bound for wildcards so using first bound is safe
      assert 1 == bounds.length;
      return getRawType(bounds[0]);

    } else {
      String className = null == type ? "null" : type.getClass().getName();
      throw new IllegalArgumentException(
          "Expected a Class, ParameterizedType, or GenericArrayType, but <"
              + type
              + "> is of type "
              + className);
    }
  }
  private static boolean equal(Object a, Object b) {
    return Objects.equals(a, b);
  }
  public static boolean equals(Type a, Type b) {
    if (a == b) {
      // also handles (a == null && b == null)
      return true;

    } else if (a instanceof Class) {
      // Class already specifies equals().
      return a.equals(b);

    } else if (a instanceof final ParameterizedType pa) {
      if (!(b instanceof final ParameterizedType pb)) {
        return false;
      }

      // TODO: save a .clone() call
        return equal(pa.getOwnerType(), pb.getOwnerType())
          && pa.getRawType().equals(pb.getRawType())
          && Arrays.equals(pa.getActualTypeArguments(), pb.getActualTypeArguments());

    } else if (a instanceof final GenericArrayType ga) {
      if (!(b instanceof final GenericArrayType gb)) {
        return false;
      }

        return equals(ga.getGenericComponentType(), gb.getGenericComponentType());

    } else if (a instanceof final WildcardType wa) {
      if (!(b instanceof final WildcardType wb)) {
        return false;
      }

        return Arrays.equals(wa.getUpperBounds(), wb.getUpperBounds())
          && Arrays.equals(wa.getLowerBounds(), wb.getLowerBounds());

    } else if (a instanceof final TypeVariable<?> va) {
      if (!(b instanceof final TypeVariable<?> vb)) {
        return false;
      }
        return Objects.equals(va.getGenericDeclaration(), vb.getGenericDeclaration())
          && va.getName().equals(vb.getName());

    } else {
      // This isn't a type we support. Could be a generic array type, wildcard type, etc.
      return false;
    }
  }
  public static String typeToString(Type type) {
    return type instanceof Class ? ((Class<?>) type).getName() : type.toString();
  }
  private static Type getGenericSupertype(Type context, Class<?> rawType, Class<?> supertype) {
    if (supertype == rawType) {
      return context;
    }

    // we skip searching through interfaces if unknown is an interface
    if (supertype.isInterface()) {
      Class<?>[] interfaces = rawType.getInterfaces();
      for (int i = 0, length = interfaces.length; i < length; i++) {
        if (interfaces[i] == supertype) {
          return rawType.getGenericInterfaces()[i];
        } else if (supertype.isAssignableFrom(interfaces[i])) {
          return getGenericSupertype(rawType.getGenericInterfaces()[i], interfaces[i], supertype);
        }
      }
    }

    // check our supertypes
    if (!rawType.isInterface()) {
      while (Object.class != rawType) {
        Class<?> rawSupertype = rawType.getSuperclass();
        if (rawSupertype == supertype) {
          return rawType.getGenericSuperclass();
        } else if (supertype.isAssignableFrom(rawSupertype)) {
          return getGenericSupertype(rawType.getGenericSuperclass(), rawSupertype, supertype);
        }
        rawType = rawSupertype;
      }
    }

    // we can't resolve this further
    return supertype;
  }
  private static Type getSupertype(Type context, Class<?> contextRawType, Class<?> supertype) {
    if (context instanceof WildcardType) {
      // Wildcards are useless for resolving supertypes. As the upper bound has the same raw type,
      // use it instead
      Type[] bounds = ((WildcardType) context).getUpperBounds();
      // Currently the JLS only permits one bound for wildcards so using first bound is safe
      assert 1 == bounds.length;
      context = bounds[0];
    }
    if (!supertype.isAssignableFrom(contextRawType)) {
      throw new IllegalArgumentException(
          contextRawType + " is not the same as or a subtype of " + supertype);
    }
    return resolve(
        context, contextRawType, GsonTypes.getGenericSupertype(context, contextRawType, supertype));
  }
  public static Type getArrayComponentType(Type array) {
    return array instanceof GenericArrayType
        ? ((GenericArrayType) array).getGenericComponentType()
        : ((Class<?>) array).getComponentType();
  }
  public static Type getCollectionElementType(Type context, Class<?> contextRawType) {
    Type collectionType = getSupertype(context, contextRawType, Collection.class);

    if (collectionType instanceof ParameterizedType) {
      return ((ParameterizedType) collectionType).getActualTypeArguments()[0];
    }
    return Object.class;
  }
  public static Type[] getMapKeyAndValueTypes(Type context, Class<?> contextRawType) {
    /*
     * Work around a problem with the declaration of java.util.Properties. That
     * class should extend Hashtable<String, String>, but it's declared to
     * extend Hashtable<Object, Object>.
     */
    if (Properties.class.isAssignableFrom(contextRawType)) {
      return new Type[] {String.class, String.class};
    }

    Type mapType = getSupertype(context, contextRawType, Map.class);
    // TODO: strip wildcards?
    if (mapType instanceof final ParameterizedType mapParameterizedType) {
        return mapParameterizedType.getActualTypeArguments();
    }
    return new Type[] {Object.class, Object.class};
  }
  public static Type resolve(Type context, Class<?> contextRawType, Type toResolve) {

    return resolve(context, contextRawType, toResolve, new HashMap<TypeVariable<?>, Type>());
  }
  private static Type resolve(
          Type context,
          Class<?> contextRawType,
          Type toResolve,
          Map<TypeVariable<?>, Type> visitedTypeVariables) {
    TypeVariable<?> resolving = null;
    while (true) {
      if (toResolve instanceof final TypeVariable<?> typeVariable) {
          Type previouslyResolved = visitedTypeVariables.get(typeVariable);
        if (null != previouslyResolved) {
          // cannot reduce due to infinite recursion
          return (previouslyResolved == Void.TYPE) ? toResolve : previouslyResolved;
        }

        // Insert a placeholder to mark the fact that we are in the process of resolving this type
        visitedTypeVariables.put(typeVariable, Void.TYPE);
        if (null == resolving) {
          resolving = typeVariable;
        }

        toResolve = resolveTypeVariable(context, contextRawType, typeVariable);
        if (toResolve == typeVariable) {
          break;
        }

      } else if (toResolve instanceof final Class<?> original && original.isArray()) {
          Type componentType = original.getComponentType();
        Type newComponentType =
                resolve(context, contextRawType, componentType, visitedTypeVariables);
        toResolve = equal(componentType, newComponentType) ? original : arrayOf(newComponentType);
        break;

      } else if (toResolve instanceof final GenericArrayType original) {
          Type componentType = original.getGenericComponentType();
        Type newComponentType =
                resolve(context, contextRawType, componentType, visitedTypeVariables);
        toResolve = equal(componentType, newComponentType) ? original : arrayOf(newComponentType);
        break;

      } else if (toResolve instanceof final ParameterizedType original) {
          Type ownerType = original.getOwnerType();
        Type newOwnerType = resolve(context, contextRawType, ownerType, visitedTypeVariables);
        boolean ownerChanged = !equal(newOwnerType, ownerType);

        Type[] args = original.getActualTypeArguments();
        boolean argsChanged = false;
        for (int t = 0, length = args.length; t < length; t++) {
          Type resolvedTypeArgument =
                  resolve(context, contextRawType, args[t], visitedTypeVariables);
          if (!equal(resolvedTypeArgument, args[t])) {
            if (!argsChanged) {
              args = args.clone();
              argsChanged = true;
            }
            args[t] = resolvedTypeArgument;
          }
        }

        toResolve =
            ownerChanged || argsChanged
                ? newParameterizedTypeWithOwner(
                    newOwnerType, (Class<?>) original.getRawType(), args)
                : original;
        break;

      } else if (toResolve instanceof final WildcardType original) {
          Type[] originalLowerBound = original.getLowerBounds();
        Type[] originalUpperBound = original.getUpperBounds();

        if (1 == originalLowerBound.length) {
          Type lowerBound =
                  resolve(context, contextRawType, originalLowerBound[0], visitedTypeVariables);
          if (lowerBound != originalLowerBound[0]) {
            toResolve = supertypeOf(lowerBound);
            break;
          }
        } else if (1 == originalUpperBound.length) {
          Type upperBound =
                  resolve(context, contextRawType, originalUpperBound[0], visitedTypeVariables);
          if (upperBound != originalUpperBound[0]) {
            toResolve = subtypeOf(upperBound);
            break;
          }
        }
        toResolve = original;
        break;

      } else {
        break;
      }
    }
    if (null != resolving) {
      visitedTypeVariables.put(resolving, toResolve);
    }
    return toResolve;
  }
  private static Type resolveTypeVariable(
          Type context, Class<?> contextRawType, TypeVariable<?> unknown) {
    Class<?> declaredByRaw = declaringClassOf(unknown);

    // we can't reduce this further
    if (null == declaredByRaw) {
      return unknown;
    }

    Type declaredBy = getGenericSupertype(context, contextRawType, declaredByRaw);
    if (declaredBy instanceof ParameterizedType) {
      int index = indexOf(declaredByRaw.getTypeParameters(), unknown);
      return ((ParameterizedType) declaredBy).getActualTypeArguments()[index];
    }

    return unknown;
  }

  private static int indexOf(Object[] array, Object toFind) {
    for (int i = 0, length = array.length; i < length; i++) {
      if (toFind.equals(array[i])) {
        return i;
      }
    }
    throw new NoSuchElementException();
  }


  private static Class<?> declaringClassOf(TypeVariable<?> typeVariable) {
    GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
    return genericDeclaration instanceof Class ? (Class<?>) genericDeclaration : null;
  }
  static void checkNotPrimitive(Type type) {
    if (type instanceof Class<?> && ((Class<?>) type).isPrimitive()) {
      throw new IllegalArgumentException("Primitive type is not allowed");
    }
  }
  public static boolean requiresOwnerType(Type rawType) {
    if (rawType instanceof final Class<?> rawTypeAsClass) {
        return !Modifier.isStatic(rawTypeAsClass.getModifiers())
          && null != rawTypeAsClass.getDeclaringClass();
    }
    return false;
  }
    private static class ParameterizedTypeImpl(Type ownerType, Type rawType,
                                               Type... typeArguments) implements ParameterizedType {
        private ParameterizedTypeImpl(Type ownerType, Class<?> rawType, Type... typeArguments) {
            requireNonNull(rawType);
            if (null == ownerType && requiresOwnerType(rawType)) {
                throw new IllegalArgumentException("Must specify owner type for " + rawType);
            }
            this.ownerType = null == ownerType ? null : canonicalize(ownerType);
            this.rawType = canonicalize(rawType);
            this.typeArguments = typeArguments.clone();
            for (int t = 0, length = this.typeArguments.length; t < length; t++) {
                requireNonNull(this.typeArguments[t]);
                checkNotPrimitive(this.typeArguments[t]);
                this.typeArguments[t] = canonicalize(this.typeArguments[t]);
            }
        }
        public Type[] getActualTypeArguments() {
            return typeArguments.clone();
        }
        public Type getRawType() {
            return rawType;
        }
        public Type getOwnerType() {
            return ownerType;
        }
        public boolean equals(Object other) {
            return other instanceof ParameterizedType
                    && GsonTypes.equals(this, (ParameterizedType) other);
        }
        private static int hashCodeOrZero(Object o) {
            return null != o ? o.hashCode() : 0;
        }
        public int hashCode() {
            return Arrays.hashCode(typeArguments) ^ rawType.hashCode() ^ hashCodeOrZero(ownerType);
        }


        public String toString() {
            int length = typeArguments.length;
            if (0 == length) {
                return typeToString(rawType);
            }

            StringBuilder stringBuilder = new StringBuilder(30 * (length + 1));
            stringBuilder
                    .append(typeToString(rawType))
                    .append("<")
                    .append(typeToString(typeArguments[0]));
            for (int i = 1; i < length; i++) {
                stringBuilder.append(", ").append(typeToString(typeArguments[i]));
            }
            return stringBuilder.append(">").toString();
        }
    }
    private static class GenericArrayTypeImpl(Type componentType) implements GenericArrayType {
        private GenericArrayTypeImpl(Type componentType) {
            requireNonNull(componentType);
            this.componentType = canonicalize(componentType);
        }


        public Type getGenericComponentType() {
            return componentType;
        }


        public boolean equals(Object o) {
            return o instanceof GenericArrayType && GsonTypes.equals(this, (GenericArrayType) o);
        }


        public String toString() {
            return typeToString(componentType) + "[]";
        }
    }

    private static final class WildcardTypeImpl implements WildcardType {
    private final Type upperBound;

    private final Type lowerBound;

    WildcardTypeImpl(Type[] upperBounds, Type[] lowerBounds) {
      if (1 < lowerBounds.length) {
        throw new IllegalArgumentException("At most one lower bound is supported");
      }
      if (1 != upperBounds.length) {
        throw new IllegalArgumentException("Exactly one upper bound must be specified");
      }

      if (1 == lowerBounds.length) {
        requireNonNull(lowerBounds[0]);
          checkNotPrimitive(lowerBounds[0]);
        if (Object.class != upperBounds[0]) {
          throw new IllegalArgumentException(
              "When lower bound is specified, upper bound must be Object");
        }
          this.lowerBound = canonicalize(lowerBounds[0]);
          this.upperBound = Object.class;

      } else {
        requireNonNull(upperBounds[0]);
          checkNotPrimitive(upperBounds[0]);
          this.lowerBound = null;
          this.upperBound = canonicalize(upperBounds[0]);
      }
    }

    
    public Type[] getUpperBounds() {
      return new Type[] {upperBound};
    }

    
    public Type[] getLowerBounds() {
      return null != this.lowerBound ? new Type[] {lowerBound} : EMPTY_TYPE_ARRAY;
    }

    
    public boolean equals(Object other) {
      return other instanceof WildcardType && GsonTypes.equals(this, (WildcardType) other);
    }

    
    public int hashCode() {
      // this equals Arrays.hashCode(getLowerBounds()) ^ Arrays.hashCode(getUpperBounds());
      return (null != this.lowerBound ? 31 + lowerBound.hashCode() : 1) ^ (31 + upperBound.hashCode());
    }

    
    public String toString() {
      if (null != this.lowerBound) {
        return "? super " + typeToString(lowerBound);
      } else if (Object.class == this.upperBound) {
        return "?";
      } else {
        return "? extends " + typeToString(upperBound);
      }
    }
  }
}
