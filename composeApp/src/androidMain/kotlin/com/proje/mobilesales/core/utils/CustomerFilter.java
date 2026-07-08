package com.proje.mobilesales.core.utils;

import com.proje.mobilesales.features.model.SpecodeGroup;

public class CustomerFilter {
    private String cityFilter;
    private int customerType;
    private int dayRef;
    private String filter;
    private boolean isSelectedAllRoute;
    private double nearRpDistance;
    private int routeRef;
    private int searchType;
    private int selectedDebitOrder;
    private int showTitleType;
    private int sortType;
    private SpecodeGroup specodeGroup;
    private String townFilter;
    private boolean useNear;
    private boolean useOrderNameOrCode;
    private int userNerarType;

    public CustomerFilter() {
    }

    public CustomerFilter(int i2, boolean z, double d2, int i3, int i4, String str, int i5, int i6, boolean z2, int i7, int i8, int i9, boolean z3, String str2, String str3, SpecodeGroup specodeGroup) {
        this.userNerarType = i2;
        this.useNear = z;
        this.nearRpDistance = d2;
        this.dayRef = i3;
        this.customerType = i4;
        this.filter = str;
        this.routeRef = i5;
        this.selectedDebitOrder = i6;
        this.useOrderNameOrCode = z2;
        this.sortType = i7;
        this.searchType = i8;
        this.showTitleType = i9;
        this.isSelectedAllRoute = z3;
        setCityFilter(str2);
        setTownFilter(str3);
        this.specodeGroup = specodeGroup;
    }

    public int getUserNerarType() {
        return this.userNerarType;
    }

    public void setUserNerarType(int i2) {
        this.userNerarType = i2;
    }

    public boolean isUseNear() {
        return this.useNear;
    }

    public void setUseNear(boolean z) {
        this.useNear = z;
    }

    public double getNearRpDistance() {
        return this.nearRpDistance;
    }

    public void setNearRpDistance(double d2) {
        this.nearRpDistance = d2;
    }

    public int getDayRef() {
        return this.dayRef;
    }

    public void setDayRef(int i2) {
        this.dayRef = i2;
    }

    public int getCustomerType() {
        return this.customerType;
    }

    public void setCustomerType(int i2) {
        this.customerType = i2;
    }

    public String getFilter() {
        return this.filter;
    }

    public void setFilter(String str) {
        this.filter = str;
    }

    public int getRouteRef() {
        return this.routeRef;
    }

    public void setRouteRef(int i2) {
        this.routeRef = i2;
    }

    public int getSelectedDebitOrder() {
        return this.selectedDebitOrder;
    }

    public void setSelectedDebitOrder(int i2) {
        this.selectedDebitOrder = i2;
    }

    public boolean isUseOrderNameOrCode() {
        return this.useOrderNameOrCode;
    }

    public void setUseOrderNameOrCode(boolean z) {
        this.useOrderNameOrCode = z;
    }

    public int getSortType() {
        return this.sortType;
    }

    public void setSortType(int i2) {
        this.sortType = i2;
    }

    public int getSearchType() {
        return this.searchType;
    }

    public void setSearchType(int i2) {
        this.searchType = i2;
    }

    public int getShowTitleType() {
        return this.showTitleType;
    }

    public void setShowTitleType(int i2) {
        this.showTitleType = i2;
    }

    public boolean isSelectedAllRoute() {
        return this.isSelectedAllRoute;
    }

    public void setSelectedAllRoute(boolean z) {
        this.isSelectedAllRoute = z;
    }

    public String getCityFilter() {
        return this.cityFilter;
    }

    public void setCityFilter(String str) {
        this.cityFilter = str;
    }

    public String getTownFilter() {
        return this.townFilter;
    }

    public void setTownFilter(String str) {
        this.townFilter = str;
    }

    public SpecodeGroup getSpecodeGroup() {
        return this.specodeGroup;
    }

    public void setSpecodeGroup(SpecodeGroup specodeGroup) {
        this.specodeGroup = specodeGroup;
    }
}
