package com.proje.mobilesales.features.sales.view.serialgroup;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.SeriLotUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.view.ScreenControl;
import com.proje.mobilesales.features.model.CheckSeriGroup;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PrimitiveCompanionObjects;
import kotlin.text.Regex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static com.proje.mobilesales.core.utils.AppUtils.createLayoutInflater;

public final class SalesSerialGroupRecyclerViewAdapter extends RecyclerView.Adapter<SalesSerialGroupViewHolder> {
    private AlertDialogBuilder<?> mAlertDialogBuilder;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ProgressDialogBuilder<?> mProgressDialogBuilder;
    private final ScreenControl screenControl = null;
    private ArrayList<CheckSeriGroup> mSerialGroupChecks = new ArrayList<>();
    private ArrayList<CheckSeriGroup> mDatas = new ArrayList<>();

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        return this.mAlertDialogBuilder;
    }

    public void setMAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        this.mAlertDialogBuilder = alertDialogBuilder;
    }

    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        return this.mProgressDialogBuilder;
    }

    public void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        this.mProgressDialogBuilder = progressDialogBuilder;
    }

    public ArrayList<CheckSeriGroup> getMSerialGroupChecks() {
        return this.mSerialGroupChecks;
    }

    public void setMSerialGroupChecks(ArrayList<CheckSeriGroup> arrayList) {
        this.mSerialGroupChecks = arrayList;
    }

    private LayoutInflater getMLayoutInflater() {
        return this.mLayoutInflater;
    }

    private void setMLayoutInflater(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
    }

    private Context getMContext() {
        return this.mContext;
    }

    private void setMContext(Context context) {
        this.mContext = context;
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
        ArrayList<CheckSeriGroup> arrayList = this.mSerialGroupChecks;
        Intrinsics.checkNotNull(arrayList);
        arrayList.clear();
        Context context2 = this.mContext;
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(context2, (BaseInjectableActivity) activity);
        Context context3 = this.mContext;
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilder = new AlertDialogBuilder.Impl(context3, (BaseInjectableActivity) activity2);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        int childCount = recyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
        }
        super.onDetachedFromRecyclerView(recyclerView);
        this.mContext = null;
    }

    public SalesSerialGroupViewHolder onCreateViewHolder(ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater layoutInflater = this.mLayoutInflater;
        Intrinsics.checkNotNull(layoutInflater);
        View inflate = layoutInflater.inflate(R.layout.item_serial_group, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new SalesSerialGroupViewHolder(inflate);
    }

    public void onBindViewHolder(SalesSerialGroupViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        bindData(holder, getProperty(i2), i2);
    }

    public CheckSeriGroup getProperty(int i2) {
        CheckSeriGroup checkSeriGroup = this.mDatas.get(i2);
        Intrinsics.checkNotNullExpressionValue(checkSeriGroup, "get(...)");
        return checkSeriGroup;
    }

    public void bindData(final SalesSerialGroupViewHolder holder, final CheckSeriGroup data, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(data, "data");
        holder.getTxt_SBegNoVal().setText(data.getGrpBegCode());
        holder.getTxt_SEndNoVal().setText(data.getGrpEndCode());
        holder.getTxt_AmountVal().setText(String.valueOf(data.getAvailableAmount()));
        holder.getTxt_UnitVal().setText(data.getUnit());
        holder.getDedt_ExpDate().setText(data.getExpDate());
        holder.getEdt_SBegNo().setText(data.getGrpBegCode());
        if (data.isChecked()) {
            holder.getEdt_Amount().setText(String.valueOf(data.getUsedAmount()));
            holder.getEdt_SBegNo().setText(data.getUsedGrpBegCode());
            serialGroupChecksAction(data, false);
        } else {
            holder.getEdt_Amount().setText("");
        }
        holder.getChk_SeriGrup().setChecked(data.isChecked());
        holder.getEdt_SBegNo().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                SalesSerialGroupRecyclerViewAdapter.bindDatalambda0(SalesSerialGroupViewHolder.this, this, data, view, z);
            }
        });
        holder.getEdt_Amount().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                SalesSerialGroupRecyclerViewAdapter.bindDatalambda1(SalesSerialGroupViewHolder.this, this, data, view, z);
            }
        });
        holder.getChk_SeriGrup().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SalesSerialGroupRecyclerViewAdapter.bindDatalambda2(SalesSerialGroupRecyclerViewAdapter.this, holder, data, compoundButton, z);
            }
        });
    }

    public static void bindDatalambda0(SalesSerialGroupViewHolder holder, SalesSerialGroupRecyclerViewAdapter this0, CheckSeriGroup data, View view, boolean z) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(data, "data");
        if (z || holder.getChk_SeriGrup().isChecked()) {
            this0.replaceData(data, holder);
        }
    }

    public static void bindDatalambda1(SalesSerialGroupViewHolder holder, SalesSerialGroupRecyclerViewAdapter this0, CheckSeriGroup data, View view, boolean z) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(data, "data");
        if (z || holder.getChk_SeriGrup().isChecked()) {
            this0.replaceData(data, holder);
        }
    }

    public static void bindDatalambda2(SalesSerialGroupRecyclerViewAdapter this0, SalesSerialGroupViewHolder holder, CheckSeriGroup data, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(data, "data");
        this0.checkChange(z, holder, data);
    }

    public void replaceData(CheckSeriGroup data, SalesSerialGroupViewHolder holder) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(holder, "holder");
        data.setUsedGrpBegCode(holder.getEdt_SBegNo().getText().toString());
        if (Intrinsics.areEqual(holder.getEdt_Amount().getText().toString(), "")) {
            return;
        }
        data.setUsedAmount(Double.parseDouble(holder.getEdt_Amount().getText().toString()));
        String usedGrpBegCode = data.getUsedGrpBegCode();
        Intrinsics.checkNotNullExpressionValue(usedGrpBegCode, "getUsedGrpBegCode(...)");
        String grpBegCode = data.getGrpBegCode();
        Intrinsics.checkNotNullExpressionValue(grpBegCode, "getGrpBegCode(...)");
        String grpEndCode = data.getGrpEndCode();
        Intrinsics.checkNotNullExpressionValue(grpEndCode, "getGrpEndCode(...)");
        data.setUsedGrpEndCode(generateEndNumberFromStart(usedGrpBegCode, grpBegCode, grpEndCode, data.getUsedAmount()));
    }

    public void checkChange(boolean z, final SalesSerialGroupViewHolder holder, final CheckSeriGroup data) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(data, "data");
        if (z) {
            if (!SeriLotUtils.checkStartNumberInRange(holder.getEdt_SBegNo().getText().toString(), holder.getTxt_SBegNoVal().getText().toString(), holder.getTxt_SEndNoVal().getText().toString())) {
                AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilder;
                Intrinsics.checkNotNull(alertDialogBuilder);
                alertDialogBuilder.setMessage(R.string.exp_87_error_entered_serial_group_is_not_available).setPositiveButton(R.string.str_okey, null).show();
            } else if (Intrinsics.areEqual(holder.getEdt_Amount().getText().toString(), "")) {
                AlertDialogBuilder<?> alertDialogBuilder2 = this.mAlertDialogBuilder;
                Intrinsics.checkNotNull(alertDialogBuilder2);
                alertDialogBuilder2.setMessage(R.string.exp_86_error_amount_can_not_be_zero).setPositiveButton(R.string.str_okey, null).show();
            } else if (Double.parseDouble(holder.getTxt_AmountVal().getText().toString()) < Double.parseDouble(holder.getEdt_Amount().getText().toString())) {
                AlertDialogBuilder<?> alertDialogBuilder3 = this.mAlertDialogBuilder;
                Intrinsics.checkNotNull(alertDialogBuilder3);
                alertDialogBuilder3.setMessage(R.string.exp_88_error_entered_amount_is_not_appropriate).setPositiveButton(R.string.str_okey, null).show();
            } else {
                replaceData(data, holder);
                data.setChecked(true);
                serialGroupChecksAction(data, false);
                return;
            }
            holder.getChk_SeriGrup().setOnCheckedChangeListener(null);
            holder.getChk_SeriGrup().setChecked(false);
            holder.getChk_SeriGrup().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                    SalesSerialGroupRecyclerViewAdapter.checkChangelambda3(SalesSerialGroupRecyclerViewAdapter.this, holder, data, compoundButton, z2);
                }
            });
            return;
        }
        serialGroupChecksAction(data, true);
        data.setChecked(false);
    }
    public static void checkChangelambda3(SalesSerialGroupRecyclerViewAdapter this0, SalesSerialGroupViewHolder holder, CheckSeriGroup data, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(data, "data");
        this0.checkChange(z, holder, data);
    }

    public void serialGroupChecksAction(CheckSeriGroup checkSeriGroup, boolean z) {
        int i2;
        Intrinsics.checkNotNullParameter(checkSeriGroup, "checkSeriGroup");
        int i3 = -1;
        if (z) {
            ArrayList<CheckSeriGroup> arrayList = this.mSerialGroupChecks;
            Intrinsics.checkNotNull(arrayList);
            if (arrayList.size() > 0) {
                ArrayList<CheckSeriGroup> arrayList2 = this.mSerialGroupChecks;
                Intrinsics.checkNotNull(arrayList2);
                Iterator<CheckSeriGroup> it = arrayList2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    CheckSeriGroup next = it.next();
                    if (next.getLogicalRef() == checkSeriGroup.getLogicalRef() && SeriLotUtils.checkStartNumberInRange(next.getGrpBegCode(), checkSeriGroup.getGrpBegCode(), checkSeriGroup.getGrpEndCode())) {
                        ArrayList<CheckSeriGroup> arrayList3 = this.mSerialGroupChecks;
                        Intrinsics.checkNotNull(arrayList3);
                        i3 = arrayList3.indexOf(next);
                        break;
                    }
                }
                ArrayList<CheckSeriGroup> arrayList4 = this.mSerialGroupChecks;
                Intrinsics.checkNotNull(arrayList4);
                arrayList4.remove(i3);
                return;
            }
            return;
        }
        ArrayList<CheckSeriGroup> arrayList5 = this.mSerialGroupChecks;
        Intrinsics.checkNotNull(arrayList5);
        Iterator<CheckSeriGroup> it2 = arrayList5.iterator();
        while (true) {
            if (!it2.hasNext()) {
                i2 = -1;
                break;
            }
            CheckSeriGroup next2 = it2.next();
            if (next2.getLogicalRef() == checkSeriGroup.getLogicalRef() && SeriLotUtils.checkStartNumberInRange(next2.getGrpBegCode(), checkSeriGroup.getGrpBegCode(), checkSeriGroup.getGrpEndCode())) {
                ArrayList<CheckSeriGroup> arrayList6 = this.mSerialGroupChecks;
                Intrinsics.checkNotNull(arrayList6);
                i2 = arrayList6.indexOf(next2);
                break;
            }
        }
        if (i2 == -1) {
            ArrayList<CheckSeriGroup> arrayList7 = this.mSerialGroupChecks;
            Intrinsics.checkNotNull(arrayList7);
            arrayList7.add(checkSeriGroup);
        }
    }

    private String generateEndNumberFromStart(String str, String str2, String str3, double d2) {
        try {
            int length = str2.length() - 1;
            while (true) {
                if (-1 >= length) {
                    length = 0;
                    break;
                }
                if (Character.isLetter(str2.charAt(length))) {
                    break;
                }
                length--;
            }
            int i2 = length + 1;
            String substring = str3.substring(i2);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            int length2 = substring.length();
            String substring2 = str.substring(i2);
            Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
            String substring3 = str.substring(0, i2);
            Intrinsics.checkNotNullExpressionValue(substring3, "substring(...)");
            int parseInt = Integer.parseInt(substring2) + Integer.parseInt(String.valueOf(Math.round(d2 - 1)));
            PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
            String format = String.format("%s%s", Arrays.copyOf(new Object[]{substring3, new Regex(" ").replace(StringUtils.padRight(Integer.toString(parseInt), length2), "0")}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return format;
        } catch (Exception e2) {
            Log.e("SeriGroup", "generateEnd", e2);
            return "";
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i2) {
        return (this.mDatas.get(i2).getGrpBegCode() + this.mDatas.get(i2).getGrpEndCode()).hashCode();
    }

    public int getItemCount() {
        return this.mDatas.size();
    }

    public boolean isAttached() {
        return this.mContext != null;
    }

    public void initDisplayOptions(Context context) {
        if (isAttached()) {
            notifyDataSetChanged();
        }
    }

    public int getCheckedGroupListSize() {
        ArrayList<CheckSeriGroup> arrayList = this.mSerialGroupChecks;
        Intrinsics.checkNotNull(arrayList);
        return arrayList.size();
    }

    public ArrayList<Serilot> getCheckedGroupList() {
        ArrayList<Serilot> arrayList = new ArrayList<>();
        ArrayList<CheckSeriGroup> arrayList2 = this.mSerialGroupChecks;
        if (arrayList2 == null) {
            return arrayList;
        }
        Intrinsics.checkNotNull(arrayList2);
        Iterator<CheckSeriGroup> it = arrayList2.iterator();
        while (it.hasNext()) {
            CheckSeriGroup next = it.next();
            Serilot serilot = new Serilot(0, 0, 0, null, 0.0d, null, null, null, 0.0d, null, null, null, 0, 0, 0, 0, 65535, null);
            serilot.f1277id = next.getId();
            serilot.logicalRef = next.getLogicalRef();
            serilot.amount = next.getUsedAmount();
            serilot.code = next.getSeriLotNo();
            serilot.expDate = next.getExpDate();
            String usedGrpBegCode = next.getUsedGrpBegCode();
            Intrinsics.checkNotNullExpressionValue(usedGrpBegCode, "getUsedGrpBegCode(...)");
            serilot.grpBegCode = usedGrpBegCode;
            String usedGrpEndCode = next.getUsedGrpEndCode();
            Intrinsics.checkNotNullExpressionValue(usedGrpEndCode, "getUsedGrpEndCode(...)");
            serilot.grpEndCode = usedGrpEndCode;
            serilot.unit = next.getUnit();
            serilot.reAmount = next.getRemAmount();
            serilot.detailRef = next.getDetailRef();
            String name = next.getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            serilot.name = name;
            String locationCode = next.getLocationCode();
            Intrinsics.checkNotNullExpressionValue(locationCode, "getLocationCode(...)");
            serilot.locationCode = locationCode;
            serilot.stTransRef = next.getStTransRef();
            serilot.sourceUnitRef = next.getSourceUnitRef();
            arrayList.add(serilot);
        }
        return arrayList;
    }

    public void setData(ArrayList<CheckSeriGroup> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return;
        }
        this.mDatas = arrayList;
        notifyDataSetChanged();
    }
}
