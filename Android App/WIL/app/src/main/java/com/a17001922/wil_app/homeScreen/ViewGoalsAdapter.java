package com.a17001922.wil_app.homeScreen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.goals.DeleteGoalObject;
import com.a17001922.wil_app.goals.goalsService;
import java.util.ArrayList;

//This class is used as a part of the recycler view functionality in the View Goals Page
public class ViewGoalsAdapter extends RecyclerView.Adapter<ViewGoalsViewHolder>
{
    private ArrayList<cardViewItem> cardViewItems;
    private ArrayList<GoalsCheckedClass> originalGoalsList;
    private ArrayList<GoalsCheckedClass> changedGoalList = new ArrayList<>();

    //_____________Binding fields and widgets_____________
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
      viewHolder.textView3.setText(cardViewItem.getDate());

      if (cardViewItem.isCompleted())
      {
          viewHolder.checkBox.setChecked(true);
      }
      else
      {
          viewHolder.checkBox.setChecked(false);
      }

          //On Click Listener for when a card item is clicked in the recycler view
          viewHolder.setItemClickListener(new CardViewItemClickListener()
          {
            @Override
            public void onItemClick(View v, int pos)
            {
                if(StaticClass.hasInternet)
                {
                    try
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
                    catch(Exception e)
                    {

                    }
                }

            }
        });


    }

    //Get Methods
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
