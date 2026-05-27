package cn.edu.android.notepad;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    public static final String DB_NAME = "note.db";
    public static final String U_NOTEPAD = "note";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + U_NOTEPAD + "( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "title VARCHAR, "
                + "content VARCHAR, "
                + "time VARCHAR "
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + U_NOTEPAD);
        onCreate(sqLiteDatabase);
    }
}
