package cn.edu.android.app04;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedList;

public class JsonUtils3 {
    public String parseUserFromJson(String jsonData) {
        Type listType = new TypeToken<LinkedList<User>>() {
        }.getType();
        Gson gson = new Gson();
        LinkedList<User> users = gson.fromJson(jsonData, listType);
        StringBuilder result = new StringBuilder();
        for (User user : users) {
            result.append("&#160;&#160;name--->")
                    .append(user.getName())
                    .append("<br/>");
            result.append("&#160;&#160;age--->")
                    .append(user.getAge())
                    .append("<br/>");
        }
        return result.toString();
    }
}
