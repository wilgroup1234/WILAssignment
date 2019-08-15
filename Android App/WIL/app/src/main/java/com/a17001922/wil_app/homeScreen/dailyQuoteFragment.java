package com.a17001922.wil_app.homeScreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.a17001922.wil_app.Connection;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.dailyQuote.DailyObject;


public class dailyQuoteFragment extends Fragment
{
        TextView lblDailyQuote,lblDailyYoutubeLink;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_daily_quote,container,false);
        Connection con = new Connection();
        lblDailyQuote=v.findViewById(R.id.lblDailyQuote);
        lblDailyYoutubeLink=v.findViewById(R.id.lblDailyYoutubeLink);
        DailyObject Quote;
        int templateID;

        try
        {
             Quote = con.getDailyQuote();
             templateID=Quote.getTemplateID();
             lblDailyQuote.setText(Quote.getQuoteText());
             lblDailyYoutubeLink.setText(Quote.getYoutubeLink());
        }
        catch (NullPointerException e)
        {
            Toast.makeText(getActivity().getApplicationContext(),"UNABLE TO GET DAILY QUOTE" , Toast.LENGTH_LONG).show();
        }

        return v;
    }

}
