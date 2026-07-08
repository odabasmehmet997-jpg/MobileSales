package com.proje.mobilesales.features.customer.view.route;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.interfaces.GetLoaderSqlText;
import com.proje.mobilesales.core.interfaces.OnLoadMoreListener;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;
import com.proje.mobilesales.databinding.ActivityRouteListBinding;
import com.proje.mobilesales.features.customer.model.Customer;
import com.proje.mobilesales.features.customer.repository.CustomerRouteRepository;
import io.reactivex.disposables.Disposable;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
 
public final class CustomerRouteListActivity extends BaseErpActivity implements View.OnClickListener, GetLoaderSqlText {
    private ActivityRouteListBinding binding;
    private final DatePickerDialog.OnDateSetListener date;
    private AppCompatImageButton imageButtonGetData;
    private LinearLayout linearDateTimeFirst;
    private CustomerRouteRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Disposable mSubscriptionGetCustomer;
    private AppBarSwipeRefreshLayout mSwipeRefreshLayout;
    private Calendar myCalender;
    private final CustomerRouteRepository repository;
    private AppCompatTextView txtDateTimeFirst;
    private final CustomerRouteViewModel viewModel;

    public CustomerRouteListActivity() {
        final CustomerRouteRepository customerRouteRepository = new CustomerRouteRepository();
        repository = customerRouteRepository;
        viewModel = new CustomerRouteViewModel(customerRouteRepository);
        mAdapter = new CustomerRouteRecyclerViewAdapter();
        date = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(final DatePicker datePicker, final int i2, final int i3, final int i4) {
                _init_lambda0(CustomerRouteListActivity.this, datePicker, i2, i3, i4);
            }
        };
    }

    public AppBarSwipeRefreshLayout getMSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }

    public void setMSwipeRefreshLayout(final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout) {
        mSwipeRefreshLayout = appBarSwipeRefreshLayout;
    }

    public CustomerRouteRecyclerViewAdapter getMAdapter() {
        return mAdapter;
    }

    public void setMAdapter(final CustomerRouteRecyclerViewAdapter customerRouteRecyclerViewAdapter) {
        Intrinsics.checkNotNullParameter(customerRouteRecyclerViewAdapter, "<set-?>");
        mAdapter = customerRouteRecyclerViewAdapter;
    }

    public AppCompatTextView getTxtDateTimeFirst() {
        return txtDateTimeFirst;
    }

    public void setTxtDateTimeFirst(final AppCompatTextView appCompatTextView) {
        txtDateTimeFirst = appCompatTextView;
    }

    public LinearLayout getLinearDateTimeFirst() {
        return linearDateTimeFirst;
    }

    public void setLinearDateTimeFirst(final LinearLayout linearLayout) {
        linearDateTimeFirst = linearLayout;
    }

    public AppCompatImageButton getImageButtonGetData() {
        return imageButtonGetData;
    }

    public void setImageButtonGetData(final AppCompatImageButton appCompatImageButton) {
        imageButtonGetData = appCompatImageButton;
    }

    public Calendar getMyCalender() {
        return myCalender;
    }

    public void setMyCalender(final Calendar calendar) {
        myCalender = calendar;
    }

    public DatePickerDialog.OnDateSetListener getDate() {
        return date;
    }

    private RecyclerView getMRecyclerView() {
        return mRecyclerView;
    }

    private void setMRecyclerView(final RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    public static void _init_lambda0(final CustomerRouteListActivity this0, final DatePicker datePicker, final int i2, final int i3, final int i4) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final Calendar calendar = this0.myCalender;
        Intrinsics.checkNotNull(calendar);
        calendar.set(1, i2);
        final Calendar calendar2 = this0.myCalender;
        Intrinsics.checkNotNull(calendar2);
        calendar2.set(2, i3);
        final Calendar calendar3 = this0.myCalender;
        Intrinsics.checkNotNull(calendar3);
        calendar3.set(5, i4);
        this0.updateDateTxtView();
    }
     public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        ActivityRouteListBinding inflate = ActivityRouteListBinding.inflate(this.getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        binding = inflate;
        ActivityRouteListBinding activityRouteListBinding = null;
        if (null == inflate) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
         this.setContentView(inflate.getRoot());
         this.getActivityComponent().inject(this);
         this.setToolbar();
        ActivityRouteListBinding activityRouteListBinding2 = binding;
        if (null == activityRouteListBinding2) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRouteListBinding2 = null;
        }
        mSwipeRefreshLayout = activityRouteListBinding2.swipeLayout;
        ActivityRouteListBinding activityRouteListBinding3 = binding;
        if (null == activityRouteListBinding3) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRouteListBinding3 = null;
        }
        mRecyclerView = activityRouteListBinding3.rcwList;
        ActivityRouteListBinding activityRouteListBinding4 = binding;
        if (null == activityRouteListBinding4) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRouteListBinding4 = null;
        }
        txtDateTimeFirst = activityRouteListBinding4.tvDate1;
        ActivityRouteListBinding activityRouteListBinding5 = binding;
        if (null == activityRouteListBinding5) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRouteListBinding5 = null;
        }
        linearDateTimeFirst = activityRouteListBinding5.linearDate1;
        final ActivityRouteListBinding activityRouteListBinding6 = binding;
        if (null == activityRouteListBinding6) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityRouteListBinding = activityRouteListBinding6;
        }
        imageButtonGetData = activityRouteListBinding.imgList;
        final LinearLayout linearLayout = linearDateTimeFirst;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setOnClickListener(this);
        final AppCompatImageButton appCompatImageButton = imageButtonGetData;
        Intrinsics.checkNotNull(appCompatImageButton);
        appCompatImageButton.setOnClickListener(this);
        myCalender = Calendar.getInstance();
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout);
        appBarSwipeRefreshLayout.setColorSchemeResources(R.color.white);
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout2 = mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout2);
        appBarSwipeRefreshLayout2.setProgressBackgroundColorSchemeResource(R.color.redA200);
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout3 = mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout3);
        appBarSwipeRefreshLayout3.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                onCreatelambda1(CustomerRouteListActivity.this);
            }
        });
        final RecyclerView recyclerView = mRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final RecyclerView recyclerView2 = mRecyclerView;
        Intrinsics.checkNotNull(recyclerView2);
        recyclerView2.setHasFixedSize(true);
        final CustomerRouteRecyclerViewAdapter customerRouteRecyclerViewAdapter = new CustomerRouteRecyclerViewAdapter();
        mAdapter = customerRouteRecyclerViewAdapter;
        customerRouteRecyclerViewAdapter.initDisplayOptions(this);
        final RecyclerView recyclerView3 = mRecyclerView;
        Intrinsics.checkNotNull(recyclerView3);
        recyclerView3.setAdapter(mAdapter);
         this.initDateTxtView();
         this.getExtras();
    }
    public static void onCreatelambda1(final CustomerRouteListActivity this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.getData();
    }

    private void updateDateTxtView() {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        final AppCompatTextView appCompatTextView = txtDateTimeFirst;
        Intrinsics.checkNotNull(appCompatTextView);
        final Calendar calendar = myCalender;
        Intrinsics.checkNotNull(calendar);
        appCompatTextView.setText(simpleDateFormat.format(calendar.getTime()));
    }

    private void initDateTxtView() {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        final Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance(...)");
        calendar.set(5, calendar.get(5));
        final AppCompatTextView appCompatTextView = txtDateTimeFirst;
        Intrinsics.checkNotNull(appCompatTextView);
        appCompatTextView.setText(simpleDateFormat.format(calendar.getTime()));
    }

    private String getDateTxtView() {
        List emptyList;
        final AppCompatTextView appCompatTextView = txtDateTimeFirst;
        Intrinsics.checkNotNull(appCompatTextView);
        final List<String> split = new Regex("/").split(appCompatTextView.getText().toString(), 0);
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
        final int parseInt = Integer.parseInt(strArr[0]);
        final String dateC = DateAndTimeUtils.getDateC(Integer.parseInt(strArr[2]), Integer.parseInt(strArr[1]), parseInt);
        Intrinsics.checkNotNullExpressionValue(dateC, "getDateC(...)");
        return dateC;
    }
    public String getLoaderSqlText(final int i2, final int i3) {
        final String str = viewModel.getBaseErp().getLogoSqlHelper().getRouteListSql(this.getDateTxtView()) + " LIMIT " + i2 + " OFFSET " + i3;
        Intrinsics.checkNotNull(str);
        return str;
    }
    public void onCustomersLoad(final ArrayList<Customer> arrayList, final String str) {
        mAdapter.addItem(arrayList);
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout);
        if (appBarSwipeRefreshLayout.isRefreshing()) {
            final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout2 = mSwipeRefreshLayout;
            Intrinsics.checkNotNull(appBarSwipeRefreshLayout2);
            appBarSwipeRefreshLayout2.setRefreshing(false);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
     private ResponseListener<ArrayList<Customer>> customerListResponseListener(CustomerRouteListActivity customerRouteListActivity) {
        return new ResponseListener<ArrayList<Customer>>(customerRouteListActivity) {
            private final WeakReference<CustomerRouteListActivity> mCustomerRouteListActivity;

            {
                mCustomerRouteListActivity = new WeakReference<>(customerRouteListActivity);
            }
            public void onResponse(  final PrintSlipModel arrayList) {
                if (null != this.mCustomerRouteListActivity.get()) {
                    final CustomerRouteListActivity customerRouteListActivity2 = mCustomerRouteListActivity.get();
                    Intrinsics.checkNotNull(customerRouteListActivity2);
                    customerRouteListActivity2.onCustomersLoad(arrayList, "");
                }
            }
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (null != this.mCustomerRouteListActivity.get()) {
                    Log.d("AA", "onError: " + errorMessage);
                    final CustomerRouteListActivity customerRouteListActivity2 = mCustomerRouteListActivity.get();
                    Intrinsics.checkNotNull(customerRouteListActivity2);
                    customerRouteListActivity2.onCustomersLoad(null, errorMessage);
                }
            }
        };
    }

    private Unit getData() {
        final Disposable disposable = mSubscriptionGetCustomer;
        if (null != disposable) {
            Intrinsics.checkNotNull(disposable);
            disposable.dispose();
        }
        mAdapter.restartAdapterAndScroll();
        mAdapter.notifyDataSetChanged();
        mSubscriptionGetCustomer = viewModel.getRouteList(this.getLoaderSqlText(50, 0), this.customerListResponseListener(this));
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            public void onLoadMore(final int i2, final int i3) {
                _get_data_lambda3(CustomerRouteListActivity.this, i2, i3);
            }
        });
        return Unit.INSTANCE;
    }
    public static void _get_data_lambda3(final CustomerRouteListActivity this0, final int i2, final int i3) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.callLoader(i2, i3);
    }

    private void callLoader(final int i2, final int i3) {
        viewModel.getRouteList(this.getLoaderSqlText(i2, i3), this.customerListResponseListener(this));
    }

    public void onClick(final View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (R.id.linearDate1 == v.getId()) {
            final DatePickerDialog.OnDateSetListener onDateSetListener = date;
            final Calendar calendar = myCalender;
            Intrinsics.checkNotNull(calendar);
            final int i2 = calendar.get(1);
            final Calendar calendar2 = myCalender;
            Intrinsics.checkNotNull(calendar2);
            final int i3 = calendar2.get(2);
            final Calendar calendar3 = myCalender;
            Intrinsics.checkNotNull(calendar3);
            new DatePickerDialog(this, onDateSetListener, i2, i3, calendar3.get(5)).show();
            return;
        }
        if (R.id.imgList == v.getId()) {
            this.getData();
        }
    }
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (16908332 == item.getItemId()) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
