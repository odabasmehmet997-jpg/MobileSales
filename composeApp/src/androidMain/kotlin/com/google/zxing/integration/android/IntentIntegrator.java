package com.google.zxing.integration.android;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.journeyapps.barcodescanner.CaptureActivity;

import java.util.*;

public class IntentIntegrator {
    public static final Collection<String> ALL_CODE_TYPES = null;
    public static final Collection<String> ONE_D_CODE_TYPES = IntentIntegrator.list("UPC_A", "UPC_E", "EAN_8", "EAN_13", "RSS_14", "CODE_39", "CODE_93", "CODE_128", "ITF", "RSS_14", "RSS_EXPANDED");
    public static final Collection<String> PRODUCT_CODE_TYPES = IntentIntegrator.list("UPC_A", "UPC_E", "EAN_8", "EAN_13", "RSS_14");
    private final Activity activity;
    private Class<?> captureActivity;
    private Collection<String> desiredBarcodeFormats;
    private Fragment fragment;
    private final Map<String, Object> moreExtras = new HashMap(3);
    private final int requestCode = 49374;
    private androidx.fragment.app.Fragment supportFragment;
    public Class<?> getDefaultCaptureActivity() {
        return CaptureActivity.class;
    }
    public IntentIntegrator(final Activity activity2) {
        activity = activity2;
    }
    public Class<?> getCaptureActivity() {
        if (null == this.captureActivity) {
            captureActivity = this.getDefaultCaptureActivity();
        }
        return captureActivity;
    }
    public IntentIntegrator setCaptureActivity(final Class<?> cls) {
        captureActivity = cls;
        return this;
    }
    public static IntentIntegrator forSupportFragment(final androidx.fragment.app.Fragment fragment2) {
        final IntentIntegrator intentIntegrator = new IntentIntegrator(fragment2.getActivity());
        intentIntegrator.supportFragment = fragment2;
        return intentIntegrator;
    }
    public final IntentIntegrator addExtra(final String str, final Object obj) {
        moreExtras.put(str, obj);
        return this;
    }
    public IntentIntegrator setBeepEnabled(final boolean z) {
        this.addExtra("BEEP_ENABLED", Boolean.valueOf(z));
        return this;
    }
    public IntentIntegrator setDesiredBarcodeFormats(final Collection<String> collection) {
        desiredBarcodeFormats = collection;
        return this;
    }
    public final void initiateScan() {
        this.startActivityForResult(this.createScanIntent(), requestCode);
    }
    public Intent createScanIntent() {
        final Intent intent = new Intent(activity, this.getCaptureActivity());
        intent.setAction("com.google.zxing.client.android.SCAN");
        if (null != this.desiredBarcodeFormats) {
            final StringBuilder sb = new StringBuilder();
            for (final String next : desiredBarcodeFormats) {
                if (0 < sb.length()) {
                    sb.append(',');
                }
                sb.append(next);
            }
            intent.putExtra("SCAN_FORMATS", sb.toString());
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        this.attachMoreExtras(intent);
        return intent;
    }
    public void startActivityForResult(final Intent intent, final int i2) {
        final Fragment fragment2 = fragment;
        if (null != fragment2) {
            fragment2.startActivityForResult(intent, i2);
            return;
        }
        final androidx.fragment.app.Fragment fragment3 = supportFragment;
        if (null != fragment3) {
            fragment3.startActivityForResult(intent, i2);
        } else {
            activity.startActivityForResult(intent, i2);
        }
    }
    public static IntentResult parseActivityResult(final int i2, final int i3, final Intent intent) {
        if (49374 == i2) {
            return IntentIntegrator.parseActivityResult(i3, intent);
        }
        return null;
    }
    public static IntentResult parseActivityResult(final int i2, final Intent intent) {
        final Integer valueOf;
        if (-1 != i2) {
            return new IntentResult(intent);
        }
        final String stringExtra = intent.getStringExtra("SCAN_RESULT");
        final String stringExtra2 = intent.getStringExtra("SCAN_RESULT_FORMAT");
        final byte[] byteArrayExtra = intent.getByteArrayExtra("SCAN_RESULT_BYTES");
        final int intExtra = intent.getIntExtra("SCAN_RESULT_ORIENTATION", Integer.MIN_VALUE);
        if (Integer.MIN_VALUE == intExtra) {
            valueOf = null;
        } else {
            valueOf = Integer.valueOf(intExtra);
        }
        return new IntentResult(stringExtra, stringExtra2, byteArrayExtra, valueOf, intent.getStringExtra("SCAN_RESULT_ERROR_CORRECTION_LEVEL"), intent.getStringExtra("SCAN_RESULT_IMAGE_PATH"), intent);
    }
    private static List<String> list(final String... strArr) {
        return Collections.unmodifiableList(Arrays.asList(strArr));
    }
   private void attachMoreExtras(final Intent intent) {
        for (final Map.Entry next : moreExtras.entrySet()) {
            final String str = (String) next.getKey();
            final Object value = next.getValue();
            if (value instanceof Integer) {
                intent.putExtra(str, (Integer) value);
            } else if (value instanceof Long) {
                intent.putExtra(str, (Long) value);
            } else if (value instanceof Boolean) {
                intent.putExtra(str, (Boolean) value);
            } else if (value instanceof Double) {
                intent.putExtra(str, (Double) value);
            } else if (value instanceof Float) {
                intent.putExtra(str, (Float) value);
            } else if (value instanceof Bundle) {
                intent.putExtra(str, (Bundle) value);
            } else if (value instanceof int[]) {
                intent.putExtra(str, (int[]) value);
            } else if (value instanceof long[]) {
                intent.putExtra(str, (long[]) value);
            } else if (value instanceof boolean[]) {
                intent.putExtra(str, (boolean[]) value);
            } else if (value instanceof double[]) {
                intent.putExtra(str, (double[]) value);
            } else if (value instanceof float[]) {
                intent.putExtra(str, (float[]) value);
            } else if (value instanceof String[]) {
                intent.putExtra(str, (String[]) value);
            } else {
                intent.putExtra(str, value.toString());
            }
        }
    }
}
