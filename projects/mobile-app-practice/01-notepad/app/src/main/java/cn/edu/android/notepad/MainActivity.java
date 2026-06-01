package cn.edu.android.notepad;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvList;
    private ImageView ivAdd;
    private TextView tvMainTitle;
    private TextView tvEmpty;
    private NoteAdapter adapter;
    private List<NoteBean> list = new ArrayList<>();
    private DBUtils dbUtils;
    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLauncher();
        init();
    }

    private void initLauncher() {
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == 2) {
                        showQueryData();
                    }
                }
        );
    }

    private void init() {
        dbUtils = DBUtils.getInstance(this);
        tvMainTitle = findViewById(R.id.tv_main_title);
        tvEmpty = findViewById(R.id.tv_empty);
        rvList = findViewById(R.id.rv_list);
        ivAdd = findViewById(R.id.iv_add);
        tvMainTitle.setText(R.string.notepad_title);
        rvList.setLayoutManager(new LinearLayoutManager(this));

        adapter = new NoteAdapter(this, new NoteAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (position < 0 || position >= list.size()) {
                    return;
                }
                NoteBean bean = list.get(position);
                Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                intent.putExtra("id", bean.getId());
                intent.putExtra("title", bean.getTitle());
                intent.putExtra("content", bean.getContent());
                intent.putExtra("time", bean.getTime());
                launcher.launch(intent);
            }

            @Override
            public void onItemLongClick(View v, int position) {
                if (position < 0 || position >= list.size()) {
                    return;
                }
                showDeleteDialog(position);
            }
        });

        ivAdd.setOnClickListener(v -> launcher.launch(new Intent(MainActivity.this, RecordActivity.class)));
        rvList.setAdapter(adapter);
        showQueryData();
    }

    private void showDeleteDialog(int position) {
        new AlertDialog.Builder(MainActivity.this)
                .setIcon(R.drawable.ic_delete)
                .setTitle("删除提示")
                .setMessage("是否删除此记录？")
                .setPositiveButton("确定", (dialog, which) -> {
                    NoteBean noteBean = list.get(position);
                    if (dbUtils.deleteNote(noteBean.getId())) {
                        list.remove(position);
                        adapter.setData(list);
                        updateEmptyState();
                        Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void showQueryData() {
        list = dbUtils.queryNote();
        adapter.setData(list);
        updateEmptyState();
    }

    private void updateEmptyState() {
        tvEmpty.setVisibility(list == null || list.isEmpty() ? View.VISIBLE : View.GONE);
    }
}
