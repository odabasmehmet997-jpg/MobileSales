package com.proje.mobilesales.features.customer.view.communication.person;

import com.proje.mobilesales.features.customer.model.database.ClCardIncharge;
import com.proje.mobilesales.features.customer.repository.CustomerPersonRepository;
import com.proje.mobilesales.features.customer.view.BaseCustomerViewModel;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;

public final class CustomerPersonViewModel extends BaseCustomerViewModel {
    final String TAG;
    private final CustomerPersonRepository repository;
    public CustomerPersonViewModel(CustomerPersonRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        this.TAG = "CustomerPersonViewModel";
    }
    public List<ClCardIncharge> getTableForClCardIncharge(Class<ClCardIncharge> tableClass, String selection, String[] selectionArgs) {
        Intrinsics.checkNotNullParameter(tableClass, "tableClass");
        Intrinsics.checkNotNullParameter(selection, "selection");
        Intrinsics.checkNotNullParameter(selectionArgs, "selectionArgs");
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        refObjectRef.element = CollectionsKt.emptyList();
        try {
            BuildersKt.runBlocking(null, new CustomerPersonViewModelgetTableForClCardIncharge1(this, refObjectRef, tableClass, selection, selectionArgs, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (List) refObjectRef.element;
    }
}
