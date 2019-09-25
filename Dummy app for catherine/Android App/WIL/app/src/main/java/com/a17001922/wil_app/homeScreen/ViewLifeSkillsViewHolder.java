package com.a17001922.wil_app.homeScreen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.a17001922.wil_app.R;

public class ViewLifeSkillsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public ImageView imageView;
    public TextView textView1;
    public CheckBox checkBox;

    CardViewItemClickListener itemClickListener;

    public ViewLifeSkillsViewHolder(@NonNull View itemView)
    {
        super(itemView);
        imageView = itemView.findViewById(R.id.imgIconCardView2);
        textView1 = itemView.findViewById(R.id.txtLifeSkillNameCardView);
        checkBox = itemView.findViewById(R.id.cBoxIsCompleted2);

        checkBox.setOnClickListener(this);

    }

    public void setItemClickListener(CardViewItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v)
    {
        this.itemClickListener.onItemClick(v, getLayoutPosition());
    }
}