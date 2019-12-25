package com.example.kesy.utils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;

import com.example.kesy.R;

/*
 *创建者：LLR
 *日期：2019/12/23
 */
public class CountDownTimerUtils extends CountDownTimer {
     private Button button;
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public CountDownTimerUtils(Button button,long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.button = button;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        button.setClickable(false);
        button.setText(millisUntilFinished / 1000 + "秒");
        button.setBackgroundResource(R.drawable.button_bg_after);
        SpannableString spannableString = new SpannableString(button.getText().toString()); //获取按钮上的文字
        ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将倒计时的时间设置为红色
        button.setText(spannableString);
    }

    @Override
    public void onFinish() {
        button.setText("重新获取验证码");
        button.setClickable(true);
        button.setBackgroundResource(R.drawable.button_bg);
    }
}
