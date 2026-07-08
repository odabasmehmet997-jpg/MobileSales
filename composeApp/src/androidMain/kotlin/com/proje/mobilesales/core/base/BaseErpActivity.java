package com.proje.mobilesales.core.base;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.emailreplacer.EmailObject;
import com.proje.mobilesales.core.enums.CustomerMenuType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.enums.NotifyType;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.enums.RouteProcessType;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.customer.model.CustomerOperation;


public abstract class BaseErpActivity extends BaseInjectableActivity {

    
    public BaseErp baseErp;
    public CustomerOperation customerOperation;
    public int customerRef;
    protected AlertDialogBuilder mBaseAlertDialogBuilder;
    public ProgressDialogBuilder mProgressDialogBuilder;
    public int routeDayRef;
    public int routePlanRef;
    public int routeUserCustomerRef;
    private long mLastClickTime = 0;
    @SuppressLint("RestrictedApi")
    private final int minClickTime = PathInterpolatorCompat.MAX_NUM_POINTS;
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        this.routePlanRef = getIntent().getIntExtra(IntentExtraName.EXTRA_ROUTEPLAN_REF, 0);
        this.routeDayRef = getIntent().getIntExtra(IntentExtraName.EXTRA_ROUTEDAY_REF, 0);
        this.routeUserCustomerRef = getIntent().getIntExtra(IntentExtraName.EXTRA_ROUTEUSERCUSTOMER_REF, 0);
    }
    protected void onPause() {
        super.onPause();
    }
    public void onResume() {
        super.onResume();
        if (this.baseErp == null) {
            finishAffinity();
        }
    }
    public void update(NotifyType notifyType) {
        Log.d("BaseErpAct", "update() called with: notifyType = [" + notifyType + "]");
    }
    public void getExtras() {
        this.customerRef = getIntent().getIntExtra(IntentExtraName.EXTRA_CUSTOMER_REF, 0);
        this.customerOperation = getIntent().getParcelableExtra(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE);
    }
    public ProcessType CustomerToType(CustomerMenuType customerMenuType) {
        return ProcessType.customerMenuToProcessType(customerMenuType);
    }
    public boolean checkOneClick() {
        if (SystemClock.elapsedRealtime() - this.mLastClickTime < this.minClickTime) {
            return false;
        }
        this.mLastClickTime = SystemClock.elapsedRealtime();
        return true;
    }
    public void insertRouteProcess(CustomerOperation customerOperation) {
        if ((customerOperation.getFicheMode() == FicheMode.NEW || customerOperation.getFicheMode() == FicheMode.COPY) && this.baseErp.getRouteNewSystem()) {
            this.baseErp.insertRouteProcess(this.routePlanRef, this.routeDayRef, this.routeUserCustomerRef, RouteProcessType.getRouteProcessType(customerOperation.getFicheType(), customerOperation.getSalesType()), MainActivity.sFicheRef);
        }
    }
    public void sendFicheMail(EmailObject emailObject) {
        new SendFicheMail(emailObject).execute();
    }
    private class SendFicheMail extends AsyncTask<Void, Void, Void> {
        private final EmailObject emailObject;
        private String msg;

        SendFicheMail(EmailObject emailObject) {
            this.emailObject = emailObject;
        }
 
        protected void onPreExecute() {
            super.onPreExecute();
            BaseErpActivity.this.mProgressDialogBuilder.setMessage(ContextUtils.getStringResource(R.string.str_sending_email)).setOnCancelClickListener().setCancelable(false).show();
        }

        public void lambdaonPreExecute0() {
            cancel(true);
        }

        protected Void doInBackground(Void... voidArr) {
            BaseCommunication baseCommunication = BaseErpActivity.this.baseErp.getBaseCommunication();
            try {
                EmailObject emailObject = this.emailObject;
                if (emailObject == null) {
                    return null;
                }
                this.msg = baseCommunication.sendMail(emailObject);
                return null;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(Void r3) {
            super.onPostExecute(r3);
            BaseErpActivity.this.mProgressDialogBuilder.dismiss();
            try {
                String str = this.msg;
                if (str == null || str.isEmpty()) {
                    return;
                }
                Toast.makeText(ContextUtils.getmContext(), this.msg, Toast.LENGTH_LONG).show();
            } catch (Exception e2) {
                Log.e("SendReportMail", "checkConnection: ", e2);
            }
        }
    }
    public void onDestroy() {
        super.onDestroy();
        ProgressDialogBuilder progressDialogBuilder = this.mProgressDialogBuilder;
        if (progressDialogBuilder != null) {
            progressDialogBuilder.dismiss();
            this.mProgressDialogBuilder = null;
        }
        AlertDialogBuilder alertDialogBuilder = this.mBaseAlertDialogBuilder;
        if (alertDialogBuilder != null) {
            alertDialogBuilder.dismiss();
            this.mBaseAlertDialogBuilder = null;
        }
    }
    public boolean isAutoDateTimeAndZoneEnabled() {
        return ContextUtils.checkAutoTimeAndTimeZoneEnabledAndShowDialog();
    }
}
