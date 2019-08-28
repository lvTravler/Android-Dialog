package com.android.dialog.widgets.itemdialog;

/**
 * @Copyright (C)seengene
 * @Package: com.seengene.ar_guide.ui.widgets.itemdialog
 * @ClassName: ItemBean
 * @Description: $DESC$
 * @Author: seengene_lvTravler
 * @CreateDate: 2019/8/28 11:51
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/28 11:51
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ItemBean {
    /**
     * 可用来区分点击事件对应的类型，例如分享时微信、QQ等
     */
    private int type;
    private String id;
    private int icon;
    private String name;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
