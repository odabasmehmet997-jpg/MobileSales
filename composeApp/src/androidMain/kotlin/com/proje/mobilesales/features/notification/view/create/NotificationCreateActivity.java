package com.proje.mobilesales.features.notification.view.create;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;
import com.proje.mobilesales.R;
import com.proje.mobilesales.C2682R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.NotificationStatus;
import com.proje.mobilesales.core.extensions.DateExtensions;
import com.proje.mobilesales.core.extensions.ViewExtensions;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.UiState;
import com.proje.mobilesales.databinding.ActivityNotificationCreateBinding;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import com.proje.mobilesales.features.notification.model.NotificationUserSelectionModel;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import com.proje.mobilesales.features.notification.repository.NotificationCreateRepository;
import com.proje.mobilesales.features.notification.util.NotificationConstants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.Function;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;



public final class NotificationCreateActivity extends BaseInjectableActivity implements NotificationUserSelectionDialogFragment.INotificationUserSelectionListener {
    private int hour;
    private int minute;
    private final NotificationCreateRepository repository;
    private final NotificationCreateViewModel viewModel;
    private final String TAG = "NotificationCreate";
    private final Lazy bindingdelegate = LazyKt.lazy(new Functions<ActivityNotificationCreateBinding>(this) { // from class: com.proje.mobilesales.features.notification.view.create.NotificationCreateActivitybinding2
        final NotificationCreateActivity this0;

        
        {
            this.this0 = r1;
        }

        
        public ActivityNotificationCreateBinding invoke() {
            return ActivityNotificationCreateBinding.inflate(this.this0.getLayoutInflater());
        }
    });
    private final Calendar selectedCalendar = Calendar.getInstance();
    private ArrayList<NotificationUserSelectionModel> selectedUsers = new ArrayList<>();

    public NotificationCreateActivity() {
        NotificationCreateRepository notificationCreateRepository = new NotificationCreateRepository();
        this.repository = notificationCreateRepository;
        this.viewModel = new NotificationCreateViewModel(notificationCreateRepository);
    }

    private ActivityNotificationCreateBinding getBinding() {
        return (ActivityNotificationCreateBinding) this.bindingdelegate.getValue();
    }

    
    
    public void onCreate(Bundle bundle) {
        ArrayList<NotificationUserSelectionModel> arrayList;
        super.onCreate(bundle);
        setContentView(getBinding().getRoot());
        setToolbar();
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(getString(R.string.str_notification_create));
        }
        setDateTime();
        if (bundle != null) {
            arrayList = bundle.getParcelableArrayList("NOTIFICATION_SELECTED_USERS");
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
        } else {
            arrayList = new ArrayList<>();
        }
        this.selectedUsers = arrayList;
        getBinding().imgAddTo.setOnClickListener(new View.OnClickListener() {  
            public void onClick(View view) {
                NotificationCreateActivity.r8lambdaKBm0hFTPjabcAQFEQZubEfLUvog(NotificationCreateActivity.this, view);
            }
        });
        this.viewModel.saveNotificationUiState().observe(this, new Object(new Function1<UiState<? extends String>, Unit>(this) {
            final NotificationCreateActivity this0;

            
            {
                this.this0 = r1;
            }

            
            
            
            public Unit invoke(UiState<? extends String> uiState) {
                invoke((UiState<String>) uiState);
                return Unit.INSTANCE;
            }

            public void invoke(UiState<String> uiState) {
                if (uiState != null) {
                    NotificationCreateActivity.accessrender(this.this0, uiState, new Function1<String, Unit>(this.this0) {  
                        public   Unit invoke(String str) {
                            invoke(str);
                            return Unit.INSTANCE;
                        }

                        public void invoke(String str) {
                            Intrinsics.checkNotNullParameter(str, "p0");
                            NotificationCreateActivity.accessonSaveNotification((NotificationCreateActivity) this.receiver, str);
                        }
                    });
                }
            }
        }) {
            private final Function1 function;

            
            {
                Intrinsics.checkNotNullParameter(r2, "function");
                this.function = r2;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof Observer) || !(obj instanceof FunctionAdapter)) {
                    return false;
                }
                return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
            }

            
            public Function<?> getFunctionDelegate() {
                return this.function;
            }

