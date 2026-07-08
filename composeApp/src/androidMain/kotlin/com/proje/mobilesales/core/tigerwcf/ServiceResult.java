package com.proje.mobilesales.core.tigerwcf;

import com.proje.mobilesales.core.enums.DataObjectType;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.enums.SendType;
import java.util.concurrent.atomic.AtomicLong;



public class ServiceResult {
    static final AtomicLong NEXT_ID = new AtomicLong(0);
    public DataObjectType dataType;
    public String guid;
    public boolean isCompleted;
    public boolean isRead;
    public boolean isSaveCompleted;
    public int logicalRef;
    public boolean notDuplicateControl;
    public String sendArpName;
    public ProcessType type;
    public String xml;

    /* renamed from: id */
    public long f1213id = NEXT_ID.getAndIncrement();
    public String dataReference = "";
    public String paramXML = "";
    public String errorString = "";
    public String status = "";
    public String dataXML = "";
    public String date = "";
    public int clRef = 0;
    public String clName = "";
    public String clCode = "";
    public String sql = "";
    public String orderByText = "";
    public SendType mSendType = SendType.APPEND;
}
