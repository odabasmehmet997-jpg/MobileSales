package com.proje.mobilesales.features.customer.view.newadd;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentActivity;
import com.google.firebase.messaging.Constants;
import com.proje.mobilesales.core.base.BaseFicheFragment;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.AddPhotoMethod;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.enums.NewCustomerRequiredFields;
import com.proje.mobilesales.core.enums.WorkTimeControlProcessType;
import com.proje.mobilesales.core.event.ResponseEvent;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.utils.CompressUtil;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.PermissionUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.widget.FichePropertyEditView;
import com.proje.mobilesales.core.widget.FichePropertyTextView;
import com.proje.mobilesales.databinding.FragmentCustomerNewBinding;
import com.proje.mobilesales.features.customer.model.CustomerNew;
import com.proje.mobilesales.features.customer.repository.CustomerNewRepository;
import com.proje.mobilesales.features.model.FicheBooleanProp;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheImageProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public final class CustomerNewFragment extends BaseFicheFragment {
    public static final int REQUEST_CAMERA_CODE = 997;
    public static final int REQUEST_SELECT_FILE_CODE = 996;
    private static final String STATE_ADD_PHOTO = "state:addPhoto";
    private static final String STATE_ADD_PHOTO_METHOD = "state:addPhotoMethod";
    private static final String STATE_CUSTOMER = "state:customer";
    private final int TCNOLENGHT;
    private FragmentCustomerNewBinding _binding;
    private boolean isPersonalCompany;
    private View lnPersonalCompany;
    private boolean mAddPhoto;
    private AddPhotoMethod mAddPhotoMethod;
    private CustomerNew mCustomerNew;
    private FichePropertyEditView mFchAddress1;
    private FichePropertyEditView mFchAddress2;
    private CheckBox mFchChkPersonalCompany;
    private FichePropertyTextView mFchCode;
    private FichePropertyEditView mFchCustomerName;
    private FichePropertyEditView mFchCustomerSurname;
    private FichePropertyEditView mFchEmail;
    private FichePropertyEditView mFchEmail2;
    private FichePropertyEditView mFchFax;
    private FichePropertyEditView mFchGroupCode;
    private FichePropertyTextView mFchIl;
    private FichePropertyTextView mFchIlce;
    private FichePropertyEditView mFchInChargeDefinition;
    private FichePropertyEditView mFchInChargeEmail;
    private EditText mFchInChargeTelCode;
    private EditText mFchInChargeTelNum;
    private FichePropertyEditView mFchIncharge;
    private FichePropertyEditView mFchIskonto;
    private FichePropertyEditView mFchName;
    private FichePropertyTextView mFchPayPlan;
    private FichePropertyTextView mFchPayType;
    private FichePropertyEditView mFchRelatedPerson;
    private FichePropertyEditView mFchSpeCode;
    private FichePropertyEditView mFchSpeCode2;
    private FichePropertyEditView mFchSpeCode3;
    private FichePropertyEditView mFchSpeCode4;
    private FichePropertyEditView mFchSpeCode5;
    private FichePropertyEditView mFchTCNo;
    private FichePropertyEditView mFchTaxNo;
    private FichePropertyEditView mFchTaxOffice;
    private FichePropertyEditView mFchTel1;
    private FichePropertyEditView mFchTel2;
    private FichePropertyTextView mFchUlke;
    private FichePropertyEditView mFchVade;
    private FichePropertyEditView mFchWarrantyCode;
    private FichePropertyEditView mFchZipCode;
    private final FicheMode mFicheMode;
    private ImageView mImgCustomer;
    private LinearLayout mLnCustomerHeader;
    private LinearLayout mLnCustomerInChargeHeader;
    public AlertDialogBuilder<?> mNewCustomerRequiredDialogBuilder;
    public AlertDialogBuilder<?> mPhotoAlertDialogBuilder;
    public AlertDialogBuilder<?> mPhotoDeleteDialogBuilder;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    public AlertDialogBuilder<?> myAlertDialogBuilder;
    private View personalCompanyDivider;
    private final ActivityResultLauncher<PickVisualMediaRequest> photoPickerLauncher;
    private final CustomerNewRepository repository;
    private MenuItem saveButton;
    private final CustomerNewViewModel viewModel;
    public static final Companion Companion = new Companion(null);
    private static final String CUSTOMER_NEW_TAG = "CUSTOMER_NEW_TAG." + CustomerNewFragment.class.getName();
    private static final String TAG = CustomerNewFragment.class.getName();

    /* compiled from: CustomerNewFragment.kt */
    public class WhenMappings {
        public static final int[] EnumSwitchMapping0;
        public static final int[] EnumSwitchMapping1;

        static {
            int[] iArr = new int[ErpType.values().length];
            try {
                iArr[ErpType.NETSIS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            EnumSwitchMapping0 = iArr;
            int[] iArr2 = new int[NewCustomerRequiredFields.values().length];
            try {
                iArr2[NewCustomerRequiredFields.TITLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr2[NewCustomerRequiredFields.f1173ID.ordinal()] = 2;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[NewCustomerRequiredFields.TAXNO.ordinal()] = 3;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[NewCustomerRequiredFields.TAXOFFICE.ordinal()] = 4;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[NewCustomerRequiredFields.TELNO1.ordinal()] = 5;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[NewCustomerRequiredFields.TELNO2.ordinal()] = 6;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[NewCustomerRequiredFields.EMAIL.ordinal()] = 7;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[NewCustomerRequiredFields.COUNTRY.ordinal()] = 8;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr2[NewCustomerRequiredFields.PROVINCE.ordinal()] = 9;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr2[NewCustomerRequiredFields.DISTRICT.ordinal()] = 10;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr2[NewCustomerRequiredFields.FAXNO.ordinal()] = 11;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr2[NewCustomerRequiredFields.ADDRESS1.ordinal()] = 12;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr2[NewCustomerRequiredFields.ADDRESS2.ordinal()] = 13;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr2[NewCustomerRequiredFields.ZIPCODE.ordinal()] = 14;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr2[NewCustomerRequiredFields.EXPIRY.ordinal()] = 15;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr2[NewCustomerRequiredFields.DISCOUNTRATE.ordinal()] = 16;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr2[NewCustomerRequiredFields.PAYMENTPLAN.ordinal()] = 17;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr2[NewCustomerRequiredFields.PAYMENTTYPE.ordinal()] = 18;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr2[NewCustomerRequiredFields.RELATEDPERSON.ordinal()] = 19;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr2[NewCustomerRequiredFields.RELATEDTITLE.ordinal()] = 20;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                iArr2[NewCustomerRequiredFields.RELATEDTELNO.ordinal()] = 21;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                iArr2[NewCustomerRequiredFields.RELATEDEMAIL.ordinal()] = 22;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                iArr2[NewCustomerRequiredFields.SPECIALCODE1.ordinal()] = 23;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                iArr2[NewCustomerRequiredFields.SPECIALCODE2.ordinal()] = 24;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                iArr2[NewCustomerRequiredFields.SPECIALCODE3.ordinal()] = 25;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                iArr2[NewCustomerRequiredFields.SPECIALCODE4.ordinal()] = 26;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                iArr2[NewCustomerRequiredFields.SPECIALCODE5.ordinal()] = 27;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                iArr2[NewCustomerRequiredFields.WARRANTYCODE.ordinal()] = 28;
            } catch (NoSuchFieldError unused29) {
            }
            EnumSwitchMapping1 = iArr2;
        }
    }

    public CustomerNewFragment() {
        CustomerNewRepository customerNewRepository = new CustomerNewRepository();
        this.repository = customerNewRepository;
        this.viewModel = new CustomerNewViewModel(customerNewRepository);
        this.TCNOLENGHT = 11;
        this.mFicheMode = FicheMode.NEW;
        ActivityResultLauncher<PickVisualMediaRequest> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback() { // from class: com.proje.mobilesales.features.customer.view.newadd.CustomerNewFragmentExternalSyntheticLambda13
            @Override // androidx.activity.result.ActivityResultCallback
            public void onActivityResult(Object obj) {
                CustomerNewFragment.photoPickerLauncherlambda19(CustomerNewFragment.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.photoPickerLauncher = registerForActivityResult;
    }

    private FragmentCustomerNewBinding getBinding() {
        FragmentCustomerNewBinding fragmentCustomerNewBinding = this._binding;
        Intrinsics.checkNotNull(fragmentCustomerNewBinding);
        return fragmentCustomerNewBinding;
    }

    public AlertDialogBuilder<?> getMyAlertDialogBuilder() {
        AlertDialogBuilder<?> alertDialogBuilder = this.myAlertDialogBuilder;
        if (alertDialogBuilder != null) {
            return alertDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("myAlertDialogBuilder");
        return null;
    }

    public void setMyAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        Intrinsics.checkNotNullParameter(alertDialogBuilder, "<set-?>");
        this.myAlertDialogBuilder = alertDialogBuilder;
    }

    public AlertDialogBuilder<?> getMPhotoAlertDialogBuilder() {
        AlertDialogBuilder<?> alertDialogBuilder = this.mPhotoAlertDialogBuilder;
        if (alertDialogBuilder != null) {
            return alertDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mPhotoAlertDialogBuilder");
        return null;
    }

    public void setMPhotoAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        Intrinsics.checkNotNullParameter(alertDialogBuilder, "<set-?>");
        this.mPhotoAlertDialogBuilder = alertDialogBuilder;
    }

    public AlertDialogBuilder<?> getMPhotoDeleteDialogBuilder() {
        AlertDialogBuilder<?> alertDialogBuilder = this.mPhotoDeleteDialogBuilder;
        if (alertDialogBuilder != null) {
            return alertDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mPhotoDeleteDialogBuilder");
        return null;
    }

    public void setMPhotoDeleteDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        Intrinsics.checkNotNullParameter(alertDialogBuilder, "<set-?>");
        this.mPhotoDeleteDialogBuilder = alertDialogBuilder;
    }

    public AlertDialogBuilder<?> getMNewCustomerRequiredDialogBuilder() {
        AlertDialogBuilder<?> alertDialogBuilder = this.mNewCustomerRequiredDialogBuilder;
        if (alertDialogBuilder != null) {
            return alertDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mNewCustomerRequiredDialogBuilder");
        return null;
    }

    public void setMNewCustomerRequiredDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        Intrinsics.checkNotNullParameter(alertDialogBuilder, "<set-?>");
        this.mNewCustomerRequiredDialogBuilder = alertDialogBuilder;
    }

    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        if (progressDialogBuilder != null) {
            return progressDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
        return null;
    }

    public void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        Intrinsics.checkNotNullParameter(progressDialogBuilder, "<set-?>");
        this.mProgressDialogBuilder = progressDialogBuilder;
    }

    @Override // com.proje.mobilesales.core.base.BaseFicheFragment, com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        Context requireContext = requireContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMProgressDialogBuilder(new ProgressDialogBuilder.Impl(requireContext, (BaseInjectableActivity) activity));
        Context requireContext2 = requireContext();
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMyAlertDialogBuilder(new AlertDialogBuilder.Impl(requireContext2, (BaseInjectableActivity) activity2));
        Context requireContext3 = requireContext();
        Activity activity3 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMPhotoAlertDialogBuilder(new AlertDialogBuilder.Impl(requireContext3, (BaseInjectableActivity) activity3));
        Context requireContext4 = requireContext();
        Activity activity4 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity4, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMPhotoDeleteDialogBuilder(new AlertDialogBuilder.Impl(requireContext4, (BaseInjectableActivity) activity4));
        Context requireContext5 = requireContext();
        Activity activity5 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity5, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMNewCustomerRequiredDialogBuilder(new AlertDialogBuilder.Impl(requireContext5, (BaseInjectableActivity) activity5));
        setHasOptionsMenu(true);
        if (bundle != null) {
            this.mCustomerNew = bundle.getParcelable(STATE_CUSTOMER);
            this.mAddPhotoMethod = AddPhotoMethod.Companion.fromAddPhotoMethod(bundle.getInt(STATE_ADD_PHOTO_METHOD, 0));
            this.mAddPhoto = bundle.getBoolean(STATE_ADD_PHOTO, false);
            return;
        }
        Bundle arguments = getArguments();
        Intrinsics.checkNotNull(arguments);
        this.mCustomerNew = arguments.getParcelable(CustomerNewActivity.EXTRA_CUSTOMER);
        this.mAddPhotoMethod = AddPhotoMethod.GALLERY;
        Bundle arguments2 = getArguments();
        Intrinsics.checkNotNull(arguments2);
        this.mAddPhoto = arguments2.getBoolean(CustomerNewActivity.EXTRA_ADD_PHOTO, false);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentCustomerNewBinding.inflate(inflater, viewGroup, false);
        this.mLnCustomerHeader = getBinding().lnCustomerHeader;
        this.mFchCode = getBinding().fchCode;
        this.mFchName = getBinding().fchName;
        this.mFchSpeCode = getBinding().fchSpeCode;
        this.mFchSpeCode2 = getBinding().fchSpeCode2;
        this.mFchSpeCode3 = getBinding().fchSpeCode3;
        this.mFchSpeCode4 = getBinding().fchSpeCode4;
        this.mFchSpeCode5 = getBinding().fchSpeCode5;
        this.mFchGroupCode = getBinding().fchGroupCode;
        this.mFchWarrantyCode = getBinding().fchWarrantyCode;
        this.mFchRelatedPerson = getBinding().fchRelatedPerson;
        this.mFchTaxOffice = getBinding().fchTaxOffice;
        this.mFchTaxNo = getBinding().fchTaxNo;
        this.mFchTCNo = getBinding().fchIdNo;
        this.mFchChkPersonalCompany = getBinding().fchPersonalCompany;
        this.lnPersonalCompany = getBinding().lnPersonalCompany;
        this.personalCompanyDivider = getBinding().personalCompanyDivider;
        this.mFchCustomerName = getBinding().fchCustomerName;
        this.mFchCustomerSurname = getBinding().fchCustomerSurname;
        this.mFchEmail = getBinding().fchEmail;
        this.mFchEmail2 = getBinding().fchEmail2;
        this.mFchTel1 = getBinding().fchPhone1;
        this.mFchTel2 = getBinding().fchPhone2;
        this.mFchFax = getBinding().fchFax;
        this.mFchAddress1 = getBinding().fchAddress1;
        this.mFchAddress2 = getBinding().fchAddress2;
        this.mFchZipCode = getBinding().fchZipCode;
        this.mFchPayPlan = getBinding().fchPayPlan;
        this.mFchPayType = getBinding().fchPayType;
        this.mImgCustomer = getBinding().imgCustomer;
        this.mFchVade = getBinding().fchExpiry;
        this.mFchIskonto = getBinding().fchDiscount;
        this.mFchIl = getBinding().fchProvince;
        this.mFchIlce = getBinding().fchDistrict;
        this.mLnCustomerInChargeHeader = getBinding().lnCustomerInchargeHeader;
        this.mFchIncharge = getBinding().fchInchargeName;
        this.mFchInChargeDefinition = getBinding().fchInchargeDefinition;
        this.mFchInChargeEmail = getBinding().fchInchargeEmail;
        this.mFchInChargeTelCode = getBinding().edtFchInchargeTelCode;
        this.mFchInChargeTelNum = getBinding().edtFchInchargeTelNum;
        this.mFchUlke = getBinding().fchCountry;
        LinearLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        if (!this.mAddPhoto) {
            FicheMode ficheMode = this.mFicheMode;
            FichePropertyEditView fichePropertyEditView = this.mFchName;
            Intrinsics.checkNotNull(fichePropertyEditView);
            EditText edtValue = fichePropertyEditView.getEdtValue();
            CustomerNew customerNew = this.mCustomerNew;
            Intrinsics.checkNotNull(customerNew);
            createEditTextAddTextChangedListener(ficheMode, edtValue, customerNew.getName());
            FicheMode ficheMode2 = this.mFicheMode;
            FichePropertyEditView fichePropertyEditView2 = this.mFchSpeCode;
            Intrinsics.checkNotNull(fichePropertyEditView2);
            EditText edtValue2 = fichePropertyEditView2.getEdtValue();
            CustomerNew customerNew2 = this.mCustomerNew;
            Intrinsics.checkNotNull(customerNew2);
            createEditTextAddTextChangedListener(ficheMode2, edtValue2, customerNew2.getSpeCode());
            FicheMode ficheMode3 = this.mFicheMode;
            FichePropertyEditView fichePropertyEditView3 = this.mFchSpeCode2;
            Intrinsics.checkNotNull(fichePropertyEditView3);
            EditText edtValue3 = fichePropertyEditView3.getEdtValue();
            CustomerNew customerNew3 = this.mCustomerNew;
            Intrinsics.checkNotNull(customerNew3);
            createEditTextAddTextChangedListener(ficheMode3, edtValue3, customerNew3.getSpeCode2());
            FicheMode ficheMode4 = this.mFicheMode;
            FichePropertyEditView fichePropertyEditView4 = this.mFchSpeCode3;
            Intrinsics.checkNotNull(fichePropertyEditView4);
            EditText edtValue4 = fichePropertyEditView4.getEdtValue();
            CustomerNew customerNew4 = this.mCustomerNew;
            Intrinsics.checkNotNull(customerNew4);
            createEditTextAddTextChangedListener(ficheMode4, edtValue4, customerNew4.getSpeCode3());
            FicheMode ficheMode5 = this.mFicheMode;
            FichePropertyEditView fichePropertyEditView5 = this.mFchSpeCode4;
            Intrinsics.checkNotNull(fichePropertyEditView5);
            EditText edtValue5 = fichePropertyEditView5.getEdtValue();
            CustomerNew customerNew5 = this.mCustomerNew;
            Intrinsics.checkNotNull(customerNew5);
            createEditTextAddTextChangedListener(ficheMode5, edtValue5, customerNew5.getSpeCode4());
            FicheMode ficheMode6 = this.mFicheMode;
            FichePropertyEditView fichePropertyEditView6 = this.mFchSpeCode5;
            Intrinsics.checkNotNull(fichePropertyEditView6);
            EditText edtValue6 = fichePropertyEditView6.getEdtValue();
            CustomerNew customerNew6 = this.mCustomerNew;
            Intrinsics.checkNotNull(customerNew6);
            createEditTextAddTextChangedListener(ficheMode6, edtValue6, customerNew6.getSpeCode5());
            FicheMode ficheMode7 = this.mFicheMode;
            FichePropertyEditView fichePropertyEditView7 = this.mFchGroupCode;
            Intrinsics.checkNotNull(fichePropertyEditView7);
            LinearLayout lnFiche = fichePropertyEditView7.getLnFiche();
            FichePropertyEditView fichePropertyEditView8 = this.mFchGroupCode;
            Intrinsics.checkNotNull(fichePropertyEditView8);
            EditText edtValue7 = fichePropertyEditView8.getEdtValue();
            CustomerNew customerNew7 = this.mCustomerNew;
            Intrinsics.checkNotNull(customerNew7);
            FicheStringProp groupCode = customerNew7.getGroupCode();
            ErpType erpType = this.viewModel.erpType();
            ErpType erpType2 = ErpType.NETSIS;
            createEditTextAddTextChangedListener(ficheMode7, lnFiche, edtValue7, groupCode, erpType == erpType2);
            FicheMode ficheMode8 = this.mFicheMode;
            FichePropertyEditView fichePropertyEditView9 = this.mFchWarrantyCode;
            Intrinsics.checkNotNull(fichePropertyEditView9);
            LinearLayout lnFiche2 = fichePropertyEditView9.getLnFiche();
            FichePropertyEditView fichePropertyEditView10 = this.mFchWarrantyCode;
            Intrinsics.checkNotNull(fichePropertyEditView10);
            EditText edtValue8 = fichePropertyEditView10.getEdtValue();
            CustomerNew customerNew8 = this.mCustomerNew;
            Intrinsics.checkNotNull(customerNew8);
            FicheStringProp warrantyCode = customerNew8.getWarrantyCode();
            ErpType erpType3 = this.viewModel.erpType();
            ErpType erpType4 = ErpType.TIGER;
            createEditTextAddTextChangedListener(ficheMode8, lnFiche2, edtValue8, warrantyCode, erpType3 == erpType4);
            FicheMode ficheMode9 = this.mFicheMode;
            FichePropertyEditView fichePropertyEditView11 = this.mFchRelatedPerson;
            Intrinsics.checkNotNull(fichePropertyEditView11);
            LinearLayout lnFiche3 = fichePropertyEditView11.getLnFiche();
            FichePropertyEditView fichePropertyEditView12 = this.mFchRelatedPerson;
            Intrinsics.checkNotNull(fichePropertyEditView12);
            EditText edtValue9 = fichePropertyEditView12.getEdtValue();
            CustomerNew customerNew9 = this.mCustomerNew;
            Intrinsics.checkNotNull(customerNew9);
            createEditTextAddTextChangedListener(ficheMode9, lnFiche3, edtValue9, customerNew9.getRelatedPerson(), this.viewModel.erpType() == erpType4);
            FicheMode ficheMode10 = this.mFicheMode;
            FichePropertyEditView fichePropertyEditView13 = this.mFchTaxOffice;
            Intrinsics.checkNotNull(fichePropertyEditView13);
            EditText edtValue10 = fichePropertyEditView13.getEdtValue();
            CustomerNew customerNew10 = this.mCustomerNew;
            Intrinsics.checkNotNull(customerNew10);
            createEditTextAddTextChangedListener(ficheMode10, edtValue10, customerNew10.getTaxOffice());
            FicheMode ficheMode11 = this.mFicheMode;
            FichePropertyEditView fichePropertyEditView14 = this.mFchTaxNo;
            Intrinsics.checkNotNull(fichePropertyEditView14);
            EditText edtValue11 = fichePropertyEditView14.getEdtValue();
            CustomerNew customerNew11 = this.mCustomerNew;
            Intrinsics.checkNotNull(customerNew11);
            createEditTextAddTextChangedListener(ficheMode11, edtValue11, customerNew11.getTaxNo());
            FicheMode ficheMode12 = this.mFicheMode;
            FichePropertyEditView fichePropertyEditView15 = this.mFchCustomerName;
            Intrinsics.checkNotNull(fichePropertyEditView15);
            LinearLayout lnFiche4 = fichePropertyEditView15.getLnFiche();
            FichePropertyEditView fichePropertyEditView16 = this.mFchCustomerName;
            Intrinsics.checkNotNull(fichePropertyEditView16);
            EditText edtValue12 = fichePropertyEditView16.getEdtValue();
            CustomerNew customerNew12 = this.mCustomerNew;
            Intrinsics.checkNotNull(customerNew12);
            createEditTextAddTextChangedListener(ficheMode12, lnFiche4, edtValue12, customerNew12.getCustomerName(), this.viewModel.erpType() == erpType4);
            FicheMode ficheMode13 = this.mFicheMode;
            FichePropertyEditView fichePropertyEditView17 = this.mFchCustomerSurname;
            Intrinsics.checkNotNull(fichePropertyEditView17);
            LinearLayout lnFiche5 = fichePropertyEditView17.getLnFiche();
            FichePropertyEditView fichePropertyEditView18 = this.mFchCustomerSurname;
            Intrinsics.checkNotNull(fichePropertyEditView18);
            EditText edtValue13 = fichePropertyEditView18.getEdtValue();
            CustomerNew customerNew13 = this.mCustomerNew;
            Intrinsics.checkNotNull(customerNew13);
            createEditTextAddTextChangedListener(ficheMode13, lnFiche5, edtValue13, customerNew13.getCustomerSurname(), this.viewModel.erpType() == erpType4);
            FicheMode ficheMode14 = mFicheMode;
            final FichePropertyEditView fichePropertyEditView19 = mFchEmail;
            Intrinsics.checkNotNull(fichePropertyEditView19);
            final EditText edtValue14 = fichePropertyEditView19.getEdtValue();
            final CustomerNew customerNew14 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew14);
            this.createEditTextAddTextChangedListener(ficheMode14, edtValue14, customerNew14.getEmail());
            final FicheMode ficheMode15 = mFicheMode;
            final FichePropertyEditView fichePropertyEditView20 = mFchEmail2;
            Intrinsics.checkNotNull(fichePropertyEditView20);
            final LinearLayout lnFiche6 = fichePropertyEditView20.getLnFiche();
            final FichePropertyEditView fichePropertyEditView21 = mFchEmail2;
            Intrinsics.checkNotNull(fichePropertyEditView21);
            final EditText edtValue15 = fichePropertyEditView21.getEdtValue();
            final CustomerNew customerNew15 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew15);
            this.createEditTextAddTextChangedListener(ficheMode15, lnFiche6, edtValue15, customerNew15.getEmail2(), viewModel.erpType() == erpType4);
            final FicheMode ficheMode16 = mFicheMode;
            final FichePropertyEditView fichePropertyEditView22 = mFchTel1;
            Intrinsics.checkNotNull(fichePropertyEditView22);
            final EditText edtValue16 = fichePropertyEditView22.getEdtValue();
            final CustomerNew customerNew16 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew16);
            this.createEditTextAddTextChangedListener(ficheMode16, edtValue16, customerNew16.getTel1());
            final FicheMode ficheMode17 = mFicheMode;
            final FichePropertyEditView fichePropertyEditView23 = mFchTel2;
            Intrinsics.checkNotNull(fichePropertyEditView23);
            final EditText edtValue17 = fichePropertyEditView23.getEdtValue();
            final CustomerNew customerNew17 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew17);
            this.createEditTextAddTextChangedListener(ficheMode17, edtValue17, customerNew17.getTel2());
            final FicheMode ficheMode18 = mFicheMode;
            final FichePropertyEditView fichePropertyEditView24 = mFchFax;
            Intrinsics.checkNotNull(fichePropertyEditView24);
            final EditText edtValue18 = fichePropertyEditView24.getEdtValue();
            final CustomerNew customerNew18 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew18);
            this.createEditTextAddTextChangedListener(ficheMode18, edtValue18, customerNew18.getFax());
            final FicheMode ficheMode19 = mFicheMode;
            final FichePropertyEditView fichePropertyEditView25 = mFchAddress1;
            Intrinsics.checkNotNull(fichePropertyEditView25);
            final EditText edtValue19 = fichePropertyEditView25.getEdtValue();
            final CustomerNew customerNew19 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew19);
            this.createEditTextAddTextChangedListener(ficheMode19, edtValue19, customerNew19.getAddress1());
            final FicheMode ficheMode20 = mFicheMode;
            final FichePropertyEditView fichePropertyEditView26 = mFchAddress2;
            Intrinsics.checkNotNull(fichePropertyEditView26);
            final EditText edtValue20 = fichePropertyEditView26.getEdtValue();
            final CustomerNew customerNew20 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew20);
            this.createEditTextAddTextChangedListener(ficheMode20, edtValue20, customerNew20.getAddress2());
            final FicheMode ficheMode21 = mFicheMode;
            final FichePropertyEditView fichePropertyEditView27 = mFchZipCode;
            Intrinsics.checkNotNull(fichePropertyEditView27);
            final EditText edtValue21 = fichePropertyEditView27.getEdtValue();
            final CustomerNew customerNew21 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew21);
            this.createEditTextAddTextChangedListener(ficheMode21, edtValue21, customerNew21.getZipCode());
            final FicheMode ficheMode22 = mFicheMode;
            final FichePropertyTextView fichePropertyTextView = mFchPayPlan;
            Intrinsics.checkNotNull(fichePropertyTextView);
            final LinearLayout lnFiche7 = fichePropertyTextView.getLnFiche();
            final FichePropertyTextView fichePropertyTextView2 = mFchPayPlan;
            Intrinsics.checkNotNull(fichePropertyTextView2);
            final TextView txtValue = fichePropertyTextView2.getTxtValue();
            final CustomerNew customerNew22 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew22);
            this.createSingleAlertCursorSales(ficheMode22, lnFiche7, txtValue, customerNew22.getPayPlan(), R.string.str_payment, R.string.qry_pay_plan, R.string.column_code);
            final FicheMode ficheMode23 = mFicheMode;
            final FichePropertyEditView fichePropertyEditView28 = mFchTCNo;
            Intrinsics.checkNotNull(fichePropertyEditView28);
            final LinearLayout lnFiche8 = fichePropertyEditView28.getLnFiche();
            final FichePropertyEditView fichePropertyEditView29 = mFchTCNo;
            Intrinsics.checkNotNull(fichePropertyEditView29);
            final EditText edtValue22 = fichePropertyEditView29.getEdtValue();
            final CustomerNew customerNew23 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew23);
            this.createEditTextAddTextChangedListener(ficheMode23, lnFiche8, edtValue22, customerNew23.getTCNo(), true, 2);
            final BaseFicheFragment.OnCheckBoxClickInterface onCheckBoxClickInterface = new BaseFicheFragment.OnCheckBoxClickInterface() { // from class: com.proje.mobilesales.features.customer.view.newadd.CustomerNewFragmentExternalSyntheticLambda0
                @Override // com.proje.mobilesales.core.base.BaseFicheFragment.OnCheckBoxClickInterface
                public void onCheckBoxClicked(final boolean z) {
                    onViewCreatedlambda0(CustomerNewFragment.this, z);
                }
            };
            final FicheMode ficheMode24 = mFicheMode;
            final View view2 = lnPersonalCompany;
            final CheckBox checkBox = mFchChkPersonalCompany;
            final CustomerNew customerNew24 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew24);
            this.createCheckboxSetCheckedChangeListener(ficheMode24, view2, checkBox, customerNew24.getPersonalCompany(), viewModel.erpType() == erpType4, false, true, onCheckBoxClickInterface);
            final FicheMode ficheMode25 = mFicheMode;
            final FichePropertyTextView fichePropertyTextView3 = mFchPayType;
            Intrinsics.checkNotNull(fichePropertyTextView3);
            final LinearLayout lnFiche9 = fichePropertyTextView3.getLnFiche();
            final FichePropertyTextView fichePropertyTextView4 = mFchPayType;
            Intrinsics.checkNotNull(fichePropertyTextView4);
            final TextView txtValue2 = fichePropertyTextView4.getTxtValue();
            final CustomerNew customerNew25 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew25);
            this.createSingleAlertCursorSalesArray(ficheMode25, lnFiche9, txtValue2, customerNew25.getPayType(), viewModel.erpType() == erpType2, R.string.str_payment_type, R.array.array_fiche_netsis_paytype, true);
            final FicheMode ficheMode26 = mFicheMode;
            final FichePropertyEditView fichePropertyEditView30 = mFchVade;
            Intrinsics.checkNotNull(fichePropertyEditView30);
            final LinearLayout lnFiche10 = fichePropertyEditView30.getLnFiche();
            final FichePropertyEditView fichePropertyEditView31 = mFchVade;
            Intrinsics.checkNotNull(fichePropertyEditView31);
            final EditText edtValue23 = fichePropertyEditView31.getEdtValue();
            final CustomerNew customerNew26 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew26);
            this.createEditTextAddTextChangedListener(ficheMode26, lnFiche10, edtValue23, customerNew26.getVade(), viewModel.erpType() == erpType2, 2);
            final FicheMode ficheMode27 = mFicheMode;
            final FichePropertyEditView fichePropertyEditView32 = mFchIskonto;
            Intrinsics.checkNotNull(fichePropertyEditView32);
            final LinearLayout lnFiche11 = fichePropertyEditView32.getLnFiche();
            final FichePropertyEditView fichePropertyEditView33 = mFchIskonto;
            Intrinsics.checkNotNull(fichePropertyEditView33);
            final EditText edtValue24 = fichePropertyEditView33.getEdtValue();
            final CustomerNew customerNew27 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew27);
            this.createEditTextAddTextChangedListener(ficheMode27, lnFiche11, edtValue24, customerNew27.getIskontoOran(), viewModel.erpType() == erpType2, 8194);
            final FicheMode ficheMode28 = mFicheMode;
            final FichePropertyTextView fichePropertyTextView5 = mFchUlke;
            Intrinsics.checkNotNull(fichePropertyTextView5);
            final LinearLayout lnFiche12 = fichePropertyTextView5.getLnFiche();
            Intrinsics.checkNotNullExpressionValue(lnFiche12, "getLnFiche(...)");
            final FichePropertyTextView fichePropertyTextView6 = mFchUlke;
            Intrinsics.checkNotNull(fichePropertyTextView6);
            final TextView txtValue3 = fichePropertyTextView6.getTxtValue();
            Intrinsics.checkNotNullExpressionValue(txtValue3, "getTxtValue(...)");
            final CustomerNew customerNew28 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew28);
            final FicheDiscountRefProp country = customerNew28.getCountry();
            final CustomerNew customerNew29 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew29);
            final FicheDiscountRefProp city = customerNew29.getCity();
            final FichePropertyTextView fichePropertyTextView7 = mFchIl;
            Intrinsics.checkNotNull(fichePropertyTextView7);
            final LinearLayout lnFiche13 = fichePropertyTextView7.getLnFiche();
            Intrinsics.checkNotNullExpressionValue(lnFiche13, "getLnFiche(...)");
            final FichePropertyTextView fichePropertyTextView8 = mFchIl;
            Intrinsics.checkNotNull(fichePropertyTextView8);
            final TextView txtValue4 = fichePropertyTextView8.getTxtValue();
            Intrinsics.checkNotNullExpressionValue(txtValue4, "getTxtValue(...)");
            final CustomerNew customerNew30 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew30);
            final FicheDiscountRefProp town = customerNew30.getTown();
            final FichePropertyTextView fichePropertyTextView9 = mFchIlce;
            Intrinsics.checkNotNull(fichePropertyTextView9);
            final LinearLayout lnFiche14 = fichePropertyTextView9.getLnFiche();
            Intrinsics.checkNotNullExpressionValue(lnFiche14, "getLnFiche(...)");
            final FichePropertyTextView fichePropertyTextView10 = mFchIlce;
            Intrinsics.checkNotNull(fichePropertyTextView10);
            final TextView txtValue5 = fichePropertyTextView10.getTxtValue();
            Intrinsics.checkNotNullExpressionValue(txtValue5, "getTxtValue(...)");
            this.createSingleAlertCursorCustomerCountry(ficheMode28, lnFiche12, txtValue3, country, city, lnFiche13, txtValue4, town, lnFiche14, txtValue5, R.string.str_country, R.string.str_country_not_found, R.string.qry_country);
            final FicheMode ficheMode29 = mFicheMode;
            final FichePropertyTextView fichePropertyTextView11 = mFchIl;
            Intrinsics.checkNotNull(fichePropertyTextView11);
            final LinearLayout lnFiche15 = fichePropertyTextView11.getLnFiche();
            Intrinsics.checkNotNullExpressionValue(lnFiche15, "getLnFiche(...)");
            final FichePropertyTextView fichePropertyTextView12 = mFchIl;
            Intrinsics.checkNotNull(fichePropertyTextView12);
            final TextView txtValue6 = fichePropertyTextView12.getTxtValue();
            Intrinsics.checkNotNullExpressionValue(txtValue6, "getTxtValue(...)");
            final CustomerNew customerNew31 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew31);
            final FicheDiscountRefProp city2 = customerNew31.getCity();
            final CustomerNew customerNew32 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew32);
            final FicheDiscountRefProp town2 = customerNew32.getTown();
            final FichePropertyTextView fichePropertyTextView13 = mFchIlce;
            Intrinsics.checkNotNull(fichePropertyTextView13);
            final LinearLayout lnFiche16 = fichePropertyTextView13.getLnFiche();
            Intrinsics.checkNotNullExpressionValue(lnFiche16, "getLnFiche(...)");
            final FichePropertyTextView fichePropertyTextView14 = mFchIl;
            Intrinsics.checkNotNull(fichePropertyTextView14);
            final TextView txtValue7 = fichePropertyTextView14.getTxtValue();
            Intrinsics.checkNotNullExpressionValue(txtValue7, "getTxtValue(...)");
            this.createSingleAlertCursorCustomerCity(ficheMode29, lnFiche15, txtValue6, city2, town2, lnFiche16, txtValue7, R.string.str_city, R.string.str_city_not_found, R.string.qry_city);
            final FicheMode ficheMode30 = mFicheMode;
            final FichePropertyTextView fichePropertyTextView15 = mFchIlce;
            Intrinsics.checkNotNull(fichePropertyTextView15);
            final LinearLayout lnFiche17 = fichePropertyTextView15.getLnFiche();
            Intrinsics.checkNotNullExpressionValue(lnFiche17, "getLnFiche(...)");
            final FichePropertyTextView fichePropertyTextView16 = mFchIlce;
            Intrinsics.checkNotNull(fichePropertyTextView16);
            final TextView txtValue8 = fichePropertyTextView16.getTxtValue();
            Intrinsics.checkNotNullExpressionValue(txtValue8, "getTxtValue(...)");
            final CustomerNew customerNew33 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew33);
            this.createSingleAlertCursorCustomerTown(ficheMode30, lnFiche17, txtValue8, customerNew33.getTown(), R.string.str_town, R.string.str_town_not_found, R.string.qry_town);
            if (viewModel.erpType() == erpType2) {
                final FicheMode ficheMode31 = mFicheMode;
                final FichePropertyEditView fichePropertyEditView34 = mFchIncharge;
                Intrinsics.checkNotNull(fichePropertyEditView34);
                final EditText edtValue25 = fichePropertyEditView34.getEdtValue();
                final CustomerNew customerNew34 = mCustomerNew;
                Intrinsics.checkNotNull(customerNew34);
                this.createEditTextAddTextChangedListener(ficheMode31, edtValue25, customerNew34.getInCharge());
                final FicheMode ficheMode32 = mFicheMode;
                final FichePropertyEditView fichePropertyEditView35 = mFchInChargeDefinition;
                Intrinsics.checkNotNull(fichePropertyEditView35);
                final EditText edtValue26 = fichePropertyEditView35.getEdtValue();
                final CustomerNew customerNew35 = mCustomerNew;
                Intrinsics.checkNotNull(customerNew35);
                this.createEditTextAddTextChangedListener(ficheMode32, edtValue26, customerNew35.getInChargeDefinition());
                final FicheMode ficheMode33 = mFicheMode;
                final FichePropertyEditView fichePropertyEditView36 = mFchInChargeEmail;
                Intrinsics.checkNotNull(fichePropertyEditView36);
                final EditText edtValue27 = fichePropertyEditView36.getEdtValue();
                final CustomerNew customerNew36 = mCustomerNew;
                Intrinsics.checkNotNull(customerNew36);
                this.createEditTextAddTextChangedListener(ficheMode33, edtValue27, customerNew36.getInChargeEmail());
                final FicheMode ficheMode34 = mFicheMode;
                final EditText editText = mFchInChargeTelNum;
                final CustomerNew customerNew37 = mCustomerNew;
                Intrinsics.checkNotNull(customerNew37);
                this.createEditTextAddTextChangedListener(ficheMode34, editText, customerNew37.getInChargeTel());
                final FicheMode ficheMode35 = mFicheMode;
                final EditText editText2 = mFchInChargeTelCode;
                final CustomerNew customerNew38 = mCustomerNew;
                Intrinsics.checkNotNull(customerNew38);
                this.createEditTextAddTextChangedListener(ficheMode35, editText2, customerNew38.getInChargeTelCode());
            }
        }
        final ImageView imageView = mImgCustomer;
        Intrinsics.checkNotNull(imageView);
        imageView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.proje.mobilesales.features.customer.view.newadd.CustomerNewFragmentExternalSyntheticLambda1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(final View view3) {
                final boolean onViewCreatedlambda3;
                onViewCreatedlambda3 = onViewCreatedlambda3(CustomerNewFragment.this, view3);
                return onViewCreatedlambda3;
            }
        });
        final ErpType erpType5 = viewModel.erpType();
        final ErpType erpType6 = ErpType.NETSIS;
        if (erpType5 == erpType6) {
            this.createSpeCodeButtonsForErpType(erpType6);
        } else {
            this.createSpeCodeButtonsForErpType(ErpType.TIGER);
        }
    }


    public static void onViewCreatedlambda0(final CustomerNewFragment this0, final boolean z) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (this0.viewModel.erpType() == ErpType.NETSIS) {
            return;
        }
        if (z) {
            this0.isPersonalCompany = true;
            final FichePropertyEditView fichePropertyEditView = this0.mFchTCNo;
            Intrinsics.checkNotNull(fichePropertyEditView);
            fichePropertyEditView.getLnFiche().setVisibility(0);
            final View view = this0.personalCompanyDivider;
            Intrinsics.checkNotNull(view);
            view.setVisibility(0);
            final FichePropertyEditView fichePropertyEditView2 = this0.mFchCustomerName;
            Intrinsics.checkNotNull(fichePropertyEditView2);
            fichePropertyEditView2.getLnFiche().setVisibility(0);
            final FichePropertyEditView fichePropertyEditView3 = this0.mFchCustomerSurname;
            Intrinsics.checkNotNull(fichePropertyEditView3);
            fichePropertyEditView3.getLnFiche().setVisibility(0);
            final FichePropertyEditView fichePropertyEditView4 = this0.mFchTaxNo;
            Intrinsics.checkNotNull(fichePropertyEditView4);
            fichePropertyEditView4.getLnFiche().setVisibility(8);
            return;
        }
        final FichePropertyEditView fichePropertyEditView5 = this0.mFchTCNo;
        Intrinsics.checkNotNull(fichePropertyEditView5);
        fichePropertyEditView5.getLnFiche().setVisibility(8);
        final FichePropertyEditView fichePropertyEditView6 = this0.mFchCustomerName;
        Intrinsics.checkNotNull(fichePropertyEditView6);
        fichePropertyEditView6.getLnFiche().setVisibility(8);
        final FichePropertyEditView fichePropertyEditView7 = this0.mFchCustomerSurname;
        Intrinsics.checkNotNull(fichePropertyEditView7);
        fichePropertyEditView7.getLnFiche().setVisibility(8);
        final FichePropertyEditView fichePropertyEditView8 = this0.mFchTaxNo;
        Intrinsics.checkNotNull(fichePropertyEditView8);
        fichePropertyEditView8.getLnFiche().setVisibility(0);
    }


    public static boolean onViewCreatedlambda3(CustomerNewFragment this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final CustomerNew customerNew = this0.mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        final FicheImageProp image = customerNew.getImage();
        Intrinsics.checkNotNull(image);
        if (null == image.getImageArray()) {
            return true;
        }
        this0.getMPhotoDeleteDialogBuilder().setTitle(R.string.str_remove_photo).setPositiveButton(R.string.str_yes, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.newadd.CustomerNewFragmentExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                onViewCreatedlambda3lambda1(CustomerNewFragment.this, dialogInterface, i2);
            }
        }).setNegativeButton(R.string.str_no, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.newadd.CustomerNewFragmentExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                onViewCreatedlambda3lambda2(dialogInterface, i2);
            }
        }).create().show();
        return true;
    }


    public static void onViewCreatedlambda3lambda1(final CustomerNewFragment this0, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
        final CustomerNew customerNew = this0.mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        final FicheImageProp image = customerNew.getImage();
        Intrinsics.checkNotNull(image);
        image.reset();
        this0.loadImageView(this0.mImgCustomer, null);
    }


    public static void onViewCreatedlambda3lambda2(final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    private void createSpeCodeButtonsForErpType(final ErpType erpType) {
        final int i2;
        String str = "SPECODE";
        if (1 == WhenMappings.EnumSwitchMapping0[erpType.ordinal()]) {
            final FichePropertyEditView fichePropertyEditView = mFchGroupCode;
            final String formatStringEnglish = ContextUtils.formatStringEnglish(R.string.net_qry_group_codes, "GROUP_CODE");
            Intrinsics.checkNotNullExpressionValue(formatStringEnglish, "formatStringEnglish(...)");
            this.createImageButtonForSpeCodes(fichePropertyEditView, formatStringEnglish, R.string.str_group_code);
            final FichePropertyEditView fichePropertyEditView2 = mFchSpeCode;
            i2 = R.string.qry_distinct_clcard_speCode_netsis;
            final String formatStringEnglish2 = ContextUtils.formatStringEnglish(R.string.qry_distinct_clcard_speCode_netsis, "SPECODE");
            Intrinsics.checkNotNullExpressionValue(formatStringEnglish2, "formatStringEnglish(...)");
            this.createImageButtonForSpeCodes(fichePropertyEditView2, formatStringEnglish2, R.string.str_customer_specode_1);
        } else {
            final FichePropertyEditView fichePropertyEditView3 = mFchWarrantyCode;
            final String formatStringEnglish3 = ContextUtils.formatStringEnglish(R.string.qry_customer_warranty, "SPECODE");
            Intrinsics.checkNotNullExpressionValue(formatStringEnglish3, "formatStringEnglish(...)");
            this.createImageButtonForSpeCodes(fichePropertyEditView3, formatStringEnglish3, R.string.str_warranty_code);
            final FichePropertyEditView fichePropertyEditView4 = mFchSpeCode;
            final StringBuilder sb = new StringBuilder();
            str = "SPETYP";
            sb.append("SPETYP");
            sb.append('1');
            final Object[] objArr = {sb.toString()};
            i2 = R.string.qry_distinct_clcard_speCode;
            final String formatStringEnglish4 = ContextUtils.formatStringEnglish(R.string.qry_distinct_clcard_speCode, objArr);
            Intrinsics.checkNotNullExpressionValue(formatStringEnglish4, "formatStringEnglish(...)");
            this.createImageButtonForSpeCodes(fichePropertyEditView4, formatStringEnglish4, R.string.str_customer_specode_1);
        }
        final FichePropertyEditView fichePropertyEditView5 = mFchSpeCode2;
        final String formatStringEnglish5 = ContextUtils.formatStringEnglish(i2, str + '2');
        Intrinsics.checkNotNullExpressionValue(formatStringEnglish5, "formatStringEnglish(...)");
        this.createImageButtonForSpeCodes(fichePropertyEditView5, formatStringEnglish5, R.string.str_customer_specode_2);
        final FichePropertyEditView fichePropertyEditView6 = mFchSpeCode3;
        final String formatStringEnglish6 = ContextUtils.formatStringEnglish(i2, str + '3');
        Intrinsics.checkNotNullExpressionValue(formatStringEnglish6, "formatStringEnglish(...)");
        this.createImageButtonForSpeCodes(fichePropertyEditView6, formatStringEnglish6, R.string.str_customer_specode_3);
        final FichePropertyEditView fichePropertyEditView7 = mFchSpeCode4;
        final String formatStringEnglish7 = ContextUtils.formatStringEnglish(i2, str + '4');
        Intrinsics.checkNotNullExpressionValue(formatStringEnglish7, "formatStringEnglish(...)");
        this.createImageButtonForSpeCodes(fichePropertyEditView7, formatStringEnglish7, R.string.str_customer_specode_4);
        final FichePropertyEditView fichePropertyEditView8 = mFchSpeCode5;
        final String formatStringEnglish8 = ContextUtils.formatStringEnglish(i2, str + '5');
        Intrinsics.checkNotNullExpressionValue(formatStringEnglish8, "formatStringEnglish(...)");
        this.createImageButtonForSpeCodes(fichePropertyEditView8, formatStringEnglish8, R.string.str_customer_specode_5);
    }

    private void createSingleAlertCursorCustomerCountry(final FicheMode ficheMode, final View view, TextView textView, FicheDiscountRefProp ficheDiscountRefProp, FicheDiscountRefProp ficheDiscountRefProp2, View view2, TextView textView2, FicheDiscountRefProp ficheDiscountRefProp3, View view3, TextView textView3, @StringRes int i2, @StringRes int i3, @StringRes int i4, String... strArr) {
        if (ficheMode == FicheMode.ANALYSE || null == ficheDiscountRefProp) {
            this.setViewDisabled(textView);
        } else {
            this.loadTextView(textView, ficheDiscountRefProp.toString());
            view.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.newadd.CustomerNewFragmentExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public void onClick(final View view4) {
                    createSingleAlertCursorCustomerCountrylambda6(CustomerNewFragment.this, i4, strArr, i3, i2, ficheDiscountRefProp, textView, ficheDiscountRefProp2, textView2, ficheDiscountRefProp3, textView3, view2, view3, view4);
                }
            });
        }
    }


    public static void createSingleAlertCursorCustomerCountrylambda6(CustomerNewFragment this0, final int i2, final String[] args, final int i3, final int i4, FicheDiscountRefProp ficheDiscountRefProp, TextView showView, FicheDiscountRefProp ficheDiscountRefProp2, TextView txtCity, FicheDiscountRefProp ficheDiscountRefProp3, TextView txtTown, View lnCity, View lnTown, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(showView, "showView");
        Intrinsics.checkNotNullParameter(txtCity, "txtCity");
        Intrinsics.checkNotNullParameter(txtTown, "txtTown");
        Intrinsics.checkNotNullParameter(lnCity, "lnCity");
        Intrinsics.checkNotNullParameter(lnTown, "lnTown");
        this0.getMyAlertDialogBuilder().dismiss();
        Cursor cursor = this0.getCursor(this0.getString(i2), Arrays.copyOf(args, args.length));
        if (null != cursor && 0 != cursor.getCount()) {
            this0.getMyAlertDialogBuilder().setTitle(StringUtils.catStringSpace(this0.getString(i4), this0.getString(R.string.str_select_text))).setSingleChoiceItems(cursor, ficheDiscountRefProp.getWhich(), this0.getString(R.string.column_name), new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.newadd.CustomerNewFragmentExternalSyntheticLambda16
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(final DialogInterface dialogInterface, final int i5) {
                    createSingleAlertCursorCustomerCountrylambda6lambda4(cursor, ficheDiscountRefProp, this0, showView, ficheDiscountRefProp2, txtCity, ficheDiscountRefProp3, txtTown, lnCity, lnTown, dialogInterface, i5);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.newadd.CustomerNewFragmentExternalSyntheticLambda17
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(final DialogInterface dialogInterface, final int i5) {
                    createSingleAlertCursorCustomerCountrylambda6lambda5(FicheDiscountRefProp.this, showView, ficheDiscountRefProp2, txtCity, ficheDiscountRefProp3, txtTown, this0, lnCity, lnTown, cursor, dialogInterface, i5);
                }
            }).create().show();
        } else if (-1 != i3) {
            Toast.makeText(this0.getActivity(), this0.getString(i3), 0).show();
        } else {
            Toast.makeText(this0.getActivity(), this0.getString(R.string.empty_text), 0).show();
        }
    }


    public static void createSingleAlertCursorCustomerCountrylambda6lambda4(final Cursor cursor, final FicheDiscountRefProp ficheDiscountRefProp, final CustomerNewFragment this0, final TextView showView, final FicheDiscountRefProp ficheDiscountRefProp2, final TextView txtCity, final FicheDiscountRefProp ficheDiscountRefProp3, final TextView txtTown, final View lnCity, final View lnTown, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(showView, "showView");
        Intrinsics.checkNotNullParameter(txtCity, "txtCity");
        Intrinsics.checkNotNullParameter(txtTown, "txtTown");
        Intrinsics.checkNotNullParameter(lnCity, "lnCity");
        Intrinsics.checkNotNullParameter(lnTown, "lnTown");
        if (cursor.moveToPosition(i2)) {
            ficheDiscountRefProp.setWhich(i2);
            ficheDiscountRefProp.setLogicalRef(cursor.getInt((int) cursor.getColumnIndex(this0.getString(com.proje.mobilesales.R.string.column_id))));
            FicheStringProp.setDefinition(cursor.getString((int) cursor.getColumnIndex(this0.getString(com.proje.mobilesales.R.string.column_name))));
            ficheDiscountRefProp.setCode(cursor.getString((int) cursor.getColumnIndex(this0.getString(com.proje.mobilesales.R.string.column_code))));
            showView.setText(ficheDiscountRefProp.getDefinition());
            if (null != ficheDiscountRefProp2) {
                ficheDiscountRefProp2.reset();
            }
            txtCity.setText(String.valueOf(ficheDiscountRefProp2));
            if (null != ficheDiscountRefProp3) {
                ficheDiscountRefProp3.reset();
            }
            txtTown.setText(String.valueOf(ficheDiscountRefProp3));
            this0.createSingleAlertCursorCustomerCity(this0.mFicheMode, lnCity, txtCity, ficheDiscountRefProp2, ficheDiscountRefProp3, lnTown, txtTown, R.string.str_city, R.string.str_city_not_found, R.string.qry_city, cursor.getString(cursor.getColumnIndex(this0.getString(R.string.column_country_code))));
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        dialogInterface.dismiss();
        if (null != ficheDiscountRefProp.getPropChangeListener()) {
            ficheDiscountRefProp.getPropChangeListener().onChange();
        }
    }


    public static void createSingleAlertCursorCustomerCountrylambda6lambda5(final FicheDiscountRefProp ficheDiscountRefProp, final TextView showView, final FicheDiscountRefProp ficheDiscountRefProp2, final TextView txtCity, final FicheDiscountRefProp ficheDiscountRefProp3, final TextView txtTown, final CustomerNewFragment this0, final View lnCity, final View lnTown, final Cursor cursor, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(showView, "showView");
        Intrinsics.checkNotNullParameter(txtCity, "txtCity");
        Intrinsics.checkNotNullParameter(txtTown, "txtTown");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(lnCity, "lnCity");
        Intrinsics.checkNotNullParameter(lnTown, "lnTown");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        ficheDiscountRefProp.reset();
        showView.setText(ficheDiscountRefProp.toString());
        if (null != ficheDiscountRefProp2) {
            ficheDiscountRefProp2.reset();
        }
        txtCity.setText(String.valueOf(ficheDiscountRefProp2));
        if (null != ficheDiscountRefProp3) {
            ficheDiscountRefProp3.reset();
        }
        txtTown.setText(String.valueOf(ficheDiscountRefProp3));
        this0.createSingleAlertCursorCustomerCity(this0.mFicheMode, lnCity, txtCity, ficheDiscountRefProp2, ficheDiscountRefProp3, lnTown, txtTown, com.proje.mobilesales.R.string.str_city, R.string.str_city_not_found, R.string.qry_city);
        this0.createSingleAlertCursorCustomerTown(this0.mFicheMode, lnTown, txtTown, ficheDiscountRefProp3, com.proje.mobilesales.R.string.str_town, R.string.str_town_not_found, R.string.qry_town);
        if (!cursor.isClosed()) {
            cursor.close();
        }
        dialog.dismiss();
    }

    private void createSingleAlertCursorCustomerCity(final FicheMode ficheMode, final View view, TextView textView, FicheDiscountRefProp ficheDiscountRefProp, FicheDiscountRefProp ficheDiscountRefProp2, View view2, TextView textView2, @StringRes int i2, @StringRes int i3, @StringRes int i4, String... strArr) {
        if (ficheMode == FicheMode.ANALYSE || null == ficheDiscountRefProp) {
            this.setViewDisabled(textView);
        } else {
            this.loadTextView(textView, ficheDiscountRefProp.toString());
            view.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.newadd.CustomerNewFragmentExternalSyntheticLambda10
                @Override // android.view.View.OnClickListener
                public void onClick(final View view3) {
                    createSingleAlertCursorCustomerCitylambda9(CustomerNewFragment.this, i4, strArr, i3, i2, ficheDiscountRefProp, textView, ficheDiscountRefProp2, textView2, view2, view3);
                }
            });
        }
    }


    public static void createSingleAlertCursorCustomerCitylambda9(CustomerNewFragment this0, final int i2, final String[] args, final int i3, final int i4, FicheDiscountRefProp ficheDiscountRefProp, TextView showView, FicheDiscountRefProp ficheDiscountRefProp2, TextView txtTown, View lnTown, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(showView, "showView");
        Intrinsics.checkNotNullParameter(txtTown, "txtTown");
        Intrinsics.checkNotNullParameter(lnTown, "lnTown");
        this0.getMyAlertDialogBuilder().dismiss();
        Cursor cursor = this0.getCursor(this0.getString(i2), Arrays.copyOf(args, args.length));
        if (null != cursor && 0 != cursor.getCount()) {
            this0.getMyAlertDialogBuilder().setTitle(StringUtils.catStringSpace(this0.getString(i4), this0.getString(R.string.str_select_text))).setSingleChoiceItems(cursor, ficheDiscountRefProp.getWhich(), this0.getString(R.string.column_name), new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.newadd.CustomerNewFragmentExternalSyntheticLambda11
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(final DialogInterface dialogInterface, final int i5) {
                    createSingleAlertCursorCustomerCitylambda9lambda7(cursor, ficheDiscountRefProp, this0, showView, ficheDiscountRefProp2, txtTown, lnTown, dialogInterface, i5);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.newadd.CustomerNewFragmentExternalSyntheticLambda12
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(final DialogInterface dialogInterface, final int i5) {
                    createSingleAlertCursorCustomerCitylambda9lambda8(FicheDiscountRefProp.this, showView, ficheDiscountRefProp2, txtTown, this0, lnTown, cursor, dialogInterface, i5);
                }
            }).create().show();
        } else if (-1 != i3) {
            Toast.makeText(this0.getActivity(), this0.getString(i3), 0).show();
        } else {
            Toast.makeText(this0.getActivity(), this0.getString(R.string.empty_text), 0).show();
        }
    }


    public static void createSingleAlertCursorCustomerCitylambda9lambda7(final Cursor cursor, final FicheDiscountRefProp ficheDiscountRefProp, final CustomerNewFragment this0, final TextView showView, final FicheDiscountRefProp ficheDiscountRefProp2, final TextView txtTown, final View lnTown, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(showView, "showView");
        Intrinsics.checkNotNullParameter(txtTown, "txtTown");
        Intrinsics.checkNotNullParameter(lnTown, "lnTown");
        if (cursor.moveToPosition(i2)) {
            ficheDiscountRefProp.setWhich(i2);
            ficheDiscountRefProp.setLogicalRef(cursor.getInt(cursor.getColumnIndex(this0.getString(R.string.column_id))));
            FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex(this0.getString(R.string.column_name))));
            ficheDiscountRefProp.setCode(cursor.getString(cursor.getColumnIndex(this0.getString(R.string.column_code))));
            showView.setText(ficheDiscountRefProp.getDefinition());
            if (null != ficheDiscountRefProp2) {
                ficheDiscountRefProp2.reset();
            }
            txtTown.setText(String.valueOf(ficheDiscountRefProp2));
            this0.createSingleAlertCursorCustomerTown(this0.mFicheMode, lnTown, txtTown, ficheDiscountRefProp2, R.string.str_town, R.string.str_town_not_found, R.string.qry_town, cursor.getString(cursor.getColumnIndex(this0.getString(R.string.column_logical_ref))));
        }
        cursor.close();
        dialogInterface.dismiss();
        if (null != ficheDiscountRefProp.getPropChangeListener()) {
            ficheDiscountRefProp.getPropChangeListener().onChange();
        }
    }


    public static void createSingleAlertCursorCustomerCitylambda9lambda8(final FicheDiscountRefProp ficheDiscountRefProp, final TextView showView, final FicheDiscountRefProp ficheDiscountRefProp2, final TextView txtTown, final CustomerNewFragment this0, final View lnTown, final Cursor cursor, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(showView, "showView");
        Intrinsics.checkNotNullParameter(txtTown, "txtTown");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(lnTown, "lnTown");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        ficheDiscountRefProp.reset();
        showView.setText(ficheDiscountRefProp.toString());
        if (null != ficheDiscountRefProp2) {
            ficheDiscountRefProp2.reset();
        }
        txtTown.setText(String.valueOf(ficheDiscountRefProp2));
        this0.createSingleAlertCursorCustomerTown(this0.mFicheMode, lnTown, txtTown, ficheDiscountRefProp2, R.string.str_town, R.string.str_town_not_found, R.string.qry_town);
        if (!cursor.isClosed()) {
            cursor.close();
        }
        dialog.dismiss();
    }

    private void createSingleAlertCursorCustomerTown(final FicheMode ficheMode, final View view, TextView textView, FicheDiscountRefProp ficheDiscountRefProp, @StringRes int i2, @StringRes int i3, @StringRes int i4, String... strArr) {
        if (ficheMode == FicheMode.ANALYSE || null == ficheDiscountRefProp) {
            this.setViewDisabled(textView);
        } else {
            this.loadTextView(textView, ficheDiscountRefProp.toString());
            view.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.newadd.CustomerNewFragmentExternalSyntheticLambda14
                @Override // android.view.View.OnClickListener
                public void onClick(final View view2) {
                    createSingleAlertCursorCustomerTownlambda12(CustomerNewFragment.this, i4, strArr, i3, i2, ficheDiscountRefProp, textView, view2);
                }
            });
        }
    }


    public static void createSingleAlertCursorCustomerTownlambda12(CustomerNewFragment this0, final int i2, final String[] args, final int i3, final int i4, FicheDiscountRefProp ficheDiscountRefProp, TextView showView, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(showView, "showView");
        this0.getMyAlertDialogBuilder().dismiss();
        Cursor cursor = this0.getCursor(this0.getString(i2), Arrays.copyOf(args, args.length));
        if (null != cursor && 0 != cursor.getCount()) {
            this0.getMyAlertDialogBuilder().setTitle(StringUtils.catStringSpace(this0.getString(i4), this0.getString(R.string.str_select_text))).setSingleChoiceItems(cursor, ficheDiscountRefProp.getWhich(), this0.getString(R.string.column_name), new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.newadd.CustomerNewFragmentExternalSyntheticLambda2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(final DialogInterface dialogInterface, final int i5) {
                    createSingleAlertCursorCustomerTownlambda12lambda10(cursor, ficheDiscountRefProp, this0, showView, dialogInterface, i5);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.newadd.CustomerNewFragmentExternalSyntheticLambda3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(final DialogInterface dialogInterface, final int i5) {
                    createSingleAlertCursorCustomerTownlambda12lambda11(FicheDiscountRefProp.this, showView, cursor, dialogInterface, i5);
                }
            }).create().show();
        } else if (-1 != i3) {
            Toast.makeText(this0.getActivity(), this0.getString(i3), 0).show();
        } else {
            Toast.makeText(this0.getActivity(), this0.getString(R.string.empty_text), 0).show();
        }
    }


    public static void createSingleAlertCursorCustomerTownlambda12lambda10(final Cursor cursor, final FicheDiscountRefProp ficheDiscountRefProp, final CustomerNewFragment this0, final TextView showView, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(showView, "showView");
        if (cursor.moveToPosition(i2)) {
            ficheDiscountRefProp.setWhich(i2);
            ficheDiscountRefProp.setLogicalRef(cursor.getInt(cursor.getColumnIndex(this0.getString(R.string.column_id))));
            FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex(this0.getString(R.string.column_name))));
            ficheDiscountRefProp.setCode(cursor.getString(cursor.getColumnIndex(this0.getString(R.string.column_code))));
            showView.setText(ficheDiscountRefProp.getDefinition());
        }
        cursor.close();
        dialogInterface.dismiss();
        if (null != ficheDiscountRefProp.getPropChangeListener()) {
            ficheDiscountRefProp.getPropChangeListener().onChange();
        }
    }


    public static void createSingleAlertCursorCustomerTownlambda12lambda11(final FicheDiscountRefProp ficheDiscountRefProp, final TextView showView, final Cursor cursor, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(showView, "showView");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        ficheDiscountRefProp.reset();
        showView.setText(ficheDiscountRefProp.toString());
        if (!cursor.isClosed()) {
            cursor.close();
        }
        dialog.dismiss();
    }

    private void createImageButtonForSpeCodes(final FichePropertyEditView fichePropertyEditView, final String str, @StringRes final int i2) {
        Intrinsics.checkNotNull(fichePropertyEditView);
        final EditText editText = fichePropertyEditView.findViewById(R.id.edt_fiche_property_edit);
        final LinearLayout linearLayout = fichePropertyEditView.findViewById(R.id.ln_fiche_property);
        editText.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 0.5f));
        final ImageButton imageButton = new ImageButton(this.getContext());
        imageButton.setImageResource(R.drawable.ic_magnify_black_18dp);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1, 0.05f);
        layoutParams.rightMargin = 5;
        imageButton.setBackgroundColor(0);
        imageButton.setLayoutParams(layoutParams);
        linearLayout.addView(imageButton);
        final FicheMode ficheMode = mFicheMode;
        Intrinsics.checkNotNull(editText);
        this.createImageSingleAlertCursorSales(ficheMode, imageButton, editText, true, i2, -1, str, R.string.column_code);
    }

    private void createImageSingleAlertCursorSales(final FicheMode ficheMode, final View view, EditText editText, final boolean z, @StringRes int i2, @StringRes int i3, String str, @StringRes int i4) {
        if (!z) {
            this.setViewVisibilityGone(view);
        } else if (ficheMode == FicheMode.ANALYSE) {
            this.setViewDisabled(editText);
        } else {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.newadd.CustomerNewFragmentExternalSyntheticLambda18
                @Override // android.view.View.OnClickListener
                public void onClick(final View view2) {
                    createImageSingleAlertCursorSaleslambda13(CustomerNewFragment.this, str, i3, i2, i4, editText, view2);
                }
            });
        }
    }


    public static void createImageSingleAlertCursorSaleslambda13(final CustomerNewFragment this0, final String query, final int i2, final int i3, final int i4, final EditText showView, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(showView, "showView");
        try {
            this0.getMyAlertDialogBuilder().dismiss();
            final Cursor cursor = this0.getCursor(query);
            if (this0.controlCustomer(cursor, i2)) {
                return;
            }
            final String string = this0.getString(i3);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            Intrinsics.checkNotNull(cursor);
            this0.createCursorAlertDialog(string, cursor, i4, showView);
        } catch (final Exception e2) {
            Log.e(CustomerNewFragment.TAG, "createSingleAlertCursorSales: ", e2);
        }
    }

    private void createCursorAlertDialog(final String str, Cursor cursor, final int i2, EditText editText) {
        this.getMyAlertDialogBuilder().setTitle(str).setSingleChoiceItems(cursor, -1, this.getString(i2), new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.newadd.CustomerNewFragmentExternalSyntheticLambda6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(final DialogInterface dialogInterface, final int i3) {
                createCursorAlertDialoglambda14(cursor, editText, this, dialogInterface, i3);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.newadd.CustomerNewFragmentExternalSyntheticLambda7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(final DialogInterface dialogInterface, final int i3) {
                createCursorAlertDialoglambda15(cursor, dialogInterface, i3);
            }
        }).create().show();
    }


    public static void createCursorAlertDialoglambda14(final Cursor mCursor, final EditText showView, final CustomerNewFragment this0, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(mCursor, "mCursor");
        Intrinsics.checkNotNullParameter(showView, "showView");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        if (mCursor.moveToPosition(i2)) {
            showView.setText(mCursor.getString(mCursor.getColumnIndex(this0.getString(R.string.column_code))));
            showView.requestFocus();
            showView.setSelection(showView.getText().length());
        }
        mCursor.close();
        dialog.dismiss();
    }


    public static void createCursorAlertDialoglambda15(final Cursor mCursor, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(mCursor, "mCursor");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        if (!mCursor.isClosed()) {
            mCursor.close();
        }
        dialog.dismiss();
    }

    private boolean controlCustomer(final Cursor cursor, final int i2) {
        if (null != cursor && 0 != cursor.getCount()) {
            return false;
        }
        if (-1 != i2) {
            Toast.makeText(this.getActivity(), this.getString(i2), 0).show();
            return true;
        }
        Toast.makeText(this.getActivity(), this.getString(R.string.empty_text), 0).show();
        return true;
    }

    @Override // com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(final Bundle bundle) {
        super.onActivityCreated(bundle);
        this.setVisible();
        this.load();
    }

    private void setVisible() {
        final LinearLayout lnFiche;
        if (mAddPhoto) {
            final LinearLayout linearLayout = mLnCustomerHeader;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setVisibility(8);
        }
        if (viewModel.erpType() == ErpType.NETSIS) {
            final LinearLayout linearLayout2 = mLnCustomerInChargeHeader;
            Intrinsics.checkNotNull(linearLayout2);
            linearLayout2.setVisibility(0);
            final View view = lnPersonalCompany;
            if (null != view) {
                view.setVisibility(8);
            }
        }
        if (viewModel.erpType() == ErpType.TIGER) {
            if (!isPersonalCompany) {
                final FichePropertyEditView fichePropertyEditView = mFchTCNo;
                final LinearLayout lnFiche2 = null != fichePropertyEditView ? fichePropertyEditView.getLnFiche() : null;
                if (null != lnFiche2) {
                    lnFiche2.setVisibility(8);
                }
                final FichePropertyEditView fichePropertyEditView2 = mFchCustomerName;
                final LinearLayout lnFiche3 = null != fichePropertyEditView2 ? fichePropertyEditView2.getLnFiche() : null;
                if (null != lnFiche3) {
                    lnFiche3.setVisibility(8);
                }
                final FichePropertyEditView fichePropertyEditView3 = mFchCustomerSurname;
                lnFiche = null != fichePropertyEditView3 ? fichePropertyEditView3.getLnFiche() : null;
                if (null == lnFiche) {
                    return;
                }
                lnFiche.setVisibility(8);
                return;
            }
            final FichePropertyEditView fichePropertyEditView4 = mFchTaxNo;
            lnFiche = null != fichePropertyEditView4 ? fichePropertyEditView4.getLnFiche() : null;
            if (null == lnFiche) {
                return;
            }
            lnFiche.setVisibility(8);
        }
    }

    @Override // com.proje.mobilesales.core.base.BaseFicheFragment
    protected void load() {
        if (!mAddPhoto) {
            final FichePropertyTextView fichePropertyTextView = mFchCode;
            Intrinsics.checkNotNull(fichePropertyTextView);
            final TextView txtValue = fichePropertyTextView.getTxtValue();
            final CustomerNew customerNew = mCustomerNew;
            Intrinsics.checkNotNull(customerNew);
            this.loadTextView(txtValue, String.valueOf(customerNew.getCode()));
            final FichePropertyEditView fichePropertyEditView = mFchName;
            Intrinsics.checkNotNull(fichePropertyEditView);
            final EditText edtValue = fichePropertyEditView.getEdtValue();
            final CustomerNew customerNew2 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew2);
            this.loadTextView(edtValue, String.valueOf(customerNew2.getName()));
            final FichePropertyEditView fichePropertyEditView2 = mFchSpeCode;
            Intrinsics.checkNotNull(fichePropertyEditView2);
            final EditText edtValue2 = fichePropertyEditView2.getEdtValue();
            final CustomerNew customerNew3 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew3);
            this.loadTextView(edtValue2, String.valueOf(customerNew3.getSpeCode()));
            final FichePropertyEditView fichePropertyEditView3 = mFchSpeCode2;
            Intrinsics.checkNotNull(fichePropertyEditView3);
            final EditText edtValue3 = fichePropertyEditView3.getEdtValue();
            final CustomerNew customerNew4 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew4);
            this.loadTextView(edtValue3, String.valueOf(customerNew4.getSpeCode2()));
            final FichePropertyEditView fichePropertyEditView4 = mFchSpeCode3;
            Intrinsics.checkNotNull(fichePropertyEditView4);
            final EditText edtValue4 = fichePropertyEditView4.getEdtValue();
            final CustomerNew customerNew5 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew5);
            this.loadTextView(edtValue4, String.valueOf(customerNew5.getSpeCode3()));
            final FichePropertyEditView fichePropertyEditView5 = mFchSpeCode4;
            Intrinsics.checkNotNull(fichePropertyEditView5);
            final EditText edtValue5 = fichePropertyEditView5.getEdtValue();
            final CustomerNew customerNew6 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew6);
            this.loadTextView(edtValue5, String.valueOf(customerNew6.getSpeCode4()));
            final FichePropertyEditView fichePropertyEditView6 = mFchSpeCode5;
            Intrinsics.checkNotNull(fichePropertyEditView6);
            final EditText edtValue6 = fichePropertyEditView6.getEdtValue();
            final CustomerNew customerNew7 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew7);
            this.loadTextView(edtValue6, String.valueOf(customerNew7.getSpeCode5()));
            final FichePropertyEditView fichePropertyEditView7 = mFchGroupCode;
            Intrinsics.checkNotNull(fichePropertyEditView7);
            final EditText edtValue7 = fichePropertyEditView7.getEdtValue();
            final CustomerNew customerNew8 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew8);
            this.loadTextView(edtValue7, String.valueOf(customerNew8.getGroupCode()));
            final FichePropertyEditView fichePropertyEditView8 = mFchWarrantyCode;
            Intrinsics.checkNotNull(fichePropertyEditView8);
            final EditText edtValue8 = fichePropertyEditView8.getEdtValue();
            final CustomerNew customerNew9 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew9);
            this.loadTextView(edtValue8, String.valueOf(customerNew9.getWarrantyCode()));
            final FichePropertyEditView fichePropertyEditView9 = mFchRelatedPerson;
            Intrinsics.checkNotNull(fichePropertyEditView9);
            final EditText edtValue9 = fichePropertyEditView9.getEdtValue();
            final CustomerNew customerNew10 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew10);
            this.loadTextView(edtValue9, String.valueOf(customerNew10.getRelatedPerson()));
            final FichePropertyEditView fichePropertyEditView10 = mFchTaxOffice;
            Intrinsics.checkNotNull(fichePropertyEditView10);
            final EditText edtValue10 = fichePropertyEditView10.getEdtValue();
            final CustomerNew customerNew11 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew11);
            this.loadTextView(edtValue10, String.valueOf(customerNew11.getTaxOffice()));
            final FichePropertyEditView fichePropertyEditView11 = mFchTaxNo;
            Intrinsics.checkNotNull(fichePropertyEditView11);
            final EditText edtValue11 = fichePropertyEditView11.getEdtValue();
            final CustomerNew customerNew12 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew12);
            this.loadTextView(edtValue11, String.valueOf(customerNew12.getTaxNo()));
            final FichePropertyEditView fichePropertyEditView12 = mFchCustomerName;
            Intrinsics.checkNotNull(fichePropertyEditView12);
            final EditText edtValue12 = fichePropertyEditView12.getEdtValue();
            final CustomerNew customerNew13 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew13);
            this.loadTextView(edtValue12, String.valueOf(customerNew13.getCustomerName()));
            final FichePropertyEditView fichePropertyEditView13 = mFchCustomerSurname;
            Intrinsics.checkNotNull(fichePropertyEditView13);
            final EditText edtValue13 = fichePropertyEditView13.getEdtValue();
            final CustomerNew customerNew14 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew14);
            this.loadTextView(edtValue13, String.valueOf(customerNew14.getCustomerSurname()));
            final FichePropertyEditView fichePropertyEditView14 = mFchEmail;
            Intrinsics.checkNotNull(fichePropertyEditView14);
            final EditText edtValue14 = fichePropertyEditView14.getEdtValue();
            final CustomerNew customerNew15 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew15);
            this.loadTextView(edtValue14, String.valueOf(customerNew15.getEmail()));
            final FichePropertyEditView fichePropertyEditView15 = mFchEmail2;
            Intrinsics.checkNotNull(fichePropertyEditView15);
            final EditText edtValue15 = fichePropertyEditView15.getEdtValue();
            final CustomerNew customerNew16 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew16);
            this.loadTextView(edtValue15, String.valueOf(customerNew16.getEmail2()));
            final FichePropertyEditView fichePropertyEditView16 = mFchTel1;
            Intrinsics.checkNotNull(fichePropertyEditView16);
            final EditText edtValue16 = fichePropertyEditView16.getEdtValue();
            final CustomerNew customerNew17 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew17);
            this.loadTextView(edtValue16, String.valueOf(customerNew17.getTel1()));
            final FichePropertyEditView fichePropertyEditView17 = mFchTel2;
            Intrinsics.checkNotNull(fichePropertyEditView17);
            final EditText edtValue17 = fichePropertyEditView17.getEdtValue();
            final CustomerNew customerNew18 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew18);
            this.loadTextView(edtValue17, String.valueOf(customerNew18.getTel2()));
            final FichePropertyEditView fichePropertyEditView18 = mFchFax;
            Intrinsics.checkNotNull(fichePropertyEditView18);
            final EditText edtValue18 = fichePropertyEditView18.getEdtValue();
            final CustomerNew customerNew19 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew19);
            this.loadTextView(edtValue18, String.valueOf(customerNew19.getFax()));
            final FichePropertyEditView fichePropertyEditView19 = mFchAddress1;
            Intrinsics.checkNotNull(fichePropertyEditView19);
            final EditText edtValue19 = fichePropertyEditView19.getEdtValue();
            final CustomerNew customerNew20 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew20);
            this.loadTextView(edtValue19, String.valueOf(customerNew20.getAddress1()));
            final FichePropertyEditView fichePropertyEditView20 = mFchAddress2;
            Intrinsics.checkNotNull(fichePropertyEditView20);
            final EditText edtValue20 = fichePropertyEditView20.getEdtValue();
            final CustomerNew customerNew21 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew21);
            this.loadTextView(edtValue20, String.valueOf(customerNew21.getAddress2()));
            final FichePropertyEditView fichePropertyEditView21 = mFchZipCode;
            Intrinsics.checkNotNull(fichePropertyEditView21);
            final EditText edtValue21 = fichePropertyEditView21.getEdtValue();
            final CustomerNew customerNew22 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew22);
            this.loadTextView(edtValue21, String.valueOf(customerNew22.getZipCode()));
            final FichePropertyTextView fichePropertyTextView2 = mFchPayPlan;
            Intrinsics.checkNotNull(fichePropertyTextView2);
            final TextView txtValue2 = fichePropertyTextView2.getTxtValue();
            final CustomerNew customerNew23 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew23);
            this.loadTextView(txtValue2, String.valueOf(customerNew23.getPayPlan()));
            final FichePropertyEditView fichePropertyEditView22 = mFchTCNo;
            Intrinsics.checkNotNull(fichePropertyEditView22);
            final EditText edtValue22 = fichePropertyEditView22.getEdtValue();
            final CustomerNew customerNew24 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew24);
            this.loadTextView(edtValue22, String.valueOf(customerNew24.getTCNo()));
            final FichePropertyTextView fichePropertyTextView3 = mFchPayType;
            Intrinsics.checkNotNull(fichePropertyTextView3);
            final TextView txtValue3 = fichePropertyTextView3.getTxtValue();
            final CustomerNew customerNew25 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew25);
            this.loadTextView(txtValue3, String.valueOf(customerNew25.getPayType()));
            final FichePropertyEditView fichePropertyEditView23 = mFchVade;
            Intrinsics.checkNotNull(fichePropertyEditView23);
            final EditText edtValue23 = fichePropertyEditView23.getEdtValue();
            final CustomerNew customerNew26 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew26);
            this.loadTextView(edtValue23, String.valueOf(customerNew26.getVade()));
            final FichePropertyEditView fichePropertyEditView24 = mFchIskonto;
            Intrinsics.checkNotNull(fichePropertyEditView24);
            final EditText edtValue24 = fichePropertyEditView24.getEdtValue();
            final CustomerNew customerNew27 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew27);
            this.loadTextView(edtValue24, String.valueOf(customerNew27.getIskontoOran()));
            final FichePropertyTextView fichePropertyTextView4 = mFchIl;
            Intrinsics.checkNotNull(fichePropertyTextView4);
            final TextView txtValue4 = fichePropertyTextView4.getTxtValue();
            final CustomerNew customerNew28 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew28);
            this.loadTextView(txtValue4, String.valueOf(customerNew28.getCity()));
            final FichePropertyTextView fichePropertyTextView5 = mFchIlce;
            Intrinsics.checkNotNull(fichePropertyTextView5);
            final TextView txtValue5 = fichePropertyTextView5.getTxtValue();
            final CustomerNew customerNew29 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew29);
            this.loadTextView(txtValue5, String.valueOf(customerNew29.getTown()));
            final FichePropertyEditView fichePropertyEditView25 = mFchIncharge;
            Intrinsics.checkNotNull(fichePropertyEditView25);
            final EditText edtValue25 = fichePropertyEditView25.getEdtValue();
            final CustomerNew customerNew30 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew30);
            this.loadTextView(edtValue25, String.valueOf(customerNew30.getInCharge()));
            final FichePropertyEditView fichePropertyEditView26 = mFchInChargeDefinition;
            Intrinsics.checkNotNull(fichePropertyEditView26);
            final EditText edtValue26 = fichePropertyEditView26.getEdtValue();
            final CustomerNew customerNew31 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew31);
            this.loadTextView(edtValue26, String.valueOf(customerNew31.getInChargeDefinition()));
            final EditText editText = mFchInChargeTelCode;
            final CustomerNew customerNew32 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew32);
            this.loadTextView(editText, String.valueOf(customerNew32.getInChargeTelCode()));
            final EditText editText2 = mFchInChargeTelNum;
            final CustomerNew customerNew33 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew33);
            this.loadTextView(editText2, String.valueOf(customerNew33.getInChargeTel()));
            final FichePropertyEditView fichePropertyEditView27 = mFchInChargeEmail;
            Intrinsics.checkNotNull(fichePropertyEditView27);
            final EditText edtValue27 = fichePropertyEditView27.getEdtValue();
            final CustomerNew customerNew34 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew34);
            this.loadTextView(edtValue27, String.valueOf(customerNew34.getInChargeEmail()));
            final FichePropertyTextView fichePropertyTextView6 = mFchUlke;
            Intrinsics.checkNotNull(fichePropertyTextView6);
            final TextView txtValue6 = fichePropertyTextView6.getTxtValue();
            final CustomerNew customerNew35 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew35);
            this.loadTextView(txtValue6, String.valueOf(customerNew35.getCountry()));
            final CheckBox checkBox = mFchChkPersonalCompany;
            final CustomerNew customerNew36 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew36);
            final FicheBooleanProp personalCompany = customerNew36.getPersonalCompany();
            Intrinsics.checkNotNull(personalCompany);
            this.loadCheckView(checkBox, personalCompany.isSelect(), isPersonalCompany);
        }
        try {
            final ImageView imageView = mImgCustomer;
            final CustomerNew customerNew37 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew37);
            final FicheImageProp image = customerNew37.getImage();
            Intrinsics.checkNotNull(image);
            this.loadImageView(imageView, CompressUtil.deCompressBitmap(image.getImageArray()));
        } catch (final IOException e2) {
            Log.e(CustomerNewFragment.TAG, "load: ", e2);
        } catch (final Exception e3) {
            Log.e(CustomerNewFragment.TAG, "load: ", e3);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override // com.proje.mobilesales.core.base.BaseFragment
    protected void createOptionsMenu(final Menu menu, final MenuInflater inflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        super.createOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_customer_new, menu);
        inflater.inflate(R.menu.menu_add_photo, menu);
        if (null != menu.findItem(R.id.menu_photo)) {
            menu.findItem(R.id.menu_photo).setVisible(!Preferences.isDemo(this.getContext()));
        }
        if (null != menu.findItem(R.id.menu_fiche_save)) {
            saveButton = menu.findItem(R.id.menu_fiche_save);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (R.id.menu_fiche_save == item.getItemId()) {
            final MenuItem menuItem = saveButton;
            Intrinsics.checkNotNull(menuItem);
            menuItem.setEnabled(false);
            this.saveCustomer();
        } else if (R.id.menu_photo == item.getItemId()) {
            this.openAddPhotoDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // com.proje.mobilesales.core.base.BaseFicheFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(final Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putParcelable(CustomerNewFragment.STATE_CUSTOMER, mCustomerNew);
        final AddPhotoMethod addPhotoMethod = mAddPhotoMethod;
        Intrinsics.checkNotNull(addPhotoMethod);
        outState.putInt(CustomerNewFragment.STATE_ADD_PHOTO_METHOD, addPhotoMethod.getValue());
        outState.putBoolean(CustomerNewFragment.STATE_ADD_PHOTO, mAddPhoto);
        super.onSaveInstanceState(outState);
    }

    public void saveCustomer() {
        final String checkRequiredFields = this.checkRequiredFields();
        if (!mAddPhoto && !TextUtils.isEmpty(checkRequiredFields)) {
            this.getMNewCustomerRequiredDialogBuilder().setMessage(checkRequiredFields).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.newadd.CustomerNewFragmentExternalSyntheticLambda15
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(final DialogInterface dialogInterface, final int i2) {
                    saveCustomerlambda16(dialogInterface, i2);
                }
            }).create().show();
            final MenuItem menuItem = saveButton;
            Intrinsics.checkNotNull(menuItem);
            menuItem.setEnabled(true);
            return;
        }
        if (!mAddPhoto) {
            final CustomerNew customerNew = mCustomerNew;
            Intrinsics.checkNotNull(customerNew);
            if (TextUtils.isEmpty(String.valueOf(customerNew.getName()))) {
                Toast.makeText(this.getActivity(), this.getString(R.string.str_question_enter_definition), 0).show();
                final MenuItem menuItem2 = saveButton;
                Intrinsics.checkNotNull(menuItem2);
                menuItem2.setEnabled(true);
                return;
            }
        }
        if (viewModel.erpType() == ErpType.NETSIS) {
            if (this.checkTCIdentityField()) {
                Toast.makeText(this.getActivity(), this.getString(R.string.str_id_be_eleven_digit), 0).show();
                final MenuItem menuItem3 = saveButton;
                Intrinsics.checkNotNull(menuItem3);
                menuItem3.setEnabled(true);
                return;
            }
            if (this.checkRelatedPersonFields()) {
                Toast.makeText(this.getActivity(), this.getString(R.string.str_related_person_error), 0).show();
                final MenuItem menuItem4 = saveButton;
                Intrinsics.checkNotNull(menuItem4);
                menuItem4.setEnabled(true);
                return;
            }
        }
        this.getMProgressDialogBuilder().setMessage(this.getString(mAddPhoto ? R.string.str_customer_image_saved : R.string.str_new_customer_saving_please_wait)).show();
        if (!mAddPhoto) {
            final CustomerNew customerNew2 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew2);
            customerNew2.setLogicalRef(viewModel.getSqlHelper().getAvailableOfflineCustomerRef());
            viewModel.saveNewCustomer(mCustomerNew, new CustomerNewFragmentListener(this));
            return;
        }
        if (!ContextUtils.checkConnectionWithoutMessage()) {
            viewModel.updateNewCustomer(mCustomerNew, new CustomerImageUpdateListener(this));
        } else {
            viewModel.addNewCustomer(mCustomerNew, mAddPhoto, new CustomerSendListener(this));
        }
    }


    public static void saveCustomerlambda16(final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    private boolean checkTCIdentityField() {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (0 < String.valueOf(customerNew.getTCNo()).length()) {
            final CustomerNew customerNew2 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew2);
            return String.valueOf(customerNew2.getTCNo()).length() != TCNOLENGHT;
        }
        return false;
    }

    private boolean checkRelatedPersonFields() {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (null != customerNew.getInCharge()) {
            final CustomerNew customerNew2 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew2);
        }
        final CustomerNew customerNew3 = mCustomerNew;
        Intrinsics.checkNotNull(customerNew3);
        if (null != customerNew3.getInChargeDefinition()) {
            final CustomerNew customerNew4 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew4);
        }
        final CustomerNew customerNew5 = mCustomerNew;
        Intrinsics.checkNotNull(customerNew5);
        if (null != customerNew5.getInChargeTel()) {
            final CustomerNew customerNew6 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew6);
        }
        final CustomerNew customerNew7 = mCustomerNew;
        Intrinsics.checkNotNull(customerNew7);
        if (null != customerNew7.getInChargeTelCode()) {
            final CustomerNew customerNew8 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew8);
        }
        final CustomerNew customerNew9 = mCustomerNew;
        Intrinsics.checkNotNull(customerNew9);
    }

    @Override // com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        mCustomerNew = null;
        super.onDestroy();
    }

    @Subscribe
    public void responseEvent(final ResponseEvent responseEvent) {
        Intrinsics.checkNotNullParameter(responseEvent, "responseEvent");
        if (this.isDetached()) {
            return;
        }
        this.getMProgressDialogBuilder().dismiss();
        if (!TextUtils.isEmpty(responseEvent.getErrorMessage())) {
            Toast.makeText(this.getActivity(), responseEvent.getErrorMessage(), 1).show();
        }
        if (responseEvent.isSuccess()) {
            Toast.makeText(this.getActivity(), this.getString(mAddPhoto ? R.string.str_customer_new_image_saved : R.string.str_new_customer_saved), 1).show();
        } else if (!mAddPhoto) {
            Toast.makeText(this.getActivity(), R.string.exp_52_wcf_connection_error_new_customer_saved, 1).show();
        }
        final FragmentActivity activity = this.getActivity();
        Intrinsics.checkNotNull(activity);
        activity.setResult(-1);
        final FragmentActivity activity2 = this.getActivity();
        Intrinsics.checkNotNull(activity2);
        activity2.finish();
    }

    private void openAddPhotoDialog() {
        final AlertDialogBuilder title = this.getMPhotoAlertDialogBuilder().setTitle(R.string.str_add_photo);
        final AddPhotoMethod addPhotoMethod = mAddPhotoMethod;
        Intrinsics.checkNotNull(addPhotoMethod);
        title.setSingleChoice(R.array.add_photo_select_values, addPhotoMethod.getValue(), new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.newadd.CustomerNewFragmentExternalSyntheticLambda9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                openAddPhotoDialoglambda17(CustomerNewFragment.this, dialogInterface, i2);
            }
        }).create().show();
    }


    public static void openAddPhotoDialoglambda17(final CustomerNewFragment this0, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        this0.mAddPhotoMethod = AddPhotoMethod.Companion.fromAddPhotoMethod(i2);
        dialog.dismiss();
        this0.startUserChooseIntent();
    }


    public static void photoPickerLauncherlambda19(final CustomerNewFragment this0, final Uri uri) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (null != uri) {
            final Bitmap bitmap = MediaStore.Images.Media.getBitmap(this0.requireContext().getContentResolver(), uri);
            this0.setCustomerImage(CompressUtil.compressBitmapToPng(CompressUtil.resizeImageForImageView(bitmap, 400)));
            final ImageView imageView = this0.mImgCustomer;
            Intrinsics.checkNotNull(imageView);
            imageView.setImageBitmap(bitmap);
        }
    }

    private void startUserChooseIntent() {
        final AddPhotoMethod addPhotoMethod = mAddPhotoMethod;
        if (addPhotoMethod == AddPhotoMethod.GALLERY) {
            if (33 <= Build.VERSION.SDK_INT) {
                photoPickerLauncher.launch(new PickVisualMediaRequest.Builder().setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE).build());
                return;
            } else {
                if (PermissionUtils.checkPermissionFromFragment(this, "android.permission.READ_EXTERNAL_STORAGE", this.getString(R.string.str_read_disk_permission_for_save_picture))) {
                    this.createGalleryIntent();
                    return;
                }
                return;
            }
        }
        if (addPhotoMethod == AddPhotoMethod.CAMERA && PermissionUtils.checkPermissionFromFragment(this, "android.permission.CAMERA", this.getString(R.string.str_camera_permission_for_take_photo))) {
            this.createCameraIntent();
        }
    }

    private void createCameraIntent() {
        final Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        final FragmentActivity activity = this.getActivity();
        Intrinsics.checkNotNull(activity);
        if (null != intent.resolveActivity(activity.getPackageManager())) {
            this.startActivityForResult(intent, 997);
        }
    }

    private void createGalleryIntent() {
        final Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        this.startActivityForResult(Intent.createChooser(intent, "Select File"), 996);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(final int i2, final int i3, final Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (996 == i2 && -1 == i3) {
            this.onSelectFromGalleryResult(intent);
        } else if (997 == i2 && -1 == i3) {
            this.onCaptureImageResult(intent);
        }
    }

    private void onSelectFromGalleryResult(final Intent intent) {
        Bitmap bitmap = null;
        if (null != intent) {
            try {
                final FragmentActivity activity = this.getActivity();
                Intrinsics.checkNotNull(activity);
                bitmap = MediaStore.Images.Media.getBitmap(activity.getApplicationContext().getContentResolver(), intent.getData());
                this.setCustomerImage(CompressUtil.compressBitmapToPng(CompressUtil.resizeImageForImageView(bitmap, 400)));
            } catch (final IOException e2) {
                e2.printStackTrace();
            }
        }
        if (null != bitmap) {
            final ImageView imageView = mImgCustomer;
            Intrinsics.checkNotNull(imageView);
            imageView.setImageBitmap(bitmap);
        }
    }

    private void onCaptureImageResult(final Intent intent) {
        Exception e2;
        Bitmap bitmap;
        Bitmap bitmap2 = null;
        if (null != intent) {
            try {
                final Bundle extras = intent.getExtras();
                Intrinsics.checkNotNull(extras);
                bitmap = (Bitmap) extras.get(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
                try {
                    this.setCustomerImage(CompressUtil.compressBitmapToPng(bitmap));
                } catch (final Exception e3) {
                    e2 = e3;
                    e2.printStackTrace();
                    bitmap2 = bitmap;
                    if (null == bitmap2) {
                    }
                }
            } catch (final Exception e4) {
                e2 = e4;
                bitmap = null;
            }
            bitmap2 = bitmap;
        }
        if (null == bitmap2) {
            final ImageView imageView = mImgCustomer;
            Intrinsics.checkNotNull(imageView);
            imageView.setImageBitmap(bitmap2);
        }
    }

    private void setCustomerImage(final byte[] bArr) {
        if (null != bArr) {
            final CustomerNew customerNew = mCustomerNew;
            Intrinsics.checkNotNull(customerNew);
            final FicheImageProp image = customerNew.getImage();
            Intrinsics.checkNotNull(image);
            image.setImageArray(bArr);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(final int i2, final String[] permissions2, final int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions2, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        if ((33 > Build.VERSION.SDK_INT && 1071 == i2) || 1074 == i2) {
            if (0 != grantResults.length && 0 == grantResults[0]) {
                mAddPhotoMethod = 1071 == i2 ? AddPhotoMethod.GALLERY : AddPhotoMethod.CAMERA;
                this.startUserChooseIntent();
            } else {
                Toast.makeText(this.getContext(), this.getString(R.string.str_permissions_denied), 1).show();
            }
        }
        super.onRequestPermissionsResult(i2, permissions2, grantResults);
    }

    /* compiled from: CustomerNewFragment.kt */
        private record CustomerNewFragmentListener(
            WeakReference<CustomerNewFragment> mCustomerNew) implements ResponseListener<Boolean> {
            private CustomerNewFragmentListener(final CustomerNewFragment mCustomerNew) {
                this.mCustomerNew = new WeakReference<>(mCustomerNew);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(final Boolean bool) {
                if (null != this.mCustomerNew.get()) {
                    final CustomerNewFragment customerNewFragment = mCustomerNew.get();
                    Intrinsics.checkNotNull(customerNewFragment);
                    if (customerNewFragment.isAttached()) {
                        final CustomerNewFragment customerNewFragment2 = mCustomerNew.get();
                        Intrinsics.checkNotNull(customerNewFragment2);
                        Intrinsics.checkNotNull(bool);
                        customerNewFragment2.onSaveResult(bool.booleanValue(), "");
                    }
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                Log.d(TAG, "onError: " + errorMessage);
                if (null != this.mCustomerNew.get()) {
                    final CustomerNewFragment customerNewFragment = mCustomerNew.get();
                    Intrinsics.checkNotNull(customerNewFragment);
                    if (customerNewFragment.isAttached()) {
                        final CustomerNewFragment customerNewFragment2 = mCustomerNew.get();
                        Intrinsics.checkNotNull(customerNewFragment2);
                        customerNewFragment2.onSaveResult(false, errorMessage);
                    }
                }
            }
        }


    public void onSaveResult(final boolean z, final String str) {
        if (z) {
            if (!ContextUtils.checkConnectionWithoutMessage()) {
                final String string = this.getString(R.string.exp_52_wcf_connection_error_new_customer_saved);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                this.onFinish(true, string);
                return;
            } else {
                final ProgressDialogBuilder<?> mProgressDialogBuilder = this.getMProgressDialogBuilder();
                final Context context = this.getContext();
                Intrinsics.checkNotNull(context);
                mProgressDialogBuilder.setMessage(context.getString(R.string.str_please_wait)).show();
                viewModel.getCheckRemoteWorkTimeControl(WorkTimeControlProcessType.TransferSend, new CheckWorkTimeListener(this));
                return;
            }
        }
        Toast.makeText(this.getContext(), str, 1).show();
        final MenuItem menuItem = saveButton;
        Intrinsics.checkNotNull(menuItem);
        menuItem.setEnabled(true);
    }

    /* compiled from: CustomerNewFragment.kt */
        private record CheckWorkTimeListener(
            WeakReference<CustomerNewFragment> mFragment) implements ResponseListener<String> {
            private CheckWorkTimeListener(final CustomerNewFragment mFragment) {
                this.mFragment = new WeakReference<>(mFragment);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(final String str) {
                if (null != this.mFragment.get()) {
                    final CustomerNewFragment customerNewFragment = mFragment.get();
                    Intrinsics.checkNotNull(customerNewFragment);
                    if (customerNewFragment.isAttached()) {
                        final CustomerNewFragment customerNewFragment2 = mFragment.get();
                        Intrinsics.checkNotNull(customerNewFragment2);
                        customerNewFragment2.getMProgressDialogBuilder().dismiss();
                        if (!TextUtils.isEmpty(str)) {
                            final CustomerNewFragment customerNewFragment3 = mFragment.get();
                            Intrinsics.checkNotNull(customerNewFragment3);
                            Toast.makeText(customerNewFragment3.getActivity(), str, 0).show();
                            return;
                        }
                        final CustomerNewFragment customerNewFragment4 = mFragment.get();
                        Intrinsics.checkNotNull(customerNewFragment4);
                        final ProgressDialogBuilder<?> mProgressDialogBuilder = customerNewFragment4.getMProgressDialogBuilder();
                        final CustomerNewFragment customerNewFragment5 = mFragment.get();
                        Intrinsics.checkNotNull(customerNewFragment5);
                        mProgressDialogBuilder.setMessage(customerNewFragment5.getString(R.string.str_please_wait)).show();
                        final CustomerNewFragment customerNewFragment6 = mFragment.get();
                        Intrinsics.checkNotNull(customerNewFragment6);
                        final CustomerNewViewModel customerNewViewModel = customerNewFragment6.viewModel;
                        final CustomerNewFragment customerNewFragment7 = mFragment.get();
                        Intrinsics.checkNotNull(customerNewFragment7);
                        final CustomerNew customerNew = customerNewFragment7.mCustomerNew;
                        final CustomerNewFragment customerNewFragment8 = mFragment.get();
                        Intrinsics.checkNotNull(customerNewFragment8);
                        customerNewViewModel.addNewCustomer(customerNew, customerNewFragment8.mAddPhoto, new CustomerSendListener(mFragment.get()));
                    }
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (null != this.mFragment.get()) {
                    final CustomerNewFragment customerNewFragment = mFragment.get();
                    Intrinsics.checkNotNull(customerNewFragment);
                    if (customerNewFragment.isAttached()) {
                        final CustomerNewFragment customerNewFragment2 = mFragment.get();
                        Intrinsics.checkNotNull(customerNewFragment2);
                        customerNewFragment2.getMProgressDialogBuilder().dismiss();
                    }
                }
            }
        }

    /* compiled from: CustomerNewFragment.kt */
        private record CustomerImageUpdateListener(
            WeakReference<CustomerNewFragment> mCustomerNew) implements ResponseListener<Boolean> {
            private CustomerImageUpdateListener(final CustomerNewFragment mCustomerNew) {
                this.mCustomerNew = new WeakReference<>(mCustomerNew);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(final Boolean bool) {
                if (null != this.mCustomerNew.get()) {
                    final CustomerNewFragment customerNewFragment = mCustomerNew.get();
                    Intrinsics.checkNotNull(customerNewFragment);
                    if (customerNewFragment.isAttached()) {
                        final CustomerNewFragment customerNewFragment2 = mCustomerNew.get();
                        Intrinsics.checkNotNull(customerNewFragment2);
                        Intrinsics.checkNotNull(bool);
                        final boolean booleanValue = bool.booleanValue();
                        final CustomerNewFragment customerNewFragment3 = mCustomerNew.get();
                        Intrinsics.checkNotNull(customerNewFragment3);
                        final String string = customerNewFragment3.getString(R.string.str_customer_new_image_saved);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                        customerNewFragment2.onFinish(booleanValue, string);
                    }
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                Log.d(TAG, "onError: " + errorMessage);
                if (null != this.mCustomerNew.get()) {
                    final CustomerNewFragment customerNewFragment = mCustomerNew.get();
                    Intrinsics.checkNotNull(customerNewFragment);
                    if (customerNewFragment.isAttached()) {
                        final CustomerNewFragment customerNewFragment2 = mCustomerNew.get();
                        Intrinsics.checkNotNull(customerNewFragment2);
                        customerNewFragment2.onFinish(false, errorMessage);
                    }
                }
            }
        }


    public void onFinish(final boolean z, final String str) {
        if (z) {
            this.getMProgressDialogBuilder().dismiss();
            Toast.makeText(this.getActivity(), str, 1).show();
            final FragmentActivity activity = this.getActivity();
            Intrinsics.checkNotNull(activity);
            activity.setResult(-1);
            final FragmentActivity activity2 = this.getActivity();
            Intrinsics.checkNotNull(activity2);
            activity2.finish();
        }
    }

    /* compiled from: CustomerNewFragment.kt */
        private record CustomerSendListener(
            WeakReference<CustomerNewFragment> mCustomerNew) implements ResponseListener<CustomerNew> {
            private CustomerSendListener(final CustomerNewFragment mCustomerNew) {
                this.mCustomerNew = new WeakReference<>(mCustomerNew);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(final CustomerNew customerNew) {
                if (null != this.mCustomerNew.get()) {
                    final CustomerNewFragment customerNewFragment = mCustomerNew.get();
                    Intrinsics.checkNotNull(customerNewFragment);
                    if (customerNewFragment.isAttached()) {
                        final CustomerNewFragment customerNewFragment2 = mCustomerNew.get();
                        Intrinsics.checkNotNull(customerNewFragment2);
                        customerNewFragment2.getMProgressDialogBuilder().dismiss();
                        Intrinsics.checkNotNull(customerNew);
                        if (null != customerNew.getCode() && 0 < String.valueOf(customerNew.getCode()).length()) {
                            final CustomerNewFragment customerNewFragment3 = mCustomerNew.get();
                            Intrinsics.checkNotNull(customerNewFragment3);
                            final CustomerNewFragment customerNewFragment4 = customerNewFragment3;
                            final CustomerNewFragment customerNewFragment5 = mCustomerNew.get();
                            Intrinsics.checkNotNull(customerNewFragment5);
                            final CustomerNewFragment customerNewFragment6 = customerNewFragment5;
                            final CustomerNewFragment customerNewFragment7 = mCustomerNew.get();
                            Intrinsics.checkNotNull(customerNewFragment7);
                            final String string = customerNewFragment6.getString(customerNewFragment7.mAddPhoto ? R.string.str_customer_new_image_saved : R.string.str_new_customer_saved);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                            customerNewFragment4.onFinish(true, string);
                            return;
                        }
                        final CustomerNewFragment customerNewFragment8 = mCustomerNew.get();
                        Intrinsics.checkNotNull(customerNewFragment8);
                        final CustomerNewFragment customerNewFragment9 = mCustomerNew.get();
                        Intrinsics.checkNotNull(customerNewFragment9);
                        final String string2 = customerNewFragment9.getString(R.string.exp_52_wcf_connection_error_new_customer_saved);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                        customerNewFragment8.onFinish(true, string2);
                    }
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                Log.d(TAG, "onError: " + errorMessage);
                if (null != this.mCustomerNew.get()) {
                    final CustomerNewFragment customerNewFragment = mCustomerNew.get();
                    Intrinsics.checkNotNull(customerNewFragment);
                    if (customerNewFragment.isAttached()) {
                        final CustomerNewFragment customerNewFragment2 = mCustomerNew.get();
                        Intrinsics.checkNotNull(customerNewFragment2);
                        customerNewFragment2.getMProgressDialogBuilder().dismiss();
                        final CustomerNewFragment customerNewFragment3 = mCustomerNew.get();
                        Intrinsics.checkNotNull(customerNewFragment3);
                        Toast.makeText(customerNewFragment3.getContext(), errorMessage, 1).show();
                        final CustomerNewFragment customerNewFragment4 = mCustomerNew.get();
                        Intrinsics.checkNotNull(customerNewFragment4);
                        final CustomerNewFragment customerNewFragment5 = mCustomerNew.get();
                        Intrinsics.checkNotNull(customerNewFragment5);
                        final String string = customerNewFragment5.getString(R.string.exp_52_wcf_connection_error_new_customer_saved);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                        customerNewFragment4.onFinish(true, string);
                    }
                }
            }
        }

    private String checkRequiredFields() {
        List emptyList;
        final String newCustomerRequiredFields = viewModel.getBaseErp().getNewCustomerRequiredFields();
        String str = "";
        if (TextUtils.isEmpty(newCustomerRequiredFields)) {
            return "";
        }
        Log.e("TAG", newCustomerRequiredFields);
        Intrinsics.checkNotNull(newCustomerRequiredFields);
        final List<String> split = new Regex(",").split(StringsKt.replacedefault(newCustomerRequiredFields, " ", "", false, 4, (Object) null), 0);
        if (!split.isEmpty()) {
            final ListIterator<String> listIterator = split.listIterator(split.size());
            while (listIterator.hasPrevious()) {
                if (0 != listIterator.previous().length()) {
                    emptyList = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList = CollectionsKt.emptyList();
        final String[] strArr = (String[]) emptyList.toArray(new String[0]);
        final Iterator it = new ArrayList(Arrays.asList(Arrays.copyOf(strArr, strArr.length))).iterator();
        while (it.hasNext()) {
            final String str2 = (String) it.next();
            final StringBuilder sb = new StringBuilder();
            sb.append(str);
            final NewCustomerRequiredFields.Companion companion = NewCustomerRequiredFields.Companion;
            final ErpType erpType = baseErp.getErpType();
            Intrinsics.checkNotNullExpressionValue(erpType, "getErpType(...)");
            sb.append(this.checkCustomerCode(companion.fromNewCustomerRequiredFields(str2, erpType)));
            str = sb.toString();
        }
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (StringsKt.endsWithdefault(str, ", ", false, 2, (Object) null)) {
            str = str.substring(0, str.length() - 2);
            Intrinsics.checkNotNullExpressionValue(str, "substring(...)");
        }
        final String stringResource = ContextUtils.getStringResource(R.string.str_please_enter_required_fields, str);
        Intrinsics.checkNotNullExpressionValue(stringResource, "getStringResource(...)");
        return stringResource;
    }

    private String checkCustomerCode(final NewCustomerRequiredFields newCustomerRequiredFields) {
        switch (WhenMappings.EnumSwitchMapping1[newCustomerRequiredFields.ordinal()]) {
            case 1:
                return this.setErrorMessageWithTitle("");
            case 2:
                return this.setErrorMessageWithId("");
            case 3:
                return this.setErrorMessageWithTaxNo("");
            case 4:
                return this.setErrorMessageWithTaxOffice("");
            case 5:
                return this.setErrorMessageWithTel1("");
            case 6:
                return this.setErrorMessageWithTel2("");
            case 7:
                return this.setErrorMessageWithEmail("");
            case 8:
                return this.setErrorMessageWithCountry("");
            case 9:
                return this.setErrorMessageWithProvince("");
            case 10:
                return this.setErrorMessageWithDistrict("");
            case 11:
                return this.setErrorMessageWithFax("");
            case 12:
                return this.setErrorMessageWithAddress1("");
            case 13:
                return this.setErrorMessageWithAddress2("");
            case 14:
                return this.setErrorMessageWithZipCode("");
            case 15:
                return this.setErrorMessageWithExpiry("");
            case 16:
                return this.setErrorMessageWithDiscountRate("");
            case 17:
                return this.setErrorMessageWithPaymentPlan("");
            case 18:
                return this.setErrorMessageWithPaymentType("");
            case 19:
                return this.controlRelatedPerson("");
            case 20:
                return this.setErrorMessageWithRelatedTitle("");
            case 21:
                return this.setErrorMessageWithRelatedTelNo("");
            case 22:
                return this.setErrorMessageWithRelatedEmail("");
            case 23:
                return this.setErrorMessageWithSpeCode1("");
            case 24:
                return this.setErrorMessageWithSpeCode2("");
            case 25:
                return this.setErrorMessageWithSpeCode3("");
            case 26:
                return this.setErrorMessageWithSpeCode4("");
            case 27:
                return this.setErrorMessageWithSpeCode5("");
            case 28:
                return this.setErrorMessageWithWarrantyCode("");
            default:
                return "";
        }
    }

    private String setErrorMessageWithTitle(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldEditText(String.valueOf(customerNew.getName()), mFchName)) {
            return str + this.getString(R.string.str_definition) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithId(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldEditText(String.valueOf(customerNew.getTCNo()), mFchTCNo)) {
            return str + this.getString(R.string.str_customer_tc_no) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithTaxNo(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldEditText(String.valueOf(customerNew.getTaxNo()), mFchTaxNo)) {
            return str + this.getString(R.string.str_customer_tax_no) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithTaxOffice(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldEditText(String.valueOf(customerNew.getTaxOffice()), mFchTaxOffice)) {
            return str + this.getString(R.string.str_customer_tax_office) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithTel1(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldEditText(String.valueOf(customerNew.getTel1()), mFchTel1)) {
            return str + this.getString(R.string.str_phone_number1) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithTel2(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldEditText(String.valueOf(customerNew.getTel2()), mFchTel2)) {
            return str + this.getString(R.string.str_phone_number2) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithEmail(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldEditText(String.valueOf(customerNew.getEmail()), mFchEmail)) {
            return str + this.getString(R.string.str_email_address) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithCountry(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldTextView(String.valueOf(customerNew.getCountry()), mFchUlke)) {
            return str + this.getString(R.string.str_country) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithProvince(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldTextView(String.valueOf(customerNew.getCity()), mFchIl)) {
            return str + this.getString(R.string.str_city) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithDistrict(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldTextView(String.valueOf(customerNew.getTown()), mFchIlce)) {
            return str + this.getString(R.string.str_town) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithFax(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldEditText(String.valueOf(customerNew.getFax()), mFchFax)) {
            return str + this.getString(R.string.str_fax_number) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithAddress1(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldEditText(String.valueOf(customerNew.getAddress1()), mFchAddress1)) {
            return str + this.getString(R.string.str_address1) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithAddress2(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldEditText(String.valueOf(customerNew.getAddress2()), mFchAddress2)) {
            return str + this.getString(R.string.str_address2) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithZipCode(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldEditText(String.valueOf(customerNew.getZipCode()), mFchZipCode)) {
            return str + this.getString(R.string.str_postal_code) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithExpiry(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldEditText(String.valueOf(customerNew.getVade()), mFchVade)) {
            return str + this.getString(R.string.str_expiry) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithDiscountRate(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldEditText(String.valueOf(customerNew.getIskontoOran()), mFchIskonto)) {
            return str + this.getString(R.string.str_discount_rate) + ',';
        }
        return "";
    }

    private String setErrorMessageWithPaymentPlan(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldTextView(String.valueOf(customerNew.getPayPlan()), mFchPayPlan)) {
            return str + this.getString(R.string.str_pay_plan) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithPaymentType(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldTextView(String.valueOf(customerNew.getPayType()), mFchPayType)) {
            return str + this.getString(R.string.str_payment_type) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithRelatedTitle(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldEditText(String.valueOf(customerNew.getInChargeDefinition()), mFchInChargeDefinition)) {
            return str + this.getString(R.string.str_related_person) + ' ' + this.getString(R.string.str_definition) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithRelatedTelNo(final String str) {
        if (this.controlTelNumber()) {
            return str + this.getString(R.string.str_related_person) + ' ' + this.getString(R.string.str_phone_number) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithRelatedEmail(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldEditText(String.valueOf(customerNew.getInChargeEmail()), mFchInChargeEmail)) {
            return str + this.getString(R.string.str_related_person) + ' ' + this.getString(R.string.str_email_address) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithSpeCode1(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldEditText(String.valueOf(customerNew.getSpeCode()), mFchSpeCode)) {
            return str + this.getString(R.string.str_customer_specode_1) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithSpeCode2(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldEditText(String.valueOf(customerNew.getSpeCode2()), mFchSpeCode2)) {
            return str + this.getString(R.string.str_customer_specode_2) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithSpeCode3(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldEditText(String.valueOf(customerNew.getSpeCode3()), mFchSpeCode3)) {
            return str + this.getString(R.string.str_customer_specode_3) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithSpeCode4(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldEditText(String.valueOf(customerNew.getSpeCode4()), mFchSpeCode4)) {
            return str + this.getString(R.string.str_customer_specode_4) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithSpeCode5(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldEditText(String.valueOf(customerNew.getSpeCode5()), mFchSpeCode5)) {
            return str + this.getString(R.string.str_customer_specode_5) + ", ";
        }
        return "";
    }

    private String setErrorMessageWithWarrantyCode(final String str) {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (this.controlRequiredFieldEditText(String.valueOf(customerNew.getWarrantyCode()), mFchWarrantyCode)) {
            return str + this.getString(R.string.str_warranty_code) + ", ";
        }
        return "";
    }

    private String controlRelatedPerson(final String str) {
        final FicheStringProp inCharge;
        final FichePropertyEditView fichePropertyEditView;
        if (viewModel.erpType() == ErpType.TIGER) {
            final CustomerNew customerNew = mCustomerNew;
            Intrinsics.checkNotNull(customerNew);
            inCharge = customerNew.getRelatedPerson();
            fichePropertyEditView = mFchRelatedPerson;
        } else {
            final CustomerNew customerNew2 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew2);
            inCharge = customerNew2.getInCharge();
            fichePropertyEditView = mFchIncharge;
        }
        if (!this.controlRequiredFieldEditText(String.valueOf(inCharge), fichePropertyEditView)) {
            return str;
        }
        return str + this.getString(R.string.str_related_person) + ", ";
    }

    private boolean controlTelNumber() {
        final CustomerNew customerNew = mCustomerNew;
        Intrinsics.checkNotNull(customerNew);
        if (TextUtils.isEmpty(String.valueOf(customerNew.getInChargeTel()))) {
            final CustomerNew customerNew2 = mCustomerNew;
            Intrinsics.checkNotNull(customerNew2);
            if (TextUtils.isEmpty(String.valueOf(customerNew2.getInChargeTelCode()))) {
                final EditText editText = mFchInChargeTelNum;
                Intrinsics.checkNotNull(editText);
                if (editText.isEnabled()) {
                    final EditText editText2 = mFchInChargeTelCode;
                    Intrinsics.checkNotNull(editText2);
                    return editText2.isEnabled();
                }
            }
        }
        return false;
    }

    private boolean controlRequiredFieldEditText(final String str, final FichePropertyEditView fichePropertyEditView) {
        return TextUtils.isEmpty(str) && this.isFieldAvailable(fichePropertyEditView);
    }

    private boolean controlRequiredFieldTextView(final String str, final FichePropertyTextView fichePropertyTextView) {
        return TextUtils.isEmpty(str) && this.isFieldAvailable(fichePropertyTextView);
    }

    private boolean isFieldAvailable(final FichePropertyEditView fichePropertyEditView) {
        Intrinsics.checkNotNull(fichePropertyEditView);
        return 0 == fichePropertyEditView.getLnFiche().getVisibility() && fichePropertyEditView.getEdtValue().isEnabled();
    }

    private boolean isFieldAvailable(final FichePropertyTextView fichePropertyTextView) {
        Intrinsics.checkNotNull(fichePropertyTextView);
        return 0 == fichePropertyTextView.getLnFiche().getVisibility() && fichePropertyTextView.getTxtValue().isEnabled();
    }

    /* compiled from: CustomerNewFragment.kt */
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public String getCUSTOMER_NEW_TAG() {
            return CUSTOMER_NEW_TAG;
        }

        public CustomerNewFragment newInstance() {
            return new CustomerNewFragment();
        }
    }
}
