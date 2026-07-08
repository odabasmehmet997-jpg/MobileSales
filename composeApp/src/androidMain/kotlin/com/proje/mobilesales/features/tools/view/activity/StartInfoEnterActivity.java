package com.proje.mobilesales.features.tools.view.activity;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivityPreferences;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.event.ResponseEvent;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.di.ActivityComponent;
import com.proje.mobilesales.core.preferences.EditTextPref;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.settings.interfaces.PreferenceClear;
import com.proje.mobilesales.features.tools.model.database.StartInfo;
import com.proje.mobilesales.features.tools.repository.StartInfoEnterRepository;
import com.proje.mobilesales.features.tools.viewmodel.StartInfoEnterViewModel;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;


public final class StartInfoEnterActivity extends BaseErpActivityPreferences implements SharedPreferences.OnSharedPreferenceChangeListener, PreferenceClear {
    private static final int CANCEL = 1;
    public static final Companion Companion = new Companion(null);
    private static final int SAVE = 0;
    private static final String TAG;
    private EditTextPreference etBasKM;
    private Object etCurr;
    private EditTextPref etNot;
    private EditTextPreference etPlaka;
    private EditTextPreference etSonKM;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    private SharedPreferences f1282sp;
    private final StartInfoEnterRepository repository = new StartInfoEnterRepository();
    private final StartInfoEnterViewModel viewModel = new StartInfoEnterViewModel(repository);
    public SharedPreferences getSp() {
        return f1282sp;
    }
    public void setSp(final SharedPreferences sharedPreferences) {
        f1282sp = sharedPreferences;
    }
    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        final ProgressDialogBuilder<?> progressDialogBuilder = mProgressDialogBuilder;
        if (null != progressDialogBuilder) {
            return progressDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
        return null;
    }
    public void setMProgressDialogBuilder(final ProgressDialogBuilder<?> progressDialogBuilder) {
        Intrinsics.checkNotNullParameter(progressDialogBuilder, "<set-?>");
        mProgressDialogBuilder = progressDialogBuilder;
    }
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final ActivityComponent activityComponent = this.getActivityComponent();
        Intrinsics.checkNotNull(activityComponent);
        activityComponent.inject(this);
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.setMProgressDialogBuilder(new ProgressDialogBuilder.Impl(this, (BaseInjectableActivity) activity));
        this.addPreferencesFromResource(R.xml.preference_start_info_enter);
        f1282sp = PreferenceManager.getDefaultSharedPreferences(this);
        this.createControls();
        this.getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.proje.mobilesales.features.tools.view.activity.StartInfoEnterActivityExternalSyntheticLambda0
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(final AdapterView adapterView, final View view, final int i2, final long j2) {
                final boolean onCreatelambda0;
                onCreatelambda0 = onCreatelambda0(StartInfoEnterActivity.this, adapterView, view, i2, j2);
                return onCreatelambda0;
            }
        });
    }
    public static boolean onCreatelambda0(final StartInfoEnterActivity this0, final AdapterView adapterView, final View view, final int i2, final long j2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNull(adapterView, "null cannot be cast to non-null type android.widget.ListView");
        final Object item = ((ListView) adapterView).getAdapter().getItem(i2);
        this0.etCurr = item;
        if (null == item || !(item instanceof View.OnLongClickListener)) {
            return false;
        }
        Intrinsics.checkNotNull(item, "null cannot be cast to non-null type android.view.View.OnLongClickListener");
        return ((View.OnLongClickListener) item).onLongClick(view);
    }
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
    protected void onActivityResult(final int i2, final int i3, final Intent data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (1234 == i2 && -1 == i3) {
            final ArrayList<String> stringArrayListExtra = data.getStringArrayListExtra("android.speech.extra.RESULTS");
            try {
                final EditTextPref editTextPref = (EditTextPref) etCurr;
                Intrinsics.checkNotNull(editTextPref);
                Intrinsics.checkNotNull(stringArrayListExtra);
                editTextPref.setText(stringArrayListExtra.get(0));
                editTextPref.setSummary(stringArrayListExtra.get(0));
                Preferences.initSummary(editTextPref);
            } catch (final Exception e2) {
                Log.e(StartInfoEnterActivity.TAG, "onActivityResult: ", e2);
            }
        }
    }
    private void createControls() {
        final Preference findPreference = this.findPreference("etPlaka");
        Intrinsics.checkNotNull(findPreference, "null cannot be cast to non-null type android.preference.EditTextPreference");
        etPlaka = (EditTextPreference) findPreference;
        final Preference findPreference2 = this.findPreference("etBasKM");
        Intrinsics.checkNotNull(findPreference2, "null cannot be cast to non-null type android.preference.EditTextPreference");
        etBasKM = (EditTextPreference) findPreference2;
        final Preference findPreference3 = this.findPreference("etSonKM");
        Intrinsics.checkNotNull(findPreference3, "null cannot be cast to non-null type android.preference.EditTextPreference");
        etSonKM = (EditTextPreference) findPreference3;
        final Preference findPreference4 = this.findPreference("etNot");
        Intrinsics.checkNotNull(findPreference4, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.EditTextPref");
        etNot = (EditTextPref) findPreference4;
        Preferences.updatePrefSummary(etPlaka);
        Preferences.updatePrefSummary(etBasKM);
        Preferences.updatePrefSummary(etSonKM);
        Preferences.updatePrefSummary(etNot);
    }
    public void onDestroy() {
        super.onDestroy();
    }
    protected void onResume() {
        super.onResume();
        this.getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }
    protected void onPause() {
        super.onPause();
        this.getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
    public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String str) {
        Preferences.updatePrefSummary(this.findPreference(str));
    }
    public boolean onCreateOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        menu.add(0, 0, 100, this.getString(R.string.str_save)).setIcon(R.drawable.ic_menu_save);
        menu.add(0, 1, 200, this.getString(R.string.str_cancel)).setIcon(R.drawable.ic_menu_close_clear_cancel);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        final int itemId = item.getItemId();
        if (0 == itemId) {
            this.save();
        } else if (1 == itemId) {
            this.clearEditor();
            this.finish();
        } else if (16908332 == itemId) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onKeyDown(final int i2, final KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (4 == i2) {
            this.clearEditor();
            this.finish();
            return true;
        }
        return super.onKeyDown(i2, event);
    }
    private void save() {
        final SharedPreferences sharedPreferences = f1282sp;
        Intrinsics.checkNotNull(sharedPreferences);
        final String string = sharedPreferences.getString("etPlaka", "");
        final SharedPreferences sharedPreferences2 = f1282sp;
        Intrinsics.checkNotNull(sharedPreferences2);
        final int convertStringToInt = StringUtils.convertStringToInt(sharedPreferences2.getString("etBasKM", ""));
        final SharedPreferences sharedPreferences3 = f1282sp;
        Intrinsics.checkNotNull(sharedPreferences3);
        final int convertStringToInt2 = StringUtils.convertStringToInt(sharedPreferences3.getString("etSonKM", ""));
        Intrinsics.checkNotNull(string);
        int length = string.length() - 1;
        int i2 = 0;
        boolean z = false;
        while (i2 <= length) {
            final boolean z2 = 0 >= Intrinsics.compare(string.charAt(!z ? i2 : length), 32);
            if (z) {
                if (!z2) {
                    break;
                } else {
                    length--;
                }
            } else if (z2) {
                i2++;
            } else {
                z = true;
            }
        }
        if (0 == string.subSequence(i2, length + 1).toString().length()) {
            Toast.makeText(this, this.getString(R.string.str_question_enter_plate_info), Toast.LENGTH_SHORT).show();
            return;
        }
        if (convertStringToInt >= convertStringToInt2) {
            Toast.makeText(this, this.getString(R.string.str_start_end_km_error), Toast.LENGTH_SHORT).show();
            final SharedPreferences sharedPreferences4 = f1282sp;
            Intrinsics.checkNotNull(sharedPreferences4);
            final SharedPreferences.Editor edit = sharedPreferences4.edit();
            edit.remove("etBasKM");
            edit.remove("etSonKM");
            edit.apply();
            return;
        }
        this.sendStartInfo();
    }
    private void sendStartInfo() {
        final StartInfo startInfo = new StartInfo();
        startInfo.date = DateAndTimeUtils.getDateSqlYearAndHour();
        final SharedPreferences sharedPreferences = f1282sp;
        Intrinsics.checkNotNull(sharedPreferences);
        startInfo.plate = sharedPreferences.getString("etPlaka", "");
        final SharedPreferences sharedPreferences2 = f1282sp;
        Intrinsics.checkNotNull(sharedPreferences2);
        startInfo.note = sharedPreferences2.getString("etNot", "");
        final SharedPreferences sharedPreferences3 = f1282sp;
        Intrinsics.checkNotNull(sharedPreferences3);
        startInfo.endKm = sharedPreferences3.getString("etSonKM", "");
        final SharedPreferences sharedPreferences4 = f1282sp;
        Intrinsics.checkNotNull(sharedPreferences4);
        startInfo.startKm = sharedPreferences4.getString("etBasKM", "");
        this.getMProgressDialogBuilder().setMessage(this.getString(R.string.str_sending_work_info)).setCancelable(true).show();
        viewModel.sendStartInfoEnter(startInfo);
    }
    private void startInfoEnterEndProcess() {
        this.clearEditor();
        this.finish();
    }
    public void responseEvent(final ResponseEvent responseEvent) {
        Intrinsics.checkNotNullParameter(responseEvent, "responseEvent");
        this.getMProgressDialogBuilder().dismiss();
        if (responseEvent.isSuccess()) {
            Toast.makeText(this, this.getString(R.string.str_sent_work_info), Toast.LENGTH_LONG).show();
            this.startInfoEnterEndProcess();
        } else {
            Toast.makeText(this, responseEvent.getErrorMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void clearEditor() {
        final SharedPreferences sharedPreferences = f1282sp;
        Intrinsics.checkNotNull(sharedPreferences);
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove("etPlaka");
        edit.remove("etBasKM");
        edit.remove("etSonKM");
        edit.remove("etNot");
        edit.apply();
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
    static {
        final String simpleName = StartInfoEnterActivity.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        TAG = simpleName;
    }
}
