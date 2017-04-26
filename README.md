### 一、效果
###### V 0.0.2
> 修复默认宽度为4px，不适4dp的bug。
###### V 0.0.1
> 1、可设置外环底色、外环进度条颜色、内环颜色、百分比文字颜色、标题文字颜色、进度、进度文字大小、标题文字大小、进度条宽度、标题字符串、是否显示百分比文字、不显示百分比文字时替换显示的文字。<br>
> 2、可动画展示进度条显示到指定进度。可取消动画。

![这里写图片描述](http://img.blog.csdn.net/20170412155525662?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveGlhb3l1Xzkz/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
### 二、使用

#### 1. Android Studio用户，在gradle中添加配置。

```
compile 'com.octopus:round-progressbar:0.0.2'
```

#### 2. xml中使用。共12个属性，除xml中配置外还可以使用java代码设置。
```
<com.octopus.round_progressbar.RoundProgressBar
	    android:layout_width="100dp"
	    android:layout_height="100dp"
	    android:layout_margin="12dp"
	    app:isShowProgress="true"
	    app:progress="47"
	    app:progressStr="progressStr"
	    app:roundColor="#e0e0e0"
	    app:roundInsideColor="#ffffff"
	    app:roundProgressColor="#00ff00"
	    app:roundWidth="4dp"
	    app:textProgressColor="#ff0000"
	    app:textProgressSize="16sp"
	    app:textTitleColor="#0000ff"
	    app:textTitleSize="14sp"
	    app:titleStr="item1" />
```
#### 3. java代码。
进度条动画执行显示。三个参数：动画时长、动画速率、动画监听。可使用无参方法，则默认时长700ms，加速速率，不监听动画执行。
```
public void startAnimation(long animTime, TimeInterpolator mTimeInterpolator, Animator.AnimatorListener mAnimatorListener)
```
调用如下方法可停止动画。
```
public void clearAnimation()
```
#### 4. 属性介绍
|参数名|参数含义| xml设置|java设置|
|:-------------:|:-------------:|:-------------:|:-------------:|
| roundColor | 外环底色颜色,默认Color.GRAY | app:roundColor="#e0e0e0" | setRoundColor(int roundColor) |
| roundProgressColor | 外环进度条颜色,默认Color.RED | app:roundProgressColor="#00ff00" | setRoundProgressColor(int roundProgressColor) |
| roundInsideColor| 内环颜色,默认Color.TRANSPARENT | app:roundInsideColor="#ffffff" | setRoundInsideColor(int roundInsideColor) |
| textProgressColor| 百分比文字颜色,默认Color.RED| app:textProgressColor="#ff0000" | setTextProgressColor(int textProgressColor) |
| textTitleColor| 标题文字颜色,默认Color.RED | app:textTitleColor="#0000ff" | setTextTitleColor(int textTitleColor) |
| progress| 进度，0-100，默认0。注意java代码。 | app:progress="47" | resetProgress(float progress) |
| textProgressSize| 百分比文字字体大小,默认14sp | app:textProgressSize="16sp" | setTextProgressSize(float textProgressSize) |
| textTitleSize | 标题文字字体大小,默认12sp | app:textTitleSize="14sp" | setTextTitleSize(float textTitleSize) |
| roundWidth| 圆环宽度,默认4dp | app:roundWidth="4dp" | setRoundWidth(float roundWidth) |
| titleStr| 显示的标题信息,默认为空 | app:titleStr="item1" | setTitleStr(String titleStr) |
| isShowProgress| 是否显示占比。若是则显示progress%，否则显示progressStr,默认为true | app:isShowProgress="true" | setShowProgress(boolean isShowProgress) |
| progressStr| isShowProgress=true的时候显示progress%。否则显示该信息。默认为空 | app:progressStr="progressStr" | setProgressStr(String progressStr) |
#### 5. 注意事项
- 所有属性均可以选择设置或不设置，选择在xml中固定设置还是在java代码中动态设置。
- 需要设置该控件的宽高一致！
- onDestroy方法中要执行控件的clearAnimation()方法！
- 若使用该控件用于app开屏广告页的跳过广告倒计时，请参照Demo源码，调用启动动画方法，监听动画，动画正常结束时跳转。若人为点击直接跳转，则要记得清除动画，且在监听到是取消的时候不再执行动画结束方法时的跳转，防止多次跳转。

