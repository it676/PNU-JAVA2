package com.rentcars;

import java.util.ArrayList;
import  java.io.*;
import java.util.Scanner;

public class Company {

    private String name;
    private String city;
    private Car[] cars = new Car[10] ;//10 cars only for now
    private ArrayList<Booking> bookings ;

    public Company(){

        name="";
        city="";
        bookings=null;
    }

    public Company(String name , String city){
        this.name=name;
        this.city=city;
        bookings = new ArrayList<>();

    }

    public boolean isCarAvailable(int index){

        return cars[index].isAvailable();
    }

    public  boolean bookCar(Booking booking) throws FileNotFoundException {

        //set car to unavailable
        booking.getCar().setAvailable(false);
        updateAndSaveCarsInfo();//update data into the file
        return bookings.add(booking);

    }

    public boolean cancelBooking(long bookingID) throws FileNotFoundException {

        for(Booking b : bookings){

            if(b.getId() == bookingID) {
                bookings.remove(b);

                b.getCar().setAvailable(true);//now car is available
                updateAndSaveCarsInfo();//update data into the file


                return  true;
            }

        }

        return  false;
    }


    public Booking getBookingInfo(long bookingID){

        for(Booking b : bookings){

            if(b.getId() == bookingID) {


                return  b;
            }

        }

        return  null;

    }

    public  void returnCar(long bookingID) throws FileNotFoundException {

        for(Booking b : bookings){

            if(b.getId() == bookingID) {

                b.print();
                System.out.printf("Total Payment: %.2f%n",b.calculatePayment());
                System.out.println("----------------Thank You-----------------");

                b.getCar().setAvailable(true);//now car is available
                updateAndSaveCarsInfo();//update data into the file
                return;

            }
        }

        System.out.println("Invalid Booking ID !");



    }
    public  void printOrderDetails(long bookingID){

        for(Booking b : bookings) {

            if (b.getId() == bookingID) {

               b.print();
               return; //end

            }
        }

        //if booking not found
        System.out.println("Can't find this booking , try again !");

    }


    public void loadCarsData(String fileName) throws IOException {



        File file = new File(fileName);

        if(!file.exists()){
            file.createNewFile();
        }
        Scanner reader = new Scanner(file);

        reader.useDelimiter(",");

        int carsCounter = 0;

        while (reader.hasNext()){

            //read all car data from the file
            long id = reader.nextLong();
            String model = reader.next();
            String color = reader.next();
            double price = reader.nextDouble();
            boolean isAvailable = reader.nextBoolean();


            //create car object
            Car car = new Car(id,model,color,price,isAvailable);

            //add the car to the array
            cars[carsCounter++] = car;


        }

        reader.close();
    }


    public void updateAndSaveCarsInfo() throws FileNotFoundException{

        PrintWriter writer = new PrintWriter("cars.txt");

        for(Car car : cars){
            if(car !=null)
           writer.printf("%s,%s,%s,%s,%s,",
                   car.getId(),car.getModel(),car.getColor(),
                   car.getPricePerDay(),car.isAvailable());

        }

        writer.close();
    }


    public  int displayAvailableCars() throws IOException{

        loadCarsData("cars.txt");//load the updated cars data

        System.out.println("=================Cars List================");
        System.out.println("No  S.N    Model  Color    Price");
        System.out.println("===========================================");

        int availableCarsCounter =0;
        int index =0;
        for(Car car : cars){

            if( car != null && car.isAvailable() ) {


                System.out.println(index+"  "+car);
                System.out.println("-------------------------------------------");

                availableCarsCounter++;
            }

            index++;
        }

        return  availableCarsCounter;
    }

    public Car getCar(int index) {


         return cars[index];
    }
}
