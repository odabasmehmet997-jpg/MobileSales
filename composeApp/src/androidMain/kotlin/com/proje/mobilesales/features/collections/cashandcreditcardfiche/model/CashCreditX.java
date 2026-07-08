package com.proje.mobilesales.features.collections.cashandcreditcardfiche.model;

import com.proje.mobilesales.core.emailreplacer.EmailObject;
import com.proje.mobilesales.core.emailreplacer.EmailReplacer;
import com.proje.mobilesales.core.enums.EmailTemplateType;
import com.proje.mobilesales.core.enums.FType;
import com.proje.mobilesales.core.utils.FTypeControlUtils;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database.CashCredit;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database.CashCreditDetail;
import com.proje.mobilesales.features.customer.model.CustomerBeforeBalance;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: CashCreditX.kt */

public final class CashCreditX extends EmailReplacer {
    private CashCredit cashCredit;
    private List<CashCreditDetail> cashCreditDetails;

    public CashCredit getCashCredit() {
        return cashCredit;
    }

    public void setCashCredit(final CashCredit cashCredit) {
        this.cashCredit = cashCredit;
    }

    public List<CashCreditDetail> getCashCreditDetails() {
        return cashCreditDetails;
    }

    public void setCashCreditDetails(final List<CashCreditDetail> list) {
        cashCreditDetails = list;
    }

    public EmailTemplateType getEmailTemplateType() {
        final FType.Companion companion = FType.Companion;
        final CashCredit cashCredit = this.cashCredit;
        Intrinsics.checkNotNull(cashCredit);
        if (FTypeControlUtils.isFTypeCashReceipt(companion.fromFType(cashCredit.getfType()))) {
            return EmailTemplateType.CashCollection;
        }
        final CashCredit cashCredit2 = this.cashCredit;
        Intrinsics.checkNotNull(cashCredit2);
        return FTypeControlUtils.isFTypeCreditReceipt(companion.fromFType(cashCredit2.getfType())) ? EmailTemplateType.CreditCollection : EmailTemplateType.Unknown;
    }

    @Override // com.proje.mobilesales.core.emailreplacer.EmailReplacer
    public EmailObject replaceHtml(final EmailTemplateType templateType, final CustomerBeforeBalance customerBeforeBalance) {
        Intrinsics.checkNotNullParameter(templateType, "templateType");
        final EmailObject replaceCashCreditHTML = this.replaceCashCreditHTML(this, templateType, customerBeforeBalance);
        Intrinsics.checkNotNullExpressionValue(replaceCashCreditHTML, "replaceCashCreditHTML(...)");
        return replaceCashCreditHTML;
    }
}
