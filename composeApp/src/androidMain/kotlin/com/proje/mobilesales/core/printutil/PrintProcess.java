package com.proje.mobilesales.core.printutil;

import android.database.Cursor;
import android.util.Log;
import com.google.gson.Gson;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.emailreplacer.CalculatedField;
import com.proje.mobilesales.core.emailreplacer.CalculatedFields;
import com.proje.mobilesales.core.enums.BaskiAlanlariNumber;
import com.proje.mobilesales.core.enums.ColType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.PrintDesignSection;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.objecthunter.exp4j.ExpressionBuilder;

public class PrintProcess {
    private PrintSettingsJsonParse printSettingsJsonParse;
    public int boslukSayisi = 256;
    public String SprintIniFileName = "";
    public String[] DataStr = new String[200];
    public String[] BaslikSabitAlanAyarlari = new String[1001];
    public String[] DetaySabitAlanAyarlari = new String[1001];
    public String[] SayfaSonuSabitAlanAyarlari = new String[1001];
    public String[] BaskiSonuSabitAlanAyarlari = new String[1001];
    public String[] BaslikAlanAyarlari = new String[1001];
    public String[] DetayAlanAyarlari = new String[1001];
    public String[] SayfaSonuAlanAyarlari = new String[1001];
    public String[] BaskiSonuAlanAyarlari = new String[1001];
    public String BaslikString = "";
    public String DetayString = "";
    public String SayfaSonuString = "";
    public String BaskiSonuString = "";
    public CalculatedFields CalculatedFieds = null;
    public String AlanAyariSeparator = "---";
    public AlanAyari AlanAyariStcr = new AlanAyari();
    public GenelAyarlar GenelAyarlarStcr = new GenelAyarlar();
    enum BaskiBolumleri {
        Baslik,
        Detay,
        SayfaSonu,
        BaskiSonu,
        BaslikSabitAlan,
        DetaySabitAlan,
        SayfaSonuSabitAlan,
        BaskiSonuSabitAlan
    }
    static class AlanAyari {

        /* renamed from: X */
        public int f1194X;

        /* renamed from: Y */
        public int f1195Y;
        public int cutWord;
        public int cutWordLength;
        public String deger;
        public int fontBold;
        public int fontItalic;
        public String fontName;
        public int fontSize;
        public int fontUnderLine;
        public String leftOrRight;
        public int uzunluk;

        AlanAyari() {
        }
    }
    static class GenelAyarlar {
        public int baskisonuYuksekligi;
        public int baslikYuksekligi;
        public int detayBaslangicYuksekligi;
        public int detaySatirSayisi;
        public int detayYuksekligi;
        public int sayfasonuYuksekligi;

