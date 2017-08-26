package com.sfkiller.interview.englishreader.helper;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.sfkiller.interview.englishreader.listener.OnItemMoveListener;

/**
 * item移动帮助类
 *
 * Created by SFKiller on 2017/8/26.
 */
public class ItemMoveHelper extends ItemTouchHelper.Callback{

    private OnItemMoveListener listener;
    /** item的背景*/
    private Drawable background = null;
    /** item的背景颜色*/
    private int bgColor = -1;

    public ItemMoveHelper(OnItemMoveListener l) {
        this.listener = l;
    }
    /**
     * 关闭默认的长按拖动
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    /**
     * 关闭默认的滑动功能
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        final int swipeFlags = 0;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //获取被拖动ViewHolder的初始position
        int fromPosition = viewHolder.getAdapterPosition();
        //获取被拖动ViewHolder的目标position
        int toPosition = target.getAdapterPosition();
        //移动
        listener.onMove(fromPosition,toPosition);
        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (ItemTouchHelper.ACTION_STATE_IDLE != actionState) {
            //设置拖动时的背景变化
            if (background == null && bgColor == -1) {
                Drawable drawable = viewHolder.itemView.getBackground();
                if (drawable == null) {
                    bgColor = 0;
                } else {
                    background = drawable;
                }
            }
            viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

        viewHolder.itemView.setAlpha(1.0f);
        if (background != null) viewHolder.itemView.setBackgroundDrawable(background);
        if (bgColor != -1) viewHolder.itemView.setBackgroundColor(bgColor);
    }

}
