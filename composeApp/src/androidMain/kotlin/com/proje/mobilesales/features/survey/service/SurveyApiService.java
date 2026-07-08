package com.proje.mobilesales.features.survey.service;

import android.os.Build;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.dbmodel.User;
import com.proje.mobilesales.features.licence.LicenseUtils;
import com.proje.mobilesales.features.survey.model.SurveyItem;
import com.proje.mobilesales.features.survey.util.SurveyConstants;
import com.proje.mobilesales.features.survey.util.SurveyDynamicHeaderInterceptor;
import io.reactivex.Observable;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class SurveyApiService {
    private final SurveyApi api;
    private final OkHttpClient client;
    private final Gson gson;
    private final User user;

    public SurveyApiService() {
        User user = ErpCreator.getInstance().getmBaseErp().getUser();
        Intrinsics.checkNotNullExpressionValue(user, "getUser(...)");
        this.user = user;
        OkHttpClient build = new OkHttpClient.Builder().addInterceptor(new SurveyDynamicHeaderInterceptor()).build();
        this.client = build;
        Gson create = new GsonBuilder().setLenient().create();
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        this.gson = create;
        Object create2 = new Retrofit.Builder().baseUrl(SurveyConstants.BASE_URL).addConverterFactory(GsonConverterFactory.create(create)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(build).build().create(SurveyApi.class);
        Intrinsics.checkNotNullExpressionValue(create2, "create(...)");
        this.api = (SurveyApi) create2;
    }
    public OkHttpClient getClient() {
        return this.client;
    }
    public Gson getGson() {
        return this.gson;
    }
    public SurveyApi getApi() {
        return this.api;
    }
    public Observable<SurveyItem> getSurveyData() {
        return this.api.getSurveyLink(StringUtils.clearTurkishChars("{\"name\":\"" + this.user.getName() + "\",\"surName\":\"\",\"uniqueIdentifier\":\"" + LicenseUtils.getLicenseKey() + '@' + this.user.getCode() + "@|" + Build.MANUFACTURER + '|' + Build.BRAND + '|' + Build.MODEL + '|' + Build.VERSION.RELEASE + "\"}"));
    }
    public Observable<SurveyItem> postNotShowLinkAgain(SurveyItem surveyItem) {
        Intrinsics.checkNotNullParameter(surveyItem, "surveyItem");
        return this.api.postNotShowLinkAgain(StringUtils.clearTurkishChars("{\"examId\":\"" + surveyItem.getSurveyId() + "\",\"uniqueIdentifier\":\"" + LicenseUtils.getLicenseKey() + '@' + this.user.getCode() + "\"}"), surveyItem);
    }
}
