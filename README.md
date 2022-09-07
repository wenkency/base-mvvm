# base-mvvm

* mvvm通用开发架构，适用于JetPack组件的mvvm开发框架，快速开发Android项目。
* 同时封装了mvp基础类，也支持mvp写法，不过不建议使用，mvp写法要定义的接口太多。
* 项目里有大量的实例，可以参考查看。

### 核心类：

* BaseActivity <- AppActivity <- BindActivity
* BaseFragment <- AppFragment <- BindFragment

### 引入

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

android {
    ...
    // Java 1.8
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
    // 这个是使用dataBinding
    buildFeatures {
        dataBinding true
    }
}
// MVVM基本库
implementation 'com.github.wenkency:base-mvvm:1.9.4'

// lifecycle扩展库：ViewModel + LiveData
def lifecycle_version = "2.4.1"
implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"

// 通用标题栏：https://github.com/wenkency/titlebar
implementation 'com.github.wenkency:titlebar:2.1.0'
// 通用加载页面布局：https://github.com/wenkency/loading
implementation 'com.github.wenkency:loading:1.5.0'

```

### Application初始化

```
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 配置返回按钮，参考TitleBarConfig，项目：https://github.com/wenkency/titlebar
        BaseConfig.IC_TITLE_BACK = R.mipmap.ic_title_back

        // 配置加载页面，实际用自己UI设置的页面：https://github.com/wenkency/loading
        LoadingManager.BASE_LOADING_LAYOUT_ID = R.layout.loading_pager_loading
        LoadingManager.BASE_RETRY_LAYOUT_ID = R.layout.loading_pager_error
        LoadingManager.BASE_DATA_ERROR_LAYOUT_ID = R.layout.loading_pager_data_error
        LoadingManager.BASE_EMPTY_LAYOUT_ID = R.layout.loading_pager_empty

        // 页面弹窗统一配置
        BaseConfig.dialog = AppDialog()

        // 网络配置，测试用
        RestConfig
            .baseUrl("http://httpbin.org/")
            .debugUrl("http://httpbin.org/")
            .debug(true)
            //.commHeaders(HashMap()) // 添加公共请求头，根据项目自己添加
            //.commParams(HashMap()) // 添加公共请求参数，根据项目自己添加
            .register(this)
    }
}
```

### 使用方式

```
/**
 * 测试页面
 * 结合多个ViewModel的写法
 * 1.一个ViewModel负责UI显示
 * 2.一个ViewModel负责数据请求
 * 3. 还添加网络请求、加载页面、统一Dialog显示
 */
 // NetTestViewModel:显示UI相关
 // NetPresenterViewModel:网络加载相关
class NetMvvmActivity : BindActivity<NetTestViewModel, ActivityNetBinding>() {
    // 数据请求的ViewModel
    private lateinit var presenter: NetPresenterViewModel

    override fun initTitle(titleBar: DefTitleBar) {
        titleBar.setTitle("网络测试")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_net
    }

    override fun onBind(binding: ActivityNetBinding, viewModel: NetTestViewModel) {
        binding.vm = viewModel
        // 点击事件
        binding.click = Click()
        // 初始化网络请求
        presenter = getViewModel(NetPresenterViewModel::class.java)
    }

    override fun initViews() {
        // 网络请求数据结果
        presenter.result.observe(this) {
            viewModel.text.value = it
        }
    }

    // 网络请求业务，可以看作是P层
    override fun initNet() {
        // 网络请求,可以控制页面
        presenter.requestNet(pageState)
    }

    inner class Click {
        fun requestNet() {
            // 网络请求，可以控制Dialog
            presenter.requestNetShowDialog(dialogState)
        }
    }

}
```

```
/**
 * 普通用法，没有DataBinding
 */
class NormalActivity : AppActivity() {
    override fun initTitle(titleBar: DefTitleBar) {
        titleBar.setTitle("普通用法")
    }
    override fun getLayoutId(): Int {
        return R.layout.activity_normal
    }
}

/**
 * MVVM用法
 */
class MVVMActivity : BindActivity<MVVMViewModel, ActivityMvvmBinding>() {
    override fun initTitle(titleBar: DefTitleBar) {
        titleBar.setTitle("MVVM用法")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_mvvm
    }

    override fun bind(binding: ActivityMvvmBinding, viewModel: MVVMViewModel) {
        // 绑定ViewModel
        binding.vm = viewModel
    }
}

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

```

### 注解不能被混淆

```
-keep class * {
@com.base.mvp.inject.InjectPresenter <fields>;
}
```
