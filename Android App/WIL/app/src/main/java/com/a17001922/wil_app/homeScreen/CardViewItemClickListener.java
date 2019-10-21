package com.a17001922.wil_app.homeScreen;

import android.view.View;

//This interface is used for recycler view items.
//The method is used when items on the recycler view are clicked
public interface CardViewItemClickListener
{
   void onItemClick(View v, int pos);
}
