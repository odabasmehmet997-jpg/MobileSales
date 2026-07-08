package com.fasterxml.jackson.annotation;

import java.io.Serializable;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

public @interface JsonAutoDetect {
    Visibility creatorVisibility() default Visibility.DEFAULT;

    Visibility fieldVisibility() default Visibility.DEFAULT;

    Visibility getterVisibility() default Visibility.DEFAULT;

    Visibility isGetterVisibility() default Visibility.DEFAULT;

    Visibility setterVisibility() default Visibility.DEFAULT;

    enum Visibility {
        ANY,
        NON_PRIVATE,
        PROTECTED_AND_PUBLIC,
        PUBLIC_ONLY,
        NONE,
        DEFAULT;

        public boolean isVisible(final Member member) {
            final int i2 = C11831.f790x23d16a11[this.ordinal()];
            if (1 == i2) {
                return true;
            }
            if (3 == i2) {
                return !Modifier.isPrivate(member.getModifiers());
            }
            if (4 != i2) {
                if (5 != i2) {
                    return false;
                }
            } else if (Modifier.isProtected(member.getModifiers())) {
                return true;
            }
            return Modifier.isPublic(member.getModifiers());
        }
    }

    class Value implements Serializable {
        protected static final Value DEFAULT;
        private static final Visibility DEFAULT_FIELD_VISIBILITY;
        protected static final Value NO_OVERRIDES;
        private static final long serialVersionUID = 1;
        protected final Visibility _creatorVisibility;
        protected final Visibility _fieldVisibility;
        protected final Visibility _getterVisibility;
        protected final Visibility _isGetterVisibility;
        protected final Visibility _setterVisibility;

        static {
            final Visibility visibility = Visibility.PUBLIC_ONLY;
            DEFAULT_FIELD_VISIBILITY = visibility;
            DEFAULT = new Value(visibility, visibility, visibility, Visibility.ANY, visibility);
            final Visibility visibility2 = Visibility.DEFAULT;
            NO_OVERRIDES = new Value(visibility2, visibility2, visibility2, visibility2, visibility2);
        }

        private Value(final Visibility visibility, final Visibility visibility2, final Visibility visibility3, final Visibility visibility4, final Visibility visibility5) {
            _fieldVisibility = visibility;
            _getterVisibility = visibility2;
            _isGetterVisibility = visibility3;
            _setterVisibility = visibility4;
            _creatorVisibility = visibility5;
        }

        public static Value defaultVisibility() {
            return Value.DEFAULT;
        }

        public static Value noOverrides() {
            return Value.NO_OVERRIDES;
        }

        public static Value from(final JsonAutoDetect jsonAutoDetect) {
            return Value.construct(jsonAutoDetect.fieldVisibility(), jsonAutoDetect.getterVisibility(), jsonAutoDetect.isGetterVisibility(), jsonAutoDetect.setterVisibility(), jsonAutoDetect.creatorVisibility());
        }

        public static Value construct(final PropertyAccessor propertyAccessor, Visibility visibility) {
            Visibility visibility2;
            final Visibility visibility3;
            Visibility visibility4;
            Visibility visibility5 = Visibility.DEFAULT;
            final int i2 = C11831.SwitchMapcomfasterxmljacksonannotationPropertyAccessor[propertyAccessor.ordinal()];
            if (1 != i2) {
                if (2 == i2) {
                    visibility3 = visibility5;
                    visibility4 = visibility3;
                } else if (3 != i2) {
                    if (4 == i2) {
                        visibility3 = visibility;
                        visibility = visibility5;
                    } else if (6 == i2) {
                        visibility4 = visibility;
                        visibility3 = visibility5;
                        visibility = visibility3;
                        visibility2 = visibility;
                    } else if (7 != i2) {
                        visibility3 = visibility5;
                        visibility = visibility3;
                    } else {
                        visibility3 = visibility;
                        visibility5 = visibility3;
                        visibility4 = visibility5;
                    }
                    visibility4 = visibility;
                } else {
                    visibility3 = visibility5;
                    visibility4 = visibility3;
                    visibility2 = visibility4;
                    visibility5 = visibility;
                    visibility = visibility2;
                }
                visibility2 = visibility4;
            } else {
                visibility2 = visibility;
                visibility3 = visibility5;
                visibility = visibility3;
                visibility4 = visibility;
            }
            return Value.construct(visibility, visibility5, visibility3, visibility4, visibility2);
        }

