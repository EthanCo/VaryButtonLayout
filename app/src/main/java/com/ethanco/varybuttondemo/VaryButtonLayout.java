package com.ethanco.varybuttondemo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * 具有多种状态的Button
 * <p/>
 * Created by YOLANDA on 2015-11-09.
 */
public class VaryButtonLayout extends RelativeLayout implements View.OnClickListener {

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
        setCurrViewVisible();
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
        addView(v);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (getChildCount() == 1 && getChildAt(0) instanceof ViewGroup) {
            isOneChildView = true;
            ViewGroup childViewGroup = (ViewGroup) getChildAt(0);
            initChildView(childViewGroup, widthMeasureSpec, heightMeasureSpec);
        } else {
            initChildView(this, widthMeasureSpec, heightMeasureSpec);
        }
    }

    private void initChildView(ViewGroup rootViewGroup, int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = rootViewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = rootViewGroup.getChildAt(i);
            fillParent(childView, widthMeasureSpec, heightMeasureSpec);
            childView.setOnClickListener(this);
            childView.setDuplicateParentStateEnabled(true);
            if (i != currIndex) {
                childView.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 将子布局大小填充满父布局
     *
     * @param childView
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    private void fillParent(View childView, int widthMeasureSpec, int heightMeasureSpec) {
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int specWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int specHeight = MeasureSpec.getSize(heightMeasureSpec);
        int specHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        ViewGroup.LayoutParams layoutParams = childView.getLayoutParams();

        float weight = 0;
        int parentOrientation = -1;
        //如果父布局是LinearLayout，进行weight的处理
        if (getParent() != null && getParent() instanceof LinearLayout) {
            LinearLayout parentLayout = (LinearLayout) getParent();
            parentOrientation = parentLayout.getOrientation();
            LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) this.getLayoutParams();
            weight = ll.weight;
        }

        Log.i("VaryButton", "2015-11-09-fillParent: weight:" + weight);
        if (specWidthMode != MeasureSpec.AT_MOST) {
            if (weight == 0 && parentOrientation != LinearLayout.HORIZONTAL) {
                layoutParams.width = specWidth;
            }
        }
        if (specHeightMode != MeasureSpec.AT_MOST) {
            if (weight == 0 && parentOrientation != LinearLayout.VERTICAL) {
                layoutParams.height = specHeight;
            }
        }
        childView.setLayoutParams(layoutParams);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public void onClick(View v) {
        Log.i("VaryButton", "2015-11-09-onClick: onCLick");
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
            childViewGroup.getChildAt(circulateIndex(childViewGroup.getChildCount())).setVisibility(View.VISIBLE);
        } else {
            getChildAt(circulateIndex(getChildCount())).setVisibility(View.VISIBLE);
        }
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
