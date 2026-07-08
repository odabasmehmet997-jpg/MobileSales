package com.proje.mobilesales.features.customer.view.communication.person;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.view.SnappyLinearLayoutManager;
import com.proje.mobilesales.features.customer.model.database.ClCardIncharge;
import com.proje.mobilesales.features.customer.repository.CustomerPersonRepository;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CustomerNetPersonActivity extends BaseErpActivity {
    public static final Companion Companion = new Companion(null);
    public static final String STATE_CUSTOMER_REF = "state:customerRef";
    private int clRef;
    private List<ClCardIncharge> inchargeList;
    private CustomerNetPersonRVAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private final CustomerPersonRepository repository;
    private final CustomerPersonViewModel viewModel;
    public CustomerNetPersonActivity() {
        CustomerPersonRepository customerPersonRepository = new CustomerPersonRepository();
        this.repository = customerPersonRepository;
        this.viewModel = new CustomerPersonViewModel(customerPersonRepository);
    }
     public void onCreate(Bundle bundle) {
        int intExtra;
        super.onCreate(bundle);
        setContentView(R.layout.activity_customer_net_person);
        setToolbar();
        if (bundle != null) {
            intExtra = bundle.getInt("state:customerRef", 0);
        } else {
            intExtra = getIntent().getIntExtra(IntentExtraName.EXTRA_CUSTOMER_REF, 0);
        }
        this.clRef = intExtra;
        load();
    }

    private void load() {
        View findViewById = findViewById(R.id.rcwList);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        RecyclerView recyclerView = (RecyclerView) findViewById;
        this.mRecyclerView = recyclerView;
        CustomerNetPersonRVAdapter customerNetPersonRVAdapter = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
            recyclerView = null;
        }
        recyclerView.setLayoutManager(new SnappyLinearLayoutManager(this));
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
            recyclerView2 = null;
        }
        recyclerView2.setHasFixedSize(true);
        CustomerNetPersonRVAdapter customerNetPersonRVAdapter2 = new CustomerNetPersonRVAdapter();
        this.mAdapter = customerNetPersonRVAdapter2;
        customerNetPersonRVAdapter2.initDisplayOptions(this);
        RecyclerView recyclerView3 = this.mRecyclerView;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
            recyclerView3 = null;
        }
        CustomerNetPersonRVAdapter customerNetPersonRVAdapter3 = this.mAdapter;
        if (customerNetPersonRVAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            customerNetPersonRVAdapter3 = null;
        }
        recyclerView3.setAdapter(customerNetPersonRVAdapter3);
        CustomerPersonViewModel customerPersonViewModel = this.viewModel;
        String clCode = customerPersonViewModel.getSqlHelper().getClCode(this.clRef);
        Intrinsics.checkNotNullExpressionValue(clCode, "getClCode(...)");
        this.inchargeList = customerPersonViewModel.getTableForClCardIncharge(ClCardIncharge.class, "CLCODE=? AND ACTIVE=?", new String[]{clCode, BuildConfig.NETSIS_DEMO_PASSWORD});
        CustomerNetPersonRVAdapter customerNetPersonRVAdapter4 = this.mAdapter;
        if (customerNetPersonRVAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        } else {
            customerNetPersonRVAdapter = customerNetPersonRVAdapter4;
        }
        List<ClCardIncharge> list = this.inchargeList;
        Intrinsics.checkNotNull(list);
        customerNetPersonRVAdapter.setMIncharges(list);
    }
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putInt("state:customerRef", this.clRef);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    public static final class Companion {
        public   Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
