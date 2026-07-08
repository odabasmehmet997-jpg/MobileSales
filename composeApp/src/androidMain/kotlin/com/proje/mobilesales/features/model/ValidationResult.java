package com.proje.mobilesales.features.model;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    private String errorMessage;
    private List<String> keyWords;
    private boolean success = true;
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(final boolean z) {
        success = z;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(final String str) {
        errorMessage = str;
    }
    public ValidationResult() {
        this.keyWords = new ArrayList();
    }
    public List<String> getKeyWords() {
        return keyWords;
    }
    public void setKeyWords(final List<String> list) {
        keyWords = list;
    }
    public SpannableStringBuilder getSpannableErrorMessage(final String str) {
        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorMessage);
        if (0 == this.keyWords.size()) {
            return spannableStringBuilder;
        }
        for (final String str2 : keyWords) {
            final int length = str2.length();
            int indexOf = errorMessage.indexOf(str2);
            while (0 <= indexOf) {
                final int i2 = indexOf + length;
                spannableStringBuilder.setSpan(new StyleSpan(1), indexOf, i2, 18);
                indexOf = errorMessage.indexOf(str2, i2);
            }
        }
        if (!TextUtils.isEmpty(str)) {
            final SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new StyleSpan(1), 0, str.length(), 18);
            spannableStringBuilder.append(spannableString);
        }
        return spannableStringBuilder;
    }
}
