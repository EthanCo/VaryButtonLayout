package com.ethanco.varybuttonlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


/**
 * 具有多种状态的Button 主动进行切换
 * <p/>
 * Created by YOLANDA on 2015-11-09.
 */
public class VaryButtonLayout extends VaryButtonLayoutPuppet implements View.OnClickListener {
    private static final String TAG = "VaryButton";

    public VaryButtonLayout(Context context) {
        super(context);
    }

    public VaryButtonLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VaryButtonLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        super.init();
        setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, "onClick currIndex:" + currIndex);
        int originalIndex = currIndex;
        setCurrViewVisible();
        onClick(originalIndex);
    }

    private void onClick(int originalIndex) {
        for (OnVaryClickListener onVaryClickListener : mVaryClickListenerList) {
            onVaryClickListener.onClick(this, originalIndex, currIndex);
        }
    }

    public interface OnVaryClickListener {
        void onClick(View v, int originalIndex, int currIndex);
    }

    public void addOnVaryClickListener(OnVaryClickListener varyClickListener) {
        if (!mVaryClickListenerList.contains(varyClickListener)) {
            mVaryClickListenerList.add(varyClickListener);
        }
    }

    public List<OnVaryClickListener> mVaryClickListenerList = new ArrayList<>();
}
