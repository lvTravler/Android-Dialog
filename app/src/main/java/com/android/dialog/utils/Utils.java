package com.android.dialog.utils;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import com.android.dialog.App;
import com.android.dialog.R;
import com.android.dialog.widgets.itemdialog.ItemBean;
import com.android.dialog.widgets.itemdialog.ItemType;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;


public class Utils {

    public static boolean isNonEmpty(String text) {
        return !TextUtils.isEmpty(text);
    }

    public static String getString(@StringRes int strRes) {
        return App.getContext().getString(strRes);
    }

    public static Drawable getDrawable(@DrawableRes int drawableRes) {
        return App.getContext().getResources().getDrawable(drawableRes);
    }

    public static int getColor(@ColorRes int colorRes) {
        return App.getContext().getResources().getColor(colorRes);
    }

    public static float getDimen(@DimenRes int dimenRes) {
        return App.getContext().getResources().getDimension(dimenRes);
    }

    public static List<ItemBean> getItemBeanList() {
        List<ItemBean> data = new ArrayList<>(3);

        ItemBean itemBeanWxFriends = new ItemBean();
        itemBeanWxFriends.setIcon(R.mipmap.icon_share_wx_friends);
        itemBeanWxFriends.setName(getString(R.string.wechat_friends));
        itemBeanWxFriends.setType(ItemType.WECHAT_FRIENDS);

        ItemBean itemBeanCircleOfFriends = new ItemBean();
        itemBeanCircleOfFriends.setIcon(R.mipmap.icon_share_wx_circle_of_friends);
        itemBeanCircleOfFriends.setName(getString(R.string.wechat_of_circle_friends));
        itemBeanCircleOfFriends.setType(ItemType.WECHAT_CIRCLE_OF_FRIENDS);

        ItemBean itemBeanWechatCollect = new ItemBean();
        itemBeanWechatCollect.setIcon(R.mipmap.icon_share_wx_collect);
        itemBeanWechatCollect.setName(getString(R.string.wechat_collect));
        itemBeanWechatCollect.setType(ItemType.WECHAT_COLLECT);
        data.add(itemBeanWxFriends);
        data.add(itemBeanCircleOfFriends);
        data.add(itemBeanWechatCollect);
        return data;
    }
}
