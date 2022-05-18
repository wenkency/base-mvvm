package com.lven.baseproject.mvptest.model;


import com.base.mvp.core.IModel;

import java.util.ArrayList;
import java.util.List;


public class MainModel implements IModel {
    public List<String> loadList() {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            items.add("item" + i);
        }
        return items;
    }

    public String loadItem() {
        return "loadItem";
    }
}
