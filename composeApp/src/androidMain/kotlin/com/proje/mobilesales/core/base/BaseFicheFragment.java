package com.proje.mobilesales.core.base;

import android.R;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.ArrayRes;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.google.gson.Gson;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ISalesDiscountFragment;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.searchdialog.BaseSearchDialogCompat;
import com.proje.mobilesales.core.searchdialog.BottomSheetDialogSearchResultListener;
import com.proje.mobilesales.core.searchdialog.SearchResultListener;
import com.proje.mobilesales.core.searchdialog.SimpleSearchDialogCompat;
import com.proje.mobilesales.core.sql.ISqlManager;
import com.proje.mobilesales.core.tigerwcf.TigerServiceResult;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.fragment.BaseSearchModelListDialogFragment;
import com.proje.mobilesales.features.model.*;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.model.SalesFicheDiscount;
import com.proje.mobilesales.features.sales.model.SalesFicheDiscountProps;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import org.greenrobot.eventbus.EventBus;

public abstract class BaseFicheFragment extends BaseFragment {
    public static final String EXTRA_CUSTOMER_REF = BaseSalesFragment.class.getName() + ".EXTRA_CUSTOMER_REF";
    private static final String STATE_CUSTOMER_REF = "state:customerRef";
    private static final String TAG = "BaseFicheFragment";
    protected BaseErp baseErp;
    AlertDialogBuilder mAlertDialogBuilder;
    AlertDialogBuilder mArrayAlertDialogBuilder;
    protected int mCustomerRef;
    AlertDialogBuilder mDateAlertDialogBuilder;
    ProgressDialogBuilder mProgressDialogBuilder;
    ISqlManager mSqlManager;
    public interface OnCheckBoxClickInterface {
        void onCheckBoxClicked(boolean z);
    }
    protected abstract void load();
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        if (bundle != null) {
            this.mCustomerRef = bundle.getInt("state:customerRef", -1);
        } else {
            this.mCustomerRef = getArguments().getInt(EXTRA_CUSTOMER_REF, -1);
        }
        this.baseErp = ErpCreator.getInstance().getmBaseErp();
    }
    public BaseFicheActivity getBaseFicheActivity() {
        return (BaseFicheActivity) getActivity();
    }
    public FicheMode getSalesFicheMode() {
        return getBaseFicheActivity().getSalesFicheMode();
    } 
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("state:customerRef", this.mCustomerRef);
    }
    protected Cursor getCursor(String str, String... strArr) {
        return this.baseErp.getLogoSqlBriteDatabase().query(str, strArr);
    }
    protected void createCheckboxSetCheckedChangeListener(FicheMode ficheMode, View view, CheckBox checkBox, FicheBooleanProp ficheBooleanProp, boolean z) {
        createCheckboxSetCheckedChangeListener(ficheMode, view, checkBox, ficheBooleanProp, z, false, true, null);
    }
    protected void createCheckboxSetCheckedChangeListener(FicheMode ficheMode, View view, CheckBox checkBox, FicheBooleanProp ficheBooleanProp, boolean z, boolean z2, boolean z3) {
        createCheckboxSetCheckedChangeListener(ficheMode, view, checkBox, ficheBooleanProp, z, z2, z3, null);
    }
    protected void createCheckboxSetCheckedChangeListener(FicheMode ficheMode, View view, final CheckBox checkBox, final FicheBooleanProp ficheBooleanProp, boolean z, boolean z2, boolean z3, final OnCheckBoxClickInterface onCheckBoxClickInterface) {
        if (!z) {
            setViewVisibilityGone(view);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE) {
            setViewDisabled(checkBox);
        } else {
            if (ficheBooleanProp == null) {
                setViewDisabled(view);
                return;
            }
            checkBox.setEnabled(z3);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {  
                public void onCheckedChanged(CompoundButton compoundButton, boolean z4) {
                    lambdacreateCheckboxSetCheckedChangeListener0(ficheBooleanProp, checkBox, onCheckBoxClickInterface, compoundButton, z4);
                }
            });
            checkBox.setChecked(z2);
        }
    } 
    public void lambdacreateCheckboxSetCheckedChangeListener0(FicheBooleanProp ficheBooleanProp, CheckBox checkBox, OnCheckBoxClickInterface onCheckBoxClickInterface, CompoundButton compoundButton, boolean z) {
        ficheBooleanProp.setSelect(z);
        FicheStringProp.setDefinition(getString(R.string.str_no));
        if (z) {
            FicheStringProp.setDefinition(getString(R.string.str_yes));
        }
        checkBox.setText(ficheBooleanProp.toString());
        if (onCheckBoxClickInterface != null) {
            onCheckBoxClickInterface.onCheckBoxClicked(z);
        }
    }
    protected void createCheckboxSetCheckedChangeListener(FicheMode ficheMode, View view, final CheckBox checkBox, final FicheBooleanProp ficheBooleanProp, boolean z, boolean z2, boolean z3, final OnCheckBoxClickInterface onCheckBoxClickInterface, final String str) {
        if (!z) {
            setViewVisibilityGone(view);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE) {
            setViewDisabled(checkBox);
        } else {
            if (ficheBooleanProp == null) {
                setViewDisabled(view);
                return;
            }
            checkBox.setEnabled(z3);
            checkBox.setOnCheckedChangeListener((compoundButton, z4) -> lambdacreateCheckboxSetCheckedChangeListener1(ficheBooleanProp, checkBox, onCheckBoxClickInterface, str, compoundButton, z4));
            checkBox.setChecked(z2);
        }
    } 
    public void lambdacreateCheckboxSetCheckedChangeListener1(FicheBooleanProp ficheBooleanProp, CheckBox checkBox, OnCheckBoxClickInterface onCheckBoxClickInterface, String str, CompoundButton compoundButton, boolean z) {
        ficheBooleanProp.setSelect(z);
        FicheStringProp.setDefinition(getString(R.string.str_no));
        if (z) {
            FicheStringProp.setDefinition(getString(R.string.str_yes));
        }
        checkBox.setText(ficheBooleanProp.toString());
        if (onCheckBoxClickInterface != null) {
            if (checkBox.getTag() == null || checkBox.getTag().toString().equals(str)) {
                onCheckBoxClickInterface.onCheckBoxClicked(z);
            }
        }
    }
    protected void createEditTextAddTextChangedListener(FicheMode ficheMode, EditText editText, FicheStringProp ficheStringProp, int r17) {
        createEditTextAddTextChangedListener(ficheMode, null, editText, ficheStringProp, true, false, 0.0d, 0.0d, r17, false);
    }
    protected void createEditTextAddTextChangedListener(FicheMode ficheMode, EditText editText, FicheStringProp ficheStringProp) {
        createEditTextAddTextChangedListener(ficheMode, null, editText, ficheStringProp, true, false, 0.0d, 0.0d);
    }
    protected void createEditTextAddTextChangedListener(FicheMode ficheMode, View view, EditText editText, FicheStringProp ficheStringProp) {
        createEditTextAddTextChangedListener(ficheMode, view, editText, ficheStringProp, true, false, 0.0d, 0.0d);
    }
    protected void createEditTextAddTextChangedListener(FicheMode ficheMode, View view, EditText editText, FicheStringProp ficheStringProp, boolean z) {
        createEditTextAddTextChangedListener(ficheMode, view, editText, ficheStringProp, z, false, 0.0d, 0.0d);
    }
    protected void createEditTextAddTextChangedListener(FicheMode ficheMode, View view, EditText editText, FicheStringProp ficheStringProp, boolean z, int r19) {
        createEditTextAddTextChangedListener(ficheMode, view, editText, ficheStringProp, z, false, 0.0d, 0.0d, r19, false);
    }
    protected void createEditTextAddTextChangedListener(FicheMode ficheMode, View view, EditText editText, FicheStringProp ficheStringProp, boolean z, boolean z2, double d2, double d3) {
        createEditTextAddTextChangedListener(ficheMode, view, editText, ficheStringProp, z, z2, d2, d3, 0, false);
    }
    protected void createEditTextAddTextChangedListener(FicheMode ficheMode, View view, final EditText editText, final FicheStringProp ficheStringProp, boolean z, final boolean z2, final double d2, final double d3, int r23, final boolean z3) {
        if (!z) {
            if (view != null) {
                setViewVisibilityGone(view);
                return;
            } else {
                setViewDisabled(editText);
                return;
            }
        }
        if (ficheMode == FicheMode.ANALYSE) {
            setViewDisabled(editText);
            return;
        }
        if (ficheStringProp == null) {
            setViewDisabled(view);
            return;
        }
        if (r23 != 0) {
            editText.setInputType(r23);
        }
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() { 
            public boolean onEditorAction(TextView textView, int r3, KeyEvent keyEvent) {
                return BaseFicheFragment.lambdacreateEditTextAddTextChangedListener2(ficheStringProp, editText, textView, r3, keyEvent);
            }
        });
        editText.addTextChangedListener(new TextWatcher() {  
            public void beforeTextChanged(CharSequence charSequence, int r2, int r3, int r4) {
            } 
            public void onTextChanged(CharSequence charSequence, int r2, int r3, int r4) {
            } 
            public void afterTextChanged(Editable editable) throws NumberFormatException {
                if (z2) {
                    double dConvertStringToDouble = StringUtils.convertStringToDouble(editable.toString());
                    if (dConvertStringToDouble < d2) {
                        Toast.makeText(BaseFicheFragment.this.getActivity(), StringUtils.catStringSpace(StringUtils.convertDoubleToString(Double.valueOf(d2))) + BaseFicheFragment.this.getString(R.string.exp_16_value_low), Toast.LENGTH_LONG).show();
                        editText.setText(ficheStringProp.getDefinition());
                        return;
                    }
                    if (dConvertStringToDouble > d3) {
                        Toast.makeText(BaseFicheFragment.this.getActivity(), StringUtils.catStringSpace(StringUtils.convertDoubleToString(Double.valueOf(d3))) + BaseFicheFragment.this.getString(R.string.exp_15_value_high), Toast.LENGTH_SHORT).show();
                        if (z3) {
                            editText.setText(IdManager.DEFAULT_VERSION_NAME);
                            return;
                        } else {
                            editText.setText(ficheStringProp.getDefinition());
                            return;
                        }
                    }
                    FicheStringProp.setDefinition(editable.toString());
                    return;
                }
                FicheStringProp.setDefinition(editable.toString());
            }
        });
    } 
    public static boolean lambdacreateEditTextAddTextChangedListener2(FicheStringProp ficheStringProp, EditText editText, TextView textView, int r3, KeyEvent keyEvent) {
        if (r3 != 6 && r3 != 5) {
            return false;
        }
        FicheStringProp.setDefinition(editText.getText().toString());
        return false;
    }
    protected void createSingleAlertCursorSales(FicheMode ficheMode, View view, TextView textView, FicheRefProp ficheRefProp, boolean z,int r19,int r20,int r21, String... strArr) {
        createSingleAlertCursorSales(ficheMode, view, textView, ficheRefProp, z, r19, -1, r20, r21, -1, true, strArr);
    }
    protected void createSingleAlertCursorSales(FicheMode ficheMode, View view, TextView textView, FicheRefProp ficheRefProp,int r18,int r19,int r20, String... strArr) {
        createSingleAlertCursorSales(ficheMode, view, textView, ficheRefProp, true, r18, -1, r19, r20, -1, true, strArr);
    }
    protected void createSingleAlertCursorSales(FicheMode ficheMode, View view, TextView textView, FicheRefProp ficheRefProp, boolean z,int r19,int r20,int r21,int r22, String... strArr) {
        createSingleAlertCursorSales(ficheMode, view, textView, ficheRefProp, z, r19, r20, r21, r22, -1, true, strArr);
    }
    protected void createSingleAlertCursorSales(FicheMode ficheMode, View view, TextView textView, FicheRefProp ficheRefProp, boolean z,int r20,int r21,int r22,int r23) {
        createSingleAlertCursorSales(ficheMode, view, textView, ficheRefProp, z, r20, r21, r22, r23, -1, true);
    }
    protected void createSingleAlertCursorSales(FicheMode ficheMode, View view, TextView textView, FicheRefProp ficheRefProp, boolean z,int r21,int r22,int r23,int r24, Object obj) {
        createSingleAlertCursorSales(ficheMode, view, textView, ficheRefProp, z, r21, r22, r23, r24, -1, true, obj);
    }
    protected void createSingleAlertCursorSales(FicheMode ficheMode, View view, TextView textView, FicheDiscountRefProp ficheDiscountRefProp, boolean z,int r21,int r22,int r23,int r24, Object obj) {
        createSingleAlertCursorSales(ficheMode, view, textView, ficheDiscountRefProp, z, r21, r22, r23, r24, -1, true, obj);
    }
    protected void createSingleAlertCursorSales(FicheMode ficheMode, View view, TextView textView, FicheDiscountRefProp ficheDiscountRefProp, boolean z,int r20,int r21,int r22,int r23, Object obj, String... strArr) {
        createSingleAlertCursorSales(ficheMode, view, textView, ficheDiscountRefProp, z, r20, r21, r22, r23, -1, true, obj, strArr);
    }
    protected void createSingleAlertCursorSales(FicheMode ficheMode, View view, TextView textView, FicheDiscountRefProp ficheDiscountRefProp, boolean z,int r21,int r22,int r23,int r24, Object obj, boolean z2, String... strArr) {
        createSingleAlertCursorSales(ficheMode, view, textView, ficheDiscountRefProp, z, r21, r22, r23, r24, -1, true, obj, z2, strArr);
    }
    protected void createSingleAlertCursorSales(FicheMode ficheMode, View view, TextView textView, FicheRefProp ficheRefProp, boolean z,int r20,int r21,int r22,int r23,int r24) {
        createSingleAlertCursorSales(ficheMode, view, textView, ficheRefProp, z, r20, r21, r22, r23, r24, true);
    }
    protected void createSingleAlertCursorSales(FicheMode ficheMode, View view, TextView textView, FicheRefProp ficheRefProp, boolean z,int r20,int r21,int r22,int r23, boolean z2) {
        createSingleAlertCursorSales(ficheMode, view, textView, ficheRefProp, z, r20, r21, r22, r23, -1, z2);
    }
    protected void createSingleAlertCursorSales(FicheMode ficheMode, View view, TextView textView, FicheRefProp ficheRefProp, boolean z,int r21,int r22,int r23,int r24, boolean z2, Object obj) {
        createSingleAlertCursorSales(ficheMode, view, textView, ficheRefProp, z, r21, r22, r23, r24, -1, z2, obj);
    }
    protected void createSingleAlertCursorSales(FicheMode ficheMode, View view, TextView textView, FicheDiscountRefProp ficheDiscountRefProp, boolean z,int r21,int r22,int r23,int r24, boolean z2, Object obj) {
        createSingleAlertCursorSales(ficheMode, view, textView, ficheDiscountRefProp, z, r21, r22, r23, r24, -1, z2, obj);
    }
    protected void createDateAlertDialog(FicheMode ficheMode, View view, final TextView textView, final FicheDateProp ficheDateProp, boolean z, final boolean z2,final int r14) {
        if (!z) {
            setViewVisibilityGone(view);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE) {
            setViewDisabled(textView);
        } else if (ficheDateProp == null) {
            setViewDisabled(textView);
        } else {
            final Calendar dateCalendar = DateAndTimeUtils.getDateCalendar(ficheDateProp.getDefinition(), "dd.MM.yyyy");
            view.setOnClickListener(new View.OnClickListener() {  
                public void onClick(View view2) {
                     lambdacreateDateAlertDialog5(dateCalendar, z2, ficheDateProp, textView, r14, view2);
                }
            });
        }
    } 
    public void lambdacreateDateAlertDialog5(final Calendar calendar, final boolean z, final FicheDateProp ficheDateProp, final TextView textView, int r11, View view) {
        if (ContextUtils.checkAutoTimeAndTimeZoneEnabledAndShowDialog()) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {  
                public void onDateSet(DatePicker datePicker, int r10, int r112, int r12) {
                    BaseFicheFragment.lambdacreateDateAlertDialog3(calendar, z, ficheDateProp, textView, datePicker, r10, r112, r12);
                }
            }, calendar.get(1), calendar.get(2), calendar.get(5));
            datePickerDialog.setTitle(StringUtils.catStringSpace(getString(r11), getString(R.string.str_select_text)));
            datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { 
                public void onCancel(DialogInterface dialogInterface) {
                    BaseFicheFragment.lambdacreateDateAlertDialog4(ficheDateProp, textView, dialogInterface);
                }
            });
            if (!z) {
                datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis() - 1000);
            }
            datePickerDialog.show();
        }
    } 
    public static void lambdacreateDateAlertDialog3(Calendar calendar, boolean z, FicheDateProp ficheDateProp, TextView textView, DatePicker datePicker, int r7, int r8, int r9) {
        calendar.set(1, r7);
        calendar.set(2, r8);
        calendar.set(5, r9);
        if (!z) {
            datePicker.setMinDate(calendar.getTimeInMillis() - 1000);
        }
        FicheStringProp.setDefinition(DateAndTimeUtils.calendarDateDMY(calendar));
        textView.setText(ficheDateProp.toString());
    } 
    public static void lambdacreateDateAlertDialog4(FicheDateProp ficheDateProp, TextView textView, DialogInterface dialogInterface) {
        ficheDateProp.reset();
        textView.setText(ficheDateProp.toString());
        dialogInterface.dismiss();
    }
    protected void createDateAlertDialog(FicheMode ficheMode, View view, final TextView textView, final FicheDateProp ficheDateProp, boolean z,  final int r13, final boolean z2) {
        if (!z) {
            setViewVisibilityGone(view);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE) {
            setViewDisabled(textView);
        } else if (ficheDateProp == null) {
            setViewDisabled(textView);
        } else {
            final Calendar calendar = Calendar.getInstance();
            view.setOnClickListener(new View.OnClickListener() {  
                public void onClick(View view2) {
                    lambdacreateDateAlertDialog8(calendar, ficheDateProp, textView, r13, z2, view2);
                }
            });
        }
    } 
    public void lambdacreateDateAlertDialog8(final Calendar calendar, final FicheDateProp ficheDateProp, final TextView textView, int r10, final boolean z, View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {  
            public void onDateSet(DatePicker datePicker, int r9, int r102, int r11) {
                BaseFicheFragment.lambdacreateDateAlertDialog6(calendar, ficheDateProp, textView, datePicker, r9, r102, r11);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5));
        datePickerDialog.setTitle(StringUtils.catStringSpace(getString(r10), getString(R.string.str_select_text)));
        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {  
            public void onCancel(DialogInterface dialogInterface) {
                BaseFicheFragment.lambdacreateDateAlertDialog7(z, ficheDateProp, textView, dialogInterface);
            }
        });
        datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis() - 1000);
        datePickerDialog.show();
    } 
    public static void lambdacreateDateAlertDialog6(Calendar calendar, FicheDateProp ficheDateProp, TextView textView, DatePicker datePicker, int r6, int r7, int r8) {
        calendar.set(1, r6);
        calendar.set(2, r7);
        calendar.set(5, r8);
        datePicker.setMinDate(calendar.getTimeInMillis() - 1000);
        FicheStringProp.setDefinition(DateAndTimeUtils.calendarDateDMY(calendar));
        textView.setText(ficheDateProp.toString());
    } 
    public static void lambdacreateDateAlertDialog7(boolean z, FicheDateProp ficheDateProp, TextView textView, DialogInterface dialogInterface) {
        if (z) {
            ficheDateProp.reset();
            textView.setText(ficheDateProp.toString());
        }
        dialogInterface.dismiss();
    }
    private void createSingleAlertCursorSales(FicheMode ficheMode, View view, final TextView textView, final FicheRefProp ficheRefProp, boolean z,final int r18,final int r19,final int r20,final int r21,int r22, final boolean z2, final String... strArr) {
        if (!z) {
            setViewVisibilityGone(view);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE) {
            setViewDisabled(textView);
        } else if (ficheRefProp == null) {
            setViewDisabled(textView);
        } else {
            loadTextView(textView, ficheRefProp.toString());
            view.setOnClickListener(new View.OnClickListener() {  
                public void onClick(View view2) {
                    lambdacreateSingleAlertCursorSales11(r20, strArr, r19, r18, z2, ficheRefProp, r21, textView, view2);
                }
            });
        }
    }
    public void lambdacreateSingleAlertCursorSales11(int r7, String[] strArr, int r9, int r10, boolean z, final FicheRefProp ficheRefProp, final int r13, final TextView textView, View view) {
        try {
            this.mAlertDialogBuilder.dismiss();
            final Cursor cursor = getCursor(getString(r7), strArr);
            if (cursor != null && cursor.getCount() != 0) {
                String string = getString(r10);
                if (z) {
                    string = StringUtils.catStringSpace(getString(r10), getString(R.string.str_select_text));
                }
                this.mAlertDialogBuilder.setTitle(string).setSingleChoiceItems(cursor, ficheRefProp.getWhich(), getString(r13), new DialogInterface.OnClickListener() {  
                    public void onClick(DialogInterface dialogInterface, int r92) {
                        lambdacreateSingleAlertCursorSales9(cursor, ficheRefProp, r13, textView, dialogInterface, r92);
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { 
                    public void onClick(DialogInterface dialogInterface, int r4) {
                        BaseFicheFragment.lambdacreateSingleAlertCursorSales10(ficheRefProp, textView, cursor, dialogInterface, r4);
                    }
                }).create().show();
                return;
            }
            if (cursor != null) {
                cursor.close();
            }
            if (r9 != -1) {
                Toast.makeText(getActivity(), getString(r9), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), getString(R.string.empty_text), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e2) {
            Log.e(TAG, "createSingleAlertCursorSales: ", e2);
        }
    }
    public void lambdacreateSingleAlertCursorSales9(Cursor cursor, FicheRefProp ficheRefProp, int r4, TextView textView, DialogInterface dialogInterface, int r7) {
        if (cursor.moveToPosition(r7)) {
            ficheRefProp.setWhich(r7);
            ficheRefProp.setLogicalRef(cursor.getInt( R.string.column_id));
            FicheStringProp.setDefinition(cursor.getString(r4));
            textView.setText(ficheRefProp.getDefinition());
        }
        cursor.close();
        if (ficheRefProp.getPropChangeListener() != null) {
            ficheRefProp.getPropChangeListener().onChange();
        }
        dialogInterface.dismiss();
    }
    public static void lambdacreateSingleAlertCursorSales10(FicheRefProp ficheRefProp, TextView textView, Cursor cursor, DialogInterface dialogInterface, int r4) {
        ficheRefProp.reset();
        textView.setText(ficheRefProp.toString());
        if (!cursor.isClosed()) {
            cursor.close();
        }
        dialogInterface.dismiss();
    }
    private void createSingleAlertCursorSales(FicheMode ficheMode, View view, final TextView textView, final FicheRefProp ficheRefProp, boolean z,final int r19,final int r20,final int r21,final int r22,int r23, final boolean z2, final Object obj, final String... strArr) {
        if (!z) {
            setViewVisibilityGone(view);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE) {
            setViewDisabled(textView);
        } else if (ficheRefProp == null) {
            setViewDisabled(textView);
        } else {
            loadTextView(textView, ficheRefProp.toString());
            view.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.core.base.BaseFicheFragmentExternalSyntheticLambda29
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    lambdacreateSingleAlertCursorSales14(r21, strArr, r20, r19, z2, ficheRefProp, r22, textView, obj, view2);
                }
            });
        }
    }
    public void lambdacreateSingleAlertCursorSales14(int r14, String[] strArr, int r16, int r17, boolean z, final FicheRefProp ficheRefProp, final int r20, final TextView textView, final Object obj, View view) {
        try {
            this.mAlertDialogBuilder.dismiss();
            final Cursor cursor = getCursor(getString(r14), strArr);
            if (cursor != null && cursor.getCount() != 0) {
                String string = getString(r17);
                if (z) {
                    string = StringUtils.catStringSpace(getString(r17), getString(R.string.str_select_text));
                }
                this.mAlertDialogBuilder.setTitle(string).setSingleChoiceItems(cursor, ficheRefProp.getWhich(), getString(r20), new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.core.base.BaseFicheFragmentExternalSyntheticLambda21
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int r10) {
                        lambdacreateSingleAlertCursorSales12(cursor, ficheRefProp, r20, textView, obj, dialogInterface, r10);
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.core.base.BaseFicheFragmentExternalSyntheticLambda22
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int r8) {
                        BaseFicheFragment.lambdacreateSingleAlertCursorSales13(ficheRefProp, textView, cursor, obj, dialogInterface, r8);
                    }
                }).create().show();
                return;
            }
            if (r16 != -1) {
                Toast.makeText(getActivity(), getString(r16), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), getString(R.string.empty_text), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e2) {
            Log.e(TAG, "createSingleAlertCursorSales: ", e2);
        }
    }
    public void lambdacreateSingleAlertCursorSales12(Cursor cursor, FicheRefProp ficheRefProp, int r4, TextView textView, Object obj, DialogInterface dialogInterface, int r8) {
        if (cursor.moveToPosition(r8)) {
            ficheRefProp.setWhich(r8);
            ficheRefProp.setLogicalRef(cursor.getInt( R.string.column_id));
            FicheStringProp.setDefinition(cursor.getString( r4));
            textView.setText(ficheRefProp.getDefinition());
            EventBus.getDefault().post(obj);
        }
        cursor.close();
        dialogInterface.dismiss();
    }
    public static void lambdacreateSingleAlertCursorSales13(FicheRefProp ficheRefProp, TextView textView, Cursor cursor, Object obj, DialogInterface dialogInterface, int r5) {
        ficheRefProp.reset();
        textView.setText(ficheRefProp.toString());
        if (!cursor.isClosed()) {
            cursor.close();
        }
        EventBus.getDefault().post(obj);
        dialogInterface.dismiss();
    }
    private void createSingleAlertCursorSales(FicheMode ficheMode, View view, final TextView textView, final FicheDiscountRefProp ficheDiscountRefProp, boolean z,final int r19,final int r20,final int r21,final int r22,int r23, final boolean z2, final Object obj, final String... strArr) {
        if (!z) {
            setViewVisibilityGone(view);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE) {
            setViewDisabled(textView);
        } else if (ficheDiscountRefProp == null) {
            setViewDisabled(textView);
        } else {
            loadTextView(textView, ficheDiscountRefProp.toString());
            view.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.core.base.BaseFicheFragmentExternalSyntheticLambda19
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    lambdacreateSingleAlertCursorSales17(r21, strArr, r20, r19, z2, ficheDiscountRefProp, r22, textView, obj, view2);
                }
            });
        }
    }
    public void lambdacreateSingleAlertCursorSales17(int r15, String[] strArr, int r17, final int r18, boolean z, final FicheDiscountRefProp ficheDiscountRefProp, final int r21, final TextView textView, final Object obj, View view) {
        try {
            this.mAlertDialogBuilder.dismiss();
            final Cursor cursor = getCursor(getString(r15), strArr);
            if (cursor != null && cursor.getCount() != 0) {
                String string = getString(r18);
                if (z) {
                    string = StringUtils.catStringSpace(getString(r18), getString(R.string.str_select_text));
                }
                this.mAlertDialogBuilder.setTitle(string).setSingleChoiceItems(cursor, ficheDiscountRefProp.getWhich(), getString(r21), new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.core.base.BaseFicheFragmentExternalSyntheticLambda4
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int r11) {
                        lambdacreateSingleAlertCursorSales15(cursor, ficheDiscountRefProp, r21, textView, r18, obj, dialogInterface, r11);
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.core.base.BaseFicheFragmentExternalSyntheticLambda5
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int r8) {
                        BaseFicheFragment.lambdacreateSingleAlertCursorSales16(ficheDiscountRefProp, textView, cursor, obj, dialogInterface, r8);
                    }
                }).create().show();
                return;
            }
            if (r17 != -1) {
                Toast.makeText(getActivity(), getString(r17), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), getString(R.string.empty_text), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e2) {
            Log.e(TAG, "createSingleAlertCursorSales: ", e2);
        }
    }
    public void lambdacreateSingleAlertCursorSales15(Cursor cursor, FicheDiscountRefProp ficheDiscountRefProp, int r4, TextView textView, int r6, Object obj, DialogInterface dialogInterface, int r9) {
        if (cursor.moveToPosition(r9)) {
            ficheDiscountRefProp.setWhich(r9);
            ficheDiscountRefProp.setLogicalRef(cursor.getInt( R.string.column_id));
            FicheStringProp.setDefinition(cursor.getString( r4));
            ficheDiscountRefProp.setCode(cursor.getString( R.string.column_code));
            textView.setText(ficheDiscountRefProp.getDefinition());
            saveSelectedItemToPreferences(ficheDiscountRefProp, r6);
            if (obj != null) {
                EventBus.getDefault().post(obj);
            }
        }
        cursor.close();
        dialogInterface.dismiss();
    }
    public static void lambdacreateSingleAlertCursorSales16(FicheDiscountRefProp ficheDiscountRefProp, TextView textView, Cursor cursor, Object obj, DialogInterface dialogInterface, int r5) {
        ficheDiscountRefProp.reset();
        textView.setText(ficheDiscountRefProp.toString());
        if (!cursor.isClosed()) {
            cursor.close();
        }
        if (obj != null) {
            EventBus.getDefault().post(obj);
        }
        dialogInterface.dismiss();
    }
    private void saveSelectedItemToPreferences(FicheDiscountRefProp ficheDiscountRefProp,int r3) {
        Gson gson = new Gson();
        if (r3 == R.string.str_banks) {
            Preferences.setCreditCardCollectionDefaultBank(ContextUtils.getmContext(), gson.toJson(ficheDiscountRefProp));
            Preferences.setCreditCardCollectionDefaultBankAccount(ContextUtils.getmContext(), null);
        } else if (r3 == R.string.str_bank_accounts) {
            Preferences.setCreditCardCollectionDefaultBankAccount(ContextUtils.getmContext(), gson.toJson(ficheDiscountRefProp));
        }
    }
    private void createSingleAlertCursorSales(FicheMode ficheMode, View view, final TextView textView, final FicheDiscountRefProp ficheDiscountRefProp, boolean z,final int r20,final int r21,final int r22,final int r23,int r24, final boolean z2, final Object obj, final boolean z3, final String... strArr) {
        if (!z) {
            setViewVisibilityGone(view);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE) {
            setViewDisabled(textView);
        } else if (ficheDiscountRefProp == null) {
            setViewDisabled(textView);
        } else {
            loadTextView(textView, ficheDiscountRefProp.toString());
            view.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.core.base.BaseFicheFragmentExternalSyntheticLambda30
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    lambdacreateSingleAlertCursorSales20(r22, strArr, r21, r20, z2, ficheDiscountRefProp, r23, textView, obj, z3, view2);
                }
            });
        }
    }
    public void lambdacreateSingleAlertCursorSales20(int r14, String[] strArr, int r16, int r17, boolean z, final FicheDiscountRefProp ficheDiscountRefProp, final int r20, final TextView textView, final Object obj, final boolean z2, View view) {
        try {
            this.mAlertDialogBuilder.dismiss();
            final Cursor cursor = getCursor(getString(r14), strArr);
            if (cursor != null && cursor.getCount() != 0) {
                String string = getString(r17);
                if (z) {
                    string = StringUtils.catStringSpace(getString(r17), getString(R.string.str_select_text));
                }
                this.mAlertDialogBuilder.setTitle(string).setSingleChoiceItems(cursor, ficheDiscountRefProp.getWhich(), getString(r20), new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.core.base.BaseFicheFragmentExternalSyntheticLambda26
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int r10) {
                        lambdacreateSingleAlertCursorSales18(cursor, ficheDiscountRefProp, r20, textView, obj, dialogInterface, r10);
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.core.base.BaseFicheFragmentExternalSyntheticLambda27
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int r9) {
                        BaseFicheFragment.lambdacreateSingleAlertCursorSales19(z2, ficheDiscountRefProp, textView, cursor, obj, dialogInterface, r9);
                    }
                }).create().show();
                return;
            }
            if (r16 != -1) {
                Toast.makeText(getActivity(), getString(r16), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), getString(R.string.empty_text), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e2) {
            Log.e(TAG, "createSingleAlertCursorSales: ", e2);
        }
    } 
    public void lambdacreateSingleAlertCursorSales18(Cursor cursor, FicheDiscountRefProp ficheDiscountRefProp, int r4, TextView textView, Object obj, DialogInterface dialogInterface, int r8) {
        if (cursor.moveToPosition(r8)) {
            ficheDiscountRefProp.setWhich(r8);
            ficheDiscountRefProp.setLogicalRef(cursor.getInt(R.string.column_id));
            FicheStringProp.setDefinition(cursor.getString( r4));
            ficheDiscountRefProp.setCode(cursor.getString(R.string.column_code));
            textView.setText(ficheDiscountRefProp.getDefinition());
            if (obj != null) {
                EventBus.getDefault().post(obj);
            }
        }
        cursor.close();
        dialogInterface.dismiss();
    } 
    public static void lambdacreateSingleAlertCursorSales19(boolean z, FicheDiscountRefProp ficheDiscountRefProp, TextView textView, Cursor cursor, Object obj, DialogInterface dialogInterface, int r6) {
        if (z && ficheDiscountRefProp.getLogicalRef() > 0) {
            ficheDiscountRefProp.reset();
            textView.setText(ficheDiscountRefProp.toString());
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        if (obj != null) {
            EventBus.getDefault().post(obj);
        }
        dialogInterface.dismiss();
    }
    protected void createSingleAlertCursorSalesArray(FicheMode ficheMode, View view, final TextView textView, final FicheRefProp ficheRefProp, boolean z,final int r13, @ArrayRes final int r14, final boolean z2) {
        if (!z) {
            setViewVisibilityGone(view);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE) {
            setViewDisabled(textView);
        } else if (ficheRefProp == null) {
            setViewDisabled(textView);
        } else {
            loadTextView(textView, ficheRefProp.toString());
            view.setOnClickListener(new View.OnClickListener() {  
                public void onClick(View view2) {
                    lambdacreateSingleAlertCursorSalesArray23(r13, z2, r14, ficheRefProp, textView, view2);
                }
            });
        }
    } 
    public void lambdacreateSingleAlertCursorSalesArray23(int r1, boolean z, final int r3, final FicheRefProp ficheRefProp, final TextView textView, View view) {
        try {
            this.mArrayAlertDialogBuilder.dismiss();
            String string = getString(r1);
            if (z) {
                string = StringUtils.catStringSpace(getString(r1), getString(R.string.str_select_text));
            }
            this.mArrayAlertDialogBuilder.setTitle(string).setSingleChoice(r3, ficheRefProp.getWhich(), new DialogInterface.OnClickListener() { 
                public void onClick(DialogInterface dialogInterface, int r8) {
                    lambdacreateSingleAlertCursorSalesArray21(r3, ficheRefProp, textView, dialogInterface, r8);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { 
                public void onClick(DialogInterface dialogInterface, int r32) {
                    BaseFicheFragment.lambdacreateSingleAlertCursorSalesArray22(ficheRefProp, textView, dialogInterface, r32);
                }
            }).create().show();
        } catch (Exception e2) {
            Log.e(TAG, "createSingleAlertCursorSales: ", e2);
        }
    } 
    public void lambdacreateSingleAlertCursorSalesArray21(int r1, FicheRefProp ficheRefProp, TextView textView, DialogInterface dialogInterface, int r5) {
        String str = getResources().getStringArray(r1)[r5];
        ficheRefProp.setLogicalRef(r5);
        FicheStringProp.setDefinition(str);
        ficheRefProp.setWhich(r5);
        textView.setText(ficheRefProp.toString());
        dialogInterface.dismiss();
    } 
    public static void lambdacreateSingleAlertCursorSalesArray22(FicheRefProp ficheRefProp, TextView textView, DialogInterface dialogInterface, int r3) {
        ficheRefProp.reset();
        textView.setText(ficheRefProp.toString());
        dialogInterface.dismiss();
    }
    public void createSingleAlertCursorDiscountSales(FicheMode ficheMode, View view, TextView textView, FicheDiscountRefProp ficheDiscountRefProp, FicheDiscountProp ficheDiscountProp, EditText editText, FicheStringProp ficheStringProp, EditText editText2, boolean z,int r25,int r26,int r27, String... strArr) {
        createSingleAlertCursorDiscountSales(ficheMode, view, textView, ficheDiscountRefProp, ficheDiscountProp, editText, ficheStringProp, editText2, z, r25, R.string.str_discount_code_not_found, r26, r27, strArr);
    }
    public void createSingleAlertCursorDiscountSales(FicheMode ficheMode, View view, final TextView textView, final FicheDiscountRefProp ficheDiscountRefProp, final FicheDiscountProp ficheDiscountProp, final EditText editText, final FicheStringProp ficheStringProp, final EditText editText2, boolean z,final int r25,final int r26,final int r27,final int r28, final String... strArr) {
        if (!z) {
            setViewVisibilityGone(view);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE) {
            setViewDisabled(textView);
        } else if (ficheDiscountRefProp == null) {
            setViewDisabled(textView);
        } else {
            loadTextView(textView, ficheDiscountRefProp.toString());
            view.setOnClickListener(new View.OnClickListener() {  
                public void onClick(View view2) {
                    lambdacreateSingleAlertCursorDiscountSales26(r27, strArr, r26, r25, ficheDiscountRefProp, r28, textView, ficheStringProp, editText2, ficheDiscountProp, editText, view2);
                }
            });
        }
    } 
    public void lambdacreateSingleAlertCursorDiscountSales26(int r17, String[] strArr, final int r19, int r20, final FicheDiscountRefProp ficheDiscountRefProp, final int r22, final TextView textView, final FicheStringProp ficheStringProp, final EditText editText, final FicheDiscountProp ficheDiscountProp, final EditText editText2, View view) {
        this.mAlertDialogBuilder.dismiss();
        final Cursor cursor = getCursor(getString(r17), strArr);
        if (cursor != null && cursor.getCount() != 0) {
            this.mAlertDialogBuilder.setTitle(getString(r20)).setSingleChoiceItems(cursor, ficheDiscountRefProp.getWhich(), getString(r22), new DialogInterface.OnClickListener() { 
                public void onClick(DialogInterface dialogInterface, int r14) {
                    lambdacreateSingleAlertCursorDiscountSales24(cursor, ficheDiscountRefProp, r22, textView, ficheStringProp, editText, ficheDiscountProp, editText2, r19, dialogInterface, r14);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { 
                public void onClick(DialogInterface dialogInterface, int r10) {
                    lambdacreateSingleAlertCursorDiscountSales25(ficheDiscountRefProp, textView, ficheDiscountProp, editText2, cursor, dialogInterface, r10);
                }
            }).create().show();
        } else if (r19 != -1) {
            Toast.makeText(getActivity(), getString(r19), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), getString(R.string.empty_text), Toast.LENGTH_SHORT).show();
        }
    } 
    public void lambdacreateSingleAlertCursorDiscountSales24(Cursor cursor, FicheDiscountRefProp ficheDiscountRefProp, int r5, TextView textView, FicheStringProp ficheStringProp, EditText editText, final FicheDiscountProp ficheDiscountProp, final EditText editText2, final int r11, DialogInterface dialogInterface, int r13) {
        boolean z;
        Sales clonedSales;
        if (cursor.moveToPosition(r13)) {
            ficheDiscountRefProp.setWhich(r13);
            ficheDiscountRefProp.setLogicalRef(cursor.getInt(R.string.column_id));
            FicheStringProp.setDefinition(cursor.getString( r5));
            ficheDiscountRefProp.setCode(cursor.getString(R.string.column_code));
            textView.setText(ficheDiscountRefProp.toString());
            resetDiscountEdt(ficheStringProp, editText, ficheDiscountProp, editText2);
            z = true;
        } else {
            z = false;
        }
        cursor.close();
        dialogInterface.dismiss();
        if (z && (this instanceof ISalesDiscountFragment) && (clonedSales = ((ISalesDiscountFragment) this).getClonedSales()) != null) {
            if (this.baseErp.getErpType() == ErpType.NETSIS) {
                clearDiscountsThatBoundedToCard(ficheDiscountRefProp.getGuid());
            } else if (!clonedSales.getMSalesDetailList().isEmpty() && ContextUtils.checkConnectionWithoutMessage()) {
                ficheDiscountProp.setBoundedToCard(true);
                this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
                this.baseErp.getDiscountCardRatio(clonedSales, ficheDiscountRefProp.getGuid(), new ResponseListener<Double>() {  
                    public void onResponse(PrintSlipModel d2) {
                        BaseFicheFragment.this.mProgressDialogBuilder.dismiss();
                        if (d2 == null) {
                            return;
                        }
                        String strConvertDoubleToString = StringUtils.convertDoubleToString(d2.getGrossTotal());
                        editText2.setEnabled(false);
                        FicheStringProp.setDefinition(strConvertDoubleToString);
                        editText2.setText(strConvertDoubleToString);
                    } 
                    public void onFailure(Throwable throwable) {
                        
                    }
                    public void onResponse(Boolean aBoolean) {}
                    public void onResponse(Sales sales) {}
                    public void onResponse(TigerServiceResult tigerServiceResult) {}
                    public void onResponse(Double obj) {}
                    public void onResponse(ArrayList<Double> obj) {}
                    public void onResponse() {}
                    public void onError(String str) {
                        BaseFicheFragment.this.mProgressDialogBuilder.dismiss();
                        Toast.makeText(BaseFicheFragment.this.getActivity(), BaseFicheFragment.this.getString(r11), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    } 
    public void lambdacreateSingleAlertCursorDiscountSales25(FicheDiscountRefProp ficheDiscountRefProp, TextView textView, FicheDiscountProp ficheDiscountProp, EditText editText, Cursor cursor, DialogInterface dialogInterface, int r7) {
        clearDiscountsThatBoundedToCard(ficheDiscountRefProp.getGuid());
        ficheDiscountRefProp.reset();
        textView.setText(ficheDiscountRefProp.toString());
        if (ficheDiscountProp.isBoundedToCard()) {
            ficheDiscountProp.reset();
            ficheDiscountProp.setBoundedToCard(false);
            editText.setEnabled(true);
            editText.setText(ficheDiscountProp.toString());
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        dialogInterface.dismiss();
    } 
    private void clearDiscountsThatBoundedToCard(String str) {
        if (this.baseErp.getErpType() == ErpType.NETSIS && (this instanceof ISalesDiscountFragment)) {
            Sales clonedSales = ((ISalesDiscountFragment) this).getClonedSales();
            if (clonedSales.findDiscountByGuid(str)) {
                resetFicheDiscounts(clonedSales.getSalesFicheDiscountProps());
                return;
            }
            for (SalesDetail next : clonedSales.getMSalesDetailList()) {
                if (next.findDiscountByGuid(str)) {
                    resetFicheDiscounts(next.getSalesFicheDiscountProps());
                    return;
                }
            }
        }
    } 
    private void resetFicheDiscounts(SalesFicheDiscountProps salesFicheDiscountProps) {
        if (this instanceof ISalesDiscountFragment iSalesDiscountFragment) {
            if (iSalesDiscountFragment.getEdtSalesDetailDiscountRatios() == null && iSalesDiscountFragment.getEdtSalesDetailDiscountTotals() == null) {
                return;
            }
            Iterator<SalesFicheDiscount> it = salesFicheDiscountProps.getDiscounts().iterator();
            int r1 = 0;
            while (it.hasNext()) {
                SalesFicheDiscount next = it.next();
                if (next.getDiscountRatio().isBoundedToCard() && iSalesDiscountFragment.getEdtSalesDetailDiscountRatios().length >= r1 + 1) {
                    EditText editText = iSalesDiscountFragment.getEdtSalesDetailDiscountRatios()[r1];
                    next.getDiscountRatio().reset();
                    next.getDiscountRatio().setBoundedToCard(false);
                    editText.setEnabled(true);
                    editText.setText(next.getDiscountRatio().toString());
                }
                if (next.getDiscountTotal().isBoundedToCard() && iSalesDiscountFragment.getEdtSalesDetailDiscountTotals().length >= r1 + 1) {
                    EditText editText2 = iSalesDiscountFragment.getEdtSalesDetailDiscountTotals()[r1];
                    next.getDiscountTotal().reset();
                    next.getDiscountTotal().setBoundedToCard(false);
                    editText2.setEnabled(true);
                    editText2.setText(next.getDiscountTotal().toString());
                }
                r1++;
            }
        }
    }
    public void createSingleChoiceSales(View view, final TextView textView, final FicheRefProp ficheRefProp, boolean z,  final int r12, final CharSequence[] charSequenceArr, final boolean z2) {
        if (!z) {
            setViewVisibilityGone(view);
        } else if (ficheRefProp == null) {
            setViewDisabled(textView);
        } else {
            loadTextView(textView, ficheRefProp.toString());
            view.setOnClickListener(new View.OnClickListener() { 
                public void onClick(View view2) {
                    lambdacreateSingleChoiceSales29(r12, z2, charSequenceArr, ficheRefProp, textView, view2);
                }
            });
        }
    }  
    public void lambdacreateSingleChoiceSales29(int r1, boolean z, CharSequence[] charSequenceArr, final FicheRefProp ficheRefProp, final TextView textView, View view) {
        String string = getString(r1);
        if (z) {
            string = StringUtils.catStringSpace(getString(r1), getString(R.string.str_select_text));
        }
        this.mAlertDialogBuilder.setTitle(string).setSingleChoice(charSequenceArr, ficheRefProp.getWhich(), new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialogInterface, int r3) {
                BaseFicheFragment.lambdacreateSingleChoiceSales27(ficheRefProp, textView, dialogInterface, r3);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialogInterface, int r3) {
                BaseFicheFragment.lambdacreateSingleChoiceSales28(ficheRefProp, textView, dialogInterface, r3);
            }
        }).create().show();
    }
    public static void lambdacreateSingleChoiceSales27(FicheRefProp ficheRefProp, TextView textView, DialogInterface dialogInterface, int r3) {
        ficheRefProp.setWhich(r3);
        ficheRefProp.setLogicalRef(r3);
        textView.setText(ficheRefProp.getDefinition());
        dialogInterface.dismiss();
    }
    public static void lambdacreateSingleChoiceSales28(FicheRefProp ficheRefProp, TextView textView, DialogInterface dialogInterface, int r3) {
        ficheRefProp.reset();
        textView.setText(ficheRefProp.toString());
        dialogInterface.dismiss();
    }
    protected void setViewVisibilityGone(View view) { view.setVisibility(View.GONE); }
    protected void setViewVisible(View view) { view.setVisibility(View.VISIBLE); }
    protected void setViewDisabled(View view) { view.setEnabled(false); }
    protected void setViewEnabled(View view) { view.setEnabled(true); }
    protected void setViewText(TextView textView, String str) { textView.setText(str); }
    protected void setViewText(EditText editText, String str) { editText.setText(str); }
    protected void loadCheckView(CheckBox checkBox, boolean z) { loadCheckView(checkBox, z, true); }
    protected void loadCheckView(CheckBox checkBox, boolean z, boolean z2) {
        if (z2) {
            checkBox.setChecked(z);
            checkBox.setText(z ? getString(R.string.str_yes) : getString(R.string.str_no));
            checkBox.jumpDrawablesToCurrentState();
        }
    }
    protected void loadTextView(TextView textView, String str) { loadTextView(textView, str, true); }
    protected void loadTextView(TextView textView, String str, boolean z) {
        if (!z || textView == null) {
            return;
        }
        textView.setText(str);
    }
    protected void loadTextViewStringArray(TextView textView,  int r3, int r4) throws Resources.NotFoundException {
        loadTextViewStringArray(textView, r3, r4, true);
    }
    protected void loadTextViewStringArray(TextView textView, int r2, int r3, boolean z) throws Resources.NotFoundException {
        if (!z || textView == null || r3 <= -1) {
            return;
        }
        String[] stringArray = getResources().getStringArray(r2);
        if (stringArray.length > r3) {
            textView.setText(stringArray[r3]);
        }
    }
    protected void loadTextView(EditText editText, String str) { loadTextView(editText, str, true); }
    protected void loadTextView(EditText editText, String str, boolean z) {
        if (z) {
            try {
                editText.setText(str);
            } catch (Exception e2) {
                Log.e(TAG, "loadTextView: ", e2);
            }
        }
    }
    protected void createDiscountEditTextSetTextChangeListener(FicheMode ficheMode, View view, EditText editText, FicheDiscountProp ficheDiscountProp, FicheDiscountProp ficheDiscountProp2, EditText editText2, FicheDiscountRefProp ficheDiscountRefProp, TextView textView, double d2, double d3, boolean z) {
        createDiscountEditTextSetTextChangeListener(ficheMode, view, editText, ficheDiscountProp, ficheDiscountProp2, editText2, ficheDiscountRefProp, textView, d2, d3, z, true, true);
    }
    protected void createDiscountEditTextSetTextChangeListener(FicheMode ficheMode, View view, EditText editText, FicheDiscountProp ficheDiscountProp, FicheDiscountProp ficheDiscountProp2, EditText editText2, FicheDiscountRefProp ficheDiscountRefProp, TextView textView, boolean z) {
        createDiscountEditTextSetTextChangeListener(ficheMode, view, editText, ficheDiscountProp, ficheDiscountProp2, editText2, ficheDiscountRefProp, textView, 0.0d, 0.0d, z, false, true);
    }
    private void createDiscountEditTextSetTextChangeListener(FicheMode ficheMode, View view, EditText editText, FicheDiscountProp ficheDiscountProp, FicheDiscountProp ficheDiscountProp2, EditText editText2, FicheDiscountRefProp ficheDiscountRefProp, TextView textView, double d2, double d3, boolean z, boolean z2, boolean z3) {
        if (!z) {
            if (view != null) {
                setViewVisibilityGone(view);
            } else {
                setViewDisabled(editText);
                return;
            }
        }
        if (ficheDiscountProp.isBoundedToCard()) {
            setViewDisabled(editText);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE) {
            setViewDisabled(editText);
            return;
        }
        MutableWatcher mutableWatcher = new MutableWatcher();
        editText.addTextChangedListener(mutableWatcher);
        mutableWatcher.setActive(false);
        mutableWatcher.setError(false);
        editText.setSelectAllOnFocus(true);
        CharSequence charSequenceConvertDoubleToString = StringUtils.convertDoubleToString(Double.valueOf(ficheDiscountProp.getDefinitionDouble()));
        TextView.BufferType bufferType = TextView.BufferType.EDITABLE;
        editText.setText(charSequenceConvertDoubleToString, bufferType);
        mutableWatcher.setFirstEditText(editText);
        mutableWatcher.setFirstProp(ficheDiscountProp);
        mutableWatcher.setSecondEditText(editText2);
        mutableWatcher.setSecondProp(ficheDiscountProp2);
        editText2.setText(StringUtils.convertDoubleToString(Double.valueOf(ficheDiscountProp2.getDefinitionDouble())), bufferType);
        mutableWatcher.setCardTextView(textView);
        mutableWatcher.setCardProp(ficheDiscountRefProp);
        textView.setText(StringUtils.convertDoubleToString(Double.valueOf(ficheDiscountRefProp.getDefinitionDouble())), bufferType);
        mutableWatcher.setMinValue(d2);
        mutableWatcher.setMaxValue(d3);
        mutableWatcher.setControl(z2);
        mutableWatcher.setActive(true);
    }
    protected void resetDiscountEdt(FicheStringProp ficheStringProp, EditText editText, FicheStringProp ficheStringProp2, EditText editText2) {
        ficheStringProp.reset();
        loadTextView(editText, ficheStringProp.toString());
        ficheStringProp2.reset();
        loadTextView(editText2, ficheStringProp2.toString());
    }
    class MutableWatcher implements TextWatcher {
        private boolean mActive;
        private FicheDiscountRefProp mCardProp;
        private TextView mCardTextView;
        private boolean mControl;
        private boolean mError;
        private EditText mFirstEditText;
        private FicheDiscountProp mFirstProp;
        private boolean mRatio;
        private EditText mSecondEditText;
        private FicheDiscountProp mSecondProp;
        private double maxValue;
        private double minValue;
 
        public void afterTextChanged(Editable editable) {
        } 
        public void beforeTextChanged(CharSequence charSequence, int r2, int r3, int r4) {
        }

        MutableWatcher() {
        }

        public void setFirstEditText(EditText editText) {
            this.mFirstEditText = editText;
        }

        public void setFirstProp(FicheDiscountProp ficheDiscountProp) {
            this.mFirstProp = ficheDiscountProp;
        }

        public void setSecondEditText(EditText editText) {
            this.mSecondEditText = editText;
        }

        public void setSecondProp(FicheDiscountProp ficheDiscountProp) {
            this.mSecondProp = ficheDiscountProp;
        }

        public void setCardTextView(TextView textView) {
            this.mCardTextView = textView;
        }

        public void setCardProp(FicheDiscountRefProp ficheDiscountRefProp) {
            this.mCardProp = ficheDiscountRefProp;
        }

        public void setRatio(boolean z) {
            this.mRatio = z;
        }

        public void setActive(boolean z) {
            this.mActive = z;
        }

        public void setError(boolean z) {
            this.mError = z;
        }

        public void setMaxValue(double d2) {
            this.maxValue = d2;
        }

        public void setMinValue(double d2) {
            this.minValue = d2;
        }

        public void setControl(boolean z) {
            this.mControl = z;
        }
 
        public void onTextChanged(CharSequence charSequence, int r6, int r7, int r8) throws NumberFormatException {
            if (this.mActive) {
                double dConvertStringToDouble = StringUtils.convertStringToDouble(this.mFirstEditText.getText().toString());
                if (this.mControl) {
                    if (dConvertStringToDouble < this.minValue) {
                        Toast.makeText(BaseFicheFragment.this.getActivity(), StringUtils.catStringSpace(StringUtils.convertDoubleToString(Double.valueOf(this.minValue))) + BaseFicheFragment.this.getString(R.string.exp_16_value_low), Toast.LENGTH_LONG).show();
                        setProp(0.0d);
                        return;
                    }
                    if (dConvertStringToDouble > this.maxValue) {
                        Toast.makeText(BaseFicheFragment.this.getActivity(), StringUtils.catStringSpace(StringUtils.convertDoubleToString(Double.valueOf(this.maxValue))) + BaseFicheFragment.this.getString(R.string.exp_15_value_high), Toast.LENGTH_LONG).show();
                        setProp(0.0d);
                        return;
                    }
                }
                if (dConvertStringToDouble > 0.0d) {
                    FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(dConvertStringToDouble)));
                    resetProp();
                } else {
                    FicheStringProp.setDefinition("");
                }
            }
        }

        private void setProp(double d2) {
            FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(d2)));
            this.mActive = false;
            this.mFirstEditText.setText(this.mFirstProp.getDefinition());
            this.mFirstEditText.setSelected(true);
            this.mFirstEditText.setSelectAllOnFocus(true);
            this.mActive = true;
        }

        private void resetProp() {
            this.mSecondProp.reset();
            this.mSecondEditText.setText(this.mSecondProp.getDefinition());
            if (this.mFirstProp.isBoundedToCard()) {
                return;
            }
            this.mCardProp.reset();
            this.mCardTextView.setText(this.mCardProp.getDefinition());
        }
    }
    protected final void loadImageView(ImageView imageView, Bitmap bitmap) { loadImageView(imageView, bitmap, true); }
    protected final void loadImageView(ImageView imageView, Bitmap bitmap, boolean z) { if (z) { imageView.setImageBitmap(bitmap); } }
    protected void createSearchDialogCursorSales(FicheMode ficheMode, View view, TextView textView, FicheStringProp.FicheAttributes ficheAttributes, boolean z, int r19,int r20,int r21, String... strArr) {
        createSearchDialogCursorSales(ficheMode, view, textView, ficheAttributes, z, r19, -1, r20, r21, -1, true, strArr);
    }
    protected void createSearchDialogCursorSales(FicheMode ficheMode, View view, final TextView textView, final FicheStringProp.FicheAttributes ficheAttributes, boolean z,final int r18,final int r19,final int r20,final int r21,int r22, final boolean z2, final String... strArr) {
        if (!z) {
            setViewVisibilityGone(view);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE) {
            setViewDisabled(textView);
        } else if (ficheAttributes == null) {
            setViewDisabled(textView);
        } else {
            loadTextView(textView, ficheAttributes.toString());
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view2) {
                    lambdacreateSearchDialogCursorSales30(r20, strArr, r19, r18, z2, r21, ficheAttributes, textView, view2);
                }
            });
        }
    }
    public void lambdacreateSearchDialogCursorSales30(int r2, String[] strArr, int r4, int r5, boolean z, int r7, final FicheStringProp.FicheAttributes ficheAttributes, final TextView textView, View view) {
        try {
            Cursor cursor = getCursor(getString(r2), strArr);
            if (cursor != null && cursor.getCount() != 0) {
                String string = getString(r5);
                if (z) {
                    string = StringUtils.catStringSpace(getString(r5), getString(R.string.str_select_text));
                }
                final ArrayList<BaseSearchModel> baseSearchModelsFromCursor = getBaseSearchModelsFromCursor(cursor, r7, ficheAttributes, strArr);
                new SimpleSearchDialogCompat(getActivity(), string, "", null, baseSearchModelsFromCursor, new SearchResultListener<BaseSearchModel>() {
                    public void onSelected(BaseSearchDialogCompat baseSearchDialogCompat, BaseSearchModel baseSearchModel, int r6) {
                        ficheAttributes.setWhich(BaseFicheFragment.this.findSearchModelPosition(baseSearchModelsFromCursor, baseSearchModel.getLogicalRef()));
                        ficheAttributes.setLogicalRef(baseSearchModel.getLogicalRef());
                        ficheAttributes.setDefinition(baseSearchModel.getTitle());
                        textView.setText(ficheAttributes.getDefinition());
                        baseSearchDialogCompat.dismiss();
                    }
                    public void onCancelled(BaseSearchDialogCompat baseSearchDialogCompat) {
                        ficheAttributes.reset();
                        textView.setText(ficheAttributes.toString());
                        baseSearchDialogCompat.dismiss();
                    }
                }).show();
                return;
            }
            if (cursor != null) {
                cursor.close();
            }
            if (r4 != -1) {
                Toast.makeText(getActivity(), getString(r4), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), getString(R.string.empty_text), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e2) {
            Log.e(TAG, "createSearchDialogCursorSales: ", e2);
        }
    }
    public int findSearchModelPosition(ArrayList<BaseSearchModel> arrayList, int r3) {
        Iterator<BaseSearchModel> it = arrayList.iterator();
        int r2 = 0;
        while (it.hasNext() && it.next().getLogicalRef() != r3) {
            r2++;
        }
        return r2;
    }
    protected ArrayList<BaseSearchModel> getBaseSearchModelsFromCursor(Cursor cursor,int r8, FicheStringProp.FicheAttributes ficheAttributes, String... strArr) {
        ArrayList<BaseSearchModel> arrayList = new ArrayList<>();
        try {
            if (cursor != null) {
                try {
                    if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                        int r1 = 0;
                        do {
                            int r2 = cursor.getInt( R.string.column_id);
                            arrayList.add(new BaseSearchModel(r2, cursor.getString( r8), r1 == ficheAttributes.getWhich() || r2 == ficheAttributes.getLogicalRef()));
                            r1++;
                        } while (cursor.moveToNext());
                    }
                    if (!cursor.isClosed()) {
                        cursor.close();
                    }
                } catch (Exception e2) {
                    Log.e(TAG, "getBaseSearchModelsFromCursor: ", e2);
                }
                if (cursor != null) {
                    cursor.close();
                }
            }
            return arrayList;
        } catch (Throwable th) {
            cursor.close();
            throw th;
        }
    }
    protected void createBottomSearchDialogCursorSales(FicheMode ficheMode, View view, TextView textView, FicheRefProp ficheRefProp, boolean z,int r22,int r23,int r24,int r25,int r26, boolean z2, String str, String... strArr) {
        createBottomSearchDialogCursorSales(ficheMode, view, textView, ficheRefProp, z, r22, -1, r24, r25, r26, -1, true, z2, str, strArr);
    }
    private void createBottomSearchDialogCursorSales(FicheMode ficheMode, View view, final TextView textView, final FicheRefProp ficheRefProp, boolean z,final int r21,final int r22,final int r23,final int r24,final int r25,int r26, final boolean z2, final boolean z3, final String str, final String... strArr) {
        if (!z) {
            setViewVisibilityGone(view);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE) {
            setViewDisabled(textView);
        } else if (ficheRefProp == null) {
            setViewDisabled(textView);
        } else {
            loadTextView(textView, ficheRefProp.toString());
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view2) {
                    lambdacreateBottomSearchDialogCursorSales31(r23, r24, r25, ficheRefProp, strArr, r22, r21, z2, z3, textView, str, view2);
                }
            });
        }
    }
    public void lambdacreateBottomSearchDialogCursorSales31(int r11, int r12, int r13, final FicheRefProp ficheRefProp, String[] strArr, int r16, int r17, boolean z, boolean z2, final TextView textView, String str, View view) {
        try {
            final ArrayList<BaseSearchModel> baseSearchModelList = this.baseErp.getBaseSearchModelList(getString(r11), getString(r12), getString(r13), ficheRefProp.getWhich(), ficheRefProp.getLogicalRef(), strArr);
            if (baseSearchModelList != null && !baseSearchModelList.isEmpty()) {
                String string = getString(r17);
                if (z) {
                    string = StringUtils.catStringSpace(getString(r17), getString(R.string.str_select_text));
                }
                BaseSearchModelListDialogFragment<?> baseSearchModelListDialogFragmentNewInstance = BaseSearchModelListDialogFragment.Companion.newInstance(string, baseSearchModelList, z2);
                baseSearchModelListDialogFragmentNewInstance.setSearchResultListener(new BottomSheetDialogSearchResultListener<BaseSearchModel>() {
                    public void onSelected(BottomSheetDialog bottomSheetDialog, BaseSearchModel baseSearchModel, int r6) {
                        ficheRefProp.setWhich(BaseFicheFragment.this.findSearchModelPosition(baseSearchModelList, baseSearchModel.getLogicalRef()));
                        ficheRefProp.setLogicalRef(baseSearchModel.getLogicalRef());
                        FicheStringProp.setDefinition(baseSearchModel.getTitle());
                        textView.setText(baseSearchModel.getTitle());
                        bottomSheetDialog.dismiss();
                    }

                    public void onCancelled(BottomSheetDialog bottomSheetDialog) {
                        ficheRefProp.reset();
                        textView.setText("");
                        bottomSheetDialog.dismiss();
                    }
                });
                baseSearchModelListDialogFragmentNewInstance.setCancelable(false);
                baseSearchModelListDialogFragmentNewInstance.show(getActivity().getSupportFragmentManager(), str);
                return;
            }
            if (r16 != -1) {
                Toast.makeText(getActivity(), getString(r16), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), getString(R.string.empty_text), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e2) {
            Log.e(TAG, "createSearchDialogCursorSales: ", e2);
        }
    }
}
