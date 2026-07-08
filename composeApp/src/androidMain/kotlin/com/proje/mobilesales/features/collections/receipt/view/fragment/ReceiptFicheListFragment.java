package com.proje.mobilesales.features.collections.receipt.view.fragment;


import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.proje.mobilesales.R;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.ActionViewResolver;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseFicheListFragment;
import com.proje.mobilesales.core.emailreplacer.EmailObject;
import com.proje.mobilesales.core.enums.EmailTemplateType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.enums.WorkTimeControlProcessType;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.interfaces.TransferAfterRefreshList;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.sql.ISqlManager;
import com.proje.mobilesales.core.utils.AutoVisitUtils;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.SuspicionsActivityUtils;
import com.proje.mobilesales.features.adapter.IListRecyclerView;
import com.proje.mobilesales.features.collections.casefiche.view.activity.CaseFicheActivity;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.activity.CashAndCreditCardFicheActivity;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.view.activity.ChequeAndDeedFicheActivity;
import com.proje.mobilesales.features.collections.receipt.adapter.ReceiptFicheListRecyclerViewAdapter;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.collections.receipt.repository.ReceiptFicheListRepository;
import com.proje.mobilesales.features.collections.receipt.viewmodel.ReceiptFicheListViewModel;
import com.proje.mobilesales.features.customer.model.CustomerBeforeBalance;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.customer.utils.CustomerRestrictionUtil;
import com.proje.mobilesales.features.dbmodel.EmailTemplate;
import com.proje.mobilesales.features.model.EmailReplacerModel;
import com.proje.mobilesales.features.model.FicheMenuRights;
import com.proje.mobilesales.features.model.FicheShort;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import static org.springframework.util.StringUtils.isEmpty;


