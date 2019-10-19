package com.a17001922.wil_app.homeScreen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;

import java.util.Random;

public class Game extends View
{
    //_____________Declarations_________________
    private static final String TAG = "GameActivity";

    //Bitmaps
    private Bitmap background, backgroundlong;
    private Bitmap [] dog = new Bitmap[5];
    private Bitmap [] lives = new Bitmap[2];
    private Bitmap bomb, biscuit;

    //Dog
    private int minDogY, maxDogY = 0;
    private int dogY = 20;
    private int dogX = 50;
    private int dogSpeed = 8;

    //Biscuit
    private int biscuitY = 600;
    private int biscuitX = 2000;
    private int biscuitSpeed = 7;

    //Bomb
    private int bombY = 400;
    private int bombX = 2000;
    private int bombSpeed = 9;

    //other
    private boolean touched = false;
    private int currentLives = 3, currentScore = 0;
    private String screenSize;
    private Paint score,finish;
    boolean run = true;
    int currentDogPosition = 1;
    int runCount = 0;
    int jumptimer = 0;



    public Game(Context context)
    {
        super(context);

        //________________Initialising Bitmaps_________________

        background = BitmapFactory.decodeResource(getResources(),R.drawable.normalbackground);
        backgroundlong = BitmapFactory.decodeResource(getResources(),R.drawable.longbackground);
        dog[0] = BitmapFactory.decodeResource(getResources(),R.drawable.run1);
        dog[1] = BitmapFactory.decodeResource(getResources(),R.drawable.run2);
        dog[2] = BitmapFactory.decodeResource(getResources(),R.drawable.run3);
        dog[3] = BitmapFactory.decodeResource(getResources(),R.drawable.run4);
        dog[4] = BitmapFactory.decodeResource(getResources(),R.drawable.jump);
        lives[0] = BitmapFactory.decodeResource(getResources(),R.drawable.alive);
        lives[1] = BitmapFactory.decodeResource(getResources(),R.drawable.dead);
        biscuit = BitmapFactory.decodeResource(getResources(),R.drawable.dogbiscuit);
        bomb = BitmapFactory.decodeResource(getResources(),R.drawable.bombicon);


        //_________________Setting up Paints_________________
        score = new Paint();
        score.setColor(Color.BLACK);
        score.setTypeface(Typeface.DEFAULT_BOLD);
        score.setTextSize(64);
        score.setAntiAlias(true);

        finish = new Paint();
        finish.setColor(Color.BLACK);
        finish.setTypeface(Typeface.DEFAULT_BOLD);
        finish.setTextSize(100);
        finish.setAntiAlias(true);
    }


