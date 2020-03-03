package com.butterfliesmonti.afterschool.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.butterfliesmonti.afterschool.R;
import com.butterfliesmonti.afterschool.activities.attendance_out;
import com.butterfliesmonti.afterschool.models.ActivitynameList;

import java.util.List;

public class attendance_main_adapter extends RecyclerView.Adapter<attendance_main_adapter.ViewHolder> {
    private Context mContext1;
    private List<ActivitynameList> mlists;
    private RecyclerView mRecyclerV1;

    public attendance_main_adapter.ViewHolder onCreateViewHolder(ViewGroup parent,
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
    public class ViewHolder extends RecyclerView.ViewHolder {
        public View layout;
        TextView activitynamelist;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            activitynamelist = v.findViewById(R.id.tvStudentList);
        }
    }

    public attendance_main_adapter(List<ActivitynameList> myDataset, Context context, RecyclerView recyclerView) {
        mlists = myDataset;
        Log.d("length-adapter", String.valueOf(mlists.size()));
        mContext1 = context;
        mRecyclerV1 = recyclerView;
    }

    public void onBindViewHolder(attendance_main_adapter.ViewHolder holder, final int position) {
        final ActivitynameList al = mlists.get(position);
        holder.activitynamelist.setText(al.getActivityName());
        holder.activitynamelist.setBackgroundResource(R.drawable.boxes1);
        holder.activitynamelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(), attendance_out.class);
                i.putExtra("activityname",al.getActivityName());
                v.getContext().startActivity(i);
            }
        });

        Log.d("length-bindvieholder", String.valueOf(mlists.size()));
    }

}
