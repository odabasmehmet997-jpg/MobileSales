package org.simpleframework.xml.core;

import java.util.ArrayList;
import java.util.Iterator;

class TreeModel implements Model {
    private final LabelMap attributes;
    private final Detail detail;
    private final LabelMap elements;
    private Expression expression;
    private final int index;
    private Label list;
    private final ModelMap models;
    private final String name;
    private final OrderList order;
    private final Policy policy;
    private final String prefix;
    private Label text;
    private static class OrderList extends ArrayList<String> {
    }
    public TreeModel(final Policy policy, final Detail detail) {
        this(policy, detail, null, null, 1);
    }
    public TreeModel(final Policy policy, final Detail detail, final String str, final String str2, final int i2) {
        attributes = new LabelMap(policy);
        elements = new LabelMap(policy);
        models = new ModelMap(detail);
        order = new OrderList();
        this.detail = detail;
        this.policy = policy;
        prefix = str2;
        index = i2;
        name = str;
    } 
    public Model lookup(final Expression expression) {
        final Model lookup = this.lookup(expression.getFirst(), expression.getIndex());
        if (expression.isPath()) {
            final Expression path = expression.getPath(1, 0);
            if (null != lookup) {
                return lookup.lookup(path);
            }
        }
        return lookup;
    }
    public void registerElement(final String str) throws Exception {
        if (!order.contains(str)) {
            order.add(str);
        }
        elements.put(str, null);
    }
    public void registerAttribute(final String str) throws Exception {
        attributes.put(str, null);
    }
    public void registerText(final Label label) throws Exception {
        if (null != this.text) {
            throw new TextException("Duplicate text annotation on %s", label);
        }
        text = label;
    }
    public void registerAttribute(final Label label) throws Exception {
        final String name = label.getName();
        if (null != this.attributes.get(name)) {
            throw new AttributeException("Duplicate annotation of name '%s' on %s", name, label);
        }
        attributes.put(name, label);
    }
    public void registerElement(final Label label) throws Exception {
        final String name = label.getName();
        if (null != this.elements.get(name)) {
            throw new ElementException("Duplicate annotation of name '%s' on %s", name, label);
        }
        if (!order.contains(name)) {
            order.add(name);
        }
        if (label.isTextList()) {
            list = label;
        }
        elements.put(name, label);
    }
    public ModelMap getModels() throws Exception {
        return models.getModels();
    }
    public LabelMap getAttributes() throws Exception {
        return attributes.getLabels();
    }
    public LabelMap getElements() throws Exception {
        return elements.getLabels();
    }
    public boolean isModel(final String str) {
        return models.containsKey(str);
    }
    public boolean isElement(final String str) {
        return elements.containsKey(str);
    }
    public boolean isAttribute(final String str) {
        return attributes.containsKey(str);
    }
    public Iterator<String> iterator() {
        final ArrayList arrayList = new ArrayList();
        final Iterator<String> it = order.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList.iterator();
    }
    public void validate(final Class cls) throws Exception {
        this.validateExpressions(cls);
        this.validateAttributes(cls);
        this.validateElements(cls);
        this.validateModels(cls);
        this.validateText(cls);
    }
    private void validateText(final Class cls) throws Exception {
        if (null != this.text) {
            if (!elements.isEmpty()) {
                throw new TextException("Text annotation %s used with elements in %s", text, cls);
            }
            if (this.isComposite()) {
                throw new TextException("Text annotation %s can not be used with paths in %s", text, cls);
            }
        }
    }
    private void validateExpressions(final Class cls) throws Exception {
        final Iterator<Label> it = elements.iterator();
        while (it.hasNext()) {
            final Label next = it.next();
            if (null != next) {
                this.validateExpression(next);
            }
        }
        final Iterator<Label> it2 = attributes.iterator();
        while (it2.hasNext()) {
            final Label next2 = it2.next();
            if (null != next2) {
                this.validateExpression(next2);
            }
        }
        final Label label = text;
        if (null != label) {
            this.validateExpression(label);
        }
    }
    private void validateExpression(final Label label) throws Exception {
        final Expression expression = label.getExpression();
        final Expression expression2 = this.expression;
        if (null != expression2) {
            final String path = expression2.getPath();
            final String path2 = expression.getPath();
            if (!path.equals(path2)) {
                throw new PathException("Path '%s' does not match '%s' in %s", path, path2, detail);
            }
            return;
        }
        this.expression = expression;
    }
    private void validateModels(final Class cls) throws Exception {
        final Iterator<ModelList> it = models.iterator();
        while (it.hasNext()) {
            final Iterator<Model> it2 = it.next().iterator();
            int i2 = 1;
            while (it2.hasNext()) {
                final Model next = it2.next();
                if (null != next) {
                    final String name = next.getName();
                    final int index = next.getIndex();
                    final int i3 = i2 + 1;
                    if (index != i2) {
                        throw new ElementException("Path section '%s[%s]' is out of sequence in %s", name, Integer.valueOf(index), cls);
                    }
                    next.validate(cls);
                    i2 = i3;
                }
            }
        }
    }
    private void validateAttributes(final Class cls) throws Exception {
        for (final String str : attributes.keySet()) {
            if (null == this.attributes.get(str)) {
                throw new AttributeException("Ordered attribute '%s' does not exist in %s", str, cls);
            }
            final Expression expression = this.expression;
            if (null != expression) {
                expression.getAttribute(str);
            }
        }
    }
    private void validateElements(final Class cls) throws Exception {
        for (final String str : elements.keySet()) {
            final ModelList modelList = models.get(str);
            final Label label = elements.get(str);
            if (null == modelList && null == label) {
                throw new ElementException("Ordered element '%s' does not exist in %s", str, cls);
            }
            if (null != modelList && null != label && !modelList.isEmpty()) {
                throw new ElementException("Element '%s' is also a path name in %s", str, cls);
            }
            final Expression expression = this.expression;
            if (null != expression) {
                expression.getElement(str);
            }
        }
    }
    public void register(final Label label) throws Exception {
        if (label.isAttribute()) {
            this.registerAttribute(label);
        } else if (label.isText()) {
            this.registerText(label);
        } else {
            this.registerElement(label);
        }
    }
    public Model lookup(final String str, final int i2) {
        return models.lookup(str, i2);
    }
    public Model register(final String str, final String str2, final int i2) throws Exception {
        final Model lookup = models.lookup(str, i2);
        return null == lookup ? this.create(str, str2, i2) : lookup;
    }
    private Model create(final String str, final String str2, final int i2) throws Exception {
        final TreeModel treeModel = new TreeModel(policy, detail, str, str2, i2);
        if (null != str) {
            models.register(str, treeModel);
            order.add(str);
        }
        return treeModel;
    }
    public boolean isComposite() {
        final Iterator<ModelList> it = models.iterator();
        while (it.hasNext()) {
            final Iterator<Model> it2 = it.next().iterator();
            while (it2.hasNext()) {
                final Model next = it2.next();
                if (null != next && !next.isEmpty()) {
                    return true;
                }
            }
        }
        return !models.isEmpty();
    }
    public boolean isEmpty() {
        if (null == this.text && elements.isEmpty() && attributes.isEmpty()) {
            return !this.isComposite();
        }
        return false;
    }
    public Label getText() {
        final Label label = list;
        return null != label ? label : text;
    }
    public Expression getExpression() {
        return expression;
    }
    public String getPrefix() {
        return prefix;
    }
    public String getName() {
        return name;
    }
    public int getIndex() {
        return index;
    }
    public String toString() {
        return String.format("model '%s[%s]'", name, Integer.valueOf(index));
    }
}
