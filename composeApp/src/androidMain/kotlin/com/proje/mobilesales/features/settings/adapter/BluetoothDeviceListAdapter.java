package com.proje.mobilesales.features.settings.adapter;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.proje.mobilesales.R;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
 
public final class BluetoothDeviceListAdapter extends BaseAdapter {
    private List<BluetoothDevice> mData;
    private final LayoutInflater mInflater;
    private final String mPair;
    private final String mUnPair;
    public long getItemId(int r1) {
        return r1;
    }
    public BluetoothDeviceListAdapter(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        Intrinsics.checkNotNullExpressionValue(layoutInflaterFrom, "from(...)");
        this.mInflater = layoutInflaterFrom;
        String string = context.getString(R.string.str_pair);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this.mPair = string;
        String string2 = context.getString(R.string.str_unpair);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        this.mUnPair = string2;
    }
    public void setData(List<BluetoothDevice> list) {
        if (null == list) {
            List<BluetoothDevice> list2 = this.mData;
            Intrinsics.checkNotNull(list2);
            list2.clear();
        } else {
            this.mData = list;
        }
        notifyDataSetChanged();
    } 
    public int getCount() {
        List<BluetoothDevice> list = this.mData;
        if (null == list) {
            return 0;
        }
        Intrinsics.checkNotNull(list);
        return list.size();
    } 
    public BluetoothDevice getItem(int r1) {
        List<BluetoothDevice> list = this.mData;
        Intrinsics.checkNotNull(list);
        return list.get(r1);
    }
    @SuppressLint("MissingPermission")
    public View getView(final int r4, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (null == view) {
            view = this.mInflater.inflate(R.layout.list_item_device, parent, false);
            viewHolder = new ViewHolder();
            View viewFindViewById = view.findViewById(R.id.btn_pair);
            Intrinsics.checkNotNull(viewFindViewById, "null cannot be cast to non-null type android.widget.Button");
            viewHolder.setPairBtn((Button) viewFindViewById);
            View viewFindViewById2 = view.findViewById(R.id.tv_name);
            Intrinsics.checkNotNull(viewFindViewById2, "null cannot be cast to non-null type android.widget.TextView");
            viewHolder.setNameTv((TextView) viewFindViewById2);
            View viewFindViewById3 = view.findViewById(R.id.tv_address);
            Intrinsics.checkNotNull(viewFindViewById3, "null cannot be cast to non-null type android.widget.TextView");
            viewHolder.setAddressTv((TextView) viewFindViewById3);
            view.setTag(viewHolder);
        } else {
            Object tag = view.getTag();
            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type com.proje.mobilesales.features.settings.adapter.BluetoothDeviceListAdapter.ViewHolder");
            viewHolder = (ViewHolder) tag;
        }
        BluetoothDevice item = getItem(r4);
        TextView nameTv = viewHolder.getNameTv();
        Intrinsics.checkNotNull(nameTv);
        nameTv.setText(item.getName());
        TextView addressTv = viewHolder.getAddressTv();
        Intrinsics.checkNotNull(addressTv);
        addressTv.setText(item.getAddress());
        Button pairBtn = viewHolder.getPairBtn();
        Intrinsics.checkNotNull(pairBtn);
        pairBtn.setText(12 == item.getBondState() ? this.mUnPair : this.mPair);
        Button pairBtn2 = viewHolder.getPairBtn();
        Intrinsics.checkNotNull(pairBtn2);
        pairBtn2.setOnClickListener(new View.OnClickListener() {  
            public void onClick(View view2) {
                getViewlambda0(this);
            }
        });
        Intrinsics.checkNotNull(view);
        return view;
    } 
    public void getViewlambda0(View.OnClickListener this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");

    }
    public static final class ViewHolder {
        private TextView addressTv;
        private TextView nameTv;
        private Button pairBtn;

        public Button getPairBtn() {
            return this.pairBtn;
        }

        public void setPairBtn(Button button) {
            this.pairBtn = button;
        }

        public TextView getNameTv() {
            return this.nameTv;
        }

        public void setNameTv(TextView textView) {
            this.nameTv = textView;
        }

        public TextView getAddressTv() {
            return this.addressTv;
        }

        public void setAddressTv(TextView textView) {
            this.addressTv = textView;
        }
    }
}
