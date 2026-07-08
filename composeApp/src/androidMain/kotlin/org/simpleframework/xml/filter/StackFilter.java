package org.simpleframework.xml.filter;

import java.util.Stack;

public class StackFilter implements Filter {
    private final Stack<Filter> stack = new Stack<>();

    public void push(final Filter filter) {
        stack.push(filter);
    }

    @Override // org.simpleframework.xml.filter.Filter
    public String replace(final String str) {
        String replace;
        int size = stack.size();
        do {
            size--;
            if (0 > size) {
                return null;
            }
            replace = stack.get(size).replace(str);
        } while (null == replace);
        return replace;
    }
}
