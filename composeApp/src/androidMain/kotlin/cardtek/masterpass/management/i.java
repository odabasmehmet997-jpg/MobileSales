package cardtek.masterpass.management;

import android.widget.CompoundButton;
import cardtek.masterpass.attributes.MasterPassEditText;
import cardtek.masterpass.interfaces.RegisterAndPurchaseListener;
 
public final class i implements Runnable {
    final int sH;
    final String sI;
    final Integer sJ;
    final String sK;
    final String sL;
    final RegisterAndPurchaseListener sN;
    final CompoundButton sp;
    final MasterPassEditText sr;
    final MasterPassEditText ss;
    final int st;
    final int su;
    final String sv;
    final String sw;
    final String sx;
    final String sy;
    final b sz;
 
    public i(b bVar, CompoundButton compoundButton, RegisterAndPurchaseListener registerAndPurchaseListener, MasterPassEditText masterPassEditText, MasterPassEditText masterPassEditText2, int i2, int i3, String str, String str2, String str3, int i4, String str4, String str5, Integer num, String str6, String str7) {
        this.sz = bVar;
        this.sp = compoundButton;
        this.sN = registerAndPurchaseListener;
        this.sr = masterPassEditText;
        this.ss = masterPassEditText2;
        this.st = i2;
        this.su = i3;
        this.sv = str;
        this.sw = str2;
        this.sx = str3;
        this.sH = i4;
        this.sI = str4;
        this.sy = str5;
        this.sJ = num;
        this.sK = str6;
        this.sL = str7;
    }

    public void run() {
        throw new UnsupportedOperationException("Method not decompiled: cardtek.masterpass.management.i.run():void");
    }

    public class a {
    }
}
