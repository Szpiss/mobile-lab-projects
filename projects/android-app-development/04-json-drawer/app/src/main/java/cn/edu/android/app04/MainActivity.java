package cn.edu.android.app04;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Html;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private final String jsonData1 = "[{\"name\":\"Jhon\",\"age\":21},{\"name\":\"Mike\",\"age\":22}]";
    private final String jsonData2 = "{\"name\":\"Jhon\",\"age\":21}";
    private final String textFormatHeader = "<font color='#4D79FF'><bold><big>";
    private final String textFormatEnd = "</big></bold></font><br/>";

    private NavigationView navigationView;
    private DrawerLayout activityMain;
    private TextView textView;

    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            textView.setText(Html.fromHtml(msg.obj.toString(), Html.FROM_HTML_MODE_LEGACY));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.menu);
            actionBar.setTitle("JSON的使用");
        }

        navigationView = findViewById(R.id.navigationView);
        activityMain = findViewById(R.id.activityMain);
        textView = findViewById(R.id.textView);
        textView.setText(Html.fromHtml(getString(R.string.default_text), Html.FROM_HTML_MODE_LEGACY));

        navigationView.setItemIconTintList(null);
        navigationView.setCheckedItem(R.id.jsonArray);
        navigationView.setNavigationItemSelectedListener(item -> {
            Message msg = new Message();
            int itemId = item.getItemId();
            if (itemId == R.id.jsonArray) {
                JsonUtils1 jsonUtils1 = new JsonUtils1();
                msg.obj = textFormatHeader + "JSON数组为：" + textFormatEnd
                        + jsonData1 + "<br/><br/>"
                        + textFormatHeader + "JsonReader解析后为：" + textFormatEnd
                        + jsonUtils1.parseJson(jsonData1);
            } else if (itemId == R.id.gsonObject) {
                JsonUtils2 jsonUtils2 = new JsonUtils2();
                msg.obj = textFormatHeader + "JSON对象为：" + textFormatEnd
                        + jsonData2 + "<br/><br/>"
                        + textFormatHeader + "Gson解析JSON对象后为：" + textFormatEnd
                        + jsonUtils2.parseUserFromJson(jsonData2);
            } else if (itemId == R.id.gsonArray) {
                JsonUtils3 jsonUtils3 = new JsonUtils3();
                msg.obj = textFormatHeader + "JSON数组为：" + textFormatEnd
                        + jsonData1 + "<br/><br/>"
                        + textFormatHeader + "Gson解析JSON数组后为：" + textFormatEnd
                        + jsonUtils3.parseUserFromJson(jsonData1);
            }
            mHandler.sendMessage(msg);
            activityMain.closeDrawers();
            return true;
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            activityMain.openDrawer(Gravity.LEFT);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
