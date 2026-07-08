package com.proje.mobilesales.features.reports.view.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.widget.Toast;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.reportparser.Report;
import com.proje.mobilesales.core.reportparser.ReportConstParamProp;
import com.proje.mobilesales.core.reportparser.ReportDesignerParser;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.features.product.model.ProductInfo;
import com.proje.mobilesales.features.reports.util.ReportUtil;
import com.proje.mobilesales.features.userreport.model.database.UserReports;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsFormActivity;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivity;
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

final class ItemReportsActivityopenReport1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final  UserReports userReport;
    int label;
    final ItemReportsActivity this0;
     ItemReportsActivityopenReport1(final ItemReportsActivity itemReportsActivity, final UserReports userReports, final Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this0 = itemReportsActivity;
        userReport = userReports;
    }

    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        return new ItemReportsActivityopenReport1(this0, userReport, continuation);
    }
 
    public Object invokeSuspend(final Object obj) {
        final BaseErp baseErp;
        final Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        final int i2 = label;
        if (0 == i2) {
            ResultKt.throwOnFailure(obj);
            final ReportDesignerParser reportDesignerParser = new ReportDesignerParser();
            baseErp = this0.baseErp;
            final Report parseReportXml = reportDesignerParser.parseReportXml(baseErp.getLogoSqlHelper().getReportXML(userReport.f1283id));
            final MainCoroutineDispatcher main = Dispatchers.getMain();
            final C28151 c28151 = new C28151(parseReportXml, this0, userReport, null);
            label = 1;
            if (BuildersKt.withContext(main, c28151, this) == coroutine_suspended) {
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
 
    static final class C28151 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final Report report = null;
        final UserReports userReport;
        int label;
        final ItemReportsActivity this0;
        C28151(final Report report, final ItemReportsActivity itemReportsActivity, final UserReports userReports, final Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation); 
            this0 = itemReportsActivity;
            userReport = userReports;
        }
 
        public Object invokeSuspend(final Object obj) {
            final ProductInfo productInfo;
            final BaseErp baseErp;
            final ProductInfo productInfo2;
            final ProductInfo productInfo3;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (0 != this.label) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (null != this.report) {
                productInfo = this0.productInfo;
                if (null != productInfo) {
                    final ItemReportsActivity itemReportsActivity = this0; 
                    final UserReports userReports = userReport;
                    baseErp = itemReportsActivity.baseErp;
                    final int saveObject = baseErp.saveObject(report);
                    Intrinsics.checkNotNull(report);
                    final ReportConstParamProp reportConstParamProp = ReportConstParamProp.Stok;
                    productInfo2 = itemReportsActivity.productInfo;
                    Intrinsics.checkNotNull(productInfo2);
                    itemReportsActivity.setIntent(new Intent(itemReportsActivity, ReportUtil.getReportFromType(report, reportConstParamProp, productInfo2.getItemRef())));
                    final ComponentName component = itemReportsActivity.getIntent().getComponent();
                    Intrinsics.checkNotNull(component);
                    if (!Intrinsics.areEqual(component.getClassName(), UserReportsGridActivity.class.getName())) {
                        final ComponentName component2 = itemReportsActivity.getIntent().getComponent();
                        Intrinsics.checkNotNull(component2);
                        if (!Intrinsics.areEqual(component2.getClassName(), UserReportsFormActivity.class.getName())) {
                            final ComponentName component3 = itemReportsActivity.getIntent().getComponent();
                            Intrinsics.checkNotNull(component3);
                        }
                    }
                    itemReportsActivity.getIntent().putExtra("ReportConstParamProp", reportConstParamProp);
                    itemReportsActivity.getIntent().putExtra("bigdata:synccode", saveObject);
                    itemReportsActivity.getIntent().putExtra("ReportName", userReports.reportName);
                    itemReportsActivity.getIntent().putExtra("ShowMailButton", false);
                    final Intent intent = itemReportsActivity.getIntent();
                    final String str = IntentExtraName.EXTRA_ITEM_ID;
                    productInfo3 = itemReportsActivity.productInfo;
                    Intrinsics.checkNotNull(productInfo3);
                    intent.putExtra(str, productInfo3.getItemRef());
                    itemReportsActivity.startActivity(itemReportsActivity.getIntent());
                }
            } else {
                final ItemReportsActivity itemReportsActivity2 = this0;
                Toast.makeText(itemReportsActivity2, itemReportsActivity2.getString(R.string.exp_84_unsupported_report_type), 0).show();
            }
            return Unit.INSTANCE;
        }
    }
}
