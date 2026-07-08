package com.proje.mobilesales.core.utils;

import android.Manifest;
import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AlertDialog;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseRepository;
import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.gpsinfo.model.database.CustGpsInfo;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;


public final class SuspicionsActivityUtils {
    public static final SuspicionsActivityUtils INSTANCE = new SuspicionsActivityUtils();
    private static BaseRepository repository;
    private static BaseViewModel viewModel;

    private SuspicionsActivityUtils() {
    }

    public void initialize(BaseRepository repository2, BaseViewModel viewModel2) {
        Intrinsics.checkNotNullParameter(repository2, "repository");
        Intrinsics.checkNotNullParameter(viewModel2, "viewModel");
        repository = repository2;
        viewModel = viewModel2;
    }

    private CustGpsInfo getCustomerGpsInfo(int i2) {
        BaseViewModel baseViewModel = viewModel;
        if (baseViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            baseViewModel = null;
        }
        ISqlHelper logoSqlHelper = baseViewModel.getBaseErp().getLogoSqlHelper();
        BaseViewModel baseViewModel2 = viewModel;
        if (baseViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            baseViewModel2 = null;
        }
        List table = logoSqlHelper.getTable(CustGpsInfo.class, "CLCODE=?", new String[]{baseViewModel2.getSqlHelper().getClCode(i2)});
        Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.gpsinfo.model.database.CustGpsInfo>");
        if (table.isEmpty()) {
            return null;
        }
        return (CustGpsInfo) table.get(0);
    }

    private boolean hasCustomerSuspiciousActivityAlert(int i2) {
        BaseViewModel baseViewModel = viewModel;
        BaseViewModel baseViewModel2 = null;
        if (baseViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            baseViewModel = null;
        }
        if (baseViewModel.getBaseErp().getErpRights().isGps()) {
            BaseViewModel baseViewModel3 = viewModel;
            if (baseViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                baseViewModel2 = baseViewModel3;
            }
            return baseViewModel2.getBaseErp().getSuspiciousActivityAlertControl() && getCustomerGpsInfo(i2) != null && getSuspiciousDistance() > 0;
        }
        return false;
    }

    private int getSuspiciousDistance() {
        BaseViewModel baseViewModel = viewModel;
        if (baseViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            baseViewModel = null;
        }
        return baseViewModel.getBaseErp().getSuspiciousActivityDistance();
    }

    private boolean isSuspiciousActivity(int i2) {
        Location location = new Location("Current Location");
        BaseViewModel baseViewModel = viewModel;
        BaseViewModel baseViewModel2 = null;
        if (baseViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            baseViewModel = null;
        }
        location.setLatitude(baseViewModel.getBaseErp().getLatitude());
        BaseViewModel baseViewModel3 = viewModel;
        if (baseViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            baseViewModel2 = baseViewModel3;
        }
        location.setLongitude(baseViewModel2.getBaseErp().getLongitude());
        Location location2 = new Location("Customer Location");
        CustGpsInfo customerGpsInfo = getCustomerGpsInfo(i2);
        location2.setLatitude(customerGpsInfo != null ? customerGpsInfo.latitude : 0.0d);
        CustGpsInfo customerGpsInfo2 = getCustomerGpsInfo(i2);
        location2.setLongitude(customerGpsInfo2 != null ? customerGpsInfo2.longtitude : 0.0d);
        if (location.distanceTo(location2) <= getSuspiciousDistance()) {
            return true;
        }
        Context context = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Context context2 = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context2);
        builder.setMessage(context2.getString(R.string.exp_suspicious_activity)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.core.utils.SuspicionsActivityUtilsExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i3) {
                SuspicionsActivityUtils.isSuspiciousActivitylambda0(dialogInterface, i3);
            }
        }).create().show();
        return false;
    }

    public static void isSuspiciousActivitylambda0(DialogInterface dialog, int i2) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    private boolean checkUsedGps() {
        BaseViewModel baseViewModel = viewModel;
        if (baseViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            baseViewModel = null;
        }
        if (baseViewModel.getBaseErp().useGps()) {
            return true;
        }
        Context context = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Context context2 = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context2);
        builder.setMessage(context2.getString(R.string.str_unable_gps_location_information)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.core.utils.SuspicionsActivityUtilsExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                SuspicionsActivityUtils.checkUsedGpslambda1(dialogInterface, i2);
            }
        }).create().show();
        return false;
    }

    public static void checkUsedGpslambda1(DialogInterface dialog, int i2) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    @RequiresPermission(allOf = {Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE})
    private boolean isDeviceReadyForSuspiciousControls() {
        return ContextUtils.checkInternetConnection() && ContextUtils.checkGpsConnection() && checkUsedGps();
    }

    @RequiresPermission(allOf = {Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE})
    public boolean isPassedSuspiciousActivityControlsForSales(int i2, SalesType salesType, BaseRepository repository2, BaseViewModel viewModel2) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        Intrinsics.checkNotNullParameter(repository2, "repository");
        Intrinsics.checkNotNullParameter(viewModel2, "viewModel");
        initialize(repository2, viewModel2);
        if (SalesUtils.INSTANCE.isSalesOrOrder(salesType) && hasCustomerSuspiciousActivityAlert(i2)) {
            return isDeviceReadyForSuspiciousControls() && isSuspiciousActivity(i2);
        }
        return true;
    }

    @RequiresPermission(allOf = {Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE})
    public boolean isPassedSuspiciousActivityControlsForReceipt(int i2, BaseRepository repository2, BaseViewModel viewModel2) {
        Intrinsics.checkNotNullParameter(repository2, "repository");
        Intrinsics.checkNotNullParameter(viewModel2, "viewModel");
        initialize(repository2, viewModel2);
        if (hasCustomerSuspiciousActivityAlert(i2)) {
            return isDeviceReadyForSuspiciousControls() && isSuspiciousActivity(i2);
        }
        return true;
    }
}
