package com.proje.mobilesales.features.sales.view.order;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.databinding.SalesorderListItemBinding;
import com.proje.mobilesales.features.dbmodel.ManagerOrder;
import com.proje.mobilesales.features.sales.repository.SalesOrderRepository;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static com.proje.mobilesales.core.utils.AppUtils.createLayoutInflater;

public final class SalesOrderListRecyclerViewAdapter extends RecyclerView.Adapter<SalesOrderListViewHolder> {
    private AlertDialogBuilder<?> mAlertDialogBuilder;
    private Context mContext;
    private ArrayList<ManagerOrder> mDatas;
    private boolean mDetail;
    private LayoutInflater mLayoutInflater;
    private boolean mMyOrder;
    private ArrayList<ManagerOrder> mSelectedOrders;
    private String mTmpFicheNo;
    private final SalesOrderRepository repository;
    private final SalesOrderViewModel viewModel;

    public SalesOrderListRecyclerViewAdapter() {
        SalesOrderRepository salesOrderRepository = new SalesOrderRepository();
        this.repository = salesOrderRepository;
        this.viewModel = new SalesOrderViewModel(salesOrderRepository);
        this.mTmpFicheNo = "";
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        return this.mAlertDialogBuilder;
    }

    public void setMAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        this.mAlertDialogBuilder = alertDialogBuilder;
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
        this.mContext = recyclerView.getContext();
        this.mSelectedOrders = new ArrayList<>();
        Context context = this.mContext;
        if (context instanceof BaseInjectableActivity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
            ((BaseInjectableActivity) context).getActivityComponent().inject(this);
        }
        this.mLayoutInflater = createLayoutInflater(this.mContext);
        Context context2 = this.mContext;
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilder = new AlertDialogBuilder.Impl(context2, (BaseInjectableActivity) activity);
    }
 
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        int childCount = recyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
        }
        super.onDetachedFromRecyclerView(recyclerView);
        this.mContext = null;
        this.mSelectedOrders = null;
    }
 
    public SalesOrderListViewHolder onCreateViewHolder(ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        SalesorderListItemBinding inflate = SalesorderListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new SalesOrderListViewHolder(inflate);
    }

    public ManagerOrder getProperty(int i2) {
        ArrayList<ManagerOrder> arrayList = this.mDatas;
        Intrinsics.checkNotNull(arrayList);
        ManagerOrder managerOrder = arrayList.get(i2);
        Intrinsics.checkNotNullExpressionValue(managerOrder, "get(...)");
        return managerOrder;
    }
 
    public void onBindViewHolder(SalesOrderListViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        bindData(holder, getProperty(i2), i2);
    }

    public void bindData(final SalesOrderListViewHolder holder, ManagerOrder data, int i2) {
        List emptyList;
        List emptyList2;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(data, "data");
        if (!this.mDetail) {
            holder.getLn_fiche_container().setVisibility(View.VISIBLE);
            holder.getLn_fiche_detail_container().setVisibility(View.GONE);
            holder.getTvFicheNo().setText(data.getFicheNo());
            if (this.viewModel.erpType() == ErpType.NETSIS) {
                holder.getTvDateTime().setText(DateAndTimeUtils.convertStringDate(data.getDate(), "yyyy-MM-dd HH:mm:ss", "dd.MM.yyyy HH:mm:ss"));
            } else {
                TextView tvDateTime = holder.getTvDateTime();
                StringBuilder sb = new StringBuilder();
                String date = data.getDate();
                Intrinsics.checkNotNullExpressionValue(date, "getDate(...)");
                List<String> split = new Regex(ExifInterface.GPS_DIRECTION_TRUE).split(date, 0);
                if (!split.isEmpty()) {
                    ListIterator<String> listIterator = split.listIterator(split.size());
                    while (listIterator.hasPrevious()) {
                        if (listIterator.previous().length() != 0) {
                            emptyList2 = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                            break;
                        }
                    }
                }
                emptyList2 = CollectionsKt.emptyList();
                sb.append(DateAndTimeUtils.convertYMDToDMY(((String[]) emptyList2.toArray(new String[0]))[0]));
                sb.append(' ');
                sb.append(DateAndTimeUtils.intToNowTimeLogo(data.getTime()));
                tvDateTime.setText(sb.toString());
            }
            if (!this.mMyOrder || data.getsCode() == null) {
                holder.getLn_salesman().setVisibility(View.GONE);
            } else {
                holder.getTvSalesMan().setText(data.getsCode() + ' ' + data.getsName());
            }
        } else {
            holder.getLn_fiche_container().setVisibility(View.GONE);
            holder.getLn_fiche_detail_container().setVisibility(View.VISIBLE);
            if (this.mTmpFicheNo.length() == 0 || (this.mTmpFicheNo.length() > 0 && !Intrinsics.areEqual(this.mTmpFicheNo, data.getFicheNo()))) {
                String ficheNo = data.getFicheNo();
                Intrinsics.checkNotNullExpressionValue(ficheNo, "getFicheNo(...)");
                this.mTmpFicheNo = ficheNo;
                holder.getLn_salesorder_detailheader().setVisibility(View.VISIBLE);
                holder.getTvDetailHeader_FicheNo().setText(data.getFicheNo());
                if (this.viewModel.erpType() == ErpType.NETSIS) {
                    holder.getTvDetailHeader_OrderDate().setText(DateAndTimeUtils.convertStringDate(data.getDate(), "yyyy-MM-dd HH:mm:ss", "dd.MM.yyyy HH:mm:ss"));
                } else {
                    TextView tvDetailHeader_OrderDate = holder.getTvDetailHeader_OrderDate();
                    StringBuilder sb2 = new StringBuilder();
                    String date2 = data.getDate();
                    Intrinsics.checkNotNullExpressionValue(date2, "getDate(...)");
                    List<String> split2 = new Regex(ExifInterface.GPS_DIRECTION_TRUE).split(date2, 0);
                    if (!split2.isEmpty()) {
                        ListIterator<String> listIterator2 = split2.listIterator(split2.size());
                        while (listIterator2.hasPrevious()) {
                            if (listIterator2.previous().length() != 0) {
                                emptyList = CollectionsKt.take(split2, listIterator2.nextIndex() + 1);
                                break;
                            }
                        }
                    }
                    emptyList = CollectionsKt.emptyList();
                    sb2.append(DateAndTimeUtils.convertYMDToDMY(((String[]) emptyList.toArray(new String[0]))[0]));
                    sb2.append(' ');
                    sb2.append(DateAndTimeUtils.intToNowTimeLogo(data.getTime()));
                    tvDetailHeader_OrderDate.setText(sb2.toString());
                }
            } else {
                holder.getLn_salesorder_detailheader().setVisibility(View.GONE);
            }
            holder.getTvStokCode().setText(data.getStockCode());
            holder.getTvStockName().setText(data.getStockName());
            holder.getTvAmount().setText(Double.toString(data.getAvailableAMOUNT()));
        }
        holder.getTvFicheNo().setTag(data);
        holder.getChbOrder().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { 
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SalesOrderListRecyclerViewAdapter.bindDatalambda2(SalesOrderListRecyclerViewAdapter.this, holder, compoundButton, z);
            }
        });
        holder.getLn_salesorderlist_container().setOnClickListener(new View.OnClickListener() { 
            public void onClick(View view) {
                SalesOrderListRecyclerViewAdapter.bindDatalambda3(SalesOrderListViewHolder.this, view);
            }
        });
    }
 
    public static void bindDatalambda2(SalesOrderListRecyclerViewAdapter this0, SalesOrderListViewHolder holder, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (z) {
            ArrayList<ManagerOrder> arrayList = this0.mSelectedOrders;
            Intrinsics.checkNotNull(arrayList);
            Object tag = holder.getTvFicheNo().getTag();
            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type com.proje.mobilesales.features.dbmodel.ManagerOrder");
            arrayList.add((ManagerOrder) tag);
            return;
        }
        ArrayList<ManagerOrder> arrayList2 = this0.mSelectedOrders;
        Intrinsics.checkNotNull(arrayList2);
        Object tag2 = holder.getTvFicheNo().getTag();
        Intrinsics.checkNotNull(tag2, "null cannot be cast to non-null type com.proje.mobilesales.features.dbmodel.ManagerOrder");
        arrayList2.remove((ManagerOrder) tag2);
    }
 
    public static void bindDatalambda3(SalesOrderListViewHolder holder, View view) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getChbOrder().setChecked(!holder.getChbOrder().isChecked());
    }
 
    public int getItemCount() {
        ArrayList<ManagerOrder> arrayList = this.mDatas;
        Intrinsics.checkNotNull(arrayList);
        return arrayList.size();
    }

    public boolean isAttached() {
        return this.mContext != null;
    }

    public void initDisplayOptions(Context context) {
        if (isAttached()) {
            notifyDataSetChanged();
        }
    }

    public void setData(ArrayList<ManagerOrder> arrayList) {
        this.mDatas = arrayList;
        notifyDataSetChanged();
    }

    public ArrayList<String> getSelectedOrders() {
        String valueOf = "";
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<ManagerOrder> arrayList2 = this.mSelectedOrders;
        Intrinsics.checkNotNull(arrayList2);
        Iterator<ManagerOrder> it = arrayList2.iterator();
        while (it.hasNext()) {
            ManagerOrder next = it.next();
            if (this.mDetail) {
                arrayList.add(next.getOrderDetailRef());
            } else {
                if (this.viewModel.erpType() == ErpType.GO || this.viewModel.erpType() == ErpType.TIGER) {
                    valueOf = String.valueOf(next.getLogicalRef());
                } else if (this.viewModel.erpType() == ErpType.NETSIS) {
                    valueOf = next.getcCode2();
                }
                arrayList.add(valueOf);
            }
        }
        return arrayList;
    }

    public void setMyOrder(boolean z) {
        this.mMyOrder = z;
    }

    public void setOrderDetail(boolean z) {
        this.mDetail = z;
    }
}
