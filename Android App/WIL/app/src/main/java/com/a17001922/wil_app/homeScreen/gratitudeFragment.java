package com.a17001922.wil_app.homeScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
    //_____________Declarations_________________
    Button btnSave;
    View v;
    EditText txtItem1, txtItem2, txtItem3, txtItem4, txtItem5;
    String items = "";
    String item1 = "", item2 = "", item3 = "", item4 = "", item5 = "";
    private static final String TAG = "Gratitude Fragment";
    goalsService goalService = StaticClass.retrofit.create(goalsService.class);
    ProgressBar progressBar;

    //____________________OnCreate Method_____________
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_gratitude, container,false);
        return v;
    }


    //____________________OnStart Method_____________
    @Override
    public void onStart()
    {
        super.onStart();

        //_____________Binding fields and widgets_____________
        btnSave = v.findViewById(R.id.btnSaveGratitude);
        txtItem1 = v.findViewById(R.id.et_Item1);
        txtItem2 = v.findViewById(R.id.et_Item2);
        txtItem3 = v.findViewById(R.id.et_Item3);
        txtItem4 = v.findViewById(R.id.et_Item4);
        txtItem5 = v.findViewById(R.id.et_Item5);
        progressBar = v.findViewById(R.id.pBarGratitude);
        progressBar.setVisibility(View.INVISIBLE);

        // Call method to show user's gratitude items for today
        StaticClass.ongoingOperation = true;
        progressBar.setVisibility(View.VISIBLE);
        GetGratitudeItems();


        //_____________Save button Click Event Listener_____________
        btnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!StaticClass.ongoingOperation)
                {
                    StaticClass.ongoingOperation = true;
                    progressBar.setVisibility(View.VISIBLE);

                    boolean valid = false;
                    String errorMessage = "Please enter at least one item...";

                    try
                    {
<<<<<<< HEAD
                        item1 = txtItem1.getText().toString().trim();
=======
                        item1 = txtItem1.getText().toString();
>>>>>>> 60b1129e6204b34ad62628cd4a985299d2b96359
                    }
                    catch(Exception e)
                    {

                    }

                    try
                    {
<<<<<<< HEAD
                        item2 = txtItem2.getText().toString().trim();
=======
                        item2 = txtItem2.getText().toString();
>>>>>>> 60b1129e6204b34ad62628cd4a985299d2b96359
                    }
                    catch(Exception e)
                    {

                    }

                    try
                    {
<<<<<<< HEAD
                        item3 = txtItem3.getText().toString().trim();
=======
                        item3 = txtItem3.getText().toString();
>>>>>>> 60b1129e6204b34ad62628cd4a985299d2b96359
                    }
                    catch(Exception e)
                    {

                    }

                    try
                    {
<<<<<<< HEAD
                        item4 = txtItem4.getText().toString().trim();
=======
                        item4 = txtItem4.getText().toString();
>>>>>>> 60b1129e6204b34ad62628cd4a985299d2b96359
                    }
                    catch(Exception e)
                    {

                    }

                    try
                    {
<<<<<<< HEAD
                        item5 = txtItem5.getText().toString().trim();
=======
                        item5 = txtItem5.getText().toString();
>>>>>>> 60b1129e6204b34ad62628cd4a985299d2b96359
                    }
                    catch(Exception e)
                    {

                    }

                    if (item1.length()> 1 || item2.length() > 1 || item3.length() > 1 || item4.length() > 1 || item5.length() > 1)
                    {
                        valid = true;
                    }

                    if(valid)
                    {
                        items = item1 + "#" + item2 + "#" + item3 + "#" + item4 + "#" + item5;

                        UpdateGratitude();
                    }
                    else
                    {
                        Toast.makeText(StaticClass.homeContext, errorMessage ,Toast.LENGTH_SHORT).show();
                        Log.e(TAG, errorMessage);
                        StaticClass.ongoingOperation = false;
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                }
                else
                {
                    Toast.makeText(StaticClass.loginContext, "Please Wait...", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    //____This method get's a user's gratitude items for today_________
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


                        StaticClass.ongoingOperation = false;
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                    catch(Exception e)
                    {
                        StaticClass.ongoingOperation = false;
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                }

                @Override
                public void onFailure(Call<GratitudeObject> call, Throwable t)
                {
                    Log.e(TAG, "Connection onFailure Get user gratitude");
                    StaticClass.ongoingOperation = false;
                    progressBar.setVisibility(View.INVISIBLE);
                }


            });

        }
        catch (Exception e)
        {
            Log.e(TAG, "Error retrieving gratitude: " + e.toString());
            txtItem1.setText("Cannot Get gratitude items - No Internet connection :(");
            StaticClass.ongoingOperation = false;
            progressBar.setVisibility(View.INVISIBLE);
        }
    }


    //_____This method updates a user's gratitude items for Today_____
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
                        StaticClass.ongoingOperation = false;
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        Log.e(TAG, " Update Gratitude items Failed :(");
                        StaticClass.ongoingOperation = false;
                        progressBar.setVisibility(View.INVISIBLE);
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
            StaticClass.ongoingOperation = false;
            progressBar.setVisibility(View.INVISIBLE);
        }
    }


}
