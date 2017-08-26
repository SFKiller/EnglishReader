package com.sfkiller.interview.englishreader.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.sfkiller.interview.englishreader.R;
import com.sfkiller.interview.englishreader.adapter.EnglishReaderAdapter;
import com.sfkiller.interview.englishreader.contract.IEnglishReaderContract;
import com.sfkiller.interview.englishreader.helper.ItemMoveHelper;
import com.sfkiller.interview.englishreader.listener.OnItemDragListener;
import com.sfkiller.interview.englishreader.listener.OnItemTouchListener;
import com.sfkiller.interview.englishreader.presenter.EnglishReaderPresenter;

/**
 * 主界面
 * @author SFKiller
 */
public class MainActivity extends Activity implements IEnglishReaderContract.IEnglishReaderView {

    private IEnglishReaderContract.IEnglishReaderPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化Presenter
        new EnglishReaderPresenter(this);
        //预取数据
        presenter.fetchDatas();
        setContentView(R.layout.activity_main);
        //初始化View并绑定数据
        initViewAndBindData();
    }

    /**
     * 初始化View并绑定数据
     */
    private void initViewAndBindData() {
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

    /**
     * 设置Presenter
     * @param p             {@link com.sfkiller.interview.englishreader.contract.IEnglishReaderContract.IEnglishReaderPresenter}
     */
    @Override
    public void setPresenter(IEnglishReaderContract.IEnglishReaderPresenter p) {
        presenter = p;
    }
}
