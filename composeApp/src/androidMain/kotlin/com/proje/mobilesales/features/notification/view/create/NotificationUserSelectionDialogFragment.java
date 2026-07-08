package com.proje.mobilesales.features.notification.view.create;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.TooltipCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.proje.mobilesales.C2682R;
import com.proje.mobilesales.core.extensions.ViewExtensions;
import com.proje.mobilesales.core.utils.UiState;
import com.proje.mobilesales.databinding.FragmentNotificationUserSelectionDialogBinding;
import com.proje.mobilesales.databinding.FragmentNotificationUserSelectionDialogItemBinding;
import com.proje.mobilesales.features.notification.model.NotificationUserSelectionModel;
import com.proje.mobilesales.features.notification.repository.NotificationCreateRepository;
import com.proje.mobilesales.features.notification.view.list.NotificationFilterFragmentKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import kotlin.Function;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationUserSelectionDialogFragment.kt */

public final class NotificationUserSelectionDialogFragment extends BottomSheetDialogFragment {
    public static final Companion Companion = new Companion(null);
    private FragmentNotificationUserSelectionDialogBinding _binding;
    private ArrayList<NotificationUserSelectionModel> initialSelectedList;
    private INotificationUserSelectionListener notificationUserSelectionListener;
    private ArrayList<NotificationUserSelectionModel> selectedUsers;
    private final String TAG = "NotificationUserSelectionDialogFragment";
    private final NotificationCreateRepository repository = new NotificationCreateRepository();
    private final NotificationUserSelectionViewModel viewModel = new NotificationUserSelectionViewModel(this.repository);
    private List<NotificationUserSelectionModel> userList = CollectionsKt.emptyList();

    /* compiled from: NotificationUserSelectionDialogFragment.kt */
    
    public interface INotificationUserSelectionListener {
        void selectionCompleted(ArrayList<NotificationUserSelectionModel> arrayList);
    }

