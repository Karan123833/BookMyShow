package org.example;

import java.util.List;

public class Screen {
    private final Integer id;
    private final List<Show> shows;

    public Screen(Integer id, List<Show> shows) {
        this.id = id;
        this.shows = shows;
    }

    public Integer getId() {
        return id;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void addShow(Show show) {
        shows.add(show);
    }
}
