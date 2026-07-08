package com.proje.mobilesales.features.driverinformation.model;

import com.proje.mobilesales.core.widget.FichePropertyEditView;
import kotlin.jvm.internal.Intrinsics;


public final class DriverInfos {
    public String driverIdentityNr;
    public String driverName;
    public String driverSurname;
    public String plateNr;
    public String trailerPlateNr;

    public DriverInfos(FichePropertyEditView driverName, FichePropertyEditView driverSurname, FichePropertyEditView driverIdentityNr, FichePropertyEditView plateNr, FichePropertyEditView trailerPlateNr) {
        Intrinsics.checkNotNullParameter(driverName, "driverName");
        Intrinsics.checkNotNullParameter(driverSurname, "driverSurname");
        Intrinsics.checkNotNullParameter(driverIdentityNr, "driverIdentityNr");
        Intrinsics.checkNotNullParameter(plateNr, "plateNr");
        Intrinsics.checkNotNullParameter(trailerPlateNr, "trailerPlateNr");
        this.driverName = driverName.getEdtValue().getText().toString();
        this.driverSurname = driverSurname.getEdtValue().getText().toString();
        this.driverIdentityNr = driverIdentityNr.getEdtValue().getText().toString();
        this.plateNr = plateNr.getEdtValue().getText().toString();
        this.trailerPlateNr = trailerPlateNr.getEdtValue().getText().toString();
    }
}
