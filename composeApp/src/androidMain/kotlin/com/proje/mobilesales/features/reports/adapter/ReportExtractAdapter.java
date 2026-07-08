package com.proje.mobilesales.features.reports.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.tigerwcf.REPORTLINE;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.reports.model.enums.ReportTransactionType;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PrimitiveCompanionObjects;


/* compiled from: ReportExtractAdapter.kt */

public final class ReportExtractAdapter extends BaseAdapter {
    private final Activity activity;
    private int[] arrVisibility;
    private final int blackColor;
    private final ErpType erpType;
    private final List<REPORTLINE> list;
    private final int redColor;
    private REPORTLINE reportLine;

    private int getTrCodeDescId(final int i2) {
        if (12 == i2) {
            return R.string.str_special_operation;
        }
        if (14 == i2) {
            return R.string.str_opening_slip;
        }
        if (56 == i2) {
            return R.string.str_customer_receipt;
        }
        if (81 == i2) {
            return R.string.str_prepayment_order;
        }
        if (99 == i2) {
            return R.string.str_invoice_amount_account;
        }
        if (999 == i2) {
            return R.string.str_report_extract_transfer;
        }
        if (20 == i2) {
            return R.string.str_incoming_remittance;
        }
        if (21 == i2) {
            return R.string.str_sent_remittance;
        }
        if (24 == i2) {
            return R.string.str_foreign_exchange_certificate_purchasing;
        }
        if (25 == i2) {
            return R.string.str_foreign_exchange_certificate_selling;
        }
        switch (i2) {
            case 1:
                return R.string.str_cash_collection;
            case 2:
                return R.string.str_cash_payment;
            case 3:
                return R.string.str_debt_note;
            case 4:
                return R.string.str_credit_note;
            case 5:
                return R.string.str_remittance_operation;
            case 6:
                return R.string.str_exchange_rate_difference_operation;
            default:
                switch (i2) {
                    case 31:
                        return R.string.str_goods_purchase_invoice;
                    case 32:
                        return R.string.str_retail_sales_return_invoice;
                    case 33:
                        return R.string.str_wholesale_sales_return_invoice;
                    case 34:
                        return R.string.str_service_purchase_return_invoice;
                    case 35:
                        return R.string.str_purchase_pro_forma_invoice;
                    case 36:
                        return R.string.str_buying_return_receipt;
                    case 37:
                        return R.string.str_retail_sales_invoice;
                    case 38:
                        return R.string.str_wholesale_sales_invoice;
                    case 39:
                        return R.string.str_given_service_sales_invoice;
                    case 40:
                        return R.string.str_given_pro_forma_sales_invoice;
                    case 41:
                        return R.string.str_due_date_diff_invoice_issued;
                    case 42:
                        return R.string.str_due_date_diff_invoice_received;
                    case 43:
                        return R.string.str_purchase_price_difference_invoice;
                    case 44:
                    case 45:
                        return R.string.str_sales_price_difference_invoice;
                    case 46:
                        return R.string.str_received_self_employment_receipt;
                    default:
                        switch (i2) {
                            case 61:
                                return R.string.str_cheque_entry;
                            case 62:
                                return R.string.str_deed_entry;
                            case 63:
                                return R.string.str_check_issued_account;
                            case 64:
                                return R.string.str_promissory_note_issued;
                            default:
                                switch (i2) {
                                    case 70:
                                        return R.string.str_credit_card_fiche;
                                    case 71:
                                        return R.string.str_credit_card_return_slip;
                                    case 72:
                                        return R.string.str_firm_credit_card_fiche;
                                    case 73:
                                        return R.string.str_firm_credit_card_return_fiche;
                                    default:
                                        return -1;
                                }
                        }
                }
        }
    }

