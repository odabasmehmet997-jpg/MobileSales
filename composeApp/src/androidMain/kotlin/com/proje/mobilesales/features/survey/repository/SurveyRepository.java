package com.proje.mobilesales.features.survey.repository;

import com.proje.mobilesales.features.survey.model.SurveyItem;
import com.proje.mobilesales.features.survey.service.SurveyApiService;
import io.reactivex.Observable;
import kotlin.jvm.internal.Intrinsics;

public final class SurveyRepository {
    private final SurveyApiService surveyApiService;
    public SurveyRepository(SurveyApiService surveyApiService) {
        Intrinsics.checkNotNullParameter(surveyApiService, "surveyApiService");
        this.surveyApiService = surveyApiService;
    }
    public Observable<SurveyItem> getSurveyDataFromAPI() {
        return this.surveyApiService.getSurveyData();
    }
    public Observable<SurveyItem> postNotShowLinkAgainToAPI(SurveyItem surveyItem) {
        Intrinsics.checkNotNullParameter(surveyItem, "surveyItem");
        return this.surveyApiService.postNotShowLinkAgain(surveyItem);
    }
}
