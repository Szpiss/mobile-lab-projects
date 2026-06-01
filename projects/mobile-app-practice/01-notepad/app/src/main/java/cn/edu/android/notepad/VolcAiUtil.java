package cn.edu.android.notepad;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class VolcAiUtil {
    private static final String API_KEY = BuildConfig.ARK_API_KEY;
    private static final String MODEL_NAME = "ep-20260527083139-fkcnp";
    private static final String API_URL = "https://ark.cn-beijing.volces.com/api/v3/chat/completions";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();
    private static final Gson GSON = new Gson();

    public static String polishText(String content) throws IOException {
        String prompt = "请润色下面这段记事本内容，保持原意，只返回润色后的文字，不要解释：\n" + content;
        return sendAiRequest(prompt);
    }

    private static String sendAiRequest(String userMsg) throws IOException {
        if (API_KEY == null || API_KEY.trim().isEmpty()) {
            throw new IOException("请先在 local.properties 中配置 ARK_API_KEY");
        }

        JsonObject system = new JsonObject();
        system.addProperty("role", "system");
        system.addProperty("content", "你是专业笔记处理助手，负责把用户的笔记内容润色得自然、清晰、简洁。");

        JsonObject userObj = new JsonObject();
        userObj.addProperty("role", "user");
        userObj.addProperty("content", userMsg);

        JsonArray msgArr = new JsonArray();
        msgArr.add(system);
        msgArr.add(userObj);

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("model", MODEL_NAME);
        requestBody.add("messages", msgArr);
        requestBody.addProperty("temperature", 0.6);

        RequestBody body = RequestBody.create(requestBody.toString(), JSON);
        Request request = new Request.Builder()
                .url(API_URL)
                .header("Authorization", "Bearer " + API_KEY)
                .header("Content-Type", "application/json")
                .post(body)
                .build();

        try (Response response = HTTP_CLIENT.newCall(request).execute()) {
            String resStr = response.body() == null ? "" : response.body().string();
            if (!response.isSuccessful()) {
                throw new IOException("请求失败：" + response.code() + " " + resStr);
            }
            JsonObject resJson = GSON.fromJson(resStr, JsonObject.class);
            return resJson.getAsJsonArray("choices")
                    .get(0).getAsJsonObject()
                    .getAsJsonObject("message")
                    .get("content").getAsString()
                    .trim();
        }
    }
}
