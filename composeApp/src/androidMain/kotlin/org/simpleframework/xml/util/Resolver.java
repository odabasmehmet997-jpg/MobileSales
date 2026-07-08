package org.simpleframework.xml.util;

import java.util.*;


public class Resolver<M extends Match> extends AbstractSet<M> {
    protected final Resolver<M>.Stack stack = new Stack();
    protected final Resolver<M>.Cache cache = new Cache();

    public M resolve(final String str) {
        List<M> list = cache.get(str);
        if (null == list) {
            list = this.resolveAll(str);
        }
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<M> resolveAll(final String str) {
        final List<M> list = cache.get(str);
        if (null != list) {
            return list;
        }
        final char[] charArray = str.toCharArray();
        if (null == charArray) {
            return null;
        }
        return this.resolveAll(str, charArray);
    }

    private List<M> resolveAll(final String str, final char[] cArr) {
        final ArrayList arrayList = new ArrayList();
        final Iterator<M> it = stack.iterator();
        while (it.hasNext()) {
            final Match match = it.next();
            if (this.match(cArr, match.getPattern().toCharArray())) {
                cache.put(str, arrayList);
                arrayList.add(match);
            }
        }
        return arrayList;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(final M m) {
        stack.push((Resolver<M>.Stack) m);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<M> iterator() {
        return stack.sequence();
    }

    public boolean remove(final M m) {
        cache.clear();
        return stack.remove(m);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return stack.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        cache.clear();
        stack.clear();
    }

    private boolean match(final char[] cArr, final char[] cArr2) {
        return this.match(cArr, 0, cArr2, 0);
    }

    private boolean match(final char[] cArr, int i2, final char[] cArr2, int i3) {
        while (i3 < cArr2.length && i2 < cArr.length) {
            if ('*' == cArr2[i3]) {
                do {
                    final char c2 = cArr2[i3];
                    if ('*' == c2) {
                        i3++;
                    } else {
                        if ('?' == c2 && (i3 = i3 + 1) >= cArr2.length) {
                            return true;
                        }
                        while (i2 < cArr.length) {
                            final char c3 = cArr[i2];
                            final char c4 = cArr2[i3];
                            if (c3 == c4 || '?' == c4) {
                                if ('?' == cArr2[i3 - 1]) {
                                    break;
                                }
                                if (this.match(cArr, i2, cArr2, i3)) {
                                    return true;
                                }
                            }
                            i2++;
                        }
                        if (cArr.length == i2) {
                            return false;
                        }
                    }
                } while (i3 < cArr2.length);
                return true;
            }
            final int i4 = i2 + 1;
            final int i5 = i3 + 1;
            if (cArr[i2] != cArr2[i3] && '?' != cArr2[i3]) {
                return false;
            }
            i2 = i4;
            i3 = i5;
        }
        if (cArr2.length == i3) {
            return cArr.length == i2;
        }
        while ('*' == cArr2[i3]) {
            i3++;
            if (i3 >= cArr2.length) {
                return true;
            }
        }
        return false;
    }

    private class Cache extends LimitedCache<List<M>> {
        public Cache() {
            super(1024);
        }
    }

    private class Stack extends LinkedList<M> {
        private Stack() {
        }

        @Override // java.util.LinkedList, java.util.Deque
        public void push(final M m) {
            cache.clear();
            this.addFirst(m);
        }

        public void purge(final int i2) {
            cache.clear();
            this.remove(i2);
        }

        public Iterator<M> sequence() {
            return new Sequence();
        }

        private class Sequence implements Iterator<M> {
            private int cursor;

            public Sequence() {
                cursor = size();
            }

            @Override // java.util.Iterator
            public M next() {
                if (!this.hasNext()) {
                    return null;
                }
                final Stack stack = Stack.this;
                final int i2 = cursor - 1;
                cursor = i2;
                return stack.get(i2);
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return 0 < this.cursor;
            }

            @Override // java.util.Iterator
            public void remove() {
                purge(cursor);
            }
        }
    }
}
