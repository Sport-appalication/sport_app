package com.example.sport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodViewAdapter extends RecyclerView.Adapter<FoodViewAdapter.ViewholdView>{
    private ArrayList<Food> FoodList = new ArrayList<>();
    private Context context;
    ConnectionSql connectionSql = new ConnectionSql();
    public FoodViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public com.example.sport.FoodViewAdapter.ViewholdView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foodview,parent,false);
        ViewholdView holder = new ViewholdView(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.sport.FoodViewAdapter.ViewholdView holder, @SuppressLint("RecyclerView") int position) {
        holder.tview.setText(FoodList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return FoodList.size();
    }
    public class ViewholdView extends RecyclerView.ViewHolder{
        private TextView tview;
        private RelativeLayout parent;
        public ViewholdView(@NonNull View itemView) {
            super(itemView);
            tview = itemView.findViewById(R.id.foodname);
            parent = itemView.findViewById(R.id.parent);
            parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    foodDetailActivity.setFood(FoodList.get(getAdapterPosition()));
                    Intent intent = new Intent(context, foodDetailActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }

    public void setFoodList(ArrayList<Food> foodList) {
        FoodList = foodList;
    }
}
