package org.simpleframework.xml.core;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class SignatureBuilder {
    private final Constructor factory;
    private final ParameterTable table = new ParameterTable();
    public SignatureBuilder(final Constructor constructor) {
        factory = constructor;
    }
    public boolean isValid() {
        return factory.getParameterTypes().length == table.width();
    }
    public void insert(final Parameter parameter, final int i2) {
        table.insert(parameter, i2);
    }
    public List<Signature> build() throws Exception {
        return this.build(new ParameterTable());
    }
    private List<Signature> build(final ParameterTable parameterTable) throws Exception {
        if (table.isEmpty()) {
            return this.create();
        }
        this.build(parameterTable, 0);
        return this.create(parameterTable);
    }
    private List<Signature> create() throws Exception {
        final ArrayList<Signature> signatures = new ArrayList<>();
        final Signature signature = new Signature(factory);
        if (this.isValid()) {
            signatures.add(signature);
        }
        return signatures;
    }
    private List<Signature> create(final ParameterTable parameterTable) throws Exception {
        final ArrayList<Signature> signatures = new ArrayList<>();
        final int height = parameterTable.height();
        final int width = parameterTable.width();
        for (int i2 = 0; i2 < height; i2++) {
            final Signature signature = new Signature(factory);
            for (int i3 = 0; i3 < width; i3++) {
                final Parameter parameter = parameterTable.get(i3, i2);
                final String path = parameter.getPath();
                if (signature.contains(parameter.getKey())) {
                    throw new ConstructorException("Parameter '%s' is a duplicate in %s", path, factory);
                }
                signature.add(parameter);
            }

            signatures.add(signature);
        }
        return signatures;
    }
    private void build(final ParameterTable parameterTable, final int i2) {
        this.build(parameterTable, new ParameterList(), i2);
    }
    private void build(final ParameterTable parameterTable, final ParameterList parameterList, final int i2) {
        final ParameterList parameterList2 = table.get(i2);
        final int size = parameterList2.size();
        if (table.width() - 1 <= i2) {
            this.populate(parameterTable, parameterList, i2);
            return;
        }
        for (int i3 = 0; i3 < size; i3++) {
            final ParameterList parameterList3 = new ParameterList(parameterList);
            if (null != parameterList) {
                parameterList3.add(parameterList2.get(i3));
                this.build(parameterTable, parameterList3, i2 + 1);
            }
        }
    }
    private void populate(final ParameterTable parameterTable, final ParameterList parameterList, final int i2) {
        final ParameterList parameterList2 = table.get(i2);
        final int size = parameterList.size();
        final int size2 = parameterList2.size();
        for (int i3 = 0; i3 < size2; i3++) {
            for (int i4 = 0; i4 < size; i4++) {
                parameterTable.get(i4).add(parameterList.get(i4));
            }
            parameterTable.get(i2).add(parameterList2.get(i3));
        }
    }
    public static class ParameterTable extends ArrayList<ParameterList> {
        
        public int height() {
            if (0 < width()) {
                return this.get(0).size();
            }
            return 0;
        }

        
        public int width() {
            return this.size();
        }

        public void insert(final Parameter parameter, final int i2) {
            final ParameterList parameterList = this.get(i2);
            if (null != parameterList) {
                parameterList.add(parameter);
            }
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        public ParameterList get(final int i2) {
            for (int size = this.size(); size <= i2; size++) {
                this.add(new ParameterList());
            }
            return super.get(i2);
        }

        public Parameter get(final int i2, final int i3) {
            return this.get(i2).get(i3);
        }
    }
    private static class ParameterList extends ArrayList<Parameter> {
        public ParameterList() {
        }

        public ParameterList(final ParameterList parameterList) {
            super(parameterList);
        }
    }
}
