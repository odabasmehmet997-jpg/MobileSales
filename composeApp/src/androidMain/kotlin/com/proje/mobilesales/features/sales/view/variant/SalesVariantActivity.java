package com.proje.mobilesales.features.sales.view.variant;

import android.R;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.databinding.SalesVariantsBinding;
import com.proje.mobilesales.features.model.ItemVariantStock;
import com.proje.mobilesales.features.sales.model.SalesVariantCheck;
import com.proje.mobilesales.features.sales.repository.SalesVariantRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
 
public final class SalesVariantActivity extends BaseErpActivity {
    public static final String MULTI_VARIANT_LIST = "MULTI_VARIANT_LIST";
    private static final String STATE_ADAPTER = "state:adapter";
    private static final String STATE_DETAIL_ID = "state:salesDetailId";
    private static final String STATE_DIV_UNIT = "state:divUnit";
    private static final String STATE_ITEM_CODE = "state:itemCode";
    private static final String STATE_ITEM_REF = "state:itemRef";
    private static final String STATE_VARIANT_CODE = "state:variantCode";
    private static final String STATE_VARIANT_REF = "state:variantRef";
    private static final String STATE_WHREF = "state:whRef";
    private static final String TAG = "SalesVariantActivity";
    private final Lazy bindingdelegate;
    private AppCompatEditText edVariant;
    private AppCompatImageButton imgBtnClearSearch;
    private AppCompatImageButton imgBtnSearch;
    private LinearLayout linearProgress;
    private ListView lvVariants;
    private SalesVariantAdapter mAdapter;
    private AlertDialogBuilder<?> mAlertDialogBuilder;
    private long mDetailId;
    private boolean mDivUnit;
    private String mItemCode;
    private int mItemRef;
    private String mVariantCode;
    private List<? extends ItemVariantStock> mVariantList;
    private int mVariantRef;
    private int mWhNr;
    private final SalesVariantRepository repository;
    private AppCompatTextView tvCheckedCount;
    private AppCompatTextView tvUserAmount;
    private final SalesVariantViewModel viewModel;
    public static final Companion Companion = new Companion(null);
    public static final String EXTRA_ITEM_REF = SalesVariantActivity.class.getName() + ".EXTRA_ITEM_REF";
    public static final String EXTRA_ITEM_CODE = SalesVariantActivity.class.getName() + ".EXTRA_ITEM_CODE";
    public static final String EXTRA_VARIANT_REF = SalesVariantActivity.class.getName() + ".EXTRA_VARIANT_REF";
    public static final String EXTRA_VARIANT_CODE = SalesVariantActivity.class.getName() + ".EXTRA_VARIANT_CODE";
    public static final String EXTRA_DIV_UNIT = SalesVariantActivity.class.getName() + ".EXTRA_DIV_UNIT";
    public static final String EXTRA_DETAIL_ID = SalesVariantActivity.class.getName() + ".EXTRA_DETAIL_ID";
    public static final String EXTRA_WHNR = SalesVariantActivity.class.getName() + ".EXTRA_WHNR";
    public interface SalesVariantListener {
        void onSelectChangedListener(int i2);
    }
    public SalesVariantActivity() {
        final SalesVariantRepository salesVariantRepository = new SalesVariantRepository();
        repository = salesVariantRepository;
        viewModel = new SalesVariantViewModel(salesVariantRepository);
        bindingdelegate = LazyKt.lazy(new Function0<SalesVariantsBinding>() {
   
            public SalesVariantsBinding invoke() {
                return SalesVariantsBinding.inflate(getLayoutInflater());
            }
        });
    }

    private SalesVariantsBinding getBinding() {
        return (SalesVariantsBinding) bindingdelegate.getValue();
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        return mAlertDialogBuilder;
    }

