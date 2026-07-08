package com.proje.mobilesales.features.customer.view.communication.person;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.view.SnappyLinearLayoutManager;
import com.proje.mobilesales.features.customer.model.CustomerDetail;
import com.proje.mobilesales.features.customer.model.CustomerProperty;
import com.proje.mobilesales.features.customer.view.general.CustomerGeneralRecyclerViewAdapter;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CustomerPersonActivity extends BaseErpActivity {
    public static final Companion Companion = new Companion(null);
    public static final String EXTRA_CUSTOMER_DETAIL = "extra:customerDetail";
    public static final String STATE_CUSTOMER_DETAIL = "state:customerDetail";
    public static final String STATE_CUSTOMER_PROPERTIES = "state:customerProperties";
    public CustomerGeneralRecyclerViewAdapter mAdapter;
    private CustomerDetail mCustomerDetail;
    private ArrayList<CustomerProperty> mCustomerProperties;
    public RecyclerView mRecyclerView;

    public CustomerGeneralRecyclerViewAdapter getMAdapter() {
        CustomerGeneralRecyclerViewAdapter customerGeneralRecyclerViewAdapter = this.mAdapter;
        if (customerGeneralRecyclerViewAdapter != null) {
            return customerGeneralRecyclerViewAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        return null;
    }

    public void setMAdapter(CustomerGeneralRecyclerViewAdapter customerGeneralRecyclerViewAdapter) {
        Intrinsics.checkNotNullParameter(customerGeneralRecyclerViewAdapter, "<set-?>");
        this.mAdapter = customerGeneralRecyclerViewAdapter;
    }

    public RecyclerView getMRecyclerView() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            return recyclerView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
        return null;
    }

    public void setMRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "<set-?>");
        this.mRecyclerView = recyclerView;
    }

    @Override // com.proje.mobilesales.core.base.BaseErpActivity, com.proje.mobilesales.core.base.BaseInjectableActivity, com.proje.mobilesales.core.base.BaseTrackableActivity, com.proje.mobilesales.core.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_customer_all_person);
        setToolbar();
        if (bundle != null) {
            this.mCustomerDetail = bundle.getParcelable("state:customerDetail");
            this.mCustomerProperties = bundle.getParcelableArrayList("state:customerProperties");
        } else {
            this.mCustomerDetail = getIntent().getParcelableExtra("extra:customerDetail");
        }
        View findViewById = findViewById(R.id.rcwList);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
        setMRecyclerView((RecyclerView) findViewById);
        getMRecyclerView().setLayoutManager(new SnappyLinearLayoutManager(this));
        getMRecyclerView().setHasFixedSize(true);
        setMAdapter(new CustomerGeneralRecyclerViewAdapter());
        getMAdapter().initDisplayOptions(this);
        getMRecyclerView().setAdapter(getMAdapter());
        if (this.mCustomerDetail != null) {
            load();
        }
    }

    private void load() {
        if (this.mCustomerProperties == null) {
            ArrayList<CustomerProperty> arrayList = new ArrayList<>();
            this.mCustomerProperties = arrayList;
            Intrinsics.checkNotNull(arrayList);
            String string = getString(R.string.str_customer_person_1);
            CustomerDetail customerDetail = this.mCustomerDetail;
            arrayList.add(new CustomerProperty(string, customerDetail != null ? customerDetail.getPerson() : null, false, false));
            ArrayList<CustomerProperty> arrayList2 = this.mCustomerProperties;
            Intrinsics.checkNotNull(arrayList2);
            String string2 = getString(R.string.str_customer_person_2);
            CustomerDetail customerDetail2 = this.mCustomerDetail;
            arrayList2.add(new CustomerProperty(string2, customerDetail2 != null ? customerDetail2.getPerson2() : null, false, false));
            ArrayList<CustomerProperty> arrayList3 = this.mCustomerProperties;
            Intrinsics.checkNotNull(arrayList3);
            String string3 = getString(R.string.str_customer_person_3);
            CustomerDetail customerDetail3 = this.mCustomerDetail;
            arrayList3.add(new CustomerProperty(string3, customerDetail3 != null ? customerDetail3.getPerson3() : null, false, false));
        }
        getMAdapter().setCustomerProperties(this.mCustomerProperties);
    }
     public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putParcelable("state:customerDetail", this.mCustomerDetail);
        outState.putParcelableArrayList("state:customerProperties", this.mCustomerProperties);
        super.onSaveInstanceState(outState);
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
