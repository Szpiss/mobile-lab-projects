package cn.itcast.saveqq;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText etAccount;
    private EditText etPassword;
    private CheckBox cbRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applySystemBarInsets();
        initView();
        loadSavedUserInfo();
    }

    private void initView() {
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        cbRemember = findViewById(R.id.cb_remember);
        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(v -> login());
    }

    private void loadSavedUserInfo() {
        Map<String, String> userInfo = SPSaveQQ.getUserInfo(this);
        String account = userInfo.get("account");
        String password = userInfo.get("password");
        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(password)) {
            etAccount.setText(account);
            etPassword.setText(password);
            cbRemember.setChecked(true);
        }
    }

    private void login() {
        String account = etAccount.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(this, R.string.input_account, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, R.string.input_password, Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, R.string.login_success, Toast.LENGTH_SHORT).show();
        if (cbRemember.isChecked()) {
            boolean isSaveSuccess = SPSaveQQ.saveUserInfo(this, account, password);
            Toast.makeText(this,
                    isSaveSuccess ? R.string.save_success : R.string.save_failed,
                    Toast.LENGTH_SHORT).show();
        }
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
