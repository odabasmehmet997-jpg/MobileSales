package com.proje.mobilesales.features.survey.service;

import com.proje.mobilesales.features.survey.model.SurveyItem;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SurveyApi {
    @GET("getSurveyLink")
    Observable<SurveyItem> getSurveyLink(@Header("requestRawBody") String str);
    @POST("notShowLinkAgain")
    Observable<SurveyItem> postNotShowLinkAgain(@Header("requestRawBody") String str, @Body SurveyItem surveyItem);
}
