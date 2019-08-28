package com.android.dialog.base;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.IntRange;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * BaseAdapter
 *
 * @param <T>
 */
public abstract class BaseViewHolderAdapter<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {

    public static final int LOAD_PAGE_SIZE = 10;
    protected List<T> mDataList;
    protected Context mContext;
    @LayoutRes
    protected int mItemLayoutRes;
    /**
     * 为了在一个Activity/Fragment有多个Adapter时区分不同类型
     */
    protected Object mType;
    private BaseViewHolder.OnItemClickListener<T> mOnItemClickListener;
    private BaseViewHolder.OnItemViewClickListener<T> mOnItemViewClickListener;
    protected LayoutInflater mLayoutInflater;

    public BaseViewHolderAdapter(Context context, @LayoutRes int itemLayoutRes, @Nullable Object type) {
        mContext = context;
        mItemLayoutRes = itemLayoutRes;
        mType = type;
        mDataList = new ArrayList<>();
        mLayoutInflater = LayoutInflater.from(context);
    }

    /**
     * 设置监听
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(BaseViewHolder.OnItemClickListener<T> onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemViewClickListener(BaseViewHolder.OnItemViewClickListener<T> onItemViewClickListener) {
        mOnItemViewClickListener = onItemViewClickListener;
    }

    /**
     * 适合分页加载
     *
     * @param dataList
     * @param isFirst
     */
    public void refreshAdapter(@NonNull List<T> dataList, boolean isFirst) {
        if (isFirst) {//第一次添加
            mDataList.clear();
            mDataList.addAll(dataList);
            notifyDataSetChanged();
        } else {
            int preSize = mDataList.size();
            mDataList.addAll(dataList);
            notifyItemRangeInserted(preSize, LOAD_PAGE_SIZE);
        }
    }


    /**
     * 适合初始加载
     *
     * @param dataList
     */
    public void refreshAdapter(@NonNull List<T> dataList) {
        if (dataList == null) return;
        mDataList.clear();
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        mDataList.remove(position);
        notifyItemRemoved(position);
    }

    public void clearAdaper() {
        mDataList.clear();
        notifyDataSetChanged();
    }

    protected boolean isNonEmpty(String content) {
        return !TextUtils.isEmpty(content);
    }

    public boolean isNonEmpty() {
        return !mDataList.isEmpty();
    }

    public List<T> getDataList() {
        return mDataList;
    }

    public void refreshAdapter(int position) {
        notifyItemChanged(position);
    }

    public void refreshAdapter() {
        notifyDataSetChanged();
    }

    public T getItemDataByPosition(@IntRange(from = 0) int position) {
        return mDataList.get(position);
    }

    protected Context getContext() {
        return mContext;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return (VH) new BaseViewHolder(getItemLayout(viewGroup));
    }

    protected View getItemLayout(@NonNull ViewGroup viewGroup) {
        return mLayoutInflater.inflate(mItemLayoutRes, viewGroup, false);
    }

    @Override
    public void onBindViewHolder(@NonNull VH t, int i) {
        T itemData = mDataList.get(i);
        setOnItemClickListener(t, itemData, i);
        bind(t, itemData, i);
    }


    protected void setOnItemClickListener(@NonNull final BaseViewHolder viewHolder, final T itemData, final int position) {
        /**
         * 为了避免单独设置监听，只要Activity设置了监听这里就直接转换设置了
         */
        if (mContext instanceof BaseViewHolder.OnItemClickListener) {
            mOnItemClickListener = (BaseViewHolder.OnItemClickListener<T>) mContext;
        }
        if (mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(viewHolder, position, itemData, mType);
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mOnItemClickListener.onItemLongClick(viewHolder, position, itemData, mType);
                    return true;
                }
            });
        }
    }

    protected void setOnItemViewClickListener(@NonNull final BaseViewHolder viewHolder, View view, final T itemData, final int position) {
        if (mContext instanceof BaseViewHolder.OnItemViewClickListener) {
            mOnItemViewClickListener = (BaseViewHolder.OnItemViewClickListener<T>) mContext;
        }
        if (mOnItemViewClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemViewClickListener.onItemViewClick(viewHolder, view, position, itemData, mType);
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mOnItemViewClickListener.onItemViewLongClick(viewHolder, view, position, itemData, mType);
                    return true;
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    /**
     * 子类实现并在里填充数据
     *
     * @param viewHolder
     * @param itemData
     * @param position
     */
    public abstract void bind(@NonNull VH viewHolder, T itemData, int position);
}
