package org.example;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BookingService {
    private final Screen screen;
    private final Map<Integer, Show> shows = new HashMap<>();
    private final Map<Integer, Set<Integer>> bookedSeatsByShow = new ConcurrentHashMap<>();
    private final Map<Integer, Object> showLocks = new ConcurrentHashMap<>();

    public BookingService(Screen screen) {
        this.screen = screen;
        for(Show s : screen.getShows()) {
            shows.put(s.getShowId(), s);
            bookedSeatsByShow.put(s.getShowId(), ConcurrentHashMap.newKeySet());
            showLocks.put(s.getShowId(), new Object());
        }
    }

    public boolean bookSeat(Integer userId, Integer showId, Integer seatId) {
        Show show = shows.get(showId);

        Object lock = showLocks.computeIfAbsent(showId, k-> new Object());

        synchronized (lock) {
            Set<Integer> booked = bookedSeatsByShow.computeIfAbsent(showId, k->new HashSet<>());
            if(booked.contains(seatId)) {
                return false;
            }
            booked.add(seatId);
            return true;
        }
    }

    public boolean cancelSeat(Integer userId, Integer showId, Integer seatId) {
        Show show = shows.get(showId);
        Object lock = showLocks.computeIfAbsent(showId, k-> new Object());
        synchronized (lock) {
            Set<Integer> booked = bookedSeatsByShow.get(showId);
            if(booked == null || !booked.contains(seatId)) return false;
            if(booked.contains(seatId)) {
                booked.remove(seatId);
            }
            return true;
        }
    }

    public List<Integer> getAvailableSeats(Integer showId) {
        Show show = shows.get(showId);

        Set<Integer> snapShot;
        synchronized (this) {
            snapShot = new HashSet<>(bookedSeatsByShow.getOrDefault(showId, Collections.emptySet()));
        }

        List<Integer> available = new ArrayList<>();
        for(int i=1;i<=10;i++) {
            if(snapShot.contains(i)) {
                continue;
            }
            else {
                available.add(i);
            }
        }
        // seats we can use this in screen we can expose the seats total if we want
        return available;
    }
}
