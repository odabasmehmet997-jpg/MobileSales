package com.proje.mobilesales.core.interfaces;

import com.proje.mobilesales.core.base.BaseResult;
import com.proje.mobilesales.core.base.BaseSelectResult;
import com.proje.mobilesales.core.base.BaseServiceResult;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.TransferOperationName;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.CabinTrans;
import com.proje.mobilesales.features.gpsinfo.model.database.CustGpsInfo;
import com.proje.mobilesales.features.gpsinfo.model.database.GpsInfo;
import com.proje.mobilesales.features.notification.model.NotificationFilterModel;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import com.proje.mobilesales.features.penetration.model.Penetration;
import com.proje.mobilesales.features.penetration.model.PenetrationLine;
import com.proje.mobilesales.features.todo.model.database.TodoInfoDb;
import com.proje.mobilesales.features.tools.model.database.StartInfo;
import com.proje.mobilesales.features.visit.model.database.VisitInfo;
import java.util.List;
public interface AppQuerable<T extends BaseSelectResult, S extends BaseServiceResult> {
    T checkNotificationAvailableToDelete(NotificationModel notificationModel);
    String deleteCustomerGpsLocation(int i2);

    T deleteFromWorProcess(TransferOperationName transferOperationName, S s);

    T deleteNotification(NotificationModel notificationModel);

    T execBarcodeSp(String str);

    T getCabins(boolean z);

    T getCustomerGpsLocation(String str);

    T getDefOrder();

    T getDefOrderDetail();

    T getDesFile();

    T getDesFileJson();

    T getDesignFileNameList(FicheType ficheType);

    T getDeviceId();

    T getEmailParam();

    T getEmailTemplates();

    String getFicheProcess(S s, int i2, double[] dArr, int i3);

    T getLicenseInformation();

    T getMarketingMessage();

    T getNotification(int i2);

    T getNotificationByGuid(String str);

    T getNotificationDetailsForSender(int i2);

    T getNotifiedUser(int i2);

    T getPanelVersion();

    T getPenetration();

    T getPenetrationDetail();

    T getReports();

    T getSalesManagers();

    T getServerLongTime();

    T getServerTime();

    T getTodo();

    T getUserDesignedSQL(String str, String str2);

    T getUserNotifications(int i2, int i3, NotificationFilterModel notificationFilterModel);

    T getUserNotificationsToBeNotified();

    T getUserParameters();

    T getVisitIdFromWorProcess(VisitInfo visitInfo);

    T getVisitReason();

    T getWorProcess(TransferOperationName transferOperationName, S s);

    T getWorRoute();

    T getWorRouteDay();

    T getWorRoutePlan();

    T getWorTables();

    T getWorUserCount();

    T getWorUserCustomers();

    T insertCabinTrans(BaseResult baseResult);

    T insertCustomerGpsLocation(CustGpsInfo custGpsInfo);

    T insertDeviceId(String str);

    T insertDeviceIdWithVersion(String str, String str2);

    T insertDiffLog(int i2, int i3, String str, String str2);

    T insertFicheProcess(TransferOperationName transferOperationName, S s);

    T insertGpsInformation(GpsInfo gpsInfo);

    List<T> insertGpsInformations(List<GpsInfo> list);

    T insertPenetration(Penetration penetration);

    T insertPenetrationDetail(Penetration penetration, PenetrationLine penetrationLine);

    String insertPenetrationHeader(Penetration penetration);

    String insertPenetrationLine(Penetration penetration, PenetrationLine penetrationLine);

    String insertPenetrationLines(Penetration penetration);

    String insertPenetrationTrans(Penetration penetration, PenetrationLine penetrationLine);

    T insertRouteProcess(TransferOperationName transferOperationName, BaseResult baseResult);

    T insertStartInfo(StartInfo startInfo);

    T insertTodoWorProc(TodoInfoDb todoInfoDb);

    T insertVisit(VisitInfo visitInfo);

    T insertWorCabinTrans(CabinTrans cabinTrans);

    T isServerTimeInWorkingHours();

    T loadCurrencyBalances(int i2, String str, String str2);

    T saveNotification(NotificationModel notificationModel);

    T saveNotifiedUsers(NotifiedUserModel notifiedUserModel);

    T updateNotificationAsDeliveredIfAllDelivered(String str);

    T updateNotificationAsReadIfAllUsersRead(String str);

    T updateNotifiedUserNotificationAsDelivered(int i2);

    T updateNotifiedUserNotificationAsRead(int i2);

    T updateTodo(TodoInfoDb todoInfoDb);

    List<? extends T> updateTodoList(List<TodoInfoDb> list);

    T updateWorCabin(int i2);
}
