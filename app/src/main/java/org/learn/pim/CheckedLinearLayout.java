package org.learn.pim;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class CheckedLinearLayout extends LinearLayout implements Checkable {
    private boolean mChecked;

    private static final int[] CHECKED_STATE_SET = {android.R.attr.state_checked};
    private static final int[] PRESSED_STATE_SET = {android.R.attr.state_pressed};

    public CheckedLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setChecked(boolean checked) {
        if (mChecked != checked) {
            mChecked = checked;
            refreshDrawableState();
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked())
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        return drawableState;
    }
}
