package com.proje.mobilesales.features.activity;

import android.Manifest;
import android.R;
import android.bluetooth.BluetoothAdapter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.documentfile.provider.DocumentFile;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.enums.*;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.printutil.TPLSUtils;
import com.proje.mobilesales.core.printutil.ZPLConverter;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.core.utils.*;
import com.proje.mobilesales.features.model.PrintSettings;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import static com.proje.mobilesales.core.utils.AppUtils.getTextFileName;
import static com.proje.mobilesales.core.utils.AppUtils.printToFolder;

public class PrintPreviewActivity extends BaseErpActivity implements BluetoothUtil.BluetoothStateChange {
    private static final String STATE_PRINT_CLREF = "state:PrintClRef";
    private static final String STATE_PRINT_SETTING = "state:PrintSettings";
    private static final String TAG = "PrintPreviewActivity";
    private static boolean sPrintAll;
    AppCompatButton btnCancel;
    AppCompatButton btnYes;
    private int clRef;
    AppCompatEditText etMatbuEvrakNo;
    private PrintSettings mPrintSettings;
    SharedPreferencesHelper mSharedPreferencesHelper;
    AppCompatSeekBar seekBar;
    AppCompatTextView tvPreview;
    public static final String EXTRA_PRINT_SETTINGS = PrintPreviewActivity.class.getName() + ".EXTRA_PRINT_SETTINGS";
    public static final String EXTRA_PRINT_CLREF = PrintPreviewActivity.class.getName() + ".EXTRA_PRINT_CLREF";
    public static String[] sPrintValue;
    private final int RESIZE_WIDTH = 800;
    private final int RESIZE_HEIGHT = 600;
    int mPageNumber;
    int mMinTextSize = 6;
    int mMaxTextSize = 40;
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
         this.setContentView(com.proje.mobilesales.R.layout.printpreview);
        mSharedPreferencesHelper = new SharedPreferencesHelper();
         this.setToolbar();
        if (null != bundle) {
            mPrintSettings = bundle.getParcelable(PrintPreviewActivity.STATE_PRINT_SETTING);
            clRef = bundle.getInt(PrintPreviewActivity.STATE_PRINT_CLREF);
        } else {
            mPrintSettings = this.getIntent().getParcelableExtra(PrintPreviewActivity.EXTRA_PRINT_SETTINGS);
            clRef = this.getIntent().getIntExtra(PrintPreviewActivity.EXTRA_PRINT_CLREF, 0);
        }
         this.setUpPrinterDevice();
        int showPrintPreviewFontSize = Preferences.getShowPrintPreviewFontSize(this);
        if (0 >= showPrintPreviewFontSize) {
            showPrintPreviewFontSize = 6;
        }
        final AppCompatSeekBar appCompatSeekBar = this.findViewById(com.proje.mobilesales.R.id.seek_change_textsize);
        seekBar = appCompatSeekBar;
        appCompatSeekBar.setMax(mMaxTextSize - mMinTextSize);
        seekBar.setProgress(showPrintPreviewFontSize);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(final SeekBar seekBar) {
            }
            public void onStopTrackingTouch(final SeekBar seekBar) {
            }
            public void onProgressChanged(final SeekBar seekBar, int i2, final boolean z) {
                try {
                    final PrintPreviewActivity printPreviewActivity = PrintPreviewActivity.this;
                    final int i3 = printPreviewActivity.mMinTextSize;
                    if (i2 < i3) {
                        i2 = i3;
                    }
                    printPreviewActivity.tvPreview.setTextSize(0, i2);
                } catch (final Exception e2) {
                    Log.d(TAG, "onProgressChanged: ", e2);
                }
            }
        });
        final AppCompatTextView appCompatTextView = this.findViewById(com.proje.mobilesales.R.id.tvPreview);
        tvPreview = appCompatTextView;
        appCompatTextView.setTextSize(0, showPrintPreviewFontSize);
        if (mPrintSettings.isDontShowPreview()) {
            this.setVisible(false);
            this.initPrint(true);
        } else {
            this.openPage();
        }
    }
    private void setUpPrinterDevice() {
        BluetoothUtil.setPrinterAddress(true, Preferences.getPrinterOneAddress(this));
        BluetoothUtil.setPrinterAddress(false, Preferences.getPrinterTwoAddress(this));
        BluetoothUtil.setPrinterName(true, Preferences.getPrinterOneName(this));
        BluetoothUtil.setPrinterName(false, Preferences.getPrinterTwoName(this));
        BluetoothUtil.createBluetoothInstance(true);
        BluetoothUtil.createBluetoothInstance(false);
        BluetoothUtil.setmBluetoothStateChange(this);
    }
    private void openPage() {
        final String str;
        tvPreview.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/DroidSansMono.ttf"));
        try {
            tvPreview.setText(this.unescape(PrintPreviewActivity.sPrintValue[mPageNumber]));
        } catch (final NullPointerException unused) {
            Toast.makeText(this, com.proje.mobilesales.R.string.str_get_print_value_not_found, Toast.LENGTH_LONG).show();
            this.finish();
        }
        final FicheType ficheType = mPrintSettings.getFicheType();
        if (FicheTypeControlUtils.isFicheTypeOrder(ficheType)) {
            str = this.getString(com.proje.mobilesales.R.string.str_order_print_preview);
        } else if (FicheTypeControlUtils.isFicheTypeDispatch(ficheType)) {
            str = this.getString(com.proje.mobilesales.R.string.str_dispatch_print_preview);
        } else if (FicheTypeControlUtils.isFicheTypeOnlyInvoice(ficheType)) {
            str = this.getString(com.proje.mobilesales.R.string.str_invoice_print_preview);
        } else if (FicheTypeControlUtils.isFicheTypeCashReceipt(ficheType)) {
            str = this.getString(com.proje.mobilesales.R.string.str_cash_print_preview);
        } else if (FicheTypeControlUtils.isFicheTypeCaseReceipt(ficheType)) {
            str = this.getString(com.proje.mobilesales.R.string.str_case_print_preview);
        } else if (FicheTypeControlUtils.isFicheTypeCreditReceipt(ficheType)) {
            str = this.getString(com.proje.mobilesales.R.string.str_credit_cart_print_preview);
        } else if (FicheTypeControlUtils.isFicheTypeCheckReceipt(ficheType)) {
            str = this.getString(com.proje.mobilesales.R.string.str_check_print_preview);
        } else if (FicheTypeControlUtils.isFicheTypeDeedReceipt(ficheType)) {
            str = this.getString(com.proje.mobilesales.R.string.str_payroll_note_print_preview);
        } else if (FicheTypeControlUtils.isFicheTypeDeliveryNote(ficheType)) {
            str = this.getString(com.proje.mobilesales.R.string.str_delivery_note_preview);
        } else if (!FicheTypeControlUtils.isFicheTypeOnlyRetailInvoice(ficheType)) {
            str = "";
        } else {
            str = this.getString(com.proje.mobilesales.R.string.str_invoice_print_preview);
        }
        final String[] strArr = PrintPreviewActivity.sPrintValue;
        int i2 = 0;
        if (null != strArr) {
            final int length = strArr.length;
            int i3 = 0;
            while (i2 < length) {
                if (null != strArr[i2]) {
                    i3++;
                }
                i2++;
            }
            i2 = i3;
        }
        final String str2 = this.getString(com.proje.mobilesales.R.string.str_page) + " " + (mPageNumber + 1) + "\\" + i2;
        this.setTitle(str);
        this.getSupportActionBar().setSubtitle(str2);
    }
    private String unescape(final String str) {
        return TextUtils.isEmpty(str) ? "" : str.replaceAll("\\\\n", "\\\n");
    }
    public void onDestroy() {
        super.onDestroy();
    }
    public boolean onPrepareOptionsMenu(final Menu menu) {
        final boolean isEInvoiceOrEArchiveCustomer = baseErp.getLogoSqlHelper().isEInvoiceOrEArchiveCustomer(clRef);
        final boolean isEInvoiceCustomerPrintInvoice = baseErp.isEInvoiceCustomerPrintInvoice();
        if (isEInvoiceOrEArchiveCustomer && !isEInvoiceCustomerPrintInvoice && FicheTypeControlUtils.isFicheTypeInvoiceOrRetailInvoice(mPrintSettings.getFicheType())) {
            menu.findItem(com.proje.mobilesales.R.id.menu_print).setVisible(false);
            menu.findItem(com.proje.mobilesales.R.id.menu_print_all).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }
    public boolean onCreateOptionsMenu(final Menu menu) {
        this.getMenuInflater().inflate(com.proje.mobilesales.R.menu.menu_print_preview, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case com.proje.mobilesales.R.id.home:
                this.finish();
                return true;
            case com.proje.mobilesales.R.id.menu_connect_device /* 2131297304 */:
                this.connectDevice();
                break;
            case com.proje.mobilesales.R.id.menu_next_page /* 2131297361 */:
                this.openNextPage();
                break;
            case com.proje.mobilesales.R.id.menu_prev_page /* 2131297370 */:
                this.openPrevPage();
                break;
            case com.proje.mobilesales.R.id.menu_print /* 2131297371 */:
                this.initPrint(false);
                break;
            case com.proje.mobilesales.R.id.menu_print_all /* 2131297372 */:
                this.initPrint(true);
                break;
            case com.proje.mobilesales.R.id.menu_zoom_in /* 2131297412 */:
                this.zoomIn();
                break;
            case com.proje.mobilesales.R.id.menu_zoom_out /* 2131297413 */:
                this.zoomOut();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }
    private void openPrevPage() {
        try {
            mPageNumber--;
            this.openPage();
        } catch (final Exception unused) {
            mPageNumber++;
        }
    }
    private void openNextPage() {
        try {
            mPageNumber++;
            this.openPage();
        } catch (final Exception unused) {
            mPageNumber--;
        }
    }
    private void connectDevice() {
        if (BluetoothUtil.hasDeviceBluetooth()) {
            this.setUpPrinterDevice();
            final boolean isPrinterFirst = mPrintSettings.isPrinterFirst();
            if (null == BluetoothUtil.getBluetoothAdapter(isPrinterFirst)) {
                BluetoothUtil.setBluetoothAdapter(isPrinterFirst, BluetoothAdapter.getDefaultAdapter());
            }
            if (!BluetoothUtil.getBluetoothAdapter(isPrinterFirst).isEnabled()) {
                if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                BluetoothUtil.getBluetoothAdapter(isPrinterFirst).enable();
            }
            if (BluetoothUtil.getBluetoothAdapter(isPrinterFirst).isEnabled()) {
                BluetoothUtil.connectPrinter(true, isPrinterFirst);
            }
        }
    }
    private void zoomIn() {
        final int round = Math.round(tvPreview.getTextSize()) + 1;
        if (round < mMaxTextSize) {
            tvPreview.setTextSize(0, round);
            Preferences.setShowPrintPreviewFontSize(this, round);
            seekBar.setProgress(round);
        }
    }
    private void zoomOut() {
        final int round = Math.round(tvPreview.getTextSize()) - 1;
        if (round > mMinTextSize) {
            tvPreview.setTextSize(0, round);
            Preferences.setShowPrintPreviewFontSize(this, round);
            seekBar.setProgress(round);
        }
    }
    private void initPrint(final boolean z) {
        if (BluetoothUtil.hasDeviceBluetooth()) {
            PrintPreviewActivity.sPrintAll = z;
            if (this.isExport()) {
                this.printPage(PrintPreviewActivity.sPrintAll);
                return;
            }
            this.setUpPrinterDevice();
            final boolean isPrinterFirst = mPrintSettings.isPrinterFirst();
            if (null == BluetoothUtil.getBluetoothAdapter(isPrinterFirst)) {
                BluetoothUtil.setBluetoothAdapter(isPrinterFirst, BluetoothAdapter.getDefaultAdapter());
            }
            if (BluetoothUtil.getBluetoothAdapter(isPrinterFirst).isEnabled()) {
                BluetoothUtil.connectPrinter(false, isPrinterFirst);
            }
        }
    }
    private void printPage(final boolean z) {
        if (z) {
            int i2 = 0;
            while (true) {
                final String[] strArr = PrintPreviewActivity.sPrintValue;
                if (i2 > strArr.length - 1) {
                    break;
                }
                if (null != strArr[i2]) {
                    mPageNumber = i2;
                    this.openPage();
                    this.print();
                }
                i2++;
            }
        } else {
            this.print();
        }
        if (mPrintSettings.getPrintDestination() == PrintDestination.FILE) {
            Toast.makeText(this, com.proje.mobilesales.R.string.str_print_export, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, com.proje.mobilesales.R.string.str_print_completed, Toast.LENGTH_LONG).show();
        }
        this.updatePrintCount(MainActivity.sFicheRef, FTypeControlUtils.getMainFType());
        if (mPrintSettings.isDontShowPreview()) {
            this.finish();
        }
    }
    private boolean isExport() {
        return mPrintSettings.getPrintDestination() == PrintDestination.FILE;
    }
    private void print() {
        if (mPrintSettings.getPrintLanguage() == PrintLanguage.STANDARD) {
            if (mPrintSettings.getPrintDestination() == PrintDestination.PRINTER) {
                if (mPrintSettings.getPrintType() == PrintType.TEXT) {
                    this.Send2Printer();
                    return;
                } else {
                    Toast.makeText(this, "Not Support", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            this.export();
            return;
        }
        if (mPrintSettings.getPrintDestination() == PrintDestination.PRINTER) {
            if (mPrintSettings.getPrintType() == PrintType.IMAGE) {
                this.sendPrintImage();
                return;
            }
            if (mPrintSettings.getPrintType() == PrintType.TEXT) {
                if (mPrintSettings.getPrintLanguage() != PrintLanguage.TSPL) {
                    this.sendPrinterPPLZ();
                    return;
                } else {
                    this.sendPrinterTSPL();
                    return;
                }
            }
            Toast.makeText(this, "Not Support", Toast.LENGTH_LONG).show();
            return;
        }
        this.export();
    }
    private void export() {
        if (mPrintSettings.getPrintType() == PrintType.IMAGE) {
            this.exportImage();
        } else if (mPrintSettings.getPrintType() == PrintType.TEXT) {
            this.exportText();
        }
    }
    private Uri exportImage() {
        return BitmapUtils.printToImage(BitmapUtils.scaleDown(BitmapUtils.getBitmapFromView(tvPreview), 800, 600));
    }
    private void exportText() {
        printToFolder(getTextFileName(), tvPreview.getText().toString());
    }
    private void sendPrintImage() {
        Bitmap decodeStream = null;
        String str;
        final Uri exportImage = this.exportImage();
        if (null != exportImage) {
            try {
                decodeStream = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(exportImage));
            } catch (final Exception e2) {
                Log.e("sendPrintImage", "decodeStream", e2);
            }
            final ZPLConverter zPLConverter = new ZPLConverter();
            zPLConverter.setCompressHex(true);
            zPLConverter.setBlacknessLimitPercentage(95);
            str = "";
            if (null != decodeStream) {
                try {
                    str = zPLConverter.convertfromImg(decodeStream);
                } catch (final IOException e3) {
                    e3.printStackTrace();
                }
            }
            if (TextUtils.isEmpty(str)) {
                final String format = String.format("%s%s%s", this.getString(com.proje.mobilesales.R.string.pplz_start_command), this.getString(com.proje.mobilesales.R.string.pplz_open_command), this.getString(com.proje.mobilesales.R.string.pplz_end_command));
                Log.d(PrintPreviewActivity.TAG, "sendPrinterPPLZ: startValues=" + str);
                BluetoothUtil.getBluetoothService(mPrintSettings.isPrinterFirst()).write(format.getBytes(StandardCharsets.UTF_8));
                Log.d(PrintPreviewActivity.TAG, "sendPrinterPPLZ: sendValues=" + str);
                BluetoothUtil.getBluetoothService(mPrintSettings.isPrinterFirst()).write(str.getBytes(StandardCharsets.UTF_8));
            } else {
                Log.d(PrintPreviewActivity.TAG, "sendPrinterPPLZ: no printable values");
                Toast.makeText(this, this.getString(com.proje.mobilesales.R.string.str_get_print_value_not_found), Toast.LENGTH_LONG).show();
            }
            try {
                Thread.sleep(mPrintSettings.getPreviewTime());
            } catch (final InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (null != decodeStream) {
                this.getContentResolver().delete(exportImage, null, null);
            }
            if (null == exportImage && DocumentFile.fromSingleUri(this, exportImage).exists()) {
                DocumentFile.fromSingleUri(this, exportImage).delete();
                return;
            }
        }
        decodeStream = null;
        final ZPLConverter zPLConverter2 = new ZPLConverter();
        zPLConverter2.setCompressHex(true);
        zPLConverter2.setBlacknessLimitPercentage(95);
        str = "";
        if (null != decodeStream) {
        }
        if (TextUtils.isEmpty(str)) {
        }
        try {
            Thread.sleep(mPrintSettings.getPreviewTime());
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (null != decodeStream) {
        }
        if (null == exportImage) {
        }
    }
    private void sendPrinterPPLZ() {
        String str = "";
        String str2 = "";
        try {
            final boolean isPrinterFirst = mPrintSettings.isPrinterFirst();
            this.openPage();
            Exception e;
            try {
                final String[] split = StringUtils.split(this.unescape(PrintPreviewActivity.sPrintValue[mPageNumber]), '\n');
                final int fontMargin = mPrintSettings.getFontMargin();
                str = "";
                int i2 = 0;
                for (int i3 = 0; i3 < split.length; i3++) {
                    try {
                        String replaceAll = split[i3].replaceAll("\r", "");
                        if (mPrintSettings.isRemoveBlankLines()) {
                            if (!"".equals(replaceAll)) {
                                final int fontSpace = (mPrintSettings.getFontSpace() * i2) % 999;
                                if (0 != this.mPrintSettings.getUseTurkishCharacter()) {
                                    replaceAll = this.getConvertPrintTurkishCharacterToEnglish(replaceAll);
                                }
                                final String formatStringEnglish = ContextUtils.formatStringEnglish(com.proje.mobilesales.R.string.pplz_repeat_command, Integer.valueOf(fontMargin), Integer.valueOf(fontSpace), mPrintSettings.getFontId(), Integer.valueOf(mPrintSettings.getFontWidth()), Integer.valueOf(mPrintSettings.getFontHeight()), replaceAll);
                                str = str + formatStringEnglish;
                                i2++;
                                Log.d(PrintPreviewActivity.TAG, "sendPrinterPPLZ:  command = " + formatStringEnglish);
                            }
                        } else {
                            final int fontSpace2 = (mPrintSettings.getFontSpace() * i3) % 999;
                            if (0 != this.mPrintSettings.getUseTurkishCharacter()) {
                                replaceAll = this.getConvertPrintTurkishCharacterToEnglish(replaceAll);
                            }
                            final String formatStringEnglish2 = ContextUtils.formatStringEnglish(com.proje.mobilesales.R.string.pplz_repeat_command, Integer.valueOf(fontMargin), Integer.valueOf(fontSpace2), mPrintSettings.getFontId(), Integer.valueOf(mPrintSettings.getFontWidth()), Integer.valueOf(mPrintSettings.getFontHeight()), replaceAll);
                            str = str + formatStringEnglish2;
                            Log.d(PrintPreviewActivity.TAG, "sendPrinterPPLZ:  command = " + formatStringEnglish2);
                        }
                    } catch (final Exception e2) {
                        e = e2;
                        str2 = str;
                        e.printStackTrace();
                        str = str2;
                        if (TextUtils.isEmpty(str)) {
                        }
                        Thread.sleep(mPrintSettings.getPreviewTime());
                    }
                }
            } catch (final Exception e3) {
                e = e3;
            }
            if (TextUtils.isEmpty(str)) {
                final String format = String.format("%s%s%s", this.getString(com.proje.mobilesales.R.string.pplz_start_command), this.getString(com.proje.mobilesales.R.string.pplz_open_command), this.getString(com.proje.mobilesales.R.string.pplz_end_command));
                Log.d(PrintPreviewActivity.TAG, "sendPrinterPPLZ: startValues=" + str);
                BluetoothUtil.getBluetoothService(isPrinterFirst).write(format.getBytes(StandardCharsets.UTF_8));
                final String format2 = String.format("%s%s%s", this.getString(com.proje.mobilesales.R.string.pplz_start_command), str, this.getString(com.proje.mobilesales.R.string.pplz_end_command));
                Log.d(PrintPreviewActivity.TAG, "sendPrinterPPLZ: sendValues=" + format2);
                BluetoothUtil.getBluetoothService(isPrinterFirst).write(format2.getBytes(StandardCharsets.UTF_8));
            } else {
                Log.d(PrintPreviewActivity.TAG, "sendPrinterPPLZ: no printable values");
                Toast.makeText(this, this.getString(com.proje.mobilesales.R.string.str_get_print_value_not_found), Toast.LENGTH_LONG).show();
            }
            try {
                Thread.sleep(mPrintSettings.getPreviewTime());
            } catch (final InterruptedException unused) {
            }
        } catch (final Exception e4) {
            e4.printStackTrace();
        }
    }
    private void sendPrinterTSPL() {
        String[] strArr;
        int i2;
        int i3;
        int fontSpace = 0;
        try {
            final boolean isPrinterFirst = mPrintSettings.isPrinterFirst();
            this.openPage();
            try {
                String[] split = StringUtils.split(this.unescape(PrintPreviewActivity.sPrintValue[mPageNumber]), '\n');
                final int fontMargin = mPrintSettings.getFontMargin();
                final String num = Integer.toString((int) (mPrintSettings.getFontWidth() * 0.46d));
                final String num2 = Integer.toString((int) (mPrintSettings.getFontHeight() * 0.46d));
                final ArrayList arrayList = new ArrayList();
                arrayList.add("SIZE 4,4.1\n");
                arrayList.add("GAP 0,0\n");
                arrayList.add("DENSITY 13\n");
                arrayList.add("DIRECTION 0\n");
                arrayList.add("CLS\n");
                arrayList.add("CODEPAGE UTF-8\n");
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                String str = "";
                while (i6 < split.length) {
                    final String replaceAll = split[i6].replaceAll("\r", "");
                    if (mPrintSettings.isRemoveBlankLines()) {
                        if ("".equals(replaceAll)) {
                            strArr = split;
                            i2 = i6;
                            i3 = i4;
                            i6 = i2 + 1;
                            i4 = i3;
                            split = strArr;
                        } else {
                            fontSpace = i7 * mPrintSettings.getFontSpace();
                            i2 = i6;
                            strArr = split;
                            i3 = i4;
                            str = TPLSUtils.PrinterFontStr(fontMargin, fontSpace, "arialbd.TTF", 0, num, num2, 0 != this.mPrintSettings.getUseTurkishCharacter() ? this.getConvertPrintTurkishCharacterToEnglish(replaceAll) : replaceAll);
                            arrayList.add(str);
                            i7++;
                        }
                    } else {
                        strArr = split;
                        i2 = i6;
                        i3 = i4;
                        final String convertPrintTurkishCharacterToEnglish = 0 != this.mPrintSettings.getUseTurkishCharacter() ? this.getConvertPrintTurkishCharacterToEnglish(replaceAll) : replaceAll;
                        fontSpace = i2 * mPrintSettings.getFontSpace();
                        str = TPLSUtils.PrinterFontStr(fontMargin, fontSpace, "arialbd.TTF", 0, num, num2, convertPrintTurkishCharacterToEnglish);
                        arrayList.add(str);
                    }
                    i5 = fontSpace;
                    i6 = i2 + 1;
                    i4 = i3;
                    split = strArr;
                }
                arrayList.set(i4, String.format("SIZE 101.6 mm,%d mm\n", Integer.valueOf(810 < i5 ? (i5 * 105) / 810 : 105)));
                final Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    TPLSUtils.SendCommand((String) it.next(), isPrinterFirst);
                    Log.d(PrintPreviewActivity.TAG, "sendPrinterPPLZ:  command = " + str);
                }
                TPLSUtils.PrintLabel(1, 1, isPrinterFirst);
            } catch (final Exception e2) {
                e2.printStackTrace();
            }
            try {
                Thread.sleep(mPrintSettings.getPreviewTime());
            } catch (final InterruptedException unused) {
            }
        } catch (final Exception e3) {
            e3.printStackTrace();
        }
    }
    private String getConvertPrintTurkishCharacterToEnglish(final String str) {
        String str2 = "";
        for (final char c2 : str.toCharArray()) {
            if ('\u00c7' == c2) {
                str2 = str2 + "C";
            } else if ('\u00d6' == c2) {
                str2 = str2 + "O";
            } else if ('\u00dc' == c2) {
                str2 = str2 + "U";
            } else if ('\u00e7' == c2) {
                str2 = str2 + "c";
            } else if ('\u00f6' == c2) {
                str2 = str2 + "o";
            } else if ('\u00fc' == c2) {
                str2 = str2 + "u";
            } else if ('\u011e' == c2) {
                str2 = str2 + "G";
            } else if ('\u011f' == c2) {
                str2 = str2 + "g";
            } else if ('\u0130' == c2) {
                str2 = str2 + "I";
            } else if ('\u0131' == c2) {
                str2 = str2 + "i";
            } else if ('\u015e' == c2) {
                str2 = str2 + ExifInterface.LATITUDE_SOUTH;
            } else if ('\u015f' == c2) {
                str2 = str2 + "s";
            } else {
                str2 = str2 + c2;
            }
        }
        return str2;
    }
    enum C26152 {
        ;
        static final int[] SwitchMapcomprojemobilesalescoreenumsFicheType;

        static {
            final int[] iArr = new int[FicheType.values().length];
            SwitchMapcomprojemobilesalescoreenumsFicheType = iArr;
            try {
                iArr[FicheType.INVOICE.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C26152.SwitchMapcomprojemobilesalescoreenumsFicheType[FicheType.ORDER.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                C26152.SwitchMapcomprojemobilesalescoreenumsFicheType[FicheType.RETAILINVOICE.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
            try {
                C26152.SwitchMapcomprojemobilesalescoreenumsFicheType[FicheType.DISPATCH.ordinal()] = 4;
            } catch (final NoSuchFieldError unused4) {
            }
        }
    }
    private void printImage(final FicheType ficheType, final boolean z) {
        final String printImage;
        final int i2 = C26152.SwitchMapcomprojemobilesalescoreenumsFicheType[ficheType.ordinal()];
        if (1 == i2) {
            printImage = Preferences.getPrintImage(this, z ? com.proje.mobilesales.R.string.pref_invoice_start_image_key : com.proje.mobilesales.R.string.pref_invoice_end_image_key);
        } else if (2 == i2) {
            printImage = Preferences.getPrintImage(this, z ? com.proje.mobilesales.R.string.pref_order_start_image_key : com.proje.mobilesales.R.string.pref_order_end_image_key);
        } else if (3 == i2) {
            printImage = Preferences.getPrintImage(this, z ? com.proje.mobilesales.R.string.pref_retail_invoice_start_image_key : com.proje.mobilesales.R.string.pref_retail_invoice_end_image_key);
        } else if (4 != i2) {
            printImage = "None";
        } else {
            printImage = Preferences.getPrintImage(this, z ? com.proje.mobilesales.R.string.pref_despatch_start_image_key : com.proje.mobilesales.R.string.pref_despatch_end_image_key);
        }
        if ("None".equals(printImage)) {
            return;
        }
        BluetoothUtil.getBluetoothService(mPrintSettings.isPrinterFirst()).write(String.format("\u001d(L\u0006 0E%1s\u0001\u0001", printImage).getBytes(StandardCharsets.UTF_8));
    }
    private void Send2Printer() {
        byte[] bytes;
        final FicheType ficheType = mPrintSettings.getFicheType();
        final boolean isPrinterFirst = mPrintSettings.isPrinterFirst();
        this.openPage();
        if (0 == this.mPageNumber) {
            this.printImage(ficheType, true);
        }
        final String[] split = mPrintSettings.getStartCode().split(",");
        final String[] split2 = mPrintSettings.getEndCode().split(",");
        int i2 = 0;
        for (int i3 = 0; i3 <= split.length - 1; i3++) {
            final String trim = split[i3].trim();
            if (0 != trim.length() && !"".equals(trim) && !"0".equals(trim)) {
                i2++;
            }
        }
        final byte[] bArr = new byte[i2];
        int i4 = 0;
        for (int i5 = 0; i5 <= split.length - 1; i5++) {
            final String trim2 = split[i5].trim();
            if (0 != trim2.length() && !"".equals(trim2) && !"0".equals(trim2)) {
                bArr[i4] = trim2.getBytes(StandardCharsets.UTF_8)[0];
                i4++;
            }
        }
        if (0 < i2) {
            BluetoothUtil.getBluetoothService(isPrinterFirst).write(bArr);
        }
        final String[] split3 = StringUtils.split(this.unescape(PrintPreviewActivity.sPrintValue[mPageNumber]), '\n');
        for (int i6 = 0; i6 < split3.length; i6++) {
            if (!mPrintSettings.isRemoveBlankLines() || !"".equals(split3[i6].replaceAll("\r", ""))) {
                String str = split3[i6] + SqlLiteVariable._NEW_LINE;
                if ("".equals(str)) {
                    str = " ";
                }
                if (2 == this.mPrintSettings.getUseTurkishCharacter()) {
                    bytes = this.convertToWindowsToDosCode(str.getBytes(StandardCharsets.UTF_8));
                } else {
                    bytes = str.getBytes(StandardCharsets.UTF_8);
                }
                BluetoothUtil.getBluetoothService(isPrinterFirst).write(bytes);
                try {
                    Thread.sleep(mPrintSettings.getPreviewTime());
                } catch (final InterruptedException unused) {
                }
            }
        }
        int i7 = 0;
        for (int i8 = 0; i8 <= split2.length - 1; i8++) {
            final String trim3 = split2[i8].trim();
            if (0 != trim3.length() && !"".equals(trim3) && !"0".equals(trim3)) {
                i7++;
            }
        }
        final byte[] bArr2 = new byte[i7];
        int i9 = 0;
        for (int i10 = 0; i10 <= split2.length - 1; i10++) {
            final String trim4 = split2[i10].trim();
            if (0 != trim4.length() && !"".equals(trim4) && !"0".equals(trim4)) {
                bArr2[i9] = trim4.getBytes(StandardCharsets.UTF_8)[0];
                i9++;
            }
        }
        if (0 < i7) {
            BluetoothUtil.getBluetoothService(isPrinterFirst).write(bArr2);
        }
        if (mPageNumber == PrintPreviewActivity.sPrintValue.length - 1) {
            this.printImage(ficheType, false);
        }
    }
    private void sendPrintValues(final String str) {
        final byte[] bytes;
        if (2 == this.mPrintSettings.getUseTurkishCharacter()) {
            bytes = this.convertToWindowsToDosCode(str.getBytes(StandardCharsets.UTF_8));
        } else {
            bytes = str.getBytes(StandardCharsets.UTF_8);
        }
        BluetoothUtil.getBluetoothService(mPrintSettings.isPrinterFirst()).write(bytes);
        try {
            Thread.sleep(mPrintSettings.getPreviewTime());
        } catch (final InterruptedException unused) {
        }
    }
    private byte[] convertToWindowsToDosCode(final byte[] bArr) {
        int i2;
        byte b2;
        int i3 = 0;
        while (i3 < bArr.length) {
            final byte b3 = bArr[i3];
            if (0 > b3 && 0 > (b2 = bArr[(i2 = i3 + 1)])) {
                if (-61 == b3 && -100 == b2) {
                    bArr[i3] = 0;
                    bArr[i2] = -102;
                    i3 = i2;
                }
                if (-61 == bArr[i3]) {
                    final int i4 = i3 + 1;
                    if (-68 == bArr[i4]) {
                        bArr[i3] = 0;
                        bArr[i4] = -127;
                        i3 = i4;
                    }
                }
                if (-61 == bArr[i3]) {
                    final int i5 = i3 + 1;
                    if (-106 == bArr[i5]) {
                        bArr[i3] = 0;
                        bArr[i5] = -103;
                        i3 = i5;
                    }
                }
                if (-61 == bArr[i3]) {
                    final int i6 = i3 + 1;
                    if (-74 == bArr[i6]) {
                        bArr[i3] = 0;
                        bArr[i6] = -108;
                        i3 = i6;
                    }
                }
                if (-60 == bArr[i3]) {
                    final int i7 = i3 + 1;
                    if (-80 == bArr[i7]) {
                        bArr[i3] = 0;
                        bArr[i7] = -104;
                        i3 = i7;
                    }
                }
                if (-60 == bArr[i3]) {
                    final int i8 = i3 + 1;
                    if (-79 == bArr[i8]) {
                        bArr[i3] = 0;
                        bArr[i8] = -115;
                        i3 = i8;
                    }
                }
                if (-59 == bArr[i3]) {
                    final int i9 = i3 + 1;
                    if (-98 == bArr[i9]) {
                        bArr[i3] = 0;
                        bArr[i9] = -98;
                        i3 = i9;
                    }
                }
                if (-59 == bArr[i3]) {
                    final int i10 = i3 + 1;
                    if (-97 == bArr[i10]) {
                        bArr[i3] = 0;
                        bArr[i10] = -97;
                        i3 = i10;
                    }
                }
                if (-61 == bArr[i3]) {
                    final int i11 = i3 + 1;
                    if (-121 == bArr[i11]) {
                        bArr[i3] = 0;
                        bArr[i11] = Byte.MIN_VALUE;
                        i3 = i11;
                    }
                }
                if (-61 == bArr[i3]) {
                    final int i12 = i3 + 1;
                    if (-89 == bArr[i12]) {
                        bArr[i3] = 0;
                        bArr[i12] = -121;
                        i3 = i12;
                    }
                }
                if (-60 == bArr[i3]) {
                    final int i13 = i3 + 1;
                    if (-98 == bArr[i13]) {
                        bArr[i3] = 0;
                        bArr[i13] = -90;
                        i3 = i13;
                    }
                }
                if (-60 == bArr[i3]) {
                    final int i14 = i3 + 1;
                    if (-97 == bArr[i14]) {
                        bArr[i3] = 0;
                        bArr[i14] = -89;
                        i3 = i14;
                    }
                }
            }
            i3++;
        }
        return bArr;
    }
    private void updatePrintCount(final int i2, final FType fType) {
        if (FTypeControlUtils.isFTypeOrder(fType)) {
            baseErp.getLogoSqlBriteDatabase().execute("UPDATE ORDERS SET PRINTCOUNT=1 WHERE LOGICALREF=" + i2);
            return;
        }
        if (FTypeControlUtils.isFTypeInvoiceOrRetailInvoiceOrDispatch(fType)) {
            baseErp.getLogoSqlBriteDatabase().execute("UPDATE INVOICE SET PRINTCOUNT=1 WHERE LOGICALREF=" + i2);
            return;
        }
        if (FTypeControlUtils.isFTypeCashOrCreditReceipt(fType)) {
            baseErp.getLogoSqlBriteDatabase().execute("UPDATE CASHCREDIT SET PRINTCOUNT=1 WHERE LOGICALREF=" + i2);
            return;
        }
        if (FTypeControlUtils.isFTypeCaseReceipt(fType)) {
            baseErp.getLogoSqlBriteDatabase().execute("UPDATE CASECASH SET PRINTCOUNT=1 WHERE LOGICALREF=" + i2);
            return;
        }
        if (FTypeControlUtils.isFTypeCheckOrDeedReceipt(fType)) {
            baseErp.getLogoSqlBriteDatabase().execute("UPDATE CHEQUEDEED SET PRINTCOUNT=1 WHERE LOGICALREF=" + i2);
        }
    }
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable(PrintPreviewActivity.STATE_PRINT_SETTING, mPrintSettings);
        bundle.putInt(PrintPreviewActivity.STATE_PRINT_CLREF, clRef);
    }
    public void onBluetoothStateChange(final int i2) {
        Log.d(PrintPreviewActivity.TAG, "onBluetoothStateChange: " + i2);
        if (3 == i2) {
            this.printPage(PrintPreviewActivity.sPrintAll);
        } else if ((1 == i2 || 0 == i2) && mPrintSettings.isDontShowPreview()) {
            this.finish();
        }
    }
}
