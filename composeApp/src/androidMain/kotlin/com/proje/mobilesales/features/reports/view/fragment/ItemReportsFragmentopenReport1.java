package com.proje.mobilesales.features.reports.view.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.reportparser.Report;
import com.proje.mobilesales.core.reportparser.ReportConstParamProp;
import com.proje.mobilesales.core.reportparser.ReportDesignerParser;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.features.product.model.ProductInfo;
import com.proje.mobilesales.features.reports.util.ReportUtil;
import com.proje.mobilesales.features.userreport.model.database.UserReports;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsParametersActivity;
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


public final class ItemReportsFragmentopenReport1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final  UserReports userReport;
    int label;
    final  ItemReportsFragment this0;
    public ItemReportsFragmentopenReport1(ItemReportsFragment itemReportsFragment, UserReports userReports, Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = itemReportsFragment;
        this.userReport = userReports;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new ItemReportsFragmentopenReport1(this.this0, this.userReport, continuation);
    }
    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
    public Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            final Report parseReportXml = new ReportDesignerParser().parseReportXml(this.this0.viewModel.getBaseErp().getLogoSqlHelper().getReportXML(this.userReport.id));
            MainCoroutineDispatcher main = Dispatchers.getMain();
            final ItemReportsFragment itemReportsFragment = this.this0;
            final UserReports userReports = this.userReport;
            AnonymousClass1 r3 = new AnonymousClass1();
            this.label = 1;
            if (BuildersKt.withContext(main, r3, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
    public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
        return null;
    }

    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        public _FileSystemKtcommonListRecursively1 create(Object obj2, Continuation<?> continuation) {
            return new AnonymousClass1(parseReportXml, itemReportsFragment, userReports, continuation);
        }

        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Report parseReportXml = null;
                ItemReportsFragment itemReportsFragment = null;
                if (parseReportXml == null) {
                    Toast.makeText(itemReportsFragment.requireContext(), itemReportsFragment.requireContext().getString(R.string.exp_84_unsupported_report_type), Toast.LENGTH_SHORT).show();
                } else if (itemReportsFragment.productInfo != null) {
                    itemReportsFragment = null;
                    Report report = parseReportXml;
                    UserReports userReports = null;
                    int saveObject = itemReportsFragment.viewModel.getBaseErp().saveObject(report);
                    Context context = itemReportsFragment.getContext();
                    Intrinsics.checkNotNull(report);
                    ReportConstParamProp reportConstParamProp = ReportConstParamProp.Stok;
                    ProductInfo productInfo = itemReportsFragment.productInfo;
                    Intrinsics.checkNotNull(productInfo);
                    Intent intent = new Intent(context, ReportUtil.getReportFromType(report, reportConstParamProp, productInfo.getItemRef()));
                    ComponentName component = intent.getComponent();
                    Intrinsics.checkNotNull(component);
                    if (Intrinsics.areEqual(component.getClassName(), UserReportsParametersActivity.class.getName())) {
                        FragmentActivity activity = itemReportsFragment.getActivity();
                        Intent intent2 = activity != null ? activity.getIntent() : null;
                        Intrinsics.checkNotNull(intent2);
                        intent2.putExtra("ReportConstParamProp", reportConstParamProp);
                    }
                    intent.putExtra("bigdata:synccode", saveObject);
                    intent.putExtra("ReportName", userReports.reportName);
                    intent.putExtra("ShowMailButton", false);
                    String str = IntentExtraName.EXTRA_ITEM_ID;
                    ProductInfo productInfo2 = itemReportsFragment.productInfo;
                    Intrinsics.checkNotNull(productInfo2);
                    intent.putExtra(str, productInfo2.getItemRef());
                    itemReportsFragment.requireActivity().startActivity(intent);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        @Override
        public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
            return null;
        }
    }
}
