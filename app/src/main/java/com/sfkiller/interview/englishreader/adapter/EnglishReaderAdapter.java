package com.sfkiller.interview.englishreader.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sfkiller.interview.englishreader.R;
import com.sfkiller.interview.englishreader.listener.OnItemMoveListener;

import java.util.Collections;
import java.util.List;

/**
 * RecyclerView适配器
 *
 * Created by SFKiller on 2017/8/26.
 */
public class EnglishReaderAdapter extends RecyclerView.Adapter<EnglishReaderAdapter.RecyclerViewHolder> implements OnItemMoveListener {

    /** 要展示的数据*/
    private List<String> datas;

    public EnglishReaderAdapter(List<String> data) {
        this.datas = data;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (null != parent) {
            View itemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, null, false);
            return new RecyclerViewHolder(itemLayout);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        if (null != datas && (position >= 0) && (position < datas.size())) {
            holder.content.setText(datas.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return null != datas ? datas.size() : 0;
    }

    @Override
    public void onMove(int fromPosition, int toPosition) {

        if (fromPosition == datas.size()-1 || toPosition == datas.size()-1){
            return;
        }
        //处理拖动时的数据交换
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(datas, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(datas, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{

        public TextView content;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.item_content);
        }
    }
}
