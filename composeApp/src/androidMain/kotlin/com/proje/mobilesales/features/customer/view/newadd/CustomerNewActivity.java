package com.proje.mobilesales.features.customer.view.newadd;

import android.R;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.databinding.ActivityVisitBinding;
import com.proje.mobilesales.features.customer.model.CustomerNew;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.customer.repository.CustomerNewRepository;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
 
public final class CustomerNewActivity extends BaseErpActivity {
    public static final Companion Companion = new Companion(null);
    public static final String EXTRA_ADD_PHOTO = "EXTRA_ADD_PHOTO." + CustomerNewActivity.class.getName();
    public static final String EXTRA_CUSTOMER = "EXTRA_CUSTOMER." + CustomerNewActivity.class.getName();
    private static final String STATE_ADD_PHOTO = "state:mAddPhoto";
    private static final String STATE_CUSTOMER = "state:customer";
    private ActivityVisitBinding binding;
    private boolean mAddPhoto;
    public AlertDialogBuilder<?> mAlertDialogBuilder;
    private CustomerNew mCustomerNew;
    private final CustomerNewRepository repository;
    private final CustomerNewViewModel viewModel;

    public CustomerNewActivity() {
        final CustomerNewRepository customerNewRepository = new CustomerNewRepository();
        repository = customerNewRepository;
        viewModel = new CustomerNewViewModel(customerNewRepository);
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        final AlertDialogBuilder<?> alertDialogBuilder = mAlertDialogBuilder;
        if (null != alertDialogBuilder) {
            return alertDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mAlertDialogBuilder");
        return null;
    }

    public void setMAlertDialogBuilder(final AlertDialogBuilder<?> alertDialogBuilder) {
        Intrinsics.checkNotNullParameter(alertDialogBuilder, "<set-?>");
        mAlertDialogBuilder = alertDialogBuilder;
    }

    @Override // com.proje.mobilesales.core.base.BaseErpActivity, com.proje.mobilesales.core.base.BaseInjectableActivity, com.proje.mobilesales.core.base.BaseTrackableActivity, com.proje.mobilesales.core.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.getActivityComponent().inject(this);
        ActivityVisitBinding inflate = ActivityVisitBinding.inflate(this.getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        binding = inflate;
        if (null == inflate) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        this.setContentView(inflate.getRoot());
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.setMAlertDialogBuilder(new AlertDialogBuilder.Impl(this, (BaseInjectableActivity) activity));
        this.setToolbar();
        this.supportInvalidateOptionsMenu();
        if (null != bundle) {
            mAddPhoto = bundle.getBoolean(CustomerNewActivity.STATE_ADD_PHOTO, false);
            mCustomerNew = bundle.getParcelable(CustomerNewActivity.STATE_CUSTOMER);
        } else {
            mAddPhoto = this.getIntent().getBooleanExtra(CustomerNewActivity.EXTRA_ADD_PHOTO, false);
            final Intent intent = this.getIntent();
            final String str = CustomerNewActivity.EXTRA_CUSTOMER;
            if (intent.hasExtra(str)) {
                mCustomerNew = this.getIntent().getParcelableExtra(str);
            } else {
                final CustomerNew customerNew = new CustomerNew(this.generateCustomerCode());
                mCustomerNew = customerNew;
                Intrinsics.checkNotNull(customerNew);
                customerNew.setActive(Boolean.valueOf(viewModel.getBaseErp().getNewCustomerState()));
            }
        }
        if (mAddPhoto) {
            this.setTitle(R.string.activity_title_customer_add_image);
        } else {
            this.setTitle(R.string.activity_title_new_customer);
        }
        this.getSupportFragmentManager().beginTransaction().replace(R.id.list, this.instantiateFragment(), CustomerNewFragment.Companion.getCUSTOMER_NEW_TAG()).commit();
    }

    private Fragment instantiateFragment() {
        final Bundle bundle = new Bundle();
        bundle.putBoolean(CustomerNewActivity.EXTRA_ADD_PHOTO, mAddPhoto);
        bundle.putParcelable(CustomerNewActivity.EXTRA_CUSTOMER, mCustomerNew);
        final Fragment instantiate = Fragment.instantiate(this, CustomerNewFragment.class.getName(), bundle);
        Intrinsics.checkNotNullExpressionValue(instantiate, "instantiate(...)");
        return instantiate;
    }

    
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (16908332 == item.getItemId()) {
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
     public void onBackPressed() {
         super.onBackPressed();
         this.cancelFicheDialog();
    }

    private void cancelFicheDialog() {
        this.getMAlertDialogBuilder().setTitle(R.string.str_cancel_fiche_title).setMessage(R.string.str_return_customer_fiche_question).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.newadd.CustomerNewActivityExternalSyntheticLambda0
            public  void CustomerNewActivityExternalSyntheticLambda0() {
            } 
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                cancelFicheDialoglambda0(CustomerNewActivity.this, dialogInterface, i2);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { 
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                cancelFicheDialoglambda1(dialogInterface, i2);
            }
        }).setNeutralButton(R.string.str_save, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.newadd.CustomerNewActivityExternalSyntheticLambda2
            public void CustomerNewActivityExternalSyntheticLambda2() {
            } 
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                cancelFicheDialoglambda2(CustomerNewActivity.this, dialogInterface, i2);
            }
        }).create().show();
    }

    public static void cancelFicheDialoglambda0(final CustomerNewActivity this0, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        dialogInterface.dismiss();
    }

    public static void cancelFicheDialoglambda1(final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        dialogInterface.dismiss();
    }

    public static void cancelFicheDialoglambda2(final CustomerNewActivity this0, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        if (-3 == i2) {
            this0.getCustomerFragment().saveCustomer();
            dialogInterface.dismiss();
        }
    }
    protected void onSaveInstanceState(final Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putBoolean(CustomerNewActivity.STATE_ADD_PHOTO, mAddPhoto);
        outState.putParcelable(CustomerNewActivity.STATE_CUSTOMER, mCustomerNew);
        super.onSaveInstanceState(outState);
    }

    private CustomerNewFragment getCustomerFragment() {
        final Fragment findFragmentByTag = this.getSupportFragmentManager().findFragmentByTag(CustomerNewFragment.Companion.getCUSTOMER_NEW_TAG());
        Intrinsics.checkNotNull(findFragmentByTag, "null cannot be cast to non-null type com.proje.mobilesales.features.customer.view.newadd.CustomerNewFragment");
        return (CustomerNewFragment) findFragmentByTag;
    }

    private String generateCustomerCode() {
        for (int i2 = 0; 3 > i2; i2++) {
            final String createCustomerCode = this.createCustomerCode();
            final List<ClCard> tableForClCard = viewModel.getTableForClCard(ClCard.class, "CODE=?", new String[]{createCustomerCode});
            if (null == tableForClCard || tableForClCard.isEmpty()) {
                return createCustomerCode;
            }
        }
        return String.valueOf(System.currentTimeMillis());
    }

    private String createCustomerCode() {
        final Calendar calendar = Calendar.getInstance();
        int i2 = calendar.get(5);
        int i3 = calendar.get(2);
        int i4 = calendar.get(1);
        int i5 = calendar.get(10);
        int i6 = calendar.get(12);
        int i7 = calendar.get(13);
        final int i8 = calendar.get(14);
        if (0 == i2) {
            i2 = 1;
        }
        if (0 == i3) {
            i3 = 1;
        }
        if (0 == i4) {
            i4 = 1;
        }
        if (0 == i5) {
            i5 = 1;
        }
        if (0 == i6) {
            i6 = 1;
        }
        if (0 == i7) {
            i7 = 1;
        }
        final int i9 = 0 != i8 ? i8 : 1;
        int i10 = (i2 * 2) + (i3 * 2) + (i4 * 2) + (i5 * 2) + (i6 * 2) + (i7 * 2) + i2 + i3 + i4 + i5 + i6 + i7 + i9;
        if (0 > i10) {
            i10 *= -1;
        }
        final String str = "WYN_" + viewModel.getUserInformation().getCode() + '_' + StringUtils.getLastnCharacters(String.valueOf(i10 * i9 * i7), 16 - ("WYN_" + viewModel.getUserInformation().getCode() + '_').length());
        if (16 >= str.length()) {
            return str;
        }
        final String substring = str.substring(0, 16);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return substring;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(final int i2, final String[] permissions2, final int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions2, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i2, permissions2, grantResults);
    }

    /* compiled from: CustomerNewActivity.kt */
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
