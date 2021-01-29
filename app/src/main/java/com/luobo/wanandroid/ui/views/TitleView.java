package com.luobo.wanandroid.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.luobo.wanandroid.R;

public class TitleView extends ConstraintLayout {
    //在用代码动态的添加我们的自定义view时调用
    public TitleView(@NonNull Context context) {
        super(context,null);
    }
    //在使用xml +inflate的方法添加控件时会调用，里面多了一个AttributeSet类型的值
    public TitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        LayoutInflater.from(context).inflate(R.layout.item_aq,this);
    }

    public TitleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
