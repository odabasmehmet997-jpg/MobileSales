package com.google.firebase.heartbeatinfo;

import androidx.annotation.NonNull;

/*  INFO: loaded from: classes2.dex */
public interface HeartBeatInfo {
    @NonNull
    HeartBeat getHeartBeatCode(@NonNull String str);

    public enum HeartBeat {
        NONE(0),
        SDK(1),
        GLOBAL(2),
        COMBINED(3);

        private final int code;

        HeartBeat(int i2) {
            this.code = i2;
        }

        public int getCode() {
            return this.code;
        }
    }
}
