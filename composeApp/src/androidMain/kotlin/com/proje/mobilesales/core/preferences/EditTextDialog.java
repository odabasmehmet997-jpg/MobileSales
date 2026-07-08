package com.proje.mobilesales.core.preferences;

import android.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class EditTextDialog extends DialogFragment {
    public static final String EDIT_VALUE_KEY = "value";
    private static final String MESSAGE_KEY = "message";
    private static final String TITLE_KEY = "title";
    private AlertDialog dialog;
    private String message;
    private String title;
    public static EditTextDialog newInstance(Fragment fragment, int i2, String str, String str2) {
        EditTextDialog editTextDialog = new EditTextDialog();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE_KEY, str);
        bundle.putString(MESSAGE_KEY, str2);
        editTextDialog.setArguments(bundle);
        editTextDialog.setTargetFragment(fragment, i2);
        return editTextDialog;
    }
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.title = getArguments().getString(TITLE_KEY);
            this.message = getArguments().getString(MESSAGE_KEY);
        }
    }
    public Dialog onCreateDialog(Bundle bundle) {
        View inflate = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_content, null);
        final EditText editText = inflate.findViewById(R.id.edit_value);
        editText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }
            public void afterTextChanged(Editable editable) {
                if (EditTextDialog.this.dialog != null) {
                    EditTextDialog.this.dialog.getButton(-1).setEnabled(!editable.toString().trim().isEmpty());
                }
            }
        });
        AlertDialog create = new AlertDialog.Builder(getActivity()).setTitle(this.title).setMessage(this.message).setView(inflate).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.core.preferences.EditTextDialog.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.dismiss();
                EditTextDialog.this.getTargetFragment().onActivityResult(EditTextDialog.this.getTargetRequestCode(), 0, null);
            }
        }).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.core.preferences.EditTextDialog.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.dismiss();
                EditTextDialog.this.getTargetFragment().onActivityResult(EditTextDialog.this.getTargetRequestCode(), -1, EditTextDialog.getReturnIntent(editText.getText().toString().trim()));
            }
        }).create();
        this.dialog = create;
        create.show();
        this.dialog.getButton(-1).setEnabled(false);
        return this.dialog;
    }
    public static Intent getReturnIntent(String str) {
        Intent intent = new Intent();
        intent.putExtra("value", str);
        return intent;
    }
}
