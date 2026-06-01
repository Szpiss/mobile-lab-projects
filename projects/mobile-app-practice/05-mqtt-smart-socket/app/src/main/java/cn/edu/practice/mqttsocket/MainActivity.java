package cn.edu.practice.mqttsocket;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.client.mqttv3.MqttException;

public class MainActivity extends AppCompatActivity {
    static final int MSG_TEXT = 1;
    static final int MSG_SOCKET_ON = 2;
    static final int MSG_SOCKET_OFF = 3;
    static final int MSG_STATUS_DETAIL = 4;

    TextView tvMsg;
    ToggleButton tgBt;
    MqttService mqttService;
    private boolean updatingFromDevice = false;

    public final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_TEXT) {
                tvMsg.setText(String.valueOf(msg.obj));
            } else if (msg.what == MSG_SOCKET_ON) {
                setToggleFromDevice(true);
            } else if (msg.what == MSG_SOCKET_OFF) {
                setToggleFromDevice(false);
            } else if (msg.what == MSG_STATUS_DETAIL) {
                showDetailAlertDialog((String[]) msg.obj);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMsg = findViewById(R.id.tv_msg);
        tgBt = findViewById(R.id.tg_bt);
        connectMqtt();
        tgBt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (updatingFromDevice) {
                    return;
                }
                if (!MqttService.isConnected()) {
                    showStatus("设备尚未连接，请先填写 MQTT 密码并重新连接。");
                    return;
                }
                if (isChecked) {
                    MqttController.open();
                    showStatus("已发送打开插座命令。");
                } else {
                    MqttController.close();
                    showStatus("已发送关闭插座命令。");
                }
            }
        });
    }

    public void connect(View v) {
        if (!MqttService.isConnected()) {
            connectMqtt();
        } else {
            Toast.makeText(this, "设备已连接！", Toast.LENGTH_LONG).show();
        }
    }

    public void getStatus(View v) {
        if (!MqttService.isConnected()) {
            connectMqtt();
        } else {
            MqttController.getStatus();
            showStatus("已发送获取设备状态命令。\n等待设备通过订阅主题返回功率、电压、电流等数据。");
            showDetailAlertDialog(new String[]{
                    "功率(W)：等待设备返回",
                    "电压(V)：等待设备返回",
                    "电流(A)：等待设备返回",
                    "电能(kWh)：等待设备返回",
                    "状态：已发送查询命令"
            });
        }
    }

    public void showStatus(String text) {
        tvMsg.setText(text);
    }

    public void showDetailAlertDialog(String[] details) {
        AlertDialog alert = new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_socket)
                .setTitle("智能转换器信息：")
                .setItems(details, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .create();
        alert.show();
    }

    private void connectMqtt() {
        try {
            mqttService = new MqttService();
            mqttService.init(this);
            if (MqttService.isConnected()) {
                showStatus("设备已连接！");
                MqttController.getInfo();
            }
        } catch (MqttException e) {
            showStatus("初始化 MQTT 失败：" + e.getMessage());
        }
    }

    private void setToggleFromDevice(boolean checked) {
        updatingFromDevice = true;
        tgBt.setChecked(checked);
        updatingFromDevice = false;
        showStatus(checked ? "设备当前状态：打开" : "设备当前状态：关闭");
    }
}