    private FragmentNotificationUserSelectionDialogBinding getBinding() {
        FragmentNotificationUserSelectionDialogBinding fragmentNotificationUserSelectionDialogBinding = this._binding;
        Intrinsics.checkNotNull(fragmentNotificationUserSelectionDialogBinding);
        return fragmentNotificationUserSelectionDialogBinding;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        this._binding = FragmentNotificationUserSelectionDialogBinding.inflate(layoutInflater, viewGroup, false);
        ConstraintLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        this.notificationUserSelectionListener = (INotificationUserSelectionListener) context;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        ViewParent parent = requireView().getParent();
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.widget.FrameLayout");
        ViewParent parent2 = requireView().getParent();
        Intrinsics.checkNotNull(parent2, "null cannot be cast to non-null type android.view.View");
        BottomSheetBehavior from = BottomSheetBehavior.from((View) parent2);
        Intrinsics.checkNotNullExpressionValue(from, "from(...)");
        from.setState(3);
        from.setDraggable(false);
        from.setSkipCollapsed(true);
        from.setHideable(false);
        Object systemService = requireActivity().getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        ViewExtensions.setBottomSheetHeight((FrameLayout) parent, from, 0.8d, (WindowManager) systemService);
        this.viewModel.notificationUsersUiState().observe(this, new Object(new Function1<UiState<? extends List<? extends NotificationUserSelectionModel>>, Unit>(this) { // from class: com.proje.mobilesales.features.notification.view.create.NotificationUserSelectionDialogFragmentonStart1
            final NotificationUserSelectionDialogFragment this0;

            
            {
                this.this0 = r1;
            }

            
            
            
            public Unit invoke(UiState<? extends List<? extends NotificationUserSelectionModel>> uiState) {
                invoke((UiState<? extends List<NotificationUserSelectionModel>>) uiState);
                return Unit.INSTANCE;
            }

            public void invoke(UiState<? extends List<NotificationUserSelectionModel>> uiState) {
                if (uiState != null) {
                    this.this0.render(uiState, new Function1<List<? extends NotificationUserSelectionModel>, Unit>(this.this0) { // from class: com.proje.mobilesales.features.notification.view.create.NotificationUserSelectionDialogFragmentonStart1.1
                        
                        
                        
                        public Unit invoke(List<? extends NotificationUserSelectionModel> list) {
                            invoke((List<NotificationUserSelectionModel>) list);
                            return Unit.INSTANCE;
                        }

                        public void invoke(List<NotificationUserSelectionModel> list) {
                            Intrinsics.checkNotNullParameter(list, "p0");
                            ((NotificationUserSelectionDialogFragment) this.receiver).onGotUsers(list);
                        }
                    });
                }
            }
        }) { // from class: com.proje.mobilesales.features.notification.view.create.NotificationUserSelectionDialogFragmentsamandroidx_lifecycle_Observer0
            private final Function1 function;

            
            {
                Intrinsics.checkNotNullParameter(r2, "function");
                this.function = r2;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof Observer) || !(obj instanceof FunctionAdapter)) {
                    return false;
                }
                return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
            }

            
            public Function<?> getFunctionDelegate() {
                return this.function;
            }

            public int hashCode() {
                return getFunctionDelegate().hashCode();
            }

            
            public void onChanged(Object obj) {
                this.function.invoke(obj);
            }
        });
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        ArrayList<NotificationUserSelectionModel> arrayList;
        ArrayList<NotificationUserSelectionModel> arrayList2;
        super.onCreate(bundle);
        if (bundle != null) {
            arrayList = bundle.getParcelableArrayList("NOTIFICATION_SELECTED_USERS");
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
        } else {
            Bundle arguments = getArguments();
            arrayList = arguments != null ? arguments.getParcelableArrayList("NOTIFICATION_SELECTED_USERS") : null;
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
        }
        this.selectedUsers = arrayList;
        if (bundle != null) {
            arrayList2 = bundle.getParcelableArrayList("NOTIFICATION_SELECTED_USERS");
            if (arrayList2 == null) {
                arrayList2 = new ArrayList<>();
            }
        } else {
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
            for (NotificationUserSelectionModel notificationUserSelectionModel : arrayList) {
                arrayList3.add(NotificationUserSelectionModel.copydefault(notificationUserSelectionModel, 0, null, null, null, false, 31, null));
            }
            arrayList2 = new ArrayList<>(arrayList3);
        }
        this.initialSelectedList = arrayList2;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        this.viewModel.getUsersConnectedToMe(String.valueOf(getBinding().edtSearch.getText()));
        TooltipCompat.setTooltipText(getBinding().btnCancel, getString(R.string.str_cancel));
        TooltipCompat.setTooltipText(getBinding().btnSelectAll, getString(R.string.str_select_all));
        TooltipCompat.setTooltipText(getBinding().btnDone, getString(R.string.str_okey));
        TooltipCompat.setTooltipText(getBinding().btnUnSelectAll, getString(R.string.str_remove_all));
        getBinding().edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.proje.mobilesales.features.notification.view.create.NotificationUserSelectionDialogFragmentExternalSyntheticLambda2
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return NotificationUserSelectionDialogFragment.onViewCreatedlambda1(NotificationUserSelectionDialogFragment.this, textView, i, keyEvent);
            }
        });
        getBinding().imgBtnSearch.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.notification.view.create.NotificationUserSelectionDialogFragmentExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NotificationUserSelectionDialogFragment.onViewCreatedlambda2(NotificationUserSelectionDialogFragment.this, view2);
            }
        });
        getBinding().imgBtnClearSearch.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.notification.view.create.NotificationUserSelectionDialogFragmentExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NotificationUserSelectionDialogFragment.onViewCreatedlambda3(NotificationUserSelectionDialogFragment.this, view2);
            }
        });
        getBinding().btnCancel.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.notification.view.create.NotificationUserSelectionDialogFragmentExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NotificationUserSelectionDialogFragment.onViewCreatedlambda4(NotificationUserSelectionDialogFragment.this, view2);
            }
        });
        getBinding().btnDone.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.notification.view.create.NotificationUserSelectionDialogFragmentExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NotificationUserSelectionDialogFragment.onViewCreatedlambda5(NotificationUserSelectionDialogFragment.this, view2);
            }
        });
        getBinding().btnSelectAll.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.notification.view.create.NotificationUserSelectionDialogFragmentExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NotificationUserSelectionDialogFragment.onViewCreatedlambda8(NotificationUserSelectionDialogFragment.this, view2);
            }
        });
        getBinding().btnUnSelectAll.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.notification.view.create.NotificationUserSelectionDialogFragmentExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NotificationUserSelectionDialogFragment.onViewCreatedlambda12(NotificationUserSelectionDialogFragment.this, view2);
            }
        });
        getBinding().list.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), 1);
        Drawable drawable = AppCompatResources.getDrawable(requireContext(), R.C2684drawable.divider_item);
        if (drawable != null) {
            dividerItemDecoration.setDrawable(drawable);
        }
        getBinding().list.addItemDecoration(dividerItemDecoration);
    }

    
    public static boolean onViewCreatedlambda1(NotificationUserSelectionDialogFragment notificationUserSelectionDialogFragment, TextView textView, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(notificationUserSelectionDialogFragment, "this0");
        if (i != 3 && i != 6 && (keyEvent.getAction() != 0 || keyEvent.getKeyCode() != 66)) {
            return false;
        }
        notificationUserSelectionDialogFragment.search();
        return true;
    }

    
    public static void onViewCreatedlambda2(NotificationUserSelectionDialogFragment notificationUserSelectionDialogFragment, View view) {
        Intrinsics.checkNotNullParameter(notificationUserSelectionDialogFragment, "this0");
        notificationUserSelectionDialogFragment.viewModel.getUsersConnectedToMe(String.valueOf(notificationUserSelectionDialogFragment.getBinding().edtSearch.getText()));
    }

    
    public static void onViewCreatedlambda3(NotificationUserSelectionDialogFragment notificationUserSelectionDialogFragment, View view) {
        Intrinsics.checkNotNullParameter(notificationUserSelectionDialogFragment, "this0");
        notificationUserSelectionDialogFragment.clearSearch();
    }

    
    public static void onViewCreatedlambda4(NotificationUserSelectionDialogFragment notificationUserSelectionDialogFragment, View view) {
        Intrinsics.checkNotNullParameter(notificationUserSelectionDialogFragment, "this0");
        INotificationUserSelectionListener iNotificationUserSelectionListener = notificationUserSelectionDialogFragment.notificationUserSelectionListener;
        ArrayList<NotificationUserSelectionModel> arrayList = null;
        if (iNotificationUserSelectionListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("notificationUserSelectionListener");
            iNotificationUserSelectionListener = null;
        }
        ArrayList<NotificationUserSelectionModel> arrayList2 = notificationUserSelectionDialogFragment.initialSelectedList;
        if (arrayList2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("initialSelectedList");
        } else {
            arrayList = arrayList2;
        }
        iNotificationUserSelectionListener.selectionCompleted(arrayList);
        notificationUserSelectionDialogFragment.dismiss();
    }

    
    public static void onViewCreatedlambda5(NotificationUserSelectionDialogFragment notificationUserSelectionDialogFragment, View view) {
        Intrinsics.checkNotNullParameter(notificationUserSelectionDialogFragment, "this0");
        INotificationUserSelectionListener iNotificationUserSelectionListener = notificationUserSelectionDialogFragment.notificationUserSelectionListener;
        ArrayList<NotificationUserSelectionModel> arrayList = null;
        if (iNotificationUserSelectionListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("notificationUserSelectionListener");
            iNotificationUserSelectionListener = null;
        }
        ArrayList<NotificationUserSelectionModel> arrayList2 = notificationUserSelectionDialogFragment.selectedUsers;
        if (arrayList2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedUsers");
        } else {
            arrayList = arrayList2;
        }
        iNotificationUserSelectionListener.selectionCompleted(arrayList);
        notificationUserSelectionDialogFragment.dismiss();
    }

    
    public static void onViewCreatedlambda8(NotificationUserSelectionDialogFragment notificationUserSelectionDialogFragment, View view) {
        Intrinsics.checkNotNullParameter(notificationUserSelectionDialogFragment, "this0");
        int i = 0;
        for (Object obj : notificationUserSelectionDialogFragment.userList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            NotificationUserSelectionModel notificationUserSelectionModel = (NotificationUserSelectionModel) obj;
            notificationUserSelectionModel.setSelected(true);
            ArrayList<NotificationUserSelectionModel> arrayList = notificationUserSelectionDialogFragment.selectedUsers;
            ArrayList<NotificationUserSelectionModel> arrayList2 = null;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedUsers");
                arrayList = null;
            }
            if (arrayList == null || !arrayList.isEmpty()) {
                for (NotificationUserSelectionModel notificationUserSelectionModel2 : arrayList) {
                    if (notificationUserSelectionModel2.getId() == notificationUserSelectionModel.getId()) {
                        break;
                    }
                }
            }
            ArrayList<NotificationUserSelectionModel> arrayList3 = notificationUserSelectionDialogFragment.selectedUsers;
            if (arrayList3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedUsers");
            } else {
                arrayList2 = arrayList3;
            }
            arrayList2.add(notificationUserSelectionModel);
            RecyclerView.Adapter adapter = notificationUserSelectionDialogFragment.getBinding().list.getAdapter();
            if (adapter != null) {
                adapter.notifyItemChanged(i);
            }
            i = i2;
        }
        notificationUserSelectionDialogFragment.toggleSelectAll();
    }

    
    public static void onViewCreatedlambda12(NotificationUserSelectionDialogFragment notificationUserSelectionDialogFragment, View view) {
        Intrinsics.checkNotNullParameter(notificationUserSelectionDialogFragment, "this0");
        int i = 0;
        for (Object obj : notificationUserSelectionDialogFragment.userList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            NotificationUserSelectionModel notificationUserSelectionModel = (NotificationUserSelectionModel) obj;
            notificationUserSelectionModel.setSelected(false);
            ArrayList<NotificationUserSelectionModel> arrayList = notificationUserSelectionDialogFragment.selectedUsers;
            ArrayList<NotificationUserSelectionModel> arrayList2 = null;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedUsers");
                arrayList = null;
            }
            if (arrayList == null || !arrayList.isEmpty()) {
                Iterator<T> it = arrayList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else if (((NotificationUserSelectionModel) it.next()).getId() == notificationUserSelectionModel.getId()) {
                        ArrayList<NotificationUserSelectionModel> arrayList3 = notificationUserSelectionDialogFragment.selectedUsers;
                        if (arrayList3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("selectedUsers");
                        } else {
                            arrayList2 = arrayList3;
                        }
                        arrayList2.removeIf(new Predicate() { // from class: com.proje.mobilesales.features.notification.view.create.NotificationUserSelectionDialogFragmentExternalSyntheticLambda1
                            @Override // java.util.function.Predicate
                            public boolean test(Object obj2) {
                                return NotificationUserSelectionDialogFragment.onViewCreatedlambda12lambda11lambda10(Function1.this, obj2);
                            }
                        });
                        RecyclerView.Adapter adapter = notificationUserSelectionDialogFragment.getBinding().list.getAdapter();
                        if (adapter != null) {
                            adapter.notifyItemChanged(i);
                        }
                    }
                }
            }
            i = i2;
        }
        notificationUserSelectionDialogFragment.toggleSelectAll();
    }

    
    public static boolean onViewCreatedlambda12lambda11lambda10(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "tmp0");
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        super.onSaveInstanceState(bundle);
        ArrayList<NotificationUserSelectionModel> arrayList = this.selectedUsers;
        ArrayList<NotificationUserSelectionModel> arrayList2 = null;
        if (arrayList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedUsers");
            arrayList = null;
        }
        bundle.putParcelableArrayList(NotificationFilterFragmentKt.ARG_NOTIFICATION_FILTER_MODEL, arrayList);
        ArrayList<NotificationUserSelectionModel> arrayList3 = this.initialSelectedList;
        if (arrayList3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("initialSelectedList");
        } else {
            arrayList2 = arrayList3;
        }
        bundle.putParcelableArrayList("NOTIFICATION_SELECTED_USERS", arrayList2);
    }

    private void search() {
        this.viewModel.getUsersConnectedToMe(String.valueOf(getBinding().edtSearch.getText()));
    }

    private void clearSearch() {
        getBinding().edtSearch.setText("");
        this.viewModel.getUsersConnectedToMe("");
    }

    
    @SuppressLint({"LongLogTag"})
    public <T> void render(UiState<? extends T> uiState, Function1<? super T, Unit> function1) {
        if (uiState instanceof UiState.Loading) {
            onLoad();
        } else if (uiState instanceof UiState.Success) {
            try {
                function1.invoke(((UiState.Success) uiState).getResult());
            } catch (Exception e) {
                Log.d(this.TAG, "successor function failure", e);
            }
            hideLoadingProgress();
        } else if (uiState instanceof UiState.Error) {
            onError((UiState.Error) uiState);
        }
    }

    
    public void onGotUsers(List<NotificationUserSelectionModel> list) {
        this.userList = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (NotificationUserSelectionModel notificationUserSelectionModel : list) {
            notificationUserSelectionModel.setSelected(isUserSelected(notificationUserSelectionModel.getId()));
            arrayList.add(Unit.INSTANCE);
        }
        getBinding().list.setAdapter(new NotificationUserSelectionAdapter());
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.proje.mobilesales.features.notification.view.create.NotificationUserSelectionDialogFragmentExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public void run() {
                NotificationUserSelectionDialogFragment.onGotUserslambda15(NotificationUserSelectionDialogFragment.this);
            }
        }, 200);
        toggleSelectAll();
    }

    
    public static void onGotUserslambda15(NotificationUserSelectionDialogFragment notificationUserSelectionDialogFragment) {
        Intrinsics.checkNotNullParameter(notificationUserSelectionDialogFragment, "this0");
        notificationUserSelectionDialogFragment.getBinding().edtSearch.requestFocus();
    }

    
    public boolean isUserSelected(int i) {
        ArrayList<NotificationUserSelectionModel> arrayList = this.selectedUsers;
        if (arrayList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedUsers");
            arrayList = null;
        }
        if (arrayList != null && arrayList.isEmpty()) {
            return false;
        }
        for (NotificationUserSelectionModel notificationUserSelectionModel : arrayList) {
            if (notificationUserSelectionModel.getId() == i) {
                return true;
            }
        }
        return false;
    }

    private void onLoad() {
        LinearLayout root = getBinding().progressBar.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewExtensions.setVisible(root);
    }

    private Unit onError(UiState.Error error) {
        getBinding();
        hideLoadingProgress();
        return ViewExtensions.toastdefault(requireContext(), ViewExtensions.uiStateError(requireContext(), error), 0, 2, null);
    }

    private void hideLoadingProgress() {
        LinearLayout root = getBinding().progressBar.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewExtensions.setGone(root);
    }

    
    public void toggleSelectAll() {
        ArrayList<NotificationUserSelectionModel> arrayList = this.selectedUsers;
        ArrayList<NotificationUserSelectionModel> arrayList2 = null;
        if (arrayList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedUsers");
            arrayList = null;
        }
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        for (NotificationUserSelectionModel notificationUserSelectionModel : arrayList) {
            arrayList3.add(Integer.valueOf(notificationUserSelectionModel.getId()));
        }
        List<NotificationUserSelectionModel> list = this.userList;
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (NotificationUserSelectionModel notificationUserSelectionModel2 : list) {
            arrayList4.add(Integer.valueOf(notificationUserSelectionModel2.getId()));
        }
        boolean containsAll = arrayList3.containsAll(arrayList4);
        int i = 8;
        getBinding().btnSelectAll.setVisibility(!containsAll ? 0 : 8);
        AppCompatImageButton appCompatImageButton = getBinding().btnUnSelectAll;
        if (containsAll) {
            i = 0;
        }
        appCompatImageButton.setVisibility(i);
        AppCompatTextView appCompatTextView = getBinding().tvSelectedCount;
        ArrayList<NotificationUserSelectionModel> arrayList5 = this.selectedUsers;
        if (arrayList5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedUsers");
        } else {
            arrayList2 = arrayList5;
        }
        appCompatTextView.setText(String.valueOf(arrayList2.size()));
    }

    /* compiled from: NotificationUserSelectionDialogFragment.kt */
    
    
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private final FragmentNotificationUserSelectionDialogItemBinding itemBinding;
        final NotificationUserSelectionDialogFragment this0;

        
        public ViewHolder(NotificationUserSelectionDialogFragment notificationUserSelectionDialogFragment, FragmentNotificationUserSelectionDialogItemBinding fragmentNotificationUserSelectionDialogItemBinding) {
            super(fragmentNotificationUserSelectionDialogItemBinding.getRoot());
            Intrinsics.checkNotNullParameter(fragmentNotificationUserSelectionDialogItemBinding, "itemBinding");
            this.this0 = notificationUserSelectionDialogFragment;
            this.itemBinding = fragmentNotificationUserSelectionDialogItemBinding;
        }

        public FragmentNotificationUserSelectionDialogItemBinding getItemBinding() {
            return this.itemBinding;
        }

        public void bind(NotificationUserSelectionModel notificationUserSelectionModel) {
            Intrinsics.checkNotNullParameter(notificationUserSelectionModel, "item");
            this.itemBinding.setNotificationUser(notificationUserSelectionModel);
            this.itemBinding.executePendingBindings();
        }
    }

    public final class NotificationUserSelectionAdapter extends RecyclerView.Adapter<ViewHolder> {
        public NotificationUserSelectionAdapter() {
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            Intrinsics.checkNotNullParameter(viewGroup, "parent");
            NotificationUserSelectionDialogFragment notificationUserSelectionDialogFragment = NotificationUserSelectionDialogFragment.this;
            FragmentNotificationUserSelectionDialogItemBinding inflate = FragmentNotificationUserSelectionDialogItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
            return new ViewHolder(notificationUserSelectionDialogFragment, inflate);
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            Intrinsics.checkNotNullParameter(viewHolder, "holder");
            viewHolder.bind(NotificationUserSelectionDialogFragment.this.userList.get(i));
            viewHolder.getItemBinding().itemLayout.setOnClickListener(new ViewOnClickListenerC3033x5223119e(NotificationUserSelectionDialogFragment.this, i, this));
        }

        
        public static void onBindViewHolderlambda1(NotificationUserSelectionDialogFragment notificationUserSelectionDialogFragment, int i, NotificationUserSelectionAdapter notificationUserSelectionAdapter, View view) {
            Intrinsics.checkNotNullParameter(notificationUserSelectionDialogFragment, "this0");
            Intrinsics.checkNotNullParameter(notificationUserSelectionAdapter, "this1");
            NotificationUserSelectionModel notificationUserSelectionModel = notificationUserSelectionDialogFragment.userList.get(i);
            notificationUserSelectionModel.setSelected(!notificationUserSelectionModel.getSelected());
            ArrayList arrayList = null;
            if (!notificationUserSelectionModel.getSelected()) {
                ArrayList arrayList2 = notificationUserSelectionDialogFragment.selectedUsers;
                if (arrayList2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedUsers");
                } else {
                    arrayList = arrayList2;
                }
                arrayList.removeIf(new C3034x5223119f(new C3035xf29f47dc(notificationUserSelectionModel)));
            } else if (!notificationUserSelectionDialogFragment.isUserSelected(notificationUserSelectionModel.getId())) {
                ArrayList arrayList3 = notificationUserSelectionDialogFragment.selectedUsers;
                if (arrayList3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedUsers");
                } else {
                    arrayList = arrayList3;
                }
                arrayList.add(notificationUserSelectionModel);
            }
            notificationUserSelectionDialogFragment.toggleSelectAll();
            notificationUserSelectionAdapter.notifyItemChanged(i);
        }

        
        public static boolean onBindViewHolderlambda1lambda0(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "tmp0");
            return ((Boolean) function1.invoke(obj)).booleanValue();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return NotificationUserSelectionDialogFragment.this.userList.size();
        }
    }

    /* compiled from: NotificationUserSelectionDialogFragment.kt */
    
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public NotificationUserSelectionDialogFragment newInstance(ArrayList<NotificationUserSelectionModel> arrayList) {
            Intrinsics.checkNotNullParameter(arrayList, "selectedUsers");
            NotificationUserSelectionDialogFragment notificationUserSelectionDialogFragment = new NotificationUserSelectionDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("NOTIFICATION_SELECTED_USERS", arrayList);
            notificationUserSelectionDialogFragment.setArguments(bundle);
            return notificationUserSelectionDialogFragment;
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }
}
