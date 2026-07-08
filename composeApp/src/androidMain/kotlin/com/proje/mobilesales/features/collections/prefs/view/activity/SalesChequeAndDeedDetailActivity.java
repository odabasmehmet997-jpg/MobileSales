package com.proje.mobilesales.features.collections.prefs.view.activity;

import android.R;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatTextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.collections.prefs.repository.SalesChequeAndDeedDetailRepository;
import com.proje.mobilesales.features.collections.prefs.viewmodel.SalesChequeAndDeedDetailViewModel;
import com.proje.mobilesales.features.reports.view.activity.ReportAllActivity;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import static com.proje.mobilesales.core.utils.AppUtils.alert;


/* compiled from: SalesChequeAndDeedDetailActivity.kt */

public final class SalesChequeAndDeedDetailActivity extends BaseErpActivity {
    public static final Companion Companion = new Companion(null);
    private static final int DELETE = 0;
    private static final int REPORT = 1;
    private DialogInterface.OnClickListener dialogClickListener;
    private final SalesChequeAndDeedDetailRepository repository;
    private final SalesChequeAndDeedDetailViewModel viewModel;

    public SalesChequeAndDeedDetailActivity() {
        final SalesChequeAndDeedDetailRepository salesChequeAndDeedDetailRepository = new SalesChequeAndDeedDetailRepository();
        repository = salesChequeAndDeedDetailRepository;
        viewModel = new SalesChequeAndDeedDetailViewModel(salesChequeAndDeedDetailRepository);
        dialogClickListener = new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.collections.prefs.view.activity.SalesChequeAndDeedDetailActivityExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                dialogClickListenerlambda1(SalesChequeAndDeedDetailActivity.this, dialogInterface, i2);
            }
        };
    }

    public SalesChequeAndDeedDetailRepository getRepository() {
        return repository;
    }

    public SalesChequeAndDeedDetailViewModel getViewModel() {
        return viewModel;
    }

    @Override // com.proje.mobilesales.core.base.BaseErpActivity, com.proje.mobilesales.core.base.BaseInjectableActivity, com.proje.mobilesales.core.base.BaseTrackableActivity, com.proje.mobilesales.core.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint("RestrictedApi")
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.listnosearch);
        this.setToolbar();
        final View findViewById = this.findViewById(R.id.lvList_listnosearch);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.ListView");
        final ListView listView = (ListView) findViewById;
        listView.setEmptyView(this.findViewById(R.id.noEntry));
        this.registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.proje.mobilesales.features.collections.prefs.view.activity.SalesChequeAndDeedDetailActivityExternalSyntheticLambda0
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(final AdapterView adapterView, final View view, final int i2, final long j2) {
                onCreatelambda0(SalesChequeAndDeedDetailActivity.this, adapterView, view, i2, j2);
            }
        });
        this.getFicheDetail();
    }

    
    public static void onCreatelambda0(final SalesChequeAndDeedDetailActivity this0, final AdapterView adapterView, final View v, final int i2, final long j2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(v, "v");
        final View findViewById = v.findViewById(R.id.LOGICALREF);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        MainActivity.DetailRef = StringUtils.convertStringToInt(((AppCompatTextView) findViewById).getText().toString());
        final ActionBar supportActionBar = this0.getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.openOptionsMenu();
    }

    @Override // com.proje.mobilesales.core.base.BaseErpActivity, com.proje.mobilesales.core.base.BaseInjectableActivity, com.proje.mobilesales.core.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.proje.mobilesales.core.base.BaseInjectableActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        MainActivity.DetailRef = -1;
        this.setResult(-1);
        this.finish();
    }

    private void getFicheDetail() {
        try {
            final SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.rowcsdeail, viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT LOGICALREF AS _id,* FROM CHEQUEDEEDDETAIL WHERE CHEQUEDEEDID=" + MainActivity.sFicheRef), new String[]{"TOTAL", "DUEDATE", "SPECODE", "LOGICALREF"}, new int[]{R.id.CTOTAL, R.id.CDUEDATE, R.id.CSPECODE, R.id.LOGICALREF});
            final View findViewById = this.findViewById(R.id.lvList_listnosearch);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.ListView");
            ((ListView) findViewById).setAdapter(simpleCursorAdapter);
        } catch (final Exception e2) {
            alert(e2.getMessage());
        }
    }

    
    public boolean onCreateOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        menu.add(0, 0, 0, this.getString(R.string.str_delete)).setIcon(R.drawable.ic_menu_delete);
        menu.add(0, 1, 0, this.getString(R.string.str_reports)).setIcon(R.drawable.ic_menu_directions);
        return super.onCreateOptionsMenu(menu);
    }

    
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        final int itemId = item.getItemId();
        if (0 != itemId) {
            if (1 == itemId) {
                this.startActivityForResult(new Intent(this, ReportAllActivity.class), 0);
            } else if (16908332 == itemId) {
                MainActivity.DetailRef = -1;
                this.setResult(-1);
                this.finish();
                return true;
            }
        } else {
            if (!this.isSelectDetail()) {
                return true;
            }
            new AlertDialog.Builder(this).setMessage(this.getString(R.string.str_question_want_delete)).setPositiveButton(this.getString(R.string.str_yes), dialogClickListener).setNegativeButton(this.getString(R.string.str_no), dialogClickListener).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public DialogInterface.OnClickListener getDialogClickListener() {
        return dialogClickListener;
    }

    public void setDialogClickListener(final DialogInterface.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "<set-?>");
        dialogClickListener = onClickListener;
    }

    
    public static void dialogClickListenerlambda1(final SalesChequeAndDeedDetailActivity this0, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (-1 != i2) {
            return;
        }
        this0.deleteFicheDetailLine();
        this0.getFicheDetail();
        this0.viewModel.getBaseErp().getLogoSqlHelper().cekSenetToplamlariGuncelle();
        MainActivity.DetailRef = -1;
    }

    private void deleteFicheDetailLine() {
        viewModel.getBaseErp().getLogoSqlBriteDatabase().execute("DELETE FROM CHEQUEDEEDDETAIL WHERE LOGICALREF=" + MainActivity.DetailRef);
    }

    private boolean isSelectDetail() {
        if (0 < MainActivity.DetailRef) {
            return true;
        }
        Toast.makeText(this, this.getString(R.string.str_question_select_detail), 1).show();
        return false;
    }

    /* compiled from: SalesChequeAndDeedDetailActivity.kt */
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
