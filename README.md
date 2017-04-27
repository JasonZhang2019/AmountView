### 一、效果
###### V 0.0.2
> 1、增加方法，可使用xml配置设置初始化显示值或调用set方法直接改变数值。<br>
> 2、增加方法，提供get方法直接获取该控件的当前数值。<br>
> 3、最小值设置值为小于1的时候，使用1作为最小值。最小值设置为大于最大值时，强制最小值等于最大值的值。最大值设置为小于最小值时，强制最大值等于最小值的值。
###### V 0.0.1
> 1、可设置文字大小，目前‘-’、‘+’、输入框文字只能使用同一个大小字体，默认为8sp。<br>
> 2、可设置最大值与最小值，默认最小为1最大为99，初始化显示为最小值。<br>
> 3、当输入数值大于最大值时自动变成最大值，小于最小值时自动变成最小值。当前已经是最小值时，减号按钮不可点击，当前已经是最大值时，加号按钮不可点击。<br>
> 4、可设置监听数值变化，回调方法中返回当前竖数值。<br>
> 5、宽高自定义，同其他View。高度占满，内部宽度左侧减小按钮：输入框：右侧增加按钮=1：1.5：1，暂不支持自定义。

![这里写图片描述](http://img.blog.csdn.net/20170427163818132?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveGlhb3l1Xzkz/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
### 二、使用

#### 1. Android Studio用户，在gradle中添加配置。

```
compile 'com.octopus:amountview:0.0.2'
```

#### 2. xml中使用。共4个属性，除xml中配置外还可以使用java代码设置。
```
<com.octopus.amountview.AmountView
        android:layout_width="200dp"
        android:layout_height="50dp"
        app:currentValue="5"
        app:maxValue="99"
        app:minValue="3"
        app:textSize="10sp" />
```
#### 3. java代码。
设置监听控件数值变化，做自己额外的处理。
```
public void setOnChangeListener(OnChangeListener)
```
示例代码：

```
amountview1.setOnChangeListener(new AmountView.OnChangeListener() {
    @Override
    public void onChanged(int value) {
        ToastUtils.showToast("--->" + value);
    }
});
```

#### 4. 属性介绍
|参数名|参数含义| xml设置|java设置|
|:-------------:|:-------------:|:-------------:|:-------------:|
| textSize | 字体大小,默认8sp | app:textSize="8sp" | public void setTextSize(float) |
| currentValue| 设置要显示的数值,默认使用最小值 | app:currentValue="5" | public void setCurrentValue(int) |
| minValue| 最小值,默认1，设置的值不能小于1 | app:minValue="1" | public void setMinValue(int) |
| maxValue| 最大值,默认99，不能小于最小值| app:maxValue="99" | public void setMaxValue(int) |

#### 5. 注意事项
- 目前只支持修改部分属性，且两个按钮与输入框的宽度占比为1：1.5。若需根据实际情况调整，可下载源码修改使用或给我提Issues。

