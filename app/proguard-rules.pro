

-keepattributes *Annotation*
-keepclassmembers class * {
@com.base.mvp.inject.InjectPresenter <fields>;
}
-keep public class * extends com.base.mvp.impl.BasePresenter