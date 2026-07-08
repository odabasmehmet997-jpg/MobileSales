package com.gu.toolargetool;

import java.util.List;

import static kotlin.jvm.internal.Intrinsics.areEqual;

public final class SizeTree {
    private final String key;
    private final List subTrees;
    private final int totalSize;
    public SizeTree() {
        this.key = paramString;
        this.totalSize = paramInt;
        this.subTrees = paramList;
    }
    public String component1() {
        return this.key;
    }
    public int component2() {
        return this.totalSize;
    }
    public List component3() {
        return this.subTrees;
    }
    public boolean equals(Object paramObject) {
        if (this != paramObject) {
            boolean bool = paramObject instanceof SizeTree;
            if (bool) {
                paramObject = paramObject;
                String str1 = this.key;
                String str2 = ((SizeTree)paramObject).key;
                bool = areEqual(str1, str2);
                if (bool) {
                    int i = this.totalSize;
                    int j = ((SizeTree)paramObject).totalSize;
                    if (i == j) {
                        List list = this.subTrees;
                        paramObject = ((SizeTree)paramObject).subTrees;
                        boolean bool1 = areEqual(list, paramObject);
                        return bool1;
                    }
                }
            }
            return false;
        }
        return true;
    }
    public int hashCode() {
        String str = this.key;
        int i = 0;
        int j = 0;
        if (null != str) {
             j = str.hashCode();
        } else {
            j = 0;
            str = null;
        }
        j *= 31;
        int k = this.totalSize;
         j = (j + k) * 31;
        List list = this.subTrees;
        if (null != list)
            i = list.hashCode();
        return j + i;
    }
    public String toString() {
        //this();
        String str = this.key;
        int i = this.totalSize;
        List list = this.subTrees;
        final String stringBuilder = "SizeTree(key=" +
                str +
                ", totalSize=" +
                i +
                ", subTrees=" +
                list +
                ")";
        return stringBuilder;
    }
}
