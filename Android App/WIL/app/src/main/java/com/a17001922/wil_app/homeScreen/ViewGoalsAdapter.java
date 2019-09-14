package com.a17001922.wil_app.homeScreen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.a17001922.wil_app.R;

import java.util.ArrayList;

public class ViewGoalsAdapter extends RecyclerView.Adapter<ViewGoalsAdapter.ViewGoalsViewHolder>
{
    private ArrayList<cardViewItem> cardViewItems;

    public static class ViewGoalsViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageView;
        public TextView textView1;
        public TextView textView2;
        public CheckBox checkBox;

        public ViewGoalsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgIconCardView);
            textView1 = itemView.findViewById(R.id.txtGoalNameCardView);
            textView2 = itemView.findViewById(R.id.txtGoalDescriptionCardView);
            checkBox = itemView.findViewById(R.id.cBoxIsCompleted);
        }
    }

    public ViewGoalsAdapter(ArrayList<cardViewItem> cvItems)
    {
        cardViewItems = cvItems;
    }

    @NonNull
    @Override
    public ViewGoalsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.goal_card_item, viewGroup, false);
        ViewGoalsViewHolder viewGoalsViewHolder = new ViewGoalsViewHolder(v);
        return viewGoalsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewGoalsViewHolder viewHolder, int i)
    {
      cardViewItem cardViewItem = cardViewItems.get(i);
      viewHolder.imageView.setImageResource(cardViewItem.getImageResource());
      viewHolder.textView1.setText(cardViewItem.getGoalName());
      viewHolder.textView2.setText(cardViewItem.getGoalDescription());
      if (cardViewItem.isCompleted())
      {
          viewHolder.checkBox.setChecked(true);
      }
      else
      {
          viewHolder.checkBox.setChecked(false);
      }
    }

    @Override
    public int getItemCount()
    {
        return cardViewItems.size();
    }
}
