package com.proje.mobilesales.features.reports.view.fragment;

import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.features.userreport.model.database.UserReports;
import java.util.List;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import okio.internal._FileSystemKtcommonListRecursively1;

public final class ItemReportsFragmentloadItemReportsFromLocal1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final ItemReportsFragment this0;

    public ItemReportsFragmentloadItemReportsFromLocal1(ItemReportsFragment itemReportsFragment, Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = itemReportsFragment;
    }

    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new ItemReportsFragmentloadItemReportsFromLocal1(this.this0, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            this.this0.itemReports.clear();
            List list = this.this0.itemReports;
            List table = this.this0.viewModel.getBaseErp().getLogoSqlHelper().getTable(UserReports.class, "RTYPE=?", new String[]{"6"});
            Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.userreport.model.database.UserReports>");
            list.addAll(table);
            UserReports userReports = new UserReports();
            List list2 = this.this0.itemReports;
            userReports.reportName = this.this0.getString(R.string.str_item_details);
            userReports.id = 0;
            list2.add(0, userReports);
            if (this.this0.viewModel.erpType() == ErpType.NETSIS) {
                UserReports userReports2 = new UserReports();
                List list3 = this.this0.itemReports;
                userReports2.reportName = this.this0.getString(R.string.str_serial_report);
                userReports2.id = 1;
                list3.add(1, userReports2);
            }
            MainCoroutineDispatcher main = Dispatchers.getMain();
            final ItemReportsFragment itemReportsFragment = this.this0;
            AnonymousClass3 r1 = new AnonymousClass3();
            this.label = 1;
            if (BuildersKt.withContext(main, r1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    public Object invoke(ItemReportsFragment p1, Continuation<Object> p2) {
        return null;
    }
    public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
        return null;
    }
    public static class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        public Object invokeSuspend(Object obj) {
            return null;
        }

        public AnonymousClass3() {
            super(2, null);
        }

        public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
            return null;
        }

        public static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;

            public _FileSystemKtcommonListRecursively1 create(Object obj2, Continuation<?> continuation) {
                return new AnonymousClass3();
            }

            public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ItemReportsFragment itemReportsFragment = null;
                    itemReportsFragment.showData(itemReportsFragment.itemReports);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
