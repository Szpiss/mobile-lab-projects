# 实验一：记事本

本实验实现一个带在线润色能力的 Android 记事本。

## 功能

- 使用 SQLite 保存笔记标题、内容和时间。
- 使用 RecyclerView 展示笔记列表。
- 点击列表项进入修改页面。
- 长按列表项弹出 AlertDialog 删除确认框。
- 在编辑页面提供清空、保存、润色三个操作。
- 使用 OkHttp 和 Gson 调用火山方舟 Ark Chat Completions 接口润色笔记内容。

## 关键文件

- `app/src/main/java/cn/edu/android/notepad/MainActivity.java`
- `app/src/main/java/cn/edu/android/notepad/RecordActivity.java`
- `app/src/main/java/cn/edu/android/notepad/DBUtils.java`
- `app/src/main/java/cn/edu/android/notepad/SQLiteHelper.java`
- `app/src/main/java/cn/edu/android/notepad/NoteAdapter.java`
- `app/src/main/java/cn/edu/android/notepad/VolcAiUtil.java`
- `app/src/main/res/layout/activity_main.xml`
- `app/src/main/res/layout/activity_record.xml`

## 运行

```bash
./gradlew :practiceNotepadApp:assembleDebug
```

在 Android Studio 中选择 `practiceNotepadApp` 运行。

## API Key

本实验不提交真实 API Key。本地运行润色功能前，在仓库根目录的 `local.properties` 中配置：

```properties
ARK_API_KEY=你的火山方舟APIKey
```
