package kotlin.coroutines;

import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import kotlin.Result;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;


final class CombinedContexttoString1 extends Lambda implements Function2<String, CoroutineContext.Element, String> {
    public static final CombinedContexttoString1 INSTANCE = new CombinedContexttoString1();
    CombinedContexttoString1() {
        super(2);
    }
    public Unit invoke(String str, CoroutineContext.Element element) {
        Intrinsics.checkNotNullParameter(str, "acc");
        Intrinsics.checkNotNullParameter(element, "element");
        int z0 = str.length();
        str.length();
        return element.toString();
    }
    public String invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
        return "";
    }
}
