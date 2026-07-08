package com.proje.mobilesales.features.collections.chequeanddeedfiche.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.base.BaseListFragment;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.interfaces.ActionModeDelegate;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.features.adapter.IListRecyclerView;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.adapter.ChequeDeedDetailListRecyclerViewAdapter;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeedFiche;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.repository.ChequeAndDeedFicheRepository;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.view.activity.ChequeAndDeedFicheActivity;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.viewmodel.ChequeAndDeedFicheViewModel;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.settings.model.MatterSettings;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: ChequeDeedFicheDetailListFragment.kt */

public final class ChequeDeedFicheDetailListFragment extends BaseListFragment implements ActionModeDelegate {
    private CustomerOperation customerOperation;
    private int customerRef;
    private int invoiceRef;
    private ActionMode mActionMode;
    private MatterSettings mMatterSettings;
    private boolean mNetsis;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    private ReceiptType mReceiptType;
    private final ChequeAndDeedFicheRepository repository = new ChequeAndDeedFicheRepository();
    private final ChequeAndDeedFicheViewModel viewModel = new ChequeAndDeedFicheViewModel(repository);
    private final ChequeDeedDetailListRecyclerViewAdapter mAdapter = new ChequeDeedDetailListRecyclerViewAdapter(this);

    public CustomerOperation getCustomerOperation() {
        return customerOperation;
    }

    public int getCustomerRef() {
        return customerRef;
    }

    public int getInvoiceRef() {
        return invoiceRef;
    }

    @Override // com.proje.mobilesales.core.base.BaseListFragment, com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.getActivityComponent().inject(this);
        mNetsis = viewModel.getBaseErp().getErpType() == ErpType.NETSIS;
        final Bundle arguments = this.getArguments();
        Intrinsics.checkNotNull(arguments);
        customerOperation = arguments.getParcelable(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE);
        final Bundle arguments2 = this.getArguments();
        Intrinsics.checkNotNull(arguments2);
        customerRef = arguments2.getInt(IntentExtraName.EXTRA_CUSTOMER_REF);
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        mReceiptType = customerOperation.getReceiptType();
        final BaseErp<?> baseErp = viewModel.getBaseErp();
        final Context context = this.getContext();
        final CustomerOperation customerOperation2 = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation2);
        mMatterSettings = baseErp.getMatterSettings(context, customerOperation2.getFicheType());
        final Bundle arguments3 = this.getArguments();
        Intrinsics.checkNotNull(arguments3);
        invoiceRef = arguments3.getInt(IntentExtraName.INVOICE_PAYMENTLINE_REF);
        final Context requireContext = this.requireContext();
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mProgressDialogBuilder = new ProgressDialogBuilder.Impl(requireContext, (BaseInjectableActivity) activity);
    }

    public View onCreateView(final LayoutInflater inflater, final ViewGroup viewGroup, final Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        final View inflate = this.getLayoutInflater(bundle).inflate(R.layout.fragment_chequedeedfiche_detaillist, viewGroup, false);
        mRecyclerView = inflate.findViewById(R.id.rcw_chequedeeddetaillist);
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        if (customerOperation.getFicheMode() != FicheMode.NEW) {
            final ChequeAndDeedFicheActivity chequeDeedFicheActivity = this.getChequeDeedFicheActivity();
            Intrinsics.checkNotNull(chequeDeedFicheActivity);
        }
        final ChequeAndDeedFicheActivity chequeDeedFicheActivity2 = this.getChequeDeedFicheActivity();
        Intrinsics.checkNotNull(chequeDeedFicheActivity2);
        final ChequeAndDeedFicheActivity chequeDeedFicheActivity3 = this.getChequeDeedFicheActivity();
        Intrinsics.checkNotNull(chequeDeedFicheActivity3);
        chequeDeedFicheActivity2.addNewDetail(0.0d < chequeDeedFicheActivity3.getInvoicePaymentTotal());
        return inflate;
    }

    @Override // com.proje.mobilesales.core.base.BaseListFragment, androidx.fragment.app.Fragment
    public void onViewCreated(final View view, final Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
    }

    @Override // com.proje.mobilesales.core.base.BaseListFragment, com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(final Bundle bundle) {
        super.onActivityCreated(bundle);
        this.load();
    }

    @Override // com.proje.mobilesales.core.base.BaseListFragment
    protected IListRecyclerView getAdapter() {
        return mAdapter;
    }

    private void load() {
        mAdapter.setmFragment(this);
        final ChequeDeedDetailListRecyclerViewAdapter chequeDeedDetailListRecyclerViewAdapter = mAdapter;
        final ChequeDeedFiche chequeDeedFiche = this.getChequeDeedFiche();
        chequeDeedDetailListRecyclerViewAdapter.setChequeDeedDetailList(null != chequeDeedFiche ? chequeDeedFiche.getDetails() : null);
        final ChequeDeedDetailListRecyclerViewAdapter chequeDeedDetailListRecyclerViewAdapter2 = mAdapter;
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        chequeDeedDetailListRecyclerViewAdapter2.setmFicheMode(customerOperation.getFicheMode());
        mAdapter.initDisplayOptions(this.getContext());
    }

    public void refreshList() {
        final ChequeDeedDetailListRecyclerViewAdapter chequeDeedDetailListRecyclerViewAdapter = mAdapter;
        final ChequeDeedFiche chequeDeedFiche = this.getChequeDeedFiche();
        chequeDeedDetailListRecyclerViewAdapter.setChequeDeedDetailList(null != chequeDeedFiche ? chequeDeedFiche.getDetails() : null);
        final ChequeAndDeedFicheActivity chequeDeedFicheActivity = this.getChequeDeedFicheActivity();
        Intrinsics.checkNotNull(chequeDeedFicheActivity);
        chequeDeedFicheActivity.setTotalLayoutWithNotify();
    }

    private ChequeDeedFiche getChequeDeedFiche() {
        final ChequeAndDeedFicheActivity chequeDeedFicheActivity = this.getChequeDeedFicheActivity();
        Intrinsics.checkNotNull(chequeDeedFicheActivity);
        return chequeDeedFicheActivity.getChequeDeedFiche();
    }

    public ChequeAndDeedFicheActivity getChequeDeedFicheActivity() {
        return (ChequeAndDeedFicheActivity) this.getActivity();
    }

    @Override // com.proje.mobilesales.core.interfaces.ActionModeDelegate
    public boolean startActionMode(final ActionMode.Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        if (customerOperation.getFicheMode() == FicheMode.ANALYSE) {
            return false;
        }
        if (null != this.mActionMode) {
            return true;
        }
        final AppCompatActivity appCompatActivity = (AppCompatActivity) this.getActivity();
        Intrinsics.checkNotNull(appCompatActivity);
        mActionMode = appCompatActivity.startSupportActionMode(callback);
        return true;
    }

    @Override // com.proje.mobilesales.core.interfaces.ActionModeDelegate
    public boolean isInActionMode() {
        return null != this.mActionMode;
    }

    @Override // com.proje.mobilesales.core.interfaces.ActionModeDelegate
    public void stopActionMode() {
        mActionMode = null;
    }

    @Override // com.proje.mobilesales.core.base.BaseListFragment, com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        final ActionMode actionMode = mActionMode;
        if (null != actionMode) {
            Intrinsics.checkNotNull(actionMode);
            actionMode.finish();
        }
    }
}
