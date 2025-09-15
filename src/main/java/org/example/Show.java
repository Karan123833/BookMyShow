package org.example;

import java.util.List;

public class Show {
    private final Integer showId;
    private final Movie movie;
    private final List<Seat> seats;

    public Show(Integer showId, Movie movie, List<Seat> seats) {
        this.showId = showId;
        this.movie = movie;
        this.seats = seats;
    }

    public Integer getShowId() {
        return showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}
