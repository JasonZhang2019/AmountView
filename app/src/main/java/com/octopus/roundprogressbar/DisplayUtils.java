package com.octopus.roundprogressbar;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * dp、sp 转换为 px 的工具类
 */
public class DisplayUtils {
	/**
	 * 将px值转换为dip或dp值，保证尺寸大小不变
	 * 
	 * @param pxValue
	 * @return dip
	 */
	public static int px2dip(float pxValue) {
		final float scale = Resources.getSystem().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将dip或dp值转换为px值，保证尺寸大小不变
	 * 
	 * @param dipValue
	 * @return px
	 */
	public static int dip2px(float dipValue) {
//		final float scale = context.getResources().getDisplayMetrics().density;
//		return (int) (dipValue * scale + 0.5f);
		
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue,
				Resources.getSystem().getDisplayMetrics());
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 * @return sp
	 */
	public static int px2sp(float pxValue) {
		final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param spValue
	 * @return px
	 */
	public static int sp2px(float spValue) {
		final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	public static float getScaledDensity() {
		DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
		return dm.scaledDensity;
	}

	/**
	 * 获取需要的屏幕宽度占比,小于等于1.0
	 * @return px
	 */
	public static int getWidthPixels(Activity activity, double scaleNum) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		// 计算两边的距离
		return (int) (scaleNum * dm.widthPixels);
	}

    /**
     * 获取屏幕高度
     * @param activity
     * @return
     */
	public static int getScreenHeight(Activity activity) {
		return activity.getResources().getDisplayMetrics().heightPixels;
	}

    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
	public static int getScreenWidth(Context context) {
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * 获取系统状态栏高度
	 * @param activity
	 * @return
     */
	public static int getSystemBarHeight(Activity activity) {
		int result = 0;
		int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = activity.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}
}