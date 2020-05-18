package com.butterfliesmonti.afterschool.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.butterfliesmonti.afterschool.R;
import com.butterfliesmonti.afterschool.models.student_regs_list;

import java.util.ArrayList;
import java.util.List;

public class student_list_adapter_attendance extends RecyclerView.Adapter<student_list_adapter_attendance.ViewHolder> {
    private Context mContext1;
    private List<student_regs_list> mlists;
    private List<student_regs_list> selectedlist;
    private RecyclerView mRecyclerV1;

    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                         int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.row_singletextbox, parent, false);
        selectedlist=new ArrayList<>();
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public int getItemCount() {
        return mlists.size();
    }

    public void onBindViewHolder(student_list_adapter_attendance.ViewHolder holder, final int position) {
        final student_regs_list al = mlists.get(position);
        holder.studentlist.setText("Student Name:-" + al.getName() + "\nStudentId:-" + al.getStudentid()+"\nActivityId:-" + al.getActivityId());
        holder.studentlist.setBackgroundResource(R.drawable.boxes1);
        holder.studentlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedlist.contains(al)) {
                    //true
                    selectedlist.remove(al);
                    holder.studentlist.setBackgroundResource(R.drawable.boxes1);
                }
                else{
                    //false
                    selectedlist.add(al);
                    holder.studentlist.setBackgroundResource(R.drawable.boxes);
                }

        }});
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

    public student_list_adapter_attendance(List<student_regs_list> myDataset, Context context, RecyclerView recyclerView) {
        mlists = myDataset;
        mContext1 = context;
        mRecyclerV1 = recyclerView;
    }
    public List<student_regs_list> sendSelectedList(){
        return selectedlist;
    }
}
