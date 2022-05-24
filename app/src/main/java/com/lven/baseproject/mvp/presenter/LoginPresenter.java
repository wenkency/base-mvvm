package com.lven.baseproject.mvp.presenter;


import com.base.mvp.impl.BasePresenter;
import com.lven.baseproject.mvp.model.LoginModel;
import com.lven.baseproject.mvp.view.ILoginView;

/**
 * 另外一个Presenter
 */
public class LoginPresenter extends BasePresenter<LoginModel, ILoginView> {
    public void showText() {
        getView().showText(getModel().getText());
    }
}
