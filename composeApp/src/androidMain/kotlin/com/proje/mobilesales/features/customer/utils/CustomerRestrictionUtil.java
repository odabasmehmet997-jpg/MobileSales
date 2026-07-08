package com.proje.mobilesales.features.customer.utils;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.CustomerRestriction;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.customer.model.database.CustomerDetailedRestriction;
import com.proje.mobilesales.features.sales.repository.SalesListRepository;
import com.proje.mobilesales.features.sales.view.list.SalesListViewModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;



public final class CustomerRestrictionUtil {
    public static final CustomerRestrictionUtil INSTANCE = new CustomerRestrictionUtil();
    private static final SalesListRepository repository;
    private static final SalesListViewModel viewModel;

    private CustomerRestrictionUtil() {
    }

    static {
        SalesListRepository salesListRepository = new SalesListRepository();
        repository = salesListRepository;
        viewModel = new SalesListViewModel(salesListRepository);
    }

    private ClCard getCustomer(int i2) {
        SalesListViewModel salesListViewModel = viewModel;
        List table = salesListViewModel.getBaseErp().getLogoSqlHelper().getTable(ClCard.class, "CODE=?", new String[]{salesListViewModel.getSqlHelper().getClCode(i2)});
        Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.customer.model.database.ClCard>");
        return !table.isEmpty() ? (ClCard) table.get(0) : new ClCard(0, null, null, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, null, 0, null, null, null, null, null, null, null, null, 0, 0, null, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0, 0.0d, 0.0d, 0.0d, null, null, null, null, null, 0.0d, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, 0, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.0d, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, 0, null, 0, null, 0, 0, null, null, null, null, null, 0, 0, null, 0, null, null, 0, 0.0d, null, null, null, 0, -1, -1, -1, -1, 3, null);
    }

    private List<CustomerRestriction> getDetailRestriction(int i2) {
        SalesListViewModel salesListViewModel = viewModel;
        List table = salesListViewModel.getBaseErp().getLogoSqlHelper().getTable(CustomerDetailedRestriction.class, "CLCODE=?", new String[]{salesListViewModel.getBaseErp().getLogoSqlHelper().getClCode(i2)});
        Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.customer.model.database.CustomerDetailedRestriction>");
        ArrayList arrayList = new ArrayList();
        if (!table.isEmpty()) {
            Iterator it = table.iterator();
            while (it.hasNext()) {
                arrayList.add(((CustomerDetailedRestriction) it.next()).getCustomerDetailRestriction());
            }
        }
        return arrayList;
    }

    public boolean hasCustomerRestriction(int i2, SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        if (viewModel.erpType() != ErpType.NETSIS) {
            return false;
        }
        SalesUtils salesUtils = SalesUtils.INSTANCE;
        if (salesUtils.isSalesOrOrder(salesType) && (getCustomer(i2).getCustomerRestriction() == CustomerRestriction.RESTRICTION_ON_SALES || getCustomer(i2).getCustomerRestriction() == CustomerRestriction.RESTRICTION_ON_ALL_FICHES)) {
            showAlertDialog(i2);
            return true;
        }
        if (!salesUtils.isSalesOrOrder(salesType) || getCustomer(i2).getCustomerRestriction() != CustomerRestriction.DETAILED_RESTRICTION) {
            return false;
        }
        if (SalesUtils.isSalesTypeInvoiceOrReturnInvoice(salesType) && getDetailRestriction(i2).contains(CustomerRestriction.INVOICE_RESTRICTION)) {
            showAlertDialog(i2);
            return true;
        }
        if (SalesUtils.isSalesTypeDispatchOrReturnDispatchOrOneToOne(salesType) && getDetailRestriction(i2).contains(CustomerRestriction.DISPATCH_RESTRICTION)) {
            showAlertDialog(i2);
            return true;
        }
        if (!SalesUtils.isSalesTypeOrder(salesType) || !getDetailRestriction(i2).contains(CustomerRestriction.ORDER_RESTRICTION)) {
            return false;
        }
        showAlertDialog(i2);
        return true;
    }

    public static boolean hasCustomerRestrictionForReceipt(int i2) {
        if (viewModel.erpType() != ErpType.NETSIS) {
            return false;
        }
        CustomerRestrictionUtil customerRestrictionUtil = INSTANCE;
        if (customerRestrictionUtil.getCustomer(i2).getCustomerRestriction() != CustomerRestriction.RESTRICTION_ON_ALL_FICHES) {
            return false;
        }
        customerRestrictionUtil.showAlertDialog(i2);
        return true;
    }

    private void showAlertDialog(int i2) {
        Context context = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Context context2 = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context2);
        String string = context2.getString(R.string.exp_customer_has_restriction);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String format = String.format(string, Arrays.copyOf(new Object[]{getCustomer(i2).getCode()}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        builder.setMessage(format).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i3) {
                CustomerRestrictionUtil.showAlertDialoglambda0(dialogInterface, i3);
            }
        }).create().show();
    }

    public static void showAlertDialoglambda0(DialogInterface dialog, int i2) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }
}
