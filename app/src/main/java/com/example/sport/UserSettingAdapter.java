package com.example.sport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserSettingAdapter extends RecyclerView.Adapter<UserSettingAdapter.Viewholds>{
    private ArrayList<String> settings = new ArrayList<>();
    private Context context;
    public UserSettingAdapter(Context context) {
        settings.add("hhe");
        settings.add("hsde");
        settings.add("hsdsdhe");
        settings.add("hhdse");
        settings.add("hdsdhe");
        settings.add("hhe");
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholds onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.username,parent,false);
        Viewholds holder = new Viewholds(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholds holder, @SuppressLint("RecyclerView") int position) {
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return settings.size();
    }
    public class Viewholds extends RecyclerView.ViewHolder{
        private TextView tview;
        private RelativeLayout parent;
        public Viewholds(@NonNull View itemView) {
            super(itemView);
            tview = itemView.findViewById(R.id.settingtxt);
            parent = itemView.findViewById(R.id.parent);
        }
    }

}
