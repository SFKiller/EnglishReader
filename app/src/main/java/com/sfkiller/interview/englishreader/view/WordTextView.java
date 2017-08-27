package com.sfkiller.interview.englishreader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.sfkiller.interview.englishreader.model.DataSources;
import com.sfkiller.interview.englishreader.model.WordAttr;
import com.sfkiller.interview.englishreader.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * 自定义TextView，用于处理单词
 *
 * @author SFKiller
 */
public class WordTextView extends TextView {

    private final static String TAG = "SFKiller";

    MainActivity activity;

    private SpannableString spannableString;
    private String selectedWord;
    private ForegroundColorSpan mForegroundColorSpan = new ForegroundColorSpan(Color.MAGENTA);

    private List<WordAttr> WordAttrs;

    public WordTextView(Context context) {
        super(context);
        initialize();
        myImageView= new DragTextView(context);
    }

    public void setActivity(MainActivity a) {
        activity = a;
    }

    public WordTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WordTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        setGravity(Gravity.TOP);
        setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onCreateContextMenu(ContextMenu menu) {
    }

    @Override
    public boolean getDefaultEditable() {
        return false;
    }

    private float mLastX = -1f;
    private float mLastY = -1f;

    private int downPos;
    private int upPos;
    DragTextView myImageView;

    private final float MIN_VALID_MOVE = 3f;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = -1f;
                mLastY = -1f;
                downPos = getCurrentIndex(event);
                activity.setSelectedWord(DataSources.getInstance().getWord(downPos));
                break;
            case MotionEvent.ACTION_MOVE:
                selectedWord = null;
                clearSpan();
                if (Math.abs(event.getX() - mLastX) > MIN_VALID_MOVE || Math.abs(event.getY() - mLastY) > MIN_VALID_MOVE) {
                    mLastX = event.getX();
                    mLastY = event.getY();
                }
                break;

            case MotionEvent.ACTION_UP:
                upPos = getCurrentIndex(event);
                Log.e("QIPU", "downPos: " + downPos + ", upPos :" + upPos);
                if (-1 != downPos && -1 != upPos) {
                    DataSources.getInstance().insert(downPos, upPos);
                    setText(DataSources.getInstance().getStringData());
                }
                Log.e("QIPU", "downPos: " + downPos + ", upPos :" + upPos);
                downPos = -1;
                upPos = -1;
                activity.disMissMovingTextView();
                break;
            case MotionEvent.ACTION_CANCEL:
                mLastX = -1f;
                mLastY = -1f;
                clearSelectedWord();
                break;
        }
        activity.disPatchEvent(event);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public int getCurrentIndex(MotionEvent event) {
        Layout layout = getLayout();
        if (layout == null) {
            return -1;
        }
        int line  = layout.getLineForVertical(getScrollY() + (int) event.getY());
        final int index = layout.getOffsetForHorizontal(line, (int) event.getX());
//        Log.e("QIPU", "index : " + index);
        WordAttr selectedWord = getWord(index);
        if (selectedWord != null) {
            return selectedWord.getIndex();
        }
        return -1;
    }

    public void trySelectWord(MotionEvent event) {
        Layout layout = getLayout();
        if (layout == null) {
            return;
        }
        int line  = layout.getLineForVertical(getScrollY() + (int) event.getY());
        final int index = layout.getOffsetForHorizontal(line, (int) event.getX());
        Log.e("QIPU", "index : " + index);
        WordAttr selectedWord = getWord(index);

        if (selectedWord != null) {
            spannableString.setSpan(mForegroundColorSpan,
                    selectedWord.getBegin(), selectedWord.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(spannableString);
            this.selectedWord = getText().subSequence(selectedWord.getBegin(), selectedWord.getEnd()).toString();
        }
    }

    public void clearSelectedWord() {
        clearSpan();
        setText(spannableString);
        showSelectedWord(selectedWord);
    }

    private void showSelectedWord(String selectedWord) {
        if (selectedWord != null) {
            Toast.makeText(getContext(), selectedWord,
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void clearSpan() {
        ForegroundColorSpan[] spans = spannableString.getSpans(0, getText().length(), ForegroundColorSpan.class);
        for (int i = 0; i < spans.length; i++) {
            spannableString.removeSpan(spans[i]);
        }
    }

    private WordAttr getWord(final int index) {
        if (WordAttrs == null) {
            return null;
        }

        for (WordAttr w : WordAttrs) {
            if (w.isIn(index)) {
                return w;
            }
        }

        return null;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        spannableString = SpannableString.valueOf(getText());
        WordAttrs = getWordAttrs();
    }

    public List<WordAttr> getWordAttrs() {

        return DataSources.getInstance().getWordAttrs();
    }

}

