package com.luobo.wanandroid.ui.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.luobo.wanandroid.R;

public class OutLineTextView extends LinearLayout {
    TextView textView;

    public OutLineTextView(@NonNull Context context) {
        super(context, null);
        inflate(context, R.layout.out_line_textview_layout, this);
        textView = this.findViewById(R.id.historyWord);
    }

    public OutLineTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public OutLineTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setText(String str) {
        textView.setText(str);
    }

    public void setOnclickListener(View.OnClickListener listener) {
        this.setOnclickListener(listener);
    }
}
