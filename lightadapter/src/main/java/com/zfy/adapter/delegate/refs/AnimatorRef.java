package com.zfy.adapter.delegate.refs;

import android.support.v7.widget.RecyclerView;
import android.view.animation.Interpolator;

import com.zfy.adapter.animations.LightAnimator;

/**
 * CreateAt : 2018/11/12
 * Describe :
 *
 * @author chendong
 */
public interface AnimatorRef {

    /**
     * 设置绑定的时候的 Animator，他会在 onBindViewHolder 的时候绑定动画
     *
     * @param animator animator
     * @see LightAnimator
     * @see com.zfy.adapter.animations.AlphaAnimator
     * @see com.zfy.adapter.animations.ScaleAnimator
     * @see com.zfy.adapter.animations.SlideAnimator
     */
    void setBindAnimator(LightAnimator animator);

    /**
     * 设置绑定的时候的 Animator，他会在 onBindViewHolder 的时候绑定动画
     *
     * @param animator     animator
     * @param duration     动画时长
     * @param interpolator 差值器
     * @see LightAnimator
     * @see com.zfy.adapter.animations.AlphaAnimator
     * @see com.zfy.adapter.animations.ScaleAnimator
     * @see com.zfy.adapter.animations.SlideAnimator
     */
    void setBindAnimator(LightAnimator animator, int duration, Interpolator interpolator);

    /**
     * bind 动画只执行一次
     *
     * @param animOnlyOnce 是否只绑定动画一次
     *                     true 表示只有第一次绑定的时候会生效，false 表示每次从页面划出都会重新绑定动画
     */
    void setBindAnimatorOnlyOnce(boolean animOnlyOnce);

    /**
     * 设置 ItemAnimator，他会在局部更新时调用这个动画
     *
     * @param animator animator
     */
    void setItemAnimator(RecyclerView.ItemAnimator animator);

}