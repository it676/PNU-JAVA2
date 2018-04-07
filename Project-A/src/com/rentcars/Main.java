package com.rentcars;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Company company = new Company("Dreams For Cars Renting !" , "Riyadh");
        try {

            System.out.println("Load Cars Data...");
            company.loadCarsData("cars.txt");//read cars info from the file
            System.out.println("All cars Data has been loaded successfully !");
        }
        catch (FileNotFoundException ex){

            System.err.println("Error : Can't find the file , check the system.");

        }
        catch (IOException ex){

            System.err.println("Error : Can't read the data from the file");

        }


        Scanner input = new Scanner(System.in);
        int userChoice = 0 ;

        do {


            try {

                displayMenu();
                userChoice = input.nextInt();

                switch (userChoice){

                    case  1:

                        System.out.println("1- VIP Service");
                        System.out.println("2- Regular Service");
                        System.out.print(">> ");
                        int serviceOption = input.nextInt();

                        System.out.println("Select a Car From the Menu Please.");

                        int availableCars = company.displayAvailableCars();
                        if( availableCars > 0) {

                            System.out.print("Select Your Cars >> ");
                            int selectedCar = input.nextInt();
                            if (!company.isCarAvailable(selectedCar))
                                System.out.println("Sorry , car is not available");
                            else {
                                if (selectedCar < 0 || selectedCar > availableCars) {

                                    System.out.println("Invalid car choice !");
                                } else {

                                    System.out.println("Enter Your Info Please :");
                                    System.out.print("National ID : ");
                                    long id = input.nextLong();
                                    input.nextLine();
                                    System.out.print("Name : ");
                                    String name = input.nextLine();
                                    System.out.print("Phone: ");
                                    String phone = input.nextLine();

                                    //create the objects
                                    Customer customer = new Customer(id, name, phone);
                                    Car car = company.getCar(selectedCar);
                                    Date rentDate = Date.getTodayDate();
                                    Booking booking;
                                    if (serviceOption == 1) {

                                        System.out.print("Delivery Address: ");
                                        String address = input.nextLine();

                                        booking = new VIPBooking(customer, car, rentDate, address);
                                    } else {

                                        booking = new RegularBooking(customer, car, rentDate);
                                    }


                                    //save booking
                                    if (company.bookCar(booking)) {
                                        System.out.println("Booking has been created successfully !");
                                        System.out.println("Your Booking Details : ");
                                        booking.print();
                                        System.out.println("----------------Thank You-----------------");
                                    } else {
                                        System.err.println("Error during saving booking ! ");
                                    }
                                }
                            }
                        }
                        else
                            System.out.println("Sorry , No Cars Available , Comeback soon.");

                        break;//end case 1

                    case  2:
                        System.out.print("Enter Booking Id : ");
                        long bookingId = input.nextLong();
                        Booking booking = company.getBookingInfo(bookingId);
                        if(booking != null){

                            System.out.println("--------Your Booking Info---------- ");
                             booking.print();
                        }else{
                            System.out.println("Booking was not found !");
                        }

                        break;


                    case 3:
                        System.out.print("Enter Booking Id : ");
                        bookingId = input.nextLong();

                        company.returnCar(bookingId);
                        break;

                    case 4:
                        System.out.print("Enter Booking Id : ");
                        bookingId = input.nextLong();

                        if(company.cancelBooking(bookingId))
                            System.out.println("Booking has been cancelled !");
                        else
                            System.out.println("something wrong happened , check Booking id please");
                        break;

                    case 5:

                }
            }
            catch (InputMismatchException ex){

                System.err.println("Invalid Input , Please Enter");
            }

            catch (FileNotFoundException ex){

                System.err.println("Error : Can't Load Cars Data.");
            }
            catch (IOException ex){

                System.err.println("Error : Can't read the data from the file");
            }

        }while (userChoice !=5);

    }//end main


    public static void displayMenu(){
        System.out.println("-----------------------------------------");
        System.out.print("\nSelect One Of The Following :\n\n" +
                "1- Book New Car\n" +
                "2- Display Booking Details\n" +
                "3- Return Car\n" +
                "4- Cancel Booking\n" +
                "5- Exit\n" +
                "-----------------------------------------\n" +
                "Your Choice >>");


    }
}
