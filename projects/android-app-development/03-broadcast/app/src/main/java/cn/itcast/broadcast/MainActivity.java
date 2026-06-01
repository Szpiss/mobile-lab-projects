package cn.itcast.broadcast;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String ACTION_UNORDERED = "cn.itcast.broadcast.UNORDERED_DUCK";
    public static final String ACTION_ORDERED = "cn.itcast.broadcast.ORDERED_DUCK";
    public static final String ACTION_SAME_PRIORITY = "cn.itcast.broadcast.SAME_PRIORITY_DUCK";
    public static final String ACTION_INTERCEPT = "cn.itcast.broadcast.INTERCEPT_DUCK";
    public static final String ACTION_STATIC = "cn.itcast.broadcast.STATIC_DUCK";
    public static final String EXTRA_DUCK_TEXT = "duck_text";

    private TextView tvResult;
    private final StringBuilder resultBuilder = new StringBuilder();

    private DuckReceiver unorderedOne;
    private DuckReceiver unorderedTwo;
    private DuckReceiver unorderedThree;
    private DuckReceiver orderedOne;
    private DuckReceiver orderedTwo;
    private DuckReceiver orderedThree;
    private DuckReceiver sameOne;
    private DuckReceiver sameTwo;
    private DuckReceiver sameThree;
    private DuckReceiver interceptOne;
    private DuckReceiver interceptTwo;
    private DuckReceiver interceptThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applySystemBarInsets();
        initView();
        registerDuckReceivers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(unorderedOne);
        unregisterReceiver(unorderedTwo);
        unregisterReceiver(unorderedThree);
        unregisterReceiver(orderedOne);
        unregisterReceiver(orderedTwo);
        unregisterReceiver(orderedThree);
        unregisterReceiver(sameOne);
        unregisterReceiver(sameTwo);
        unregisterReceiver(sameThree);
        unregisterReceiver(interceptOne);
        unregisterReceiver(interceptTwo);
        unregisterReceiver(interceptThree);
    }

    private void initView() {
        tvResult = findViewById(R.id.tv_result);
        Button btnUnordered = findViewById(R.id.btn_unordered);
        Button btnOrdered = findViewById(R.id.btn_ordered);
        Button btnSamePriority = findViewById(R.id.btn_same_priority);
        Button btnIntercept = findViewById(R.id.btn_intercept);
        Button btnStatic = findViewById(R.id.btn_static);
        Button btnClear = findViewById(R.id.btn_clear);

        btnUnordered.setOnClickListener(v -> sendDuckBroadcast(ACTION_UNORDERED, false,
                "无序广播：一只鸭、两只鸭、三只鸭"));
        btnOrdered.setOnClickListener(v -> sendDuckBroadcast(ACTION_ORDERED, true,
                "有序广播：按优先级数鸭子"));
        btnSamePriority.setOnClickListener(v -> sendDuckBroadcast(ACTION_SAME_PRIORITY, true,
                "同优先级广播：按注册顺序数鸭子"));
        btnIntercept.setOnClickListener(v -> sendDuckBroadcast(ACTION_INTERCEPT, true,
                "拦截广播：第一只鸭拦住队伍"));
        btnStatic.setOnClickListener(v -> sendStaticReceiverBroadcast());
        btnClear.setOnClickListener(v -> {
            resultBuilder.setLength(0);
            tvResult.setText(R.string.result_empty);
        });
    }

    private void registerDuckReceivers() {
        unorderedOne = new DuckReceiver("接收者 A", false, this::appendResult);
        unorderedTwo = new DuckReceiver("接收者 B", false, this::appendResult);
        unorderedThree = new DuckReceiver("接收者 C", false, this::appendResult);
        registerDuckReceiver(unorderedOne, ACTION_UNORDERED, 0);
        registerDuckReceiver(unorderedTwo, ACTION_UNORDERED, 0);
        registerDuckReceiver(unorderedThree, ACTION_UNORDERED, 0);

        orderedOne = new DuckReceiver("高优先级接收者 A", false, this::appendResult);
        orderedTwo = new DuckReceiver("中优先级接收者 B", false, this::appendResult);
        orderedThree = new DuckReceiver("低优先级接收者 C", false, this::appendResult);
        registerDuckReceiver(orderedOne, ACTION_ORDERED, 900);
        registerDuckReceiver(orderedTwo, ACTION_ORDERED, 600);
        registerDuckReceiver(orderedThree, ACTION_ORDERED, 300);

        sameOne = new DuckReceiver("同优先级接收者 A", false, this::appendResult);
        sameTwo = new DuckReceiver("同优先级接收者 B", false, this::appendResult);
        sameThree = new DuckReceiver("同优先级接收者 C", false, this::appendResult);
        registerDuckReceiver(sameOne, ACTION_SAME_PRIORITY, 500);
        registerDuckReceiver(sameTwo, ACTION_SAME_PRIORITY, 500);
        registerDuckReceiver(sameThree, ACTION_SAME_PRIORITY, 500);

        interceptOne = new DuckReceiver("拦截接收者 A", true, this::appendResult);
        interceptTwo = new DuckReceiver("拦截接收者 B", false, this::appendResult);
        interceptThree = new DuckReceiver("拦截接收者 C", false, this::appendResult);
        registerDuckReceiver(interceptOne, ACTION_INTERCEPT, 900);
        registerDuckReceiver(interceptTwo, ACTION_INTERCEPT, 600);
        registerDuckReceiver(interceptThree, ACTION_INTERCEPT, 300);
    }

    private void registerDuckReceiver(DuckReceiver receiver, String action, int priority) {
        IntentFilter filter = new IntentFilter(action);
        filter.setPriority(priority);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(receiver, filter, Context.RECEIVER_NOT_EXPORTED);
        } else {
            registerReceiver(receiver, filter);
        }
    }

    private void sendDuckBroadcast(String action, boolean ordered, String duckText) {
        appendHeader(duckText);
        Intent intent = new Intent(action);
        intent.setPackage(getPackageName());
        intent.putExtra(EXTRA_DUCK_TEXT, duckText);
        if (ordered) {
            sendOrderedBroadcast(intent, null);
        } else {
            sendBroadcast(intent);
        }
    }

    private void sendStaticReceiverBroadcast() {
        String duckText = "指定广播：只让 StaticDuckReceiver 数鸭子";
        appendHeader(duckText);
        appendResult("MainActivity 指定接收者：StaticDuckReceiver");
        Intent intent = new Intent(ACTION_STATIC);
        intent.setComponent(new ComponentName(this, StaticDuckReceiver.class));
        intent.putExtra(EXTRA_DUCK_TEXT, duckText);
        sendBroadcast(intent);
    }

    private void appendHeader(String text) {
        resultBuilder.setLength(0);
        resultBuilder.append("发送内容：").append(text).append("\n\n");
        tvResult.setText(resultBuilder.toString());
    }

    private void appendResult(String text) {
        resultBuilder.append(text).append('\n');
        tvResult.setText(resultBuilder.toString());
    }

    private void applySystemBarInsets() {
        View root = findViewById(R.id.main_root);
        root.setOnApplyWindowInsetsListener((view, insets) -> {
            view.setPadding(view.getPaddingLeft(), insets.getSystemWindowInsetTop(),
                    view.getPaddingRight(), insets.getSystemWindowInsetBottom());
            return insets;
        });
        root.requestApplyInsets();
    }
}
