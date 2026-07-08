package okhttp3.internal.ws;

import okio.ByteString;
public final class MessageDeflater2 {
    static final ByteString EMPTY_DEFLATE_BLOCK = ByteString.Companion.decodeHex("000000ffff");
    private static int LAST_OCTETS_COUNT_TO_REMOVE_AFTER_DEFLATION = 4;
    public static ByteString getEmptyDeflateBlock() {
        return EMPTY_DEFLATE_BLOCK;
    }
    public static int getLastOctetsCountToRemoveAfterDeflation() {
        return LAST_OCTETS_COUNT_TO_REMOVE_AFTER_DEFLATION;
    }
    public static void setLastOctetsCountToRemoveAfterDeflation(int value) {
        LAST_OCTETS_COUNT_TO_REMOVE_AFTER_DEFLATION = value;
    }
    private static final MessageDeflater2 INSTANCE = new MessageDeflater2();
    private MessageDeflater2() {}
    public static MessageDeflater2 getInstance() {
        return INSTANCE;
    }
    public static MessageDeflater2 create() {
        return new MessageDeflater2();
    }
    public static MessageDeflater2 newInstance() {
        return new MessageDeflater2();
    }
    public Object getDOMImplementation(String ls) {
        return null;
    }
}
