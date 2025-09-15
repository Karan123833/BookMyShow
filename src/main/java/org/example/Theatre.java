package org.example;

import java.util.List;

public class Theatre {
    private final Integer id;
    private final String name;
    private final List<Screen> screenList;

    public Theatre(Integer id, String name, List<Screen> screenList) {
        this.id = id;
        this.name = name;
        this.screenList = screenList;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Screen> getScreenList() {
        return screenList;
    }
}
