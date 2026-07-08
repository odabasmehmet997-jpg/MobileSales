package com.proje.mobilesales.features.userreport.view.activity;

import android.os.Bundle;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.PdfExportOption;
import com.proje.mobilesales.core.reportparser.Report;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.view.ScreenControl;
import com.proje.mobilesales.core.widget.SlideInLeftAnimator;
import com.proje.mobilesales.features.model.GenericData;
import com.proje.mobilesales.features.userreport.adapter.UserReportsFormActivityRecyclerViewAdapter;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

public final class UserReportsFormActivity extends UserReportsActivity {
    private UserReportsFormActivityRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private ScreenControl screenControl;
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setSoftInputMode(3);
        setContentView(R.layout.userreports_form);
        RecyclerView recyclerView = findViewById(R.id.rwUserReportsForm);
        this.recyclerView = recyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 != null) {
            recyclerView2.setHasFixedSize(true);
        }
        RecyclerView recyclerView3 = this.recyclerView;
        if (recyclerView3 != null) {
            recyclerView3.setItemAnimator(new SlideInLeftAnimator(new OvershootInterpolator(2.0f)));
        }
        UserReportsFormActivityRecyclerViewAdapter userReportsFormActivityRecyclerViewAdapter = new UserReportsFormActivityRecyclerViewAdapter();
        this.adapter = userReportsFormActivityRecyclerViewAdapter;
        Intrinsics.checkNotNull(userReportsFormActivityRecyclerViewAdapter);
        userReportsFormActivityRecyclerViewAdapter.initDisplayOptions(this);
        setToolbar();
        setTitle(getIntent().getStringExtra("ReportName"));
        this.screenControl = new ScreenControl(this);
        setListener(new UserReportActivityListener(this) {
            final UserReportsFormActivity this0 = null;
            public void dataReady() {
                this.this0.dataInit();
            }
            public void dataError(String str) {
                Intrinsics.checkNotNullParameter(str, "errorMessage");
                Toast.makeText(this.this0, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void dataInit() {
        if (getReport() != null) {
            Report report = getReport();
            Intrinsics.checkNotNull(report);
            if (report.getReportLayout() != null) {
                Report report2 = getReport();
                Intrinsics.checkNotNull(report2);
                if (report2.getReportLayout().getCard() != null) {
                    UserReportsFormActivityRecyclerViewAdapter userReportsFormActivityRecyclerViewAdapter = this.adapter;
                    Intrinsics.checkNotNull(userReportsFormActivityRecyclerViewAdapter);
                    userReportsFormActivityRecyclerViewAdapter.setData(getData());
                    UserReportsFormActivityRecyclerViewAdapter userReportsFormActivityRecyclerViewAdapter2 = this.adapter;
                    Intrinsics.checkNotNull(userReportsFormActivityRecyclerViewAdapter2);
                    Report report3 = getReport();
                    Intrinsics.checkNotNull(report3);
                    userReportsFormActivityRecyclerViewAdapter2.setFormTemplate(report3.getReportLayout().getCard());
                    RecyclerView recyclerView = this.recyclerView;
                    Intrinsics.checkNotNull(recyclerView);
                    recyclerView.setAdapter(this.adapter);
                    return;
                }
            }
        }
        Toast.makeText(this, R.string.str_empty_list_text, Toast.LENGTH_SHORT).show();
    }
    public void onBackPressed() {
        super.onBackPressed();
        RecyclerView recyclerView = this.recyclerView;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setAdapter(null);
    }
    public void exportPdf(PdfExportOption pdfExportOption) {
        ArrayList<GenericData> data = getData();
        if (data == null || data.isEmpty()) {
            Toast.makeText(this, ContextUtils.getStringResource(R.string.str_empty_list_text), Toast.LENGTH_SHORT).show();
        } else {
            exportPdf(null, pdfExportOption);
        }
    }
}
