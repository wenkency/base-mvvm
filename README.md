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
// MVVM基本库
implementation 'com.github.wenkency:base-mvvm:1.7.0'

// lifecycle扩展库
def lifecycle_version = "2.3.0-alpha05"
// ViewModel
implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
// LiveData
implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
// Lifecycles only (without ViewModel or LiveData)
implementation "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"

// 通用标题栏
// https://github.com/wenkency/titlebar
implementation 'com.github.wenkency:titlebar:1.8.0'
// 通用加载页面布局
// https://github.com/wenkency/loading
implementation 'com.github.wenkency:loading:1.3.0'

```
### Application初始化
```
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 配置返回按钮，其它标题配置，可以参考TitleBarConfig注释
        // 详细项目：https://github.com/wenkency/titlebar
        TitleBarConfig.IC_TITLE_BACK = R.mipmap.ic_title_back

        // 配置加载页面，实际用自己UI设置的页面
        // 详细项目：https://github.com/wenkency/loading
        LoadingManager.BASE_LOADING_LAYOUT_ID = R.layout.loading_pager_empty
        LoadingManager.BASE_RETRY_LAYOUT_ID = R.layout.loading_pager_empty
        LoadingManager.BASE_DATA_ERROR_LAYOUT_ID = R.layout.loading_pager_empty
        LoadingManager.BASE_EMPTY_LAYOUT_ID = R.layout.loading_pager_empty
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

```