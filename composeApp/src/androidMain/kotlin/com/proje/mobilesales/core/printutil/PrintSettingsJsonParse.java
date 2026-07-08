package com.proje.mobilesales.core.printutil;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class PrintSettingsJsonParse {
    private static final String TAG = PrintSettingsJsonParse.class.getSimpleName();
    JSONObject jsonObject;
    private PrintSettings printSettings;
    public PrintSettingsJsonParse() {
        setPrintSettings(new PrintSettings());
    }
    public void parseJson(String str) {
        try {
            this.jsonObject = new JSONObject(str);
            parseDesignPattern();
            parseBaslik();
        } catch (Exception e2) {
            printLog(e2.getMessage());
        }
    }
    private void printLog(String message) {
        Log.e(TAG, message);
    }
    private void parseDesignPattern() {
        try {
            JSONObject jSONObject = this.jsonObject.getJSONObject(JsonNames.getTagDesignPattern());
            getPrintSettings().getDesignPattern().setNr(jSONObject.getInt(JsonNames.getTagNr()));
            getPrintSettings().getDesignPattern().setX(jSONObject.getInt(JsonNames.getTagX()));
            getPrintSettings().getDesignPattern().setY(jSONObject.getInt(JsonNames.getTagY()));
            getPrintSettings().getDesignPattern().setWidth(jSONObject.getInt(JsonNames.getTagWidth()));
            getPrintSettings().getDesignPattern().setHeight(jSONObject.getInt(JsonNames.getTagHeight()));
            getPrintSettings().getDesignPattern().setBaslikHeight(jSONObject.getInt(JsonNames.getTagBaslikHeight()));
            getPrintSettings().getDesignPattern().setDetayHeight(jSONObject.getInt(JsonNames.getTagDetayHeight()));
            getPrintSettings().getDesignPattern().setSayfaSonuHeight(jSONObject.getInt(JsonNames.getTagSayfasonuHeight()));
            getPrintSettings().getDesignPattern().setBaskiSonuHeight(jSONObject.getInt(JsonNames.getTagBaskisonuHeight()));
            getPrintSettings().getDesignPattern().setEmptyAreaHeight(jSONObject.getInt(JsonNames.getTagEmptyareaheight()));
            getPrintSettings().getDesignPattern().setGenelBaslikHeight(jSONObject.getInt(JsonNames.getTagBaslikheight()));
            getPrintSettings().getDesignPattern().setGenelDetayHeight(jSONObject.getInt(JsonNames.getTagDetayheight()));
            getPrintSettings().getDesignPattern().setGenelSayfaSonuHeight(jSONObject.getInt(JsonNames.getTagSayfasonuheight()));
            getPrintSettings().getDesignPattern().setGenelBaskiSonuHeight(jSONObject.getInt(JsonNames.getTagBaskisonuheight()));
            getPrintSettings().getDesignPattern().setHucreBoyu(jSONObject.getInt(JsonNames.getTagHucreboyu()));
            getPrintSettings().getDesignPattern().setHucreEni(jSONObject.getInt(JsonNames.getTagHucreeni()));
        } catch (Exception e2) {
            printLog(e2.getMessage());
        }
    }
    private void printLog(String message) {
        Log.e(TAG, message);
    }
    private void parseBaslik() {
        int i2 = 0;
        while (i2 < JsonNames.TAG_BASLIK.length) {
            PrintBaslik printBaslik = new PrintBaslik();
            try {
                JSONObject jSONObject = this.jsonObject.getJSONObject(JsonNames.TAG_BASLIK[i2]);
                printBaslik.setId(i2);
                if (jSONObject != null) {
                    printBaslik.setPrintAlanList(parseBaslikAlan(jSONObject.getJSONArray(JsonNames.TAG_ALAN)));
                    if (i2 == 3) {
                        printBaslik.setBaslangicY(jSONObject.getInt(JsonNames.TAG_BASLANGIC_Y));
                        printBaslik.setDetaySatirSayisi(jSONObject.getInt(JsonNames.TAG_DETAY_SATIR_SAYISI));
                    }
                }
                getPrintSettings().addPrintBaslik(printBaslik);
            } catch (Exception e2) {
                printLog(e2.getMessage());
                printBaslik.setId(i2);
                getPrintSettings().addPrintBaslik(printBaslik);
            }
            i2++;
        }
    }
    private List<PrintAlan> parseBaslikAlan(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                PrintAlan printAlan = new PrintAlan();
                printAlan.setTextType(jSONObject.getInt(JsonNames.TAG_TEXTTYPE));
                printAlan.setAlanNo(jSONObject.getInt(JsonNames.TAG_ALANO));
                printAlan.setValue(jSONObject.getString(JsonNames.TAG_VALUE));
                printAlan.setX(jSONObject.getInt(JsonNames.TAG_X));
                printAlan.setY(jSONObject.getInt(JsonNames.TAG_Y));
                printAlan.setW(jSONObject.getInt(JsonNames.TAG_W));
                printAlan.setAlign(jSONObject.getString(JsonNames.TAG_ALIGN));
                printAlan.setIsCutWord(jSONObject.getInt(JsonNames.TAG_IS_CUTWORD));
                printAlan.setWorldLength(jSONObject.getInt(JsonNames.TAG_WORD_LENGTH));
                arrayList.add(printAlan);
            } catch (Exception e2) {
                printLog(e2.getMessage());
            }
        }
        return arrayList;
    }
    private void printLog(String message) {
        Log.e(TAG, message);
    }
    public PrintSettings getPrintSettings() {
        return this.printSettings;
    }
    public void setPrintSettings(PrintSettings printSettings) {
        this.printSettings = printSettings;
    }
}
