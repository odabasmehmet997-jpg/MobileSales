package com.proje.mobilesales.features.notification.view.list;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.extensions.DateExtensions;
import com.proje.mobilesales.core.extensions.ViewExtensions;
import com.proje.mobilesales.core.interfaces.notification.INoticationFilterResult;
import com.proje.mobilesales.databinding.FragmentNotificationFilterBinding;
import com.proje.mobilesales.features.notification.model.NotificationFilterModel;
import java.util.Calendar;
import java.util.Date;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationFilterFragment.kt */

public final class NotificationFilterFragment extends DialogFragment {
    public static final Companion Companion = new Companion(null);
    private FragmentNotificationFilterBinding _binding;
    private NotificationFilterModel filterModel;
    private INoticationFilterResult filterResult;

    public static NotificationFilterFragment newInstance(NotificationFilterModel notificationFilterModel) {
        return Companion.newInstance(notificationFilterModel);
    }

    private FragmentNotificationFilterBinding getBinding() {
        FragmentNotificationFilterBinding fragmentNotificationFilterBinding = this._binding;
        Intrinsics.checkNotNull(fragmentNotificationFilterBinding);
        return fragmentNotificationFilterBinding;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        this._binding = FragmentNotificationFilterBinding.inflate(layoutInflater, viewGroup, false);
        initializeButtons();
        setData();
        ConstraintLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        NotificationFilterModel notificationFilterModel;
        super.onCreate(bundle);
        if (bundle != null) {
            notificationFilterModel = bundle.getParcelable(NotificationFilterFragmentKt.ARG_NOTIFICATION_FILTER_MODEL);
            if (notificationFilterModel == null) {
                notificationFilterModel = new NotificationFilterModel();
            }
        } else {
            Bundle arguments = getArguments();
            notificationFilterModel = arguments != null ? (NotificationFilterModel) arguments.getParcelable(NotificationFilterFragmentKt.ARG_NOTIFICATION_FILTER_MODEL) : null;
            if (notificationFilterModel == null) {
                notificationFilterModel = new NotificationFilterModel();
            }
        }
        this.filterModel = notificationFilterModel;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Window window;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setCancelable(false);
        }
        Dialog dialog2 = getDialog();
        if (dialog2 != null && (window = dialog2.getWindow()) != null) {
            window.setLayout(-1, -2);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        super.onSaveInstanceState(bundle);
        NotificationFilterModel notificationFilterModel = this.filterModel;
        if (notificationFilterModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterModel");
            notificationFilterModel = null;
        }
        bundle.putParcelable(NotificationFilterFragmentKt.ARG_NOTIFICATION_FILTER_MODEL, notificationFilterModel);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        this.filterResult = (INoticationFilterResult) context;
    }

