package com.boardour.basemvvm.mvp.view;

import com.base.mvp.core.IView;

import java.util.List;


public interface IMainView extends IView {

    /**
     * 显示
     */
    void showItem(String item);

    /**
     * 显示列表
     */
    void showList(List<String> items);
}
