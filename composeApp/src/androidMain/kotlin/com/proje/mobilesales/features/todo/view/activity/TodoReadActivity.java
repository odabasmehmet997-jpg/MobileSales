package com.proje.mobilesales.features.todo.view.activity;

import android.R;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.material.card.MaterialCardViewHelper;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivityPreferences;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.ColType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FType;
import com.proje.mobilesales.core.enums.MobileSalesUpdateType;
import com.proje.mobilesales.core.enums.WorkTimeControlProcessType;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.interfaces.di.ActivityComponent;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.preferences.EditTextPref;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.customer.view.general.CustomerActivity;
import com.proje.mobilesales.features.settings.interfaces.PreferenceClear;
import com.proje.mobilesales.features.todo.repository.BaseTodoRepository;
import com.proje.mobilesales.features.todo.viewmodel.BaseTodoViewModel;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class TodoReadActivity extends BaseErpActivityPreferences implements SharedPreferences.OnSharedPreferenceChangeListener, PreferenceClear {
    private static final int CANCEL = 1;
    public static final Companion Companion = new Companion(null);
    private static final int GO_CUSTOMER = 2;
    private static final int SAVE = 0;
    private static final String TAG;
    private String customerCode;
    private int customerId;
    private Object etCurr;
    private EditTextPref etNote;
    private ListPreference lpDutyStatus;
    private ProgressDialogBuilder<?> mProgressDialogBuilder;
    private int messageRef;
    private int newStatus;
    private int oldStatus;
    private final BaseTodoRepository repository;
    private SharedPreferences f1279sp;
    private final BaseTodoViewModel viewModel;
    public TodoReadActivity() {
        BaseTodoRepository baseTodoRepository = new BaseTodoRepository();
        this.repository = baseTodoRepository;
        this.viewModel = new BaseTodoViewModel(baseTodoRepository);
        this.newStatus = -1;
        this.customerCode = "";
    }
    public BaseTodoRepository getRepository() {
        return this.repository;
    }
    public BaseTodoViewModel getViewModel() {
        return this.viewModel;
    }
    public SharedPreferences getSp() {
        return this.f1279sp;
    }
    public void setSp(SharedPreferences sharedPreferences) {
        this.f1279sp = sharedPreferences;
    }
    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        return this.mProgressDialogBuilder;
    }
    public void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        this.mProgressDialogBuilder = progressDialogBuilder;
    }
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityComponent activityComponent = getActivityComponent();
        Intrinsics.checkNotNull(activityComponent);
        activityComponent.inject(this);
        Context context = ContextUtils.getmContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(context, (BaseInjectableActivity) activity);
        this.messageRef = getIntent().getIntExtra(IntentExtraName.EXTRAS_MESSAGE_REF, 0);
        addPreferencesFromResource(R.xml.todoread);
        this.f1279sp = PreferenceManager.getDefaultSharedPreferences(this);
        createControls();
        getDutyInformation();
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView adapterView, View view, int i2, long j2) {
                boolean onCreatelambda0;
                onCreatelambda0 = TodoReadActivity.onCreatelambda0(TodoReadActivity.this, adapterView, view, i2, j2);
                return onCreatelambda0;
            }
        });
    }
    public static boolean onCreatelambda0(TodoReadActivity this0, AdapterView adapterView, View view, int i2, long j2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNull(adapterView, "null cannot be cast to non-null type android.widget.ListView");
        Object item = ((ListView) adapterView).getAdapter().getItem(i2);
        this0.etCurr = item;
        if (item == null || !(item instanceof View.OnLongClickListener)) {
            return false;
        }
        Intrinsics.checkNotNull(item, "null cannot be cast to non-null type android.view.View.OnLongClickListener");
        return ((View.OnLongClickListener) item).onLongClick(view);
    }
    private void getDutyInformation() {
        if (this.messageRef > 0) {
            Cursor rawQuery = this.viewModel.getSqlHelper().getReadableDatabase().rawQuery("\n            SELECT *,\n            (CASE PRIORITY WHEN 0 THEN 'Acil' WHEN 1 THEN 'Normal' WHEN 2 THEN 'Dusuk' ELSE 'Acil' END) AS PRIORITYSTR \n            FROM TODOINFO \n            WHERE LOGICALREF=?\n        ", new String[]{String.valueOf(this.messageRef)});
            Intrinsics.checkNotNullExpressionValue(rawQuery, "rawQuery(...)");
            if (rawQuery.moveToFirst()) {
                Preference findPreference = findPreference("pTarih");
                if (findPreference != null) {
                    StringBuilder sb = new StringBuilder();
                    ISqlHelper<?> sqlHelper = this.viewModel.getSqlHelper();
                    ColType colType = ColType.metin;
                    sb.append(sqlHelper.dbVal(rawQuery, "BEGDATE", colType));
                    sb.append(" / ");
                    sb.append(this.viewModel.getSqlHelper().dbVal(rawQuery, "ENDDATE", colType));
                    findPreference.setSummary(sb.toString());
                }
                Preference findPreference2 = findPreference("pKimden");
                if (findPreference2 != null) {
                    findPreference2.setSummary(this.viewModel.getSqlHelper().dbVal(rawQuery, "SENDER", ColType.metin).toString());
                }
                Preference findPreference3 = findPreference("pDurum");
                if (findPreference3 != null) {
                    findPreference3.setSummary(this.viewModel.getSqlHelper().dbVal(rawQuery, "PRIORITYSTR", ColType.metin).toString());
                }
                Preference findPreference4 = findPreference("pAciklama");
                if (findPreference4 != null) {
                    findPreference4.setSummary(this.viewModel.getSqlHelper().dbVal(rawQuery, "DESC_", ColType.metin).toString());
                }
                Preference findPreference5 = findPreference("pDetay");
                if (findPreference5 != null) {
                    findPreference5.setSummary(this.viewModel.getSqlHelper().dbVal(rawQuery, "NOTE", ColType.metin).toString());
                }
                EditTextPref editTextPref = this.etNote;
                if (editTextPref != null) {
                    editTextPref.setText(this.viewModel.getSqlHelper().dbVal(rawQuery, "USERNOTE", ColType.metin).toString());
                }
                ISqlHelper<?> sqlHelper2 = this.viewModel.getSqlHelper();
                ColType colType2 = ColType.sayi;
                int parseInt = Integer.parseInt(sqlHelper2.dbVal(rawQuery, "STATUS", colType2).toString());
                this.oldStatus = parseInt;
                ListPreference listPreference = this.lpDutyStatus;
                if (listPreference != null) {
                    listPreference.setValue(String.valueOf(parseInt));
                }
                Preferences.initSummary(findPreference);
                Preferences.initSummary(findPreference2);
                Preferences.initSummary(findPreference3);
                Preferences.initSummary(findPreference4);
                Preferences.initSummary(findPreference5);
                Preferences.initSummary(this.etNote);
                this.customerId = Integer.parseInt(this.viewModel.getSqlHelper().dbVal(rawQuery, "CLREF", colType2).toString());
                this.customerCode = this.viewModel.getSqlHelper().dbVal(rawQuery, "CLCODE", ColType.metin).toString();
                Log.i("CUSTOMERID", "GorevBilgileriniGetir: " + this.customerId);
            }
            rawQuery.close();
        }
        Preferences.initSummary(this.lpDutyStatus);
        invalidateOptionsMenu();
    }
    protected void onActivityResult(int i2, int i3, Intent data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (i2 == 1234 && i3 == -1) {
            ArrayList<String> stringArrayListExtra = data.getStringArrayListExtra("android.speech.extra.RESULTS");
            try {
                EditTextPref editTextPref = (EditTextPref) this.etCurr;
                Intrinsics.checkNotNull(editTextPref);
                Intrinsics.checkNotNull(stringArrayListExtra);
                editTextPref.setText(stringArrayListExtra.get(0));
                editTextPref.setSummary(stringArrayListExtra.get(0));
                Preferences.initSummary(editTextPref);
            } catch (Exception e2) {
                Log.e(TAG, "onActivityResult: ", e2);
            }
        }
    }
    private void createControls() {
        Preference findPreference = findPreference("lpGorevDurumu");
        ListPreference listPreference = findPreference instanceof ListPreference ? (ListPreference) findPreference : null;
        if (listPreference != null) {
            listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                public boolean onPreferenceChange(Preference preference, Object obj) {
                    boolean createControlslambda1;
                    createControlslambda1 = TodoReadActivity.createControlslambda1(TodoReadActivity.this, preference, obj);
                    return createControlslambda1;
                }
            });
        }
        this.lpDutyStatus = listPreference;
        Preference findPreference2 = findPreference("etNot");
        this.etNote = findPreference2 instanceof EditTextPref ? (EditTextPref) findPreference2 : null;
    }
    public static boolean createControlslambda1(TodoReadActivity this0, Preference preference, Object obj) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.newStatus = StringUtils.convertStringToInt(obj.toString());
        return true;
    }
    private Preference.OnPreferenceClickListener getOnPreferenceClickListener(final String str) {
        return new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                boolean onPreferenceClickListenerlambda2;
                onPreferenceClickListenerlambda2 = TodoReadActivity.getOnPreferenceClickListenerlambda2(TodoReadActivity.this, str, preference);
                return onPreferenceClickListenerlambda2;
            }
        };
    }
    public static boolean getOnPreferenceClickListenerlambda2(TodoReadActivity this0, String s, Preference preference) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(s, "s");
        Toast.makeText(this0, s, Toast.LENGTH_SHORT).show();
        return false;
    }
    public void onDestroy() {
        super.onDestroy();
    }
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        Preferences.updatePrefSummary(findPreference(str));
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        MenuItem add = menu.add(0, 2, 100, getString(R.string.str_go_to_customer));
        menu.add(0, 0, 200, getString(R.string.str_save)).setIcon(R.drawable.ic_menu_save);
        menu.add(0, 1, MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION, getString(R.string.str_cancel)).setIcon(R.drawable.ic_menu_close_clear_cancel);
        add.setVisible(this.customerId != -1);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        int itemId = item.getItemId();
        if (itemId == 0) {
            save();
        } else if (itemId == 1) {
            clearEditor();
            finish();
        } else if (itemId == 2) {
            goToCustomer();
        } else if (itemId == 16908332) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void goToCustomer() {
        startActivity(new Intent(getApplicationContext(), CustomerActivity.class).putExtra(CustomerActivity.EXTRA_CUSTOMER_CODE, this.viewModel.erpType() == ErpType.NETSIS ? this.customerCode : this.viewModel.getSqlHelper().getClCode(this.customerId)));
    }
    public boolean onKeyDown(int i2, KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (i2 == 4) {
            clearEditor();
            finish();
            return true;
        }
        return super.onKeyDown(i2, event);
    }
    private void save() {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
        this.viewModel.getBaseErp().checkRemoteWorkTimeControl(WorkTimeControlProcessType.DataEntry, new CheckWorkTimeListener(this));
    }

    private record CheckWorkTimeListener(
            WeakReference<TodoReadActivity> mActivity) implements ResponseListener<String> {
            private CheckWorkTimeListener(TodoReadActivity mActivity) {
                this.mActivity = new WeakReference<>(mActivity);
            }

            public void onResponse(PrintSlipModel str) {
                if (this.mActivity.get() != null) {
                    TodoReadActivity todoReadActivity = this.mActivity.get();
                    Intrinsics.checkNotNull(todoReadActivity);
                    ProgressDialogBuilder<?> mProgressDialogBuilder = todoReadActivity.getMProgressDialogBuilder();
                    Intrinsics.checkNotNull(mProgressDialogBuilder);
                    mProgressDialogBuilder.dismiss();
                    if (!TextUtils.isEmpty(str)) {
                        Toast.makeText(this.mActivity.get(), str, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    TodoReadActivity todoReadActivity2 = this.mActivity.get();
                    Intrinsics.checkNotNull(todoReadActivity2);
                    todoReadActivity2.updateDuty();
                    TodoReadActivity todoReadActivity3 = this.mActivity.get();
                    Intrinsics.checkNotNull(todoReadActivity3);
                    todoReadActivity3.clearEditor();
                    TodoReadActivity todoReadActivity4 = this.mActivity.get();
                    Intrinsics.checkNotNull(todoReadActivity4);
                    todoReadActivity4.finish();
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mActivity.get() != null) {
                    TodoReadActivity todoReadActivity = this.mActivity.get();
                    Intrinsics.checkNotNull(todoReadActivity);
                    ProgressDialogBuilder<?> mProgressDialogBuilder = todoReadActivity.getMProgressDialogBuilder();
                    Intrinsics.checkNotNull(mProgressDialogBuilder);
                    mProgressDialogBuilder.dismiss();
                }
            }
        }
    public void updateDuty() {
        ContentValues contentValues = new ContentValues();
        SharedPreferences sharedPreferences = this.f1279sp;
        Intrinsics.checkNotNull(sharedPreferences);
        contentValues.put("USERNOTE", sharedPreferences.getString("etNot", ""));
        SharedPreferences sharedPreferences2 = this.f1279sp;
        Intrinsics.checkNotNull(sharedPreferences2);
        contentValues.put("STATUS", Integer.valueOf(StringUtils.convertStringToInt(sharedPreferences2.getString("lpGorevDurumu", "0"))));
        if (this.oldStatus != this.newStatus) {
            contentValues.put("ISTRANSFER", "0");
        }
        contentValues.put("LATITUDE", Double.valueOf(ContextUtils.getLongitude()));
        contentValues.put("LONGTITUDE", Double.valueOf(ContextUtils.getLongitude()));
        contentValues.put("ISTRANSFERWORPROC", "0");
        this.viewModel.getSqlHelper().getWritableDatabase().update("TODOINFO", contentValues, "LOGICALREF=" + this.messageRef, null);
        this.viewModel.updateDataLogo(MobileSalesUpdateType.TODO, this.messageRef);
        this.viewModel.getBaseErp().insertFicheBroadcastMessage(this.messageRef, FType.Gorev.ordinal());
    }
    public void clearEditor() {
        SharedPreferences sharedPreferences = this.f1279sp;
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        EditTextPref editTextPref = this.etNote;
        Intrinsics.checkNotNull(editTextPref);
        edit.remove(editTextPref.getKey());
        ListPreference listPreference = this.lpDutyStatus;
        Intrinsics.checkNotNull(listPreference);
        edit.remove(listPreference.getKey());
        edit.apply();
    }
    public static final class Companion {
        public   Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
    static {
        String simpleName = TodoReadActivity.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        TAG = simpleName;
    }
}