        public static Value construct(final Visibility visibility, final Visibility visibility2, final Visibility visibility3, final Visibility visibility4, final Visibility visibility5) {
            final Value value_predefined = Value._predefined(visibility, visibility2, visibility3, visibility4, visibility5);
            return null == value_predefined ? new Value(visibility, visibility2, visibility3, visibility4, visibility5) : value_predefined;
        }

        public Value withFieldVisibility(final Visibility visibility) {
            return Value.construct(visibility, _getterVisibility, _isGetterVisibility, _setterVisibility, _creatorVisibility);
        }

        public Value withGetterVisibility(final Visibility visibility) {
            return Value.construct(_fieldVisibility, visibility, _isGetterVisibility, _setterVisibility, _creatorVisibility);
        }

        public Value withIsGetterVisibility(final Visibility visibility) {
            return Value.construct(_fieldVisibility, _getterVisibility, visibility, _setterVisibility, _creatorVisibility);
        }

        public Value withSetterVisibility(final Visibility visibility) {
            return Value.construct(_fieldVisibility, _getterVisibility, _isGetterVisibility, visibility, _creatorVisibility);
        }

        public Value withCreatorVisibility(final Visibility visibility) {
            return Value.construct(_fieldVisibility, _getterVisibility, _isGetterVisibility, _setterVisibility, visibility);
        }

        public static Value merge(final Value value, final Value value2) {
            return null == value ? value2 : value.withOverrides(value2);
        }

        public Value withOverrides(final Value value) {
            if (null == value || value == Value.NO_OVERRIDES || value == this || Value._equals(this, value)) {
                return this;
            }
            Visibility visibility = value._fieldVisibility;
            final Visibility visibility2 = Visibility.DEFAULT;
            if (visibility == visibility2) {
                visibility = _fieldVisibility;
            }
            Visibility visibility3 = value._getterVisibility;
            if (visibility3 == visibility2) {
                visibility3 = _getterVisibility;
            }
            Visibility visibility4 = value._isGetterVisibility;
            if (visibility4 == visibility2) {
                visibility4 = _isGetterVisibility;
            }
            Visibility visibility5 = value._setterVisibility;
            if (visibility5 == visibility2) {
                visibility5 = _setterVisibility;
            }
            Visibility visibility6 = value._creatorVisibility;
            if (visibility6 == visibility2) {
                visibility6 = _creatorVisibility;
            }
            return Value.construct(visibility, visibility3, visibility4, visibility5, visibility6);
        }

        public Class<JsonAutoDetect> valueFor() {
            return JsonAutoDetect.class;
        }

        public Visibility getFieldVisibility() {
            return _fieldVisibility;
        }

        public Visibility getGetterVisibility() {
            return _getterVisibility;
        }

        public Visibility getIsGetterVisibility() {
            return _isGetterVisibility;
        }

        public Visibility getSetterVisibility() {
            return _setterVisibility;
        }

        public Visibility getCreatorVisibility() {
            return _creatorVisibility;
        }

        protected Object readResolve() {
            final Value value_predefined = Value._predefined(_fieldVisibility, _getterVisibility, _isGetterVisibility, _setterVisibility, _creatorVisibility);
            return null == value_predefined ? this : value_predefined;
        }

        public String toString() {
            return String.format("JsonAutoDetect.Value(fields=%s,getters=%s,isGetters=%s,setters=%s,creators=%s)", _fieldVisibility, _getterVisibility, _isGetterVisibility, _setterVisibility, _creatorVisibility);
        }

