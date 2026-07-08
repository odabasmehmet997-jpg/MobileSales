package com.example.privacy_policy_lib;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.privacy_policy_lib.adapter.ContractsAdapter;
import com.example.privacy_policy_lib.core.AgreementTypes;
import com.example.privacy_policy_lib.core.model.ContractItem;
import com.example.privacy_policy_lib.core.model.PrivacyPolicyLibParams;
import com.example.privacy_policy_lib.core.model.PrivacyPolicyState;
import com.example.privacy_policy_lib.core.utils.ContextUtils;
import com.example.privacy_policy_lib.databinding.FragmentContractsBinding;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
 
public final class ContractsFragment extends Fragment implements ContractsAdapter.OnAllCheckboxCheckedListener {
    private FragmentContractsBinding _binding;
    private boolean[] checkboxStates;
    private ArrayList<ContractItem> contractItemList = CollectionsKt.arrayListOf(new ContractItem(null, null, 3, null));
    private ContractsAdapter mAdapter;
    private Function0<Unit> onPrivacyPolicyAccepted;
    private FragmentContractsBinding getBinding() {
        FragmentContractsBinding fragmentContractsBinding = this._binding;
        Intrinsics.checkNotNull(fragmentContractsBinding);
        return fragmentContractsBinding;
    }
    public ArrayList<ContractItem> getContractItemList() {
        return this.contractItemList;
    }
    public void setContractItemList(ArrayList<ContractItem> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.contractItemList = arrayList;
    }
    public Function0<Unit> getOnPrivacyPolicyAccepted() {
        return this.onPrivacyPolicyAccepted;
    }
    public void setOnPrivacyPolicyAccepted(Function0<Unit> function0) {
        this.onPrivacyPolicyAccepted = function0;
    }
    public void onCreate(Bundle bundle) {
        ArrayList<ContractItem> arrayList;
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            PrivacyPolicyLibParams privacyPolicyLibParams = arguments.getParcelable("params");
            if (privacyPolicyLibParams != null) {
                PrivacyPolicyState.setParams(privacyPolicyLibParams);
                List<AgreementTypes> agreementTypes = privacyPolicyLibParams.getAgreementTypes();
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(agreementTypes, 10));
                for (AgreementTypes agreementTypes2 : agreementTypes) {
                    AgreementTypes.Companion companion = AgreementTypes.Companion;
                    Context requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
                    arrayList2.add(new ContractItem(companion.getStringForEnum(agreementTypes2, requireContext), null, 2, null));
                }
                arrayList = new ArrayList<>(arrayList2);
            } else {
                ArrayList<AgreementTypes> arrayListOf = CollectionsKt.arrayListOf(AgreementTypes.GENERALAGREEMENT, AgreementTypes.TERMSOFUSE);
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayListOf, 10));
                for (AgreementTypes agreementTypes3 : arrayListOf) {
                    AgreementTypes.Companion companion2 = AgreementTypes.Companion;
                    Context requireContext2 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext(...)");
                    arrayList3.add(new ContractItem(companion2.getStringForEnum(agreementTypes3, requireContext2), null, 2, null));
                }
                arrayList = new ArrayList<>(arrayList3);
            }
            this.contractItemList = arrayList;
        }
        Context context = getContext();
        if (context != null) {
            ContextUtils.Companion.setmContext(context);
        }
        ContractsAdapter contractsAdapter = new ContractsAdapter(requireActivity());
        contractsAdapter.setOnContractClickedprivacy_policy_lib_release(new Function1<Integer, Unit>(this) {
            @Override
            public Unit invoke(Object p1) {
                return null;
            }

            final ContractsFragment this0 = null;

            public Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public void invoke(int i2) {
                this.this0.requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, PrivacyPolicyDialogFragment.Companion.newInstance(i2)).addToBackStack(null).commit();
            }
        });
        this.mAdapter = contractsAdapter;
        Intrinsics.checkNotNull(contractsAdapter);
        contractsAdapter.addItem(this.contractItemList);
    }
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        this._binding = FragmentContractsBinding.inflate(layoutInflater, viewGroup, false);
        ConstraintLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        OnBackPressedDispatcher onBackPressedDispatcher = requireActivity().getOnBackPressedDispatcher();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");

    }
    public void onActivityCreated(Bundle bundle) {
        int color;
        super.onActivityCreated(bundle);
        RecyclerView recyclerView = requireView().findViewById(R.id.rcw);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(this.mAdapter);
        getBinding().btnRead.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ContractsFragment.onActivityCreatedlambda6(ContractsFragment.this, view);
            }
        });
        int size = this.contractItemList.size();
        boolean[] zArr = new boolean[size];
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            zArr[i3] = false;
        }
        this.checkboxStates = zArr;
        AppCompatButton appCompatButton = getBinding().btnRead;
        boolean[] zArr2 = this.checkboxStates;
        boolean[] zArr3 = null;
        if (zArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("checkboxStates");
            zArr2 = null;
        }
        int length = zArr2.length;
        while (true) {
            if (i2 >= length) {
                color = getResources().getColor(R.color.colorPrimary);
                break;
            } else if (!zArr2[i2]) {
                color = getResources().getColor(R.color.colorDisabled);
                break;
            } else {
                i2++;
            }
        }
        appCompatButton.setBackgroundColor(color);
        ContractsAdapter contractsAdapter = this.mAdapter;
        if (contractsAdapter != null) {
            boolean[] zArr4 = this.checkboxStates;
            if (zArr4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("checkboxStates");
            } else {
                zArr3 = zArr4;
            }
            contractsAdapter.updateCheckboxStates(zArr3);
        }
        getParentFragmentManager().setFragmentResultListener(PrivacyPolicyState.PRIVACY_POLICY, getViewLifecycleOwner(), new FragmentResultListener() { // from class: com.example.privacy_policy_lib.ContractsFragmentExternalSyntheticLambda1
            public void onFragmentResult(String str, Bundle bundle2) {
                ContractsFragment.onActivityCreatedlambda9(ContractsFragment.this, str, bundle2);
            }
        });
    }
    public static void onActivityCreatedlambda6(ContractsFragment contractsFragment, View view) {
        Intrinsics.checkNotNullParameter(contractsFragment, "this0");
        boolean[] zArr = contractsFragment.checkboxStates;
        if (zArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("checkboxStates");
            zArr = null;
        }
        for (boolean z : zArr) {
            if (!z) {
                return;
            }
        }
        Function0<Unit> function0 = contractsFragment.onPrivacyPolicyAccepted;
        if (function0 != null) {
            function0.invoke();
        }
        contractsFragment.getParentFragmentManager().popBackStack();
    }
    public static void onActivityCreatedlambda9(ContractsFragment contractsFragment, String str, Bundle bundle) {
        int color;
        Intrinsics.checkNotNullParameter(contractsFragment, "this0");
        Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        int i2 = bundle.getInt(PrivacyPolicyState.POSITION);
        boolean z = bundle.getBoolean(PrivacyPolicyState.IS_APPROVED);
        boolean[] zArr = contractsFragment.checkboxStates;
        boolean[] zArr2 = null;
        if (zArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("checkboxStates");
            zArr = null;
        }
        zArr[i2] = z;
        ContractsAdapter contractsAdapter = contractsFragment.mAdapter;
        if (contractsAdapter != null) {
            boolean[] zArr3 = contractsFragment.checkboxStates;
            if (zArr3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("checkboxStates");
                zArr3 = null;
            }
            contractsAdapter.updateCheckboxStates(zArr3);
        }
        AppCompatButton appCompatButton = contractsFragment.getBinding().btnRead;
        boolean[] zArr4 = contractsFragment.checkboxStates;
        if (zArr4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("checkboxStates");
        } else {
            zArr2 = zArr4;
        }
        int length = zArr2.length;
        int i3 = 0;
        while (true) {
            if (i3 >= length) {
                color = contractsFragment.getResources().getColor(R.color.colorPrimary);
                break;
            } else if (!zArr2[i3]) {
                color = contractsFragment.getResources().getColor(R.color.colorDisabled);
                break;
            } else {
                i3++;
            }
        }
        appCompatButton.setBackgroundColor(color);
    }
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }
    public void onAllCheckboxChecked(boolean z) {
        int i2;
        AppCompatButton appCompatButton = getBinding().btnRead;
        if (z) {
            i2 = getResources().getColor(R.color.colorPrimary);
        } else {
            i2 = getResources().getColor(R.color.colorDisabled);
        }
        appCompatButton.setBackgroundColor(i2);
    }
}
