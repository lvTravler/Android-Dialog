package com.android.dialog.base;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.dialog.utils.Utils;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.IntRange;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

/**
 * BaseViewHolder
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViewSparseArray;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public interface OnItemClickListener<T> {
        void onItemClick(BaseViewHolder viewHolder, int position, T itemData, Object type);

        void onItemLongClick(BaseViewHolder viewHolder, int position, T itemData, Object type);
    }

    public interface OnItemViewClickListener<T> {
        void onItemViewClick(BaseViewHolder viewHolder, View view, int position, T itemData, Object type);

        void onItemViewLongClick(BaseViewHolder viewHolder, View view, int position, T itemData, Object type);
    }

    /**
     * 获取Item里的控件
     *
     * @param viewId
     * @param <V>
     * @return
     */
    public <V extends View> V getView(@IdRes int viewId) {
        if (mViewSparseArray == null) {
            mViewSparseArray = new SparseArray<>();
        }
        View view = mViewSparseArray.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViewSparseArray.put(viewId, view);
        }
        return (V) view;
    }

    /****************************************各种设置方法******************************************************/
    public void setText(@IdRes int viewId, @StringRes int text) {
        this.<TextView>getView(viewId).setText(text);
    }

    public void setText(@IdRes int viewId, String text) {
        this.<TextView>getView(viewId).setText(text);
    }

    public void setTextType(@IdRes int viewId, Typeface typeface) {
        this.<TextView>getView(viewId).setTypeface(typeface);
    }

    public void setTextSize(@IdRes int viewId, @DimenRes int dimenRes) {
        this.<TextView>getView(viewId).setTextSize(Utils.getDimen(dimenRes));
    }

    public void setTextColor(@IdRes int viewId, @ColorRes int colorRes) {
        this.<TextView>getView(viewId).setTextColor(Utils.getColor(colorRes));
    }

    public void setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
        this.<ImageView>getView(viewId).setImageBitmap(bitmap);
    }

    public void setImageResource(@IdRes int viewId, @DrawableRes int resId) {
        this.<ImageView>getView(viewId).setImageResource(resId);
    }

    public void setBackgroundColor(@IdRes int viewId, @ColorRes int colorRes) {
        this.getView(viewId).setBackgroundColor(Utils.getColor(colorRes));
    }

    public void setBackground(@IdRes int viewId, @DrawableRes int resId) {
        this.getView(viewId).setBackground(Utils.getDrawable(resId));
    }

    public void setBackgroundAlpha(@IdRes int viewId, @IntRange(from = 0, to = 255) int alpha) {
        this.getView(viewId).getBackground().mutate().setAlpha(alpha);
    }

    public void setCompoundDrawablesRelativeWithIntrinsicBounds(@IdRes int viewId, @DrawableRes int start,
                                                                @DrawableRes int top, @DrawableRes int end, @DrawableRes int bottom) {
        this.<TextView>getView(viewId).setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
    }

    public void setVisibility(@IdRes int viewId, boolean isShow) {
        if (isShow) {
            this.getView(viewId).setVisibility(View.VISIBLE);
        } else {
            this.getView(viewId).setVisibility(View.GONE);
        }
    }
}
