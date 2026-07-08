package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import java.io.Serializable;
 
public abstract class PropertyNamingStrategies implements Serializable {
    private static final long serialVersionUID = 2;
    public static final PropertyNamingStrategy LOWER_CAMEL_CASE = new LowerCamelCaseStrategy();
    public static final PropertyNamingStrategy UPPER_CAMEL_CASE = new UpperCamelCaseStrategy();
    public static final PropertyNamingStrategy SNAKE_CASE = new SnakeCaseStrategy();
    public static final PropertyNamingStrategy LOWER_CASE = new LowerCaseStrategy();
    public static final PropertyNamingStrategy KEBAB_CASE = new KebabCaseStrategy();
    public static final PropertyNamingStrategy LOWER_DOT_CASE = new LowerDotCaseStrategy();
    public static class LowerCamelCaseStrategy extends NamingBase {
        private static final long serialVersionUID = 2;
        public String translate(final String str) {
            return str;
        }
    }
    public static abstract class NamingBase extends PropertyNamingStrategy {
        private static final long serialVersionUID = 2;

        public abstract String translate(String str);
        public String nameForField(final MapperConfig<?> mapperConfig, final AnnotatedField annotatedField, final String str) {
            return this.translate(str);
        }

        public String nameForGetterMethod(final MapperConfig<?> mapperConfig, final AnnotatedMethod annotatedMethod, final String str) {
            return this.translate(str);
        }
        public String nameForSetterMethod(final MapperConfig<?> mapperConfig, final AnnotatedMethod annotatedMethod, final String str) {
            return this.translate(str);
        }
        public String nameForConstructorParameter(final MapperConfig<?> mapperConfig, final AnnotatedParameter annotatedParameter, final String str) {
            return this.translate(str);
        }

        protected String translateLowerCaseWithSeparator(final String str, final char c2) {
            final int length;
            if (null == str || 0 == (length = str.length())) {
                return str;
            }
            final StringBuilder sb = new StringBuilder((length >> 1) + length);
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                final char cCharAt = str.charAt(i3);
                final char lowerCase = Character.toLowerCase(cCharAt);
                if (lowerCase == cCharAt) {
                    if (1 < i2) {
                        sb.insert(sb.length() - 1, c2);
                    }
                    i2 = 0;
                } else {
                    if (0 == i2 && 0 < i3) {
                        sb.append(c2);
                    }
                    i2++;
                }
                sb.append(lowerCase);
            }
            return sb.toString();
        }
    }
    public static class SnakeCaseStrategy extends NamingBase {
        private static final long serialVersionUID = 2;
        public String translate(final String str) {
            if (null == str) {
                return str;
            }
            final int length = str.length();
            final StringBuilder sb = new StringBuilder(length * 2);
            int i2 = 0;
            boolean z = false;
            for (int i3 = 0; i3 < length; i3++) {
                char cCharAt = str.charAt(i3);
                if (0 < i3 || '_' != cCharAt) {
                    if (Character.isUpperCase(cCharAt)) {
                        if (!z && 0 < i2 && '_' != sb.charAt(i2 - 1)) {
                            sb.append('_');
                            i2++;
                        }
                        cCharAt = Character.toLowerCase(cCharAt);
                        z = true;
                    } else {
                        z = false;
                    }
                    sb.append(cCharAt);
                    i2++;
                }
            }
            return 0 < i2 ? sb.toString() : str;
        }
    }
    public static class UpperCamelCaseStrategy extends NamingBase {
        private static final long serialVersionUID = 2;
        public String translate(final String str) {
            char cCharAt = 0;
            final char upperCase;
            if (null == str || str.isEmpty() || cCharAt == (upperCase = Character.toUpperCase((cCharAt = str.charAt(0))))) {
                return str;
            }
            final StringBuilder sb = new StringBuilder(str);
            sb.setCharAt(0, upperCase);
            return sb.toString();
        }
    }
    public static class LowerCaseStrategy extends NamingBase {
        private static final long serialVersionUID = 2;
        public String translate(final String str) {
            return str.toLowerCase();
        }
    }
    public static class KebabCaseStrategy extends NamingBase {
        private static final long serialVersionUID = 2;
        public String translate(final String str) {
            return this.translateLowerCaseWithSeparator(str, '-');
        }
    }
    public static class LowerDotCaseStrategy extends NamingBase {
        private static final long serialVersionUID = 2;
        public String translate(final String str) {
            return this.translateLowerCaseWithSeparator(str, '.');
        }
    }
}
