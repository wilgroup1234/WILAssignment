package com.a17001922.wil_app.homeScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.dailyQuote.DailyObject;
import com.a17001922.wil_app.dailyQuote.DailyQuoteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class dailyQuoteFragment extends Fragment
{
    //_____________Declarations_________________
    TextView lblDailyQuote, lblDailyYoutubeLink;
    ImageView DailyImage;
    DailyObject Quote;
    int templateID;
    private final String TAG = "Daily Quote Page";
    View v;

    //____________________OnCreate Method_____________
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_daily_quote, container, false);
        return v;
    }


    //____________________OnStart Method_____________
    @Override
    public void onStart()
    {
        super.onStart();

        //_____________Binding fields and widgets_____________
        lblDailyQuote = v.findViewById(R.id.lblQuote);
        lblDailyYoutubeLink = v.findViewById(R.id.lbl_QuoteYoutubeLink);
        DailyImage = v.findViewById(R.id.imgDailyImage);

        if(StaticClass.hasInternet)
        {
            try
            {
                Quote = new DailyObject();
                DailyQuoteService service = StaticClass.retrofit.create(DailyQuoteService.class);
                final Call<DailyObject> quoteCall = service.getQuote();
                quoteCall.enqueue(new Callback<DailyObject>()
                {
                    @Override
                    public void onResponse(Call<DailyObject> call, Response<DailyObject> response)
                    {
                        if (!response.isSuccessful())
                        {

                        }
                        else
                        {
                            Quote = response.body();
                            if(Quote == null)
                            {
                                Toast.makeText(StaticClass.homeContext, "Unfortunately we had an error retrieving the daily quote", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {

                                try
                                {
                                    templateID = Quote.getTemplateID();

                                    String uri = "i" + templateID;  // where my resource (without the extension) is the file

                                    int imageId = getResources().getIdentifier(uri, "drawable", StaticClass.homeContext.getPackageName());

                                    DailyImage.setImageResource(imageId);

                                    lblDailyQuote.setText(Quote.getQuoteText());
                                    lblDailyYoutubeLink.setText(Quote.getYoutubeLink());
                                }
                                catch(Exception e)
                                {
                                    Log.e(TAG, "Exception caught: " + e.getMessage());
                                }

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DailyObject> call, Throwable t)
                    {
                        Toast.makeText(StaticClass.homeContext, "Response from API failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            catch (NullPointerException e)
            {
                Toast.makeText(StaticClass.homeContext, "UNABLE TO GET DAILY QUOTE", Toast.LENGTH_LONG).show();
            }
        }
    }


}
