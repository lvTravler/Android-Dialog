package com.android.dialog;

import android.app.Application;
import android.content.Context;

/**
 * @Copyright (C)seengene
 * @Package: com.android.dialog
 * @ClassName: App
 * @Description: $DESC$
 * @Author: seengene_lvTravler
 * @CreateDate: 2019/8/28 22:00
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/28 22:00
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
