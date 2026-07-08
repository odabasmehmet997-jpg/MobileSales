package com.proje.mobilesales.core.base;

import android.os.Build;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.enums.RouteProcessType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.enums.TransferOperationName;
import com.proje.mobilesales.core.extensions.DateExtensions;
import com.proje.mobilesales.core.interfaces.AppQuerable;
import com.proje.mobilesales.core.interfaces.PrintQuerable;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.printutil.PrintDesignReportType;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.tigerwcf.TigerSelectResult;
import com.proje.mobilesales.core.typs.ParameterTypes;
import com.proje.mobilesales.core.utils.AutoVisitUtils;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.Cabin;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.CabinTrans;
import com.proje.mobilesales.features.dbmodel.Invoice;
import com.proje.mobilesales.features.dbmodel.User;
import com.proje.mobilesales.features.dbmodel.WorRoutePlan;
import com.proje.mobilesales.features.dbmodel.WorRouteProcess;
import com.proje.mobilesales.features.dbmodel.WorTables;
import com.proje.mobilesales.features.dbmodel.WorUserCustomers;
import com.proje.mobilesales.features.gpsinfo.model.database.GpsInfo;
import com.proje.mobilesales.features.model.TransferGetItem;
import com.proje.mobilesales.features.model.UserSettings;
import com.proje.mobilesales.features.notification.model.NotificationFilterModel;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import com.proje.mobilesales.features.penetration.model.Penetration;
import com.proje.mobilesales.features.penetration.model.PenetrationLine;
import com.proje.mobilesales.features.penetration.model.database.UserMainPenetration;
import com.proje.mobilesales.features.todo.model.database.TodoInfoDb;
import com.proje.mobilesales.features.tools.model.database.StartInfo;
import com.proje.mobilesales.features.visit.model.database.VisitInfo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static android.text.TextUtils.isEmpty;

