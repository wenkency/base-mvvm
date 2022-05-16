

-keepattributes *Annotation*
-keepclassmembers class * {
@com.lven.base.mvp.inject.InjectPresenter <fields>;
}
-keep public class * extends com.lven.base.mvp.impl.BasePresenter