package com.base.mvp.impl;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.base.mvp.core.IPresenter;
import com.base.mvp.core.IProxyPresenter;
import com.base.mvp.core.IView;
import com.base.mvp.inject.InjectPresenter;


/**
 * 解决一个类多个Presenter
 */
public class ProxyPresenter implements IProxyPresenter {
    private List<IPresenter> presenters = new ArrayList<>();

    @Override
    public void bindAndCreatePresenter(IView view) {
        if (view == null) {
            return;
        }
        Field[] fields = view.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectPresenter annotation = field.getAnnotation(InjectPresenter.class);
            if (annotation != null) {
                try {
                    field.setAccessible(true);
                    // 创建一个Presenter
                    IPresenter presenter = (IPresenter) field.getType().newInstance();
                    field.set(view, presenter);
                    // 绑定
                    presenter.attach(view);
                    presenters.add(presenter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void unbindPresenter() {
        for (IPresenter presenter : presenters) {
            presenter.detach();
        }
        presenters.clear();
        presenters = null;
    }
}
