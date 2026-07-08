package com.proje.mobilesales.core.interfaces;

import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.tigerwcf.REPORTXML;

public interface AsyncReportResponse {
    void onPreExecute(ProcessType processType);

    void processFinish(REPORTXML reportxml, ProcessType processType);
}
