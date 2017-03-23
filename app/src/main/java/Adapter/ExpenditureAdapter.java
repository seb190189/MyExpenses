package Adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.nebula.sebjohn.myexpenses.Expenditure;
import com.nebula.sebjohn.myexpenses.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import Database.SqliteDatabase;

/**
 * Created by Sebastian John on 3/23/2017.
 */

public class ExpenditureAdapter extends RecyclerView.Adapter<ExpenditureViewHolder> {

    private Context context;
    private List<Expenditure> listExpenditure;
    private SqliteDatabase mDatabase;

    public ExpenditureAdapter(Context context,List<Expenditure> listExpenditure){
        this.context =context;
        this.listExpenditure = listExpenditure;
        mDatabase = new SqliteDatabase(context);
    }


    @Override
    public ExpenditureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_expenditure_summary,parent,false);

        return new ExpenditureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExpenditureViewHolder holder, int position) {
    final Expenditure singleExpense =listExpenditure.get(position);
        holder.expenseName.setText(singleExpense.getPlace());
        holder.editExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTaskDialog(singleExpense);
            }
        });
        holder.deleteExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.deleteExpenditure(singleExpense.getId());
                ((Activity)context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });
    }



    @Override
    public int getItemCount() {
        return listExpenditure.size();
    }

    private void editTaskDialog(final Expenditure expenditure){
        LayoutInflater inflater =LayoutInflater.from(context);
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
                new DatePickerDialog(context,datePickerListener,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final EditText amountField =(EditText)subview.findViewById(R.id.enter_amount);

        //Spinner
        final Spinner spinnerCategories=(Spinner)subview.findViewById(R.id.enter_category);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(adapter);

        final EditText additional_infoField = (EditText)subview.findViewById(R.id.enter_additional_info);

        if (expenditure !=null){
            placeField.setText(expenditure.getPlace());
            dateField.setText(expenditure.getDate());
            amountField.setText(String.valueOf(expenditure.getAmount()));
            additional_infoField.setText(expenditure.getAdditional_Info());

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit expense");
        builder.setView(subview);
        builder.create();

        builder.setPositiveButton("EDIT EXPENSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final String place = placeField.getText().toString();
                final String date = dateField.getText().toString();
                final int amount = Integer.parseInt(amountField.getText().toString());
                final String category = spinnerCategories.getSelectedItem().toString();
                final String additional_info = additional_infoField.getText().toString();

                if (TextUtils.isEmpty(place)||amount<=0 || TextUtils.isEmpty(category)||TextUtils.isEmpty(date)){
                    Toast.makeText(context, "Check input values", Toast.LENGTH_SHORT).show();
                }
                else {
                   mDatabase.updateExpenditure(new Expenditure(expenditure.getId(),place,date,amount,category,additional_info));

                    //refresh the activity
                    ((Activity)context).finish();
                    context.startActivity(((Activity)context).getIntent());
                }
            }
        });
        builder.setNegativeButton("CANCEL",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }


}
