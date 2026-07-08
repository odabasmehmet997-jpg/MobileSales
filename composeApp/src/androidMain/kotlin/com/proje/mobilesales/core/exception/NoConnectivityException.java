package com.proje.mobilesales.core.exception;

import com.proje.mobilesales.R;
import com.proje.mobilesales.core.utils.ContextUtils;
import java.io.IOException;

public class NoConnectivityException extends IOException {
    public String getMessage() {
        return ContextUtils.getStringResource(R.string.exp_23_internet_connection_not_found);
    }
}
