package com.boardour.basemvvm.mvp.presenter;

import com.base.mvp.impl.BasePresenter;
import com.boardour.basemvvm.mvp.model.MainModel;
import com.boardour.basemvvm.mvp.view.IMainView;

import java.util.List;


public class MainPresenter extends BasePresenter<MainModel, IMainView> implements IMainView {

    /**
     * View的控制逻辑
     */
    public void loadList() {
        // 从Model层加载数据
        showList(getModel().loadList());
    }

    /**
     * View的控制逻辑
     */
    public void loadItem() {
        // 从Model层加载数据
        showItem(getModel().loadItem());
    }

    /**
     * View的显示逻辑
     */
    @Override
    public void showItem(String item) {
        getView().showItem(item);
    }

    @Override
    public void showList(List<String> items) {
        getView().showList(items);
    }


}
