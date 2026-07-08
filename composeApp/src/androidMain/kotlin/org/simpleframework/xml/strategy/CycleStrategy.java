package org.simpleframework.xml.strategy;

import org.simpleframework.xml.stream.NodeMap;

import java.util.Map;


public class CycleStrategy implements Strategy {
    private final Contract contract;
    private final ReadState read;
    private final WriteState write;

    public CycleStrategy() {
        this(Name.MARK, "reference");
    }

    public CycleStrategy(final String str, final String str2) {
        this(str, str2, Name.LABEL);
    }

    public CycleStrategy(final String str, final String str2, final String str3) {
        this(str, str2, str3, Name.LENGTH);
    }

    public CycleStrategy(final String str, final String str2, final String str3, final String str4) {
        final Contract contract = new Contract(str, str2, str3, str4);
        this.contract = contract;
        write = new WriteState(contract);
        read = new ReadState(contract);
    }

    @Override // org.simpleframework.xml.strategy.Strategy
    public Value read(final Type type, final NodeMap nodeMap, final Map map) throws Exception {
        final ReadGraph find = read.find(map);
        if (null != find) {
            return find.read(type, nodeMap);
        }
        return null;
    }

    @Override // org.simpleframework.xml.strategy.Strategy
    public boolean write(final Type type, final Object obj, final NodeMap nodeMap, final Map map) {
        final WriteGraph find = write.find(map);
        if (null != find) {
            return find.write(type, obj, nodeMap);
        }
        return false;
    }
}