            public int hashCode() {
                return getFunctionDelegate().hashCode();
            }

            
            public void onChanged(Object obj) {
                this.function.invoke(obj);
            }
        });
    }

    
    public static void onCreatelambda0(NotificationCreateActivity notificationCreateActivity, View view) {
        Intrinsics.checkNotNullParameter(notificationCreateActivity, "this0");
        NotificationUserSelectionDialogFragment newInstance = NotificationUserSelectionDialogFragment.Companion.newInstance(notificationCreateActivity.selectedUsers);
        newInstance.setCancelable(false);
        newInstance.show(notificationCreateActivity.getSupportFragmentManager(), "NotificationUserSelectionDialog");
    }

    
    @Override // com.proje.mobilesales.core.base.BaseActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        super.onSaveInstanceState(bundle);
        bundle.putParcelableArrayList("NOTIFICATION_SELECTED_USERS", this.selectedUsers);
    }

    
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        getMenuInflater().inflate(R.menu.menu_create_notification, menu);
        return super.onCreateOptionsMenu(menu);
    }

    
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            onBackPressed();
            return true;
        } else if (itemId != R.id.menu_send_notification) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            saveNotification();
            return true;
        }
    }

    private void saveNotification() {
        if (ContextUtils.checkAutoTimeAndTimeZoneEnabledAndShowDialog()) {
            ActivityNotificationCreateBinding binding = getBinding();
            String selectedDateWithTime = getSelectedDateWithTime(binding.tvSendDate.getText().toString(), binding.tvSendTime.getText().toString());
            String uuid = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
            NotificationModel notificationModel = new NotificationModel(uuid, String.valueOf(binding.tvNotificationTitle.getText()), String.valueOf(binding.tvMessage.getText()), Integer.valueOf(NotificationStatus.Created.getStatus()), selectedDateWithTime, binding.swcPayAttentionToWorkTime.isChecked() ? 1 : 0);
            ArrayList arrayList = new ArrayList();
            Iterator<NotificationUserSelectionModel> it = this.selectedUsers.iterator();
            while (it.hasNext()) {
                String uuid2 = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(uuid2, "toString(...)");
                arrayList.add(new NotifiedUserModel(uuid2, uuid, it.next().getId(), Integer.valueOf(NotificationStatus.Created.getStatus())));
            }
            if (ViewExtensions.isConnecteddefault(this, false, 1, null)) {
                ViewExtensions.alertdefault(this, 0, new Function1<AlertDialog.Builder, Unit>(this, notificationModel, arrayList) { // from class: com.proje.mobilesales.features.notification.view.create.NotificationCreateActivitysaveNotification11
                    final NotificationModel notificationModel;
                    final List<NotifiedUserModel> notifiedUserList;
                    final NotificationCreateActivity this0;

                    
                    {
                        this.this0 = r1;
                        this.notificationModel = r2;
                        this.notifiedUserList = r3;
                    }

                    
                    
                    
                    public Unit invoke(AlertDialog.Builder builder) {
                        invoke(builder);
                        return Unit.INSTANCE;
                    }

                    public void invoke(AlertDialog.Builder builder) {
                        Intrinsics.checkNotNullParameter(builder, "thisalert");
                        builder.setMessage(this.this0.getString(R.string.str_confirm_save_notification));
                        final NotificationCreateActivity notificationCreateActivity = this.this0;
                        final NotificationModel notificationModel2 = this.notificationModel;
                        final List<NotifiedUserModel> list = this.notifiedUserList;
                        ViewExtensions.positiveButtondefault(builder, 0, new Function1<DialogInterface, Unit>() { // from class: com.proje.mobilesales.features.notification.view.create.NotificationCreateActivitysaveNotification11.1
                            
                            
                            
                            public Unit invoke(DialogInterface dialogInterface) {
                                invoke(dialogInterface);
                                return Unit.INSTANCE;
                            }

                            public void invoke(DialogInterface dialogInterface) {
                                Intrinsics.checkNotNullParameter(dialogInterface, "it");
                                dialogInterface.dismiss();
                                NotificationCreateActivity.accessgetViewModelp(notificationCreateActivity).saveNotification(notificationModel2, list);
                            }
                        }, 1, null);
                        ViewExtensions.negativeButtondefault(builder, 0, C30232.INSTANCE, 1, null);
                    }
                }, 1, null);
            }
        }
    }

    private String getSelectedDateWithTime(String str, String str2) {
        List splitdefault = StringsKt.splitdefault((CharSequence) str2, new String[]{":"}, false, 0, 6, (Object) null);
        if (splitdefault.size() == 2) {
            return str + ' ' + splitdefault.get(0) + ':' + splitdefault.get(1) + ":00";
        }
        return str + " 00:00:00";
    }

    
    public void onSaveNotification(String str) {
        Intent intent = new Intent();
        intent.putExtra(NotificationConstants.EXTRA_NOTIFICATION_GUID, str);
        setResult(-1, intent);
        finish();
    }

    private void setDateTime() {
        getBinding().tvSendDate.setText(DateExtensions.formatDate(this.selectedCalendar.getTime(), "dd.MM.yyyy"));
        this.hour = this.selectedCalendar.get(11);
        this.minute = this.selectedCalendar.get(12);
        getBinding().tvSendTime.setText(getSelectedTime());
        getBinding().imgBtnSendTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NotificationCreateActivity.m1348r8lambda6MX8X3oJ0QFUJM8DN6T3c4bFnA(NotificationCreateActivity.this, view);
            }
        });
        getBinding().imgBtnSendDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NotificationCreateActivity.r8lambdaZky88viBtTBeCPQTSHnC1vZ4mxo(NotificationCreateActivity.this, view);
            }
        });
    }

    
    public static void setDateTimelambda3(NotificationCreateActivity notificationCreateActivity, View view) {
        Intrinsics.checkNotNullParameter(notificationCreateActivity, "this0");
        if (ContextUtils.checkAutoTimeAndTimeZoneEnabledAndShowDialog()) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(notificationCreateActivity, new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker timePicker, int i, int i2) {
                    NotificationCreateActivity.r8lambdaM4kVRmsQssx4Nc6SWdBrcShHRj0(NotificationCreateActivity.this, timePicker, i, i2);
                }
            }, notificationCreateActivity.hour, notificationCreateActivity.minute, true);
            timePickerDialog.setTitle("");
            timePickerDialog.show();
        }
    }

    
    public static void setDateTimelambda3lambda2(NotificationCreateActivity notificationCreateActivity, TimePicker timePicker, int i, int i2) {
        Intrinsics.checkNotNullParameter(notificationCreateActivity, "this0");
        notificationCreateActivity.hour = i;
        notificationCreateActivity.minute = i2;
        notificationCreateActivity.getBinding().tvSendTime.setText(notificationCreateActivity.getSelectedTime());
        notificationCreateActivity.checkTime();
    }

    
    public static void setDateTimelambda5(NotificationCreateActivity notificationCreateActivity, View view) {
        Intrinsics.checkNotNullParameter(notificationCreateActivity, "this0");
        if (ContextUtils.checkAutoTimeAndTimeZoneEnabledAndShowDialog()) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(notificationCreateActivity, new DatePickerDialog.OnDateSetListener() { // from class: com.proje.mobilesales.features.notification.view.create.NotificationCreateActivityExternalSyntheticLambda3
                @Override // android.app.DatePickerDialog.OnDateSetListener
                public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                    NotificationCreateActivity.r8lambdaUaesLbXqy3vxWzUZBKGce9wDb1Y(NotificationCreateActivity.this, datePicker, i, i2, i3);
                }
            }, notificationCreateActivity.selectedCalendar.get(1), notificationCreateActivity.selectedCalendar.get(2), notificationCreateActivity.selectedCalendar.get(5));
            datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTime().getTime());
            datePickerDialog.show();
        }
    }

    
    public static void setDateTimelambda5lambda4(NotificationCreateActivity notificationCreateActivity, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(notificationCreateActivity, "this0");
        notificationCreateActivity.selectedCalendar.set(i, i2, i3);
        notificationCreateActivity.getBinding().tvSendDate.setText(DateExtensions.formatDate(notificationCreateActivity.selectedCalendar.getTime(), "dd.MM.yyyy"));
        notificationCreateActivity.checkTime();
    }

    private void checkTime() {
        ActivityNotificationCreateBinding binding = getBinding();
        Calendar instance = Calendar.getInstance();
        instance.set(13, 0);
        Date date = DateExtensions.toDate(getSelectedDateWithTime(binding.tvSendDate.getText().toString(), binding.tvSendTime.getText().toString()), "dd.MM.yyyy HH:mm:ss");
        if (date != null && date.toInstant().isBefore(instance.toInstant())) {
            this.hour = instance.get(11);
            this.minute = instance.get(12);
            getBinding().tvSendTime.setText(getSelectedTime());
        }
    }

    private String getSelectedTime() {
        return StringsKt.padStart(String.valueOf(this.hour), 2, '0') + ':' + StringsKt.padStart(String.valueOf(this.minute), 2, '0');
    }

    
    public <T> void render(UiState<? extends T> uiState, Function1<? super T, Unit> function1) {
        if (uiState instanceof UiState.Loading) {
            onLoad();
        } else if (uiState instanceof UiState.Success) {
            try {
                function1.invoke(((UiState.Success) uiState).getResult());
            } catch (Exception e) {
                Log.d(this.TAG, "successor function failure", e);
            }
            hideLoadingProgress();
        } else if (uiState instanceof UiState.Error) {
            onError((UiState.Error) uiState);
        }
    }

    private void onLoad() {
        LinearLayout root = getBinding().loadingBar.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewExtensions.setVisible(root);
    }

    private Unit onError(UiState.Error error) {
        getBinding();
        hideLoadingProgress();
        return ViewExtensions.toastdefault(this, ViewExtensions.uiStateError(this, error), 0, 2, null);
    }

    private void hideLoadingProgress() {
        LinearLayout root = getBinding().loadingBar.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewExtensions.setGone(root);
    }
    public void selectionCompleted(ArrayList<NotificationUserSelectionModel> arrayList) {
        String str;
        Intrinsics.checkNotNullParameter(arrayList, "selectedUsers");
        this.selectedUsers = arrayList;
        AppCompatTextView appCompatTextView = getBinding().tvTo;
        if (this.selectedUsers.size() > 0) {
            str = CollectionsKt.joinToStringdefault(this.selectedUsers, "; ", null, null, 0, null, NotificationCreateActivityselectionCompleted1.INSTANCE, 30, null);
        } else {
            str = "";
        }
        appCompatTextView.setText(str);
    }
}
