package Adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nebula.sebjohn.myexpenses.R;

/**
 * Created by Sebastian John on 3/23/2017.
 */

public class ExpenditureViewHolder extends RecyclerView.ViewHolder {
    public TextView expenseName;
    public ImageView deleteExpense;
    public ImageView editExpense;


    public ExpenditureViewHolder(View itemView) {
        super(itemView);
        expenseName = (TextView)itemView.findViewById(R.id.expense_name);
        deleteExpense=(ImageView)itemView.findViewById(R.id.delete_expense);
        editExpense =(ImageView)itemView.findViewById(R.id.edit_expense);
    }
}
