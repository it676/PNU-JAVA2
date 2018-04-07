package com.rentcars;

public class VIPBooking extends  Booking {

    private String address;

    public VIPBooking(){

        super();
    }

    public VIPBooking( Customer customer, Car car, Date rentDate, String address) {
        super(customer, car, rentDate);
        this.address = address;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String getServiceType(){

        return "VIP Service ";
    }

    @Override
    public double calculatePayment() {




            int numberOfDays =Math.abs( Date.getTotalDays(this.getRentDate()));

            double carPricePerDay = this.getCar().getPricePerDay();
            double basePrice = numberOfDays * carPricePerDay;

            double priceForVIPService = basePrice * 10 / 100; //10%

           double totalPrice  = basePrice + priceForVIPService;

            if (numberOfDays > 25) {
                //apply late tax by 10%

                    double taxRate = 10; //10%
                    double taxAmount = totalPrice * taxRate / 100;
                    totalPrice = totalPrice + taxAmount;


            }

            return totalPrice;
        }


}
