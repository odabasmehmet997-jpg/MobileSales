package com.proje.mobilesales.features.userreport.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.reportparser.Report;
import com.proje.mobilesales.core.reportparser.ReportConstParamProp;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.view.ScreenControl;
import com.proje.mobilesales.core.view.SnappyLinearLayoutManager;
import com.proje.mobilesales.core.widget.SlideInLeftAnimator;
import com.proje.mobilesales.features.userreport.adapter.UserReportsParametersRecyclerViewAdapter;
import com.proje.mobilesales.features.userreport.repository.UserReportRepository;
import com.proje.mobilesales.features.userreport.viewmodel.UserReportViewModel;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UserReportsParametersActivity.kt */

public final class UserReportsParametersActivity extends BaseErpActivity {
    private UserReportsParametersRecyclerViewAdapter adapter;
    public AlertDialogBuilder<?> mAlertDialogBuilder;
    private int mCustomerRef;
    private int mItemRef;
    private boolean mShowMailButton;
    private RecyclerView recyclerView;
    private Report report;
    private Button runReportButton;
    private ScreenControl screenControl;
    private final UserReportRepository repository = new UserReportRepository();
    private final UserReportViewModel viewModel = new UserReportViewModel(this.repository);

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

    
    @Override // com.proje.mobilesales.core.base.BaseErpActivity, com.proje.mobilesales.core.base.BaseInjectableActivity, com.proje.mobilesales.core.base.BaseTrackableActivity, com.proje.mobilesales.core.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        Context context = ContextUtils.getmContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMAlertDialogBuilder(new AlertDialogBuilder.Impl(context, (BaseInjectableActivity) activity));
        getWindow().setSoftInputMode(3);
        int intExtra = getIntent().getIntExtra("bigdata:synccode", -1);
        ReportConstParamProp reportConstParamProp = (ReportConstParamProp) getIntent().getSerializableExtra("ReportConstParamProp");
        this.mCustomerRef = getIntent().getIntExtra(IntentExtraName.EXTRA_CUSTOMER_REF, -9999);
        this.mShowMailButton = getIntent().getBooleanExtra("ShowMailButton", false);
        this.mItemRef = getIntent().getIntExtra(IntentExtraName.EXTRA_ITEM_ID, -9999);
        Object object = this.viewModel.getBaseErp().getObject(intExtra, false);
        Report report = object instanceof Report ? (Report) object : null;
        this.report = report;
        if (report == null) {
            Toast.makeText(this, getString(R.string.exp_85_error_invalid_report_format), Toast.LENGTH_LONG).show();
            super.onBackPressed();
            return;
        }
        setContentView(R.layout.userreports_parameters);
        RecyclerView recyclerView = findViewById(R.id.rwParametersListView);
        this.recyclerView = recyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new SnappyLinearLayoutManager(this));
        }
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 != null) {
            recyclerView2.setHasFixedSize(true);
        }
        RecyclerView recyclerView3 = this.recyclerView;
        if (recyclerView3 != null) {
            recyclerView3.setItemAnimator(new SlideInLeftAnimator(new OvershootInterpolator(2.0f)));
        }
        UserReportsParametersRecyclerViewAdapter userReportsParametersRecyclerViewAdapter = new UserReportsParametersRecyclerViewAdapter();
        this.adapter = userReportsParametersRecyclerViewAdapter;
        Intrinsics.checkNotNull(userReportsParametersRecyclerViewAdapter);
        userReportsParametersRecyclerViewAdapter.initDisplayOptions(this);
        UserReportsParametersRecyclerViewAdapter userReportsParametersRecyclerViewAdapter2 = this.adapter;
        Intrinsics.checkNotNull(userReportsParametersRecyclerViewAdapter2);
        Report report2 = this.report;
        Intrinsics.checkNotNull(report2);
        userReportsParametersRecyclerViewAdapter2.setReportParams(report2.getReportParams());
        UserReportsParametersRecyclerViewAdapter userReportsParametersRecyclerViewAdapter3 = this.adapter;
        Intrinsics.checkNotNull(userReportsParametersRecyclerViewAdapter3);
        userReportsParametersRecyclerViewAdapter3.setExtraParams(this.mCustomerRef, this.mItemRef, reportConstParamProp);
        RecyclerView recyclerView4 = this.recyclerView;
        if (recyclerView4 != null) {
            recyclerView4.setAdapter(this.adapter);
        }
        this.runReportButton = findViewById(R.id.run_query_button);
        setToolbar();
        ActionBar supportActionBar = getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setDisplayHomeAsUpEnabled(false);
        this.screenControl = new ScreenControl(this);
        Button button = this.runReportButton;
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.userreport.view.activity.UserReportsParametersActivityExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    UserReportsParametersActivity.onCreatelambda0(UserReportsParametersActivity.this, view);
                }
            });
        }
    }

    
    public static void onCreatelambda0(UserReportsParametersActivity userReportsParametersActivity, View view) {
        Intrinsics.checkNotNullParameter(userReportsParametersActivity, "this0");
        userReportsParametersActivity.runQuery();
    }

    private void runQuery() {
        Intent intent = new Intent();
        intent.putExtra("bigdata:synccode", getIntent().getIntExtra("bigdata:synccode", -1));
        intent.putExtra("ReportName", getIntent().getStringExtra("ReportName"));
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, this.mCustomerRef);
        intent.putExtra("ShowMailButton", this.mShowMailButton);
        setResult(-1, intent);
        finish();
    }

    
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        MenuInflater menuInflater = getMenuInflater();
        Intrinsics.checkNotNullExpressionValue(menuInflater, "getMenuInflater(...)");
        menuInflater.inflate(R.menu.menu_user_reports, menu);
        menu.findItem(R.id.menu_send_mail).setVisible(false);
        menu.findItem(R.id.menu_pdf).setVisible(false);
        menu.findItem(R.id.menu_run_query).setVisible(false);
        menu.findItem(R.id.menu_filter).setVisible(false);
        menu.findItem(R.id.action_pdf).setVisible(false);
        menu.findItem(R.id.action_excel).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (menuItem.getItemId() != R.id.menu_close) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return false;
    }

    public boolean paramsControl() {
        StringBuilder sb = new StringBuilder();
        RecyclerView recyclerView = this.recyclerView;
        Intrinsics.checkNotNull(recyclerView);
        int childCount = recyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            RecyclerView recyclerView2 = this.recyclerView;
            Intrinsics.checkNotNull(recyclerView2);
            UserReportsParametersRecyclerViewAdapter.ItemViewHolder itemViewHolder = (UserReportsParametersRecyclerViewAdapter.ItemViewHolder) recyclerView2.findViewHolderForAdapterPosition(i2);
            if (itemViewHolder != null && Intrinsics.areEqual(String.valueOf(itemViewHolder.getTxtValue().getText()), "")) {
                sb.append(itemViewHolder.getTxtCaption().getText() + ", ");
            }
        }
        if (sb.length() <= 0) {
            return true;
        }
        AlertDialogBuilder title = getMAlertDialogBuilder().setTitle((int) R.string.str_required_fields);
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        int length = sb2.length() - 1;
        int i3 = 0;
        boolean z = false;
        while (i3 <= length) {
            boolean z2 = Intrinsics.compare(sb2.charAt(!z ? i3 : length), 32) <= 0;
            if (!z) {
                if (!z2) {
                    z = true;
                } else {
                    i3++;
                }
            } else if (!z2) {
                break;
            } else {
                length--;
            }
        }
        String obj = sb2.subSequence(i3, length + 1).toString();
        String sb3 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb3, "toString(...)");
        int length2 = sb3.length() - 1;
        int i4 = 0;
        boolean z3 = false;
        while (i4 <= length2) {
            boolean z4 = Intrinsics.compare(sb3.charAt(!z3 ? i4 : length2), 32) <= 0;
            if (!z3) {
                if (!z4) {
                    z3 = true;
                } else {
                    i4++;
                }
            } else if (!z4) {
                break;
            } else {
                length2--;
            }
        }
        String substring = obj.substring(0, sb3.subSequence(i4, length2 + 1).toString().length() - 1);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        title.setMessage(substring).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i5) {
                UserReportsParametersActivity.paramsControllambda3(dialogInterface, i5);
            }
        }).show();
        return false;
    }
    public static void paramsControllambda3(DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        if (i2 == -1) {
            dialogInterface.dismiss();
        }
        dialogInterface.dismiss();
    }
   public void onStop() {
        super.onStop();
    }

    public void onBackPressed() {
        super.onBackPressed();
        RecyclerView recyclerView = this.recyclerView;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setAdapter(null);
    }

    public void hideKeyboard() {
        ScreenControl screenControl = this.screenControl;
        Intrinsics.checkNotNull(screenControl);
        screenControl.hideKeyboard(this);
    }
}
