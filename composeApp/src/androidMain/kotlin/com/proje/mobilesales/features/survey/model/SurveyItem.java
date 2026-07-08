package com.proje.mobilesales.features.survey.model;

import kotlin.jvm.internal.Intrinsics;

public final class SurveyItem {
    private final String executeResult;
    private final String message;
    private final String surveyId;
    private final String surveyLink;
    private final String surveyName;
    private final String willGetSurvey;
    public static  SurveyItem copydefault(SurveyItem surveyItem, String str, String str2, String str3, String str4, String str5, String str6, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = surveyItem.willGetSurvey;
        }
        if ((i2 & 2) != 0) {
            str2 = surveyItem.surveyId;
        }
        String str7 = str2;
        if ((i2 & 4) != 0) {
            str3 = surveyItem.surveyName;
        }
        String str8 = str3;
        if ((i2 & 8) != 0) {
            str4 = surveyItem.surveyLink;
        }
        String str9 = str4;
        if ((i2 & 16) != 0) {
            str5 = surveyItem.message;
        }
        String str10 = str5;
        if ((i2 & 32) != 0) {
            str6 = surveyItem.executeResult;
        }
        return surveyItem.copy(str, str7, str8, str9, str10, str6);
    }
    public String component1() {
        return this.willGetSurvey;
    }
    public String component2() {
        return this.surveyId;
    }
    public String component3() {
        return this.surveyName;
    }
    public String component4() {
        return this.surveyLink;
    }
    public String component5() {
        return this.message;
    }
    public String component6() {
        return this.executeResult;
    }
    public SurveyItem copy(String willGetSurvey, String surveyId, String surveyName, String surveyLink, String message, String executeResult) {
        Intrinsics.checkNotNullParameter(willGetSurvey, "willGetSurvey");
        Intrinsics.checkNotNullParameter(surveyId, "surveyId");
        Intrinsics.checkNotNullParameter(surveyName, "surveyName");
        Intrinsics.checkNotNullParameter(surveyLink, "surveyLink");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(executeResult, "executeResult");
        return new SurveyItem(willGetSurvey, surveyId, surveyName, surveyLink, message, executeResult);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SurveyItem surveyItem)) {
            return false;
        }
        return Intrinsics.areEqual(this.willGetSurvey, surveyItem.willGetSurvey) && Intrinsics.areEqual(this.surveyId, surveyItem.surveyId) && Intrinsics.areEqual(this.surveyName, surveyItem.surveyName) && Intrinsics.areEqual(this.surveyLink, surveyItem.surveyLink) && Intrinsics.areEqual(this.message, surveyItem.message) && Intrinsics.areEqual(this.executeResult, surveyItem.executeResult);
    }
    public int hashCode() {
        return (((((((((this.willGetSurvey.hashCode() * 31) + this.surveyId.hashCode()) * 31) + this.surveyName.hashCode()) * 31) + this.surveyLink.hashCode()) * 31) + this.message.hashCode()) * 31) + this.executeResult.hashCode();
    }
    public String toString() {
        return "SurveyItem(willGetSurvey=" + this.willGetSurvey + ", surveyId=" + this.surveyId + ", surveyName=" + this.surveyName + ", surveyLink=" + this.surveyLink + ", message=" + this.message + ", executeResult=" + this.executeResult + ')';
    }
    public SurveyItem(String willGetSurvey, String surveyId, String surveyName, String surveyLink, String message, String executeResult) {
        Intrinsics.checkNotNullParameter(willGetSurvey, "willGetSurvey");
        Intrinsics.checkNotNullParameter(surveyId, "surveyId");
        Intrinsics.checkNotNullParameter(surveyName, "surveyName");
        Intrinsics.checkNotNullParameter(surveyLink, "surveyLink");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(executeResult, "executeResult");
        this.willGetSurvey = willGetSurvey;
        this.surveyId = surveyId;
        this.surveyName = surveyName;
        this.surveyLink = surveyLink;
        this.message = message;
        this.executeResult = executeResult;
    }
    public String getWillGetSurvey() {
        return this.willGetSurvey;
    }
    public String getSurveyId() {
        return this.surveyId;
    }
    public String getSurveyName() {
        return this.surveyName;
    }
    public String getSurveyLink() {
        return this.surveyLink;
    }
    public String getMessage() {
        return this.message;
    }
    public String getExecuteResult() {
        return this.executeResult;
    }
    public SurveyItem() {
        this("", "", "", "", "", "");
    }
    public String surveyLink() {
        return "";
    }
    public Object surveyName() {
        return null;
    }
    public String willGetSurvey() {
        return "";
    }
}
