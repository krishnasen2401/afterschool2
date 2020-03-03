package com.butterfliesmonti.afterschool.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.butterfliesmonti.afterschool.R;
import com.butterfliesmonti.afterschool.activities.activities_reg;
import com.butterfliesmonti.afterschool.models.studentslist;

import java.util.List;

public class student_list_adapter extends RecyclerView.Adapter<student_list_adapter.ViewHolder> {
    private Context mContext1;
    private List<studentslist> mlists;
    private RecyclerView mRecyclerV1;
    String drawable;

    public student_list_adapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.row_studentlist, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public int getItemCount() {
        return mlists.size();
    }

    public void onBindViewHolder(student_list_adapter.ViewHolder holder, final int position) {
        final studentslist al = mlists.get(position);
        holder.studentlist.setText("Student Name:-" + al.getName() + "\nGender:-" + al.getGender() + "\nDoB:-" + al.getDob());
        holder.studentlist.setBackgroundResource(R.drawable.boxes1);
        drawable = String.valueOf(holder.studentlist.getBackground());
        holder.studentlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onclick","clicked");
                AlertLauncher(al,v);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View layout;
        TextView studentlist;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            studentlist = v.findViewById(R.id.tvStudentList);
        }
    }

    public student_list_adapter(List<studentslist> myDataset, Context context, RecyclerView recyclerView) {
        mlists = myDataset;
        mContext1 = context;
        mRecyclerV1 = recyclerView;
    }

    public void AlertLauncher(studentslist st,View v) {
        AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create();
        alertDialog.setTitle("Action Needed");
        alertDialog.setMessage("Do you want to register a course or check profile");
        alertDialog.setCancelable(false);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Register Course", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent I = new Intent(v.getContext(), activities_reg.class);
                I.putExtra("stname", st.getName());
                I.putExtra("stid", st.getStudentid());
                v.getContext().startActivity(I);
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Go to Profile", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //nothing to be done here required when back button is set false i.e setscancelable false
            }
        });
        alertDialog.show();
        }

}
