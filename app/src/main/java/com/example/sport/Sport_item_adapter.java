package com.example.sport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Sport_item_adapter extends RecyclerView.Adapter<Sport_item_adapter.Viewhold>{
    private ArrayList<Sport> sports = new ArrayList<>();
    private Context context;
    public Sport_item_adapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Sport_item_adapter.Viewhold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sport_item,parent,false);
        Sport_item_adapter.Viewhold holder = new Sport_item_adapter.Viewhold(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Sport_item_adapter.Viewhold holder, @SuppressLint("RecyclerView") int position) {
        holder.tview.setText(sports.get(position).getName());
        holder.icon.setImageResource(sports.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return sports.size();
    }

    public void setSports(ArrayList<Sport> sports) {
        this.sports = sports;
        notifyDataSetChanged();
    }

    public class Viewhold extends RecyclerView.ViewHolder{
        private TextView tview;
        private RelativeLayout parent;
        private Button start;
        private ImageView icon;
        public Viewhold(@NonNull View itemView) {
            super(itemView);
            tview = itemView.findViewById(R.id.sportnametxt);
            parent = itemView.findViewById(R.id.parent);
            start =  itemView.findViewById(R.id.start);
            icon = itemView.findViewById(R.id.sporticon);
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SportViewActivity.setSportView(sports.get(getAdapterPosition()));
                    Intent intent = new Intent(context, SportViewActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }

}
