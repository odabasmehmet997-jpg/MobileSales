package com.proje.mobilesales.core.preferences;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.DialogPreference;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class LogoDatePreference extends DialogPreference implements DatePicker.OnDateChangedListener {
    public boolean ShowMessage;
    private String changedValueCanBeNull;
    private DatePicker datePicker;
    private String dateString;
    public DialogInterface.OnClickListener dialogClickListenerTeslimTarihi;
    private String tarihDegeri;
    public LogoDatePreference(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.ShowMessage = false;
        this.tarihDegeri = "";
        this.dialogClickListenerTeslimTarihi = new DialogInterface.OnClickListener() {
            void DialogInterfaceOnClickListenerC25431() {
            }
            public void onClick(DialogInterface dialogInterface, int i3) {
                if (i3 != -1) {
                    return;
                }
                ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().siparisDetayTeslimTarihleriniGuncelle(LogoDatePreference.this.tarihDegeri);
            }
        };
    }
    public LogoDatePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.ShowMessage = false;
        this.tarihDegeri = "";
        this.dialogClickListenerTeslimTarihi = new DialogInterface.OnClickListener() {
            void DialogInterfaceOnClickListenerC25431() {
            }
            public void onClick(DialogInterface dialogInterface, int i3) {
                if (i3 != -1) {
                    return;
                }
                ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().siparisDetayTeslimTarihleriniGuncelle(LogoDatePreference.this.tarihDegeri);
            }
        };
    }
    protected View onCreateDialogView() {
        this.datePicker = new DatePicker(getContext());
        Calendar date = getDate();
        this.datePicker.init(date.get(1), date.get(2), date.get(5), this);
        return this.datePicker;
    }
    public Calendar getDate() {
        try {
            String[] split = StringUtils.split(defaultValue(), '.');
            int convertStringToInt = StringUtils.convertStringToInt(split[0]);
            int convertStringToInt2 = StringUtils.convertStringToInt(split[1]);
            int convertStringToInt3 = StringUtils.convertStringToInt(split[2]);
            Calendar calendar = Calendar.getInstance();
            calendar.set(convertStringToInt3, convertStringToInt2 - 1, convertStringToInt);
            return calendar;
        } catch (Exception unused) {
            return defaultCalendar();
        }
    }
    public void setDate(String str) {
        this.dateString = str;
        persistDate(str);
    }
    public static SimpleDateFormat formatter() {
        return new SimpleDateFormat("dd.MM.yyyy");
    }
    public static SimpleDateFormat summaryFormatter() {
        return new SimpleDateFormat("MMMM dd, yyyy");
    }
    protected Object onGetDefaultValue(TypedArray typedArray, int i2) {
        return typedArray.getString(i2);
    }
    protected void onSetInitialValue(boolean z, Object obj) {
        if (z) {
            String persistedString = getPersistedString(defaultValue());
            this.dateString = persistedString;
            setTheDate(persistedString);
        } else {
            boolean z2 = this.dateString == null;
            setDate((String) obj);
            if (z2) {
                return;
            }
            persistDate(this.dateString);
        }
    }
    protected Parcelable onSaveInstanceState() {
        if (isPersistent()) {
            return super.onSaveInstanceState();
        }
        return new SavedState(super.onSaveInstanceState());
    }
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable != null) {
            try {
                if (parcelable.getClass().equals(SavedState.class)) {
                    SavedState savedState = (SavedState) parcelable;
                    super.onRestoreInstanceState(savedState.getSuperState());
                    setTheDate(savedState.dateValue);
                    return;
                }
            } catch (Exception unused) {
                return;
            }
        }
        super.onRestoreInstanceState(parcelable);
        setTheDate(((SavedState) parcelable).dateValue);
    }
    public void onDateChanged(DatePicker datePicker, int i2, int i3, int i4) {
        this.changedValueCanBeNull = formatter().format(new GregorianCalendar(i2, i3, i4).getTime());
    }
    protected void onDialogClosed(boolean z) {
        String str;
        if (!z || (str = this.changedValueCanBeNull) == null) {
            return;
        }
        setTheDate(str);
        if (this.ShowMessage) {
            new AlertDialog.Builder(ContextUtils.getmContext()).setMessage(ContextUtils.getStringResource(R.string.str_question_change_delivery_dates)).setPositiveButton(ContextUtils.getStringResource(R.string.str_yes), this.dialogClickListenerTeslimTarihi).setNegativeButton(ContextUtils.getStringResource(R.string.str_no), this.dialogClickListenerTeslimTarihi).show();
        }
        this.tarihDegeri = this.changedValueCanBeNull;
        this.changedValueCanBeNull = null;
    }
    class DialogInterfaceOnClickListenerC25431 implements DialogInterface.OnClickListener {
        DialogInterfaceOnClickListenerC25431() {
        }
        public void onClick(DialogInterface dialogInterface, int i3) {
            if (i3 != -1) {
                return;
            }
            ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().siparisDetayTeslimTarihleriniGuncelle(LogoDatePreference.this.tarihDegeri);
        }
    }
    public void setTheDate(String str) {
        setDate(str);
        persistDate(str);
    }
    private void persistDate(String str) {
        persistString(str);
        setSummary(summaryFormatter().format(getDate().getTime()));
    }
    public static Calendar defaultCalendar() {
        return new GregorianCalendar(1970, 0, 1);
    }
    public static String defaultCalendarString() {
        return formatter().format(defaultCalendar().getTime());
    }
    private String defaultValue() {
        if (this.dateString == null) {
            setDate(defaultCalendarString());
        }
        return this.dateString;
    }
    public void onClick(DialogInterface dialogInterface, int i2) {
        super.onClick(dialogInterface, i2);
        this.datePicker.clearFocus();
        DatePicker datePicker = this.datePicker;
        onDateChanged(datePicker, datePicker.getYear(), this.datePicker.getMonth(), this.datePicker.getDayOfMonth());
        onDialogClosed(i2 == -1);
    }
    public static Calendar getDateFor(SharedPreferences sharedPreferences, String str) {
        Date stringToDate = stringToDate(sharedPreferences.getString(str, defaultCalendarString()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(stringToDate);
        return calendar;
    }
    private static Date stringToDate(String str) {
        try {
            return formatter().parse(str);
        } catch (ParseException unused) {
            return defaultCalendar().getTime();
        }
    }
    private static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            void C25441() {
            }
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };
        String dateValue;
        public SavedState(Parcel parcel) {
            super(parcel);
            this.dateValue = parcel.readString();
        }
        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
        @Override
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeString(this.dateValue);
        }
        @Override
        public int describeContents() {
            return 0;
        }
        class C25441 implements Parcelable.Creator<SavedState> {
            C25441() {
            }
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }
    }
}
