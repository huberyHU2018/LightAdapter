package com.zfy.adapter.function;

/**
 * CreateAt : 2018/11/8
 * Describe :
 *
 * @author chendong
 */
public interface LightConsumer<T> {

    void accept(T data);
}
