package com.base.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;

import java.lang.reflect.ParameterizedType;

import com.base.AppActivity;
import com.base.mvp.core.IPresenter;
import com.base.mvp.core.IProxyPresenter;
import com.base.mvp.core.IView;
import com.base.mvp.impl.ProxyPresenter;


public abstract class MvpActivity<P extends IPresenter> extends AppActivity implements IView {
    protected P mPresenter;
    private IProxyPresenter mProxyPresenter;

    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState) {
        bindPresenter();
        super.onCreate(savedInstanceState);
    }

    /**
     * 绑定Presenter
     */
    private final void bindPresenter() {
        // 1. 绑定常用的
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
        // 2. 创建和绑定其它
        mProxyPresenter = createProxyPresenter();
        if (mProxyPresenter != null) {
            mProxyPresenter.bindAndCreatePresenter(this);
        }
        // 3. 在这里
    }

    protected IProxyPresenter createProxyPresenter() {
        if (mProxyPresenter == null) {
            mProxyPresenter = new ProxyPresenter();
        }
        return mProxyPresenter;
    }

    /**
     * 解绑Presenter
     */
    private final void unbindPresenter() {
        if (mPresenter != null) {
            mPresenter.detach();
        }
        if (mProxyPresenter != null) {
            mProxyPresenter.unbindPresenter();
        }
    }

    @Override
    public void onDestroy() {
        unbindPresenter();
        super.onDestroy();
    }

    /**
     * 创建Presenter
     * 子类要指定泛型类型如：MainActivity extends MvpActivity<MainPresenter>
     */
    protected P createPresenter() {
        try {
            ParameterizedType superClass = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class actualType = (Class) superClass.getActualTypeArguments()[0];
            mPresenter = (P) actualType.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mPresenter;
    }

    public P getPresenter() {
        return mPresenter;
    }
}
