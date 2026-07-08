package com.proje.mobilesales.features.todo.view.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.widget.AppCompatTextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.MobileSalesUpdateType;
import com.proje.mobilesales.core.event.ResponseEvent;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.todo.repository.BaseTodoRepository;
import com.proje.mobilesales.features.todo.viewmodel.BaseTodoViewModel;
import kotlin.jvm.internal.Intrinsics;

import static com.proje.mobilesales.core.utils.AppUtils.alert;
import static org.greenrobot.eventbus.EventBus.getDefault;

public final class TodoListActivity extends BaseErpActivity {
    private ProgressDialogBuilder<?> mProgressDialogBuilder;
    private int messageRef;
    private final BaseTodoRepository repository;
    private MenuItem todoSend;
    private final BaseTodoViewModel viewModel;
    public TodoListActivity() {
        BaseTodoRepository baseTodoRepository = new BaseTodoRepository();
        this.repository = baseTodoRepository;
        this.viewModel = new BaseTodoViewModel(baseTodoRepository);
    }
    public BaseTodoRepository getRepository() {
        return this.repository;
    }
    public BaseTodoViewModel getViewModel() {
        return this.viewModel;
    }
    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        return this.mProgressDialogBuilder;
    }
    public void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        this.mProgressDialogBuilder = progressDialogBuilder;
    }
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        setContentView(R.layout.listnosearch);
        setToolbar();
        Context context = ContextUtils.getmContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(context, (BaseInjectableActivity) activity);
        this.messageRef = getIntent().getIntExtra(IntentExtraName.EXTRAS_MESSAGE_REF, 0);
        View findViewById = findViewById(R.id.lvList_listnosearch);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.ListView");
        ListView listView = (ListView) findViewById;
        listView.setEmptyView(findViewById(R.id.noEntry));
        listView.setOnItemClickListener((adapterView, view, i2, j2) -> TodoListActivity.onCreatelambda0(TodoListActivity.this, adapterView, view, i2, j2));
        getDutyList();
    }
    public static void onCreatelambda0(TodoListActivity this0, AdapterView adapterView, View view, int i2, long j2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        View findViewById = view.findViewById(R.id.TODOID);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        int convertStringToInt = StringUtils.convertStringToInt(((AppCompatTextView) findViewById).getText().toString());
        this0.viewModel.getSqlHelper().getReadableDatabase().execSQL("UPDATE TODOINFO SET ISREAD=1 WHERE LOGICALREF=" + convertStringToInt);
        Intent intent = new Intent(this0, TodoReadActivity.class);
        intent.putExtra(IntentExtraName.EXTRAS_MESSAGE_REF, convertStringToInt);
        this0.startActivityForResult(intent, 0);
    }
    private void getDutyList() {
        try {
            String[] strArr = {"LOGICALREF", "BEGDATE", "ENDDATE", "DESC_", "STATUSSTR", "PRIORITYSTR"};
            String str = "SELECT LOGICALREF _id,LOGICALREF,(CASE STATUS WHEN 0 THEN 'Henuz Baslamadi' WHEN 1 THEN 'Devam Ediyor' WHEN 2 THEN 'Tamamlandi' WHEN 3 THEN 'Red Edildi' ELSE 'Henuz Baslamadi' END) AS STATUSSTR,(CASE PRIORITY WHEN 0 THEN 'Acil' WHEN 1 THEN 'Normal' WHEN 2 THEN 'Dusuk' ELSE 'Tumu' END) AS PRIORITYSTR,BEGDATE,ENDDATE,DESC_ FROM TODOINFO WHERE ISTRANSFER=0 ";
            if (this.messageRef > 0) {
                str = str + " AND CLREF=" + this.messageRef;
            }
            Cursor rawQuery = this.viewModel.getSqlHelper().getReadableDatabase().rawQuery(str, null);
            Intrinsics.checkNotNullExpressionValue(rawQuery, "rawQuery(...)");
            View findViewById = findViewById(R.id.NAME);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            AppCompatTextView appCompatTextView = (AppCompatTextView) findViewById;
            if (rawQuery.getCount() <= 0) {
                appCompatTextView.setVisibility(View.VISIBLE);
            } else {
                appCompatTextView.setVisibility(View.GONE);
            }
            SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.rowtodolist, rawQuery, strArr, new int[]{R.id.TODOID, R.id.TBEGDATE, R.id.TENDDATE, R.id.TDESC, R.id.TSTATUSSTR, R.id.TPRIORITYSTR});
            View findViewById2 = findViewById(R.id.lvList_listnosearch);
            Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.ListView");
            ((ListView) findViewById2).setAdapter(simpleCursorAdapter);
        } catch (Exception e2) {
            alert(e2.getMessage());
        }
    }
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    protected void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        getDutyList();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        this.todoSend = menu.add(0, 0, 200, getString(R.string.str_send));
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            finish();
        } else if (item.getItemId() == 0) {
            this.mProgressDialogBuilder.setMessage(getString(R.string.str_todo_sending)).show();
            this.baseErp.updateDataLogo(MobileSalesUpdateType.TODO, this.messageRef);
        }
        return super.onOptionsItemSelected(item);
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE})
    public void onStart() {
        super.onStart();
        getDefault().register(this);
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE})
    public void onStop() {
        super.onStop();
        getDefault().unregister(this);
         
    }
    public void responseEvent(ResponseEvent responseEvent) {
        Intrinsics.checkNotNullParameter(responseEvent, "responseEvent");
        this.mProgressDialogBuilder.dismiss();
        if (responseEvent.isSuccess()) {
            Toast.makeText(this, getString(R.string.str_todo_send_success), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, getString(R.string.str_todo_send_failed), Toast.LENGTH_LONG).show();
        }
        getDutyList();
    }
}
