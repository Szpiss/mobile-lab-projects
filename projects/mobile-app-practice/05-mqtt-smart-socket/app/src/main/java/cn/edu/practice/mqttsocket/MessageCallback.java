package cn.edu.practice.mqttsocket;

import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MessageCallback implements MqttCallbackExtended {
    private static final String TAG = "MessageCallback";
    private final MainActivity mainActivity;

    MessageCallback(MainActivity activity) {
        this.mainActivity = activity;
    }

    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        Log.i(TAG, "GeekOpen -> 连接成功，重新连接：" + reconnect + "，地址：" + serverURI);
    }

    @Override
    public void connectionLost(Throwable throwable) {
        String reason = throwable == null ? "未知原因" : throwable.getMessage();
        Log.i(TAG, "GeekOpen -> 连接断开：" + reason);
        mainActivity.runOnUiThread(() ->
                Toast.makeText(mainActivity, "设备已断开！", Toast.LENGTH_LONG).show()
        );
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        try {
            Log.i(TAG, "GeekOpen -> 服务端发送文本消息到设备完成，消息："
                    + new String(token.getMessage().getPayload()));
        } catch (MqttException e) {
            Log.e(TAG, "deliveryComplete failed", e);
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) {
        String payload = new String(mqttMessage.getPayload());
        Log.i(TAG, "GeekOpen -> 服务端收到设备消息，topic：" + topic);
        Log.i(TAG, "GeekOpen -> 服务端收到设备消息，内容：" + payload);
        try {
            CallBackVo parse = new Gson().fromJson(payload, CallBackVo.class);
            if (parse.getKey() != null) {
                if (parse.getKey() == 1 && !mainActivity.tgBt.isChecked()) {
                    Message msg = mainActivity.handler.obtainMessage();
                    msg.what = MainActivity.MSG_SOCKET_ON;
                    mainActivity.handler.sendMessage(msg);
                } else if (parse.getKey() == 0 && mainActivity.tgBt.isChecked()) {
                    Message msg = mainActivity.handler.obtainMessage();
                    msg.what = MainActivity.MSG_SOCKET_OFF;
                    mainActivity.handler.sendMessage(msg);
                }
            }
            if (parse.getPower() != null || parse.getVoltage() != null || parse.getCurrent() != null) {
                String[] details = new String[]{
                        "功率(W)：" + valueOf(parse.getPower()),
                        "电压(V)：" + valueOf(parse.getVoltage()),
                        "电流(A)：" + valueOf(parse.getCurrent()),
                        "电能(kWh)：" + valueOf(parse.getEnergy()),
                        "状态：" + (parse.getKey() != null && parse.getKey() == 1 ? "打开" : "关闭")
                };
                Message msg = mainActivity.handler.obtainMessage();
                msg.what = MainActivity.MSG_STATUS_DETAIL;
                msg.obj = details;
                mainActivity.handler.sendMessage(msg);
            }
            Message msg = mainActivity.handler.obtainMessage();
            msg.what = MainActivity.MSG_TEXT;
            msg.obj = "收到设备消息：\n" + payload;
            mainActivity.handler.sendMessage(msg);
        } catch (Exception e) {
            Log.e(TAG, "parse message failed", e);
        }
    }

    private String valueOf(Object value) {
        return value == null ? "--" : value.toString();
    }
}
