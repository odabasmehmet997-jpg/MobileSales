package com.proje.mobilesales.features.adapter;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.sql.ISqlManager;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.widget.TintableTextView;
import com.proje.mobilesales.features.dbmodel.ShipAddress;
import com.proje.mobilesales.features.gpsinfo.view.activity.GpsInfoActivity;

import static com.proje.mobilesales.core.utils.AppUtils.createLayoutInflater;

public class ShipAddressRecyclerViewAdapter extends RecyclerView.Adapter<ItemViewHolder> implements IListRecyclerView {
    private String loadingText;
    private Activity mActivity;
    private int mCardBackgroundColorResId;
    private int mCardElevation;
    private int mCardHighlightColorResId;
    private int mCardRadius;
    private boolean mCardViewEnabled = true;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private RecyclerView mRecyclerView;
    private boolean mSalesSelect;
    private int mSecondaryTextColorResId;
    private ISqlManager.ShipAddressCursor mShipAddressCursor;
    private int mTertiaryTextColorResId;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        Context context = recyclerView.getContext();
        this.mContext = context;
        this.mRecyclerView = recyclerView;
        this.loadingText = context.getString(R.string.loading_text);
        this.mLayoutInflater = createLayoutInflater(this.mContext);
        this.mCardElevation = this.mContext.getResources().getDimensionPixelSize(R.dimen.cardview_default_elevation);
        this.mCardRadius = this.mContext.getResources().getDimensionPixelSize(R.dimen.cardview_default_radius);
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.textColorTertiary, R.attr.textColorSecondary, R.attr.colorCardBackground, R.attr.colorCardHighlight});
        this.mTertiaryTextColorResId = obtainStyledAttributes.getInt(0, 0);
        this.mSecondaryTextColorResId = obtainStyledAttributes.getInt(1, 0);
        this.mCardBackgroundColorResId = obtainStyledAttributes.getInt(2, 0);
        this.mCardHighlightColorResId = obtainStyledAttributes.getInt(3, 0);
        obtainStyledAttributes.recycle();
    }
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mRecyclerView = null;
        this.mContext = null;
    }

    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new ItemViewHolder(this.mLayoutInflater.inflate(R.layout.item_ship_address, viewGroup, false));
    }
    public void onBindViewHolder(@NonNull com.proje.mobilesales.features.adapter.ItemViewHolder holder, int position) {

    }
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i2) {
        final ShipAddress shipAddress = getShipAddress(i2);
        if (this.mCardViewEnabled) {
            itemViewHolder.mCardView.setCardElevation(this.mCardElevation);
            itemViewHolder.mCardView.setRadius(this.mCardRadius);
            itemViewHolder.mCardView.setUseCompatPadding(true);
        } else {
            itemViewHolder.mCardView.setCardElevation(0.0f);
            itemViewHolder.mCardView.setRadius(0.0f);
            itemViewHolder.mCardView.setUseCompatPadding(false);
        }
        clear(itemViewHolder);
        itemViewHolder.mTintTxtShip.setText(shipAddress.toString());
        if (this.mSalesSelect) {
            itemViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {  
                public void onClick(View view) {
                    ShipAddressRecyclerViewAdapter.this.lambdaonBindViewHolder0(shipAddress, view);
                }
            });
        } else {
            if (ErpCreator.getInstance().getmBaseErp().getErpType() == ErpType.NETSIS || ErpCreator.getInstance().getmBaseErp().getUser().getPanelVersion() < 18500) {
                return;
            }
            itemViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {  
                public void onClick(View view) {
                    ShipAddressRecyclerViewAdapter.this.lambdaonBindViewHolder1(shipAddress, view);
                }
            });
        }
    } 
    public void lambdaonBindViewHolder0(ShipAddress shipAddress, View view) {
        finishActivitySales(shipAddress);
    }
  
    public com.proje.mobilesales.features.adapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return new ItemViewHolder(this.mLayoutInflater.inflate(R.layout.item_ship_address, viewGroup, false));
    }
        
    public void lambdaonBindViewHolder1(ShipAddress shipAddress, View view) {
        this.mContext.startActivity(new Intent(this.mContext, GpsInfoActivity.class).putExtra(GpsInfoActivity.EXTRA_CUSTOMER_REF, shipAddress.getClRef()).putExtra(GpsInfoActivity.EXTRA_SHIPPING_REF, shipAddress.getLogicalRef()).putExtra(GpsInfoActivity.EXTRA_SHIPPING_CODE, shipAddress.getCode()).putExtra(GpsInfoActivity.EXTRA_SHIPPING_INFO, shipAddress.toString()));
    }

    private void finishActivitySales(ShipAddress shipAddress) {
        Intent intent = new Intent();
        intent.putExtra(IntentExtraName.EXTRA_REF, shipAddress.getLogicalRef());
        intent.putExtra(IntentExtraName.EXTRA_CODE, shipAddress.getCode());
        intent.putExtra(IntentExtraName.EXTRA_DEFINITION, shipAddress.toString());
        this.mActivity.setResult(-1, intent);
        this.mActivity.finish();
    }

    public boolean isAttached() {
        return this.mContext != null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ISqlManager.ShipAddressCursor shipAddressCursor = this.mShipAddressCursor;
        if (shipAddressCursor == null) {
            return 0;
        }
        return shipAddressCursor.getCount();
    }

    private void clear(ItemViewHolder itemViewHolder) {
        itemViewHolder.mTintTxtShip.setText(this.loadingText);
    }

    private ShipAddress getShipAddress(int i2) {
        ISqlManager.ShipAddressCursor shipAddressCursor = this.mShipAddressCursor;
        if (shipAddressCursor == null || !shipAddressCursor.moveToPosition(i2)) {
            return null;
        }
        return this.mShipAddressCursor.getShipAddress();
    }

    public void setmShipAddressCursor(ISqlManager.ShipAddressCursor shipAddressCursor) {
        this.mShipAddressCursor = shipAddressCursor;
        if (shipAddressCursor == null) {
            notifyDataSetChanged();
        } else {
            notifyDataSetChanged();
        }
    }

    @Override // com.proje.mobilesales.features.adapter.IListRecyclerView
    public boolean isCardViewEnabled() {
        return this.mCardViewEnabled;
    }

    @Override // com.proje.mobilesales.features.adapter.IListRecyclerView
    public void setCardViewEnabled(boolean z) {
        this.mCardViewEnabled = z;
        notifyDataSetChanged();
    }

    @Override // com.proje.mobilesales.features.adapter.IListRecyclerView
    public void restoreState(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.mCardViewEnabled = bundle.getBoolean(IListRecyclerView.STATE_CARD_VIEW_ENABLED, true);
    }

    @Override // com.proje.mobilesales.features.adapter.IListRecyclerView
    public Bundle saveState() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(IListRecyclerView.STATE_CARD_VIEW_ENABLED, this.mCardViewEnabled);
        return bundle;
    }

    public void setmSalesSelect(boolean z) {
        this.mSalesSelect = z;
    }

    public void setmActivity(Activity activity) {
        this.mActivity = activity;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        final CardView mCardView;
        final ImageButton mImgLocationShip;
        final TintableTextView mTintTxtShip;

        public ItemViewHolder(View view) {
            super(view);
            this.mCardView = (CardView) view;
            this.mImgLocationShip = view.findViewById(R.id.img_customer_ship_address);
            this.mTintTxtShip = view.findViewById(R.id.txt_customer_ship_address);
        }
    }
}
