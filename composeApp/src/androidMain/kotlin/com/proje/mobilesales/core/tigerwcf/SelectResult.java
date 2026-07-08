package com.proje.mobilesales.core.tigerwcf;

import com.proje.mobilesales.core.enums.ProcessType;



public class SelectResult {
    public ProcessType type;
    public String resultXML = "";
    public String errorString = "";
    public String status = "";
    public String sql = "";
    public boolean isGetCompleted = false;
    public boolean isSaveCompleted = false;
    public String orderByText = "";
    public boolean isTableDelete = true;
}
