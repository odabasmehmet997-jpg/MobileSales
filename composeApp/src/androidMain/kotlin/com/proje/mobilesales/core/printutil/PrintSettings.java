package com.proje.mobilesales.core.printutil;

import java.util.ArrayList;
import java.util.List;

public class PrintSettings {
    private DesignPattern designPattern;
    private List<PrintBaslik> printBaslikList;
    public PrintSettings() {
        this.printBaslikList = new ArrayList();
        this.designPattern = new DesignPattern();
    }
    public DesignPattern getDesignPattern() {
        return this.designPattern;
    }
    public void setDesignPattern(DesignPattern designPattern) {
        this.designPattern = designPattern;
    }
    public List<PrintBaslik> getPrintBaslikList() {
        return this.printBaslikList;
    }
    public void setPrintBaslikList(List<PrintBaslik> list) {
        this.printBaslikList = list;
    }
    public void addPrintBaslik(PrintBaslik printBaslik) {
        this.printBaslikList.add(printBaslik);
    }
    public PrintBaslik getPrintBaslik(int i2) {
        for (PrintBaslik printBaslik : this.printBaslikList) {
            if (printBaslik.getId() == i2) {
                return printBaslik;
            }
        }
        return null;
    }
}
