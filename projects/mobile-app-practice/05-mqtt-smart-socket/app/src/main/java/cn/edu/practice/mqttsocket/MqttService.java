package cn.edu.practice.mqttsocket;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MqttService {
    private static final String TAG = "MqttService";
    private static final boolean CLEAN_SESSION = false;
    private static MqttClient client;
    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();
    private static final Handler MAIN = new Handler(Looper.getMainLooper());
    private MainActivity mainActivity;

    public MqttService() throws MqttException {
        if (client == null) {
            client = new MqttClient(
                    MqttConfig.HOST + ":" + MqttConfig.PORT,
                    MqttConfig.CLIENT_ID,
                    new MemoryPersistence()
            );
        }
    }

    public void init(MainActivity activity) {
        this.mainActivity = activity;
        connect();
    }

    private void connect() {
        if (!MqttConfig.hasPassword()) {
            postStatus("已读取设备 MQTT 信息，还需要在 MqttConfig.java 填写平台密码后才能真实连接。");
            return;
        }
        EXECUTOR.execute(() -> {
            try {
                MqttConnectOptions options = new MqttConnectOptions();
                options.setCleanSession(CLEAN_SESSION);
                options.setUserName(MqttConfig.USER_NAME);
                options.setPassword(MqttConfig.PASSWORD.toCharArray());
                options.setConnectionTimeout(200);
                options.setKeepAliveInterval(30);
                options.setAutomaticReconnect(true);
                client.setCallback(new MessageCallback(mainActivity));
                if (!client.isConnected()) {
                    client.connect(options);
                }
                subscribe(MqttConfig.SUBSCRIBE_TOPIC);
                postStatus("设备已连接！\n已订阅：" + MqttConfig.SUBSCRIBE_TOPIC);
            } catch (Exception e) {
                Log.e(TAG, "connect failed", e);
                postStatus("连接失败：" + e.getMessage());
            }
        });
    }

    public static void subscribe(String topic) {
        EXECUTOR.execute(() -> {
            try {
                if (client != null && client.isConnected()) {
                    client.subscribe(topic, 0);
                }
            } catch (Exception e) {
                Log.e(TAG, "subscribe failed", e);
            }
        });
    }

    public static void sendMessage(String message, String topic, Integer qos) {
        EXECUTOR.execute(() -> {
            try {
                if (client == null || !client.isConnected()) {
                    Log.w(TAG, "MQTT is not connected, ignore message: " + message);
                    return;
                }
                MqttMessage mqttMessage = new MqttMessage();
                mqttMessage.setQos(qos);
                mqttMessage.setRetained(false);
                mqttMessage.setPayload(message.getBytes());
                client.publish(topic, mqttMessage);
            } catch (Exception e) {
                Log.e(TAG, "publish failed", e);
            }
        });
    }

    public static boolean isConnected() {
        return client != null && client.isConnected();
    }

    private void postStatus(String text) {
        MAIN.post(() -> {
            if (mainActivity != null) {
                mainActivity.showStatus(text);
            }
        });
    }
}
