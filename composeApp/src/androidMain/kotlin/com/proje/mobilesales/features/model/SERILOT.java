package com.proje.mobilesales.features.model;

import java.io.Serializable;

public class SERILOT implements Serializable {
    public float AMOUNT;
    public String CODE;
    public int DETAILREF;
    public String EXPDATE;
    public int LOGICALREF;
    public String UNIT;

    public static String getSql() {
        return "CREATE TABLE IF NOT EXISTS SERILOT (LOGICALREF INTEGER, DETAILREF INTEGER, CODE VARCHAR, AMOUNT FLOAT, UNIT VARCHAR);";
    }
}
