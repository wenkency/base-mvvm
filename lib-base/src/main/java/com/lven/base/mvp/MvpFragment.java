package com.lven.base.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lven.base.AppFragment;
import com.lven.base.mvp.core.IPresenter;
import com.lven.base.mvp.core.IProxyPresenter;
import com.lven.base.mvp.core.IView;
import com.lven.base.mvp.impl.ProxyPresenter;


public abstract class MvpFragment<P extends IPresenter> extends AppFragment implements IView {
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


    protected abstract P createPresenter();


}
