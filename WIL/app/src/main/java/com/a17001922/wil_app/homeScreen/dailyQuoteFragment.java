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
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.dailyQuote.DailyObject;
import com.a17001922.wil_app.dailyQuote.DailyQuoteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class dailyQuoteFragment extends Fragment {
    TextView lblDailyQuote, lblDailyYoutubeLink;
    DailyObject Quote;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_daily_quote, container, false);
        Connection con = new Connection();
        lblDailyQuote = v.findViewById(R.id.lblQuote);
        lblDailyYoutubeLink = v.findViewById(R.id.lbl_QuoteYoutubeLink);

        int templateID;

        try {
            Quote = new DailyObject();
            DailyQuoteService service = StaticClass.retrofit.create(DailyQuoteService.class);
            final Call<DailyObject> quoteCall = service.getQuote();
            quoteCall.enqueue(new Callback<DailyObject>() {
                @Override
                public void onResponse(Call<DailyObject> call, Response<DailyObject> response) {
                    if (!response.isSuccessful()) {

                    } else {
                        Quote = response.body();
                        if(Quote== null){
                            Toast.makeText(getActivity().getApplicationContext(), "Unfortunately we had an error retrieving the daily goal", Toast.LENGTH_SHORT).show();
                        }else{
                            lblDailyQuote.setText(Quote.getQuoteText()+"\n Template ID: "+Quote.getTemplateID());
                            lblDailyYoutubeLink.setText(Quote.getYoutubeLink());
                        }
                    }
                }

                @Override
                public void onFailure(Call<DailyObject> call, Throwable t) {
                    Toast.makeText(getActivity().getApplicationContext(), "Response from API failed", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (NullPointerException e) {
            Toast.makeText(getActivity().getApplicationContext(), "UNABLE TO GET DAILY QUOTE", Toast.LENGTH_LONG).show();
        }


        return v;
    }

}
