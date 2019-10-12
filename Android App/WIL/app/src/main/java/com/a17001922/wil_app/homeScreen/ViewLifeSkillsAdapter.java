package com.a17001922.wil_app.homeScreen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;

import java.util.ArrayList;

public class ViewLifeSkillsAdapter extends RecyclerView.Adapter<ViewLifeSkillsViewHolder>
{
    private ArrayList<cardViewItem2> cardViewItems;
    private ArrayList<LifeSkillChecked> changedLifeSkillsList = new ArrayList<>();
    private ArrayList<LifeSkillChecked> originalLifeSkillsList;


    public ViewLifeSkillsAdapter(ArrayList<cardViewItem2> cvItems,  ArrayList<LifeSkillChecked> ogItems)
    {
        cardViewItems = cvItems;
        originalLifeSkillsList = ogItems;
    }

    @NonNull
    @Override
    public ViewLifeSkillsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lifeskills_card_item, viewGroup, false);
        ViewLifeSkillsViewHolder viewLifeSkillsViewHolder = new ViewLifeSkillsViewHolder(v);
        return viewLifeSkillsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewLifeSkillsViewHolder viewHolder, int i)
    {
        cardViewItem2 cardViewItem = cardViewItems.get(i);
        viewHolder.imageView.setImageResource(cardViewItem.getImageResource());
        viewHolder.textView1.setText(cardViewItem.getLifeSkillName());
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
                    CheckBox checkBox = (CheckBox) v;

                    LifeSkillChecked changedLifeSkill = originalLifeSkillsList.get(pos);

                    if (checkBox.isChecked())
                    {
                        cardViewItems.get(i).setCompleted(true);
                        changedLifeSkill.setCompleted(true);

                        if (changedLifeSkillsList.contains(changedLifeSkill))
                        {

                        }
                        else
                        {
                            changedLifeSkillsList.add(changedLifeSkill);
                        }
                    }
                    else
                    {
                        cardViewItems.get(i).setCompleted(false);
                        changedLifeSkill.setCompleted(false);

                        if (changedLifeSkillsList.contains(changedLifeSkill))
                        {

                        }
                        else
                        {
                            changedLifeSkillsList.add(changedLifeSkill);
                        }
                    }
                }

            }

            @Override
            public void onDeleteClick(View v, int pos)
            {

            }
        });
    }


    @Override
    public int getItemCount()
    {
        return cardViewItems.size();
    }

    public ArrayList<cardViewItem2> getCardViewItems() {
        return cardViewItems;
    }

    public ArrayList<LifeSkillChecked> getChangedLifeSkillsList() {
        return changedLifeSkillsList;
    }

    public ArrayList<LifeSkillChecked> getOriginalLifeSkillsList() {
        return originalLifeSkillsList;
    }
}

