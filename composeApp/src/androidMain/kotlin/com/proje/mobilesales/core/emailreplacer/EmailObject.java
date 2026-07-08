package com.proje.mobilesales.core.emailreplacer;

import java.util.List;

public class EmailObject {
    private List<String> files;
    private String from;
    private String html;
    private boolean ignoreToListInParams;
    private boolean sendHTMLContent;
    private String subject;

    private String[] f1171to;

    public String getFrom() {
        return from;
    }

    public void setFrom(final String str) {
        from = str;
    }

    public String[] getTo() {
        return f1171to;
    }

    public void setTo(final String[] strArr) {
        f1171to = strArr;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(final String str) {
        html = str;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(final String str) {
        subject = str;
    }

    public boolean isSendHTMLContent() {
        return sendHTMLContent;
    }

    public void setSendHTMLContent(final boolean z) {
        sendHTMLContent = z;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(final List<String> list) {
        files = list;
    }

    public boolean isIgnoreToListInParams() {
        return ignoreToListInParams;
    }

    public void setIgnoreToListInParams(final boolean z) {
        ignoreToListInParams = z;
    }
}
