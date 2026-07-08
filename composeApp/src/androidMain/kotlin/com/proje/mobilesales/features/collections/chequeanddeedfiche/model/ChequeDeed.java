package com.proje.mobilesales.features.collections.chequeanddeedfiche.model;

import com.proje.mobilesales.core.emailreplacer.EmailObject;
import com.proje.mobilesales.core.emailreplacer.EmailReplacer;
import com.proje.mobilesales.core.enums.EmailTemplateType;
import com.proje.mobilesales.core.enums.FType;
import com.proje.mobilesales.core.utils.FTypeControlUtils;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database.Chequedeed;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database.ChequedeedDetail;
import com.proje.mobilesales.features.customer.model.CustomerBeforeBalance;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: ChequeDeed.kt */

public final class ChequeDeed extends EmailReplacer {
    private Chequedeed chequedeed;
    private List<ChequedeedDetail> chequedeedDetail;

    public Chequedeed getChequedeed() {
        return chequedeed;
    }

    public void setChequedeed(final Chequedeed chequedeed) {
        this.chequedeed = chequedeed;
    }

    public List<ChequedeedDetail> getChequedeedDetail() {
        return chequedeedDetail;
    }

    public void setChequedeedDetail(final List<ChequedeedDetail> list) {
        chequedeedDetail = list;
    }

    public EmailTemplateType getEmailTemplateType() {
        final FType.Companion companion = FType.Companion;
        final Chequedeed chequedeed = this.chequedeed;
        Intrinsics.checkNotNull(chequedeed);
        return FTypeControlUtils.isFTypeCheckReceipt(companion.fromFType(chequedeed.getfType())) ? EmailTemplateType.CheckCollection : EmailTemplateType.Unknown;
    }

    @Override // com.proje.mobilesales.core.emailreplacer.EmailReplacer
    public EmailObject replaceHtml(final EmailTemplateType templateType, final CustomerBeforeBalance customerBeforeBalance) {
        Intrinsics.checkNotNullParameter(templateType, "templateType");
        final EmailObject replaceChequeDeedHTML = this.replaceChequeDeedHTML(this, templateType, customerBeforeBalance);
        Intrinsics.checkNotNullExpressionValue(replaceChequeDeedHTML, "replaceChequeDeedHTML(...)");
        return replaceChequeDeedHTML;
    }
}
