package com.google.zxing.client.android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.zxing.DecodeHintType;
import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Pattern;

public enum DecodeHintManager {
    ;
    private static final Pattern COMMA = Pattern.compile(",");
    private static final String TAG = "DecodeHintManager";

    public static Map<DecodeHintType, Object> parseDecodeHints(final Intent intent) {
        final Bundle extras = intent.getExtras();
        if (null == extras || extras.isEmpty()) {
            return null;
        }
        final EnumMap enumMap = new EnumMap(DecodeHintType.class);
        for (final DecodeHintType decodeHintType : DecodeHintType.values()) {
            if (!(DecodeHintType.CHARACTER_SET == decodeHintType || DecodeHintType.NEED_RESULT_POINT_CALLBACK == decodeHintType || DecodeHintType.POSSIBLE_FORMATS == decodeHintType)) {
                final String name = decodeHintType.name();
                if (extras.containsKey(name)) {
                    if (decodeHintType.getValueType().equals(Void.class)) {
                        enumMap.put(decodeHintType, Boolean.TRUE);
                    } else {
                        final Object obj = extras.get(name);
                        if (decodeHintType.getValueType().isInstance(obj)) {
                            enumMap.put(decodeHintType, obj);
                        } else {
                            Log.w(DecodeHintManager.TAG, "Ignoring hint " + decodeHintType + " because it is not assignable from " + obj);
                        }
                    }
                }
            }
        }
        Log.i(DecodeHintManager.TAG, "Hints from the Intent: " + enumMap);
        return enumMap;
    }
}
