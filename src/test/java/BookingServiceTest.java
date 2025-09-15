//how our coding is extensible also lets say if we want to add something hwo it will be handled by minimal code change


import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class BookingServiceTest {
    // my working will include my full energy will go in the coding plus gym right now until naukri then i will do the cmplkete forming of the things what i can do at my besy aboiut ai
    private BookingService bookingService;
    private Show show;
    private Show show1;

    @BeforeEach
    void setup() {
        Screen screen = new Screen(2,new ArrayList<>());
        List<Seat> seats = new ArrayList<>();
        List<Seat> seats1 = new ArrayList<>();
        for(int i=1;i<=10;i++) {
            seats.add(new Seat(i, "A"+i,i));
        }
        show = new Show(1, new Movie(2,"MHUNA","Hindi"), seats);
        show1 = new Show(2,new Movie(2,"jdnjn","english"),seats1);

        screen.addShow(show);
        screen.addShow(show1);
        bookingService = new BookingService(screen);
    }

    @Test
    public void testBookSeat() {
        assertTrue(bookingService.bookSeat(1,1,1));
    }

    @Test
    void testDoubleBookingFails() {
        bookingService.bookSeat(1, 1,1);

        assertTrue(bookingService.bookSeat(2,2,1));
    }

    @Test
    public void testConcurrencyForSameSeat() throws InterruptedException{
        int seatId = 1;
        int threads = 20;
        ExecutorService ex = Executors.newFixedThreadPool(threads);
        CountDownLatch latch = new CountDownLatch(1);
        List<Future<Boolean>> results = new ArrayList<>();

        for (int i = 0; i < threads; i++) {
            final int threadNum = i;
            results.add(ex.submit(() -> {
                latch.await(); // wait until all threads are ready
                boolean success = bookingService.bookSeat(1, 1,seatId);
                System.out.println("Thread-" + threadNum +
                        " tried to book seat " + seatId +
                        " => " + (success ? "SUCCESS" : "FAILED"));
                return success;
            }));
        }
        latch.countDown();
        ex.shutdown();
        ex.awaitTermination(5, TimeUnit.SECONDS);

        long successCount = results.stream().filter(f -> {
            try {
                return f.get();
            } catch (Exception e) {
                return false;
            }
        }).count();

        assertEquals(1, successCount);
    }

}
