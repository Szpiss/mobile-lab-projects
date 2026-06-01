package cn.itcast.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DuckReceiver extends BroadcastReceiver {
    public interface OnDuckReceiveListener {
        void onReceiveDuck(String message);
    }

    private final String name;
    private final boolean intercept;
    private final OnDuckReceiveListener listener;

    public DuckReceiver(String name, boolean intercept, OnDuckReceiveListener listener) {
        this.name = name;
        this.intercept = intercept;
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String duckText = intent.getStringExtra(MainActivity.EXTRA_DUCK_TEXT);
        String message = name + " 收到：" + duckText;
        if (intercept && isOrderedBroadcast()) {
            message += "，已拦截广播";
            abortBroadcast();
        }
        if (listener != null) {
            listener.onReceiveDuck(message);
        }
    }
}
