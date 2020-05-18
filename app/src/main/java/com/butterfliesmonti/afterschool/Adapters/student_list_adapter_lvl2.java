package com.butterfliesmonti.afterschool.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.butterfliesmonti.afterschool.R;
import com.butterfliesmonti.afterschool.models.Studentdata_list;

import java.util.List;

public class student_list_adapter_lvl2 extends RecyclerView.Adapter<student_list_adapter_lvl2.ViewHolder> {
    private Context mContext1;
    private List<String> mlists;
    private RecyclerView mRecyclerV1;

    public student_list_adapter_lvl2.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.row_singletextbox, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public int getItemCount() {
        return mlists.size();
    }

    public void onBindViewHolder(student_list_adapter_lvl2.ViewHolder holder, final int position) {
        final String al = mlists.get(position);
        holder.studentlist.setText(al);
        holder.studentlist.setBackgroundResource(R.drawable.boxes);
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

    public student_list_adapter_lvl2(List<String> myDataset, Context context, RecyclerView recyclerView) {
        mlists = myDataset;
        mContext1 = context;
        mRecyclerV1 = recyclerView;
    }

}
