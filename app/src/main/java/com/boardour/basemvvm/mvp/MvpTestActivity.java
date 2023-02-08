package com.boardour.basemvvm.mvp;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.base.mvp.MvpActivity;
import com.base.mvp.inject.InjectPresenter;
import com.boardour.basemvvm.R;
import com.boardour.basemvvm.mvp.presenter.LoginPresenter;
import com.boardour.basemvvm.mvp.presenter.MainPresenter;
import com.boardour.basemvvm.mvp.view.ILoginView;
import com.boardour.basemvvm.mvp.view.IMainView;

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
        titleBar.setRightText("测试", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MvpTestActivity.this, "测试", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean titleDark() {
        return true;
    }

    @Override
    public int titleBackImageFilterColor() {
        return Color.BLACK;
    }

    @Override
    public int titleColor() {
        return Color.WHITE;
    }

    @Override
    public int titleContentColor() {
        return Color.TRANSPARENT;
    }

    @Override
    public int titleTextColor() {
        return Color.RED;
    }

    @Override
    public int titleRightTextColor() {
        return Color.RED;
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
