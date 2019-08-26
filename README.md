# Android-AlertDialogFragment

采用DialogFragment实现的AlertDialog,有以下几点优势：
1. 异常情况状态自动保存
2. show、dismiss等严格被Fragment生命周期托管，不会出现内存泄露
3. 支持自定义，扩展性高

**Show**
***AlertDialog:***

![AlertDialog](https://github.com/lvTravler/Android-AlertDialogFragment/blob/master/app/image/AlertDialog.png)

***ProgressDialog:***

![ProgressDialog](https://github.com/lvTravler/Android-AlertDialogFragment/blob/master/app/image/ProgressDialog.png)

***SuccessDialog:***

![SuccessDialog](https://github.com/lvTravler/Android-AlertDialogFragment/blob/master/app/image/SuccessDialog.png)

***ErrorDialog:***

![ErrorDialog](https://github.com/lvTravler/Android-AlertDialogFragment/blob/master/app/image/ErrorDialog.png)

**Usage**
```
   public void showAlertDialog() {
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

    public void showErrorDialog() {
        StatusDialog.with(MainActivity.this)
                .setCancelable(false)
                .setPrompt("load error")
                .setType(StatusDialog.Type.ERROR)
                .show();
    }

    public void showSuccessDialog() {
        StatusDialog.with(MainActivity.this)
                .setCancelable(false)
                .setPrompt("load success")
                .setType(StatusDialog.Type.SUCCESS)
                .show();
    }

    public void showProgressDialog() {
        StatusDialog.with(MainActivity.this)
                .setCancelable(false)
                .setPrompt("loading…")
                .setType(StatusDialog.Type.PROGRESS)
                .show();
    }
```

**It feels good，Please Star,Thank you!**
