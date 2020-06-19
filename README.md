# base-mvvm
MVVM通用开发架构，适用于MVVM架构，快速开发Android项目

核心类：BaseActivity AppActivity BindActivity BaseFragment AppFragment BindFragment


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
    buildFeatures{
        dataBinding = true
    }
}

implementation 'com.github.wenkency:base-mvvm:1.0.0'
// lifecycle扩展库
implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
// 通用标题栏
implementation 'com.github.wenkency:titlebar:1.7.0'
// 通用加载页面布局
implementation 'com.github.wenkency:loading:1.1.0'

```
### Application初始化
```
class BaseApplication : Application() {
    @Override
    public void onCreate() {
        super.onCreate();
        // 配置加载页面，实际用自己开发的页面
        // LoadingManager.BASE_LOADING_LAYOUT_ID = (R.layout.pager_loading)
        // LoadingManager.BASE_RETRY_LAYOUT_ID = (R.layout.pager_retry)
        // LoadingManager.BASE_DATA_ERROR_LAYOUT_ID = (R.layout.pager_data_error)
        // LoadingManager.BASE_EMPTY_LAYOUT_ID = (R.layout.pager_empty)
    }
}
```

### 使用方式
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
class MVVMActivity : BindActivity<ActivityMvvmBinding>() {
    override fun initTitle(titleBar: DefTitleBar) {
        titleBar.setTitle("MVVM用法")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_mvvm
    }

    override fun initViews() {
        // 绑定ViewModel
        mBinding.vm = MVVMViewModel()
    }
}

```