package com.proje.mobilesales.features.product.repository;

import com.proje.mobilesales.core.base.IBaseRepository;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.product.model.ProductTreeItem;
import java.util.ArrayList;

public interface IBaseProductRepository extends IBaseRepository {
    void getProductTreeItems(ArrayList<String> arrayList, ProductTreeItem productTreeItem, ResponseListener<?> responseListener);
}
