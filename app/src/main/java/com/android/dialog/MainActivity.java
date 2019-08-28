package com.android.dialog;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.dialog.utils.Utils;
import com.android.dialog.widgets.AlertDialog;
import com.android.dialog.widgets.StatusDialog;
import com.android.dialog.widgets.itemdialog.ItemBean;
import com.android.dialog.widgets.itemdialog.ItemDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();
    private StatusDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog();
            }
        });
        findViewById(R.id.tv_act_main_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showItemDialog();
            }
        });
    }

    private void showItemDialog() {
        List<ItemBean> itemBeanList = Utils.getItemBeanList();
        ItemDialog.with(MainActivity.this)
                .setCancelable(true)
                .setData(itemBeanList)
                .setOnItemClickListener(new ItemDialog.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, ItemBean itemData) {
                        showSuccessDialog();
                    }
                })
                .setSpanCount(itemBeanList.size())
                .setAnimations(R.style.Dialog_Anim_Bottom_In_Bottom_Out)
                .setGravity(Gravity.BOTTOM)
                .setShowType(ItemDialog.ShowType.GRID)
                .show();
    }

    private void showAlertDialog() {
        AlertDialog.with(MainActivity.this)
                .setCancelable(true)
                .setContent("Android Alert DialogFragment Content")
                .setTitle("AlertDialog Title")
                .setPositiveButton("success", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showSuccessDialog();
                    }
                }).setNegativeButton("error", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showErrorDialog();
            }
        }).show();
    }

    private void showErrorDialog() {
        StatusDialog.with(MainActivity.this)
                .setCancelable(false)
                .setPrompt("load error")
                .setType(StatusDialog.Type.ERROR)
                .show();
    }

    private void showSuccessDialog() {
        StatusDialog.with(MainActivity.this)
                .setCancelable(false)
                .setPrompt("success")
                .setType(StatusDialog.Type.SUCCESS)
                .show();
    }

    private void showProgressDialog() {
        mProgressDialog = StatusDialog.with(MainActivity.this)
                .setCancelable(false)
                .setPrompt("loading…")
                .setType(StatusDialog.Type.PROGRESS)
                .show();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgressDialog.dismiss();
                showAlertDialog();
            }
        }, 3000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
