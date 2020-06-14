package com.butterfliesmonti.afterschool.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.butterfliesmonti.afterschool.R;
import java.util.List;

public class attendmonthmainadapter extends RecyclerView.Adapter<attendmonthmainadapter.ViewHolder> {
    private List<attendmonthmain.attendance> mlists;
    private Context mContext1;
    private RecyclerView mRecyclerV1;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public View layout;
        TextView textView;
        public ViewHolder(View v) {
            super(v);
            layout = v;
            textView=v.findViewById(R.id.tvStudentList);
        }

        public void add(int position, attendmonthmain.attendance activitieslist1) {
            mlists.add(position, activitieslist1);
            notifyItemInserted(position);
        }

    }
    public attendmonthmainadapter(List<attendmonthmain.attendance> myDataset, Context context, RecyclerView recyclerView) {
        mlists = myDataset;
        mContext1 = context;
        mRecyclerV1 = recyclerView;
    }
    public attendmonthmainadapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType){
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =inflater.inflate(R.layout.row_singletextbox, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    public void onBindViewHolder(attendmonthmainadapter. ViewHolder holder, final int position){
        final attendmonthmain.attendance al=mlists.get(position);
        holder.textView.setText("No of Days:-"+al.getNoOfDays()+"\nMonth/Year:-"+al.getMonthYear());
            }
    public int getItemCount(){
        return  mlists.size();}

}