    public void setMAlertDialogBuilder(final AlertDialogBuilder<?> alertDialogBuilder) {
        mAlertDialogBuilder = alertDialogBuilder;
    }
     public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
         this.getActivityComponent().inject(this);
         this.setContentView(this.getBinding().getRoot());
         this.setToolbar();
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mAlertDialogBuilder = new AlertDialogBuilder.Impl(this, (BaseInjectableActivity) activity);
        if (null != bundle) {
            mItemRef = bundle.getInt(SalesVariantActivity.STATE_ITEM_REF, -1);
            mVariantRef = bundle.getInt(SalesVariantActivity.STATE_VARIANT_REF, -1);
            mDivUnit = bundle.getBoolean(SalesVariantActivity.STATE_DIV_UNIT, false);
            mDetailId = bundle.getLong(SalesVariantActivity.STATE_DETAIL_ID, -1L);
            mWhNr = bundle.getInt(SalesVariantActivity.STATE_WHREF, -1);
            mItemCode = bundle.getString(SalesVariantActivity.STATE_ITEM_CODE);
            mVariantCode = bundle.getString(SalesVariantActivity.STATE_VARIANT_CODE);
            final SalesVariantAdapter salesVariantAdapter = mAdapter;
            if (null != salesVariantAdapter) {
                Intrinsics.checkNotNull(salesVariantAdapter);
                salesVariantAdapter.saveState(bundle.getBundle("state:adapter"));
            }
        } else {
            mItemRef = this.getIntent().getIntExtra(SalesVariantActivity.EXTRA_ITEM_REF, -1);
            mVariantRef = this.getIntent().getIntExtra(SalesVariantActivity.EXTRA_VARIANT_REF, -1);
            mDivUnit = this.getIntent().getBooleanExtra(SalesVariantActivity.EXTRA_DIV_UNIT, false);
            mDetailId = this.getIntent().getLongExtra(SalesVariantActivity.EXTRA_DETAIL_ID, -1L);
            mWhNr = this.getIntent().getIntExtra(SalesVariantActivity.EXTRA_WHNR, -1);
            mItemCode = this.getIntent().getStringExtra(SalesVariantActivity.EXTRA_ITEM_CODE);
            mVariantCode = this.getIntent().getStringExtra(SalesVariantActivity.EXTRA_VARIANT_CODE);
        }
        mAdapter = new SalesVariantAdapter(this, new SalesVariantListener() {
            public void onSelectChangedListener(final int i2) {
                final AppCompatTextView appCompatTextView;
                appCompatTextView = tvCheckedCount;
                Intrinsics.checkNotNull(appCompatTextView);
                appCompatTextView.setText(String.valueOf(i2));
            }
        }, mDivUnit);
         this.initialize();
        final ListView listView = lvVariants;
        Intrinsics.checkNotNull(listView);
        listView.setAdapter(mAdapter);
         this.loadItemVariants();
        final LinearLayout linearLayout = linearProgress;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(View.VISIBLE);
         this.loadSalesVariantChecks();
        final LinearLayout linearLayout2 = linearProgress;
        Intrinsics.checkNotNull(linearLayout2);
        linearLayout2.setVisibility(View.INVISIBLE);
         this.setTitle(R.string.str_variant);
    }

    
    public boolean onCreateOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        final MenuInflater menuInflater = this.getMenuInflater();
        Intrinsics.checkNotNullExpressionValue(menuInflater, "getMenuInflater(...)");
        menuInflater.inflate(R.menu.menu_serial_lot, menu);
        menu.findItem(R.id.menu_range_select).setVisible(false);
        menu.findItem(R.id.menu_barcode).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    
    public boolean onPrepareOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        final MenuItem findItem = menu.findItem(R.id.menu_select_all);
        final SalesVariantAdapter salesVariantAdapter = mAdapter;
        Intrinsics.checkNotNull(salesVariantAdapter);
        findItem.setTitle(salesVariantAdapter.isAllChecked() ? R.string.menu_select_all : R.string.menu_unselect_all);
        return super.onPrepareOptionsMenu(menu);
    }

    
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        final int itemId = item.getItemId();
        if (16908332 == itemId) {
            this.onBackPressed();
            return true;
        }
        if (R.id.menu_select_all == itemId) {
            this.selectAll();
        }
        return super.onOptionsItemSelected(item);
    }
 
    public void onBackPressed() {
        super.onBackPressed();
        this.checkVariants();
    }

    private void checkVariants() {
        final ArrayList<SalesVariantCheck> checkedItems = this.getCheckedItems();
        final Iterator<SalesVariantCheck> it = checkedItems.iterator();
        while (it.hasNext()) {
            final ItemVariantStock mVariant = it.next().getMVariant();
            Intrinsics.checkNotNull(mVariant);
            if (0.0d == mVariant.getAmount()) {
                final AlertDialogBuilder<?> alertDialogBuilder = mAlertDialogBuilder;
                Intrinsics.checkNotNull(alertDialogBuilder);
                alertDialogBuilder.setMessage(this.getString(R.string.str_amount_could_not_be_zero_for_selected)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.variant.SalesVariantActivityExternalSyntheticLambda0
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(final DialogInterface dialogInterface, final int i2) {
                        checkVariantslambda0(dialogInterface, i2);
                    }
                }).create().show();
                return;
            }
        }
        if (0 < checkedItems.size()) {
            final Intent intent = new Intent();
            intent.putParcelableArrayListExtra(SalesVariantActivity.MULTI_VARIANT_LIST, checkedItems);
            this.setResult(-1, intent);
        } else {
            this.setResult(0);
        }
        this.finish();
    }

    public static void checkVariantslambda0(final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    private ArrayList<SalesVariantCheck> getCheckedItems() {
        final ArrayList<SalesVariantCheck> arrayList = new ArrayList<>();
        final SalesVariantAdapter salesVariantAdapter = mAdapter;
        Intrinsics.checkNotNull(salesVariantAdapter);
        if (0 != salesVariantAdapter.getCount()) {
            final SalesVariantAdapter salesVariantAdapter2 = mAdapter;
            Intrinsics.checkNotNull(salesVariantAdapter2);
            final ArrayList<SalesVariantCheck> items = salesVariantAdapter2.getItems();
            Intrinsics.checkNotNull(items);
            if (0 != items.size()) {
                final SalesVariantAdapter salesVariantAdapter3 = mAdapter;
                Intrinsics.checkNotNull(salesVariantAdapter3);
                final ArrayList<SalesVariantCheck> items2 = salesVariantAdapter3.getItems();
                Intrinsics.checkNotNull(items2);
                final Iterator<SalesVariantCheck> it = items2.iterator();
                while (it.hasNext()) {
                    final SalesVariantCheck next = it.next();
                    if (next.isChecked()) {
                        arrayList.add(next);
                    }
                }
            }
        }
        return arrayList;
    }

    private void selectAll() {
        final SalesVariantAdapter salesVariantAdapter = mAdapter;
        Intrinsics.checkNotNull(salesVariantAdapter);
        if (salesVariantAdapter.isAllChecked()) {
            final SalesVariantAdapter salesVariantAdapter2 = mAdapter;
            Intrinsics.checkNotNull(salesVariantAdapter2);
            salesVariantAdapter2.selectAll(true);
        } else {
            final SalesVariantAdapter salesVariantAdapter3 = mAdapter;
            Intrinsics.checkNotNull(salesVariantAdapter3);
            salesVariantAdapter3.selectAll(false);
        }
    }

    private void initialize() {
        lvVariants = this.getBinding().lvVariants;
        edVariant = this.getBinding().edVariant;
        final AppCompatImageButton appCompatImageButton = this.getBinding().imgBtnSearch;
        imgBtnSearch = appCompatImageButton;
        Intrinsics.checkNotNull(appCompatImageButton);
        appCompatImageButton.setOnClickListener(new View.OnClickListener() {  
            public void onClick(final View view) {
                initializelambda1(SalesVariantActivity.this, view);
            }
        });
        final AppCompatImageButton appCompatImageButton2 = this.findViewById(R.id.imgBtnClearSearch);
        imgBtnClearSearch = appCompatImageButton2;
        Intrinsics.checkNotNull(appCompatImageButton2);
        appCompatImageButton2.setOnClickListener(new View.OnClickListener() {   
            public void onClick(final View view) {
                initializelambda2(SalesVariantActivity.this, view);
            }
        });
        linearProgress = this.getBinding().linearProgress;
        tvCheckedCount = this.getBinding().tvCheckedCount;
        tvUserAmount = this.getBinding().tvUserAmount;
    }

    public static void initializelambda1(final SalesVariantActivity this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final LinearLayout linearLayout = this0.linearProgress;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(View.VISIBLE);
        this0.searchVariants();
        final LinearLayout linearLayout2 = this0.linearProgress;
        Intrinsics.checkNotNull(linearLayout2);
        linearLayout2.setVisibility(View.INVISIBLE);
    }

    public static void initializelambda2(final SalesVariantActivity this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final LinearLayout linearLayout = this0.linearProgress;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(View.VISIBLE);
        final AppCompatEditText appCompatEditText = this0.edVariant;
        Intrinsics.checkNotNull(appCompatEditText);
        appCompatEditText.setText("");
        this0.searchVariants();
        final LinearLayout linearLayout2 = this0.linearProgress;
        Intrinsics.checkNotNull(linearLayout2);
        linearLayout2.setVisibility(View.INVISIBLE);
    }

    private void loadSalesVariantChecks() {
        final ArrayList<SalesVariantCheck> arrayList = new ArrayList<>();
        final List<? extends ItemVariantStock> list = mVariantList;
        Intrinsics.checkNotNull(list);
        if (list.isEmpty()) {
            return;
        }
        final List<? extends ItemVariantStock> list2 = mVariantList;
        Intrinsics.checkNotNull(list2);
        for (final ItemVariantStock itemVariantStock : list2) {
            final ItemVariantStock itemVariantStock2 = new ItemVariantStock();
            itemVariantStock2.setVarintCode(itemVariantStock.getVarintCode());
            itemVariantStock2.setVariantName(null == itemVariantStock.getVariantName() ? "" : itemVariantStock.getVariantName());
            itemVariantStock2.setItemRef(mItemRef);
            itemVariantStock2.setItemCode(mItemCode);
            itemVariantStock2.setVariantRef(itemVariantStock.getVariantRef());
            itemVariantStock2.setAmount(0.0d);
            itemVariantStock2.setWareHouseNr(itemVariantStock.getWareHouseNr());
            itemVariantStock2.setVariantRealStok(itemVariantStock.getVariantRealStok());
            itemVariantStock2.setVariantActualStok(itemVariantStock.getVariantActualStok());
            final SalesVariantCheck salesVariantCheck = new SalesVariantCheck(false, null, false, false, 0L, 31, null);
            salesVariantCheck.setMVariant(itemVariantStock2);
            salesVariantCheck.setChecked(false);
            salesVariantCheck.setEnabled(true);
            salesVariantCheck.setDivUnit(mDivUnit);
            salesVariantCheck.setDetailId(mDetailId);
            arrayList.add(salesVariantCheck);
        }
        final SalesVariantAdapter salesVariantAdapter = mAdapter;
        Intrinsics.checkNotNull(salesVariantAdapter);
        salesVariantAdapter.setList(arrayList);
    }

    private void loadItemVariants() {
        final SalesVariantViewModel salesVariantViewModel = viewModel;
        final int i2 = mItemRef;
        final int i3 = mWhNr;
        final String str = mItemCode;
        Intrinsics.checkNotNull(str);
        mVariantList = salesVariantViewModel.getVariantInfo(i2, i3, str);
    }

    private void searchVariants() {
        final ArrayList<SalesVariantCheck> arrayList = new ArrayList<>();
        final ArrayList<SalesVariantCheck> arrayList2 = new ArrayList<>();
        final AppCompatEditText appCompatEditText = edVariant;
        Intrinsics.checkNotNull(appCompatEditText);
        final String valueOf = String.valueOf(appCompatEditText.getText());
        int length = valueOf.length() - 1;
        int i2 = 0;
        boolean z = false;
        while (i2 <= length) {
            final boolean z2 = 0 >= Intrinsics.compare(valueOf.charAt(!z ? i2 : length), 32);
            if (z) {
                if (!z2) {
                    break;
                } else {
                    length--;
                }
            } else if (z2) {
                i2++;
            } else {
                z = true;
            }
        }
        final String obj = valueOf.subSequence(i2, length + 1).toString();
        final Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
        final String upperCase = obj.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        final List<? extends ItemVariantStock> list = mVariantList;
        Intrinsics.checkNotNull(list);
        for (final ItemVariantStock itemVariantStock : list) {
            if (!TextUtils.isEmpty(upperCase)) {
                if (null != itemVariantStock.getVariantName()) {
                    final String variantName = itemVariantStock.getVariantName();
                    Intrinsics.checkNotNullExpressionValue(variantName, "getVariantName(...)");
                    final Locale locale2 = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale2, "getDefault(...)");
                    final String upperCase2 = variantName.toUpperCase(locale2);
                    Intrinsics.checkNotNullExpressionValue(upperCase2, "toUpperCase(...)");
                    if (StringsKt.contains(upperCase2, upperCase, false)) {
                        this.addVariantCheckToList(arrayList, itemVariantStock, true, false);
                    }
                }
                if (null != itemVariantStock.getVarintCode()) {
                    final String varintCode = itemVariantStock.getVarintCode();
                    Intrinsics.checkNotNullExpressionValue(varintCode, "getVarintCode(...)");
                    final Locale locale3 = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale3, "getDefault(...)");
                    final String upperCase3 = varintCode.toUpperCase(locale3);
                    Intrinsics.checkNotNullExpressionValue(upperCase3, "toUpperCase(...)");
                    if (StringsKt.contains(upperCase3, upperCase, false)) {
                        this.addVariantCheckToList(arrayList, itemVariantStock, true, false);
                    }
                }
                this.addVariantCheckToList(arrayList2, itemVariantStock, false, true);
            } else {
                this.addVariantCheckToList(arrayList, itemVariantStock, true, true);
            }
        }
        final Iterator<SalesVariantCheck> it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        final SalesVariantAdapter salesVariantAdapter = mAdapter;
        Intrinsics.checkNotNull(salesVariantAdapter);
        salesVariantAdapter.setList(arrayList);
    }

    private void addVariantCheckToList(final ArrayList<SalesVariantCheck> arrayList, final ItemVariantStock itemVariantStock, final boolean z, final boolean z2) {
        final double d2;
        final SalesVariantAdapter salesVariantAdapter = mAdapter;
        Intrinsics.checkNotNull(salesVariantAdapter);
        final SalesVariantCheck findItem = salesVariantAdapter.findItem(itemVariantStock.getVariantRef());
        final ItemVariantStock itemVariantStock2 = new ItemVariantStock();
        itemVariantStock2.setVarintCode(itemVariantStock.getVarintCode());
        itemVariantStock2.setVariantName(null == itemVariantStock.getVariantName() ? "" : itemVariantStock.getVariantName());
        itemVariantStock2.setItemRef(mItemRef);
        itemVariantStock2.setItemCode(mItemCode);
        itemVariantStock2.setVariantRef(itemVariantStock.getVariantRef());
        if (null != findItem) {
            final ItemVariantStock mVariant = findItem.getMVariant();
            Intrinsics.checkNotNull(mVariant);
            d2 = mVariant.getAmount();
        } else {
            d2 = 0.0d;
        }
        itemVariantStock2.setAmount(d2);
        itemVariantStock2.setVariantRealStok(itemVariantStock.getVariantRealStok());
        itemVariantStock2.setVariantActualStok(itemVariantStock.getVariantActualStok());
        final SalesVariantCheck salesVariantCheck = new SalesVariantCheck(false, null, false, false, 0L, 31, null);
        salesVariantCheck.setMVariant(itemVariantStock2);
        salesVariantCheck.setChecked(null != findItem && findItem.isChecked());
        salesVariantCheck.setEnabled(z);
        salesVariantCheck.setDivUnit(mDivUnit);
        salesVariantCheck.setDetailId(mDetailId);
        if (!salesVariantCheck.isChecked() || !z2) {
            arrayList.add(salesVariantCheck);
        } else {
            arrayList.add(this.getLastCheckedItemIndex(arrayList), salesVariantCheck);
        }
    }

    public int getLastCheckedItemIndex(final ArrayList<SalesVariantCheck> filteredList) {
        Intrinsics.checkNotNullParameter(filteredList, "filteredList");
        final int size = filteredList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!filteredList.get(i2).component1()) {
                return i2;
            }
        }
        return filteredList.size();
    }
     public void onSaveInstanceState(final Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putLong(SalesVariantActivity.STATE_DETAIL_ID, mDetailId);
        outState.putBoolean(SalesVariantActivity.STATE_DIV_UNIT, mDivUnit);
        outState.putInt(SalesVariantActivity.STATE_ITEM_REF, mItemRef);
        outState.putInt(SalesVariantActivity.STATE_VARIANT_REF, mVariantRef);
        outState.putInt(SalesVariantActivity.STATE_WHREF, mWhNr);
        outState.putString(SalesVariantActivity.STATE_VARIANT_CODE, mVariantCode);
        outState.putString(SalesVariantActivity.STATE_ITEM_CODE, mItemCode);
        final SalesVariantAdapter salesVariantAdapter = mAdapter;
        if (null != salesVariantAdapter) {
            Intrinsics.checkNotNull(salesVariantAdapter);
            outState.putBundle("state:adapter", salesVariantAdapter.outState());
        }
    } 
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
