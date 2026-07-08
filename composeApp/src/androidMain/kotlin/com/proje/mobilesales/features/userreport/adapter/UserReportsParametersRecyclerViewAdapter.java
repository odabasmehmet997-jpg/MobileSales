package com.proje.mobilesales.features.userreport.adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.RecyclerView;
import com.google.common.base.Strings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.base.BaseSelectResult;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.reportparser.ReportConstParam;
import com.proje.mobilesales.core.reportparser.ReportConstParamProp;
import com.proje.mobilesales.core.reportparser.ReportParam;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.view.CustomEditText;
import com.proje.mobilesales.features.model.GenericData;
import com.proje.mobilesales.features.userreport.repository.UserReportRepository;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsActivity;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsParametersActivity;
import com.proje.mobilesales.features.userreport.viewmodel.UserReportViewModel;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.proje.mobilesales.core.utils.AppUtils.createLayoutInflater;

public final class UserReportsParametersRecyclerViewAdapter extends RecyclerView.Adapter<UserReportsParametersRecyclerViewAdapter.ItemViewHolder> {
    public AlertDialogBuilder<?> mAlertDialogBuilder;
    private Context mContext;
    private int mCustomerRef;
    private int mItemRef;
    private LayoutInflater mLayoutInflater;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    private ReportConstParamProp mProp;
    private final UserReportRepository repository = new UserReportRepository();
    private final UserReportViewModel viewModel = new UserReportViewModel(this.repository);
    private final List<ReportParam> mParams = new ArrayList();
    public class EntriesMappings {
        public static final EnumEntries<ReportConstParam> entries0 = EnumEntriesKt.enumEntries(ReportConstParam.values());
    }
    public void restoreState(Bundle bundle) {
    }
    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilder;
        if (alertDialogBuilder != null) {
            return alertDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mAlertDialogBuilder");
        return null;
    }
    public void setMAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        Intrinsics.checkNotNullParameter(alertDialogBuilder, "<set-?>");
        this.mAlertDialogBuilder = alertDialogBuilder;
    }
    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        if (progressDialogBuilder != null) {
            return progressDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
        return null;
    }
    public void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        Intrinsics.checkNotNullParameter(progressDialogBuilder, "<set-?>");
        this.mProgressDialogBuilder = progressDialogBuilder;
    }
    public void setExtraParams(int i2, int i3, ReportConstParamProp reportConstParamProp) {
        this.mCustomerRef = i2;
        this.mItemRef = i3;
        this.mProp = reportConstParamProp;
    }
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        Context context = recyclerView.getContext();
        this.mContext = context;
        if (context instanceof BaseInjectableActivity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
            ((BaseInjectableActivity) context).getActivityComponent().inject(this);
        }
        this.mLayoutInflater = createLayoutInflater(this.mContext);
        Context context2 = ContextUtils.getmContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMAlertDialogBuilder(new AlertDialogBuilder.Impl(context2, (BaseInjectableActivity) activity));
        Context context3 = ContextUtils.getmContext();
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMProgressDialogBuilder(new ProgressDialogBuilder.Impl(context3, (BaseInjectableActivity) activity2));
    } 
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        int childCount = recyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) recyclerView.findViewHolderForAdapterPosition(i2);
            if (itemViewHolder != null) {
                itemViewHolder.getTxtValue().setOnFocusChangeListener(null);
            }
        }
        super.onDetachedFromRecyclerView(recyclerView);
        this.mContext = null;
    }
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater layoutInflater = this.mLayoutInflater;
        Intrinsics.checkNotNull(layoutInflater);
        View inflate = layoutInflater.inflate(R.layout.item_report_params, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ItemViewHolder(inflate);
    }
    public void onBindViewHolder(ItemViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        bindData(holder, getProperty(i2));
    }
    private boolean isParamConstant(String str) {
        for (ReportConstParam reportConstParam : EntriesMappings.entries0) {
            if (Intrinsics.areEqual(reportConstParam.name(), str) && (reportConstParam.getProp() == ReportConstParamProp.Sabit || reportConstParam.getProp() == this.mProp)) {
                return true;
            }
        }
        return false;
    }
    private ReportConstParam findConstantParam(String str) {
        for (ReportConstParam reportConstParam : EntriesMappings.entries0) {
            if (Intrinsics.areEqual(reportConstParam.name(), str)) {
                return reportConstParam;
            }
        }
        return null;
    }
    public void bindData(ItemViewHolder itemViewHolder, ReportParam reportParam) {
        Intrinsics.checkNotNullParameter(itemViewHolder, "holder");
        Intrinsics.checkNotNullParameter(reportParam, "property");
        itemViewHolder.getTxtCaption().setText(reportParam.getFieldLabel());
        itemViewHolder.getTxtCaption().setTag(reportParam.getName());
        itemViewHolder.getTxtValue().setBackgroundColor(-1);
        String dataType = reportParam.getDataType();
        LinkedHashMap linkedHashMap = null;
        if (dataType != null) {
            int hashCode = dataType.hashCode();
            if (hashCode != -335760659) {
                if (hashCode != 2603341) {
                    if (hashCode == 1857393595 && dataType.equals(ExifInterface.TAG_DATETIME)) {
                        itemViewHolder.getTxtValue().setKeyListener(null);
                        itemViewHolder.getTxtValue().setText(new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime()));
                        itemViewHolder.getTxtValue().setOnFocusChangeListener(new View.OnFocusChangeListener(itemViewHolder) { 
                            
                            public final   UserReportsParametersRecyclerViewAdapter.ItemViewHolder f1;
                            {
                                this.f1 = r2;
                            }

                            public void onFocusChange(View view, boolean z) {
                                UserReportsParametersRecyclerViewAdapter.bindDatalambda1(UserReportsParametersRecyclerViewAdapter.this, this.f1, view, z);
                            }
                        });
                        itemViewHolder.getTxtValue().setOnClickListener(new View.OnClickListener(itemViewHolder) { 
                            public final   UserReportsParametersRecyclerViewAdapter.ItemViewHolder f1;

                            {
                                this.f1 = r2;
                            }

                            public void onClick(View view) {
                                UserReportsParametersRecyclerViewAdapter.bindDatalambda2(UserReportsParametersRecyclerViewAdapter.this, this.f1, view);
                            }
                        });
                        itemViewHolder.getTxtValue().setText(reportParam.getValues());
                    }
                } else if (dataType.equals("Text")) {
                    String name = reportParam.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                    if (findConstantParam(name) == ReportConstParam.CariHesapKodu && this.mProp == ReportConstParamProp.Cari) {
                        itemViewHolder.getTxtValue().setText(this.viewModel.getSqlHelper().getClCode(this.mCustomerRef));
                        itemViewHolder.getLnReportParams().setVisibility(View.GONE);
                        itemViewHolder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                    } else {
                        String name2 = reportParam.getName();
                        Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
                        if (findConstantParam(name2) == ReportConstParam.MalzemeKodu && this.mProp == ReportConstParamProp.Stok) {
                            itemViewHolder.getTxtValue().setText(this.viewModel.getSqlHelper().getItemCode(this.mItemRef));
                            itemViewHolder.getLnReportParams().setVisibility(View.GONE);
                        }
                    }
                    itemViewHolder.getTxtValue().setInputType(1);
                    CustomEditText txtValue = itemViewHolder.getTxtValue();
                    String name3 = reportParam.getName();
                    Intrinsics.checkNotNullExpressionValue(name3, "getName(...)");
                    txtValue.setEnabled(!isParamConstant(name3));
                    itemViewHolder.getTxtValue().setText(reportParam.getValues());
                }
            } else if (dataType.equals("Numeric")) {
                itemViewHolder.getTxtValue().setInputType(2);
                itemViewHolder.getTxtValue().setText(reportParam.getValues());
            }
        }
        String type = reportParam.getType();
        if (type != null) {
            int hashCode2 = type.hashCode();
            if (hashCode2 != 2603341) {
                if (hashCode2 != 1408955741) {
                    if (hashCode2 == 1882908748 && type.equals("StaticList")) {
                        itemViewHolder.getTxtValue().setKeyListener(null);
                        CustomEditText txtValue2 = itemViewHolder.getTxtValue();
                        Context context = this.mContext;
                        Intrinsics.checkNotNull(context);
                        txtValue2.setHint(context.getString(R.string.str_question_make_choice));
                        CustomEditText txtValue3 = itemViewHolder.getTxtValue();
                        String keys = reportParam.getKeys();
                        Intrinsics.checkNotNullExpressionValue(keys, "getKeys(...)");
                        String values = reportParam.getValues();
                        Intrinsics.checkNotNullExpressionValue(values, "getValues(...)");
                        txtValue3.setTag(getList(keys, values));
                        itemViewHolder.getTxtValue().setOnFocusChangeListener(new View.OnFocusChangeListener(itemViewHolder, reportParam) {
                            public final UserReportsParametersRecyclerViewAdapter.ItemViewHolder f1;
                            public final   ReportParam f2;

                            {
                                this.f1 = r2;
                                this.f2 = r3;
                            } 
                            public void onFocusChange(View view, boolean z) {
                                UserReportsParametersRecyclerViewAdapter.bindDatalambda3(UserReportsParametersRecyclerViewAdapter.this, this.f1, this.f2, view, z);
                            }
                        });
                        itemViewHolder.getTxtValue().setOnClickListener(new View.OnClickListener(itemViewHolder, reportParam) {
                            public final   UserReportsParametersRecyclerViewAdapter.ItemViewHolder f1;
                            public final  ReportParam f2;

                            {
                                this.f1 = r2;
                                this.f2 = r3;
                            }

                            @Override // android.view.View.OnClickListener
                            public void onClick(View view) {
                                UserReportsParametersRecyclerViewAdapter.bindDatalambda4(UserReportsParametersRecyclerViewAdapter.this, this.f1, this.f2, view);
                            }
                        });
                        if (!Strings.isNullOrEmpty(reportParam.getSelectedItemValue())) {
                            Object tag = itemViewHolder.getTxtValue().getTag();
                            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type java.util.LinkedHashMap<kotlin.String?, kotlin.String>{ kotlin.collections.TypeAliasesKt.LinkedHashMap<kotlin.String?, kotlin.String> }");
                            LinkedHashMap linkedHashMap2 = (LinkedHashMap) tag;
                            int indexOf = CollectionsKt.indexOf((List<? extends String>) new ArrayList(linkedHashMap2.keySet()), reportParam.getSelectedItemValue());
                            CustomEditText txtValue4 = itemViewHolder.getTxtValue();
                            Collection values2 = linkedHashMap2.values();
                            Intrinsics.checkNotNullExpressionValue(values2, "<get-values>(...)");
                            txtValue4.setText(((String[]) values2.toArray(new String[0]))[indexOf]);
                            CustomEditText txtValue5 = itemViewHolder.getTxtValue();
                            Set keySet = linkedHashMap2.keySet();
                            Intrinsics.checkNotNullExpressionValue(keySet, "<get-keys>(...)");
                            txtValue5.setStringTag(String.valueOf(((String[]) keySet.toArray(new String[0]))[indexOf]));
                            return;
                        }
                        itemViewHolder.getTxtValue().setText(reportParam.getSelectedItemValue());
                    }
                } else if (type.equals("DynamicList")) {
                    itemViewHolder.getTxtValue().setKeyListener(null);
                    CustomEditText txtValue6 = itemViewHolder.getTxtValue();
                    Context context2 = this.mContext;
                    Intrinsics.checkNotNull(context2);
                    txtValue6.setHint(context2.getString(R.string.str_question_make_choice));
                    itemViewHolder.getTxtValue().setOnFocusChangeListener(new View.OnFocusChangeListener(itemViewHolder, reportParam) {
                        public final  UserReportsParametersRecyclerViewAdapter.ItemViewHolder f1;
                        public final  ReportParam f2;

                        {
                            this.f1 = r2;
                            this.f2 = r3;
                        } 
                        public void onFocusChange(View view, boolean z) {
                            UserReportsParametersRecyclerViewAdapter.bindDatalambda5(UserReportsParametersRecyclerViewAdapter.this, this.f1, this.f2, view, z);
                        }
                    });
                    itemViewHolder.getTxtValue().setOnClickListener(new View.OnClickListener(itemViewHolder, reportParam) {
                        public final UserReportsParametersRecyclerViewAdapter.ItemViewHolder f1;
                        public final ReportParam f2;

                        {
                            this.f1 = r2;
                            this.f2 = r3;
                        }

                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            UserReportsParametersRecyclerViewAdapter.bindDatalambda6(UserReportsParametersRecyclerViewAdapter.this, this.f1, this.f2, view);
                        }
                    });
                    if (!Strings.isNullOrEmpty(reportParam.getSelectedItemValue())) {
                        Object tag2 = itemViewHolder.getTxtValue().getTag();
                        if (tag2 instanceof LinkedHashMap) {
                            linkedHashMap = (LinkedHashMap) tag2;
                        }
                        if (linkedHashMap == null) {
                            linkedHashMap = new LinkedHashMap();
                        }
                        if (!linkedHashMap.isEmpty()) {
                            CustomEditText txtValue7 = itemViewHolder.getTxtValue();
                            Collection values3 = linkedHashMap.values();
                            Intrinsics.checkNotNullExpressionValue(values3, "<get-values>(...)");
                            txtValue7.setText(((String[]) values3.toArray(new String[0]))[StringUtils.convertStringToInt(reportParam.getSelectedItemValue())]);
                            CustomEditText txtValue8 = itemViewHolder.getTxtValue();
                            Set keySet2 = linkedHashMap.keySet();
                            Intrinsics.checkNotNullExpressionValue(keySet2, "<get-keys>(...)");
                            txtValue8.setStringTag(((String[]) keySet2.toArray(new String[0]))[StringUtils.convertStringToInt(reportParam.getSelectedItemValue())]);
                            return;
                        }
                        itemViewHolder.getTxtValue().setText(reportParam.getValues());
                        itemViewHolder.getTxtValue().setStringTag(reportParam.getSelectedItemValue());
                        return;
                    }
                    itemViewHolder.getTxtValue().setText("");
                }
            } else if (type.equals("Text")) {
                reportParam.setValues(String.valueOf(itemViewHolder.getTxtValue().getText()));
                itemViewHolder.getTxtValue().addTextChangedListener(new TextWatcher(reportParam) { 
                    ReportParam property;
 
                    {
                        this.property = r1;
                    }
    
                    public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                        Intrinsics.checkNotNullParameter(charSequence, "s");
                    }
 
                    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                        Intrinsics.checkNotNullParameter(charSequence, "s");
                    }
 
                    {
                        this.property = r1;
                    }
 
                    public void afterTextChanged(Editable editable) {
                        Intrinsics.checkNotNullParameter(editable, "s");
                        this.property.setValues(editable.toString());
                    }
                });
                itemViewHolder.getTxtValue().setText(reportParam.getValues());
                if (Intrinsics.areEqual(reportParam.getName(), "AktifFirmaNo") || Intrinsics.areEqual(reportParam.getName(), "AktifDonemNo")) {
                    itemViewHolder.getLnReportParams().setVisibility(View.GONE);
                }
            }
        }
    }
    public static void bindDatalambda1(UserReportsParametersRecyclerViewAdapter this0, ItemViewHolder holder, View view, boolean z) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(holder, "holder");
        Context context = this0.mContext;
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.proje.mobilesales.features.userreport.view.activity.UserReportsParametersActivity");
        ((UserReportsParametersActivity) context).hideKeyboard();
        if (z) {
            this0.showCalendar(holder.getTxtValue());
        }
    }
    public static void bindDatalambda2(UserReportsParametersRecyclerViewAdapter this0, ItemViewHolder holder, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(holder, "holder");
        this0.showCalendar(holder.getTxtValue());
    }
    public static void bindDatalambda3(UserReportsParametersRecyclerViewAdapter this0, ItemViewHolder holder, ReportParam property, View view, boolean z) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(property, "property");
        Context context = this0.mContext;
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.proje.mobilesales.features.userreport.view.activity.UserReportsParametersActivity");
        ((UserReportsParametersActivity) context).hideKeyboard();
        if (z) {
            this0.showDialog(holder.getTxtValue(), property);
        }
    }
    public static void bindDatalambda4(UserReportsParametersRecyclerViewAdapter this0, ItemViewHolder holder, ReportParam property, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(property, "property");
        this0.showDialog(holder.getTxtValue(), property);
    }
    public static void bindDatalambda5(UserReportsParametersRecyclerViewAdapter this0, ItemViewHolder holder, ReportParam property, View view, boolean z) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(property, "property");
        Context context = this0.mContext;
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.proje.mobilesales.features.userreport.view.activity.UserReportsParametersActivity");
        ((UserReportsParametersActivity) context).hideKeyboard();
        if (z) {
            this0.loadDynamicData(holder.getTxtValue(), property, "");
        }
    }
    public static void bindDatalambda6(UserReportsParametersRecyclerViewAdapter this0, ItemViewHolder holder, ReportParam property, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(property, "property");
        this0.loadDynamicData(holder.getTxtValue(), property, "");
    }
    private void loadDynamicData(CustomEditText customEditText, ReportParam reportParam, String str) {
        if (customEditText.getTag() == null) {
            String subSql = reportParam.getSubSql();
            Intrinsics.checkNotNullExpressionValue(subSql, "getSubSql(...)");
            int length = subSql.length() - 1;
            int i2 = 0;
            boolean z = false;
            while (i2 <= length) {
                boolean z2 = Intrinsics.compare(subSql.charAt(!z ? i2 : length), 32) <= 0;
                if (z) {
                    if (!z2) {
                        break;
                    } else {
                        length--;
                    }
                } else if (z2) {
                    i2++;
                } else {
                    z = true;
                }
            }
            if (Intrinsics.areEqual(subSql.subSequence(i2, length + 1).toString(), "")) {
                return;
            }
            ProgressDialogBuilder<?> mProgressDialogBuilder = getMProgressDialogBuilder();
            Context context = this.mContext;
            Intrinsics.checkNotNull(context);
            mProgressDialogBuilder.setMessage(context.getString(R.string.str_please_wait)).show();
            UserReportViewModel userReportViewModel = this.viewModel;
            UserReportsActivity.UserDefinedResponseListener userDefinedResponseListener = new UserReportsActivity.UserDefinedResponseListener(this, customEditText, reportParam);
            String subSql2 = reportParam.getSubSql();
            Intrinsics.checkNotNullExpressionValue(subSql2, "getSubSql(...)");
            userReportViewModel.runUserDefinedSQL(userDefinedResponseListener, subSql2, str);
            return;
        }
        showDialog(customEditText, reportParam);
    }
    public void showCalendar(CustomEditText control) {
        int i2;
        int i3;
        int i4;
        List emptyList;
        Intrinsics.checkNotNullParameter(control, "control");
        Context context = this.mContext;
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.proje.mobilesales.features.userreport.view.activity.UserReportsParametersActivity");
        ((UserReportsParametersActivity) context).hideKeyboard();
        if (!Intrinsics.areEqual(String.valueOf(control.getText()), "")) {
            List<String> split = new Regex("\\.").split(String.valueOf(control.getText()), 0);
            if (!split.isEmpty()) {
                ListIterator<String> listIterator = split.listIterator(split.size());
                while (listIterator.hasPrevious()) {
                    if (listIterator.previous().length() != 0) {
                        emptyList = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                        break;
                    }
                }
            }
            emptyList = CollectionsKt.emptyList();
            String[] strArr = (String[]) emptyList.toArray(new String[0]);
            i3 = Integer.parseInt(strArr[0]);
            i2 = Integer.parseInt(strArr[1]) - 1;
            i4 = Integer.parseInt(strArr[2]);
        } else {
            Calendar calendar = Calendar.getInstance();
            int i5 = calendar.get(1);
            i2 = calendar.get(2);
            i3 = calendar.get(5);
            i4 = i5;
        }
        int i6 = i3;
        int i7 = i2;
        Context context2 = this.mContext;
        Intrinsics.checkNotNull(context2, "null cannot be cast to non-null type com.proje.mobilesales.features.userreport.view.activity.UserReportsParametersActivity");
        DatePickerDialog datePickerDialog = new DatePickerDialog(context2, new DatePickerDialog.OnDateSetListener() { // from class: com.proje.mobilesales.features.userreport.adapter.UserReportsParametersRecyclerViewAdapterExternalSyntheticLambda6
            public void C2928x9194f082() {
            }

            public void onDateSet(DatePicker datePicker, int i8, int i9, int i10) {
                UserReportsParametersRecyclerViewAdapter.showCalendarlambda9(CustomEditText.this, datePicker, i8, i9, i10);
            }
        }, i4, i7, i6);
        Context context3 = this.mContext;
        Intrinsics.checkNotNull(context3, "null cannot be cast to non-null type com.proje.mobilesales.features.userreport.view.activity.UserReportsParametersActivity");
        datePickerDialog.setButton(-1, context3.getString(R.string.str_okey), datePickerDialog);
        Context context4 = this.mContext;
        Intrinsics.checkNotNull(context4, "null cannot be cast to non-null type com.proje.mobilesales.features.userreport.view.activity.UserReportsParametersActivity");
        datePickerDialog.setButton(-2, context4.getString(R.string.str_cancel), datePickerDialog);
        datePickerDialog.show();
    }
    public static void showCalendarlambda9(CustomEditText control, DatePicker datePicker, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(control, "control");
        String fillFormat = StringUtils.fillFormat(4, i2);
        String fillFormat2 = StringUtils.fillFormat(2, i3 + 1);
        control.setText(StringUtils.fillFormat(2, i4) + '.' + fillFormat2 + '.' + fillFormat);
    }
    private void showDialog(CustomEditText customEditText, ReportParam reportParam) {
        Context context = this.mContext;
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.proje.mobilesales.features.userreport.view.activity.UserReportsParametersActivity");
        ((UserReportsParametersActivity) context).hideKeyboard();
        if (customEditText.getTag() != null) {
            Object tag = customEditText.getTag();
            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type java.util.LinkedHashMap<kotlin.String, kotlin.String>{ kotlin.collections.TypeAliasesKt.LinkedHashMap<kotlin.String, kotlin.String> }");
            LinkedHashMap linkedHashMap = (LinkedHashMap) tag;
            int indexOf = (Intrinsics.areEqual(String.valueOf(customEditText.getText()), "") || Intrinsics.areEqual(customEditText.getStringTag(), "") || !linkedHashMap.containsKey(customEditText.getStringTag())) ? -1 : new ArrayList(linkedHashMap.keySet()).indexOf(customEditText.getStringTag());
            getMProgressDialogBuilder().dismiss();
            AlertDialogBuilder title = getMAlertDialogBuilder().setTitle(reportParam.getFieldLabel());
            Collection values = linkedHashMap.values();
            Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
            title.setSingleChoice((CharSequence[]) values.toArray(new CharSequence[0]), indexOf, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.userreport.adapter.UserReportsParametersRecyclerViewAdapterExternalSyntheticLambda7
                public final   LinkedHashMap f1;
                public final   ReportParam f2;

                {
                    this.f1 = r2;
                    this.f2 = r3;
                }

                public void onClick(DialogInterface dialogInterface, int i2) {
                    UserReportsParametersRecyclerViewAdapter.showDialoglambda10(customEditText, this.f1, this.f2, dialogInterface, i2);
                }
            }).create().show();
            return;
        }
        getMProgressDialogBuilder().dismiss();
    }
    public static void showDialoglambda10(CustomEditText customEditText, LinkedHashMap linkedHashMap, ReportParam reportParam, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(customEditText, "control");
        Intrinsics.checkNotNullParameter(linkedHashMap, "map");
        Intrinsics.checkNotNullParameter(reportParam, "property");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialog");
        Collection values = linkedHashMap.values();
        Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
        customEditText.setText(((String[]) values.toArray(new String[0]))[i2]);
        Set keySet = linkedHashMap.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet, "<get-keys>(...)");
        customEditText.setStringTag(((String[]) keySet.toArray(new String[0]))[i2]);
        Set keySet2 = linkedHashMap.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet2, "<get-keys>(...)");
        reportParam.setSelectedItemValue(((String[]) keySet2.toArray(new String[0]))[i2]);
        if (Intrinsics.areEqual(reportParam.getType(), "DynamicList")) {
            Collection values2 = linkedHashMap.values();
            Intrinsics.checkNotNullExpressionValue(values2, "<get-values>(...)");
            reportParam.setValues(((String[]) values2.toArray(new String[0]))[i2]);
        }
        dialogInterface.dismiss();
    }
    public void fillDialogData(ArrayList<GenericData> response, CustomEditText customEditText, ReportParam reportParam) {
        String substring;
        Intrinsics.checkNotNullParameter(response, "response");
        Intrinsics.checkNotNullParameter(customEditText, "_control");
        Intrinsics.checkNotNullParameter(reportParam, "property");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (!response.isEmpty()) {
            Iterator<GenericData> it = response.iterator();
            while (it.hasNext()) {
                GenericData next = it.next();
                String str = "";
                if (next.getGenericDataPrimitives().size() == 2) {
                    substring = next.getGenericDataPrimitives().get(1).getValue() == null ? "" : next.getGenericDataPrimitives().get(1).getValue().toString();
                } else if (next.getGenericDataPrimitives().size() == 1) {
                    substring = next.getGenericDataPrimitives().get(0).getValue().toString();
                } else {
                    int size = next.getGenericDataPrimitives().size();
                    String str2 = "";
                    for (int i2 = 1; i2 < size; i2++) {
                        String sb = str2 +
                                (next.getGenericDataPrimitives().get(i2).getValue() == null ? "" : next.getGenericDataPrimitives().get(i2).getValue() + " - ");
                        str2 = sb;
                    }
                    substring = str2.substring(0, StringsKt.lastIndexOf(str2, " - ", 0, false));
                    Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                }
                if (next.getGenericDataPrimitives().get(0).getValue() != null) {
                    str = next.getGenericDataPrimitives().get(0).getValue().toString();
                }
                linkedHashMap.put(str, substring);
            }
            customEditText.setTag(linkedHashMap);
        }
        showDialog(customEditText, reportParam);
    }
    public void showMessage(String str) {
        getMProgressDialogBuilder().dismiss();
        Toast.makeText(this.mContext, str, Toast.LENGTH_LONG).show();
    }

    private record UserDefinedResponseListener(WeakReference<UserReportsParametersRecyclerViewAdapter> mAdapter,
                                               CustomEditText control,
                                               ReportParam property) implements ResponseListener<BaseSelectResult<?>> {
            private UserDefinedResponseListener(UserReportsParametersRecyclerViewAdapter mAdapter, CustomEditText control, ReportParam property) {
                Intrinsics.checkNotNullParameter(control, "control");
                Intrinsics.checkNotNullParameter(property, "reportParam");
                this.control = control;
                this.property = property;
                this.mAdapter = new WeakReference<>(mAdapter);
            }

        public void onResponse(PrintSlipModel baseSelectResult) {
                Intrinsics.checkNotNull(baseSelectResult);
                if (!baseSelectResult.isSuccess()) {
                    if (this.mAdapter.get() != null) {
                        UserReportsParametersRecyclerViewAdapter userReportsParametersRecyclerViewAdapter = this.mAdapter.get();
                        Intrinsics.checkNotNull(userReportsParametersRecyclerViewAdapter);
                        if (userReportsParametersRecyclerViewAdapter.isAttached()) {
                            Log.d("AA", "onError: " + baseSelectResult.getErrorString());
                            UserReportsParametersRecyclerViewAdapter userReportsParametersRecyclerViewAdapter2 = this.mAdapter.get();
                            Intrinsics.checkNotNull(userReportsParametersRecyclerViewAdapter2);
                            userReportsParametersRecyclerViewAdapter2.showMessage(ContextUtils.getStringResource(R.string.str_error_report_query_could_not_be_executed));
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (this.mAdapter.get() != null) {
                    UserReportsParametersRecyclerViewAdapter userReportsParametersRecyclerViewAdapter3 = this.mAdapter.get();
                    Intrinsics.checkNotNull(userReportsParametersRecyclerViewAdapter3);
                    if (userReportsParametersRecyclerViewAdapter3.isAttached()) {
                        UserReportsParametersRecyclerViewAdapter userReportsParametersRecyclerViewAdapter4 = this.mAdapter.get();
                        Intrinsics.checkNotNull(userReportsParametersRecyclerViewAdapter4);
                        List<?> dataList = baseSelectResult.getDataList();
                        Intrinsics.checkNotNull(dataList, "null cannot be cast to non-null type java.util.ArrayList<com.proje.mobilesales.features.model.GenericData>{ kotlin.collections.TypeAliasesKt.ArrayList<com.proje.mobilesales.features.model.GenericData> }");
                        userReportsParametersRecyclerViewAdapter4.fillDialogData((ArrayList) dataList, this.control, this.property);
                    }
                }
            }

            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mAdapter.get() != null) {
                    UserReportsParametersRecyclerViewAdapter userReportsParametersRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(userReportsParametersRecyclerViewAdapter);
                    if (userReportsParametersRecyclerViewAdapter.isAttached()) {
                        Log.d("AA", "onError: " + errorMessage);
                        UserReportsParametersRecyclerViewAdapter userReportsParametersRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(userReportsParametersRecyclerViewAdapter2);
                        userReportsParametersRecyclerViewAdapter2.showMessage(errorMessage);
                    }
                }
            }
        }
    private LinkedHashMap<String, String> getList(String str, String str2) {
        List emptyList;
        List emptyList2;
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        List<String> split = new Regex("\\|").split(str, 0);
        if (!split.isEmpty()) {
            ListIterator<String> listIterator = split.listIterator(split.size());
            while (listIterator.hasPrevious()) {
                if (listIterator.previous().length() != 0) {
                    emptyList = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList = CollectionsKt.emptyList();
        String[] strArr = (String[]) emptyList.toArray(new String[0]);
        List<String> split2 = new Regex("\\|").split(str2, 0);
        if (!split2.isEmpty()) {
            ListIterator<String> listIterator2 = split2.listIterator(split2.size());
            while (listIterator2.hasPrevious()) {
                if (listIterator2.previous().length() != 0) {
                    emptyList2 = CollectionsKt.take(split2, listIterator2.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList2 = CollectionsKt.emptyList();
        String[] strArr2 = (String[]) emptyList2.toArray(new String[0]);
        int length = strArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            linkedHashMap.put(strArr[i2], strArr2[i2]);
        }
        return linkedHashMap;
    }
    public ReportParam getProperty(int i2) {
        return this.mParams.get(i2);
    }
    public boolean isAttached() {
        return this.mContext != null;
    }
    public Bundle saveState() {
        return new Bundle();
    }
    public void initDisplayOptions(Context context) {
        if (isAttached()) {
            notifyDataSetChanged();
        }
    }
    public int getItemCount() {
        return this.mParams.size();
    }
    public void setReportParams(List<? extends ReportParam> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator<? extends ReportParam> it = list.iterator();
        while (it.hasNext()) {
            this.mParams.add(it.next());
        }
        notifyDataSetChanged();
    }
    public static final class ItemViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout lnReportParams;
        private final AppCompatTextView txtCaption;
        private final CustomEditText txtValue;

        public ItemViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.ln_report_params);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.LinearLayout");
            this.lnReportParams = (LinearLayout) findViewById;
            View findViewById2 = itemView.findViewById(R.id.txtCaption);
            Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            this.txtCaption = (AppCompatTextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.txtValue);
            Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type com.proje.mobilesales.core.view.CustomEditText");
            this.txtValue = (CustomEditText) findViewById3;
        }

        public LinearLayout getLnReportParams() {
            return this.lnReportParams;
        }

        public AppCompatTextView getTxtCaption() {
            return this.txtCaption;
        }

        public CustomEditText getTxtValue() {
            return this.txtValue;
        }
    }
}
