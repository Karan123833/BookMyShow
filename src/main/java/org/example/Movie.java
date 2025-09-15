package org.example;

public class Movie {
    private final Integer id;
    private final String name;
    private final String language;

    public Movie(Integer id, String name, String language) {
        this.id = id;
        this.name = name;
        this.language = language;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
