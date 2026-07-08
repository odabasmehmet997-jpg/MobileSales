package com.proje.mobilesales.features.notification.view.list;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.paging.CombinedLoadStates;
import androidx.paging.LoadState;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.proje.mobilesales.C2682R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.NotificationStatus;
import com.proje.mobilesales.core.extensions.ViewExtensions;
import com.proje.mobilesales.core.interfaces.notification.INoticationFilterResult;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.UiState;
import com.proje.mobilesales.databinding.ActivityNotificationListBinding;
import com.proje.mobilesales.features.notification.model.NotificationFilterModel;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import com.proje.mobilesales.features.notification.repository.NotificationListRepository;
import com.proje.mobilesales.features.notification.util.NotificationConstants;
import com.proje.mobilesales.features.notification.view.create.NotificationCreateActivity;
import com.proje.mobilesales.features.notification.view.detail.NotificationDetailActivity;
import com.proje.mobilesales.features.notification.view.list.NotificationListAdapter;
import com.proje.mobilesales.features.notification.view.list.NotificationListEvents;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;

/* compiled from: NotificationListActivity.kt */

public final class NotificationListActivity extends BaseInjectableActivity implements INoticationFilterResult {
    public static final Companion Companion = new Companion(null);
    private static final int START_POSITION = 0;
    private final ActivityResultLauncher<Intent> notificationCreateResultLauncher;
    private final ActivityResultLauncher<Intent> notificationDetailResultLauncher;
    private int notificationId;
    private final NotificationListRepository notificationListRepository;
    private final NotificationListViewModel viewModel;
    private final String TAG = "NotificationList";
    private final Lazy bindingdelegate = LazyKt.lazy(new Functions<ActivityNotificationListBinding>(this) { // from class: com.proje.mobilesales.features.notification.view.list.NotificationListActivitybinding2
        final NotificationListActivity this0;

        
        {
            this.this0 = r1;
        }

        
        public ActivityNotificationListBinding invoke() {
            return ActivityNotificationListBinding.inflate(this.this0.getLayoutInflater());
        }
    });
    private NotificationFilterModel filterModel = new NotificationFilterModel();
    private final Lazy notificationListAdapterdelegate = LazyKt.lazy(new Functions<NotificationListAdapter>(this) { // from class: com.proje.mobilesales.features.notification.view.list.NotificationListActivitynotificationListAdapter2
        final NotificationListActivity this0;

        
        {
            this.this0 = r1;
        }

        
        public NotificationListAdapter invoke() {
            List listOf = CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(ContextCompat.getColor(this.this0, R.C2683color.yellow700)), Integer.valueOf(ContextCompat.getColor(this.this0, R.C2683color.lightGreen700))});
            boolean isSalesManager = ErpCreator.getInstance().getmBaseErp().getUser().isSalesManager();
            int userId = ErpCreator.getInstance().getmBaseErp().getUser().getUserId();
            final NotificationListActivity notificationListActivity = this.this0;
            NotificationListAdapter.NotificationClickListener notificationClickListener = new NotificationListAdapter.NotificationClickListener(new Function1<NotificationModel, Unit>() {
                public   Unit invoke(NotificationModel notificationModel) {
                    invoke(notificationModel);
                    return Unit.INSTANCE;
                }

                public void invoke(NotificationModel notificationModel) {
                    Intrinsics.checkNotNullParameter(notificationModel, "it");
                    notificationListActivity.onNotificationClick(notificationModel);
                }
            });
            final NotificationListActivity notificationListActivity2 = this.this0;
            NotificationListAdapter.InfoClickListener infoClickListener = new NotificationListAdapter.InfoClickListener(new Function1<NotificationModel, Unit>() {

                public  Unit invoke(NotificationModel notificationModel) {
                    invoke(notificationModel);
                    return Unit.INSTANCE;
                }

                public void invoke(NotificationModel notificationModel) {
                    Intrinsics.checkNotNullParameter(notificationModel, "it");
                    notificationListActivity2.onInfoClick(notificationModel);
                }
            });
            final NotificationListActivity notificationListActivity3 = this.this0;
            return new NotificationListAdapter(listOf, isSalesManager, userId, notificationClickListener, infoClickListener, new NotificationListAdapter.DeleteClickListener(new Function1<NotificationModel, Unit>() {
                public   Unit invoke(NotificationModel notificationModel) {
                    invoke(notificationModel);
                    return Unit.INSTANCE;
                }

                public void invoke(NotificationModel notificationModel) {
                    Intrinsics.checkNotNullParameter(notificationModel, "it");
                    notificationListActivity3.onDeleteClick(notificationModel);
                }
            }));
        }
    });

    public NotificationListActivity() {
        NotificationListRepository notificationListRepository = new NotificationListRepository();
        this.notificationListRepository = notificationListRepository;
        this.viewModel = new NotificationListViewModel(notificationListRepository);
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.proje.mobilesales.features.notification.view.list.NotificationListActivityExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public void onActivityResult(Object obj) {
                NotificationListActivity.notificationDetailResultLauncherlambda0(NotificationListActivity.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.notificationDetailResultLauncher = registerForActivityResult;
        ActivityResultLauncher<Intent> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.proje.mobilesales.features.notification.view.list.NotificationListActivityExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public void onActivityResult(Object obj) {
                NotificationListActivity.notificationCreateResultLauncherlambda1(NotificationListActivity.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResult(...)");
        this.notificationCreateResultLauncher = registerForActivityResult2;
    }

    /* compiled from: NotificationListActivity.kt */
    
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    
    public ActivityNotificationListBinding getBinding() {
        return (ActivityNotificationListBinding) this.bindingdelegate.getValue();
    }

    
    public static void notificationDetailResultLauncherlambda0(NotificationListActivity notificationListActivity, ActivityResult activityResult) {
        Intent data;
        Intrinsics.checkNotNullParameter(notificationListActivity, "this0");
        if (activityResult.getResultCode() == -1 && (data = activityResult.getData()) != null) {
            int intExtra = data.getIntExtra(NotificationConstants.EXTRA_NOTIFICATION_ID, 0);
            if (data.getIntExtra(NotificationConstants.EXTRA_NOTIFIEDUSER_ID, 0) != 0) {
                notificationListActivity.viewModel.onViewEvent(new NotificationListEvents.StatusUpdate(intExtra, NotificationStatus.Read.getStatus()));
            }
        }
    }

    
    public static void notificationCreateResultLauncherlambda1(NotificationListActivity notificationListActivity, ActivityResult activityResult) {
        Intent data;
        String stringExtra;
        Intrinsics.checkNotNullParameter(notificationListActivity, "this0");
        if (activityResult.getResultCode() == -1 && (data = activityResult.getData()) != null && (stringExtra = data.getStringExtra(NotificationConstants.EXTRA_NOTIFICATION_GUID)) != null && stringExtra.length() != 0 && ViewExtensions.isConnecteddefault(notificationListActivity, false, 1, null)) {
            notificationListActivity.viewModel.getNotificationByGuid(stringExtra);
        }
    }

    
    public NotificationListAdapter getNotificationListAdapter() {
        return (NotificationListAdapter) this.notificationListAdapterdelegate.getValue();
    }

    
    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getBinding().getRoot());
        getActivityComponent().inject(this);
        NotificationManagerCompat.from(getApplicationContext()).cancel(NotificationConstants.PERIODIC_WORK_NOTIFICATION_ID);
        this.viewModel.deleteUiState().observe(this, new NotificationListActivitysamandroidx_lifecycle_Observer0(new Function1<UiState<? extends Integer>, Unit>(this) { // from class: com.proje.mobilesales.features.notification.view.list.NotificationListActivityonCreate1
            final NotificationListActivity this0;

            
            {
                this.this0 = r1;
            }
            public   Unit invoke(UiState<? extends Integer> uiState) {
                invoke((UiState<Integer>) uiState);
                return Unit.INSTANCE;
            }

            public void invoke(UiState<Integer> uiState) {
                if (uiState != null) {
                    this.this0.render(uiState, new Function1<Integer, Unit>(this.this0) {
                        public   Unit invoke(Integer num) {
                            invoke(num.intValue());
                            return Unit.INSTANCE;
                        }

                        public void invoke(int i) {
                            ((NotificationListActivity) this.receiver).onNotificationDeleted(i);
                        }
                    });
                }
            }
        }));
        this.viewModel.notifiedUsersUiState().observe(this, new NotificationListActivitysamandroidx_lifecycle_Observer0(new Function1<UiState<? extends List<? extends NotifiedUserModel>>, Unit>(this) { // from class: com.proje.mobilesales.features.notification.view.list.NotificationListActivityonCreate2
            final NotificationListActivity this0;

            
            {
                this.this0 = r1;
            }

            public  Unit invoke(UiState<? extends List<? extends NotifiedUserModel>> uiState) {
                invoke((UiState<? extends List<NotifiedUserModel>>) uiState);
                return Unit.INSTANCE;
            }

            public void invoke(UiState<? extends List<NotifiedUserModel>> uiState) {
                if (uiState != null) {
                    this.this0.render(uiState, new Function1<List<? extends NotifiedUserModel>, Unit>(this.this0) {
                        public   Unit invoke(List<? extends NotifiedUserModel> list) {
                            invoke((List<NotifiedUserModel>) list);
                            return Unit.INSTANCE;
                        }

                        public void invoke(List<NotifiedUserModel> list) {
                            Intrinsics.checkNotNullParameter(list, "p0");
                            ((NotificationListActivity) this.receiver).onGotInfo(list);
                        }
                    });
                }
            }
        }));
        this.viewModel.getInsertedNotificationUiState().observe(this, new NotificationListActivitysamandroidx_lifecycle_Observer0(new Function1<UiState<? extends NotificationModel>, Unit>(this) { // from class: com.proje.mobilesales.features.notification.view.list.NotificationListActivityonCreate3
            final NotificationListActivity this0;

            
            {
                this.this0 = r1;
            }

            public  Unit invoke(UiState<? extends NotificationModel> uiState) {
                invoke((UiState<NotificationModel>) uiState);
                return Unit.INSTANCE;
            }

            public void invoke(UiState<NotificationModel> uiState) {
                if (uiState != null) {
                    this.this0.render(uiState, new Function1<NotificationModel, Unit>(this.this0) {
                        public  Unit invoke(NotificationModel notificationModel) {
                            invoke(notificationModel);
                            return Unit.INSTANCE;
                        }

                        public void invoke(NotificationModel notificationModel) {
                            Intrinsics.checkNotNullParameter(notificationModel, "p0");
                            ((NotificationListActivity) this.receiver).onGotInsertedNotification(notificationModel);
                        }
                    });
                }
            }
        }));
        setToolbar();
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(getString(R.string.str_notification_list));
        }
        onNewIntent(getIntent());
    }

    
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        MenuInflater menuInflater = getMenuInflater();
        Intrinsics.checkNotNullExpressionValue(menuInflater, "getMenuInflater(...)");
        menuInflater.inflate(R.menu.menu_notification_list, menu);
        menu.findItem(R.id.menu_new).setVisible(ErpCreator.getInstance().getmBaseErp().getUser().isSalesManager());
        return super.onCreateOptionsMenu(menu);
    }

    
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            finish();
            return true;
        } else if (itemId == R.id.menu_filter) {
            NotificationFilterFragment.Companion.newInstance(this.filterModel).show(getSupportFragmentManager(), "NotificationFilter");
            return true;
        } else if (itemId != R.id.menu_new) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            if (!ContextUtils.checkAutoTimeAndTimeZoneEnabledAndShowDialog()) {
                return true;
            }
            this.notificationCreateResultLauncher.launch(new Intent(this, NotificationCreateActivity.class));
            return true;
        }
    }

    
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

    
    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int i = -1;
        if (intent != null) {
            i = intent.getIntExtra(NotificationConstants.EXTRA_NOTIFICATION_ID, -1);
        }
        this.notificationId = i;
        getBinding().list.setLayoutManager(new LinearLayoutManager(this));
        getBinding().list.setAdapter(getNotificationListAdapter());
        getBinding().refreshLayout.setColorSchemeResources(R.C2683color.white);
        getBinding().refreshLayout.setProgressBackgroundColorSchemeResource(R.C2683color.redA200);
        initObservers();
        initAdapter();
        bindEvents();
    }

    
    @Override // com.proje.mobilesales.core.base.BaseActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        super.onSaveInstanceState(bundle);
        bundle.putInt(NotificationConstants.EXTRA_NOTIFICATION_ID, this.notificationId);
    }

    private void onLoad() {
        ActivityNotificationListBinding binding = getBinding();
        LinearLayout root = binding.loadingBar.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewExtensions.setVisible(root);
        binding.edtSearch.setEnabled(false);
        binding.imgBtnSearch.setEnabled(false);
        binding.imgBtnClearSearch.setEnabled(false);
    }

    private void initObservers() {
        if (ViewExtensions.isConnecteddefault(this, false, 1, null)) {
            this.filterModel.setSearchText(String.valueOf(getBinding().edtSearch.getText()));
            Job unused = BuildersKt__Builders_commonKt.launchdefault(LifecycleOwnerKt.getLifecycleScope(this), null, null, new NotificationListActivityinitObservers1(this, null), 3, null);
            return;
        }
        getBinding().refreshLayout.setRefreshing(false);
    }

    private void initAdapter() {
        getNotificationListAdapter().addLoadStateListener(new Function1<CombinedLoadStates, Unit>(this) { // from class: com.proje.mobilesales.features.notification.view.list.NotificationListActivityinitAdapter1
            final NotificationListActivity this0;

            
            {
                this.this0 = r1;
            }

            public  Unit invoke(CombinedLoadStates combinedLoadStates) {
                invoke(combinedLoadStates);
                return Unit.INSTANCE;
            }

            public void invoke(CombinedLoadStates combinedLoadStates) {
                LoadState.Error error;
                Intrinsics.checkNotNullParameter(combinedLoadStates, "loadState");
                boolean z = (combinedLoadStates.getRefresh() instanceof LoadState.NotLoading) && this.this0.getNotificationListAdapter().getItemCount() == 0;
                RelativeLayout relativeLayout = this.this0.getBinding().noResults.emptySearch;
                Intrinsics.checkNotNullExpressionValue(relativeLayout, "emptySearch");
                int i = 8;
                relativeLayout.setVisibility(z ? 0 : 8);
                RecyclerView recyclerView = this.this0.getBinding().list;
                Intrinsics.checkNotNullExpressionValue(recyclerView, "list");
                if (combinedLoadStates.getSource().getRefresh() instanceof LoadState.NotLoading) {
                    i = 0;
                }
                recyclerView.setVisibility(i);
                this.this0.handleLoading(combinedLoadStates.getSource().getRefresh() instanceof LoadState.Loading);
                if (combinedLoadStates.getAppend() instanceof LoadState.Error) {
                    LoadState append = combinedLoadStates.getAppend();
                    Intrinsics.checkNotNull(append, "null cannot be cast to non-null type androidx.paging.LoadState.Error");
                    error = (LoadState.Error) append;
                } else if (combinedLoadStates.getPrepend() instanceof LoadState.Error) {
                    LoadState prepend = combinedLoadStates.getPrepend();
                    Intrinsics.checkNotNull(prepend, "null cannot be cast to non-null type androidx.paging.LoadState.Error");
                    error = (LoadState.Error) prepend;
                } else if (combinedLoadStates.getRefresh() instanceof LoadState.Error) {
                    LoadState refresh = combinedLoadStates.getRefresh();
                    Intrinsics.checkNotNull(refresh, "null cannot be cast to non-null type androidx.paging.LoadState.Error");
                    error = (LoadState.Error) refresh;
                } else {
                    error = null;
                }
                if (error != null) {
                    ViewExtensions.toastdefault(this.this0, String.valueOf(error.getError().getMessage()), 0, 2, null);
                }
            }
        });
    }

    private void bindEvents() {
        ActivityNotificationListBinding binding = getBinding();
        binding.list.addOnScrollListener(new RecyclerView.OnScrollListener(binding, this) { // from class: com.proje.mobilesales.features.notification.view.list.NotificationListActivitybindEvents11
            final ActivityNotificationListBinding this_with;
            final NotificationListActivity this0;

            
            {
                this.this_with = r1;
                this.this0 = r2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                this.this_with.refreshLayout.setEnabled(((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition() == 0 || this.this0.getNotificationListAdapter().getItemCount() == 0);
            }
        });
        binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.proje.mobilesales.features.notification.view.list.NotificationListActivityExternalSyntheticLambda2
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                NotificationListActivity.bindEventslambda7lambda3(NotificationListActivity.this);
            }
        });
        binding.edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.proje.mobilesales.features.notification.view.list.NotificationListActivityExternalSyntheticLambda3
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return NotificationListActivity.bindEventslambda7lambda4(NotificationListActivity.this, textView, i, keyEvent);
            }
        });
        binding.imgBtnSearch.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.notification.view.list.NotificationListActivityExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NotificationListActivity.bindEventslambda7lambda5(NotificationListActivity.this, view);
            }
        });
        binding.imgBtnClearSearch.setOnClickListener(new View.OnClickListener(this) { // from class: com.proje.mobilesales.features.notification.view.list.NotificationListActivityExternalSyntheticLambda5
            public final NotificationListActivity f1;

            {
                this.f1 = r2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NotificationListActivity.bindEventslambda7lambda6(ActivityNotificationListBinding.this, this.f1, view);
            }
        });
    }

    
    public static void bindEventslambda7lambda3(NotificationListActivity notificationListActivity) {
        Intrinsics.checkNotNullParameter(notificationListActivity, "this0");
        notificationListActivity.initObservers();
    }

    
    public static boolean bindEventslambda7lambda4(NotificationListActivity notificationListActivity, TextView textView, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(notificationListActivity, "this0");
        if (i != 3 && i != 6 && (keyEvent.getAction() != 0 || keyEvent.getKeyCode() != 66)) {
            return false;
        }
        notificationListActivity.initObservers();
        return true;
    }

    
    public static void bindEventslambda7lambda5(NotificationListActivity notificationListActivity, View view) {
        Intrinsics.checkNotNullParameter(notificationListActivity, "this0");
        notificationListActivity.refreshList();
    }

    
    public static void bindEventslambda7lambda6(ActivityNotificationListBinding activityNotificationListBinding, NotificationListActivity notificationListActivity, View view) {
        Intrinsics.checkNotNullParameter(activityNotificationListBinding, "this_with");
        Intrinsics.checkNotNullParameter(notificationListActivity, "this0");
        Editable text = activityNotificationListBinding.edtSearch.getText();
        if (text != null && text.length() != 0) {
            activityNotificationListBinding.edtSearch.setText("");
            notificationListActivity.refreshList();
        }
    }

    private void refreshList() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.proje.mobilesales.features.notification.view.list.NotificationListActivityExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public void run() {
                NotificationListActivity.refreshListlambda8(NotificationListActivity.this);
            }
        }, 200);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.proje.mobilesales.features.notification.view.list.NotificationListActivityExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public void run() {
                NotificationListActivity.refreshListlambda9(NotificationListActivity.this);
            }
        }, 300);
    }

    
    public static void refreshListlambda8(NotificationListActivity notificationListActivity) {
        Intrinsics.checkNotNullParameter(notificationListActivity, "this0");
        notificationListActivity.scrollToTop();
    }

    
    public static void refreshListlambda9(NotificationListActivity notificationListActivity) {
        Intrinsics.checkNotNullParameter(notificationListActivity, "this0");
        notificationListActivity.initObservers();
    }

    private void scrollToTop() {
        RecyclerView.LayoutManager layoutManager = getBinding().list.getLayoutManager();
        Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(0, 0);
    }

    
    public void handleLoading(boolean z) {
        ActivityNotificationListBinding binding = getBinding();
        binding.refreshLayout.setRefreshing(z);
        binding.edtSearch.setEnabled(!z);
        binding.imgBtnSearch.setEnabled(!z);
        binding.imgBtnClearSearch.setEnabled(!z);
        if (!z) {
            binding.edtSearch.requestFocus();
        }
    }

    
    public void onNotificationDeleted(int i) {
        this.viewModel.onViewEvent(new NotificationListEvents.Delete(i));
    }

    private void hideLoadingProgress() {
        ActivityNotificationListBinding binding = getBinding();
        LinearLayout root = binding.loadingBar.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewExtensions.setGone(root);
        binding.edtSearch.setEnabled(true);
        binding.imgBtnSearch.setEnabled(true);
        binding.imgBtnClearSearch.setEnabled(true);
    }

    private Unit onError(UiState.Error error) {
        ActivityNotificationListBinding binding = getBinding();
        hideLoadingProgress();
        binding.edtSearch.setEnabled(true);
        binding.imgBtnSearch.setEnabled(true);
        binding.imgBtnClearSearch.setEnabled(true);
        return ViewExtensions.toastdefault(this, ViewExtensions.uiStateError(this, error), 0, 2, null);
    }

    @Override // com.proje.mobilesales.core.interfaces.notification.INoticationFilterResult
    public void filterChanged(NotificationFilterModel notificationFilterModel) {
        Intrinsics.checkNotNullParameter(notificationFilterModel, "notificationFilterModel");
        this.filterModel = notificationFilterModel;
    }

    @Override // com.proje.mobilesales.core.interfaces.notification.INoticationFilterResult
    public void applyFilter(NotificationFilterModel notificationFilterModel) {
        Intrinsics.checkNotNullParameter(notificationFilterModel, "notificationFilterModel");
        this.filterModel = notificationFilterModel;
        refreshList();
    }

    
    public void onNotificationClick(NotificationModel notificationModel) {
        if (ContextUtils.checkAutoTimeAndTimeZoneEnabledAndShowDialog()) {
            Intent intent = new Intent(this, NotificationDetailActivity.class);
            intent.putExtra(NotificationConstants.EXTRA_NOTIFICATION_ID, notificationModel.getNotificationId());
            intent.putExtra(NotificationConstants.EXTRA_NOTIFIEDUSER_ID, notificationModel.getNotifiedUserId());
            this.notificationDetailResultLauncher.launch(intent);
        }
    }

    
    public void onInfoClick(NotificationModel notificationModel) {
        if (ViewExtensions.isConnecteddefault(this, false, 1, null)) {
            this.viewModel.getNotifiedUsers(notificationModel);
        }
    }

    
    public void onDeleteClick(NotificationModel notificationModel) {
        if (ViewExtensions.isConnecteddefault(this, false, 1, null) && ContextUtils.checkAutoTimeAndTimeZoneEnabledAndShowDialog()) {
            ViewExtensions.alertdefault(this, 0, new Function1<AlertDialog.Builder, Unit>(this, notificationModel) { // from class: com.proje.mobilesales.features.notification.view.list.NotificationListActivityonDeleteClick1
                final NotificationModel notificationModel;
                final NotificationListActivity this0;

                
                {
                    this.this0 = r1;
                    this.notificationModel = r2;
                }

                public Unit invoke(AlertDialog.Builder builder) {
                    invoke(builder);
                    return Unit.INSTANCE;
                }

                public void invoke(AlertDialog.Builder builder) {
                    Intrinsics.checkNotNullParameter(builder, "thisalert");
                    builder.setMessage(this.this0.getString(R.string.str_confirm_delete_notification));
                    final NotificationListActivity notificationListActivity = this.this0;
                    final NotificationModel notificationModel2 = this.notificationModel;
                    ViewExtensions.positiveButtondefault(builder, 0, new Function1<DialogInterface, Unit>() {
                        public   Unit invoke(DialogInterface dialogInterface) {
                            invoke(dialogInterface);
                            return Unit.INSTANCE;
                        }

                        public void invoke(DialogInterface dialogInterface) {
                            Intrinsics.checkNotNullParameter(dialogInterface, "it");
                            dialogInterface.dismiss();
                            notificationListActivity.viewModel.deleteNotification(notificationModel2);
                        }
                    }, 1, null);
                    ViewExtensions.negativeButtondefault(builder, 0, C30542.INSTANCE, 1, null);
                }
            }, 1, null);
        }
    }

    
    public void onGotInfo(List<NotifiedUserModel> list) {
        NotifiedUserListDialogFragment.Companion.newInstance(list).show(getSupportFragmentManager(), "NotifiedUserListDialog");
    }

    
    public void onGotInsertedNotification(NotificationModel notificationModel) {
        this.viewModel.onViewEvent(new NotificationListEvents.ItemAdded(notificationModel));
        refreshList();
    }
}
