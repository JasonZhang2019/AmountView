package com.octopus.round_progressbar;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

public class RoundProgressBar extends View {
    private int roundColor;
    private int roundProgressColor;
    private int roundInsideColor;
    private int textProgressColor;
    private int textTitleColor;

    private float progress;

    private float textProgressSize;
    private float textTitleSize;
    private float roundWidth;

    private String titleStr;

    private String progressStr;
    private boolean isShowProgress = true;

    private Paint paint;
    private TextPaint textPaint;

    private float textWidth;

    private AnimatorSet animation;

    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        paint.setAntiAlias(true);
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);

        roundColor = typedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.GRAY);
        roundProgressColor = typedArray.getColor(R.styleable.RoundProgressBar_roundProgressColor, Color.RED);
        roundInsideColor = typedArray.getColor(R.styleable.RoundProgressBar_roundInsideColor, Color.TRANSPARENT);
        textProgressColor = typedArray.getColor(R.styleable.RoundProgressBar_textProgressColor, Color.RED);
        textTitleColor = typedArray.getColor(R.styleable.RoundProgressBar_textTitleColor, Color.RED);

        progress = typedArray.getFloat(R.styleable.RoundProgressBar_progress, 0);
        textProgressSize = typedArray.getDimension(R.styleable.RoundProgressBar_textProgressSize, sp2px(14));
        textTitleSize = typedArray.getDimension(R.styleable.RoundProgressBar_textTitleSize, sp2px(12));
        roundWidth = typedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, dip2px(4));

        isShowProgress = typedArray.getBoolean(R.styleable.RoundProgressBar_isShowProgress, true);

        progressStr = typedArray.getString(R.styleable.RoundProgressBar_progressStr);

        titleStr = typedArray.getString(R.styleable.RoundProgressBar_titleStr);

        typedArray.recycle();
    }

    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
    }

    public void setRoundProgressColor(int roundProgressColor) {
        this.roundProgressColor = roundProgressColor;
    }

    public void setRoundInsideColor(int roundInsideColor) {
        this.roundInsideColor = roundInsideColor;
    }

    public void setTextProgressColor(int textProgressColor) {
        this.textProgressColor = textProgressColor;
    }

    public void setTextTitleColor(int textTitleColor) {
        this.textTitleColor = textTitleColor;
    }

    private void setProgress(float progress) {
        this.progress = progress;
        postInvalidate();
    }

    public void resetProgress(float progress) {
        this.progress = progress;
    }

    public void setTextProgressSize(float textProgressSize) {
        this.textProgressSize = textProgressSize;
    }

    public void setTextTitleSize(float textTitleSize) {
        this.textTitleSize = textTitleSize;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }

    public void setProgressStr(String progressStr) {
        this.progressStr = progressStr;
    }

    public void setShowProgress(boolean isShowProgress) {
        this.isShowProgress = isShowProgress;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int center = getWidth() / 2; // center point
        int radius = (int) (center - roundWidth / 2); //radius
        paint.setColor(roundColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(roundWidth);
        canvas.drawCircle(center, center, radius, paint);

        if (progress > 0) {
            paint.setStrokeCap(Paint.Cap.SQUARE);
            paint.setStrokeWidth(roundWidth);
            paint.setColor(roundProgressColor);
            // center - radius = roundWidth / 2; center + radius = getWidth() - roundWidth / 2
            RectF oval = new RectF(center - radius, center - radius, center
                    + radius, center + radius);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawArc(oval, -90, 360 * progress / 100, false, paint);
        }

        int radiusInside = (int) (center - roundWidth);
        paint.setColor(roundInsideColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(0);
        canvas.drawCircle(center, center, radiusInside, paint);

        if (isShowProgress) {
            int percent = (int) (progress);
            progressStr = percent + "%";
        }
        if (!TextUtils.isEmpty(progressStr)) {
            textPaint.setStrokeWidth(0);
            textPaint.setColor(textProgressColor);
            textPaint.setTextSize(textProgressSize);

            textWidth = textPaint.measureText(progressStr);
            canvas.drawText(progressStr, center - textWidth / 2, TextUtils.isEmpty(titleStr) ? center + textProgressSize / 2 : center, textPaint);
        }

        if (!TextUtils.isEmpty(titleStr)) {
            textPaint.setStrokeWidth(0);
            textPaint.setColor(textTitleColor);
            textPaint.setTextSize(textTitleSize);
            textPaint.setTypeface(Typeface.DEFAULT);
            textWidth = textPaint.measureText(titleStr);
            canvas.drawText(titleStr, center - textWidth / 2, TextUtils.isEmpty(progressStr) ? center + textTitleSize / 2 : center + textTitleSize, textPaint); //画出标题文字
        }
    }

    public void startAnimation() {
        startAnimation(700, null, null);
    }

    public void startAnimation(long animTime, TimeInterpolator mTimeInterpolator, Animator.AnimatorListener mAnimatorListener) {
        if (progress < 5) {
            return;
        }

        //3.0 AnimationSet
//      AnimationSet set = new AnimationSet(true);
//      set.setRepeatCount(AnimationSet.INFINITE);
//      set.setInterpolator(new AccelerateDecelerateInterpolator());
//      set.start();
//      this.setAnimation(set);

        //4.0+
        if (null == animation) {
            animation = new AnimatorSet();
        }

        ObjectAnimator progressAnimation = ObjectAnimator.ofFloat(this, "progress", 0.1f, progress);
        progressAnimation.setDuration(animTime);// 动画执行时间

        progressAnimation.setInterpolator(mTimeInterpolator == null ? new AccelerateInterpolator() : mTimeInterpolator);

        if (null != mAnimatorListener) {
            progressAnimation.addListener(mAnimatorListener);
        }

        animation.playTogether(progressAnimation);
        animation.start();
    }

    public void clearAnimation() {
        super.clearAnimation();
        if (null != animation) {
            animation.cancel();
        }
    }

    private int sp2px(float spValue) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    private int dip2px(float dipValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue,
                Resources.getSystem().getDisplayMetrics());
    }
}
