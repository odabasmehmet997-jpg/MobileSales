package com.proje.mobilesales.features.product.view.list;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.ItemSurplusDiscountListener;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.activity.ImageViewActivity;
import com.proje.mobilesales.features.adapter.EndlessListRecyclerViewAdapter;
import com.proje.mobilesales.features.adapter.ItemViewHolder;
import com.proje.mobilesales.features.adapter.LoadingViewHolder;
import com.proje.mobilesales.features.fragment.VariantListDialogFragment;
import com.proje.mobilesales.features.model.VariantPriceParams;
import com.proje.mobilesales.features.model.VariantSelectionParams;
import com.proje.mobilesales.features.product.model.Product;
import com.proje.mobilesales.features.product.model.ProductOperationDiscount;
import com.proje.mobilesales.features.product.model.database.ItemPrice;
import com.proje.mobilesales.features.product.repository.ProductListRepository;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import com.proje.mobilesales.features.visit.model.VisitInfoShort;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ProductRecyclerViewAdapter extends EndlessListRecyclerViewAdapter<RecyclerView.ViewHolder, Product> {
    public static final Companion Companion = new Companion(null);
    private static final String STATE_PRODUCT_DISCOUNT = "state:productDiscount";
    private static final String STATE_PRODUCT_LIST = "state:productList";
    private static final String STATE_PRODUCT_SELECT_TYPE = "state:productSelectType";
    private static final String STATE_PRODUCT_SHOW_TYPE = "state:productShowType";
    private static final String STATE_SELECTED_PRODUCT_LIST = "state:selectedProductList";
    private static final String STATE_SELECTED_PRODUCT_LIST_REF = "state:selectedProductListRef";
    private static final String STATE_TEMP_PRODUCT_LIST = "state:tempProductList";
    private static final int VIEV_TYPE_ITEM_PROCESS = 2;
    private int addedSelectedProductsize;
    private int customerRef;
    private double defaultAmount;
    private String defaultExplanation;
    private int divisionNr;
    private boolean isSurplusAmountEnabled;
    private ItemSurplusDiscountListener itemSurplusDiscountListener;
    private final boolean mAutoExpand = false;
    private TypedArray mColors;
    private final ItemCountListener mItemCountListener;
    private ProductOperationDiscount mProductOperationDiscount;
    private boolean mProductSelectType;
    private boolean mProductShowType;
    private boolean mSelectProductShow;
    private final ProductListRepository repository;
    private boolean showExplanation;
    private VariantPriceParams variantPriceParams;
    private final ProductListViewModel viewModel;
    private int whNr;
    private ArrayList<Product> mProducts = new ArrayList<>();
    private ArrayList<Integer> mSelectedProductsRef = new ArrayList<>();
    private ArrayList<Product> mTempProducts = new ArrayList<>();
    private ArrayList<Product> mSelectedProducts = new ArrayList<>();
    private int lastExpandPosition = -1;
    private int lastExpandPositionProduct = -1;
    private SalesType mSalesType = SalesType.FREE;

    public interface ItemCountListener {
        void decrease();

        void increase();

        void update(int i);
    }
    protected void bindItem(RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
    }

    public boolean getmSelectedProductsSize() {
        return true;
    }

    protected void handleItemClick(Object obj, RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(obj, "item");
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
    }

    protected boolean isItemAvailable(Object obj) {
        return obj != null;
    }

    public ProductRecyclerViewAdapter(ItemCountListener itemCountListener) {
        Intrinsics.checkNotNullParameter(itemCountListener, "mItemCountListener");
        this.mItemCountListener = itemCountListener;
        ProductListRepository productListRepository = new ProductListRepository();
        this.repository = productListRepository;
        this.viewModel = new ProductListViewModel(productListRepository);
    }

    public boolean getShowExplanation() {
        return this.showExplanation;
    }

    public void setShowExplanation(boolean z) {
        this.showExplanation = z;
    }

    public String getDefaultExplanation() {
        return this.defaultExplanation;
    }

    public void setDefaultExplanation(String str) {
        this.defaultExplanation = str;
    }

    public SalesType getMSalesType() {
        return this.mSalesType;
    }

    public void setMSalesType(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "<set-?>");
        this.mSalesType = salesType;
    }

    public VariantPriceParams getVariantPriceParams() {
        return this.variantPriceParams;
    }

    public void setVariantPriceParams(VariantPriceParams variantPriceParams) {
        this.variantPriceParams = variantPriceParams;
    }

    public int getWhNr() {
        return this.whNr;
    }

    public void setWhNr(int i) {
        this.whNr = i;
    }

    public int getDivisionNr() {
        return this.divisionNr;
    }

    public void setDivisionNr(int i) {
        this.divisionNr = i;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        Context context = this.mContext;
        if (context instanceof BaseInjectableActivity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
            ((BaseInjectableActivity) context).getActivityComponent().inject(this);
        }
        this.mColors = this.mContext.getResources().obtainTypedArray(R.array.color_codes);
        this.isSurplusAmountEnabled = this.viewModel.getSurplusAmountEnabled();
    }
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        TypedArray typedArray = this.mColors;
        Intrinsics.checkNotNull(typedArray);
        typedArray.recycle();
    }
     public long getItemId(int i) {
        int i2;
        Product item = getItem(i);
        if (item == null) {
            return super.getItemId(i);
        }
        if (item.getService()) {
            String sb = String.valueOf(item.getLogicalRef()) +
                    '0';
            i2 = StringUtils.convertStringToInt(sb);
        } else {
            String sb2 = String.valueOf(item.getLogicalRef()) +
                    '1';
            i2 = StringUtils.convertStringToInt(sb2);
        }
        return i2;
    }
    protected void clearViewHolder(RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        if (viewHolder instanceof ProductViewHolder) {
            ((ProductViewHolder) viewHolder).mProductView.reset();
        } else if (viewHolder instanceof ProductOperationViewHolder) {
            ((ProductOperationViewHolder) viewHolder).mProductOperationView.reset(0.0d, 0.0d);
        }
    }

    public Product getItem(int i) {
        return this.mProducts.get(i);
    }

    public ArrayList<Product> getProducts() {
        return this.mProducts;
    }
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        if (i == this.VIEW_TYPE_ITEM) {
            View inflate = this.mInflater.inflate(R.layout.item_product, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
            return new ProductViewHolder(inflate);
        } else if (i != 2) {
            return new LoadingViewHolder(this.mInflater.inflate(R.layout.item_loading, viewGroup, false));
        } else {
            View inflate2 = this.mInflater.inflate(R.layout.item_product_operation, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate2, "inflate(...)");
            return new ProductOperationViewHolder(inflate2);
        }
    }
    public VariantListDialogFragment.IVariantSelectionListener getVariantSelectionListener() {
        return new ProductRecyclerViewAdaptervariantSelectionListener1(this);
    }
    public void clearEditTextFocus() {
        if (ContextUtils.getmActivity() != null) {
            View currentFocus = ContextUtils.getmActivity().getCurrentFocus();
            AppCompatEditText appCompatEditText = currentFocus instanceof AppCompatEditText ? (AppCompatEditText) currentFocus : null;
            if (appCompatEditText != null) {
                appCompatEditText.clearFocus();
            }
        }
    }
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        String unitCode2;
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        if (viewHolder instanceof ProductViewHolder productViewHolder) {
            Product item = getItem(viewHolder.getAdapterPosition());
            checkCardViewStatus((ItemViewHolder) viewHolder);
            clearViewHolder(viewHolder);
            if (isItemAvailable(item) && item != null) {
                item.setSelect(isSelected(item.getLogicalRef(), item.getService()));
                boolean z = this.lastExpandPositionProduct == i;
                changeToggleState(productViewHolder, z);
                setProductViewHolder(productViewHolder, z, item, i);
            }
        } else if (viewHolder instanceof ProductOperationViewHolder productOperationViewHolder) {
            Product item2 = getItem(i);
            Intrinsics.checkNotNull(item2);
            item2.setLineNr(i);
            if (isItemAvailable(item2)) {
                boolean z2 = i == this.lastExpandPosition;
                changeToggleState(productOperationViewHolder, z2);
                productOperationViewHolder.mProductOperationView.toggleExpand(item2, z2, this.addedSelectedProductsize, this.viewModel.getSalesFicheApplyPromotion(this.mSalesType), this.isSurplusAmountEnabled);
                checkCardViewStatus((ItemViewHolder) viewHolder);
                clearViewHolder(viewHolder);
                if (isItemAvailable(item2)) {
                    controlProductProcess(item2);
                    int logicalRef = item2.getLogicalRef();
                    String code = item2.getCode();
                    Intrinsics.checkNotNull(code);
                    String name = item2.getName();
                    Intrinsics.checkNotNull(name);
                    boolean divUnit = item2.getDivUnit();
                    int i2 = this.whNr;
                    VariantPriceParams variantPriceParams = this.variantPriceParams;
                    int unitRef = item2.getUnitRef();
                    if (TextUtils.isEmpty(item2.getUnitCode2())) {
                        unitCode2 = "";
                    } else {
                        unitCode2 = item2.getUnitCode2();
                        Intrinsics.checkNotNull(unitCode2);
                    }
                    VariantSelectionParams variantSelectionParams = new VariantSelectionParams(logicalRef, code, name, divUnit, i2, i, variantPriceParams, unitRef, unitCode2, item2.getMSelectedVariants(), this.divisionNr);
                    productOperationViewHolder.mProductOperationView.setVariantSelectionListener(getVariantSelectionListener());
                    productOperationViewHolder.mProductOperationView.setVariantSelectionParams(variantSelectionParams);
                    productOperationViewHolder.mProductOperationView.setPromotionItemPriceEnabled(this.viewModel.getIsPromotionItemPriceEnabled());
                    ProductOperationView productOperationView = productOperationViewHolder.mProductOperationView;
                    SalesType salesType = this.mSalesType;
                    int i3 = this.addedSelectedProductsize;
                    boolean salesFicheApplyPromotion = this.viewModel.getSalesFicheApplyPromotion(salesType);
                    BaseErp<?> baseErp = this.viewModel.getBaseErp();
                    boolean enterPrice = this.viewModel.getEnterPrice();
                    boolean changePrice = this.viewModel.getChangePrice();
                    String priceWithDigits = item2.getPriceWithDigits();
                    productOperationView.setProductProcess(item2, salesType, i3, salesFicheApplyPromotion, baseErp.isPriceCanBeEnter(enterPrice, changePrice, priceWithDigits == null || priceWithDigits.length() == 0), item2.getVariant(), this.isSurplusAmountEnabled);
                    productOperationViewHolder.mProductOperationView.getToggleButton().setOnClickListener(new View.OnClickListener(viewHolder, z2, i) { // from class: com.proje.mobilesales.features.product.view.list.ProductRecyclerViewAdapterExternalSyntheticLambda3
                        public final RecyclerView.ViewHolder f1;
                        public final boolean f2;
                        public final int f3;

                        {
                            this.f1 = r2;
                            this.f2 = r3;
                            this.f3 = r4;
                        }

                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            ProductRecyclerViewAdapter.onBindViewHolderlambda1(ProductRecyclerViewAdapter.this, this.f1, this.f2, this.f3, view);
                        }
                    });
                }
            }
        } else if (viewHolder instanceof LoadingViewHolder) {
            ((LoadingViewHolder) viewHolder).progressBar.setIndeterminate(true);
        }
    }
    public static void onBindViewHolderlambda1(ProductRecyclerViewAdapter productRecyclerViewAdapter, RecyclerView.ViewHolder viewHolder, boolean z, int i, View view) {
        Intrinsics.checkNotNullParameter(productRecyclerViewAdapter, "this0");
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        productRecyclerViewAdapter.changeToggleState((ProductOperationViewHolder) viewHolder, z);
        if (z) {
            i = -1;
        }
        productRecyclerViewAdapter.lastExpandPosition = i;
        TransitionManager.beginDelayedTransition(productRecyclerViewAdapter.mRecyclerView);
        productRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void setProductViewHolder(ProductViewHolder productViewHolder, boolean z, Product product, int i) {
        productViewHolder.mProductView.toggleExpand(z);
        productViewHolder.mProductView.toggleToggleExpand(product.getSelect());
        productViewHolder.mProductView.getImgProductImage().setOnClickListener(new View.OnClickListener(product) {
            public final Product f1;

            {
                this.f1 = r2;
            }
            public void onClick(View view) {
                ProductRecyclerViewAdapter.setProductViewHolderlambda2(ProductRecyclerViewAdapter.this, this.f1, view);
            }
        });
        productViewHolder.mProductView.setProduct(product, this.mProductShowType, this.mSalesType, product.getVariant() ? 0.0d : this.defaultAmount);
        if (this.mProductSelectType) {
            productViewHolder.mProductView.getRltProductHeader().setOnClickListener(new View.OnClickListener(product, i) { // from class: com.proje.mobilesales.features.product.view.list.ProductRecyclerViewAdapterExternalSyntheticLambda7
                public final Product f1;
                public final int f2;

                {
                    this.f1 = r2;
                    this.f2 = r3;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ProductRecyclerViewAdapter.setProductViewHolderlambda3(ProductRecyclerViewAdapter.this, this.f1, this.f2, view);
                }
            });
        }
        setVisibilityProductInfoButton(productViewHolder.mProductView.getBtnProductInfo(), product.getLogicalRef());
        productViewHolder.mProductView.getToggleButton().setOnClickListener(new View.OnClickListener(this, productViewHolder, z, i) { // from class: com.proje.mobilesales.features.product.view.list.ProductRecyclerViewAdapterExternalSyntheticLambda8
            public final ProductRecyclerViewAdapter f1;
            public final ProductViewHolder f2;
            public final boolean f3;
            public final int f4;
            {
                this.f1 = r2;
                this.f2 = r3;
                this.f3 = r4;
                this.f4 = r5;
            }
            public void onClick(View view) {
                ProductRecyclerViewAdapter.setProductViewHolderlambda4(Product.this, this.f1, this.f2, this.f3, this.f4, view);
            }
        });
    }
    public static void setProductViewHolderlambda2(ProductRecyclerViewAdapter productRecyclerViewAdapter, Product product, View view) {
        Intrinsics.checkNotNullParameter(productRecyclerViewAdapter, "this0");
        Intrinsics.checkNotNullParameter(product, "it");
        Intent intent = new Intent(productRecyclerViewAdapter.mContext, ImageViewActivity.class);
        intent.putExtra(IntentExtraName.EXTRA_ITEM_ID, product.getLogicalRef());
        productRecyclerViewAdapter.mContext.startActivity(intent);
    }
    public static void setProductViewHolderlambda3(ProductRecyclerViewAdapter productRecyclerViewAdapter, Product product, int i, View view) {
        Intrinsics.checkNotNullParameter(productRecyclerViewAdapter, "this0");
        Intrinsics.checkNotNullParameter(product, "it");
        productRecyclerViewAdapter.productOnClick(product);
        TransitionManager.beginDelayedTransition(productRecyclerViewAdapter.mRecyclerView);
        productRecyclerViewAdapter.notifyItemChanged(i);
    }
    public static void setProductViewHolderlambda4(Product product, ProductRecyclerViewAdapter productRecyclerViewAdapter, ProductViewHolder productViewHolder, boolean z, int i, View view) {
        Intrinsics.checkNotNullParameter(product, "it");
        Intrinsics.checkNotNullParameter(productRecyclerViewAdapter, "this0");
        Intrinsics.checkNotNullParameter(productViewHolder, "holder");
        if (product.getSelect()) {
            productRecyclerViewAdapter.changeToggleState(productViewHolder, z);
            ProductView productView = productViewHolder.mProductView;
            if (!z) {
                productView.focusAndShowKeyBoard();
            } else {
                productView.hideKeyboard();
            }
            if (z) {
                i = -1;
            }
            productRecyclerViewAdapter.lastExpandPositionProduct = i;
            TransitionManager.beginDelayedTransition(productRecyclerViewAdapter.mRecyclerView);
            productRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    private void controlProductProcess(Product product) {
        if (product.getMItemUnits() == null) {
            product.initUnitList(this.viewModel.getProductUnitsFromSqlHelper(product.getLogicalRef(), product.getService()));
            if (product.isBarcode()) {
                product.selectUnitLogicalRef(product.getBarcodeUnitRef());
            } else {
                product.selectUnitLastIndex();
            }
        }
    }

    private void checkCardViewStatus(ItemViewHolder itemViewHolder) {
        if (this.mCardViewEnabled) {
            CardView cardView = itemViewHolder.mCardView;
            Intrinsics.checkNotNull(cardView);
            cardView.setCardElevation((float) this.mCardElevation);
            itemViewHolder.mCardView.setRadius((float) this.mCardRadius);
            itemViewHolder.mCardView.setUseCompatPadding(true);
            return;
        }
        itemViewHolder.mCardView.setCardElevation(0.0f);
        itemViewHolder.mCardView.setRadius(0.0f);
        itemViewHolder.mCardView.setUseCompatPadding(false);
    }

    private void productOnClick(Product product) {
        product.setSelect(!product.getSelect());
        if (product.getSelect()) {
            this.mItemCountListener.increase();
            this.mSelectedProductsRef.add(Integer.valueOf(product.getLogicalRef()));
            controlClickedProduct(product);
            controlProductItemProperties(product);
            this.mSelectedProducts.add(product);
            return;
        }
        this.mItemCountListener.decrease();
        removeSelectedProductsList(product.getLogicalRef(), product.getService());
        this.mSelectedProducts.remove(product);
        this.lastExpandPositionProduct = -1;
        if (product.getVariant()) {
            product.setAmount(0.0d);
        }
        product.setMSelectedVariants(new ArrayList<>());
    }

    private void controlProductItemProperties(Product product) {
        if (product.getItemSecondUnitCodeList() == null) {
            product.initSecondUnitList(this.viewModel.getProductUnitsFromSqlHelper(product.getLogicalRef(), product.getService()));
            controlSecondUnitIndex(product);
        }
    }

    private void controlSecondUnitIndex(Product product) {
        if (product.getSecondSelectUnitIndex() > -1) {
            product.selectSecondUnitLastIndex();
        } else if (product.getDefUnitRef() > 0) {
            product.selectSecondUnitLogicalRef(product.getDefUnitRef());
        } else {
            product.selectSecondUnitIndexFirst();
        }
    }

    private void controlClickedProduct(Product product) {
        if (product.getMItemUnits() == null) {
            product.initUnitList(this.viewModel.getProductUnitsFromSqlHelper(product.getLogicalRef(), product.getService()));
            Log.d(MobileSales.TAG, "onBindViewHolder: " + product.isBarcode());
            if (product.isBarcode()) {
                product.selectUnitLogicalRef(product.getBarcodeUnitRef());
            } else {
                controlProductUnitRef(product);
            }
        }
    }

    private void controlProductUnitRef(Product product) {
        if (product.getDefUnitRef() > 0) {
            product.selectUnitLogicalRef(product.getDefUnitRef());
        } else {
            controlProductSelectUnitIndex(product);
        }
    }

    private void controlProductSelectUnitIndex(Product product) {
        if (product.getSelectUnitIndex() > -1) {
            product.selectUnitLastIndex();
        } else if (product.getDefUnitRef() > 0) {
            product.selectUnitLogicalRef(product.getDefUnitRef());
        } else {
            initUnitIndex(product);
        }
    }

    private void initUnitIndex(Product product) {
        if (product.getPriceRef() <= 0 || this.viewModel.erpType() != ErpType.NETSIS) {
            product.selectUnitIndexFirst();
            return;
        }
        List<ItemPrice> itemPriceTypeTableFromLogoSqlHelper = this.viewModel.getItemPriceTypeTableFromLogoSqlHelper(ItemPrice.class, "LOGICALREF=?", new String[]{String.valueOf(product.getPriceRef())});
        if (itemPriceTypeTableFromLogoSqlHelper == null || itemPriceTypeTableFromLogoSqlHelper.isEmpty()) {
            product.selectUnitIndexFirst();
        } else {
            product.selectUnitLineNr(StringUtils.convertStringToInt(itemPriceTypeTableFromLogoSqlHelper.get(0).unitCode));
        }
    }

    public void checkAllProducts(boolean z) {
        Iterator<Product> it = this.mProducts.iterator();
        while (it.hasNext()) {
            Product next = it.next();
            if (next != null) {
                IntStream.range(0, this.mSelectedProducts.size()).filter(new IntPredicate(this) { // from class: com.proje.mobilesales.features.product.view.list.ProductRecyclerViewAdapterExternalSyntheticLambda0
                    public final ProductRecyclerViewAdapter f1;

                    {
                        this.f1 = r2;
                    }

                    @Override // java.util.function.IntPredicate
                    public boolean test(int i) {
                        return ProductRecyclerViewAdapter.checkAllProductslambda5(Product.this, this.f1, i);
                    }
                }).findFirst().ifPresent(new IntConsumer() { // from class: com.proje.mobilesales.features.product.view.list.ProductRecyclerViewAdapterExternalSyntheticLambda1
                    @Override // java.util.function.IntConsumer
                    public void accept(int i) {
                        ProductRecyclerViewAdapter.checkAllProductslambda6(Product.this, i);
                    }
                });
                if (next.getSelect() != z) {
                    next.setSelect(!z);
                    productOnClick(next);
                }
            }
        }
        notifyDataSetChanged();
    }


    public static boolean checkAllProductslambda5(Product product, ProductRecyclerViewAdapter productRecyclerViewAdapter, int i) {
        Product product2;
        Intrinsics.checkNotNullParameter(productRecyclerViewAdapter, "this0");
        Product product3 = productRecyclerViewAdapter.mSelectedProducts.get(i);
        return product3 != null && product.getLogicalRef() == product3.getLogicalRef() && (product2 = productRecyclerViewAdapter.mSelectedProducts.get(i)) != null && product.getService() == product2.getService();
    }


    public static void checkAllProductslambda6(Product product, int i) {
        product.setSelect(true);
    }

    private void removeSelectedProductsList(int i, boolean z) {
        boolean z2;
        Iterator<Product> it = this.mSelectedProducts.iterator();
        int i2 = -1;
        while (true) {
            if (!it.hasNext()) {
                z2 = false;
                break;
            }
            Product next = it.next();
            i2++;
            if (next != null && next.getLogicalRef() == i && next.getService() == z) {
                z2 = true;
                break;
            }
        }
        if (z2) {
            this.mSelectedProducts.remove(i2);
            this.mSelectedProductsRef.remove(i2);
        }
    }

    private void changeToggleState(ProductOperationViewHolder productOperationViewHolder, boolean z) {
        productOperationViewHolder.mProductOperationView.getToggle().setCompoundDrawablesWithIntrinsicBounds(0, 0, z ? R.drawable.ic_chevron_up_white_24dp : R.drawable.ic_chevron_down_white_24dp, 0);
    }

    private void changeToggleState(ProductViewHolder productViewHolder, boolean z) {
        productViewHolder.mProductView.getToggle().setCompoundDrawablesWithIntrinsicBounds(0, 0, z ? R.drawable.ic_chevron_up_white_36dp : R.drawable.ic_chevron_down_white_36dp, 0);
    }

    public boolean isSelected(int i) {
        int size = this.mSelectedProductsRef.size();
        for (int i2 = 0; i2 < size; i2++) {
            Integer num = this.mSelectedProductsRef.get(i2);
            if (num != null && num.intValue() == i) {
                return true;
            }
        }
        return false;
    }

    private boolean isSelected(int i, boolean z) {
        Product product;
        int size = this.mSelectedProducts.size();
        for (int i2 = 0; i2 < size; i2++) {
            Product product2 = this.mSelectedProducts.get(i2);
            if (product2 != null && product2.getLogicalRef() == i && (product = this.mSelectedProducts.get(i2)) != null && product.getService() == z) {
                return true;
            }
        }
        return false;
    }
    public int getItemCount() {
        return this.mProducts.size();
    }
     public Bundle saveState() {
        Bundle saveState = super.saveState();
        saveState.putBoolean(STATE_PRODUCT_SELECT_TYPE, this.mProductSelectType);
        saveState.putBoolean(STATE_PRODUCT_SHOW_TYPE, this.mProductShowType);
        saveState.putIntegerArrayList(STATE_SELECTED_PRODUCT_LIST_REF, this.mSelectedProductsRef);
        saveState.putParcelable(STATE_PRODUCT_DISCOUNT, this.mProductOperationDiscount);
        saveState.putParcelableArrayList(STATE_SELECTED_PRODUCT_LIST, this.mSelectedProducts);
        Intrinsics.checkNotNull(saveState);
        return saveState;
    }

    public void restoreState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "savedState");
        super.restoreState(bundle);
        this.mProductSelectType = bundle.getBoolean(STATE_PRODUCT_SELECT_TYPE);
        this.mProductShowType = bundle.getBoolean(STATE_PRODUCT_SHOW_TYPE);
        ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList(STATE_SELECTED_PRODUCT_LIST_REF);
        Intrinsics.checkNotNull(integerArrayList);
        this.mSelectedProductsRef = integerArrayList;
        this.mProductOperationDiscount = bundle.getParcelable(STATE_PRODUCT_DISCOUNT);
        ArrayList<Product> parcelableArrayList = bundle.getParcelableArrayList(STATE_SELECTED_PRODUCT_LIST);
        Intrinsics.checkNotNull(parcelableArrayList);
        this.mSelectedProducts = parcelableArrayList;
        Log.d(MobileSales.TAG, "restoreState: ");
        notifyDataSetChanged();
    }

    public void setmProductSelectType(boolean z) {
        this.mProductSelectType = z;
    }

    public void setmProductShowType(boolean z) {
        this.mProductShowType = z;
    }

    public void setDefaultAmount(double d) {
        this.defaultAmount = d;
    }

    public void setProductOperationDiscount(ProductOperationDiscount productOperationDiscount) {
        this.mProductOperationDiscount = productOperationDiscount;
    }
    public void insertProgressBar() {
        if (!this.mSelectProductShow && !this.mProducts.contains(null)) {
            this.mProducts.add(null);
            notifyItemInserted(getItemCount() - 1);
        }
    }
    public void removeProgressBar() {
        int indexOf = this.mProducts.indexOf(null);
        if (indexOf != -1) {
            this.mProducts.remove(indexOf);
            notifyItemRemoved(indexOf);
        }
    }
    public void addItem(ArrayList<ArrayList<VisitInfoShort>> list) {
        removeProgressBar();
        if (!this.mSelectProductShow && list != null) {
            Iterator<Product> it = this.mSelectedProducts.iterator();
            while (it.hasNext()) {
                Product next = it.next();
                if (next != null) {
                    list.stream().filter(new Predicate() {
                        public boolean test(Object obj) {
                            return ProductRecyclerViewAdapter.addItemlambda7(Function1.this, obj);
                        }
                    }).findFirst().ifPresent(new Consumer() {
                        public void accept(Object obj) {
                            ProductRecyclerViewAdapter.addItemlambda8(Function1.this, obj);
                        }
                    });
                }
            }
            this.mProducts.addAll(list);
            this.lastExpandPositionProduct = -1;
            this.mRecyclerView.getRecycledViewPool().clear();
            notifyDataSetChanged();
            this.mTempProducts = this.mProducts;
        }
    }


    public static boolean addItemlambda7(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "tmp0");
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }


    public static void addItemlambda8(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "tmp0");
        function1.invoke(obj);
    }


    public void setTempPrices(Product product, Product product2) {
        if (product.getTempPriceRef() <= 0) {
            product2.setTempPriceRef(product.getPriceRef());
            product2.setTempPrice(product.getPrice());
            product2.setTempPriceWithDigits(product.getPriceWithDigits());
            product2.setTempCPrice(product.getCPrice());
            return;
        }
        product2.setTempPriceRef(product.getTempPriceRef());
        product2.setTempPrice(product.getPrice());
        product2.setTempPriceWithDigits(product.getTempPriceWithDigits());
        product2.setTempCPrice(product.getTempCPrice());
    }

    public ArrayList<Product> getmSelectedProducts() {
        Iterator<Product> it = this.mSelectedProducts.iterator();
        while (it.hasNext()) {
            Product next = it.next();
            controlProductOperationDiscount(next);
            Log.d(MobileSales.TAG, "getmSelectedProducts: " + isShowExplanation());
            if (TextUtils.isEmpty(next != null ? next.getExplanation() : null) && next != null) {
                next.setExplanation("");
            }
            if (next != null) {
                next.setShowExplanation(this.showExplanation);
            }
            setSelectedProductTempPrices(next);
        }
        return this.mSelectedProducts;
    }

    private void setSelectedProductTempPrices(Product product) {
        Iterator<Product> it = this.mProducts.iterator();
        while (it.hasNext()) {
            Product next = it.next();
            if (!(next == null || product == null || next.getLogicalRef() != product.getLogicalRef())) {
                product.setAmount(next.getAmount());
                product.selectUnitIndex(next.getSelectUnitIndex());
                setTempPrices(next, product);
            }
        }
    }

    private void controlProductOperationDiscount(Product product) {
        if ((product != null ? product.getProductOperationDiscount() : null) == null) {
            if (product != null) {
                product.setProductOperationDiscount(this.mProductOperationDiscount);
            }
            Intrinsics.checkNotNull(product);
            double d = 0.0d;
            if (product.getAmount() == 0.0d) {
                if (!product.getVariant()) {
                    d = this.defaultAmount;
                }
                product.setAmount(d);
            }
        }
    }

    public boolean isShowExplanation() {
        return this.showExplanation;
    }

    public void showExplanation(boolean z) {
        this.showExplanation = z;
    }

    public int getmSelectedProductsCount() {
        return this.mSelectedProducts.size();
    }

    public void selectAllProduct() {
        Iterator<Product> it = this.mProducts.iterator();
        while (it.hasNext()) {
            Product next = it.next();
            if (next != null && !this.mSelectedProductsRef.contains(Integer.valueOf(next.getLogicalRef()))) {
                this.mSelectedProductsRef.add(Integer.valueOf(next.getLogicalRef()));
                if (TextUtils.isEmpty(next.getUnitCodes())) {
                    next.initUnitList(this.viewModel.getProductUnitsFromSqlHelper(next.getLogicalRef(), next.getService()));
                    Log.d(MobileSales.TAG, "onBindViewHolder: " + next.isBarcode());
                    controlSelectedProductBarcode(next);
                }
                this.mSelectedProducts.add(next);
            }
        }
        notifyDataSetChanged();
    }

    private void controlSelectedProductBarcode(Product product) {
        if (product.isBarcode()) {
            product.selectUnitLogicalRef(product.getBarcodeUnitRef());
        } else if (product.getDefUnitRef() > 0) {
            product.selectUnitLogicalRef(product.getDefUnitRef());
        } else {
            product.selectUnitIndexFirst();
        }
    }

    public void selectOne(Product product) {
        Intrinsics.checkNotNullParameter(product, "_product");
        int size = this.mProducts.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            Product product2 = this.mProducts.get(i);
            if (product2 == null || product2.getLogicalRef() != product.getLogicalRef()) {
                i++;
            } else if (!this.mSelectedProductsRef.contains(Integer.valueOf(product2.getLogicalRef()))) {
                this.mSelectedProductsRef.add(Integer.valueOf(product2.getLogicalRef()));
                this.mItemCountListener.increase();
                addSelectedProduct(product, i);
            } else if (controlSelectedProductSerialList(product, i)) {
                return;
            }
        }
        notifyDataSetChanged();
    }

    private boolean controlSelectedProductSerialList(Product product, int i) {
        if (product.getSalesSerialLots().isEmpty()) {
            setProductToSelectedProduct(product, i);
            return false;
        } else return controlSelectedProduct(product);
    }

    private void setProductToSelectedProduct(Product product, int i) {
        try {
            int size = this.mSelectedProducts.size();
            int i2 = 0;
            while (true) {
                if (i2 < size) {
                    Product product2 = this.mSelectedProducts.get(i2);
                    if (product2 != null && product2.getLogicalRef() == product.getLogicalRef()) {
                        ArrayList<Product> arrayList = this.mSelectedProducts;
                        Object clone = product.clone();
                        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type com.proje.mobilesales.features.product.model.Product");
                        arrayList.set(i2, (Product) clone);
                        break;
                    }
                    i2++;
                } else {
                    break;
                }
            }
            ArrayList<Product> arrayList2 = this.mProducts;
            Object clone2 = product.clone();
            Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type com.proje.mobilesales.features.product.model.Product");
            arrayList2.set(i, (Product) clone2);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private boolean controlSelectedProduct(Product product) {
        Iterator<Product> it = this.mSelectedProducts.iterator();
        while (it.hasNext()) {
            Product next = it.next();
            if (next != null && next.getLogicalRef() == product.getLogicalRef()) {
                Iterator<Serilot> it2 = product.getSalesSerialLots().iterator();
                while (it2.hasNext()) {
                    Serilot next2 = it2.next();
                    Iterator<Serilot> it3 = next.getSalesSerialLots().iterator();
                    while (it3.hasNext()) {
                        if (Intrinsics.areEqual(next2.code, it3.next().code)) {
                            return true;
                        }
                    }
                    next.getSalesSerialLots().add(next2);
                    next.setAmount(next.getAmount() + ((double) 1));
                }
                return true;
            }
        }
        return false;
    }

    private void addSelectedProduct(Product product, int i) {
        try {
            ArrayList<Product> arrayList = this.mSelectedProducts;
            Object clone = product.clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type com.proje.mobilesales.features.product.model.Product");
            arrayList.add((Product) clone);
            ArrayList<Product> arrayList2 = this.mProducts;
            Object clone2 = product.clone();
            Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type com.proje.mobilesales.features.product.model.Product");
            arrayList2.set(i, (Product) clone2);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void selectProductWithSeri(int i, Serilot serilot) {
        Intrinsics.checkNotNullParameter(serilot, "serilot");
        Iterator<Product> it = this.mProducts.iterator();
        while (it.hasNext()) {
            Product next = it.next();
            if (next != null && next.getLogicalRef() == i) {
                if (!this.mSelectedProductsRef.contains(Integer.valueOf(next.getLogicalRef()))) {
                    this.mSelectedProductsRef.add(Integer.valueOf(next.getLogicalRef()));
                    this.mSelectedProducts.add(next);
                }
                next.getSalesSerialLots().add(serilot);
                next.setAmount(serilot.amount);
                return;
            }
        }
    }

    public void setSeriToProducts(ArrayList<ProductListFragment.SeriLotItem> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "seriLotItems");
        Iterator<Product> it = this.mProducts.iterator();
        while (it.hasNext()) {
            Product next = it.next();
            Iterator<ProductListFragment.SeriLotItem> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                ProductListFragment.SeriLotItem next2 = it2.next();
                if (next != null && next.getLogicalRef() == next2.getItemRef()) {
                    ArrayList<Serilot> salesSerialLots = next.getSalesSerialLots();
                    Serilot serilot = next2.getSerilot();
                    Intrinsics.checkNotNull(serilot);
                    salesSerialLots.add(serilot);
                    double amount = next.getAmount();
                    Serilot serilot2 = next2.getSerilot();
                    Intrinsics.checkNotNull(serilot2);
                    next.setAmount(amount + serilot2.reAmount);
                }
            }
        }
    }

    @Override // com.proje.mobilesales.features.adapter.EndlessListRecyclerViewAdapter
    public void restartAdapterAndScroll() {
        if (getItemCount() > 0) {
            this.mProducts.clear();
        }
        notifyDataSetChanged();
    }

    @Override // com.proje.mobilesales.features.adapter.EndlessListRecyclerViewAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (getItem(i) == null) {
            return this.VIEW_TYPE_LOADING;
        }
        Product item = getItem(i);
        Intrinsics.checkNotNull(item);
        if (!item.getSelect() || !this.mSelectProductShow) {
            return this.VIEW_TYPE_ITEM;
        }
        return 2;
    }

    public void setSalesType(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        this.mSalesType = salesType;
    }

    public void setSelectProductShow(boolean z) {
        this.mRecyclerView.removeAllViews();
        this.mSelectProductShow = z;
        swapData();
    }

    private void swapData() {
        if (this.mSelectProductShow) {
            this.mProducts = getmSelectedProducts();
        } else {
            this.mProducts = this.mTempProducts;
        }
        notifyDataSetChanged();
    }

    public void setProductShowType(boolean z) {
        this.mProductShowType = z;
    }

    public void setAddedSelectedProductsize(int i) {
        this.addedSelectedProductsize = i;
    }

    public void setCustomerRef(int i) {
        this.customerRef = i;
    }

    public void setItemSurplusDiscountListener(ItemSurplusDiscountListener itemSurplusDiscountListener) {
        this.itemSurplusDiscountListener = itemSurplusDiscountListener;
    }

    private void setVisibilityProductInfoButton(ImageButton imageButton, int i) {
        String customerConditionCodeFromSqlHelper = this.viewModel.getCustomerConditionCodeFromSqlHelper(this.customerRef);
        if (this.viewModel.erpType() != ErpType.NETSIS || !this.mProductSelectType || TextUtils.isEmpty(customerConditionCodeFromSqlHelper)) {
            imageButton.setVisibility(View.GONE);
            return;
        }
        imageButton.setVisibility(View.VISIBLE);
        imageButton.setOnClickListener(new View.OnClickListener(i, customerConditionCodeFromSqlHelper) { // from class: com.proje.mobilesales.features.product.view.list.ProductRecyclerViewAdapterExternalSyntheticLambda2
            public final  int f1;
            public final   String f2;

            {
                this.f1 = r2;
                this.f2 = r3;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ProductRecyclerViewAdapter.setVisibilityProductInfoButtonlambda10(ProductRecyclerViewAdapter.this, this.f1, this.f2, view);
            }
        });
    }


    public static void setVisibilityProductInfoButtonlambda10(ProductRecyclerViewAdapter productRecyclerViewAdapter, int i, String str, View view) {
        Intrinsics.checkNotNullParameter(productRecyclerViewAdapter, "this0");
        Intrinsics.checkNotNullParameter(str, "customerConditionCode");
        productRecyclerViewAdapter.setProductInfoClickListener(i, str);
    }

    private void setProductInfoClickListener(int i, String str) {
        ItemSurplusDiscountListener itemSurplusDiscountListener = this.itemSurplusDiscountListener;
        Intrinsics.checkNotNull(itemSurplusDiscountListener);
        itemSurplusDiscountListener.getItemSurplusDiscount(i, str);
    }

    /* compiled from: ProductRecyclerViewAdapter.kt */

    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
