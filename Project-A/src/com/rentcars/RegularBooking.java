package com.rentcars;

public class RegularBooking  extends  Booking{


    public RegularBooking(){

        super();
    }


    public RegularBooking( Customer customer, Car car, Date rentDate) {

        super(customer, car, rentDate);
    }

    @Override
    public String getServiceType(){

        return "Regular Service ";
    }


    @Override
    public double calculatePayment() {

        int numberOfDays =Math.abs( Date.getTotalDays(this.getRentDate()));

        double carPricePerDay = this.getCar().getPricePerDay();
        double totalPrice  = numberOfDays * carPricePerDay;

        if (numberOfDays > 25) {
            //apply late tax by 5%

            double taxRate = 5; //5%
            double taxAmount = totalPrice * taxRate / 100;
            totalPrice = totalPrice + taxAmount;


        }

        return totalPrice;
    }
}
