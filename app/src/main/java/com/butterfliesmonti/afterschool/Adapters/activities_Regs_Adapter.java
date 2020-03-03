package com.butterfliesmonti.afterschool.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.butterfliesmonti.afterschool.R;
import com.butterfliesmonti.afterschool.models.activities_reg_list;
import com.butterfliesmonti.afterschool.models.activitieslist;

import java.util.ArrayList;
import java.util.List;

public class activities_Regs_Adapter extends RecyclerView.Adapter<activities_Regs_Adapter.ViewHolder> {
    private List<activitieslist> mlists;
    public List<activities_reg_list>selectedactivites;
    private Context mContext1;
    private String Userid;
    private RecyclerView mRecyclerV1;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public View layout;
        CheckBox cbactivitieslistreg;
        public ViewHolder(View v) {
            super(v);
            layout = v;
            cbactivitieslistreg = v.findViewById(R.id.cbActiviteslist);
            selectedactivites=new ArrayList<>();
        }

        public void add(int position, activitieslist activitieslist1) {
            mlists.add(position, activitieslist1);
            notifyItemInserted(position);
        }

    }
    public activities_Regs_Adapter(List<activitieslist> myDataset, Context context, RecyclerView recyclerView,String Studentid) {
        mlists = myDataset;
        mContext1 = context;
        mRecyclerV1 = recyclerView;
        Userid=Studentid;
    }
    public activities_Regs_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType){
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =inflater.inflate(R.layout.row_activitieslist, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    public void onBindViewHolder(activities_Regs_Adapter.ViewHolder holder, final int position){
        final activitieslist al=mlists.get(position);
        activities_reg_list arl=new activities_reg_list();
        holder.cbactivitieslistreg.setText("Activity Name:-"+al.getActivityName()+"\nAge Group:-"+al.getAgeGroup()+"\nFees:-"+al.getFees());
        holder.cbactivitieslistreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(holder.cbactivitieslistreg.isChecked()) {
                holder.cbactivitieslistreg.setBackgroundResource(R.drawable.boxes1);
                Log.d("foundstatus", String.valueOf(selectedactivites.contains(arl)));
                arl.setStudentid(Userid);
                arl.setActivityId(al.getActivityId());
                arl.setDateOfReg("2019-01-17");
                selectedactivites.add(arl);
            }else{
                Log.d("foundstatus", String.valueOf(selectedactivites.contains(arl)));
                holder.cbactivitieslistreg.setBackgroundResource(0);
                arl.setStudentid(Userid);
                arl.setActivityId(al.getActivityId());
                arl.setDateOfReg("2019-01-17");
                selectedactivites.remove(arl);
            }
            }
        });
    }
    public int getItemCount(){
        return  mlists.size();}
    public List<activities_reg_list> listofselectedactivites(){
        return selectedactivites;
    }
}