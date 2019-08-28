package com.android.dialog.base;

import android.content.Context;

import androidx.annotation.Nullable;

/**
 * BaseAdapter
 *
 * @param <T>
 */
public abstract class BaseAdapter<T> extends BaseViewHolderAdapter<T, BaseViewHolder> {

    public BaseAdapter(Context context, int itemLayoutRes, @Nullable Object type) {
        super(context, itemLayoutRes, type);
    }
}
