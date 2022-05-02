package com.example.sport.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sport.DetailsActivity;
import com.example.sport.LevelActivity;
import com.example.sport.MainActivity;
import com.example.sport.R;
import com.example.sport.ReminderActivity;
import com.example.sport.SettingItem;
import com.example.sport.database.DatabaseConnection;
import com.example.sport.database.DatabaseControl;

import java.util.ArrayList;

public class UserSettingAdapter extends RecyclerView.Adapter<UserSettingAdapter.Viewholds>{
    private ArrayList<SettingItem> settings = new ArrayList<>();
    private Context context;
    DatabaseControl databaseControl = new DatabaseControl();
    public UserSettingAdapter(Context context) {
        settings.add(new SettingItem("Details", R.drawable.detail_icon));
        settings.add(new SettingItem("Reminder",R.drawable.schule_icon));
        settings.add(new SettingItem("Levels",R.drawable.ic_action_name));
        settings.add(new SettingItem("Log Out",R.drawable.logout_icon));
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
        holder.icon.setImageResource(settings.get(position).getImageUrl());
        holder.tview.setText(settings.get(position).getText());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position){
                    case 0:
                        context.startActivity(new Intent(context, DetailsActivity.class));
                        break;
                    case 1:
                        context.startActivity(new Intent(context, ReminderActivity.class));
                        break;
                    case 2:
                        context.startActivity(new Intent(context, LevelActivity.class));
                        break;
                    case 3:
                        databaseControl.logout();
                        context.startActivity(new Intent(context, MainActivity.class));
                        break;
                    default:
                        break;
                }
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
        private ImageView icon;
        public Viewholds(@NonNull View itemView) {
            super(itemView);
            tview = itemView.findViewById(R.id.settingtxt);
            parent = itemView.findViewById(R.id.parent);
            icon = itemView.findViewById(R.id.icon);
        }
    }

}
