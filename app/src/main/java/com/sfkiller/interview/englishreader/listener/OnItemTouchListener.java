package com.sfkiller.interview.englishreader.listener;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Item触摸事件监听接口
 *
 * Created by SFKiller on 2017/8/26.
 */
public class OnItemTouchListener implements RecyclerView.OnItemTouchListener {

    /** 手势监听*/
    private GestureDetectorCompat mGestureDetector;
    private RecyclerView recyclerView;
    private OnItemDragListener itemDragListener;


    public OnItemTouchListener(RecyclerView recyclerView, OnItemDragListener onItemDragListener){
        this.recyclerView = recyclerView;
        mGestureDetector = new GestureDetectorCompat(recyclerView.getContext(), new ItemTouchHelperGestureListener());
        itemDragListener = onItemDragListener;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child!=null) {
                RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(child);
                itemDragListener.onDrag(vh);
            }
        }
    }

}
