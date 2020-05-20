package com.butterfliesmonti.afterschool.Adapters;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.butterfliesmonti.afterschool.R;
import com.butterfliesmonti.afterschool.models.Studentdata_list;

import java.util.List;

public class student_list_adapter_lvl1 extends RecyclerView.Adapter<student_list_adapter_lvl1.ViewHolder> {
    private Context mContext1;
    private List<Studentdata_list> mlists;
    private RecyclerView mRecyclerV1;

    public student_list_adapter_lvl1.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.row_level1, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public int getItemCount() {
        return mlists.size();
    }

    public void onBindViewHolder(student_list_adapter_lvl1.ViewHolder holder, final int position) {
        final Studentdata_list al = mlists.get(position);
        boolean expanded = al.isExpanded();
        Log.d("array", String.valueOf(al.getActivityList().size()));
        if(al.getActivityList().size()!=0) {
            if (expanded) {
                holder.imageView.setImageResource(R.drawable.minus);
            } else {
                holder.imageView.setImageResource(R.drawable.plus);
            }
        }else
        {
            holder.imageView.setVisibility(View.INVISIBLE);
        }
        Log.d("expanded", String.valueOf(expanded));
        holder.mRecyclerView2.setVisibility(expanded ? View.VISIBLE : View.GONE);
        holder.studentlist.setText("Student Name:-" + al.getStudentname());
        holder.madapter2 = new student_list_adapter_lvl2(al.getActivityList(),mContext1,holder.mRecyclerView2);
        holder.mRecyclerView2.setAdapter(holder.madapter2);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean expanded = al.isExpanded();
                al.setExpanded(!expanded);
                notifyItemChanged(position);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View layout;
        TextView studentlist;
        private RecyclerView mRecyclerView2;
        private RecyclerView.LayoutManager mLayoutManager2;
        private student_list_adapter_lvl2 madapter2;
        ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            studentlist = v.findViewById(R.id.tvLevel1);
            mRecyclerView2=v.findViewById(R.id.recyclerviewlvl2);
            imageView=v.findViewById(R.id.imageExpand);
            mLayoutManager2 = new LinearLayoutManager(v.getContext(),LinearLayoutManager.VERTICAL,false);
            mRecyclerView2.setLayoutManager(mLayoutManager2);
        }
    }

    public student_list_adapter_lvl1(List<Studentdata_list> myDataset, Context context, RecyclerView recyclerView) {
        mlists = myDataset;
        mContext1 = context;
        mRecyclerV1 = recyclerView;
    }

}
