package com.example.privacy_policy_lib.adapter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.privacy_policy_lib.R;
import com.example.privacy_policy_lib.core.model.ContractItem;
import com.example.privacy_policy_lib.core.utils.ContextUtils;
import com.example.privacy_policy_lib.databinding.ContractItemBinding;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ContractsAdapter extends RecyclerView.Adapter<ContractsAdapter.ViewHolder> {
    private boolean[] checkboxStates;
    private final List<ContractItem> contractsList;
    private FragmentActivity mContext;
    private final LayoutInflater mLayoutInflater;
    private Function1<? super Integer, Unit> onContractClicked;
    private int r5;

    public interface OnAllCheckboxCheckedListener {
        void onAllCheckboxChecked(boolean z);
    }

    public ContractsAdapter() {
        this(null, 1,null);
    }

    public   ContractsAdapter(FragmentActivity fragmentActivity, int r2, DefaultConstructorMarker defaultConstructorMarker) {
        this((r2 & 1) != 0 ? null : fragmentActivity);
    }

    public FragmentActivity getMContext() {
        return this.mContext;
    }

    public void setMContext(FragmentActivity fragmentActivity) {
        this.mContext = fragmentActivity;
    }

    public ContractsAdapter(FragmentActivity fragmentActivity) {
        this.mContext = fragmentActivity;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(fragmentActivity);
        Intrinsics.checkNotNullExpressionValue(layoutInflaterFrom, "from(...)");
        this.mLayoutInflater = layoutInflaterFrom;
        this.contractsList = new ArrayList();
        this.checkboxStates = new boolean[0];
        this.onContractClicked = new Function1<Integer, Unit>() {
            @Override
            public Unit invoke(Object p1) {
                return null;
            }

            public void invoke(int r1) {
            }
            public  Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }
        };
    }

    public Function1<Integer, Unit> getOnContractClickedprivacy_policy_lib_release() {
        return (Function1<Integer, Unit>) this.onContractClicked;
    }

    public void setOnContractClickedprivacy_policy_lib_release(Function1<? super Integer, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.onContractClicked = function1;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        this.mContext = (FragmentActivity) recyclerView.getContext();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int r3) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ContractItemBinding contractItemBindingInflate = ContractItemBinding.inflate(this.mLayoutInflater, parent, false);
        Intrinsics.checkNotNullExpressionValue(contractItemBindingInflate, "inflate(...)");
        return new ViewHolder(this, contractItemBindingInflate);
    }

    public int getItemCount() {
        return this.contractsList.size();
    }

    public void onBindViewHolder(ViewHolder holder) {
        onBindViewHolder(holder, 0);
    }

    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int r5) {
        this.r5 = r5;
        Intrinsics.checkNotNullParameter(holder, "holder");
        ContractItem contractItem = this.contractsList.get(r5);
        holder.getChkBoxContract().setChecked(this.checkboxStates[r5]);
        holder.getChkBoxContract().setClickable(false);
        holder.bind(contractItem, new Function0<Unit>() {  
            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            } 
            public void invoke2() {
                ContractsAdapter.this.getOnContractClickedprivacy_policy_lib_release().invoke(Integer.valueOf(r5));
            }
        });
    }

    public void addItem(ArrayList<ContractItem> arrayList) {
        if (arrayList != null) {
            this.contractsList.clear();
            this.contractsList.addAll(arrayList);
            int size = arrayList.size();
            boolean[] zArr = new boolean[size];
            for (int r2 = 0; r2 < size; r2++) {
                zArr[r2] = false;
            }
            this.checkboxStates = zArr;
            notifyDataSetChanged();
        }
    }

    public void updateCheckboxStates(boolean[] states) {
        Intrinsics.checkNotNullParameter(states, "states");
        int length = states.length;
        for (int r1 = 0; r1 < length; r1++) {
            boolean[] zArr = this.checkboxStates;
            boolean z = zArr[r1];
            boolean z2 = states[r1];
            if (z != z2) {
                zArr[r1] = z2;
                notifyItemChanged(r1);
            }
        }
    }
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox chkBoxContract;
        private static LinearLayout lnContract = null;
        final   ContractsAdapter this0;
        private final TextView txtContract;

        public ViewHolder(ContractsAdapter contractsAdapter, ContractItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this0 = contractsAdapter;
            TextView txtContract = binding.txtContract;
            Intrinsics.checkNotNullExpressionValue(txtContract, "txtContract");
            this.txtContract = txtContract;
            CheckBox chkContract = binding.chkContract;
            Intrinsics.checkNotNullExpressionValue(chkContract, "chkContract");
            this.chkBoxContract = chkContract;
            LinearLayout lnContract = binding.lnContract;
            Intrinsics.checkNotNullExpressionValue(lnContract, "lnContract");
            ViewHolder.lnContract = lnContract;
        }

        public CheckBox getChkBoxContract() {
            return this.chkBoxContract;
        }

        public void bind(ContractItem contractItem, final Function0<Unit> onContractClicked) {
            Intrinsics.checkNotNullParameter(contractItem, "contractItem");
            Intrinsics.checkNotNullParameter(onContractClicked, "onContractClicked");
            String contractItemText = contractItem.getContractItemText();
            SpannableString spannableString = new SpannableString(contractItemText + " " + ContextUtils.Companion.getStringResource( R.string.str_approve));
            spannableString.setSpan(new ForegroundColorSpan(-16776961), 0, contractItemText.length(), 33);
            spannableString.setSpan(new UnderlineSpan(), 0, contractItemText.length(), 33);
            this.txtContract.setText(spannableString);
            this.chkBoxContract.setClickable(false);
            this.this0.updateContractState(this.chkBoxContract.isChecked(), lnContract, onContractClicked);
            CheckBox checkBox = this.chkBoxContract;
            final ContractsAdapter contractsAdapter = this.this0;
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {  
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    ViewHolder.bindlambda0(contractsAdapter, this, onContractClicked, compoundButton, z);
                }
            });
        }

        public static void bindlambda0(ContractsAdapter this0, CompoundButton.OnCheckedChangeListener this1, Function0 onContractClicked, CompoundButton compoundButton, boolean z) {
            Intrinsics.checkNotNullParameter(this0, "this0");
            Intrinsics.checkNotNullParameter(this1, "this1");
            Intrinsics.checkNotNullParameter(onContractClicked, "onContractClicked");
            this0.updateContractState(z, lnContract, onContractClicked);
        }
    }

    public void updateContractState(boolean z, final LinearLayout linearLayout, final Function0<Unit> function0) {
        if (!z) {
            linearLayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ContractsAdapter.updateContractStatelambda1(linearLayout, function0, view);
                }
            });
        } else {
            linearLayout.setOnClickListener(null);
        }
    }
    public static void updateContractStatelambda1(final LinearLayout lnContract, Function0 onContractClicked, View view) {
        Intrinsics.checkNotNullParameter(lnContract, "lnContract");
        Intrinsics.checkNotNullParameter(onContractClicked, "onContractClicked");
        lnContract.setEnabled(false);
        onContractClicked.invoke();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                ContractsAdapter.updateContractStatelambda1lambda0(lnContract);
            }
        }, 2000L);
    }
    public static void updateContractStatelambda1lambda0(LinearLayout lnContract) {
        Intrinsics.checkNotNullParameter(lnContract, "lnContract");
        lnContract.setEnabled(true);
    }
}