    @Override
    protected void onDraw(Canvas canvas)
    {

        super.onDraw(canvas);

        //get height and width of canvas
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        //______________________Set Screen Size And Y Boundary________________________
        SetScreenSizeAndYBoundary(canvasHeight);

        //______________________Move Dog________________________
        MoveDog();

        //______________________Draw Background_______________________

        switch(screenSize)
        {
            //draw background for normal 1080p screen height (level 1)
            case "normal" :
                canvas.drawBitmap(background, 0,0, null);
                break;

            //draw background for tall 1080p screen height (level 1)
            case "tall" :
                canvas.drawBitmap(backgroundlong, 0,0, null);
                break;

        }


        //______________________Make Dog Jump if screen is touched_______________________
        if (touched)
        {
            //draw dog jumping
            canvas.drawBitmap(dog[4], dogX, dogY, null);
            jumptimer = 20;
            touched = false;
        }


        //______________________display score at the top of the screen_______________________
        canvas.drawText("Score: " + currentScore,20,60, score);


        //______________________Set Co-ordinates for balls and keep ball within bounds______________________

        SetBiscuitAndBombCoordinates(canvasWidth);


        //______________________Draw Biscuit, Bomb and Dog______________________
        canvas.drawBitmap(biscuit, biscuitX, biscuitY, null);
        canvas.drawBitmap(bomb, bombX, bombY, null);



        if(jumptimer == 0)
        {
            switch(currentDogPosition)
            {
                case 1:
                    canvas.drawBitmap(dog[0], dogX, dogY, null);
                    break;
                case 2:
                    canvas.drawBitmap(dog[1], dogX, dogY, null);
                    break;
                case 3:
                    canvas.drawBitmap(dog[2], dogX, dogY, null);
                    break;
                case 4:
                    canvas.drawBitmap(dog[3], dogX, dogY, null);
                    break;
            }
        }
        else
        {
            canvas.drawBitmap(dog[4], dogX, dogY, null);
            jumptimer --;
        }




    //______________________Check if dog hit Biscuit______________________
        if (hitCheck(biscuitX, biscuitY))
        {
            //add score
            currentScore += 10;

            //increase speed of biscuit and bomb
            biscuitSpeed ++;
            bombSpeed ++;

            //reset position of blue ball;
            biscuitX = canvasWidth + 20;
            int random = new Random().nextInt((maxDogY+1)) + minDogY;
            biscuitY = random;
        }



        //______________________Check if dog hit Bomb______________________
        if (hitCheck(bombX, bombY))
        {
            //take away life
            currentLives--;

            //reset position of black ball;
            bombX = canvasWidth + 20;
            int random = new Random().nextInt((maxDogY+1)) + minDogY;
            bombY = random;
        }





        //____________________Update lives____________________
        switch(currentLives)
        {
            case 0:
                //if all lives are lost

                canvas.drawBitmap(lives[1], 800,0, null);
                canvas.drawBitmap(lives[1], 900,0, null);
                canvas.drawBitmap(lives[1], 1000,0, null);


                //stop everything on screen
                StopAnimation();
                //end game;
                run = false;

                break;
            case 1:
                //if 1 life is left

                canvas.drawBitmap(lives[0], 800,0, null);
                canvas.drawBitmap(lives[1], 900,0, null);
                canvas.drawBitmap(lives[1], 1000,0, null);

                break;
            case 2:
                //if 2 lives are left

                canvas.drawBitmap(lives[0], 800,0, null);
                canvas.drawBitmap(lives[0], 900,0, null);
                canvas.drawBitmap(lives[1], 1000,0, null);

                break;
            case 3:
                //if all lives are left

                canvas.drawBitmap(lives[0], 800,0, null);
                canvas.drawBitmap(lives[0], 900,0, null);
                canvas.drawBitmap(lives[0], 1000,0, null);

                break;
        }


        //____________________Check if Game is over and display result____________________


        if (!run)
        {
            StaticClass.playerScore = currentScore;
            StaticClass.gameOver = true;
        }

    }


    public void SetScreenSizeAndYBoundary(int canvasHeight)
    {
        if (canvasHeight > 1920)
        {
            screenSize = "tall";
        }
        else
        {
            screenSize = "normal";
        }

        //setting boundaries
        minDogY = dog[0].getHeight()/2;
        maxDogY = (canvasHeight - dog[0].getHeight()*2);
    }


    public void SetBiscuitAndBombCoordinates(int canvasWidth)
    {
        //______________________Set Co-ordinates for Biscuit______________________
        if (biscuitX <= 0)
        {
            //reset ball position if it hits the end of the screen
            biscuitX = canvasWidth + 20;
            int random = new Random().nextInt(maxDogY + 1) + minDogY;
            biscuitY = random;

        }
        else
        {
            //change ball speed
            biscuitX -= biscuitSpeed;
        }

        //______________________Set Co-ordinates for Bomb______________________
        if (bombX <= 0)
        {
            //reset ball position if it hits the end of the screen
            bombX = canvasWidth + 20;
            int random = new Random().nextInt((maxDogY + 1)) + minDogY;
            bombY = random;

        }
        else
        {
            //change ball speed
            bombX -= bombSpeed;

        }

        runCount++;

        if(runCount == 8)
        {
            //Change dog walking image
            if(currentDogPosition == 4)
            {
                currentDogPosition = 1;
            }
            else
            {
                currentDogPosition ++;
            }

            runCount = 0;
        }



    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            //when screen is touched
            case MotionEvent.ACTION_DOWN:

                if (run)
                {
                    touched = true;
                    //Dog must jump

                    dogSpeed = -25;

                }

                break;


        }
        return true;
    }


    public void MoveDog()
    {
        //change dog height

        dogY += dogSpeed;


        //Keep bird within bounds
        if (dogY < minDogY) dogY = minDogY;
        if (dogY > maxDogY) dogY = maxDogY;



        dogSpeed +=2;

    }


    public void StopAnimation()
    {
        biscuitSpeed = 0;
        bombSpeed = 0;
        dogSpeed = 0;
    }

    //Check if the biscuit or bomb hits the dog
    public boolean hitCheck (int x, int y)
    {
        boolean hit = false;

        if ((dogX < x && x < dogX + dog[0].getWidth()) &&  (dogY < y && y < dogY + dog[0].getHeight()))
        {
            hit = true;
        }

        return hit;
    }

}