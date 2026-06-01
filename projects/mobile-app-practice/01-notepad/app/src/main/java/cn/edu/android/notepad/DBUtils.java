package cn.edu.android.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DBUtils {
    private static DBUtils instance = null;
    private static SQLiteDatabase db;

    public DBUtils(Context context) {
        SQLiteHelper helper = new SQLiteHelper(context.getApplicationContext());
        db = helper.getWritableDatabase();
    }

    public static DBUtils getInstance(Context context) {
        if (instance == null) {
            instance = new DBUtils(context);
        }
        return instance;
    }

    public List<NoteBean> queryNote() {
        List<NoteBean> list = new ArrayList<>();
        String sql = "SELECT * FROM " + SQLiteHelper.U_NOTEPAD + " ORDER BY id DESC";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                NoteBean bean = new NoteBean();
                bean.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                bean.setTitle(cursor.getString(cursor.getColumnIndexOrThrow("title")));
                bean.setContent(cursor.getString(cursor.getColumnIndexOrThrow("content")));
                bean.setTime(cursor.getString(cursor.getColumnIndexOrThrow("time")));
                list.add(bean);
            }
            cursor.close();
        }
        return list;
    }

    public boolean deleteNote(int id) {
        int rows = db.delete(SQLiteHelper.U_NOTEPAD, "id=?", new String[]{String.valueOf(id)});
        return rows > 0;
    }

    public static String getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss", Locale.CHINA);
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    public boolean saveNote(String title, String content, String time) {
        ContentValues cv = new ContentValues();
        cv.put("title", title);
        cv.put("content", content);
        cv.put("time", time);
        long rowId = db.insert(SQLiteHelper.U_NOTEPAD, null, cv);
        return rowId > 0;
    }

    public boolean updateNote(int id, String title, String content, String time) {
        ContentValues cv = new ContentValues();
        cv.put("title", title);
        cv.put("content", content);
        cv.put("time", time);
        int rows = db.update(SQLiteHelper.U_NOTEPAD, cv, "id=?", new String[]{String.valueOf(id)});
        return rows > 0;
    }
}
