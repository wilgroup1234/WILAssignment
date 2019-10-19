package com.a17001922.wil_app.homeScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.a17001922.wil_app.LoginScreen.ReturnMessageObject;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.dailyQuote.DailyObject;
import com.a17001922.wil_app.goals.goalsService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class dailyQuoteFragment extends Fragment
{
    //_____________Declarations_________________
    TextView lblDailyQuote;
    ImageView DailyImage;
    DailyObject Quote;
    int templateID;
    private final String TAG = "Daily Quote Page";
    View v;
    Button btnLink;
    String link = "https://youtu.be/oLbZTyDyssg";
    Uri uri;
    SharedPreferences sharedPreferences;

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
        DailyImage = v.findViewById(R.id.imgDailyImage);
        btnLink = v.findViewById(R.id.btnOpenLink);
        sharedPreferences = StaticClass.homeContext.getSharedPreferences(StaticClass.SHARED_PREFS, MODE_PRIVATE);


        try
        {
            Quote = new DailyObject();
            Quote.setQuoteText(sharedPreferences.getString(StaticClass.USER_DAILYQUOTETEXT, ""));

            try
            {
                Quote.setTemplateID(Integer.parseInt(sharedPreferences.getString(StaticClass.USER_DAILYQUOTEImage, "")));
            }
            catch(Exception e)
            {

            }

            Quote.setYoutubeLink(sharedPreferences.getString(StaticClass.USER_DAILYQUOTELINK, ""));

            templateID = Quote.getTemplateID();

            String uri = "i" + templateID;  // where my resource (without the extension) is the file

            int imageId = getResources().getIdentifier(uri, "drawable", StaticClass.homeContext.getPackageName());

            DailyImage.setImageResource(imageId);

            lblDailyQuote.setText(Quote.getQuoteText());
            link = Quote.getYoutubeLink();
        }
        catch(Exception e)
        {
            Log.e(TAG, "Exception caught: " + e.getMessage());
        }



        btnLink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    uri = Uri.parse(link);
                    Intent intentFIVE = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intentFIVE);

                    if(StaticClass.hasInternet)
                    {
                        try
                        {

                            final goalsService service = StaticClass.retrofit.create(goalsService.class);
                            final Call<ReturnMessageObject> updateVideoViews = service.updateViews();
                            updateVideoViews.enqueue(new Callback<ReturnMessageObject>()
                            {
                                @Override
                                public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response)
                                {
                                    if (!response.isSuccessful())
                                    {
                                        ReturnMessageObject returnObject = response.body();

                                        if(returnObject.getResult())
                                        {
                                            Log.e(TAG, "Views updated :)");
                                        }
                                        else
                                        {
                                            Log.e(TAG, "Error updating views :(");
                                        }

                                    }
                                }

                                @Override
                                public void onFailure(Call<ReturnMessageObject> call, Throwable t)
                                {
                                    Log.e(TAG, "Can't Connect  to update views :(");
                                }
                            });
                        }
                        catch (Exception e)
                        {
                            Log.e(TAG, "Exception caught while trying to update views: " + e.getMessage());
                        }
                    }

                }
                catch(Exception e)
                {
                    Toast.makeText(StaticClass.homeContext, "Invalid Youtube Link provided :(", Toast.LENGTH_SHORT);
                    Log.e(TAG, "Invalid Youtube Link provided :( ");
                }








            }
        });



    }


}
