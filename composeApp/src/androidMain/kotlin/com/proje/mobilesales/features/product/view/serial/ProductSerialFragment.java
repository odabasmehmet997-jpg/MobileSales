package com.proje.mobilesales.features.product.view.serial;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseFragment;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.utils.Connectivity;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.databinding.SerialInfoReportBinding;
import com.proje.mobilesales.features.product.model.database.ItemSerialInfo;
import com.proje.mobilesales.features.product.repository.ProductSerialRepository;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
 
public final class ProductSerialFragment extends BaseFragment {
    public static final Companion Companion = new Companion(null);
    public static final String PRODUCT_CODE = ProductSerialFragment.class.getName() + ".PRODUCT_CODE";
    public static final String STATE_ITEM_LOGICAL_REF = "state:mItemLogicalRef";
    private SerialInfoReportBinding _binding;
    private ProductSerialInfoAdapter mAdapter;
    private int mItemLogicalRef;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    private final ProductSerialRepository repository;
    private final ProductSerialViewModel viewModel;
    public ProductSerialFragment() {
        ProductSerialRepository productSerialRepository = null;
        this.repository = productSerialRepository;
        this.viewModel = new ProductSerialViewModel(productSerialRepository);
    }
    private   SerialInfoReportBinding getBinding() {
        SerialInfoReportBinding serialInfoReportBinding = this._binding;
        Intrinsics.checkNotNull(serialInfoReportBinding);
        return serialInfoReportBinding;
    }
    public   ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        if (progressDialogBuilder != null) {
            return progressDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
        return null;
    }
    public   void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        Intrinsics.checkNotNullParameter(progressDialogBuilder, "<set-?>");
        this.mProgressDialogBuilder = progressDialogBuilder;
    } 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        Context requireContext = requireContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMProgressDialogBuilder(new ProgressDialogBuilder.Impl(requireContext, (BaseInjectableActivity) activity));
        this.mAdapter = new ProductSerialInfoAdapter(requireContext());
    } 
    public void onActivityCreated(Bundle bundle) {
        ProgressDialogBuilder cancelable;
        super.onActivityCreated(bundle);
        if (bundle != null) {
            this.mItemLogicalRef = bundle.getInt(STATE_ITEM_LOGICAL_REF);
        } else {
            Bundle arguments = getArguments();
            Intrinsics.checkNotNull(arguments);
            this.mItemLogicalRef = arguments.getInt(PRODUCT_CODE);
        }
        View view = getView();
        Intrinsics.checkNotNull(view);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(this.mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        if (Connectivity.isConnected(getContext())) {
            ProgressDialogBuilder message = getMProgressDialogBuilder().setMessage(getString(R.string.str_please_wait));
            if (!(message == null || (cancelable = message.setCancelable(false)) == null)) {
                cancelable.show();
            }
            ProductSerialViewModel productSerialViewModel = this.viewModel;
            String itemCode = productSerialViewModel.getSqlHelper().getItemCode(this.mItemLogicalRef);
            Intrinsics.checkNotNullExpressionValue(itemCode, "getItemCode(...)");
            productSerialViewModel.getProductSerialInfo(itemCode, new ProductSerialInfoResponseListener(this));
            return;
        }
        Toast.makeText(getContext(), R.string.str_check_internet_connection, Toast.LENGTH_LONG).show();
    }
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        this._binding = SerialInfoReportBinding.inflate(layoutInflater, viewGroup, false);
        setHasOptionsMenu(true);
        LinearLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    private record ProductSerialInfoResponseListener(
            WeakReference<ProductSerialFragment> mProductSerialFragment) implements ResponseListener<ArrayList<ItemSerialInfo>> {
            private ProductSerialInfoResponseListener(ProductSerialFragment mProductSerialFragment) {
                Intrinsics.checkNotNullParameter(mProductSerialFragment, "productSerialFragment");
                this.mProductSerialFragment = new WeakReference<>(mProductSerialFragment);
            }

        public void onResponse(PrintSlipModel arrayList) {
                if (this.mProductSerialFragment.get() != null) {
                    ProductSerialFragment productSerialFragment = this.mProductSerialFragment.get();
                    Intrinsics.checkNotNull(productSerialFragment);
                    if (productSerialFragment.isAttached()) {
                        ProductSerialFragment productSerialFragment2 = this.mProductSerialFragment.get();
                        Intrinsics.checkNotNull(productSerialFragment2);
                        productSerialFragment2.onProductSerialLoad(arrayList);
                    }
                }
            }

        public void onError(String str) {
                Intrinsics.checkNotNullParameter(str, "errorMessage");
                if (this.mProductSerialFragment.get() != null) {
                    ProductSerialFragment productSerialFragment = this.mProductSerialFragment.get();
                    Intrinsics.checkNotNull(productSerialFragment);
                    if (productSerialFragment.isAttached()) {
                        Log.d(MobileSales.TAG, "onError: " + str);
                    }
                }
            }
        }
    public void onProductSerialLoad(ArrayList<ItemSerialInfo> arrayList) {
        ProductSerialInfoAdapter productSerialInfoAdapter;
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        Intrinsics.checkNotNull(appCompatActivity);
        ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        Context context = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context);
        supportActionBar.setTitle(context.getString(R.string.str_serial_report));
        getMProgressDialogBuilder().dismiss();
        if (arrayList != null && !arrayList.isEmpty() && (productSerialInfoAdapter = this.mAdapter) != null) {
            productSerialInfoAdapter.addItem(arrayList);
        }
    }
    public static final class Companion {
        public   Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
