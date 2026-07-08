package com.proje.mobilesales.core.base;

import android.Manifest;
//noinspection SuspiciousImport
import android.R;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.ArrayRes;
import androidx.annotation.RequiresPermission;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.utils.CompressUtil;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.activity.ImageDialogActivity;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheImageProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import java.io.IOException;
import java.util.UUID;
import javax.inject.Inject;

public abstract class BaseFicheActivity extends BaseErpActivity {
    public static final int REQUEST_IMAGE_CODE = 998;
    private static final String STATE_CUSTOMER_OPERATION = "state:customerOperation";
    private static final String STATE_CUSTOMER_REF = "state:customerRef";
    AlertDialogBuilder mAlertDialogBuilder;
    protected CustomerOperation mCustomerOperation;
    protected int mCustomerRef;
    AlertDialogBuilder mPhotoShowAlertDialogBuilder;
    public static final String EXTRA_CUSTOMER_REF = BaseFicheActivity.class.getName() + ".EXTRA_CUSTOMER_REF";
    public static final String EXTRA_CUSTOMER_OPERATION = BaseFicheActivity.class.getName() + ".EXTRA_CUSTOMER_OPERATION";
    protected abstract void initFiche();
    protected abstract void saveFiche();
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.mCustomerRef = bundle.getInt(STATE_CUSTOMER_REF);
            this.mCustomerOperation = bundle.getParcelable(STATE_CUSTOMER_OPERATION);
        } else {
            this.mCustomerRef = getIntent().getExtras().getInt(EXTRA_CUSTOMER_REF, -1);
            this.mCustomerOperation = getIntent().getExtras().getParcelable(EXTRA_CUSTOMER_OPERATION);
        }
    }
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(STATE_CUSTOMER_REF, this.mCustomerRef);
        bundle.putParcelable(STATE_CUSTOMER_OPERATION, this.mCustomerOperation);
    }
    protected void loadFicheDefaultLocal(FicheRefProp ficheRefProp, int i2, int i3, String... strArr) {
        if (TextUtils.isEmpty(ficheRefProp.getDefinition())) {
            Cursor cursorQuery = null;
            try {
                try {
                    cursorQuery = this.baseErp.getLogoSqlBriteDatabase().query(getString(i3), strArr);
                    if (cursorQuery != null && cursorQuery.moveToFirst()) {
                        ficheRefProp.setLogicalRef(cursorQuery.getInt(R.string.column_id));
                        FicheStringProp.setDefinition(cursorQuery.getString(Integer.parseInt(getString(i2))));
                    }
                    if (cursorQuery == null) {
                        return;
                    }
                } catch (Exception e2) {
                    Log.e(MobileSales.TAG, "loadFicheDefaultParameter: ", e2);
                    if (cursorQuery == null) {
                        return;
                    }
                }
                cursorQuery.close();
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
        }
    }
    protected void loadFicheDefaultLocal(FicheDiscountRefProp ficheDiscountRefProp, int i2, int i3, String... strArr) {
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = this.baseErp.getLogoSqlBriteDatabase().query(getString(i3), strArr);
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    ficheDiscountRefProp.setLogicalRef(cursorQuery.getInt(  R.string.column_id));
                    FicheStringProp.setDefinition(cursorQuery.getString(i2));
                    ficheDiscountRefProp.setCode(cursorQuery.getString(i2));
                }
                if (cursorQuery == null) {
                    return;
                }
            } catch (Exception e2) {
                Log.e(MobileSales.TAG, "loadFicheDefaultParameter: ", e2);
                if (cursorQuery == null) {
                    return;
                }
            }
            cursorQuery.close();
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }
    protected void loadFicheDefaultLocal(FicheDiscountRefProp ficheDiscountRefProp, int i2, int i3, int i4, String... strArr) {
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = this.baseErp.getLogoSqlBriteDatabase().query(getString(i4), strArr);
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    ficheDiscountRefProp.setLogicalRef(cursorQuery.getInt(R.string.column_id));
                    FicheStringProp.setDefinition(cursorQuery.getString(i2));
                    ficheDiscountRefProp.setCode(cursorQuery.getString(i3));
                }
                if (cursorQuery == null) {
                    return;
                }
            } catch (Exception e2) {
                Log.e(MobileSales.TAG, "loadFicheDefaultParameter: ", e2);
                if (cursorQuery == null) {
                    return;
                }
            }
            cursorQuery.close();
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }
    protected void loadFicheDefaultParameter(FicheStringProp ficheStringProp, String str) {
        if (str != null) {
            FicheStringProp.setDefinition(str);
        }
    }
    protected void loadFicheDefaultParameter(FicheRefProp ficheRefProp, int i2, int i3, String str) {
        if (i2 == -1) {
            return;
        }
        ficheRefProp.setLogicalRef(i2);
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = this.baseErp.getLogoSqlBriteDatabase().query(str, StringUtils.convertIntToString(i2));
                if (cursorQuery.moveToFirst()) {
                    FicheStringProp.setDefinition(cursorQuery.getString(Integer.parseInt(getString(i3))));
                }
            } catch (Exception e2) {
                Log.e(MobileSales.TAG, "loadFicheDefaultParameter: ", e2);
                if (cursorQuery == null) {
                    return;
                }
            }
            cursorQuery.close();
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }
    protected void loadFicheRefPropDefaultParameterByCode(FicheRefProp ficheRefProp, String str, int i2, int i3, String... strArr) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (strArr == null) {
            strArr = new String[0];
        }
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = this.baseErp.getLogoSqlBriteDatabase().query(getString(i3), strArr);
                if (cursorQuery.moveToFirst()) {
                    FicheStringProp.setDefinition(str);
                    ficheRefProp.setLogicalRef(cursorQuery.getInt(i2));
                }
            } catch (Exception e2) {
                Log.e(MobileSales.TAG, "loadFicheDefaultParameter: ", e2);
                if (cursorQuery == null) {
                    return;
                }
            }
            cursorQuery.close();
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }
    protected void loadFicheDefaultParameterStringArray(FicheRefProp ficheRefProp, int i2, int i3) {
        if (i2 == -1) {
            return;
        }
        ficheRefProp.setLogicalRef(i2);
        String[] stringArray = getResources().getStringArray(i3);
        if (stringArray.length > i2) {
            FicheStringProp.setDefinition(stringArray[i2]);
        }
    }
    protected void loadFicheDefaultParameter(FicheDiscountRefProp ficheDiscountRefProp, int r4,  int r5, String str) {
        if (r4 == -1) {
            return;
        }
        ficheDiscountRefProp.setLogicalRef(r4);
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = this.baseErp.getLogoSqlBriteDatabase().query(str, StringUtils.convertIntToString(r4));
                if (cursorQuery.moveToFirst()) {
                    FicheStringProp.setDefinition(cursorQuery.getString(r5));
                    ficheDiscountRefProp.setCode(cursorQuery.getString(r5));
                }
            } catch (Exception e2) {
                Log.e(MobileSales.TAG, "loadFicheDefaultParameter: ", e2);
                if (cursorQuery == null) {
                    return;
                }
            }
            cursorQuery.close();
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }
    protected void loadFicheDefaultParameter(FicheDiscountRefProp ficheDiscountRefProp, int i2, int i3, int i4, String str) {
        if (i2 == -1) {
            return;
        }
        ficheDiscountRefProp.setLogicalRef(i2);
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = this.baseErp.getLogoSqlBriteDatabase().query(str, StringUtils.convertIntToString(i2));
                if (cursorQuery.moveToFirst()) {
                    FicheStringProp.setDefinition(cursorQuery.getString(i4));
                    ficheDiscountRefProp.setCode(cursorQuery.getString(i3));
                }
            } catch (Exception e2) {
                Log.e(MobileSales.TAG, "loadFicheDefaultParameter: ", e2);
                if (cursorQuery == null) {
                    return;
                }
            }
            cursorQuery.close();
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }
    protected String getCreateAndFicheNo() {
        return UUID.randomUUID().toString().toUpperCase();
    }
    public void showImageActivity(FicheImageProp ficheImageProp, int i2, FicheMode ficheMode) {
        if (ficheMode != FicheMode.ANALYSE) {
            Intent intent = new Intent(this, ImageDialogActivity.class);
            intent.putExtra(IntentExtraName.EXTRAS_IMAGE, ficheImageProp);
            intent.putExtra(IntentExtraName.EXTRAS_POSITON, i2);
            startActivityForResult(intent, 998);
            return;
        }
        if (ficheImageProp.getImageArray() != null) {
            showImageDialog(ficheImageProp);
        } else {
            Toast.makeText(this, R.string.str_no_photo_found, Toast.LENGTH_SHORT).show();
        }
    }
    public void showImageDialog(FicheImageProp ficheImageProp) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_show_photo, null);
        try {
            ((ImageView) inflate.findViewById(R.id.img_photo)).setImageBitmap(CompressUtil.deCompressBitmap(ficheImageProp.getImageArray()));
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        this.mPhotoShowAlertDialogBuilder.setView(inflate).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.dismiss();
            }
        }).create().show();
    }
    public void showNotDialog(FicheStringProp ficheStringProp, FicheMode ficheMode) {
        if (ficheMode != FicheMode.ANALYSE) {
            EditText editText = new EditText(this);
            editText.setText(ficheStringProp.toString());
            this.mAlertDialogBuilder.setMessage(R.string.exp_37_visit_not_enter).setView(editText).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.core.base.BaseFicheActivity.1
                public void onClick(DialogInterface dialogInterface, int i2) {
                    FicheStringProp.setDefinition(editText.getText().toString());
                }
            }).setNegativeButton(R.string.cancel, null).show();
        } else if (!TextUtils.isEmpty(ficheStringProp.toString())) {
            this.mAlertDialogBuilder.setTitle(R.string.str_sales_note).setMessage(ficheStringProp.toString()).setPositiveButton(R.string.ok, null).create().show();
        } else {
            Toast.makeText(this, R.string.str_no_note_found, Toast.LENGTH_SHORT).show();
        }
    }
    class DialogInterfaceOnClickListenerC24681 implements DialogInterface.OnClickListener {
        final EditText valedittext = null;
        final FicheStringProp valficheStringProp = null;
        FicheStringProp r2;
        EditText r3;

        DialogInterfaceOnClickListenerC24681(FicheStringProp ficheStringProp2, EditText editText2) {
            r2 = ficheStringProp2;
            r3 = editText2;
        }
        public void onClick(DialogInterface dialogInterface, int i2) {
            FicheStringProp.setDefinition(r3.getText().toString());
        }
    }
    public FicheMode getSalesFicheMode() {
        return mCustomerOperation.getFicheMode();
    }
    public CustomerOperation getmCustomerOperation() {
        return this.mCustomerOperation;
    }
    public int getmCustomerRef() {
        return this.mCustomerRef;
    }
}
