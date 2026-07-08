package com.proje.mobilesales.features.sales.view.detail;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.*;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.base.BaseListFragment;
import com.proje.mobilesales.core.enums.*;
import com.proje.mobilesales.core.interfaces.*;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.utils.*;
import com.proje.mobilesales.core.view.SnappyLinearLayoutManager;
import com.proje.mobilesales.core.widget.OnSwipeTouchListener;
import com.proje.mobilesales.databinding.FragmentSalesOrderLineBinding;
import com.proje.mobilesales.features.activity.BarcodeScannerView;
import com.proje.mobilesales.features.adapter.AlternativePromotionListAdapter;
import com.proje.mobilesales.features.adapter.IListRecyclerView;
import com.proje.mobilesales.features.adapter.SurplusDiscountListAdapter;
import com.proje.mobilesales.features.customer.model.CustomerDiscount;
import com.proje.mobilesales.features.dbmodel.Curr;
import com.proje.mobilesales.features.model.*;
import com.proje.mobilesales.features.product.model.ItemUnit;
import com.proje.mobilesales.features.product.model.Product;
import com.proje.mobilesales.features.product.model.database.ItemPrice;
import com.proje.mobilesales.features.product.view.list.ProductListActivity;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.model.SalesFicheUserRights;
import com.proje.mobilesales.features.sales.model.SalesVariantCheck;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import com.proje.mobilesales.features.sales.model.fiche.SalesFicheDetailFields;
import com.proje.mobilesales.features.sales.repository.SalesDetailRepository;
import com.proje.mobilesales.features.sales.view.newadd.SalesActivityNew;
import com.proje.mobilesales.features.sales.view.variant.SalesVariantActivity;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PrimitiveCompanionObjects;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.proje.mobilesales.core.utils.AppUtils.isConnected;

public final class SalesDetailListFragment extends BaseListFragment implements ActionModeDelegate, ItemSurplusDiscountListener, UpdateFragmentView, AlternativeItemForCampaignPromotion, AlternativeItemAmountChangeListener {
    public static final int PRODUCT_BARCODE_READ_REQUEST_CODE = 101;
    public static final int PRODUCT_SELECT_REQUEST_CODE = 100;
    public static final int SALES_DETAIL_ENTER_REQUEST = 102;
    public static final int SALES_DETAIL_MULTIVARIANT_REQUEST = 103;
    private static final String STATE_CUSTOMER_CURRENCY = "state:customerCurrency";
    private static final String STATE_DEF_GROUP_CODE = "state:defGroupCode";
    private static final String STATE_DEF_ORDER_ID = "state:defOrderId";
    private static final String STATE_DEF_ORDER_ID_WHICH = "state:defOrderIdWhich";
    private static final String STATE_DEF_ORDER_ID_WHICH_GROUP = "state:defOrderIdWhichGroup";
    private static final String STATE_TOTAL_FRAME_OPEN = "state:totalFrameOpen";
    private FragmentSalesOrderLineBinding _binding;
    private View alternativePromotionDialogView;
    private AppCompatButton btnAddProduct;
    private AppCompatButton btnAddProductBarcode;
    private AppCompatButton btnAddProductForm;
    private LinearLayout btnContainer;
    private Drawable closeTotalDrawable;
    private FrameLayout frmOrderLineTotal;
    private FrameLayout frmProductAddContainer;
    private ImageView imgOrderLineTotalUp;
    private View lnOrderLineTotalAmount;
    private View lnOrderLineUnitTotal;
    private ActionMode mActionMode;
    private final SalesDetailLineRecyclerViewAdapter mAdapter;
    private AlertDialogBuilder<?> mAlertDialogBuilder;
    private AlertDialogBuilder<?> mAlternativePromotionAlertDialogBuilder;
    private int mCustomerCurrency;
    private String mDefGroupCode;
    private int mDefOrderId;
    private ProgressDialogBuilder<?> mProgressAlternativePromotionDialogBuilder;
    private ProgressDialogBuilder<?> mProgressDialogBuilder;
    private ProgressDialogBuilder<?> mProgressSurplusDialogBuilder;
    private boolean mTotalFrameOpen;
    private int mWhichForm;
    private int mWhichGroupForm;
    private Drawable openTotalDrawable;
    private final SalesDetailRepository repository;
    private ArrayList<Product> selectedProductList;
    private StringBuilder stringBuilder;
    private TextView txtOrderLineDiscount;
    private TextView txtOrderLineLineCount;
    private TextView txtOrderLineLineNet;
    private TextView txtOrderLineLineSku;
    private TextView txtOrderLineLineVat;
    private TextView txtOrderLineTotal;
    private TextView txtOrderLineTotalAmount;
    private TextView txtOrderLineTotalLabel;
    private TextView txtorderLineUnitTotal;
    private final SalesDetailViewModel viewModel;
    public static final Companion Companion = new Companion(null);
    public static final String EXTRA_SELECT_PRODUCTS = SalesDetailListFragment.class.getName() + ".EXTRA_SELECT_PRODUCTS";
    public static final String EXTRA_SALES_DETAIL = SalesDetailListFragment.class.getName() + ".EXTRA_SALES_DETAIL";
    public static final String EXTRA_SALES_DETAIL_POSITION = SalesDetailListFragment.class.getName() + ".EXTRA_SALES_DETAIL_POSITION";

    public SalesDetailListFragment() {
        SalesDetailRepository salesDetailRepository = new SalesDetailRepository();
        this.repository = salesDetailRepository;
        this.viewModel = new SalesDetailViewModel(salesDetailRepository);
        this.selectedProductList = new ArrayList<>();
        this.mAdapter = new SalesDetailLineRecyclerViewAdapter(this);
        this.mWhichForm = -1;
        this.mWhichGroupForm = -1;
    }

