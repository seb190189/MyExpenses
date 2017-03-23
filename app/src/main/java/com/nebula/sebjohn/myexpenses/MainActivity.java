package com.nebula.sebjohn.myexpenses;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import Adapter.ExpenditureAdapter;
import Database.SqliteDatabase;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = ExpenditureSummary.class.getSimpleName();
    private SqliteDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout frameLayout=(FrameLayout)findViewById(R.id.activity_expenditure_summary);
        RecyclerView expenditureView =(RecyclerView)findViewById(R.id.expenditure_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        expenditureView.setLayoutManager(linearLayoutManager);

        expenditureView.setHasFixedSize(true);
        mDatabase = new SqliteDatabase(this);

        List<Expenditure> allExpenditure = mDatabase.listExpenditure();

        if (allExpenditure.size() > 0){
            expenditureView.setVisibility(View.VISIBLE);
            ExpenditureAdapter mAdapter = new ExpenditureAdapter(this,allExpenditure);
            expenditureView.setAdapter(mAdapter);
        } else {
            expenditureView.setVisibility(View.GONE);
            Toast.makeText(this, "There is no entries.Start adding now", Toast.LENGTH_SHORT).show();
        }

        FloatingActionButton fab =(FloatingActionButton)findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            addTaskDialog();
            }
        });
    }

    private void addTaskDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subview = inflater.inflate(R.layout.activity_edit_expenditure_details,null);

        final EditText placeField = (EditText)subview.findViewById(R.id.enter_place);

        //date picker
             final EditText dateField =(EditText)subview.findViewById(R.id.enter_date);
             final Calendar myCalendar = Calendar.getInstance();
             final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                String myFormat ="dd/MM/yyyy";
                SimpleDateFormat simpleDateFormat =new SimpleDateFormat(myFormat, Locale.UK);
                dateField.setText(simpleDateFormat.format(myCalendar.getTime()));
            }
        };
            dateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               new DatePickerDialog(MainActivity.this,datePickerListener,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final EditText amountField =(EditText)subview.findViewById(R.id.enter_amount);

        //Spinner
        final Spinner spinnerCategories=(Spinner)subview.findViewById(R.id.enter_category);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(adapter);

        final EditText additional_infoField = (EditText)subview.findViewById(R.id.enter_additional_info);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add new expense");
        builder.setView(subview);
        builder.create();

        builder.setPositiveButton("ADD EXPENSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final String place = placeField.getText().toString();
                final String date = dateField.getText().toString();
                final int amount = Integer.parseInt(amountField.getText().toString());
                final String category = spinnerCategories.getSelectedItem().toString();
                final String additional_info = additional_infoField.getText().toString();

                if (TextUtils.isEmpty(place)|| amount <= 0 || TextUtils.isEmpty(category)||TextUtils.isEmpty(date)){
                    Toast.makeText(MainActivity.this, "Please input values", Toast.LENGTH_SHORT).show();
                }
                else {
                    Expenditure newExpenditure = new Expenditure(place,date,amount,category,additional_info);
                    mDatabase.addExpenditure(newExpenditure);
                    finish();
                    startActivity(getIntent());
                }
            }
        });
        builder.setNegativeButton("CANCEL",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Task Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

            @Override
            protected void onDestroy(){
            super.onDestroy();
            if (mDatabase != null){
            mDatabase.close();
        }
    }
}
