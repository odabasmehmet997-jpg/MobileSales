package com.proje.mobilesales.features.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class VariantModel extends BaseObservable {
    private ObservableField<String> actualStock;
    private ObservableField<String> amount;
    private final ObservableField<Boolean> checked;
    private final ObservableField<String> code;
    private ObservableField<String> definedPrice;
    private int id;
    private final ObservableField<String> name;
    private final PriceInfo priceInfo;
    private final ObservableField<String> realStock;
    private boolean striked;
    public VariantModel(final ObservableField observableField, final ObservableField observableField2, final ObservableField observableField3, final ObservableField observableField4, final ObservableField observableField5, final ObservableField observableField6, final int i2, final ObservableField observableField7, final PriceInfo priceInfo, final boolean z, final int i3, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i3 & 1) ? new ObservableField() : observableField, 0 != (i3 & 2) ? new ObservableField() : observableField2, 0 != (i3 & 4) ? new ObservableField() : observableField3, 0 != (i3 & 8) ? new ObservableField() : observableField4, 0 != (i3 & 16) ? new ObservableField() : observableField5, 0 != (i3 & 32) ? new ObservableField() : observableField6, 0 != (i3 & 64) ? 0 : i2, 0 != (i3 & 128) ? new ObservableField() : observableField7, priceInfo, z);
    }
    public ObservableField<String> getActualStock() {
        return actualStock;
    }
    public void setActualStock(final ObservableField<String> observableField) {
        Intrinsics.checkNotNullParameter(observableField, "<set-?>");
        actualStock = observableField;
    }
    public ObservableField<String> getRealStock() {
        return realStock;
    }
    public ObservableField<String> getName() {
        return name;
    }
    public ObservableField<String> getCode() {
        return code;
    }
    public ObservableField<String> getAmount() {
        return amount;
    }
    public void setAmount(final ObservableField<String> observableField) {
        Intrinsics.checkNotNullParameter(observableField, "<set-?>");
        amount = observableField;
    }
    public ObservableField<Boolean> getChecked() {
        return checked;
    }
    public int getId() {
        return id;
    }
    public void setId(final int i2) {
        id = i2;
    }
    public ObservableField<String> getDefinedPrice() {
        return definedPrice;
    }
    public void setDefinedPrice(final ObservableField<String> observableField) {
        Intrinsics.checkNotNullParameter(observableField, "<set-?>");
        definedPrice = observableField;
    }
    public PriceInfo getPriceInfo() {
        return priceInfo;
    }
    public boolean getStriked() {
        return striked;
    }
    public void setStriked(final boolean z) {
        striked = z;
    }
    public VariantModel(final ObservableField<String> actualStock, final ObservableField<String> realStock, final ObservableField<String> name, final ObservableField<String> code, final ObservableField<String> amount, final ObservableField<Boolean> checked, final int i2, final ObservableField<String> definedPrice, final PriceInfo priceInfo, final boolean z) {
        Intrinsics.checkNotNullParameter(actualStock, "actualStock");
        Intrinsics.checkNotNullParameter(realStock, "realStock");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(amount, "amount");
        Intrinsics.checkNotNullParameter(checked, "checked");
        Intrinsics.checkNotNullParameter(definedPrice, "definedPrice");
        Intrinsics.checkNotNullParameter(priceInfo, "priceInfo");
        this.actualStock = actualStock;
        this.realStock = realStock;
        this.name = name;
        this.code = code;
        this.amount = amount;
        this.checked = checked;
        id = i2;
        this.definedPrice = definedPrice;
        this.priceInfo = priceInfo;
        striked = z;
    }
}
