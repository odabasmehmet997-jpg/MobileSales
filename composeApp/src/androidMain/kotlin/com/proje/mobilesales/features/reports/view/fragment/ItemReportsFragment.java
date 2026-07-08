package com.proje.mobilesales.features.reports.view.fragment;

import android.R;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseFragment;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.databinding.FragmentItemReportsBinding;
import com.proje.mobilesales.features.product.model.ProductInfo;
import com.proje.mobilesales.features.product.view.detail.ProductDetailFragment;
import com.proje.mobilesales.features.product.view.serial.ProductSerialFragment;
import com.proje.mobilesales.features.reports.repository.ItemReportsRepository;
import com.proje.mobilesales.features.reports.viewmodel.ItemReportsViewModel;
import com.proje.mobilesales.features.userreport.model.database.UserReports;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;

public final class ItemReportsFragment extends BaseFragment {
    public static final Companion Companion = new Companion(null);
    private FragmentItemReportsBinding binding;
    List<UserReports> itemReports;
    private String previousTitle;
    ProductInfo productInfo;
    private final ItemReportsRepository repository;
    final ItemReportsViewModel viewModel;

    public static ItemReportsFragment newInstance(final ProductInfo productInfo) {
        return ItemReportsFragment.Companion.newInstance(productInfo);
    }

