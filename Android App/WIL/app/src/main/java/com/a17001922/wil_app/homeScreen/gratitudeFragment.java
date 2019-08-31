package com.a17001922.wil_app.homeScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.a17001922.wil_app.LoginScreen.ReturnMessageObject;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.goals.goalsService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class gratitudeFragment extends Fragment
{
    Button btnSave;
    View v;
    EditText txtItem1, txtItem2, txtItem3, txtItem4, txtItem5;
    String items = "";
    String item1 = "", item2 = "", item3 = "", item4 = "", item5 = "";
    private static final String TAG = "Gratitude Fragment";
    goalsService goalService = StaticClass.retrofit.create(goalsService.class);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_gratitude, container,false);
        return v;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        btnSave = v.findViewById(R.id.btnSaveGratitude);
        txtItem1 = v.findViewById(R.id.et_Item1);
        txtItem2 = v.findViewById(R.id.et_Item2);
        txtItem3 = v.findViewById(R.id.et_Item3);
        txtItem4 = v.findViewById(R.id.et_Item4);
        txtItem5 = v.findViewById(R.id.et_Item5);


        GetGratitudeItems();


        btnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                  item1 = txtItem1.getText().toString();
                }
                catch(Exception e)
                {

                }

                try
                {
                    item2 = txtItem2.getText().toString();
                }
                catch(Exception e)
                {

                }

                try
                {
                    item3 = txtItem3.getText().toString();
                }
                catch(Exception e)
                {

                }

                try
                {
                    item4 = txtItem4.getText().toString();
                }
                catch(Exception e)
                {

                }

                try
                {
                    item5 = txtItem5.getText().toString();
                }
                catch(Exception e)
                {

                }

                items = item1 + "#" + item2 + "#" + item3 + "#" + item4 + "#" + item5;

                UpdateGratitude();

            }
        });

    }


    public void GetGratitudeItems()
    {
        GratitudeObject gratitudeObject = new GratitudeObject();
        gratitudeObject.setEmail(StaticClass.currentUser);

        try
        {
            final Call<GratitudeObject> gratitudeCall = goalService.getUserGratitude(gratitudeObject);
            gratitudeCall.enqueue(new Callback<GratitudeObject>()
            {
                @Override
                public void onResponse(Call<GratitudeObject> call, Response<GratitudeObject> response)
                {
                    try
                    {
                        String[] items;

                        GratitudeObject returnGratitude = response.body();
                        String gratItems = returnGratitude.getItems();

                        if (!gratItems.equals("No_Items"))
                        {
                            items = gratItems.split("#");

                            try
                            {
                                txtItem1.setText(items[0]);
                            }
                            catch(Exception e)
                            {

                            }

                            try
                            {
                                txtItem2.setText(items[1]);
                            }
                            catch(Exception e)
                            {

                            }

                            try
                            {
                                txtItem3.setText(items[2]);
                            }
                            catch(Exception e)
                            {

                            }

                            try
                            {
                                txtItem4.setText(items[3]);
                            }
                            catch(Exception e)
                            {

                            }

                            try
                            {
                                txtItem5.setText(items[4]);
                            }
                            catch(Exception e)
                            {

                            }
                        }
                    }
                    catch(Exception e)
                    {

                    }






                }

                @Override
                public void onFailure(Call<GratitudeObject> call, Throwable t)
                {
                    Log.e(TAG, "Connection onFailure Get user gratitude");
                }


            });

        }
        catch (Exception e)
        {
            Log.e(TAG, "Error retrieving gratitude: " + e.toString());
            txtItem1.setText("Cannot Get gratitude items - No Internet connection :(");
        }
    }


    public void UpdateGratitude()
    {
        GratitudeObject gratitudeObject = new GratitudeObject();
        gratitudeObject.setEmail(StaticClass.currentUser);
        gratitudeObject.setItems(items);

        try
        {
            final Call<ReturnMessageObject> updateGratitudeCall = goalService.updateGratitude(gratitudeObject);
            updateGratitudeCall.enqueue(new Callback<ReturnMessageObject>()
            {
                @Override
                public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response)
                {

                    ReturnMessageObject returnMessage = response.body();

                    if (returnMessage.getResult())
                    {
                        Toast.makeText(getActivity().getApplicationContext(), "Gratitude items updated", Toast.LENGTH_LONG).show();
                        Log.e(TAG, "Gratitude items Updated :)");
                    }
                    else
                    {
                        Log.e(TAG, " Update Gratitude items Failed :(");
                    }

                }

                @Override
                public void onFailure(Call<ReturnMessageObject> call, Throwable t)
                {
                    Log.e(TAG, "Connection onFailure Update user gratitude items");
                }


            });

        }
        catch (Exception e)
        {
            Log.e(TAG, "Error updating gratitude items: " + e.toString());
            txtItem1.setText("Cannot update gratitude items - No Internet connection :(");
        }
    }


}
