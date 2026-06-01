package cn.edu.android.app04;

import com.google.gson.Gson;

public class JsonUtils2 {
    public String parseUserFromJson(String jsonData) {
        Gson gson = new Gson();
        User user = gson.fromJson(jsonData, User.class);
        return "&#160;&#160;name--->" + user.getName() + "<br/>"
                + "&#160;&#160;age--->" + user.getAge() + "<br/>";
    }
}
