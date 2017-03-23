package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nebula.sebjohn.myexpenses.Expenditure;
import com.nebula.sebjohn.myexpenses.ExpenditureSummary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian John on 3/22/2017.
 */

public class SqliteDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "myexpenditure";
    private static final String TABLE_EXPENDITURE = "myexpenditures";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PLACE = "place";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_AMOUNT = "amount";
    private static final String COLUMN_CATEGORIES = "categories";
    private static final String COLUMN_ADDITIONAL_INFO = "additional_info";


    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_EXPENDITURE_TABLE = "CREATE TABLE " + TABLE_EXPENDITURE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUMN_PLACE + " TEXT , " + COLUMN_DATE + "TEXT , " +
                COLUMN_AMOUNT + " INTEGER , " + COLUMN_CATEGORIES + " TEXT , " + COLUMN_ADDITIONAL_INFO + " TEXT );";
                sqLiteDatabase.execSQL(CREATE_EXPENDITURE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENDITURE);
        onCreate(sqLiteDatabase);
    }

    public List<Expenditure> listExpenditure() {
        String sql = "select * from " + TABLE_EXPENDITURE;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<Expenditure> storeExpenditure = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String place = cursor.getString(1);
                String date = cursor.getString(2);
                int amount = Integer.parseInt(cursor.getString(3));
                String category = cursor.getString(4);
                String additional_info = cursor.getString(5);
                storeExpenditure.add(new Expenditure(id, place, date, amount, category, additional_info));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeExpenditure;
    }

        public void addExpenditure(Expenditure expenditure){
            ContentValues values = new ContentValues();
            values.put(COLUMN_PLACE,expenditure.getPlace());
            values.put(COLUMN_DATE,expenditure.getDate());
            values.put(COLUMN_AMOUNT,expenditure.getAmount());
            values.put(COLUMN_CATEGORIES,expenditure.getCategories());
            values.put(COLUMN_ADDITIONAL_INFO,expenditure.getAdditional_Info());
            SQLiteDatabase db = this.getWritableDatabase();
            db.insert(TABLE_EXPENDITURE,null,values);
        }

        public void updateExpenditure(Expenditure expenditure){
            ContentValues values = new ContentValues();
            values.put(COLUMN_PLACE,expenditure.getPlace());
            values.put(COLUMN_DATE,expenditure.getDate());
            values.put(COLUMN_AMOUNT,expenditure.getAmount());
            values.put(COLUMN_CATEGORIES,expenditure.getCategories());
            values.put(COLUMN_ADDITIONAL_INFO,expenditure.getAdditional_Info());
            SQLiteDatabase db = this.getWritableDatabase();
            db.update(TABLE_EXPENDITURE,values,COLUMN_ID + " =?",new String[]{String.valueOf(expenditure.getId())});

        }

        public Expenditure findExpenditure(String place){
            String query = "Select * FROM" + TABLE_EXPENDITURE + "WHERE"+ COLUMN_PLACE + " = " + "place";
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            Expenditure mExpenditure =null;
            Cursor cursor=sqLiteDatabase.rawQuery(query,null);
            if (cursor.moveToFirst()){
                int id = Integer.parseInt(cursor.getString(0));
                String expenditurePlace = cursor.getString(1);
                String date = cursor.getString(2);
                int amount = Integer.parseInt(cursor.getString(3));
                String category = cursor.getString(4);
                String additional_info = cursor.getString(5);
                mExpenditure = new Expenditure(id,expenditurePlace,date,amount,category,additional_info);

            }
            cursor.close();
            return mExpenditure;
        }

        public void deleteExpenditure(int id){
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            sqLiteDatabase.delete(TABLE_EXPENDITURE,COLUMN_ID + " = ?",new String[]{String.valueOf(id)});
        }
}
