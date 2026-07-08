package com.proje.mobilesales.features.reports.view.activity;

import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.features.userreport.model.database.UserReports;
import java.util.List;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import okio.internal._FileSystemKtcommonListRecursively1;

final class ItemReportsActivityloadItemReportsFromLocal1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final   ItemReportsActivity this0;
 
    ItemReportsActivityloadItemReportsFromLocal1(final ItemReportsActivity itemReportsActivity, final Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this0 = itemReportsActivity;
    }
 
    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        return new ItemReportsActivityloadItemReportsFromLocal1(this0, continuation);
    }
    public Object invoke(final Object coroutineScope, final ? super CoroutineContext.Element continuation) {
        return this.create(coroutineScope, (Continuation<?>) continuation).invokeSuspend(Unit.INSTANCE);
    }

    public Object invokeSuspend(final Object obj) {
        final List list;
        final List list2;
        final BaseErp baseErp;
        final List list3;
        final Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        final int i2 = label;
        if (0 == i2) {
            ResultKt.throwOnFailure(obj);
            list = this0.itemReports;
            list.clear();
            list2 = this0.itemReports;
            baseErp = this0.baseErp;
            final List table = baseErp.getLogoSqlHelper().getTable(UserReports.class, "RTYPE=?", new String[]{"6"});
            Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.userreport.model.database.UserReports>");
            list2.addAll(table);
            final UserReports userReports = new UserReports();
            userReports.reportName = this0.getString(R.string.str_item_details);
            userReports.f1283id = 0;
            list3 = this0.itemReports;
            list3.add(0, userReports);
            final MainCoroutineDispatcher main = Dispatchers.getMain();
            final C28141 c28141 = new C28141(this0, null);
            label = 1;
            if (BuildersKt.withContext(main, c28141, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (1 != i2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
     static final class C28141 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final   ItemReportsActivity this0;
        C28141(final ItemReportsActivity itemReportsActivity, final Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation);
            this0 = itemReportsActivity;
        }
        public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
            return new C28141(this0, continuation);
        }
        public Object invoke(final Object coroutineScope, final ? super CoroutineContext.Element continuation) {
            return this.create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public Object invokeSuspend(final Object obj) {
            final List list;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (0 != this.label) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            final ItemReportsActivity itemReportsActivity = this0;
            list = itemReportsActivity.itemReports;
            itemReportsActivity.showData(list);
            return Unit.INSTANCE;
        }
    }
}
