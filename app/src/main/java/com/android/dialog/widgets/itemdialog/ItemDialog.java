package com.android.dialog.widgets.itemdialog;

import android.os.Bundle;
import android.view.View;

import com.android.dialog.R;
import com.android.dialog.base.BaseViewHolder;
import com.android.dialog.widgets.Dialog;

import java.util.List;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Copyright (C)seengene
 * @Package: open.ui.widgets
 * @ClassName: ItemDialog
 * @Description: 适用于重复性数据展示，例如使用RecyclerView展示
 * @Author: seengene_lvTravler
 * @CreateDate: 2019/8/28 11:34
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/28 11:34
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ItemDialog extends Dialog implements BaseViewHolder.OnItemClickListener<ItemBean> {
    private DialogParams mDialogParams;

    private ItemDialog() {
        mDialogParams = new DialogParams();
    }

    public static Builder with(AppCompatActivity activity) {
        return new Builder(activity);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.dialog_item;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (mDialogParams.backgroundColor != -1 && mDialogParams.backgroundColor != 0) {
            view.setBackgroundColor(getContext().getResources().getColor(mDialogParams.backgroundColor));
        }
        RecyclerView recDialogItemShow = view.findViewById(R.id.rec_dialog_item_show);
        recDialogItemShow.setHasFixedSize(true);
        switch (mDialogParams.showType) {
            case ShowType.LIST:
                recDialogItemShow.setLayoutManager(new LinearLayoutManager(getContext()));
                break;
            case ShowType.GRID:
                recDialogItemShow.setLayoutManager(new GridLayoutManager(getContext(), mDialogParams.spanCount));
                break;
        }
        ItemDialogAdapter adapter = new ItemDialogAdapter(getContext(), R.layout.item_item_dialog, null);
        recDialogItemShow.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        adapter.refreshAdapter(mDialogParams.data);
    }

    @Override
    protected boolean setCancelable() {
        return mDialogParams.isCancelable;
    }

    @Override
    protected int setGravity() {
        return mDialogParams.gravity;
    }

    @Override
    protected int setAnimations() {
        return mDialogParams.animations;
    }

    @Override
    public void onItemClick(BaseViewHolder viewHolder, int position, ItemBean itemData, Object type) {
        if (mDialogParams.onItemClickListener != null) {
            dismiss();
            mDialogParams.onItemClickListener.onItemClick(position, itemData);
        }
    }

    @Override
    public void onItemLongClick(BaseViewHolder viewHolder, int position, ItemBean itemData, Object type) {

    }

    public class DialogParams {
        /**
         * 是否可以点击外部dismiss
         */
        boolean isCancelable = true;
        /**
         * 展示类型
         */
        @ShowType
        int showType = -1;
        /**
         * Grid形式展示时每行显示的数量
         */
        int spanCount = -1;
        /**
         * 展示数据
         */
        List<ItemBean> data;
        /**
         * 监听
         */
        OnItemClickListener onItemClickListener;
        /**
         * 位置
         *
         * @see android.view.Gravity
         */
        int gravity = -1;
        /**
         * 背景颜色
         */
        int backgroundColor = -1;
        /**
         * 动画
         */
        int animations = -1;
    }

    public static class Builder {
        AppCompatActivity activity;
        DialogParams P;
        ItemDialog dialog;

        public Builder(AppCompatActivity activity) {
            dialog = new ItemDialog();
            this.P = dialog.mDialogParams;
            this.activity = activity;
        }

        public Builder setShowType(@ShowType int val) {
            P.showType = val;
            return this;
        }

        public Builder setSpanCount(int val) {
            P.spanCount = val;
            return this;
        }

        public Builder setData(@NonNull List<ItemBean> val) {
            P.data = val;
            return this;
        }

        public Builder setOnItemClickListener(@NonNull OnItemClickListener val) {
            P.onItemClickListener = val;
            return this;
        }

        public Builder setCancelable(boolean val) {
            P.isCancelable = val;
            return this;
        }

        public Builder setGravity(int val) {
            P.gravity = val;
            return this;
        }

        public Builder setBackgroundColor(@ColorRes int val) {
            P.backgroundColor = val;
            return this;
        }

        public Builder setAnimations(@StyleRes int val) {
            P.animations = val;
            return this;
        }

        public ItemDialog show() {
            if (P.data == null) {
                throw new IllegalArgumentException("Please set data");
            }

            if (P.showType == -1) {
                throw new IllegalArgumentException("Please set showType");
            }

            dialog.show(activity);
            return dialog;
        }
    }

    public @interface ShowType {

        int LIST = 0x000000051;

        int GRID = 0x000000061;

    }

    public interface OnItemClickListener {
        void onItemClick(int position, ItemBean itemData);
    }
}
