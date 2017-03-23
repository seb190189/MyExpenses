package com.nebula.sebjohn.myexpenses;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Database.SqliteDatabase;

public class ExpenditureSummary extends AppCompatActivity {


   /* Button nextButton,previousButton,addMore;
    EditText place,date,amount,category,additional_info;
    SQLiteDatabase sqLiteDatabase;
    String getSQLiteQuery,updateRecordQuery,deleteQuery;
    Cursor cursor;
    TextView idTextView;
    Boolean checkEditTextEmpty;
    String getPlace,getDate,getAmount,getCategory,getAddtional_Info;
    int userID;
    String convertUserID;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure_summary);



        /*
        nextButton = (Button)findViewById(R.id.button1);
        previousButton = (Button)findViewById(R.id.button2);
        addMore=(Button)findViewById(R.id.button3);
        place = (EditText)findViewById(R.id.editText1);
        date = (EditText)findViewById(R.id.editText2);
        amount = (EditText)findViewById(R.id.editText3);
        category=(EditText)findViewById(R.id.editText4);
        additional_info = (EditText)findViewById(R.id.editText5);
        idTextView=(TextView)findViewById(R.id.textview1);

        getSQLiteQuery ="SELECT * FROM expenditureTable";
        sqLiteDatabase = openOrCreateDatabase("ExpenditureDataBase", Context.MODE_PRIVATE,null);
        cursor =sqLiteDatabase.rawQuery(getSQLiteQuery,null);
        cursor.moveToFirst();
        getSQLiteDatabaseRecords();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cursor.isLast()){
                    cursor.moveToNext();

                }
                getSQLiteDatabaseRecords();
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cursor.isFirst()){
                    cursor.moveToPrevious();
                }
                getSQLiteDatabaseRecords();
            }
        });

        addMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(ExpenditureSummary.this,GetExpenditureDetails.class);
                startActivity(j);
            }
        });
    }

    public void getSQLiteDatabaseRecords(){
        idTextView.setText(cursor.getString(0));
        place.setText(cursor.getString(1).toString());
        date.setText(cursor.getString(2).toString());
        amount.setText(cursor.getString(3).toString());
        category.setText(cursor.getString(4).toString());
        additional_info.setText(cursor.getString(5).toString());
    }

   public void CheckEditTextIsEmptyOrNot(String Name,String PhoneNumber, String subject ){

        if(TextUtils.isEmpty(Name) || TextUtils.isEmpty(PhoneNumber) || TextUtils.isEmpty(subject)){

            CheckEditTextEmpty = true ;

        }
        else {
            CheckEditTextEmpty = false ;
        }*/


}}
