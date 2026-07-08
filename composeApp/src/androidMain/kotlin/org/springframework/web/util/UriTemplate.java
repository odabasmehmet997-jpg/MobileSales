package org.springframework.web.util;

import org.springframework.util.Assert;

import java.io.Serializable;
import java.net.URI;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UriTemplate implements Serializable {
    private static final String DEFAULT_VARIABLE_PATTERN = "(.*)";
    private static final Pattern NAMES_PATTERN = Pattern.compile("\\{([^/]+?)\\}");
    private final Pattern matchPattern;
    private final UriComponents uriComponents;
    private final String uriTemplate;
    private final List<String> variableNames;
    public UriTemplate(final String str) {
        final Parser parser = new Parser(str);
        uriTemplate = str;
        variableNames = parser.getVariableNames();
        matchPattern = parser.getMatchPattern();
        uriComponents = UriComponentsBuilder.fromUriString(str).build();
    }
    public List<String> getVariableNames() {
        return variableNames;
    }
    public URI expand(final Map<String, ?> map) {
        return uriComponents.expand(map).encode().toUri();
    }
    public URI expand(final Object... objArr) {
        return uriComponents.expand(objArr).encode().toUri();
    }
    public boolean matches(final String str) {
        if (null == str) {
            return false;
        }
        return matchPattern.matcher(str).matches();
    }
    public Map<String, String> match(final String str) {
        Assert.notNull(str, "'uri' must not be null");
        final LinkedHashMap linkedHashMap = new LinkedHashMap(variableNames.size());
        final Matcher matcher = matchPattern.matcher(str);
        if (matcher.find()) {
            for (int i2 = 1; i2 <= matcher.groupCount(); i2++) {
                linkedHashMap.put(variableNames.get(i2 - 1), matcher.group(i2));
            }
        }
        return linkedHashMap;
    }
    public String toString() {
        return uriTemplate;
    }
    private static class Parser {
        private final StringBuilder patternBuilder;
        private final List<String> variableNames;

        private Parser(final String str) {
            variableNames = new LinkedList();
            patternBuilder = new StringBuilder();
            Assert.hasText(str, "'uriTemplate' must not be null");
            final Matcher matcher = NAMES_PATTERN.matcher(str);
            int i2 = 0;
            while (matcher.find()) {
                patternBuilder.append(this.quote(str, i2, matcher.start()));
                final String group = matcher.group(1);
                final int indexOf = group.indexOf(58);
                if (-1 == indexOf) {
                    patternBuilder.append(DEFAULT_VARIABLE_PATTERN);
                    variableNames.add(group);
                } else {
                    final int i3 = indexOf + 1;
                    if (i3 == group.length()) {
                        throw new IllegalArgumentException("No custom regular expression specified after ':' in \"" + group + "\"");
                    }
                    final String substring = group.substring(i3);
                    patternBuilder.append('(');
                    patternBuilder.append(substring);
                    patternBuilder.append(')');
                    variableNames.add(group.substring(0, indexOf));
                }
                i2 = matcher.end();
            }
            patternBuilder.append(this.quote(str, i2, str.length()));
            final int length = patternBuilder.length() - 1;
            if (0 > length || '/' != this.patternBuilder.charAt(length)) {
                return;
            }
            patternBuilder.deleteCharAt(length);
        }

        private String quote(final String str, final int i2, final int i3) {
            if (i2 == i3) {
                return "";
            }
            return Pattern.quote(str.substring(i2, i3));
        }

        
        public List<String> getVariableNames() {
            return Collections.unmodifiableList(variableNames);
        }

        
        public Pattern getMatchPattern() {
            return Pattern.compile(patternBuilder.toString());
        }
    }
}
