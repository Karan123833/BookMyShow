package org.example;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Seat> seats = new ArrayList<>();
        for(int i=1;i<=10;i++) {
            seats.add(new Seat(i, "A" + i, i));
        }

        Screen screen = new Screen(1,new ArrayList<>());
        Show morningShow = new Show(101, new Movie(1,"DEKHA TUHE SANAM","English"), seats);
        screen.addShow(morningShow);

        BookingService bookingService = new BookingService(screen);

        Thread t1 = new Thread(() -> {
            boolean success = bookingService.bookSeat(1, 1, 1);
            System.out.println(Thread.currentThread().getName() + " booking seat 1: " + success);
        },"User1");

        Thread t2 = new Thread(() -> {
            boolean success = bookingService.bookSeat(1, 1, 1);
            System.out.println(Thread.currentThread().getName() + " booking seat 1: " + success);
        },"User2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
//        System.out.println("Booking seat"+ bookingService.bookSeat(1,1,1));
//        System.out.println("Booking seat" + bookingService.bookSeat(2,1,1));
//
//        System.out.println("Available seats: " + bookingService.getAvailableSeats(1));

    }
}