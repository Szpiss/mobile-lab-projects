package cn.edu.practice.mqttsocket;

public class MqttController {
    private MqttController() {
    }

    public static void open() {
        MqttService.sendMessage("{\"type\":\"event\",\"key\":1}", MqttConfig.PUBLISH_TOPIC, 0);
    }

    public static void close() {
        MqttService.sendMessage("{\"type\":\"event\",\"key\":0}", MqttConfig.PUBLISH_TOPIC, 0);
    }

    public static void getStatus() {
        MqttService.sendMessage("{\"type\":\"get\"}", MqttConfig.PUBLISH_TOPIC, 0);
    }

    public static void getInfo() {
        MqttService.sendMessage("{\"type\":\"info\"}", MqttConfig.PUBLISH_TOPIC, 0);
    }
}
