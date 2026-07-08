package com.proje.mobilesales.core.utils;

import com.proje.mobilesales.core.enums.FDateField;
import com.proje.mobilesales.features.model.CheckFDateModel;

public class FDateUtils {
    private static FDateUtils mInstance;
    private final CheckFDateModel mFDateModel = new CheckFDateModel();
    public static FDateUtils getInstance() {
        if (mInstance == null) {
            mInstance = new FDateUtils();
        }
        return mInstance;
    }
    private FDateUtils() {}
    public CheckFDateModel getFDateModel() {
        return this.mFDateModel;
    }
    public void updateValues(CheckFDateModel checkFDateModel) {
        this.mFDateModel.setfDateOnOrFiche(checkFDateModel.getfDateOnOrFiche());
        this.mFDateModel.setfDateOnStFiche(checkFDateModel.getfDateOnStFiche());
        this.mFDateModel.setfDateOnClCard(checkFDateModel.getfDateOnClCard());
        this.mFDateModel.setfDateOnClfLine(checkFDateModel.getfDateOnClfLine());
        this.mFDateModel.setfDateOnItems(checkFDateModel.getfDateOnItems());
        this.mFDateModel.setfDateOnSrvCard(checkFDateModel.getfDateOnSrvCard());
        this.mFDateModel.setfDateOnPrcList(checkFDateModel.getfDateOnPrcList());
        this.mFDateModel.setfDateOnPayPlans(checkFDateModel.getfDateOnPayPlans());
        this.mFDateModel.setfDateOnShipInfo(checkFDateModel.getfDateOnShipInfo());
        this.mFDateModel.setfDateOnBnCard(checkFDateModel.getfDateOnBnCard());
        this.mFDateModel.setfDateOnBnkAcc(checkFDateModel.getfDateOnBnkAcc());
        this.mFDateModel.setfDateOnAddTax(checkFDateModel.getfDateOnAddTax());
        this.mFDateModel.setfDateOnWorCabin(checkFDateModel.getfDateOnWorCabin());
        this.mFDateModel.setfDateOnDeletedRecs(checkFDateModel.getfDateOnDeletedRecs());
        this.mFDateModel.setAppVersionOnWorProcess(checkFDateModel.getAppVersionOnWorProcess());
    }

    public void clearValues() {
        this.mFDateModel.setfDateOnOrFiche(0);
        this.mFDateModel.setfDateOnStFiche(0);
        this.mFDateModel.setfDateOnClCard(0);
        this.mFDateModel.setfDateOnClfLine(0);
        this.mFDateModel.setfDateOnItems(0);
        this.mFDateModel.setfDateOnSrvCard(0);
        this.mFDateModel.setfDateOnPrcList(0);
        this.mFDateModel.setfDateOnPayPlans(0);
        this.mFDateModel.setfDateOnShipInfo(0);
        this.mFDateModel.setfDateOnBnCard(0);
        this.mFDateModel.setfDateOnBnkAcc(0);
        this.mFDateModel.setfDateOnAddTax(0);
        this.mFDateModel.setfDateOnWorCabin(0);
        this.mFDateModel.setfDateOnDeletedRecs(0);
        this.mFDateModel.setAppVersionOnWorProcess(0);
    }

    public String getValuesForLog() {
        return "FDate Info   OrFiche:" + this.mFDateModel.getfDateOnOrFiche() + " StFiche:" + this.mFDateModel.getfDateOnStFiche() + " ClCard:" + this.mFDateModel.getfDateOnClCard() + " ClfLine:" + this.mFDateModel.getfDateOnClfLine() + " Items:" + this.mFDateModel.getfDateOnClfLine() + " SrvCard:" + this.mFDateModel.getfDateOnClfLine() + " PrcList:" + this.mFDateModel.getfDateOnClfLine() + " PayPlans:" + this.mFDateModel.getfDateOnClfLine() + " ShipInfo:" + this.mFDateModel.getfDateOnClfLine() + " BnCard:" + this.mFDateModel.getfDateOnClfLine() + " BnkAcc:" + this.mFDateModel.getfDateOnClfLine() + " AddTax:" + this.mFDateModel.getfDateOnClfLine() + " Cabin:" + this.mFDateModel.getfDateOnClfLine() + " DeletedRecs:" + this.mFDateModel.getfDateOnDeletedRecs() + " AppVersion:" + this.mFDateModel.getAppVersionOnWorProcess();
    }

    public static boolean isFDateFieldEnabled(FDateField fDateField) {
        return getInstance().getFDateModel().getFieldValue(fDateField) == 1;
    }
}
