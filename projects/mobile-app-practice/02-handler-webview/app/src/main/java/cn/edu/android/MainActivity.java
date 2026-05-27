package cn.edu.android;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressbar;
    private WebView webview;
    private FloatingActionButton floatingactionbutton;
    private int progressValue = 0;

    private final Handler updateBarHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            int currentProgress = msg.arg1;
            if (currentProgress <= 100) {
                progressbar.setProgress(currentProgress);
                updateBarHandler.postDelayed(updateThread, 1000);
            } else {
                progressValue = 0;
                progressbar.setProgress(progressValue);
                progressbar.setVisibility(View.INVISIBLE);
                updateBarHandler.removeCallbacks(updateThread);
                Snackbar.make(floatingactionbutton, "网页已打开！", Snackbar.LENGTH_LONG)
                        .setAction("确定", view -> Toast.makeText(
                                MainActivity.this,
                                "网页已打开！",
                                Toast.LENGTH_SHORT
                        ).show())
                        .show();
            }
        }
    };

    private final Runnable updateThread = new Runnable() {
        @Override
        public void run() {
            progressValue += 10;
            Message msg = updateBarHandler.obtainMessage();
            msg.arg1 = progressValue;
            updateBarHandler.sendMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressbar = findViewById(R.id.progressbar);
        webview = findViewById(R.id.webview);
        floatingactionbutton = findViewById(R.id.floatingactionbutton);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void onViewClicked(View v) {
        progressValue = 0;
        progressbar.setProgress(progressValue);
        progressbar.setVisibility(View.VISIBLE);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://blog.csdn.net/");

        updateBarHandler.removeCallbacks(updateThread);
        updateBarHandler.post(updateThread);
    }

    @Override
    protected void onDestroy() {
        updateBarHandler.removeCallbacks(updateThread);
        super.onDestroy();
    }
}
