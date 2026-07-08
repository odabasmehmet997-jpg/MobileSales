package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
 
public final class SearchType extends Enum<SearchType> {
    private static final EnumEntries ENTRIES;
    private static final SearchType[] VALUES;
    private int value;
    public static final SearchType CONTAINING_SEARCH_WORDS = new SearchType("CONTAINING_SEARCH_WORDS", 0, 0);
    public static final SearchType STARTING_WITH_SEARCH_WORD = new SearchType("STARTING_WITH_SEARCH_WORD", 1, 1);
    public static final SearchType CONTAINING_SEARCH_WORD_ORDER = new SearchType("CONTAINING_SEARCH_WORD_ORDER", 2, 2);
    private static SearchType[] values() {
        return new SearchType[]{CONTAINING_SEARCH_WORDS, STARTING_WITH_SEARCH_WORD, CONTAINING_SEARCH_WORD_ORDER};
    }
    public static EnumEntries<SearchType> getEntries() {
        return ENTRIES;
    }
    public static SearchType valueOf(String str) {
        return Enum.valueOf(SearchType.class, str);
    }
    public static SearchType[] values() {
        return VALUES.clone();
    }
    private SearchType(String str, int i2, int i3) {
        super(str,i2);
        this.value = i3;
    }
    public int getValue() {
        return this.value;
    }
    public void setValue(int i2) {
        this.value = i2;
    }

    static {
        SearchType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
