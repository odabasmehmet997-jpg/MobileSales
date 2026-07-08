package com.proje.mobilesales.features.visit.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.PickVisualMediaRequestKt;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.google.firebase.messaging.Constants;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseFicheFragment;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.utils.CompressUtil;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.ImageUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.PermissionUtils;
import com.proje.mobilesales.features.customer.view.communication.ship.CustomerShipAddressActivity;
import com.proje.mobilesales.features.model.FicheImageProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.visit.model.Visit;
import com.proje.mobilesales.features.visit.view.activity.VisitEnterActivityNew;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import static androidx.activity.result.PickVisualMediaRequestKt.PickVisualMediaRequest;

public final class VisitEnterFragment extends BaseFicheFragment {
    public static final Companion Companion = new Companion(null);
    public static final int REQUEST_CAMERA_CODE = 997;
    public static final int REQUEST_SELECT_FILE_CODE = 996;
    public static final int SHIP_ADDRESS_CODE = 998;
    private static final String STATE_USER_CHOICE = "state:userChoice";
    public static final int VISIT_REASON_VOICE_CODE = 999;
    private EditText edtVisitNote;
    private ImageView imgDeleteShipAddress;
    private ImageView imgVisit;
    private LinearLayout lnShipAddress;
    private LinearLayout lnVisitNote;
    private LinearLayout lnVisitReason;
    private LinearLayout lnVisitUserTitle;
    public AlertDialogBuilder<?> mPhotoAlertDialogBuilder;
    public AlertDialogBuilder<?> mPhotoDeleteDialogBuilder;
    public AlertDialogBuilder<?> mUserTitleDialogBuilder;
    private final ActivityResultLauncher<PickVisualMediaRequest> photoPickerLauncher;
    private final int select = -1;
    private TextView txtShipAddress;
    private TextView txtVisitReason;
    private TextView txtVisitUserTitle;
    private String userChoice;

