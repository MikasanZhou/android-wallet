 /*
 * Copyright (c) 2019-2020 The X-Cash Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xcash.adapters.recyclerviewadapter;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xcash.base.BaseActivity;
import com.xcash.base.recyclerviewlibrary.adapters.UnLoadMoreRecyclerViewAdapter;
import com.xcash.base.recyclerviewlibrary.models.ViewItem;
import com.xcash.wallet.R;

import java.util.List;

public class LanguageSettingActivity_RecyclerViewAdapter extends UnLoadMoreRecyclerViewAdapter {

    private BaseActivity baseActivity;
    private RecyclerView recyclerView;
    private int selectPosition = -1;
    private OnLanguageSettingListener onLanguageSettingListener;

    public LanguageSettingActivity_RecyclerViewAdapter(BaseActivity baseActivity, RecyclerView recyclerView, List<ViewItem> datas) {
        super(datas, UnLoadMoreRecyclerViewAdapter.UNSHOW_LOADCOMPLETEDLAYOUTID);
        init(baseActivity, recyclerView);
    }

    private void init(BaseActivity baseActivity, RecyclerView recyclerView) {
        this.baseActivity = baseActivity;
        this.recyclerView = recyclerView;
    }

    @Override
    public int getNormalLayoutId(int viewType) {
        if (viewType == ViewItem.VIEW_TYPE_NORMAL_ITEM_TYPE1) {
            return R.layout.activity_language_setting_item;
        } else {
            return R.layout.base_recyclerview_lostviewtype_item;
        }
    }

    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    @Override
    public void onBindNormalViewHolder(final BaseRecyclerViewHolder holder, final int position) {
        ViewItem viewItem = getDatas().get(position);
        if (viewItem == null) {
            return;
        }
        if (viewItem.getViewType() == ViewItem.VIEW_TYPE_NORMAL_ITEM_TYPE1) {
            final String language = (String) viewItem.getModel();
            if (language != null) {
                RadioButton radioButton = (RadioButton) holder.getView(R.id.radioButton);
                TextView textViewName = (TextView) holder.getView(R.id.textViewName);
                if (selectPosition == position) {
                    radioButton.setChecked(true);
                } else {
                    radioButton.setChecked(false);
                }
                textViewName.setText(language);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position == selectPosition) {
                            return;
                        }
                        BaseRecyclerViewHolder selectBaseRecyclerViewHolder = (BaseRecyclerViewHolder) recyclerView.findViewHolderForAdapterPosition(selectPosition);
                        BaseRecyclerViewHolder baseRecyclerViewHolder = (BaseRecyclerViewHolder) recyclerView.findViewHolderForAdapterPosition(position);
                        if (selectBaseRecyclerViewHolder != null) {
                            RadioButton radioButton = (RadioButton) selectBaseRecyclerViewHolder.getView(R.id.radioButton);
                            radioButton.setChecked(false);
                        }
                        if (baseRecyclerViewHolder != null) {
                            RadioButton radioButton = (RadioButton) baseRecyclerViewHolder.getView(R.id.radioButton);
                            radioButton.setChecked(true);
                        }
                        selectPosition = position;
                        if (onLanguageSettingListener != null) {
                            onLanguageSettingListener.onItemSelect(language);
                        }
                    }
                });
            }
        }
    }

    public OnLanguageSettingListener getOnLanguageSettingListener() {
        return onLanguageSettingListener;
    }

    public void setOnLanguageSettingListener(OnLanguageSettingListener onLanguageSettingListener) {
        this.onLanguageSettingListener = onLanguageSettingListener;
    }

    public interface OnLanguageSettingListener {
        void onItemSelect(String language);
    }

}
