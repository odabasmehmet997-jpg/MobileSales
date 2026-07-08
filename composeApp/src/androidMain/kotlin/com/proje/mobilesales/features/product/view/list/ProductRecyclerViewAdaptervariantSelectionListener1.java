package com.proje.mobilesales.features.product.view.list;

import com.proje.mobilesales.features.fragment.VariantListDialogFragment;
import com.proje.mobilesales.features.model.SelectedVariant;
import com.proje.mobilesales.features.product.model.Product;
import java.util.ArrayList;
import java.util.function.ToDoubleFunction;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
 
public final class ProductRecyclerViewAdaptervariantSelectionListener1 implements VariantListDialogFragment.IVariantSelectionListener {
    final   ProductRecyclerViewAdapter this0;
 
    public ProductRecyclerViewAdaptervariantSelectionListener1(ProductRecyclerViewAdapter productRecyclerViewAdapter) {
        this.this0 = productRecyclerViewAdapter;
    } 
    public void operationCompleted(ArrayList<SelectedVariant> arrayList, int i) {
        Intrinsics.checkNotNullParameter(arrayList, "selectedVariants");
        if (arrayList.size() > 0) {
            Product item = this.this0.getItem(i);
            if (item != null) {
                item.setMSelectedVariants(arrayList);
                item.setAmount(arrayList.stream().mapToDouble(new ToDoubleFunction() {  
                    public double applyAsDouble(Object obj) {
                        return ProductRecyclerViewAdaptervariantSelectionListener1.operationCompletedlambda1lambda0(Function1.this, obj);
                    }
                }).sum());
            }
            this.this0.notifyItemChanged(i);
            this.this0.clearEditTextFocus();
        }
    } 
    public static double operationCompletedlambda1lambda0(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "tmp0");
        return ((Number) function1.invoke(obj)).doubleValue();
    } 
    public void onCancelled() {
        this.this0.clearEditTextFocus();
    }
}
