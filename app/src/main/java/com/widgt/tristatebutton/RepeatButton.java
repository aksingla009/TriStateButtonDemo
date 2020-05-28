package com.widgt.tristatebutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageButton;

public class RepeatButton extends AppCompatImageButton {

    private final int MAX_STATES=3;
    int state;
    Drawable srcRepeatOff;
    Drawable srcRepeatOne;
    Drawable srcRepeatAll;
    int repeatState;
    Context context;


    public RepeatButton(Context context) {
        super(context);
        this.context=context;

    }

    public RepeatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.RepeatButton);

        try {

            repeatState = a
                    .getInteger(R.styleable.RepeatButton_repeat_state, 0);
            srcRepeatOff = a
                    .getDrawable(R.styleable.RepeatButton_src_repeat_off);
            srcRepeatOne = a
                    .getDrawable(R.styleable.RepeatButton_src_repeat_one);
            srcRepeatAll = a
                    .getDrawable(R.styleable.RepeatButton_src_repeat_all);

        } catch (Exception e) {

        } finally {
            a.recycle();
        }

        switch (repeatState) {
            case 0:
                this.setBackground(srcRepeatOff);
                break;
            case 1:
                this.setBackground(srcRepeatOne);
                break;
            case 2:
                this.setBackground(srcRepeatAll);
                break;
            default:
                break;

        }
    }

    @Override
    public boolean performClick() {
        super.performClick();
        nextState();
        setStateBackground();
        return true;

    }

    private void nextState() {
        state++;

        if (state == MAX_STATES) {
            state = 0;
        }
    }

    private void setStateBackground() {

        switch (state) {
            case 0:
                this.setBackground(srcRepeatOff);
                showButtonText("Repeat Off");
                break;
            case 1:
                this.setBackground(srcRepeatOne);
                showButtonText("Repeat One");
                break;
            case 2:
                this.setBackground(srcRepeatAll);
                showButtonText("Repeat All");

                break;
            default:
                break;

        }
    }
    public void showButtonText(String text) {

        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

    }
    public REPEAT getRepeatState() {

        switch (state) {
            case 0:
                return REPEAT.OFF;
            case 1:
                return REPEAT.ONE;
            case 2:
                return REPEAT.ALL;
            default:
                return REPEAT.OFF;

        }
    }

    public void setRepeatState(REPEAT repeatState) {

        switch (repeatState) {
            case OFF:
                state=0;

                break;
            case ONE:
                state=1;
                break;
            case ALL:
                state=2;
                break;
            default:
                break;
        }

        setStateBackground();
    }
}

