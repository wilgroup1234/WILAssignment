package com.a17001922.wil_app.homeScreen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;

public class ViewGoalsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public ImageView imageView;
    public TextView textView1;
    public TextView textView2;
    public TextView textView3;
    public CheckBox checkBox;
    public ImageView deleteButton;

    CardViewItemClickListener itemClickListener;

    public ViewGoalsViewHolder(@NonNull View itemView)
    {
        super(itemView);
        imageView = itemView.findViewById(R.id.imgIconCardView);
        textView1 = itemView.findViewById(R.id.txtGoalNameCardView);
        textView2 = itemView.findViewById(R.id.txtGoalDescriptionCardView);
        textView3 = itemView.findViewById(R.id.txtGoalDateCardView);
        checkBox = itemView.findViewById(R.id.cBoxIsCompleted);
        deleteButton = itemView.findViewById(R.id.imgDelete);


        checkBox.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
    }

    public void setItemClickListener(CardViewItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v)
    {
        this.itemClickListener.onItemClick(v, getLayoutPosition());

        this.itemClickListener.onDeleteClick(v, getLayoutPosition());
    }
}