package com.proje.mobilesales.core.netsis;

import java.util.List;

public class NetsisDataHeader extends Response {

    private List<NetsisData> data = null;
    private int limit;
    private int offset;
    private int totalCount;
    public int  getOffset() {
        return this.offset;
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }
    public int  getTotalCount() {
        return this.totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    public int  getLimit() {
        return this.limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
    public List<NetsisData> getData() {
        return this.data;
    }
    public void setData(List<NetsisData> data) {
        this.data = data;
    }
}
