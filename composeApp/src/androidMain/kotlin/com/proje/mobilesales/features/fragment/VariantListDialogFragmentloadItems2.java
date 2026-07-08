package com.proje.mobilesales.features.fragment;

import androidx.databinding.ObservableField;
import androidx.exifinterface.media.ExifInterface;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.model.ItemVariantStock;
import com.proje.mobilesales.features.model.PriceInfo;
import com.proje.mobilesales.features.model.SelectedVariant;
import com.proje.mobilesales.features.model.VariantModel;
import com.proje.mobilesales.features.model.VariantPriceParams;
import com.proje.mobilesales.features.model.VariantSelectionParams;
import java.util.ArrayList;
import java.util.List;

import kotlin.Result;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import okio.internal._FileSystemKtcommonListRecursively1;

final class VariantListDialogFragmentloadItems2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final VariantListDialogFragment this0;

    VariantListDialogFragmentloadItems2(VariantListDialogFragment variantListDialogFragment, Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = variantListDialogFragment;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new VariantListDialogFragmentloadItems2(this.this0, continuation);
    }
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
    public Object invokeSuspend(Object obj) {
        String str;
        List list;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        ISqlHelper logoSqlHelper = baseErp.getLogoSqlHelper();
        VariantSelectionParams variantSelectionParams = this.this0.variantSelectionParams;
        if (variantSelectionParams == null) {
            Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
            variantSelectionParams = null;
        }
        VariantPriceParams variantPriceParams = variantSelectionParams.getVariantPriceParams();
        String clCardTradingGrp = logoSqlHelper.getClCardTradingGrp(variantPriceParams != null ? variantPriceParams.getCustomerRef() : 0);
        VariantSelectionParams variantSelectionParams2 = this.this0.variantSelectionParams;
        if (variantSelectionParams2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
            variantSelectionParams2 = null;
        }
        int productId = variantSelectionParams2.getProductId();
        VariantSelectionParams variantSelectionParams3 = this.this0.variantSelectionParams;
        if (variantSelectionParams3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
            variantSelectionParams3 = null;
        }
        int whNr = variantSelectionParams3.getWhNr();
        VariantSelectionParams variantSelectionParams4 = this.this0.variantSelectionParams;
        if (variantSelectionParams4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
            variantSelectionParams4 = null;
        }
        List<ItemVariantStock> variantInfo = baseErp.getVariantInfo(productId, whNr, variantSelectionParams4.getProductCode());
        Intrinsics.checkNotNull(variantInfo, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.model.ItemVariantStock>");
        for (ItemVariantStock itemVariantStock : variantInfo) {
            VariantSelectionParams variantSelectionParams5 = this.this0.variantSelectionParams;
            if (variantSelectionParams5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
                variantSelectionParams5 = null;
            }
            VariantPriceParams variantPriceParams2 = variantSelectionParams5.getVariantPriceParams();
            int customerRef = variantPriceParams2 != null ? variantPriceParams2.getCustomerRef() : 0;
            VariantSelectionParams variantSelectionParams6 = this.this0.variantSelectionParams;
            if (variantSelectionParams6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
                variantSelectionParams6 = null;
            }
            String productCode = variantSelectionParams6.getProductCode();
            VariantSelectionParams variantSelectionParams7 = this.this0.variantSelectionParams;
            if (variantSelectionParams7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
                variantSelectionParams7 = null;
            }
            String unitCode = variantSelectionParams7.getUnitCode();
            String varintCode = itemVariantStock.getVarintCode();
            VariantSelectionParams variantSelectionParams8 = this.this0.variantSelectionParams;
            if (variantSelectionParams8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
                variantSelectionParams8 = null;
            }
            int divisionNr = variantSelectionParams8.getDivisionNr();
            VariantSelectionParams variantSelectionParams9 = this.this0.variantSelectionParams;
            if (variantSelectionParams9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
                variantSelectionParams9 = null;
            }
            String valueOf = String.valueOf(variantSelectionParams9.getProductId());
            VariantSelectionParams variantSelectionParams10 = this.this0.variantSelectionParams;
            if (variantSelectionParams10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
                variantSelectionParams10 = null;
            }
            VariantPriceParams variantPriceParams3 = variantSelectionParams10.getVariantPriceParams();
            Boxing boxing = null;
            String valueOf2 = String.valueOf(variantPriceParams3 != null ? Boxing.boxInt(variantPriceParams3.getCustomerRef()) : null);
            VariantSelectionParams variantSelectionParams11 = this.this0.variantSelectionParams;
            if (variantSelectionParams11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
                variantSelectionParams11 = null;
            }
            String valueOf3 = String.valueOf(variantSelectionParams11.getUnitRef());
            VariantSelectionParams variantSelectionParams12 = this.this0.variantSelectionParams;
            if (variantSelectionParams12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
                variantSelectionParams12 = null;
            }
            VariantPriceParams variantPriceParams4 = variantSelectionParams12.getVariantPriceParams();
            String valueOf4 = String.valueOf(variantPriceParams4 != null ? Boxing.boxInt(variantPriceParams4.getPaymentRef()) : null);
            VariantSelectionParams variantSelectionParams13 = this.this0.variantSelectionParams;
            if (variantSelectionParams13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
                variantSelectionParams13 = null;
            }
            VariantPriceParams variantPriceParams5 = variantSelectionParams13.getVariantPriceParams();
            if (variantPriceParams5 == null || (str = variantPriceParams5.getTradeGroup()) == null) {
                str = "";
            }
            PriceInfo productPriceInfo = baseErp.getProductPriceInfo(customerRef, true, productCode, unitCode, varintCode, divisionNr, new String[]{valueOf, valueOf2, ExifInterface.GPS_MEASUREMENT_2D, valueOf3, valueOf4, str, clCardTradingGrp});
            list = this.this0.mList;
            ObservableField observableField = new ObservableField(StringUtils.formatStock(itemVariantStock.getVariantActualStok()));
            ObservableField observableField2 = new ObservableField(StringUtils.formatStock(itemVariantStock.getVariantRealStok()));
            ObservableField observableField3 = new ObservableField(itemVariantStock.getVariantName());
            ObservableField observableField4 = new ObservableField(itemVariantStock.getVarintCode());
            VariantSelectionParams variantSelectionParams14 = this.this0.variantSelectionParams;
            if (variantSelectionParams14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
                variantSelectionParams14 = null;
            }
            ArrayList<SelectedVariant> selectedVariants = variantSelectionParams14.getSelectedVariants();
            VariantListDialogFragment variantListDialogFragment = this.this0;
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : selectedVariants) {
                SelectedVariant selectedVariant = (SelectedVariant) obj2;
                if (selectedVariant.getVariantCode().equals(itemVariantStock.getVarintCode())) {
                    String productCode2 = selectedVariant.getProductCode();
                    VariantSelectionParams variantSelectionParams15 = variantListDialogFragment.variantSelectionParams;
                    if (variantSelectionParams15 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
                        variantSelectionParams15 = null;
                    }
                    if (productCode2.equals(variantSelectionParams15.getProductCode())) {
                        arrayList.add(obj2);
                    }
                }
            }
            SelectedVariant selectedVariant2 = (SelectedVariant) CollectionsKt.firstOrNull(arrayList);
            ObservableField observableField5 = new ObservableField(StringUtils.formatStock(selectedVariant2 != null ? selectedVariant2.getAmount() : 0.0d));
            VariantSelectionParams variantSelectionParams16 = this.this0.variantSelectionParams;
            if (variantSelectionParams16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
                variantSelectionParams16 = null;
            }
            ArrayList<SelectedVariant> selectedVariants2 = variantSelectionParams16.getSelectedVariants();
            VariantListDialogFragment variantListDialogFragment2 = this.this0;
            ArrayList arrayList2 = new ArrayList();
            for (Object obj3 : selectedVariants2) {
                SelectedVariant selectedVariant3 = (SelectedVariant) obj3;
                if (selectedVariant3.getVariantCode().equals(itemVariantStock.getVarintCode())) {
                    String productCode3 = selectedVariant3.getProductCode();
                    VariantSelectionParams variantSelectionParams17 = variantListDialogFragment2.variantSelectionParams;
                    if (variantSelectionParams17 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
                        variantSelectionParams17 = null;
                    }
                    if (productCode3.equals(variantSelectionParams17.getProductCode())) {
                        arrayList2.add(obj3);
                    }
                }
            }
            ObservableField observableField6 = new ObservableField(Boxing.boxBoolean(!arrayList2.isEmpty()));
            int variantRef = itemVariantStock.getVariantRef();
            ObservableField observableField7 = new ObservableField(productPriceInfo.getFormattedPrice());
            Intrinsics.checkNotNull(productPriceInfo);
            list.add(new VariantModel(observableField, observableField2, observableField3, observableField4, observableField5, observableField6, variantRef, observableField7, productPriceInfo, false));
        }
        return Unit.INSTANCE;
    }
}
