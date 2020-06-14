package com.butterfliesmonti.afterschool.fragment;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.butterfliesmonti.afterschool.Adapters.student_list_adapter_lvl1;
import com.butterfliesmonti.afterschool.R;
import com.butterfliesmonti.afterschool.activities.student_list;
import com.butterfliesmonti.afterschool.baseurl;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link attendmonthmain#newInstance} factory method to
 * create an instance of this fragment.
 */
public class attendmonthmain extends DialogFragment {

    List<attendance> mlists;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView mRecyclerView;
    public attendmonthmain() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment attendmonthmain.
     */
    // TODO: Rename and change types and number of parameters
    public static attendmonthmain newInstance(String param1, String param2) {
        attendmonthmain fragment = new attendmonthmain();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_attendmonthmain, container, false);

        mRecyclerView=root.findViewById(R.id.fragRV);
        baseurl bs=new baseurl();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(bs.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())//mention which conveter we are using for fetch
                .build();

        Api_attendacedata_fetch service=retrofit.create(Api_attendacedata_fetch.class);
        Call<List<attendance>> call= service.getattendancedatajson("list",mParam1,mParam2);
        call.enqueue(new Callback<List<attendance>>() {
            @Override
            public void onResponse(Call<List<attendance>> call, Response<List<attendance>> response) {
                mlists=new ArrayList<>(response.body());
               RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                mRecyclerView.setLayoutManager(mLayoutManager);
                attendmonthmainadapter madapter=new attendmonthmainadapter(mlists,getContext(),mRecyclerView);

                mRecyclerView.setAdapter(madapter);
            }
            @Override
            public void onFailure(Call<List<attendance>> call, Throwable t) {
                Toast.makeText(getContext(),"Failed",Toast.LENGTH_SHORT).show();
            }
        });
        return root;

    }
    public class attendance{
        @SerializedName("no of days")
        @Expose
        private String noOfDays;
        @SerializedName("month-year")
        @Expose
        private String monthYear;

        public String getNoOfDays() {
            return noOfDays;
        }

        public void setNoOfDays(String noOfDays) {
            this.noOfDays = noOfDays;
        }

        public String getMonthYear() {
            return monthYear;
        }

        public void setMonthYear(String monthYear) {
            this.monthYear = monthYear;
        }
    }
    interface Api_attendacedata_fetch {
        @FormUrlEncoded
        @POST("attendance_android.php")
        Call<List<attendance>> getattendancedatajson(@Field("action") String action,@Field("studentid") String studentid,@Field("activityid") String activityid);
    }
}