    public ItemReportsFragment() {
        final ItemReportsRepository itemReportsRepository = new ItemReportsRepository();
        repository = itemReportsRepository;
        viewModel = new ItemReportsViewModel(itemReportsRepository);
        previousTitle = "";
        itemReports = new ArrayList();
    } 
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final Bundle arguments = this.getArguments();
        if (null != arguments) {
            productInfo = arguments.getParcelable("arg_product_info");
        }
        this.getActivityComponent().inject(this);
    } 
    public View onCreateView(final LayoutInflater inflater, final ViewGroup viewGroup, final Bundle bundle) {
        String itemCode;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        final FragmentActivity activity = this.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
        final ActionBar supportActionBar = ((AppCompatActivity) activity).getSupportActionBar();
        FragmentItemReportsBinding fragmentItemReportsBinding = null;
        previousTitle = String.valueOf(null != supportActionBar ? supportActionBar.getTitle() : null);
        final FragmentActivity activity2 = this.getActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
        final ActionBar supportActionBar2 = ((AppCompatActivity) activity2).getSupportActionBar();
        if (null != supportActionBar2) {
            supportActionBar2.setTitle(this.getString(R.string.str_stock_reports));
        }
        this.setHasOptionsMenu(true);
        FragmentItemReportsBinding inflate = FragmentItemReportsBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        binding = inflate;
        if (null == inflate) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        final TextView textView = inflate.itemCode;
        final ProductInfo productInfo = this.productInfo;
        final String itemName = null != productInfo ? productInfo.getItemName() : null;
        if (null == itemName || 0 == itemName.length()) {
            final ProductInfo productInfo2 = this.productInfo;
            if (null != productInfo2) {
                itemCode = productInfo2.getItemCode();
            }
            itemCode = null;
        } else {
            final ProductInfo productInfo3 = this.productInfo;
            if (null != productInfo3) {
                itemCode = productInfo3.getItemName();
            }
            itemCode = null;
        }
        textView.setText(itemCode);
        FragmentItemReportsBinding fragmentItemReportsBinding2 = binding;
        if (null == fragmentItemReportsBinding2) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentItemReportsBinding2 = null;
        }
        fragmentItemReportsBinding2.swiperefresh.setColorSchemeResources(R.color.white);
        FragmentItemReportsBinding fragmentItemReportsBinding3 = binding;
        if (null == fragmentItemReportsBinding3) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentItemReportsBinding3 = null;
        }
        fragmentItemReportsBinding3.swiperefresh.setProgressBackgroundColorSchemeResource(R.color.redA200);
        FragmentItemReportsBinding fragmentItemReportsBinding4 = binding;
        if (null == fragmentItemReportsBinding4) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentItemReportsBinding4 = null;
        }
        fragmentItemReportsBinding4.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.proje.mobilesales.features.reports.view.fragment.ItemReportsFragmentExternalSyntheticLambda1
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                onCreateViewlambda1(ItemReportsFragment.this);
            }
        });
        this.loadItemReportsFromLocal();
        final FragmentItemReportsBinding fragmentItemReportsBinding5 = binding;
        if (null == fragmentItemReportsBinding5) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentItemReportsBinding = fragmentItemReportsBinding5;
        }
        final View root = fragmentItemReportsBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    } 
    public static void onCreateViewlambda1(final ItemReportsFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.setRefreshing(true);
        this0.updateUserReportsOnline();
    }
    private void setRefreshing(final boolean z) {
        FragmentItemReportsBinding fragmentItemReportsBinding = binding;
        FragmentItemReportsBinding fragmentItemReportsBinding2 = null;
        if (null == fragmentItemReportsBinding) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentItemReportsBinding = null;
        }
        if (fragmentItemReportsBinding.swiperefresh.isRefreshing()) {
            if (z) {
                return;
            }
            final FragmentItemReportsBinding fragmentItemReportsBinding3 = binding;
            if (null == fragmentItemReportsBinding3) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentItemReportsBinding2 = fragmentItemReportsBinding3;
            }
            fragmentItemReportsBinding2.swiperefresh.setRefreshing(false);
            return;
        }
        if (z) {
            final FragmentItemReportsBinding fragmentItemReportsBinding4 = binding;
            if (null == fragmentItemReportsBinding4) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentItemReportsBinding2 = fragmentItemReportsBinding4;
            }
            fragmentItemReportsBinding2.swiperefresh.setRefreshing(z);
        }
    } 
    protected void createOptionsMenu(final Menu menu, final MenuInflater menuInflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        super.createOptionsMenu(menu, menuInflater);
        menu.clear();
    } 
    protected void prepareOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        super.prepareOptionsMenu(menu);
        menu.clear();
    } 
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (16908332 == item.getItemId()) {
            final FragmentActivity activity = this.getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
            final ActionBar supportActionBar = ((AppCompatActivity) activity).getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle(this.getString(R.string.str_stock_reports));
            }
        }
        return super.onOptionsItemSelected(item);
    } 
    public void onAttach(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
    } 
    public void onDetach() {
        final FragmentActivity activity = this.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
        final ActionBar supportActionBar = ((AppCompatActivity) activity).getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(previousTitle);
        }
        super.onDetach();
    } 
    public void onSaveInstanceState(final Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putParcelable("arg_product_info", productInfo);
    }

    public void loadItemReportsFromLocal() {
        this.setRefreshing(true);
        final LifecycleOwner viewLifecycleOwner = this.getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        BuildersKt.launch(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new ItemReportsFragmentloadItemReportsFromLocal1(this, null));
    }

    private void updateUserReportsOnline() {
        viewModel.getReportOnline(new ReportOnLineListener(new WeakReference(this)));
    }
    public static final class ReportOnLineListener implements ResponseListener<Boolean> {
        private final WeakReference<ItemReportsFragment> activityReference;

        public ReportOnLineListener(final WeakReference<ItemReportsFragment> activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            activityReference = activity;
        }
        public void onResponse(final PrintSlipModel bool) {
            final ItemReportsFragment itemReportsFragment = activityReference.get();
            if (null != itemReportsFragment) {
                itemReportsFragment.loadItemReportsFromLocal();
            }
        }
        public void onError(final String str) {
            String str2 = str;
            if (null != this.activityReference.get()) {
                if (null == str) {
                    final ItemReportsFragment itemReportsFragment = activityReference.get();
                    Intrinsics.checkNotNull(itemReportsFragment);
                    str2 = (String) itemReportsFragment.getText(R.string.exp_10_transfer_get_error);
                }
                Intrinsics.checkNotNull(str2);
                final ItemReportsFragment itemReportsFragment2 = activityReference.get();
                Intrinsics.checkNotNull(itemReportsFragment2);
                Toast.makeText(itemReportsFragment2.requireActivity(), str2, Toast.LENGTH_LONG).show();
            }
        }
    }
    public void showData(List<UserReports> list) {
        if (null == list || list.isEmpty()) {
            Toast.makeText(this.getActivity(), this.getText(R.string.str_data_not_found), Toast.LENGTH_SHORT).show();
            return;
        }
        final FragmentActivity requireActivity = this.requireActivity();
        final ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (final UserReports userReports : list) {
            Intrinsics.checkNotNull(userReports);
            arrayList.add(userReports.reportName);
        }
        final ArrayAdapter arrayAdapter = new ArrayAdapter(requireActivity, R.layout.simple_list_item_1, arrayList);
        FragmentItemReportsBinding fragmentItemReportsBinding = binding;
        FragmentItemReportsBinding fragmentItemReportsBinding2 = null;
        if (fragmentItemReportsBinding != null) {
            fragmentItemReportsBinding2 = fragmentItemReportsBinding;
        }
        fragmentItemReportsBinding2.list.setAdapter(arrayAdapter);
        this.setRefreshing(false);
        final FragmentItemReportsBinding fragmentItemReportsBinding3 = binding;
        if (fragmentItemReportsBinding3 != null) {
            fragmentItemReportsBinding2 = fragmentItemReportsBinding3;
        }
        fragmentItemReportsBinding2.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {  
            public void onItemClick(final AdapterView adapterView, final View view, final int i2, final long j2) {
                showDatalambda8(ItemReportsFragment.this, list, adapterView, view, i2, j2);
            }
        });
    } 
    public static void showDatalambda8(final ItemReportsFragment this0, final List userReports, final AdapterView adapterView, final View view, final int i2, final long j2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(userReports, "userReports");
        final UserReports userReports2 = (UserReports) userReports.get(i2);
        if (userReports2 != null) {
            if (i2 == 0) {
                final Bundle bundle = new Bundle();
                final ProductInfo productInfo = this0.productInfo;
                if (productInfo != null) {
                    bundle.putInt(ProductDetailFragment.PRODUCT_CODE, productInfo.getItemRef());
                }
                final ProductInfo productInfo2 = this0.productInfo;
                if (productInfo2 != null) {
                    bundle.putBoolean(ProductDetailFragment.PRODUCT_ISSERVICE, productInfo2.isService());
                }
                this0.requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.constLayout, ProductDetailFragment.class, bundle, "PRODUCT DETAILS").addToBackStack("ITEM REPORTS").commit();
                return;
            }
            if (this0.viewModel.erpType() == ErpType.NETSIS && i2 == 1) {
                final Bundle bundle2 = new Bundle();
                final ProductInfo productInfo3 = this0.productInfo;
                if (productInfo3 != null) {
                    bundle2.putInt(ProductSerialFragment.PRODUCT_CODE, productInfo3.getItemRef());
                }
                this0.requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.constLayout, ProductSerialFragment.class, bundle2, "PRODUCT SERIAL").addToBackStack("ITEM REPORTS").commit();
                return;
            }
            this0.openReport(userReports2);
        }
    }
    private void openReport(final UserReports userReports) {
        final LifecycleOwner viewLifecycleOwner = this.getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        BuildersKt.launch(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new ItemReportsFragmentopenReport1(this, userReports, null));
    }
    public static final class Companion {
        public  Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public ItemReportsFragment newInstance(final ProductInfo productInfo) {
            Intrinsics.checkNotNullParameter(productInfo, "productInfo");
            final ItemReportsFragment itemReportsFragment = new ItemReportsFragment();
            final Bundle bundle = new Bundle();
            bundle.putParcelable("arg_product_info", productInfo);
            itemReportsFragment.setArguments(bundle);
            return itemReportsFragment;
        }
    }
}
