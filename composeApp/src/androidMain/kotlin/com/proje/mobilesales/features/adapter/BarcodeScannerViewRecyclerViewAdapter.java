package com.proje.mobilesales.features.adapter;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.enums.TrackType;
import com.proje.mobilesales.core.interfaces.*;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.sql.SqlManager;
import com.proje.mobilesales.core.typs.ParameterTypes;
import com.proje.mobilesales.core.utils.*;
import com.proje.mobilesales.core.widget.TintableTextView;
import com.proje.mobilesales.features.activity.BarcodeScannerView;
import com.proje.mobilesales.features.dbmodel.Price;
import com.proje.mobilesales.features.fragment.VariantListDialogFragment;
import com.proje.mobilesales.features.model.*;
import com.proje.mobilesales.features.product.model.Product;
import com.proje.mobilesales.features.product.model.ProductOperationDiscount;
import org.jetbrains.annotations.UnknownNullability;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleFunction;

import static com.proje.mobilesales.core.utils.AppUtils.createLayoutInflater;

public class BarcodeScannerViewRecyclerViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    BaseErp baseErp;
    private boolean isSurplusAmountEnabled;
    AlertDialogBuilder mAlertDialogBuilder;
    protected Context mContext;
    private int mCustomerRef;
    private ArrayList<BarcodeItem> mDatas = new ArrayList<>();
    private int mDivisionNr;
    protected LayoutInflater mLayoutInflater;
    private int mPaymentRef;
    private ProductOperationDiscount mProductOperationDiscount;
    ProgressDialogBuilder mProgressDialogBuilder;
    private SalesType mSalesType;
    private String mSpecode;
    private String mTradingGrp;
    private VariantPriceParams mVariantPriceParams;
    private int mWhRef;
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        Context context = recyclerView.getContext();
        this.mContext = context;
        if (context instanceof BaseInjectableActivity) {
            ((BaseInjectableActivity) context).getActivityComponent().inject(this);
        }
        this.mLayoutInflater = createLayoutInflater(this.mContext);
        this.isSurplusAmountEnabled = this.baseErp.getSurplusAmountEnabled();
    }
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        for (int i2 = 0; i2 < recyclerView.getChildCount(); i2++) {
        }
        super.onDetachedFromRecyclerView(recyclerView);
        this.mContext = null;
    }
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new ItemViewHolder(this.mLayoutInflater.inflate(R.layout.item_barcode_product, viewGroup, false), new AmountTextListener(), new ExplanationTextListener(), new CustomCheckedListener(), new DiscountViewListener[3], new SecondAmountTextListener(), new SurplusAmountTextListener());
    }

    public void onBindViewHolder(@NonNull com.proje.mobilesales.features.adapter.ItemViewHolder holder, int position) {

    }

    public BarcodeItem getProperty(int i2) {
        return this.mDatas.get(i2);
    }

    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i2) {
        bindData(itemViewHolder, getProperty(i2), i2);
    }

    public void setProductOperationDiscount(ProductOperationDiscount productOperationDiscount) {
        this.mProductOperationDiscount = productOperationDiscount;
    }

    public void setSalesType(SalesType salesType) {
        this.mSalesType = salesType;
    }

    public void setCustomerRef(int i2) {
        this.mCustomerRef = i2;
    }

    public void setPaymentRef(int i2) {
        this.mPaymentRef = i2;
    }

    public void setTradingGrp(String str) {
        this.mTradingGrp = str;
    }

    void bindData(final ItemViewHolder itemViewHolder, final BarcodeItem barcodeItem, final int i2) {
        itemViewHolder.lnPromation.setVisibility(View.GONE);
        itemViewHolder.edtExplanation.setVisibility(View.GONE);
        itemViewHolder.lnSurplusAmount.setVisibility(View.GONE);
        itemViewHolder.lnDiscountHolder.setVisibility(View.GONE);
        itemViewHolder.txt_productName.setText(barcodeItem.getProduct().getName());
        itemViewHolder.txt_productCode.setText(barcodeItem.getProduct().getCode());
        itemViewHolder.amountTextListener.updateData(barcodeItem);
        if (barcodeItem.getProduct().getAmount() == 0.0d) {
            if (barcodeItem.getProduct().getVariant()) {
                barcodeItem.getProduct().setAmount(0.0d);
            } else if (barcodeItem.getProduct().getTrackType() == TrackType.NON_TRACK.getType()) {
                barcodeItem.getProduct().setAmount(1.0d);
            }
        }
        itemViewHolder.edt_amount.setText(String.valueOf(barcodeItem.getProduct().getAmount()));
        boolean z = true;
        if (barcodeItem.getProduct().getVariant()) {
            itemViewHolder.edt_amount.setClickable(true);
            itemViewHolder.edt_amount.setFocusable(false);
            itemViewHolder.edt_amount.setCursorVisible(false);
            itemViewHolder.edt_amount.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BarcodeScannerViewRecyclerViewAdapter.this.lambdabindData0(barcodeItem, i2, view);
                }
            });
        } else if (barcodeItem.getProduct().getTrackType() != TrackType.NON_TRACK.getType() && !SalesUtils.isSalesTypeOrder(this.mSalesType)) {
            itemViewHolder.edt_amount.setClickable(true);
            itemViewHolder.edt_amount.setFocusable(false);
            itemViewHolder.edt_amount.setCursorVisible(false);
            itemViewHolder.edt_amount.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BarcodeScannerViewRecyclerViewAdapter.lambdabindData1(BarcodeItem, view);
                }
            });
        } else {
            itemViewHolder.edt_amount.setOnClickListener(null);
            itemViewHolder.edt_amount.setEnabled(true);
            itemViewHolder.edt_amount.setClickable(true);
            itemViewHolder.edt_amount.setFocusable(true);
            itemViewHolder.edt_amount.setFocusableInTouchMode(true);
            itemViewHolder.edt_amount.setCursorVisible(true);
        }
        itemViewHolder.secondAmountTextListener.updateData(barcodeItem);
        if (barcodeItem.getProduct().getSecondAmount() != 0.0d) {
            itemViewHolder.edt_secondAmount.setText(String.valueOf(barcodeItem.getProduct().getSecondAmount()));
        } else {
            itemViewHolder.edt_secondAmount.setText("");
        }
        itemViewHolder.edt_secondAmount.setEnabled(barcodeItem.getProduct().getTrackType() != TrackType.SERIAL.getType());
        itemViewHolder.surplusAmountTextListener.updateData(barcodeItem);
        if (barcodeItem.getProduct().getSurplusAmount() != 0.0d) {
            itemViewHolder.edtSurplusAmount.setText(String.valueOf(barcodeItem.getProduct().getSurplusAmount()));
        } else {
            itemViewHolder.edtSurplusAmount.setText("");
        }
        itemViewHolder.edtSurplusAmount.setEnabled(barcodeItem.getProduct().getTrackType() == TrackType.NON_TRACK.getType());
        itemViewHolder.mUnitDataAdapter.clear();
        itemViewHolder.mSecondUnitDataAdapter.clear();
        initUnitList(barcodeItem);
        itemViewHolder.mUnitDataAdapter.addAll(barcodeItem.getProduct().getItemUnitCodeList());
        itemViewHolder.spn_productUnitSpinner.setAdapter(itemViewHolder.mUnitDataAdapter);
        itemViewHolder.spn_productUnitSpinner.setSelection(barcodeItem.getProduct().getSelectUnitIndex() <= 0 ? 0 : barcodeItem.getProduct().getSelectUnitIndex());
        itemViewHolder.spn_productUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapter.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i3, long j2) {
                if (BarcodeScannerViewRecyclerViewAdapter.this.baseErp.getErpType() == ErpType.TIGER && barcodeItem.getProduct().getUnitRef() == barcodeItem.getProduct().getMItemUnits().get(i3).logicalRef) {
                    return;
                }
                if (BarcodeScannerViewRecyclerViewAdapter.this.baseErp.getErpType() == ErpType.NETSIS && barcodeItem.getProduct().getUnitCode().equals(barcodeItem.getProduct().getMItemUnits().get(i3).code)) {
                    return;
                }
                barcodeItem.getProduct().selectUnitIndex(i3);
                if (barcodeItem.getProduct().getSalesSerialLots() != null && barcodeItem.getProduct().getSalesSerialLots().size() != 0) {
                    itemViewHolder.edt_amount.setText(String.valueOf(barcodeItem.getProduct().getAmount()));
                }
                if (itemViewHolder.lnPriceContainer.getVisibility() == View.GONE) {
                    return;
                }
                BarcodeScannerViewRecyclerViewAdapter.this.resetPrice(itemViewHolder.txtSelectablePrice, barcodeItem.getProduct(), barcodeItem.getSelectedPriceRefProp());
                if (itemViewHolder.lnEdtPrice.getVisibility() != View.VISIBLE) {
                    BarcodeScannerViewRecyclerViewAdapter.this.getPrice(itemViewHolder, barcodeItem);
                } else if (barcodeItem.getPriceProp().getDefinitionDouble() != 0.0d) {
                    itemViewHolder.edtPrice.setText(barcodeItem.getPriceProp().toString());
                } else {
                    itemViewHolder.edtPrice.setText("");
                    BarcodeScannerViewRecyclerViewAdapter.this.getPrice(itemViewHolder, barcodeItem);
                }
            }
        });
        if (barcodeItem.getProduct().getMItemSecondUnits() == null) {
            barcodeItem.getProduct().initSecondUnitList(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getProductUnits(barcodeItem.getProduct().getLogicalRef(), barcodeItem.getProduct().getService()));
            if (barcodeItem.getProduct().getSecondSelectUnitIndex() > -1) {
                barcodeItem.getProduct().selectSecondUnitLastIndex();
            } else if (barcodeItem.getProduct().getSecondDefUnitRef() > 0) {
                barcodeItem.getProduct().selectSecondUnitLogicalRef(barcodeItem.getProduct().getSecondDefUnitRef());
            } else if (!TextUtils.isEmpty(barcodeItem.getKey().getSecondUnitCode())) {
                if (!barcodeItem.getProduct().selectSecondUnitByCode(barcodeItem.getKey().getSecondUnitCode())) {
                    barcodeItem.getProduct().selectSecondUnitIndexFirst();
                }
            } else {
                barcodeItem.getProduct().selectSecondUnitIndexFirst();
            }
        }
        itemViewHolder.mSecondUnitDataAdapter.addAll(barcodeItem.getProduct().getItemSecondUnitCodeList());
        itemViewHolder.spn_productSecondUnitSpinner.setAdapter(itemViewHolder.mSecondUnitDataAdapter);
        itemViewHolder.spn_productSecondUnitSpinner.setSelection(barcodeItem.getProduct().getSecondSelectUnitIndex() <= 0 ? 0 : barcodeItem.getProduct().getSecondSelectUnitIndex());
        itemViewHolder.spn_productSecondUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapter.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i3, long j2) {
                barcodeItem.getProduct().selectSecondUnitIndex(i3);
            }
        });
        itemViewHolder.explanationTextListener.updateData(barcodeItem);
        itemViewHolder.edtExplanation.setText(barcodeItem.getProduct().getExplanation());
        itemViewHolder.img_add_to_list.setVisibility(((BarcodeScannerView) this.mContext).showConfButton() ? View.VISIBLE : View.GONE);
        itemViewHolder.img_remove_from_list.setVisibility(!((BarcodeScannerView) this.mContext).showConfButton() ? View.VISIBLE : View.GONE);
        itemViewHolder.customCheckedListener.updateData(barcodeItem);
        itemViewHolder.chbPromotion.setChecked(barcodeItem.getProduct().getPromotion());
        itemViewHolder.spn_lineType.setAdapter(itemViewHolder.mLineDataAdapter);
        itemViewHolder.spn_lineType.setSelection(barcodeItem.getProduct().getGlobalLineType());
        itemViewHolder.spn_lineType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapter.3
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i3, long j2) {
                barcodeItem.getProduct().setGlobalLineType(i3);
            }
        });
        if (barcodeItem.getProduct().getProductOperationDiscount() == null) {
            barcodeItem.getProduct().setProductOperationDiscount(this.mProductOperationDiscount);
        }
        if (barcodeItem.getProduct().getProductOperationDiscount().isDoDiscount()) {
            for (int i3 = 0; i3 < 3; i3++) {
                itemViewHolder.discount[i3].setVisibility((barcodeItem.getProduct().getProductOperationDiscount().isDoRatio(i3) || barcodeItem.getProduct().getProductOperationDiscount().isDoTotal(i3)) ? View.VISIBLE : View.GONE);
            }
        } else {
            itemViewHolder.lnDiscountHolder.setVisibility(View.GONE);
        }
        for (int i4 = 0; i4 < itemViewHolder.discountViewListeners.length; i4++) {
            itemViewHolder.discountViewListeners[i4].updateData(barcodeItem);
            itemViewHolder.discountViewListeners[i4].updateHolder(itemViewHolder);
            toggleDiscountImage(itemViewHolder, i4, barcodeItem.getProduct().getDiscountRatio()[i4]);
            itemViewHolder.edtProductDiscount[i4].setText(String.valueOf(barcodeItem.getProduct().getDiscount()[i4]));
            if (!barcodeItem.getProduct().getProductOperationDiscount().isDoRatio(i4) || !barcodeItem.getProduct().getProductOperationDiscount().isDoTotal(i4)) {
                if (barcodeItem.getProduct().getProductOperationDiscount().isDoRatio(i4)) {
                    barcodeItem.getProduct().getDiscountRatio()[i4] = true;
                    toggleDiscountImage(itemViewHolder, i4, barcodeItem.getProduct().getDiscountRatio()[i4]);
                } else if (barcodeItem.getProduct().getProductOperationDiscount().isDoTotal(i4)) {
                    barcodeItem.getProduct().getDiscountRatio()[i4] = false;
                    toggleDiscountImage(itemViewHolder, i4, barcodeItem.getProduct().getDiscountRatio()[i4]);
                }
            } else {
                itemViewHolder.discountViewListeners[i4].updateData(barcodeItem);
                itemViewHolder.discountViewListeners[i4].setCanToggle(true);
            }
        }
        if (barcodeItem.getProduct().getProductOperationDiscount().getRatioCount() == 0 && barcodeItem.getProduct().getProductOperationDiscount().getTotalCount() == 0) {
            itemViewHolder.lnDiscountHolder.setVisibility(View.GONE);
        }
        itemViewHolder.img_add_to_list.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapterExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BarcodeScannerViewRecyclerViewAdapter.this.lambdabindData2(barcodeItem, view);
            }
        });
        itemViewHolder.button_toggle.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BarcodeScannerViewRecyclerViewAdapter barcodeScannerViewRecyclerViewAdapter = BarcodeScannerViewRecyclerViewAdapter.this;
                ItemViewHolder itemViewHolder2 = itemViewHolder;
                barcodeScannerViewRecyclerViewAdapter.toggleView(itemViewHolder2, itemViewHolder2.edtExplanation.getVisibility() == View.GONE, barcodeItem.getProduct().getLogicalRef(), barcodeItem.getProduct().getProductOperationDiscount().isDoDiscount(), barcodeItem.getProduct().getVariant());
            }
        });
        itemViewHolder.img_remove_from_list.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapterExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BarcodeScannerViewRecyclerViewAdapter.this.lambdabindData3(barcodeItem, view);
            }
        });
        itemViewHolder.toggle.setCompoundDrawablesWithIntrinsicBounds(0, 0, itemViewHolder.edtExplanation.getVisibility() == View.VISIBLE ? R.drawable.ic_chevron_up_white_18dp : R.drawable.ic_chevron_down_white_18dp, 0);
        itemViewHolder.txtProductActualStock.setText(StringUtils.formatStock(barcodeItem.getProduct().getActualStock()));
        itemViewHolder.txtProductRealStock.setText(StringUtils.formatStock(barcodeItem.getProduct().getRealStock()));
        itemViewHolder.lnActualStock.setVisibility((barcodeItem.getProduct().getService() && this.baseErp.isHideActualStockAmount()) ? View.GONE : View.VISIBLE);
        itemViewHolder.lnRealStock.setVisibility((barcodeItem.getProduct().getService() && this.baseErp.isHideRealStockAmount()) ? View.GONE : View.VISIBLE);
        itemViewHolder.edt_amount.setEnabled(!this.baseErp.getUseStoredProcedureForBarcode());
        itemViewHolder.edt_secondAmount.setEnabled(!this.baseErp.getUseStoredProcedureForBarcode());
        itemViewHolder.spn_productUnitSpinner.setEnabled(!this.baseErp.getUseStoredProcedureForBarcode());
        itemViewHolder.spn_productSecondUnitSpinner.setEnabled(!this.baseErp.getUseStoredProcedureForBarcode());
        if (SalesUtils.isSalesTypeDemandOrWhTransfer(this.mSalesType) || (!this.baseErp.getEnterPrice() && !this.baseErp.getShowDefinedPricesForBarcode())) {
            itemViewHolder.lnPriceContainer.setVisibility(View.GONE);
            itemViewHolder.priceTextListener = null;
            return;
        }
        BaseErp baseErp = this.baseErp;
        boolean enterPrice = baseErp.getEnterPrice();
        boolean changePrice = this.baseErp.getChangePrice();
        if (barcodeItem.getProduct().getPriceWithDigits() != null && !barcodeItem.getProduct().getPriceWithDigits().isEmpty()) {
            z = false;
        }
        if (!baseErp.isPriceCanBeEnter(enterPrice, changePrice, z)) {
            itemViewHolder.lnEdtPrice.setVisibility(View.GONE);
            itemViewHolder.priceTextListener = null;
        } else {
            itemViewHolder.lnEdtPrice.setVisibility(View.VISIBLE);
        }
        if (!this.baseErp.getShowDefinedPricesForBarcode()) {
            itemViewHolder.lnDefinedPrice.setVisibility(View.GONE);
        }
        if (this.baseErp.getErpType() == ErpType.TIGER && barcodeItem.getProduct().getVariant()) {
            itemViewHolder.lnPriceContainer.setVisibility(android.view.View.GONE);
            itemViewHolder.priceTextListener = null;
            return;
        }
        if (itemViewHolder.priceTextListener != null) {
            itemViewHolder.priceTextListener.updateData(barcodeItem);
            itemViewHolder.priceTextListener.set_relatedView(itemViewHolder.txtSelectablePrice);
        }
        if (itemViewHolder.lnEdtPrice.getVisibility() != View.VISIBLE) {
            getPrice(itemViewHolder, barcodeItem);
        } else if (barcodeItem.getPriceProp().getDefinitionDouble() != 0.0d) {
            itemViewHolder.edtPrice.setText(barcodeItem.getPriceProp().toString());
        } else {
            itemViewHolder.edtPrice.setText("");
            getPrice(itemViewHolder, barcodeItem);
        }
    }

    
    public void lambdabindData0(BarcodeItem barcodeItem, int i2, View view) {
        showVariantSelectionDialog(barcodeItem.getProduct(), i2);
    }

    
    public static void lambdabindData1(BarcodeItem barcodeItem, View view) {
        if (ContextUtils.getmContext() instanceof ISeriLotCaller) {
            ((ISeriLotCaller) ContextUtils.getmContext()).openSeriLotActivity(barcodeItem.getProduct());
        }
    }

    
    public void lambdabindData2(BarcodeItem barcodeItem, View view) {
        confProduct(barcodeItem.getKey(), barcodeItem.getProduct());
    }

    
    public void lambdabindData3(BarcodeItem barcodeItem, View view) {
        removeFromList(barcodeItem);
    }

    public void initUnitList(BarcodeItem barcodeItem) {
        if (barcodeItem.getProduct().getMItemUnits() == null) {
            barcodeItem.getProduct().initUnitList(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getProductUnits(barcodeItem.getProduct().getLogicalRef(), barcodeItem.getProduct().getService()));
            if (barcodeItem.getProduct().getSelectUnitIndex() > -1) {
                barcodeItem.getProduct().selectUnitLastIndex();
                return;
            }
            if (barcodeItem.getProduct().getDefUnitRef() > 0) {
                barcodeItem.getProduct().selectUnitLogicalRef(barcodeItem.getProduct().getDefUnitRef());
                return;
            }
            if (this.baseErp.getErpType() == ErpType.TIGER && barcodeItem.getProduct().isBarcode() && barcodeItem.getProduct().getBarcodeUnitRef() > 0) {
                barcodeItem.getProduct().selectUnitLogicalRef(barcodeItem.getProduct().getBarcodeUnitRef());
            } else {
                if (!TextUtils.isEmpty(barcodeItem.getKey().getUnitCode())) {
                    if (barcodeItem.getProduct().selectUnitByCode(barcodeItem.getKey().getUnitCode())) {
                        return;
                    }
                    barcodeItem.getProduct().selectUnitIndexFirst();
                    return;
                }
                barcodeItem.getProduct().selectUnitIndexFirst();
            }
        }
    }

    public void showVariantSelectionDialog(Product product, int i2) {
        VariantListDialogFragment newInstance = VariantListDialogFragment.newInstance(new VariantSelectionParams(product.getLogicalRef(), product.getCode(), product.getName(), product.getDivUnit(), this.mWhRef, i2, this.mVariantPriceParams, product.getUnitRef(), TextUtils.isEmpty(product.getUnitCode2()) ? "" : product.getUnitCode2(), product.getMSelectedVariants(), this.mDivisionNr));
        newInstance.setVariantSelectionListener(new VariantListDialogFragment.IVariantSelectionListener() { // from class: com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapter.5
            @Override // com.proje.mobilesales.features.fragment.VariantListDialogFragment.IVariantSelectionListener
            public void onCancelled() {
            }

            @Override // com.proje.mobilesales.features.fragment.VariantListDialogFragment.IVariantSelectionListener
            public void operationCompleted(@NonNull ArrayList<SelectedVariant> arrayList, int i3) {
                if (arrayList.size() > 0) {
                    Product product2 = BarcodeScannerViewRecyclerViewAdapter.this.getProperty(i3).getProduct();
                    product2.setMSelectedVariants(arrayList);
                    product2.setAmount(arrayList.stream().mapToDouble(new ToDoubleFunction() { // from class: com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapter5ExternalSyntheticLambda0
                        @Override // java.util.function.ToDoubleFunction
                        public double applyAsDouble(Object obj) {
                            return ((SelectedVariant) obj).getAmount();
                        }
                    }).sum());
                    BarcodeScannerViewRecyclerViewAdapter.this.notifyItemChanged(i3);
                }
            }
        });
        newInstance.setCancelable(false);
        newInstance.show(((FragmentActivity) ContextUtils.getmActivity()).getSupportFragmentManager(), "");
    }

    
    public void toggleView(ItemViewHolder itemViewHolder, boolean z, int i2, boolean z2, boolean z3) {
        int i3 = 8;
        itemViewHolder.edtExplanation.setVisibility(z ? View.VISIBLE : View.GONE);
        itemViewHolder.lnPromation.setVisibility((z && ((BarcodeScannerView) this.mContext).showPromotion(i2)) ? View.VISIBLE : View.GONE);
        itemViewHolder.lnDiscountHolder.setVisibility((z && z2) ? View.VISIBLE : View.GONE);
        LinearLayout linearLayout = itemViewHolder.lnSurplusAmount;
        if (z && this.isSurplusAmountEnabled && !z3) {
            i3 = 0;
        }
        linearLayout.setVisibility(View.VISIBLE);
        itemViewHolder.toggle.setCompoundDrawablesWithIntrinsicBounds(0, 0, z ? R.drawable.ic_chevron_up_white_18dp : R.drawable.ic_chevron_down_white_18dp, 0);
    }

    
    public void toggleDiscountImage(ItemViewHolder itemViewHolder, int i2, boolean z) {
        if (!z) {
            itemViewHolder.imgBtnProductDiscountChange[i2].setImageDrawable(itemViewHolder.coinGrey600);
        } else {
            itemViewHolder.imgBtnProductDiscountChange[i2].setImageDrawable(itemViewHolder.percentGrey600);
        }
    }

    public boolean isAttached() {
        return this.mContext != null;
    }

    public void initDisplayOptions(Context context) {
        if (isAttached()) {
            notifyDataSetChanged();
        }
    }

    private void confProduct(BarcodeResult barcodeResult, Product product) {
        ((BarcodeScannerView) this.mContext).confProduct(barcodeResult, product);
    }

    private void removeFromList(BarcodeItem barcodeItem) {
        ((BarcodeScannerView) this.mContext).getmBarcodeList().remove(barcodeItem.getKey());
        ((BarcodeScannerView) this.mContext).removeSelectedProducts(barcodeItem.getProduct().getLogicalRef());
        this.mDatas.remove(barcodeItem);
        notifyDataSetChanged();
    }

    public void updateData() {
        notifyDataSetChanged();
    }

    public void setData(ArrayList<BarcodeItem> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return;
        }
        setmDatas(arrayList);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mDatas.size();
    }

    public void setmDatas(ArrayList<BarcodeItem> arrayList) {
        this.mDatas = arrayList;
    }

    public int getDivisionNr() {
        return this.mDivisionNr;
    }

    public void setDivisionNr(int i2) {
        this.mDivisionNr = i2;
    }

    public VariantPriceParams getVariantPriceParams() {
        return this.mVariantPriceParams;
    }

    public void setVariantPriceParams(VariantPriceParams variantPriceParams) {
        this.mVariantPriceParams = variantPriceParams;
    }

    public int getWhRef() {
        return this.mWhRef;
    }

    public void setWhRef(int i2) {
        this.mWhRef = i2;
    }

    public String getSpecode() {
        return this.mSpecode;
    }

    public void setSpecode(String str) {
        this.mSpecode = str;
    }

    public class AmountTextListener extends CustomTextListener {
        public AmountTextListener() {
            super();
        }

        @Override // com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapter.CustomTextListener, android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            Double valueOf;
            super.afterTextChanged(editable);
            if (editable.toString().equals("")) {
                valueOf = Double.valueOf(0.0d);
            } else {
                valueOf = Double.valueOf(Double.parseDouble(editable.toString()));
            }
            get_data().getProduct().setAmount(valueOf.doubleValue());
        }
    }

    public class SecondAmountTextListener extends CustomTextListener {
        public SecondAmountTextListener() {
            super();
        }

        @Override // com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapter.CustomTextListener, android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            Double valueOf;
            super.afterTextChanged(editable);
            if (editable.toString().equals("")) {
                valueOf = Double.valueOf(0.0d);
            } else {
                valueOf = Double.valueOf(Double.parseDouble(editable.toString()));
            }
            get_data().getProduct().setSecondAmount(valueOf.doubleValue());
        }
    }

    public class ExplanationTextListener extends CustomTextListener {
        public ExplanationTextListener() {
            super();
        }

        @Override // com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapter.CustomTextListener, android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            super.afterTextChanged(editable);
            get_data().getProduct().setExplanation(editable.toString());
        }
    }

    public class SurplusAmountTextListener extends CustomTextListener {
        public SurplusAmountTextListener() {
            super();
        }

        @Override // com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapter.CustomTextListener, android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            Double valueOf;
            super.afterTextChanged(editable);
            if (editable.toString().equals("")) {
                valueOf = Double.valueOf(0.0d);
            } else {
                valueOf = Double.valueOf(Double.parseDouble(editable.toString()));
            }
            get_data().getProduct().setSurplusAmount(valueOf.doubleValue());
        }
    }

    public class CustomTextListener implements TextWatcher {
        private BarcodeItem _data;
        private View _relatedView;

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        public CustomTextListener() {
        }

        public void updateData(BarcodeItem barcodeItem) {
            set_data(barcodeItem);
        }

        public BarcodeItem get_data() {
            return this._data;
        }

        public void set_data(BarcodeItem barcodeItem) {
            this._data = barcodeItem;
        }

        public View get_relatedView() {
            return this._relatedView;
        }

        public void set_relatedView(View view) {
            this._relatedView = view;
        }
    }

    public class CustomCheckedListener implements CompoundButton.OnCheckedChangeListener {
        private BarcodeItem _data;

        public CustomCheckedListener() {
        }

        public void updateData(BarcodeItem barcodeItem) {
            set_data(barcodeItem);
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            this._data.getProduct().setPromotion(z);
        }

        public BarcodeItem get_data() {
            return this._data;
        }

        public void set_data(BarcodeItem barcodeItem) {
            this._data = barcodeItem;
        }
    }

    private class DiscountViewListener {
        private BarcodeItem _data;
        private ItemViewHolder _holder;
        private boolean canToggle;
        private final int mIndex;
        private View.OnClickListener productDiscountClickListener = new View.OnClickListener() { // from class: com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapter.DiscountViewListener.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DiscountViewListener.this.canToggle) {
                    DiscountViewListener.this._data.getProduct().getDiscountRatio()[DiscountViewListener.this.mIndex] = !DiscountViewListener.this._data.getProduct().getDiscountRatio()[DiscountViewListener.this.mIndex];
                    DiscountViewListener discountViewListener = DiscountViewListener.this;
                    BarcodeScannerViewRecyclerViewAdapter.this.toggleDiscountImage(discountViewListener._holder, DiscountViewListener.this.mIndex, DiscountViewListener.this._data.getProduct().getDiscountRatio()[DiscountViewListener.this.mIndex]);
                }
            }
        };
        private TextWatcher productDiscountTextWatcher = new TextWatcher() { // from class: com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapter.DiscountViewListener.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                Double valueOf;
                if (editable.toString().equals("")) {
                    valueOf = Double.valueOf(0.0d);
                } else {
                    valueOf = Double.valueOf(Double.parseDouble(editable.toString()));
                }
                DiscountViewListener.this._data.getProduct().getDiscount()[DiscountViewListener.this.mIndex] = valueOf.doubleValue();
            }
        };

        public void setCanToggle(boolean z) {
            this.canToggle = z;
        }

        public DiscountViewListener(int i2) {
            this.mIndex = i2;
        }

        public void updateData(BarcodeItem barcodeItem) {
            this._data = barcodeItem;
        }

        public void updateHolder(ItemViewHolder itemViewHolder) {
            this._holder = itemViewHolder;
        }

        public View.OnClickListener getProductDiscountClickListener() {
            return this.productDiscountClickListener;
        }

        public void setProductDiscountClickListener(View.OnClickListener onClickListener) {
            this.productDiscountClickListener = onClickListener;
        }

        public TextWatcher getProductDiscountTextWatcher() {
            return this.productDiscountTextWatcher;
        }

        public void setProductDiscountTextWatcher(TextWatcher textWatcher) {
            this.productDiscountTextWatcher = textWatcher;
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private final AmountTextListener amountTextListener;
        final FrameLayout button_toggle;
        final CheckBox chbPromotion;
        final Drawable coinGrey600;
        private final CustomCheckedListener customCheckedListener;
        final LinearLayout detailSecondContainer;
        final View[] discount;
        private final DiscountViewListener[] discountViewListeners;
        final EditText edtExplanation;
        private final EditText edtPrice;
        final EditText[] edtProductDiscount;
        EditText edtSurplusAmount;
        final EditText edt_amount;
        final EditText edt_secondAmount;
        private final ExplanationTextListener explanationTextListener;
        final ImageButton[] imgBtnProductDiscountChange;
        final ImageView img_add_to_list;
        final ImageView img_remove_from_list;
        LinearLayout lnActualStock;
        LinearLayout lnAmount;
        LinearLayout lnDefinedPrice;
        final LinearLayout lnDiscountHolder;
        LinearLayout lnEdtPrice;
        LinearLayout lnPriceContainer;
        final LinearLayout lnPromation;
        LinearLayout lnRealStock;
        LinearLayout lnSurplusAmount;
        final ArrayAdapter<String> mLineDataAdapter;
        final ArrayAdapter<String> mSecondUnitDataAdapter;
        final ArrayAdapter<String> mUnitDataAdapter;
        final Drawable percentGrey600;
        private PriceTextListener priceTextListener;
        private final SecondAmountTextListener secondAmountTextListener;
        final Spinner spn_lineType;
        final Spinner spn_productSecondUnitSpinner;
        final Spinner spn_productUnitSpinner;
        private final SurplusAmountTextListener surplusAmountTextListener;
        final TintableTextView toggle;
        TextView txtProductActualStock;
        TextView txtProductRealStock;
        private final TextView txtSelectablePrice;
        final TextView txt_productCode;
        final TextView txt_productName;

        public ItemViewHolder(View view, AmountTextListener amountTextListener, ExplanationTextListener explanationTextListener, CustomCheckedListener customCheckedListener, DiscountViewListener[] discountViewListenerArr, SecondAmountTextListener secondAmountTextListener, SurplusAmountTextListener surplusAmountTextListener) {
            super(view);
            View[] viewArr = {r11, r11, r11};
            this.discount = viewArr;
            this.edtProductDiscount = new EditText[]{(EditText) r11.findViewById(R.id.edt_product_discount), (EditText) r11.findViewById(R.id.edt_product_discount), (EditText) r11.findViewById(R.id.edt_product_discount)};
            this.imgBtnProductDiscountChange = new ImageButton[]{viewArr[0].findViewById(R.id.imgBtn_product_discount_type_change), viewArr[1].findViewById(R.id.imgBtn_product_discount_type_change), viewArr[2].findViewById(R.id.imgBtn_product_discount_type_change)};
            this.priceTextListener = BarcodeScannerViewRecyclerViewAdapter.this.new PriceTextListener();
            this.amountTextListener = amountTextListener;
            this.secondAmountTextListener = secondAmountTextListener;
            this.explanationTextListener = explanationTextListener;
            this.customCheckedListener = customCheckedListener;
            this.surplusAmountTextListener = surplusAmountTextListener;
            ImageView imageView = view.findViewById(R.id.img_add_to_list);
            this.img_add_to_list = imageView;
            imageView.setVisibility(View.GONE);
            this.txt_productName = view.findViewById(R.id.txt_productName);
            this.txt_productCode = view.findViewById(R.id.txt_productCode);
            EditText editText = view.findViewById(R.id.edt_amount);
            this.edt_amount = editText;
            editText.addTextChangedListener(amountTextListener);
            EditText editText2 = view.findViewById(R.id.edt_secondAmount);
            this.edt_secondAmount = editText2;
            editText2.addTextChangedListener(secondAmountTextListener);
            this.spn_productUnitSpinner = view.findViewById(R.id.spn_productUnitSpinner);
            this.spn_productSecondUnitSpinner = view.findViewById(R.id.spn_secondProductUnitSpinner);
            this.img_remove_from_list = view.findViewById(R.id.img_remove_from_list);
            LinearLayout linearLayout = view.findViewById(R.id.lnPromotion);
            this.lnPromation = linearLayout;
            linearLayout.setVisibility(View.GONE);
            CheckBox checkBox = view.findViewById(R.id.chk_salesDetailPromotion);
            this.chbPromotion = checkBox;
            checkBox.setOnCheckedChangeListener(customCheckedListener);
            this.spn_lineType = view.findViewById(R.id.spn_lineType);
            LinearLayout linearLayout2 = view.findViewById(R.id.ln_detailSecondContainer);
            this.detailSecondContainer = linearLayout2;
            LinearLayout linearLayout3 = view.findViewById(R.id.lnDiscountHolder);
            this.lnDiscountHolder = linearLayout3;
            linearLayout3.setVisibility(View.GONE);
            EditText editText3 = view.findViewById(R.id.edtPrice);
            this.edtPrice = editText3;
            editText3.addTextChangedListener(this.priceTextListener);
            this.txtSelectablePrice = view.findViewById(R.id.txt_definedPrice);
            this.lnDefinedPrice = view.findViewById(R.id.ln_definedPrice);
            this.lnPriceContainer = view.findViewById(R.id.ln_priceContainer);
            this.lnEdtPrice = view.findViewById(R.id.ln_price);
            EditText editText4 = view.findViewById(R.id.edtExplanation);
            this.edtExplanation = editText4;
            editText4.addTextChangedListener(explanationTextListener);
            editText4.setVisibility(View.GONE);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(BarcodeScannerViewRecyclerViewAdapter.this.mContext, R.layout.layout_product_spinner_item);
            this.mUnitDataAdapter = arrayAdapter;
            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(BarcodeScannerViewRecyclerViewAdapter.this.mContext, R.layout.layout_product_spinner_item);
            this.mSecondUnitDataAdapter = arrayAdapter2;
            arrayAdapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            Context context = BarcodeScannerViewRecyclerViewAdapter.this.mContext;
            ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<>(context, R.layout.layout_product_spinner_item, context.getResources().getStringArray(R.array.array_global_line_type));
            this.mLineDataAdapter = arrayAdapter3;
            arrayAdapter3.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            this.button_toggle = view.findViewById(R.id.button_toggle);
            this.toggle = view.findViewById(R.id.toggle);
            this.percentGrey600 = ContextCompat.getDrawable(BarcodeScannerViewRecyclerViewAdapter.this.mContext, R.drawable.ic_sale_grey600_24dp);
            this.coinGrey600 = ContextCompat.getDrawable(BarcodeScannerViewRecyclerViewAdapter.this.mContext, R.drawable.ic_do_not_disturb_grey600_24dp);
            View findViewById = view.findViewById(R.id.inc_discount1);
            viewArr[0].setVisibility(View.GONE);
            View findViewById2 = view.findViewById(R.id.inc_discount2);
            viewArr[1].setVisibility(View.GONE);
            View findViewById3 = view.findViewById(R.id.inc_discount3);
            viewArr[2].setVisibility(View.GONE);
            if (BarcodeScannerViewRecyclerViewAdapter.this.baseErp.getErpType() != ErpType.NETSIS || !BarcodeScannerViewRecyclerViewAdapter.this.baseErp.getLogoSqlHelper().getLogoParamValue("OLCUBIRIMITABLODAN").equals("D")) {
                linearLayout2.setVisibility(View.GONE);
            }
            this.discountViewListeners = discountViewListenerArr;
            for (int i2 = 0; i2 < discountViewListenerArr.length; i2++) {
                DiscountViewListener discountViewListener = BarcodeScannerViewRecyclerViewAdapter.this.new DiscountViewListener(i2);
                discountViewListenerArr[i2] = discountViewListener;
                this.imgBtnProductDiscountChange[i2].setOnClickListener(discountViewListener.getProductDiscountClickListener());
                this.edtProductDiscount[i2].addTextChangedListener(discountViewListenerArr[i2].getProductDiscountTextWatcher());
                this.discount[i2].setVisibility(View.VISIBLE);
            }
            this.lnRealStock = view.findViewById(R.id.ln_product_real_stock);
            this.lnActualStock = view.findViewById(R.id.ln_product_actual_stock);
            this.txtProductActualStock = view.findViewById(R.id.txt_product_actual_stock);
            this.txtProductRealStock = view.findViewById(R.id.txt_real_stock);
            this.lnAmount = view.findViewById(R.id.ln_amount);
            this.lnSurplusAmount = view.findViewById(R.id.ln_surplusAmount);
            EditText editText5 = view.findViewById(R.id.edt_surplusAmount);
            this.edtSurplusAmount = editText5;
            editText5.addTextChangedListener(surplusAmountTextListener);
            this.lnSurplusAmount.setVisibility(View.GONE);
        }
    }

    public class PriceTextListener extends CustomTextListener {
        public PriceTextListener() {
            super();
        }

        @Override // com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapter.CustomTextListener, android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            super.afterTextChanged(editable);
            if (get_data() == null) {
                return;
            }
            FicheStringProp.setDefinition(editable.toString());
            if (StringUtils.convertStringToDouble(editable.toString()) > 0.0d) {
                BarcodeScannerViewRecyclerViewAdapter.this.resetPrice((TextView) get_relatedView(), get_data().getProduct(), get_data().getSelectedPriceRefProp());
                get_data().getProduct().setPrice(StringUtils.convertStringToDouble(editable.toString()));
                get_data().getProduct().setPriceSetFromBarcode(true);
            }
        }
    }

    private void createSingleAlertCursorSalesPrice(View view, final TextView textView, final Product product, final EditText editText, final FicheDiscountRefProp ficheDiscountRefProp, boolean z, boolean z2, @StringRes final int i2, @StringRes final int i3, @NonNull final String str, @StringRes final int i4, @StringRes int i5, final boolean z3, final String... strArr) {
        if (!z) {
            view.setVisibility(View.GONE);
            return;
        }
        if (!z2) {
            textView.setEnabled(false);
            return;
        }
        if (ficheDiscountRefProp.getDefinition().isEmpty() && (this.baseErp.getErpType() != ErpType.TIGER || !product.getVariant() || product.getVariantRef() != -1)) {
            if (ficheDiscountRefProp.getLogicalRef() <= 0) {
                setFirstPrice(str, strArr, textView, editText, product, ficheDiscountRefProp);
            } else {
                setSelectedPricePosition(str, strArr, textView, editText, product, ficheDiscountRefProp);
            }
        }
        if (z && textView != null) {
            textView.setText(ficheDiscountRefProp.toString());
        }
        if (StringUtils.paramValueTrueCheck(this.baseErp.getLogoSqlHelper().getParamValue(ParameterTypes.ptCanChangeSelectedPrice))) {
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view2) {
                    BarcodeScannerViewRecyclerViewAdapter.this.lambdacreateSingleAlertCursorSalesPrice6(str, strArr, product, i3, i2, z3, ficheDiscountRefProp, i4, textView, editText, view2);
                }
            });
        }
    }
    public void lambdacreateSingleAlertCursorSalesPrice6(String str, String[] strArr, final Product product, int i2, int i3, boolean z, final FicheDiscountRefProp ficheDiscountRefProp, int i4, final TextView textView, final EditText editText, View view) {
        this.mAlertDialogBuilder.dismiss();
        final Cursor query = this.baseErp.getLogoSqlBriteDatabase().query(str, strArr);
        if (query != null && query.getCount() != 0 && (this.baseErp.getErpType() != ErpType.TIGER || !product.getVariant() || product.getVariantRef() != -1)) {
            String string = this.mContext.getString(i3);
            if (z) {
                string = StringUtils.catStringSpace(this.mContext.getString(i3), this.mContext.getString(R.string.str_select_text));
            }
            new AlertDialog.Builder(this.mContext, R.style.PriceAlertDialogStyle).setTitle(string).setSingleChoiceItems(query, ficheDiscountRefProp.getWhich(), this.mContext.getString(i4), new DialogInterface.OnClickListener() { 
                public void onClick(DialogInterface dialogInterface, int i5) {
                    BarcodeScannerViewRecyclerViewAdapter.this.lambdacreateSingleAlertCursorSalesPrice4(query, textView, editText, product, ficheDiscountRefProp, dialogInterface, i5);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { 
                public void onClick(DialogInterface dialogInterface, int i5) {
                    BarcodeScannerViewRecyclerViewAdapter.lambdacreateSingleAlertCursorSalesPrice5(FicheDiscountRefProp.this, product, textView, query, dialogInterface, i5);
                }
            }).create().show();
            return;
        }
        if (i2 != -1) {
            Context context = this.mContext;
            Toast.makeText(context, context.getString(i2), Toast.LENGTH_SHORT).show();
        } else {
            Context context2 = this.mContext;
            Toast.makeText(context2, context2.getString(R.string.empty_text), Toast.LENGTH_SHORT).show();
        }
    } 
    public void lambdacreateSingleAlertCursorSalesPrice4(Cursor cursor, TextView textView, EditText editText, Product product, FicheDiscountRefProp ficheDiscountRefProp, DialogInterface dialogInterface, int i2) {
        if (SalesUtils.isSalesTypeRetailInvoiceOrRetailReturnInvoice(this.mSalesType)) {
            setRetailPrice(cursor, i2, textView, editText, product, ficheDiscountRefProp);
        } else {
            setPrice(cursor, i2, textView, editText, product, ficheDiscountRefProp);
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        dialogInterface.dismiss();
    }

    public static void lambdacreateSingleAlertCursorSalesPrice5(FicheDiscountRefProp ficheDiscountRefProp, Product product, TextView textView, Cursor cursor, DialogInterface dialogInterface, int i2) {
        ficheDiscountRefProp.reset();
        product.setPriceWithDigits("");
        product.setPrice(0.0d);
        product.setPriceRef(-1);
        textView.setText("");
        if (!cursor.isClosed()) {
            cursor.close();
        }
        dialogInterface.dismiss();
    }

    private void setFirstPrice(String str, String[] strArr, TextView textView, EditText editText, Product product, FicheDiscountRefProp ficheDiscountRefProp) {
        Cursor query = this.baseErp.getLogoSqlBriteDatabase().query(str, strArr);
        if (query == null || query.getCount() == 0) {
            if (query != null && !query.isClosed()) {
                query.close();
            }
            if (SalesUtils.isSalesTypeRetailInvoiceOrRetailReturnInvoice(this.mSalesType) || !this.baseErp.getUseVatIncForProductsDontHavePriceCard()) {
                return;
            }
            product.setIncVat(true);
            return;
        }
        if (SalesUtils.isSalesTypeRetailInvoiceOrRetailReturnInvoice(this.mSalesType)) {
            setRetailPrice(query, 0, textView, editText, product, ficheDiscountRefProp);
        } else {
            setPrice(query, 0, textView, editText, product, ficheDiscountRefProp);
        }
        if (query.isClosed()) {
            return;
        }
        query.close();
    }

    private void setSelectedPricePosition(String str, String[] strArr, TextView textView, EditText editText, Product product, FicheDiscountRefProp ficheDiscountRefProp) {
        int i2;
        boolean z;
        Cursor query = this.baseErp.getLogoSqlBriteDatabase().query(str, strArr);
        if (query == null || query.getCount() == 0) {
            if (query == null || query.isClosed()) {
                return;
            }
            query.close();
            return;
        }
        if (query.moveToFirst()) {
            i2 = 0;
            while (true) {
                if (ficheDiscountRefProp.getLogicalRef() == query.getInt(query.getColumnIndex(this.mContext.getString(R.string.column_id)))) {
                    z = true;
                    break;
                }
                i2++;
                if (!query.moveToNext()) {
                    z = false;
                    break;
                }
            }
        } else {
            i2 = 0;
            z = false;
        }
        setPrice(query, z ? i2 : 0, textView, editText, product, ficheDiscountRefProp);
        if (query.isClosed()) {
            return;
        }
        query.close();
    }
    private void setPrice(Cursor cursor, int i2, TextView textView, EditText editText, Product product, FicheDiscountRefProp ficheDiscountRefProp) {
        ficheDiscountRefProp.setWhich(i2);
        if (cursor.moveToPosition(i2)) {
            SqlManager.setSpecodePrices(product, this.baseErp.getCentOfUnitPriceDigit(), this.mSpecode);
            ficheDiscountRefProp.setLogicalRef(cursor.getInt(cursor.getColumnIndex(this.mContext.getString(R.string.column_id))));
            ficheDiscountRefProp.setCode(String.valueOf(cursor.getInt(cursor.getColumnIndex(this.mContext.getString(R.string.column_id)))));
            if (cursor.getInt(cursor.getColumnIndex("UNITCONVERT")) == 1) {
                product.setPrice(CalculateUtils.convertUnitPrice(product.getPrice(), product.getConvfact1(), product.getConvfact2(), cursor.getDouble(cursor.getColumnIndex("CONVFACT1")), cursor.getDouble(cursor.getColumnIndex("CONVFACT2"))));
            }
            product.setIncVat(StringUtils.convertIntToBoolean(cursor.getInt(cursor.getColumnIndex("INCVAT"))));
            FicheStringProp.setDefinition(product.getPriceWithDigits());
            product.setPriceSetFromBarcode(true);
            textView.setText(product.getPriceWithDigits());
            editText.setText("");
        }
    }
    public void resetPrice(TextView textView, Product product, FicheDiscountRefProp ficheDiscountRefProp) {
        ficheDiscountRefProp.reset();
        product.setPrice(0.0d);
        product.setPriceRef(-1);
        product.setPriceWithDigits("");
        textView.setText("");
    }

    private void setRetailPrice(Cursor cursor, int i2, TextView textView, EditText editText, Product product, FicheDiscountRefProp ficheDiscountRefProp) {
        if (cursor.moveToPosition(i2)) {
            resetPrice(textView, product, ficheDiscountRefProp);
            double d2 = cursor.getDouble(cursor.getColumnIndex("PRICE"));
            boolean convertIntToBoolean = StringUtils.convertIntToBoolean(cursor.getInt(cursor.getColumnIndex("INCVAT")));
            double calculatePriceAddVat = CalculateUtils.calculatePriceAddVat(d2, product.getVat(), convertIntToBoolean);
            FicheStringProp.setDefinition(String.valueOf(calculatePriceAddVat));
            product.setIncVat(convertIntToBoolean);
            product.setPrice(calculatePriceAddVat);
            product.setPriceSetFromBarcode(true);
            editText.setText(StringUtils.convertDoubleToString(Double.valueOf(calculatePriceAddVat)));
        }
    }

    public void getPrice(ItemViewHolder itemViewHolder, BarcodeItem barcodeItem) {
        if (this.baseErp.getShowDefinedPricesForBarcode()) {
            if (this.baseErp.getProductOnlinePrice()) {
                getPriceOnline(itemViewHolder, barcodeItem);
            } else {
                getPriceOffline(itemViewHolder, barcodeItem);
            }
        }
    }

    public void getPriceOffline(ItemViewHolder itemViewHolder, BarcodeItem barcodeItem) {
        createSingleAlertCursorSalesPrice(itemViewHolder.lnDefinedPrice, itemViewHolder.txtSelectablePrice, barcodeItem.getProduct(), itemViewHolder.edtPrice, barcodeItem.getSelectedPriceRefProp(), true, true, R.string.str_price, R.string.str_product_price_not_found, this.baseErp.getLogoSqlHelper().getSalesDetailProductPriceSql(this.mContext, this.mCustomerRef, !barcodeItem.getProduct().getService(), barcodeItem.getProduct().getVariantCode(), this.mDivisionNr), R.string.column_unit_price, R.string.column_curr_nr, true, StringUtils.convertIntToString(barcodeItem.getProduct().getLogicalRef()), StringUtils.convertIntToString(this.mCustomerRef), !barcodeItem.getProduct().getService() ? ExifInterface.GPS_MEASUREMENT_2D : "4", StringUtils.convertIntToString(barcodeItem.getProduct().getUnitRef()), StringUtils.convertIntToString(this.mPaymentRef), barcodeItem.getProduct().getVariantCode(), this.mTradingGrp);
    }

    private void getPriceOnline(final ItemViewHolder itemViewHolder, final BarcodeItem barcodeItem) {
        final Product product = barcodeItem.getProduct();
        List<Price> priceList = barcodeItem.getPriceList();
        barcodeItem.setPriceList(priceList);
        if (priceList != null && priceList.size() > 0) {
            createSingleAlertCursorSalesPrice(itemViewHolder.lnDefinedPrice, itemViewHolder.txtSelectablePrice, product, itemViewHolder.edtPrice, barcodeItem.getSelectedPriceRefProp(), true, true, R.string.str_price, R.string.column_curr_price, R.string.column_curr_type, true, priceList);
        } else {
            this.mProgressDialogBuilder.setMessage(this.mContext.getString(R.string.type_get_online_price)).show();
            this.baseErp.getOnlinePrice(product.getLogicalRef(), this.mCustomerRef, product.getUnitRef(), product.getIncVat(), this.mPaymentRef, -1, this.mTradingGrp, !product.getService() ? 2 : 4, new ResponseListener() { // from class: com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapter.6

                public void onResponse(@Nullable @UnknownNullability PrintSlipModel obj) {
                    BarcodeScannerViewRecyclerViewAdapter.this.mProgressDialogBuilder.dismiss();
                    Log.d("getPriceOnline", "onresponse");
                    List<Price> list = (List) obj;
                    barcodeItem.setPriceList(list);
                    if (list == null) {
                        BarcodeScannerViewRecyclerViewAdapter.this.getPriceOffline(itemViewHolder, barcodeItem);
                        return;
                    }
                    BarcodeScannerViewRecyclerViewAdapter barcodeScannerViewRecyclerViewAdapter = BarcodeScannerViewRecyclerViewAdapter.this;
                    ItemViewHolder itemViewHolder2 = itemViewHolder;
                    barcodeScannerViewRecyclerViewAdapter.createSingleAlertCursorSalesPrice(itemViewHolder2.lnDefinedPrice, itemViewHolder2.txtSelectablePrice, product, itemViewHolder.edtPrice, barcodeItem.getSelectedPriceRefProp(), true, true, R.string.str_price, R.string.column_curr_price, R.string.column_curr_type, true, list);
                }
 
                public void onError(String str) {
                    BarcodeScannerViewRecyclerViewAdapter.this.mProgressDialogBuilder.dismiss();
                    if (!TextUtils.isEmpty(str)) {
                        Toast.makeText(BarcodeScannerViewRecyclerViewAdapter.this.mContext, str, Toast.LENGTH_LONG).show();
                    }
                    BarcodeScannerViewRecyclerViewAdapter.this.getPriceOffline(itemViewHolder, barcodeItem);
                    Log.d("getPriceOnline", "onerror");
                }
            }, barcodeItem.getProduct().getVariantCode(), this.mDivisionNr);
        }
    } 
    public void createSingleAlertCursorSalesPrice(View view, final TextView textView, final Product product, final EditText editText, final FicheDiscountRefProp ficheDiscountRefProp, boolean z, boolean z2, @StringRes final int i2, @StringRes final int i3, @StringRes int i4, final boolean z3, final List<Price> list) {
        if (!z) {
            view.setVisibility(View.GONE);
            return;
        }
        if (!z2) {
            textView.setEnabled(false);
            return;
        }
        boolean z4 = true;
        if ((list == null || list.size() == 0) && !SalesUtils.isSalesTypeRetailInvoiceOrRetailReturnInvoice(this.mSalesType) && this.baseErp.getUseVatIncForProductsDontHavePriceCard()) {
            product.setIncVat(true);
        }
        if (list != null && list.size() > 0 && ((this.baseErp.getErpType() != ErpType.TIGER || !product.getVariant() || product.getVariantRef() != -1) && ficheDiscountRefProp.getDefinition().isEmpty())) {
            if (ficheDiscountRefProp.getLogicalRef() <= 0) {
                setFirstPrice(list.get(0), 0, ficheDiscountRefProp, textView, editText, product);
            } else {
                int i5 = 0;
                while (true) {
                    if (i5 >= list.size()) {
                        z4 = false;
                        break;
                    } else if (ficheDiscountRefProp.getLogicalRef() == list.get(i5).getPriceRef()) {
                        break;
                    } else {
                        i5++;
                    }
                }
                Price price = list.get(z4 ? i5 : 0);
                if (!z4) {
                    i5 = 0;
                }
                setFirstPrice(price, i5, ficheDiscountRefProp, textView, editText, product);
            }
        }
        if (z && textView != null) {
            textView.setText(ficheDiscountRefProp.toString());
        }
        if (StringUtils.paramValueTrueCheck(this.baseErp.getLogoSqlHelper().getParamValue(ParameterTypes.ptCanChangeSelectedPrice))) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapterExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    BarcodeScannerViewRecyclerViewAdapter.this.lambdacreateSingleAlertCursorSalesPrice9(list, product, i2, z3, ficheDiscountRefProp, i3, textView, editText, view2);
                }
            });
        }
    }
 
    public void lambdacreateSingleAlertCursorSalesPrice9(final List list, final Product product, int i2, boolean z, final FicheDiscountRefProp ficheDiscountRefProp, int i3, final TextView textView, final EditText editText, View view) {
        this.mAlertDialogBuilder.dismiss();
        if (list == null || list.size() == 0 || (this.baseErp.getErpType() == ErpType.TIGER && product.getVariant() && product.getVariantRef() == -1)) {
            Context context = this.mContext;
            Toast.makeText(context, context.getString(R.string.str_product_price_not_found), Toast.LENGTH_LONG).show();
        } else {
            String string = this.mContext.getString(i2);
            if (z) {
                string = StringUtils.catStringSpace(this.mContext.getString(i2), this.mContext.getString(R.string.str_select_text));
            }
            this.mAlertDialogBuilder.setTitle(string).setSingleChoiceItems((List<CharSequenceGet>) list, ficheDiscountRefProp.getWhich(), this.mContext.getString(i3), new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.adapter.BarcodeScannerViewRecyclerViewAdapterExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i4) {
                    BarcodeScannerViewRecyclerViewAdapter.this.lambdacreateSingleAlertCursorSalesPrice7(list, textView, editText, ficheDiscountRefProp, product, dialogInterface, i4);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i4) {
                    BarcodeScannerViewRecyclerViewAdapter.lambdacreateSingleAlertCursorSalesPrice8(FicheDiscountRefProp.this, product, textView, dialogInterface, i4);
                }
            }).create().show();
        }
    }
    private void lambdacreateSingleAlertCursorSalesPrice7(List list, TextView textView, EditText editText, FicheDiscountRefProp ficheDiscountRefProp, Product product, DialogInterface dialogInterface, int i2) {
        Price price = (Price) list.get(i2);
        if (price != null) {
            if (SalesUtils.isSalesTypeRetailInvoiceOrRetailReturnInvoice(this.mSalesType)) {
                setRetailPrice(price, textView, editText, ficheDiscountRefProp, product);
            } else {
                setFirstPrice(price, i2, ficheDiscountRefProp, textView, editText, product);
            }
        }
        dialogInterface.dismiss();
    }

    public static void lambdacreateSingleAlertCursorSalesPrice8(FicheDiscountRefProp ficheDiscountRefProp, Product product, TextView textView, DialogInterface dialogInterface, int i2) {
        ficheDiscountRefProp.reset();
        product.setPriceWithDigits("");
        product.setPrice(0.0d);
        product.setPriceRef(-1);
        textView.setText("");
        dialogInterface.dismiss();
    }

    private void setFirstPrice(Price price, int i2, FicheDiscountRefProp ficheDiscountRefProp, TextView textView, EditText editText, Product product) {
        String str;
        ficheDiscountRefProp.setWhich(i2);
        ficheDiscountRefProp.setLogicalRef(price.getPriceRef());
        product.setPrice(StringUtils.convertStringToDouble(price.getPrice()));
        product.setPriceRef(ficheDiscountRefProp.getLogicalRef());
        if (price.getUnitConvert() == 1) {
            product.setPrice(CalculateUtils.convertUnitPrice(StringUtils.convertStringToDouble(price.getPrice()), product.getConvfact1(), product.getConvfact2(), price.getConvFact1(), price.getConvFact2()));
        }
        String curCode = price.getCurCode();
        product.setPriceWithDigits(UnitPriceFormatter.getInstance(this.baseErp.getCentOfUnitPriceDigit()).getFormattedPrice(product.getPrice()));
        String priceWithDigits = product.getPriceWithDigits();
        if (TextUtils.isEmpty(curCode)) {
            str = "";
        } else {
            str = "/ " + curCode;
        }
        String format = String.format("%s %s", priceWithDigits, str);
        product.setIncVat(StringUtils.convertIntToBoolean(price.getInvcat()));
        FicheStringProp.setDefinition(format);
        product.setCPrice(format);
        product.setPriceSetFromBarcode(true);
        textView.setText(format);
        editText.setText("");
    }

    private void setRetailPrice(Price price, TextView textView, EditText editText, FicheDiscountRefProp ficheDiscountRefProp, Product product) {
        resetPrice(textView, product, ficheDiscountRefProp);
        double calculatePriceAddVat = CalculateUtils.calculatePriceAddVat(StringUtils.convertStringToDouble(price.getPrice()), product.getVat(), StringUtils.convertIntToBoolean(price.getInvcat()));
        FicheStringProp.setDefinition(String.valueOf(calculatePriceAddVat));
        product.setIncVat(StringUtils.convertIntToBoolean(price.getInvcat()));
        product.setPrice(calculatePriceAddVat);
        product.setPriceSetFromBarcode(true);
        editText.setText(StringUtils.convertDoubleToString(Double.valueOf(calculatePriceAddVat)));
    }
}
