package com.proje.mobilesales.features.reports.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.reports.adapter.ReportExtractInvDetailAdapter;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: ReportExtractInvoiceDetailActivity.kt */

public final class ReportExtractInvoiceDetailActivity extends Fragment {
    private ReportExtractInvDetailAdapter adapter;
    private ReportExtractInvoiceActivity.ExtractInvHeader header;
    private ListView lvList;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(final LayoutInflater inflater, final ViewGroup viewGroup, final Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        final View inflate = inflater.inflate(R.layout.extract_invoice_detail, viewGroup, false);
        Log.d("MNTTAG", "ExtractInvDetailActivity");
        Intrinsics.checkNotNull(inflate);
        this.initialize(inflate);
        final Bundle arguments = this.getArguments();
        Intrinsics.checkNotNull(arguments);
        final ReportExtractInvoiceActivity.ExtractInvHeader extractInvHeader = arguments.getParcelable("INVOICE");
        header = extractInvHeader;
        Intrinsics.checkNotNull(extractInvHeader);
        if (!extractInvHeader.getDetailList().isEmpty()) {
            this.setItemValues();
        }
        return inflate;
    }

    private void initialize(final View view) {
        final View findViewById = view.findViewById(R.id.lvList);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.ListView");
        lvList = (ListView) findViewById;
    }

    private void setItemValues() {
        final FragmentActivity activity = this.getActivity();
        final ReportExtractInvoiceActivity.ExtractInvHeader extractInvHeader = header;
        Intrinsics.checkNotNull(extractInvHeader);
        adapter = new ReportExtractInvDetailAdapter(activity, extractInvHeader.getDetailList());
        final ListView listView = lvList;
        Intrinsics.checkNotNull(listView);
        listView.setAdapter(adapter);
    }
}
