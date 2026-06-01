package cn.edu.practice.mqttsocket;

public final class MqttConfig {
    public static final String HOST = "tcp://mqtt.smart-bird.cn";
    public static final String PORT = "1883";
    public static final String CLIENT_ID = "LDqWLGacrZXe";
    public static final String USER_NAME = "MpUhFiRrsBrY";
    public static final String PASSWORD = "xGqJqodbyUfbEaFbZL";
    public static final String DEVICE_MAC = "c82b96f82026";
    public static final String SUBSCRIBE_TOPIC = "/LqQAcl/KISoxNrBdGfi/c82b96f82026/publish";
    public static final String PUBLISH_TOPIC = "/LqQAcl/KISoxNrBdGfi/c82b96f82026/subscribe";

    private MqttConfig() {
    }

    public static boolean hasPassword() {
        return PASSWORD != null && !PASSWORD.trim().isEmpty()
                && !"请填写开发设置中的MQTT密码".equals(PASSWORD);
    }
}
