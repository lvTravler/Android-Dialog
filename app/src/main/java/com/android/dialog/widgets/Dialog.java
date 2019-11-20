package com.android.dialog.widgets;

import android.app.AlertDialog;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

/**
 * @Copyright (C)open
 * @Package: open.ui.widgets
 * @ClassName: Dialog
 * @Description: Dialog
 * @Author: seengene_lvTravler
 * @CreateDate: 2019/8/2 20:14
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/2 20:14
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class Dialog extends AppCompatDialogFragment implements View.OnClickListener {
    private DialogParams mDialogParams;

    public Dialog() {
        this.mDialogParams = new DialogParams();
    }

    protected void show(AppCompatActivity activity, String tag) {
        if (!TextUtils.isEmpty(tag)) {
            show(activity.getSupportFragmentManager(), tag);
        } else {
            show(activity);
        }
    }

    public static Builder newBuilder(AppCompatActivity activity) {
        return new Builder(activity);
    }

    protected void show(AppCompatActivity activity) {
        show(activity.getSupportFragmentManager(), activity.getClass().getSimpleName());
    }


    protected boolean isNonEmpty(String content) {
        return !TextUtils.isEmpty(content);
    }

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //getDialog().setCancelable(setCancelable());
        getDialog().setCanceledOnTouchOutside(setCancelable());
        Window window = getDialog().getWindow();
        int animationsRes = setAnimations();
        if (animationsRes != 0 && animationsRes != -1) {
            window.setWindowAnimations(animationsRes);
        }
        setCancelable(setCancelable());
        //设置背景透明
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        int gravity = setGravity();
        if (gravity != -1 && gravity != 0) {//即未设置
            window.setGravity(gravity);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        dismiss();
        super.onConfigurationChanged(newConfig);
    }

    /**
     * @return
     * @see Gravity
     */
    protected int setGravity() {
        return Gravity.CENTER;
    }

    protected boolean setCancelable() {
        return mDialogParams.isCancelable;
    }

    @StyleRes
    protected int setAnimations() {
        return mDialogParams.animations;
    }

    @NonNull
    @Override
    public final android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogLayout = LayoutInflater.from(getContext()).inflate(setLayoutRes(), null);
        builder.setView(dialogLayout);
        onViewCreated(dialogLayout, null);
        return builder.create();
    }

    protected int setLayoutRes() {
        return mDialogParams.contentView;
    }

    @Override
    public void onClick(View v) {

    }

    public class DialogParams {
        AppCompatActivity activity;
        int style;
        int animations;
        int contentView;
        String tag;
        boolean isCancelable;
    }

    public static class Builder {
        private DialogParams P;
        private Dialog dialog;

        private Builder(AppCompatActivity activity) {
            dialog = new Dialog();
            P = dialog.mDialogParams;
            P.activity = activity;
        }

        public Builder setStyle(int val) {
            P.style = val;
            return this;
        }

        public Builder setAnimations(int val) {
            P.animations = val;
            return this;
        }

        public Builder setContentView(@LayoutRes int val) {
            P.contentView = val;
            return this;
        }

        public Builder setCancelable(boolean val) {
            P.isCancelable = val;
            return this;
        }

        public Dialog build() {
            if (P.contentView == -1) {
                throw new IllegalArgumentException("Please set setContentView");
            }
            dialog.show(P.activity, P.tag);
            return dialog;
        }
    }

}
