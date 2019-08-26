package com.android.dialog.widgets;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

/**
 * @Copyright (C)open
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
    private DialogParams dialogParams;

    public Dialog() {
        this.dialogParams = new DialogParams();
    }

    protected void show(AppCompatActivity activity, String tag) {
        if (!TextUtils.isEmpty(tag)) {
            show(activity.getSupportFragmentManager(), tag);
        } else {
            show(activity);
        }
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
        setCancelable(setCancelable());
        //设置背景透明
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    protected boolean setCancelable() {
        return dialogParams.isCancelable;
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
        return dialogParams.contentView;
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
            P = dialog.dialogParams;
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
