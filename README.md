# Android-AlertDialogFragment

采用DialogFragment实现的AlertDialog,有以下几点优势：
1.异常情况状态自动保存
2.show、dismiss等严格被Fragment生命周期托管，不会出现内存泄露
3.支持自定义，扩展性高

**Show：**

![效果图](https://github.com/lvTravler/Android-AlertDialogFragment/blob/master/app/image/show.png)


**Usage**
```
 AlertDialog.with(MainActivity.this)
    .setCancelable(true)
    .setContent("Android Alert DialogFragment Content")
    .setTitle("AlertDialog Title")
    .setPositiveButton("confirm", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "click confirm", Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("cancel", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "click cancel", Toast.LENGTH_SHORT).show();
                    }
                }).show();
```

**It feels good，Please Star,Thank you!**
