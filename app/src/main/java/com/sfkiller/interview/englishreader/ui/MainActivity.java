package com.sfkiller.interview.englishreader.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sfkiller.interview.englishreader.R;
import com.sfkiller.interview.englishreader.adapter.EnglishReaderAdapter;
import com.sfkiller.interview.englishreader.contract.IEnglishReaderContract;
import com.sfkiller.interview.englishreader.helper.ItemMoveHelper;
import com.sfkiller.interview.englishreader.listener.OnItemDragListener;
import com.sfkiller.interview.englishreader.listener.OnItemTouchListener;
import com.sfkiller.interview.englishreader.model.DataSources;
import com.sfkiller.interview.englishreader.presenter.EnglishReaderPresenter;
import com.sfkiller.interview.englishreader.view.DragTextView;
import com.sfkiller.interview.englishreader.view.WordTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面
 * @author SFKiller
 */
public class MainActivity extends Activity implements IEnglishReaderContract.IEnglishReaderView {

    private IEnglishReaderContract.IEnglishReaderPresenter presenter;
    public TextView readerTextView;


    boolean down = false;
    boolean up = false;

    int startWordIndex, endWordIndex;
    private WordTextView mWordView;

    private DragTextView dragTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化Presenter
        new EnglishReaderPresenter(this);
        //预取数据
        presenter.fetchDatas();
        setContentView(R.layout.main_layout);
        //初始化View并绑定数据
//        initViewAndBindData();
        mWordView = (WordTextView) findViewById(R.id.english_sentence);
        Log.e("QIPU", " mWordView.setText :" + DataSources.getInstance().getStringData());
        mWordView.setText(DataSources.getInstance().getStringData());
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout);
        dragTextView = new DragTextView(this);
        dragTextView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        dragTextView.setBackgroundColor(Color.parseColor("#FF0000"));
        dragTextView.setVisibility(View.GONE);
        linearLayout.addView(dragTextView);
        mWordView.setActivity(this);
    }

    public void disPatchEvent(MotionEvent event) {
        dragTextView.dispatchTouchEvent(event);
    }

    public void setSelectedWord(String word) {
        dragTextView.setVisibility(View.VISIBLE);
        dragTextView.setText(word);
    }

    public void disMissMovingTextView() {
//        AlphaAnimation alphaAnimation = new AlphaAnimation(0.8f,0.0f);
//        alphaAnimation.setDuration(1000);
//        dragTextView.setAnimation(alphaAnimation);
//        alphaAnimation.setAnimationListener(new Animation.AnimationListener(){
//
//            public void onAnimationStart(Animation animation){}
//
//            public void onAnimationRepeat(Animation animation){}
//
//            public void onAnimationEnd(Animation animation) {
//                dragTextView.setVisibility(View.GONE);
//            }
//        });
    }

    /**
     * 初始化View并绑定数据
     */
    private void initViewAndBindData() {
        readerTextView = (TextView) findViewById(R.id.reader_text_view);
        //初始化RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置item分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //绑定adapter
        EnglishReaderAdapter adapter = new EnglishReaderAdapter(presenter.getDatas());
        recyclerView.setAdapter(adapter);

        //设置拖动事件的处理
        final ItemTouchHelper helper = new ItemTouchHelper(new ItemMoveHelper(adapter));
        helper.attachToRecyclerView(recyclerView);

        recyclerView.addOnItemTouchListener(new OnItemTouchListener(recyclerView, new OnItemDragListener() {
            @Override
            public void onDrag(RecyclerView.ViewHolder viewHolder) {
                if (null != viewHolder && (viewHolder.getLayoutPosition()!= (presenter.getDatas().size() - 1))) {
                    helper.startDrag(viewHolder);
                }
            }
        }));

    }

    public void getEachWord(TextView textView){
        Spannable spans = (Spannable)textView.getText();
        Integer[] indices = getIndices(
                textView.getText().toString().trim(), ' ');
        Log.e("QIPU", "size : " + indices.length);
        int start = 0;
        int end = 0;
        for (int i = 0; i <= indices.length; i++) {
            ClickableSpan clickSpan = getClickableSpan();
            end = (i < indices.length ? indices[i] : spans.length());
            spans.setSpan(clickSpan, start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = end + 1;
        }
        textView.setHighlightColor(Color.BLUE);
    }

    private ClickableSpan getClickableSpan(){
        return new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                TextView tv = (TextView) widget;
                String s = tv
                        .getText()
                        .subSequence(tv.getSelectionStart(),
                                tv.getSelectionEnd()).toString();
                if (down) {
                    down = false;
//                    startWordIndex = DataSources.wordIndexs.get(posX);
                    Log.e("QIPU", "startWordIndex : " + startWordIndex);
                }
                if (up) {
                    up = false;
//                    endWordIndex =  DataSources.wordIndexs.get(posX);
                    Log.e("QIPU", "endWordIndex : " + endWordIndex);
                    startWordIndex = 0;
                    endWordIndex = 0;
                }
                Log.d("QIPU", "position : " + tv.getSelectionStart()  + "; " + tv.getSelectionEnd());
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(Color.BLACK);
                ds.setUnderlineText(false);
            }
        };
    }

    public static Integer[] getIndices(String s, char c) {
        int pos = s.indexOf(c, 0);
        List<Integer> indices = new ArrayList<Integer>();
        while (pos != -1) {
            indices.add(pos);
            pos = s.indexOf(c, pos + 1);
        }
        return (Integer[]) indices.toArray(new Integer[0]);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    /**
     * 设置Presenter
     * @param p             {@link com.sfkiller.interview.englishreader.contract.IEnglishReaderContract.IEnglishReaderPresenter}
     */
    @Override
    public void setPresenter(IEnglishReaderContract.IEnglishReaderPresenter p) {
        presenter = p;
    }
}
