package com.android.dialog.widgets.itemdialog;

import android.content.Context;

import com.android.dialog.R;
import com.android.dialog.base.BaseAdapter;
import com.android.dialog.base.BaseViewHolder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Copyright (C)seengene
 * @Package: open.ui.widgets.itemdialog
 * @ClassName: ItemDialogAdapter
 * @Description: $DESC$
 * @Author: seengene_lvTravler
 * @CreateDate: 2019/8/28 11:51
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/28 11:51
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ItemDialogAdapter extends BaseAdapter<ItemBean> {
    public ItemDialogAdapter(Context context, int itemLayoutRes, @Nullable Object type) {
        super(context, itemLayoutRes, type);
    }

    @Override
    public void bind(@NonNull BaseViewHolder viewHolder, ItemBean itemData, int position) {
        viewHolder.setImageResource(R.id.img_item_item_dialog_icon,itemData.getIcon());
        viewHolder.setText(R.id.tv_item_item_dialog_name, itemData.getName());
    }
}
