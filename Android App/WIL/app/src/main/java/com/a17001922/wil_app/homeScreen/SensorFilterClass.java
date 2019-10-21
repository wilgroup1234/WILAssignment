package com.a17001922.wil_app.homeScreen;

// Used for Step-Tracker functionality
public class SensorFilterClass
{

    //_________________________code attribution_______________________
    // The code in this class was attributed from http://www.gadgetsaint.com:
    //Author : Anu S Pillai
    //Published March 30, 2017
    // Updated April 25, 2017
    // Link: http://www.gadgetsaint.com/android/create-pedometer-step-counter-android/#.W9CYIfaxXIw


    //This class is used to get and set values form the accelerometer sensor to track user's steps

    private SensorFilterClass()
    {
    }

    public static float sum(float[] array)
    {
        float retval = 0;
        for (int i = 0; i < array.length; i++)
        {
            retval += array[i];
        }
        return retval;
    }

    public static float[] cross(float[] arrayA, float[] arrayB)
    {
        float[] retArray = new float[3];
        retArray[0] = arrayA[1] * arrayB[2] - arrayA[2] * arrayB[1];
        retArray[1] = arrayA[2] * arrayB[0] - arrayA[0] * arrayB[2];
        retArray[2] = arrayA[0] * arrayB[1] - arrayA[1] * arrayB[0];
        return retArray;
    }

    public static float norm(float[] array)
    {
        float retval = 0;
        for (int i = 0; i < array.length; i++)
        {
            retval += array[i] * array[i];
        }
        return (float) Math.sqrt(retval);
    }


    public static float dot(float[] a, float[] b)
    {
        float retval = a[0] * b[0] + a[1] * b[1] + a[2] * b[2];
        return retval;
    }

    public static float[] normalize(float[] a)
    {
        float[] retval = new float[a.length];
        float norm = norm(a);
        for (int i = 0; i < a.length; i++)
        {
            retval[i] = a[i] / norm;
        }
        return retval;
    }

}