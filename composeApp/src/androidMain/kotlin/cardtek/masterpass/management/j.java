package cardtek.masterpass.management;

import cardtek.masterpass.attributes.MasterPassEditText;
import cardtek.masterpass.cardIO.MasterPassCardIOListener;
import cardtek.masterpass.cardIO.a;

/* loaded from: classes.dex */
public final class j implements a {
    final /* synthetic */ MasterPassEditText sO;
    final /* synthetic */ MasterPassCardIOListener sP;
    final /* synthetic */ b sz;

    public j(b bVar, MasterPassEditText masterPassEditText, MasterPassCardIOListener masterPassCardIOListener) {
        this.sz = bVar;
        this.sO = masterPassEditText;
        this.sP = masterPassCardIOListener;
    }

    @Override // cardtek.masterpass.cardIO.a
    public void a() {
        this.sP.onCancelled();
        b.sk = null;
    }

    @Override // cardtek.masterpass.cardIO.a
    public void a(String str, String str2, String str3) {
        this.sz.sj.setText(this.sO, str);
        this.sP.onSuccess(str2, str3);
        b.sk = null;
    }
}
