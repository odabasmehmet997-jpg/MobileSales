package com.proje.mobilesales.features.fragment;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.databinding.FragmentVariantListDialogBinding;
import com.proje.mobilesales.features.model.VariantHeaderModel;
import com.proje.mobilesales.features.model.VariantModel;
import com.proje.mobilesales.features.model.VariantSelectionParams;
import java.util.ArrayList;
import java.util.List;

import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import okio.internal._FileSystemKtcommonListRecursively1;

final class VariantListDialogFragmentloadVariantList1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final VariantListDialogFragment this0;
    VariantListDialogFragmentloadVariantList1(VariantListDialogFragment variantListDialogFragment, Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = variantListDialogFragment;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new VariantListDialogFragmentloadVariantList1(this.this0, continuation);
    }

    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
    public Object invokeSuspend(Object obj) {
        FragmentVariantListDialogBinding binding;
        FragmentVariantListDialogBinding binding2;
        String productCode;
        Object loadItems;
        FragmentVariantListDialogBinding binding3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            binding = this.this0.getBinding();
            binding.llProgressBar.getRoot().setVisibility(0);
            binding2 = this.this0.getBinding();
            AppCompatTextView appCompatTextView = binding2.tvProduct;
            VariantSelectionParams variantSelectionParams = this.this0.variantSelectionParams;
            if (variantSelectionParams == null) {
                Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
                variantSelectionParams = null;
            }
            String productName = variantSelectionParams.getProductName();
            if (productName == null || productName.length() == 0) {
                VariantSelectionParams variantSelectionParams2 = this.this0.variantSelectionParams;
                if (variantSelectionParams2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
                    variantSelectionParams2 = null;
                }
                productCode = variantSelectionParams2.getProductCode();
            } else {
                VariantSelectionParams variantSelectionParams3 = this.this0.variantSelectionParams;
                if (variantSelectionParams3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
                    variantSelectionParams3 = null;
                }
                productCode = variantSelectionParams3.getProductName();
            }
            appCompatTextView.setText(productCode);
            VariantListDialogFragment variantListDialogFragment = this.this0;
            this.label = 1;
            loadItems = variantListDialogFragment.loadItems(this);
            if (loadItems == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                binding3 = this.this0.getBinding();
                binding3.llProgressBar.getRoot().setVisibility(8);
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
        }
        MainCoroutineDispatcher main = Dispatchers.getMain();
        C27061 c27061 = new C27061(this.this0, null);
        this.label = 2;
        if (BuildersKt.withContext(main, c27061, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        binding3 = this.this0.getBinding();
        binding3.llProgressBar.getRoot().setVisibility(8);
        return Unit.INSTANCE;
    }
    static final class C27061 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final VariantListDialogFragment this0;
  
        C27061(VariantListDialogFragment variantListDialogFragment, Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.this0 = variantListDialogFragment;
        } 
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C27061(this.this0, continuation);
        } 
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        } 
        public Object invokeSuspend(Object obj) {
            List list;
            List<VariantModel> list2;
            FragmentVariantListDialogBinding binding;
            FragmentVariantListDialogBinding binding2;
            List list3;
            FragmentVariantListDialogBinding binding3;
            VariantListDialogFragment.ItemAdapter itemAdapter;
            FragmentVariantListDialogBinding binding4;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            VariantHeaderModel variantHeaderModel = new VariantHeaderModel();
            ObservableField<Integer> selectedItemCount = variantHeaderModel.getSelectedItemCount();
            list = this.this0.mList;
            ArrayList arrayList = new ArrayList();
            Boxing boxing = null;
            for (Object obj2 : list) {
                if (Intrinsics.areEqual(((VariantModel) obj2).getChecked().get(), Boxing.boxBoolean(true))) {
                    arrayList.add(obj2);
                }
            }
            selectedItemCount.set(Boxing.boxInt(arrayList.size()));
            list2 = this.this0.mList;
            variantHeaderModel.setMVariantModelList(list2);
            binding = this.this0.getBinding();
            binding.setVariantHeader(variantHeaderModel);
            binding2 = this.this0.getBinding();
            binding2.list.setLayoutManager(new LinearLayoutManager(this.this0.getContext()));
            VariantListDialogFragment variantListDialogFragment = this.this0;
            list3 = variantListDialogFragment.mList;
            variantListDialogFragment.itemAdapter = new VariantListDialogFragment.ItemAdapter(variantListDialogFragment, list3);
            binding3 = this.this0.getBinding();
            RecyclerView recyclerView = binding3.list;
            itemAdapter = this.this0.itemAdapter;
            if (itemAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("itemAdapter");
                itemAdapter = null;
            }
            recyclerView.setAdapter(itemAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.this0.getContext(), 1);
            binding4 = this.this0.getBinding();
            binding4.list.addItemDecoration(dividerItemDecoration);
            return Unit.INSTANCE;
        }
    }
}
