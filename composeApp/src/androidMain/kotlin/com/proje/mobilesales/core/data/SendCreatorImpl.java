package com.proje.mobilesales.core.data;

import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.proje.mobilesales.core.asynctask.TransferAutoAsyncTask;
import com.proje.mobilesales.core.base.BaseSelectResult;
import com.proje.mobilesales.core.base.BaseServiceResult;
import com.proje.mobilesales.core.enums.DataObjectType;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.enums.TransferOperationName;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.sql.ISqlManager;
import com.proje.mobilesales.core.tigerwcf.TigerServiceResult;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.SendLogger;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.CabinTrans;
import com.proje.mobilesales.features.collections.casefiche.model.database.CaseCash;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditX;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeed;
import com.proje.mobilesales.features.customer.model.CustomerNew;
import com.proje.mobilesales.features.model.GroupItem;
import com.proje.mobilesales.features.penetration.model.Penetration;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.todo.model.database.TodoInfoDb;
import com.proje.mobilesales.features.visit.model.database.VisitInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public final class SendCreatorImpl<T extends BaseServiceResult, S extends BaseSelectResult> implements SendCreator<T, S, SendCreatorImpl<T, S>>, SendDataCreator<T, S> {
    private static final Object LOCK = new Object();
    private static SendCreatorImpl sInstance;
    private ISqlManager mSqlManager;
    private SendLogger mLogger = new SendLogger();
    private ArrayList<GroupItem> mGroupItems = new ArrayList<>();
    private final SendDataCreator<T, S> mSendDataCreator = SendDateCreatorFactory.getSendDataCreater(Preferences.getErpType(ContextUtils.getmContext()));
    public SendCreatorImpl(ISqlManager iSqlManager) {
        this.mSqlManager = iSqlManager;
    }
    public ArrayList<GroupItem> getGroupItems() {
        return this.mGroupItems;
    }
    public void setGroupItems(ArrayList<GroupItem> arrayList) {
        this.mGroupItems = arrayList;
    }
    public ISqlManager getSqlManager() {
        return this.mSqlManager;
    }
    public void setSqlManager(ISqlManager sqlManager) {
        this.mSqlManager = sqlManager;
    }
    public SendLogger getLogger() {
        return this.mLogger;
    }
    public void setLogger(SendLogger sendLogger) {
        this.mLogger = sendLogger;
    }
    public  ObservableSource lambdagetAllSend0() throws Exception {
        return Observable.just(this.mGroupItems);
    }
    public void getAllSend(ResponseListener<Boolean> responseListener) {
        Observable subscribeOn = Observable.defer(new Callable() { 
            public Object call() {
                try {
                    return SendCreatorImpl.this.lambdagetAllSend0();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).map(new Function() {  
            public Object apply(Object obj) {
                try {
                    return SendCreatorImpl.this.lambdagetAllSend1((ArrayList) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).map(new Function() {  
            public Object apply(Object obj) {
                try {
                    return SendCreatorImpl.lambdagetAllSend2((ArrayList) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        Objects.requireNonNull(responseListener);
        subscribeOn.subscribe(new SendCreatorImplExternalSyntheticLambda3());
    }
    public  ArrayList lambdagetAllSend1(ArrayList arrayList) throws Exception {
        this.mGroupItems.clear();
        getDemands();
        getOrders();
        getInvoices();
        getRetailInvoices();
        getDispatches();
        getOneToOnes();
        getCaseCashes();
        getCheques();
        getDeeds();
        getCashes();
        getCreditCards();
        getVisits();
        getTodosWorProc();
        getPenetrations();
        getCabinTransAndCabin();
        getWhTransfers();
        return this.mGroupItems;
    } 
    public static  Boolean lambdagetAllSend2(ArrayList arrayList) throws Exception {
        return Boolean.valueOf(arrayList.size() > 0);
    }
    public static  void lambdagetAllSend3(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }
    public void getOrders() {
        getOrders(this.mSqlManager.getSalesList());
    }
    public SendCreatorImpl getOrder(int i) {
        this.mGroupItems.clear();
        getOrders(this.mSqlManager.getSalesList(i));
        return this;
    }
    public List<T> getOrders(List<Sales> list) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (Sales sales : list) {
            T order = getOrder(sales);
            addLog(order);
            addGroupList(TransferOperationName.ORDER,  order);
            arrayList.add(order);
        }
        return arrayList;
    }
    public T getOrder(Sales sales) {
        return this.mSendDataCreator.getOrder(sales);
    } 
    public void getDemands() {
        getDemands(this.mSqlManager.getDemandList());
    }
    public SendCreatorImpl getDemand(int i) {
        this.mGroupItems.clear();
        getDemands(this.mSqlManager.getDemandList(i));
        return this;
    }
    public List<T> getDemands(List<Sales> list) {
        ArrayList arrayList = new ArrayList();
        int i = 1;
        for (Sales sales : list) {
            T demand = getDemand(sales, i);
            addLog(demand);
            addGroupList(TransferOperationName.DEMAND,  demand);
            arrayList.add(demand);
            i++;
        }
        return arrayList;
    } 
    public T getDemand(Sales sales, int i) {
        return this.mSendDataCreator.getDemand(sales, i);
    }
    public void getInvoices() {
        getInvoices(this.mSqlManager.mapCursorToInvoice(SalesType.INVOICE));
        getReturnInvoices(this.mSqlManager.mapCursorToInvoice(SalesType.RETURN_INVOICE));
    }
    public SendCreatorImpl getInvoice(int i) {
        this.mGroupItems.clear();
        getInvoices(this.mSqlManager.mapCursorToInvoice(SalesType.INVOICE, i));
        getReturnInvoices(this.mSqlManager.mapCursorToInvoice(SalesType.RETURN_INVOICE, i));
        return this;
    }
    public void getRetailInvoices() {
        getRetailInvoices(this.mSqlManager.mapCursorToInvoice(SalesType.RETAIL_INVOICE));
        getRetailReturnInvoices(this.mSqlManager.mapCursorToInvoice(SalesType.RETAIL_RETURN_INVOICE));
    }
    public SendCreatorImpl getRetailInvoice(int i) {
        this.mGroupItems.clear();
        getRetailInvoices(this.mSqlManager.mapCursorToInvoice(SalesType.RETAIL_INVOICE, i));
        getRetailReturnInvoices(this.mSqlManager.mapCursorToInvoice(SalesType.RETAIL_RETURN_INVOICE, i));
        return this;
    }
    public List<T> getInvoices(List<Sales> list) {
        ArrayList arrayList = new ArrayList();
        for (Sales sales : list) {
            T invoice = getInvoice(sales);
            addLog(invoice);
            addGroupList(TransferOperationName.INVOICE, invoice);
            arrayList.add(invoice);
        }
        return arrayList;
    }
    public T getInvoice(PrintSlipModel sales) {
        return this.mSendDataCreator.getInvoice(sales);
    }
    public List<T> getReturnInvoices(List<Sales> list) {
        ArrayList arrayList = new ArrayList();
        for (Sales sales : list) {
            T returnInvoice = getReturnInvoice(sales);
            addLog(returnInvoice);
            addGroupList(TransferOperationName.RETURN_INVOICE, returnInvoice);
            arrayList.add(returnInvoice);
        }
        return arrayList;
    }
    public T getReturnInvoice(Sales sales) {
        return this.mSendDataCreator.getReturnInvoice(sales);
    }
    public List<T> getRetailInvoices(List<Sales> list) {
        ArrayList arrayList = new ArrayList();
        for (Sales sales : list) {
            T invoice = getInvoice(sales);
            addLog(invoice);
            addGroupList(TransferOperationName.RETAIL_INVOICE, invoice);
            arrayList.add(invoice);
        }
        return arrayList;
    }
    public List<T> getRetailReturnInvoices(List<Sales> list) {
        ArrayList arrayList = new ArrayList();
        for (Sales sales : list) {
            T returnInvoice = getReturnInvoice(sales);
            addLog(returnInvoice);
            addGroupList(TransferOperationName.RETAIL_RETURN_INVOICE, returnInvoice);
            arrayList.add(returnInvoice);
        }
        return arrayList;
    }
    public void getDispatches() {
        getDispatches(this.mSqlManager.mapCursorToInvoice(SalesType.DISPATCH));
        getReturnDispatches(this.mSqlManager.mapCursorToInvoice(SalesType.RETURN_DISPATCH));
    }
    public SendCreatorImpl getDispatch(int i) {
        this.mGroupItems.clear();
        getDispatches(this.mSqlManager.mapCursorToInvoice(SalesType.DISPATCH, i));
        getReturnDispatches(this.mSqlManager.mapCursorToInvoice(SalesType.RETURN_DISPATCH, i));
        return this;
    }
    public List<T> getDispatches(List<Sales> list) {
        ArrayList arrayList = new ArrayList();
        for (Sales sales : list) {
            T dispatch = getDispatch(sales);
            addLog(dispatch);
            addGroupList(TransferOperationName.DISPATCH, dispatch);
            arrayList.add(dispatch);
        }
        return arrayList;
    }
    public T getDispatch(Sales sales) {
        return this.mSendDataCreator.getDispatch(sales);
    }
    public List<T> getReturnDispatches(List<Sales> list) {
        ArrayList arrayList = new ArrayList();
        for (Sales sales : list) {
            T returnDispatch = getReturnDispatch(sales);
            addLog(returnDispatch);
            addGroupList(TransferOperationName.RETURN_DISPATCH, returnDispatch);
            arrayList.add(returnDispatch);
        }
        return arrayList;
    }
    public T getReturnDispatch(Sales sales) {
        return this.mSendDataCreator.getReturnDispatch(sales);
    }
    public void getOneToOnes() {
        getOneToOnes(this.mSqlManager.mapCursorToInvoice(SalesType.ONE_TO_ONE_CHANGE));
    }
    public SendCreatorImpl getOneToOne(int i) {
        this.mGroupItems.clear();
        getOneToOnes(this.mSqlManager.mapCursorToInvoice(SalesType.ONE_TO_ONE_CHANGE, i));
        return this;
    }
    public List<T> getOneToOnes(List<Sales> list) {
        ArrayList arrayList = new ArrayList();
        for (Sales sales : list) {
            List<T> oneToOne = getOneToOne(sales);
            for (T t : oneToOne) {
                addLog(t);
            }
            addGroupList(TransferOperationName.ONE_TO_ONE_CHANGE, oneToOne);
            arrayList.addAll(oneToOne);
        }
        return arrayList;
    }
    public List<T> getOneToOne(Sales sales) {
        return this.mSendDataCreator.getOneToOne(sales);
    }

    
    public void getCheques() {
        getCheques(this.mSqlManager.getCheques());
    }

    
    public SendCreatorImpl getCheque(int i) {
        this.mGroupItems.clear();
        getCheques(this.mSqlManager.getCheques(i));
        return this;
    }

    
    public List<T> getCheques(List<ChequeDeed> list) {
        ArrayList arrayList = new ArrayList();
        for (ChequeDeed chequeDeed : list) {
            T cheque = getCheque(chequeDeed);
            addLog(cheque);
            addGroupList(TransferOperationName.CHEQUE, cheque);
            arrayList.add(cheque);
        }
        return arrayList;
    }

    
    public T getCheque(ChequeDeed chequeDeed) {
        return this.mSendDataCreator.getCheque(chequeDeed);
    }

    
    public void getDeeds() {
        getDeeds(this.mSqlManager.getDeeds());
    }

    
    public SendCreatorImpl getDeed(int i) {
        this.mGroupItems.clear();
        getDeeds(this.mSqlManager.getDeeds(i));
        return this;
    }

    
    public List<T> getDeeds(List<ChequeDeed> list) {
        ArrayList arrayList = new ArrayList();
        for (ChequeDeed chequeDeed : list) {
            T deed = getDeed(chequeDeed);
            addLog(deed);
            addGroupList(TransferOperationName.DEED, deed);
            arrayList.add(deed);
        }
        return arrayList;
    }

    
    public T getDeed(ChequeDeed chequeDeed) {
        return this.mSendDataCreator.getDeed(chequeDeed);
    }

    
    public void getCashes() {
        getCashes(this.mSqlManager.getCashes());
    }

    
    public SendCreatorImpl getCash(int i) {
        this.mGroupItems.clear();
        getCashes(this.mSqlManager.getCashes(i));
        return this;
    }

    
    public List<T> getCashes(List<CashCreditX> list) {
        ArrayList arrayList = new ArrayList();
        for (CashCreditX cashCreditX : list) {
            T cash = getCash(cashCreditX);
            addLog(cash);
            addGroupList(TransferOperationName.CASH, cash);
            arrayList.add(cash);
        }
        return arrayList;
    }

    
    public T getCash(CashCreditX cashCreditX) {
        return this.mSendDataCreator.getCash(cashCreditX);
    }

    
    public T getCustomer(CustomerNew customerNew) {
        return this.mSendDataCreator.getCustomer(customerNew);
    }

    
    public void getCaseCashes() {
        getCaseCashes(this.mSqlManager.getCaseCashs());
    }

    
    public SendCreatorImpl getCaseCash(int i) {
        this.mGroupItems.clear();
        getCaseCashes(this.mSqlManager.getCaseCashs(i));
        return this;
    }

    
    public List<T> getCaseCashes(List<CaseCash> list) {
        ArrayList arrayList = new ArrayList();
        for (CaseCash caseCash : list) {
            T caseCash2 = getCaseCash(caseCash);
            addLog(caseCash2);
            addGroupList(TransferOperationName.CASE_CASH, caseCash2);
            arrayList.add(caseCash2);
        }
        return arrayList;
    }

    
    public T getCaseCash(CaseCash caseCash) {
        return this.mSendDataCreator.getCaseCash(caseCash);
    }

    
    public void getCreditCards() {
        getCreditCards(this.mSqlManager.getCreditCards());
    }

    
    public SendCreatorImpl getCreditCard(int i) {
        this.mGroupItems.clear();
        getCreditCards(this.mSqlManager.getCreditCards(i));
        return this;
    }
    public List<T> getCreditCards(List<CashCreditX> list) {
        ArrayList arrayList = new ArrayList();
        for (CashCreditX cashCreditX : list) {
            T creditCard = getCreditCard(cashCreditX);
            addLog(creditCard);
            addGroupList(TransferOperationName.CREDIT_CARD);
            arrayList.add(creditCard);
        }
        return arrayList;
    }
    public T getCreditCard(CashCreditX cashCreditX) {
        return this.mSendDataCreator.getCreditCard(cashCreditX);
    }
    public void getVisits() {
        getVisits(this.mSqlManager.getVisits());
    }
    public SendCreatorImpl getVisit(int i) {
        this.mGroupItems.clear();
        getVisits(this.mSqlManager.getVisits(i));
        return this;
    }
    public List<S> getVisits(List<VisitInfo> list) {
        ArrayList arrayList = new ArrayList();
        for (VisitInfo visitInfo : list) {
            S visit = getVisit(visitInfo);
            addLog(visit.getProcessType(), visit.getClCode(), visit.getSql());
            addGroupList(TransferOperationName.VISIT);
            arrayList.add(visit);
        }
        return arrayList;
    }
    public S getVisit(VisitInfo visitInfo) {
        return this.mSendDataCreator.getVisit(visitInfo);
    }
    public void getTodosWorProc() {
        getTodosWorProc(getSqlManager().getTodos());
    }
    public SendCreatorImpl getTodoWorProc(int i) {
        this.mGroupItems.clear();
        getTodosWorProc(this.mSqlManager.getTodos(i));
        return this;
    }
    public List<S> getTodosWorProc(List<TodoInfoDb> list) {
        ArrayList arrayList = new ArrayList();
        for (TodoInfoDb todoInfoDb : list) {
            S todo = getTodo(todoInfoDb);
            addLog(todo.getProcessType(), todo.getClCode(), todo.getSql());
            addGroupList(TransferOperationName.TODOWORPROC);
            arrayList.add(todo);
        }
        return arrayList;
    }
    public S getTodo(TodoInfoDb todoInfoDb) {
        return this.mSendDataCreator.getTodo(todoInfoDb);
    }
    public void getPenetrations() {
        getPenetrations(this.mSqlManager.getPenetrations());
    }
    public SendCreatorImpl getPenetration(int i) {
        this.mGroupItems.clear();
        getPenetrations(this.mSqlManager.getPenetrations(i));
        return this;
    }
    public List<S> getPenetrations(List<Penetration> list) {
        ArrayList arrayList = new ArrayList();
        new ArrayList();
        for (Penetration penetration : list) {
            S penetration2 = getPenetration(penetration);
            addLog(penetration2.getProcessType(), penetration2.getClCode(), penetration2.getSql());
            addGroupList(TransferOperationName.PENETRATION);
            arrayList.add(penetration2);
            for (S s : getPenetrationDetailList(penetration)) {
                addLog(s.getProcessType(), s.getClCode(), s.getSql());
                addGroupToCurrentList(this.mGroupItems.get(0), s);
                arrayList.add(s);
            }
        }
        return arrayList;
    }
    public S getPenetration(Penetration penetration) {
        return this.mSendDataCreator.getPenetration(penetration);
    }
    public List<S> getPenetrationDetailList(Penetration penetration) {
        return this.mSendDataCreator.getPenetrationDetailList(penetration);
    }
    public void addGroupList(TransferOperationName transferOperationName,S s) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(s);
        this.mGroupItems.add(new GroupItem(arrayList, s.getClName(), transferOperationName, false));
    }
    private void addGroupToCurrentList(GroupItem groupItem, S s) {
        groupItem.getItemList().add(s);
    }


    @Override
    public void addGroupList(TransferOperationName transferOperationName) {

    }

    public void addGroupList(TransferOperationName transferOperationName, T t) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(t);
        this.mGroupItems.add(new GroupItem(arrayList, t.getClName(), transferOperationName, true));
    }

    
    public void addGroupList(TransferOperationName transferOperationName, List<T> list) {
        this.mGroupItems.add(new GroupItem(list, list.get(0).getClName(), transferOperationName, true));
    }

    
    public void addLog(T t) {
        String str;
        try {
            if (Preferences.getTransferUseLog(ContextUtils.getmContext())) {
                DataObjectType dataType = t.getDataType();
                String clCode = t.getClCode();
                String guid = t.getGuid();
                if (t instanceof TigerServiceResult) {
                    str = (String) t.getSendData();
                } else {
                    str = new Gson().toJson(t.getSendData());
                }
                addLog(dataType, clCode, guid, str);
            }
        } catch (Exception e) {
            Log.e("SendCreator", "add log error", e);
        }
    }

    
    public void addLog(ProcessType processType, String str, String str2) {
        if (Preferences.getTransferUseLog(ContextUtils.getmContext())) {
            this.mLogger.log(processType, str, str2);
        }
    }

    
    public void addLog(DataObjectType dataObjectType, String str, String str2, String str3) {
        if (Preferences.getTransferUseLog(ContextUtils.getmContext())) {
            this.mLogger.log(dataObjectType, str, str2, str3);
        }
    }

    
    public GroupItem getGroupItem() {
        ArrayList<GroupItem> arrayList = this.mGroupItems;
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        return this.mGroupItems.get(0);
    }

    
    public void getCabinTransAndCabin() {
        getCabinTransAndCabin(this.mSqlManager.getCabinTrans());
    }

    
    public SendCreatorImpl getCabinTransAndCabin(int i) {
        this.mGroupItems.clear();
        getCabinTransAndCabin(this.mSqlManager.getCabinTrans(i));
        return this;
    }

    
    public List<S> getCabinTransAndCabin(List<CabinTrans> list) {
        ArrayList arrayList = new ArrayList();
        for (CabinTrans cabinTrans : list) {
            S cabinTrans2 = getCabinTrans(cabinTrans);
            addLog(cabinTrans2.getProcessType(), cabinTrans2.getClCode(), cabinTrans2.getSql());
            addGroupList(TransferOperationName.CABINTRANS);
            arrayList.add(cabinTrans2);
            S cabin = getCabin(cabinTrans.cabinID);
            if (!TextUtils.isEmpty(cabin.getSql())) {
                addLog(cabin.getProcessType(), cabin.getClCode(), cabin.getSql());
                addGroupToCurrentList(this.mGroupItems.get(0), cabin);
                arrayList.add(cabin);
            }
        }
        return arrayList;
    }

    
    public S getCabinTrans(CabinTrans cabinTrans) {
        return this.mSendDataCreator.getCabinTrans(cabinTrans);
    }

    
    public S getCabin(int i) {
        return this.mSendDataCreator.getCabin(i);
    }

    
    public void getWhTransfers() {
        getWhTransfers(this.mSqlManager.mapCursorToWhTransfer(-1));
    }

    
    public SendCreatorImpl<T,S> getWhTransfer(PrintSlipModel i) {
        this.mGroupItems.clear();
        getWhTransfers(this.mSqlManager.mapCursorToWhTransfer(i.size()));
        return this;
    }
 
    public List<T> getWhTransfers(List<Sales> list) {
        ArrayList arrayList = new ArrayList();
        for (Sales sales : list) {
            SendCreatorImpl<T, S> whTransfer = getWhTransfer(sales);
            whTransfer.getClass(sales.getWareHouse().getDefinition());
            addLog(whTransfer);
            addGroupList(TransferOperationName.WHTRANSFER,whTransfer);
            arrayList.add(whTransfer);
        }
        return arrayList;
    }

    private void getClass(String definition) {

    }


    public Object getSendData() {
        return null;
    }

    public TransferAutoAsyncTask.AutoTransferFicheSendWcf getWhTransfer(int sLocalFicheRef) {
        return null;
    }
}