    private void initializeButtons() {
        FragmentNotificationFilterBinding binding = getBinding();
        binding.btnClose.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.notification.view.list.NotificationFilterFragmentExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NotificationFilterFragment.m1352r8lambdaC_syUdKit2w3xNuPDmUBq83GY(NotificationFilterFragment.this, view);
            }
        });
        binding.btnClear.setOnClickListener(new View.OnClickListener(binding) { // from class: com.proje.mobilesales.features.notification.view.list.NotificationFilterFragmentExternalSyntheticLambda3
            public final FragmentNotificationFilterBinding f1;

            {
                this.f1 = r2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NotificationFilterFragment.r8lambdaAnq3BSupfDeobEyx4NVHo0CYspo(NotificationFilterFragment.this, this.f1, view);
            }
        });
        binding.btnApply.setOnClickListener(new View.OnClickListener(binding) { // from class: com.proje.mobilesales.features.notification.view.list.NotificationFilterFragmentExternalSyntheticLambda4
            public final FragmentNotificationFilterBinding f1;

            {
                this.f1 = r2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NotificationFilterFragment.r8lambdaKbio_rwI2twuMBUx9Ja0WJ8yOuY(NotificationFilterFragment.this, this.f1, view);
            }
        });
        binding.imgBtnStartDate.setOnClickListener(new View.OnClickListener(binding) { // from class: com.proje.mobilesales.features.notification.view.list.NotificationFilterFragmentExternalSyntheticLambda5
            public final FragmentNotificationFilterBinding f1;

            {
                this.f1 = r2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NotificationFilterFragment.r8lambdawd0UYCYusFXLmt9lGADzT91LbO8(NotificationFilterFragment.this, this.f1, view);
            }
        });
        binding.imgBtnEndDate.setOnClickListener(new View.OnClickListener(binding) { // from class: com.proje.mobilesales.features.notification.view.list.NotificationFilterFragmentExternalSyntheticLambda6
            public final FragmentNotificationFilterBinding f1;

            {
                this.f1 = r2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NotificationFilterFragment.r8lambdaETAFqDOB3T6XSnQMi7DzNsklc6A(NotificationFilterFragment.this, this.f1, view);
            }
        });
    }

    
    public static void initializeButtonslambda7lambda0(NotificationFilterFragment notificationFilterFragment, View view) {
        Intrinsics.checkNotNullParameter(notificationFilterFragment, "this0");
        NotificationFilterModel notificationFilterModel = notificationFilterFragment.filterModel;
        NotificationFilterModel notificationFilterModel2 = null;
        if (notificationFilterModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterModel");
            notificationFilterModel = null;
        }
        notificationFilterModel.setShowDeleted(notificationFilterFragment.getBinding().swcShowDeleted.isChecked());
        INoticationFilterResult iNoticationFilterResult = notificationFilterFragment.filterResult;
        if (iNoticationFilterResult == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterResult");
            iNoticationFilterResult = null;
        }
        NotificationFilterModel notificationFilterModel3 = notificationFilterFragment.filterModel;
        if (notificationFilterModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterModel");
        } else {
            notificationFilterModel2 = notificationFilterModel3;
        }
        iNoticationFilterResult.filterChanged(notificationFilterModel2);
        notificationFilterFragment.dismiss();
    }

    
    public static void initializeButtonslambda7lambda1(NotificationFilterFragment notificationFilterFragment, FragmentNotificationFilterBinding fragmentNotificationFilterBinding, View view) {
        Intrinsics.checkNotNullParameter(notificationFilterFragment, "this0");
        Intrinsics.checkNotNullParameter(fragmentNotificationFilterBinding, "this_with");
        notificationFilterFragment.filterModel = new NotificationFilterModel();
        fragmentNotificationFilterBinding.swcShowDeleted.setChecked(false);
        fragmentNotificationFilterBinding.txtStartDateValue.setText("");
        fragmentNotificationFilterBinding.txtEndDateValue.setText("");
    }

    
    public static void initializeButtonslambda7lambda2(NotificationFilterFragment notificationFilterFragment, FragmentNotificationFilterBinding fragmentNotificationFilterBinding, View view) {
        Intrinsics.checkNotNullParameter(notificationFilterFragment, "this0");
        Intrinsics.checkNotNullParameter(fragmentNotificationFilterBinding, "this_with");
        NotificationFilterModel notificationFilterModel = notificationFilterFragment.filterModel;
        NotificationFilterModel notificationFilterModel2 = null;
        if (notificationFilterModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterModel");
            notificationFilterModel = null;
        }
        notificationFilterModel.setShowDeleted(fragmentNotificationFilterBinding.swcShowDeleted.isChecked());
        if (notificationFilterFragment.isFilterDatesValid()) {
            INoticationFilterResult iNoticationFilterResult = notificationFilterFragment.filterResult;
            if (iNoticationFilterResult == null) {
                Intrinsics.throwUninitializedPropertyAccessException("filterResult");
                iNoticationFilterResult = null;
            }
            NotificationFilterModel notificationFilterModel3 = notificationFilterFragment.filterModel;
            if (notificationFilterModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("filterModel");
            } else {
                notificationFilterModel2 = notificationFilterModel3;
            }
            iNoticationFilterResult.applyFilter(notificationFilterModel2);
            notificationFilterFragment.dismiss();
            return;
        }
        Context requireContext = notificationFilterFragment.requireContext();
        String string = notificationFilterFragment.getString(R.string.str_start_date_should_be_less_than_end_date);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        ViewExtensions.toastdefault(requireContext, string, 0, 2, null);
    }

    
    public static void initializeButtonslambda7lambda4(NotificationFilterFragment notificationFilterFragment, FragmentNotificationFilterBinding fragmentNotificationFilterBinding, View view) {
        Intrinsics.checkNotNullParameter(notificationFilterFragment, "this0");
        Intrinsics.checkNotNullParameter(fragmentNotificationFilterBinding, "this_with");
        Calendar instance = Calendar.getInstance();
        NotificationFilterModel notificationFilterModel = notificationFilterFragment.filterModel;
        NotificationFilterModel notificationFilterModel2 = null;
        if (notificationFilterModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterModel");
            notificationFilterModel = null;
        }
        if (notificationFilterModel.getStartDate() != null) {
            NotificationFilterModel notificationFilterModel3 = notificationFilterFragment.filterModel;
            if (notificationFilterModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("filterModel");
            } else {
                notificationFilterModel2 = notificationFilterModel3;
            }
            Date startDate = notificationFilterModel2.getStartDate();
            Intrinsics.checkNotNull(startDate);
            instance.setTime(startDate);
        }
        new DatePickerDialog(notificationFilterFragment.requireContext(), new DatePickerDialog.OnDateSetListener(fragmentNotificationFilterBinding) { // from class: com.proje.mobilesales.features.notification.view.list.NotificationFilterFragmentExternalSyntheticLambda0
            public final FragmentNotificationFilterBinding f1;

            {
                this.f1 = r2;
            }

            @Override // android.app.DatePickerDialog.OnDateSetListener
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                NotificationFilterFragment.r8lambda63N6pAgokPhvDhvoNB5vQedbIHU(NotificationFilterFragment.this, this.f1, datePicker, i, i2, i3);
            }
        }, instance.get(1), instance.get(2), instance.get(5)).show();
    }

    
    public static void initializeButtonslambda7lambda4lambda3(NotificationFilterFragment notificationFilterFragment, FragmentNotificationFilterBinding fragmentNotificationFilterBinding, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(notificationFilterFragment, "this0");
        Intrinsics.checkNotNullParameter(fragmentNotificationFilterBinding, "this_with");
        Calendar instance = Calendar.getInstance();
        instance.set(i, i2, i3);
        NotificationFilterModel notificationFilterModel = notificationFilterFragment.filterModel;
        NotificationFilterModel notificationFilterModel2 = null;
        if (notificationFilterModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterModel");
            notificationFilterModel = null;
        }
        notificationFilterModel.setStartDate(instance.getTime());
        AppCompatTextView appCompatTextView = fragmentNotificationFilterBinding.txtStartDateValue;
        NotificationFilterModel notificationFilterModel3 = notificationFilterFragment.filterModel;
        if (notificationFilterModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterModel");
        } else {
            notificationFilterModel2 = notificationFilterModel3;
        }
        appCompatTextView.setText(DateExtensions.formatDate(notificationFilterModel2.getStartDate(), "dd.MM.yyyy"));
    }

    
    public static void initializeButtonslambda7lambda6(NotificationFilterFragment notificationFilterFragment, FragmentNotificationFilterBinding fragmentNotificationFilterBinding, View view) {
        Intrinsics.checkNotNullParameter(notificationFilterFragment, "this0");
        Intrinsics.checkNotNullParameter(fragmentNotificationFilterBinding, "this_with");
        Calendar instance = Calendar.getInstance();
        NotificationFilterModel notificationFilterModel = notificationFilterFragment.filterModel;
        NotificationFilterModel notificationFilterModel2 = null;
        if (notificationFilterModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterModel");
            notificationFilterModel = null;
        }
        if (notificationFilterModel.getEndDate() != null) {
            NotificationFilterModel notificationFilterModel3 = notificationFilterFragment.filterModel;
            if (notificationFilterModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("filterModel");
            } else {
                notificationFilterModel2 = notificationFilterModel3;
            }
            Date endDate = notificationFilterModel2.getEndDate();
            Intrinsics.checkNotNull(endDate);
            instance.setTime(endDate);
        }
        new DatePickerDialog(notificationFilterFragment.requireContext(), new DatePickerDialog.OnDateSetListener(fragmentNotificationFilterBinding) { // from class: com.proje.mobilesales.features.notification.view.list.NotificationFilterFragmentExternalSyntheticLambda1
            public final FragmentNotificationFilterBinding f1;

            {
                this.f1 = r2;
            }

            @Override // android.app.DatePickerDialog.OnDateSetListener
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                NotificationFilterFragment.r8lambdaXorJSOKDRExG77XhjxxRhH4dkNI(NotificationFilterFragment.this, this.f1, datePicker, i, i2, i3);
            }
        }, instance.get(1), instance.get(2), instance.get(5)).show();
    }

    
    public static void initializeButtonslambda7lambda6lambda5(NotificationFilterFragment notificationFilterFragment, FragmentNotificationFilterBinding fragmentNotificationFilterBinding, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(notificationFilterFragment, "this0");
        Intrinsics.checkNotNullParameter(fragmentNotificationFilterBinding, "this_with");
        Calendar instance = Calendar.getInstance();
        instance.set(i, i2, i3);
        NotificationFilterModel notificationFilterModel = notificationFilterFragment.filterModel;
        NotificationFilterModel notificationFilterModel2 = null;
        if (notificationFilterModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterModel");
            notificationFilterModel = null;
        }
        notificationFilterModel.setEndDate(instance.getTime());
        AppCompatTextView appCompatTextView = fragmentNotificationFilterBinding.txtEndDateValue;
        NotificationFilterModel notificationFilterModel3 = notificationFilterFragment.filterModel;
        if (notificationFilterModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterModel");
        } else {
            notificationFilterModel2 = notificationFilterModel3;
        }
        appCompatTextView.setText(DateExtensions.formatDate(notificationFilterModel2.getEndDate(), "dd.MM.yyyy"));
    }

    private void setData() {
        String str;
        FragmentNotificationFilterBinding binding = getBinding();
        SwitchCompat switchCompat = binding.swcShowDeleted;
        NotificationFilterModel notificationFilterModel = this.filterModel;
        NotificationFilterModel notificationFilterModel2 = null;
        if (notificationFilterModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterModel");
            notificationFilterModel = null;
        }
        switchCompat.setChecked(notificationFilterModel.getShowDeleted());
        AppCompatTextView appCompatTextView = binding.txtStartDateValue;
        NotificationFilterModel notificationFilterModel3 = this.filterModel;
        if (notificationFilterModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterModel");
            notificationFilterModel3 = null;
        }
        String str2 = "";
        if (notificationFilterModel3.getStartDate() == null) {
            str = str2;
        } else {
            NotificationFilterModel notificationFilterModel4 = this.filterModel;
            if (notificationFilterModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("filterModel");
                notificationFilterModel4 = null;
            }
            str = DateExtensions.formatDate(notificationFilterModel4.getStartDate(), "dd.MM.yyyy");
        }
        appCompatTextView.setText(str);
        AppCompatTextView appCompatTextView2 = binding.txtEndDateValue;
        NotificationFilterModel notificationFilterModel5 = this.filterModel;
        if (notificationFilterModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterModel");
            notificationFilterModel5 = null;
        }
        if (notificationFilterModel5.getEndDate() != null) {
            NotificationFilterModel notificationFilterModel6 = this.filterModel;
            if (notificationFilterModel6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("filterModel");
            } else {
                notificationFilterModel2 = notificationFilterModel6;
            }
            str2 = DateExtensions.formatDate(notificationFilterModel2.getEndDate(), "dd.MM.yyyy");
        }
        appCompatTextView2.setText(str2);
    }

    private boolean isFilterDatesValid() {
        NotificationFilterModel notificationFilterModel = this.filterModel;
        NotificationFilterModel notificationFilterModel2 = null;
        if (notificationFilterModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterModel");
            notificationFilterModel = null;
        }
        if (notificationFilterModel.getStartDate() == null) {
            return true;
        }
        NotificationFilterModel notificationFilterModel3 = this.filterModel;
        if (notificationFilterModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterModel");
            notificationFilterModel3 = null;
        }
        if (notificationFilterModel3.getEndDate() == null) {
            return true;
        }
        NotificationFilterModel notificationFilterModel4 = this.filterModel;
        if (notificationFilterModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterModel");
            notificationFilterModel4 = null;
        }
        Date startDate = notificationFilterModel4.getStartDate();
        Intrinsics.checkNotNull(startDate);
        NotificationFilterModel notificationFilterModel5 = this.filterModel;
        if (notificationFilterModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterModel");
        } else {
            notificationFilterModel2 = notificationFilterModel5;
        }
        Date endDate = notificationFilterModel2.getEndDate();
        Intrinsics.checkNotNull(endDate);
        return !startDate.after(endDate);
    }

    /* compiled from: NotificationFilterFragment.kt */
    
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public NotificationFilterFragment newInstance(NotificationFilterModel notificationFilterModel) {
            Intrinsics.checkNotNullParameter(notificationFilterModel, "filterModel");
            NotificationFilterFragment notificationFilterFragment = new NotificationFilterFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(NotificationFilterFragmentKt.ARG_NOTIFICATION_FILTER_MODEL, notificationFilterModel);
            notificationFilterFragment.setArguments(bundle);
            return notificationFilterFragment;
        }
    }
}
