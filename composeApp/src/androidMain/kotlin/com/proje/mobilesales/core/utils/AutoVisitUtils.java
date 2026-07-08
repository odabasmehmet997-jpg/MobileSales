package com.proje.mobilesales.core.utils;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.RouteProcessType;
import com.proje.mobilesales.core.enums.TransferOperationName;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.tigerwcf.TigerServiceResult;
import com.proje.mobilesales.features.collections.casefiche.model.database.CaseCash;
import com.proje.mobilesales.features.collections.casefiche.view.activity.CaseFicheActivity;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database.CashCredit;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database.Chequedeed;
import com.proje.mobilesales.features.dbmodel.Invoice;
import com.proje.mobilesales.features.dbmodel.Order;
import com.proje.mobilesales.features.model.CheckAutoVisitItem;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.visit.model.database.VisitInfo;
import org.jetbrains.annotations.UnknownNullability;

import java.util.ArrayList;
import java.util.List;



public class AutoVisitUtils {
    public static void checkAndCreateAutoVisit(int i2, ResponseListener responseListener) {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        CheckAutoVisitItem checkExistingAutoVisit = checkExistingAutoVisit(i2);
        String customerDefinition = checkExistingAutoVisit.getCustomerDefinition();
        if (customerDefinition != null && customerDefinition.equals("")) {
            customerDefinition = baseErp.getCustomerClCode(i2);
        }
        if (checkExistingAutoVisit.isExisting()) {
            if (checkExistingAutoVisit.getDifferentCustomer()) {
                new AlertDialog.Builder(ContextUtils.getmContext()).setMessage(ContextUtils.getStringResource(R.string.str_finish_other_active_visit, customerDefinition)).setPositiveButton(R.string.str_complete_visit, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.core.utils.AutoVisitUtilsExternalSyntheticLambda0
                    public final int f1 = 0;
                    public final ResponseListener f2 = null;

                    public void AutoVisitUtilsExternalSyntheticLambda0(int i22, ResponseListener responseListener2) {
                        r2 = i22;
                        r3 = responseListener2;
                    }
                    public void onClick(DialogInterface dialogInterface, int i3) {
                        AutoVisitUtils.lambdacheckAndCreateAutoVisit0(CheckAutoVisitItem.this, r2, r3, dialogInterface, i3);
                    }
                }).setNegativeButton(R.string.str_form_dialog_cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.core.utils.AutoVisitUtilsExternalSyntheticLambda1
                    public void AutoVisitUtilsExternalSyntheticLambda1() {
                    }
                    public void onClick(DialogInterface dialogInterface, int i3) {
                        AutoVisitUtils.lambdacheckAndCreateAutoVisit1(ResponseListener.this, dialogInterface, i3);
                    }
                }).create().show();
                return;
            } else {
                CaseFicheActivity.ReceiptFicheGetListener responseListener2 = null;
                responseListener2.onResponse(Integer.valueOf(checkExistingAutoVisit.getAutoVisitId()));
                return;
            }
        }
        new AlertDialog.Builder(ContextUtils.getmContext()).setMessage(ContextUtils.getStringResource(R.string.str_visit_will_start, customerDefinition)).setPositiveButton(R.string.str_start_visit, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.core.utils.AutoVisitUtilsExternalSyntheticLambda2
            public final int f0 = 0;
            public final ResponseListener f1 = null;

            public void AutoVisitUtilsExternalSyntheticLambda2(int i22, ResponseListener responseListener2) {
                r1 = i22;
                r2 = responseListener2;
            }
            public void onClick(DialogInterface dialogInterface, int i3) {
                AutoVisitUtils.lambdacheckAndCreateAutoVisit2(r1, r2, dialogInterface, i3);
            }
        }).setNegativeButton(R.string.str_form_dialog_cancel, new DialogInterface.OnClickListener() {
            public void AutoVisitUtilsExternalSyntheticLambda3() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i3) {
                AutoVisitUtils.lambdacheckAndCreateAutoVisit3(ResponseListener.this, dialogInterface, i3);
            }
        }).create().show();
    }

    public static void lambdacheckAndCreateAutoVisit0(CheckAutoVisitItem checkAutoVisitItem, int i2, ResponseListener responseListener, DialogInterface dialogInterface, int i3) {
        dialogInterface.dismiss();
        new finishAutoVisitWithWorProcess(checkAutoVisitItem.getAutoVisitId(), i2, true, responseListener).execute();
    }

    public static void lambdacheckAndCreateAutoVisit1(ResponseListener responseListener, DialogInterface dialogInterface, int i2) {
        dialogInterface.dismiss();
        Toast.makeText(ContextUtils.getmContext(), R.string.exp_91_error_complete_other_active_visit, Toast.LENGTH_LONG).show();
        responseListener.onResponse(0);
    }

    public static void lambdacheckAndCreateAutoVisit2(int i2, ResponseListener responseListener, DialogInterface dialogInterface, int i3) {
        dialogInterface.dismiss();
        new startAutoVisitWithWorProcess(i2, responseListener).execute();
    }

    public static void lambdacheckAndCreateAutoVisit3(ResponseListener responseListener, DialogInterface dialogInterface, int i2) {
        dialogInterface.dismiss();
        Toast.makeText(ContextUtils.getmContext(), R.string.exp_92_error_cancel_to_start_auto_visit, Toast.LENGTH_LONG).show();
        responseListener.onResponse(0);
    }

    @NonNull
    public static CheckAutoVisitItem checkIsExistingAutoVisitToComplete(int i2) {
        CheckAutoVisitItem checkAutoVisitItem = new CheckAutoVisitItem();
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        String clCode = baseErp.getLogoSqlHelper().getClCode(i2);
        StringBuilder sb = new StringBuilder();
        sb.append("AUTO=1 AND ");
        if (baseErp.getErpType() == ErpType.TIGER) {
            sb.append("CLREF=? ");
        } else {
            sb.append("CLCODE=? ");
        }
        sb.append("AND STARTDATE IS NOT NULL AND ENDDATE='' AND USERID=? ");
        List table = baseErp.getLogoSqlHelper().getTable(VisitInfo.class, sb.toString(), new String[]{baseErp.getErpType() == ErpType.NETSIS ? String.valueOf(clCode) : String.valueOf(i2), String.valueOf(baseErp.getUser().getUserId())});
        if (table != null && table.size() > 0) {
            checkAutoVisitItem.setExisting(true);
            checkAutoVisitItem.setAutoVisitId(((VisitInfo) table.get(0)).id);
        }
        return checkAutoVisitItem;
    }

    public static class startAutoVisitWithWorProcess extends AsyncTask<Void, Void, Integer> {
        private final int clRef;
        ProgressDialog progressDialog;
        private final ResponseListener responseListener;

        startAutoVisitWithWorProcess(int i2, ResponseListener responseListener) {
            this.clRef = i2;
            this.responseListener = responseListener;
        }

        @Override // android.os.AsyncTask
        protected Integer doInBackground(Void... voidArr) {
            int insertAutoVisit = AutoVisitUtils.insertAutoVisit(this.clRef);
            if (insertAutoVisit != 0) {
                if (ContextUtils.checkConnectionWithoutMessage()) {
                    VisitInfo autoVisit = AutoVisitUtils.getAutoVisit(insertAutoVisit);
                    if (autoVisit != null) {
                        int insertAutoVisitToWorProcess = AutoVisitUtils.insertAutoVisitToWorProcess(autoVisit);
                        if (insertAutoVisitToWorProcess > 0) {
                            autoVisit.logicalRef = insertAutoVisitToWorProcess;
                            AutoVisitUtils.updateAutoVisitLogicalRef(autoVisit.id, insertAutoVisitToWorProcess);
                        }
                    } else {
                        return Integer.valueOf(insertAutoVisit);
                    }
                }
                return Integer.valueOf(insertAutoVisit);
            }
            return 0;
        }

        @Override // android.os.AsyncTask
        protected void onPostExecute(Integer num) {
            ProgressDialog progressDialog = this.progressDialog;
            if (progressDialog != null && progressDialog.isShowing()) {
                this.progressDialog.dismiss();
            }
            if (num.intValue() != 0) {
                this.responseListener.onResponse(num);
            } else {
                this.responseListener.onError(ContextUtils.getStringResource(R.string.exp_90_error_create_auto_visit));
            }
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            if (ContextUtils.getmActivity().isFinishing()) {
                return;
            }
            this.progressDialog = ProgressDialog.show(ContextUtils.getmContext(), ContextUtils.getStringResource(R.string.str_processing), ContextUtils.getStringResource(R.string.str_please_wait));
        }
    }

    public static class finishAutoVisitWithWorProcess extends AsyncTask<Void, Void, Boolean> {
        private final int autoVisitId;
        private final int customerRef;
        ProgressDialog progressDialog;
        private final ResponseListener responseListener;
        private final boolean startAutoVisit;

        public finishAutoVisitWithWorProcess(int i2, int i3, boolean z, ResponseListener responseListener) {
            this.responseListener = responseListener;
            this.autoVisitId = i2;
            this.customerRef = i3;
            this.startAutoVisit = z;
        }

        @Override // android.os.AsyncTask
        protected Boolean doInBackground(Void... voidArr) {
            AutoVisitUtils.updateAutoVisitEndDate(this.autoVisitId, DateAndTimeUtils.getNowDateTime());
            VisitInfo autoVisit = AutoVisitUtils.getAutoVisit(this.autoVisitId);
            if (!ContextUtils.checkConnectionWithoutMessage()) {
                return Boolean.FALSE;
            }
            if (autoVisit != null && ContextUtils.checkConnectionWithoutMessage()) {
                if (autoVisit.logicalRef > 0) {
                    if (AutoVisitUtils.updateWorProcessWithAutoVisit(autoVisit)) {
                        AutoVisitUtils.updateAutoVisitIsTransfer(this.autoVisitId);
                    }
                } else {
                    int insertAutoVisitToWorProcess = AutoVisitUtils.insertAutoVisitToWorProcess(autoVisit);
                    if (insertAutoVisitToWorProcess > 0) {
                        AutoVisitUtils.updateAutoVisitLogicalRef(this.autoVisitId, insertAutoVisitToWorProcess);
                        AutoVisitUtils.updateAutoVisitIsTransfer(this.autoVisitId);
                    }
                }
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }

        @Override // android.os.AsyncTask
        protected void onPostExecute(Boolean bool) {
            ProgressDialog progressDialog = this.progressDialog;
            if (progressDialog != null && progressDialog.isShowing()) {
                this.progressDialog.dismiss();
            }
            if (bool.booleanValue()) {
                ResponseListener responseListener = this.responseListener;
                if (responseListener != null) {
                    if (this.startAutoVisit) {
                        new startAutoVisitWithWorProcess(this.customerRef, new ResponseListener() {
                            void C25741() {
                            }
                            public void onResponse(@Nullable @UnknownNullability PrintSlipModel obj) {
                                Integer num = (Integer) obj;
                                if (num.intValue() != 0) {
                                    finishAutoVisitWithWorProcess.this.responseListener.onResponse(num);
                                } else {
                                    finishAutoVisitWithWorProcess.this.responseListener.onError(ContextUtils.getStringResource(R.string.exp_90_error_create_auto_visit));
                                }
                            }
                            public void onError(String str) {
                                finishAutoVisitWithWorProcess.this.responseListener.onError(ContextUtils.getStringResource(R.string.exp_90_error_create_auto_visit));
                            }

                            @Override
                            public void onFailure(Throwable throwable) {

                            }

                            @Override
                            public void onResponse(Boolean aBoolean) {

                            }

                            @Override
                            public void onResponse(Sales sales) {

                            }

                            @Override
                            public void onResponse(TigerServiceResult tigerServiceResult) {

                            }

                            @Override
                            public void onResponse(Object obj) {

                            }

                            @Override
                            public void onResponse(ArrayList obj) {

                            }

                            @Override
                            public void onResponse() {

                            }

                            @Override
                            public void onResponse(Integer integer) {

                            }
                        }).execute();
                        return;
                    } else {
                        responseListener.onResponse(Boolean.TRUE);
                        return;
                    }
                }
                return;
            }
            this.responseListener.onResponse(Boolean.FALSE);
        }

        class C25741 implements ResponseListener {
            C25741() {
            }
            public void onResponse(@Nullable @UnknownNullability PrintSlipModel obj) {
                Integer num = (Integer) obj;
                if (num.intValue() != 0) {
                    finishAutoVisitWithWorProcess.this.responseListener.onResponse(num);
                } else {
                    finishAutoVisitWithWorProcess.this.responseListener.onError(ContextUtils.getStringResource(R.string.exp_90_error_create_auto_visit));
                }
            }
            public void onError(String str) {
                finishAutoVisitWithWorProcess.this.responseListener.onError(ContextUtils.getStringResource(R.string.exp_90_error_create_auto_visit));
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onResponse(Boolean aBoolean) {

            }

            @Override
            public void onResponse(Sales sales) {

            }

            @Override
            public void onResponse(TigerServiceResult tigerServiceResult) {

            }

            @Override
            public void onResponse(Object obj) {

            }

            @Override
            public void onResponse(ArrayList obj) {

            }

            @Override
            public void onResponse() {

            }

            @Override
            public void onResponse(Integer integer) {

            }
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            if (ContextUtils.getmActivity().isFinishing()) {
                return;
            }
            this.progressDialog = ProgressDialog.show(ContextUtils.getmContext(), ContextUtils.getStringResource(R.string.str_processing), ContextUtils.getStringResource(R.string.str_please_wait));
        }
    }

    public static boolean isAutoVisitActivity(RouteProcessType routeProcessType) {
        return routeProcessType == RouteProcessType.ORDER || routeProcessType == RouteProcessType.INVOICE || routeProcessType == RouteProcessType.RETURNINVOICE || routeProcessType == RouteProcessType.RETAILINVOICE || routeProcessType == RouteProcessType.RETAILRETURNINVOICE || routeProcessType == RouteProcessType.DISPATCH || routeProcessType == RouteProcessType.RETURNDISPATCH || routeProcessType == RouteProcessType.ONETOONECHANGE || routeProcessType == RouteProcessType.CASH || routeProcessType == RouteProcessType.CASE || routeProcessType == RouteProcessType.CREDITCARD || routeProcessType == RouteProcessType.CHEQUE || routeProcessType == RouteProcessType.DEED;
    }

    public static int checkAndGetWorProcessRefAfterFicheTransfer(TransferOperationName transferOperationName, int i2) {
        int visitInfoId;
        RouteProcessType routeProcessType = RouteProcessType.getRouteProcessType(transferOperationName);
        if (!isAutoVisitActivity(routeProcessType) || (visitInfoId = getVisitInfoId(routeProcessType, i2)) <= 0) {
            return 0;
        }
        VisitInfo autoVisit = getAutoVisit(visitInfoId);
        int i3 = autoVisit.logicalRef;
        return i3 != 0 ? i3 : insertAutoVisitToWorProcess(autoVisit);
    }

    private static int getVisitInfoId(RouteProcessType routeProcessType, int i2) {
        List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(routeProcessType.getmDatabaseClass(), "LOGICALREF=?", new String[]{StringUtils.convertIntToString(i2)});
        if (table != null && table.size() > 0) {
            if (routeProcessType.getmDatabaseClass() == Invoice.class) {
                return ((Invoice) table.toArray()[0]).getVisitInfoId();
            }
            if (routeProcessType.getmDatabaseClass() == Order.class) {
                return ((Order) table.toArray()[0]).getVisitInfoId();
            }
            if (routeProcessType.getmDatabaseClass() == Chequedeed.class) {
                return ((Chequedeed) table.toArray()[0]).visitInfoId;
            }
            if (routeProcessType.getmDatabaseClass() == CashCredit.class) {
                return ((CashCredit) table.toArray()[0]).visitInfoId;
            }
            if (routeProcessType.getmDatabaseClass() == CaseCash.class) {
                return ((CaseCash) table.toArray()[0]).visitInfoId;
            }
        }
        return 0;
    }

    public static int sendAutoVisitsToWorProcess() {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        List<VisitInfo> table = baseErp.getLogoSqlHelper().getTable(VisitInfo.class, "AUTO=1 AND LOGICALREF = 0 AND USERID=?", new String[]{String.valueOf(baseErp.getUser().getUserId())});
        if (table == null || table.size() <= 0) {
            return 0;
        }
        for (VisitInfo visitInfo : table) {
            if (visitInfo.endDate.equals("")) {
                String nowDateTime = DateAndTimeUtils.getNowDateTime();
                visitInfo.endDate = nowDateTime;
                updateAutoVisitEndDate(visitInfo.id, nowDateTime);
            }
            updateAutoVisitLogicalRef(visitInfo.id, insertAutoVisitToWorProcess(visitInfo));
            updateAutoVisitIsTransfer(visitInfo.id);
        }
        return table.get(0).id;
    }

    @NonNull
    private static CheckAutoVisitItem checkExistingAutoVisit(int i2) {
        CheckAutoVisitItem checkAutoVisitItem = new CheckAutoVisitItem();
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        List table = baseErp.getLogoSqlHelper().getTable(VisitInfo.class, "AUTO=1 AND STARTDATE IS NOT NULL AND ENDDATE IS '' AND USERID=?", new String[]{String.valueOf(baseErp.getUser().getUserId())});
        if (table != null && table.size() > 0) {
            checkAutoVisitItem.setExisting(true);
            VisitInfo visitInfo = (VisitInfo) table.get(0);
            checkAutoVisitItem.setAutoVisitId(visitInfo.id);
            if (baseErp.getErpType() == ErpType.TIGER) {
                if (visitInfo.clRef == i2) {
                    checkAutoVisitItem.setDifferentCustomer(false);
                } else {
                    checkAutoVisitItem.setDifferentCustomer(true);
                    checkAutoVisitItem.setCustomerDefinition(baseErp.getCustomerInfo(visitInfo.clRef).getDefinition());
                }
            } else {
                if (visitInfo.clCode.equals(baseErp.getLogoSqlHelper().getClCode(i2))) {
                    checkAutoVisitItem.setDifferentCustomer(false);
                } else {
                    checkAutoVisitItem.setDifferentCustomer(true);
                    checkAutoVisitItem.setCustomerDefinition(baseErp.getCustomerInfo(baseErp.getCustomerClRef(visitInfo.clCode)).getDefinition());
                }
            }
        } else {
            checkAutoVisitItem.setExisting(false);
            if (baseErp.getErpType() == ErpType.TIGER) {
                checkAutoVisitItem.setCustomerDefinition(baseErp.getCustomerInfo(i2).getDefinition());
            } else {
                checkAutoVisitItem.setCustomerDefinition(baseErp.getCustomerInfo(baseErp.getCustomerClRef(baseErp.getLogoSqlHelper().getClCode(i2))).getDefinition());
            }
        }
        return checkAutoVisitItem;
    }

    public static int insertAutoVisit(int i2) {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        VisitInfo visitInfo = new VisitInfo();
        visitInfo.auto = 1;
        visitInfo.reason = ContextUtils.getStringResource(R.string.str_sales_activity);
        String nowDateTime = DateAndTimeUtils.getNowDateTime();
        visitInfo.startDate = nowDateTime;
        visitInfo.visitDate = nowDateTime;
        visitInfo.userId = baseErp.getUser().getUserId();
        visitInfo.clRef = i2;
        if (baseErp.getErpType() == ErpType.NETSIS) {
            visitInfo.clCode = baseErp.getCustomerClCode(i2);
        }
        return baseErp.insertVisit(visitInfo);
    }

    public static void updateAutoVisitLogicalRef(int i2, int i3) {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        ContentValues contentValues = new ContentValues();
        contentValues.put("LOGICALREF", Integer.valueOf(i3));
        baseErp.getLogoSqlBriteDatabase().update(VisitInfo.class, contentValues, "ID=?", String.valueOf(i2));
    }

    public static void updateAutoVisitEndDate(int i2, String str) {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ENDDATE", str);
        baseErp.getLogoSqlBriteDatabase().update(VisitInfo.class, contentValues, "ID=?", String.valueOf(i2));
    }

    public static void updateAutoVisitIsTransfer(int i2) {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ISTRANSFER", 1);
        baseErp.getLogoSqlBriteDatabase().update(VisitInfo.class, contentValues, "ID=?", String.valueOf(i2));
    }

    public static int insertAutoVisitToWorProcess(VisitInfo visitInfo) {
        return ErpCreator.getInstance().getmBaseErp().insertWorProcess(visitInfo);
    }

    public static boolean updateWorProcessWithAutoVisit(@NonNull VisitInfo visitInfo) {
        return ErpCreator.getInstance().getmBaseErp().updateWorProcess(visitInfo.endDate, visitInfo.logicalRef);
    }

    @Nullable
    public static VisitInfo getAutoVisit(int i2) {
        List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(VisitInfo.class, "ID=? ", new String[]{String.valueOf(i2)});
        if (table == null || table.size() <= 0) {
            return null;
        }
        return (VisitInfo) table.get(0);
    }
}
