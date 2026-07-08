package com.proje.mobilesales.core.pagingsources;

import android.text.format.DateUtils;
import androidx.paging.PagingSource;
import androidx.paging.PagingState;
import com.proje.mobilesales.core.extensions.DateExtensions;
import com.proje.mobilesales.features.notification.model.NotificationFilterModel;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import java.util.Calendar;
import java.util.Date;

import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;

public final class NotificationListPagingSource extends PagingSource<Integer, NotificationModel> {
    private final NotificationFilterModel filterModel;
    public NotificationListPagingSource(NotificationFilterModel filterModel) {
        Intrinsics.checkNotNullParameter(filterModel, "filterModel");
        this.filterModel = filterModel;
    }
    public NotificationFilterModel getFilterModel() {
        return this.filterModel;
    }
    public Integer getRefreshKey(PagingState<Integer, NotificationModel> state) {
        Integer nextKey;
        Integer prevKey;
        Intrinsics.checkNotNullParameter(state, "state");
        Integer anchorPosition = state.getAnchorPosition();
        if (anchorPosition == null) {
            return null;
        }
        int intValue = anchorPosition.intValue();
        PagingSource.LoadResult.Page<Integer, NotificationModel> closestPageToPosition = state.closestPageToPosition(intValue);
        if (closestPageToPosition != null && (prevKey = closestPageToPosition.getPrevKey()) != null) {
            return Integer.valueOf(prevKey.intValue() + 1);
        }
        PagingSource.LoadResult.Page<Integer, NotificationModel> closestPageToPosition2 = state.closestPageToPosition(intValue);
        if (closestPageToPosition2 == null || (nextKey = closestPageToPosition2.getNextKey()) == null) {
            return null;
        }
        return Integer.valueOf(nextKey.intValue() - 1);
    }
    public Object load(LoadParams<Integer> loadParams, Continuation<? super LoadResult<Integer, NotificationModel>> continuation) {
        NotificationListPagingSourceload1 notificationListPagingSourceload1;
        int i2;
        try {
            Object obj = null;
            if (continuation instanceof NotificationListPagingSourceload1) {
                notificationListPagingSourceload1 = ( NotificationListPagingSourceload1 ) continuation;
                int i3 = notificationListPagingSourceload1.label;
                if ((i3 & Integer.MIN_VALUE) != 0) {
                    notificationListPagingSourceload1.label = i3 - Integer.MIN_VALUE;
                    obj = notificationListPagingSourceload1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i2 = notificationListPagingSourceload1.label;
                    if (i2 != 0) {
                        ResultKt.throwOnFailure(obj);
                        Integer key = loadParams.getKey();
                        int intValue = key != null ? key.intValue() : 1;
                        int i4 = intValue == 1 ? 1 : ((intValue - 1) * 10) + 1;
                        int i5 = intValue == 1 ? 30 : intValue * 10;
                        CoroutineDispatcher io2 = Dispatchers.getIO();
                        NotificationListPagingSourceload2 notificationListPagingSourceload2 = new NotificationListPagingSourceload2(i4, i5, this, intValue, loadParams, null);
                        notificationListPagingSourceload1.label = 1;
                        obj = BuildersKt.withContext(io2, notificationListPagingSourceload2, notificationListPagingSourceload1);
                    } else {
                        if (i2 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return obj;
                }
            }

            return obj;
        } catch (Exception e2) {
            return new LoadResult.Error(e2);
        }
        notificationListPagingSourceload1 = new NotificationListPagingSourceload1(this, ( Continuation<? super NotificationListPagingSourceload1> ) continuation);
        Object obj2 = notificationListPagingSourceload1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = notificationListPagingSourceload1.label;
    }
    public String getRelativeTime(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        Date datedefault = DateExtensions.toDatedefault(str, null, 1, null);
        if (datedefault == null) {
            return null;
        }
        if (DateUtils.isToday(datedefault.getTime())) {
            return DateUtils.getRelativeTimeSpanString(datedefault.getTime(), Calendar.getInstance().getTimeInMillis(), 1000L).toString();
        }
        return DateExtensions.formatDatedefault(datedefault, null, 1, null);
    }
}
