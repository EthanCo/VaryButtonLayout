package com.ethanco.varybuttonlayout;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * 具有多种状态的Button
 * <p/>
 * Created by YOLANDA on 2015-11-09.
 */
public class VaryButtonLayout extends RelativeLayout implements View.OnClickListener {
    private static final String TAG = "VaryButton";

    /**
     * 现在的状态
     */
    int currIndex = 0;
    /**
     * 是否只有一个子View(如果只有一个子View，会从该子View中再去寻找子View)
     */
    private boolean isOneChildView;

    public VaryButtonLayout(Context context) {
        super(context);
        init();
    }

    public VaryButtonLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VaryButtonLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnClickListener(this);
    }

    /**
     * 设置现在的状态
     *
     * @param currIndex
     */
    public void setCurrSatus(int currIndex) {
        if (currIndex < 0 || currIndex >= getChildCount()) {
            throw new IndexOutOfBoundsException("length=" + getChildCount() + "; index=" + currIndex);
        }
        this.currIndex = currIndex;
        setCurrViewVisible(currIndex);
    }

    public int getCurrSatus() {
        return currIndex;
    }

    /**
     * 添加一种状态
     *
     * @param res
     */
    public void addStatusView(@LayoutRes int res) {
        View v = LayoutInflater.from(getContext()).inflate(res, this, false);
        addStatusView(v);
    }

    /**
     * 添加一种状态
     *
     * @param v
     */
    public void addStatusView(View v) {
        RelativeLayout.LayoutParams lp = (LayoutParams) v.getLayoutParams();
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        v.setLayoutParams(lp);
        addView(v);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (getChildCount() == 1 && getChildAt(0) instanceof ViewGroup) {
            isOneChildView = true;
            ViewGroup childViewGroup = (ViewGroup) getChildAt(0);
            initChildView(childViewGroup);
        } else {
            initChildView(this);
        }
    }

    private void initChildView(ViewGroup rootViewGroup) {
        int childCount = rootViewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = rootViewGroup.getChildAt(i);
            //fillParent(childView, widthMeasureSpec, heightMeasureSpec);
            //当前控件跟随父控件的(点击、焦点等)状态
            childView.setDuplicateParentStateEnabled(true);
            if (i != currIndex) {
                childView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick currIndex:" + currIndex);
        int originalIndex = currIndex;
        setCurrViewVisible();

        if (mVaryClickListener != null) {
            mVaryClickListener.onClick(this, originalIndex, currIndex);
        }
    }

    /**
     * 使现在的状态可见
     */
    private void setCurrViewVisible() {
        if (isOneChildView) {
            ViewGroup childViewGroup = (ViewGroup) getChildAt(0);
            setCurrViewVisible(childViewGroup, circulateIndex(childViewGroup.getChildCount()));
        } else {
            setCurrViewVisible(this, circulateIndex(getChildCount()));
        }
    }

    /**
     * 使现在的状态可见 (指定Index)
     *
     * @param index
     */
    private void setCurrViewVisible(int index) {
        if (isOneChildView) {
            ViewGroup childViewGroup = (ViewGroup) getChildAt(0);
            setCurrViewVisible(childViewGroup, index);
        } else {
            setCurrViewVisible(this, index);
        }
    }

    /**
     * 使现在的状态可见 (指定ViewGroup和Index)
     *
     * @param viewGroup
     * @param index
     */
    private void setCurrViewVisible(ViewGroup viewGroup, int index) {
        viewGroup.getChildAt(index).setVisibility(View.VISIBLE);
    }

    /**
     * 获得下一个Index
     *
     * @return
     */
    private int circulateIndex(int maxIndex) {
        currIndex++;
        if (currIndex >= maxIndex) {
            currIndex = 0;
        }
        return currIndex;
    }

    public interface OnVaryClickListener {
        void onClick(View v, int currIndex, int nextIndex);
    }

    public void setOnVaryClickListener(OnVaryClickListener varyClickListener) {
        this.mVaryClickListener = varyClickListener;
    }

    public OnVaryClickListener mVaryClickListener;
}
