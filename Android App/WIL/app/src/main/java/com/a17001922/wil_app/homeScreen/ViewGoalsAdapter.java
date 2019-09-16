package com.a17001922.wil_app.homeScreen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.a17001922.wil_app.R;

import java.util.ArrayList;
import java.util.List;

public class ViewGoalsAdapter extends RecyclerView.Adapter<ViewGoalsViewHolder>
{
    private ArrayList<cardViewItem> cardViewItems;
    private ArrayList<GoalsCheckedClass> originalGoalsList;
    private ArrayList<GoalsCheckedClass> changedGoalList = new ArrayList<>();


    public ViewGoalsAdapter(ArrayList<cardViewItem> cvItems, ArrayList<GoalsCheckedClass> ogItems)
    {
        cardViewItems = cvItems;
        originalGoalsList = ogItems;
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

      viewHolder.setItemClickListener(new CardViewItemClickListener()
      {
          @Override
          public void onItemClick(View v, int pos)
          {
              CheckBox checkBox = (CheckBox) v;

              GoalsCheckedClass changedGoal = originalGoalsList.get(pos);

              if (checkBox.isChecked())
              {
                  changedGoal.setChecked(true);

                  if (changedGoalList.contains(changedGoal))
                  {

                  }
                  else
                  {
                      changedGoalList.add(changedGoal);
                  }
              }
              else
              {
                  changedGoal.setChecked(false);

                  if (changedGoalList.contains(changedGoal))
                  {

                  }
                  else
                  {
                      changedGoalList.add(changedGoal);
                  }
              }



          }
      });
    }

    @Override
    public int getItemCount()
    {
        return cardViewItems.size();
    }

    public ArrayList<cardViewItem> getCardViewItems()
    {
        return cardViewItems;
    }


    public ArrayList<GoalsCheckedClass> getOriginalGoalsList()
    {
        return originalGoalsList;
    }

    public ArrayList<GoalsCheckedClass> getChangedGoalList()
    {
        return changedGoalList;
    }
}
