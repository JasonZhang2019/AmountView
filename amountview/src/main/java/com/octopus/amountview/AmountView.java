package com.octopus.amountview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

/**
 *
 */
public class AmountView extends FrameLayout {
    private Context mContext;

    private View rootView;
    private EditText amountEt;
    private Button reduceBtn;
    private Button increaseBtn;

    private AttributeSet mAttrs;

    //------------------------attrs-----------------------------
    private float textSize; // default 8sp
    private int currentValue; // default use minValue
    private int minValue = 1; // must no less than 1,default is 1
    private int maxValue = 99; // default is 99

    private OnChangeListener mOnChangeListener;
    //------------------------attrs-----------------------------

    public AmountView(Context context) {
        this(context, null);
    }

    public AmountView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AmountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        this.mAttrs = attrs;
        initView();
    }

    private void initView() {
        rootView = LayoutInflater.from(mContext).inflate(R.layout.layout_amount_view, null);

        amountEt = (EditText) rootView.findViewById(R.id.amount_et);
        amountEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // after changed, check minValue and maxValue. change button enable. call listener.
                if (TextUtils.isEmpty(s.toString())) {
                    amountEt.setText(String.valueOf(minValue));
                } else {
                    int amount = Integer.parseInt(amountEt.getText().toString());

                    if (amount < minValue) {
                        amountEt.setText(String.valueOf(minValue));
                    } else if (amount > maxValue) {
                        amountEt.setText(String.valueOf(maxValue));
                    } else {
                        if (amount > minValue) {
                            reduceBtn.setEnabled(true);
                        } else {
                            reduceBtn.setEnabled(false);
                        }

                        if (amount < maxValue) {
                            increaseBtn.setEnabled(true);
                        } else {
                            increaseBtn.setEnabled(false);
                        }

                        if (null != mOnChangeListener) {
                            mOnChangeListener.onChanged(amount);
                        }
                    }
                }
            }
        });

        reduceBtn = (Button) rootView.findViewById(R.id.reduce_btn);
        reduceBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                amountEt.setText(String.valueOf(Integer.parseInt(amountEt.getText().toString()) - 1));
            }
        });
        increaseBtn = (Button) rootView.findViewById(R.id.increase_btn);
        increaseBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                amountEt.setText(String.valueOf(Integer.parseInt(amountEt.getText().toString()) + 1));
            }
        });

        if (mAttrs != null) {
            TypedArray typedArray = mContext.obtainStyledAttributes(mAttrs, R.styleable.AmountView);

//        roundColor = typedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.GRAY);
//
//        progress = typedArray.getFloat(R.styleable.RoundProgressBar_progress, 0);

            textSize = typedArray.getDimension(R.styleable.AmountView_textSize, sp2px(8));
            amountEt.setTextSize(textSize);
            reduceBtn.setTextSize(textSize);
            increaseBtn.setTextSize(textSize);


            minValue = typedArray.getInteger(R.styleable.AmountView_minValue, 1);
            if (minValue < 1) {
                minValue = 1;
            }
            maxValue = typedArray.getInteger(R.styleable.AmountView_maxValue, 99);
            if (maxValue < minValue) {
                maxValue = minValue;
            }
            currentValue = typedArray.getInteger(R.styleable.AmountView_currentValue, minValue);
//
//        isShowProgress = typedArray.getBoolean(R.styleable.RoundProgressBar_isShowProgress, true);
//
//        titleStr = typedArray.getString(R.styleable.RoundProgressBar_titleStr);

            typedArray.recycle();
        }

        amountEt.setText(String.valueOf(currentValue));

        addView(rootView);
    }

    //----------------------------- get/set ----------------------------------------
    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        amountEt.setTextSize(textSize);
        reduceBtn.setTextSize(textSize);
        increaseBtn.setTextSize(textSize);
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        if (minValue > maxValue) {
            minValue = maxValue;
        }
        this.minValue = minValue;
        setCurrentValue(getCurrentValue());
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        if (maxValue < minValue) {
            maxValue = minValue;
        }
        this.maxValue = maxValue;
        setCurrentValue(getCurrentValue());
    }

    public void setOnChangeListener(OnChangeListener mOnChangeListener) {
        this.mOnChangeListener = mOnChangeListener;
    }

    public int getCurrentValue() {
        return Integer.parseInt(amountEt.getText().toString());
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
        amountEt.setText(String.valueOf(currentValue));
    }
    //----------------------------- get/set ----------------------------------------

    private int sp2px(float spValue) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public interface OnChangeListener {
        void onChanged(int value);
    }
}
