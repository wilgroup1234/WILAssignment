package com.a17001922.wil_app.homeScreen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;
import com.a17001922.wil_app.R;

//This class manages the Loading Screen Animations
public class LoadingScreen extends View
{

    //_____________Declarations_________________
    private static final String TAG = "LoadingScreenActivity";

    //Bitmaps
    private Bitmap background;
    private Bitmap [] dog = new Bitmap[8];

    //Dog
    private int dogY = 600;
    private int dogX = 80;

    //other
    private Paint loading;
    int currentDogPosition = 0;
    int count = 0;

    public LoadingScreen(Context context)
    {
        super(context);

        //________________Initialising Bitmaps_________________

        background = BitmapFactory.decodeResource(getResources(), R.drawable.bgl);
        dog[0] = BitmapFactory.decodeResource(getResources(),R.drawable.t1);
        dog[1] = BitmapFactory.decodeResource(getResources(),R.drawable.t2);
        dog[2] = BitmapFactory.decodeResource(getResources(),R.drawable.t3);
        dog[3] = BitmapFactory.decodeResource(getResources(),R.drawable.t4);
        dog[4] = BitmapFactory.decodeResource(getResources(),R.drawable.t5);
        dog[5] = BitmapFactory.decodeResource(getResources(),R.drawable.t6);
        dog[6] = BitmapFactory.decodeResource(getResources(),R.drawable.t7);
        dog[7] = BitmapFactory.decodeResource(getResources(),R.drawable.t8);

        //_________________Setting up Paints_________________
        loading = new Paint();
        loading.setColor(Color.BLACK);
        loading.setTypeface(Typeface.DEFAULT_BOLD);
        loading.setTextSize(115);
        loading.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas)
    {

        super.onDraw(canvas);

        //______________________Draw Background_______________________

        try
        {
            canvas.drawBitmap(background, 0,0, null);
        }
        catch(Exception e)
        {

        }

        try
        {
            //______________________display loading at the top of the screen_______________________
            canvas.drawText("Loading...",(canvas.getWidth()/2) - 200,430, loading);

            canvas.drawBitmap(dog[currentDogPosition], dogX, dogY, null);
            dogX = dogX + 1;

            if(dogX >= canvas.getWidth() - 20)
            {
                dogX = 80;
            }

            if(count > 5)
            {
                count = 0;


                if(currentDogPosition == 7)
                {
                    currentDogPosition = 0;
                }
                else
                {
                    currentDogPosition++;
                }




            }
            else
            {
                count++;
            }

        }
        catch(Exception e)
        {

        }



    }


}
