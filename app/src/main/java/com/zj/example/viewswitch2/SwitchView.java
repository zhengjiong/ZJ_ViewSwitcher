package com.zj.example.viewswitch2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * create by zhengjiong
 * Date: 2015-06-01
 * Time: 08:37
 */
public class SwitchView extends ViewGroup{


    public SwitchView(Context context) {
        this(context, null);
    }

    public SwitchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
