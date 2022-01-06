package com.example.sport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class bodyTypeList extends RecyclerView.Adapter<bodyTypeList.viewholder>{
    private ArrayList<BodyType> bodyTypes = new ArrayList<>();
    private int indexOfColoredItem = -1;
    private Context context;
    public bodyTypeList(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.body_type,parent,false);
        viewholder holder = new viewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.bindItem(position);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indexOfColoredItem = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return bodyTypes.size();
    }

    public void setbodyTypes(ArrayList<BodyType> bodyTypes) {
        this.bodyTypes = bodyTypes;
        notifyDataSetChanged();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        private TextView tview;
        private RelativeLayout parent;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            tview = itemView.findViewById(R.id.bodytypename);
            parent = itemView.findViewById(R.id.parent);
        }
        void bindItem(int pos) {
            String txt = bodyTypes.get(pos).getName();
            tview.setText(txt);
            if(indexOfColoredItem==pos){
                tview.setTextColor(ContextCompat.getColor(context, R.color.blue));
            } else{
                tview.setTextColor(ContextCompat.getColor(context, R.color.black));
            }
        }
    }

}
