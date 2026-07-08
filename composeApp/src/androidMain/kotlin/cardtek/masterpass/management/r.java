package cardtek.masterpass.management;

import cardtek.masterpass.attributes.MasterPassEditText;
import cardtek.masterpass.interfaces.MoneySendListener;
import cardtek.masterpass.util.b;
 
public final class r implements Runnable {
    int sH = 0;
    String sI = "";
    Integer sJ = 0;
    String sK = "";
    String sL = "";
    MasterPassEditText ss = null;
    String sx = "";
    String sy = "";
    b sz = null;
    b tc = null;
    String td = "";
    MoneySendListener te = null;
    String tf = "";

    public r(b bVar, b bVar2, String str, MoneySendListener moneySendListener, String str2, MasterPassEditText masterPassEditText, String str3, int i2, String str4, String str5, Integer num, String str6, String str7) {
        this.sz = bVar;
        this.tc = bVar2;
        this.td = str;
        this.te = moneySendListener;
        this.tf = str2;
        this.ss = masterPassEditText;
        this.sx = str3;
        this.sH = i2;
        this.sI = str4;
        this.sy = str5;
        this.sJ = num;
        this.sK = str6;
        this.sL = str7;
    }

    public r(cardtek.masterpass.management.b b, b bVar, String str2, MoneySendListener moneySendListener, String str3, MasterPassEditText masterPassEditText, String str, int i2, String str4, String str5, Integer num, String str6, String str7) {
    }
    public void run() {
        throw new UnsupportedOperationException("Method not decompiled: cardtek.masterpass.management.r.run():void");
    }
}
