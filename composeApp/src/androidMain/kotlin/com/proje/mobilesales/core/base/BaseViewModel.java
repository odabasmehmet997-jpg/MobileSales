package com.proje.mobilesales.core.base;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.sql.ISqlBriteDatabase;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.sql.ISqlManager;
import com.proje.mobilesales.features.dbmodel.User;
import com.proje.mobilesales.features.model.ValidationResult;
import com.proje.mobilesales.features.sales.model.Sales;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;

public class BaseViewModel extends ViewModel {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "BaseViewModel";
    private final boolean checkCustomerRiskControl;
    private final IBaseRepository repository;
    public BaseViewModel(IBaseRepository repository) {
        Object runBlockingdefault;
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        try {
            runBlockingdefault = BuildersKt.runBlocking(null, new BaseViewModelcheckCustomerRiskControl1(repository, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.checkCustomerRiskControl = ((Boolean) runBlockingdefault).booleanValue();
    }
    public BaseErp<?> getBaseErp() {
        return this.repository.getBaseErp();
    }
    public final ISqlManager<?> getSqlManager() {
        return this.repository.getSqlManager();
    }
    public final ISqlBriteDatabase<?> getSqlBriteDatabase() {
        return this.repository.getLogoSqlBriteDatabase();
    }
    public final ErpType erpType() {
        return this.repository.getErp();
    }
    public final ISqlHelper<?> getSqlHelper() {
        return this.repository.getLogoSqlHelper();
    }
    public final User user() {
        return this.repository.getUser();
    }
    public final boolean shouldValidateFactory() {
        return this.repository.shouldValidateFactory();
    }
    public final boolean shouldValidateBranch() {
        return this.repository.shouldValidateBranch();
    }
    public final double orderMinLimit() {
        return this.repository.getOrderMinLimit();
    }
    public final ValidationResult controlSecondUnitConversions(Sales sales) {
        ValidationResult validationResult = null;
        try {
            validationResult = this.repository.controlSecondUnitConversions(sales);
            Log.i(TAG, "controlSecondUnitConversions");
        } catch (Exception e2) {
            Log.e(TAG, "controlSecondUnitConversions", e2);
        }
        Intrinsics.checkNotNull(validationResult);
        return validationResult;
    }
    public final boolean getCheckCustomerRiskControl() {
        return this.checkCustomerRiskControl;
    }
    public static final class Companion {
        private Companion() {
        }

        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
