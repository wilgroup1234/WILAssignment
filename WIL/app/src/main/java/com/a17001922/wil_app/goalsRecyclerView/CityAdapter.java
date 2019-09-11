package com.a17001922.wil_app.goalsRecyclerView;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a17001922.wil_app.R;
import com.a17001922.wil_app.goals.goals;

import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private ArrayList<goals> goals;

    public CityAdapter(ArrayList<goals> goals) {
        this.goals = goals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        goals goal = goals.get(position);

        holder.name.setText(goal.getGoalName());
        holder.description.setText(goal.getGoalDescription());


    }

    @Override
    public int getItemCount() {
        if (goals != null) {
            return goals.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView name;
        public final TextView description;


        public ViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById(R.id.txtvGoalName);
            description = view.findViewById(R.id.txtvGoalDescription);

        }
    }
}