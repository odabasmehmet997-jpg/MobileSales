package com.proje.mobilesales.core.design;

import android.content.Context;
import android.util.Log;
import com.proje.mobilesales.R;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseErpFactory;
import com.proje.mobilesales.core.enums.CommunicationType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.exception.ErpNotFoundException;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.utils.ContextUtils;

public class ErpCreator extends BaseErpFactory {
    private static final Object LOCK = new Object();
    private static final String TAG = "ErpCreator";
    private static ErpCreator instance;
    private static BaseErp mBaseErp;
    private ErpCreator() {
    }
    public static ErpCreator getInstance() {
        synchronized (LOCK) {
            try {
                if (instance == null) {
                    instance = new ErpCreator();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return instance;
    }
    public BaseErp getmBaseErp() throws ErpNotFoundException {
        return getmBaseErp(ContextUtils.getmContext());
    }
    public BaseErp getmBaseErp(Context context) throws ErpNotFoundException {
        synchronized (LOCK) {
            BaseErp baseErp = mBaseErp;
            if (baseErp != null) {
                return baseErp;
            }
            try {
                Log.d(TAG, "getmBaseErp: new erp created");
                BaseErp erp = getErp(Preferences.getErpType(context));
                mBaseErp = erp;
                return erp;
            } catch (Exception e2) {
                Log.e(TAG, "Error on creating ERP instance", e2);
                throw new ErpNotFoundException(ContextUtils.getStringResource(R.string.exp_1_erp_null));
            }
        }
    }
    public BaseErp getErp(ErpType erpType) throws ErpNotFoundException {
        if (erpType == ErpType.TIGER) {
            mBaseErp = new Tiger(CommunicationType.WCF);
        } else if (erpType == ErpType.NETSIS) {
            mBaseErp = new Netsis(erpType, CommunicationType.REST);
        } else {
            throw new ErpNotFoundException(ContextUtils.getStringResource(R.string.exp_1_erp_null));
        }
        return mBaseErp;
    }
}