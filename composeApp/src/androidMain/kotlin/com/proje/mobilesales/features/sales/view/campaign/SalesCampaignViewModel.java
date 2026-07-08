package com.proje.mobilesales.features.sales.view.campaign;

import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import com.proje.mobilesales.features.product.model.database.ItemUnits;
import com.proje.mobilesales.features.sales.repository.SalesCampaignRepository;
import com.proje.mobilesales.features.sales.view.BaseSalesViewModel;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref ;
import kotlinx.coroutines.BuildersKt;

public final class SalesCampaignViewModel<T> extends BaseSalesViewModel {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "SalesCampaignViewModel";
    private final SalesCampaignRepository repository;
    public SalesCampaignViewModel(SalesCampaignRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
    }
    public SalesCampaignRepository getRepository() {
        return this.repository;
    }
    public List<ItemUnits> getTableForItemUnits(Class<ItemUnits> tableClass, String selection, String[] selectionArgs) {
        Intrinsics.checkNotNullParameter(tableClass, "tableClass");
        Intrinsics.checkNotNullParameter(selection, "selection");
        Intrinsics.checkNotNullParameter(selectionArgs, "selectionArgs");
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        refObjectRef.element = CollectionsKt.emptyList();
        try {
            BuildersKt.runBlocking(null, new SalesCampaignViewModelgetTableForItemUnits1(this, refObjectRef, tableClass, selection, selectionArgs, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (List) refObjectRef.element;
    }
    public ItemSlip getObjectForItemSlip(int i2, boolean z) {
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        try {
            BuildersKt.runBlocking(null, new SalesCampaignViewModelgetObjectForItemSlip1(this, refObjectRef, i2, z, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        T t = (T) refObjectRef.element;
        Intrinsics.checkNotNull(t);
        return (ItemSlip) t;
    }
    public String getItemNameFromSqlHelper(String code) {
        Intrinsics.checkNotNullParameter(code, "code");
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        refObjectRef.element = "";
        try {
            BuildersKt.runBlocking(null, new SalesCampaignViewModelgetItemNameFromSqlHelper1(this, refObjectRef, code, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (String) refObjectRef.element;
    }
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public String getTAG() {
            return SalesCampaignViewModel.TAG;
        }
    }
}
