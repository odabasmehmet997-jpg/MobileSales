package com.proje.mobilesales.features.survey.viewmodel;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.proje.mobilesales.features.survey.model.SurveyItem;
import com.proje.mobilesales.features.survey.repository.SurveyRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import kotlin.jvm.internal.Intrinsics;

public final class SurveyViewModel extends ViewModel {
    private final MutableLiveData<SurveyItem> showDialogLiveData;
    private final SurveyRepository surveyRepository;
    private final String tag;
    public SurveyViewModel(SurveyRepository surveyRepository) {
        Intrinsics.checkNotNullParameter(surveyRepository, "surveyRepository");
        this.surveyRepository = surveyRepository;
        this.tag = "SurveyViewModel";
        this.showDialogLiveData = new MutableLiveData<>();
    }
    public MutableLiveData<SurveyItem> getShowDialogLiveData() {
        return this.showDialogLiveData;
    }
    public void getDataFromAPI() {
        this.surveyRepository.getSurveyDataFromAPI().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<SurveyItem>() {
            public void onComplete() {
            }
            public void onNext(SurveyItem surveyItem) {
                Intrinsics.checkNotNullParameter(surveyItem, "surveyItem");
                SurveyViewModel.this.getShowDialogLiveData().postValue(surveyItem);
            }
            public void onError(Throwable e2) {
                Intrinsics.checkNotNullParameter(e2, "e");
                e2.printStackTrace();
            }

            @Override
            public void onNext(Object t) {

            }
        });
    }
    public void postSurveyDataFromAPI(SurveyItem surveyItem) {
        Intrinsics.checkNotNullParameter(surveyItem, "surveyItem");
        this.surveyRepository.postNotShowLinkAgainToAPI(surveyItem).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<SurveyItem>() {

            public void onNext(SurveyItem responseSurvey) {
                String str;
                Intrinsics.checkNotNullParameter(responseSurvey, "responseSurvey");
                str = SurveyViewModel.this.tag;
                Log.d(str, "executeResult: " + responseSurvey.getExecuteResult());
            }
            public void onError(Throwable e2) {
                Intrinsics.checkNotNullParameter(e2, "e");
                e2.printStackTrace();
            }

            @Override
            public void onNext(Object t) {

            }

            @Override
            public void onComplete() {
                Log.d("SurveyItem", SurveyViewModel.this.getShowDialogLiveData().toString());
            }
        });
    }
}
