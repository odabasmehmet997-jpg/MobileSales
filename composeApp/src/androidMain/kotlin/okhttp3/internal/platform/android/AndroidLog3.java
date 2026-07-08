package okhttp3.internal.platform.android;

import java.util.logging.Level;
import java.util.logging.LogRecord;

public enum AndroidLog3 {
    ;

    static int getAndroidLevel(LogRecord logRecord) {
        int iIntValue = logRecord.getLevel().intValue();
        Level level = Level.INFO;
        if (iIntValue > level.intValue()) {
            return 5;
        }
        return logRecord.getLevel().intValue() == level.intValue() ? 4 : 3;
    }
}
