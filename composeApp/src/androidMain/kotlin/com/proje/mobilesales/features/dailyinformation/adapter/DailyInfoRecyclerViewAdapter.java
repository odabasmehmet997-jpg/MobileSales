package com.proje.mobilesales.features.dailyinformation.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.interfaces.OnOptionsMenu;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.zakariya.stickyheaders.SectioningAdapter;
 
public final class DailyInfoRecyclerViewAdapter extends SectioningAdapter {
    private List<DailyInfo> dailyInfoList;
    private final Context mContext;
    private OnOptionsMenu onOptionsMenu;
    private final Set<String> sectionTypes;
    private final List<Section> sections;
 
    public boolean doesSectionHaveFooter(int i2) {
        return false;
    }
 
    public boolean doesSectionHaveHeader(int i2) {
        return true;
    }

    public DailyInfoRecyclerViewAdapter(Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.mContext = mContext;
        this.sections = new ArrayList();
        this.sectionTypes = new HashSet();
    }

    public OnOptionsMenu getOnOptionsMenu() {
        return this.onOptionsMenu;
    }

    public void setOnOptionsMenu(OnOptionsMenu onOptionsMenu) {
        this.onOptionsMenu = onOptionsMenu;
    }

    /* compiled from: DailyInfoRecyclerViewAdapter.kt */
    public static final class Section {
        private ArrayList<DailyInfo> dailyInfos = new ArrayList<>();

        public ArrayList<DailyInfo> getDailyInfos() {
            return this.dailyInfos;
        }

        public void setDailyInfos(ArrayList<DailyInfo> arrayList) {
            Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
            this.dailyInfos = arrayList;
        }
    } 
    public final class ItemViewHolder extends SectioningAdapter.ItemViewHolder implements View.OnLongClickListener {
        private ImageView imgFicheTranfer;
        private LinearLayout lnCustomerLayout;
        private LinearLayout lnDebitedOrVisitReason;
        private LinearLayout lnReceiptInfo;
        private LinearLayout lnTotalLayout;
        final DailyInfoRecyclerViewAdapter this0;
        private AppCompatTextView tvAccountNumber;
        private AppCompatTextView tvAccountNumberNote;
        private AppCompatTextView tvBranch;
        private AppCompatTextView tvBranchNote;
        private AppCompatTextView tvCustomerCode;
        private AppCompatTextView tvCustomerName;
        private AppCompatTextView tvDebitedOrVisitReason;
        private AppCompatTextView tvDebitedOrVisitReasonExplanation;
        private AppCompatTextView tvNote;
        private AppCompatTextView tvNoteColon;
        private AppCompatTextView tvNoteExplanation;
        private AppCompatTextView tvShipAddressOrBank;
        private AppCompatTextView tvTime;
        private AppCompatTextView tvTotal;
        private AppCompatTextView tvTotalColon;
        private AppCompatTextView tvTotalExplanation;

