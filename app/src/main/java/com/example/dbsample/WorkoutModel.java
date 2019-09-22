package com.example.dbsample;

public class WorkoutModel {

    private String title;
    private String sets;
    private  String reps;
    private String desc;

    WorkoutModel(String t, String s, String r, String d)
    {
        this.title = t;
        this.sets = s;
        this.reps = r;
        this.desc = d;
    }

    public  String getTitle()
    {
        return title;
    }

    public  String getSets()
    {
        return sets;
    }

    public  String getReps()
    {
        return reps;
    }

    public  String getDesc()
    {
        return desc;
    }

    public String toString()
    {
        return  this.title;
    }
}
