package com.lven.baseproject.mvp;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.base.mvp.MvpActivity;
import com.base.mvp.inject.InjectPresenter;
import com.lven.baseproject.R;
import com.lven.baseproject.mvp.presenter.LoginPresenter;
import com.lven.baseproject.mvp.presenter.MainPresenter;
import com.lven.baseproject.mvp.view.ILoginView;
import com.lven.baseproject.mvp.view.IMainView;

import java.util.List;

import cn.carhouse.titlebar.DefTitleBar;

/**
 * MVP写法
 */
public class MvpTestActivity extends MvpActivity<MainPresenter> implements IMainView, ILoginView {


    private TextView tv;

    // 添加注解，自动创建另外一个Presenter
    @InjectPresenter
    private LoginPresenter loginPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mvp_test;
    }

    /**
     * 不要加载页面
     */
    @Override
    public boolean isNeedLoading() {
        return false;
    }

    @Override
    public void initTitle(DefTitleBar titleBar) {
        titleBar.setTitle("MVP测试");
        // 不要返回按钮
        titleBar.clearBackImage();
        titleBar.setRightTextColor(Color.WHITE);
        titleBar.setRightText("测试", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MvpTestActivity.this, "测试", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initViews() {
        tv = findViewById(R.id.tv);
    }


    /**
     * 点击事件
     */
    public void loadItem(View view) {
        getPresenter().loadItem();
    }

    /**
     * 点击事件
     */
    public void loadList(View view) {
        getPresenter().loadList();
    }

    /**
     * 调用另外一个Presenter类
     *
     * @param view
     */
    public void loginCall(View view) {
        loginPresenter.showText();
    }

    // P层的回调
    @Override
    public void showItem(String item) {
        tv.setText(item);
    }

    // P层的回调
    @Override
    public void showList(List<String> items) {
        tv.setText(items.toString());
    }

    @Override
    public void showText(String text) {
        tv.setText(text);
    }
}
