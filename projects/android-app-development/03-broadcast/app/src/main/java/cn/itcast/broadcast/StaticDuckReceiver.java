package cn.itcast.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StaticDuckReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String duckText = intent.getStringExtra(MainActivity.EXTRA_DUCK_TEXT);
        Toast.makeText(context, "指定接收者收到：" + duckText, Toast.LENGTH_SHORT).show();
    }
}
