package com.zfy.light.sample.cases;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.march.common.exts.ListX;
import com.zfy.adapter.LightAdapter;
import com.zfy.adapter.LightAdapterBuilder;
import com.zfy.adapter.collections.LightList;
import com.zfy.adapter.common.SpanSize;
import com.zfy.adapter.listener.ModelTypeConfigCallback;
import com.zfy.adapter.type.ModelTypeRegistry;
import com.zfy.component.basic.mvx.mvp.app.MvpActivity;
import com.zfy.component.basic.mvx.mvp.app.MvpV;
import com.zfy.light.sample.GlideCallback;
import com.zfy.light.sample.R;
import com.zfy.light.sample.SampleUtils;
import com.zfy.light.sample.Utils;
import com.zfy.light.sample.entity.MultiTypeEntity;

import java.util.List;

import butterknife.BindView;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;

/**
 * CreateAt : 2018/11/13
 * Describe : 空白页面测试
 *
 * @author chendong
 */
@MvpV(layout = R.layout.topmore_activity)
public class TopMoreTestActivity extends MvpActivity {

    @BindView(R.id.content_rv) RecyclerView mContentRv;

    private LightAdapter<MultiTypeEntity> mAdapter;
    private LightList<MultiTypeEntity>    mEntities;

    @Override
    public void init() {
        mEntities = LightList.diffList();
        // type callback
        ModelTypeConfigCallback callback = modelType -> {
            switch (modelType.type) {
                case MultiTypeEntity.TYPE_DELEGATE:
                    modelType.spanSize = SpanSize.SPAN_SIZE_HALF;
                    modelType.layoutId = R.layout.item_deleate;
                    break;
                case MultiTypeEntity.TYPE_PROJECT:
                    modelType.spanSize = SpanSize.SPAN_SIZE_HALF;
                    modelType.layoutId = R.layout.item_cover;
                    break;
            }
        };
        ModelTypeRegistry registry = ModelTypeRegistry.create()
                .add(MultiTypeEntity.TYPE_DELEGATE, R.layout.item_deleate, SpanSize.SPAN_SIZE_HALF)
                .add(MultiTypeEntity.TYPE_PROJECT, R.layout.item_cover, SpanSize.SPAN_SIZE_HALF);

        // adapter
        mAdapter = new LightAdapterBuilder<>(mEntities, registry).onBindView((holder, pos, data) -> {
            holder.setText(R.id.title_tv, data.title)
                    .setText(R.id.desc_tv, data.desc);
            switch (data.type) {
                case MultiTypeEntity.TYPE_DELEGATE:
                    holder.setText(R.id.subtitle_tv, "子标题");
                    break;
                case MultiTypeEntity.TYPE_PROJECT:
                    holder.setCallback(R.id.cover_iv, new GlideCallback(Utils.randomImage()));
                    break;
            }
        }).build();
        SampleUtils.addLoadingView(mAdapter);
        // loadMore
        mAdapter.loadMore().setLoadMoreListener(adapter -> {
            post(() -> {
                appendData(false);
                mAdapter.loadMore().finishLoadMore();
            }, 2000);
        });
        // topMore
        mAdapter.topMore().setTopMoreListener(adapter -> {
            post(() -> {
                appendData(true);
                mAdapter.topMore().finishTopMore();
            }, 2000);
        });
        // animator
        mAdapter.animator().setItemAnimator(new ScaleInAnimator());
        mContentRv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mContentRv.setAdapter(mAdapter);

        appendData(false);

        mContentRv.scrollToPosition(mAdapter.getDatas().size() / 2);
    }


    private void appendData(boolean top) {
        List<MultiTypeEntity> list = ListX.range(10, index -> {
            MultiTypeEntity entity = new MultiTypeEntity(index % 3 == 0 ? MultiTypeEntity.TYPE_DELEGATE : MultiTypeEntity.TYPE_PROJECT);
            entity.title = "Title " + index;
            entity.desc = "Desc " + index;
            entity.subTitle = "SubTitle " + index;
            return entity;
        });
        if (top) {
            mEntities.updateAddAll(0, list);
        } else {
            mEntities.updateAddAll(list);
        }
    }

}
