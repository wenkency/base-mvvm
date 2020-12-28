package com.lven.baseproject.mvptest.presenter;


import com.lven.base.mvp.impl.BasePresenter;
import com.lven.baseproject.mvptest.model.LoginModel;
import com.lven.baseproject.mvptest.view.ILoginView;

/**
 * 另外一个Presenter
 */
public class LoginPresenter extends BasePresenter<LoginModel, ILoginView> {
    public void showText() {
        getView().showText(getModel().getText());
    }
}