        GenelAyarlar() {
        }
    }
    private String getJsonText() {
        Cursor cursor;
        Throwable th;
        String str = null;
        try {
            cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query("SELECT DESIGN FROM DESIGNJSON WHERE NAME='" + this.SprintIniFileName + "'");
            if (cursor != null) {
                try {
                    try {
                        if (cursor.moveToPosition(0)) {
                            str = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "DESIGN", ColType.metin).toString();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        throw th;
                    }
                } catch (Exception e2) {

                    if (cursor != null) {
                    }
                    return str;
                }
            }
            if (cursor != null) {
            }
        } catch (Exception e3) {

            cursor = null;
        } catch (Throwable th3) {
            cursor = null;
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            try {
                throw th;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        return str;
    }
    private void getCalculatedFields() {
        String obj;
        Cursor cursor = null;
        this.CalculatedFieds = null;
        try {
            try {
                cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query("SELECT FORMCONFIG FROM DESIGNJSON WHERE NAME='" + this.SprintIniFileName + "'");
                if (cursor != null && cursor.moveToPosition(0) && (obj = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "FORMCONFIG", ColType.metin).toString()) != null && !obj.isEmpty()) {
                    CalculatedFields calculatedFields = new Gson().fromJson(obj, CalculatedFields.class);
                    this.CalculatedFieds = calculatedFields;
                    Collections.sort(calculatedFields.getCalculatedFields(), new Comparator() { // from class: com.proje.mobilesales.core.printutil.PrintProcessExternalSyntheticLambda0
                        @Override // java.util.Comparator
                        public int compare(Object obj2, Object obj3) {
                            int lambdagetCalculatedFields0;
                            lambdagetCalculatedFields0 = PrintProcess.lambdagetCalculatedFields0((CalculatedField) obj2, (CalculatedField) obj3);
                            return lambdagetCalculatedFields0;
                        }
                    });
                }
                if (cursor == null || cursor.isClosed()) {
                    return;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (cursor == null || cursor.isClosed()) {
                    return;
                }
            }
            cursor.close();
        } catch (Throwable th) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
    }
    public static int lambdagetCalculatedFields0(CalculatedField calculatedField, CalculatedField calculatedField2) {
        return calculatedField.getFieldNumber() - calculatedField2.getFieldNumber();
    }
    private List<CalculatedField> getSectionCalculatedFieds(String str) {
        CalculatedFields calculatedFields = this.CalculatedFieds;
        if (calculatedFields == null || calculatedFields.getCalculatedFields() == null || this.CalculatedFieds.getCalculatedFields().size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<CalculatedField> it = this.CalculatedFieds.getCalculatedFields().iterator();
        while (it.hasNext()) {
            CalculatedField next = it.next();
            if (next.getSection().equals(str)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }
    public void SetFieldsSettings() {
        String str = "";
        for (int i2 = 0; i2 <= this.boslukSayisi - 1; i2++) {
            str = str + " ";
        }
        for (int i3 = 0; i3 < 200; i3++) {
            try {
                this.DataStr[i3] = str;
            } catch (Exception unused) {
            }
        }
        for (int i4 = 0; i4 <= 1000; i4++) {
            try {
                this.BaslikSabitAlanAyarlari[i4] = "";
            } catch (Exception unused2) {
                for (int i5 = 0; i5 <= 1000; i5++) {
                    this.BaslikSabitAlanAyarlari[i5] = "";
                }
            }
        }
        List<PrintAlan> printAlanList = this.printSettingsJsonParse.getPrintSettings().getPrintBaslik(BaskiAlanlariNumber.BASLIK_SABIT_ALANLAR.getNumber()).getPrintAlanList();
        if (printAlanList.size() > 0) {
            for (int i6 = 0; i6 < printAlanList.size(); i6++) {
                this.BaslikSabitAlanAyarlari[i6] = printAlanList.get(i6).toString();
            }
        }
        for (int i7 = 0; i7 <= 1000; i7++) {
            try {
                this.BaslikAlanAyarlari[i7] = "";
            } catch (Exception unused3) {
                for (int i8 = 0; i8 <= 1000; i8++) {
                    this.BaslikAlanAyarlari[i8] = "";
                }
            }
        }
        List<PrintAlan> printAlanList2 = this.printSettingsJsonParse.getPrintSettings().getPrintBaslik(BaskiAlanlariNumber.FIS_BASLIK.getNumber()).getPrintAlanList();
        if (printAlanList2.size() > 0) {
            for (int i9 = 0; i9 < printAlanList2.size(); i9++) {
                int alanNo = printAlanList2.get(i9).getAlanNo();
                String printAlan = printAlanList2.get(i9).toString();
                if (this.BaslikAlanAyarlari[alanNo].isEmpty()) {
                    this.BaslikAlanAyarlari[alanNo] = printAlan;
                } else {
                    this.BaslikAlanAyarlari[alanNo] = this.BaslikAlanAyarlari[alanNo] + this.AlanAyariSeparator + printAlan;
                }
            }
        }
        for (int i10 = 0; i10 <= 1000; i10++) {
            try {
                this.DetaySabitAlanAyarlari[i10] = "";
            } catch (Exception unused4) {
                for (int i11 = 0; i11 <= 1000; i11++) {
                    this.DetaySabitAlanAyarlari[i11] = "";
                }
            }
        }
        List<PrintAlan> printAlanList3 = this.printSettingsJsonParse.getPrintSettings().getPrintBaslik(BaskiAlanlariNumber.DETAY_SABIT_ALANLAR.getNumber()).getPrintAlanList();
        if (printAlanList3.size() > 0) {
            for (int i12 = 0; i12 < printAlanList3.size(); i12++) {
                this.DetaySabitAlanAyarlari[i12] = printAlanList3.get(i12).toString();
            }
        }
        for (int i13 = 0; i13 <= 1000; i13++) {
            try {
                this.DetayAlanAyarlari[i13] = "";
            } catch (Exception unused5) {
                for (int i14 = 0; i14 <= 1000; i14++) {
                    this.DetayAlanAyarlari[i14] = "";
                }
            }
        }
        List<PrintAlan> printAlanList4 = this.printSettingsJsonParse.getPrintSettings().getPrintBaslik(BaskiAlanlariNumber.FIS_DETAY.getNumber()).getPrintAlanList();
        if (printAlanList4.size() > 0) {
            for (int i15 = 0; i15 < printAlanList4.size(); i15++) {
                int alanNo2 = printAlanList4.get(i15).getAlanNo();
                String printAlan2 = printAlanList4.get(i15).toString();
                if (this.DetayAlanAyarlari[alanNo2].isEmpty()) {
                    this.DetayAlanAyarlari[alanNo2] = printAlan2;
                } else {
                    this.DetayAlanAyarlari[alanNo2] = this.DetayAlanAyarlari[alanNo2] + this.AlanAyariSeparator + printAlan2;
                }
            }
        }
        for (int i16 = 0; i16 <= 1000; i16++) {
            try {
                this.SayfaSonuSabitAlanAyarlari[i16] = "";
            } catch (Exception unused6) {
                for (int i17 = 0; i17 <= 1000; i17++) {
                    this.SayfaSonuSabitAlanAyarlari[i17] = "";
                }
            }
        }
        List<PrintAlan> printAlanList5 = this.printSettingsJsonParse.getPrintSettings().getPrintBaslik(BaskiAlanlariNumber.SAYFASONU_SABIT_ALANLAR.getNumber()).getPrintAlanList();
        if (printAlanList5.size() > 0) {
            for (int i18 = 0; i18 < printAlanList5.size(); i18++) {
                this.SayfaSonuSabitAlanAyarlari[i18] = printAlanList5.get(i18).toString();
            }
        }
        for (int i19 = 0; i19 <= 1000; i19++) {
            try {
                this.SayfaSonuAlanAyarlari[i19] = "";
            } catch (Exception unused7) {
                for (int i20 = 0; i20 <= 1000; i20++) {
                    this.SayfaSonuAlanAyarlari[i20] = "";
                }
            }
        }
        List<PrintAlan> printAlanList6 = this.printSettingsJsonParse.getPrintSettings().getPrintBaslik(BaskiAlanlariNumber.SAYFA_SONU.getNumber()).getPrintAlanList();
        if (printAlanList6.size() > 0) {
            for (int i21 = 0; i21 < printAlanList6.size(); i21++) {
                int alanNo3 = printAlanList6.get(i21).getAlanNo();
                String printAlan3 = printAlanList6.get(i21).toString();
                if (this.SayfaSonuAlanAyarlari[alanNo3].isEmpty()) {
                    this.SayfaSonuAlanAyarlari[alanNo3] = printAlan3;
                } else {
                    this.SayfaSonuAlanAyarlari[alanNo3] = this.SayfaSonuAlanAyarlari[alanNo3] + this.AlanAyariSeparator + printAlan3;
                }
            }
        }
        for (int i22 = 0; i22 <= 1000; i22++) {
            try {
                this.BaskiSonuSabitAlanAyarlari[i22] = "";
            } catch (Exception unused8) {
                for (int i23 = 0; i23 <= 1000; i23++) {
                    this.BaskiSonuSabitAlanAyarlari[i23] = "";
                }
            }
        }
        List<PrintAlan> printAlanList7 = this.printSettingsJsonParse.getPrintSettings().getPrintBaslik(BaskiAlanlariNumber.BASKISONU_SABIT_ALANLAR.getNumber()).getPrintAlanList();
        if (printAlanList7.size() > 0) {
            for (int i24 = 0; i24 < printAlanList7.size(); i24++) {
                this.BaskiSonuSabitAlanAyarlari[i24] = printAlanList7.get(i24).toString();
            }
        }
        for (int i25 = 0; i25 <= 1000; i25++) {
            try {
                this.BaskiSonuAlanAyarlari[i25] = "";
            } catch (Exception unused9) {
                for (int i26 = 0; i26 <= 1000; i26++) {
                    this.BaskiSonuAlanAyarlari[i26] = "";
                }
                return;
            }
        }
        List<PrintAlan> printAlanList8 = this.printSettingsJsonParse.getPrintSettings().getPrintBaslik(BaskiAlanlariNumber.BASKI_SONU.getNumber()).getPrintAlanList();
        if (printAlanList8.size() > 0) {
            for (int i27 = 0; i27 < printAlanList8.size(); i27++) {
                int alanNo4 = printAlanList8.get(i27).getAlanNo();
                String printAlan4 = printAlanList8.get(i27).toString();
                if (this.BaskiSonuAlanAyarlari[alanNo4].isEmpty()) {
                    this.BaskiSonuAlanAyarlari[alanNo4] = printAlan4;
                } else {
                    this.BaskiSonuAlanAyarlari[alanNo4] = this.BaskiSonuAlanAyarlari[alanNo4] + this.AlanAyariSeparator + printAlan4;
                }
            }
        }
    }
    public int GetFieldValueSettings(String str) {
        int i2 = 0;
        Cursor query = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(SqlLiteVariable._SELECT + str + " FROM DESIGNSETTINGS WHERE DEFINITION_='" + this.SprintIniFileName + "'");
        if (query != null && query.moveToPosition(0)) {
            i2 = StringUtils.convertStringToInt(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(query, str, ColType.metin).toString());
        }
        query.close();
        return i2;
    }
    public void GetDefaultSettings() {
        GenelAyarlar genelAyarlar = this.GenelAyarlarStcr;
        PrintSettings printSettings = this.printSettingsJsonParse.getPrintSettings();
        BaskiAlanlariNumber baskiAlanlariNumber = BaskiAlanlariNumber.FIS_DETAY;
        genelAyarlar.detayBaslangicYuksekligi = printSettings.getPrintBaslik(baskiAlanlariNumber.getNumber()).getBaslangicY();
        this.GenelAyarlarStcr.detaySatirSayisi = this.printSettingsJsonParse.getPrintSettings().getPrintBaslik(baskiAlanlariNumber.getNumber()).getDetaySatirSayisi();
        this.GenelAyarlarStcr.baslikYuksekligi = this.printSettingsJsonParse.getPrintSettings().getDesignPattern().getGenelBaslikHeight();
        this.GenelAyarlarStcr.detayYuksekligi = this.printSettingsJsonParse.getPrintSettings().getDesignPattern().getGenelDetayHeight();
        this.GenelAyarlarStcr.sayfasonuYuksekligi = this.printSettingsJsonParse.getPrintSettings().getDesignPattern().getGenelSayfaSonuHeight();
        this.GenelAyarlarStcr.baskisonuYuksekligi = this.printSettingsJsonParse.getPrintSettings().getDesignPattern().getGenelBaskiSonuHeight();
    }
    public String SetValueAlign2LeftRigth(String str, int i2, String str2) {
        String padLeft;
        if (str2.trim().equals("R")) {
            padLeft = StringUtils.padRight(str, i2);
        } else {
            padLeft = StringUtils.padLeft(str, i2);
        }
        return padLeft.substring(0, i2);
    }
    public boolean GetFieldSettings(String str, BaskiBolumleri baskiBolumleri) {
        String[] split = StringUtils.split(str, ',');
        if (split.length <= 1) {
            return false;
        }
        AlanAyari alanAyari = this.AlanAyariStcr;
        alanAyari.deger = split[2];
        alanAyari.f1194X = StringUtils.convertStringToInt(split[3]);
        this.AlanAyariStcr.f1195Y = StringUtils.convertStringToInt(split[4]);
        this.AlanAyariStcr.uzunluk = StringUtils.convertStringToInt(split[5]);
        AlanAyari alanAyari2 = this.AlanAyariStcr;
        alanAyari2.leftOrRight = split[6];
        alanAyari2.cutWord = StringUtils.convertStringToInt(split[7]);
        this.AlanAyariStcr.cutWordLength = StringUtils.convertStringToInt(split[8]);
        return true;
    }
    public boolean GetFieldSettings2Yedek(String str, BaskiBolumleri baskiBolumleri) {
        String[] split = StringUtils.split(str, ',');
        if (split.length == 1) {
            return false;
        }
        if (baskiBolumleri == BaskiBolumleri.Baslik) {
            this.AlanAyariStcr.f1194X = StringUtils.convertStringToInt(split[0]);
            this.AlanAyariStcr.f1195Y = StringUtils.convertStringToInt(split[1]);
        } else if (baskiBolumleri == BaskiBolumleri.Detay) {
            this.AlanAyariStcr.f1194X = StringUtils.convertStringToInt(split[0]);
            this.AlanAyariStcr.f1195Y = StringUtils.convertStringToInt(split[1]);
            this.AlanAyariStcr.uzunluk = StringUtils.convertStringToInt(split[2]);
            this.AlanAyariStcr.leftOrRight = split[3];
        } else if (baskiBolumleri == BaskiBolumleri.SayfaSonu) {
            this.AlanAyariStcr.f1194X = StringUtils.convertStringToInt(split[0]);
            this.AlanAyariStcr.f1195Y = StringUtils.convertStringToInt(split[1]);
            this.AlanAyariStcr.uzunluk = StringUtils.convertStringToInt(split[2]);
            this.AlanAyariStcr.leftOrRight = split[3];
        } else if (baskiBolumleri == BaskiBolumleri.BaskiSonu) {
            this.AlanAyariStcr.f1194X = StringUtils.convertStringToInt(split[0]);
            this.AlanAyariStcr.f1195Y = StringUtils.convertStringToInt(split[1]);
            this.AlanAyariStcr.uzunluk = StringUtils.convertStringToInt(split[2]);
            this.AlanAyariStcr.leftOrRight = split[3];
        } else if (baskiBolumleri == BaskiBolumleri.BaslikSabitAlan) {
            AlanAyari alanAyari = this.AlanAyariStcr;
            alanAyari.deger = split[0];
            alanAyari.f1194X = StringUtils.convertStringToInt(split[1]);
            this.AlanAyariStcr.f1195Y = StringUtils.convertStringToInt(split[2]);
        } else if (baskiBolumleri == BaskiBolumleri.DetaySabitAlan) {
            AlanAyari alanAyari2 = this.AlanAyariStcr;
            alanAyari2.deger = split[0];
            alanAyari2.f1194X = StringUtils.convertStringToInt(split[1]);
            this.AlanAyariStcr.f1195Y = StringUtils.convertStringToInt(split[2]);
        } else if (baskiBolumleri == BaskiBolumleri.SayfaSonuSabitAlan) {
            AlanAyari alanAyari3 = this.AlanAyariStcr;
            alanAyari3.deger = split[0];
            alanAyari3.f1194X = StringUtils.convertStringToInt(split[1]);
            this.AlanAyariStcr.f1195Y = StringUtils.convertStringToInt(split[2]);
        } else if (baskiBolumleri == BaskiBolumleri.BaskiSonuSabitAlan) {
            AlanAyari alanAyari4 = this.AlanAyariStcr;
            alanAyari4.deger = split[0];
            alanAyari4.f1194X = StringUtils.convertStringToInt(split[1]);
            this.AlanAyariStcr.f1195Y = StringUtils.convertStringToInt(split[2]);
        }
        return true;
    }
    public String GetError(Exception exc) {
        return ((((" ****************************************************************** HATA ******************************************************************\n\r") + "\n\r") + "  Message    : " + exc.getMessage() + "\n\r") + "\n\r") + " *********************************************************************************************************************************************\n\r";
    }
    public String Substring(String str, int i2) {
        return str.length() > i2 ? str.substring(0, i2) : str;
    }
    private String[] checkSectionLenght(String[] strArr, int i2, int i3) {
        if (i2 < i3) {
            i2 = i3;
        }
        if (strArr.length < i2) {
            String[] strArr2 = new String[i2];
            for (int i4 = 0; i4 < i2; i4++) {
                if (i4 < strArr.length) {
                    strArr2[i4] = strArr[i4];
                } else {
                    strArr2[i4] = "";
                }
            }
            return strArr2;
        }
        return strArr.clone();
    }
    private String[] addCalculatedFields(String[] strArr, PrintDesignSection printDesignSection) {
        try {
            List<CalculatedField> sectionCalculatedFieds = getSectionCalculatedFieds(printDesignSection.getSectionNumber());
            if (sectionCalculatedFieds != null && sectionCalculatedFieds.size() != 0) {
                int fieldNumber = sectionCalculatedFieds.get(sectionCalculatedFieds.size() - 1).getFieldNumber();
                if (printDesignSection != PrintDesignSection.DETAIL && printDesignSection != PrintDesignSection.SUMMARY && printDesignSection != PrintDesignSection.FOOTER) {
                    fieldNumber = strArr.length;
                }
                String[] strArr2 = Arrays.copyOf(strArr, fieldNumber);
                for (int i2 = 0; i2 < sectionCalculatedFieds.size(); i2++) {
                    CalculatedField calculatedField = sectionCalculatedFieds.get(i2);
                    calculatedField.setFormula(calculatedField.getOrgFormula().replace(",", "."));
                    Matcher matcher = Pattern.compile("\\{(.*?)\\}").matcher(sectionCalculatedFieds.get(i2).getOrgFormula());
                    while (matcher.find()) {
                        int convertStringToInt = StringUtils.convertStringToInt(matcher.group(1));
                        calculatedField.setFormula(calculatedField.getFormula().replace("{" + convertStringToInt + "}", strArr[convertStringToInt - 1].replace(".", "").replace(",", ".")));
                    }
                    strArr2[calculatedField.getFieldNumber() - 1] = calculateFormula(calculatedField.getFormula());
                }
                return strArr2;
            }
        } catch (Exception unused) {
        }
        return strArr;
    }
    private String calculateFormula(String str) {
        try {
            return StringUtils.dFormat(new ExpressionBuilder(str).build().evaluate());
        } catch (Exception unused) {
            return str;
        }
    }
    private String Print(int i2) {
        char c2;
        char c3;
        int i3;
        String[] strArr;
        String[] strArr2;
        int i4;
        String[] strArr3;
        String[] strArr4;
        try {
            char c4 = '\r';
            String[] split = StringUtils.split(this.BaslikString, '\r');
            String[] split2 = StringUtils.split(this.DetayString, '\r');
            String[] split3 = StringUtils.split(this.SayfaSonuString, '\r');
            String[] split4 = StringUtils.split(this.BaskiSonuString, '\r');
            int i5 = 0;
            while (true) {
                int i6 = 1;
                c2 = '\t';
                c3 = '\n';
                if (i5 > split.length - 1) {
                    break;
                }
                String[] addCalculatedFields = addCalculatedFields(StringUtils.split(StringUtils.replace(StringUtils.replace(split[i5], String.valueOf(c4), ""), String.valueOf('\n'), ""), '\t'), PrintDesignSection.HEADER);
                int i7 = 0;
                while (i7 <= addCalculatedFields.length - i6) {
                    String[] strArr5 = this.BaslikAlanAyarlari;
                    if (strArr5.length - i6 == i7) {
                        break;
                    }
                    int i8 = i7 + 1;
                    String[] split5 = StringUtils.split(strArr5[i8], this.AlanAyariSeparator);
                    int length = split5.length;
                    int i9 = 0;
                    while (i9 < length) {
                        if (!(GetFieldSettings(split5[i9], BaskiBolumleri.Baslik) == i6)) {
                            strArr4 = split;
                        } else {
                            String str = addCalculatedFields[i7];
                            AlanAyari alanAyari = this.AlanAyariStcr;
                            if (alanAyari.cutWord == i6) {
                                str = Substring(str, alanAyari.cutWordLength);
                            }
                            String[] strArr6 = this.DataStr;
                            AlanAyari alanAyari2 = this.AlanAyariStcr;
                            int i10 = alanAyari2.f1195Y;
                            strArr4 = split;
                            strArr6[i10] = StringUtils.remove(strArr6[i10], alanAyari2.f1194X, str);
                            String[] strArr7 = this.DataStr;
                            AlanAyari alanAyari3 = this.AlanAyariStcr;
                            int i11 = alanAyari3.f1195Y;
                            strArr7[i11] = StringUtils.insert(strArr7[i11], alanAyari3.f1194X, str);
                        }
                        i9++;
                        split = strArr4;
                        i6 = 1;
                    }
                    i7 = i8;
                }
                i5++;
                split = split;
                c4 = '\r';
            }
            int i12 = 0;
            while (true) {
                String[] strArr8 = this.BaslikSabitAlanAyarlari;
                if (i12 > strArr8.length - 1 || strArr8.length - 1 == i12) {
                    break;
                }
                if (GetFieldSettings(strArr8[i12], BaskiBolumleri.BaslikSabitAlan)) {
                    AlanAyari alanAyari4 = this.AlanAyariStcr;
                    String str2 = alanAyari4.deger;
                    if (alanAyari4.cutWord == 1) {
                        str2 = Substring(str2, alanAyari4.cutWordLength);
                    }
                    String[] strArr9 = this.DataStr;
                    AlanAyari alanAyari5 = this.AlanAyariStcr;
                    int i13 = alanAyari5.f1195Y;
                    strArr9[i13] = StringUtils.remove(strArr9[i13], alanAyari5.f1194X, str2);
                    String[] strArr10 = this.DataStr;
                    AlanAyari alanAyari6 = this.AlanAyariStcr;
                    int i14 = alanAyari6.f1195Y;
                    strArr10[i14] = StringUtils.insert(strArr10[i14], alanAyari6.f1194X, str2);
                }
                i12++;
            }
            GenelAyarlar genelAyarlar = this.GenelAyarlarStcr;
            int i15 = genelAyarlar.detayBaslangicYuksekligi;
            int i16 = (genelAyarlar.detaySatirSayisi * i2) + i15;
            int i17 = 0;
            while (i17 <= split2.length - 1) {
                String[] addCalculatedFields2 = addCalculatedFields(StringUtils.split(StringUtils.replace(StringUtils.replace(split2[i17], String.valueOf('\r'), ""), String.valueOf(c3), ""), c2), PrintDesignSection.DETAIL);
                int i18 = 0;
                int i19 = 0;
                while (i18 <= addCalculatedFields2.length - 1) {
                    String[] strArr11 = this.DetayAlanAyarlari;
                    if (strArr11.length - 1 == i18) {
                        break;
                    }
                    int i20 = i18 + 1;
                    String[] split6 = StringUtils.split(strArr11[i20], this.AlanAyariSeparator);
                    int length2 = split6.length;
                    int i21 = 0;
                    while (i21 < length2) {
                        String[] strArr12 = split2;
                        if (GetFieldSettings(split6[i21], BaskiBolumleri.Detay)) {
                            String str3 = addCalculatedFields2[i18];
                            String stringResource = ContextUtils.getStringResource(R.string.str_material_description);
                            strArr2 = addCalculatedFields2;
                            i4 = i18;
                            if (ErpCreator.getInstance().getmBaseErp().getErpType() == ErpType.NETSIS) {
                                stringResource = ContextUtils.getStringResource(R.string.str_stock_name);
                            }
                            if (this.AlanAyariStcr.deger.equals(stringResource)) {
                                int length3 = str3.length();
                                int i22 = this.AlanAyariStcr.uzunluk;
                                if (length3 > i22) {
                                    String[] wrapTextInColumns = StringUtils.wrapTextInColumns(str3, i22);
                                    if (wrapTextInColumns.length - 1 > i19) {
                                        i19 = wrapTextInColumns.length - 1;
                                    }
                                    int i23 = 0;
                                    for (int i24 = 1; i23 <= wrapTextInColumns.length - i24; i24 = 1) {
                                        String str4 = wrapTextInColumns[i23];
                                        AlanAyari alanAyari7 = this.AlanAyariStcr;
                                        String[] strArr13 = wrapTextInColumns;
                                        String SetValueAlign2LeftRigth = SetValueAlign2LeftRigth(str4, alanAyari7.uzunluk, alanAyari7.leftOrRight);
                                        AlanAyari alanAyari8 = this.AlanAyariStcr;
                                        int i25 = alanAyari8.f1195Y + i15 + i23;
                                        int i26 = i19;
                                        String[] strArr14 = this.DataStr;
                                        strArr14[i25] = StringUtils.remove(strArr14[i25], alanAyari8.f1194X, SetValueAlign2LeftRigth);
                                        String[] strArr15 = this.DataStr;
                                        strArr15[i25] = StringUtils.insert(strArr15[i25], this.AlanAyariStcr.f1194X, SetValueAlign2LeftRigth);
                                        i23++;
                                        wrapTextInColumns = strArr13;
                                        i19 = i26;
                                        split6 = split6;
                                    }
                                }
                            }
                            strArr3 = split6;
                            AlanAyari alanAyari9 = this.AlanAyariStcr;
                            if (alanAyari9.cutWord == 1) {
                                str3 = Substring(str3, alanAyari9.cutWordLength);
                            }
                            AlanAyari alanAyari10 = this.AlanAyariStcr;
                            String SetValueAlign2LeftRigth2 = SetValueAlign2LeftRigth(str3, alanAyari10.uzunluk, alanAyari10.leftOrRight);
                            AlanAyari alanAyari11 = this.AlanAyariStcr;
                            int i27 = alanAyari11.f1195Y + i15;
                            String[] strArr16 = this.DataStr;
                            strArr16[i27] = StringUtils.remove(strArr16[i27], alanAyari11.f1194X, SetValueAlign2LeftRigth2);
                            String[] strArr17 = this.DataStr;
                            strArr17[i27] = StringUtils.insert(strArr17[i27], this.AlanAyariStcr.f1194X, SetValueAlign2LeftRigth2);
                            i21++;
                            addCalculatedFields2 = strArr2;
                            split2 = strArr12;
                            i18 = i4;
                            split6 = strArr3;
                        } else {
                            strArr2 = addCalculatedFields2;
                            i4 = i18;
                        }
                        strArr3 = split6;
                        i21++;
                        addCalculatedFields2 = strArr2;
                        split2 = strArr12;
                        i18 = i4;
                        split6 = strArr3;
                    }
                    i18 = i20;
                }
                String[] strArr18 = split2;
                i15 += this.GenelAyarlarStcr.detaySatirSayisi + i19;
                i16 += i19;
                i17++;
                split2 = strArr18;
                c2 = '\t';
                c3 = '\n';
            }
            int i28 = 0;
            while (true) {
                String[] strArr19 = this.DetaySabitAlanAyarlari;
                if (i28 > strArr19.length - 1 || strArr19.length - 1 == i28) {
                    break;
                }
                if (GetFieldSettings(strArr19[i28], BaskiBolumleri.DetaySabitAlan)) {
                    AlanAyari alanAyari12 = this.AlanAyariStcr;
                    String str5 = alanAyari12.deger;
                    if (alanAyari12.cutWord == 1) {
                        str5 = Substring(str5, alanAyari12.cutWordLength);
                    }
                    int i29 = this.GenelAyarlarStcr.detayBaslangicYuksekligi;
                    String[] strArr20 = this.DataStr;
                    AlanAyari alanAyari13 = this.AlanAyariStcr;
                    int i30 = alanAyari13.f1195Y;
                    strArr20[i29 + i30] = StringUtils.remove(strArr20[i30 + i29], alanAyari13.f1194X, str5);
                    String[] strArr21 = this.DataStr;
                    AlanAyari alanAyari14 = this.AlanAyariStcr;
                    int i31 = alanAyari14.f1195Y;
                    strArr21[i29 + i31] = StringUtils.insert(strArr21[i29 + i31], alanAyari14.f1194X, str5);
                }
                i28++;
            }
            int i32 = 0;
            boolean z = false;
            while (i32 <= split3.length - 1) {
                String[] addCalculatedFields3 = addCalculatedFields(StringUtils.split(StringUtils.replace(StringUtils.replace(split3[i32], String.valueOf('\r'), ""), String.valueOf('\n'), ""), '\t'), PrintDesignSection.SUMMARY);
                int i33 = 0;
                while (i33 <= addCalculatedFields3.length - 1) {
                    String[] strArr22 = this.SayfaSonuAlanAyarlari;
                    if (strArr22.length - 1 == i33) {
                        break;
                    }
                    int i34 = i33 + 1;
                    String[] split7 = StringUtils.split(strArr22[i34], this.AlanAyariSeparator);
                    int length4 = split7.length;
                    int i35 = 0;
                    while (i35 < length4) {
                        if (GetFieldSettings(split7[i35], BaskiBolumleri.SayfaSonu)) {
                            String str6 = addCalculatedFields3[i33];
                            AlanAyari alanAyari15 = this.AlanAyariStcr;
                            if (alanAyari15.cutWord == 1) {
                                str6 = Substring(str6, alanAyari15.cutWordLength);
                            }
                            AlanAyari alanAyari16 = this.AlanAyariStcr;
                            String SetValueAlign2LeftRigth3 = SetValueAlign2LeftRigth(str6, alanAyari16.uzunluk, alanAyari16.leftOrRight);
                            AlanAyari alanAyari17 = this.AlanAyariStcr;
                            int i36 = alanAyari17.f1195Y + i16;
                            String[] strArr23 = this.DataStr;
                            strArr = split3;
                            strArr23[i36] = StringUtils.remove(strArr23[i36], alanAyari17.f1194X, SetValueAlign2LeftRigth3);
                            String[] strArr24 = this.DataStr;
                            strArr24[i36] = StringUtils.insert(strArr24[i36], this.AlanAyariStcr.f1194X, SetValueAlign2LeftRigth3);
                            z = true;
                        } else {
                            strArr = split3;
                        }
                        i35++;
                        split3 = strArr;
                    }
                    i33 = i34;
                }
                i32++;
                split3 = split3;
            }
            if (this.SayfaSonuString.trim().length() != 0) {
                int i37 = 0;
                while (true) {
                    String[] strArr25 = this.SayfaSonuSabitAlanAyarlari;
                    if (i37 > strArr25.length - 1 || strArr25.length - 1 == i37) {
                        break;
                    }
                    if (GetFieldSettings(strArr25[i37], BaskiBolumleri.SayfaSonuSabitAlan)) {
                        AlanAyari alanAyari18 = this.AlanAyariStcr;
                        String str7 = alanAyari18.deger;
                        if (alanAyari18.cutWord == 1) {
                            str7 = Substring(str7, alanAyari18.cutWordLength);
                        }
                        AlanAyari alanAyari19 = this.AlanAyariStcr;
                        int i38 = alanAyari19.f1195Y + i16;
                        String[] strArr26 = this.DataStr;
                        strArr26[i38] = StringUtils.remove(strArr26[i38], alanAyari19.f1194X, str7);
                        String[] strArr27 = this.DataStr;
                        strArr27[i38] = StringUtils.insert(strArr27[i38], this.AlanAyariStcr.f1194X, str7);
                        z = true;
                    }
                    i37++;
                }
            }
            if (z) {
                GenelAyarlar genelAyarlar2 = this.GenelAyarlarStcr;
                int i39 = genelAyarlar2.detaySatirSayisi;
                if (i39 != 0) {
                    i3 = genelAyarlar2.sayfasonuYuksekligi / (genelAyarlar2.detayYuksekligi / i39);
                } else {
                    i3 = genelAyarlar2.sayfasonuYuksekligi;
                }
                i16 += i3;
            }
            for (int i40 = 0; i40 <= split4.length - 1; i40++) {
                String[] addCalculatedFields4 = addCalculatedFields(StringUtils.split(StringUtils.replace(StringUtils.replace(split4[i40], String.valueOf('\r'), ""), String.valueOf('\n'), ""), '\t'), PrintDesignSection.FOOTER);
                int i41 = 0;
                while (i41 <= addCalculatedFields4.length - 1) {
                    String[] strArr28 = this.BaskiSonuAlanAyarlari;
                    if (strArr28.length - 1 == i41) {
                        break;
                    }
                    int i42 = i41 + 1;
                    for (String str8 : StringUtils.split(strArr28[i42], this.AlanAyariSeparator)) {
                        if (GetFieldSettings(str8, BaskiBolumleri.BaskiSonu)) {
                            String str9 = addCalculatedFields4[i41];
                            AlanAyari alanAyari20 = this.AlanAyariStcr;
                            if (alanAyari20.cutWord == 1) {
                                str9 = Substring(str9, alanAyari20.cutWordLength);
                            }
                            AlanAyari alanAyari21 = this.AlanAyariStcr;
                            String SetValueAlign2LeftRigth4 = SetValueAlign2LeftRigth(str9, alanAyari21.uzunluk, alanAyari21.leftOrRight);
                            AlanAyari alanAyari22 = this.AlanAyariStcr;
                            int i43 = alanAyari22.f1195Y + i16;
                            String[] strArr29 = this.DataStr;
                            strArr29[i43] = StringUtils.remove(strArr29[i43], alanAyari22.f1194X, SetValueAlign2LeftRigth4);
                            String[] strArr30 = this.DataStr;
                            strArr30[i43] = StringUtils.insert(strArr30[i43], this.AlanAyariStcr.f1194X, SetValueAlign2LeftRigth4);
                        }
                    }
                    i41 = i42;
                }
            }
            if (this.BaskiSonuString.trim().length() != 0) {
                int i44 = 0;
                while (true) {
                    String[] strArr31 = this.BaskiSonuSabitAlanAyarlari;
                    if (i44 > strArr31.length - 1 || strArr31.length - 1 == i44) {
                        break;
                    }
                    if (GetFieldSettings(strArr31[i44], BaskiBolumleri.BaskiSonuSabitAlan)) {
                        AlanAyari alanAyari23 = this.AlanAyariStcr;
                        String str10 = alanAyari23.deger;
                        if (alanAyari23.cutWord == 1) {
                            str10 = Substring(str10, alanAyari23.cutWordLength);
                        }
                        AlanAyari alanAyari24 = this.AlanAyariStcr;
                        int i45 = alanAyari24.f1195Y + i16;
                        String[] strArr32 = this.DataStr;
                        strArr32[i45] = StringUtils.remove(strArr32[i45], alanAyari24.f1194X, str10);
                        String[] strArr33 = this.DataStr;
                        strArr33[i45] = StringUtils.insert(strArr33[i45], this.AlanAyariStcr.f1194X, str10);
                    }
                    i44++;
                }
            }
            String str11 = "";
            int i46 = 0;
            while (i46 <= this.DataStr.length - 1) {
                i46++;
                str11 = str11 + this.DataStr[i46].replaceFirst("\\s+", "") + "\r\n";
            }
            String replaceFirst = str11.replaceFirst("\\s+", "");
            return replaceFirst.trim().length() == 0 ? "" : replaceFirst;
        } catch (Exception e2) {
            return GetError(e2);
        }
    }
    public static String trimEnd(String str, String str2) {
        return str.endsWith(str2) ? str.substring(0, str.length() - str2.length()) : str;
    }
    public String Print(int i2, String str, String str2, String str3, String str4) {
        this.printSettingsJsonParse = new PrintSettingsJsonParse();
        this.BaslikString = str;
        this.DetayString = str2;
        this.SayfaSonuString = str3;
        this.BaskiSonuString = str4;
        String jsonText = getJsonText();
        if (jsonText == null) {
            Log.e("AA", "Hata json verisi null");
            return null;
        }
        this.printSettingsJsonParse.parseJson(jsonText);
        getCalculatedFields();
        SetFieldsSettings();
        GetDefaultSettings();
        return Print(i2);
    }
}
