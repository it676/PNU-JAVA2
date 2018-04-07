package com.rentcars;

public class Car {

    private long id;
    private String model;
    private String color;
    private double pricePerDay;
    private boolean isAvailable;


    public Car(){
        id=0;
        model="";
        color="";
        pricePerDay=0.0;
        isAvailable=false;
    }

    public Car(long id, String model, String color, double pricePerDay , boolean isAvailable) {
        this.id = id;
        this.model = model;
        this.color = color;
        setPricePerDay( pricePerDay);
        this.isAvailable = isAvailable;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPricePerDay(double pricePerDay) {
        if(pricePerDay < 0)
            throw new IllegalArgumentException("Invalid Price !");
        this.pricePerDay = pricePerDay;
    }


    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }


    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public String toString(){

        return  String.format("%d , %s , %s , %.2f%n",
                getId(),getModel(),getColor(),getPricePerDay());
    }
}
