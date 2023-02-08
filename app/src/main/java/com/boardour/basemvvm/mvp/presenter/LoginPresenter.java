package com.boardour.basemvvm.mvp.presenter;


import com.base.mvp.impl.BasePresenter;
import com.boardour.basemvvm.mvp.model.LoginModel;
import com.boardour.basemvvm.mvp.view.ILoginView;

/**
 * 另外一个Presenter
 */
public class LoginPresenter extends BasePresenter<LoginModel, ILoginView> {
    public void showText() {
        getView().showText(getModel().getText());
    }
}