        public int hashCode() {
            return ((_fieldVisibility.ordinal() + 1) ^ (((_getterVisibility.ordinal() * 3) - (_isGetterVisibility.ordinal() * 7)) + (_setterVisibility.ordinal() * 11))) ^ (_creatorVisibility.ordinal() * 13);
        }

        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (null == obj) {
                return false;
            }
            return obj.getClass() == this.getClass() && Value._equals(this, (Value) obj);
        }

        private static Value _predefined(final Visibility visibility, final Visibility visibility2, final Visibility visibility3, final Visibility visibility4, final Visibility visibility5) {
            if (visibility == Value.DEFAULT_FIELD_VISIBILITY) {
                final Value value = Value.DEFAULT;
                if (visibility2 == value._getterVisibility && visibility3 == value._isGetterVisibility && visibility4 == value._setterVisibility && visibility5 == value._creatorVisibility) {
                    return value;
                }
                return null;
            }
            final Visibility visibility6 = Visibility.DEFAULT;
            if (visibility == visibility6 && visibility2 == visibility6 && visibility3 == visibility6 && visibility4 == visibility6 && visibility5 == visibility6) {
                return Value.NO_OVERRIDES;
            }
            return null;
        }

        private static boolean _equals(final Value value, final Value value2) {
            return value._fieldVisibility == value2._fieldVisibility && value._getterVisibility == value2._getterVisibility && value._isGetterVisibility == value2._isGetterVisibility && value._setterVisibility == value2._setterVisibility && value._creatorVisibility == value2._creatorVisibility;
        }
    }

    /* renamed from: com.fasterxml.jackson.annotation.JsonAutoDetect1 */
    enum C11831 {
        ;

        /* renamed from: SwitchMapcomfasterxmljacksonannotationJsonAutoDetectVisibility */
        static final int[] f790x23d16a11;
        static final int[] SwitchMapcomfasterxmljacksonannotationPropertyAccessor;

        static {
            final int[] iArr = new int[PropertyAccessor.values().length];
            SwitchMapcomfasterxmljacksonannotationPropertyAccessor = iArr;
            try {
                iArr[PropertyAccessor.CREATOR.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C11831.SwitchMapcomfasterxmljacksonannotationPropertyAccessor[PropertyAccessor.FIELD.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                C11831.SwitchMapcomfasterxmljacksonannotationPropertyAccessor[PropertyAccessor.GETTER.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
            try {
                C11831.SwitchMapcomfasterxmljacksonannotationPropertyAccessor[PropertyAccessor.IS_GETTER.ordinal()] = 4;
            } catch (final NoSuchFieldError unused4) {
            }
            try {
                C11831.SwitchMapcomfasterxmljacksonannotationPropertyAccessor[PropertyAccessor.NONE.ordinal()] = 5;
            } catch (final NoSuchFieldError unused5) {
            }
            try {
                C11831.SwitchMapcomfasterxmljacksonannotationPropertyAccessor[PropertyAccessor.SETTER.ordinal()] = 6;
            } catch (final NoSuchFieldError unused6) {
            }
            try {
                C11831.SwitchMapcomfasterxmljacksonannotationPropertyAccessor[PropertyAccessor.ALL.ordinal()] = 7;
            } catch (final NoSuchFieldError unused7) {
            }
            final int[] iArr2 = new int[Visibility.values().length];
            f790x23d16a11 = iArr2;
            try {
                iArr2[Visibility.ANY.ordinal()] = 1;
            } catch (final NoSuchFieldError unused8) {
            }
            try {
                C11831.f790x23d16a11[Visibility.NONE.ordinal()] = 2;
            } catch (final NoSuchFieldError unused9) {
            }
            try {
                C11831.f790x23d16a11[Visibility.NON_PRIVATE.ordinal()] = 3;
            } catch (final NoSuchFieldError unused10) {
            }
            try {
                C11831.f790x23d16a11[Visibility.PROTECTED_AND_PUBLIC.ordinal()] = 4;
            } catch (final NoSuchFieldError unused11) {
            }
            try {
                C11831.f790x23d16a11[Visibility.PUBLIC_ONLY.ordinal()] = 5;
            } catch (final NoSuchFieldError unused12) {
            }
        }
    }
}
