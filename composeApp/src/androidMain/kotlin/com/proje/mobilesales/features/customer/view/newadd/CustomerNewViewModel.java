package com.proje.mobilesales.features.customer.view.newadd;

import android.util.Log;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.customer.model.CustomerNew;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.customer.repository.CustomerNewRepository;
import com.proje.mobilesales.features.customer.view.BaseCustomerViewModel;
import com.proje.mobilesales.features.dbmodel.User;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.RefObjectRef;
import kotlinx.coroutines.BuildersKt__BuildersKt;


/* compiled from: CustomerNewViewModel.kt */

public final class CustomerNewViewModel extends BaseCustomerViewModel {
    final String TAG;
    final CustomerNewRepository repository;

    
    public CustomerNewViewModel(final CustomerNewRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        TAG = "CustomerNewViewModel";
    }

    public void addNewCustomer(final CustomerNew customerNew, final boolean z, final ResponseListener<CustomerNew> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        repository.addNewCustomer(customerNew, z, responseListener);
        Log.i(TAG, "addNewCustomer");
    }

    public void saveNewCustomer(final CustomerNew customerNew, final ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        repository.saveNewCustomerFromSqlManager(customerNew, responseListener);
        Log.i(TAG, "saveNewCustomer");
    }

    public void updateNewCustomer(final CustomerNew customerNew, final ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        repository.updateNewCustomerFromSqlManager(customerNew, responseListener);
        Log.i(TAG, "updateNewCustomer");
    }

    public List<ClCard> getTableForClCard(final Class<ClCard> tableClass, final String selection, final String[] selectionArgs) {
        Intrinsics.checkNotNullParameter(tableClass, "tableClass");
        Intrinsics.checkNotNullParameter(selection, "selection");
        Intrinsics.checkNotNullParameter(selectionArgs, "selectionArgs");
        final RefObjectRef refObjectRef = new RefObjectRef();
        BuildersKt.runBlockingdefault(null, new CustomerNewViewModelgetTableForClCard1(this, refObjectRef, tableClass, selection, selectionArgs, null), 1, null);
        return (List) refObjectRef.element;
    }

    
    public User getUserInformation() {
        final RefObjectRef refObjectRef = new RefObjectRef();
        BuildersKt.runBlockingdefault(null, new CustomerNewViewModelgetUserInformation1(refObjectRef, this, null), 1, null);
        final T t = refObjectRef.element;
        if (0 != t) {
            return (User) t;
        }
        Intrinsics.throwUninitializedPropertyAccessException("result");
        return null;
    }
}