    public VisitEnterFragment() {
        ActivityResultLauncher<PickVisualMediaRequest> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback() { // from class: com.proje.mobilesales.features.visit.view.fragment.VisitEnterFragmentExternalSyntheticLambda2
 
            public void onActivityResult(Object obj) {
                VisitEnterFragment.photoPickerLauncherlambda7(VisitEnterFragment.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.photoPickerLauncher = registerForActivityResult;
    } 
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        Context requireContext = requireContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mPhotoAlertDialogBuilder = new AlertDialogBuilder.Impl(requireContext, (BaseInjectableActivity) activity);
        Context requireContext2 = requireContext();
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mPhotoDeleteDialogBuilder = new AlertDialogBuilder.Impl(requireContext2, (BaseInjectableActivity) activity2);
        Context requireContext3 = requireContext();
        Activity activity3 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mUserTitleDialogBuilder = new AlertDialogBuilder.Impl(requireContext3, (BaseInjectableActivity) activity3);
        setHasOptionsMenu(true);
        if (bundle != null) {
            str = bundle.getString(STATE_USER_CHOICE);
        } else {
            str = "";
        }
        this.userChoice = str;
    } 
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        @SuppressLint("RestrictedApi") View inflate = getLayoutInflater(bundle).inflate(R.layout.fragment_visit, viewGroup, false);
        View findViewById = inflate.findViewById(R.id.ln_visitShipAddress);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.LinearLayout");
        this.lnShipAddress = (LinearLayout) findViewById;
        View findViewById2 = inflate.findViewById(R.id.txt_visitShipAddress);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        this.txtShipAddress = (TextView) findViewById2;
        View findViewById3 = inflate.findViewById(R.id.img_visitShipAddressDelete);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.ImageView");
        this.imgDeleteShipAddress = (ImageView) findViewById3;
        View findViewById4 = inflate.findViewById(R.id.ln_visitReasonSelect);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.LinearLayout");
        this.lnVisitReason = (LinearLayout) findViewById4;
        View findViewById5 = inflate.findViewById(R.id.txt_visitReasonSelect);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.TextView");
        this.txtVisitReason = (TextView) findViewById5;
        View findViewById6 = inflate.findViewById(R.id.ln_visitUserTitle);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type android.widget.LinearLayout");
        this.lnVisitUserTitle = (LinearLayout) findViewById6;
        View findViewById7 = inflate.findViewById(R.id.txt_visitUserTitle);
        Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type android.widget.TextView");
        this.txtVisitUserTitle = (TextView) findViewById7;
        View findViewById8 = inflate.findViewById(R.id.ln_visitNote);
        Intrinsics.checkNotNull(findViewById8, "null cannot be cast to non-null type android.widget.LinearLayout");
        this.lnVisitNote = (LinearLayout) findViewById8;
        View findViewById9 = inflate.findViewById(R.id.edt_visitNote);
        Intrinsics.checkNotNull(findViewById9, "null cannot be cast to non-null type android.widget.EditText");
        this.edtVisitNote = (EditText) findViewById9;
        View findViewById10 = inflate.findViewById(R.id.img_visit);
        Intrinsics.checkNotNull(findViewById10, "null cannot be cast to non-null type android.widget.ImageView");
        this.imgVisit = (ImageView) findViewById10;
        return inflate;
    }
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        FicheStringProp ficheStringProp = null;
        if (getSalesFicheMode() != FicheMode.ANALYSE) {
            LinearLayout linearLayout = this.lnShipAddress;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view2) {
                    onViewCreatedlambda0(VisitEnterFragment.this, view2);
                }
            });
            ImageView imageView = this.imgDeleteShipAddress;
            Intrinsics.checkNotNull(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view2) {
                    onViewCreatedlambda1(VisitEnterFragment.this, view2);
                }
            });
            EditText editText = this.edtVisitNote;
            Intrinsics.checkNotNull(editText);
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            assert context != null;
            editText.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context, R.drawable.ic_microphone_grey600_24dp), null);
            EditText editText2 = this.edtVisitNote;
            Intrinsics.checkNotNull(editText2);
            editText2.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view2, MotionEvent motionEvent) {
                    return onViewCreatedlambda2(VisitEnterFragment.this, view2, motionEvent);
                }
            });
            ImageView imageView2 = this.imgVisit;
            Intrinsics.checkNotNull(imageView2);
            imageView2.setOnLongClickListener(new OnLongClickListener() {
                public boolean onLongClick(View view2) {
                    return onViewCreatedlambda5(VisitEnterFragment.this, view2);
                }
            });
        }
        FicheMode salesFicheMode = getSalesFicheMode();
        LinearLayout linearLayout2 = this.lnVisitUserTitle;
        TextView textView = this.txtVisitUserTitle;
        Visit visitInfo = getVisitInfo();
        createSingleAlertCursorSalesArray(salesFicheMode, linearLayout2, textView, visitInfo != null ? visitInfo.getUserTitle() : null, true, R.string.str_visit_user_title, R.array.visit_user_title_values, true);
        FicheMode salesFicheMode2 = getSalesFicheMode();
        LinearLayout linearLayout3 = this.lnVisitReason;
        TextView textView2 = this.txtVisitReason;
        Visit visitInfo2 = getVisitInfo();
        createSingleAlertCursorSales(salesFicheMode2, linearLayout3, textView2, visitInfo2 != null ? visitInfo2.getReason() : null, R.string.str_visit_reason, R.string.qry_visit_reason, R.string.column_code);
        FicheMode salesFicheMode3 = getSalesFicheMode();
        LinearLayout linearLayout4 = this.lnVisitNote;
        EditText editText3 = this.edtVisitNote;
        Visit visitInfo3 = getVisitInfo();
        if (visitInfo3 != null) {
            ficheStringProp = visitInfo3.getNote();
        }
        createEditTextAddTextChangedListener(salesFicheMode3, linearLayout4, editText3, ficheStringProp);
    }
    public void onViewCreatedlambda0(VisitEnterFragment this0, View view) {
            Intrinsics.checkNotNullParameter(this0, "this0");
        Intent intent = new Intent(this0.getActivity(), CustomerShipAddressActivity.class);
        intent.putExtra(CustomerShipAddressActivity.EXTRA_SALES_SELECT, true);
        intent.putExtra("extra:customerRef", this0.mCustomerRef);
        this0.startActivityForResult(intent, 998);
    }
    public void onViewCreatedlambda1(VisitEnterFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Visit visitInfo = this0.getVisitInfo();
        FicheRefProp shipAddress = visitInfo != null ? visitInfo.getShipAddress() : null;
        if (shipAddress == null) {
            return;
        }
        shipAddress.reset();
        TextView textView = this0.txtShipAddress;
        Visit visitInfo2 = this0.getVisitInfo();
        if (visitInfo2 == null) {
            return;
        }
        this0.loadTextView(textView, String.valueOf(visitInfo2.getShipAddress()));
        ImageView imageView = this0.imgDeleteShipAddress;
        Intrinsics.checkNotNull(imageView);
        imageView.setVisibility(View.GONE);
    }
    public boolean onViewCreatedlambda2(VisitEnterFragment this0, View view, MotionEvent event) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getAction() != 1) {
            return false;
        }
        float rawX = event.getRawX();
        EditText editText = this0.edtVisitNote;
        Intrinsics.checkNotNull(editText);
        int right = editText.getRight();
        Intrinsics.checkNotNull(this0.edtVisitNote);
        if (rawX < right - editText.getCompoundDrawables()[2].getBounds().width()) {
            return false;
        }
        this0.startVoiceIntent();
        return true;
    } 
    public boolean onViewCreatedlambda5(final VisitEnterFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Visit visitInfo = this0.getVisitInfo();
        if (visitInfo == null) {
            return true;
        }
        FicheImageProp image = visitInfo.getImage();
        if (image == null) {
            return true;
        }
        Intrinsics.checkNotNull(image);
        if (image.getImageArray() == null) {
            return true;
        }
        AlertDialogBuilder<?> alertDialogBuilder = this0.mPhotoDeleteDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.setTitle(R.string.str_remove_photo).setPositiveButton(R.string.str_yes, new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialogInterface, int i2) {
                VisitEnterFragment.onViewCreatedlambda5lambda3(VisitEnterFragment.this, dialogInterface, i2);
            }
        }).setNegativeButton(R.string.str_no, new DialogInterface.OnClickListener() { 
            public void onClick(DialogInterface dialogInterface, int i2) {
                VisitEnterFragment.onViewCreatedlambda5lambda4(VisitEnterFragment.this, dialogInterface, i2);
            }
        }).create().show();
        return true;
    } 
    public static void onViewCreatedlambda5lambda3(VisitEnterFragment this0, DialogInterface dialog, int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
        Visit visitInfo = this0.getVisitInfo();
        if (visitInfo == null) {
            return;
        }
        FicheImageProp image = visitInfo.getImage();
        if (image == null) {
            return;
        }
        Intrinsics.checkNotNull(image);
        image.reset();
        this0.loadImageView(this0.imgVisit, null);
    }
    public static void onViewCreatedlambda5lambda4(VisitEnterFragment visitEnterFragment, DialogInterface dialog, int i2) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putString(STATE_USER_CHOICE, this.userChoice);
        super.onSaveInstanceState(outState);
    }
    private void startVoiceIntent() {
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        intent.putExtra("android.speech.extra.LANGUAGE", Locale.getDefault());
        intent.putExtra("android.speech.extra.PROMPT", "Ziyaret Notu Giriniz");
        try {
            startActivityForResult(intent, 999);
        } catch (Exception e2) {
            Toast.makeText(getContext(), getString(R.string.str_device_not_support_feature), Toast.LENGTH_LONG).show();
            Log.d(MobileSales.TAG, "startVoiceIntent: ", e2);
        }
    }
    protected void createOptionsMenu(Menu menu, MenuInflater inflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        super.createOptionsMenu(menu, inflater);
    } 
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == R.id.menu_photo) {
            Visit visitInfo = getVisitInfo();
            if (visitInfo == null) {
                return super.onOptionsItemSelected(item);
            }
            FicheImageProp image = visitInfo.getImage();
            if (image == null) {
                return super.onOptionsItemSelected(item);
            }
            Intrinsics.checkNotNull(image);
            image.reset();
            openAddPhotoDialog();
        }
        return super.onOptionsItemSelected(item);
    }
    public int getSelect() {
        return this.select;
    }
    private void openAddPhotoDialog() {
        AlertDialogBuilder<?> alertDialogBuilder = this.mPhotoAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.setTitle(R.string.str_add_photo).setSingleChoice(R.array.add_photo_select_values, this.select, new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialogInterface, int i2) {
                VisitEnterFragment.openAddPhotoDialoglambda6(VisitEnterFragment.this, dialogInterface, i2);
            }
        }).create().show();
    } 
    public static void openAddPhotoDialoglambda6(VisitEnterFragment this0, DialogInterface dialog, int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        this0.userChoice = this0.getResources().getStringArray(R.array.add_photo_select_values)[i2];
        dialog.dismiss();
        this0.startUserChooseIntent();
    } 
    public static void photoPickerLauncherlambda7(VisitEnterFragment this0, Uri uri) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (uri != null) {
            Bitmap selectedGalleryImageWithSdkControl = null;
            try {
                selectedGalleryImageWithSdkControl = this0.getSelectedGalleryImageWithSdkControl(uri);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                this0.setVisitImage(CompressUtil.compressBitmapToJpeg(selectedGalleryImageWithSdkControl));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ImageView imageView = this0.imgVisit;
            Intrinsics.checkNotNull(imageView);
            imageView.setImageDrawable(null);
            ImageView imageView2 = this0    .imgVisit;
            Intrinsics.checkNotNull(imageView2);
            imageView2.setImageBitmap(selectedGalleryImageWithSdkControl);
        }
    }
    private void startUserChooseIntent() {
        if (Intrinsics.areEqual(this.userChoice, getString(R.string.str_choose_from_library))) {
            if (Build.VERSION.SDK_INT >= 33) {
                this.photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE));
                return;
            } else {
                if (PermissionUtils.checkPermissionFromFragment(this, "android.permission.READ_EXTERNAL_STORAGE", getString(R.string.str_read_disk_permission_for_save_picture))) {
                    createGalleryIntent();
                    return;
                }
                return;
            }
        }
        if (Intrinsics.areEqual(this.userChoice, getString(R.string.str_take_photo)) && PermissionUtils.checkPermissionFromFragment(this, "android.permission.CAMERA", getString(R.string.str_camera_permission_for_take_photo))) {
            createCameraIntent();
        }
    }
    private PickVisualMediaRequest PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly instance) {
        return null;
    }
    private void createCameraIntent() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            startActivityForResult(intent, 997);
        }
    }
    private void createGalleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(intent, "Select File"), 996);
    }
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        switch (i2) {
            case 996:
                if (i3 == -1) {
                    onSelectFromGalleryResult(intent);
                    break;
                }
                break;
            case 997:
                if (i3 == -1) {
                    onCaptureImageResult(intent);
                    break;
                }
                break;
            case 998:
                if (-1 == i3) {
                    Intrinsics.checkNotNull(intent);
                    Bundle extras = intent.getExtras();
                    Intrinsics.checkNotNull(extras);
                    int i4 = extras.getInt(IntentExtraName.EXTRA_REF);
                    Bundle extras2 = intent.getExtras();
                    Intrinsics.checkNotNull(extras2);
                    String string = extras2.getString(IntentExtraName.EXTRA_DEFINITION);
                    Visit visitInfo = getVisitInfo();
                    FicheRefProp shipAddress = visitInfo != null ? visitInfo.getShipAddress() : null;
                    Intrinsics.checkNotNull(shipAddress);
                    shipAddress.setLogicalRef(i4);
                    Visit visitInfo2 = getVisitInfo();
                    FicheRefProp shipAddress2 = visitInfo2 != null ? visitInfo2.getShipAddress() : null;
                    Intrinsics.checkNotNull(shipAddress2);
                    FicheStringProp.setDefinition(string);
                    TextView textView = this.txtShipAddress;
                    Intrinsics.checkNotNull(textView);
                    Visit visitInfo3 = getVisitInfo();
                    textView.setText(String.valueOf(visitInfo3 != null ? visitInfo3.getShipAddress() : null));
                    Visit visitInfo4 = getVisitInfo();
                    FicheRefProp shipAddress3 = visitInfo4 != null ? visitInfo4.getShipAddress() : null;
                    Intrinsics.checkNotNull(shipAddress3);
                    if (shipAddress3.getLogicalRef() > 0) {
                        ImageView imageView = this.imgDeleteShipAddress;
                        Intrinsics.checkNotNull(imageView);
                        imageView.setVisibility(View.VISIBLE);
                        break;
                    }
                }
                break;
            case 999:
                if (i3 == -1) {
                    Intrinsics.checkNotNull(intent);
                    ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("android.speech.extra.RESULTS");
                    Intrinsics.checkNotNull(stringArrayListExtra);
                    if (!stringArrayListExtra.isEmpty()) {
                        Visit visitInfo5 = getVisitInfo();
                        FicheStringProp note = visitInfo5 != null ? visitInfo5.getNote() : null;
                        Intrinsics.checkNotNull(note);
                        if (TextUtils.isEmpty(note.getDefinition())) {
                            Visit visitInfo6 = getVisitInfo();
                            FicheStringProp note2 = visitInfo6 != null ? visitInfo6.getNote() : null;
                            Intrinsics.checkNotNull(note2);
                            FicheStringProp.setDefinition(addVisitEditTextVoiceResult(stringArrayListExtra));
                        } else {
                            Visit visitInfo7 = getVisitInfo();
                            FicheStringProp note3 = visitInfo7 != null ? visitInfo7.getNote() : null;
                            Intrinsics.checkNotNull(note3);
                            StringBuilder sb = new StringBuilder();
                            Visit visitInfo8 = getVisitInfo();
                            FicheStringProp note4 = visitInfo8 != null ? visitInfo8.getNote() : null;
                            Intrinsics.checkNotNull(note4);
                            sb.append(note4.getDefinition());
                            sb.append(' ');
                            sb.append(addVisitEditTextVoiceResult(stringArrayListExtra));
                            FicheStringProp.setDefinition(sb.toString());
                        }
                        EditText editText = this.edtVisitNote;
                        Visit visitInfo9 = getVisitInfo();
                        loadTextView(editText, String.valueOf(visitInfo9 != null ? visitInfo9.getNote() : null));
                        break;
                    }
                }
                break;
        }
    }
    private  void onSelectFromGalleryResult(Intent intent) {
        Bitmap bitmap;
        if (intent != null) {
            IOException e;
            try {
                bitmap = getSelectedGalleryImageWithSdkControl(intent.getData());
                try {
                    setVisitImage(CompressUtil.compressBitmapToJpeg(bitmap));
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                    if (bitmap == null) {
                    }
                }
            } catch (IOException e3) {
                e = e3;
                bitmap = null;
            }
        } else {
            bitmap = null;
        }
        if (bitmap == null) {
            ImageView imageView = this.imgVisit;
            Intrinsics.checkNotNull(imageView);
            imageView.setImageDrawable(null);
            ImageView imageView2 = this.imgVisit;
            Intrinsics.checkNotNull(imageView2);
            imageView2.setImageBitmap(bitmap);
        }
    }
    private Bitmap getSelectedGalleryImageWithSdkControl(Uri uri) throws IOException {
        ImageDecoder.Source createSource;
        Bitmap decodeBitmap;
        if (Build.VERSION.SDK_INT >= 29) {
            ContentResolver contentResolver = requireActivity().getApplicationContext().getContentResolver();
            Intrinsics.checkNotNull(uri);
            createSource = ImageDecoder.createSource(contentResolver, uri);
            Intrinsics.checkNotNullExpressionValue(createSource, "createSource(...)");
            try {
                decodeBitmap = ImageDecoder.decodeBitmap(createSource);
                return decodeBitmap;
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } else {
            try {
                ContentResolver contentResolver2 = requireActivity().getApplicationContext().getContentResolver();
                Intrinsics.checkNotNull(uri);
                Bitmap decodeStream = BitmapFactory.decodeStream(contentResolver2.openInputStream(uri));
                Intrinsics.checkNotNull(decodeStream);
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                return ImageUtils.modifyOrientation(decodeStream, ImageUtils.getAbsolutePath(requireActivity, uri));
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        return null;
    }
    private void onCaptureImageResult(Intent intent) {
        Exception e2;
        Bitmap bitmap;
        Bitmap bitmap2 = null;
        if (intent != null) {
            try {
                Bundle extras = intent.getExtras();
                Intrinsics.checkNotNull(extras);
                bitmap = (Bitmap) extras.get(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
                try {
                    setVisitImage(CompressUtil.compressBitmapToJpeg(bitmap));
                } catch (Exception e3) {
                    e2 = e3;
                    e2.printStackTrace();
                    bitmap2 = bitmap;
                    if (bitmap2 == null) {
                    }
                }
            } catch (Exception e4) {
                e2 = e4;
                bitmap = null;
            }
            bitmap2 = bitmap;
        }
        if (bitmap2 == null) {
            ImageView imageView = this.imgVisit;
            Intrinsics.checkNotNull(imageView);
            imageView.setImageBitmap(bitmap2);
        }
    }
    private  void setVisitImage(byte[] bArr) {
        if (bArr != null) {
            Visit visitInfo = getVisitInfo();
            FicheImageProp image = visitInfo != null ? visitInfo.getImage() : null;
            Intrinsics.checkNotNull(image);
            image.setImageArray(bArr);
        }
    }
    private String addVisitEditTextVoiceResult(ArrayList<String> arrayList) {
        StringBuilder sb = new StringBuilder();
        Intrinsics.checkNotNull(arrayList);
        sb.append(arrayList.get(0));
        sb.append(' ');
        return sb.toString();
    } 
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        load();
    }
    protected void load() {
        TextView textView = this.txtShipAddress;
        Visit visitInfo = getVisitInfo();
        loadTextView(textView, String.valueOf(visitInfo != null ? visitInfo.getShipAddress() : null));
        EditText editText = this.edtVisitNote;
        Visit visitInfo2 = getVisitInfo();
        loadTextView(editText, String.valueOf(visitInfo2 != null ? visitInfo2.getNote() : null));
        TextView textView2 = this.txtVisitReason;
        Visit visitInfo3 = getVisitInfo();
        loadTextView(textView2, String.valueOf(visitInfo3 != null ? visitInfo3.getReason() : null));
        TextView textView3 = this.txtVisitUserTitle;
        Visit visitInfo4 = getVisitInfo();
        loadTextView(textView3, String.valueOf(visitInfo4 != null ? visitInfo4.getUserTitle() : null));
        try {
            ImageView imageView = this.imgVisit;
            Visit visitInfo5 = getVisitInfo();
            FicheImageProp image = visitInfo5 != null ? visitInfo5.getImage() : null;
            Intrinsics.checkNotNull(image);
            loadImageView(imageView, CompressUtil.deCompressBitmap(image.getImageArray()));
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
    private Visit getVisitInfo() {
        VisitEnterActivityNew visitEnterActivityNew = (VisitEnterActivityNew) getActivity();
        Intrinsics.checkNotNull(visitEnterActivityNew);
        return visitEnterActivityNew.getmVisit();
    }
    public void onRequestPermissionsResult(int i2, String[] permissions2, int[] grantResults) {
        String string;
        Intrinsics.checkNotNullParameter(permissions2, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        if (i2 == 1071 || i2 == 1074) {
            if (!(grantResults.length == 0) && grantResults[0] == 0) {
                if (i2 == 1071) {
                    string = getString(R.string.str_choose_from_library);
                } else {
                    string = getString(R.string.str_take_photo);
                }
                this.userChoice = string;
                startUserChooseIntent();
            } else {
                Toast.makeText(getContext(), getString(R.string.str_permissions_denied), Toast.LENGTH_LONG).show();
            }
        }
        super.onRequestPermissionsResult(i2, permissions2, grantResults);
    }
    public static final class Companion {
        public   Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
