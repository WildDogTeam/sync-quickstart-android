package com.wilddog.wilddogsyncdemo.tool;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/9/21.
 */
public class ToastTool {
    static Toast toast;
    public static void showToast(Context context,String content){
        if(toast==null){
            toast=new Toast(context);
        }
        TextView textView=new TextView(context);
        textView.setText(content);
        textView.setBackgroundColor(Color.BLACK);
        textView.setTextColor(Color.WHITE);
        toast.setView(textView);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