        public ItemViewHolder(DailyInfoRecyclerViewAdapter dailyInfoRecyclerViewAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this0 = dailyInfoRecyclerViewAdapter;
            View findViewById = itemView.findViewById(R.id.tv_time);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            this.tvTime = (AppCompatTextView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.tv_customerName);
            Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            this.tvCustomerName = (AppCompatTextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.tv_customerCode);
            Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            this.tvCustomerCode = (AppCompatTextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.tv_shipAddressOrBankExplanation);
            Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            this.tvShipAddressOrBank = (AppCompatTextView) findViewById4;
            View findViewById5 = itemView.findViewById(R.id.tv_branch);
            Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            this.tvBranch = (AppCompatTextView) findViewById5;
            View findViewById6 = itemView.findViewById(R.id.tv_branchNote);
            Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            this.tvBranchNote = (AppCompatTextView) findViewById6;
            View findViewById7 = itemView.findViewById(R.id.tv_accountNumber);
            Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            this.tvAccountNumber = (AppCompatTextView) findViewById7;
            View findViewById8 = itemView.findViewById(R.id.tv_accountNumberNote);
            Intrinsics.checkNotNull(findViewById8, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            this.tvAccountNumberNote = (AppCompatTextView) findViewById8;
            View findViewById9 = itemView.findViewById(R.id.tv_debitedOrVisitReason);
            Intrinsics.checkNotNull(findViewById9, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            this.tvDebitedOrVisitReason = (AppCompatTextView) findViewById9;
            View findViewById10 = itemView.findViewById(R.id.tv_debitedOrVisitReasonExplanation);
            Intrinsics.checkNotNull(findViewById10, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            this.tvDebitedOrVisitReasonExplanation = (AppCompatTextView) findViewById10;
            View findViewById11 = itemView.findViewById(R.id.tv_note);
            Intrinsics.checkNotNull(findViewById11, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            this.tvNote = (AppCompatTextView) findViewById11;
            View findViewById12 = itemView.findViewById(R.id.tv_noteColon);
            Intrinsics.checkNotNull(findViewById12, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            this.tvNoteColon = (AppCompatTextView) findViewById12;
            View findViewById13 = itemView.findViewById(R.id.tv_noteExplanation);
            Intrinsics.checkNotNull(findViewById13, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            this.tvNoteExplanation = (AppCompatTextView) findViewById13;
            View findViewById14 = itemView.findViewById(R.id.tv_total);
            Intrinsics.checkNotNull(findViewById14, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            this.tvTotal = (AppCompatTextView) findViewById14;
            View findViewById15 = itemView.findViewById(R.id.tv_totalColon);
            Intrinsics.checkNotNull(findViewById15, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            this.tvTotalColon = (AppCompatTextView) findViewById15;
            View findViewById16 = itemView.findViewById(R.id.tv_totalExplanation);
            Intrinsics.checkNotNull(findViewById16, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            this.tvTotalExplanation = (AppCompatTextView) findViewById16;
            View findViewById17 = itemView.findViewById(R.id.ln_receiptInfo);
            Intrinsics.checkNotNull(findViewById17, "null cannot be cast to non-null type android.widget.LinearLayout");
            this.lnReceiptInfo = (LinearLayout) findViewById17;
            View findViewById18 = itemView.findViewById(R.id.ln_debitedOrVisitReason);
            Intrinsics.checkNotNull(findViewById18, "null cannot be cast to non-null type android.widget.LinearLayout");
            this.lnDebitedOrVisitReason = (LinearLayout) findViewById18;
            View findViewById19 = itemView.findViewById(R.id.ln_totalLayout);
            Intrinsics.checkNotNull(findViewById19, "null cannot be cast to non-null type android.widget.LinearLayout");
            this.lnTotalLayout = (LinearLayout) findViewById19;
            View findViewById20 = itemView.findViewById(R.id.ln_customerInfo);
            Intrinsics.checkNotNull(findViewById20, "null cannot be cast to non-null type android.widget.LinearLayout");
            this.lnCustomerLayout = (LinearLayout) findViewById20;
            View findViewById21 = itemView.findViewById(R.id.img_fiche_transfer);
            Intrinsics.checkNotNull(findViewById21, "null cannot be cast to non-null type android.widget.ImageView");
            this.imgFicheTranfer = (ImageView) findViewById21;
            itemView.setOnLongClickListener(this);
        }

        public AppCompatTextView getTvTime() {
            return this.tvTime;
        }

        public void setTvTime(AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvTime = appCompatTextView;
        }

        public AppCompatTextView getTvCustomerName() {
            return this.tvCustomerName;
        }

        public void setTvCustomerName(AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvCustomerName = appCompatTextView;
        }

        public AppCompatTextView getTvCustomerCode() {
            return this.tvCustomerCode;
        }

        public void setTvCustomerCode(AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvCustomerCode = appCompatTextView;
        }

        public AppCompatTextView getTvShipAddressOrBank() {
            return this.tvShipAddressOrBank;
        }

        public void setTvShipAddressOrBank(AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvShipAddressOrBank = appCompatTextView;
        }

        public AppCompatTextView getTvBranch() {
            return this.tvBranch;
        }

        public void setTvBranch(AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvBranch = appCompatTextView;
        }

        public AppCompatTextView getTvBranchNote() {
            return this.tvBranchNote;
        }

        public void setTvBranchNote(AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvBranchNote = appCompatTextView;
        }

        public AppCompatTextView getTvAccountNumber() {
            return this.tvAccountNumber;
        }

        public void setTvAccountNumber(AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvAccountNumber = appCompatTextView;
        }

        public AppCompatTextView getTvAccountNumberNote() {
            return this.tvAccountNumberNote;
        }

        public void setTvAccountNumberNote(AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvAccountNumberNote = appCompatTextView;
        }

        public AppCompatTextView getTvDebitedOrVisitReason() {
            return this.tvDebitedOrVisitReason;
        }

        public void setTvDebitedOrVisitReason(AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvDebitedOrVisitReason = appCompatTextView;
        }

        public AppCompatTextView getTvDebitedOrVisitReasonExplanation() {
            return this.tvDebitedOrVisitReasonExplanation;
        }

        public void setTvDebitedOrVisitReasonExplanation(AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvDebitedOrVisitReasonExplanation = appCompatTextView;
        }

        public AppCompatTextView getTvNote() {
            return this.tvNote;
        }

        public void setTvNote(AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvNote = appCompatTextView;
        }

        public AppCompatTextView getTvNoteColon() {
            return this.tvNoteColon;
        }

        public void setTvNoteColon(AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvNoteColon = appCompatTextView;
        }

        public AppCompatTextView getTvNoteExplanation() {
            return this.tvNoteExplanation;
        }

        public void setTvNoteExplanation(AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvNoteExplanation = appCompatTextView;
        }

        public AppCompatTextView getTvTotal() {
            return this.tvTotal;
        }

        public void setTvTotal(AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvTotal = appCompatTextView;
        }

        public AppCompatTextView getTvTotalColon() {
            return this.tvTotalColon;
        }

        public void setTvTotalColon(AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvTotalColon = appCompatTextView;
        }

        public AppCompatTextView getTvTotalExplanation() {
            return this.tvTotalExplanation;
        }

        public void setTvTotalExplanation(AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvTotalExplanation = appCompatTextView;
        }

        public LinearLayout getLnReceiptInfo() {
            return this.lnReceiptInfo;
        }

        public void setLnReceiptInfo(LinearLayout linearLayout) {
            Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
            this.lnReceiptInfo = linearLayout;
        }

        public LinearLayout getLnDebitedOrVisitReason() {
            return this.lnDebitedOrVisitReason;
        }

        public void setLnDebitedOrVisitReason(LinearLayout linearLayout) {
            Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
            this.lnDebitedOrVisitReason = linearLayout;
        }

        public LinearLayout getLnTotalLayout() {
            return this.lnTotalLayout;
        }

        public void setLnTotalLayout(LinearLayout linearLayout) {
            Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
            this.lnTotalLayout = linearLayout;
        }

        public LinearLayout getLnCustomerLayout() {
            return this.lnCustomerLayout;
        }

        public void setLnCustomerLayout(LinearLayout linearLayout) {
            Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
            this.lnCustomerLayout = linearLayout;
        }

        public ImageView getImgFicheTranfer() {
            return this.imgFicheTranfer;
        }

        public void setImgFicheTranfer(ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.imgFicheTranfer = imageView;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View v) {
            Intrinsics.checkNotNullParameter(v, "v");
            DailyInfoRecyclerViewAdapter dailyInfoRecyclerViewAdapter = this.this0;
            String sb = "onLongClick: " +
                    dailyInfoRecyclerViewAdapter.getPositionOfItemInSection(dailyInfoRecyclerViewAdapter.getSectionForAdapterPosition(getAdapterPosition()), getAdapterPosition());
            Log.i("POSITION", sb);
            OnOptionsMenu onOptionsMenu = this.this0.getOnOptionsMenu();
            Intrinsics.checkNotNull(onOptionsMenu);
            DailyInfoRecyclerViewAdapter dailyInfoRecyclerViewAdapter2 = this.this0;
            onOptionsMenu.chooseOptions(dailyInfoRecyclerViewAdapter2.getPositionOfItemInSection(dailyInfoRecyclerViewAdapter2.getSectionForAdapterPosition(getAdapterPosition()), getAdapterPosition()));
            return false;
        }
    }

    public void getOptions(OnOptionsMenu onOptionsMenu) {
        this.onOptionsMenu = onOptionsMenu;
    }

    /* compiled from: DailyInfoRecyclerViewAdapter.kt */
    public final class HeaderViewHolder extends SectioningAdapter.HeaderViewHolder {
        final DailyInfoRecyclerViewAdapter this0;
        private TextView titleTextView;

        
        public HeaderViewHolder(DailyInfoRecyclerViewAdapter dailyInfoRecyclerViewAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this0 = dailyInfoRecyclerViewAdapter;
            View findViewById = itemView.findViewById(R.id.titleTextView);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
            this.titleTextView = (TextView) findViewById;
        }

        public TextView getTitleTextView() {
            return this.titleTextView;
        }

        public void setTitleTextView(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.titleTextView = textView;
        }
    }

    private Unit getDistinctSections() {
        this.sectionTypes.clear();
        List<DailyInfo> list = this.dailyInfoList;
        Intrinsics.checkNotNull(list);
        Iterator<DailyInfo> it = list.iterator();
        while (it.hasNext()) {
            this.sectionTypes.add(it.next().itemType);
        }
        return Unit.INSTANCE;
    }

    public void setDailyInfo(List<DailyInfo> dailyInfoList) {
        Intrinsics.checkNotNullParameter(dailyInfoList, "dailyInfoList");
        this.dailyInfoList = dailyInfoList;
        this.sections.clear();
        getDistinctSections();
        for (String str : this.sectionTypes) {
            Section section = new Section();
            for (DailyInfo dailyInfo : dailyInfoList) {
                if (Intrinsics.areEqual(dailyInfo.itemType, str)) {
                    section.getDailyInfos().add(dailyInfo);
                }
            }
            this.sections.add(section);
        }
        notifyAllSectionsDataSetChanged();
    }

    @Override // org.zakariya.stickyheaders.SectioningAdapter
    public int getNumberOfSections() {
        return this.sections.size();
    }

    @Override // org.zakariya.stickyheaders.SectioningAdapter
    public int getNumberOfItemsInSection(int i2) {
        return this.sections.get(i2).getDailyInfos().size();
    }

    @Override // org.zakariya.stickyheaders.SectioningAdapter
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_visit_order_info, parent, false);
        Intrinsics.checkNotNull(inflate);
        return new ItemViewHolder(this, inflate);
    }

    @Override // org.zakariya.stickyheaders.SectioningAdapter
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_schedule_header, parent, false);
        Intrinsics.checkNotNull(inflate);
        return new HeaderViewHolder(this, inflate);
    }

    @Override // org.zakariya.stickyheaders.SectioningAdapter
    @SuppressLint({"SetTextI18n"})
    public void onBindHeaderViewHolder(SectioningAdapter.HeaderViewHolder viewHolder, int i2, int i3) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Section section = this.sections.get(i2);
        HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
        headerViewHolder.itemView.setBackgroundColor(-1);
        if (section.getDailyInfos().size() >= 1) {
            headerViewHolder.getTitleTextView().setText(setHeaderTitle(section.getDailyInfos().get(0).itemType, i2, section.getDailyInfos()));
        }
    }

    private String setHeaderTitle(String str, int i2, List<DailyInfo> list) {
        if (str != null) {
            switch (str.hashCode()) {
                case 66601:
                    if (str.equals(DailyInfo.CEK)) {
                        return this.mContext.getString(R.string.str_cheque_receipt) + " (" + this.mContext.getString(R.string.str_total_quantity) + ' ' + getNumberOfItemsInSection(i2) + ' ' + this.mContext.getString(R.string.str_receipt) + ' ' + StringUtils.formatDouble(itemTotal(list)) + ')';
                    }
                    break;
                case 2061104:
                    if (str.equals(DailyInfo.CASE)) {
                        return this.mContext.getString(R.string.str_safe_deposit) + " (" + this.mContext.getString(R.string.str_total_quantity) + ' ' + getNumberOfItemsInSection(i2) + ' ' + this.mContext.getString(R.string.str_receipt) + ' ' + StringUtils.formatDouble(itemTotal(list)) + ')';
                    }
                    break;
                case 2061107:
                    if (str.equals(DailyInfo.CASH)) {
                        return this.mContext.getString(R.string.str_cash_receipt) + " (" + this.mContext.getString(R.string.str_total_quantity) + ' ' + getNumberOfItemsInSection(i2) + ' ' + this.mContext.getString(R.string.str_receipt) + ' ' + StringUtils.formatDouble(itemTotal(list)) + ')';
                    }
                    break;
                case 75468590:
                    if (str.equals(DailyInfo.ORDER)) {
                        return this.mContext.getString(R.string.str_sales) + " (" + this.mContext.getString(R.string.str_total_quantity) + ' ' + getNumberOfItemsInSection(i2) + ' ' + this.mContext.getString(R.string.str_order) + ' ' + StringUtils.formatDouble(itemTotal(list)) + ')';
                    }
                    break;
                case 78785003:
                    if (str.equals(DailyInfo.SENET)) {
                        return this.mContext.getString(R.string.str_bill_receipt) + " (" + this.mContext.getString(R.string.str_total_quantity) + ' ' + getNumberOfItemsInSection(i2) + ' ' + this.mContext.getString(R.string.str_receipt) + ' ' + StringUtils.formatDouble(itemTotal(list)) + ')';
                    }
                    break;
                case 81679659:
                    if (str.equals(DailyInfo.VISIT)) {
                        return this.mContext.getString(R.string.str_visit_info) + " (" + getNumberOfItemsInSection(i2) + ' ' + this.mContext.getString(R.string.str_visit) + ')';
                    }
                    break;
                case 575578880:
                    if (str.equals(DailyInfo.IRSALIYE)) {
                        return this.mContext.getString(R.string.str_sales) + " (" + this.mContext.getString(R.string.str_total_quantity) + ' ' + getNumberOfItemsInSection(i2) + ' ' + this.mContext.getString(R.string.str_dispatch) + ' ' + StringUtils.formatDouble(itemTotal(list)) + ')';
                    }
                    break;
                case 613478897:
                    if (str.equals(DailyInfo.BIREBIR)) {
                        return this.mContext.getString(R.string.str_sales) + " (" + this.mContext.getString(R.string.str_total_quantity) + ' ' + getNumberOfItemsInSection(i2) + ' ' + this.mContext.getString(R.string.str_exchange) + ' ' + StringUtils.formatDouble(itemTotal(list)) + ')';
                    }
                    break;
                case 859001276:
                    if (str.equals(DailyInfo.WHTRANSFER)) {
                        return this.mContext.getString(R.string.str_sales_transfer) + " (" + this.mContext.getString(R.string.str_total_quantity) + ' ' + getNumberOfItemsInSection(i2) + ' ' + this.mContext.getString(R.string.str_sales_transfer) + ')';
                    }
                    break;
                case 1209916476:
                    if (str.equals(DailyInfo.PERAKENDEFATURA)) {
                        return this.mContext.getString(R.string.str_sales) + " (" + this.mContext.getString(R.string.str_total_quantity) + ' ' + getNumberOfItemsInSection(i2) + ' ' + this.mContext.getString(R.string.str_sales_retail_invoice) + ' ' + StringUtils.formatDouble(itemTotal(list)) + ')';
                    }
                    break;
                case 1996005113:
                    if (str.equals(DailyInfo.CREDIT)) {
                        return this.mContext.getString(R.string.str_credit_cart_receipt) + " (" + this.mContext.getString(R.string.str_total_quantity) + ' ' + getNumberOfItemsInSection(i2) + ' ' + this.mContext.getString(R.string.str_receipt) + ' ' + StringUtils.formatDouble(itemTotal(list)) + ')';
                    }
                    break;
                case 2012864075:
                    if (str.equals(DailyInfo.DEMAND)) {
                        return this.mContext.getString(R.string.str_sales_demand) + " (" + this.mContext.getString(R.string.str_total_quantity) + ' ' + getNumberOfItemsInSection(i2) + ' ' + this.mContext.getString(R.string.str_demand) + ' ' + StringUtils.formatDouble(itemTotal(list)) + ')';
                    }
                    break;
                case 2066656171:
                    if (str.equals(DailyInfo.FATURA)) {
                        return this.mContext.getString(R.string.str_sales) + " (" + this.mContext.getString(R.string.str_total_quantity) + ' ' + getNumberOfItemsInSection(i2) + ' ' + this.mContext.getString(R.string.str_invoice) + ' ' + StringUtils.formatDouble(itemTotal(list)) + ')';
                    }
                    break;
            }
        }
        return "";
    }

    private double itemTotal(List<DailyInfo> list) {
        int size = list.size();
        double d2 = 0.0d;
        for (int i2 = 0; i2 < size; i2++) {
            d2 += list.get(i2).total;
        }
        return d2;
    }

    @Override // org.zakariya.stickyheaders.SectioningAdapter
    @SuppressLint({"SetTextI18n"})
    public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder viewHolder, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        DailyInfo dailyInfo = this.sections.get(i2).getDailyInfos().get(i3);
        Intrinsics.checkNotNullExpressionValue(dailyInfo, "get(...)");
        DailyInfo dailyInfo2 = dailyInfo;
        ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        itemViewHolder.getTvTime().setText(dailyInfo2.getdDate());
        itemViewHolder.getTvCustomerName().setText(dailyInfo2.customerName);
        itemViewHolder.getTvCustomerCode().setText(dailyInfo2.customerCode);
        itemViewHolder.getImgFicheTranfer().setVisibility(dailyInfo2.isTransfer() ? View.VISIBLE : View.GONE);
        setLayoutForItemType(dailyInfo2.itemType, itemViewHolder);
        setUIForItemType(dailyInfo2.itemType, itemViewHolder, dailyInfo2);
    }

    private void setLayoutForItemType(String str, ItemViewHolder itemViewHolder) {
        itemViewHolder.getLnTotalLayout().setVisibility(View.VISIBLE);
        itemViewHolder.getLnCustomerLayout().setVisibility(View.VISIBLE);
        if (str != null) {
            switch (str.hashCode()) {
                case 66601:
                    if (!str.equals(DailyInfo.CEK)) {
                    }
                    itemViewHolder.getTvNote().setText(R.string.str_expiry);
                    itemViewHolder.getTvDebitedOrVisitReason().setText(R.string.str_debtor);
                    itemViewHolder.getLnReceiptInfo().setVisibility(View.VISIBLE);
                    itemViewHolder.getLnDebitedOrVisitReason().setVisibility(View.VISIBLE);
                    break;
                case 2061104:
                    if (!str.equals(DailyInfo.CASE)) {
                    }
                    itemViewHolder.getTvNoteColon().setVisibility(View.GONE);
                    itemViewHolder.getTvNote().setVisibility(View.GONE);
                    itemViewHolder.getTvNoteExplanation().setVisibility(View.GONE);
                    itemViewHolder.getLnReceiptInfo().setVisibility(View.GONE);
                    itemViewHolder.getLnDebitedOrVisitReason().setVisibility(View.GONE);
                    break;
                case 2061107:
                    if (!str.equals(DailyInfo.CASH)) {
                    }
                    itemViewHolder.getTvNoteColon().setVisibility(View.GONE);
                    itemViewHolder.getTvNote().setVisibility(View.GONE);
                    itemViewHolder.getTvNoteExplanation().setVisibility(View.GONE);
                    itemViewHolder.getLnReceiptInfo().setVisibility(View.GONE);
                    itemViewHolder.getLnDebitedOrVisitReason().setVisibility(View.GONE);
                    break;
                case 75468590:
                    if (!str.equals(DailyInfo.ORDER)) {
                    }
                    itemViewHolder.getTvNote().setText(R.string.str_pay_plan);
                    itemViewHolder.getLnReceiptInfo().setVisibility(View.GONE);
                    itemViewHolder.getLnDebitedOrVisitReason().setVisibility(View.GONE);
                    break;
                case 78785003:
                    if (!str.equals(DailyInfo.SENET)) {
                    }
                    itemViewHolder.getTvNote().setText(R.string.str_expiry);
                    itemViewHolder.getTvDebitedOrVisitReason().setText(R.string.str_debtor);
                    itemViewHolder.getLnReceiptInfo().setVisibility(View.VISIBLE);
                    itemViewHolder.getLnDebitedOrVisitReason().setVisibility(View.VISIBLE);
                    break;
                case 81679659:
                    if (str.equals(DailyInfo.VISIT)) {
                        itemViewHolder.getTvTotal().setVisibility(View.GONE);
                        itemViewHolder.getTvTotalColon().setVisibility(View.GONE);
                        itemViewHolder.getTvTotalExplanation().setVisibility(View.GONE);
                        itemViewHolder.getLnReceiptInfo().setVisibility(View.GONE);
                        itemViewHolder.getTvDebitedOrVisitReason().setText(R.string.str_visit_reason);
                        itemViewHolder.getLnDebitedOrVisitReason().setVisibility(View.VISIBLE);
                        break;
                    }
                    break;
                case 575578880:
                    if (!str.equals(DailyInfo.IRSALIYE)) {
                    }
                    itemViewHolder.getTvNote().setText(R.string.str_pay_plan);
                    itemViewHolder.getLnReceiptInfo().setVisibility(View.GONE);
                    itemViewHolder.getLnDebitedOrVisitReason().setVisibility(View.GONE);
                    break;
                case 613478897:
                    if (!str.equals(DailyInfo.BIREBIR)) {
                    }
                    itemViewHolder.getTvNote().setText(R.string.str_pay_plan);
                    itemViewHolder.getLnReceiptInfo().setVisibility(View.GONE);
                    itemViewHolder.getLnDebitedOrVisitReason().setVisibility(View.GONE);
                    break;
                case 859001276:
                    if (str.equals(DailyInfo.WHTRANSFER)) {
                        itemViewHolder.getLnTotalLayout().setVisibility(View.GONE);
                        itemViewHolder.getLnCustomerLayout().setVisibility(View.GONE);
                        itemViewHolder.getLnReceiptInfo().setVisibility(View.GONE);
                        itemViewHolder.getLnDebitedOrVisitReason().setVisibility(View.GONE);
                        break;
                    }
                    break;
                case 1209916476:
                    if (!str.equals(DailyInfo.PERAKENDEFATURA)) {
                    }
                    itemViewHolder.getTvNote().setText(R.string.str_pay_plan);
                    itemViewHolder.getLnReceiptInfo().setVisibility(View.GONE);
                    itemViewHolder.getLnDebitedOrVisitReason().setVisibility(View.GONE);
                    break;
                case 1996005113:
                    if (!str.equals(DailyInfo.CREDIT)) {
                    }
                    itemViewHolder.getTvNoteColon().setVisibility(View.GONE);
                    itemViewHolder.getTvNote().setVisibility(View.GONE);
                    itemViewHolder.getTvNoteExplanation().setVisibility(View.GONE);
                    itemViewHolder.getLnReceiptInfo().setVisibility(View.GONE);
                    itemViewHolder.getLnDebitedOrVisitReason().setVisibility(View.GONE);
                    break;
                case 2012864075:
                    if (!str.equals(DailyInfo.DEMAND)) {
                    }
                    itemViewHolder.getTvNote().setText(R.string.str_pay_plan);
                    itemViewHolder.getLnReceiptInfo().setVisibility(View.GONE);
                    itemViewHolder.getLnDebitedOrVisitReason().setVisibility(View.GONE);
                    break;
                case 2066656171:
                    if (!str.equals(DailyInfo.FATURA)) {
                    }
                    itemViewHolder.getTvNote().setText(R.string.str_pay_plan);
                    itemViewHolder.getLnReceiptInfo().setVisibility(View.GONE);
                    itemViewHolder.getLnDebitedOrVisitReason().setVisibility(View.GONE);
                    break;
            }
        }
    }

    public void setUIForItemType(String str, ItemViewHolder viewHolder, DailyInfo dailyInfo) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(dailyInfo, "dailyInfo");
        if (str != null) {
            switch (str.hashCode()) {
                case 66601:
                    if (!str.equals(DailyInfo.CEK)) {
                    }
                    viewHolder.getTvNote().setText(R.string.str_expiry);
                    viewHolder.getTvDebitedOrVisitReason().setText(R.string.str_debtor);
                    viewHolder.getLnReceiptInfo().setVisibility(View.VISIBLE);
                    viewHolder.getLnDebitedOrVisitReason().setVisibility(View.VISIBLE);
                    viewHolder.getTvShipAddressOrBank().setText(dailyInfo.bankName);
                    viewHolder.getTvBranchNote().setText(dailyInfo.bankBranch);
                    viewHolder.getTvAccountNumberNote().setText(dailyInfo.accNo);
                    viewHolder.getTvDebitedOrVisitReason().setText(R.string.str_debtor);
                    viewHolder.getTvDebitedOrVisitReasonExplanation().setText(dailyInfo.debited);
                    viewHolder.getTvNote().setText(R.string.str_expiry);
                    viewHolder.getTvNoteExplanation().setText(dailyInfo.dueDate);
                    viewHolder.getTvTotal().setText(R.string.str_total);
                    viewHolder.getTvTotalExplanation().setText(StringUtils.formatDouble(dailyInfo.total));
                    break;
                case 2061104:
                    if (str.equals(DailyInfo.CASE)) {
                        viewHolder.getTvNoteColon().setVisibility(View.GONE);
                        viewHolder.getTvNote().setVisibility(View.GONE);
                        viewHolder.getTvNoteExplanation().setVisibility(View.GONE);
                        viewHolder.getLnReceiptInfo().setVisibility(View.GONE);
                        viewHolder.getLnDebitedOrVisitReason().setVisibility(View.GONE);
                        viewHolder.getTvTotal().setText(R.string.str_slip_amount);
                        viewHolder.getTvTotalExplanation().setText(StringUtils.formatDouble(dailyInfo.total));
                        break;
                    }
                    break;
                case 2061107:
                    if (!str.equals(DailyInfo.CASH)) {
                    }
                    viewHolder.getTvNoteColon().setVisibility(View.GONE);
                    viewHolder.getTvNote().setVisibility(View.GONE);
                    viewHolder.getTvNoteExplanation().setVisibility(View.GONE);
                    viewHolder.getLnReceiptInfo().setVisibility(View.GONE);
                    viewHolder.getLnDebitedOrVisitReason().setVisibility(View.GONE);
                    viewHolder.getTvTotal().setText(R.string.str_slip_amount);
                    viewHolder.getTvTotalExplanation().setText(StringUtils.formatDouble(dailyInfo.total));
                    if (dailyInfo.getfType() == 1) {
                        viewHolder.getTvShipAddressOrBank().setText(dailyInfo.bankName);
                        break;
                    } else {
                        viewHolder.getTvShipAddressOrBank().setText(dailyInfo.bankName + " - " + dailyInfo.bankBranch);
                        break;
                    }

                case 75468590:
                    if (!str.equals(DailyInfo.ORDER)) {
                    }
                    viewHolder.getTvShipAddressOrBank().setText(dailyInfo.shipAddress);
                    viewHolder.getLnReceiptInfo().setVisibility(View.GONE);
                    viewHolder.getLnDebitedOrVisitReason().setVisibility(View.GONE);
                    viewHolder.getTvShipAddressOrBank().setVisibility(View.VISIBLE);
                    viewHolder.getTvTotal().setText(R.string.str_total);
                    viewHolder.getTvTotalExplanation().setText(StringUtils.formatDouble(dailyInfo.total));
                    viewHolder.getTvNote().setText(R.string.str_pay_plan);
                    viewHolder.getTvNoteExplanation().setText(dailyInfo.paymentPlan);
                    break;
                case 78785003:
                    if (!str.equals(DailyInfo.SENET)) {
                    }
                    viewHolder.getTvNote().setText(R.string.str_expiry);
                    viewHolder.getTvDebitedOrVisitReason().setText(R.string.str_debtor);
                    viewHolder.getLnReceiptInfo().setVisibility(View.VISIBLE);
                    viewHolder.getLnDebitedOrVisitReason().setVisibility(View.VISIBLE);
                    viewHolder.getTvShipAddressOrBank().setText(dailyInfo.bankName);
                    viewHolder.getTvBranchNote().setText(dailyInfo.bankBranch);
                    viewHolder.getTvAccountNumberNote().setText(dailyInfo.accNo);
                    viewHolder.getTvDebitedOrVisitReason().setText(R.string.str_debtor);
                    viewHolder.getTvDebitedOrVisitReasonExplanation().setText(dailyInfo.debited);
                    viewHolder.getTvNote().setText(R.string.str_expiry);
                    viewHolder.getTvNoteExplanation().setText(dailyInfo.dueDate);
                    viewHolder.getTvTotal().setText(R.string.str_total);
                    viewHolder.getTvTotalExplanation().setText(StringUtils.formatDouble(dailyInfo.total));
                    break;
                case 81679659:
                    if (str.equals(DailyInfo.VISIT)) {
                        viewHolder.getTvShipAddressOrBank().setText(dailyInfo.shipAddress);
                        viewHolder.getLnReceiptInfo().setVisibility(View.GONE);
                        viewHolder.getTvDebitedOrVisitReason().setText(R.string.str_visit_reason);
                        viewHolder.getLnDebitedOrVisitReason().setVisibility(View.VISIBLE);
                        viewHolder.getTvDebitedOrVisitReasonExplanation().setText(dailyInfo.visitReason);
                        viewHolder.getTvNote().setText(R.string.str_note);
                        viewHolder.getTvNoteExplanation().setText(dailyInfo.visitNote);
                        break;
                    }
                    break;
                case 575578880:
                    if (!str.equals(DailyInfo.IRSALIYE)) {
                    }
                    viewHolder.getTvShipAddressOrBank().setText(dailyInfo.shipAddress);
                    viewHolder.getLnReceiptInfo().setVisibility(View.GONE);
                    viewHolder.getLnDebitedOrVisitReason().setVisibility(View.GONE);
                    viewHolder.getTvShipAddressOrBank().setVisibility(View.VISIBLE);
                    viewHolder.getTvTotal().setText(R.string.str_total);
                    viewHolder.getTvTotalExplanation().setText(StringUtils.formatDouble(dailyInfo.total));
                    viewHolder.getTvNote().setText(R.string.str_pay_plan);
                    viewHolder.getTvNoteExplanation().setText(dailyInfo.paymentPlan);
                    break;
                case 613478897:
                    if (!str.equals(DailyInfo.BIREBIR)) {
                    }
                    viewHolder.getTvShipAddressOrBank().setText(dailyInfo.shipAddress);
                    viewHolder.getLnReceiptInfo().setVisibility(View.GONE);
                    viewHolder.getLnDebitedOrVisitReason().setVisibility(View.GONE);
                    viewHolder.getTvShipAddressOrBank().setVisibility(View.VISIBLE);
                    viewHolder.getTvTotal().setText(R.string.str_total);
                    viewHolder.getTvTotalExplanation().setText(StringUtils.formatDouble(dailyInfo.total));
                    viewHolder.getTvNote().setText(R.string.str_pay_plan);
                    viewHolder.getTvNoteExplanation().setText(dailyInfo.paymentPlan);
                    break;
                case 859001276:
                    if (str.equals(DailyInfo.WHTRANSFER)) {
                        viewHolder.getTvCustomerName().setVisibility(View.VISIBLE);
                        viewHolder.getLnTotalLayout().setVisibility(View.GONE);
                        viewHolder.getLnCustomerLayout().setVisibility(View.GONE);
                        viewHolder.getLnReceiptInfo().setVisibility(View.GONE);
                        viewHolder.getLnDebitedOrVisitReason().setVisibility(View.GONE);
                        break;
                    }
                    break;
                case 1209916476:
                    if (!str.equals(DailyInfo.PERAKENDEFATURA)) {
                    }
                    viewHolder.getTvShipAddressOrBank().setText(dailyInfo.shipAddress);
                    viewHolder.getLnReceiptInfo().setVisibility(View.GONE);
                    viewHolder.getLnDebitedOrVisitReason().setVisibility(View.GONE);
                    viewHolder.getTvShipAddressOrBank().setVisibility(View.VISIBLE);
                    viewHolder.getTvTotal().setText(R.string.str_total);
                    viewHolder.getTvTotalExplanation().setText(StringUtils.formatDouble(dailyInfo.total));
                    viewHolder.getTvNote().setText(R.string.str_pay_plan);
                    viewHolder.getTvNoteExplanation().setText(dailyInfo.paymentPlan);
                    break;
                case 1996005113:
                    if (!str.equals(DailyInfo.CREDIT)) {
                    }
                    viewHolder.getTvNoteColon().setVisibility(View.GONE);
                    viewHolder.getTvNote().setVisibility(View.GONE);
                    viewHolder.getTvNoteExplanation().setVisibility(View.GONE);
                    viewHolder.getLnReceiptInfo().setVisibility(View.GONE);
                    viewHolder.getLnDebitedOrVisitReason().setVisibility(View.GONE);
                    viewHolder.getTvTotal().setText(R.string.str_slip_amount);
                    viewHolder.getTvTotalExplanation().setText(StringUtils.formatDouble(dailyInfo.total));
                    if (dailyInfo.getfType() == 1) {
                    }
                    break;
                case 2012864075:
                    if (str.equals(DailyInfo.DEMAND)) {
                        viewHolder.getTvCustomerCode().setVisibility(View.GONE);
                        viewHolder.getTvCustomerName().setVisibility(View.GONE);
                        viewHolder.getTvShipAddressOrBank().setText(dailyInfo.shipAddress);
                        viewHolder.getLnReceiptInfo().setVisibility(View.GONE);
                        viewHolder.getLnDebitedOrVisitReason().setVisibility(View.GONE);
                        viewHolder.getTvShipAddressOrBank().setVisibility(View.VISIBLE);
                        viewHolder.getTvTotal().setText(R.string.str_total);
                        viewHolder.getTvTotalExplanation().setText(StringUtils.formatDouble(dailyInfo.total));
                        viewHolder.getTvNote().setText(R.string.str_pay_plan);
                        viewHolder.getTvNoteExplanation().setText(dailyInfo.paymentPlan);
                        break;
                    }
                    break;
                case 2066656171:
                    if (!str.equals(DailyInfo.FATURA)) {
                    }
                    viewHolder.getTvShipAddressOrBank().setText(dailyInfo.shipAddress);
                    viewHolder.getLnReceiptInfo().setVisibility(View.GONE);
                    viewHolder.getLnDebitedOrVisitReason().setVisibility(View.GONE);
                    viewHolder.getTvShipAddressOrBank().setVisibility(View.VISIBLE);
                    viewHolder.getTvTotal().setText(R.string.str_total);
                    viewHolder.getTvTotalExplanation().setText(StringUtils.formatDouble(dailyInfo.total));
                    viewHolder.getTvNote().setText(R.string.str_pay_plan);
                    viewHolder.getTvNoteExplanation().setText(dailyInfo.paymentPlan);
                    break;
            }
        }
    }
}
