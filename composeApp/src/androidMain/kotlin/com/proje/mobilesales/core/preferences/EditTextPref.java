package com.proje.mobilesales.core.preferences;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.preference.EditTextPreference;
import android.util.AttributeSet;
import android.view.View;
import com.proje.mobilesales.core.utils.IntentExtraName;

import static android.content.Context.VIBRATOR_SERVICE;

public class EditTextPref extends EditTextPreference implements View.OnLongClickListener {
    public EditTextPref(Context context) {
        super(context);
    }
    public EditTextPref(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
    public EditTextPref(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
    }
    public boolean onLongClick(View view) {
        final Object systemService = getContext().getSystemService(VIBRATOR_SERVICE);
        if (getContext().getPackageManager().queryIntentActivities(new Intent("android.speech.action.RECOGNIZE_SPEECH"), 0).isEmpty()) {
            return false;
        }
        startVoiceRecognitionActivity();
        return true;
    }
    private void startVoiceRecognitionActivity() {
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        intent.putExtra("android.speech.extra.PROMPT", getTitle());
        ((Activity) getContext()).startActivityForResult(intent, IntentExtraName.VOICE_RECOGNITION_REQUEST_CODE);
    }
}