public final class ReceiptFicheListFragment extends BaseFicheListFragment implements LoaderManager.LoaderCallbacks<Cursor>, SearchView.OnQueryTextListener, TransferAfterRefreshList {
    public static final Companion Companion = new Companion(null);
    private static final String STATE_SALES_FICHE_MENU_RIGHTS = "state:salesFicheMenuRights";
    private static final String STATE_SHOW_DETAIL = "state:showDetail";
    private FicheMenuRights ficheMenuRights;
    private int mAutoVisitId;
    private boolean mDisplayOptions;
    private final ReceiptFicheListRepository receiptFicheListRepository = new ReceiptFicheListRepository();
    private final ReceiptFicheListViewModel receiptFicheListViewModel = new ReceiptFicheListViewModel(receiptFicheListRepository);
    private final List<Integer> inTransferFicheList = new ArrayList();
    private ReceiptFicheListRecyclerViewAdapter mAdapter = new ReceiptFicheListRecyclerViewAdapter();
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.proje.mobilesales.features.collections.receipt.view.fragment.ReceiptFicheListFragmentmReceiver1
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, final Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            refreshTransferAfterList(intent.getBooleanExtra(IntentExtraName.EXTRA_TRANSFER_STATUS, false));
        }
    };
 
    public enum WhenMappings {
        ;
        public static final int[] EnumSwitchMapping0;

        static {
            final int[] iArr = new int[FicheMode.values().length];
            try {
                iArr[FicheMode.ANALYSE.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                iArr[FicheMode.COPY.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                iArr[FicheMode.EDIT.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
            try {
                iArr[FicheMode.NEW.ordinal()] = 4;
            } catch (final NoSuchFieldError unused4) {
            }
            try {
                iArr[FicheMode.SENDMAIL.ordinal()] = 5;
            } catch (final NoSuchFieldError unused5) {
            }
            EnumSwitchMapping0 = iArr;
        }
    }
    public boolean onQueryTextSubmit(final String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        return false;
    }

    public int getMAutoVisitId() {
        return mAutoVisitId;
    }

    public void setMAutoVisitId(final int i2) {
        mAutoVisitId = i2;
    }

    public ReceiptFicheListRecyclerViewAdapter getMAdapter() {
        return mAdapter;
    }

    public void setMAdapter(final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter) {
        Intrinsics.checkNotNullParameter(receiptFicheListRecyclerViewAdapter, "<set-?>");
        mAdapter = receiptFicheListRecyclerViewAdapter;
    }

    public BroadcastReceiver getMReceiver() {
        return mReceiver;
    }

    public void setMReceiver(final BroadcastReceiver broadcastReceiver) {
        Intrinsics.checkNotNullParameter(broadcastReceiver, "<set-?>");
        mReceiver = broadcastReceiver;
    }

    public boolean isFicheInTransfer(final int i2) {
        if (inTransferFicheList.contains(Integer.valueOf(i2))) {
            return true;
        }
        inTransferFicheList.add(Integer.valueOf(i2));
        return false;
    }
     public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
         this.getActivityComponent().inject(this);
        final Context context = this.getContext();
        Intrinsics.checkNotNull(context);
        LocalBroadcastManager.getInstance(context).registerReceiver(mReceiver, new IntentFilter(IntentExtraName.ACTION_TRANSFER_FICHE));
    }

    public View onCreateView(final LayoutInflater inflater, final ViewGroup viewGroup, final Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        final View onCreateView = super.onCreateView(inflater, viewGroup, bundle);
        this.getMSwipeRefreshLayout().setEnabled(true);
        this.getMSwipeRefreshLayout().setRefreshing(false);
        this.getMSwipeRefreshLayout().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                onCreateViewlambda0(ReceiptFicheListFragment.this);
            }
        });
        return onCreateView;
    }

    public static void onCreateViewlambda0(final ReceiptFicheListFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.restartLoader();
    }

    public void onActivityCreated(final Bundle bundle) {
        super.onActivityCreated(bundle);
        if (null != bundle) {
            ficheMenuRights = bundle.getParcelable(ReceiptFicheListFragment.STATE_SALES_FICHE_MENU_RIGHTS);
            mDisplayOptions = bundle.getBoolean(ReceiptFicheListFragment.STATE_SHOW_DETAIL);
            return;
        }
        final BaseErp<?> baseErp = receiptFicheListViewModel.getBaseErp();
        final CustomerOperation customerOperation = mCustomerOperation;
        Intrinsics.checkNotNull(customerOperation);
        ficheMenuRights = baseErp.getFicheMenuRights(customerOperation.getReceiptType());
        final BaseErp<?> baseErp2 = receiptFicheListViewModel.getBaseErp();
        final CustomerOperation customerOperation2 = mCustomerOperation;
        Intrinsics.checkNotNull(customerOperation2);
        mDisplayOptions = baseErp2.getSalesShowDetail(customerOperation2.getFicheType());
    }

    public void onResume() {
        super.onResume();
        this.restartLoader();
    }

    public void onDestroy() {
        super.onDestroy();
        final Context context = this.getContext();
        Intrinsics.checkNotNull(context);
        LocalBroadcastManager.getInstance(context).unregisterReceiver(mReceiver);
    }

    protected void prepareOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        menu.findItem(R.id.menu_filter).setVisible(false);
        menu.findItem(R.id.menu_sort).setVisible(false);
        super.prepareOptionsMenu(menu);
    }

    @Override // com.proje.mobilesales.core.base.BaseFicheListFragment, com.proje.mobilesales.core.base.BaseListFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(final Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putBoolean(ReceiptFicheListFragment.STATE_SHOW_DETAIL, mDisplayOptions);
        outState.putParcelable(ReceiptFicheListFragment.STATE_SALES_FICHE_MENU_RIGHTS, ficheMenuRights);
    }

    @Override // com.proje.mobilesales.core.base.BaseListFragment, com.proje.mobilesales.core.base.BaseFragment
    protected void createOptionsMenu(final Menu menu, final MenuInflater inflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        inflater.inflate(R.menu.menu_search, menu);
        this.createSearchView(menu.findItem(R.id.menu_search));
        inflater.inflate(R.menu.menu_sales_list, menu);
        super.createOptionsMenu(menu, inflater);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (R.id.menu_new == item.getItemId()) {
            this.getCustomerCreateReceiptControls();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getCustomerCreateReceiptControls() {
        if (!SuspicionsActivityUtils.INSTANCE.isPassedSuspiciousActivityControlsForReceipt(mCustomerRef, receiptFicheListRepository, receiptFicheListViewModel) || CustomerRestrictionUtil.hasCustomerRestrictionForReceipt(mCustomerRef)) {
            return;
        }
        this.checkAutoVisit();
    }

    private void checkAutoVisit() {
        if (receiptFicheListViewModel.getBaseErp().isActiveAutoVisit()) {
            AutoVisitUtils.checkAndCreateAutoVisit(mCustomerRef, new ResponseListener<Object>() {
                public void onResponse(final PrintSlipModel obj) {
                    final ReceiptFicheListFragment receiptFicheListFragment = ReceiptFicheListFragment.this;
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
                    receiptFicheListFragment.setMAutoVisitId(((Integer) obj).intValue());
                    if (0 < receiptFicheListFragment.getMAutoVisitId()) {
                        receiptFicheListFragment.checkAutoTimeAndTimeZoneEnabledAndShowDialog();
                    }
                }

                public void onError(final String errorMessage) {
                    Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
                }
            });
        } else {
            this.checkAutoTimeAndTimeZoneEnabledAndShowDialog();
        }
    }
 
    public void checkAutoTimeAndTimeZoneEnabledAndShowDialog() {
        if (ContextUtils.checkAutoTimeAndTimeZoneEnabledAndShowDialog()) {
            final ProgressDialogBuilder<?> mProgressDialogBuilder = this.getMProgressDialogBuilder();
            final Context context = this.getContext();
            Intrinsics.checkNotNull(context);
            mProgressDialogBuilder.setMessage(context.getString(R.string.str_please_wait)).show();
            receiptFicheListViewModel.getBaseErp().checkRemoteWorkTimeControl(WorkTimeControlProcessType.Receipt, new CheckWorkTimeListener(this));
        }
    }
 
        private record CheckWorkTimeListener(
            WeakReference<ReceiptFicheListFragment> mFragment) implements ResponseListener<String> {
            private CheckWorkTimeListener(final ReceiptFicheListFragment mFragment) {
                this(new WeakReference<>(mFragment));
            }
     
            public void onResponse(final String str) {
                if (null != this.mFragment.get()) {
                    final ReceiptFicheListFragment receiptFicheListFragment = mFragment.get();
                    Intrinsics.checkNotNull(receiptFicheListFragment);
                    if (receiptFicheListFragment.isAttached()) {
                        final ReceiptFicheListFragment receiptFicheListFragment2 = mFragment.get();
                        Intrinsics.checkNotNull(receiptFicheListFragment2);
                        receiptFicheListFragment2.getMProgressDialogBuilder().dismiss();
                        if (!isEmpty(str)) {
                            final ReceiptFicheListFragment receiptFicheListFragment3 = mFragment.get();
                            Intrinsics.checkNotNull(receiptFicheListFragment3);
                            Toast.makeText(receiptFicheListFragment3.getActivity(), str, Toast.LENGTH_SHORT).show();
                        } else {
                            final ReceiptFicheListFragment receiptFicheListFragment4 = mFragment.get();
                            Intrinsics.checkNotNull(receiptFicheListFragment4);
                            receiptFicheListFragment4.openReceiptFiche(0, FicheMode.NEW);
                        }
                    }
                }
            }
     
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (null != this.mFragment.get()) {
                    final ReceiptFicheListFragment receiptFicheListFragment = mFragment.get();
                    Intrinsics.checkNotNull(receiptFicheListFragment);
                    if (receiptFicheListFragment.isAttached()) {
                        final ReceiptFicheListFragment receiptFicheListFragment2 = mFragment.get();
                        Intrinsics.checkNotNull(receiptFicheListFragment2);
                        receiptFicheListFragment2.getMProgressDialogBuilder().dismiss();
                    }
                }
            }
        }
 
    protected void createSearchView(final MenuItem menuItem) {
        final Drawable textCursorDrawable = null;
        final ActionViewResolver actionViewResolver = mActionViewResolver;
        Intrinsics.checkNotNull(actionViewResolver);
        final View actionView = actionViewResolver.getActionView(menuItem);
        Intrinsics.checkNotNull(actionView, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView");
        final SearchView searchView = (SearchView) actionView;
        searchView.setQueryHint(this.getString(R.string.hint_search_receipt));
        final FragmentActivity activity = this.getActivity();
        final Object systemService = null != activity ? activity.getSystemService(Context.SEARCH_SERVICE) : null;
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.SearchManager");
        final FragmentActivity activity2 = this.getActivity();
        Intrinsics.checkNotNull(activity2);
        searchView.setSearchableInfo(((SearchManager) systemService).getSearchableInfo(activity2.getComponentName()));
        searchView.setIconified(!mSearchViewExpanded);
        searchView.setQuery(mFilter, false);
        searchView.setOnQueryTextListener(this);
        final SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(R.id.search_src_text);
        searchAutoComplete.setHintTextColor(this.getResources().getColor(R.color.white));
        if (29 <= Build.VERSION.SDK_INT && null != textCursorDrawable) {
            final Context context = this.getContext();
            Intrinsics.checkNotNull(context);
            textCursorDrawable.setColorFilter(ContextCompat.getColor(context, R.color.custom_cursor_color), PorterDuff.Mode.SRC_IN);
            searchAutoComplete.setTextCursorDrawable(textCursorDrawable);
        }
        searchView.setOnSearchClickListener(new View.OnClickListener() {  
            public void onClick(final View view) {
                createSearchViewlambda1(ReceiptFicheListFragment.this, view);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            public boolean onClose() {
                final boolean createSearchViewlambda2;
                createSearchViewlambda2 = createSearchViewlambda2(ReceiptFicheListFragment.this);
                return createSearchViewlambda2;
            }
        });
    } 
    public static void createSearchViewlambda1(final ReceiptFicheListFragment this0, final View v) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(v, "v");
        this0.mSearchViewExpanded = true;
        v.requestFocus();
    }
 
    public static boolean createSearchViewlambda2(final ReceiptFicheListFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.mFilter = "";
        final AppCompatActivity appCompatActivity = (AppCompatActivity) this0.getActivity();
        Intrinsics.checkNotNull(appCompatActivity);
        final ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setSubtitle(this0.mFilter);
        this0.restartLoader();
        return false;
    }
 
    protected IListRecyclerView getAdapter() {
        return mAdapter;
    } 
    public boolean onQueryTextChange(final String newText) {
        Intrinsics.checkNotNullParameter(newText, "newText");
        this.restartLoader();
        return true;
    }

    @Override 
    public void restartLoader() {
        this.getMProgressDialogBuilder().setMessage("Loading").show();
        this.getLoaderManager().restartLoader(0, null, this);
    }

    @Override 
    public Loader<Cursor> onCreateLoader(final int i2, final Bundle bundle) {
        return new ISqlManager.Loader(this.getActivity(), this.getFicheListSql());
    }

    @Override 
    public void onLoadFinished(final Loader<Cursor> loader, final Cursor data) {
        Intrinsics.checkNotNullParameter(loader, "loader");
        Intrinsics.checkNotNullParameter(data, "data");
        receiptFicheListViewModel.getSqlManager().getReceiptCursorToList(data, new FicheListResponseListener(this));
    }

    @Override 
    public void onLoaderReset(final Loader<Cursor> loader) {
        Intrinsics.checkNotNullParameter(loader, "loader");
        this.swapCursor(null);
    }

    private void swapCursor(final List<? extends FicheShort> list) {
        mAdapter.setFicheList(list);
        mAdapter.setmShowSalesDetail(mDisplayOptions);
        final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter = mAdapter;
        receiptFicheListRecyclerViewAdapter.receiptFicheListFragment = this;
        receiptFicheListRecyclerViewAdapter.ficheMenuRights = ficheMenuRights;
        if (!this.isDetached()) {
            this.toggleEmptyView(0 == this.mAdapter.getItemCount(), mFilter);
        }
        this.getMProgressDialogBuilder().dismiss();
        if (this.getMSwipeRefreshLayout().isRefreshing()) {
            this.getMSwipeRefreshLayout().setRefreshing(false);
        }
    }

    private String getFicheListSql() {
        final String ficheListSql = receiptFicheListViewModel.getSqlHelper().getFicheListSql(this.getContext(), mCustomerRef, mCustomerOperation, mFilter);
        Intrinsics.checkNotNullExpressionValue(ficheListSql, "getFicheListSql(...)");
        return ficheListSql;
    }
 
    public void openReceiptFiche(final int i2, final FicheMode ficheMode) {
        Intrinsics.checkNotNullParameter(ficheMode, "ficheMode");
        final int i3 = WhenMappings.EnumSwitchMapping0[ficheMode.ordinal()];
        if (1 == i3) {
            final CustomerOperation customerOperation = mCustomerOperation;
            Intrinsics.checkNotNull(customerOperation);
            customerOperation.setFicheMode(FicheMode.ANALYSE);
        } else if (2 == i3) {
            final CustomerOperation customerOperation2 = mCustomerOperation;
            Intrinsics.checkNotNull(customerOperation2);
            customerOperation2.setFicheMode(FicheMode.COPY);
        } else if (3 == i3) {
            final CustomerOperation customerOperation3 = mCustomerOperation;
            Intrinsics.checkNotNull(customerOperation3);
            customerOperation3.setFicheMode(FicheMode.EDIT);
        } else if (4 == i3) {
            final CustomerOperation customerOperation4 = mCustomerOperation;
            Intrinsics.checkNotNull(customerOperation4);
            customerOperation4.setFicheMode(FicheMode.NEW);
        } else if (5 == i3) {
            this.prepareToSendEmail(i2);
            return;
        }
        Intent intent = new Intent(this.getActivity(), CashAndCreditCardFicheActivity.class);
        final CustomerOperation customerOperation5 = mCustomerOperation;
        Intrinsics.checkNotNull(customerOperation5);
        if (customerOperation5.getReceiptType() != ReceiptType.CASH) {
            final CustomerOperation customerOperation6 = mCustomerOperation;
            Intrinsics.checkNotNull(customerOperation6);
            if (customerOperation6.getReceiptType() != ReceiptType.CREDIT) {
                final CustomerOperation customerOperation7 = mCustomerOperation;
                Intrinsics.checkNotNull(customerOperation7);
                if (customerOperation7.getReceiptType() == ReceiptType.CASE) {
                    intent = new Intent(this.getActivity(), CaseFicheActivity.class);
                } else {
                    final CustomerOperation customerOperation8 = mCustomerOperation;
                    Intrinsics.checkNotNull(customerOperation8);
                    if (customerOperation8.getReceiptType() != ReceiptType.CHEQUE) {
                        final CustomerOperation customerOperation9 = mCustomerOperation;
                        Intrinsics.checkNotNull(customerOperation9);
                    }
                    intent = new Intent(this.getActivity(), ChequeAndDeedFicheActivity.class);
                }
                intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, mCustomerRef);
                intent.putExtra(IntentExtraName.EXTRA_RECEIPT_FICHE_REF, i2);
                intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, mCustomerOperation);
                intent.putExtra(IntentExtraName.EXTRA_ROUTEPLAN_REF, routePlanRef);
                intent.putExtra(IntentExtraName.EXTRA_ROUTEDAY_REF, routeDayRef);
                intent.putExtra(IntentExtraName.EXTRA_ROUTEUSERCUSTOMER_REF, routeUserCustomerRef);
                intent.putExtra(IntentExtraName.EXTRA_AUTO_VISIT_ID, mAutoVisitId);
                final Context context = this.getContext();
                Intrinsics.checkNotNull(context);
                context.startActivity(intent);
            }
        }
        intent = new Intent(this.getActivity(), CashAndCreditCardFicheActivity.class);
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, mCustomerRef);
        intent.putExtra(IntentExtraName.EXTRA_RECEIPT_FICHE_REF, i2);
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, mCustomerOperation);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEPLAN_REF, routePlanRef);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEDAY_REF, routeDayRef);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEUSERCUSTOMER_REF, routeUserCustomerRef);
        intent.putExtra(IntentExtraName.EXTRA_AUTO_VISIT_ID, mAutoVisitId);
        final Context context2 = this.getContext();
        Intrinsics.checkNotNull(context2);
        context2.startActivity(intent);
    }

    private void prepareToSendEmail(int i2) {
        final BaseErp<?> baseErp = receiptFicheListViewModel.getBaseErp();
        final CustomerOperation customerOperation = mCustomerOperation;
        Intrinsics.checkNotNull(customerOperation);
        EmailTemplateType emailTemplateTypeFromReceiptType = baseErp.getEmailTemplateTypeFromReceiptType(customerOperation.getReceiptType());
        final List table = receiptFicheListViewModel.getBaseErp().getLogoSqlHelper().getTable(EmailTemplate.class, "FORMTYPE=?", new String[]{String.valueOf(emailTemplateTypeFromReceiptType.getmValue())});
        Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.EmailTemplate>");
        if (table.isEmpty()) {
            Toast.makeText(this.getContext(), this.getString(R.string.str_not_found_any_email_form_design), Toast.LENGTH_LONG).show();
            return;
        }
        final View inflate = LayoutInflater.from(this.getContext()).inflate(R.layout.dialog_email_input, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setView(inflate);
        TextView textView = inflate.findViewById(R.id.txtViewError);
        textView.setVisibility(View.GONE);
        EditText editText = inflate.findViewById(R.id.edtDialogUserInput);
        editText.setText(receiptFicheListViewModel.getBaseErp().getCustomerInfo(mCustomerRef).getEmailAddr());
        Intrinsics.checkNotNull(editText);
        this.selectAllAndFocusOfEditText(editText);
        Intrinsics.checkNotNull(textView);
        editText.addTextChangedListener(new EmailDialogTextChangeListener(this, textView));
        builder.setPositiveButton(android.R.string.ok, null).setNegativeButton(android.R.string.cancel, null);
        AlertDialog create = builder.create();
        final String str = "(([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4}))(((;|,|; | ;| ; | , | ,){1}([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4}))*)";
        create.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(final DialogInterface dialogInterface) {
                prepareToSendEmaillambda5(create, editText, str, textView, ReceiptFicheListFragment.this, emailTemplateTypeFromReceiptType, i2, dialogInterface);
            }
        });
        create.show();
    }
 
    public static void prepareToSendEmaillambda5(final AlertDialog alertDialog, EditText editText, String regEx, TextView textView, ReceiptFicheListFragment this0, EmailTemplateType emailTemplateType, int i2, DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(regEx, "regEx");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() {  
            public void onClick(final View view) {
                prepareToSendEmaillambda5lambda4(editText, regEx, textView, this0, dialog, emailTemplateType, i2, view);
            }
        });
    }
 
    public static void prepareToSendEmaillambda5lambda4(final EditText editText, final String regEx, final TextView textView, final ReceiptFicheListFragment this0, final DialogInterface dialog, final EmailTemplateType emailTemplateType, final int i2, final View view) {
        Intrinsics.checkNotNullParameter(regEx, "regEx");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        final String obj = editText.getText().toString();
        if (!isEmpty(obj)) {
            if (!Pattern.matches(regEx, editText.getText())) {
                textView.setText(this0.getString(R.string.str_invalid_email_address));
                textView.setVisibility(View.VISIBLE);
                return;
            }
            dialog.dismiss();
            try {
                final ProgressDialogBuilder<?> mProgressDialogBuilder = this0.getMProgressDialogBuilder();
                final Context context = this0.getContext();
                Intrinsics.checkNotNull(context);
                mProgressDialogBuilder.setMessage(context.getString(R.string.str_please_wait)).show();
                final CustomerOperation customerOperation = this0.mCustomerOperation;
                Intrinsics.checkNotNull(customerOperation);
                final ReceiptType receiptType = customerOperation.getReceiptType();
                final EmailReplacerModel receipt = null != receiptType ? this0.receiptFicheListViewModel.getReceipt(i2, receiptType) : null;
                if (null != receipt) {
                    receipt.setEmail(obj);
                }
                final BaseErp<?> baseErp = this0.receiptFicheListViewModel.getBaseErp();
                Intrinsics.checkNotNull(receipt);
                final int clRef = receipt.getClRef();
                final BaseErp<?> baseErp2 = this0.receiptFicheListViewModel.getBaseErp();
                final CustomerOperation customerOperation2 = this0.mCustomerOperation;
                Intrinsics.checkNotNull(customerOperation2);
                final Class<?> reciptClassFromReceiptType = baseErp2.getReciptClassFromReceiptType(customerOperation2.getReceiptType());
                final int ficheRef = receipt.getFicheRef();
                Intrinsics.checkNotNull(emailTemplateType);
                baseErp.getCustomerNowAndBeforeBalance(clRef, reciptClassFromReceiptType, ficheRef, new CustomerNowAndBeforeResponseListener(this0, receipt, emailTemplateType));
                return;
            } catch (final Exception unused) {
                this0.getMProgressDialogBuilder().dismiss();
                Toast.makeText(this0.getContext(), this0.getString(R.string.str_send_email_failed), Toast.LENGTH_LONG).show();
                return;
            }
        }
        textView.setText(this0.getString(R.string.str_email_address_required));
        textView.setVisibility(View.VISIBLE);
    } 
        private record CustomerNowAndBeforeResponseListener(
            WeakReference<ReceiptFicheListFragment> mReceiptFicheListFragmentWeakReference,
            WeakReference<EmailReplacerModel> mReceiptWeakReference,
            WeakReference<EmailTemplateType> mEmailTemplateTypeWeakReference) implements ResponseListener<ArrayList<CustomerBeforeBalance>> {
            private CustomerNowAndBeforeResponseListener(final ReceiptFicheListFragment mReceiptFicheListFragmentWeakReference, final EmailReplacerModel mReceiptWeakReference, final EmailTemplateType mEmailTemplateTypeWeakReference) {
                Intrinsics.checkNotNullParameter(mReceiptWeakReference, "data");
                Intrinsics.checkNotNullParameter(mEmailTemplateTypeWeakReference, "emailTemplateType");
                this.mReceiptFicheListFragmentWeakReference = new WeakReference<>(mReceiptFicheListFragmentWeakReference);
                this.mReceiptWeakReference = new WeakReference<>(mReceiptWeakReference);
                this.mEmailTemplateTypeWeakReference = new WeakReference<>(mEmailTemplateTypeWeakReference);
            }
     
            public void onResponse(final ArrayList<CustomerBeforeBalance> arrayList) {
                if (null != this.mReceiptFicheListFragmentWeakReference.get()) {
                    final ReceiptFicheListFragment receiptFicheListFragment = mReceiptFicheListFragmentWeakReference.get();
                    Intrinsics.checkNotNull(receiptFicheListFragment);
                    if (receiptFicheListFragment.isAttached()) {
                        final ReceiptFicheListFragment receiptFicheListFragment2 = mReceiptFicheListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(receiptFicheListFragment2);
                        receiptFicheListFragment2.getMProgressDialogBuilder().dismiss();
                        final ReceiptFicheListFragment receiptFicheListFragment3 = mReceiptFicheListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(receiptFicheListFragment3);
                        receiptFicheListFragment3.onCustomerNowAndBefore(mReceiptWeakReference.get(), mEmailTemplateTypeWeakReference.get(), arrayList);
                    }
                }
            }
     
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (null != this.mReceiptFicheListFragmentWeakReference.get()) {
                    final ReceiptFicheListFragment receiptFicheListFragment = mReceiptFicheListFragmentWeakReference.get();
                    Intrinsics.checkNotNull(receiptFicheListFragment);
                    if (receiptFicheListFragment.isAttached()) {
                        Log.d("CustomerNowAndBefore", "onError: " + errorMessage);
                        final ReceiptFicheListFragment receiptFicheListFragment2 = mReceiptFicheListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(receiptFicheListFragment2);
                        receiptFicheListFragment2.getMProgressDialogBuilder().dismiss();
                        final ReceiptFicheListFragment receiptFicheListFragment3 = mReceiptFicheListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(receiptFicheListFragment3);
                        receiptFicheListFragment3.onCustomerNowAndBefore(mReceiptWeakReference.get(), mEmailTemplateTypeWeakReference.get(), null);
                    }
                }
            }
        }
 
    public void onCustomerNowAndBefore(final EmailReplacerModel emailReplacerModel, final EmailTemplateType emailTemplateType, final ArrayList<CustomerBeforeBalance> arrayList) {
        this.getMProgressDialogBuilder().setMessage(this.getString(R.string.str_applying_email_form_design)).show();
        receiptFicheListViewModel.getBaseErp().replaceReceiptFicheHtml(emailReplacerModel, emailTemplateType, (null == arrayList || arrayList.isEmpty()) ? null : arrayList.get(0), new ReplaceHtmlResponseListener(this));
    }
 
        private record ReplaceHtmlResponseListener(
            WeakReference<ReceiptFicheListFragment> mReceiptFicheListFragmentWeakReference) implements ResponseListener<EmailObject> {
            private ReplaceHtmlResponseListener(final ReceiptFicheListFragment mReceiptFicheListFragmentWeakReference) {
                this(new WeakReference<>(mReceiptFicheListFragmentWeakReference));
            }
     
            public void onResponse(final EmailObject emailObject) {
                if (null != this.mReceiptFicheListFragmentWeakReference.get()) {
                    final ReceiptFicheListFragment receiptFicheListFragment = mReceiptFicheListFragmentWeakReference.get();
                    Intrinsics.checkNotNull(receiptFicheListFragment);
                    if (receiptFicheListFragment.isAttached()) {
                        final ReceiptFicheListFragment receiptFicheListFragment2 = mReceiptFicheListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(receiptFicheListFragment2);
                        receiptFicheListFragment2.getMProgressDialogBuilder().dismiss();
                        final ReceiptFicheListFragment receiptFicheListFragment3 = mReceiptFicheListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(receiptFicheListFragment3);
                        receiptFicheListFragment3.onReplaceHtml("", emailObject);
                    }
                }
            }
     
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (null != this.mReceiptFicheListFragmentWeakReference.get()) {
                    final ReceiptFicheListFragment receiptFicheListFragment = mReceiptFicheListFragmentWeakReference.get();
                    Intrinsics.checkNotNull(receiptFicheListFragment);
                    if (receiptFicheListFragment.isAttached()) {
                        Log.d("CustomerNowAndBefore", "onError: " + errorMessage);
                        final ReceiptFicheListFragment receiptFicheListFragment2 = mReceiptFicheListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(receiptFicheListFragment2);
                        receiptFicheListFragment2.getMProgressDialogBuilder().dismiss();
                        final ReceiptFicheListFragment receiptFicheListFragment3 = mReceiptFicheListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(receiptFicheListFragment3);
                        receiptFicheListFragment3.onReplaceHtml(errorMessage, null);
                    }
                }
            }
        }
 
    public void onReplaceHtml(final String str, final EmailObject emailObject) {
        if (isEmpty(str) && null != emailObject) {
            final BaseErpActivity baseErpActivity = (BaseErpActivity) this.getActivity();
            Intrinsics.checkNotNull(baseErpActivity);
            baseErpActivity.sendFicheMail(emailObject);
            return;
        }
        Toast.makeText(this.getContext(), this.getString(R.string.str_send_email_failed), Toast.LENGTH_LONG).show();
    }
 
    private final class EmailDialogTextChangeListener implements TextWatcher {
        private TextView errorTextView;
        final ReceiptFicheListFragment this0;

        
        public void afterTextChanged(final Editable s) {
            Intrinsics.checkNotNullParameter(s, "s");
        }

        
        public void beforeTextChanged(final CharSequence s, final int i2, final int i3, final int i4) {
            Intrinsics.checkNotNullParameter(s, "s");
        }

        public EmailDialogTextChangeListener(final ReceiptFicheListFragment receiptFicheListFragment, final TextView errorTextView) {
            Intrinsics.checkNotNullParameter(errorTextView, "errorTextView");
            this0 = receiptFicheListFragment;
            this.errorTextView = errorTextView;
        }

        public TextView getErrorTextView() {
            return errorTextView;
        }

        public void setErrorTextView(final TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            errorTextView = textView;
        }

        
        public void onTextChanged(final CharSequence s, final int i2, final int i3, final int i4) {
            Intrinsics.checkNotNullParameter(s, "s");
            errorTextView.setVisibility(View.GONE);
            errorTextView.setText("");
        }
    }

    public void refreshTransferAfterList(final boolean z) {
        if (!z || this.isDetached()) {
            return;
        }
        inTransferFicheList.clear();
        this.restartLoader();
    }
        private record FicheListResponseListener(
            WeakReference<ReceiptFicheListFragment> mFicheListFragmentWeakReference) implements ResponseListener<List<? extends FicheShort>> {
            private FicheListResponseListener(final ReceiptFicheListFragment mFicheListFragmentWeakReference) {
                this(new WeakReference<>(mFicheListFragmentWeakReference));
            }

            public void onResponse(final List<? extends FicheShort> list) {
                if (null != this.mFicheListFragmentWeakReference.get()) {
                    final ReceiptFicheListFragment receiptFicheListFragment = mFicheListFragmentWeakReference.get();
                    Intrinsics.checkNotNull(receiptFicheListFragment);
                    if (receiptFicheListFragment.isAttached()) {
                        final ReceiptFicheListFragment receiptFicheListFragment2 = mFicheListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(receiptFicheListFragment2);
                        receiptFicheListFragment2.swapCursor(list);
                    }
                }
            }
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (null != this.mFicheListFragmentWeakReference.get()) {
                    final ReceiptFicheListFragment receiptFicheListFragment = mFicheListFragmentWeakReference.get();
                    Intrinsics.checkNotNull(receiptFicheListFragment);
                    if (receiptFicheListFragment.isAttached()) {
                        Log.d("AA", "onError: " + errorMessage);
                        final ReceiptFicheListFragment receiptFicheListFragment2 = mFicheListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(receiptFicheListFragment2);
                        receiptFicheListFragment2.onFicheLoadError(errorMessage);
                    }
                }
            }
        }

    public void onFicheLoadError(final String str) {
        if (isEmpty(str)) {
            return;
        }
        Toast.makeText(this.getContext(), str, Toast.LENGTH_LONG).show();
    }

    public void onPause() {
        super.onPause();
        if (null != getMProgressDialogBuilder()) {
            this.getMProgressDialogBuilder().dismiss();
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
