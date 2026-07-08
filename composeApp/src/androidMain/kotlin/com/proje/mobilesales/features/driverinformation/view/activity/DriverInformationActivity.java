package com.proje.mobilesales.features.driverinformation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.appcompat.app.ActionBar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.widget.FichePropertyEditView;
import com.proje.mobilesales.features.driverinformation.model.DriverInfos;
import com.proje.mobilesales.features.driverinformation.model.database.EDispatchAdditionalInfo;
import com.proje.mobilesales.features.driverinformation.repository.DriverInformationRepository;
import com.proje.mobilesales.features.driverinformation.viewmodel.DriverInformationViewModel;
import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class DriverInformationActivity extends BaseErpActivity {
    public static final Companion Companion = new Companion(null);
    private static final String EXTRA_SALES_FICHE_MODE = EDispatchAdditionalInfo.class.getName() + ".EXTRA_SALES_FICHE_MODE";
    private static final String STATE_SALES_EDISPATCH_ADDITIONAL = "state:salesEDispatchAdditional";
    private static final String STATE_SALES_FICHE_MODE = "state:salesFicheMode";
    private EDispatchAdditionalInfo additionalInfo;
    private FichePropertyEditView driverIdentityNr;
    private final List<LinearLayout> driverInfoLayoutsList;
    private List<DriverInfos> driverInfoList;
    private FichePropertyEditView driverName;
    private FichePropertyEditView driverPlateNr;
    private FichePropertyEditView driverSurname;
    private FichePropertyEditView driverTrailerPlateNr;
    private FicheMode ficheMode;
    private final DriverInformationRepository repository;
    private final DriverInformationViewModel viewModel;

    public DriverInformationActivity() {
        DriverInformationRepository driverInformationRepository = new DriverInformationRepository();
        this.repository = driverInformationRepository;
        this.viewModel = new DriverInformationViewModel(driverInformationRepository);
        this.driverInfoLayoutsList = new ArrayList();
        this.driverInfoList = new ArrayList();
    }

    public DriverInformationRepository getRepository() {
        return this.repository;
    }

    public DriverInformationViewModel getViewModel() {
        return this.viewModel;
    }
     public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_driver_information);
        init(bundle);
    }
    public void init(Bundle bundle) {
        setToolbar();
        ActionBar supportActionBar = getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setTitle(R.string.menu_sales_driver_information);
        List<LinearLayout> list = this.driverInfoLayoutsList;
        View findViewById = findViewById(R.id.driver1);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        list.add((LinearLayout) findViewById);
        List<LinearLayout> list2 = this.driverInfoLayoutsList;
        View findViewById2 = findViewById(R.id.driver2);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        list2.add((LinearLayout) findViewById2);
        List<LinearLayout> list3 = this.driverInfoLayoutsList;
        View findViewById3 = findViewById(R.id.driver3);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        list3.add((LinearLayout) findViewById3);
        if (bundle != null) {
            this.additionalInfo = bundle.getParcelable(STATE_SALES_EDISPATCH_ADDITIONAL);
            this.ficheMode = FicheMode.values()[bundle.getInt(STATE_SALES_FICHE_MODE, 0)];
        } else {
            this.additionalInfo = getIntent().getParcelableExtra(IntentExtraName.EXTRA_EDESPATCH_EXTRA_INFO);
            this.ficheMode = (FicheMode) getIntent().getSerializableExtra(EXTRA_SALES_FICHE_MODE);
        }
        if (this.additionalInfo == null) {
            this.additionalInfo = new EDispatchAdditionalInfo();
        }
        FicheMode ficheMode = this.ficheMode;
        if (ficheMode == FicheMode.EDIT || ficheMode == FicheMode.ANALYSE) {
            getDriverInfoFromAdditionalInfo();
        } else {
            getDriverInfoFromPreferences();
        }
        setEnableDriverInfoEditTexts();
        FicheMode ficheMode2 = FicheMode.NEW;
    }

    private Unit getDriverInfoFromPreferences() {
        EditText edtValue;
        EditText edtValue2;
        EditText edtValue3;
        EditText edtValue4;
        EditText edtValue5;
        String driverInfo = Preferences.getDriverInfo(this);
        Intrinsics.checkNotNull(driverInfo);
        if (driverInfo.length() > 0) {
            Object fromJson = new Gson().fromJson(driverInfo, new TypeToken<ArrayList<DriverInfos>>() {
            }.getType());
            Intrinsics.checkNotNullExpressionValue(fromJson, "fromJson(...)");
            this.driverInfoList = (List) fromJson;
            int size = this.driverInfoLayoutsList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.driverName = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverName);
                this.driverSurname = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverSurname);
                this.driverIdentityNr = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverIdentityNr);
                this.driverPlateNr = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverPlateNr);
                this.driverTrailerPlateNr = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverTrailerPlateNr);
                FichePropertyEditView fichePropertyEditView = this.driverName;
                if (fichePropertyEditView != null && (edtValue5 = fichePropertyEditView.getEdtValue()) != null) {
                    DriverInfos driverInfos = this.driverInfoList.get(i2);
                    Intrinsics.checkNotNull(driverInfos);
                    edtValue5.setText(driverInfos.driverName);
                }
                FichePropertyEditView fichePropertyEditView2 = this.driverSurname;
                if (fichePropertyEditView2 != null && (edtValue4 = fichePropertyEditView2.getEdtValue()) != null) {
                    DriverInfos driverInfos2 = this.driverInfoList.get(i2);
                    Intrinsics.checkNotNull(driverInfos2);
                    edtValue4.setText(driverInfos2.driverSurname);
                }
                FichePropertyEditView fichePropertyEditView3 = this.driverIdentityNr;
                if (fichePropertyEditView3 != null && (edtValue3 = fichePropertyEditView3.getEdtValue()) != null) {
                    DriverInfos driverInfos3 = this.driverInfoList.get(i2);
                    Intrinsics.checkNotNull(driverInfos3);
                    edtValue3.setText(driverInfos3.driverIdentityNr);
                }
                FichePropertyEditView fichePropertyEditView4 = this.driverPlateNr;
                if (fichePropertyEditView4 != null && (edtValue2 = fichePropertyEditView4.getEdtValue()) != null) {
                    DriverInfos driverInfos4 = this.driverInfoList.get(i2);
                    Intrinsics.checkNotNull(driverInfos4);
                    edtValue2.setText(driverInfos4.plateNr);
                }
                FichePropertyEditView fichePropertyEditView5 = this.driverTrailerPlateNr;
                if (fichePropertyEditView5 != null && (edtValue = fichePropertyEditView5.getEdtValue()) != null) {
                    DriverInfos driverInfos5 = this.driverInfoList.get(i2);
                    Intrinsics.checkNotNull(driverInfos5);
                    edtValue.setText(driverInfos5.trailerPlateNr);
                }
            }
        }
        return Unit.INSTANCE;
    }

    private void setEnableDriverInfoEditTexts() {
        if (this.ficheMode != FicheMode.ANALYSE) {
            return;
        }
        int size = this.driverInfoLayoutsList.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.driverName = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverName);
            this.driverSurname = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverSurname);
            this.driverIdentityNr = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverIdentityNr);
            this.driverPlateNr = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverPlateNr);
            this.driverTrailerPlateNr = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverTrailerPlateNr);
            FichePropertyEditView fichePropertyEditView = this.driverName;
            EditText edtValue = fichePropertyEditView != null ? fichePropertyEditView.getEdtValue() : null;
            if (edtValue != null) {
                edtValue.setEnabled(false);
            }
            FichePropertyEditView fichePropertyEditView2 = this.driverSurname;
            EditText edtValue2 = fichePropertyEditView2 != null ? fichePropertyEditView2.getEdtValue() : null;
            if (edtValue2 != null) {
                edtValue2.setEnabled(false);
            }
            FichePropertyEditView fichePropertyEditView3 = this.driverIdentityNr;
            EditText edtValue3 = fichePropertyEditView3 != null ? fichePropertyEditView3.getEdtValue() : null;
            if (edtValue3 != null) {
                edtValue3.setEnabled(false);
            }
            FichePropertyEditView fichePropertyEditView4 = this.driverPlateNr;
            EditText edtValue4 = fichePropertyEditView4 != null ? fichePropertyEditView4.getEdtValue() : null;
            if (edtValue4 != null) {
                edtValue4.setEnabled(false);
            }
            FichePropertyEditView fichePropertyEditView5 = this.driverTrailerPlateNr;
            EditText edtValue5 = fichePropertyEditView5 != null ? fichePropertyEditView5.getEdtValue() : null;
            if (edtValue5 != null) {
                edtValue5.setEnabled(false);
            }
        }
    }

    private Unit getDriverInfoFromAdditionalInfo() {
        EditText edtValue;
        EditText edtValue2;
        EditText edtValue3;
        EditText edtValue4;
        EditText edtValue5;
        EditText edtValue6;
        EditText edtValue7;
        EditText edtValue8;
        EditText edtValue9;
        EditText edtValue10;
        EditText edtValue11;
        EditText edtValue12;
        EditText edtValue13;
        EditText edtValue14;
        EditText edtValue15;
        int size = this.driverInfoLayoutsList.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.driverName = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverName);
            this.driverSurname = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverSurname);
            this.driverIdentityNr = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverIdentityNr);
            this.driverPlateNr = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverPlateNr);
            this.driverTrailerPlateNr = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverTrailerPlateNr);
            if (i2 == 0) {
                FichePropertyEditView fichePropertyEditView = this.driverName;
                if (fichePropertyEditView != null && (edtValue15 = fichePropertyEditView.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo);
                    edtValue15.setText(eDispatchAdditionalInfo.firstDriverName);
                }
                FichePropertyEditView fichePropertyEditView2 = this.driverSurname;
                if (fichePropertyEditView2 != null && (edtValue14 = fichePropertyEditView2.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo2 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo2);
                    edtValue14.setText(eDispatchAdditionalInfo2.firstDriverLastName);
                }
                FichePropertyEditView fichePropertyEditView3 = this.driverIdentityNr;
                if (fichePropertyEditView3 != null && (edtValue13 = fichePropertyEditView3.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo3 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo3);
                    edtValue13.setText(eDispatchAdditionalInfo3.firstDriverIdentityNr);
                }
                FichePropertyEditView fichePropertyEditView4 = this.driverPlateNr;
                if (fichePropertyEditView4 != null && (edtValue12 = fichePropertyEditView4.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo4 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo4);
                    edtValue12.setText(eDispatchAdditionalInfo4.firstDriverPlate);
                }
                FichePropertyEditView fichePropertyEditView5 = this.driverTrailerPlateNr;
                if (fichePropertyEditView5 != null && (edtValue11 = fichePropertyEditView5.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo5 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo5);
                    edtValue11.setText(eDispatchAdditionalInfo5.firstTrailerPlate);
                }
            }
            if (i2 == 1) {
                FichePropertyEditView fichePropertyEditView6 = this.driverName;
                if (fichePropertyEditView6 != null && (edtValue10 = fichePropertyEditView6.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo6 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo6);
                    edtValue10.setText(eDispatchAdditionalInfo6.secondDriverName);
                }
                FichePropertyEditView fichePropertyEditView7 = this.driverSurname;
                if (fichePropertyEditView7 != null && (edtValue9 = fichePropertyEditView7.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo7 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo7);
                    edtValue9.setText(eDispatchAdditionalInfo7.secondDriverLastName);
                }
                FichePropertyEditView fichePropertyEditView8 = this.driverIdentityNr;
                if (fichePropertyEditView8 != null && (edtValue8 = fichePropertyEditView8.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo8 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo8);
                    edtValue8.setText(eDispatchAdditionalInfo8.secondDriverIdentityNr);
                }
                FichePropertyEditView fichePropertyEditView9 = this.driverPlateNr;
                if (fichePropertyEditView9 != null && (edtValue7 = fichePropertyEditView9.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo9 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo9);
                    edtValue7.setText(eDispatchAdditionalInfo9.secondDriverPlate);
                }
                FichePropertyEditView fichePropertyEditView10 = this.driverTrailerPlateNr;
                if (fichePropertyEditView10 != null && (edtValue6 = fichePropertyEditView10.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo10 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo10);
                    edtValue6.setText(eDispatchAdditionalInfo10.secondTrailerPlate);
                }
            }
            if (i2 == 2) {
                FichePropertyEditView fichePropertyEditView11 = this.driverName;
                if (fichePropertyEditView11 != null && (edtValue5 = fichePropertyEditView11.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo11 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo11);
                    edtValue5.setText(eDispatchAdditionalInfo11.thirdDriverName);
                }
                FichePropertyEditView fichePropertyEditView12 = this.driverSurname;
                if (fichePropertyEditView12 != null && (edtValue4 = fichePropertyEditView12.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo12 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo12);
                    edtValue4.setText(eDispatchAdditionalInfo12.thirdDriverLastName);
                }
                FichePropertyEditView fichePropertyEditView13 = this.driverIdentityNr;
                if (fichePropertyEditView13 != null && (edtValue3 = fichePropertyEditView13.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo13 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo13);
                    edtValue3.setText(eDispatchAdditionalInfo13.thirdDriverIdentityNr);
                }
                FichePropertyEditView fichePropertyEditView14 = this.driverPlateNr;
                if (fichePropertyEditView14 != null && (edtValue2 = fichePropertyEditView14.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo14 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo14);
                    edtValue2.setText(eDispatchAdditionalInfo14.thirdDriverPlate);
                }
                FichePropertyEditView fichePropertyEditView15 = this.driverTrailerPlateNr;
                if (fichePropertyEditView15 != null && (edtValue = fichePropertyEditView15.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo15 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo15);
                    edtValue.setText(eDispatchAdditionalInfo15.thirdTrailerPlate);
                }
            }
        }
        return Unit.INSTANCE;
    }

    private void setAdditionalInfoFromDriverLayout() {
        EditText edtValue;
        EditText edtValue2;
        EditText edtValue3;
        EditText edtValue4;
        EditText edtValue5;
        EditText edtValue6;
        EditText edtValue7;
        EditText edtValue8;
        EditText edtValue9;
        EditText edtValue10;
        EditText edtValue11;
        EditText edtValue12;
        EditText edtValue13;
        EditText edtValue14;
        EditText edtValue15;
        EditText edtValue16;
        EditText edtValue17;
        EditText edtValue18;
        EditText edtValue19;
        EditText edtValue20;
        EditText edtValue21;
        EditText edtValue22;
        EditText edtValue23;
        EditText edtValue24;
        EditText edtValue25;
        EditText edtValue26;
        EditText edtValue27;
        EditText edtValue28;
        EditText edtValue29;
        EditText edtValue30;
        int size = this.driverInfoLayoutsList.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.driverName = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverName);
            this.driverSurname = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverSurname);
            this.driverIdentityNr = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverIdentityNr);
            this.driverPlateNr = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverPlateNr);
            this.driverTrailerPlateNr = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverTrailerPlateNr);
            Editable editable = null;
            if (i2 == 0) {
                EDispatchAdditionalInfo eDispatchAdditionalInfo = this.additionalInfo;
                Intrinsics.checkNotNull(eDispatchAdditionalInfo);
                FichePropertyEditView fichePropertyEditView = this.driverName;
                eDispatchAdditionalInfo.firstDriverName = String.valueOf((fichePropertyEditView == null || (edtValue30 = fichePropertyEditView.getEdtValue()) == null) ? null : edtValue30.getText());
                EDispatchAdditionalInfo eDispatchAdditionalInfo2 = this.additionalInfo;
                Intrinsics.checkNotNull(eDispatchAdditionalInfo2);
                FichePropertyEditView fichePropertyEditView2 = this.driverSurname;
                eDispatchAdditionalInfo2.firstDriverLastName = String.valueOf((fichePropertyEditView2 == null || (edtValue29 = fichePropertyEditView2.getEdtValue()) == null) ? null : edtValue29.getText());
                EDispatchAdditionalInfo eDispatchAdditionalInfo3 = this.additionalInfo;
                Intrinsics.checkNotNull(eDispatchAdditionalInfo3);
                FichePropertyEditView fichePropertyEditView3 = this.driverIdentityNr;
                eDispatchAdditionalInfo3.firstDriverIdentityNr = String.valueOf((fichePropertyEditView3 == null || (edtValue28 = fichePropertyEditView3.getEdtValue()) == null) ? null : edtValue28.getText());
                EDispatchAdditionalInfo eDispatchAdditionalInfo4 = this.additionalInfo;
                Intrinsics.checkNotNull(eDispatchAdditionalInfo4);
                FichePropertyEditView fichePropertyEditView4 = this.driverPlateNr;
                eDispatchAdditionalInfo4.firstDriverPlate = String.valueOf((fichePropertyEditView4 == null || (edtValue27 = fichePropertyEditView4.getEdtValue()) == null) ? null : edtValue27.getText());
                EDispatchAdditionalInfo eDispatchAdditionalInfo5 = this.additionalInfo;
                Intrinsics.checkNotNull(eDispatchAdditionalInfo5);
                FichePropertyEditView fichePropertyEditView5 = this.driverTrailerPlateNr;
                eDispatchAdditionalInfo5.firstTrailerPlate = String.valueOf((fichePropertyEditView5 == null || (edtValue26 = fichePropertyEditView5.getEdtValue()) == null) ? null : edtValue26.getText());
                FichePropertyEditView fichePropertyEditView6 = this.driverName;
                if (fichePropertyEditView6 != null && (edtValue25 = fichePropertyEditView6.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo6 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo6);
                    edtValue25.setText(eDispatchAdditionalInfo6.firstDriverName);
                }
                FichePropertyEditView fichePropertyEditView7 = this.driverSurname;
                if (fichePropertyEditView7 != null && (edtValue24 = fichePropertyEditView7.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo7 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo7);
                    edtValue24.setText(eDispatchAdditionalInfo7.firstDriverLastName);
                }
                FichePropertyEditView fichePropertyEditView8 = this.driverIdentityNr;
                if (fichePropertyEditView8 != null && (edtValue23 = fichePropertyEditView8.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo8 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo8);
                    edtValue23.setText(eDispatchAdditionalInfo8.firstDriverIdentityNr);
                }
                FichePropertyEditView fichePropertyEditView9 = this.driverPlateNr;
                if (fichePropertyEditView9 != null && (edtValue22 = fichePropertyEditView9.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo9 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo9);
                    edtValue22.setText(eDispatchAdditionalInfo9.firstDriverPlate);
                }
                FichePropertyEditView fichePropertyEditView10 = this.driverTrailerPlateNr;
                if (fichePropertyEditView10 != null && (edtValue21 = fichePropertyEditView10.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo10 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo10);
                    edtValue21.setText(eDispatchAdditionalInfo10.firstTrailerPlate);
                }
            }
            if (i2 == 1) {
                EDispatchAdditionalInfo eDispatchAdditionalInfo11 = this.additionalInfo;
                Intrinsics.checkNotNull(eDispatchAdditionalInfo11);
                FichePropertyEditView fichePropertyEditView11 = this.driverName;
                eDispatchAdditionalInfo11.secondDriverName = String.valueOf((fichePropertyEditView11 == null || (edtValue20 = fichePropertyEditView11.getEdtValue()) == null) ? null : edtValue20.getText());
                EDispatchAdditionalInfo eDispatchAdditionalInfo12 = this.additionalInfo;
                Intrinsics.checkNotNull(eDispatchAdditionalInfo12);
                FichePropertyEditView fichePropertyEditView12 = this.driverSurname;
                eDispatchAdditionalInfo12.secondDriverLastName = String.valueOf((fichePropertyEditView12 == null || (edtValue19 = fichePropertyEditView12.getEdtValue()) == null) ? null : edtValue19.getText());
                EDispatchAdditionalInfo eDispatchAdditionalInfo13 = this.additionalInfo;
                Intrinsics.checkNotNull(eDispatchAdditionalInfo13);
                FichePropertyEditView fichePropertyEditView13 = this.driverIdentityNr;
                eDispatchAdditionalInfo13.secondDriverIdentityNr = String.valueOf((fichePropertyEditView13 == null || (edtValue18 = fichePropertyEditView13.getEdtValue()) == null) ? null : edtValue18.getText());
                EDispatchAdditionalInfo eDispatchAdditionalInfo14 = this.additionalInfo;
                Intrinsics.checkNotNull(eDispatchAdditionalInfo14);
                FichePropertyEditView fichePropertyEditView14 = this.driverPlateNr;
                eDispatchAdditionalInfo14.secondDriverPlate = String.valueOf((fichePropertyEditView14 == null || (edtValue17 = fichePropertyEditView14.getEdtValue()) == null) ? null : edtValue17.getText());
                EDispatchAdditionalInfo eDispatchAdditionalInfo15 = this.additionalInfo;
                Intrinsics.checkNotNull(eDispatchAdditionalInfo15);
                FichePropertyEditView fichePropertyEditView15 = this.driverTrailerPlateNr;
                eDispatchAdditionalInfo15.secondTrailerPlate = String.valueOf((fichePropertyEditView15 == null || (edtValue16 = fichePropertyEditView15.getEdtValue()) == null) ? null : edtValue16.getText());
                FichePropertyEditView fichePropertyEditView16 = this.driverName;
                if (fichePropertyEditView16 != null && (edtValue15 = fichePropertyEditView16.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo16 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo16);
                    edtValue15.setText(eDispatchAdditionalInfo16.secondDriverName);
                }
                FichePropertyEditView fichePropertyEditView17 = this.driverSurname;
                if (fichePropertyEditView17 != null && (edtValue14 = fichePropertyEditView17.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo17 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo17);
                    edtValue14.setText(eDispatchAdditionalInfo17.secondDriverLastName);
                }
                FichePropertyEditView fichePropertyEditView18 = this.driverIdentityNr;
                if (fichePropertyEditView18 != null && (edtValue13 = fichePropertyEditView18.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo18 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo18);
                    edtValue13.setText(eDispatchAdditionalInfo18.secondDriverIdentityNr);
                }
                FichePropertyEditView fichePropertyEditView19 = this.driverPlateNr;
                if (fichePropertyEditView19 != null && (edtValue12 = fichePropertyEditView19.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo19 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo19);
                    edtValue12.setText(eDispatchAdditionalInfo19.secondDriverPlate);
                }
                FichePropertyEditView fichePropertyEditView20 = this.driverTrailerPlateNr;
                if (fichePropertyEditView20 != null && (edtValue11 = fichePropertyEditView20.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo20 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo20);
                    edtValue11.setText(eDispatchAdditionalInfo20.secondTrailerPlate);
                }
            }
            if (i2 == 2) {
                EDispatchAdditionalInfo eDispatchAdditionalInfo21 = this.additionalInfo;
                Intrinsics.checkNotNull(eDispatchAdditionalInfo21);
                FichePropertyEditView fichePropertyEditView21 = this.driverName;
                eDispatchAdditionalInfo21.thirdDriverName = String.valueOf((fichePropertyEditView21 == null || (edtValue10 = fichePropertyEditView21.getEdtValue()) == null) ? null : edtValue10.getText());
                EDispatchAdditionalInfo eDispatchAdditionalInfo22 = this.additionalInfo;
                Intrinsics.checkNotNull(eDispatchAdditionalInfo22);
                FichePropertyEditView fichePropertyEditView22 = this.driverSurname;
                eDispatchAdditionalInfo22.thirdDriverLastName = String.valueOf((fichePropertyEditView22 == null || (edtValue9 = fichePropertyEditView22.getEdtValue()) == null) ? null : edtValue9.getText());
                EDispatchAdditionalInfo eDispatchAdditionalInfo23 = this.additionalInfo;
                Intrinsics.checkNotNull(eDispatchAdditionalInfo23);
                FichePropertyEditView fichePropertyEditView23 = this.driverIdentityNr;
                eDispatchAdditionalInfo23.thirdDriverIdentityNr = String.valueOf((fichePropertyEditView23 == null || (edtValue8 = fichePropertyEditView23.getEdtValue()) == null) ? null : edtValue8.getText());
                EDispatchAdditionalInfo eDispatchAdditionalInfo24 = this.additionalInfo;
                Intrinsics.checkNotNull(eDispatchAdditionalInfo24);
                FichePropertyEditView fichePropertyEditView24 = this.driverPlateNr;
                eDispatchAdditionalInfo24.thirdDriverPlate = String.valueOf((fichePropertyEditView24 == null || (edtValue7 = fichePropertyEditView24.getEdtValue()) == null) ? null : edtValue7.getText());
                EDispatchAdditionalInfo eDispatchAdditionalInfo25 = this.additionalInfo;
                Intrinsics.checkNotNull(eDispatchAdditionalInfo25);
                FichePropertyEditView fichePropertyEditView25 = this.driverTrailerPlateNr;
                if (fichePropertyEditView25 != null && (edtValue6 = fichePropertyEditView25.getEdtValue()) != null) {
                    editable = edtValue6.getText();
                }
                eDispatchAdditionalInfo25.thirdTrailerPlate = String.valueOf(editable);
                FichePropertyEditView fichePropertyEditView26 = this.driverName;
                if (fichePropertyEditView26 != null && (edtValue5 = fichePropertyEditView26.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo26 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo26);
                    edtValue5.setText(eDispatchAdditionalInfo26.thirdDriverName);
                }
                FichePropertyEditView fichePropertyEditView27 = this.driverSurname;
                if (fichePropertyEditView27 != null && (edtValue4 = fichePropertyEditView27.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo27 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo27);
                    edtValue4.setText(eDispatchAdditionalInfo27.thirdDriverLastName);
                }
                FichePropertyEditView fichePropertyEditView28 = this.driverIdentityNr;
                if (fichePropertyEditView28 != null && (edtValue3 = fichePropertyEditView28.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo28 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo28);
                    edtValue3.setText(eDispatchAdditionalInfo28.thirdDriverIdentityNr);
                }
                FichePropertyEditView fichePropertyEditView29 = this.driverPlateNr;
                if (fichePropertyEditView29 != null && (edtValue2 = fichePropertyEditView29.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo29 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo29);
                    edtValue2.setText(eDispatchAdditionalInfo29.thirdDriverPlate);
                }
                FichePropertyEditView fichePropertyEditView30 = this.driverTrailerPlateNr;
                if (fichePropertyEditView30 != null && (edtValue = fichePropertyEditView30.getEdtValue()) != null) {
                    EDispatchAdditionalInfo eDispatchAdditionalInfo30 = this.additionalInfo;
                    Intrinsics.checkNotNull(eDispatchAdditionalInfo30);
                    edtValue.setText(eDispatchAdditionalInfo30.thirdTrailerPlate);
                }
            }
        }
    }

    @Override // com.proje.mobilesales.core.base.BaseActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putParcelable(STATE_SALES_EDISPATCH_ADDITIONAL, this.additionalInfo);
        super.onSaveInstanceState(outState);
    }

    private void setDriverInfoToPreferences() {
        this.driverInfoList = new ArrayList();
        int size = this.driverInfoLayoutsList.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.driverName = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverName);
            this.driverSurname = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverSurname);
            this.driverIdentityNr = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverIdentityNr);
            this.driverPlateNr = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverPlateNr);
            this.driverTrailerPlateNr = this.driverInfoLayoutsList.get(i2).findViewById(R.id.driverTrailerPlateNr);
            FichePropertyEditView fichePropertyEditView = this.driverName;
            if (fichePropertyEditView == null) {
                fichePropertyEditView = new FichePropertyEditView(ContextUtils.getmContext());
            }
            FichePropertyEditView fichePropertyEditView2 = fichePropertyEditView;
            FichePropertyEditView fichePropertyEditView3 = this.driverSurname;
            if (fichePropertyEditView3 == null) {
                fichePropertyEditView3 = new FichePropertyEditView(ContextUtils.getmContext());
            }
            FichePropertyEditView fichePropertyEditView4 = fichePropertyEditView3;
            FichePropertyEditView fichePropertyEditView5 = this.driverIdentityNr;
            if (fichePropertyEditView5 == null) {
                fichePropertyEditView5 = new FichePropertyEditView(ContextUtils.getmContext());
            }
            FichePropertyEditView fichePropertyEditView6 = fichePropertyEditView5;
            FichePropertyEditView fichePropertyEditView7 = this.driverPlateNr;
            if (fichePropertyEditView7 == null) {
                fichePropertyEditView7 = new FichePropertyEditView(ContextUtils.getmContext());
            }
            FichePropertyEditView fichePropertyEditView8 = fichePropertyEditView7;
            FichePropertyEditView fichePropertyEditView9 = this.driverTrailerPlateNr;
            if (fichePropertyEditView9 == null) {
                fichePropertyEditView9 = new FichePropertyEditView(ContextUtils.getmContext());
            }
            this.driverInfoList.add(new DriverInfos(fichePropertyEditView2, fichePropertyEditView4, fichePropertyEditView6, fichePropertyEditView8, fichePropertyEditView9));
        }
        Preferences.setDriverInfo(this, new Gson().toJson(this.driverInfoList));
    }

    private void returnActivityResult() {
        Intent intent = new Intent();
        intent.putExtra(IntentExtraName.EXTRA_EDESPATCH_EXTRA_INFO, this.additionalInfo);
        setResult(-1, intent);
    }

    
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            setDriverInfoToPreferences();
            setAdditionalInfoFromDriverLayout();
            returnActivityResult();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /* compiled from: DriverInformationActivity.kt */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public String getEXTRA_SALES_FICHE_MODE() {
            return DriverInformationActivity.EXTRA_SALES_FICHE_MODE;
        }
    }
}
