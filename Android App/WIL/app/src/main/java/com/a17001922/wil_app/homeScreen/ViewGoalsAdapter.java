package com.a17001922.wil_app.homeScreen;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import com.a17001922.wil_app.LoginScreen.ReturnMessageObject;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.goals.DeleteGoalObject;
import com.a17001922.wil_app.goals.goalsService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
      viewHolder.textView3.setText(cardViewItem.getDate());

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

        @Override
        public void onDeleteClick(View v, int pos)
        {

           if(StaticClass.hasInternet)
           {
               if(StaticClass.ongoingOperation == false)
               {
                   try
                   {
                       //StaticClass.ongoingOperation = true;

                       String normal = "";
                       String id = "";

                       if(originalGoalsList.get(pos).isNormalGoal())
                       {
                           normal = "normal goal";
                       }
                       else
                       {
                           normal = "custom goal";
                       }

                       id = originalGoalsList.get(pos).getGoalID() + "";

                       Log.e("DELETE ", "CLICKED!" + pos + " " + normal + " ID: " + id );

                       DeleteGoalObject deleteGoalObject = new DeleteGoalObject();
                       deleteGoalObject.setEmail(StaticClass.currentUser);
                       deleteGoalObject.setGoalID(originalGoalsList.get(pos).getGoalID());
                       deleteGoalObject.setNormal(originalGoalsList.get(pos).isNormalGoal());


                       final goalsService service = StaticClass.retrofit.create(goalsService.class);

                       final String TAG = "ViewGoalsAdapter Class";


                       /*
                       try
                       {
                           final Call<ReturnMessageObject> deleteGoal = service.deleteGoal(deleteGoalObject);
                           deleteGoal .enqueue(new Callback<ReturnMessageObject>()
                           {
                               @Override
                               public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response)
                               {
                                   if (response.isSuccessful())
                                   {
                                       ReturnMessageObject returnMessageObject = response.body();

                                       if (returnMessageObject.getResult())
                                       {
                                           Log.e(TAG, "goal deleted successfully");



                                           StaticClass.ongoingOperation = false;
                                       }
                                       else
                                       {
                                           Log.e(TAG, " goal delete unsuccessful :(");
                                           StaticClass.ongoingOperation = false;
                                       }


                                   }
                               }

                               @Override
                               public void onFailure(Call<ReturnMessageObject> call, Throwable t)
                               {
                                   Log.e(TAG, " OnFailure error: can't delete goal");
                                   StaticClass.ongoingOperation = false;
                               }
                           });
                       }
                       catch(Exception e)
                       {
                           Log.e(TAG, " Exception error: " + e.getMessage());
                           StaticClass.ongoingOperation = false;
                       }
                          */

                   }
                   catch(Exception e)
                   {
                       Log.e("ViewGoalsAdapter Class", " " + e.getMessage());
                       StaticClass.ongoingOperation = false;
                   }
               }
               else
               {
                   Log.e("ViewGoalsAdapter Class", " ongoing operation");
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
