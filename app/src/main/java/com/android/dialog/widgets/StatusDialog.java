package com.android.dialog.widgets;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.dialog.R;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Copyright (C)open
 * @ClassName: StatusDialog
 * @Description: $DESC$
 * @Author: seengene_lvTravler
 * @CreateDate: 2019/8/25 22:57
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/25 22:57
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class StatusDialog extends Dialog {

    private Handler mMainHandler = new Handler();
    /**
     * 成功或错误2s后dismiss
     */
    public static final int DELAY_TIME = 2000;
    private DialogParams mDialogParams;
    private Timer mDelayTimer;

    public StatusDialog() {
        mDialogParams = new DialogParams();
    }

    public static Builder with(AppCompatActivity activity) {
        return new Builder(activity);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.dialog_status;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView tvDialogStatusContent = view.findViewById(R.id.tv_dialog_status_prompt);
        ImageView imgDialogStatusShow = view.findViewById(R.id.img_dialog_status_show);
        ProgressBar pbDialogStatusShow = view.findViewById(R.id.pb_dialog_status_show);

        //更换圆形进度条颜色
        pbDialogStatusShow.getIndeterminateDrawable()
                .setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);

        //填充数据
        if (isNonEmpty(mDialogParams.prompt)) {
            tvDialogStatusContent.setText(mDialogParams.prompt);
        }

        switch (mDialogParams.type) {
            case Type.ERROR:
                pbDialogStatusShow.setVisibility(View.GONE);
                imgDialogStatusShow.setVisibility(View.VISIBLE);
                imgDialogStatusShow.setImageResource(R.mipmap.icon_dialog_error);
                break;
            case Type.SUCCESS:
                pbDialogStatusShow.setVisibility(View.GONE);
                imgDialogStatusShow.setVisibility(View.VISIBLE);
                imgDialogStatusShow.setImageResource(R.mipmap.icon_dialog_success);
                break;
            case Type.PROGRESS:
                imgDialogStatusShow.setVisibility(View.GONE);
                pbDialogStatusShow.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected boolean setCancelable() {
        return mDialogParams.isCancelable;
    }

    @Override
    public void onStart() {
        super.onStart();
        /**
         * 显示定时间后自动dismiss
         */
        if (mDialogParams.type == Type.SUCCESS || mDialogParams.type == Type.ERROR) {
            cancelTimer();
            mDelayTimer = new Timer();
            mDelayTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mMainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            dismiss();
                        }
                    });
                }
            }, DELAY_TIME);
        }
    }

    private void cancelTimer() {
        if (mDelayTimer != null) {
            mDelayTimer.cancel();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        cancelTimer();
    }

    @Override
    public void onPause() {
        super.onPause();
        cancelTimer();
    }

    public class DialogParams {
        String prompt;
        boolean isCancelable;
        int type = -1;
    }

    public static class Builder {
        AppCompatActivity activity;
        DialogParams P;
        StatusDialog progressDialog;

        public Builder(AppCompatActivity activity) {
            progressDialog = new StatusDialog();
            this.P = progressDialog.mDialogParams;
            this.activity = activity;
        }

        public Builder setPrompt(String val) {
            P.prompt = val;
            return this;
        }

        public Builder setCancelable(boolean val) {
            P.isCancelable = val;
            return this;
        }

        public Builder setType(int val) {
            P.type = val;
            return this;
        }

        public StatusDialog show() {
            if (P.type == -1) {
                throw new IllegalArgumentException("Please set type");
            }
            progressDialog.show(activity);
            return progressDialog;
        }
    }

    public interface Type {

        int PROGRESS = 0x000000000211;

        int ERROR = 0x000000000985;

        int SUCCESS = 0x00000000011;

    }
}
