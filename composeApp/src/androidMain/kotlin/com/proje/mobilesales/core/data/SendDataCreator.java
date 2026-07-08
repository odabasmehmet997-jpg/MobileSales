package com.proje.mobilesales.core.data;

import com.proje.mobilesales.core.base.BaseSelectResult;
import com.proje.mobilesales.core.base.BaseServiceResult;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.CabinTrans;
import com.proje.mobilesales.features.collections.casefiche.model.database.CaseCash;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditX;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeed;
import com.proje.mobilesales.features.customer.model.CustomerNew;
import com.proje.mobilesales.features.penetration.model.Penetration;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.todo.model.database.TodoInfoDb;
import com.proje.mobilesales.features.visit.model.database.VisitInfo;
import java.util.List;

public interface SendDataCreator<T extends BaseServiceResult, S extends BaseSelectResult> {
    S getCabin(int i2);
    S getCabinTrans(CabinTrans cabinTrans);
    T getCaseCash(CaseCash caseCash);
    T getCash(CashCreditX cashCreditX);
    T getCheque(ChequeDeed chequeDeed);
    T getCreditCard(CashCreditX cashCreditX);
    T getCustomer(CustomerNew customerNew);
    T getDeed(ChequeDeed chequeDeed);
    T getDemand(Sales sales, int i2);
    T getDispatch(Sales sales);
    T getInvoice(PrintSlipModel sales);
    List<T> getOneToOne(Sales sales);
    T getOrder(Sales sales);
    S getPenetration(Penetration penetration);
    List<S> getPenetrationDetailList(Penetration penetration);
    T getReturnDispatch(Sales sales);
    T getReturnInvoice(Sales sales);
    S getTodo(TodoInfoDb todoInfoDb);
    S getVisit(VisitInfo visitInfo);
    T getWhTransfer(PrintSlipModel sales);
}
