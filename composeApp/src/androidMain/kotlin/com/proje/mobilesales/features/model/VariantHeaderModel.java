package com.proje.mobilesales.features.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class VariantHeaderModel extends BaseObservable {
    private List<VariantModel> mVariantModelList = new ArrayList();
    private ObservableField<Integer> selectedItemCount = new ObservableField<>();

    public List<VariantModel> getMVariantModelList() {
        return mVariantModelList;
    }

    public void setMVariantModelList(final List<VariantModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        mVariantModelList = list;
    }

    public ObservableField<Integer> getSelectedItemCount() {
        return selectedItemCount;
    }

    public void setSelectedItemCount(final ObservableField<Integer> observableField) {
        Intrinsics.checkNotNullParameter(observableField, "<set-?>");
        selectedItemCount = observableField;
    }
}
