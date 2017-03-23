package com.nebula.sebjohn.myexpenses;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.CalendarContract;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class GetExpenditureDetails extends AppCompatActivity {

  /*  EditText recentspends_date;
    EditText recentspends_amount,recentspends_additional_information,recentspends_place;
    Spinner spinnerCategories;
    ImageButton imgBtnSave;
    SQLiteDatabase sqLiteDatabase;
    Boolean checkEditTextEmpty;
    String sqLiteQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_expenditure_details);

        recentspends_amount = (EditText) findViewById(R.id.edit_amount);
        recentspends_additional_information = (EditText) findViewById(R.id.edit_additional_info);
        recentspends_place = (EditText) findViewById(R.id.edit_place);
        imgBtnSave = (ImageButton) findViewById(R.id.button_save);

        //For saving data
        imgBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBCreate();
                submitData2SQLiteDB();
                Intent i = new Intent(GetExpenditureDetails.this,ExpenditureSummary.class);
                startActivity(i);
            }
        });




        //**For Spinner
        spinnerCategories=(Spinner) findViewById(R.id.myspinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(adapter);

        //**For DatePicker
        recentspends_date = (EditText)findViewById(R.id.edit_datepicker);
        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR,year);
            myCalendar.set(Calendar.MONTH,monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                String myFormat ="dd/MM/yyyy";
                SimpleDateFormat simpleDateFormat =new SimpleDateFormat(myFormat, Locale.UK);
                recentspends_date.setText(simpleDateFormat.format(myCalendar.getTime()));
             }
             };
            recentspends_date.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
               new DatePickerDialog(GetExpenditureDetails.this,datePickerListener,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
               }
             });



    }

    public void DBCreate(){
        sqLiteDatabase = openOrCreateDatabase("ExpenditureDataBase", Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS expenditureTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,place VARCHAR,date VARCHAR,amount VARCHAR,category VARCHAR,additional_info VARCHAR);");
    }

    public void submitData2SQLiteDB(){
        String Place = recentspends_place.getText().toString();
        String Date = recentspends_date.getText().toString();
        String Amount = recentspends_amount.getText().toString();
        String Category = spinnerCategories.getSelectedItem().toString();
        String Additional_Info = recentspends_additional_information.getText().toString();
        checkEditTextIsEmptyOrNot(Place,Amount);

        if (checkEditTextEmpty == true){
            sqLiteQuery = "INSERT INTO expenditureTable(place,date,amount,category,additional_info)VALUES('"+Place+"','"+Date+"','"+Amount+"','"+Category+"','"+Additional_Info+"');";
            sqLiteDatabase.execSQL(sqLiteQuery);
            Toast.makeText(this, "Data Submitted", Toast.LENGTH_SHORT).show();
            clearEditTextAfterDoneTask();

        }else{
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }
        }

    public void checkEditTextIsEmptyOrNot(String Place,String Amount){
        if (TextUtils.isEmpty(Place)||TextUtils.isEmpty(Amount)){
            checkEditTextEmpty = false;

        }
        else{
            checkEditTextEmpty=true;
        }
        }

    public void clearEditTextAfterDoneTask(){
        recentspends_additional_information.getText().clear();
        recentspends_place.getText().clear();
        recentspends_amount.getText().clear();
    }*/

}
