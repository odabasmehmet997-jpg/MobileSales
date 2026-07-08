package org.simpleframework.xml.core;

import org.simpleframework.xml.Order;
import org.simpleframework.xml.stream.Format;


class ModelAssembler {
    private final ExpressionBuilder builder;
    private final Detail detail;
    private final Format format;

    public ModelAssembler(final ExpressionBuilder expressionBuilder, final Detail detail, final Support support) throws Exception {
        format = support.getFormat();
        builder = expressionBuilder;
        this.detail = detail;
    }

    public void assemble(final Model model, final Order order) throws Exception {
        this.assembleElements(model, order);
        this.assembleAttributes(model, order);
    }

    private void assembleElements(final Model model, final Order order) throws Exception {
        for (final String str : order.elements()) {
            final Expression build = builder.build(str);
            if (build.isAttribute()) {
                throw new PathException("Ordered element '%s' references an attribute in %s", build, detail);
            }
            this.registerElements(model, build);
        }
    }

    private void assembleAttributes(final Model model, final Order order) throws Exception {
        for (final String str : order.attributes()) {
            final Expression build = builder.build(str);
            if (!build.isAttribute() && build.isPath()) {
                throw new PathException("Ordered attribute '%s' references an element in %s", build, detail);
            }
            if (!build.isPath()) {
                model.registerAttribute(format.style().getAttribute(str));
            } else {
                this.registerAttributes(model, build);
            }
        }
    }

    private void registerAttributes(final Model model, final Expression expression) throws Exception {
        final String prefix = expression.getPrefix();
        final String first = expression.getFirst();
        final int index = expression.getIndex();
        if (expression.isPath()) {
            final Model register = model.register(first, prefix, index);
            final Expression path = expression.getPath(1);
            if (null == register) {
                throw new PathException("Element '%s' does not exist in %s", first, detail);
            }
            this.registerAttributes(register, path);
            return;
        }
        this.registerAttribute(model, expression);
    }

    private void registerAttribute(final Model model, final Expression expression) throws Exception {
        final String first = expression.getFirst();
        if (null != first) {
            model.registerAttribute(first);
        }
    }

    private void registerElements(final Model model, final Expression expression) throws Exception {
        final String prefix = expression.getPrefix();
        final String first = expression.getFirst();
        final int index = expression.getIndex();
        if (null != first) {
            final Model register = model.register(first, prefix, index);
            final Expression path = expression.getPath(1);
            if (expression.isPath()) {
                this.registerElements(register, path);
            }
        }
        this.registerElement(model, expression);
    }

    private void registerElement(final Model model, final Expression expression) throws Exception {
        final String prefix = expression.getPrefix();
        final String first = expression.getFirst();
        final int index = expression.getIndex();
        if (1 < index && null == model.lookup(first, index - 1)) {
            throw new PathException("Ordered element '%s' in path '%s' is out of sequence for %s", first, expression, detail);
        }
        model.register(first, prefix, index);
    }
}
