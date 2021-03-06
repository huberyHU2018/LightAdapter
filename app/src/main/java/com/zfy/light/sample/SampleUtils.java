package com.zfy.light.sample;

import android.view.View;

import com.zfy.adapter.LightAdapter;
import com.zfy.adapter.model.LightView;
import com.zfy.adapter.model.LoadingState;

/**
 * CreateAt : 2018/11/14
 * Describe :
 *
 * @author chendong
 */
public class SampleUtils {

    // 添加 loading
    public static void addLoadingView(LightAdapter adapter) {
        LightView loadingView = LightView.from(R.layout.loading_view);
        adapter.loadingView().setLoadingView(loadingView, (holder, data, extra) -> {
            switch (data.state) {
                case LoadingState.LOADING:
                    holder.setVisible(R.id.pb)
                            .setText(R.id.content_tv, "加载中请稍候～");
                    break;
                case LoadingState.FINISH:
                    holder.setGone(R.id.pb)
                            .setText(R.id.content_tv, "加载完成");
                    break;
            }
        });
    }

    // 添加 header
    public static void addHeader(LightAdapter adapter, String desc, View.OnClickListener listener) {
        LightView lightView = LightView.from(R.layout.desc_header);
        adapter.header().addHeaderView(lightView, (holder) -> {
            holder.setText(R.id.desc_tv, desc)
                    .setCallback(R.id.cover_iv, new GlideCallback(Utils.randomImage()))
                    .setClick(R.id.action_fab, listener);
        });
    }

    // 添加 empty
    public static void addEmpty(LightAdapter adapter, View.OnClickListener listener) {
        adapter.emptyView().setEmptyView(LightView.from(R.layout.empty_view), (holder, data, extra) -> {
            holder.setClick(R.id.refresh_tv, listener);
        });
    }
}
