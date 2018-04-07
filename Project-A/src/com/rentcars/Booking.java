package com.rentcars;



public abstract class Booking implements Payable {
    private long id;
    private static long bookingCounter = 1001;
    private Customer customer;
    private Car car;
    private Date rentDate;

    public Booking(){

        id=0;
        customer=null;
        car=null;
        rentDate=null;
    }

    public Booking(Customer customer, Car car, Date rentDate) {
        this.id = bookingCounter++;
        this.customer = customer;
        this.car = car;
        this.rentDate = rentDate;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }


    public abstract String getServiceType();


    public  void  print(){

     System.out.println("================Booking Info=================");

     System.out.println("Booking ID : "+getId());

    System.out.println("Rent Date : "+getRentDate());

    System.out.println("Customer Name: "+customer.getName());
    System.out.println("Customer Phone No: "+customer.getPhone());

        System.out.println("Car Model : "+car.getModel());
        System.out.println("Car Color : "+car.getColor());


    if(this instanceof  VIPBooking)
        System.out.println("Address : "+((VIPBooking)this).getAddress());

    System.out.println("Service Type : "+getServiceType());//polymorphism
        System.out.println();


}

}
