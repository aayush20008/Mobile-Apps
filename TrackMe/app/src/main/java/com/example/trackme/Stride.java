package com.example.trackme;

public class Stride {
    public float StrideLength(float stepDur, float stepCount,float height){
        float l = 0;
        l = (stepDur / stepCount) * height;
        return l;
    }
    public float StrideLength(float height){
        float l;
        l = (float) (height * 0.415);
        return l;
    }
}
