package cn.edu.android.notepad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RecordActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvTime;
    private TextView tvMainTitle;
    private TextView tvBack;
    private EditText etTitle;
    private EditText etContent;
    private ImageView ivDel;
    private ImageView ivSave;
    private ImageView ivPolish;
    private DBUtils dbUtils;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initView();
        initData();
    }

    private void initView() {
        tvTime = findViewById(R.id.tv_time);
        tvMainTitle = findViewById(R.id.tv_main_title);
        tvBack = findViewById(R.id.tv_back);
        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);
        ivDel = findViewById(R.id.iv_del);
        ivSave = findViewById(R.id.iv_save);
        ivPolish = findViewById(R.id.iv_polish);

        tvBack.setVisibility(View.VISIBLE);
        tvBack.setOnClickListener(this);
        ivDel.setOnClickListener(this);
        ivSave.setOnClickListener(this);
        ivPolish.setOnClickListener(this);
    }

    private void initData() {
        dbUtils = DBUtils.getInstance(this);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        if (id != 0) {
            tvMainTitle.setText(R.string.edit_note);
            etTitle.setText(intent.getStringExtra("title"));
            etContent.setText(intent.getStringExtra("content"));
            tvTime.setText(intent.getStringExtra("time"));
            tvTime.setVisibility(View.VISIBLE);
        } else {
            tvMainTitle.setText(R.string.add_note);
            tvTime.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.tv_back) {
            finish();
        } else if (viewId == R.id.iv_del) {
            etContent.setText("");
        } else if (viewId == R.id.iv_save) {
            saveRecord();
        } else if (viewId == R.id.iv_polish) {
            polishContent();
        }
    }

    private void saveRecord() {
        String title = etTitle.getText().toString().trim();
        String content = etContent.getText().toString().trim();
        if (content.isEmpty()) {
            showToast(id == 0 ? "添加的内容不能为空!" : "修改内容不能为空!");
            return;
        }
        if (title.isEmpty()) {
            title = content.length() > 12 ? content.substring(0, 12) : content;
        }

        boolean success = id == 0
                ? dbUtils.saveNote(title, content, DBUtils.getTime())
                : dbUtils.updateNote(id, title, content, DBUtils.getTime());
        if (success) {
            showToast(id == 0 ? "保存成功" : "修改成功");
            setResult(2);
            finish();
        } else {
            showToast(id == 0 ? "保存失败" : "修改失败");
        }
    }

    private void polishContent() {
        String content = etContent.getText().toString().trim();
        if (content.isEmpty()) {
            showToast("请先输入要润色的内容");
            return;
        }
        ivPolish.setEnabled(false);
        showToast("正在润色，请稍候");
        new Thread(() -> {
            try {
                String result = VolcAiUtil.polishText(content);
                runOnUiThread(() -> {
                    etContent.setText(result);
                    etContent.setSelection(etContent.getText().length());
                    ivPolish.setEnabled(true);
                    showToast("润色完成");
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    ivPolish.setEnabled(true);
                    showToast("接口调用出错：" + e.getMessage());
                });
            }
        }).start();
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
