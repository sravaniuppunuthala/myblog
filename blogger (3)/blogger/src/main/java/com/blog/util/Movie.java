package com.blog.util;

public  class Movie {//comparable intruduced in 1.2

    public Movie(String name, int rating, int year) {
        this.name = name;
        this.rating = rating;
        this.year = year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setYear(int year) {
        this.year = year;
    }

    private String name;
    private int rating;
    private int year;

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public int getYear() {
        return year;
    }

   // @Override
   // public int compareTo(Movie o) {//this method compared objects based on movie name rate year
        // return this.year-o.year;//it works on +/_ value
       // return this.name.compareTo(o.name);//compare the strings,- compare integers
    //}

   // @Override
   // public int compareTo(Object o) {
    //    return 0;
    }





