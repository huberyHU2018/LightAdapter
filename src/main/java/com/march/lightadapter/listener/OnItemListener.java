package com.march.lightadapter.listener;

import com.march.lightadapter.core.ViewHolder;

/**
 * CreateAt : 2016/11/9
 * Describe : 点击事件
 *
 * @author chendong
 */
public interface OnItemListener<D> {
    // 单击事件
    void onClick(int pos, ViewHolder holder, D data);

    // 长按事件
    void onLongPress(int pos, ViewHolder holder, D data);

    // 双击事件
    void onDoubleClick(int pos, ViewHolder holder, D data);

    boolean isSupportDoubleClick();
}
