package org.simpleframework.xml.core;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.filter.Filter;
import org.simpleframework.xml.filter.PlatformFilter;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.TreeStrategy;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeBuilder;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.transform.Matcher;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Persister implements Serializer {
    private final Format format;
    private final SessionManager manager;
    private final Strategy strategy;
    private final Support support;
    public Persister() {
        this(new HashMap());
    }
    public Persister(final Format format) {
        this(new TreeStrategy(), format);
    }
    public Persister(final Map map) {
        this(new PlatformFilter(map));
    }
    public Persister(final Map map, final Format format) {
        this(new PlatformFilter(map));
    }
    public Persister(final Filter filter) {
        this(new TreeStrategy(), filter);
    }
    public Persister(final Filter filter, final Format format) {
        this(new TreeStrategy(), filter, format);
    }
    public Persister(final Matcher matcher) {
        this(new TreeStrategy(), matcher);
    }
    public Persister(final Matcher matcher, final Format format) {
        this(new TreeStrategy(), matcher, format);
    }
    public Persister(final Strategy strategy) {
        this(strategy, new HashMap());
    }
    public Persister(final Strategy strategy, final Format format) {
        this(strategy, new HashMap(), format);
    }
    public Persister(final Filter filter, final Matcher matcher) {
        this(new TreeStrategy(), filter, matcher);
    }
    public Persister(final Filter filter, final Matcher matcher, final Format format) {
        this(new TreeStrategy(), filter, matcher, format);
    }
    public Persister(final Strategy strategy, final Map map) {
        this(strategy, new PlatformFilter(map));
    }
    public Persister(final Strategy strategy, final Map map, final Format format) {
        this(strategy, new PlatformFilter(map), format);
    }
    public Persister(final Strategy strategy, final Filter filter) {
        this(strategy, filter, new Format());
    }
    public Persister(final Strategy strategy, final Filter filter, final Format format) {
        this(strategy, filter, new EmptyMatcher(), format);
    }
    public Persister(final Strategy strategy, final Matcher matcher) {
        this(strategy, new PlatformFilter(), matcher);
    }
    public Persister(final Strategy strategy, final Matcher matcher, final Format format) {
        this(strategy, new PlatformFilter(), matcher, format);
    }
    public Persister(final Strategy strategy, final Filter filter, final Matcher matcher) {
        this(strategy, filter, matcher, new Format());
    }
    public Persister(final Strategy strategy, final Filter filter, final Matcher matcher, final Format format) {
        support = new Support(filter, matcher, format);
        manager = new SessionManager();
        this.strategy = strategy;
        this.format = format;
    }
    public <T> T read(final Class<? extends T> cls, final String str) throws Exception {
        return (T) this.read((Class) cls, str, true);
    }
    public <T> T read(final Class<? extends T> cls, final File file) throws Exception {
        return (T) this.read((Class) cls, file, true);
    }
    public <T> T read(final Class<? extends T> cls, final InputStream inputStream) throws Exception {
        return (T) this.read((Class) cls, inputStream, true);
    }
    public <T> T read(final Class<? extends T> cls, final Reader reader) throws Exception {
        return (T) this.read((Class) cls, reader, true);
    }
    public <T> T read(final Class<? extends T> cls, final InputNode inputNode) throws Exception {
        return (T) this.read((Class) cls, inputNode, true);
    }
    public <T> T read(final Class<? extends T> cls, final String str, final boolean z) throws Exception {
        return (T) this.read((Class) cls, (Reader) new StringReader(str), z);
    }
    public <T> T read(final Class<? extends T> cls, final File file, final boolean z) throws Exception {
        final FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return (T) this.read((Class) cls, (InputStream) fileInputStream, z);
        } finally {
            fileInputStream.close();
        }
    }
    public <T> T read(final Class<? extends T> cls, final InputStream inputStream, final boolean z) throws Exception {
        return (T) this.read((Class) cls, NodeBuilder.read(inputStream), z);
    }
    public <T> T read(final Class<? extends T> cls, final Reader reader, final boolean z) throws Exception {
        return (T) this.read((Class) cls, NodeBuilder.read(reader), z);
    }
    public <T> T read(final Class<? extends T> cls, final InputNode inputNode, final boolean z) throws Exception {
        try {
            return (T) this.read((Class) cls, inputNode, manager.open(z));
        } finally {
            manager.close();
        }
    }
    private <T> T read(final Class<? extends T> cls, final InputNode inputNode, final Session session) throws Exception {
        return (T) this.read((Class) cls, inputNode, (Context) new Source(strategy, support, session));
    }
    private <T> T read(final Class<? extends T> cls, final InputNode inputNode, final Context context) throws Exception {
        return (T) new Traverser(context).read(inputNode, (Class) cls);
    }
    public <T> T read(final T t, final String str) throws Exception {
        return (T) this.read((Persister) t, str, true);
    }
    public <T> T read(final T t, final File file) throws Exception {
        return (T) this.read((Persister) t, file, true);
    }
    public <T> T read(final T t, final InputStream inputStream) throws Exception {
        return (T) this.read((Persister) t, inputStream, true);
    }
    public <T> T read(final T t, final Reader reader) throws Exception {
        return (T) this.read((Persister) t, reader, true);
    }
    public <T> T read(final T t, final InputNode inputNode) throws Exception {
        return (T) this.read((Persister) t, inputNode, true);
    }
    public <T> T read(final T t, final String str, final boolean z) throws Exception {
        return (T) this.read((Persister) t, (Reader) new StringReader(str), z);
    }
    public <T> T read(final T t, final File file, final boolean z) throws Exception {
        final FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return (T) this.read((Persister) t, (InputStream) fileInputStream, z);
        } finally {
            fileInputStream.close();
        }
    }
    public <T> T read(final T t, final InputStream inputStream, final boolean z) throws Exception {
        return (T) this.read((Persister) t, NodeBuilder.read(inputStream), z);
    }
    public <T> T read(final T t, final Reader reader, final boolean z) throws Exception {
        return (T) this.read((Persister) t, NodeBuilder.read(reader), z);
    }
    public <T> T read(final T t, final InputNode inputNode, final boolean z) throws Exception {
        try {
            return (T) this.read((Persister) t, inputNode, manager.open(z));
        } finally {
            manager.close();
        }
    }
    private <T> T read(final T t, final InputNode inputNode, final Session session) throws Exception {
        return (T) this.read((Persister) t, inputNode, (Context) new Source(strategy, support, session));
    }
    private <T> T read(final T t, final InputNode inputNode, final Context context) throws Exception {
        return (T) new Traverser(context).read(inputNode, t);
    }
    public boolean validate(final Class cls, final String str) throws Exception {
        return this.validate(cls, str, true);
    }
    public boolean validate(final Class cls, final File file) throws Exception {
        return this.validate(cls, file, true);
    }
    public boolean validate(final Class cls, final InputStream inputStream) throws Exception {
        return this.validate(cls, inputStream, true);
    }
    public boolean validate(final Class cls, final Reader reader) throws Exception {
        return this.validate(cls, reader, true);
    }
    public boolean validate(final Class cls, final InputNode inputNode) throws Exception {
        return this.validate(cls, inputNode, true);
    }
    public boolean validate(final Class cls, final String str, final boolean z) throws Exception {
        return this.validate(cls, new StringReader(str), z);
    }
    public boolean validate(final Class cls, final File file, final boolean z) throws Exception {
        final FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return this.validate(cls, fileInputStream, z);
        } finally {
            fileInputStream.close();
        }
    }
    public boolean validate(final Class cls, final InputStream inputStream, final boolean z) throws Exception {
        return this.validate(cls, NodeBuilder.read(inputStream), z);
    }
    public boolean validate(final Class cls, final Reader reader, final boolean z) throws Exception {
        return this.validate(cls, NodeBuilder.read(reader), z);
    }
    public boolean validate(final Class cls, final InputNode inputNode, final boolean z) throws Exception {
        try {
            return this.validate(cls, inputNode, manager.open(z));
        } finally {
            manager.close();
        }
    }
    private boolean validate(final Class cls, final InputNode inputNode, final Session session) throws Exception {
        return this.validate(cls, inputNode, new Source(strategy, support, session));
    }
    private boolean validate(final Class cls, final InputNode inputNode, final Context context) throws Exception {
        return new Traverser(context).validate(inputNode, cls);
    }
    public void write(final Object obj, final OutputNode outputNode) throws Exception {
        try {
            this.write(obj, outputNode, manager.open());
        } finally {
            manager.close();
        }
    }
    private void write(final Object obj, final OutputNode outputNode, final Session session) throws Exception {
        this.write(obj, outputNode, new Source(strategy, support, session));
    }
    private void write(final Object obj, final OutputNode outputNode, final Context context) throws Exception {
        new Traverser(context).write(outputNode, obj);
    }
    public void write(final Object obj, final File file) throws Exception {
        final FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            this.write(obj, fileOutputStream);
        } finally {
            fileOutputStream.close();
        }
    }
    public void write(final Object obj, final OutputStream outputStream) throws Exception {
        this.write(obj, outputStream, "utf-8");
    }
    public void write(final Object obj, final OutputStream outputStream, final String str) throws Exception {
        this.write(obj, new OutputStreamWriter(outputStream, str));
    }
    public void write(final Object obj, final Writer writer) throws Exception {
        this.write(obj, NodeBuilder.write(writer, format));
    }
}
