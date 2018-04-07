package com.rentcars;

import java.util.Calendar;

public class Date {

    private int day;
    private int month;
    private int year;


    public Date(){
        day =0;
        month = 0;
        year=0;
    }


    public Date(int day, int month, int year) {
        setDay(day);
        setMonth(month);
        setYear( year);
    }


    public void setDay(int day)  {
        if(day <1 || day>31)
            System.out.println("invalid Day");

        this.day = day;
    }

    public void setMonth(int month) {
        if(month <1 || month>12)
            throw  new IllegalArgumentException("Invalid month value !");

        this.month = month;
    }

    public void setYear(int year) {

        if(year < 2018)
            throw  new IllegalArgumentException("Invalid year value !");

        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }


    public static Date getTodayDate(){

        //getting current date
        Calendar cal = Calendar.getInstance();

        int y = cal.get(Calendar.YEAR);
        int m = 1+cal.get(Calendar.MONTH); // we increased the month by 1 since it starts from 0
        int d = cal.get(Calendar.DAY_OF_MONTH);

          return new Date(d,m,y);

    }

    public static int getTotalDays(Date rentDate) {



        Date currentDate = getTodayDate();

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        start.set(currentDate.getYear(), currentDate.getMonth(), currentDate.getDay());
        end.set(rentDate.getYear(), rentDate.getMonth(), rentDate.getDay());

        java.util.Date startDate = start.getTime();
        java.util.Date endDate = end.getTime();



        long startTime = startDate.getTime();
        long endTime = endDate.getTime();



        long diffTime = endTime - startTime;
        long diffDays = diffTime / (1000 * 60 * 60 * 24);//convert to days

        return (int) diffDays;

    }

    @Override
    public String toString(){

        return  String.format("%02d/%02d/%04d",
                getDay(),getMonth(),getYear());
    }
}
