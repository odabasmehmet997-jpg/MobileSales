package cardtek.masterpass.management;

import cardtek.masterpass.interfaces.RecurringPaymentListener;
import cardtek.masterpass.util.ActionType;

/* loaded from: classes.dex */
public final class l implements Runnable {
    final /* synthetic */ int sH;
    final /* synthetic */ RecurringPaymentListener sR;
    final /* synthetic */ String sS;
    final /* synthetic */ ActionType sT;
    final /* synthetic */ String sU;
    final /* synthetic */ String sw;
    final /* synthetic */ String sx;
    final /* synthetic */ String sy;
    final /* synthetic */ b sz;

    public l(b bVar, String str, RecurringPaymentListener recurringPaymentListener, String str2, int i2, String str3, String str4, ActionType actionType, String str5) {
        this.sz = bVar;
        this.sw = str;
        this.sR = recurringPaymentListener;
        this.sx = str2;
        this.sH = i2;
        this.sy = str3;
        this.sS = str4;
        this.sT = actionType;
        this.sU = str5;
    }

    /*  DEBUG: Multi-variable search result rejected for r0v22, resolved type: cardtek.masterpass.management.ServiceResponse */
    /*  DEBUG: Multi-variable search result rejected for r2v28, resolved type: cardtek.masterpass.results.RecurringPaymentResult */
    /*  WARN: Multi-variable type inference failed */
    /*  WARNING: Removed duplicated region for block: B:40:0x0154 A[Catch: Exception -> 0x00b6, TryCatch #0 {Exception -> 0x00b6, blocks: (B:3:0x0014, B:5:0x0018, B:8:0x0020, B:11:0x009e, B:13:0x00a8, B:16:0x00b9, B:19:0x00c3, B:21:0x00cd, B:23:0x00f4, B:24:0x00fb, B:26:0x0101, B:27:0x0108, B:29:0x010e, B:30:0x0116, B:32:0x0138, B:33:0x013f, B:35:0x0147, B:38:0x0150, B:40:0x0154, B:42:0x0185, B:44:0x0189, B:46:0x0191, B:48:0x0199), top: B:60:0x0014 }] */
    /*  WARNING: Removed duplicated region for block: B:42:0x0185 A[Catch: Exception -> 0x00b6, TryCatch #0 {Exception -> 0x00b6, blocks: (B:3:0x0014, B:5:0x0018, B:8:0x0020, B:11:0x009e, B:13:0x00a8, B:16:0x00b9, B:19:0x00c3, B:21:0x00cd, B:23:0x00f4, B:24:0x00fb, B:26:0x0101, B:27:0x0108, B:29:0x010e, B:30:0x0116, B:32:0x0138, B:33:0x013f, B:35:0x0147, B:38:0x0150, B:40:0x0154, B:42:0x0185, B:44:0x0189, B:46:0x0191, B:48:0x0199), top: B:60:0x0014 }] */
    @Override // java.lang.Runnable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        // Method dump skipped, instructions count: 486
        */
        throw new UnsupportedOperationException("Method not decompiled: cardtek.masterpass.management.l.run():void");
    }
}
