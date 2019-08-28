package com.android.dialog.widgets.itemdialog;

/**
 * @Copyright (C)seengene
 * @Package: open.ui.widgets.itemdialog
 * @ClassName: ItemType
 * @Description: $DESC$
 * @Author: seengene_lvTravler
 * @CreateDate: 2019/8/28 16:52
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/28 16:52
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public @interface ItemType {
    /**
     * 微信好友
     */
    int WECHAT_FRIENDS = 0x00000001;

    /**
     * 微信朋友圈
     */
    int WECHAT_CIRCLE_OF_FRIENDS = 0x00000002;

    /**
     * 微信收藏
     */
    int WECHAT_COLLECT=0x00000003;
}
