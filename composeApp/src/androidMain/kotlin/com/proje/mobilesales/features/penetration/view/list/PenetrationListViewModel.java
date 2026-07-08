package com.proje.mobilesales.features.penetration.view.list;

import android.util.Log;
import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.penetration.model.database.Penetration;
import com.proje.mobilesales.features.penetration.model.database.PenetrationShort;
import com.proje.mobilesales.features.penetration.repository.PenetrationListRepository;
import com.proje.mobilesales.features.penetration.viewmodel.BasePenetrationViewModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt ;

public final class PenetrationListViewModel extends BasePenetrationViewModel {
    private final PenetrationListRepository repository;

    public PenetrationListViewModel(PenetrationListRepository penetrationListRepository) {
        super(penetrationListRepository);
        Intrinsics.checkNotNullParameter(penetrationListRepository, "repository");
        this.repository = penetrationListRepository;
    }

    public PenetrationListRepository getRepository() {
        return this.repository;
    }

    public void getPenetrationList(String str, ResponseListener<ArrayList<PenetrationShort>> responseListener) {
        Intrinsics.checkNotNullParameter(str, "query");
        this.repository.getSqlManager().getPenetrationList(str, responseListener);
        Log.i(BaseViewModel.TAG, "getPenetrationList");
    }

    public List<Penetration> getTableWhereForPenetration(Class<Penetration> cls, String str, String[] strArr, String str2) {
        Intrinsics.checkNotNullParameter(cls, "tableClass");
        Intrinsics.checkNotNullParameter(str, "where");
        Intrinsics.checkNotNullParameter(strArr, "whereParams");
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        refObjectRef.element = CollectionsKt.emptyList();
        try {
            Object unused = BuildersKt.runBlocking(null, new PenetrationListViewModelgetTableWhereForPenetration1(refObjectRef, this, cls, str, strArr, str2, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (List) refObjectRef.element;
    }
}
