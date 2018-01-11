package nick.reminder.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nick on 1/11/2018.
 */

public class ReminderHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "reminder";
    public static final int DATABASE_VERSION = 1;


    public ReminderHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + DATABASE_NAME + " (" +
                ReminderDataModel._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ReminderDataModel.TITLE + " TEXT NOT NULL, " +
                ReminderDataModel.DESCRIPTION + " TEXT NOT NULL, " +
                ReminderDataModel.TAB + " TEXT NOT NULL);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS " + DATABASE_NAME;
        db.execSQL(dropTable);
        onCreate(db);
    }
}
