package com.work.diandianzhuan.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;


/**
 * created by vincent on 2020/8/20
 */
public class ClipboardUtils {
    public static String getClipboard(Context context) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = cm.getPrimaryClip();
        if (data == null)
            return "";
        ClipData.Item item = data.getItemAt(0);
        if (item == null)
            return "";
        if (item.getText() == null)
            return "";
        String content = item.getText().toString();
        return content;
    }
}
