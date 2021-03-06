package com.lven.baseproject.mvptest.view;

import com.lven.base.mvp.core.IView;

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
