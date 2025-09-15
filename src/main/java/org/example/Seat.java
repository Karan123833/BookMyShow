package org.example;

public class Seat {
    private final Integer id;
    private final String seatNumber;
    private final Integer row;

    public Seat(Integer id, String seatNumber, Integer row) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.row = row;
    }

    public Integer getId() {
        return id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public Integer getRow() {
        return row;
    }
}
