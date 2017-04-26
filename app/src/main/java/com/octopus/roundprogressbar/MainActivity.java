package com.octopus.roundprogressbar;

import android.animation.Animator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.octopus.round_progressbar.RoundProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rpb_item1)
    RoundProgressBar rpbItem1;
    @BindView(R.id.rpb_item3)
    RoundProgressBar rpbItem3;

    private boolean cancelAnim = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rpbItem1.clearAnimation();
    }

    private void initView() {
        rpbItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast("点击跳过广告，进入主界面");
                rpbItem1.clearAnimation();
                toMainActivity();
            }
        });
        rpbItem1.startAnimation(3000, new LinearInterpolator(), new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (!cancelAnim) {
                    ToastUtils.showToast("广告结束，进入主界面");
                    toMainActivity();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                cancelAnim = true;
                ToastUtils.showToast("动画取消");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        rpbItem3.setTitleStr("item3");
        rpbItem3.setRoundColor(Color.YELLOW);
        rpbItem3.setRoundInsideColor(Color.LTGRAY);
        rpbItem3.setRoundProgressColor(Color.BLUE);
        rpbItem3.setRoundWidth(DisplayUtils.dip2px(4));
        rpbItem3.setTextProgressSize(DisplayUtils.sp2px(16));
        rpbItem3.setTextTitleSize(DisplayUtils.sp2px(12));
        rpbItem3.setTextProgressColor(Color.BLACK);
        rpbItem3.setTextTitleColor(Color.CYAN);
//        rpbItem3.resetProgress(50);
        rpbItem3.startAnimation(2000, null, null);
    }

    private void toMainActivity() {

    }

}
