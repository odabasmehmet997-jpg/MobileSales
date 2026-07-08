package com.proje.mobilesales.features.model;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;
public class SurplusDiscount {


    private String aciklama;

    
    private double ind1;


    private double ind2;

    private double ind3;
    private double ind4;
    private double ind5;
    private double ind6;
    private String kosulKod;
    private double malFazlasi1;
    private double malfMiktar1;
    private String malfStokKodu;
    private String oranTutar;

    public String getKosulKod() {
        return kosulKod;
    }

    public String getAciklama() {
        return aciklama;
    }

    public String getOranTutar() {
        return oranTutar;
    }

    public double getInd1() {
        return ind1;
    }

    public double getInd2() {
        return ind2;
    }

    public double getInd3() {
        return ind3;
    }

    public double getInd4() {
        return ind4;
    }

    public double getInd5() {
        return ind5;
    }

    public double getInd6() {
        return ind6;
    }

    public String getMalfStokKodu() {
        return malfStokKodu;
    }

    public double getMalfMiktar1() {
        return malfMiktar1;
    }

    public double getMalFazlasi1() {
        return malFazlasi1;
    }
}