    @Override // android.widget.Adapter
    public Object getItem(final int i2) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(final int i2) {
        return 0L;
    }

    
    public ReportExtractAdapter(final Activity activity, final List<? extends REPORTLINE> list, final ErpType erpType) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(erpType, "erpType");
        this.activity = activity;
        this.list = list;
        this.erpType = erpType;
        final Context context = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context);
        redColor = ContextCompat.getColor(context, R.color.colorPrimary);
        final Context context2 = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context2);
        blackColor = ContextCompat.getColor(context2, R.color.black);
    }

    public void setColumnVisibility(final int[] arrVisibility) {
        Intrinsics.checkNotNullParameter(arrVisibility, "arrVisibility");
        this.arrVisibility = arrVisibility;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return list.size();
    }

    @Override // android.widget.Adapter
    @SuppressLint({"InflateParams", "DefaultLocale"})
    public View getView(final int i2, View view, final ViewGroup parent) {
        final ViewHolder viewHolder;
        Intrinsics.checkNotNullParameter(parent, "parent");
        try {
            if (null == view) {
                viewHolder = new ViewHolder();
                final Activity activity = ContextUtils.getmActivity();
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type android.app.Activity");
                final LayoutInflater layoutInflater = activity.getLayoutInflater();
                Intrinsics.checkNotNullExpressionValue(layoutInflater, "getLayoutInflater(...)");
                if (erpType == ErpType.NETSIS) {
                    view = layoutInflater.inflate(R.layout.extract_row, parent, false);
                    final View findViewById = view.findViewById(R.id.txt_ficheDueDate);
                    Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
                    viewHolder.setTxtFicheDueDate((AppCompatTextView) findViewById);
                } else {
                    view = layoutInflater.inflate(R.layout.extract_row_tiger, parent, false);
                }
                final View findViewById2 = view.findViewById(R.id.txt_ficheInfo);
                Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
                viewHolder.setTxtFicheInfo((AppCompatTextView) findViewById2);
                final View findViewById3 = view.findViewById(R.id.ln_ficheDescription);
                Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.LinearLayout");
                viewHolder.setLnFicheDescription((LinearLayout) findViewById3);
                final View findViewById4 = view.findViewById(R.id.txt_ficheCredit);
                Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
                viewHolder.setTxtFicheCredit((AppCompatTextView) findViewById4);
                final View findViewById5 = view.findViewById(R.id.txt_ficheDebit);
                Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
                viewHolder.setTxtFicheDebit((AppCompatTextView) findViewById5);
                final View findViewById6 = view.findViewById(R.id.txt_ficheBalance);
                Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
                viewHolder.setTxtFicheBalance((AppCompatTextView) findViewById6);
                final View findViewById7 = view.findViewById(R.id.ln_ficheBalance);
                Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type android.widget.LinearLayout");
                viewHolder.setLnFicheBalance((LinearLayout) findViewById7);
                view.setTag(viewHolder);
            } else {
                final Object tag = view.getTag();
                Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type com.proje.mobilesales.features.reports.adapter.ReportExtractAdapter.ViewHolder");
                viewHolder = (ViewHolder) tag;
            }
            reportLine = list.get(i2);
            final AppCompatTextView txtFicheInfo = viewHolder.getTxtFicheInfo();
            Intrinsics.checkNotNull(txtFicheInfo);
            txtFicheInfo.setText(this.getDescription(reportLine));
            final REPORTLINE reportline = reportLine;
            Intrinsics.checkNotNull(reportline);
            if (0.0d != reportline.DEBIT) {
                final AppCompatTextView txtFicheDebit = viewHolder.getTxtFicheDebit();
                Intrinsics.checkNotNull(txtFicheDebit);
                final REPORTLINE reportline2 = reportLine;
                Intrinsics.checkNotNull(reportline2);
                txtFicheDebit.setText(StringUtils.ffFormat(reportline2.DEBIT));
            } else {
                final AppCompatTextView txtFicheDebit2 = viewHolder.getTxtFicheDebit();
                Intrinsics.checkNotNull(txtFicheDebit2);
                txtFicheDebit2.setText(activity.getResources().getString(R.string.empty));
            }
            if (erpType == ErpType.NETSIS) {
                final REPORTLINE reportline3 = reportLine;
                Intrinsics.checkNotNull(reportline3);
                if (!Intrinsics.areEqual(reportline3.f1208X, ReportTransactionType.CHEQUE_ENTRY.getType())) {
                    final REPORTLINE reportline4 = reportLine;
                    Intrinsics.checkNotNull(reportline4);
                    if (!Intrinsics.areEqual(reportline4.f1208X, ReportTransactionType.DEED_ENTRY.getType())) {
                        final AppCompatTextView txtFicheDueDate = viewHolder.getTxtFicheDueDate();
                        Intrinsics.checkNotNull(txtFicheDueDate);
                        txtFicheDueDate.setText("-");
                    }
                }
                final AppCompatTextView txtFicheDueDate2 = viewHolder.getTxtFicheDueDate();
                Intrinsics.checkNotNull(txtFicheDueDate2);
                final REPORTLINE reportline5 = reportLine;
                Intrinsics.checkNotNull(reportline5);
                final String str = reportline5.PROCDATE;
                txtFicheDueDate2.setText(null != str ? str : "-");
            }
            final REPORTLINE reportline6 = reportLine;
            Intrinsics.checkNotNull(reportline6);
            if (0.0d != reportline6.CREDIT) {
                final AppCompatTextView txtFicheCredit = viewHolder.getTxtFicheCredit();
                Intrinsics.checkNotNull(txtFicheCredit);
                final REPORTLINE reportline7 = reportLine;
                Intrinsics.checkNotNull(reportline7);
                txtFicheCredit.setText(StringUtils.ffFormat(reportline7.CREDIT));
            } else {
                final AppCompatTextView txtFicheCredit2 = viewHolder.getTxtFicheCredit();
                Intrinsics.checkNotNull(txtFicheCredit2);
                txtFicheCredit2.setText(activity.getResources().getString(R.string.empty));
            }
            if (!ErpCreator.getInstance().getmBaseErp().isHideCustomerBalance()) {
                final REPORTLINE reportline8 = reportLine;
                Intrinsics.checkNotNull(reportline8);
                if (0.0d > reportline8.BALANCE) {
                    final AppCompatTextView txtFicheBalance = viewHolder.getTxtFicheBalance();
                    Intrinsics.checkNotNull(txtFicheBalance);
                    final PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
                    final REPORTLINE reportline9 = reportLine;
                    Intrinsics.checkNotNull(reportline9);
                    final String format = String.format("%s(B)", Arrays.copyOf(new Object[]{StringUtils.ffFormat((-1) * reportline9.BALANCE)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                    txtFicheBalance.setText(format);
                    final AppCompatTextView txtFicheBalance2 = viewHolder.getTxtFicheBalance();
                    Intrinsics.checkNotNull(txtFicheBalance2);
                    txtFicheBalance2.setTextColor(redColor);
                } else {
                    final REPORTLINE reportline10 = reportLine;
                    Intrinsics.checkNotNull(reportline10);
                    if (0.0d < reportline10.BALANCE) {
                        final AppCompatTextView txtFicheBalance3 = viewHolder.getTxtFicheBalance();
                        Intrinsics.checkNotNull(txtFicheBalance3);
                        final PrimitiveCompanionObjects primitiveCompanionObjects2 = PrimitiveCompanionObjects.INSTANCE;
                        final REPORTLINE reportline11 = reportLine;
                        Intrinsics.checkNotNull(reportline11);
                        final String format2 = String.format("%s(A)", Arrays.copyOf(new Object[]{StringUtils.ffFormat(reportline11.BALANCE)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
                        txtFicheBalance3.setText(format2);
                        final AppCompatTextView txtFicheBalance4 = viewHolder.getTxtFicheBalance();
                        Intrinsics.checkNotNull(txtFicheBalance4);
                        txtFicheBalance4.setTextColor(blackColor);
                    } else {
                        final AppCompatTextView txtFicheBalance5 = viewHolder.getTxtFicheBalance();
                        Intrinsics.checkNotNull(txtFicheBalance5);
                        final REPORTLINE reportline12 = reportLine;
                        Intrinsics.checkNotNull(reportline12);
                        txtFicheBalance5.setText(StringUtils.ffFormat(reportline12.BALANCE));
                        final AppCompatTextView txtFicheBalance6 = viewHolder.getTxtFicheBalance();
                        Intrinsics.checkNotNull(txtFicheBalance6);
                        final Context context = ContextUtils.getmContext();
                        Intrinsics.checkNotNull(context);
                        txtFicheBalance6.setTextColor(ContextCompat.getColor(context, R.color.grey800));
                    }
                }
            } else {
                final LinearLayout lnFicheBalance = viewHolder.getLnFicheBalance();
                Intrinsics.checkNotNull(lnFicheBalance);
                lnFicheBalance.setVisibility(8);
            }
            int[] iArr = arrVisibility;
            int[] iArr2 = null;
            if (null == iArr) {
                Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                iArr = null;
            }
            if (0 == iArr[0]) {
                final LinearLayout lnFicheDescription = viewHolder.getLnFicheDescription();
                Intrinsics.checkNotNull(lnFicheDescription);
                lnFicheDescription.setVisibility(8);
            } else {
                final LinearLayout lnFicheDescription2 = viewHolder.getLnFicheDescription();
                Intrinsics.checkNotNull(lnFicheDescription2);
                lnFicheDescription2.setVisibility(0);
            }
            final int[] iArr3 = arrVisibility;
            if (null == iArr3) {
                Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            } else {
                iArr2 = iArr3;
            }
            if (0 == iArr2[1]) {
                final LinearLayout lnFicheBalance2 = viewHolder.getLnFicheBalance();
                Intrinsics.checkNotNull(lnFicheBalance2);
                lnFicheBalance2.setVisibility(8);
            } else {
                final LinearLayout lnFicheBalance3 = viewHolder.getLnFicheBalance();
                Intrinsics.checkNotNull(lnFicheBalance3);
                lnFicheBalance3.setVisibility(0);
            }
        } catch (final Exception e2) {
            Log.e("AA", "getView: ", e2);
        }
        Intrinsics.checkNotNull(view);
        return view;
    }

    private String getDescription(final REPORTLINE reportline) {
        final ErpType erpType = this.erpType;
        if (erpType == ErpType.TIGER || erpType == ErpType.f1172GO) {
            return this.getDescriptionTigerAndGo(reportline);
        }
        return this.getDescriptionForNetsis(reportline);
    }

    private String getDescriptionTigerAndGo(final REPORTLINE reportline) {
        final PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
        Intrinsics.checkNotNull(reportline);
        final String format = String.format("%s / %s - %s", Arrays.copyOf(new Object[]{reportline.DATE_, reportline.FICHENO, activity.getResources().getString(this.getTrCodeDescId(reportline.TRCODE))}, 3));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    private String getDescriptionForNetsis(final REPORTLINE reportline) {
        final PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
        Intrinsics.checkNotNull(reportline);
        final String format = String.format("%s / %s - %s", Arrays.copyOf(new Object[]{reportline.DATE_, reportline.FICHENO, reportline.DESC}, 3));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    /* compiled from: ReportExtractAdapter.kt */
    public static final class ViewHolder {
        private LinearLayout lnFicheBalance;
        private LinearLayout lnFicheDescription;
        private AppCompatTextView txtFicheBalance;
        private AppCompatTextView txtFicheCredit;
        private AppCompatTextView txtFicheDebit;
        private AppCompatTextView txtFicheDueDate;
        private AppCompatTextView txtFicheInfo;

        public LinearLayout getLnFicheDescription() {
            return lnFicheDescription;
        }

        public void setLnFicheDescription(final LinearLayout linearLayout) {
            lnFicheDescription = linearLayout;
        }

        public AppCompatTextView getTxtFicheInfo() {
            return txtFicheInfo;
        }

        public void setTxtFicheInfo(final AppCompatTextView appCompatTextView) {
            txtFicheInfo = appCompatTextView;
        }

        public AppCompatTextView getTxtFicheCredit() {
            return txtFicheCredit;
        }

        public void setTxtFicheCredit(final AppCompatTextView appCompatTextView) {
            txtFicheCredit = appCompatTextView;
        }

        public AppCompatTextView getTxtFicheDebit() {
            return txtFicheDebit;
        }

        public void setTxtFicheDebit(final AppCompatTextView appCompatTextView) {
            txtFicheDebit = appCompatTextView;
        }

        public AppCompatTextView getTxtFicheDueDate() {
            return txtFicheDueDate;
        }

        public void setTxtFicheDueDate(final AppCompatTextView appCompatTextView) {
            txtFicheDueDate = appCompatTextView;
        }

        public AppCompatTextView getTxtFicheBalance() {
            return txtFicheBalance;
        }

        public void setTxtFicheBalance(final AppCompatTextView appCompatTextView) {
            txtFicheBalance = appCompatTextView;
        }

        public LinearLayout getLnFicheBalance() {
            return lnFicheBalance;
        }

        public void setLnFicheBalance(final LinearLayout linearLayout) {
            lnFicheBalance = linearLayout;
        }
    }
}
