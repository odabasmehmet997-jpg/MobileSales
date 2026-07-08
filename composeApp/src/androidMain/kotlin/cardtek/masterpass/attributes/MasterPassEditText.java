package cardtek.masterpass.attributes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import cardtek.masterpass.util.MasterPassInfo;
import com.proje.mobilesales.R;

import java.util.LinkedList;

public final class MasterPassEditText extends androidx.appcompat.widget.AppCompatEditText implements TextWatcher, View.OnTouchListener {
    private static final int GROUPSIZE = 4;
    private static final int GROUPSIZEAMEX = 6;
    private static final char SPACE_CHAR = ' ';
    private static final String constClassName = "cardtek.masterpass.management.MasterPassTextHelper";
    private Drawable cardIOCameraIcon;
    private CardNumberTextListener cardNumberTextListener;
    private Drawable clearTextIcon;
    private View.OnTouchListener l;
    private IconClickListener listener;
    private MasterPassEditTextListener masterPassEditTextListener;
    private b type;
    private Drawable xD;
    private static final String[] constClassName_1 = {"cardtek.masterpass.attributes.MasterPassEditText", "android.widget.EditText", "com.google.android.material.textfield.TextInputLayout", "android.view.autofill.AutofillManager"};
    private static final String[] constClassName_1_rawText = {"cardtek.masterpass.management.EncryptionHelper", "cardtek.masterpass.management.MasterPassTextHelper"};
    private static final String[] constClassName_1_onTextChanged = {"android.widget.TextView"};
    private static final String[] constClassName_2 = {"android.widget.EditText", "cardtek.masterpass.attributes.MasterPassEditText", "android.widget.TextView"};
    private static final String[] constClassName_3 = {"android.widget.EditText", "android.widget.TextView", "android.view.View"};
    private static final String[] constClassName_4 = {"android.text.SpannableStringBuilder", "cardtek.masterpass.attributes.MasterPassEditText", "android.widget.EditText", "android.view.autofill.AutofillManager"};
    private static final String[] constClassName_6 = {"java.lang.reflect.Constructor", "android.widget.EditText", "android.view.View", "android.view.ViewGroup", "com.google.android.material.textfield.TextInputLayout", "cardtek.masterpass.attributes.MasterPassEditText", "android.text.SpannableStringBuilder", "androidx.fragment.app.Fragment", "android.view.autofill.AutofillManager"};
    private static final String SPACE_STRING = " ";
    private final String regexp = "^(\\d{4}\\s)*\\d{0,4}(?<!\\s)";
    private final String regexpA = "^(\\d{6}\\s)*\\d{0,6}(?<!\\s)";
    private String rawText = "";
    private boolean isUpdating = false;
    private boolean isMaxLenghtExceeded = false;
    private boolean isMaxAMEXLenghtExceeded = false;
    private String lastCharCallback = "a";
    private String last6Digits = "";
    private boolean first6DigitOk = false;
    private boolean instCountCancel = false;
    private boolean fixCursorLocation = false;
    private final a loc = a.RIGHT;
    public MasterPassEditText(Context context) {
        super(context);
        init();
    }
    public MasterPassEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MasterPassEditText);
        this.type = b.values()[obtainStyledAttributes.getInt(R.styleable.MasterPassEditText_type, 0)];
        this.clearTextIcon = obtainStyledAttributes.getDrawable(R.styleable.MasterPassEditText_clearTextIcon);
        this.cardIOCameraIcon = obtainStyledAttributes.getDrawable(R.styleable.MasterPassEditText_cardIOCameraIcon);
        obtainStyledAttributes.recycle();
        init();
    }
    public MasterPassEditText(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init();
    }
    public MasterPassEditText(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context);
        init();
    }
    private boolean checkClassName(String str, String[] strArr) {
        for (String str2 : strArr) {
            if (str.equalsIgnoreCase(str2)) {
                return true;
            }
        }
        return false;
    }
    private Drawable getDisplayedDrawable() {
        if (this.loc != null) {
            return getCompoundDrawables()[this.loc.f7c];
        }
        return null;
    }
    private void init() {
        super.addTextChangedListener(this);
        if (this.type == b.CARDNUMBER_ICONABLE) {
            initDraw();
        }
    }
    private void initDraw() {
        super.setOnTouchListener(this);
        setCameraIconVisible(true);
    }
    private void initIcon(boolean z) {
        this.xD = null;
        if (this.loc != null) {
            this.xD = getCompoundDrawables()[this.loc.f7c];
        }
        if (this.xD == null) {
            this.xD = z ? this.clearTextIcon : this.cardIOCameraIcon;
        }
        Drawable drawable = this.xD;
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), this.xD.getIntrinsicHeight());
        int paddingTop = getPaddingTop() + this.xD.getIntrinsicHeight() + getPaddingBottom();
        if (getSuggestedMinimumHeight() < paddingTop) {
            setMinimumHeight(paddingTop);
        }
    }
    private void returnCardNumberDigits(Editable editable) {
        CardNumberTextListener cardNumberTextListener;
        if (this.rawText.length() == 0 && this.cardNumberTextListener != null && !"".equals(this.lastCharCallback)) {
            this.lastCharCallback = "";
            this.cardNumberTextListener.getFirstChar(SPACE_CHAR);
            this.last6Digits = "";
            this.first6DigitOk = false;
        }
        if (this.rawText.length() > 0 && !this.lastCharCallback.equals(this.rawText.substring(0, 1)) && this.cardNumberTextListener != null) {
            String str = this.lastCharCallback;
            StringBuilder sb = new StringBuilder();
            sb.append(editable.charAt(0));
            if (str != sb.toString()) {
                this.lastCharCallback = String.valueOf(editable.charAt(0));
                this.cardNumberTextListener.getFirstChar(editable.charAt(0));
            }
        }
        if (this.rawText.length() == 6) {
            if (this.cardNumberTextListener != null) {
                this.lastCharCallback = this.rawText.substring(0, 1);
                if (this.first6DigitOk || !this.last6Digits.equalsIgnoreCase(this.rawText.substring(0, 6))) {
                    this.cardNumberTextListener.getFirst6Chars(this.rawText);
                }
                this.last6Digits = this.rawText;
            }
            this.instCountCancel = true;
        }
        if (this.rawText.length() == 5) {
            this.first6DigitOk = true;
            if (this.instCountCancel && (cardNumberTextListener = this.cardNumberTextListener) != null) {
                cardNumberTextListener.cancelInstallment();
            }
        }
        if (this.rawText.length() == 7) {
            this.first6DigitOk = false;
        }
        if (this.rawText.length() == 4) {
            this.instCountCancel = false;
        }
        if (this.rawText.length() > 6 && !this.last6Digits.equalsIgnoreCase(this.rawText.substring(0, 6))) {
            this.last6Digits = this.rawText.substring(0, 6);
            CardNumberTextListener cardNumberTextListener2 = this.cardNumberTextListener;
            if (cardNumberTextListener2 != null) {
                cardNumberTextListener2.getFirst6Chars(this.rawText.substring(0, 6));
            }
        }
    }
    public void addTextChangedListener(TextWatcher textWatcher) {
    }
    public void afterTextChanged(Editable editable) {
        b bVar = this.type;
        if (bVar == b.CARDNUMBER || bVar == b.CARDNUMBER_ICONABLE) {
            String obj = editable.toString();
            Boolean bool = Boolean.FALSE;
            if (this.rawText.length() > 2) {
                bool = Boolean.valueOf(this.rawText.charAt(0) == '3' && (this.rawText.charAt(1) == '4' || this.rawText.charAt(1) == '7'));
            }
            if (this.isMaxAMEXLenghtExceeded && bool.booleanValue()) {
                String replaceAll = editable.toString().replaceAll(" ", "");
                if (replaceAll.length() > 15) {
                    this.isMaxLenghtExceeded = false;
                    this.fixCursorLocation = true;
                    if (checkClassName(new Throwable().getStackTrace()[1].getClassName(), constClassName_1_onTextChanged)) {
                        setText(replaceAll.substring(0, 15));
                        return;
                    }
                    Log.e(MasterPassInfo.TAG, "[MasterPassEditText] onTextChanged -> " + new Throwable().getStackTrace()[1].getClassName());
                    return;
                }
            }
            if (this.isMaxLenghtExceeded) {
                String replaceAll2 = editable.toString().replaceAll(" ", "");
                if (replaceAll2.length() > 16) {
                    this.isMaxLenghtExceeded = false;
                    this.fixCursorLocation = true;
                    if (checkClassName(new Throwable().getStackTrace()[1].getClassName(), constClassName_1_onTextChanged)) {
                        setText(replaceAll2.substring(0, 16));
                        return;
                    }
                    Log.e(MasterPassInfo.TAG, "[MasterPassEditText] onTextChanged -> " + new Throwable().getStackTrace()[1].getClassName());
                    return;
                }
            }
            if (this.isUpdating || obj.matches("^(\\d{4}\\s)*\\d{0,4}(?<!\\s)")) {
                this.rawText = editable.toString().replaceAll(" ", "");
                returnCardNumberDigits(editable);
                MasterPassEditTextListener masterPassEditTextListener = this.masterPassEditTextListener;
                if (masterPassEditTextListener != null) {
                    masterPassEditTextListener.onEditTextChanged();
                }
                if (this.type != b.CARDNUMBER_ICONABLE) {
                    return;
                }
                if (!this.rawText.isEmpty()) {
                    setCameraIconVisible(false);
                    setClearIconVisible(true);
                    return;
                }
                setClearIconVisible(false);
                setCameraIconVisible(true);
                return;
            }
            this.isUpdating = true;
            LinkedList linkedList = new LinkedList();
            for (int indexOf = obj.indexOf(32); indexOf >= 0; indexOf = obj.indexOf(32, indexOf + 1)) {
                linkedList.offerLast(Integer.valueOf(indexOf));
            }
            while (!linkedList.isEmpty()) {
                Integer num = (Integer) linkedList.removeLast();
                editable.delete(num.intValue(), num.intValue() + 1);
            }
            if (this.rawText.charAt(0) != '3' || (this.rawText.charAt(1) != '4' && this.rawText.charAt(1) != '7')) {
                int i2 = 0;
                while (true) {
                    int i3 = i2 + 1;
                    int i4 = (i3 * 4) + i2;
                    if (i4 >= editable.length()) {
                        break;
                    }
                    editable.insert(i4, SPACE_STRING);
                    i2 = i3;
                }
            } else if (4 < editable.length()) {
                String str = SPACE_STRING;
                editable.insert(4, str);
                if (11 < editable.length()) {
                    editable.insert(11, str);
                }
            }
            int selectionStart = getSelectionStart();
            if (selectionStart > 0) {
                int i5 = selectionStart - 1;
                if (editable.charAt(i5) == ' ') {
                    setSelection(i5);
                }
            }
            this.isUpdating = false;
            if (this.fixCursorLocation) {
                this.fixCursorLocation = false;
                setSelection(editable.toString().length());
                requestFocus();
                return;
            }
            return;
        }
        b bVar2 = b.RTA;
        String obj2 = editable.toString();
        this.rawText = obj2;
        if (bVar != bVar2) {
            MasterPassEditTextListener masterPassEditTextListener2 = this.masterPassEditTextListener;
            if (masterPassEditTextListener2 != null) {
                masterPassEditTextListener2.onEditTextChanged();
            }
        } else if (obj2.equalsIgnoreCase(".")) {
            setText("");
        } else if (this.rawText.length() == 3 && this.rawText.substring(2, 3).contains(".")) {
            String substring = this.rawText.substring(0, 2);
            this.rawText = substring;
            setText(substring);
            setSelection(this.rawText.length());
            requestFocus();
        } else if (this.rawText.length() != 4 || (this.rawText.contains(".") && !this.rawText.substring(3, 4).contains("."))) {
            MasterPassEditTextListener masterPassEditTextListener3 = this.masterPassEditTextListener;
            if (masterPassEditTextListener3 != null) {
                masterPassEditTextListener3.onEditTextChanged();
            }
        } else {
            String substring2 = this.rawText.substring(0, 3);
            this.rawText = substring2;
            setText(substring2);
            setSelection(this.rawText.length());
            requestFocus();
        }
    }
    public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        b bVar = this.type;
        if (bVar == b.CARDNUMBER || bVar == b.CARDNUMBER_ICONABLE) {
            int i5 = i2 + i4;
            if (i5 > 16) {
                this.isMaxLenghtExceeded = true;
            }
            if (i5 > 15) {
                this.isMaxAMEXLenghtExceeded = true;
            }
        }
    }
    public void clear() {
        getText().clear();
    }
    public int getLength() {
        return getText().length();
    }
    public String getRawText() {
        if (checkClassName(new Throwable().getStackTrace()[1].getClassName(), constClassName_1_rawText)) {
            String str = this.rawText;
            return str == null ? "" : str;
        }
        String str2 = MasterPassInfo.TAG;
        Log.e(str2, "[MasterPassCardEditText] Get Raw Text -> " + new Throwable().getStackTrace()[1].getClassName());
        return "";
    }
    public Editable getText() {
        if (checkClassName(new Throwable().getStackTrace()[1].getClassName(), constClassName_1)) {
            return super.getText();
        }
        String str = MasterPassInfo.TAG;
        Log.e(str, "[MasterPassEditText] Get Text -> " + new Throwable().getStackTrace()[1].getClassName());
        return new SpannableStringBuilder("");
    }
    public boolean isEmpty() {
        return this.rawText.length() == 0;
    }
    public boolean onKeyPreIme(int i2, KeyEvent keyEvent) {
        if (i2 != 4 || keyEvent.getAction() != 1) {
            return super.dispatchKeyEvent(keyEvent);
        }
        clearFocus();
        return false;
    }
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        if (!checkClassName(new Throwable().getStackTrace()[4].getClassName(), constClassName_4)) {
            throw new RuntimeException("Restricted Method! - MasterPassCardEditText onTextChanged is called [" + new Throwable().getStackTrace()[4].getClassName() + "]");
        }
    }
    public boolean onTouch(View view, MotionEvent motionEvent) {
        IconClickListener iconClickListener;
        if (getDisplayedDrawable() != null) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            a aVar = this.loc;
            a aVar2 = a.LEFT;
            int width = aVar == aVar2 ? 0 : (getWidth() - getPaddingRight()) - this.xD.getIntrinsicWidth();
            int paddingLeft = this.loc == aVar2 ? getPaddingLeft() + this.xD.getIntrinsicWidth() : getWidth();
            if (x >= width && x <= paddingLeft && y >= 0 && y <= getBottom() - getTop()) {
                if (motionEvent.getAction() == 1) {
                    if (getDisplayedDrawable().equals(this.clearTextIcon)) {
                        super.requestFocus();
                        clear();
                        if (this.cardNumberTextListener != null && !"".equals(this.lastCharCallback)) {
                            this.lastCharCallback = "";
                            this.cardNumberTextListener.getFirstChar(SPACE_CHAR);
                        }
                    } else if (getDisplayedDrawable().equals(this.cardIOCameraIcon) && (iconClickListener = this.listener) != null) {
                        iconClickListener.cameraOpen();
                    }
                }
                return true;
            }
        }
        View.OnTouchListener onTouchListener = this.l;
        if (onTouchListener != null) {
            return onTouchListener.onTouch(view, motionEvent);
        }
        return false;
    }
    private void setCameraIconVisible(boolean z) {
        initIcon(false);
        Drawable[] compoundDrawables = getCompoundDrawables();
        if (z == (getDisplayedDrawable() == null)) {
            Drawable drawable = z ? this.xD : null;
            a aVar = this.loc;
            Drawable drawable2 = aVar == a.LEFT ? drawable : compoundDrawables[0];
            Drawable drawable3 = compoundDrawables[1];
            if (aVar != a.RIGHT) {
                drawable = compoundDrawables[2];
            }
            super.setCompoundDrawables(drawable2, drawable3, drawable, compoundDrawables[3]);
        }
    }
    public void setCardIOCameraIcon(Drawable drawable) {
        this.cardIOCameraIcon = drawable;
    }
    public void setCardTypeCallback(CardNumberTextListener cardNumberTextListener) {
        this.cardNumberTextListener = cardNumberTextListener;
    }
    private void setClearIconVisible(boolean z) {
        initIcon(true);
        Drawable[] compoundDrawables = getCompoundDrawables();
        if (z == (getDisplayedDrawable() == null)) {
            Drawable drawable = z ? this.xD : null;
            a aVar = this.loc;
            Drawable drawable2 = aVar == a.LEFT ? drawable : compoundDrawables[0];
            Drawable drawable3 = compoundDrawables[1];
            if (aVar != a.RIGHT) {
                drawable = compoundDrawables[2];
            }
            super.setCompoundDrawables(drawable2, drawable3, drawable, compoundDrawables[3]);
        }
    }
    private void setClearTextIcon(Drawable drawable) {
        this.clearTextIcon = drawable;
    }
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
    }
    public void setIconClickListener(IconClickListener iconClickListener) {
        this.listener = iconClickListener;
    }
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.l = onTouchListener;
    }
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        if (new Throwable().getStackTrace()[2].getClassName().equalsIgnoreCase(constClassName)) {
            b bVar = this.type;
            if (bVar == b.CARDNUMBER || bVar == b.CARDNUMBER_ICONABLE) {
                this.fixCursorLocation = true;
            }
        } else if (!checkClassName(new Throwable().getStackTrace()[2].getClassName(), constClassName_2) || !checkClassName(new Throwable().getStackTrace()[3].getClassName(), constClassName_3) || !checkClassName(new Throwable().getStackTrace()[6].getClassName(), constClassName_6)) {
            throw new RuntimeException("Restricted Method! - MasterPassCardEditText Set Text [" + new Throwable().getStackTrace()[2].getClassName() + " - " + new Throwable().getStackTrace()[3].getClassName() + " -  " + new Throwable().getStackTrace()[6].getClassName() + "]");
        }
        this.rawText = charSequence.toString();
        super.setText(charSequence, bufferType);
    }
    public void setTextChangeListener(MasterPassEditTextListener masterPassEditTextListener) {
        this.masterPassEditTextListener = masterPassEditTextListener;
    }
    public void setType(b bVar) {
        this.type = bVar;
    }
    public boolean validate() {
        b bVar = this.type;
        if (bVar == b.CARDNUMBER || bVar == b.CARDNUMBER_ICONABLE) {
            char[] charArray = this.rawText.toCharArray();
            int length = charArray.length;
            int[] iArr = new int[length];
            for (int i2 = 0; i2 < charArray.length; i2++) {
                iArr[i2] = Character.getNumericValue(charArray[i2]);
            }
            for (int i3 = length - 2; i3 >= 0; i3 -= 2) {
                int i4 = iArr[i3] * 2;
                iArr[i3] = i4;
                if (i4 > 9) {
                    iArr[i3] = i4 - 9;
                }
            }
            int i5 = 0;
            for (int i6 = 0; i6 < length; i6++) {
                i5 += iArr[i6];
            }
            return i5 % 10 == 0;
        } else if (bVar == b.CVV) {
            return this.rawText.length() == 3 || this.rawText.length() == 4;
        } else {
            if (bVar == b.OTP) {
                return this.rawText.length() == 6;
            }
            if (bVar == b.MPIN) {
                return this.rawText.length() == 4;
            }
            if (bVar != b.RTA) {
                return false;
            }
            this.rawText = this.rawText.replace(".", "");
            return true;
        }
    }
}