public abstract class BaseQueryCreator<T extends BaseSelectResult, V extends BaseSelectResult.SelectBuilder, S extends BaseServiceResult> implements AppQuerable<T, S>, PrintQuerable<T> {
    private final SharedPreferencesHelper mSharedPreferencesHelper = new SharedPreferencesHelper(ContextUtils.getmContext());
    private final ISqlHelper mSqlHelper;
    private User mUser;
    protected UserSettings mUserSettings;
    public abstract T getBankAccounts(boolean z);
    public abstract T getBanks(boolean z);
    public abstract T getBranches();
    public abstract T getCases();
    public abstract T getCheckSeriGroup(int i2, int i3, SalesType salesType, boolean z, int i4, boolean z2);
    public abstract T getCities();
    public abstract T getCompositeColies(boolean z);
    public abstract T getCountries();
    public abstract T getCreditAggrs();
    public abstract T getCurrRates();
    public abstract T getCurrTypes();
    public abstract T getCustomerRiskTotals(int i2, boolean z);
    public abstract T getCustomers(int i2, boolean z);
    public abstract T getCustomersIncharge(boolean z);
    public abstract T getDiscounts(boolean z);
    public abstract T getDistributionList(int i2);
    public abstract T getDivisions();
    public abstract T getFactories();
    public abstract T getItemAddTaxLines(boolean z);
    public abstract T getItemBarcode(boolean z);
    public abstract T getItemImages(boolean z);
    public abstract T getItemPrices(boolean z);
    public abstract T getItemStock(boolean z);
    public abstract T getItemUnits(boolean z);
    public abstract T getItems(boolean z);
    public abstract T getLoginUserInformation();
    public abstract T getMuhRefCodes();
    public abstract T getOrderAvailableAmounts(ArrayList<String> arrayList);
    public abstract T getOrderAvailableAmountsFromDetailRef(int i2);
    public abstract T getOrderAvailableAmountsFromDetailWithRefs(ArrayList arrayList);
    public abstract T getOrderGrossTotal(int i2);
    public abstract T getPayments(boolean z);
    public abstract T getProjects();
    public abstract T getSalesMansList();
    public abstract T getSalesOrderList(SalesType salesType, int i2, boolean z, ArrayList arrayList);
    public abstract String getSalesmansFilterInSql(int i2);
    public abstract T getSelectedDistributions(int i2, int i3);
    public abstract T getShipAddress(boolean z);
    public abstract T getShipCustomers(String str);
    public abstract T getSpeCodes();
    public abstract T getSuppAsgns();
    public abstract T getTowns();
    public abstract List<T> getTransferList(TransferGetItem transferGetItem, boolean z);
    public abstract T getUsers();
    public abstract T getUsersConnectedToMe(String str);
    public abstract V getV();
    public abstract T getVariantStock(boolean z);
    public abstract T getVariantUnits(boolean z);
    public abstract T getVariants(boolean z);
    public abstract T getWareHouse();
    public abstract T listAllCustomersOnline(String str);
    public BaseQueryCreator(ISqlHelper iSqlHelper, User user, UserSettings userSettings) {
        this.mSqlHelper = iSqlHelper;
        this.mUser = user;
        this.mUserSettings = userSettings;
    }
    public User getUser() {
        return this.mUser;
    }
    public void setUser(User user) {
        this.mUser = user;
    }
    public ISqlHelper getSqlHelper() {
        return this.mSqlHelper;
    }
    public SharedPreferencesHelper getSharedPreferencesHelper() {
        return this.mSharedPreferencesHelper;
    }
    public UserSettings getUserSettings() {
        return this.mUserSettings;
    }
    protected void update(User user) {
        this.mUser = user;
    }
    public T getVisitReason() {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_visit_reason)).withProcessType(ProcessType.GETVISITREASON).withTableDelete(true).withDatabaseSave(true).build();
    } 
    public T getTodo() {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_todo, Integer.valueOf(getUser().getUserId()))).withProcessType(ProcessType.GETTODO).withTableDelete(true).withDatabaseSave(true).build();
    } 
    public T getMarketingMessage() {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_marketing_message, Integer.valueOf(getUser().getUserId()))).withProcessType(ProcessType.GETMARKETINGMESSAGE).withTableDelete(true).withDatabaseSave(true).build();
    } 
    public T getPenetration() {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_penetration)).withOrderBy(ContextUtils.getStringResource(R.string.app_get_penetration_order_by)).withProcessType(ProcessType.GETPENETRATION).withTableDelete(true).withDatabaseSave(true).build();
    } 
    public T getPenetrationDetail() {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_penetration_detail, Integer.valueOf(getUser().getUserId()))).withProcessType(ProcessType.GETPENETRATIONDETAIL).withTableDelete(true).withDatabaseSave(true).build();
    } 
    public T getEmailParam() {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_email_parameters)).withProcessType(ProcessType.GETEMAILPARAM).withTableDelete(true).withDatabaseSave(true).build();
    } 
    public T getEmailTemplates() {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_email_templates)).withProcessType(ProcessType.GETEMAILTEMPLATE).withTableDelete(true).withDatabaseSave(true).build();
    } 
    public T getReports() {
        String paramValue = getSqlHelper().getParamValue(ParameterTypes.ptUserDefinedReports);
        V v = getV();
        if (paramValue.isEmpty()) {
            paramValue = "0";
        }
        return (T) v.withSql(ContextUtils.getStringResource(R.string.app_get_reports, paramValue)).withProcessType(ProcessType.GETREPORTS).withTableDelete(true).withDatabaseSave(true).build();
    } 
    public T getDesFile() {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_design_file, Integer.valueOf(getUser().getUserId()))).withOrderBy(ContextUtils.getStringResource(R.string.app_get_design_file_order_by)).withProcessType(ProcessType.GETDESFILE).withTableDelete(true).withDatabaseSave(true).build();
    } 
    public T getDesFileJson() {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_design_json)).withProcessType(ProcessType.GETDESFILESTRJSON).withTableDelete(true).withDatabaseSave(true).build();
    } 
    public T getUserParameters() {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_user_parameters, Integer.valueOf(getUser().getUserId()))).withProcessType(ProcessType.GETUSERPARAM).withDatabaseSave(true).withTableDelete(true).build();
    } 
    public T getUserDesignedSQL(String str, String str2) {
        return (T) getV().withSql(str).withOrderBy(str2).withProcessType(ProcessType.GETKEYVALUES).withDatabaseSave(false).withTableDelete(false).build();
    } 
    public T getDeviceId() {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_device_id, Integer.valueOf(getUser().getUserId()))).withProcessType(ProcessType.GETUSERDEVICE).withDatabaseSave(false).withTableDelete(false).build();
    } 
    public T insertDeviceId(String str) {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_insert_device_id, Integer.valueOf(getUser().getUserId()), str)).withProcessType(ProcessType.SAVEDEVICEID).build();
    } 
    public T insertDeviceIdWithVersion(String str, String str2) {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_insert_device_id_withVersion, Integer.valueOf(getUser().getUserId()), str, str2)).withProcessType(ProcessType.SAVEDEVICEID).build();
    } 
    public T getPanelVersion() {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_panel_version)).withProcessType(ProcessType.GETPANELVERSION).withTableDelete(false).withDatabaseSave(false).build();
    } 
    public T getLicenseInformation() {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_license_information)).withProcessType(ProcessType.GETLISANSINFO).build();
    } 
    public List<T> updateTodoList(List<TodoInfoDb> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<TodoInfoDb> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(updateTodo(it.next()));
        }
        return arrayList;
    } 
    public T updateTodo(TodoInfoDb todoInfoDb) {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_update_todo_information, todoInfoDb.userNote.replace("'", "''"), Integer.valueOf(todoInfoDb.status), todoInfoDb.status == 2 ? ContextUtils.getStringResource(R.string.app_update_todo_completa_time) : "", Integer.valueOf(todoInfoDb.logicalRef))).withProcessType(ProcessType.UPDATETODOINFO).build();
    } 
    public T getDesignFileNameList(FicheType ficheType) {
        String str;
        int mValue = ficheType == FicheType.WHTRANSFER ? PrintDesignReportType.WHTRANSFER.getMValue() : ficheType.getmValue();
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        if (!baseErp.hasDefaultPrintDesign(ficheType)) {
            str = "";
        } else {
            str = " AND ID = " + baseErp.getDefaultPrintDesign(ficheType);
        }
        return (T) getV().withSql(ContextUtils.formatStringEnglish(R.string.app_get_design_file_name_list, Integer.valueOf(mValue), str)).withProcessType(ProcessType.GETDESFILESTRJSON).withTableDelete(false).withDatabaseSave(true).build();
    } 
    public T insertStartInfo(StartInfo startInfo) {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_insert_start_information, startInfo.date, Integer.valueOf(getUser().getUserId()), startInfo.plate, startInfo.startKm, startInfo.endKm, startInfo.note)).withProcessType(ProcessType.SENDSTARTINFO).build();
    } 
    public List<T> insertGpsInformations(List<GpsInfo> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<GpsInfo> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(insertGpsInformation(it.next()));
        }
        return arrayList;
    } 
    public T insertGpsInformation(GpsInfo gpsInfo) {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_insert_gps_information, Integer.valueOf(getUser().getUserId()), gpsInfo.gpsDate, Double.valueOf(gpsInfo.latitude), Double.valueOf(gpsInfo.longtitude), Double.valueOf(gpsInfo.speed), Double.valueOf(gpsInfo.distance), Double.valueOf(gpsInfo.timeSpan))).withProcessType(ProcessType.SENDGPSINFO).withLogicalRef(gpsInfo.f1251id).build();
    }
    public T getCustomerGpsLocation(String str) {
        if (getUser().getPanelVersion() < 18500) {
            return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_customer_gps_location_withFirm, str)).withProcessType(ProcessType.GETCUSTOMERGPSLOCATION).withDatabaseSave(true).withTableDelete(true).build();
        }
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_customer_gps_location_withShipInfo, str)).withProcessType(ProcessType.GETCUSTOMERGPSLOCATION).withDatabaseSave(true).withTableDelete(true).build();
    }
    public String deleteCustomerGpsLocation(int i2) {
        return ContextUtils.getStringResource(R.string.app_delete_customer_gps_location, Integer.valueOf(i2));
    }
    public T insertPenetration(Penetration penetration) {
        return (T) getV().withSql(insertPenetrationHeader(penetration)).withProcessType(ProcessType.INSERTPENETRATION).withClCode(getSqlHelper().getClCode(penetration.getClRef())).withClName(getSqlHelper().getClName(penetration.getClRef())).withLogicalRef(penetration.getId()).build();
    }
    public T insertPenetrationDetail(Penetration penetration, PenetrationLine penetrationLine) {
        return (T) getV().withSql(insertPenetrationTrans(penetration, penetrationLine)).withProcessType(ProcessType.INSERTPENETRATIONDETAIL).withClCode(getSqlHelper().getClCode(penetration.getClRef())).withClName(getSqlHelper().getClName(penetration.getClRef())).withLogicalRef(penetration.getId()).build();
    }
    public String insertPenetrationLines(Penetration penetration) {
        String str = insertPenetrationHeader(penetration);
        Iterator<PenetrationLine> it = penetration.getPenetrations().iterator();
        while (it.hasNext()) {
            str = str + insertPenetrationTrans(penetration, it.next());
        }
        return str;
    }
    public T insertFicheProcess(TransferOperationName transferOperationName, S s) {
        return (T) getV().withSql(getFicheProcess(s, transferOperationName.getProcessType(), getSqlHelper().getFicheGpsInfo(transferOperationName.getDatabaseClass(), s.getLogicalRef()), ErpCreator.getInstance().getmBaseErp().isActiveAutoVisit() ? AutoVisitUtils.checkAndGetWorProcessRefAfterFicheTransfer(transferOperationName, s.getLogicalRef()) : 0)).build();
    }
    public T deleteFromWorProcess(TransferOperationName transferOperationName, S s) {
        return (T) getV().withSql(ContextUtils.formatStringEnglish(R.string.app_delete_fiche_process, Integer.valueOf(s.getDataReference()), Integer.valueOf(s.getClRef()), Integer.valueOf(StringUtils.convertStringToInt(getUser().getFirmNr())), Integer.valueOf(transferOperationName.getProcessType()), Integer.valueOf(getUser().getUserId()))).build();
    }
    public T insertRouteProcess(TransferOperationName transferOperationName, BaseResult baseResult) {
        return (T) getV().withSql(getInsertRouteProcessSql(transferOperationName, baseResult)).withProcessType(ProcessType.INSERTWORROUTEPROCESS).build();
    }
    private String getInsertRouteProcessSql(TransferOperationName transferOperationName, BaseResult baseResult) {
        String str;
        List table;
        String clientRef;
        String str2 = "";
        String str3 = "";
        String valueOf;
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        List table2 = baseErp.getLogoSqlHelper().getTable(WorRouteProcess.class, "FICHEREF=? AND TYPE=?", new String[]{String.valueOf(baseResult.getLogicalRef()), String.valueOf(RouteProcessType.getRouteProcessType(transferOperationName).getmValue())});
        String str4 = "";
        if (table2 == null || table2.size() <= 0) {
            return "";
        }
        WorRouteProcess worRouteProcess = (WorRouteProcess) table2.get(0);
        String firmNr = baseErp.getErpType() == ErpType.TIGER ? getUser().getFirmNr() : String.valueOf(((Preferences.NetsisUserSettings) baseErp.getUserSettings()).getBranchCode());
        if (worRouteProcess.getRoutePlanRef() > 0) {
            List table3 = baseErp.getLogoSqlHelper().getTable(WorRoutePlan.class, "LOGICALREF=?", new String[]{String.valueOf(worRouteProcess.getRoutePlanRef())});
            if (table3 == null || table3.size() <= 0) {
                clientRef = "";
            } else {
                clientRef = ((WorRoutePlan) table3.get(0)).getClientRef();
            }
        } else {
            if (worRouteProcess.getRouteUserCustomerRef() <= 0 || (table = baseErp.getLogoSqlHelper().getTable(WorUserCustomers.class, "LOGICALREF=?", new String[]{String.valueOf(worRouteProcess.getRouteUserCustomerRef())})) == null || table.size() <= 0) {
                str = "";
                switch (C24741.SwitchMapcomprojemobilesalescoreenumsTransferOperationName[transferOperationName.ordinal()]) {
                    case 1:
                        List table4 = baseErp.getLogoSqlHelper().getTable(VisitInfo.class, "ID=?", new String[]{String.valueOf(baseResult.getLogicalRef())});
                        if (table4 != null && table4.size() > 0) {
                            valueOf = String.valueOf(((VisitInfo) table4.get(0)).logicalRef);
                            str2 = valueOf;
                            str3 = str4;
                            break;
                        }
                        str2 = "";
                        str3 = str2;
                        break;
                    case 2:
                        List table5 = baseErp.getLogoSqlHelper().getTable(UserMainPenetration.class, "ID=?", new String[]{String.valueOf(baseResult.getLogicalRef())});
                        if (table5 != null && table5.size() > 0) {
                            valueOf = ((UserMainPenetration) table5.get(0)).getPnt_GUID();
                            str2 = valueOf;
                            str3 = str4;
                            break;
                        }
                        str2 = "";
                        str3 = str2;
                        break;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                        List<String> ficheNoAndRef = baseErp.getLogoSqlHelper().getFicheNoAndRef(transferOperationName.getDatabaseClass(), baseResult.getLogicalRef());
                        str4 = ficheNoAndRef.get(0);
                        valueOf = ficheNoAndRef.get(1);
                        str2 = valueOf;
                        str3 = str4;
                        break;
                    default:
                        str2 = "";
                        str3 = str2;
                        break;
                }
                return ContextUtils.getStringResource(R.string.app_insert_route_process, Integer.valueOf(worRouteProcess.getRouteUserCustomerRef()), Integer.valueOf(worRouteProcess.getRoutePlanRef()), Integer.valueOf(worRouteProcess.getRouteDayRef()), DateAndTimeUtils.getSqlDate(Boolean.TRUE), str2, str3, Integer.valueOf(worRouteProcess.getType()), str, firmNr, Integer.valueOf(worRouteProcess.getPlannedSequence()), 0, Integer.valueOf(getUser().getUserId()), worRouteProcess.getCreatedDate());
            }
            clientRef = ((WorUserCustomers) table.get(0)).getClientRef();
        }
        str = clientRef;
        switch (C24741.SwitchMapcomprojemobilesalescoreenumsTransferOperationName[transferOperationName.ordinal()]) {
        }
        return ContextUtils.getStringResource(R.string.app_insert_route_process, Integer.valueOf(worRouteProcess.getRouteUserCustomerRef()), Integer.valueOf(worRouteProcess.getRoutePlanRef()), Integer.valueOf(worRouteProcess.getRouteDayRef()), DateAndTimeUtils.getSqlDate(Boolean.TRUE), str2, str3, Integer.valueOf(worRouteProcess.getType()), str, firmNr, Integer.valueOf(worRouteProcess.getPlannedSequence()), 0, Integer.valueOf(getUser().getUserId()), worRouteProcess.getCreatedDate());
    }

    public abstract TigerSelectResult getWorDeletedRecsExistsQuery();

    static  class C24741 {
        static final  int[] SwitchMapcomprojemobilesalescoreenumsTransferOperationName;

        static {
            int[] iArr = new int[TransferOperationName.values().length];
            SwitchMapcomprojemobilesalescoreenumsTransferOperationName = iArr;
            try {
                iArr[TransferOperationName.VISIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.PENETRATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.INVOICE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.DISPATCH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.RETURN_INVOICE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.RETURN_DISPATCH.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.ONE_TO_ONE_CHANGE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.ORDER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.CASH.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.CREDIT_CARD.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.CASE_CASH.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.CHEQUE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.DEED.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.RETAIL_INVOICE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.RETAIL_RETURN_INVOICE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }
    public T getWorRoute() {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_wor_route, Integer.valueOf(getUser().getUserId()))).withProcessType(ProcessType.GETWORROUTE).withTableDelete(true).withDatabaseSave(true).build();
    }
    public T getWorRouteDay() {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_wor_route_day, Integer.valueOf(getUser().getUserId()))).withProcessType(ProcessType.GETWORROUTEDAY).withTableDelete(true).withDatabaseSave(true).build();
    }
    public T getWorRoutePlan() {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_wor_route_plan, Integer.valueOf(getUser().getUserId()))).withProcessType(ProcessType.GETWORROUTEPLAN).withTableDelete(true).withDatabaseSave(true).build();
    }
    public T getWorUserCustomers() {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_wor_usercustomers, Integer.valueOf(getUser().getUserId()))).withProcessType(ProcessType.GETWORUSERCUSTOMERS).withTableDelete(true).withDatabaseSave(true).build();
    }
    public T getServerTime() {
        return (T) getV().withSql("SELECT CONVERT(VARCHAR(8),GETDATE(),114) WORKTIME").withProcessType(ProcessType.LOGIN).withTableDelete(false).withDatabaseSave(false).build();
    }
    public T getServerLongTime() {
        return (T) getV().withSql("SELECT CONCAT(CONVERT(VARCHAR,GETDATE(),104),' ',CONVERT(VARCHAR,GETDATE(),114)) WORKTIME").withProcessType(ProcessType.LOGIN).withTableDelete(false).withDatabaseSave(false).build();
    }
    public T insertCabinTrans(BaseResult baseResult) {
        String str;
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        List table = baseErp.getLogoSqlHelper().getTable(CabinTrans.class, "LOCALFICHEREF=? ", new String[]{String.valueOf(baseResult.getLogicalRef())});
        int i2 = 0;
        if (table != null && table.size() > 0) {
            CabinTrans cabinTrans = (CabinTrans) table.get(0);
            int i3 = cabinTrans.f1224id;
            int i4 = cabinTrans.cabinID;
            int i5 = cabinTrans.trtype;
            List<String> ficheNoAndRef = baseErp.getLogoSqlHelper().getFicheNoAndRef(Invoice.class, baseResult.getLogicalRef());
            String str2 = ficheNoAndRef.get(0);
            int convertStringToInt = StringUtils.convertStringToInt(ficheNoAndRef.get(1));
            String str3 = cabinTrans.clientCode;
            int i6 = cabinTrans.clientRef;
            str = ContextUtils.getStringResource(R.string.app_insert_cabin_trans, Integer.valueOf(i4), Integer.valueOf(i5), str2, Integer.valueOf(convertStringToInt), str3, Integer.valueOf(i6), cabinTrans.date, Integer.valueOf(cabinTrans.salesmanRef), Integer.valueOf(cabinTrans.trcode), baseErp.getUser().getFirmNr(), baseErp.getUser().getPeridodNr());
            i2 = i3;
        } else {
            str = "";
        }
        return (T) getV().withSql(str).withProcessType(ProcessType.INSERTCABINTRANS).withLogicalRef(i2).build();
    }
    public T insertWorCabinTrans(CabinTrans cabinTrans) {
        int i2 = cabinTrans.cabinID;
        int i3 = cabinTrans.trtype;
        String str = cabinTrans.trFicheNo;
        int i4 = cabinTrans.trFicheRef;
        String str2 = cabinTrans.clientCode;
        int i5 = cabinTrans.clientRef;
        String str3 = cabinTrans.date;
        int i6 = cabinTrans.salesmanRef;
        int i7 = cabinTrans.trcode;
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_insert_cabin_trans, Integer.valueOf(i2), Integer.valueOf(i3), str, Integer.valueOf(i4), str2, Integer.valueOf(i5), str3, Integer.valueOf(i6), Integer.valueOf(i7), baseErp.getUser().getFirmNr(), baseErp.getUser().getPeridodNr())).withProcessType(ProcessType.INSERTCABINTRANS).withLogicalRef(cabinTrans.f1224id).build();
    }
    public T updateWorCabin(int i2) {
        String str;
        List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Cabin.class, "ID=? AND ISTRANSFER=?", new String[]{String.valueOf(i2), String.valueOf(0)});
        if (table != null && table.size() > 0) {
            Cabin cabin = (Cabin) table.get(0);
            str = ContextUtils.getStringResource(R.string.app_update_cabin, Integer.valueOf(cabin.locInfo), Integer.valueOf(cabin.status), cabin.clientCode, Integer.valueOf(cabin.salesmanRef), cabin.modifiedDate, Integer.valueOf(i2));
        } else {
            str = "";
        }
        return (T) getV().withSql(str).withProcessType(ProcessType.UPDATECABIN).withLogicalRef(i2).build();
    }
    public T getWorTables() {
        List<WorTables> table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(WorTables.class);
        StringBuilder sb = new StringBuilder();
        if (table == null || table.size() == 0) {
            sb.append("SELECT 'NODATA' NAME");
        } else {
            for (WorTables worTables : table) {
                sb.append("SELECT '");
                sb.append(worTables.getName());
                sb.append("' NAME UNION ");
            }
            sb.append("SELECT 'NODATA' NAME");
        }
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_wortables, sb.toString())).withProcessType(ProcessType.GETWORTABLES).withTableDelete(false).withDatabaseSave(false).build();
    }
    public T getSalesManagers() {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_salesmanagers, Integer.valueOf(getUser().getUserId()))).withProcessType(ProcessType.GETWORUSERS).withTableDelete(false).withDatabaseSave(false).build();
    }
    public T insertDiffLog(int i2, int i3, String str, String str2) {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_insert_diff_log, getUser().getFirmNr(), Integer.valueOf(i2), Integer.valueOf(i3), str, DateAndTimeUtils.getSqlDate(Boolean.FALSE), str2)).withProcessType(ProcessType.INSERTWORLOG).build();
    }
    public T getVisitIdFromWorProcess(VisitInfo visitInfo) {
        return (T) getV().withSql(ContextUtils.formatStringEnglish(R.string.app_get_visit_process, DateAndTimeUtils.convertDateSqlDate(visitInfo.visitDate), Integer.valueOf(visitInfo.clRef), Integer.valueOf(StringUtils.convertStringToInt(getUser().getFirmNr())), 9, Integer.valueOf(getUser().getUserId()), Integer.valueOf(visitInfo.userTitle), getSqlHelper().getClCode(visitInfo.clRef))).withProcessType(ProcessType.GETWORPROCESS).build();
    }
    public T getUserNotificationsToBeNotified() {
        return (T) getV().withSql(ContextUtils.formatStringEnglish(R.string.app_get_user_notifications_to_be_notified, Integer.valueOf(getUser().getUserId()))).withProcessType(ProcessType.GETUSERNOTIFICATIONS).build();
    }
    public T getUserNotifications(int i2, int i3, NotificationFilterModel notificationFilterModel) {
        String str;
        String str2;
        Integer valueOf = Integer.valueOf(getUser().getUserId());
        Integer valueOf2 = Integer.valueOf(i2);
        Integer valueOf3 = Integer.valueOf(i3);
        String searchText = isEmpty(notificationFilterModel.getSearchText()) ? "" : notificationFilterModel.getSearchText();
        if (notificationFilterModel.getStartDate() == null) {
            str = "";
        } else {
            str = DateAndTimeUtils.formatFromDate(notificationFilterModel.getStartDate(), "yyyy-MM-dd") + " 00:00:00";
        }
        if (notificationFilterModel.getEndDate() == null) {
            str2 = "";
        } else {
            str2 = DateAndTimeUtils.formatFromDate(notificationFilterModel.getEndDate(), "yyyy-MM-dd") + " 23:59:59";
        }
        return (T) getV().withSql(ContextUtils.formatStringEnglish(R.string.app_get_user_notifications, valueOf, valueOf2, valueOf3, searchText, str, str2, notificationFilterModel.getShowDeleted() ? " NU.STATUS = 3" : " NU.STATUS>0 AND NU.STATUS <> 3", notificationFilterModel.getShowDeleted() ? " N.STATUS = 3" : " N.STATUS <> 3")).withProcessType(ProcessType.GETUSERNOTIFICATIONS).build();
    }
    public T deleteNotification(NotificationModel notificationModel) {
        String formatStringEnglish;
        if (notificationModel.getSenderRef() == getUser().getUserId()) {
            formatStringEnglish = ContextUtils.formatStringEnglish(R.string.app_delete_wor_notification, Integer.valueOf(notificationModel.getNotificationId()));
        } else {
            formatStringEnglish = ContextUtils.formatStringEnglish(R.string.app_delete_wor_notifiedUser, Integer.valueOf(notificationModel.getNotifiedUserId()));
        }
        return (T) getV().withSql(formatStringEnglish).withProcessType(ProcessType.GETKEYVALUEPAIR).build();
    }
    public T checkNotificationAvailableToDelete(NotificationModel notificationModel) {
        String formatStringEnglish;
        if (notificationModel.getSenderRef() == getUser().getUserId()) {
            formatStringEnglish = ContextUtils.formatStringEnglish(R.string.app_check_worNotification_availableToDelete, notificationModel.getNotificationGuid());
        } else {
            formatStringEnglish = ContextUtils.formatStringEnglish(R.string.app_check_worNotifiedUser_availableToDelete, Integer.valueOf(notificationModel.getNotifiedUserId()));
        }
        return (T) getV().withSql(formatStringEnglish).withProcessType(ProcessType.GETKEYVALUEPAIR).build();
    }
    public T getNotificationDetailsForSender(int i2) {
        return (T) getV().withSql(ContextUtils.formatStringEnglish(R.string.app_getNotificationInfo_for_sender, Integer.valueOf(getUser().getUserId()), Integer.valueOf(i2))).withProcessType(ProcessType.GETNOTIFIEDUSERS).build();
    }
    public T getNotification(int i2) {
        return (T) getV().withSql(ContextUtils.formatStringEnglish(R.string.app_get_notification, Integer.valueOf(i2))).withProcessType(ProcessType.GETNOTIFICATION).build();
    }
    public T updateNotifiedUserNotificationAsRead(int i2) {
        return (T) getV().withSql(ContextUtils.formatStringEnglish(R.string.app_updateNotifiedUserNotification_asRead, Integer.valueOf(i2))).withProcessType(ProcessType.GETKEYVALUEPAIR).build();
    }
    public T getNotifiedUser(int i2) {
        return (T) getV().withSql(ContextUtils.formatStringEnglish(R.string.app_getNotifiedUser, Integer.valueOf(i2))).withProcessType(ProcessType.GETNOTIFIEDUSERS).build();
    }
    public T updateNotificationAsReadIfAllUsersRead(String str) {
        return (T) getV().withSql(ContextUtils.formatStringEnglish(R.string.app_updateNotification_ifAllRead, str)).withProcessType(ProcessType.GETKEYVALUEPAIR).build();
    }
    public T updateNotificationAsDeliveredIfAllDelivered(String str) {
        return (T) getV().withSql(ContextUtils.formatStringEnglish(R.string.app_updateNotification_ifAllDelivered, str)).withProcessType(ProcessType.GETKEYVALUEPAIR).build();
    }
    public T updateNotifiedUserNotificationAsDelivered(int i2) {
        return (T) getV().withSql(ContextUtils.formatStringEnglish(R.string.app_updateNotifiedUserNotification_asDelivered, Integer.valueOf(i2))).withProcessType(ProcessType.GETKEYVALUEPAIR).build();
    }
    public T saveNotification(NotificationModel notificationModel) {
        String formatStringEnglish = "";
        Date date = DateExtensions.toDate(notificationModel.getDateSend(), "dd.MM.yyyy HH:mm:ss");
        Date time = Calendar.getInstance().getTime();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (date != null && !date.toInstant().isBefore(time.toInstant())) {
                formatStringEnglish = ContextUtils.formatStringEnglish(R.string.app_save_worNotification, Integer.valueOf(ErpCreator.getInstance().getmBaseErp().getUser().getUserId()), notificationModel.getMessage(), DateAndTimeUtils.convertDateSqlDate(notificationModel.getDateSend()), notificationModel.getStatus(), notificationModel.getNotificationGuid(), notificationModel.getTitle(), Integer.valueOf(notificationModel.getWorkingHours()));
            } else {
                formatStringEnglish = ContextUtils.formatStringEnglish(R.string.app_save_worNotificationWithGetDate, Integer.valueOf(ErpCreator.getInstance().getmBaseErp().getUser().getUserId()), notificationModel.getMessage(), notificationModel.getStatus(), notificationModel.getNotificationGuid(), notificationModel.getTitle(), Integer.valueOf(notificationModel.getWorkingHours()));
            }
        }
        return (T) getV().withSql(formatStringEnglish).withProcessType(ProcessType.GETKEYVALUEPAIR).build();
    }
    public T saveNotifiedUsers(NotifiedUserModel notifiedUserModel) {
        return (T) getV().withSql(ContextUtils.formatStringEnglish(R.string.app_save_worNotifiedUser, notifiedUserModel.getStatus(), notifiedUserModel.getNotificationGuid(), notifiedUserModel.getNotifiedUserGuid(), Integer.valueOf(notifiedUserModel.getUserRef()))).withProcessType(ProcessType.GETKEYVALUEPAIR).build();
    }
    public T getNotificationByGuid(String str) {
        return (T) getV().withSql(ContextUtils.formatStringEnglish(R.string.app_get_notification_byGuid, str)).withProcessType(ProcessType.GETNOTIFICATION).build();
    }
    public T isServerTimeInWorkingHours() {
        return (T) getV().withSql(ContextUtils.formatStringEnglish(R.string.app_isServerTimeInWorkingHours, Integer.valueOf(getUser().getUserId()))).withProcessType(ProcessType.GETKEYVALUEPAIR).build();
    }
    public T getWorUserCount() {
        return (T) getV().withSql(ContextUtils.getStringResource(R.string.app_get_wor_user_count)).withProcessType(ProcessType.GETKEYVALUEPAIR).build();
    }
}
