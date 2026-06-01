package cn.edu.android.app04;

import android.util.JsonReader;

import java.io.StringReader;

public class JsonUtils1 {
    public String parseJson(String jsonData) {
        StringBuilder result = new StringBuilder();
        try {
            JsonReader reader = new JsonReader(new StringReader(jsonData));
            reader.beginArray();
            while (reader.hasNext()) {
                reader.beginObject();
                while (reader.hasNext()) {
                    String tagName = reader.nextName();
                    if ("name".equals(tagName)) {
                        result.append("&#160;&#160;name--->")
                                .append(reader.nextString())
                                .append("<br/>");
                    } else if ("age".equals(tagName)) {
                        result.append("&#160;&#160;age--->")
                                .append(reader.nextInt())
                                .append("<br/>");
                    } else {
                        reader.skipValue();
                    }
                }
                reader.endObject();
            }
            reader.endArray();
            reader.close();
        } catch (Exception e) {
            result.append("解析失败：").append(e.getMessage());
        }
        return result.toString();
    }
}