    private FragmentSalesOrderLineBinding getBinding() {
        FragmentSalesOrderLineBinding fragmentSalesOrderLineBinding = this._binding;
        Intrinsics.checkNotNull(fragmentSalesOrderLineBinding);
        return fragmentSalesOrderLineBinding;
    }

    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        return this.mProgressDialogBuilder;
    }

    public void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        this.mProgressDialogBuilder = progressDialogBuilder;
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        return this.mAlertDialogBuilder;
    }

    public void setMAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        this.mAlertDialogBuilder = alertDialogBuilder;
    }

    public ProgressDialogBuilder<?> getMProgressSurplusDialogBuilder() {
        return this.mProgressSurplusDialogBuilder;
    }

    public void setMProgressSurplusDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        this.mProgressSurplusDialogBuilder = progressDialogBuilder;
    }

    public ProgressDialogBuilder<?> getMProgressAlternativePromotionDialogBuilder() {
        return this.mProgressAlternativePromotionDialogBuilder;
    }

    public void setMProgressAlternativePromotionDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        this.mProgressAlternativePromotionDialogBuilder = progressDialogBuilder;
    }

    public AlertDialogBuilder<?> getMAlternativePromotionAlertDialogBuilder() {
        return this.mAlternativePromotionAlertDialogBuilder;
    }

    public void setMAlternativePromotionAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        this.mAlternativePromotionAlertDialogBuilder = alertDialogBuilder;
    }

    public boolean getMTotalFrameOpen() {
        return this.mTotalFrameOpen;
    }

    public void setMTotalFrameOpen(boolean z) {
        this.mTotalFrameOpen = z;
    }
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context requireContext = requireContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(requireContext, (BaseInjectableActivity) activity);
        Context requireContext2 = requireContext();
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilder = new AlertDialogBuilder.Impl(requireContext2, (BaseInjectableActivity) activity2);
        Context requireContext3 = requireContext();
        Activity activity3 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressSurplusDialogBuilder = new ProgressDialogBuilder.Impl(requireContext3, (BaseInjectableActivity) activity3);
        Context requireContext4 = requireContext();
        Activity activity4 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity4, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressAlternativePromotionDialogBuilder = new ProgressDialogBuilder.Impl(requireContext4, (BaseInjectableActivity) activity4);
        Context requireContext5 = requireContext();
        Activity activity5 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity5, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlternativePromotionAlertDialogBuilder = new AlertDialogBuilder.Impl(requireContext5, (BaseInjectableActivity) activity5);
        if (bundle != null) {
            this.mTotalFrameOpen = bundle.getBoolean(STATE_TOTAL_FRAME_OPEN, false);
            this.mDefOrderId = bundle.getInt(STATE_DEF_ORDER_ID, 0);
            this.mWhichForm = bundle.getInt(STATE_DEF_ORDER_ID_WHICH, 0);
            this.mWhichGroupForm = bundle.getInt(STATE_DEF_ORDER_ID_WHICH_GROUP, 0);
            this.mDefGroupCode = bundle.getString(STATE_DEF_GROUP_CODE);
        } else {
            this.mTotalFrameOpen = false;
        }
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        this.openTotalDrawable = ContextCompat.getDrawable(context, R.drawable.ic_arrow_up_bold_circle_outline_white_36dp);
        Context context2 = getContext();
        Intrinsics.checkNotNull(context2);
        this.closeTotalDrawable = ContextCompat.getDrawable(context2, R.drawable.ic_arrow_down_bold_circle_outline_white_36dp);
    } 
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentSalesOrderLineBinding.inflate(inflater, viewGroup, false);
        this.mRecyclerView = getBinding().rcwList;
        this.frmProductAddContainer = getBinding().rltProductContainer;
        this.btnAddProduct = getBinding().btnAddProduct;
        this.btnAddProductBarcode = getBinding().btnAddProductBarcode;
        this.btnAddProductForm = getBinding().btnAddProductForm;
        this.btnContainer = getBinding().buttonsContainer;
        this.frmOrderLineTotal = getBinding().frmOrderLineTotal;
        this.txtOrderLineTotal = getBinding().txtOrderLineTotal;
        this.txtOrderLineTotalLabel = getBinding().txtOrderLineTotalLabel;
        this.txtOrderLineDiscount = getBinding().txtOrderLineDiscount;
        this.txtOrderLineLineVat = getBinding().txtOrderLineVatTotal;
        this.txtOrderLineLineNet = getBinding().txtOrderLineNet;
        this.txtOrderLineLineCount = getBinding().txtOrderLineCount;
        this.txtOrderLineLineSku = getBinding().txtOrderLineSku;
        this.imgOrderLineTotalUp = getBinding().imgOrderLineTotalUp;
        this.txtorderLineUnitTotal = getBinding().txtOrderLineUnitTotal;
        this.lnOrderLineUnitTotal = getBinding().lnOrderLineUnitTotal;
        this.lnOrderLineTotalAmount = getBinding().lnOrderLineTotalAmount;
        this.txtOrderLineTotalAmount = getBinding().txtOrderLineTotalAmount;
        RelativeLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    } 
    public void onViewCreated(final View view, final Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        FicheMode salesFicheMode = getSalesFicheMode();
        FicheMode ficheMode = FicheMode.ANALYSE;
        if (salesFicheMode != ficheMode) {
            AppCompatButton appCompatButton = this.btnAddProduct;
            Intrinsics.checkNotNull(appCompatButton);
            appCompatButton.setOnClickListener(new View.OnClickListener() { 
                public   void SalesDetailListFragmentExternalSyntheticLambda5() {
                } 
                public void onClick(View view2) {
                    SalesDetailListFragment.onViewCreatedlambda0(SalesDetailListFragment.this, view2);
                }
            });
            AppCompatButton appCompatButton2 = this.btnAddProductForm;
            Intrinsics.checkNotNull(appCompatButton2);
            appCompatButton2.setOnClickListener(new View.OnClickListener() { 
                public   void SalesDetailListFragmentExternalSyntheticLambda6() {
                } 
                public void onClick(View view2) {
                    SalesDetailListFragment.onViewCreatedlambda1(SalesDetailListFragment.this, view2);
                }
            });
        } else {
            FrameLayout frameLayout = this.frmProductAddContainer;
            Intrinsics.checkNotNull(frameLayout);
            frameLayout.setVisibility(View.GONE);
        }
        FrameLayout frameLayout2 = this.frmOrderLineTotal;
        Intrinsics.checkNotNull(frameLayout2);
        frameLayout2.setOnTouchListener(new OnSwipeTouchListener() { 
            void SalesDetailListFragmentonViewCreated3() {
            }
            public void onBottomToTopSwipe() {
                if (SalesDetailListFragment.this.getMTotalFrameOpen()) {
                    return;
                }
                SalesDetailListFragment.this.openFrame();
            }

            public void onTopToBottomSwipe() {
                if (SalesDetailListFragment.this.getMTotalFrameOpen()) {
                    SalesDetailListFragment.this.closeFrame();
                }
            }
        });
        AppCompatButton appCompatButton3 = this.btnAddProductBarcode;
        Intrinsics.checkNotNull(appCompatButton3);
        appCompatButton3.setOnClickListener(new View.OnClickListener() { 
            public   void SalesDetailListFragmentExternalSyntheticLambda7() {
            } 
            public void onClick(View view2) {
                SalesDetailListFragment.onViewCreatedlambda2(SalesDetailListFragment.this, view2);
            }
        });
        initImgOrderLineTotalClick();
        if (!SalesUtils.isSalesTypeOrder(getmSales().getmSalesType()) || getSalesFicheMode() == ficheMode) {
            return;
        }
        dragAndDropProducts();
    }

    public static void onViewCreatedlambda0(SalesDetailListFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.resetForm();
        this0.startProductActivity(null);
    }

    public static void onViewCreatedlambda1(SalesDetailListFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.createFormGroupDialog();
    }

    public static void onViewCreatedlambda2(SalesDetailListFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.scanFromFragment();
    }

    public int getProductPromotionCount(int i2) {
        ArrayList<SalesDetail> mSalesDetailList = getmSales().getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList);
        int size = mSalesDetailList.size();
        int i3 = 0;
        for (int i4 = i2 + 1; i4 < size && isItemDetailPromotion(i4); i4++) {
            i3++;
        }
        return i3;
    }

    private SalesDetail getDetailItem(int i2) {
        if (i2 >= 0) {
            ArrayList<SalesDetail> mSalesDetailList = getmSales().getMSalesDetailList();
            Intrinsics.checkNotNull(mSalesDetailList);
            if (i2 <= mSalesDetailList.size()) {
                ArrayList<SalesDetail> mSalesDetailList2 = getmSales().getMSalesDetailList();
                Intrinsics.checkNotNull(mSalesDetailList2);
                return mSalesDetailList2.get(i2);
            }
        }
        return null;
    }

    private GlobalLineType getItemGlobalLineType(int i2) {
        if (i2 >= 0) {
            ArrayList<SalesDetail> mSalesDetailList = getmSales().getMSalesDetailList();
            Intrinsics.checkNotNull(mSalesDetailList);
            if (i2 <= mSalesDetailList.size()) {
                GlobalLineType.Companion companion = GlobalLineType.Companion;
                SalesDetail detailItem = getDetailItem(i2);
                Intrinsics.checkNotNull(detailItem);
                return companion.fromInt(detailItem.getGlobalLineType());
            }
        }
        return GlobalLineType.UNKNOWN;
    }

    public boolean hasItemPromotion(int i2) {
        if (i2 >= 0) {
            ArrayList<SalesDetail> mSalesDetailList = getmSales().getMSalesDetailList();
            Intrinsics.checkNotNull(mSalesDetailList);
            if (i2 <= mSalesDetailList.size()) {
                SalesDetail detailItem = getDetailItem(i2);
                Intrinsics.checkNotNull(detailItem);
                return detailItem.getPromotion().isSelect();
            }
        }
        return false;
    }

    public boolean isItemDetailPromotion(int i2) {
        if (i2 < 0) {
            return false;
        }
        ArrayList<SalesDetail> mSalesDetailList = getmSales().getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList);
        return i2 <= mSalesDetailList.size() && hasItemPromotion(i2) && getItemGlobalLineType(i2) == GlobalLineType.DETAIL;
    }

    private boolean isItemGlobalPromotion(int i2) {
        if (i2 < 0) {
            return false;
        }
        ArrayList<SalesDetail> mSalesDetailList = getmSales().getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList);
        return i2 <= mSalesDetailList.size() && hasItemPromotion(i2) && getItemGlobalLineType(i2) == GlobalLineType.GENERAL;
    }

    private void dragAndDropProducts() {
        new ItemTouchHelper(new ItemTouchHelper.Callback() {  
            public boolean isLongPressDragEnabled() {
                return true;
            }
 
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int i2) {
                Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
            }

            void SalesDetailListFragmentdragAndDropProducts1() {
            }
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                boolean hasItemPromotion;
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
                hasItemPromotion = SalesDetailListFragment.this.hasItemPromotion(viewHolder.getAdapterPosition());
                if (hasItemPromotion) {
                    Toast.makeText(SalesDetailListFragment.this.getContext(), SalesDetailListFragment.this.getString(R.string.exp_95_error_promotion_cannot_be_moved), Toast.LENGTH_LONG).show();
                    return 0;
                }
                return ItemTouchHelper.Callback.makeMovementFlags(1, 0);
            }
 
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int productPromotionCount;
                boolean isItemDetailPromotion;
                SalesDetailLineRecyclerViewAdapter salesDetailLineRecyclerViewAdapter;
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
                Intrinsics.checkNotNullParameter(target, "target");
                int adapterPosition = viewHolder.getAdapterPosition();
                int adapterPosition2 = target.getAdapterPosition();
                boolean z = adapterPosition > adapterPosition2;
                productPromotionCount = SalesDetailListFragment.this.getProductPromotionCount(adapterPosition);
                if (!z) {
                    return false;
                }
                isItemDetailPromotion = SalesDetailListFragment.this.isItemDetailPromotion(adapterPosition2);
                if (isItemDetailPromotion) {
                    Log.d("ContentValues", "Ta\u015f\u0131d\u0131\u011f\u0131n\u0131z yerde sat\u0131r promosyonu olamaz");
                    return false;
                }
                ArrayList<SalesDetail> mSalesDetailList = SalesDetailListFragment.this.getmSales().getMSalesDetailList();
                Intrinsics.checkNotNull(mSalesDetailList);
                int i2 = adapterPosition2 + 1;
                mSalesDetailList.get(adapterPosition).setLineNr(i2);
                ArrayList<SalesDetail> mSalesDetailList2 = SalesDetailListFragment.this.getmSales().getMSalesDetailList();
                Intrinsics.checkNotNull(mSalesDetailList2);
                int i3 = adapterPosition + 1;
                mSalesDetailList2.get(adapterPosition2).setLineNr(i3);
                ArrayList<SalesDetail> mSalesDetailList3 = SalesDetailListFragment.this.getmSales().getMSalesDetailList();
                Intrinsics.checkNotNull(mSalesDetailList3);
                mSalesDetailList3.get(adapterPosition).setLineNumber(i2);
                ArrayList<SalesDetail> mSalesDetailList4 = SalesDetailListFragment.this.getmSales().getMSalesDetailList();
                Intrinsics.checkNotNull(mSalesDetailList4);
                mSalesDetailList4.get(adapterPosition2).setLineNumber(i3);
                ArrayList<SalesDetail> mSalesDetailList5 = SalesDetailListFragment.this.getmSales().getMSalesDetailList();
                Intrinsics.checkNotNull(mSalesDetailList5);
                ArrayList<SalesDetail> mSalesDetailList6 = SalesDetailListFragment.this.getmSales().getMSalesDetailList();
                Intrinsics.checkNotNull(mSalesDetailList6);
                mSalesDetailList5.add(adapterPosition2, mSalesDetailList6.remove(adapterPosition));
                for (int i4 = 0; i4 < productPromotionCount; i4++) {
                    ArrayList<SalesDetail> mSalesDetailList7 = SalesDetailListFragment.this.getmSales().getMSalesDetailList();
                    Intrinsics.checkNotNull(mSalesDetailList7);
                    int i5 = adapterPosition + i4;
                    int i6 = i5 + 1;
                    int i7 = adapterPosition2 + i4;
                    int i8 = i7 + 2;
                    mSalesDetailList7.get(i6).setLineNr(i8);
                    ArrayList<SalesDetail> mSalesDetailList8 = SalesDetailListFragment.this.getmSales().getMSalesDetailList();
                    Intrinsics.checkNotNull(mSalesDetailList8);
                    int i9 = i7 + 1;
                    int i10 = i5 + 2;
                    mSalesDetailList8.get(i9).setLineNr(i10);
                    ArrayList<SalesDetail> mSalesDetailList9 = SalesDetailListFragment.this.getmSales().getMSalesDetailList();
                    Intrinsics.checkNotNull(mSalesDetailList9);
                    mSalesDetailList9.get(i6).setLineNumber(i8);
                    ArrayList<SalesDetail> mSalesDetailList10 = SalesDetailListFragment.this.getmSales().getMSalesDetailList();
                    Intrinsics.checkNotNull(mSalesDetailList10);
                    mSalesDetailList10.get(i9).setLineNumber(i10);
                    ArrayList<SalesDetail> mSalesDetailList11 = SalesDetailListFragment.this.getmSales().getMSalesDetailList();
                    Intrinsics.checkNotNull(mSalesDetailList11);
                    ArrayList<SalesDetail> mSalesDetailList12 = SalesDetailListFragment.this.getmSales().getMSalesDetailList();
                    Intrinsics.checkNotNull(mSalesDetailList12);
                    mSalesDetailList11.add(i9, mSalesDetailList12.remove(i6));
                }
                salesDetailLineRecyclerViewAdapter = SalesDetailListFragment.this.mAdapter;
                ArrayList<SalesDetail> mSalesDetailList13 = SalesDetailListFragment.this.getmSales().getMSalesDetailList();
                Intrinsics.checkNotNull(mSalesDetailList13);
                salesDetailLineRecyclerViewAdapter.setSalesDetails(mSalesDetailList13);
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                Intrinsics.checkNotNull(adapter);
                adapter.notifyItemMoved(adapterPosition, adapterPosition2);
                return true;
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundResource(0);
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i2) {
                super.onSelectedChanged(viewHolder, i2);
                if (i2 == 2) {
                    Intrinsics.checkNotNull(viewHolder);
                    viewHolder.itemView.setBackgroundResource(R.drawable.item_shadow);
                }
            }
        }).attachToRecyclerView(this.mRecyclerView);
    }

    private void initImgOrderLineTotalClick() {
        ImageView imageView = this.imgOrderLineTotalUp;
        Intrinsics.checkNotNull(imageView);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.detail.SalesDetailListFragmentExternalSyntheticLambda8
            public void SalesDetailListFragmentExternalSyntheticLambda8() {
            }
            public void onClick(View view) {
                SalesDetailListFragment.initImgOrderLineTotalClicklambda3(SalesDetailListFragment.this, view);
            }
        });
    }

    public static void initImgOrderLineTotalClicklambda3(SalesDetailListFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (this0.mTotalFrameOpen) {
            this0.closeFrame();
        } else {
            this0.openFrame();
        }
    }

    private void setImgOrderLineTotalUpDrawable() {
        if (this.mTotalFrameOpen) {
            ImageView imageView = this.imgOrderLineTotalUp;
            Intrinsics.checkNotNull(imageView);
            imageView.setImageDrawable(this.closeTotalDrawable);
        } else {
            ImageView imageView2 = this.imgOrderLineTotalUp;
            Intrinsics.checkNotNull(imageView2);
            imageView2.setImageDrawable(this.openTotalDrawable);
        }
    }

    private void resetForm() {
        this.mDefOrderId = 0;
        this.mWhichForm = -1;
        this.mWhichGroupForm = -1;
        this.mDefGroupCode = "";
    }

    private void createFormDialog() {
        Cursor query = this.viewModel.getSqlBriteDatabase().query(getString(R.string.qry_get_sales_def_order_short), StringUtils.convertIntToString(!SalesUtils.isSalesTypeOrderOrDemand(getmSales().getmSalesType()) ? 1 : 0), this.mDefGroupCode);
        if (query == null || query.getCount() == 0) {
            Toast.makeText(getActivity(), getString(R.string.str_not_found_definition_form), Toast.LENGTH_SHORT).show();
            if (query != null) {
                query.isClosed();
                return;
            }
            return;
        }
        this.mWhichForm = 0;
        query.moveToPosition(0);
        this.mDefOrderId = query.getInt(query.getColumnIndex(getString(R.string.column_id)));
        startProductActivity(null);
    }

    private void createFormGroupDialog() {
        if (SalesUtils.isSalesTypeWhTransfer(getmSales().getmSalesType())) {
            return;
        }
        Cursor query = this.viewModel.getSqlBriteDatabase().query(getString(R.string.qry_get_sales_def_group_order_short, StringUtils.convertIntToString(!SalesUtils.isSalesTypeOrderOrDemand(getmSales().getmSalesType()) ? 1 : 0)));
        if (query == null || query.getCount() == 0) {
            if (query != null) {
                query.close();
            }
            Toast.makeText(getActivity(), getString(R.string.str_not_found_definition_form), Toast.LENGTH_SHORT).show();
            return;
        }
        query.moveToPosition(0);
        this.mWhichGroupForm = 0;
        this.mDefOrderId = query.getInt(query.getColumnIndex(getString(R.string.column_id)));
        this.mDefGroupCode = query.getString(query.getColumnIndex(getString(R.string.column_code)));
        int i2 = query.getInt(query.getColumnIndex(getString(R.string.column_count)));
        query.close();
        if (i2 == 1) {
            startProductActivity(null);
        } else {
            createFormDialog();
        }
    }

    public void openFrame() {
        FrameLayout frameLayout = this.frmOrderLineTotal;
        Intrinsics.checkNotNull(frameLayout);
        ViewPropertyAnimator animate = frameLayout.animate();
        Intrinsics.checkNotNull(this.frmOrderLineTotal);
        animate.translationY((r1.getHeight() / 7) * (-6)).setDuration(500L);
        ImageView imageView = this.imgOrderLineTotalUp;
        Intrinsics.checkNotNull(imageView);
        imageView.setImageDrawable(this.closeTotalDrawable);
        this.mTotalFrameOpen = true;
    }

    public void closeFrame() {
        FrameLayout frameLayout = this.frmOrderLineTotal;
        Intrinsics.checkNotNull(frameLayout);
        frameLayout.animate().translationY(0.0f).setDuration(500L);
        ImageView imageView = this.imgOrderLineTotalUp;
        Intrinsics.checkNotNull(imageView);
        imageView.setImageDrawable(this.openTotalDrawable);
        this.mTotalFrameOpen = false;
    }

    private void startProductActivity(ArrayList<BarcodeResult> arrayList) {
        Intent intent = new Intent(getActivity(), ProductListActivity.class);
        intent.putExtra(ProductListActivity.EXTRA_CUSTOMER_REF, getCustomerRef());
        intent.putExtra(ProductListActivity.EXTRA_PRODUCT_SELECT_TYPE, true);
        intent.putExtra(ProductListActivity.EXTRA_WAREHOUSE_NR, getmSales().getWareHouse().getLogicalRef());
        if (getmSales().getmSalesType() == SalesType.DEMAND) {
            intent.putExtra(ProductListActivity.EXTRA_SOURCE_WAREHOUSE_NR, getmSales().getSourceWareHouse().getLogicalRef());
        }
        intent.putExtra(ProductListActivity.EXTRA_SALES_TYPE, getmSales().getmSalesType());
        intent.putExtra(ProductListActivity.EXTRA_DEF_ORDER_ID, this.mDefOrderId);
        intent.putExtra(ProductListActivity.EXTRA_PAYMENT_TRADE_GROUP, getmSales().getTradeGroup().toString());
        intent.putExtra(ProductListActivity.EXTRA_PAYMENT_REF, getmSales().getPayPlan().getLogicalRef());
        intent.putExtra(ProductListActivity.EXTRA_BARCODE_FILTER, arrayList);
        String str = ProductListActivity.EXTRA_SELECTED_PRODUCT_SIZE;
        ArrayList<SalesDetail> mSalesDetailList = getmSales().getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList);
        intent.putExtra(str, mSalesDetailList.size());
        intent.putExtra(ProductListActivity.EXTRA_DIVISION_NR, getmSales().getBranch().getLogicalRef());
        intent.putExtra(ProductListActivity.EXTRA_SPECODE, getmSales().getFirstSpeCode().getCode());
        startActivityForResult(intent, 100);
    }

    @Override // com.proje.mobilesales.core.base.BaseListFragment, com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getActivityComponent().inject(this);
        this.mCustomerCurrency = bundle != null ? bundle.getInt(STATE_CUSTOMER_CURRENCY) : this.viewModel.getSqlHelper().getClCardCurrency(getCustomerRef());
        setProductMenuVisibility();
        updateItemStocks();
    }

    private void updateItemStocks() {
        if (getmSales().getmSalesType() == SalesType.RETURN_INVOICE || getmSales().getmSalesType() == SalesType.RETAIL_RETURN_INVOICE || getmSales().getmSalesType() == SalesType.RETURN_DISPATCH || ((getSalesFicheMode() != FicheMode.EDIT && getSalesFicheMode() != FicheMode.COPY) || !this.viewModel.getProductOnlineStock() || !isConnected(getActivity()))) {
            loadDetailsAfterStocksUpdated();
        } else {
            updateStockAmounts();
        }
    }

    public void updateStockAmounts() {
        if (ContextUtils.checkInternetConnection()) {
            ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder);
            progressDialogBuilder.setMessage(getString(R.string.type_get_stock)).setCancelable(false).show();
            this.viewModel.getOnlineStockAllInSingleResponse(new ProductOnlineStockGetListener(this));
        }
    }

    public void showCurrencyPriceTextView() {
        this.mAdapter.setShowCurrencyPrice(true);
        notifyAdapter();
    }

    private void setProductMenuVisibility() {
        if (!getSalesFicheUserRights().isOpenClassic()) {
            AppCompatButton appCompatButton = this.btnAddProduct;
            Intrinsics.checkNotNull(appCompatButton);
            appCompatButton.setVisibility(View.INVISIBLE);
        }
        if (!getSalesFicheUserRights().isOpenForm()) {
            AppCompatButton appCompatButton2 = this.btnAddProductForm;
            Intrinsics.checkNotNull(appCompatButton2);
            appCompatButton2.setVisibility(View.INVISIBLE);
        }
        if (getmSales().hasOrderReference() && !this.viewModel.addProductOutOfTheOrder(getSalesType())) {
            FrameLayout frameLayout = this.frmProductAddContainer;
            Intrinsics.checkNotNull(frameLayout);
            frameLayout.setVisibility(View.GONE);
        }
        if (getSalesType() == SalesType.WHTRANSFER) {
            AppCompatButton appCompatButton3 = this.btnAddProductForm;
            Intrinsics.checkNotNull(appCompatButton3);
            appCompatButton3.setVisibility(View.INVISIBLE);
            LinearLayout linearLayout = this.btnContainer;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.removeView(this.btnAddProductForm);
            AppCompatButton appCompatButton4 = this.btnAddProductBarcode;
            Intrinsics.checkNotNull(appCompatButton4);
            ViewGroup.LayoutParams layoutParams = appCompatButton4.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
            ((LinearLayout.LayoutParams) layoutParams).weight = 1.5f;
            AppCompatButton appCompatButton5 = this.btnAddProduct;
            Intrinsics.checkNotNull(appCompatButton5);
            ViewGroup.LayoutParams layoutParams2 = appCompatButton5.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams2, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
            ((LinearLayout.LayoutParams) layoutParams2).weight = 1.5f;
        }
    }

    @Override // com.proje.mobilesales.core.base.BaseListFragment, com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        ActionMode actionMode = this.mActionMode;
        if (actionMode != null) {
            Intrinsics.checkNotNull(actionMode);
            actionMode.finish();
        }
    }

    @Override // com.proje.mobilesales.core.base.BaseListFragment
    protected IListRecyclerView getAdapter() {
        return this.mAdapter;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i2, int i3, Intent intent) {
        ArrayList parcelableArrayList;
        FicheStringProp ficheStringProp;
        super.onActivityResult(i2, i3, intent);
        if (i2 == 49374 && i3 == -1) {
            int intExtra = intent != null ? intent.getIntExtra("bigdata:synccode", -1) : -1;
            if (intExtra != -1 && this.viewModel.getObjectWithSyncCode(intExtra, false) != null) {
                ArrayList<Product> arrayList = this.selectedProductList;
                Object objectWithSyncCode = this.viewModel.getObjectWithSyncCode(intExtra, true);
                Intrinsics.checkNotNull(objectWithSyncCode, "null cannot be cast to non-null type java.util.ArrayList<com.proje.mobilesales.features.product.model.Product>{ kotlin.collections.TypeAliasesKt.ArrayList<com.proje.mobilesales.features.product.model.Product> }");
                arrayList.addAll((ArrayList) objectWithSyncCode);
            }
            convertProductToSalesDetail();
            if (getmSales().hasCampaignApplied()) {
                notifyReApplyCampaign();
            }
        }
        if (getSalesFicheMode() != FicheMode.ANALYSE) {
            if (i2 == 100 && i3 == -1) {
                int intExtra2 = intent != null ? intent.getIntExtra("bigdata:synccode", -1) : -1;
                if (intExtra2 != -1 && this.viewModel.getObjectWithSyncCode(intExtra2, false) != null) {
                    ArrayList<Product> arrayList2 = this.selectedProductList;
                    Object objectWithSyncCode2 = this.viewModel.getObjectWithSyncCode(intExtra2, true);
                    Intrinsics.checkNotNull(objectWithSyncCode2, "null cannot be cast to non-null type java.util.ArrayList<com.proje.mobilesales.features.product.model.Product>{ kotlin.collections.TypeAliasesKt.ArrayList<com.proje.mobilesales.features.product.model.Product> }");
                    arrayList2.addAll((ArrayList) objectWithSyncCode2);
                }
                convertProductToSalesDetail();
                if (getmSales().hasCampaignApplied()) {
                    notifyReApplyCampaign();
                }
            }
            if (i2 == 102) {
                if (i3 == -1) {
                    try {
                        Intrinsics.checkNotNull(intent);
                        Bundle extras = intent.getExtras();
                        Intrinsics.checkNotNull(extras);
                        int i4 = extras.getInt(EXTRA_SALES_DETAIL_POSITION);
                        Bundle extras2 = intent.getExtras();
                        Intrinsics.checkNotNull(extras2);
                        SalesDetail salesDetail = extras2.getParcelable(EXTRA_SALES_DETAIL);
                        if (checkShouldReApplyCampaign(i4, salesDetail)) {
                            notifyReApplyCampaign();
                        } else {
                            selectProduct(salesDetail, i4, false);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } else if (i3 == 104) {
                    try {
                        Intrinsics.checkNotNull(intent);
                        Bundle extras3 = intent.getExtras();
                        Intrinsics.checkNotNull(extras3);
                        int i5 = extras3.getInt(EXTRA_SALES_DETAIL_POSITION);
                        if (getmSales().getMSalesDetailList() == null) {
                            return;
                        }
                        ArrayList<SalesDetail> mSalesDetailList = getmSales().getMSalesDetailList();
                        Intrinsics.checkNotNull(mSalesDetailList);
                        if (mSalesDetailList.size() == 0) {
                            return;
                        }
                        ArrayList<SalesDetail> mSalesDetailList2 = getmSales().getMSalesDetailList();
                        Intrinsics.checkNotNull(mSalesDetailList2);
                        SalesDetail salesDetail2 = mSalesDetailList2.get(i5);
                        Intrinsics.checkNotNullExpressionValue(salesDetail2, "get(...)");
                        boolean checkShouldReApplyCampaignForDelete = checkShouldReApplyCampaignForDelete(salesDetail2);
                        ArrayList<SalesDetail> mSalesDetailList3 = getmSales().getMSalesDetailList();
                        Intrinsics.checkNotNull(mSalesDetailList3);
                        mSalesDetailList3.remove(i5);
                        this.mAdapter.setSalesDetails(getmSales().getMSalesDetailList());
                        Toast.makeText(getActivity(), getString(R.string.str_deleted), Toast.LENGTH_LONG).show();
                        if (checkShouldReApplyCampaignForDelete) {
                            notifyReApplyCampaign();
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            }
            if (i2 == 103 && i3 == -1) {
                Intrinsics.checkNotNull(intent);
                Bundle extras4 = intent.getExtras();
                if (extras4 != null && extras4.containsKey(SalesVariantActivity.MULTI_VARIANT_LIST) && (parcelableArrayList = extras4.getParcelableArrayList(SalesVariantActivity.MULTI_VARIANT_LIST)) != null && parcelableArrayList.size() > 0) {
                    long detailId = ((SalesVariantCheck) parcelableArrayList.get(0)).getDetailId();
                    if (detailId == -1) {
                        return;
                    }
                    ArrayList<SalesDetail> mSalesDetailList4 = getmSales().getMSalesDetailList();
                    Intrinsics.checkNotNull(mSalesDetailList4);
                    Iterator<SalesDetail> it = mSalesDetailList4.iterator();
                    SalesDetail salesDetail3 = null;
                    while (it.hasNext()) {
                        SalesDetail next = it.next();
                        if (next.getId() == detailId) {
                            salesDetail3 = next;
                        }
                    }
                    if (salesDetail3 == null) {
                        return;
                    }
                    try {
                        Iterator it2 = parcelableArrayList.iterator();
                        while (it2.hasNext()) {
                            SalesVariantCheck salesVariantCheck = (SalesVariantCheck) it2.next();
                            SalesDetail m1481clone = salesDetail3.m1481clone();
                            ItemVariantStock mVariant = salesVariantCheck.getMVariant();
                            Intrinsics.checkNotNull(mVariant);
                            int variantRef = mVariant.getVariantRef();
                            ItemVariantStock mVariant2 = salesVariantCheck.getMVariant();
                            Intrinsics.checkNotNull(mVariant2);
                            String variantName = mVariant2.getVariantName();
                            ItemVariantStock mVariant3 = salesVariantCheck.getMVariant();
                            Intrinsics.checkNotNull(mVariant3);
                            m1481clone.setVariant(new FicheDiscountRefProp(variantRef, -1, variantName, mVariant3.getVarintCode()));
                            if (salesVariantCheck.isDivUnit()) {
                                ItemVariantStock mVariant4 = salesVariantCheck.getMVariant();
                                Intrinsics.checkNotNull(mVariant4);
                                ficheStringProp = new FicheStringProp(StringUtils.convertDoubleToString(Double.valueOf(mVariant4.getAmount())));
                            } else {
                                ItemVariantStock mVariant5 = salesVariantCheck.getMVariant();
                                Intrinsics.checkNotNull(mVariant5);
                                ficheStringProp = new FicheStringProp(StringUtils.convertIntToString((int) mVariant5.getAmount()));
                            }
                            m1481clone.setAmount(ficheStringProp);
                            if (!SalesUtils.isSalesTypeDemandOrWhTransfer(getmSales().getmSalesType())) {
                                setPriceForClonedDetail(m1481clone);
                            }
                            ItemVariantStock mVariant6 = salesVariantCheck.getMVariant();
                            Intrinsics.checkNotNull(mVariant6);
                            m1481clone.setActualStock(mVariant6.getVariantRealStok());
                            ArrayList<SalesDetail> arrayList3 = new ArrayList<>();
                            arrayList3.add(m1481clone);
                            if (getSalesFicheUserRights().isLineIntegration()) {
                                getmSales().addSalesDetailItemsLineIntegration(arrayList3);
                            } else {
                                getmSales().addSalesDetailItems(arrayList3);
                            }
                        }
                        ArrayList<SalesDetail> mSalesDetailList5 = getmSales().getMSalesDetailList();
                        if (mSalesDetailList5 != null) {
                            mSalesDetailList5.remove(salesDetail3);
                        }
                        this.selectedProductList.clear();
                        this.mAdapter.setSalesDetails(getmSales().getMSalesDetailList());
                        getmSales().calculateSalesTotal();
                        if (getmSales().hasCampaignApplied()) {
                            notifyReApplyCampaign();
                        } else if (isConnected(getContext()) && !SalesUtils.isSalesTypeDemandOrWhTransfer(getmSales().getmSalesType())) {
                            getSalesActivity().startSetProductPrice(parcelableArrayList.size());
                        }
                    } catch (Exception e4) {
                        Log.e("MULTI_VARIANT", "Multi variant error", e4);
                    }
                }
            }
            setTotalLayoutWithNotify();
        }
    }

    public void notifyReApplyCampaign() {
        try {
            getSalesActivity().openSalesCampaignDialog();
        } catch (Exception e2) {
            Log.e(getTag(), "Error on checkShouldReApplyCampaign", e2);
        }
    }

    private boolean checkShouldReApplyCampaign(int i2, SalesDetail salesDetail) {
        if (getSalesFicheMode() != FicheMode.ANALYSE && getmSales().hasCampaignApplied() && this.viewModel.erpType() == ErpType.TIGER) {
            ArrayList<SalesDetail> mSalesDetailList = getmSales().getMSalesDetailList();
            Intrinsics.checkNotNull(mSalesDetailList);
            SalesDetail salesDetail2 = mSalesDetailList.get(i2);
            Intrinsics.checkNotNullExpressionValue(salesDetail2, "get(...)");
            SalesDetail salesDetail3 = salesDetail2;
            Intrinsics.checkNotNull(salesDetail);
            if (salesDetail.getUnit().getLogicalRef() == salesDetail3.getUnit().getLogicalRef() && salesDetail.getPrice().getDefinitionDouble() == salesDetail3.getPrice().getDefinitionDouble() && salesDetail.getEnteryPrice() == salesDetail3.getEnteryPrice() && salesDetail.getAmount().getDefinitionDouble() == salesDetail3.getAmount().getDefinitionDouble()) {
                return false;
            }
            if (salesDetail.hasCampaign()) {
                selectProduct(salesDetail, i2, true);
                return true;
            }
            if (getmSales().getSalesFicheDiscountProps().hasCampaignApplied()) {
                selectProduct(salesDetail, i2, true);
                return true;
            }
            ArrayList<SalesDetail> mSalesDetailList2 = getmSales().getMSalesDetailList();
            Intrinsics.checkNotNull(mSalesDetailList2);
            Iterator<SalesDetail> it = mSalesDetailList2.iterator();
            while (it.hasNext()) {
                SalesDetail next = it.next();
                if (next.getLineType() == LineType.PROMOTION.value && next.getGlobalLineType() == GlobalLineType.GENERAL.getValue()) {
                    selectProduct(salesDetail, i2, true);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkShouldReApplyCampaignForDelete(SalesDetail salesDetail) {
        Intrinsics.checkNotNullParameter(salesDetail, "salesDetail");
        if (salesDetail.hasCampaign() || getmSales().getSalesFicheDiscountProps().hasCampaignApplied()) {
            return true;
        }
        ArrayList<SalesDetail> mSalesDetailList = getmSales().getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList);
        Iterator<SalesDetail> it = mSalesDetailList.iterator();
        while (it.hasNext()) {
            SalesDetail next = it.next();
            if (next.getLineType() == LineType.PROMOTION.value && next.getGlobalLineType() == GlobalLineType.GENERAL.getValue()) {
                return true;
            }
        }
        return false;
    }
    private void setPriceForClonedDetail(SalesDetail salesDetail) {
        String str;
        try {
            Cursor query = this.viewModel.getSqlBriteDatabase().query(this.viewModel.getSqlHelper().getSalesDetailProductPriceSql(getContext(), getmSales().getClRef(), salesDetail.isProduct(), salesDetail.getVariant().getCode(), getmSales().getBranch().getLogicalRef()), StringUtils.convertIntToString(salesDetail.getItemRef()), StringUtils.convertIntToString(getmSales().getClRef()), String.valueOf(salesDetail.getPType()), StringUtils.convertIntToString(salesDetail.getUnit().getLogicalRef()), StringUtils.convertIntToString(salesDetail.getPayment().getLogicalRef()), getmSales().getTradeGroup().getDefinition(), this.viewModel.getSqlHelper().getClCardTradingGrp(getmSales().getClRef()));
            if (query != null) {
                if (query.getCount() == 0) {
                }
                salesDetail.getSelectedPrice().setWhich(0);
                Intrinsics.checkNotNull(query);
                if (query.moveToPosition(0)) {
                    salesDetail.getSelectedPrice().setLogicalRef(query.getInt(query.getColumnIndex(getString(R.string.column_id))));
                    salesDetail.getSelectedPrice().setCode(String.valueOf(query.getInt(query.getColumnIndex(getString(R.string.column_id)))));
                    salesDetail.setEnteryPrice(query.getDouble(query.getColumnIndex("PRICE")));
                    if (query.getInt(query.getColumnIndex("UNITCONVERT")) == 1) {
                        salesDetail.setEnteryPrice(CalculateUtils.convertUnitPrice(salesDetail.getEnteryPrice(), salesDetail.getConvFact1(), salesDetail.getConvFact2(), query.getDouble(query.getColumnIndex("CONVFACT1")), query.getDouble(query.getColumnIndex("CONVFACT2"))));
                    }
                    salesDetail.getCurrType().reset();
                    salesDetail.curCodeStr = query.getString(query.getColumnIndex("CURCODE"));
                    salesDetail.setPriceWithDigit(UnitPriceFormatter.getInstance(this.viewModel.getCentOfUnitPriceDigit()).getFormattedPrice(salesDetail.getEnteryPrice()));
                    PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
                    String priceWithDigit = salesDetail.getPriceWithDigit();
                    if (!TextUtils.isEmpty(salesDetail.getCurCodeStr())) {
                        str = "/ " + salesDetail.getCurCodeStr();
                    } else {
                        str = "";
                    }
                    String format = String.format("%s %s", Arrays.copyOf(new Object[]{priceWithDigit, str}, 2));
                    Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                    salesDetail.setPriceWithCurCode(format);
                    FicheStringProp.setDefinition(salesDetail.getPriceWithCurCode());
                    salesDetail.setPrRate(query.getDouble(query.getColumnIndex("RATE")));
                    salesDetail.prCurrType = query.getInt(query.getColumnIndex(getString(R.string.column_curr_nr)));
                    salesDetail.getCurrType().setLogicalRef(salesDetail.getPrCurrType());
                    FicheStringProp.setDefinition(salesDetail.getCurCodeStr());
                    if (this.viewModel.erpType() != ErpType.NETSIS) {
                        salesDetail.getIncludeVat().setSelect(StringUtils.convertIntToBoolean(query.getInt(query.getColumnIndex("INCVAT"))));
                    }
                    if (SalesUtils.isSalesTypeRetailInvoiceOrRetailReturnInvoice(salesDetail.getmSalesType())) {
                        salesDetail.resetSelectedPrice();
                        FicheStringProp.setDefinition(String.valueOf(CalculateUtils.calculatePriceAddVat(query.getDouble(query.getColumnIndex("PRICE")), salesDetail.getVat().getDefinitionDouble(), StringUtils.convertIntToBoolean(query.getInt(query.getColumnIndex("INCVAT"))))));
                    }
                }
                if (query.isClosed()) {
                    query.close();
                    return;
                }
                return;
            }
            if (query != null && !query.isClosed()) {
                query.close();
            }
            salesDetail.getSelectedPrice().setWhich(0);
            Intrinsics.checkNotNull(query);
            if (query.moveToPosition(0)) {
            }
            if (query.isClosed()) {
            }
        } catch (Exception e2) {
            Log.e("MULTI_VARIANT", "setPriceForClonedDetail error", e2);
        }
    }

    private void selectProduct(SalesDetail salesDetail, int i2, boolean z) {
        checkUsedSeriLots(salesDetail, i2);
        Intrinsics.checkNotNull(salesDetail);
        salesDetail.setSalesType(getmSales().getMSalesType());
        ArrayList<SalesDetail> mSalesDetailList = getmSales().getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList);
        mSalesDetailList.set(i2, salesDetail);
        if (!z) {
            salesDetail.calculateFiche(getmSales().isNotUseGattribKdv());
        }
        getmSales().moveGeneralPromotionLineToTheLast();
        SalesDetailLineRecyclerViewAdapter salesDetailLineRecyclerViewAdapter = this.mAdapter;
        ArrayList<SalesDetail> mSalesDetailList2 = getmSales().getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList2);
        salesDetailLineRecyclerViewAdapter.setSalesDetails(mSalesDetailList2);
    }

    private void checkUsedSeriLots(SalesDetail salesDetail, int i2) {
        Intrinsics.checkNotNull(salesDetail);
        if (salesDetail.getTrackType() != TrackType.SERIAL.getType() || getSalesType() == SalesType.ORDER) {
            return;
        }
        ArrayList<Serilot> deleteUsedSeriLots = deleteUsedSeriLots(salesDetail.getSalesSerialLots(), i2);
        salesDetail.setSalesSerialLots(deleteUsedSeriLots);
        salesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(deleteUsedSeriLots, salesDetail.getTrackType()));
        double calculateSerialLotTotals = CalculateUtils.calculateSerialLotTotals(salesDetail.getSalesSerialLots(), salesDetail.getConvFact1(), salesDetail.getConvFact2());
        FicheStringProp amount = salesDetail.getAmount();
        FicheStringProp surplusAmount = salesDetail.getSurplusAmount();
        Intrinsics.checkNotNull(surplusAmount);
        FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(calculateSerialLotTotals - surplusAmount.getDefinitionDouble())));
        StringBuilder sb = this.stringBuilder;
        if (sb == null || String.valueOf(sb).length() <= 0) {
            return;
        }
        Toast.makeText(getContext(), String.valueOf(this.stringBuilder), Toast.LENGTH_LONG).show();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setTotalLayoutWithNotify();
    }

    public void setTotalLayoutWithNotify() {
        setTotalLayout();
        notifyAdapter();
    }

    public void setTotalLayout() {
        getmSales().calculateSalesTotal();
        initUnit();
        SalesDetailLineRecyclerViewAdapter salesDetailLineRecyclerViewAdapter = this.mAdapter;
        ArrayList<SalesDetail> mSalesDetailList = getmSales().getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList);
        salesDetailLineRecyclerViewAdapter.setSalesDetails(mSalesDetailList);
        getmSales().calculateVolumeAndWeightInfo(getSalesFicheUserRights().isLineIntegration());
        TextView textView = this.txtOrderLineTotal;
        Intrinsics.checkNotNull(textView);
        textView.setText(StringUtils.formatDouble(getmSales().getTotal()));
        ErpType erpType = this.viewModel.erpType();
        ErpType erpType2 = ErpType.NETSIS;
        if (erpType == erpType2) {
            TextView textView2 = this.txtOrderLineTotal;
            Intrinsics.checkNotNull(textView2);
            textView2.setText(StringUtils.formatDouble(getmSales().getGrossTotal()));
            TextView textView3 = this.txtOrderLineTotalLabel;
            Intrinsics.checkNotNull(textView3);
            textView3.setText(R.string.str_sales_fiche_gross_total);
        }
        TextView textView4 = this.txtOrderLineDiscount;
        Intrinsics.checkNotNull(textView4);
        textView4.setText(StringUtils.formatDouble(getmSales().getDiscTotal()));
        TextView textView5 = this.txtOrderLineLineVat;
        Intrinsics.checkNotNull(textView5);
        textView5.setText(StringUtils.formatDouble(getmSales().getTotalVat()));
        TextView textView6 = this.txtOrderLineLineNet;
        Intrinsics.checkNotNull(textView6);
        textView6.setText(StringUtils.formatDouble(getmSales().getTotalNet()));
        TextView textView7 = this.txtOrderLineLineCount;
        Intrinsics.checkNotNull(textView7);
        ArrayList<SalesDetail> mSalesDetailList2 = getmSales().getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList2);
        textView7.setText(StringUtils.convertIntToString(mSalesDetailList2.size()));
        TextView textView8 = this.txtOrderLineLineSku;
        Intrinsics.checkNotNull(textView8);
        textView8.setText(StringUtils.convertDoubleToString(Double.valueOf(getmSales().getTotalSku())));
        TextView textView9 = this.txtOrderLineTotalAmount;
        Intrinsics.checkNotNull(textView9);
        textView9.setText(StringUtils.convertDoubleToString(Double.valueOf(getmSales().getTotalAmount())));
        if (this.viewModel.erpType() == erpType2) {
            View view = this.lnOrderLineTotalAmount;
            Intrinsics.checkNotNull(view);
            view.setVisibility(View.GONE);
            List<Double> calculateLineUnitTotals = this.viewModel.calculateLineUnitTotals(getmSales());
            TextView textView10 = this.txtorderLineUnitTotal;
            Intrinsics.checkNotNull(textView10);
            PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
            String format = String.format("%s / %s / %s", Arrays.copyOf(new Object[]{calculateLineUnitTotals.get(0).doubleValue() == 0.0d ? "" : StringUtils.formatDoubleThreeDigits(calculateLineUnitTotals.get(0).doubleValue()), calculateLineUnitTotals.get(1).doubleValue() == 0.0d ? "" : StringUtils.formatDoubleThreeDigits(calculateLineUnitTotals.get(1).doubleValue()), calculateLineUnitTotals.get(2).doubleValue() != 0.0d ? StringUtils.formatDoubleThreeDigits(calculateLineUnitTotals.get(2).doubleValue()) : ""}, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            textView10.setText(format);
            return;
        }
        View view2 = this.lnOrderLineUnitTotal;
        Intrinsics.checkNotNull(view2);
        view2.setVisibility(View.GONE);
    }

    private void initUnit() {
        ArrayList<SalesDetail> mSalesDetailList = getmSales().getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList);
        Iterator<SalesDetail> it = mSalesDetailList.iterator();
        while (it.hasNext()) {
            SalesDetail next = it.next();
            int i2 = -1;
            if ((this.viewModel.erpType() == ErpType.TIGER && next.getUnit().getLogicalRef() != 0 && next.getUnit().getWhich() == -1) || (this.viewModel.erpType() == ErpType.NETSIS && next.getUnit().getWhich() == -1)) {
                ArrayList<ItemUnit> productUnits = this.viewModel.getSqlHelper().getProductUnits(next.getItemRef(), !next.isProduct());
                if (productUnits != null && productUnits.size() > 0) {
                    Iterator<ItemUnit> it2 = productUnits.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            ItemUnit next2 = it2.next();
                            i2++;
                            if (next2.logicalRef == next.getUnit().getLogicalRef()) {
                                next.getUnit().setWhich(i2);
                                next.setNetVolume(next2.netVolume);
                                next.setGrossVolume(next2.grossVolume);
                                next.setNetWeight(next2.netWeight);
                                next.setGrossWeight(next2.grossWeight);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override // com.proje.mobilesales.core.interfaces.UpdateFragmentView
    public void update() {
        setTotalLayout();
    }

    public void notifyAdapter() {
        getSalesActivity().notifyViewPagerDataSetChanged();
    }

    private void convertProductToSalesDetail() {
        int addSalesDetailItems = getmSales().addSalesDetailItems(getConvertProductToSalesDetailList(), getSalesFicheUserRights().isLineIntegration());
        SalesDetailLineRecyclerViewAdapter salesDetailLineRecyclerViewAdapter = this.mAdapter;
        ArrayList<SalesDetail> mSalesDetailList = getmSales().getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList);
        salesDetailLineRecyclerViewAdapter.setSalesDetails(mSalesDetailList);
        this.mAdapter.setmFragment(this);
        this.mAdapter.setVatParams(getmSales().isSaleVatCanBeChange(), getmSales().isSaleVatDefaultChecked());
        boolean priceSetFromBarcode = this.selectedProductList.size() > 0 && this.selectedProductList.get(0).getPriceSetFromBarcode();
        this.selectedProductList.clear();
        getmSales().calculateSalesTotal();
        if (priceSetFromBarcode || !isConnected(getContext()) || SalesUtils.isSalesTypeDemandOrWhTransfer(getmSales().getmSalesType())) {
            return;
        }
        getSalesActivity().startSetProductPrice(addSalesDetailItems);
    }

    private ArrayList<SalesDetail> getConvertProductToSalesDetailList() {
        ArrayList<SalesDetail> arrayList = new ArrayList<>();
        Iterator<Product> it = this.selectedProductList.iterator();
        while (it.hasNext()) {
            Product next = it.next();
            if (next.getVariant() && next.getMSelectedVariants().size() > 0) {
                Iterator<SelectedVariant> it2 = next.getMSelectedVariants().iterator();
                while (it2.hasNext()) {
                    SelectedVariant next2 = it2.next();
                    next.setVariantRef(next2.getVariantRef());
                    next.setVariantCode(next2.getVariantCode());
                    next.setAmount(next2.getAmount());
                    next.setVariantName(next2.getVariantName());
                    next.setPriceRef(next2.getPriceInfo().getPriceRef());
                    next.setPrice(next2.getPriceInfo().getPrice());
                    next.setPriceWithDigits(next2.getPriceInfo().getPriceWithDigits());
                    next.setCPrice(next2.getPriceInfo().getFormattedPrice());
                    next.setIncVat(next2.getPriceInfo().getIncVat());
                    next.setCurCode(next2.getPriceInfo().getCurrCode());
                    next.setCurNr(next2.getPriceInfo().getCurrNr());
                    next.setRate(next2.getPriceInfo().getRate());
                    Intrinsics.checkNotNull(next);
                    arrayList.add(getSalesDetailFromProduct(next));
                }
            } else {
                Intrinsics.checkNotNull(next);
                SalesDetail salesDetailFromProduct = getSalesDetailFromProduct(next);
                int size = salesDetailFromProduct.getSalesSerialLots().size();
                checkUsedSeriLots(salesDetailFromProduct, -1);
                if (size == 0 || salesDetailFromProduct.getTrackType() != TrackType.SERIAL.getType() || salesDetailFromProduct.getSalesSerialLots().size() != 0) {
                    arrayList.add(salesDetailFromProduct);
                }
            }
        }
        return arrayList;
    }

    private void checkPriceHasCurrency(Product product) {
        if (this.viewModel.erpType() != ErpType.NETSIS || product.getCurNr() == 0) {
            return;
        }
        List table = this.viewModel.getBaseErp().getLogoSqlHelper().getTable(Curr.class, "CURRCODE=?", new String[]{product.getCurCode()});
        Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.Curr>");
        if (table.isEmpty()) {
            return;
        }
        product.setRate(((Curr) table.get(0)).getRate());
    }

    private SalesDetail getSalesDetailFromProduct(Product product) {
        List<ItemPrice> tableForItemPriceFromSqlHelper;
        if (product.getPromotion() && !this.viewModel.isPromotionItemPriceEnabled()) {
            product.setPriceRef(0);
            product.setPrice(0.0d);
            product.setPriceWithDigits("");
            product.setCPrice("");
        }
        checkUnitPrice(product);
        checkPriceHasCurrency(product);
        SalesDetail salesDetail = new SalesDetail(getmSales().getMSalesType(), product, getmSales().getDeliveryDate().toString(), getSalesFicheUserRights().isReserve());
        ErpType erpType = this.viewModel.erpType();
        ErpType erpType2 = ErpType.NETSIS;
        if (erpType == erpType2) {
            salesDetail.getReserve().setSelect(getmSales().getReserved().isSelect());
            FicheStringProp.setDefinition(getString(getmSales().getReserved().isSelect() ? R.string.str_yes : R.string.str_no));
        }
        CustomerDiscount customerDiscount = getSalesActivity().getCustomerDiscount();
        Intrinsics.checkNotNull(customerDiscount);
        if (customerDiscount.getType() == 1) {
            CustomerDiscount customerDiscount2 = getSalesActivity().getCustomerDiscount();
            Intrinsics.checkNotNull(customerDiscount2);
            salesDetail.setCustomerDiscRatio(customerDiscount2.getRatio());
        }
        salesDetail.setSalesType(getmSales().getMSalesType());
        if (salesDetail.getWareHouse().getLogicalRef() == -1) {
            if (SalesUtils.isSalesTypeDemand(getSalesType())) {
                FicheRefProp ficheRefProp = new FicheRefProp();
                ficheRefProp.setLogicalRef(getmSales().getSourceWareHouse().getLogicalRef());
                ficheRefProp.setWhich(getmSales().getSourceWareHouse().getWhich());
                FicheStringProp.setDefinition(getmSales().getSourceWareHouse().getDefinition());
                salesDetail.setWareHouse(ficheRefProp);
            } else if (SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(getSalesType())) {
                FicheRefProp ficheRefProp2 = new FicheRefProp();
                ficheRefProp2.setLogicalRef(getmSales().getReturnWareHouse().getLogicalRef());
                ficheRefProp2.setWhich(getmSales().getReturnWareHouse().getWhich());
                FicheStringProp.setDefinition(getmSales().getReturnWareHouse().getDefinition());
                salesDetail.setWareHouse(ficheRefProp2);
            } else {
                FicheRefProp ficheRefProp3 = new FicheRefProp();
                ficheRefProp3.setLogicalRef(getmSales().getWareHouse().getLogicalRef());
                ficheRefProp3.setWhich(getmSales().getWareHouse().getWhich());
                FicheStringProp.setDefinition(getmSales().getWareHouse().getDefinition());
                salesDetail.setWareHouse(ficheRefProp3);
            }
        }
        salesDetail.getIncludeVat().setSelect(getmSales().isSaleVatDefaultChecked() || SalesUtils.isSalesTypeRetailInvoiceOrRetailReturnInvoice(getSalesType()) || (TextUtils.isEmpty(product.getTempPriceWithDigits()) && this.viewModel.getGetUseVatIncForProductsDontHavePriceCard()));
        if (this.viewModel.erpType() != erpType2 && salesDetail.getSelectedPrice().getLogicalRef() >= 0 && (tableForItemPriceFromSqlHelper = this.viewModel.getTableForItemPriceFromSqlHelper(ItemPrice.class, "LOGICALREF=? ", new String[]{String.valueOf(salesDetail.getSelectedPrice().getLogicalRef())})) != null && !tableForItemPriceFromSqlHelper.isEmpty()) {
            salesDetail.getIncludeVat().setSelect(tableForItemPriceFromSqlHelper.get(0).getIncVat() == 1);
        }
        if (this.viewModel.erpType() == erpType2 && !TextUtils.isEmpty(getmSales().getDiscountCard(0).getCode())) {
            salesDetail.getDiscountCard(0).setCode(getmSales().getDiscountCard(0).getCode());
            salesDetail.getDiscountCard(0).setLogicalRef(getmSales().getDiscountCard(0).getLogicalRef());
            salesDetail.getDiscountCard(0).setWhich(getmSales().getDiscountCard(0).getWhich());
            salesDetail.getDiscountCard(0).setGuid(getmSales().getDiscountCard(0).getGuid());
            salesDetail.getDiscountCard(0).setCampaignCode(getmSales().getDiscountCard(0).getCampaignCode());
            salesDetail.getDiscountCard(0).setCampaignLineNo(getmSales().getDiscountCard(0).getCampaignLineNo());
            salesDetail.getDiscountCard(0);
            FicheStringProp.setDefinition(getmSales().getDiscountCard(0).getDefinition());
        }
        if (product.getPaymentRef() == 0) {
            if ((!TextUtils.isEmpty(getmSales().getPayPlan().getCode()) || getmSales().getPayPlan().getLogicalRef() != -1) && (TextUtils.isEmpty(salesDetail.getPayment().getCode()) || salesDetail.getPayment().getLogicalRef() == -1)) {
                FicheDiscountRefProp ficheDiscountRefProp = new FicheDiscountRefProp();
                ficheDiscountRefProp.setCode(getmSales().getPayPlan().getCode());
                ficheDiscountRefProp.setLogicalRef(getmSales().getPayPlan().getLogicalRef());
                ficheDiscountRefProp.setWhich(getmSales().getPayPlan().getWhich());
                FicheStringProp.setDefinition(getmSales().getPayPlan().getDefinition());
                salesDetail.setPayment(ficheDiscountRefProp);
            }
        } else {
            FicheDiscountRefProp ficheDiscountRefProp2 = new FicheDiscountRefProp();
            ficheDiscountRefProp2.setCode(product.getPaymentCode());
            ficheDiscountRefProp2.setLogicalRef(product.getPaymentRef());
            ficheDiscountRefProp2.setWhich(-1);
            FicheStringProp.setDefinition(product.getPaymentDef());
            salesDetail.setPayment(ficheDiscountRefProp2);
        }
        this.viewModel.setDueDateForSalesDetail(salesDetail, getCustomerRef());
        salesDetail.calculateFiche(getmSales().isNotUseGattribKdv());
        return salesDetail;
    }

    private void checkUnitPrice(Product product) {
        List<ItemPrice> tableForItemPriceFromSqlHelper;
        double[] unitConvfact;
        if (product.getPriceRef() == 0 || isConnected(getContext()) || SalesUtils.isSalesTypeDemandOrWhTransfer(getmSales().getmSalesType())) {
            return;
        }
        if ((product.getVariant() && product.getMSelectedVariants().size() == 0) || (tableForItemPriceFromSqlHelper = this.viewModel.getTableForItemPriceFromSqlHelper(ItemPrice.class, "LOGICALREF=? AND (VARIANTCODE = ? OR VARIANTCODE='')", new String[]{String.valueOf(product.getPriceRef()), product.getVariantCode()})) == null || tableForItemPriceFromSqlHelper.isEmpty() || tableForItemPriceFromSqlHelper.get(0).getUnitConvert() == 0 || product.getUnitRef() == tableForItemPriceFromSqlHelper.get(0).getUnitRef()) {
            return;
        }
        ItemPrice itemPrice = tableForItemPriceFromSqlHelper.get(0);
        if (this.viewModel.erpType() == ErpType.NETSIS) {
            unitConvfact = this.viewModel.getSqlHelper().getUnitConvfact(itemPrice.unitCode, product.getCode());
        } else {
            unitConvfact = this.viewModel.getSqlHelper().getUnitConvfact(itemPrice.getUnitRef(), product.getLogicalRef());
        }
        if (unitConvfact == null || unitConvfact.length < 2 || unitConvfact[0] == 0.0d || unitConvfact[1] == 0.0d) {
            return;
        }
        product.setPrice(CalculateUtils.convertUnitPrice(itemPrice.price, product.getConvfact1(), product.getConvfact2(), unitConvfact[0], unitConvfact[1]));
    }

    private ArrayList<Serilot> deleteUsedSeriLots(ArrayList<Serilot> arrayList, int i2) {
        ArrayList<Serilot> allSeriLots = getmSales().getAllSeriLots(i2);
        this.stringBuilder = new StringBuilder();
        int size = allSeriLots.size();
        for (int i3 = 0; i3 < size; i3++) {
            int size2 = arrayList.size() - 1;
            if (size2 >= 0) {
                while (true) {
                    int i4 = size2 - 1;
                    if (Intrinsics.areEqual(allSeriLots.get(i3).code, arrayList.get(size2).code)) {
                        StringBuilder sb = this.stringBuilder;
                        Intrinsics.checkNotNull(sb);
                        sb.append(StringsKt.trimIndent(' ' + ContextUtils.getStringResource(R.string.str_serial_number_used_previously, arrayList.get(size2).code) + ". "));
                        arrayList.remove(size2);
                        break;
                    }
                    if (i4 < 0) {
                        break;
                    }
                    size2 = i4;
                }
            }
        }
        return arrayList;
    }

    private SalesActivityNew getSalesActivity() {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.view.newadd.SalesActivityNew");
        return (SalesActivityNew) requireActivity;
    }

    public Sales getmSales() {
        Sales sales = getSalesActivity().getmSales();
        Intrinsics.checkNotNull(sales);
        return sales;
    }

    public int getCustomerRef() {
        return getSalesActivity().getmCustomerRef();
    }

    public SalesType getSalesType() {
        return getSalesActivity().getSalesType();
    }

    public FicheMode getSalesFicheMode() {
        FicheMode salesFicheMode = getSalesActivity().getSalesFicheMode();
        Intrinsics.checkNotNullExpressionValue(salesFicheMode, "getSalesFicheMode(...)");
        return salesFicheMode;
    }

    public SalesFicheDetailFields getmSalesFicheDetail() {
        return getSalesActivity().getmSalesFicheDetailFields();
    }

    public SalesFicheUserRights getSalesFicheUserRights() {
        return getSalesActivity().getSalesFicheUserRights();
    }

    @Override // com.proje.mobilesales.core.base.BaseListFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putBoolean(STATE_TOTAL_FRAME_OPEN, this.mTotalFrameOpen);
        outState.putInt(STATE_DEF_ORDER_ID_WHICH, this.mWhichForm);
        outState.putInt(STATE_DEF_ORDER_ID, this.mDefOrderId);
        outState.putInt(STATE_CUSTOMER_CURRENCY, this.mCustomerCurrency);
        outState.putString(STATE_DEF_GROUP_CODE, this.mDefGroupCode);
        outState.putInt(STATE_DEF_ORDER_ID_WHICH_GROUP, this.mWhichGroupForm);
        super.onSaveInstanceState(outState);
    }

    @Override // com.proje.mobilesales.core.interfaces.ActionModeDelegate
    public boolean startActionMode(ActionMode.Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (getSalesFicheMode() == FicheMode.ANALYSE) {
            return false;
        }
        if (this.mActionMode != null) {
            return true;
        }
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        Intrinsics.checkNotNull(appCompatActivity);
        this.mActionMode = appCompatActivity.startSupportActionMode(callback);
        return true;
    }

    @Override // com.proje.mobilesales.core.interfaces.ActionModeDelegate
    public boolean isInActionMode() {
        return this.mActionMode != null;
    }

    @Override // com.proje.mobilesales.core.interfaces.ActionModeDelegate
    public void stopActionMode() {
        this.mActionMode = null;
    }

    public void scanFromFragment() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(IntentIntegrator.ONE_D_CODE_TYPES.toString());
        arrayList.add("QR_CODE");
        IntentIntegrator.forSupportFragment(this).setBeepEnabled(false).setDesiredBarcodeFormats(arrayList).setCaptureActivity(BarcodeScannerView.class).addExtra(IntentExtraName.EXTRA_SELECT_TYPE, Boolean.FALSE).addExtra(BarcodeScannerView.EXTRA_CUSTOMER_REF, Integer.valueOf(getCustomerRef())).addExtra(BarcodeScannerView.EXTRA_WAREHOUSE_NR, Integer.valueOf(getmSales().getWareHouse().getLogicalRef())).addExtra(BarcodeScannerView.EXTRA_SALES_TYPE, Integer.valueOf(getmSales().getmSalesType().getmValue())).addExtra(BarcodeScannerView.EXTRA_DEF_ORDER_ID, Integer.valueOf(this.mDefOrderId)).addExtra(BarcodeScannerView.EXTRA_PAYMENT_TRADE_GROUP, getmSales().getTradeGroup().toString()).addExtra(BarcodeScannerView.EXTRA_PAYMENT_REF, Integer.valueOf(getmSales().getPayPlan().getLogicalRef())).addExtra(BarcodeScannerView.EXTRA_DIVISION_NR, Integer.valueOf(getmSales().getBranch().getLogicalRef())).addExtra(BarcodeScannerView.EXTRA_SPECODE, getmSales().getFirstSpeCode().getCode()).initiateScan();
    }

    public SalesDetailLineRecyclerViewAdapter getmAdapter() {
        return this.mAdapter;
    }

    @Override // com.proje.mobilesales.core.interfaces.ItemSurplusDiscountListener
    public void getItemSurplusDiscount(int i2, String customerConditionCode) {
        Intrinsics.checkNotNullParameter(customerConditionCode, "customerConditionCode");
        if (ContextUtils.checkInternetConnection()) {
            ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressSurplusDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder);
            progressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
            this.viewModel.getSurplusDiscountForItem(i2, customerConditionCode, new ProductInfoResponseListener(this));
        }
    }

    /* compiled from: SalesDetailListFragment.kt */
    public static final class ProductInfoResponseListener implements ResponseListener<ArrayList<SurplusDiscount>> {
        private final WeakReference<SalesDetailListFragment> mProductListFragment;

        public ProductInfoResponseListener(SalesDetailListFragment salesDetailListFragment) {
            this.mProductListFragment = new WeakReference<>(salesDetailListFragment);
        }

        @Override // com.proje.mobilesales.core.interfaces.ResponseListener
        public void onResponse(PrintSlipModel arrayList) {
            if (this.mProductListFragment.get() != null) {
                SalesDetailListFragment salesDetailListFragment = this.mProductListFragment.get();
                Intrinsics.checkNotNull(salesDetailListFragment);
                if (salesDetailListFragment.isAttached()) {
                    SalesDetailListFragment salesDetailListFragment2 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(salesDetailListFragment2);
                    ProgressDialogBuilder<?> mProgressSurplusDialogBuilder = salesDetailListFragment2.getMProgressSurplusDialogBuilder();
                    Intrinsics.checkNotNull(mProgressSurplusDialogBuilder);
                    mProgressSurplusDialogBuilder.dismiss();
                    if (arrayList != null && arrayList.size() > 0) {
                        SalesDetailListFragment salesDetailListFragment3 = this.mProductListFragment.get();
                        Intrinsics.checkNotNull(salesDetailListFragment3);
                        salesDetailListFragment3.showSurplusDiscountList(arrayList);
                    } else {
                        SalesDetailListFragment salesDetailListFragment4 = this.mProductListFragment.get();
                        Intrinsics.checkNotNull(salesDetailListFragment4);
                        Toast.makeText(salesDetailListFragment4.getContext(), R.string.str_no_condition_for_product, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

        @Override // com.proje.mobilesales.core.interfaces.ResponseListener
        public void onError(String errorMessage) {
            Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
            if (this.mProductListFragment.get() != null) {
                SalesDetailListFragment salesDetailListFragment = this.mProductListFragment.get();
                Intrinsics.checkNotNull(salesDetailListFragment);
                if (salesDetailListFragment.isAttached()) {
                    SalesDetailListFragment salesDetailListFragment2 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(salesDetailListFragment2);
                    ProgressDialogBuilder<?> mProgressSurplusDialogBuilder = salesDetailListFragment2.getMProgressSurplusDialogBuilder();
                    Intrinsics.checkNotNull(mProgressSurplusDialogBuilder);
                    mProgressSurplusDialogBuilder.dismiss();
                    SalesDetailListFragment salesDetailListFragment3 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(salesDetailListFragment3);
                    Toast.makeText(salesDetailListFragment3.getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void showSurplusDiscountList(List<? extends SurplusDiscount> list) {
        try {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.recyclerviewlist, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setView(inflate);
            RecyclerView recyclerView = inflate.findViewById(R.id.recyclerViewList);
            recyclerView.setLayoutManager(new SnappyLinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);
            SurplusDiscountListAdapter surplusDiscountListAdapter = new SurplusDiscountListAdapter();
            recyclerView.setAdapter(surplusDiscountListAdapter);
            surplusDiscountListAdapter.setSurplusDiscounts(list);
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.detail.SalesDetailListFragmentExternalSyntheticLambda4
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    SalesDetailListFragment.showSurplusDiscountListlambda6(dialogInterface, i2);
                }
            });
            builder.show();
        } catch (Exception e2) {
            Log.e("AA", "showSurplusDiscountList: ", e2);
        }
    }

    public static void showSurplusDiscountListlambda6(DialogInterface dialog, int i2) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    @Override // com.proje.mobilesales.core.interfaces.AlternativeItemForCampaignPromotion
    public void getAlternativeItemForCampaignPromotion(SalesDetail mainPromotionDetail, int i2) {
        Intrinsics.checkNotNullParameter(mainPromotionDetail, "mainPromotionDetail");
        if (ContextUtils.checkInternetConnection()) {
            ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressAlternativePromotionDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder);
            progressDialogBuilder.setMessage(getString(R.string.str_please_wait)).setCancelable(false).show();
            this.viewModel.getAlternativePromotionItems(mainPromotionDetail.getCampaignCode(), mainPromotionDetail.getCampaignLineNo(), new AlternativePromotionItemResponseListener(this, mainPromotionDetail, i2));
        }
    }

    /* compiled from: SalesDetailListFragment.kt */
    public static final class AlternativePromotionItemResponseListener implements ResponseListener<ArrayList<KeyValuePair>> {
        private final WeakReference<SalesDetailListFragment> mProductListFragment;
        private final SalesDetail mainPromotionDetail;
        private final int mainPromotionPosition;

        public AlternativePromotionItemResponseListener(SalesDetailListFragment salesDetailListFragment, SalesDetail mainPromotionDetail, int i2) {
            Intrinsics.checkNotNullParameter(mainPromotionDetail, "mainPromotionDetail");
            this.mainPromotionDetail = mainPromotionDetail;
            this.mainPromotionPosition = i2;
            this.mProductListFragment = new WeakReference<>(salesDetailListFragment);
        }

        @Override // com.proje.mobilesales.core.interfaces.ResponseListener
        public void onResponse(PrintSlipModel arrayList) {
            if (this.mProductListFragment.get() != null) {
                SalesDetailListFragment salesDetailListFragment = this.mProductListFragment.get();
                Intrinsics.checkNotNull(salesDetailListFragment);
                if (salesDetailListFragment.isAttached()) {
                    SalesDetailListFragment salesDetailListFragment2 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(salesDetailListFragment2);
                    salesDetailListFragment2.getAlternativeProductListFromSelected(arrayList, this.mainPromotionDetail, this.mainPromotionPosition, "");
                }
            }
        }

        @Override // com.proje.mobilesales.core.interfaces.ResponseListener
        public void onError(String errorMessage) {
            Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
            if (this.mProductListFragment.get() != null) {
                SalesDetailListFragment salesDetailListFragment = this.mProductListFragment.get();
                Intrinsics.checkNotNull(salesDetailListFragment);
                if (salesDetailListFragment.isAttached()) {
                    SalesDetailListFragment salesDetailListFragment2 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(salesDetailListFragment2);
                    ProgressDialogBuilder<?> mProgressAlternativePromotionDialogBuilder = salesDetailListFragment2.getMProgressAlternativePromotionDialogBuilder();
                    Intrinsics.checkNotNull(mProgressAlternativePromotionDialogBuilder);
                    mProgressAlternativePromotionDialogBuilder.dismiss();
                    SalesDetailListFragment salesDetailListFragment3 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(salesDetailListFragment3);
                    salesDetailListFragment3.getAlternativeProductListFromSelected(null, this.mainPromotionDetail, this.mainPromotionPosition, errorMessage);
                }
            }
        }
    }

    public void getAlternativeProductListFromSelected(ArrayList<KeyValuePair> arrayList, SalesDetail salesDetail, int i2, String str) {
        if (arrayList == null) {
            if (str != null) {
                Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
                return;
            }
            return;
        }
        SalesType salesType = getSalesType();
        int customerRef = getCustomerRef();
        String ficheStringProp = getmSales().getTradeGroup().toString();
        Intrinsics.checkNotNullExpressionValue(ficheStringProp, "toString(...)");
        String alternativeProductList = this.viewModel.getSqlHelper().getAlternativeProductList(getContext(), new AlternativeProductListOptions(salesType, customerRef, ficheStringProp, getmSales().getWareHouse().getLogicalRef(), getmSales().getPayPlan().getLogicalRef(), getmSales().getBranch().getLogicalRef(), this.viewModel.getProductListParameter(getSalesType()), arrayList));
        Intrinsics.checkNotNullExpressionValue(alternativeProductList, "getAlternativeProductList(...)");
        String replace = new Regex("\\s*[\\r\\n]+\\s*").replace(alternativeProductList, "");
        int length = replace.length() - 1;
        int i3 = 0;
        boolean z = false;
        while (i3 <= length) {
            boolean z2 = Intrinsics.compare(replace.charAt(!z ? i3 : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                } else {
                    length--;
                }
            } else if (z2) {
                i3++;
            } else {
                z = true;
            }
        }
        this.viewModel.getProductList(replace.subSequence(i3, length + 1).toString(), 0, new AlternativeProductListResponseListener(this, salesDetail, i2), getmSales().getFirstSpeCode().getCode());
    }

    /* compiled from: SalesDetailListFragment.kt */
        private record AlternativeProductListResponseListener(WeakReference<SalesDetailListFragment> mProductListFragment,
                                                              SalesDetail mainPromotionDetail,
                                                              int mainPromotionPosition) implements ResponseListener<ArrayList<Product>> {
            private AlternativeProductListResponseListener(SalesDetailListFragment mProductListFragment, SalesDetail mainPromotionDetail, int mainPromotionPosition) {
                Intrinsics.checkNotNullParameter(mainPromotionDetail, "mainPromotionDetail");
                this.mainPromotionDetail = mainPromotionDetail;
                this.mainPromotionPosition = mainPromotionPosition;
                this.mProductListFragment = new WeakReference<>(mProductListFragment);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(PrintSlipModel arrayList) {
                if (this.mProductListFragment.get() != null) {
                    SalesDetailListFragment salesDetailListFragment = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(salesDetailListFragment);
                    if (salesDetailListFragment.isAttached()) {
                        SalesDetailListFragment salesDetailListFragment2 = this.mProductListFragment.get();
                        Intrinsics.checkNotNull(salesDetailListFragment2);
                        salesDetailListFragment2.showAlternativePromotionList(arrayList, this.mainPromotionDetail, this.mainPromotionPosition, "");
                    }
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mProductListFragment.get() != null) {
                    SalesDetailListFragment salesDetailListFragment = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(salesDetailListFragment);
                    if (salesDetailListFragment.isAttached()) {
                        Log.d("AA", "onError: " + errorMessage);
                        SalesDetailListFragment salesDetailListFragment2 = this.mProductListFragment.get();
                        Intrinsics.checkNotNull(salesDetailListFragment2);
                        AlertDialogBuilder<?> mAlternativePromotionAlertDialogBuilder = salesDetailListFragment2.getMAlternativePromotionAlertDialogBuilder();
                        Intrinsics.checkNotNull(mAlternativePromotionAlertDialogBuilder);
                        mAlternativePromotionAlertDialogBuilder.dismiss();
                        SalesDetailListFragment salesDetailListFragment3 = this.mProductListFragment.get();
                        Intrinsics.checkNotNull(salesDetailListFragment3);
                        salesDetailListFragment3.showAlternativePromotionList(null, this.mainPromotionDetail, this.mainPromotionPosition, errorMessage);
                    }
                }
            }
        }

    public void showAlternativePromotionList(ArrayList<Product> arrayList, SalesDetail salesDetail, int i2, String str) {
        if (arrayList == null) {
            if (str != null) {
                Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
            }
        } else {
            if (arrayList.size() > 0) {
                showAlternativePromotionDialog(arrayList, salesDetail, i2);
                return;
            }
            AlertDialogBuilder<?> alertDialogBuilder = this.mAlternativePromotionAlertDialogBuilder;
            Intrinsics.checkNotNull(alertDialogBuilder);
            alertDialogBuilder.setMessage(R.string.str_alternative_promotion_not_found).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.detail.SalesDetailListFragmentExternalSyntheticLambda9
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i3) {
                    SalesDetailListFragment.showAlternativePromotionListlambda8(dialogInterface, i3);
                }
            }).create().show();
            AlertDialogBuilder<?> alertDialogBuilder2 = this.mAlternativePromotionAlertDialogBuilder;
            Intrinsics.checkNotNull(alertDialogBuilder2);
            alertDialogBuilder2.getAlertDialog().setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.proje.mobilesales.features.sales.view.detail.SalesDetailListFragmentExternalSyntheticLambda10
                public void SalesDetailListFragmentExternalSyntheticLambda10() {
                }

                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    SalesDetailListFragment.showAlternativePromotionListlambda9(SalesDetailListFragment.this, dialogInterface);
                }
            });
        }
    }

    public static void showAlternativePromotionListlambda8(DialogInterface dialog, int i2) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    public static void showAlternativePromotionListlambda9(SalesDetailListFragment this0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        ProgressDialogBuilder<?> progressDialogBuilder = this0.mProgressAlternativePromotionDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
    }

    private void showAlternativePromotionDialog(ArrayList<Product> arrayList, SalesDetail salesDetail, int i2) {
        try {
            this.alternativePromotionDialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_alternative_promotion, null);
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(this.alternativePromotionDialogView);
            View view = this.alternativePromotionDialogView;
            Intrinsics.checkNotNull(view);
            ((TextView) view.findViewById(R.id.tv_alternative_line_quantity)).setText(StringUtils.convertDoubleToString(Double.valueOf(salesDetail.getAmount().getDefinitionDouble())));
            View view2 = this.alternativePromotionDialogView;
            Intrinsics.checkNotNull(view2);
            ((TextView) view2.findViewById(R.id.tv_alternative_selected_quantity)).setText(StringUtils.convertDoubleToString(Double.valueOf(salesDetail.getAmount().getDefinitionDouble())));
            View view3 = this.alternativePromotionDialogView;
            Intrinsics.checkNotNull(view3);
            RecyclerView recyclerView = view3.findViewById(R.id.recyclerViewList);
            recyclerView.setLayoutManager(new SnappyLinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);
            AlternativePromotionListAdapter alternativePromotionListAdapter = new AlternativePromotionListAdapter();
            recyclerView.setAdapter(alternativePromotionListAdapter);
            Iterator<Product> it = arrayList.iterator();
            while (it.hasNext()) {
                Product next = it.next();
                if (next.getLogicalRef() == salesDetail.getItemRef()) {
                    next.setAmount(salesDetail.getAmount().getDefinitionDouble());
                }
            }
            alternativePromotionListAdapter.setProducts(arrayList, salesDetail.getItemRef(), salesDetail.getAmount().getDefinitionDouble(), this);
            builder.setPositiveButton(R.string.ok, null);
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i3) {
                    SalesDetailListFragment.showAlternativePromotionDialoglambda10(dialogInterface, i3);
                }
            });
            AlertDialog create = builder.create();
            Intrinsics.checkNotNullExpressionValue(create, "create(...)");
            create.setOnShowListener(new DialogInterface.OnShowListener() {
                public final SalesDetail f1 = null;
                public final int f2 = 0;
                public final AlternativePromotionListAdapter f3 = null;

                public void SalesDetailListFragmentExternalSyntheticLambda2(SalesDetail salesDetail2, int i22, AlternativePromotionListAdapter alternativePromotionListAdapter2) {
                    r2 = salesDetail2;
                    r3 = i22;
                    r4 = alternativePromotionListAdapter2;
                }

                public void onShow(DialogInterface dialogInterface) {
                    SalesDetailListFragment.showAlternativePromotionDialoglambda12(SalesDetailListFragment.this, r2, r3, r4, dialogInterface);
                }
            });
            create.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void SalesDetailListFragmentExternalSyntheticLambda3() {
                }
                public void onDismiss(DialogInterface dialogInterface) {
                    SalesDetailListFragment.showAlternativePromotionDialoglambda13(SalesDetailListFragment.this, dialogInterface);
                }
            });
            create.show();
            Window window = create.getWindow();
            Intrinsics.checkNotNull(window);
            window.clearFlags(131080);
            Window window2 = create.getWindow();
            Intrinsics.checkNotNull(window2);
            window2.setSoftInputMode(4);
        } catch (Exception e2) {
            Log.e("AA", "showAlternativePromotionList: ", e2);
        }
    }

    public static void showAlternativePromotionDialoglambda10(DialogInterface dialog, int i2) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    public static void showAlternativePromotionDialoglambda12(SalesDetailListFragment this0, SalesDetail mainPromotionDetail, int i2, AlternativePromotionListAdapter adapter, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(mainPromotionDetail, "mainPromotionDetail");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        ((AlertDialog) dialogInterface).getButton(-1).setOnClickListener(new View.OnClickListener() {
            public final SalesDetail f1 = null;
            public final int f2 = 0;
            public final AlternativePromotionListAdapter f3 = null;
            public final DialogInterface f4 = null;

            public void SalesDetailListFragmentExternalSyntheticLambda0(SalesDetail mainPromotionDetail2, int i22, AlternativePromotionListAdapter adapter2, DialogInterface dialogInterface2) {
                r2 = mainPromotionDetail2;
                r3 = i22;
                r4 = adapter2;
                r5 = dialogInterface2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SalesDetailListFragment.showAlternativePromotionDialoglambda12lambda11(SalesDetailListFragment.this, r2, r3, r4, r5, view);
            }
        });
    }

    public static void showAlternativePromotionDialoglambda12lambda11(SalesDetailListFragment this0, SalesDetail mainPromotionDetail, int i2, AlternativePromotionListAdapter adapter, DialogInterface dialogInterface, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(mainPromotionDetail, "mainPromotionDetail");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        ArrayList<Product> products = adapter.getProducts();
        Intrinsics.checkNotNullExpressionValue(products, "getProducts(...)");
        if (this0.checkTotalAmountAndApplyAlternativePromotionItems(mainPromotionDetail, i2, products)) {
            dialogInterface.dismiss();
            this0.update();
        } else {
            Toast.makeText(this0.getContext(), ContextUtils.getStringResource(R.string.str_selected_products_quantity_total, mainPromotionDetail.getAmount().toString()), Toast.LENGTH_SHORT).show();
        }
    }

    public static void showAlternativePromotionDialoglambda13(SalesDetailListFragment this0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        ProgressDialogBuilder<?> progressDialogBuilder = this0.mProgressAlternativePromotionDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
    }
 
    public void getAlternativeItemAmountChangeListener(List<? extends Product> alternativePromotionItems) {
        Intrinsics.checkNotNullParameter(alternativePromotionItems, "alternativePromotionItems");
        View view = this.alternativePromotionDialogView;
        Intrinsics.checkNotNull(view);
        TextView textView = view.findViewById(R.id.tv_alternative_selected_quantity);
        Iterator<? extends Product> it = alternativePromotionItems.iterator();
        double d2 = 0.0d;
        while (it.hasNext()) {
            d2 += it.next().getAmount();
        }
        textView.setText(StringUtils.convertDoubleToString(Double.valueOf(d2)));
    }

    private boolean checkTotalAmountAndApplyAlternativePromotionItems(SalesDetail salesDetail, int i2, ArrayList<Product> arrayList) {
        ArrayList<Product> arrayList2 = new ArrayList<>();
        Iterator<Product> it = arrayList.iterator();
        double d2 = 0.0d;
        while (it.hasNext()) {
            Product next = it.next();
            d2 += next.getAmount();
            if (next.getAmount() > 0.0d) {
                next.setCampaignCode(salesDetail.getCampaignCode());
                next.setCampaignLineNo(salesDetail.getCampaignLineNo());
                next.setGlobalLineType(salesDetail.getGlobalLineType());
                next.setPromotion(true);
                next.initUnitList(this.viewModel.getSqlHelper().getProductUnits(next.getLogicalRef(), next.getService()));
                next.selectUnitIndexFirst();
                arrayList2.add(next);
            }
        }
        if (salesDetail.getAmount().getDefinitionDouble() != d2) {
            return false;
        }
        this.selectedProductList = arrayList2;
        getmSales().addAlternativePromotionDetailItems(salesDetail, i2, getConvertProductToSalesDetailList());
        this.selectedProductList.clear();
        SalesDetailLineRecyclerViewAdapter salesDetailLineRecyclerViewAdapter = this.mAdapter;
        ArrayList<SalesDetail> mSalesDetailList = getmSales().getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList);
        salesDetailLineRecyclerViewAdapter.setSalesDetails(mSalesDetailList);
        getmSales().calculateSalesTotal();
        return true;
    }

    private record ProductOnlineStockGetListener(
            WeakReference<SalesDetailListFragment> mSalesDetailListFragmentWeakReference) implements ResponseListener<Boolean> {
            private ProductOnlineStockGetListener(SalesDetailListFragment mSalesDetailListFragmentWeakReference) {
                this.mSalesDetailListFragmentWeakReference = new WeakReference<>(mSalesDetailListFragmentWeakReference);
            }

        public void onResponse(PrintSlipModel bool) {
                if (this.mSalesDetailListFragmentWeakReference.get() != null) {
                    SalesDetailListFragment salesDetailListFragment = this.mSalesDetailListFragmentWeakReference.get();
                    Intrinsics.checkNotNull(salesDetailListFragment);
                    if (salesDetailListFragment.isAttached()) {
                        SalesDetailListFragment salesDetailListFragment2 = this.mSalesDetailListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(salesDetailListFragment2);
                        Intrinsics.checkNotNull(bool);
                        salesDetailListFragment2.onOnlineStockGetDone(bool.booleanValue(), "");
                    }
                }
            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mSalesDetailListFragmentWeakReference.get() != null) {
                    SalesDetailListFragment salesDetailListFragment = this.mSalesDetailListFragmentWeakReference.get();
                    Intrinsics.checkNotNull(salesDetailListFragment);
                    if (salesDetailListFragment.isAttached()) {
                        Log.d("OnlineStockGet", "onError: " + errorMessage);
                        SalesDetailListFragment salesDetailListFragment2 = this.mSalesDetailListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(salesDetailListFragment2);
                        salesDetailListFragment2.onOnlineStockGetDone(false, errorMessage);
                    }
                }
            }
        }

    public void onOnlineStockGetDone(boolean z, String str) {
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(getActivity(), str, Toast.LENGTH_LONG).show();
            ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder);
            progressDialogBuilder.dismiss();
        }
        try {
            if (z) {
                try {
                    this.viewModel.getSaveStockLastTransDate();
                    this.viewModel.updateSalesDetailItemStock(getmSales());
                } catch (Exception e2) {
                    Log.e("OnlineStockGet", "Error", e2);
                }
            }
        } finally {
            ProgressDialogBuilder<?> progressDialogBuilder2 = this.mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder2);
            progressDialogBuilder2.dismiss();
            loadDetailsAfterStocksUpdated();
        }
    }

    private void loadDetailsAfterStocksUpdated() {
        SalesDetailLineRecyclerViewAdapter salesDetailLineRecyclerViewAdapter = this.mAdapter;
        ArrayList<SalesDetail> mSalesDetailList = getmSales().getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList);
        salesDetailLineRecyclerViewAdapter.setSalesDetails(mSalesDetailList);
        this.mAdapter.setmFragment(this);
        this.mAdapter.setmFicheMode(getSalesFicheMode());
        this.mAdapter.setVatParams(getmSales().isSaleVatCanBeChange(), getmSales().isSaleVatDefaultChecked());
        this.mAdapter.setItemSurplusDiscountListener(this);
        this.mAdapter.setAlternativeItemForCampaignPromotion(this);
        setImgOrderLineTotalUpDrawable();
    } 
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    } 
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
